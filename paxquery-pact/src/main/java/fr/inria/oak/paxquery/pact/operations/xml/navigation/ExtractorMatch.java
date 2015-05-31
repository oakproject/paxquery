/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeID;


/**
 * This class encapsulates the information needed to tell that a given XML node
 * matches a given view node (that is, a match).
 * 
 */
public final class ExtractorMatch {
	
	private static final Log logger = LogFactory.getLog(ExtractorMatch.class);
	
	/**
	 * pre-order for this element in the document.
	 */
	public int no;

	/**
	 * depth counted starting from the root. no and depth should be redundant.
	 */
	public int depth;

	/**
	 * the ID that was constructed and retained from this document
	 */
	NodeID id;

	/**
	 * The tag of the element.
	 */
	public String tag;

	/**
	 * match in the stack associated to the parent of the node to which this
	 * stack is associated.
	 */
	ExtractorMatch parent;

	/**
	 * If, when creating this match, there is another open match in the same
	 * stack, then that match becomes the ownParent of this match.
	 */
	public ExtractorMatch ownParent;

	/**
	 * Children of this match, by the stack in which they are.
	 */
	public HashMap<ExtractorMatchStack, ArrayList<ExtractorMatch>> childrenByStack;

	/**
	 * Children of this match in its own stack.
	 */
	public ArrayList<ExtractorMatch> ownChildren;

	/**
	 * Becomes true when this match has been invalidated due to lack of
	 * required children/descendants.
	 */
	public boolean erased;

	/**
	 * If this match is under a semijoin, we collect in minOccur: the minimum
	 * number of /a1/a2/a3.../ak descendants of elements on the path
	 * /b0/b1/b2/.../bl, where the path /b0/.../bl leads to the nearest
	 * ancestor above a semijoin edge, and the path /b0/b1/.../bl/a1/a2/.../ak
	 * is the path for this match
	 * 
	 * If minOccur >=1 and underSemiJoin, then the node (and its descendants)
	 * are useless
	 */
	int minOccur;

	/**
	 * Each match points to its own stack -- this comes in handy at some point.
	 */
	public ExtractorMatchStack theStack;
	
	private StringBuilder valSB;
	private String val;
	private StringBuilder contSB;
	private String content;
	

	/**
	 * Constructor method.
	 * 
	 * @param no
	 *            The number of the path summary node for which this match is
	 *            created.
	 * @param parent
	 *            The parent match for this one.
	 * @param depth
	 *            The depth of this path summary node in the path summary.
	 */
	public ExtractorMatch(int no, ExtractorMatch parent, int depth, String tag) {
		this.no = no;
		this.depth = depth;
		this.parent = parent;
		this.tag = tag;
		this.childrenByStack = new HashMap<ExtractorMatchStack, ArrayList<ExtractorMatch>>();
		this.ownChildren = new ArrayList<ExtractorMatch>();
		this.erased = false;
		this.minOccur = -1;
		this.val = "";
		this.content = "";
		this.id = null;
		this.contSB = new StringBuilder();
		this.content = null;
		this.valSB = new StringBuilder();
		this.val = null;
	}

	public void setID(NodeID newID) {
		this.id = newID;
	}

	/**
	 * @return the id
	 */
	public NodeID getID() {
		return id;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public String getVal(){
		if (val == null){
			val = valSB.toString();
		}
		return val;
	}

	public void addToVal(char[] ch, int start, int length) {
		this.valSB.append(ch, start, length);
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent(){
		if (content == null){
			content = contSB.toString();
		}
		return content;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the childrenByStack
	 */
	public HashMap<ExtractorMatchStack, ArrayList<ExtractorMatch>> getChildrenByStack() {
		return childrenByStack;
	}

	/**
	 * @return the erased
	 */
	public boolean isErased() {
		return erased;
	}
	
	public void addToContent(StringBuilder add) {
		this.contSB.append(add);
	}
	
	public void addToContent(char[] ch, int start, int length) {
		this.contSB.append(ch, start, length);
	}

	/**
	 * @param childmatch
	 *            A match in another stack.
	 */
	public void addChild(
		ExtractorMatch childExtractorMatch,
		ExtractorMatchStack theStack) {
		ArrayList<ExtractorMatch> v = childrenByStack.get(theStack);
		if (v == null) {
			v = new ArrayList<ExtractorMatch>();
		}
		v.add(childExtractorMatch);
		childrenByStack.put(theStack, v);
	
		//this.children.addElement(childExtractorMatch);
	}

	/**
	 * @param childmatch
	 *            A match in another stack.
	 */
	public void addOwnChild(ExtractorMatch childExtractorMatch) {
		this.ownChildren.add(childExtractorMatch);
	}

	public void displayTree() {
		if(logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder();
			recDisplayTree(sb);
			logger.debug(sb);
		}
	}

	private void recDisplayTree(StringBuilder sb) {
		if (erased) {
			return;
		}
		sb.append(this.no);
		sb.append("\n ");
		sb.append("Tag: " + tag + " ID: " + id + " Val: " + val + " Cont: " + content + "\n");

		int cntChildren = 0;
		Iterator<ArrayList<ExtractorMatch>> iChildren = childrenByStack.values().iterator();
		while (iChildren.hasNext()) {
			cntChildren += iChildren.next().size();
		}

		if (cntChildren > 0) {
			sb.append("(");
		} else {
			sb.append(" ");
		}

		iChildren = childrenByStack.values().iterator();
		while (iChildren.hasNext()) {
			Iterator<ExtractorMatch> it = iChildren.next().iterator();
			while (it.hasNext()) {
				ExtractorMatch se = (ExtractorMatch) it.next();
				se.recDisplayTree(sb);
			}
		}

		if (cntChildren > 0) {
			sb.append(")\n");
		}
	}

	/**
	 * Removes this match from its own stack, and does the same with all its
	 * descendant entries (each one removed from its own stack).
	 */
	public void recErase() {
		this.theStack.removeEntry(this);

		Iterator<ArrayList<ExtractorMatch>> iChildren = childrenByStack.values().iterator();
		while (iChildren.hasNext()) {
			Iterator<ExtractorMatch> it = iChildren.next().iterator();
			while (it.hasNext()) {
				ExtractorMatch child = (ExtractorMatch) it.next();
				child.recErase();
			}
		}
	}
}
