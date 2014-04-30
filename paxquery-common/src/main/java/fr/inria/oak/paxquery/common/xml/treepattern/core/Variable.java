package fr.inria.oak.paxquery.common.xml.treepattern.core;



public class Variable {
	public enum VariableDataType { None, Value, Content }

	public String name;
	public VariableDataType dataType;
	public int positionInTree;
	
	public Variable(String name, VariableDataType dataType) {
		this.name = name;
		this.dataType = dataType;
		this.positionInTree = -1;
	}
	
	public Variable(String name, VariableDataType dataType, int positionInTree) {
		this.name = name;
		this.dataType = dataType;
		this.positionInTree = positionInTree;
	}
}
