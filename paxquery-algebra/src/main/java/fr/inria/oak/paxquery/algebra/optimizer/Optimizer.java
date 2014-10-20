package fr.inria.oak.paxquery.algebra.optimizer;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.optimizer.rules.OptimizationRule;
import fr.inria.oak.paxquery.algebra.optimizer.rules.SelectionCartesianProductToJoin;

public class Optimizer {
	private static ArrayList<OptimizationRule> ruleList;
	
	/**
	 * Builds a hardcoded list of optimization rules that will be applied to a logical plan
	 */
	public Optimizer() {
		ruleList = new ArrayList<OptimizationRule>();
		ruleList.add(new SelectionCartesianProductToJoin());
	}
	
	/**
	 * Parses a text file containing a list of optimization rules that will be applied to a logical plan
	 * @param filePath text file containing the list of optimization rules
	 * TODO: not implemented yet!
	 */
	public Optimizer(String filePath) {
		//TODO
	}
	
	public void optimize(LogicalPlan logicalPlan) {
		logicalPlan.adjustParents();
		logicalPlan.adjustNavigationTreePatterns();
		for(OptimizationRule rule : ruleList)
			rule.applyOptimization(logicalPlan);
	}
}
