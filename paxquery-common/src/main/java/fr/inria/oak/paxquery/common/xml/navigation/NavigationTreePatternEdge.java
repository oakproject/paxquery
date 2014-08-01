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
package fr.inria.oak.paxquery.common.xml.navigation;

import java.io.Serializable;

/**
 * Represents the edges that will connect two nodes in a {@link NavigationTreePattern}.
 * 
 */
public final class NavigationTreePatternEdge implements Serializable {
	
	/**
	 * The "parent" (upper) node. Strictly speaking the edge may avoid storing
	 * the parent node since the parent node stores the edges. But it's more
	 * convenient this way.
	 */
	public NavigationTreePatternNode n1;

	/**
	 * The "child" (lower) node.
	 */
	public NavigationTreePatternNode n2;

	/**
	 * If false, the edge is ancestor-descendant, otherwise it is parent-child.
	 */
	private boolean parent;

	/**
	 * If true, the join is nested with the child being nested under the parent.
	 */
	private boolean nested;	
		
	private boolean optional;

	
	public NavigationTreePatternEdge(NavigationTreePatternNode n1, NavigationTreePatternNode n2,
		boolean parent, boolean nested, boolean optional) {
		this.n1 = n1;
		this.n2 = n2;
		this.parent = parent;
		this.nested = nested;
		this.optional = optional;
	}

	public NavigationTreePatternNode getN1() {
		return this.n1;
	}


	public NavigationTreePatternNode getN2() {
		return this.n2;
	}


	/**
	 * If false, the edge is ancestor-descendant edge, 
	 * otherwise it is parent-child edge.
	 * @return
	 */
	public boolean isParent() {
		return this.parent;
	}
	
	public void setNested(boolean b){
		this.nested = b;
	}
	
	public boolean isNested(){
		return this.nested;
	}
	
	public void setOptional(boolean b){
		this.optional = b;
	}
	
	public boolean isOptional(){
		return this.optional;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public static String display(NavigationTreePatternEdge parentEdge) {
		StringBuffer sb = new StringBuffer();
		if (parentEdge == null){
			sb.append("null");
		}
		else{
			if (parentEdge.n1 != null){
				sb.append(parentEdge.n1.getTag());
			}
			else{
				sb.append("null");
			}
			if (parentEdge.isParent()){
				sb.append("/");
			}
			else{
				sb.append("//");
			}
			if (parentEdge.isNested()){
				sb.append("n");
			}
			sb.append(parentEdge.n2.getTag() + " (" + (parentEdge.n1 == null?"":parentEdge.n1.getNodeCode()) + " " + parentEdge.n2.getNodeCode() + ")");
		}
		return new String(sb);
	}

}
