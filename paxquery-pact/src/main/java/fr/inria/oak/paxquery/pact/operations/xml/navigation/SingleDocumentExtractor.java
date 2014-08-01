/*******************************************************************************
 * Copyright (C) 2013, 2014 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fr.inria.oak.paxquery.pact.operations.xml.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.types.Record;
import fr.inria.oak.paxquery.common.configuration.GlobalConfiguration;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternEdge;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeID;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeIDScheme;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeIDSchemeAssignator;


/**
 * Records extractor using a tree pattern from a XML document.
 * 
 */
public class SingleDocumentExtractor {
	
	private static final Log logger = LogFactory.getLog(SingleDocumentExtractor.class);
	
	
	private XMLStreamReader streamReader;
	
	
	private static final String START_NAMESPACE_DELIMITER = GlobalConfiguration.ConfigurationParameters.START_DELIMITER.getString();
	private static final String END_NAMESPACE_DELIMITER = GlobalConfiguration.ConfigurationParameters.END_DELIMITER.getString();
	
	/**
	 * The xam we currently try to match
	 */
	NavigationTreePattern currentQP;
	// when the xam extractor is used in a multi-xam context,
	// it serves to know one's own position
	int myPosition;

	/**
	 * Stack in which the integer codes of paths get pushed as we open the
	 * corresponding path nodes.
	 */
	Stack<Integer> currentNodes;

	/**
	 * For each query xam node, the associated match stack.
	 */
	HashMap<NavigationTreePatternNode, ExtractorMatchStack> stacksByNodes;

	/**
	 * For each match stack, the associated query xam node.
	 */
	HashMap<ExtractorMatchStack, NavigationTreePatternNode> nodesByStacks;

	/**
	 * For each tag, a ArrayList of stacks associated with this tag. If the tag is
	 * "*", then we obtain exactly the set of stacks associated with
	 * "*"-labeled query xam nodes.
	 */
	HashMap<String, ArrayList<ExtractorMatchStack>> stacksByTag;
	
	/**
	 * The integer code of the last path summary node that was opened (the
	 * current path summary node).
	 */
	int currentPathNo;

	/**
	 * The depth of the current node.
	 */
	int currentDepth;

	/**
	 * To be used to assign identifiers.
	 */
	NodeIDScheme[] schemes;
	boolean[] usefulSchemes;
	HashMap<NavigationTreePatternNode, NodeIDScheme> schemesByNode;

	StringBuilder sb;

	/**
	 * This is the last created match. It is useful for finding out where to
	 * glue content that we just encountered.
	 */
	ExtractorMatch lastCreatedMatch;

	/**
	 * Compute once and for all the set of stacks whose corresponding xam
	 * nodes need Content. This avoids the need to go and check at every step.
	 */
	ExtractorMatchStack[] stacksNeedingContent;
	
	/**
	 * Stacks with the prefixes defined for the scope of each element.
	 * One stack per node match. Each map has pairs <prefix, namespaceuri>.
	 */
	Stack<HashMap<String,String>>[] prefixesDefined;
	
	/**
	 * Stacks with the default namespaces for the scope of each element.
	 * One stack per node match.
	 */
	Stack<String>[] defaultNamespaces;

	/**
	 * The number of stacks which will need content.
	 */
	int numberOfStacksNeedingContent;

	/**
	 * Compute once and for all the set of stacks whose corresponding xam
	 * nodes need Value. This avoids the need to go and check at every step.
	 */
	ExtractorMatchStack[] stacksNeedingValue;

	/**
	 * The number of stacks which will need Value.
	 */
	int numberOfStacksNeedingValue;

	/**
	 * The builder will produce (nested) tuples from the matches extracted from
	 * the document
	 */
	RecordBuilder builder;

	/**
	 * Result vector -- this is used by the nested tuple builder
	 */
	ArrayList<Record> vTuples;

	/**
	 * StringBuffer that contains temporary characters.
	 */
	StringBuilder characterSB;

	HashMap<NavigationTreePatternNode, ArrayList<String>> childrenContexts;
	HashMap<NavigationTreePatternNode, ArrayList<String>> currentContexts;
	
	
	
	public void setPosition(int i){
		this.myPosition = i;
	}


	/**
	 * Not called currently, since it is the DocumentTuplesMultiExtractor which drives the process.
	 * 
	 * This method matches the XAM xam obtained from the query against the
	 * XML file XMLFileName
	 * 
	 * @param qp
	 * @param DG_XML
	 * @throws SummaryException
	 */
	public SingleDocumentExtractor(NavigationTreePattern qp, XMLStreamReader xmlReader) {
		this.currentQP = qp;
		this.streamReader = xmlReader;
		this.builder = new RecordBuilder();
				
		this.sb = new StringBuilder();
		this.characterSB = new StringBuilder();

		this.stacksByNodes = new HashMap<NavigationTreePatternNode, ExtractorMatchStack>();
		this.nodesByStacks = new HashMap<ExtractorMatchStack, NavigationTreePatternNode>();
		this.currentNodes = new Stack<Integer>();
		this.stacksByTag = new HashMap<String, ArrayList<ExtractorMatchStack>>();
		this.schemesByNode = new HashMap<NavigationTreePatternNode, NodeIDScheme>();

		this.childrenContexts = new HashMap<NavigationTreePatternNode, ArrayList<String>>();
		this.currentContexts = new HashMap<NavigationTreePatternNode, ArrayList<String>>();
		
		this.vTuples = new ArrayList<Record>();

		this.stacksNeedingContent = new ExtractorMatchStack[qp.getNodesNo()];
		this.prefixesDefined = new Stack[qp.getNodesNo()];
		this.defaultNamespaces = new Stack[qp.getNodesNo()];
		this.numberOfStacksNeedingContent = 0;
		this.stacksNeedingValue = new ExtractorMatchStack[qp.getNodesNo()];
		this.numberOfStacksNeedingValue = 0;

		NavigationTreePatternNode root = qp.getRoot();
		createStacks(root);
		this.currentContexts.put(root, this.childrenContexts.get(root));
		this.setSchemes(qp.getRoot());
		schemesBeginDocument();
	
		this.builder.setSchemesByNodes(this.schemesByNode);
		this.builder.setStacksByNodes(this.stacksByNodes);
	
		this.currentPathNo = -1;
		this.myPosition = -1;		
	}

