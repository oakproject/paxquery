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
package fr.inria.oak.paxquery.common.xml.nodeidentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a compact dynamic Dewey ID scheme.
 * 
 */
public class CompactDynamicDeweyScheme implements NodeIDScheme {
	
	private static final Log logger = LogFactory.getLog(CompactDynamicDeweyScheme.class);

	private static String nullIDStringImage = "null";
	
	private static int tagCount = 0;
	
	public int[] currentId;
	int currentSiblingNo;
	
	int[] parentId;
	
	int[] currentTag;
	int[] parentTag;
	
	/**
	 * by the string of the parent, the integer representing the most advanced child currently
	 * initially 0, then 1, 2, 3 etc.
	 */
    HashMap<int[], Integer> currentChildren;

	
	Stack<CompactDynamicDeweyID> s;
	
	int depth;
	int lastDepth;
	
	CompactDynamicDeweyID lastID;
	
	public static Map<String, Integer> tagDictionary = new HashMap<String, Integer>();
	
	public CompactDynamicDeweyScheme() {		
		currentId = new int[0];
		parentId = new int[0];
		currentTag = new int[0];
		parentTag = new int[0];
		currentSiblingNo = 0;
		s = new Stack<CompactDynamicDeweyID>();
		lastID = null;
		depth = 0;
		lastDepth = 0;
		currentChildren = new HashMap<int[], Integer>();
	}
	
	public CompactDynamicDeweyScheme(CompactDynamicDeweyID id) {
		int[] tempPath = new int[id.path.length + 1];
		System.arraycopy(id.path, 0, tempPath, 0, id.path.length);
		tempPath[id.path.length] = 0;
		currentId = tempPath;
		parentId = id.path;
		int[] tempTag = new int[id.tag.length + 1];
		System.arraycopy(id.tag, 0, tempTag, 0, id.tag.length);
		tempPath[id.tag.length] = -1;
		currentTag = tempTag;
		parentTag = id.tag;
		/*try {
			parentString = ((CompactDynamicDeweyID)id.getParent()).s;
		} catch (VIP2PExecutionException e) {
			Parameters.logger.error("Exception", e);
		}*/
		currentSiblingNo = 0;
		s = new Stack<CompactDynamicDeweyID>();
		// Is this needed?
		s.push(id);
		lastID = null;
		// Depth and lastDepth will be wrong but I don't think it's relevant for the update purpose
		depth = 0;
		lastDepth = 0;
		currentChildren = new HashMap<int[], Integer>();
		currentChildren.put(parentId, currentSiblingNo);
	}
	
