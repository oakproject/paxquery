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

	/**
	 * Goes through a logical plan and updates the positions of the columns used as data inputs.
	 * @param logicalPlan the plan to go through
	 * @param varMap a VarMap object containing the variables remapping information
	 */
	private static void traverseLogicalPlanDFS(BaseLogicalOperator operator, VarMap varMap) {
		//visit this node
		visitLogicalOperator(operator, varMap);
		ArrayList<BaseLogicalOperator> children = operator.getChildren();
		if(children != null) {
			//recursive call for descendant sub-trees, recursion ends when children.size()==0
			for(BaseLogicalOperator child : children)
				traverseLogicalPlanDFS(child, varMap);
		}
	}
	
	/**
	 * Change the temporary positions of variables used in operator to their final temporary positions according to varMap
	 * @param operator the logical operator
	 * @param varMap a VarMap object containing the temporary positions of variables
	 */
	private static void visitLogicalOperator(BaseLogicalOperator operator, VarMap varMap) {
		if(operator instanceof XMLConstruct)
			visitLogicalOperator((XMLConstruct) operator, varMap);
		else if(operator instanceof DuplicateElimination)
			visitLogicalOperator((DuplicateElimination) operator, varMap);
		else if(operator instanceof Selection)
			visitLogicalOperator((Selection) operator, varMap);
		//nothing is done for XMLScan, CartesianProduct
		else if(!(operator instanceof XMLScan) && !(operator instanceof CartesianProduct))
			throw new PAXQueryExecutionException("Variable remapping not implemented for operator " + operator.getName());
	}
	
	private static void visitLogicalOperator(XMLConstruct construct, VarMap varMap) {
		//first find the XMLScan operators that hang from construct
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(construct, scans);
		//then calculate the positions of the variables in those XMLScans relative to dupElim
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		int[] fields = construct.getApply().getFields();
		construct.getApply().setFields(equivalences.getEquivalence(fields));

		ApplyConstruct[] nested = construct.getApply().getNested();
		if(nested != null) {
			for(int i = 0; i < nested.length; i++)
				nested[i].setFields(equivalences.getEquivalence(nested[i].getFields()));
		}
	}

	private static void visitLogicalOperator(DuplicateElimination dupElim, VarMap varMap) { 
		//first find the XMLScan operators that hang from dupElim
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(dupElim, scans);
		//then calculate the positions of the variables in those XMLScans relative to dupElim
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		for(int i = 0; i < dupElim.columns.length; i++)
			dupElim.columns[i] = equivalences.getEquivalence(dupElim.columns[i]);
		dupElim.buildOwnDetails();
	}
	
	private static void visitLogicalOperator(Selection selection, VarMap varMap) {
		//first find the XMLScan operators that hang from selection
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(selection, scans);
		//them calculate the positions of the variables in those XMLScans relative to selection
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		for(ConjunctivePredicate conjPred : ((DisjunctivePredicate) selection.getPred()).getConjunctivePreds()) {
			for(SimplePredicate simplePred : conjPred.getSimplePreds()) {
				if(simplePred.getColumn1() != -1)
					simplePred.setColumn1(equivalences.getEquivalence(simplePred.getColumn1()));
				if(simplePred.getColumn2() != -1)
				simplePred.setColumn2(equivalences.getEquivalence(simplePred.getColumn2()));
			}
		}		
		selection.buildOwnDetails();
	}
	
	/**
	 * Fills the scansList input list with pointers to all XMLScan objects that are descendants of operator. Recursive method. 
	 * @param operator the root of the subtree whose leaves are XMLScan operators
	 * @param scansList an ArrayList containing pointers to those XMLScan objects contained in the subtree whose root is operator
	 */
	private static void findXMLScanDescendants(BaseLogicalOperator operator, ArrayList<XMLScan> scansList) {
		if(scansList == null)
			scansList = new ArrayList<XMLScan>();
		
		if(operator instanceof XMLScan)
			scansList.add((XMLScan)operator);
		else {
			for(BaseLogicalOperator child : operator.getChildren())
				findXMLScanDescendants(child, scansList);
		}
	}
}