	/**
	 * This method handles a "start element" event. In this context, a "start
	 * element" means a "start path summary node". What must be done:
	 * 
	 * First, check if the context is appropriate for creating a match out of
	 * this path summary node. This means: either the element matches an
	 * (almost) root node in the query xam; or, the element matches a query
	 * xam node which has a parent, and the stack corresponding to the
	 * parent contains a currently open match, and that currently open match
	 * satisfies the parent/child resp. ancestor-descendant relationship with
	 * the current match.
	 * 
	 * Second, if the context is appropriate, then attach the element to the
	 * correct stack.
	 * 
	 * If this stack contained an open match, then connect the open match to
	 * this match.
	 */
	public final void startElement() {		
		final String namespaceuri = this.streamReader.getNamespaceURI() != null ?
				this.streamReader.getNamespaceURI() :
				"";
		final String localName = this.streamReader.getLocalName();
		final String qName = this.streamReader.getPrefix() != null && this.streamReader.getPrefix() != "" ? 
    			this.streamReader.getPrefix() + ":" + this.streamReader.getLocalName() :
				this.streamReader.getLocalName();
    	   	
    	this.startElement(namespaceuri, localName, qName);
    	
		for (int i = 0; i < this.streamReader.getAttributeCount(); i++) {
			final String attNamespaceuri = this.streamReader.getAttributeNamespace(i) != null ?
					this.streamReader.getAttributeNamespace(i) :
					"";
			final String attLocalName = this.streamReader.getAttributeLocalName(i);
			final String attQName = this.streamReader.getAttributePrefix(i) != null && this.streamReader.getAttributePrefix(i) != "" ?
				this.streamReader.getAttributePrefix(i) + ":" + this.streamReader.getAttributeLocalName(i) :
				this.streamReader.getAttributeLocalName(i);
			final String attValue = this.streamReader.getAttributeValue(i);
		
			this.beginAttribute(attNamespaceuri, attLocalName, attQName, attValue);
			this.endAttribute(attNamespaceuri, attLocalName, attQName);
		}
	}
	
	
	private final void startElement(final String namespaceuri, final String localName, final String qName) {		
		// creating word elements
		// we only create them if we are currently looking for word elements,
		// that is, if there is an open match on some stack, which needs a word child
		//createWordElements();
		
		//Parameters.logger.debug("DataExtractor:beginElement " + localName);
		sb = new StringBuilder();
	
		//Parameters.logger.debug("\n<" + localName + ">");
	
		// processing element IDs
		schemesNotifyBegin(localName);
		
		// update current path, the stack of nodes, and the current depth
		currentPathNo++;
		currentNodes.push(new Integer(currentPathNo));
		currentDepth++;
	
		// In this vector, we collect all matches produced for this node.
		ArrayList<ExtractorMatch> matchesForThisNode = new ArrayList<ExtractorMatch>();
	
		//Parameters.logger.debug("Looking for stacks...");
		
		StringBuilder completeTagBuilder = new StringBuilder(START_NAMESPACE_DELIMITER.length()+namespaceuri.length()+END_NAMESPACE_DELIMITER.length()+localName.length());
		completeTagBuilder.append(START_NAMESPACE_DELIMITER).append(namespaceuri).append(END_NAMESPACE_DELIMITER).append(localName);
		final String completeTag = completeTagBuilder.toString();
		// For each stacks that may be interested in this node, try to produce
		// matches
		Iterator<ExtractorMatchStack> it1 = stacksCompatibleWithTag(completeTag);
		while (it1.hasNext()) {
			ExtractorMatchStack s = it1.next();
			if (s.isAttribute) {
				continue;
			}
	
			// the query xam node for this stack (and match)
			NavigationTreePatternNode nodeForThisMatch = nodesByStacks.get(s);
	
			//Parameters.logger.debug("\nLooking at stack "+ s.hashCode()+ " "+ s.tag+ " for "+ localName+ " "+ currentPathNo);
	
			// for each of these stacks, we may produce a match,
			// with a link to its parent match,
			// and perhaps to its self-parent (parent in the same stack).
	
			ExtractorMatchStack sPar = s.parentStack;
			ExtractorMatch parentMatch = null;
			if (sPar != null) {
				parentMatch = sPar.dnop;
				if (parentMatch != null) {
					if (parentMatch.no == currentPathNo) {
						//Parameters.logger.debug(
						//	"This is not the real parent, but the same match just pushed");
						parentMatch = parentMatch.ownParent;
					}
				}
			}
			
			// we are not sure if we keep this match
			boolean keep = false;
	
			//Parameters.logger.debug("Finding out whether to keep...");
	
			if (sPar != null) {
				//Parameters.logger.debug("PARENT STACK NOT NULL");
				if (parentMatch != null) {
					//Parameters.logger.debug("PARENT ENTRY NOT NULL "+ parentMatch.no+ " "+ parentMatch.ownPath);
	
					// establish if we have a parent-child or ancestor/desc
					// relationship in the query
					// between the current node and its parent
	
					// first, find the edge connecting the current query node
					// to its parent.
					// n1 is parent of the current query node:
					NavigationTreePatternNode n1 = nodesByStacks.get(sPar);
					// check for the edge uniting it with the node for this
					// match
					Iterator<NavigationTreePatternEdge> itEdge = n1.getEdges().iterator();
					boolean foundEdge = false;
					NavigationTreePatternEdge theEdge = null;
					while (itEdge.hasNext() && !foundEdge) {
						theEdge = itEdge.next();
						if (theEdge.n2 == nodeForThisMatch) {
							foundEdge = true;
						} else {
							theEdge = null;
						}
					}
	
					// now verify if the edge is of parent or ancestor type
					if (theEdge.isParent()) {
						// parent-child
						//Parameters.logger.info("PARENT-CHILD EDGE WITH THE PARENT");
						// is the current node one level below the parent match
						// ?
						//Parameters.logger.debug("Current depth is "+ currentDepth+ " parent match depth is "+ parentMatch.depth);
						if (parentMatch.depth + 1 == currentDepth) {
							//Parameters.logger.info("CURRENT NODE AT RIGHT DEPTH WRT PARENT MATCH");
							keep = true;
						} else {
							//Parameters.logger.info("CURRENT NODE AT WRONG DEPTH WRT PARENT MATCH");
						}
					} else {
						// ancestor-descendant, check descendant is below
						// ancestor
						//Parameters.logger.debug("ANCESTOR-DESCENDENT EDGE WITH THE PARENT");
						if (parentMatch.depth < currentDepth) {
							keep = true;
						}
					}
				} // parentMatch != null
			} // sPar != null
			else 
			{
				// sPar == null: this element should not have a parent
				// so, we must only check the ancestor-desc or parent-child
				// story
				NavigationTreePatternNode theRoot = currentQP.getRoot();
				NavigationTreePatternEdge theEdge = theRoot.getEdges().get(0);
				if (theEdge.isParent()) 
				{
					// check if the current node appears at depth 1, as
					// required
					if (currentDepth == 1) 
					{
						keep = true;
					}
				} 
				else 
				{
					// no depth condition applies
					keep = true;
				}
	
			}
	
			// now we know that this element appears in the right context,
			// that is, it satisfies all conditions "upwards" (on the path to
			// the root).
			if (keep) 
			{
				// create the match
				ExtractorMatch thisMatch = new ExtractorMatch(currentPathNo, parentMatch, currentDepth, completeTag);
	
				lastCreatedMatch = thisMatch;
					
				if (parentMatch != null) {
					parentMatch.addChild(thisMatch, s);
				}
	
				if (nodeForThisMatch.storesTag() || nodeForThisMatch.selectsTag()) {
					thisMatch.setTag(completeTag);
				}
	
				// push this match
				s.push(thisMatch);
				
				// update the last open match on this stack, if needed
				if (s.dnop != null) {
					s.dnop.addOwnChild(thisMatch);
					thisMatch.ownParent = s.dnop;
					s.dnop = thisMatch;
				} else {
					s.dnop = thisMatch;
				}

				matchesForThisNode.add(thisMatch);
				
				this.currentContexts.put(nodeForThisMatch, this.childrenContexts.get(nodeForThisMatch));	
			}
		}
	
		// If it is not a word element we add it into the Cont stacks.
		// Note that we have made the convention that only the word
		// elements (words that have been converted into elements) can 
		// start with _
		if(localName.charAt(0) != '_')
			addBeginElementToContStacks(namespaceuri, localName, qName);
	}
	

