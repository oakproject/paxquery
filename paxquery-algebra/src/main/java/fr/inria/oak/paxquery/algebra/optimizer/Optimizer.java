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
package fr.inria.oak.paxquery.algebra.optimizer;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.optimizer.rules.CartesianProductSelectionMerge;
import fr.inria.oak.paxquery.algebra.optimizer.rules.GroupByAggregationMerge;
import fr.inria.oak.paxquery.algebra.optimizer.rules.Logical2Logical;
import fr.inria.oak.paxquery.algebra.optimizer.rules.OuterNestedJoinAggregationMerge;
import fr.inria.oak.paxquery.algebra.optimizer.rules.PushProjections;

public class Optimizer {

  // Default instance for logical optimizations over the plan
  // 1. Merge CartesianProduct and Select into Join,
  //    merge Aggregation into GroupBy and Nested Join
  // 2. Pushdown projections
  //    Prune navigation trees
  //    Prune nested outer join //TODO
  //    Prune group-by //TODO
  // 3. Pushdown selections //TODO
  public static final Optimizer INSTANCE = new Optimizer(
          CartesianProductSelectionMerge.INSTANCE,
          GroupByAggregationMerge.INSTANCE,
          OuterNestedJoinAggregationMerge.INSTANCE,
          PushProjections.INSTANCE);

  private final ArrayList<Logical2Logical> ruleList;

  /**
   * Builds a hardcoded list of optimization rules that will be applied to a logical plan
   */
  public Optimizer(Logical2Logical... rules) {
    ruleList = new ArrayList<Logical2Logical>();
    for (Logical2Logical rule : rules) {
      ruleList.add(rule);
    }
  }

  public void optimize(LogicalPlan logicalPlan) {
    logicalPlan.adjustParents();
    logicalPlan.adjustNavigationTreePatterns();
    for (Logical2Logical rule : ruleList) {
      BaseLogicalOperator newRoot = rule.transform(logicalPlan.getRoot());
      logicalPlan.setRoot(newRoot);
    }
  }
}
