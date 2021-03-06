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
package fr.inria.oak.paxquery.common.xml.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePattern;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;



public class Variable implements Serializable {
	public enum VariableDataType { None, Value, Content, NodeID, DocumentID, Subquery, Aggregation }

	/**
	 * Name of the variable
	 */
	public String name;		

	/**
	 * The kind of data this variable stores, namely value or content
	 */
	public VariableDataType dataType;	

	/**
	 * The node this variable is assigned to
	 */
	public NavigationTreePatternNode node;	
	
	//public ArrayList<Variable> nestedVariables;
	public ConstructionTreePatternNode nestedCTPRoot;
	
	public ArrayList<Integer> nestedPaths;

	/**
	 * Pre-order overall position within all trees
	 */	
	public int positionInForest;	
	
	public Variable(String name, VariableDataType dataType) {
		this.name = name;
		this.dataType = dataType;
		this.node = null;
		//this.nestedVariables = new ArrayList<Variable>();
		this.nestedCTPRoot = null;
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		//this.nestedVariables = new ArrayList<Variable>();
		this.nestedCTPRoot = null;
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node, int positionInForest) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		//this.nestedVariables = new ArrayList<Variable>();
		this.nestedCTPRoot = null;
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = positionInForest;
	}

	/**
	 * Adds the variables indicated as input to nestedVariables and their paths (the columns inside the outer variable)
	 * @param variables the new variables to be added to the existing ones
	 */
	/*public void addNestedVariables(List<Variable> variables, List<Integer> paths) {
		for(Variable var : variables)
			nestedVariables.add(var);
		for(Integer path : paths)
			nestedPaths.add(path);
	}
	
	public void addNestedVariable(Variable var, int path) {
		nestedVariables.add(var);
		nestedPaths.add(path);
	}*/
	public void setNestedCTP(ConstructionTreePatternNode ctpRoot) {
		this.nestedCTPRoot = ctpRoot;
	}
	
	public void updateNestedCTPWithVarPosition(int position) {
		updateNestedCTPWithVarPositionRecursive(position, this.nestedCTPRoot);
	}
	
	public void updateNestedCTPWithVarPositionRecursive(int position, ConstructionTreePatternNode node) {
		if(node.getContentType() == ContentType.VARIABLE_PATH) {
			List<Integer> new_varpath = node.getVarPath();
			new_varpath.add(0, position);
			node.setVarPath(new_varpath);
		}
				
		for(ConstructionTreePatternNode child : node.getChildren()) {
			updateNestedCTPWithVarPositionRecursive(position, child);
		}
	}
	
	/**
	 * Replaces the previous content of nestedVariables by the new variables indicated as input and their paths (the columns inside the outer variable)
	 * One should only add here the variables (and their paths) that are returned in the return statement of a subquery
	 * @param variables the new variables to replace the existing ones
	 */
	/*public void setNestedVariables(List<Variable> variables, List<Integer> paths) {
		nestedVariables.clear();
		nestedPaths.clear();
		addNestedVariables(variables, paths);
	}*/
	
	/**
	 * Tells whether this variable contains any nested variables.
	 * @return true is nestedVariables contains any variable, false if nestedVariables is empty
	 */
	/*public boolean hasNestedVariables() {
		return nestedVariables.size() != 0;
	}
	
	public boolean hasNestedPaths() {
		return nestedPaths.size() != 0;
	}
	
	public void addNestedPath(int path) {
		nestedPaths.add(path);
	}*/
		
	/**
	 * Returns the TreePattern (if any) this variable's node is assigned to.
	 * @return the TreePattern this variable's node is assigned to, null if no tree is assigned to the node, or if no node is assigned to this variable.
	 */
	public NavigationTreePattern getTreePattern() {
		if(node != null)
			return node.getTreePattern();
		return null;
	}
}