	/**
	 * Attributes are processed in a manner very similar to elements. A notable
	 * difference is the fact that nothing is done to propagate the attribute
	 * name and value on other node's contents, because the parent element has
	 * already done that, nor are the attribute name and value propagate on
	 * some node's value, because they do not belong to some other node value.
	 * 
	 * @param namespaceuri The Namespace URI, or the empty string if the
	 *        attribute has no Namespace URI.
	 * @param localName The local name (without prefix).
	 * @param qName The qualified name (with prefix).
	 * @param attValue The value of the attribute.
	 */
	private final void beginAttribute(final String namespaceuri, final String localName, final String qName, final String attValue) {		
		//		processing element IDs
		schemesNotifyBegin(localName);
	
		// update current path, the stack of nodes, and the current depth
		currentPathNo++;
		currentNodes.push(new Integer(currentPathNo));
		currentDepth++;
	
		// In this vector, we collect all matches produced for this node.
		ArrayList<ExtractorMatch> matchesForThisNode = new ArrayList<ExtractorMatch>();
	
		StringBuilder completeTagBuilder = new StringBuilder(START_NAMESPACE_DELIMITER.length()+namespaceuri.length()+END_NAMESPACE_DELIMITER.length()+localName.length());
		completeTagBuilder.append(START_NAMESPACE_DELIMITER).append(namespaceuri).append(END_NAMESPACE_DELIMITER).append(localName);
		final String completeTag = completeTagBuilder.toString();
		// For each stacks that may be interested in this node, try to produce
		// matches
		Iterator<ExtractorMatchStack> it1 = stacksCompatibleWithTag(completeTag);
		while (it1.hasNext()) {
			ExtractorMatchStack s = it1.next();
			if (!s.isAttribute) {
				continue;
			}
	
			// the query xam node for this stack (and match)
			NavigationTreePatternNode nodeForThisMatch = nodesByStacks.get(s);
		
			// for each of these stacks, we may produce a match,
			// with a link to its parent match,
			// and perhaps to its self-parent (parent in the same stack).
			ExtractorMatchStack sPar = s.parentStack;
			ExtractorMatch parentMatch = null;
			if (sPar != null) {
				parentMatch = sPar.dnop;
			}
	
			// we are not sure if we keep this match
			boolean keep = false;
	
			if (sPar != null) {
				//Parameters.logger.debug("PARENT STACK NOT NULL");
				if (parentMatch != null) {
					//Parameters.logger.debug("PARENT ENTRY NOT NULL");
	
					// establish if we have a parent-child or ancestor/desc
					// relationship in the query
					// between the current node and its parent
	
					// first, find the edge connecting the current query node
					// to its parent.
					// n1 is parent of the current query node:
					NavigationTreePatternNode n1 = nodesByStacks.get(sPar);
					// check for the edge uniting it with the node for this
					// match
					Iterator<NavigationTreePatternEdge> itEdge = n1.getEdges().iterator();
					boolean foundEdge = false;
					NavigationTreePatternEdge theEdge = null;
					while (itEdge.hasNext() && !foundEdge) {
						theEdge = itEdge.next();
						if (theEdge.n2 == nodeForThisMatch) {
							foundEdge = true;
						} else {
							theEdge = null;
						}
					}
	
					// now verify if the edge is of parent or ancestor type
					if (theEdge.isParent()) {
						// parent-child
						if (parentMatch.depth + 1 == currentDepth) {
							keep = true;
						}
					} else {
						// ancestor-descendant, no depth condition is needed
						if (parentMatch.depth < currentDepth) {
							keep = true;
						}
					}
				} // parentMatch != null
			} // sPar != null
			else {
				// sPar == null: this element should not have a parent
				// so, we must only check the ancestor-desc or parent-child
				// story
				NavigationTreePatternNode theRoot = currentQP.getRoot();
				NavigationTreePatternEdge theEdge = theRoot.getEdges().get(0);
				if (theEdge.isParent()) {
					// check if the current node appears at depth 1, as
					// required
					if (currentDepth == 1) {
						keep = true;
					}
				} else {
					// no depth condition applies
					keep = true;
				}
			}
	
			// now we know that this element appears in the right context,
			// that is, it satisfies all conditions "upwards" (on the path to
			// the root).
			if (keep) { // create the match
				ExtractorMatch thisMatch = new ExtractorMatch(currentPathNo, parentMatch, currentDepth, completeTag);
	
				lastCreatedMatch = thisMatch;
	
				if (parentMatch != null) {
					parentMatch.addChild(thisMatch, s);
				}
	
				if (nodeForThisMatch.storesTag() || nodeForThisMatch.selectsTag()) {
					thisMatch.setTag(completeTag);
				}
	
				if (nodeForThisMatch.storesValue() || nodeForThisMatch.selectsValue()) {
					thisMatch.setVal(attValue);
				}
	
				if (nodeForThisMatch.storesContent()) {
					if(qName.contains(":"))
						thisMatch.setContent(qName + "=\"" + attValue + "\" xmlns:" + qName.substring(0,qName.indexOf(":")) + "=\"" + namespaceuri + "\"");
					else if(namespaceuri.compareTo("") != 0)
						thisMatch.setContent(qName + "=\"" + attValue + "\" xmlns=\"" + namespaceuri + "\"");
					else
						thisMatch.setContent(qName + "=\"" + attValue + "\"");
				}
				// push this match
				s.push(thisMatch);
				
				this.currentContexts.put(nodeForThisMatch, this.childrenContexts.get(nodeForThisMatch));
	
				// update the last open match on this stack, if needed
				if (s.dnop != null) {
					s.dnop.addOwnChild(thisMatch);
					thisMatch.ownParent = s.dnop;
					s.dnop = thisMatch;
				} else {
					s.dnop = thisMatch;
				}

				matchesForThisNode.add(thisMatch);
	
			}
		}
	}

