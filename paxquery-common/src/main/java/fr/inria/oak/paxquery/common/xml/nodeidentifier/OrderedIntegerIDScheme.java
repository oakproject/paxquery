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
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package fr.inria.oak.paxquery.common.xml.nodeidentifier;

import java.util.Stack;

/**
 * 
 * 
 */
public class OrderedIntegerIDScheme implements NodeIDScheme {
	
	int n;

	OrderedIntegerElementID ied;

	Stack<OrderedIntegerElementID> s;
	
	private static String nullIDStringImage = "null";
	
	public OrderedIntegerIDScheme() {
		this.n = 0;
		this.ied = null;
		this.s = new Stack<OrderedIntegerElementID>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#isOrderPreserving()
	 */
	@Override
	public final boolean isOrderPreserving() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#isParentAncestorPreserving()
	 */
	@Override
	public final boolean isParentAncestorPreserving() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#allowsParentNavigation()
	 */
	@Override
	public final boolean allowsParentNavigation() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#allowsUpdates()
	 */
	@Override
	public final boolean allowsUpdates() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#initDocument()
	 */
	@Override
	public final void beginDocument() {
		this.n = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#initElement()
	 */
	@Override
	public final void beginNode() {
		this.s.push(new OrderedIntegerElementID(this.n));
		this.n++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#endElement()
	 */
	@Override
	public final void endNode() {
		this.ied = this.s.pop();
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#endDocument()
	 */
	@Override
	public final void endDocument() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#getLastID()
	 */
	@Override
	public final NodeID getLastID() {
		return this.ied;
	}
	
	@Override
	public final String getSignature(String suffix){
		return new String("ID" + suffix + " int");
	}
	
	@Override
	public final String nullIDStringImage(){
		return nullIDStringImage;
	}

	/* (non-Javadoc)
	 * @see fr.inria.oak.stratosphere.xml.IDs.IDScheme#getIndexSignature(java.lang.String)
	 */
	@Override
	public String getIndexSignature(String suffix) {
		return ("ID" + suffix);
	}

}
