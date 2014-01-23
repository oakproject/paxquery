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
package fr.inria.oak.paxquery.algebra.operators.unary;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;


/**
 * Generic class that a unary operator extends.
 *
 */
public abstract class BaseUnaryOperator extends BaseLogicalOperator{
	

	public BaseUnaryOperator(BaseLogicalOperator child) throws PAXQueryExecutionException {
		if (child == null){
		    throw new PAXQueryExecutionException("Null child!");
		}
		this.children = new ArrayList<BaseLogicalOperator>();
		this.children.add(child);
	}
	
	public BaseLogicalOperator getChild() {
		return this.children.get(0);
	}

	public void setChild(BaseLogicalOperator child) {
		this.children.set(0, child);
	}
	
	@Override
	public String getName(){
		String s = this.ownName + "(";
		if (getChild() == null){
		    System.out.println("CHILD SHOULD NOT BE NULL!!!!!");
			s = s + "null";
		    
		}
		else{
		    s = s + getChild().getName();
		}
		if (this.ownDetails == null){
			s = s + ")";
		}
		else{
			s = s + ", " + this.ownDetails + ")";
		}
		return s;
	}
	
	@Override
	public final int recursiveDotString(StringBuffer sb, int parentNo, int firstAvailable){
		int selfNumber = -1;
			
		if (this.visible || parentNo < 100) {
			selfNumber = firstAvailable;
			sb.append(selfNumber + " [label=\"" + this.ownName);
			if (this.ownDetails != null){
				sb.append(" " + this.ownDetails);
			}
			sb.append("\"] ; \n");
			if (parentNo >= 100) {
				sb.append(parentNo + " -> " + selfNumber + " ; \n");
			}
			if (getChild() == null){
			    System.out.println(this.ownDetails);
			    try{
			    	throw new Exception("");
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
			int childNumber =
				getChild().recursiveDotString(sb, selfNumber, (firstAvailable + 1));
			return childNumber;
		}
		
		return getChild().recursiveDotString(sb, parentNo, firstAvailable);
	}

	@Override
	public void recDisplayNRSMD() {
		System.out.println("\n" + this.ownName + " of: ");
		getChild().recDisplayNRSMD();
		this.nestedMetadata.display();
		
	}
	
	@Override
	public int getJoinDepth() {
		return getChild().getJoinDepth();
	}

	
}
