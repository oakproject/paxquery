package fr.inria.oak.paxquery.xparser.subquery;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public class GroupByInfo extends BaseOperatorInfo {
	public GroupBy groupByOperator;
	
	public GroupByInfo(GroupBy groupByOperator, ArrayList<XMLScan> outerXMLScans, XMLScan innerXMLScan, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		super(outerXMLScans, innerXMLScan, outerVariable, returnedVariable, varMap);
		this.groupByOperator = groupByOperator;
	}
	
	public void updateOperatorState() {
		//prepare reduceByColumns for the GroupBy
		ArrayList<Variable> idVars = new ArrayList<Variable>();
		for(XMLScan scan : outerXMLScans) {
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
		int[] reduceByColumns = new int[idVars.size()];
		for(int i = 0; i < idVars.size(); i++)
			reduceByColumns[i] = varMap.getTemporaryPositionByName(idVars.get(i).name);
		
		//prepare groupByColumns for the GroupBy
		ArrayList<Variable> outerVars = new ArrayList<Variable>();
		for(XMLScan scan : outerXMLScans) {
			if(scan.isAttachDocumentID() == true)
				outerVars.add(varMap.getVariable("IDTP-"+scan.getNavigationTreePattern().getName()));
			outerVars.addAll(scan.getNavigationTreePattern().getAllVariables());
		}
		int[] groupByColumns = new int[outerVars.size()];
		for(int i = 0; i < outerVars.size(); i++)
			groupByColumns[i] = varMap.getTemporaryPositionByName(outerVars.get(i).name);

		//prepare nestColumns for the GroupBy
		ArrayList<Variable> innerVars = innerXMLScan.getNavigationTreePattern().getAllVariables();
		int[] nestColumns = new int[innerVars.size()];
		for(int i = 0; i < innerVars.size(); i++) {
			nestColumns[i] = varMap.getTemporaryPositionByName(innerVars.get(i).name);
			if(innerVars.get(i).name.compareTo(returnedVariable.name) == 0)	//when we find the variable returned in this subquery we add it as a nested variable in the outer variable
				outerVariable.addNestedVariable(returnedVariable, i);
		}
		//update the operator
		groupByOperator.setGroupByColumns(groupByColumns);
		groupByOperator.setReduceByColumns(reduceByColumns);
		groupByOperator.setNestColumns(nestColumns);
	}
}
