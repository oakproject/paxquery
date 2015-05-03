package fr.inria.oak.paxquery.xparser.operatorinfo;

import java.util.ArrayList;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public class GroupByInfo extends BaseNestingOperatorInfo {
	public GroupBy groupByOperator;
	
	public GroupByInfo(GroupBy groupByOperator, ArrayList<BaseLogicalOperator> outerXMLScans, BaseLogicalOperator innerVariableHolderOperatorsRoot, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		super(outerXMLScans, innerVariableHolderOperatorsRoot, outerVariable, returnedVariable, varMap);
		this.groupByOperator = groupByOperator;
	}
	
	public GroupByInfo(GroupBy groupByOperator, ArrayList<BaseLogicalOperator> outerXMLScans, BaseLogicalOperator innerVariableHolderOperatorsRoot, Variable outerVariable, VarMap varMap) {
		super(outerXMLScans, innerVariableHolderOperatorsRoot, outerVariable, varMap);
		this.groupByOperator = groupByOperator;
	}
	
	public void updateOperatorState() {
		if(groupByOperator.getChild() instanceof CartesianProduct)
			buildOuterVariableHolderOperators(((CartesianProduct)(groupByOperator.getChild())).getLeft());
		//prepare reduceByColumns for the GroupBy
		ArrayList<Variable> idVars = new ArrayList<Variable>();
		for(BaseLogicalOperator operator : outerVariableHolderOperators) {
			if(operator instanceof XMLScan) {
				XMLScan scan = (XMLScan) operator;				
				//mark the XMLScan as attaching documentID
				scan.setAttachDocumentID(true);
				//add a variable pointing to the DocumentID column for this TP
				Variable var = new Variable("IDTP-"+scan.getNavigationTreePattern().getName(), Variable.VariableDataType.DocumentID);
				varMap.addNewVariable(var);
				idVars.add(var);
				//mark the associated TP's nodes as storesNodeID where needed
				ArrayList<Variable> newVariables = scan.getNavigationTreePattern().markAsStoresIDWhereNeeded();
				//add the new variables (which point to nodeIDs) to varMap
				varMap.addNewVariables(newVariables);
				idVars.addAll(newVariables);
			}
		}		
		int[] reduceByColumns = new int[idVars.size()];
		for(int i = 0; i < idVars.size(); i++)
			reduceByColumns[i] = varMap.getTemporaryPositionByName(idVars.get(i).name);
		
		//prepare groupByColumns for the GroupBy
		ArrayList<Variable> outerVars = new ArrayList<Variable>();
		for(BaseLogicalOperator operator : outerVariableHolderOperators) {
			if(operator instanceof XMLScan) {
				XMLScan scan = (XMLScan) operator;
				if(scan.isAttachDocumentID() == true)
					outerVars.add(varMap.getVariable("IDTP-"+scan.getNavigationTreePattern().getName()));
				outerVars.addAll(scan.getNavigationTreePattern().getAllVariables());
			}
			else if(operator instanceof Aggregation)
				outerVars.add(((Aggregation)operator).getOuterVariable());
		}
		int[] groupByColumns = new int[outerVars.size()];
		for(int i = 0; i < outerVars.size(); i++)
			groupByColumns[i] = varMap.getTemporaryPositionByName(outerVars.get(i).name);

		//prepare nestColumns for the GroupBy
		ArrayList<Variable> innerVars = getInnerVariables();
		int[] nestColumns = new int[innerVars.size()];
		for(int i = 0; i < innerVars.size(); i++) {
			nestColumns[i] = varMap.getTemporaryPositionByName(innerVars.get(i).name);
			//if(innerVars.get(i).name.compareTo(returnedVariable.name) == 0)	//when we find the variable returned in this subquery we add it as a nested variable in the outer variable
			//	outerVariable.addNestedVariable(returnedVariable, i);
		}
		//update the operator
		groupByOperator.setGroupByColumns(groupByColumns);
		groupByOperator.setReduceByColumns(reduceByColumns);
		groupByOperator.setNestColumns(nestColumns);
		
		updateInnerCTPsVarPos();
	}
	
	private void updateInnerCTPsVarPos() {
		if(outerVariable == null || outerVariable.nestedCTPRoot == null)
			return;
		
		ArrayList<Variable> innerVars = getInnerVariables();
		updateInnerCTPsVarPos(outerVariable.nestedCTPRoot, innerVars);
	}
	
	private void updateInnerCTPsVarPos(ConstructionTreePatternNode ctpNode, ArrayList<Variable> innerVars) {
		if(ctpNode.getContentType() == ContentType.VARIABLE_PATH) {
			List<Integer> varPath = ctpNode.getVarPath();
			if(varPath != null && varPath.size() > 1) {// || varPath.get(1) != -1) {
				for(int i = 1; i < varPath.size(); i++) {
					if(varPath.get(i) != -1) {
						String varName = varMap.getNameByTemporaryPosition(varPath.get(i));
						//int index = varPath.indexOf(varMap.getVariable(varName));
						int index = innerVars.indexOf(varMap.getVariable(varName));
						varPath.set(i, index);
					}
				}
			}
		}
		
		for(ConstructionTreePatternNode childNode : ctpNode.getChildren())
			updateInnerCTPsVarPos(childNode, innerVars);
	}
	
	/**
	 * Clears outerVariableHolderOperators and fills it with all BaseLogicalOperators hanging from nestingOperatorLeftChild that
	 * contain references to variables, for the moment: XMLScan and Aggregation.
	 * Recursive method
	 * @param nestingOperatorLeftChild
	 */
	private void buildOuterVariableHolderOperators(BaseLogicalOperator nestingOperatorLeftChild) {
		outerVariableHolderOperators = new ArrayList<BaseLogicalOperator>();
		buildOuterVariableHolderOperatorsRecursive(nestingOperatorLeftChild);
	}
	
	private void buildOuterVariableHolderOperatorsRecursive(BaseLogicalOperator subtreeRoot) {
		if(subtreeRoot.getChildren() != null) {
			for(BaseLogicalOperator child : subtreeRoot.getChildren())
				buildOuterVariableHolderOperatorsRecursive(child);
		}
		//visit subtreeRoot
		outerVariableHolderOperators.add(subtreeRoot);
	}
}
