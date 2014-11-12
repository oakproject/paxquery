package fr.inria.oak.paxquery.xparser.mapping;

import java.util.ArrayList;
import java.util.List;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.border.*;
import fr.inria.oak.paxquery.algebra.operators.unary.DuplicateElimination;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;

/**
 * Goes through a logical plan and updates the positions of the columns used as data inputs.
 * This update is needed since after having parsed the query the variables' positions in the pattern node forest are recomputed.
 * @author jalvaro
 */
public class LogicalPlanRemapper {
	public static boolean print = true;

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
		VariablePositionEquivalences equivalences = null;
		if(operator instanceof XMLTreeConstruct)
			equivalences = visitLogicalOperator((XMLTreeConstruct) operator, varMap);
		else if(operator instanceof DuplicateElimination)
			equivalences = visitLogicalOperator((DuplicateElimination) operator, varMap);
		else if(operator instanceof Selection)
			equivalences = visitLogicalOperator((Selection) operator, varMap);
		else if(operator instanceof GroupBy)
			equivalences = visitLogicalOperator((GroupBy) operator, varMap);
		else if(operator instanceof LeftOuterNestedJoin)
			equivalences = visitLogicalOperator((LeftOuterNestedJoin) operator, varMap);
		//nothing is done for XMLScan, CartesianProduct
		else if(!(operator instanceof XMLScan) && !(operator instanceof CartesianProduct))
			throw new PAXQueryExecutionException("Variable remapping not implemented for operator " + operator.getName());

