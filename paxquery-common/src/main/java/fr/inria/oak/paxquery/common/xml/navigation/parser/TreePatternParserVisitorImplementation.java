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
package fr.inria.oak.paxquery.common.xml.navigation.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.predicates.BasePredicate.PredicateType;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;


/**
 * 
 * 
 * 
 */
public class TreePatternParserVisitorImplementation implements TreePatternParserVisitor {
		
	private static final Log logger = LogFactory.getLog(TreePatternParserVisitorImplementation.class);
	
	private final String startNamespaceDelimiter;
	private final String endNamespaceDelimiter;
	
	/*
	 * A HashMap where all the PatternNodes that appear in the whole XAM file are stored.
	 * The key is the ID with which the nodes appear in the file and the value is the actual PatternNode.
	 */
	private HashMap<Integer, NavigationTreePatternNode> patternNodeMap;
	
	/*
	 * The key of the HashMap is the PatternNode that appears in the Xam file and the value is the
	 * (tree) Pattern to which it belongs.
	 */
	private HashMap<Integer, NavigationTreePattern> patternMap;
	
	/*
	 * Default namespace for the nodes.
	 */
	private String defaultNamespace;
	
	
	public TreePatternParserVisitorImplementation(String startNamespaceDelimiter, String endNamespaceDelimiter) {		
		this.startNamespaceDelimiter = startNamespaceDelimiter;
		this.endNamespaceDelimiter = endNamespaceDelimiter;
	}
	
	public Object visit(SimpleNode node, Object data) {

		return null;
	}

	/**
	 * the JoinedPattern is built from the tree patterns and the value joins specified
	 */
	public Object visit(ASTStart node, Object data) {
		patternNodeMap = new HashMap<Integer, NavigationTreePatternNode>();
		patternMap = new HashMap<Integer, NavigationTreePattern>();
		
		//this is the JoinedPattern that will be return by each call of the parser
//		JoinedPattern joinedPat = new JoinedPattern(SimpleIDGenerator.generateID(IDGeneratorType.XAM_VJ));
		//temp variables for building the patterns and the value-join edges
		Object child = node.jjtGetChild(0);
		NavigationTreePattern navigationTreePattern = (NavigationTreePattern) visit(((ASTTreePatternSpec) child), data);
				
		return navigationTreePattern;
	}
	
