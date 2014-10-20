package fr.inria.oak.paxquery.algebra.optimizer.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.binary.Join;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;

public class SelectionCartesianProductToJoin implements OptimizationRule {

	@Override
	public void applyOptimization(LogicalPlan logicalPlan) {
		BaseLogicalOperator construct = logicalPlan.getRoot();
		traversePlanRecursive(construct);	
	}
	
	/*private void traversePlanRecursive(BaseLogicalOperator root) {
		ArrayList<BaseLogicalOperator> children = root.getChildren();
		if(children != null && children.size() > 0) {
			if(root instanceof Selection && children.get(0) instanceof CartesianProduct) {
				//do the actual operator replacement
				BaseLogicalOperator result = replaceOperators((Selection)root, (CartesianProduct)children.get(0));
				if(result != null) {
					root = result;
					children = root.getChildren();
				}
			}		
			//keep traversing the plan recursively. The base case is when children == null || children.size() == 0
			for(BaseLogicalOperator child : children)
				traversePlanRecursive(child);
		}
	}*/
	private void traversePlanRecursive(BaseLogicalOperator root) {
		ArrayList<BaseLogicalOperator> children = root.getChildren();
		if(children != null && children.size() > 0) {
			if(root instanceof Selection && children.get(0) instanceof CartesianProduct) {
				//do the actual operator replacement
				BaseLogicalOperator result = replaceOperators((Selection)root, (CartesianProduct)children.get(0));
				if(result != null) {
					root = result;
					children = root.getChildren();
					//call again on this same node
					traversePlanRecursive(root);
				}
			}
			else if(root instanceof Join) {
				if(((Join)root).getLeft() instanceof CartesianProduct) {
					replaceOperators((Join)root, (CartesianProduct)((Join)root).getLeft());
					children = root.getChildren();
					traversePlanRecursive(root);
				}
				if(((Join)root).getRight() instanceof CartesianProduct) {
					replaceOperators((Join)root, (CartesianProduct)((Join)root).getRight());
					children = root.getChildren();
					traversePlanRecursive(root);
				}
			}
			else {
				//keep traversing the plan recursively. The base case is when children == null || children.size() == 0
				for(BaseLogicalOperator child : children)
					traversePlanRecursive(child);
			}
		}
	}
	/**
	 * Join - CP -> Join - Join
	 */
	private void replaceOperators(Join join, CartesianProduct cp) {
		//declare stack
		Stack<BasePredicate> stack = new Stack<BasePredicate>();
		//declare disjPred
		DisjunctivePredicate disjPred = (DisjunctivePredicate) join.getPred();
		List<ConjunctivePredicate> conjPreds = disjPred.getConjunctivePreds();
		for(int conjIndex = conjPreds.size()-1; conjIndex >= 0; conjIndex--) {
			ConjunctivePredicate conjPred = conjPreds.get(conjIndex);
			List<SimplePredicate> simplePreds = conjPred.getSimplePreds();
			for(int simpleIndex = simplePreds.size()-1; simpleIndex >= 0; simpleIndex--) {
				SimplePredicate simplePred = simplePreds.get(simpleIndex);
				//in a join there are only predicates with two variables
				if(isVarInTreePatternList(cp.getNavigationTreePatterns(), simplePred.getVariable1()) && isVarInTreePatternList(cp.getNavigationTreePatterns(), simplePred.getVariable2())) {
					stack.push(simplePred);
					simplePreds.remove(simpleIndex);
				}
			}
			if(conjPred.getSimplePreds().size() == 0)
				conjPreds.remove(conjIndex);
			
			//collect all pending SimplePredicates
			ArrayList<SimplePredicate> simplePredsBelow = new ArrayList<SimplePredicate>();
			while(stack.empty() == false && stack.peek() instanceof SimplePredicate)
				simplePredsBelow.add((SimplePredicate) stack.pop());	
			if(simplePredsBelow.size() > 0)
				stack.add(new ConjunctivePredicate(simplePredsBelow));
		}
		ArrayList<ConjunctivePredicate> conjPredsBelow = new ArrayList<ConjunctivePredicate>();
		while(stack.empty() == false && stack.peek() instanceof ConjunctivePredicate)
			conjPredsBelow.add((ConjunctivePredicate) stack.pop());
		DisjunctivePredicate disjPredBelow = new DisjunctivePredicate(conjPredsBelow);
		//create the child join
		Join childJoin = new Join(cp.getLeft(), cp.getRight(), disjPredBelow);
		if(join.getLeft() == cp)
			join.setLeft(childJoin);
		else if(join.getRight() == cp)
			join.setRight(childJoin);
		childJoin.setParent(join);
	}
	
	private boolean isVarInTreePatternList(ArrayList<NavigationTreePattern> ntps, Variable var) {
		for(NavigationTreePattern ntp : ntps) {
			if(var.getTreePattern() == ntp)
				return true;
		}		
		return false;
	}
	