	public final void characters() {
		char ch[] = this.streamReader.getTextCharacters();
		int start = this.streamReader.getTextStart();
		int length = this.streamReader.getTextLength();
		
		if(this.numberOfStacksNeedingContent > 0){
			addTextToContStacks(ch, start, length);
		}
		if (this.numberOfStacksNeedingValue > 0){
			addTextToValueStack(ch, start, length);
		}
		
		// We store all the text of the parsed document in the following
		// stringBuffer. When there is a startElement or endElement
		// call the contents of the characterSB are split into words and
		// are created "word elements" (look createWordElements method
		// for more information). 
		characterSB.append(ch, start, length);
	}

	/**
	 * This method is called by startElement and endElement. The purpose is
	 * to take the contents of the characterSB (which contains the parsed
	 * document text since the last time that createWordElements was called),
	 * remove punctuation points, split it into words and for each word to
	 * create a new element.
	 */
	void createWordElements()
	{
		if(characterSB.length() == 0)
			return;
		
		String tempStr = characterSB.toString();
		characterSB = new StringBuilder();
		tempStr = tempStr.replaceAll("[^a-zA-Z0-9]", " ");
		
		StringTokenizer stk = new StringTokenizer(tempStr);
		while(stk.hasMoreTokens())
		{
			String elementName = "_" + stk.nextToken();
			startElement("", elementName, elementName);
			endElement("", elementName, elementName);
		}
	}

	/**
	 * This handles the "end element" events. If this element did not produce a
	 * match, nothing to do (just adjust the current path, depth, stack etc.)
	 * If the element did produce matches, check if each match had encountered
	 * all its required descendants by this time. If a match does not have all
	 * required child matches, mark it as erased.
	 */
	public final void endElement() {
		final String namespaceuri = this.streamReader.getNamespaceURI() != null ?
				this.streamReader.getNamespaceURI() :
				"";
		final String localName = this.streamReader.getLocalName();
		final String qName = this.streamReader.getPrefix() != null && this.streamReader.getPrefix() != "" ? 
    			this.streamReader.getPrefix() + ":" + this.streamReader.getLocalName() :
				this.streamReader.getLocalName();
    	
    	this.endElement(namespaceuri, localName, qName);
	}
	
	
	private final void endElement(final String namespaceuri, final String localName, final String qName) {
		schemesNotifyEnd();
	
		// If it is not a word element we add it into the Cont stacks.
		// Note that we have made the convention that only the word
		// elements (words that have been converted into elements) can 
		// start with _
		if(localName.charAt(0) != '_')
			addEndElementToContStacks(qName);
	
		//Parameters.logger.debug("Getting ending node number");
		int endingNode = currentNodes.pop().intValue();
	
		StringBuilder completeTagBuilder = new StringBuilder(START_NAMESPACE_DELIMITER.length()+namespaceuri.length()+END_NAMESPACE_DELIMITER.length()+localName.length());
		completeTagBuilder.append(START_NAMESPACE_DELIMITER).append(namespaceuri).append(END_NAMESPACE_DELIMITER).append(localName);
		final String completeTag = completeTagBuilder.toString();
		Iterator<ExtractorMatchStack> it1 = stacksCompatibleWithTag(completeTag);
		while (it1.hasNext()) {
			ExtractorMatchStack s = it1.next();

			if (s.isAttribute) {
				continue;
			}
	
			// getting the ElementID for this element
			NodeID currentElementID = null;
			NavigationTreePatternNode pn = this.nodesByStacks.get(s);
	
			if (pn.storesID()) {
				NodeIDScheme sch = this.schemesByNode.get(this.nodesByStacks.get(s));
				currentElementID = sch.getLastID();
			}
			
			ExtractorMatch em = s.dnop;
			if (em != null) {
			if (em.no == endingNode) {
					if (pn.storesID()) {
						em.setID(currentElementID);
					}
					
					checkPruneAndFillIn(s, endingNode);
					
					NavigationTreePatternNode realPatternRoot = ( (NavigationTreePatternEdge) (this.currentQP.getRoot().getEdges().get(0))).n2;

					if (this.stacksByNodes.get(realPatternRoot) == s) {
						if(logger.isDebugEnabled())
							logger.debug("Associated to the real xam root we have the stack named "+ s.tag);
						if (!em.erased) {
							if(logger.isDebugEnabled())
								logger.debug("ExtractorMatch " + em.tag + " not erased ! Producing tuples");

							if (myPosition >= 0){								
								builder.produceTuples(em, realPatternRoot, vTuples, this.currentQP, this.myPosition);
							}
							else{
								builder.produceTuples(em, realPatternRoot, vTuples, this.currentQP);
							}

							// we cannot pop em, because it may hold the
							// children that another
							// match (a self-ancestor of em) needs.
							// we can pop it iff the self-parent is null.	
							if (em.ownParent == null) {
								recursivePop(em, s);
							}
						} else {
							recursivePop(em, s);
							em = null;
						}
					}
				}
			}
		}
	
		currentDepth--;
	}

	/**
	 * Attributes are processed in a manner very similar to elements. A notable
	 * difference is that no check-and-prune is performed, since attribute
	 * nodes do not have children in the XAM.
	 * 
	 * @param namespaceuri The Namespace URI, or the empty string if the
	 *        attribute has no Namespace URI.
	 * @param localName The local name (without prefix).
	 * @param qName The qualified name (with prefix).
	 */
	private final void endAttribute(final String namespaceuri, final String localName, final String qName) {
		schemesNotifyEnd();
	
		int endingNode = currentNodes.pop().intValue();
	
		StringBuilder completeTagBuilder = new StringBuilder(START_NAMESPACE_DELIMITER.length()+namespaceuri.length()+END_NAMESPACE_DELIMITER.length()+localName.length());
		completeTagBuilder.append(START_NAMESPACE_DELIMITER).append(namespaceuri).append(END_NAMESPACE_DELIMITER).append(localName);
		final String completeTag = completeTagBuilder.toString();
		Iterator<ExtractorMatchStack> it1 = stacksCompatibleWithTag(completeTag);
		while (it1.hasNext()) {
			ExtractorMatchStack s = it1.next();
			if (!s.isAttribute) {
				continue;
			}
			
			ExtractorMatch em = s.dnop;
	
			/**
			 * getting the ElementID for this attribute
			 */
			NodeIDScheme sch = this.schemesByNode.get(this.nodesByStacks.get(s));
			if (sch != null) {
				NodeID currentID = sch.getLastID();
				if (em != null) {
					if (em.no == endingNode) {
						em.setID(currentID);
					}
				}
			}
			checkPruneAndFillIn(s, endingNode);
	
			NavigationTreePatternNode realPatternRoot = this.currentQP.getRoot().getEdges().get(0).n2;
			if (this.stacksByNodes.get(realPatternRoot) == s) {
				if (!em.erased) {
					if (myPosition >=0){
						builder.produceTuples(em, realPatternRoot, vTuples, this.currentQP, this.myPosition);
					}
					else{
						builder.produceTuples(em, realPatternRoot, vTuples, this.currentQP);
					}
					if (em.ownParent == null) {
						recursivePop(em, s);
					}
				} else {
					recursivePop(em, s);
					em = null;
				}
			}
		}
	
		currentDepth--;
	}

