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

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * 
 */
public class PrePostDepthID implements NodeID, Serializable, Comparable<PrePostDepthID> {
	
	private static final long serialVersionUID = -5128504862786511446L;
	
	private static final Log logger = LogFactory.getLog(PrePostDepthID.class);
	
	private static final String delimiter= "\u0020";
	
	public int pre;
	public int post;
	public int depth;
	
	public static PrePostDepthID theNull = new PrePostDepthID(-1, -1);
	
	public PrePostDepthID(int pre, int depth) {		
		this.pre = pre;
		this.depth = depth;
	}
	
	public String toString(){
		//return new String("[" + pre + "," + post + "," + depth + "]");
		if (this == theNull){
			return "\0";
		}
		//Parameters.logger.debug(PrePostDepthIDScheme.delimiter);
		
		return new String(pre + delimiter + post + delimiter + depth);
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#isParentOf(fr.inria.gemo.uload.IDs.ElementID)
	 */
	public boolean isParentOf(NodeID id2) {
		PrePostDepthID other = (PrePostDepthID)id2;
		if (other.pre >= this.pre){
			if (other.post <= this.post){
				if (other.depth == this.depth + 1){
					return true;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#isAncestorOf(fr.inria.gemo.uload.IDs.ElementID)
	 */
	public boolean isAncestorOf(NodeID id2) {
		PrePostDepthID other = (PrePostDepthID)id2;
		if (other.pre >= this.pre){
			if (other.post <= this.post){
				if (other.depth > this.depth){
					return true;
				}
			}	
		}	
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#getParent()
	 */
	public NodeID getParent() {
		logger.error("PrePostDepthID cannot infer parent");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#getNull()
	 */
	public NodeID getNull() {
		return theNull;
	}
	
	public boolean isNull(){
		return (this == getNull());
	}
	
	public boolean equals(Object o){
		PrePostDepthID id2 = (PrePostDepthID)o;
		return (this.pre == id2.pre && this.post == id2.post && this.depth == id2.depth);
	}
	
	public int hashCode(){
		return ((new Integer(this.pre)).hashCode());
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#startsAfter(fr.inria.gemo.uload.IDs.ElementID)
	 */
	public boolean startsAfter(NodeID id2) {
		PrePostDepthID other = (PrePostDepthID) id2;
		return (this.pre > other.pre);
	}

	/* (non-Javadoc)
	 * @see fr.inria.gemo.uload.IDs.ElementID#endsAfter(fr.inria.gemo.uload.IDs.ElementID)
	 */
	public boolean endsAfter(NodeID id2) {
		PrePostDepthID other = (PrePostDepthID) id2;
		return (this.post > other.post);
	}

	@Override
	public int getType() {
		return 0;
	}

	public void setPost(int post2) {
		this.post = post2;
	}

	@Override
	public int compareTo(PrePostDepthID otherID) {
		int res = 0;
		
		if (this.startsAfter(otherID))
			res = 1;
		else if (otherID.startsAfter(this))
			res = -1;
		
		return res;
	}
	
	
}
