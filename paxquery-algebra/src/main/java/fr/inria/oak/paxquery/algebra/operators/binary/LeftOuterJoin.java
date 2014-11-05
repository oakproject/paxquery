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
package fr.inria.oak.paxquery.algebra.operators.binary;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;


/**
 * Left outer join logical operator.
 *
 */
public class LeftOuterJoin extends BaseJoinOperator {
	
	private final int documentIDColumn;
	
	private final int[] nodeIDColumns;
	
	
	public LeftOuterJoin(BaseLogicalOperator left, BaseLogicalOperator right, BasePredicate pred,
			int documentIDColumn, int[] nodeIDColumns) throws PAXQueryExecutionException {
		super(left,right,pred);
		this.ownName = "LeftOuterJoin";
		this.visible = true;
		this.documentIDColumn = documentIDColumn;
		this.nodeIDColumns = nodeIDColumns;
	}
	
	public int getDocumentIDColumn() {
		return this.documentIDColumn;
	}

	public int[] getNodeIDColumns() {
		return this.nodeIDColumns;
	}
	
}