		if(print == true && equivalences != null)
			System.out.println("VarMap for " + operator.getClass().getSimpleName() +" " + varMap.printNamesFinals(equivalences));
	}
	
	private static VariablePositionEquivalences visitLogicalOperator(XMLTreeConstruct construct, VarMap varMap) {
		//first find the XMLScan operators that hang from construct
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(construct, scans);
		//then calculate the positions of the variables in those XMLScans relative to construct
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//then substitute
		substituteVariablesInCTP(construct.getConstructionTreePattern().getRoot(), equivalences);
		
		return equivalences;
	}
	
	private static void substituteVariablesInCTP(ConstructionTreePatternNode node, VariablePositionEquivalences equivalences) {
		//substitute
		substituteVariablesInCTPNode(node, equivalences);
		//go to children
		for(ConstructionTreePatternNode child : node.getChildren())
			substituteVariablesInCTP(child, equivalences);
	}
	
	private static void substituteVariablesInCTPNode(ConstructionTreePatternNode node, VariablePositionEquivalences equivalences) {
		if(node.getContentType() == ContentType.VARIABLE_PATH) {
			//node.setVarPath(equivalences.getEquivalence(node.getVarPath()));
			List<Integer> varPath = node.getVarPath();
			varPath.set(0, equivalences.getEquivalence(varPath.get(0)));
			for(int i = 1; i < varPath.size(); i++) {
				if(varPath.get(i) == -1)
					varPath.set(i, equivalences.getEquivalence(varPath.get(i)));
			}
			node.setVarPath(varPath);
		}
	}

	private static VariablePositionEquivalences visitLogicalOperator(DuplicateElimination dupElim, VarMap varMap) { 
		//first find the XMLScan operators that hang from dupElim
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(dupElim, scans);
		//then calculate the positions of the variables in those XMLScans relative to dupElim
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		for(int i = 0; i < dupElim.columns.length; i++)
			dupElim.columns[i] = equivalences.getEquivalence(dupElim.columns[i]);
		dupElim.buildOwnDetails();
		
		return equivalences;
	}
	
	private static VariablePositionEquivalences visitLogicalOperator(Selection selection, VarMap varMap) {
		//first find the XMLScan operators that hang from selection
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(selection, scans);
		//then calculate the positions of the variables in those XMLScans relative to selection
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
		
		return equivalences;
	}
	
	private static VariablePositionEquivalences visitLogicalOperator(GroupBy groupBy, VarMap varMap) {
		//first find the XMLScan operators that hang from groupBy
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(groupBy, scans);
		//then calculate the positions of the variables in those XMLScans relative to groupBy
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		int[] columns = groupBy.getGroupByColumns();
		for(int i = 0; i < columns.length; i++)
			columns[i] = equivalences.getEquivalence(columns[i]);
		groupBy.setGroupByColumns(columns);
		if(print) {
			System.out.print("groupByColumns: { ");
			for(int i = 0; i < columns.length-1; i++) {
				System.out.print(columns[i] + ", ");
			}
			System.out.println(columns[columns.length-1]+" }");
		}

		columns = groupBy.getReduceByColumns();
		for(int i = 0; i < columns.length; i++)
			columns[i] = equivalences.getEquivalence(columns[i]);
		groupBy.setReduceByColumns(columns);
		if(print) {
			System.out.print("reduceByColumns: { ");
			for(int i = 0; i < columns.length-1; i++) {
				System.out.print(columns[i] + ", ");
			}
			System.out.println(columns[columns.length-1]+" }");
		}

		columns = groupBy.getNestColumns();
		for(int i = 0; i < columns.length; i++)
			columns[i] = equivalences.getEquivalence(columns[i]);
		groupBy.setNestColumns(columns);
		if(print) {
			System.out.print("nestColumns: { ");
			for(int i = 0; i < columns.length-1; i++) {
				System.out.print(columns[i] + ", ");
			}
			System.out.println(columns[columns.length-1]+" }");
		}
		
		return equivalences;
	}
		
	private static VariablePositionEquivalences visitLogicalOperator(LeftOuterNestedJoin lonjoin, VarMap varMap) {
		//remap documentIDColumn and nodeIDColumns
		//first find the XMLScan operators that hang from groupBy
		ArrayList<XMLScan> scans = new ArrayList<XMLScan>();
		findXMLScanDescendants(lonjoin, scans);
		//then calculate the positions of the variables in those XMLScans relative to lonjoin
		VariablePositionEquivalences equivalences = varMap.calculateFinalPositions(scans);
		//now substitute
		lonjoin.setDocumentIDColumn(equivalences.getEquivalence(lonjoin.getDocumentIDColumn()));
		if(print)
			System.out.println("documentIDColumn: "+lonjoin.getDocumentIDColumn());
		int[] nodeIDs = lonjoin.getNodeIDColumns();
		for(int i = 0; i < nodeIDs.length; i++)
			nodeIDs[i] = equivalences.getEquivalence(nodeIDs[i]);
		lonjoin.setNodeIDColumns(nodeIDs);
		if(print) {
			System.out.print("nodeIDColumns: {");
			for(int i = 0; i < nodeIDs.length-1; i++)
				System.out.print(nodeIDs[i] + ", ");
			System.out.println(nodeIDs[nodeIDs.length-1]+" }");
		}

		//now remap the predicate
		for(ConjunctivePredicate conjPred : ((DisjunctivePredicate)lonjoin.getPred()).getConjunctivePreds()) {
			for(SimplePredicate simplePred : conjPred.getSimplePreds()) {
				if(simplePred.getVariable1() != null)
					simplePred.setColumn1(equivalences.getEquivalence(simplePred.getColumn1()));
				if(simplePred.getVariable2() != null)
					simplePred.setColumn2(equivalences.getEquivalence(simplePred.getColumn2()));
			}
		}
		if(print)
			System.out.println("Predicate: "+lonjoin.getPred().toString());
		
		return equivalences;
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
	
	/**
	 * Returns true if "var" points to a node of any of the TPs contained in "scans"; false otherwise
	 * @param var the variable
	 * @param scans the XMLScan objects that store TPs
	 * @return true if "var" points to a node of any of the TPs contained in "scans"; false otherwise; false if "var" or "scans" are null.
	 */
	private static boolean isVariableInXMLScans(Variable var, ArrayList<XMLScan> scans) {
		if(var == null || scans == null)
			return false;
		for(XMLScan scan : scans) {
			if(var.getTreePattern() == scan.getNavigationTreePattern())
				return true;
		}
		return false;
	}
}