	/**
	 * Checks if the match produced by the current node, on the stack s, has
	 * all required child matches (that is, at least a required child match for
	 * each required child).
	 * 
	 * It also fills in the match with the children that it had by transitivity
	 * only
	 * 
	 * @param s
	 * @param endingNode
	 */
	private final void checkPruneAndFillIn(ExtractorMatchStack s, int endingNode) {
		ExtractorMatch se = s.findEntry(endingNode);
	
		if (se != null) {
			NavigationTreePatternNode pns = this.nodesByStacks.get(s);
	
			// this means all required children have been matched
			boolean childrenPresent = true;
	
			boolean correctValue = true;
			// checking value condition first
			if (pns.selectsValue()) {
				if(pns.getStringValue() != null) {
					String thisVal = pns.getStringValue();
					if (se.getVal() == null) {
						correctValue = false;
					} else {
						if (thisVal.startsWith("~")) {
							Pattern p = Pattern.compile("(^|\\s+|[^a-zA-Z0-9]+)" + thisVal.substring(1, thisVal.length()) + "($|\\s+|[^a-zA-Z0-9]+)", Pattern.MULTILINE);
							Matcher m = p.matcher(se.getVal());
							if (!m.find())
								correctValue = false;
						}
						else {
							String otherVal = se.getVal();
							switch(pns.getSelectOnValuePredicate()) {
								case PREDICATE_EQUAL:
									correctValue = otherVal.compareTo(thisVal) == 0;
									break;
								case PREDICATE_GREATEROREQUALTHAN:
									correctValue = otherVal.compareTo(thisVal) >= 0;
									break;
								case PREDICATE_GREATERTHAN:
									correctValue = otherVal.compareTo(thisVal) > 0;
									break;
								case PREDICATE_NOTEQUAL:
									correctValue = otherVal.compareTo(thisVal) != 0;
									break;
								case PREDICATE_SMALLEROREQUALTHAN:
									correctValue = otherVal.compareTo(thisVal) <= 0;
									break;
								case PREDICATE_SMALLERTHAN:
									correctValue = otherVal.compareTo(thisVal) < 0;
									break;
								default:
									logger.error("Predicate not supported in tree pattern!");
							}
						}
					}
				}
				else {
					double thisVal = pns.getDoubleValue();
					if (se.getVal() == null) {
						correctValue = false;
					} else {
						double otherVal = Double.parseDouble(se.getVal());
						switch(pns.getSelectOnValuePredicate()) {
							case PREDICATE_EQUAL:
								correctValue = otherVal == thisVal;
								break;
							case PREDICATE_GREATEROREQUALTHAN:
								correctValue = otherVal >= thisVal;
								break;
							case PREDICATE_GREATERTHAN:
								correctValue = otherVal > thisVal;
								break;
							case PREDICATE_NOTEQUAL:
								correctValue = otherVal != thisVal;
								break;
							case PREDICATE_SMALLEROREQUALTHAN:
								correctValue = otherVal <= thisVal;
								break;
							case PREDICATE_SMALLERTHAN:
								correctValue = otherVal < thisVal;
								break;
							default:
								logger.error("Predicate not supported in tree pattern!");
						}
					}
				}
			}

			if (correctValue) {
				Iterator<NavigationTreePatternEdge> iChildren = pns.getEdges().iterator();
				while (iChildren.hasNext()) {
					NavigationTreePatternEdge thisEdge = iChildren.next();
	
					// Only if it is not optional
					if (!thisEdge.isOptional()) {
						NavigationTreePatternNode nChild = thisEdge.n2;
						// stack for this child
						ExtractorMatchStack sChild = stacksByNodes.get(nChild);
		
						boolean hasChildInThisStack =
							(findChildInStack(se, sChild, thisEdge.isParent()) != null);
		
						if (!hasChildInThisStack) {
							childrenPresent = false;
						} else {
							boolean thisChildPresent = false;
							ArrayList<ExtractorMatch> o = se.childrenByStack.get(sChild);
							Iterator<ExtractorMatch> itChildrenThisStack = o.iterator();
							while (itChildrenThisStack.hasNext()) {
								ExtractorMatch emChild = itChildrenThisStack.next();
								if (emChild.erased) {
								} else {
									thisChildPresent = true;
								}
							}
							if (!thisChildPresent) {
								childrenPresent = false;
							}
						}
					}
				}
			}
	
			// connecting to the last open match in this stack, if necessary
			if (s.dnop == se) {
				s.dnop = se.ownParent;
			}
			// dropping this match if some required child is absent
			if ((!childrenPresent) || (!correctValue)) {
				se.erased = true;
				// dropping also what is underneath
				s.removeEntry(se);
				
				// clearing context
				if (s.dnop == null){
					this.currentContexts.remove(pns);
				}
				
				// if se has no ownParent, then we can erase all its
				// descendant matches.
				if (se.ownParent == null) {
					// erase se's children
					Iterator<ArrayList<ExtractorMatch>> itChildren =
						se.childrenByStack.values().iterator();
					while (itChildren.hasNext()) {
						Iterator<ExtractorMatch> it4 = itChildren.next().iterator();
						while (it4.hasNext()) {
							ExtractorMatch sChild = it4.next();
							sChild.recErase();
						}
					}
	
				} else { // se.ownParent is not null
					// go see the children and connect them to the ownParent
					// tell the parent that these are its children
					int cnt = 0;
					Iterator<ArrayList<ExtractorMatch>> itChildren =
						se.childrenByStack.values().iterator();
					while (itChildren.hasNext()) {
						cnt += itChildren.next().size();
					}
					if (cnt > 0) {
						itChildren = se.childrenByStack.values().iterator();
						while (itChildren.hasNext()) {
							Iterator<ExtractorMatch> it4 = itChildren.next().iterator();
							while (it4.hasNext()) {
								ExtractorMatch sChild = it4.next();
								ExtractorMatchStack theChildsStack = sChild.theStack;					
								NavigationTreePatternNode sesNode = this.nodesByStacks.get(se.theStack);								
								NavigationTreePatternNode sChildsNode = this.nodesByStacks.get(theChildsStack);
							
								// learn if this matches for the child node were supposed to be direct
								// descendants of their parent:
								boolean wasParentEdge = false;
								if (sesNode.getEdges() != null){
									Iterator<NavigationTreePatternEdge> itEdges = sesNode.getEdges().iterator();
									while (itEdges.hasNext()){
										NavigationTreePatternEdge pe = itEdges.next();
										if (pe.n2 == sChildsNode){
											if (pe.isParent()){
												wasParentEdge = true;
											}
										}
									}
									// now establish if it is OK to reconnect the children to their
									// parent's own parent
									if (!wasParentEdge || (se.ownParent.depth + 1 == sChild.depth)){
										sChild.ownParent = se.ownParent;
										se.ownParent.addChild(sChild, sChild.theStack);
									}
								}
								
							}
						}
					}
				}
			}
		}
	}

