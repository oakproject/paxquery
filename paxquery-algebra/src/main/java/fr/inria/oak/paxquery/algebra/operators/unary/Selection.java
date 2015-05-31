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
package fr.inria.oak.paxquery.algebra.operators.unary;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;


/**
 * Selection logical operator.
 *
 */
public class Selection extends BaseUnaryOperator {
	
	protected BasePredicate pred;

	
	public Selection(BaseLogicalOperator child, BasePredicate pred) throws PAXQueryExecutionException {
		super(child);
		this.pred = pred;
		this.visible = true;
		this.ownName = "Select";
		this.ownDetails = pred.toString();
	}
	
	@Override
	public void buildNRSMD() {
		for(BaseLogicalOperator op : children)
			op.buildNRSMD();
		this.nestedMetadata = children.get(0).getNRSMD();
	}
	
	public BasePredicate getPred() {
		return this.pred;
	}

	public void setPred(BasePredicate pred) {
		this.pred = pred;
	}
	
	@Override
	public void buildOwnDetails() {
		this.ownDetails = pred.toString();
	}
}
