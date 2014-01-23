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
package fr.inria.oak.paxquery.algebra.optimizer.pushdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.algebra.optimizer.Logical2Logical;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;

/**
 * This class contains the methods for pushing down selections in a given logical plan.
 * 
 */
public class PushSelections implements Logical2Logical {

	
	/**
	 * <p>This method pushes down selections in a given logical plan while keeping the set of tuples
	 * that result from the plan unchanged.</p>
	 * 
	 * <p>It simply calls {@link #pushSelections(LogicalOperator, Set)} with an empty set.</p>
  	 *
	 * @param op the root of the logical plan
	 * @return the new root of the logical plan
	 * @throws VIP2PExecutionException when the creation of a new logical operator fails
	 */
	@Override
	public BaseLogicalOperator transform(BaseLogicalOperator op) {
		return pushSelections(op, new HashSet<BasePredicate>());
	}
	
	/*
	 * <p>This method creates selections as low as possible in a subtree of a logical plan.
	 * It takes the predicates of the selections out of the logical plan tree, and updates
	 * them to match the new columns as it progresses toward the bottom. Once it has found
	 * a place where a selection can be pushed no further, it inserts it there.</p>
	 * 
	 * <p>The method calls itself recursively to for all children of the current logical operator.
	 * It returns a logical operator which is guaranteed to have the predicates given in the
	 * selectionPredicates set already applied.</p>
	 *
	 * <p>Operators that are recursed into include any {@link UnaryOperator}, {@link BinaryOperator}
	 * or {@link NaryOperator}, with the notable exception of {@link PatternNavigate} which is
	 * explicitly treated as a leaf operator.</p>
	 * 
	 * @param op the logical operator to push selections into
	 * @param selectionPredicates the predicates of the selections above
	 * @return an equivalent logical operator that has the selections pushed down
	 * @throws VIP2PExecutionException 
	 */
	private static BaseLogicalOperator pushSelections(BaseLogicalOperator op, Set<BasePredicate> selectionPredicates) {
		ArrayList<BaseLogicalOperator> newChildren = new ArrayList<BaseLogicalOperator>();
		
		if ( op instanceof Selection ) {
			// Retrieve the selection predicate
			Selection sel = (Selection)op;
			selectionPredicates.add(sel.getPred());
			// Recurse down with the new predicate, skipping everything else
			return pushSelections(sel.getChild(), selectionPredicates);
		} else if ( op instanceof Projection ) {
			Projection proj = (Projection) op;
			// Generate the map of projected column indexes to the indexes before the projection
			Map<Integer, Integer> oldColumnMap = new HashMap<Integer, Integer>();
			for ( int i = 0; i < proj.columns.length; i++ ) {
				oldColumnMap.put(i, proj.columns[i]);
			}
			// Update all predicates to match the column indexes before the projection
			Set<BasePredicate> newSelectionPredicates = new HashSet<BasePredicate>();
			for ( BasePredicate pred: selectionPredicates ) {
				newSelectionPredicates.add(PushdownUtility.updatePredicate(pred, oldColumnMap));
			}
			// Make sure that no predicates will be reapplied here
			selectionPredicates.clear();
			// Recurse down with the new predicates
			newChildren.add(pushSelections(proj.getChild(), newSelectionPredicates));
		} else if ( op instanceof BaseUnaryOperator ) {
			// Assumptions: UnaryOperators other than Projections only ever append columns.
			BaseUnaryOperator uop = (BaseUnaryOperator) op;
			// Pass down the predicates that do not need to be applied here
			Set<BasePredicate> newSelectionPredicates = new HashSet<BasePredicate>();
			for ( BasePredicate pred: selectionPredicates ) {
				if ( predicateMatchesRange(pred, 0, uop.getChild().getNRSMD().colNo-1) ) {
					newSelectionPredicates.add(pred);
				}
			}
			// Make sure that the predicates that were passed down aren't reapplied here
			selectionPredicates.removeAll(newSelectionPredicates);
			// Recurse down
			newChildren.add(pushSelections(uop.getChild(), newSelectionPredicates));
		} else if ( op instanceof BaseBinaryOperator ) {
			// Assumption: BinaryOperators concatenate the nrsmd's of all their children.
			int childBaseIndex = 0;
			for ( BaseLogicalOperator child: op.getChildren() ) {

				// Generate the map of the current column indexes to the child's indexes 
				Map<Integer, Integer> oldColumnMap = new HashMap<Integer, Integer>();
				for ( int i = 0; i < child.getNRSMD().colNo; i++ ) {
					oldColumnMap.put(childBaseIndex+i, i);
				}
				// See what predicates should be applied at this child instead of here
				Set<BasePredicate> oldSelectionPredicates = new HashSet<BasePredicate>(selectionPredicates);
				Set<BasePredicate> newSelectionPredicates = new HashSet<BasePredicate>();
				for ( BasePredicate pred: oldSelectionPredicates ) {
					if ( predicateMatchesRange(pred, childBaseIndex, childBaseIndex+child.getNRSMD().colNo-1) ) {
						selectionPredicates.remove(pred);
						newSelectionPredicates.add(PushdownUtility.updatePredicate(pred, oldColumnMap));
					}
				}
				// Recurse down
				newChildren.add(pushSelections(child, newSelectionPredicates));
				childBaseIndex += child.getNRSMD().colNo;
			}
		}
		
		BaseLogicalOperator newOp = op;
		// Only recreate the operator if the new children have been computed
		if ( newChildren.size() > 0 ) {
			assert newChildren.size() == op.getChildren().size();
			// Create a (dummy) column array so we can use recreateOp here too
			ArrayList<Integer> identicalColArray = new ArrayList<Integer>();
			if ( op instanceof Projection ) {
				// Projection masks refer to children column indexes
				for ( int i=0; i<((Projection) op).getChild().getNRSMD().colNo; i++ )
					identicalColArray.add(i);
			} else {
				// Predicates refer to this operation's column indexes
				for ( int i=0; i<op.getNRSMD().colNo; i++ )
					identicalColArray.add(i);
			}
			newOp = PushdownUtility.recreateOp(op, newChildren, identicalColArray);
		}

		/* Selections need to be inserted above this operator for all predicates that haven't
		   been passed to the children. */
		for(BasePredicate pred: selectionPredicates)
			newOp = new Selection(newOp, pred);
		
		return newOp;
	}
	

	/*
	 * Verifies whether all of a predicate's columns are contained within a given 
	 * column range.
	 * 
	 * @param pred the predicate to check
	 * @param firstCol the beginning of the range (inclusive)
	 * @param lastCol the end of the range (inclusive)
	 * @return true if the predicate is contained within the range, false otherwise
	 */
	private static boolean predicateMatchesRange(BasePredicate pred, int firstCol, int lastCol) {
		for ( int i: PushdownUtility.getPredicateColumns(pred) ) {
			if ( i < firstCol || i > lastCol )
				return false;
		}
		return true;
	}

}
