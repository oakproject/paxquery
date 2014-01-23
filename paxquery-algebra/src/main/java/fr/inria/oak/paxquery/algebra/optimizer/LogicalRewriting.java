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
package fr.inria.oak.paxquery.algebra.optimizer;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.optimizer.pushdown.PushProjections;
import fr.inria.oak.paxquery.algebra.optimizer.pushdown.PushSelections;


/**
 * This class provides static methods for logical plan rewriting.
 * 
 */
public class LogicalRewriting {
	
	/**
	 * <p>This method pushes down selections and projections (in this order) in a given logical
	 * plan.</p>
	 * 
	 * <p>Its contract is to never change the metadata and resulting tuples of the given plan, or in
	 * other words to always generate equivalent plans.</p>
	 * 
	 * <p>The purpose of this method is to optimize the I/O by decreasing the result set both
	 * in terms of width (by pushing projections) and of length (by pushing selections).</p>
	 * 
	 * <p>It catches internal errors, but it does not raise them; instead, it logs them and
	 * returns null.</p>
	 * 
	 * @param op the root of the logical plan to rewrite
	 * @return the rewritten logical plan
	 */
	public static BaseLogicalOperator push(BaseLogicalOperator op) {
		return new PushProjections().transform(new PushSelections().transform(op));
	}
	
}