	/**
	 * The following two methods are used to set up the ElementID schemes needed for the
	 * xam.
	 * 
	 * @param pn
	 *            the root node of the XAM.
	 */
	final void setSchemes(NavigationTreePatternNode pn) {
		schemes = new NodeIDScheme[2];
		usefulSchemes = new boolean[2];
		for (int i = 0; i < 2; i++) {
			usefulSchemes[i] = false;
		}
		schemes[0] = NodeIDSchemeAssignator.getStructuralScheme();
		schemes[1] = NodeIDSchemeAssignator.getOrderedIntegerIDScheme();
		recSetSchemes(pn);
	}

	private final void recSetSchemes(NavigationTreePatternNode pn) {
		if (pn.storesID()) {
			if(pn.isStructIDType()) {
				schemesByNode.put(pn, schemes[0]);
				usefulSchemes[0] = true;
			}
			if(pn.isOrderIDType()) {
				schemesByNode.put(pn, schemes[1]);
				usefulSchemes[1] = true;
			}
		}
		Iterator<NavigationTreePatternEdge> it = pn.getEdges().iterator();
		while (it.hasNext()) {
			NavigationTreePatternNode pChild = it.next().n2;
			recSetSchemes(pChild);
		}
	}

	final void schemesBeginDocument() {
		for (int i = 0; i < 2; i++) {
			if (schemes[i] != null && usefulSchemes[i]) {
				schemes[i].beginDocument();
			}
		}
	}

	/**
	 * The following two methods allow registering elements begin and end.
	 *  
	 */
	private final void schemesNotifyBegin(String tag) {
		for (int i = 0; i < 2; i++) {
			if (schemes[i] != null && usefulSchemes[i]) {
				schemes[i].beginNode();
			}
		}
	}

	private final void schemesNotifyEnd() {
		for (int i = 0; i < 2; i++) {
			if (schemes[i] != null && usefulSchemes[i]) {
				schemes[i].endNode();
			}
		}
	}

	/**
	 * This method creates the stacks that reflect the query xam. It ignores
	 * the symbolic document root. *
	 * 
	 * @param pn
	 */
	final void createStacks(NavigationTreePatternNode pn) {
		Iterator<NavigationTreePatternEdge> it = pn.getEdges().iterator();
		ArrayList<String> rootContext = new ArrayList<String>();
		while (it.hasNext()) {
			NavigationTreePatternEdge e = it.next();
			NavigationTreePatternNode onlyChild = e.getN2();
			rootContext.add(START_NAMESPACE_DELIMITER + onlyChild.getNamespace() + END_NAMESPACE_DELIMITER + onlyChild.getTag());
			recCreateStacks(onlyChild, null);
		}
		childrenContexts.put(pn, rootContext);
	}

	/**
	 * The actual filling in of the stacks for the query xam.
	 * 
	 * @param pn
	 * @param sParent
	 */
	private final void recCreateStacks(NavigationTreePatternNode pn, ExtractorMatchStack sParent) {		
		ExtractorMatchStack s1 = new ExtractorMatchStack(START_NAMESPACE_DELIMITER + pn.getNamespace() + END_NAMESPACE_DELIMITER + pn.getTag(), pn.isAttribute(), sParent);
	
		stacksByNodes.put(pn, s1);
		nodesByStacks.put(s1, pn);
		if (pn.storesContent()) {
			this.stacksNeedingContent[numberOfStacksNeedingContent] = s1;
			this.prefixesDefined[numberOfStacksNeedingContent] = new Stack<HashMap<String,String>>();
			this.defaultNamespaces[numberOfStacksNeedingContent] = new Stack<String>();
			this.numberOfStacksNeedingContent++;
		}
		if (pn.storesValue() || pn.selectsValue()) {
			this.stacksNeedingValue[numberOfStacksNeedingValue] = s1;
			this.numberOfStacksNeedingValue++;
		}
	
		ArrayList<String> childrenContext = new ArrayList<String>();
		
		ArrayList<ExtractorMatchStack> stacks = stacksByTag.get(START_NAMESPACE_DELIMITER + pn.getNamespace() + END_NAMESPACE_DELIMITER + pn.getTag());
		if (stacks == null) {
			stacks = new ArrayList<ExtractorMatchStack>();
			stacks.add(s1);
			stacksByTag.put(START_NAMESPACE_DELIMITER + pn.getNamespace() + END_NAMESPACE_DELIMITER + pn.getTag(), stacks);
		} else {
			stacks.add(s1);
		}
	
		Iterator<NavigationTreePatternEdge> it = pn.getEdges().iterator();
		while (it.hasNext()) {
			NavigationTreePatternEdge pe = it.next();
			NavigationTreePatternNode n2 = pe.n2;
			childrenContext.add(START_NAMESPACE_DELIMITER + n2.getNamespace() + END_NAMESPACE_DELIMITER + n2.getTag());
			recCreateStacks(n2, s1);
		}

		childrenContexts.put(pn, childrenContext);
	}

	/**
	 * Fetches all stacks of a given tag
	 */
	private final Iterator<ExtractorMatchStack> stacksCompatibleWithTag(String s) {
		ArrayList<ExtractorMatchStack> v = stacksByTag.get(s);
		ArrayList<ExtractorMatchStack> stars = stacksByTag.get("{}*");
		if (stars == null){
			if (v == null){
				return (new ArrayList<ExtractorMatchStack>()).iterator();
			}

			return v.iterator();
		}

		if (v == null) {
			return stars.iterator();
		}

		// v != null and stars != null
		ArrayList<ExtractorMatchStack> vRes = new ArrayList<ExtractorMatchStack>();
		Iterator<ExtractorMatchStack> it = v.iterator();
		while (it.hasNext()) {
			vRes.add(it.next());
		}
		it = stars.iterator();
		while (it.hasNext()) {
			vRes.add(it.next());
		}
		return vRes.iterator();
	}

