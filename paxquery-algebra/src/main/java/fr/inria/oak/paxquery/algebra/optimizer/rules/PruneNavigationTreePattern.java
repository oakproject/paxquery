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

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.Navigation;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;

public final class PruneNavigationTreePattern implements Logical2Logical {

  public static final PruneNavigationTreePattern INSTANCE = new PruneNavigationTreePattern();

  private PruneNavigationTreePattern() {
  }

  @Override
  public BaseLogicalOperator transform(BaseLogicalOperator operator) {
    return pruneNavigationTreePattern(operator);
  }

  private static BaseLogicalOperator pruneNavigationTreePattern(BaseLogicalOperator operator) {
    if (operator instanceof Projection
            && operator.getChildren().get(0) instanceof XMLScan) {
      // We have a potential match
      final Projection project = (Projection) operator;
      final XMLScan xmlScan = (XMLScan) operator.getChildren().get(0);

      //TODO
    } else if (operator instanceof Projection
            && operator.getChildren().get(0) instanceof Navigation) {
      // We have a potential match
      final Projection project = (Projection) operator;
      final Navigation navigation = (Navigation) operator.getChildren().get(0);

      //TODO
    }

    // Nothing to do
    return operator;
  }

}
