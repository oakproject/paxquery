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

import java.util.Stack;

/**
 * 
 * 
 */
public class PrePostDepthIDScheme implements NodeIDScheme {

	int currentPre;
	int currentPost;
	int currentDepth;

	static boolean initialized;
	
	Stack<PrePostDepthID> s;
	
	PrePostDepthID currentID;
	
	private static String nullIDStringImage = "null null null";

	public PrePostDepthIDScheme(){
		currentPre = 0;
		currentPost = 0;
		currentDepth = 0;
		currentID = null;
		s = new Stack<PrePostDepthID>();
	}
	
	
	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#isOrderPreserving()
	 */
	public boolean isOrderPreserving() {
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#isParentAncestorPreserving()
	 */
	public boolean isParentAncestorPreserving() {
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#allowsParentNavigation()
	 */
	public boolean allowsParentNavigation() {
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#allowsUpdates()
	 */
	public boolean allowsUpdates() {
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#initDocument()
	 */
	public void beginDocument() {
		currentPre = 0;
		currentPost = 0;
		currentDepth = 0;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#initElement()
	 */
	public void beginNode() {
		currentID = new PrePostDepthID(currentPre, currentDepth);
		currentPre ++;
		currentDepth ++;
		s.push(currentID);
		//logger.debug("Assigned and pushed " + currentID.toString());
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.inria.gemo.vip2p.IDs.IDScheme#beginNode(java.lang.String)
	 */
	public void beginNode(String tag) {}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#endElement()
	 */
	public void endNode() {
		currentID = (PrePostDepthID)(s.pop());
		currentID.setPost(currentPost);
		//logger.debug("Completed and popped " + currentID.toString());
		currentPost ++;
		currentDepth --;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#endDocument()
	 */
	public void endDocument() {
		// nothing
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#getLastID()
	 */
	public NodeID getLastID() {
		//logger.debug("Current ID: " + currentID.toString());
		return currentID;
	}
	
	public PrePostDepthID getLastPrePostDepthID() {
		return currentID;
	}
	
	public String getSignature(String suffix){
		return ("ID" + suffix + "Pre int, ID" + suffix + "Post int, ID" + suffix + "depth int");
	}
	
	public  String nullIDStringImage(){
		return nullIDStringImage;
	}


	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.IDScheme#getIndexSignature(java.lang.String)
	 */
	public String getIndexSignature(String suffix) {
		return ("ID" + suffix + "Pre, ID" + suffix + "Post, ID" + suffix + "depth");
	}
}