	/**
	 * Basically it adds the opening tag for an element to some content nodes.
	 * Two arrays have been created specially for this method: prefixesDefined and defaultNamespaces.
	 * Each position of those arrays stays for one element that needs content, so every element that
	 * needs content will have an element of each array.
	 * <a href="#prefixesDefined">prefixesDefined</a> is an array of stacks. Each stack will contain
	 * HashMap<prefix,namespaceuri> objects. The top of the stack will represent the prefixes defined
	 * for the scope of the last xml element that we have examined. When reach the end of an element,
	 * we will pop the top element from the stack.
	 * <a href="#defaultNamespaces">defaultNamespaces</a> is an array of stacks. Each stack will contain
	 * String objects. The top of the stack will represent the default namespace defined for the scope
	 * of the last xml element that we have examined. When reach the end of an element, we will pop the
	 * top element from the stack.
	 * @param namespaceuri The Namespace URI, or the empty string if the
	 *        element has no Namespace URI.
	 * @param localName The local name (without prefix).
	 * @param qName The qualified name (with prefix).
	 * @param attributes The attributes attached to the element.  If
	 *        there are no attributes, it shall be an empty
	 *        Attributes object.
	 */
	private final void addBeginElementToContStacks(String namespaceuri, String localName, String qName) {
		for (int i = 0; i < this.numberOfStacksNeedingContent; i++) {
			ExtractorMatch em = this.stacksNeedingContent[i].dnop;
			if (em != null) {
				sb = new StringBuilder();
				
				HashMap<String,String> prefixes;
				if (this.prefixesDefined[i].size() != 0)
					prefixes = new HashMap<String,String>(this.prefixesDefined[i].peek()); //We create a copy of the prefixes already defined by this element's parent
				else
					prefixes = new HashMap<String,String>(); //If there is not any HashMap in the stack, we will create a new prefixes object
				
				//Variable for knowing if the element defines a new default namespace...
				boolean elementInDefaultNamespace = false;
				if(namespaceuri.compareTo("") == 0) { //Namespace is empty
					//If there is not any default namespace defined for this scope
					if(defaultNamespaces[i].size() == 0 || defaultNamespaces[i].peek().compareTo("") == 0)
						sb.append("<" + localName);
					//Else, there was a default namespace but now there is not anymore, so we have to set it to the new empty value
					else 
						sb.append("<" + localName + " xmlns=\"\"");
					elementInDefaultNamespace = true;
				}
				else if(localName.compareTo(qName) == 0) { //It has a default namespace that is not empty
					//The default namespace is not the same one that the default namespace of his parent, so we have to define a new default namespace
					if(defaultNamespaces[i].size() == 0 || defaultNamespaces[i].peek().compareTo(namespaceuri) != 0)
						sb.append("<" + qName + " xmlns=\"" + namespaceuri + "\"");
					else
						sb.append("<" + qName);	
					elementInDefaultNamespace = true;
				}
				else if(prefixes.containsKey(qName.substring(0,qName.indexOf(":")))) { //From here, we know it is a qualified name with a prefix.
					 //If the prefix has been redefined by this element.
					if(prefixes.get(qName.substring(0,qName.indexOf(":"))).compareTo(namespaceuri) != 0) {
						prefixes.put(qName.substring(0,qName.indexOf(":")), namespaceuri);
						sb.append("<" + qName + " xmlns:" + qName.substring(0,qName.indexOf(":")) + "=\"" + namespaceuri + "\"");
					}
					//Else, the prefix is already defined, so we don't have do no anything with the prefixes HashMap
					else
						sb.append("<" + qName);
				}
				else { //If the prefix has not been defined, then we will do it.
					prefixes.put(qName.substring(0,qName.indexOf(":")), namespaceuri);
					sb.append("<" + qName + " xmlns:" + qName.substring(0,qName.indexOf(":")) + "=\"" + namespaceuri + "\"");			
				}
				
				for (int ii = 0; ii < this.streamReader.getAttributeCount(); ii++) { //Now, for each attribute of this element...
					if(this.streamReader.getAttributePrefix(ii) != null && this.streamReader.getAttributePrefix(ii) != "") { //If the qualified name has a prefix...
						//If the prefix has been already defined for this scope...
						if(prefixes.containsKey(this.streamReader.getAttributePrefix(ii))) {
							//If the prefix has been redefined for this scope...
							if(prefixes.get(this.streamReader.getAttributePrefix(ii)).compareTo(this.streamReader.getAttributeNamespace(ii)) != 0) {
								prefixes.put(this.streamReader.getAttributePrefix(ii), this.streamReader.getAttributeNamespace(ii));
								sb.append(" " + this.streamReader.getAttributePrefix(ii) +":"+this.streamReader.getAttributeLocalName(ii) +
										"=\"" + this.streamReader.getAttributeValue(ii) + "\" xmlns:" + this.streamReader.getAttributePrefix(ii) + "=\"" + this.streamReader.getAttributeNamespace(ii) + "\"");
							}
							//Else, we don't have to do anything with the prefixes list and we just add the attribute to the String
							else
								sb.append(" " + this.streamReader.getAttributePrefix(ii) +":"+this.streamReader.getAttributeLocalName(ii)
										+ "=\"" + this.streamReader.getAttributeValue(ii) + "\"");
						}
						//Otherwise, we will have to add it to the prefixes and define it.
						else {
							prefixes.put(this.streamReader.getAttributePrefix(ii), this.streamReader.getAttributeNamespace(ii));
							sb.append(" " + this.streamReader.getAttributePrefix(ii) +":"+this.streamReader.getAttributeLocalName(ii) +
									"=\"" + this.streamReader.getAttributeValue(ii) + "\" xmlns:" + this.streamReader.getAttributePrefix(ii) + "=\"" + this.streamReader.getAttributeNamespace(ii) + "\"");
						}
					}
					//Else, the attribute does not have a prefix.
					else
						sb.append(" " + this.streamReader.getAttributeLocalName(ii) + "=\"" + this.streamReader.getAttributeValue(ii) + "\"");
	
				}
				sb.append(">");
	
	
				this.prefixesDefined[i].push(prefixes); //We put the prefixes that we have defined in the scope of this element in the stack.
				if(elementInDefaultNamespace) //If the element defines a new default namespace...
					this.defaultNamespaces[i].push(namespaceuri);
				else if(defaultNamespaces[i].empty()) //Else, if this is the first element and it does not define a default namespace...
					this.defaultNamespaces[i].push("");
				else //Else, the default namespace is the same one that in the element's parent
					this.defaultNamespaces[i].push(this.defaultNamespaces[i].peek());
				
				while (em != null) {
					em.addToContent(sb);
					em = em.ownParent;
				}
			}
		}
	}

