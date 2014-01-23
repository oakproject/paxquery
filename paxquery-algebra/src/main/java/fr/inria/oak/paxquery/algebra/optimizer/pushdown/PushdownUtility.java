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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.Join;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterJoin;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.Navigation;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;

/**
 * This class provides static methods for logical plan rewriting.
 * It implements methods used by {@link PushSelections} and {@link PushProjections}.
 * 
 */
public class PushdownUtility {
	
	private static final Log logger = LogFactory.getLog(PushdownUtility.class);


	/**
	 * <p>This method recreates an existing {@link LogicalOperator} to take into account any possible
	 * changes to its children. It updates projection masks and predicates to match any column index
	 * changes.</p>
	 * 
	 * <p>Supported operators: {@link Selection}, {@link Sort}, {@link Projection}, {@link Join},
	 * {@link StructJoin}, {@link CartesianProduct}, {@link NaryAppend}, {@link SimpleUnion}<br />
	 * Unsupported operators are simply returned as they were given.</p>
	 * 
	 * @param op the logical operator to be recreated
	 * @param newChildren the list of new children of the logical operator
	 * @param myUpdatedColumns an array of old column indices, indexed by the new column indices,
	 * or <tt>null</tt> if an identical mapping is desired.
	 * @return a logical operator that matches the given rewritings
	 * @throws PAXQueryExecutionException when the recreation of the logical operator itself failed
	 */
	public static BaseLogicalOperator recreateOp(BaseLogicalOperator op, ArrayList<BaseLogicalOperator> newChildren, ArrayList<Integer> myUpdatedColumns) {
		// Do not recreate operators unless their rewriting is explicitly implemented.
		BaseLogicalOperator newOp = op;
		// Compute the map of old-to-new indices.
		Map<Integer, Integer> oldColumnMap = new HashMap<Integer, Integer>();
		if ( myUpdatedColumns != null ) {
			for ( int i = 0; i < myUpdatedColumns.size(); i++ ) {
				oldColumnMap.put(myUpdatedColumns.get(i), i);
			}			
		} else {
			for ( int i = 0; i < op.getNRSMD().colNo; i++ ) {
				oldColumnMap.put(i, i);
			}
		}

		// Recreate logical operator with given children
		if ( op instanceof BaseUnaryOperator ) {
			assert newChildren.size() == 1;
			BaseLogicalOperator newChild = newChildren.get(0);
			// Create the new operator
			if ( op instanceof Selection ) {
				Selection sel = (Selection) op;
				newOp = new Selection(newChild, updatePredicate(sel.getPred(), oldColumnMap));
			} else if ( op instanceof Navigation) {
				Navigation pnop = (Navigation) op;
				// First we update the columns used for the navigation with the new
				// values contained in oldColumnMap
				int newColumnsPos = oldColumnMap.get(pnop.pos);
				// We copy the values contained in the oldColumnMap in int[] newColumns
				int[] newColumns = new int[oldColumnMap.size()];
				Iterator<Integer> oldColumnMapKeysIterator = oldColumnMap.keySet().iterator();
				for ( int i = 0; i < oldColumnMap.size(); i++ ) {
					newColumns[i] = oldColumnMapKeysIterator.next();
				}
				// Finally we create the PatternNavigate pointing to the new child, with the columns used
				// for the navigation updated as well as the NRSMD updated
				newOp = new Navigation(newChild, newColumnsPos, pnop.navigationTreePattern);
			} else if ( op instanceof Projection ) {
				Projection proj = (Projection) op;
				int[] projMask = updateProjMask(proj.columns, oldColumnMap);
				// An identical projection should be skipped.
				boolean identicalProjection = true;
				if ( projMask.length < proj.getChild().getNRSMD().colNo ) {
					identicalProjection = false;
				} else {
					for ( int i = 0; i < projMask.length; i++ ) {
						if ( i != projMask[i] ) {
							identicalProjection = false;
							break;
						}
					}
				}
				
				if ( !identicalProjection && !(newChild instanceof Projection) ) {
					newOp = new Projection(newChild, projMask);
				} else {
					if ( identicalProjection ) {
						return newChild;
					}
						
					/* (newChild instanceof Projection) */
					// The child is also a projection; it will be recreated and returned.
					// First, create the new projection mask by combining the current two,
					int[] childProjMask = new int[projMask.length];
					for ( int i = 0; i < projMask.length; i++ ) {
						childProjMask[i] = ((Projection) newChild).columns[projMask[i]];
					}
					// then create the Projection operator,
					BaseLogicalOperator grandChild = newChild.getChildren().get(0);
					newChild = new Projection(grandChild, childProjMask);
					return newChild;
				}
			}
		} else if ( op instanceof BaseBinaryOperator ) {
			assert newChildren.size() == 2;
			BaseLogicalOperator left = newChildren.get(0);
			BaseLogicalOperator right = newChildren.get(1);
			// Create the new operator
			if ( op instanceof Join ) {
				Join join = (Join) op;
				newOp = new Join(left, right, updatePredicate(join.getPred(), oldColumnMap));
			}
			else if ( op instanceof LeftOuterJoin ) {
				LeftOuterJoin join = (LeftOuterJoin) op;
				newOp = new LeftOuterJoin(left, right, updatePredicate(join.getPred(), oldColumnMap),
						oldColumnMap.get(join.getDocumentIDColumn()), oldColumnMap.get(join.getNodeIDColumn()));
			}
			else if ( op instanceof LeftOuterNestedJoin ) {
				LeftOuterNestedJoin join = (LeftOuterNestedJoin) op;
				
				newOp = new LeftOuterNestedJoin(left, right, updatePredicate(join.getPred(), oldColumnMap),
						oldColumnMap.get(join.getDocumentIDColumn()), oldColumnMap.get(join.getNodeIDColumn()));
			}
			else
				throw new PAXQueryExecutionException("The type of binary operator is not supported by PushDown utilities.");
		}
		return newOp;
	}


