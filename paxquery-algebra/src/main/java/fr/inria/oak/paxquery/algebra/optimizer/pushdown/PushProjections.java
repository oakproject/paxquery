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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseJoinOperator;
import fr.inria.oak.paxquery.algebra.operators.border.BaseLeafOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.Navigation;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.algebra.optimizer.Logical2Logical;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;

/**
 * This class contains the methods for pushing down projections in a given logical plan.
 * 
 */
public class PushProjections implements Logical2Logical {	
	
	/**
	 * <p>This method pushes down projections in a given logical plan while keeping the list of columns
	 * that result from the plan unchanged.</p>
	 * 
	 * <p>It first computes the required column set and then calls
	 * {@link #pushProjections(LogicalOperator, Set)}.</p>
  	 *
	 * @param op the root of the logical plan
	 * @return the new root of the logical plan
	 * @throws VIP2PExecutionException when the creation of a new logical operator fails
	 */
	@Override
	public BaseLogicalOperator transform(BaseLogicalOperator op) {
		Set<Integer> required = new HashSet<Integer>();
		for ( int i = 0; i < op.getNRSMD().colNo; i++ )
			required.add(i);
		return pushProjections(op, required);
	}
	
	/*
	 * <p>This method creates projections as low as possible in a subtree of a logical plan.
	 * It computes the columns that are required by the given {@link LogicalOperator}, taking
	 * into account the columns that are required above the subtree root.</p>
	 * 
	 * <p>The method calls itself recursively to for all children of the current logical operator.
	 * It returns a logical operator in which only provides the columns that are explicitly
	 * requested for in the columnsRequiredAbove set.</p>
	 *
	 * <p>Operators that are recursed into include any {@link UnaryOperator}, {@link BinaryOperator}
	 * or {@link NaryOperator}, with the notable exception of {@link PatternNavigate} which is
	 * explicitly treated as a leaf operator.</p>
	 * 
	 * <p>The columns of the returned {@link LogicalOperator} might change their indices to match the
	 * given column set, but they will not change their current ordering.</p>
	 * 
	 * <p>Instead of this function, call {@link #pushProjections(LogicalOperator)}, which computes the
	 * required columns itself.</p>
	 * 
	 * @param op the logical operator to process
	 * @param columnsRequiredAbove the set of columns that this operator needs to output
	 * @return a logical operator that matches the given output column set 
	 * @throws VIP2PExecutionException when the creation of a new logical operator fails
	 */
	private static BaseLogicalOperator pushProjections(BaseLogicalOperator op, Set<Integer> columnsRequiredAbove) {
		// The set of columns that the logical operator should have. It is built with the set of
		// columnsRequiredAbove and the columns referred by any predicate that the logical operator
		// would have.
		Set<Integer> requiredHere = new HashSet<Integer>(columnsRequiredAbove);
		// The columns that are referred by any predicate of the given logical operator.
		Set<Integer> predicateColumns = getPredicateColumns(op);
		requiredHere.addAll(predicateColumns);

		// Set of columns contained in the requiredHere set.
		// Columns in ascending order.
		ArrayList<Integer> myUpdatedColumns = new ArrayList<Integer>();
		// Children created by the method as we push projections.
		ArrayList<BaseLogicalOperator> newChildren = new ArrayList<BaseLogicalOperator>();
		
		
		if ( op instanceof BaseLeafOperator ) {
			// Leaf operators will keep all of their columns 
			//Parameters.logger.debug("pushProjections op instanceof LeafOperator: " + op);
			//Parameters.logger.debug("pushProjections op.nrsmd.colNo: " + op.getNRSMD().colNo);
			//if(op instanceof VectorTable){
			//Parameters.logger.debug("pushProjections op tuples: " + ((VectorTable)op).getTuples().size());
			//}
			for ( int i=0; i<op.getNRSMD().colNo; i++ )
				requiredHere.add(i);
		} else if ( op instanceof Navigation ) {
			Navigation pnop = (Navigation) op;
			Set<Integer> requiredFromChild = new HashSet<Integer>();
			// We add the columns that we need for the navigation to the 
			// requiredHere set
			requiredHere.add(pnop.pos);
			// Now we copy the elements of the requiredHere set to
			// myUpdatedColumns arraylist
			Iterator<Integer> elementsRequiredHere = requiredHere.iterator();
			while ( elementsRequiredHere.hasNext() ) {
				myUpdatedColumns.add(elementsRequiredHere.next());
			}
			// We define the requiredFromChild set of elements
			for ( int i = 0; i < pnop.getChild().getNRSMD().colNo; i++ ) {
				if ( requiredHere.contains(i) ) {
					requiredFromChild.add(i);
				}
			}
			// Recurse down
			newChildren.add(pushProjections(pnop.getChild(), requiredFromChild));
		} else if ( op instanceof Projection ) {
			Projection proj = (Projection) op;
			// Set of columns that we need from the logical operator's child.
			Set<Integer> requiredFromChild = new HashSet<Integer>();
			// Set of columns that the operator should project, taking in account
			// the current projection and the columns that the logical operator
			// should have (requiredHere set).
			Set<Integer> projColumnsRequiredHere = new HashSet<Integer>();
			for ( int i = 0; i < proj.columns.length; i++ ) {
				if ( requiredHere.contains(i) ) {
					projColumnsRequiredHere.add(proj.columns[i]);
				}
			}
			for ( int i = 0; i < proj.getChild().getNRSMD().colNo; i++ ) {
				if ( projColumnsRequiredHere.contains(i) ) {
					requiredFromChild.add(i);
					myUpdatedColumns.add(i);
				}
			}
			// Recurse down
			newChildren.add(pushProjections(proj.getChild(), requiredFromChild));
		} else if ( op instanceof BaseUnaryOperator ) {
			// Assumptions: UnaryOperators other than Projections only ever append columns.
			BaseUnaryOperator uop = (BaseUnaryOperator) op;
			Set<Integer> requiredFromChild = new HashSet<Integer>();
			for ( int i = 0; i < uop.getChild().getNRSMD().colNo; i++ ) {
				if ( requiredHere.contains(i) ) {
					requiredFromChild.add(i);
					myUpdatedColumns.add(i);
				}
			}
			// Recurse down
			newChildren.add(pushProjections(uop.getChild(), requiredFromChild));
		} else if ( op instanceof BaseBinaryOperator ) {
			// Assumption: BinaryOperators concatenate the nrsmd's of all their children.
			int childBaseIndex = 0;
			for ( BaseLogicalOperator child: op.getChildren() ) {
				// Get columns for each child.
				// Set of columns that we need from the logical operator's child.
				// The columns needed from the child are different from the columns
				// required here as we talking about binary operators.
				
				Set<Integer> requiredFromChild = new HashSet<Integer>();
				for ( int i = 0; i < child.getNRSMD().colNo; i++ ) {
					if ( requiredHere.contains(childBaseIndex+i) ) {
						requiredFromChild.add(i);
						myUpdatedColumns.add(childBaseIndex+i);
					}
				}

				// Recurse down
				newChildren.add(pushProjections(child, requiredFromChild));
				childBaseIndex += child.getNRSMD().colNo;
			}
		}
		
		BaseLogicalOperator newOp = op;
		// Only recreate the operator if there are new children computed.
		if ( newChildren.size() > 0 ) {
			assert newChildren.size() == op.getChildren().size();
			newOp = PushdownUtility.recreateOp(op, newChildren, myUpdatedColumns);
		}
		
		if ( newOp.getNRSMD().colNo > columnsRequiredAbove.size() ) {
			// Insert a projection above this operator.
			int projCols[] = new int[columnsRequiredAbove.size()];
			int here = 0;
			int above = 0;
			for ( int i = 0; i < op.getNRSMD().colNo; i++ ) {
				if ( columnsRequiredAbove.contains(i) ) {
					projCols[above++] = here++;
				} else if ( requiredHere.contains(i) ) {
					here++;
				}
			}
			Projection proj = new Projection(newOp, projCols);
			newOp = proj;
		}
		return newOp;
	}
	
	/*
	 * <p>This method returns the columns that are referred to by the predicate of a
	 * given logical operator.</p>
	 * 
	 * <p>Supported predicates: {@link SimplePredicate}, {@link ConjunctivePredicate}</p>
	 * 
	 * @param op a logical operator whose predicates will be read
	 * @return a set of the columns to which the predicates refer
	 */
	private static Set<Integer> getPredicateColumns(BaseLogicalOperator op) {
		BasePredicate pred = null;
		if ( op instanceof Selection ) {
			pred = ((Selection)op).getPred();
		} else if ( op instanceof BaseJoinOperator ) {
			pred = ((BaseJoinOperator)op).getPred(); 
		}

		return PushdownUtility.getPredicateColumns(pred);
	}
}
