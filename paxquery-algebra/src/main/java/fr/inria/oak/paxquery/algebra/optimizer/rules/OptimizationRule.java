/**
 * 
 */
package fr.inria.oak.paxquery.algebra.optimizer.rules;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;

/**
 * @author jalvaro
 * Interface for optimization rules
 */
public interface OptimizationRule {
	/**
	 * Takes a logical (algebraic) plan as input and applies a given optimization on it.
	 * @param logicalPlan
	 */
	public void applyOptimization(LogicalPlan logicalPlan);
}
