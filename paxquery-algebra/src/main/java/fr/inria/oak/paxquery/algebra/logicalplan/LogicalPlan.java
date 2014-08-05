package fr.inria.oak.paxquery.algebra.logicalplan;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.border.*;

/**
 * Describes a logical plan composed of algebraic operators.
 * A logical plan is a tree of BaseLogicalOperator derived objects where the root is a XMLConstruct object and leaves are XMLScan objects. 
 * @author jalvaro
 */
public class LogicalPlan {
	
	private XMLConstruct root;
	private ArrayList<XMLScan> leaves;

	public LogicalPlan() {
		root = null;
		leaves = new ArrayList<XMLScan>();
	}
	
	public XMLConstruct getRoot() {
		return root;
	}
	
	public void setRoot(XMLConstruct root) {
		this.root = root;
	}
	
	public ArrayList<XMLScan> getLeaves() {
		return leaves;
	}
	
	public void setLeaves(ArrayList<XMLScan> leaves) {
		this.leaves = leaves;
	}
	
	public XMLScan getLeaf(int index) {
		return leaves.get(index);
	}
	
	public void addLeaf(XMLScan scan) {
		leaves.add(scan);
	}
}
