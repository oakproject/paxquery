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
/*
 * Created on Jun 13, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.inria.oak.paxquery.common.xml.nodeidentifier;

import java.io.Serializable;

import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;

/**
 * 
 * 
 */
public class OrderedIntegerElementID implements NodeID, Serializable, Comparable<OrderedIntegerElementID> {
	
	public static OrderedIntegerElementID theNull = new OrderedIntegerElementID(-1);
	
	/**
	 * The actual ID
	 */
	public int n;
	
	public OrderedIntegerElementID(int n){
		this.n = n;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#getType()
	 */
	@Override
	public int getType() {
		return 0;
	}


	@Override
	public String toString(){
		if (this == theNull){ 
			return "\0";
		}
		return ("" + this.n);
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#isParent(fr.inria.oak.stratosphere.xml.IDs.ElementID, fr.inria.oak.stratosphere.xml.IDs.ElementID)
	 */
	@Override
	public  boolean isParentOf(NodeID id2) throws PAXQueryExecutionException {
		throw new PAXQueryExecutionException("IntegerIDs cannot answer isParentOf");
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#isAncestor(fr.inria.oak.stratosphere.xml.IDs.ElementID, fr.inria.oak.stratosphere.xml.IDs.ElementID)
	 */
	@Override
	public boolean isAncestorOf(NodeID id2) throws PAXQueryExecutionException {
		throw new PAXQueryExecutionException("IntegerIDs cannot answer isAncestorOf");
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#getParent(fr.inria.oak.stratosphere.xml.IDs.ElementID)
	 */
	@Override
	public NodeID getParent() throws PAXQueryExecutionException {
		throw new PAXQueryExecutionException("IntegerIDs cannot answer getParent");
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#getNull()
	 */
	@Override
	public NodeID getNull() {
		return theNull;
	}
	
	@Override
	public boolean equals(Object o){
		try{
			OrderedIntegerElementID iid = (OrderedIntegerElementID)o;
			return (this.n == iid.n);
		}
		catch(ClassCastException cce){
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return (new Integer(this.n)).hashCode();
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#startsAfter(fr.inria.oak.stratosphere.xml.IDs.ElementID)
	 */
	@Override
	public boolean startsAfter(NodeID id2) throws PAXQueryExecutionException {
		OrderedIntegerElementID other = (OrderedIntegerElementID)id2;
		return (this.n > other.n);
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.ElementID#endsAfter(fr.inria.oak.stratosphere.xml.IDs.ElementID)
	 */
	@Override
	public boolean endsAfter(NodeID id2) throws PAXQueryExecutionException {
		throw new PAXQueryExecutionException("EndsAfter undefined for integer IDs");
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(OrderedIntegerElementID otherID) {
		if(this.n == otherID.n)
			return 0;
		else if(this.n < otherID.n)
			return -1;
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.NodeID#isNull()
	 */
	@Override
	public boolean isNull() {
		return (this == getNull());
	}
}
