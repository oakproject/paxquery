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
package fr.inria.oak.paxquery.algebra.optimizer.rules;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupByWithAggregation;

public final class GroupByAggregationMerge implements Logical2Logical {

  public static final GroupByAggregationMerge INSTANCE =
          new GroupByAggregationMerge();


  private GroupByAggregationMerge() {}

  @Override
  public BaseLogicalOperator transform(BaseLogicalOperator operator) {
    return mergeGroupByAggregation(operator);
  }

  private static BaseLogicalOperator mergeGroupByAggregation(BaseLogicalOperator operator) {
    if (operator instanceof Aggregation &&
            operator.getChildren().get(0) instanceof GroupBy) {
      // We have a potential match
      final Aggregation aggregate = (Aggregation) operator;
      final GroupBy groupBy = (GroupBy) operator.getChildren().get(0);

      // If it is an aggregation on the whole dataset, we bail out
      if(aggregate.getAggregationPath().length == 1) {
        mergeGroupByAggregation(groupBy);
        return aggregate;
      }

      // If the aggregation is not done on the grouping by field,
      // we can push it below the group-by
      if (aggregate.getAggregationPath().length > 2 ||
              aggregate.getAggregationPath()[0] != groupBy.getNRSMD().getColNo() - 1) {
        final BaseLogicalOperator parent = aggregate.getParent();
        final BaseLogicalOperator child = groupBy.getChild();
        LogicalPlan.connect(parent, groupBy);
        LogicalPlan.connect(aggregate, child);
        LogicalPlan.connect(groupBy, aggregate);
        mergeGroupByAggregation(aggregate);
        return groupBy;
      }

      // If the aggregation is done on the grouping field, we just
      // merge both operators
      final BaseLogicalOperator newGroupByAggregate = new GroupByWithAggregation(
              groupBy.getChild(), groupBy.getReduceByColumns(),
              groupBy.getGroupByColumns(), groupBy.getNestColumns(),
              aggregate.getAggregationPath()[1], aggregate.getAggregationType(), 
              false);
      // We finish linking below
      groupBy.getChild().setParent(newGroupByAggregate);
      // We link above
      final BaseLogicalOperator parent = aggregate.getParent();
      LogicalPlan.connect(parent, newGroupByAggregate);
      mergeGroupByAggregation(newGroupByAggregate.getChildren().get(0));
      return newGroupByAggregate;
    } else if (operator instanceof BaseBinaryOperator ||
            operator instanceof BaseUnaryOperator) {
      // Unary and binary operators
      for (BaseLogicalOperator child : operator.getChildren()) {
        mergeGroupByAggregation(child);
      }
      return operator;
    }

    // Leaf operator
    return operator;
  }

}
