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
package fr.inria.oak.paxquery.algebra.operators.binary;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;


/**
 * Generic class that a binary join operator extends.
 *
 */
public abstract class BaseJoinOperator extends BaseBinaryOperator {
	
	private static final Log logger = LogFactory.getLog(BaseJoinOperator.class);
	
	protected BasePredicate pred;
	

	public BaseJoinOperator(BaseLogicalOperator left, BaseLogicalOperator right, BasePredicate pred) {
		super(left, right);
		this.pred = pred;
	}
	
	public BasePredicate getPred() {
		return this.pred;
	}

	public void setPred(BasePredicate pred) {
		this.pred = pred;
	}
	
	@Override
	public String getName(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.ownName);
		sb.append("(" + this.getLeft().getName() + "," + this.getRight().getName());
		if (this.pred != null){
			sb.append(", " + this.pred.toString()+")");
		}
		else{
			sb.append(")");
		}
		return new String(sb);
	}
	
	@Override
	public final int recursiveDotString(StringBuffer sb, int parentNo, int firstAvailableNo){
		int selfNumber = -1;

		if (this.visible) {
			selfNumber = firstAvailableNo;

			sb.append(selfNumber + " [label=\""	+ this.ownName);
			if (this.pred != null){
				sb.append("\\n" + this.pred.toString());
			}
			sb.append("\"] ; \n");

			if (parentNo != -1) {
				sb.append(parentNo + " -> " + selfNumber + " ; \n");
			}
		}
		int childNumber1 = -1;
		if (this.visible) {
			childNumber1 = this.getLeft().recursiveDotString(sb, selfNumber, (firstAvailableNo + 1));
		} else {
			childNumber1 = this.getLeft().recursiveDotString(sb, parentNo, firstAvailableNo);
		}
		int childNumber2 = -1;
		if (this.visible) {
			childNumber2 = this.getRight().recursiveDotString(sb, selfNumber, childNumber1);
		} else {
			childNumber2 = this.getRight().recursiveDotString(sb, parentNo, childNumber1);
		}
		return childNumber2;
	}
	
	@Override
	public void buildNRSMD() {
		if(this.children != null && this.children.size() == 2)
			this.nestedMetadata = NestedMetadataUtils.appendNRSMD(this.children.get(0).getNRSMD(), this.children.get(1).getNRSMD());
		else
			throw new PAXQueryExecutionException("Cannot build metadata for join operator.");
	}

	@Override
	public void recDisplayNRSMD() {
		logger.info("\n" + this.ownName + "of: ");
		this.getLeft().recDisplayNRSMD();
		logger.info("and");
		this.getRight().recDisplayNRSMD();
		logger.info("is ");
		this.nestedMetadata.display();
	}
	
}
