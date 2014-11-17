package fr.inria.oak.paxquery.xparser.operatorinfo;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public abstract class BaseNestingOperatorInfo {
	public ArrayList<BaseLogicalOperator> outerVariableHolderOperators;	//TPs in these scans are used to build GroupBy.GroupByColumns
	public BaseLogicalOperator innerVariableHolderOperatorsRoot;	//the root for the subtree in the inner part of the subquery
	public Variable outerVariable;				//the variable to which the subquery is assigned
	public Variable returnedVariable;			//the variable returned inside the subquery
	public VarMap varMap;						//a varmap with all variables in the query

	public BaseNestingOperatorInfo(ArrayList<BaseLogicalOperator> outerXMLScans, BaseLogicalOperator innerVariableHolderOperatorsRoot, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		this.outerVariableHolderOperators = outerXMLScans;
		this.innerVariableHolderOperatorsRoot = innerVariableHolderOperatorsRoot;
		this.outerVariable = outerVariable;
		this.returnedVariable = returnedVariable;
		this.varMap = varMap;
	}
	
	/**
	 * Updates the internal state of a BaseLogicalOperator. The operator itself is declared in inheriting classes.
	 */
	public abstract void updateOperatorState() throws PAXQueryExecutionException;
	
	/**
	 * Find variables in the inner part of the subquery in postorder 
	 * @return all variables appearing in XMLScan and Aggregation operators (for the moment) in postorder
	 */
	protected ArrayList<Variable> getInnerVariables() {
		ArrayList<Variable> innerVars = new ArrayList<Variable>();
		getInnerVariablesRecursive(innerVariableHolderOperatorsRoot, innerVars);

		return innerVars;
	}
	
	private void getInnerVariablesRecursive(BaseLogicalOperator subtreeRoot, ArrayList<Variable> innerVars) {
		if(subtreeRoot.getChildren() != null) {
			for(BaseLogicalOperator child : subtreeRoot.getChildren())	
				getInnerVariablesRecursive(child, innerVars);
		}
		//visit this node
		if(subtreeRoot instanceof XMLScan) {
			innerVars.addAll(((XMLScan)subtreeRoot).getNavigationTreePattern().getVariables(false, true, true));
		}
		else if(subtreeRoot instanceof Aggregation)
			innerVars.add(((Aggregation)subtreeRoot).getOuterVariable());
	}
}