	/**
	 * Basically it adds the closing tag for an element to some content nodes.
	 * 
	 * @param qName The qualified name (with prefix).
	 */
	private final void addEndElementToContStacks(String qName) {
		for (int i = 0; i < this.numberOfStacksNeedingContent; i++) {
			ExtractorMatch em = this.stacksNeedingContent[i].dnop;
			if (em != null) {
				this.prefixesDefined[i].pop(); //We remove the prefixes defined in this element's scope
				this.defaultNamespaces[i].pop(); //We remove the default namespace defined in this element's scope
				while (em != null) {
					StringBuilder sb = new StringBuilder();
					sb.append("</");
					sb.append(qName);
					sb.append(">");
					em.addToContent(sb);
					em = em.ownParent;
				}
			}
		}
	}

	/**
	 * It adds some text to some content nodes.
	 * 
	 * @param text
	 */
	private final void addTextToContStacks(char[] ch, int start, int length) {
		for (int i = 0; i < this.numberOfStacksNeedingContent; i++) {
			ExtractorMatch em = this.stacksNeedingContent[i].dnop;
			while (em != null) {
				em.addToContent(ch, start, length);
				em = em.ownParent;
			}
		}
	}

	/**
	 * It is called every time the characters method is called. It accumulates
	 * characters in a buffer that will hold the text value of the current element.
	 * The full text value of the current element will only be known after all the
	 * necessary calls to characters, which is at the end of the element.
	 * 
	 * This method only adds text to XAM nodes annotated wit Val.
	 */
	private final void addTextToValueStack(char[] ch, int start, int length) {
		int lastEncounteredNode = this.currentNodes.peek().intValue();
		for (int i = 0; i < this.numberOfStacksNeedingValue; i++) {
			// here it looks who wants the value in order to add it
			ExtractorMatchStack ems = this.stacksNeedingValue[i];
			ExtractorMatch em = ems.dnop;
			if (em != null) {
				/*
				 * The following lines are commented because we want the
				 * val to contain the concatenation of all the text
				 * descendants of the node that is annotated with val label.
				 */
				if(ch.length != 0) {
					em.addToVal(ch, start, length);
				}
				
			}
		}
	}

	private final void showMatchesCount() {
		int ct = 0;
		Iterator<ExtractorMatchStack> it = stacksByNodes.values().iterator();
		while (it.hasNext()) {
			ExtractorMatchStack ms = it.next();
			ct += ms.getEntriesSize();
		}
	}

	private final String displayMatches(NavigationTreePattern qp) {
		return this.displayMatches(qp.getRoot());
	}

	private final void displayMatchesAsStrings(NavigationTreePattern qp) {
		displayMatchesAsStrings(qp.getRoot());
	}

	private final void displayMatchesAsStrings(NavigationTreePatternNode n) {
		if(logger.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append(START_NAMESPACE_DELIMITER + n.getNamespace() + END_NAMESPACE_DELIMITER + n.getTag());
			logger.info(sb.toString());
			Iterator<NavigationTreePatternEdge> it2 = n.getEdges().iterator();
			while (it2.hasNext()) {
				NavigationTreePatternNode e2 = it2.next().getN2();
				displayMatchesAsStrings(e2);
			}
		}
	}

	//so far query node has been matched by number 3
	private final String displayMatches(NavigationTreePatternNode n) {
		StringBuilder sb = new StringBuilder();
		sb.append(START_NAMESPACE_DELIMITER + n.getNamespace() + END_NAMESPACE_DELIMITER + n.getTag());
		logger.info(sb.toString());
		Iterator<NavigationTreePatternEdge> it2 = n.getEdges().iterator();
		while (it2.hasNext()) {
			NavigationTreePatternNode e2 = it2.next().n2;
			sb.append(displayMatches(e2));
		}
		return sb.toString();
	}

	/**
	 * This method eliminates a match from a stack, taking some care to fix the
	 * dnop if needed. The invariant is that elimination is attempted only for a
	 * match whose own parent was null. Thus, we can afford to set the stack's dnop
	 * to null if the dnop was this match.
	 * 
	 * @param s
	 * @param em
	 */
	private void eliminate(ExtractorMatchStack s, ExtractorMatch em) {
		if (s.dnop == em) {
			s.dnop = null;
		}
		s.removeEntry(em);
	}

	/**
	 * This method eliminates the match em, its children and descendants, in other
	 * stacks and in the same stack, from their respective stacks. 
	 * 
	 * @param em
	 * @param s
	 */
	private void recursivePop(ExtractorMatch em, ExtractorMatchStack s) {
		if (em.childrenByStack == null) {
			eliminate(s, em);
		}
		if (em.childrenByStack.size() == 0) {
			eliminate(s, em);
		}
		Iterator<ExtractorMatchStack> it = em.childrenByStack.keySet().iterator();
		while (it.hasNext()) {
			ExtractorMatchStack s2 = it.next();
			ArrayList<ExtractorMatch> v2 = em.childrenByStack.get(s2);
			if (v2 != null) {
				Iterator<ExtractorMatch> it3 = v2.iterator();
				while (it3.hasNext()) {
					ExtractorMatch em3 = it3.next();
					recursivePop(em3, s2);
				}
			}
		}
		Iterator<ExtractorMatch> itc = em.ownChildren.iterator();
		while (itc.hasNext()) {
			ExtractorMatch em4 = itc.next();
			recursivePop(em4, s);
		}
	}

	/**
	 * This method attempts to locate children of em in the stack s, either
	 * directly from em's children, or by transitivity from em's own children
	 * (matches in the same stack, corresponding to ancestors of em). The method
	 * returns em's resulting (potentially enhanced) children in the stack s.
	 * 
	 * @param em
	 *            The match.
	 * @param s
	 *            The stack for which we attempt to find children.
	 * @return the set of the match's children in the stack, if any were found.
	 */
	private final ArrayList<ExtractorMatch> findChildInStack(
		ExtractorMatch em,
		ExtractorMatchStack s, boolean isParent) {

		if (em.childrenByStack == null) {
			return null;
		}
		ArrayList<ExtractorMatch> v = em.childrenByStack.get(s);
	
		if (em.ownChildren != null) {
			Iterator<ExtractorMatch> it = em.ownChildren.iterator();
			while (it.hasNext()) {
				ExtractorMatch emChild = it.next();
				// cannot be null
				ArrayList<ExtractorMatch> vAux = findChildInStack(emChild, s, isParent);
				if (vAux != null) {
					Iterator<ExtractorMatch> iAux = vAux.iterator();
					if (v == null) {
						v = new ArrayList<ExtractorMatch>();
						em.childrenByStack.put(s, v);
					}
					while (iAux.hasNext()) {
						ExtractorMatch o = iAux.next();
						if (v.indexOf(o) == -1) {
							ExtractorMatch emo = o;
							if ((!isParent) || (em.depth + 1 == emo.depth)){
								v.add(o);
							}
						}
					}
				}
			}
		}
		return v;
	}

	public List<Record> getRecords() {
		return vTuples;
	}
}
