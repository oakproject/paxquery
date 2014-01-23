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
package fr.inria.oak.paxquery.common.xml.nodeidentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a compact dynamic Dewey ID.
 * 
 */
public class CompactDynamicDeweyID implements NodeID, Serializable {
	
	private static final long serialVersionUID = -5832214012082963306L;

	private final static Log logger = LogFactory.getLog(CompactDynamicDeweyID.class);

	public static CompactDynamicDeweyID theNull = new CompactDynamicDeweyID(new int[0], 0, new int[0], "");
	
	/**
	 * The current id
	 */
	int[] path;
	
	/**
	 * the integer describing the last sibling position
	 */
	int n;
	
	/**
	 * The tag at this level
	 */
	int[] tag;
	
	/*
	 * Result Path for this node (ivma algorithm)
	 */
	private LinkedList<CompactDynamicDeweyID> resultPath = new LinkedList<CompactDynamicDeweyID>();
	
	public CompactDynamicDeweyID(int[] path, int[] tag) {		
		this.path = path;
		//int lastDot = s.lastIndexOf('.');
		this.n = path[path.length-1];
		this.tag = tag;
	}
	
	public CompactDynamicDeweyID(int[] path, int n, int[] tag, String xmlTag) {
		logger.debug("COMPACT DYNAMIC DEWEY ID CREATED !");
		if (path.length == 0) {
			this.path = new int[]{n};
		} else {
			int[] tempPath = new int[path.length + 1];
			System.arraycopy(path, 0, tempPath, 0, path.length);
			tempPath[path.length] = n;
			this.path = tempPath;
		}
		if(!xmlTag.equals("")) {
			if (tag.length == 0) {
				this.tag = new int[]{CompactDynamicDeweyScheme.tagDictionary.get(xmlTag)};
			} else {
				this.tag = new int[tag.length + 1];
				System.arraycopy(tag, 0, this.tag, 0, tag.length);
				this.tag[tag.length] = CompactDynamicDeweyScheme.tagDictionary.get(xmlTag);
			}
		}
		this.n = n;
	}
	
	public CompactDynamicDeweyID(int[] path, int[] tag, String xmlTag) {
		logger.debug("COMPACT DYNAMIC DEWEY ID CREATED !");
		this.path = path;
		this.n = path[path.length-1];
		this.tag = tag;
		if(!xmlTag.equals("")) {
			if (tag.length == 0) {
				this.tag = new int[]{CompactDynamicDeweyScheme.tagDictionary.get(xmlTag)};
			} else {
				this.tag = new int[tag.length + 1];
				System.arraycopy(tag, 0, this.tag, 0, tag.length);
				this.tag[tag.length] = CompactDynamicDeweyScheme.tagDictionary.get(xmlTag);
			}
		}
	}
	
