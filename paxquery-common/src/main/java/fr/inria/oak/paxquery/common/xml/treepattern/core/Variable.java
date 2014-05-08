package fr.inria.oak.paxquery.common.xml.treepattern.core;



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
	public PatternNode node;	

	/**
	 * Pre-order overall position within all trees
	 */	
	public int positionInForest;	
	
	public Variable(String name, VariableDataType dataType) {
		this.name = name;
		this.dataType = dataType;
		this.node = null;
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, PatternNode node) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.positionInForest = -1;
	}
	
	public Variable(String name, VariableDataType dataType, PatternNode node, int positionInForest) {
		this.name = name;
		this.dataType = dataType;
		this.node = node;
		this.positionInForest = positionInForest;
	}

	/**
	 * Returns the TreePattern (if any) this variable's node is assigned to.
	 * @return the TreePattern this variable's node is assigned to, null if no tree is assigned to the node, or if no node is assigned to this variable.
	 */
	public TreePattern getTreePattern() {
		if(node != null)
			return node.getTreePattern();
		return null;
	}
}
