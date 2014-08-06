package fr.inria.oak.paxquery.xparser.mapping;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLConstruct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.DuplicateElimination;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;

/**
 * Goes through a logical plan and updates the positions of the columns used as data inputs.
 * This update is needed since after having parsed the query the variables' positions in the pattern node forest are recomputed.
 * @author jalvaro
 */
public class LogicalPlanRemapper {

	/**
	 * Goes through a logical plan and updates the positions of the columns used as data inputs.
	 * @param logicalPlan the plan to go through
	 * @param varMap a VarMap object containing the variables remapping information
	 */
	public static void remapLogicalPlan(LogicalPlan logicalPlan, VarMap varMap) {
		traverseLogicalPlanDFS(logicalPlan.getRoot(), varMap);		
	}
	
	private static void traverseLogicalPlanDFS(BaseLogicalOperator operator, VarMap varMap) {
		//visit this node
		remapLogicalOperator(operator, varMap);
		ArrayList<BaseLogicalOperator> children = operator.getChildren();
		if(children != null) {
			//recursive call for descendant sub-trees, recursion ends when children.size()==0
			for(BaseLogicalOperator child : children)
				traverseLogicalPlanDFS(child, varMap);
		}
	}
	
	/**
	 * Change the temporary positions of variables used in operator to the final temporary positions
	 * @param operator the logical operator
	 * @param varMap a VarMap object containing the variables remapping information
	 */
	private static void remapLogicalOperator(BaseLogicalOperator operator, VarMap varMap) {
		if(operator instanceof XMLConstruct)
			remapLogicalOperator((XMLConstruct) operator, varMap);
		else if(operator instanceof DuplicateElimination)
			remapLogicalOperator((DuplicateElimination) operator, varMap);
		else if(operator instanceof Selection)
			remapLogicalOperator((Selection) operator, varMap);
		//nothing is done for XMLScan, CartesianProduct
		else if(!(operator instanceof XMLScan) && !(operator instanceof CartesianProduct))
			throw new PAXQueryExecutionException("Variable remapping not implemented for operator " + operator.getName());
	}
	
	private static void remapLogicalOperator(XMLConstruct construct, VarMap varMap) {
		int[] fields = construct.getApply().getFields();
		construct.getApply().setFields(varMap.translateTemporaryPositionToFinalPosition(fields));
		
		ApplyConstruct[] nested = construct.getApply().getNested();
		if(nested != null) {
			for(int i = 0; i < nested.length; i++)
				nested[i].setFields(varMap.translateTemporaryPositionToFinalPosition(nested[i].getFields()));
		}
	}

	private static void remapLogicalOperator(DuplicateElimination dupElim, VarMap varMap) { 
		for(int i = 0; i < dupElim.columns.length; i++)
			dupElim.columns[i] = varMap.translateTemporaryPositionToFinalPosition(dupElim.columns[i]);
	}
	
	private static void remapLogicalOperator(Selection selection, VarMap varMap) {
		for(ConjunctivePredicate conjPred : ((DisjunctivePredicate) selection.getPred()).getConjunctivePreds()) {
			for(SimplePredicate simplePred : conjPred.getSimplePreds()) {
				simplePred.setColumn1(varMap.translateTemporaryPositionToFinalPosition(simplePred.getColumn1()));
				simplePred.setColumn2(varMap.translateTemporaryPositionToFinalPosition(simplePred.getColumn2()));
			}
		}
		selection.buildOwnDetails();
	}
}