	/**
	 * build the XAM representation in memory by gathering the nodes and the
	 * edges return the in-memory tree representation of the XAM
	 */
	public Object visit(ASTTreePatternSpec node, Object data) {
		defaultNamespace = new String("");
		NavigationTreePattern p = new NavigationTreePattern();
		LinkedList<NavigationTreePatternNode> nodes = new LinkedList<NavigationTreePatternNode>();
		boolean fromRoot = false;
		
		try {
			for (int i = 0; i < node.jjtGetNumChildren(); i++) {
				Object child = node.jjtGetChild(i);
				Class c = child.getClass();
				
				if (c.getName().equals(
						"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTNSPEC")) {
					//add all xam nodes to the in-memory tree structure x
					nodes = (LinkedList<NavigationTreePatternNode>) visit(((ASTNSPEC) child), data, nodes);
				} else if (c.getName().equals(
						"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTROOT")) {
					// the first node is the root
					fromRoot=true;
				} else if (c.getName().equals(
						"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTDefaultNamespace")) {
					ASTDefaultNamespace defaultNamespaceNode = (ASTDefaultNamespace) child;
					ASTContent defaultNamespace = (ASTContent) defaultNamespaceNode.jjtGetChild(0);
					this.defaultNamespace = removeQuotes(defaultNamespace.getInfo());
				} else if (c.getName().equals(
						"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTXamOrdered")) {
					// the first node is the root
					p.setOrdered(true);
				} else if (c.getName().equals(
						"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTEdgeSpec")) {
					//add all xam edges to the in-memory tree structure x
					nodes = (LinkedList<NavigationTreePatternNode>) visit(((ASTEdgeSpec) child), data, nodes);
				} else
					return null;//todo: maybe throw an exception instead
			}
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		NavigationTreePatternNode theRoot = new NavigationTreePatternNode("", "", -1, startNamespaceDelimiter, endNamespaceDelimiter);
		theRoot.addEdge(nodes.getFirst(), fromRoot, false, false);
		p.setRoot(theRoot);
		
		//add the PatternNodes with the Patterns to which they belong to the HashMap patternMap
		Iterator<NavigationTreePatternNode> iterPatNode = nodes.iterator();
		while (iterPatNode.hasNext()) {
			patternMap.put(iterPatNode.next().getNodeCode(), p);
		}
		
		return p;
	}
	
	/**
	 * from an AST node of type ASTIDSpec get XAMs id specification and store-it
	 * into the XAM's node
	 */
	public void getIDSpec(ASTIDSpec astNode, NavigationTreePatternNode navigationTreePatternNode) {
		//first part is an ID number
		SimpleNode idType = (SimpleNode) astNode.jjtGetChild(0);
		navigationTreePatternNode.setStoresID(true); //for XAM it was setIDSpecified(boolean)
		//then the type of the ID follows        
		if (idType.toString().equals("Structural"))
			navigationTreePatternNode.setIDType(false,false,true,false);
		else if (idType.toString().equals("Ordered"))
			navigationTreePatternNode.setIDType(false,true,false,false);
		else if (idType.toString().equals("Integer"))
			navigationTreePatternNode.setIDType(true,false,false,false);
		else if (idType.toString().equals("Updating"))
			navigationTreePatternNode.setIDType(false,false,false,true);
		else {
			navigationTreePatternNode.setIDType(false,false,false,false);
			navigationTreePatternNode.setStoresID(false);
		}
		if (astNode.jjtGetNumChildren() == 2) {
			navigationTreePatternNode.setRequiresID(true);
		}else{
			navigationTreePatternNode.setRequiresID(false);
		}

	}

	/**
	 * Remove the " from the tag value specifications
	 */

	public String removeQuotes(String s) {
		if (s.contains("\"")) return s.substring(1, s.length() - 1);
		return s;
	}

	/**
	 * from an AST node of type ASTTagRestriction get XAMs tag restriction
	 * specified as an equality predicate and store-it into the XAM's node (a
	 * tag restriction is a specification [Tag="gogo"] in the XAM) that
	 * restricts the elements stored under the current XAM to those that have
	 * the tagname "gogo"
	 */
	public void getTagRestrictionSpec(ASTTagRestriction astNode, NavigationTreePatternNode navigationTreePatternNode) {
		ASTContent namespace = (ASTContent) astNode.jjtGetChild(0);
		String tagString = removeQuotes(namespace.getInfo());
		if(tagString.contains(startNamespaceDelimiter))
			navigationTreePatternNode.setSelectOnTag(true, tagString.substring(tagString.indexOf(startNamespaceDelimiter)+1, tagString.indexOf(endNamespaceDelimiter)), tagString.substring(tagString.indexOf(endNamespaceDelimiter)+1));
		else
			navigationTreePatternNode.setSelectOnTag(true, this.defaultNamespace, tagString);
	}

	/**
	 * from an AST node of type ASTValRestriction get XAMs value restriction
	 * specified as an equality predicate and store-it into the XAM's node (a
	 * tag restriction is a specification [Val="gogo"] in the XAM) that
	 * restricts the elements stored under the current XAM to those that have
	 * the value (text content or id content to "gogo")
	 */
	public void getValRestrictionSpec(ASTValRestriction astNode, NavigationTreePatternNode navigationTreePatternNode) {
		ASTPredCode restrPredCode = (ASTPredCode) astNode.jjtGetChild(0);
  	  	PredicateType predCode = null;
  	  	if(restrPredCode.getInfo().equals("="))
			predCode = PredicateType.PREDICATE_EQUAL;
		else if(restrPredCode.getInfo().equals("!="))
			predCode = PredicateType.PREDICATE_NOTEQUAL;
		else if(restrPredCode.getInfo().equals(">="))
			predCode = PredicateType.PREDICATE_GREATEROREQUALTHAN;
		else if(restrPredCode.getInfo().equals(">"))
			predCode = PredicateType.PREDICATE_GREATERTHAN;
		else if(restrPredCode.getInfo().equals("<="))
			predCode = PredicateType.PREDICATE_SMALLEROREQUALTHAN;
		else if(restrPredCode.getInfo().equals("<"))
			predCode = PredicateType.PREDICATE_SMALLERTHAN;		
		SimpleNode child = (SimpleNode) astNode.jjtGetChild(1);
		if (child.getClass().getName().equals(
				"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTContent")) {
			ASTContent restrValue = (ASTContent) child;
			navigationTreePatternNode.setSelectOnValue(true,predCode,StringEscapeUtils.unescapeJava(removeQuotes(restrValue.getInfo())));
		} else if (child.getClass().getName().equals(
				"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTMyID")) {
			ASTMyID restrValue = (ASTMyID) child;
			double constant = restrValue.getID();
			navigationTreePatternNode.setSelectOnValue(true,predCode,constant);
		} 
	}

	/**
	 * from an AST node of type ASTVal get XAMs value specification and store-it
	 * in the XAMs node
	 */
	public void getValSpec(ASTVal astNode, NavigationTreePatternNode navigationTreePatternNode) {
		navigationTreePatternNode.setSelectOnValue(false,PredicateType.PREDICATE_EQUAL,"*");
		navigationTreePatternNode.setStoresValue(true);
		//it can have only a Required child
		if (astNode.jjtGetNumChildren() > 0){
			navigationTreePatternNode.setRequiresVal(true);
		}else{
			navigationTreePatternNode.setRequiresVal(false);
		}

	}

	/**
	 * from an AST node of type ASTTag get XAMs tagname specification and
	 * store-it in the XAMs node
	 */
	public void getTagSpec(ASTTag astNode, NavigationTreePatternNode navigationTreePatternNode) {
		navigationTreePatternNode.setSelectOnTag(false, "", "*");
		navigationTreePatternNode.setStoresTag(true);
		//it can have only a Required child
		if (astNode.jjtGetNumChildren() > 0){
			navigationTreePatternNode.setRequiresVal(true);
		}else{
			navigationTreePatternNode.setRequiresVal(false);
		}
	}

	public Object visit(ASTNSPEC node, Object data) {
		return null;
	}

	/**
	 * for a ASTNSPEC node that correspond to a XAM node specification in the
	 * parser build and return the corresponding xamNode (element or attribute)
	 * 
	 * @returns a new xamNode
	 */
	public Object visit(ASTNSPEC node, Object data, LinkedList<NavigationTreePatternNode> nodes) {
		NavigationTreePatternNode ne;
		
		try {
			for (int i = 0; i < node.jjtGetNumChildren(); i++) {
				ne = new NavigationTreePatternNode("", "*",-1, startNamespaceDelimiter, endNamespaceDelimiter);
				//a NE or NA node
				SimpleNode child1 = (SimpleNode) node.jjtGetChild(i);

				//if NA set "attribute" attribute to true
				if ((child1.getClass()).equals(Class
						.forName("fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTNE")))
					ne.setAttribute(false);
				else
					ne.setAttribute(true);

				//all nodes have a node identifier - grab-it
				ASTMyID nodeId = (ASTMyID) child1.jjtGetChild(0);
				ne.setNodeCode(nodeId.getID());

				//for the children of NE test if we have IDSpec/TagRestriction
				// /ValRestriction/Tag.[Req] /Val[.Req]
				for (int j = 1; j < child1.jjtGetNumChildren(); j++) {
					//child2 is an IDSpec TagRestriction Val ValRestriction....
					SimpleNode child2 = (SimpleNode) child1.jjtGetChild(j);
					if (child2.getClass().getName().equals(
							"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTIDSpec")) {
						getIDSpec((ASTIDSpec) child2, ne);
					} else if (child2
							.getClass()
							.getName()
							.equals(
									"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTTagRestriction")) {
						getTagRestrictionSpec((ASTTagRestriction) child2, ne);
					} else if (child2
							.getClass()
							.getName()
							.equals(
									"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTValRestriction")) {
						getValRestrictionSpec((ASTValRestriction) child2, ne);
					}else if (child2
							.getClass()
							.getName()
							.equals(
									"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTTagFull")) {						
						ne.setStoresTag(true); //Update, although actually we are not entering here
						if(((SimpleNode)child2).jjtGetNumChildren()>0)
							ne.setRequiresTag(true);
					}else if (child2
							.getClass()
							.getName()
							.equals(
									"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTValFull")) {
						ne.setStoresValue(true);
						if(((SimpleNode)child2).jjtGetNumChildren()>0)
							ne.setRequiresVal(true);
					}else if (child2.getClass().getName().equals(
							"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTTag")) {
						getTagSpec((ASTTag) child2, ne);
					} else if (child2.getClass().getName().equals(
							"fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTCSpec")) {
						//getSerializedContentSpec((ASTCSpec)child2,ne);
						ne.setStoresContent(true);
					} else {//ASTVal
						getValSpec((ASTVal) child2, ne);
					}
				}
				nodes.add(ne);
				
				//add the PatternNode to the HashMap patternNodeMap
				patternNodeMap.put(ne.getNodeCode(), ne);
			}

			return nodes;
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		return null;

	}

	public Object visit(ASTNE node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTSerializedContent node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTNA node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTOrdered node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTInteger node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTStructural node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTRequired node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTIDSpec node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTTag node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTTagRestriction node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTVal node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTValRestriction node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTContent node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTMyID node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTDescendant node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTChild node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTOuter node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTJoin node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTSemi node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTEdgeSpec node, Object data) {
		return visit((SimpleNode) node, data);
	}

	/**
	 * Get the edge specifications by getting the children of the node that should
	 * be From To Child/Desc Nested Join
	 * 
	 * @param node
	 *                  the ASTNode storing info about the edge
	 * @param data
	 *                  the child subtree
	 * @param x
	 *                  the XAM to be updated
	 * @returns the Xam updated with the informations about the current edge
	 */
	public Object visit(ASTEdgeSpec node, Object data, LinkedList<NavigationTreePatternNode> nodes) {
		//XamEdge xe = new XamEdge();
		NavigationTreePatternNode from;
		NavigationTreePatternNode to;
		boolean parent = false;
		boolean semijoin = false;
		boolean optional = false;
		boolean nested = false;
		
		//a NE or NA node
		ASTMyID fromNode = (ASTMyID) node.jjtGetChild(0);
		ASTMyID toNode = (ASTMyID) node.jjtGetChild(1);
		SimpleNode descendantTypeNode = (SimpleNode) node.jjtGetChild(2);
		from=patternNodeMap.get(fromNode.getID());
		to=patternNodeMap.get(toNode.getID());
		
		try {
			if ((descendantTypeNode.getClass()).equals(Class
					.forName("fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTChild")))
				parent=true;				//xe.setParent(true);
			
			SimpleNode nestingTypeNode = (SimpleNode) node.jjtGetChild(3);
			SimpleNode joinTypeNode = null;
			if ((nestingTypeNode.getClass()).equals(Class
					.forName("fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTNested"))) {
				//xe.setNested(true);
				nested=true;
				joinTypeNode = (SimpleNode) node.jjtGetChild(4);
			} else {
				//xe.setNested(false);
				joinTypeNode = nestingTypeNode;//the
				// nestingTypeNode
				// is absent
				// from the
				// AST
			}
			
			if(joinTypeNode instanceof ASTOuter) {//Outer
				//xe.setJoinType(Constants.OUTER_JOIN);
				optional = true;
			}
			else if (joinTypeNode instanceof ASTJoin) {//Simple
				// Join
				//xe.setJoinType(Constants.JOIN);
			} else {//Semijoin
				//xe.setJoinType(Constants.SEMI_JOIN);
				semijoin = true;
			}
			//xe.setInternalID(SimpleIDGenerator.generateID(Constants.EDGES_KEY));
			if(from.getNodeCode()==to.getNodeCode()){
				Exception e = new Exception("And edge between the same node has been found "+from.getNodeCode());
				throw e;
				
			}
			from.addEdge(to, parent, nested, optional);
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
			
		return nodes;
	}
	
	public Object visit(ASTPredCode node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTNested node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTROOT node, Object data) {
		return visit((SimpleNode) node, data);
	}

	public Object visit(ASTCSpec node, Object data) {
		return null;
	}

	public Object visit(ASTNavigating node, Object data) {
		return null;
	}

	public Object visit(ASTUpdating node, Object data) {
		return null;
	}
	public Object visit(ASTTreePatternOrdered node, Object data) {
		return null;
	}

	public Object visit(ASTTagFull node, Object data) {
		if(node==null)return null;
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			Object child = node.jjtGetChild(i);
			//visit(child,data);
		}
		return null;
	}

	public Object visit(ASTValFull node, Object data) {
		return null;
	}

	public Object visit(ASTDefaultNamespace node, Object data) {
		return null;
	}
	
}