	/**
	 * This method updates a predicate to take into account any column index modification.
	 * 
	 * @param pred the existing predicate
	 * @param oldColumnMap the map of old column indices to updated ones
	 * @return a new predicate with updated column indices
	 */	
	public static BasePredicate updatePredicate(BasePredicate pred, Map<Integer, Integer> oldColumnMap) {
		if(pred instanceof DisjunctivePredicate)
			return updateDisjunctivePredicate((DisjunctivePredicate)pred, oldColumnMap);
		else if(pred instanceof ConjunctivePredicate)
			return updateConjunctivePredicate((ConjunctivePredicate)pred, oldColumnMap);
		else if(pred instanceof SimplePredicate)
			return updateSimplePredicate((SimplePredicate)pred, oldColumnMap);
		
		throw new PAXQueryExecutionException("Predicate type not supported!");
	}
	
	private static DisjunctivePredicate updateDisjunctivePredicate(DisjunctivePredicate pred, Map<Integer, Integer> oldColumnMap) {
		ArrayList<ConjunctivePredicate> preds = new ArrayList<ConjunctivePredicate>();
		for(ConjunctivePredicate conjPred: pred.getConjunctivePreds())
			preds.add(updateConjunctivePredicate(conjPred, oldColumnMap));
		return new DisjunctivePredicate(preds);
	}
	
	private static ConjunctivePredicate updateConjunctivePredicate(ConjunctivePredicate pred, Map<Integer, Integer> oldColumnMap) {
		ArrayList<SimplePredicate> preds = new ArrayList<SimplePredicate>();
		for(SimplePredicate simplePred: pred.getSimplePreds())
			preds.add(updateSimplePredicate(simplePred, oldColumnMap));
		return new ConjunctivePredicate(preds);
	}
	

	/*
	 * This method updates a simple predicate to take into account any column index modification.
	 * It only works on {@link SimplePredicate} instances, and it is used by the
	 * {@link #updatePredicate(Predicate, Map)} function.
	 * 
	 * @param pred the existing simple predicate
	 * @param oldColumnMap the map of old column indices to updated ones
	 * @return a new predicate with updated column indices
	 */
	private static SimplePredicate updateSimplePredicate(SimplePredicate pred, Map<Integer, Integer> oldColumnMap) {
		if (pred.getStringConstant() != null)
			return new SimplePredicate(
					oldColumnMap.containsKey(pred.getColumn1()) ? oldColumnMap.get(pred.getColumn1()) : pred.getColumn1(),
					pred.getStringConstant(),
					pred.getPredCode());
		else if (pred.getDoubleConstant() != -1)
			return new SimplePredicate(
					oldColumnMap.containsKey(pred.getColumn1()) ? oldColumnMap.get(pred.getColumn1()) : pred.getColumn1(),
					pred.getDoubleConstant(),
					pred.getPredCode());
		else if (pred.getOperation1() != null || pred.getOperation2() != null)
			return new SimplePredicate(
					oldColumnMap.containsKey(pred.getColumn1()) ? oldColumnMap.get(pred.getColumn1()) : pred.getColumn1(),
					pred.getOperation1(),
					oldColumnMap.containsKey(pred.getColumn2()) ? oldColumnMap.get(pred.getColumn2()) : pred.getColumn2(),
					pred.getOperation2(),
					pred.getPredCode());
		
		return new SimplePredicate(
				oldColumnMap.containsKey(pred.getColumn1()) ? oldColumnMap.get(pred.getColumn1()) : pred.getColumn1(),
				oldColumnMap.containsKey(pred.getColumn2()) ? oldColumnMap.get(pred.getColumn2()) : pred.getColumn2(),
				pred.getPredCode());
	}
	
	/*
	 * This method updates the columns of a {@link Projection} to take into
	 * account any column index modification.
	 * 
	 * @param oldColumns the array of column indices in the existing projection
	 * @param oldColumnMap the map of old column indices to updated ones
	 * @return an array of column indices representing the updated project mask
	 */
	private static int[] updateProjMask(int[] oldColumns, Map<Integer, Integer> oldColumnMap) {
		ArrayList<Integer> newColumnList = new ArrayList<Integer>();
		for ( int i=0; i<oldColumns.length; i++ ) {
			if ( oldColumnMap.containsKey(oldColumns[i]) ) {
				newColumnList.add(oldColumnMap.get(oldColumns[i]));
			}
		}
		int[] newColumns = new int[newColumnList.size()];
		int i=0;
		for ( int j: newColumnList ) {
			newColumns[i++] = j;
		}
		return newColumns;
	}
	

	/**
	 * <p>This method returns the columns that are referred to by a given
	 * predicate.</p>
	 * 
	 * <p>Supported predicates: {@link SimplePredicate}, {@link ConjunctivePredicate}</p>
	 */
	public static Set<Integer> getPredicateColumns(BasePredicate pred) {
		HashSet<Integer> predicateColumns = new HashSet<Integer>();
		
		int[][] leftColumns = pred.getLeftColumns();
		for(int i=0; i<leftColumns.length; i++)
			for(int j=0; j<leftColumns[i].length; j++)
				predicateColumns.add(leftColumns[i][j]);
		int[][] rightColumns = pred.getRightColumns();
		for(int i=0; i<rightColumns.length; i++)
			for(int j=0; j<rightColumns[i].length; j++)
				predicateColumns.add(rightColumns[i][j]);
		
		return predicateColumns;
	}
	
	
}
