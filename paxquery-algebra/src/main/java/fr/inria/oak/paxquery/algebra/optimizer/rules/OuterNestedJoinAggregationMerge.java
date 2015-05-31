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
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoinWithAggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;


public final class OuterNestedJoinAggregationMerge implements Logical2Logical {

  public static final OuterNestedJoinAggregationMerge INSTANCE =
          new OuterNestedJoinAggregationMerge();


  private OuterNestedJoinAggregationMerge() {}

  @Override
  public BaseLogicalOperator transform(BaseLogicalOperator operator) {
    return mergeOuterNestedJoinAggregation(operator);
  }

  private static BaseLogicalOperator mergeOuterNestedJoinAggregation(BaseLogicalOperator operator) {
    if (operator instanceof Aggregation &&
            operator.getChildren().get(0) instanceof LeftOuterNestedJoin) {
      // We have a potential match
      final Aggregation aggregate = (Aggregation) operator;
      final LeftOuterNestedJoin leftOuterNestedJoin = (LeftOuterNestedJoin) operator.getChildren().get(0);

      // If it is an aggregation on the whole dataset, we bail out
      if(aggregate.getAggregationPath().length == 1) {
        mergeOuterNestedJoinAggregation(leftOuterNestedJoin);
        return aggregate;
      }

      // If the aggregation is not done on the nested field created by the join,
      // we can push it below
      if (aggregate.getAggregationPath().length > 2 ||
              aggregate.getAggregationPath()[0] != leftOuterNestedJoin.getNRSMD().getColNo() - 1) {
        final BaseLogicalOperator parent = aggregate.getParent();
        final BaseLogicalOperator targetChild;
        final int noColsLeftChild = leftOuterNestedJoin.getChildren().get(0).getNRSMD().getColNo();
        if (aggregate.getAggregationPath()[0] < noColsLeftChild) {
          targetChild = leftOuterNestedJoin.getChildren().get(0);
        } else {
          targetChild = leftOuterNestedJoin.getChildren().get(1);
        }
        LogicalPlan.connect(parent, leftOuterNestedJoin);
        LogicalPlan.connect(aggregate, targetChild);
        LogicalPlan.connect(leftOuterNestedJoin, aggregate);
        for (BaseLogicalOperator child : leftOuterNestedJoin.getChildren()) {
          mergeOuterNestedJoinAggregation(child);
        }
        return leftOuterNestedJoin;
      }

      // If the aggregation is done on the nested field, we just
      // merge both operators
      final BaseLogicalOperator newLeftOuterNestedJoinAggregate =
              new LeftOuterNestedJoinWithAggregation(leftOuterNestedJoin.getLeft(),
                      leftOuterNestedJoin.getRight(), leftOuterNestedJoin.getPred(),
                      leftOuterNestedJoin.getDocumentIDColumn(), leftOuterNestedJoin.getNodeIDColumns(),
                      leftOuterNestedJoin.getLeft().getNRSMD().getColNo() + aggregate.getAggregationPath()[1],
                      aggregate.getAggregationType(), false);
      // We finish linking below
      for (BaseLogicalOperator child : leftOuterNestedJoin.getChildren()) {
        child.setParent(newLeftOuterNestedJoinAggregate);
      }
      // We link above
      BaseLogicalOperator parent = aggregate.getParent();
      LogicalPlan.connect(parent, newLeftOuterNestedJoinAggregate);
      for (BaseLogicalOperator child : newLeftOuterNestedJoinAggregate.getChildren()) {
        mergeOuterNestedJoinAggregation(child);
      }
      return newLeftOuterNestedJoinAggregate;
    } else if (operator instanceof BaseBinaryOperator ||
            operator instanceof BaseUnaryOperator) {
      // Unary and binary operators
      for (BaseLogicalOperator child : operator.getChildren()) {
        mergeOuterNestedJoinAggregation(child);
      }
      return operator;
    }

    // Leaf operator
    return operator;
  }

}