	/**
	 * Replaces the sel-cp construction by a join, and returns the join if the replacement was actually done. Otherwise, it returns null.
	 * @param sel
	 * @param cp
	 * @return
	 */
	private BaseLogicalOperator replaceOperators(Selection sel, CartesianProduct cp) {		
		reorganizeSelections(sel, cp);
		
		int selIndex = sel.getParent().getChildIndex(sel);
		if(selIndex != -1) {
			Join join = new Join(cp.getLeft(), cp.getRight(), sel.getPred());
			//LeftOuterJoin join = new LeftOuterJoin(cp.getLeft(), cp.getRight(), sel.getPred(), 0, 0);
			sel.getParent().getChildren().set(selIndex, join);
			join.setParent(sel.getParent());
			join.getLeft().setParent(join);
			join.getRight().setParent(join);			
			return join;
		}
		return null;
	}
	
	/**
	 * TODO: change stack.add for stack.push!!!!!!!!
	 * @param sel
	 * @param cp
	 */
	private void reorganizeSelections(Selection sel, CartesianProduct cp) {
		Stack<BasePredicate> stackLeft = new Stack<BasePredicate>();
		Stack<BasePredicate> stackRight = new Stack<BasePredicate>();
		DisjunctivePredicate disjPred = (DisjunctivePredicate) sel.getPred();
		List<ConjunctivePredicate> conjPreds = disjPred.getConjunctivePreds();
		for(int conjIndex = conjPreds.size()-1; conjIndex >= 0; conjIndex--) {
			ConjunctivePredicate conjPred = conjPreds.get(conjIndex);
			List<SimplePredicate> simplePreds = conjPred.getSimplePreds();
			for(int simpleIndex = simplePreds.size()-1; simpleIndex >= 0; simpleIndex--) {
				SimplePredicate simplePred = simplePreds.get(simpleIndex);
				if(simplePred.comparesToConstant() == true) {
					if(cp.isInNavigationTreePatternsLeft(simplePred.getVariable1().getTreePattern()) == true)
						stackLeft.add(simplePred);
					if(cp.isInNavigationTreePatternsRight(simplePred.getVariable1().getTreePattern()) == true)
						stackRight.add(simplePred);
					simplePreds.remove(simpleIndex);					
				}
			}
			if(conjPred.getSimplePreds().size() == 0)
				conjPreds.remove(conjIndex);
			
			//collect all pending SimplePredicatesLeft
			ArrayList<SimplePredicate> simplePredsLeft = new ArrayList<SimplePredicate>();
			while(stackLeft.empty() == false && stackLeft.peek() instanceof SimplePredicate)
				simplePredsLeft.add((SimplePredicate) stackLeft.pop());	
			if(simplePredsLeft.size() > 0)
				stackLeft.add(new ConjunctivePredicate(simplePredsLeft));
			//collect all pending SimplePredicatesRight
			ArrayList<SimplePredicate> simplePredsRight = new ArrayList<SimplePredicate>();
			while(stackRight.empty() == false && stackRight.peek() instanceof SimplePredicate)
				simplePredsRight.add((SimplePredicate) stackRight.pop());
			if(simplePredsRight.size() > 0)
				stackRight.add(new ConjunctivePredicate(simplePredsRight));			
		}
		//DisjunctivePredicate newDPLeft = new DP(stackLeft.getAllConjunctivePreds)
		//stackLeft.add(newDPLeft)
		ArrayList<ConjunctivePredicate> conjPredsLeft = new ArrayList<ConjunctivePredicate>();
		while(stackLeft.empty() == false && stackLeft.peek() instanceof ConjunctivePredicate)
			conjPredsLeft.add((ConjunctivePredicate) stackLeft.pop());
		DisjunctivePredicate newDisjPredLeft = new DisjunctivePredicate(conjPredsLeft);
		//DisjunctivePredicate newDPRight = new DP(stackRight.getAllConjunctivePreds)
		//stackRight.add(newDPRight)
		ArrayList<ConjunctivePredicate> conjPredsRight = new ArrayList<ConjunctivePredicate>();
		while(stackRight.empty() == false && stackRight.peek() instanceof ConjunctivePredicate)
			conjPredsRight.add((ConjunctivePredicate) stackRight.pop());
		DisjunctivePredicate newDisjPredRight = new DisjunctivePredicate(conjPredsRight);
		//Selection selLeft = new Selection(cp.left, newDPLeft)
		//cp.left = selLeft
		if(conjPredsLeft.size() > 0) {
			Selection selLeft = new Selection(cp.getLeft(), newDisjPredLeft);
			cp.setLeft(selLeft);
		}
		//Selection selRight = new Selection(cp.right, newDPRight)
		//cp.right = selRight
		if(conjPredsRight.size() > 0) {
			Selection selRight = new Selection(cp.getRight(), newDisjPredRight);
			cp.setRight(selRight);
		}
	}
	
}
