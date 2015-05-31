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

import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;

/**
 * Factory class that creates and returns the ID scheme specified.
 * 
 */
public class NodeIDSchemeAssignator{
	
	/**
	 * Returns the kind of ID scheme that the node passed as a parameter
	 * specifies.
	 * @param pn the pattern node that we will use for getting the ID scheme
	 * @return the ID scheme
	 */
	public static NodeIDScheme getIDScheme(NavigationTreePatternNode pn){
		if (pn.storesID()){
			if (pn.isStructIDType()){
				return getStructuralScheme();
			}
			if (pn.isIdentityIDType()){
				return getOrderedIntegerIDScheme();
			}
			if (pn.isOrderIDType()){
				return getOrderedIntegerIDScheme();
			}
		}
		return null;
	}
	
	/*
	 * 
	 * @return an IDScheme which allows, by comparing two IDs, to know whether they are 
	 * 	in an ancestor-descendant (or parent-child) relationship or not
	 */
	public static NodeIDScheme getStructuralScheme(){
		return new PrePostDepthIDScheme();
	}
	
	public static NodeIDScheme getOrderedIntegerIDScheme(){
		return new OrderedIntegerIDScheme();
	}
	
}
