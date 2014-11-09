package fr.inria.oak.paxquery.common.xml.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Variable implements Serializable {
	public enum VariableDataType { None, Value, Content, NodeID, DocumentID, Subquery }

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
	
	public ArrayList<Variable> nestedVariables;
	
	public ArrayList<Integer> nestedPaths;

	/**
	 * Pre-order overall position within all trees
	 */	
	public int positionInForest;	
	
	public Variable(String name, VariableDataType dataType) {
		this.name = name;
		this.dataType = dataType;
		this.node = null;
		this.nestedVariables = new ArrayList<Variable>();
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.nestedVariables = new ArrayList<Variable>();
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node, int positionInForest) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.nestedVariables = new ArrayList<Variable>();
		this.nestedPaths = new ArrayList<Integer>();
		this.positionInForest = positionInForest;
	}

	/**
	 * Adds the variables indicated as input to nestedVariables and their paths (the columns inside the outer variable)
	 * @param variables the new variables to be added to the existing ones
	 */
	public void addNestedVariables(List<Variable> variables, List<Integer> paths) {
		for(Variable var : variables)
			nestedVariables.add(var);
		for(Integer path : paths)
			nestedPaths.add(path);
	}
	
	public void addNestedVariable(Variable var, int path) {
		nestedVariables.add(var);
		nestedPaths.add(path);
	}

	
	/**
	 * Replaces the previous content of nestedVariables by the new variables indicated as input and their paths (the columns inside the outer variable)
	 * One should only add here the variables (and their paths) that are returned in the return statement of a subquery
	 * @param variables the new variables to replace the existing ones
	 */
	public void setNestedVariables(List<Variable> variables, List<Integer> paths) {
		nestedVariables.clear();
		nestedPaths.clear();
		addNestedVariables(variables, paths);
	}
	
	/**
	 * Tells whether this variable contains any nested variables.
	 * @return true is nestedVariables contains any variable, false if nestedVariables is empty
	 */
	public boolean hasNestedVariables() {
		return nestedVariables.size() != 0;
	}
	
	public boolean hasNestedPaths() {
		return nestedPaths.size() != 0;
	}
	
	public void addNestedPath(int path) {
		nestedPaths.add(path);
	}
		
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