	// Needs tested
	public CompactDynamicDeweyScheme(CompactDynamicDeweyID id, CompactDynamicDeweyID lastSibling) {
		int[] tempPath = new int[id.path.length + 1];
		System.arraycopy(id.path, 0, tempPath, 0, id.path.length);
		tempPath[id.path.length] = lastSibling.n;
		currentId = tempPath;
		parentId = id.path;
		int[] tempTag = new int[id.tag.length + 1];
		System.arraycopy(id.tag, 0, tempTag, 0, id.tag.length);
		tempPath[id.tag.length] = -1;
		currentTag = tempTag;
		parentTag = id.tag;
		/*try {
			parentString = ((CompactDynamicDeweyID)id.getParent()).s;
		} catch (VIP2PExecutionException e) {
			Parameters.logger.error("Exception", e);
		}*/
		currentSiblingNo = lastSibling.n;
		s = new Stack<CompactDynamicDeweyID>();
		// Is this needed?
		s.push(id);
		lastID = null;
		// Depth and lastDepth will be wrong but I don't think it's relevant for the update purpose
		depth = 0;
		lastDepth = 0;
		currentChildren = new HashMap<int[], Integer>();
		currentChildren.put(parentId, currentSiblingNo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#isOrderPreserving()
	 */
	public boolean isOrderPreserving() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#isParentAncestorPreserving()
	 */
	public boolean isParentAncestorPreserving() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#allowsParentNavigation()
	 */
	public boolean allowsParentNavigation() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#allowsUpdates()
	 */
	public boolean allowsUpdates() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#beginDocument()
	 */
	public void beginDocument() {
		currentChildren.put(this.parentId, new Integer(0));		
	}

	@Override
	public void beginNode() {}
	
	/*
	 * CHECK THIS
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#beginNode()
	 */
	public void beginNode(String tag) {
		logger.debug("COMPACT DYNAMIC DEWEY BEGIN NODE");
		lastDepth = depth;
		depth ++;

		Integer x = (Integer)currentChildren.get(parentId);
		int k = x.intValue();
				
		CompactDynamicDeweyScheme.updateDictionary(tag);
		
		this.lastID = new CompactDynamicDeweyID(parentId, (k + 1), this.parentTag, tag);
			
		s.push(lastID);
		currentChildren.put(parentId, new Integer(k+1));
				
		this.parentId = lastID.path;
		currentChildren.put(parentId, new Integer(0));
		
		this.parentTag = lastID.tag;
				
		logger.debug("Created Compact Dynamic Dewey " + CompactDynamicDeweyScheme.getPathAsString(lastID.path) + " parent String is " + CompactDynamicDeweyScheme.getPathAsString(parentId));		
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#endNode()
	 */
	public void endNode() {
		logger.debug("COMPACT DYNAMIC DEWEY END NODE");
		this.lastID = (CompactDynamicDeweyID)s.pop();
				
		if (s.empty()){
			this.parentId = new int[0];
			this.parentTag = new int[0];
		}
		else{
			this.parentId = ((CompactDynamicDeweyID)s.peek()).path;
			this.parentTag = ((CompactDynamicDeweyID)s.peek()).tag;
		}
				
		logger.debug("Now parentString is " + CompactDynamicDeweyScheme.getPathAsString(parentId));
		logger.debug("Now parentTag is " + CompactDynamicDeweyScheme.getPathAsString(parentTag));
	}

	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#endDocument()
	 */
	public void endDocument() {
		// nothing
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#getLastID()
	 */
	public NodeID getLastID() {
		return lastID;
	}

	/*
	 * CHECK THIS
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#getSignature(java.lang.String)
	 */
	public String getSignature(String suffix) {
		return " ID" + suffix + " varchar";
	}

	/*
	 * CHECK THIS
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#getIndexSignature(java.lang.String)
	 */
	public String getIndexSignature(String suffix) {
		return " ID" + suffix;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#nullIDStringImage()
	 */
	public String nullIDStringImage() {
		return nullIDStringImage;
	}
	
	public static String getPathAsString(int[] path) {
		String s = "";
		for(int i = 0; i<path.length; i++) {
			s = s+path[i]+".";
		}
		return s;
	}
	
	public static void updateDictionary(String tag) {
		if(CompactDynamicDeweyScheme.tagDictionary.get(tag) == null) {
			CompactDynamicDeweyScheme.tagDictionary.put(tag, CompactDynamicDeweyScheme.tagCount);
			CompactDynamicDeweyScheme.tagCount++;
		}
	}
	
	public int[] getCurrentID() {
		return this.currentId;
	}
	
	public HashMap<int[], Integer> getCurrentChildren() {
		return this.currentChildren;
	}
	
//	public static void main(String[] args) {
//		CompactDynamicDeweyScheme dds = new CompactDynamicDeweyScheme();
//		// 1
//		dds.beginDocument();
//		dds.beginNode("a");
//		
//		// 1.1
//		dds.beginNode("b");
//		dds.endNode();
//		
//		// 1.2
//		dds.beginNode("c");
//		// 1.2.1
//		dds.beginNode("d");
//		dds.endNode();
//		
//		// 1.2.2
//		dds.beginNode("e");
//		dds.endNode();
//		
//		// 1.2.3
//		dds.beginNode("f");
//		dds.endNode();
//		dds.endNode();
//		
//		// 1.3
//		dds.beginNode("g");
//		dds.endNode();
//		
//		// 1.4
//		dds.beginNode("h");
//		
//		// 1.4.1
//		dds.beginNode("i");
//		dds.endNode();
//		dds.endNode();
//		
//		// 1.5
//		dds.beginNode("j");
//		dds.endNode();
//		
//		dds.endNode();
//		dds.endDocument();
//	}

}