	public CompactDynamicDeweyID(ArrayList<Integer> v, ArrayList<Integer> t) {
		int[] tempPath = new int[v.size()];
		for(int i = 0; i<v.size(); i++) {
			Integer n = (Integer) v.get(i);
			tempPath[i] = n;
		}
		this.path = tempPath;
		if (v.size() == 0) {
			this.n = 1;
		} else {
			this.n = ( v.get(v.size() - 1)).intValue();
		}
		int[] tempTag = new int[t.size()];
		for(int i = 0; i<t.size(); i++) {
			Integer n = (Integer) t.get(i);
			tempTag[i] = n;
		}
		this.tag = tempTag;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#getType()
	 */
	public int getType() {
		// Is this right?
		return 0;
	}
	 /*
	  * (non-Javadoc)
	  * @see java.lang.Object#toString()
	  */
	public String toString() {
		if (this == theNull) {
			return "null";
		}
		String path = "";
		for(int i = 0; i<this.path.length-1; i++) {
			path = path + this.path[i] + ".";
		}
		path = path + this.path[this.path.length-1]; 
		/*
		String tagPath = "";
		for(int i = 0; i<this.tag.length-1; i++) {
			tagPath = tagPath + this.tag[i] + ".";
		}
		tagPath = tagPath + this.tag[this.tag.length-1];
		*/
		return path + " " + this.translateTag();
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#isParentOf(fr.inria.gemo.vip2p.IDs.ID)
	 */
	public boolean isParentOf(NodeID id2) {
		CompactDynamicDeweyID ddeid2 = (CompactDynamicDeweyID) id2;

		if(this.path.length != (ddeid2.path.length-1)) {
			return false;
		}
		for(int i = (this.path.length-1); i>=0; i--) {
			if(this.path[i] != ddeid2.path[i]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#isAncestorOf(fr.inria.gemo.vip2p.IDs.ID)
	 */
	public boolean isAncestorOf(NodeID id2) {
		CompactDynamicDeweyID ddeid2 = (CompactDynamicDeweyID) id2;
		
		if(this.path.length >= ddeid2.path.length) {
			return false;
		}
		
		for(int i = 0; i<this.path.length; i++) {
			if(this.path[i] != ddeid2.path[i]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#getParent()
	 */
	public NodeID getParent() {
		if(this.path.length == 1) {
			return null;
		}
		int[] parent = new int[this.path.length-1];
		for(int i = 0; i<this.path.length-1; i++) {
			parent[i] = this.path[i];
		}
		int[] parentTag = new int[this.tag.length-1];
		for(int i = 0; i<this.tag.length-1; i++) {
			parentTag[i] = this.tag[i];
		}
		return new CompactDynamicDeweyID(parent, parentTag);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#getNull()
	 */
	public NodeID getNull() {
		return theNull;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		try {
			CompactDynamicDeweyID id2 = (CompactDynamicDeweyID) o;
			if(this.path.length != id2.path.length) {
				return false;
			}
			for(int i = 0; i<this.path.length; i++) {
				if(this.path[i] != id2.path[i]) {
					return false;
				}
			}
			return true;
		} catch (ClassCastException cce) {
			logger.warn("ClassCastException: ", cce);
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.path.hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#startsAfter(fr.inria.gemo.vip2p.IDs.ID)
	 */
	public boolean startsAfter(NodeID id2) {
		CompactDynamicDeweyID other = (CompactDynamicDeweyID) id2;
		
		int[] currentPath = this.path;
		int[] otherPath = other.path;
		int[] tempArray;
		
		while(true) {
			if(currentPath.length == 1) {
					if(otherPath.length == 1) {
						if(currentPath[0] > otherPath[0]) {
							return true;
						} else {
							return false;
						}
					} else {
						// this.path is "a", other.path is "b.something"
						if(currentPath[0] > otherPath[0]) {
							return true;
						} else {
							return false;
						}
					}
			} else {
				// this.path is "a.something"
				if(otherPath.length == 1) {
					// other.path is "b."
					if(currentPath[0] > otherPath[0]) {
						return true;
					} else {
						if(currentPath[0] < otherPath[0]) {
							return false;
						} else {
							// They are equal, but this.path is longer
							return true;
						}
					}
				} else {
					// this.path is a.something1, other.path is b.something2
					if(currentPath[0] > otherPath[0]) {
						return true;
					}  else {
						if (currentPath[0] < otherPath[0]) {
							return false;
						} else {
							// m1 == m2
							tempArray = new int[currentPath.length-1];
							for(int i = 1; i<currentPath.length; i++) {
								tempArray[i-1] = currentPath[i];
							}
							currentPath = tempArray;
							tempArray = new int[otherPath.length-1];
							for(int i = 1; i<otherPath.length; i++) {
								tempArray[i-1] = otherPath[i];
							}
							otherPath = tempArray;
						}
					}
				}
			} 
		}
	}	
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.ID#endsAfter(fr.inria.gemo.vip2p.IDs.ID)
	 */
	public boolean endsAfter(NodeID id2) {
		CompactDynamicDeweyID other = (CompactDynamicDeweyID) id2;

		if (this.isAncestorOf(other)) {
			return true;
		}
		boolean b1 = this.startsAfter(other);
		boolean b2 = other.isAncestorOf(this);
		return (b1 && !b2);
	}
	
	public boolean isNull(){
		return (this == getNull());
	}
	
	/*
	 * Three cases of insertions:
	 * Leftmost insertion, rightmost insertion, insertion below a leaf node and insertion between two consecutive siblings.
	 * 
	 */
	/*
	public void getIDForInsertedNode(ID parent, ID leftSibling, ID rightSibling) throws VIP2PExecutionException {	
		CompactDynamicDeweyID castParent;
		CompactDynamicDeweyID castLeftSibling;
		CompactDynamicDeweyID castRightSibling;
		try{
			castParent = (CompactDynamicDeweyID)parent;
			castLeftSibling = (CompactDynamicDeweyID)leftSibling;
			castRightSibling = (CompactDynamicDeweyID)rightSibling;
		} catch (ClassCastException e) {
			throw new VIP2PExecutionException("Cannot apply getIDForInsertedNode on different ID schemes");
		}
		
		// Leftmost insertion
		if(leftSibling ==  null && rightSibling != null) {
			this.s = castParent.getS() + "." + (castRightSibling.getN() - 1);
			this.n = castRightSibling.getN() - 1;
		// Rightmost insertion
		} else if(leftSibling != null && rightSibling == null) {
			this.s = castParent.getS() + "." + (castLeftSibling.getN() + 1);
			this.n = castLeftSibling.getN() + 1;
		// Insertion below a leaf node
		} else if(leftSibling == null && rightSibling == null) {
			this.s = castParent.getS() + "." + 1;
			this.n = 1;
		// Insertion between two consecutive siblings
		} else{
			String[] pathLeftSibling = castLeftSibling.getS().split(".");
			String[] pathRightSibling = castRightSibling.getS().split(".");
			String newID = "";
			// Don't need separate variables/checks for right sibling as the length is the same as it is a sibling
			for(int i = 0; i<pathLeftSibling.length; i++) {
				if(i == 0 || i == (pathLeftSibling.length-1)) {
					newID = newID + (Integer.parseInt(pathLeftSibling[i]) + Integer.parseInt(pathRightSibling[i]));
				} else {
					newID = newID + pathLeftSibling[i];
				}
			}
			this.s = newID;
			this.n = (new Integer(this.s.substring(this.s.lastIndexOf('.') + 1))).intValue();
		}
	}
	
	/*
	 * Two cases of insertions:
	 * Rightmost insertion and insertion below a leaf node.  This is used when the order of children is irrelevant.
	 * 
	 */
	/*
	public void getIDForInsertedNode(ID parent, ID lastChild) throws VIP2PExecutionException{
		CompactDynamicDeweyID castParent;
		CompactDynamicDeweyID castLastChild;
		try{
			castParent = (CompactDynamicDeweyID)parent;
			castLastChild = (CompactDynamicDeweyID)lastChild;
		} catch (ClassCastException e) {
			throw new VIP2PExecutionException("Cannot apply getIDForInsertedNode on different ID schemes");
		}
		
		// Rightmost insertion
		if(lastChild != null) {
			this.s = castParent.getS() + "." + (castLastChild.getN() + 1);
			this.n = castLastChild.getN() + 1;
		// Insertion below a leaf node
		} else {
			this.s = castParent.getS() + "." + 1;
			this.n = 1;
		} 
	}*/
	
	/**
	 * Gets the path to the current node as a string
	 * 
	 * @return	the path as a string
	 */
	public String getPathAsString() {
		String s = "";
		for(int i = 0; i<this.path.length; i++){
			s = s + this.path[i];
		}
		return s;	
	}
	
	/**
	 * Gets the tag path to the current node as a string
	 * 
	 * @return	the tag path as a string
	 */
	public String getTagPathAsString() {
		String s = "";
		for(int i = 0; i <this.tag.length; i++){
			s = s + this.tag[i];
		}
		return s;	
	}
	
	/**
	 * Translates the tag into it's original representation using tag names
	 * instead of numbers which were introduced for compression purposes
	 * 
	 * @return	the translated tag
	 */
	public String translateTag() {
		String s = "";
		for(int i = 0; i<tag.length; i++) {
			for(String key : CompactDynamicDeweyScheme.tagDictionary.keySet()) {
				if(CompactDynamicDeweyScheme.tagDictionary.get(key) == tag[i]) {
					if(i != tag.length-1) {
						s = s + key + ".";
					} else {
						s = s + key;
					}
				}
			}
		}
		return s;
	}
	
	/**
	 * Determines if the given tag exists in the path
	 * @param tag the tag to check for existence in the path
	 * @return	  <code>true</code> if the path contains the tag, <code>false</code> otherwise
	 */
	public boolean inPath(String tag) {
		if(tag != null) {
			for(int i = 0; i< this.path.length; i++) {
				if(this.path[i] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int[] getPath() {
		return this.path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public int[] getTag() {
		return tag;
	}

	public void setTag(int[] tag) {
		this.tag = tag;
	}

	public void setResultPath(LinkedList<CompactDynamicDeweyID> resultPath) {
		this.resultPath = resultPath;
	}

	public LinkedList<CompactDynamicDeweyID> getResultPath() {
		return resultPath;
	}
	
}
