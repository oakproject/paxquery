package fr.inria.oak.paxquery.common.xml.navigation;

import java.util.ArrayList;



public class Variable {
	public enum VariableDataType { None, Value, Content }

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

	/**
	 * Pre-order overall position within all trees
	 */	
	public int positionInForest;	
	
	public Variable(String name, VariableDataType dataType) {
		this.name = name;
		this.dataType = dataType;
		this.node = null;
		this.nestedVariables = new ArrayList<Variable>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.nestedVariables = new ArrayList<Variable>();
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, NavigationTreePatternNode node, int positionInForest) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.nestedVariables = new ArrayList<Variable>();
		this.positionInForest = positionInForest;
	}

	/**
	 * Adds the variables indicated as input to nestedVariables.
	 * @param variables the new variables to be added to the existing ones
	 */
	public void addNestedVariables(Variable... variables) {
		for(Variable var : variables)
			nestedVariables.add(var);
	}
	
	/**
	 * Replaces the previous content of nestedVariables by the new variables indicated as input.
	 * @param variables the new variables to replace the existing ones
	 */
	public void setNestedVariables(Variable... variables) {
		nestedVariables.clear();
		addNestedVariables(variables);
	}
	
	/**
	 * Tells whether this variable contains any nested variables.
	 * @return true is nestedVariables contains any variable, false if nestedVariables is empty
	 */
	public boolean hasNestedVariables() {
		return nestedVariables.size() != 0;
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
