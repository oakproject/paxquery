package fr.inria.oak.paxquery.xparser.subquery;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public class LeftOuterNestedJoinInfo extends BaseOperatorInfo {
	LeftOuterNestedJoin lonjoinOperator;

	public LeftOuterNestedJoinInfo(LeftOuterNestedJoin lonjoinOperator, ArrayList<XMLScan> outerXMLScans, XMLScan innerXMLScan, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		super(outerXMLScans, innerXMLScan, outerVariable, returnedVariable, varMap);
		this.lonjoinOperator = lonjoinOperator;
	}

	@Override
	public void updateOperatorState() throws PAXQueryExecutionException {
		if(outerXMLScans != null && outerXMLScans.size() == 1 ) {
			//prepare lonjoinOperator.documentIdColumn
			//mark the XMLScan as attaching documentID
			outerXMLScans.get(0).setAttachDocumentID(true);
			//if IDTP doesnt exist then create it and add it to varmap
			NavigationTreePattern outerTP = outerXMLScans.get(0).getNavigationTreePattern();
			Variable var = varMap.getVariable("IDTP-"+outerTP.getName()); 
			if(var == null) {
				var = new Variable("IDTP-"+outerTP.getName(), Variable.VariableDataType.DocumentID);
				varMap.addNewVariable(var);
			}
			//set documentIdColumn = IDTP variable
			lonjoinOperator.setDocumentIDColumn(varMap.getTemporaryPositionByName(var.name));			

			//now, prepare lonjoinOperator.nodeIdColumns
			//on the same TP, markStoresIDWhereNeeded(), get the variables in ArrayList<Variable> and add them to varmap
			ArrayList<Variable> nodeIDVariables = outerTP.markAsStoresIDWhereNeeded();
			varMap.addNewVariables(nodeIDVariables);
			//set nodeIDColumns[] with the temp positions of those variables
			int[] nodeIDColumns = new int[nodeIDVariables.size()];
			for(int i = 0; i < nodeIDVariables.size(); i++)
				nodeIDColumns[i] = varMap.getTemporaryPositionByName(nodeIDVariables.get(i).name);
			lonjoinOperator.setNodeIDColumns(nodeIDColumns);
			
			//find the returned variable in the inner TP and set it as a nested variable in outerVariable
			ArrayList<Variable> innerVars = innerXMLScan.getNavigationTreePattern().getAllVariables();
			for(int i = 0; i < innerVars.size(); i++) {
				if(innerVars.get(i) == returnedVariable)
					outerVariable.addNestedVariable(returnedVariable, i);
			}
		}
		else
			throw new PAXQueryExecutionException("Currently only one outer tree pattern is supported in left nested outer joins.");
	}
	/*public void updateOperatorState() throws PAXQueryExecutionException {
		if(outerXMLScans != null && outerXMLScans.size() == 1 ) {
			//prepare lonjoinOperator.documentIdColumn
			//mark the XMLScan as attaching documentID
			outerXMLScans.get(0).setAttachDocumentID(true);
			//if IDTP doesnt exist then create it and add it to varmap
			NavigationTreePattern outerTP = outerXMLScans.get(0).getNavigationTreePattern();
			Variable var = varMap.getVariable("IDTP-"+outerTP.getName()); 
			if(var == null) {
				var = new Variable("IDTP-"+outerTP.getName(), Variable.VariableDataType.DocumentID);
				varMap.addNewVariable(var);
			}
			//set documentIdColumn = IDTP variable
			lonjoinOperator.setDocumentIDColumn(varMap.getTemporaryPositionByName(var.name));			

			//now, prepare lonjoinOperator.nodeIdColumns
			//on the same TP, markStoresIDWhereNeeded(), get the variables in ArrayList<Variable> and add them to varmap
			ArrayList<Variable> nodeIDVariables = outerTP.markAsStoresIDWhereNeeded();
			varMap.addNewVariables(nodeIDVariables);
			//set nodeIDColumns[] with the temp positions of all variables
			ArrayList<Variable> outerVars = outerTP.getAllVariables();
			//int[] nodeIDColumns = new int[nodeIDVariables.size()];
			int[] nodeIDColumns = new int[outerVars.size()];
			for(int i = 0; i < outerVars.size(); i++)
				nodeIDColumns[i] = varMap.getTemporaryPositionByName(outerVars.get(i).name);
			lonjoinOperator.setNodeIDColumns(nodeIDColumns);
		}
		else
			throw new PAXQueryExecutionException("Currently only one outer tree pattern is supported in left nested outer joins.");
	}*/
}
