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

import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;

/**
 * Interface that will be implemented by classes that represent different kind of IDs.
 * 
 */
public interface NodeID {

	/**
	 * TODO: refine this into better type description (3 integers...)
	 * @return the type code, in a type system to be defined. But for the time being this needs 
	 * to be redone
	 */
	public int getType();
	
	/**
	 * @param id2
	 * @return true if id1 is a parent of id2
	 */
	public boolean isParentOf(NodeID id2) throws PAXQueryExecutionException;
	
	/**
	 * @param id2
	 * @return true if id1 is an ancestor of id2
	 */
	public boolean isAncestorOf(NodeID id2) throws PAXQueryExecutionException;
	
	/**
	 * @return the parent ID if it can be computed
	 */
	public NodeID getParent() throws PAXQueryExecutionException;
	
	/**
	 * @param id2
	 * @return true if this element ID starts strictly after id2
	 */
	public boolean startsAfter(NodeID id2) throws PAXQueryExecutionException;
	
	/**
	 * 
	 * @param id2
	 * @return true if this element ID ends strictly after id2
	 */
	public boolean endsAfter(NodeID id2) throws PAXQueryExecutionException;
	
	/**
	 * Gets the element ID that represents the null ID.
	 * @return the null element for this kind of ID
	 */
	public NodeID getNull();
	
	/**
	 * Returns true if this element ID is null.
	 * @return if this element ID is null
	 */
	public boolean isNull();
}
