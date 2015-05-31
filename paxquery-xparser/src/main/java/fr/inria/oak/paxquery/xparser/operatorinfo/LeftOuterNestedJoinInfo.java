/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fr.inria.oak.paxquery.xparser.operatorinfo;

import java.util.ArrayList;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public class LeftOuterNestedJoinInfo extends BaseNestingOperatorInfo {
	LeftOuterNestedJoin lonjoinOperator;

	public LeftOuterNestedJoinInfo(LeftOuterNestedJoin lonjoinOperator, ArrayList<BaseLogicalOperator> outerXMLScans, BaseLogicalOperator innerXMLScan, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		super(outerXMLScans, innerXMLScan, outerVariable, returnedVariable, varMap);
		this.lonjoinOperator = lonjoinOperator;
	}
	
	public LeftOuterNestedJoinInfo(LeftOuterNestedJoin lonjoinOperator, ArrayList<BaseLogicalOperator> outerXMLScans, BaseLogicalOperator innerXMLScan, Variable outerVariable, VarMap varMap) {
		super(outerXMLScans, innerXMLScan, outerVariable, varMap);
		this.lonjoinOperator = lonjoinOperator;
	}

	@Override
	public void updateOperatorState() throws PAXQueryExecutionException {
		//if(outerVariableHolderOperators != null && outerVariableHolderOperators.size() == 1 ) {
		if(outerVariableHolderOperators != null && outerVariableHolderOperators.size() > 0 ) {
			//prepare lonjoinOperator.documentIdColumn
			//mark the XMLScan as attaching documentID
			ArrayList<Variable> nodeIDVariables = new ArrayList<Variable>();
			for(BaseLogicalOperator operator : outerVariableHolderOperators) {
				if(operator instanceof XMLScan) {
					//((XMLScan)outerVariableHolderOperators.get(0)).setAttachDocumentID(true);
					XMLScan scan = (XMLScan) operator;
					//if IDTP doesnt exist then create it and add it to varmap
					((XMLScan) operator).setAttachDocumentID(true);
					//NavigationTreePattern outerTP = ((XMLScan)outerVariableHolderOperators.get(0)).getNavigationTreePattern();
					NavigationTreePattern outerTP = scan.getNavigationTreePattern();
					Variable var = varMap.getVariable("IDTP-"+outerTP.getName()); 
					if(var == null) {
						var = new Variable("IDTP-"+outerTP.getName(), Variable.VariableDataType.DocumentID);
						varMap.addNewVariable(var);
					}
					//set documentIdColumn = IDTP variable
					lonjoinOperator.setDocumentIDColumn(varMap.getTemporaryPositionByName(var.name));			
		
					//now, prepare lonjoinOperator.nodeIdColumns
					//on the same TP, markStoresIDWhereNeeded(), get the variables in ArrayList<Variable> and add them to varmap
					//ArrayList<Variable> nodeIDVariables = outerTP.markAsStoresIDWhereNeeded();
					nodeIDVariables.addAll(outerTP.markAsStoresIDWhereNeeded());
					varMap.addNewVariables(nodeIDVariables);
				}
			}
			//set nodeIDColumns[] with the temp positions of those variables
			int[] nodeIDColumns = new int[nodeIDVariables.size()];
			for(int i = 0; i < nodeIDVariables.size(); i++)
				nodeIDColumns[i] = varMap.getTemporaryPositionByName(nodeIDVariables.get(i).name);
			lonjoinOperator.setNodeIDColumns(nodeIDColumns);
			
			updateInnerCTPsVarPos();
			
			//find the returned variable in the inner TP and set it as a nested variable in outerVariable
			//ArrayList<Variable> innerVars = getInnerVariables();
			//for(int i = 0; i < innerVars.size(); i++) {
			//	if(innerVars.get(i) == returnedVariable)
			//		outerVariable.addNestedVariable(returnedVariable, i);
			//}
		}
		else
			throw new PAXQueryExecutionException("Currently only one outer tree pattern is supported in left nested outer joins.");
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
	
	private int countXMLScans(ArrayList<BaseLogicalOperator> operatorsList) {
		int occurrences = 0;
		for(BaseLogicalOperator operator : operatorsList) {
			if(operator instanceof XMLScan)
				occurrences++;
		}
		return occurrences;
	}
}
