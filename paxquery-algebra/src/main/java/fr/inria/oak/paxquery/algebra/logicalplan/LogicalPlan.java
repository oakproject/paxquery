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
package fr.inria.oak.paxquery.algebra.logicalplan;

import java.util.ArrayList;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;

/**
 * Describes a logical plan composed of algebraic operators.
 * A logical plan is a tree of BaseLogicalOperator derived objects where the root is a XMLConstruct object and leaves are XMLScan objects. 
 * @author jalvaro
 */
public class LogicalPlan {
	
	//private XMLTreeConstruct root;
	private BaseLogicalOperator root;
	private List<XMLScan> leaves;
	
	private boolean parentsAdjusted = false;
	private boolean navigationTreePatternsAdjusted = false;

	public LogicalPlan() {
		root = null;
		leaves = new ArrayList<XMLScan>();
	}
	
	public BaseLogicalOperator getRoot() {
		return root;
	}
	
	public void setRoot(BaseLogicalOperator root) {
		this.root = root;
	}
	
	public List<XMLScan> getLeaves() {
		return leaves;
	}
	
	public void setLeaves(ArrayList<XMLScan> leaves) {
		this.leaves = leaves;
	}
	
	public XMLScan getLeaf(int index) {
		return leaves.get(index);
	}
	
	/**
	 * Returns the XMLScan leaf associated to the tree pattern "tp", or null if "tp" is not associated to any leaf.
	 * @param tp
	 * @return
	 */
	public XMLScan getLeaf(NavigationTreePattern tp) {
		for(XMLScan scan : leaves) {
			if(scan.getNavigationTreePattern() == tp)
				return scan;
		}
		return null;
	}
	
	public void addLeaf(XMLScan scan) {
		leaves.add(scan);
	}
	
	/**
	 * Climbs from the XMLScan leaf to the highest placed ancestor (this does not need to be the root of the tree, if this leaf is temporary not connected to the root by some reason)
	 * @return
	 */
	public BaseLogicalOperator getTopFromLeaf(XMLScan leaf) {
		BaseLogicalOperator currentTop = leaf;
		
		while(currentTop.getParent() != null)
			currentTop = currentTop.getParent();
		
		return currentTop;
	}

	
	/**
	 * Climbs from the XMLScan leaf associated to the navigation tree pattern "tp" to the highest placed ancestor (this does not need to be the root of the tree, if this leaf is temporary not connected to the root by some reason)
	 * @param tp
	 * @return
	 */
	public BaseLogicalOperator getTopFromLeaf(NavigationTreePattern tp) {		
		return getTopFromLeaf(getLeaf(tp));
	}

	
	/**
	 * Inserts newOperator as direct ancestor of referenceOperator. If referenceOperator had a different direct ancestor
	 * then the latter is set as direct ancestor of newOperator.
	 * @param referenceOperator the operator that will become the descendant
	 * @param newOperator the operator that will become the direct ancestor
	 */
	public static void insertOnTop(BaseLogicalOperator referenceOperator, BaseUnaryOperator newOperator) {
		//create link from referenceOperator's ancestor to newOperator
		if(referenceOperator != null)
			replaceChild(referenceOperator.getParent(), referenceOperator, newOperator);
		//create link from newOperator to referenceOperator
		if(newOperator != null)
			newOperator.setChild(referenceOperator);			
	}
	
	/**
	 * Inserts newOperator as direct ancestor of referenceOperator on newOperator's left link.
	 * If referenceOperator had a different direct ancestor then the latter is set as a direct ancestor of newOperator.
	 * @param referenceOperator the operator that will become the descendant
	 * @param newOperator the operator that will become the direct ancestor
	 */
	public static void insertOnTopLeft(BaseLogicalOperator referenceOperator, BaseBinaryOperator newOperator) {
		//create link from referenceOperator's ancestor to newOperator
		if(referenceOperator != null)
			replaceChild(referenceOperator.getParent(), referenceOperator, newOperator);
		//create link from newOperator to referenceOperator, on newOperator's left link
		if(newOperator != null)
			newOperator.setLeft(referenceOperator);
	}
	
	/**
	 * Inserts newOperator as direct ancestor of referenceOperator on newOperator's right link.
	 * If referenceOperator had a different direct ancestor then the latter is set as a direct ancestor of newOperator.
	 * @param referenceOperator the operator that will become the descendant
	 * @param newOperator the operator that will become the direct ancestor
	 */
	public static void insertOnTopRight(BaseLogicalOperator referenceOperator, BaseBinaryOperator newOperator) {
		//create link from referenceOperator's ancestor to newOperator
		if(referenceOperator != null)
			replaceChild(referenceOperator.getParent(), referenceOperator, newOperator);
		//create link from newOperator to referenceOperator, on newOperator's left link
		if(newOperator != null)
			newOperator.setRight(referenceOperator);
	}	
	
	/**
	 * Replaces old operator's child, i.e. oldChild, for newChild. If oldChild was not a child of operator then nothing is done.
	 * @param operator the operator with the child
	 * @param oldChild the old of operator to be replaced
	 * @param newChild the new child to be put in the place of oldChild
	 */
	private static void replaceChild(BaseLogicalOperator operator, BaseLogicalOperator oldChild, BaseLogicalOperator newChild) {
		if(operator != null) {
			if(operator instanceof BaseUnaryOperator && ((BaseUnaryOperator) operator).getChild() == oldChild)
				((BaseUnaryOperator) operator).setChild(newChild);
			else if(operator instanceof BaseBinaryOperator)
				((BaseBinaryOperator) operator).replaceChild(oldChild,  newChild);
		}
	}

	public static void connect(BaseLogicalOperator parent, BaseLogicalOperator child) {
    parent.getChildren().clear();
    parent.getChildren().add(child);
    child.setParent(parent);
	}

	public static void connect(BaseLogicalOperator parent, BaseLogicalOperator leftChild,
	        BaseLogicalOperator rightChild) {
	  parent.getChildren().clear();
	  parent.getChildren().add(leftChild);
    leftChild.setParent(parent);
    parent.getChildren().add(rightChild);
    rightChild.setParent(parent);
	}

	/**
	 * Goes through the tree of algebraic operators in pre-order and prints it out.
	 * @param root the root of the tree, always an XMLConstruct operator
	 * @return
	 */
	public String toString() {
		return traverseAlgebraicOperatorsTree(root);
	}
	
	/**
	 * Makes the "parent" variable of each node point the node's parent 
	 */
	public void adjustParents() {
		adjustParent(root);
		parentsAdjusted = true;
	}
	
	private void adjustParent(BaseLogicalOperator parent) {
		ArrayList<BaseLogicalOperator> children = parent.getChildren();
		if(children != null) {
			for(BaseLogicalOperator child : children) {
				child.setParent(parent);
				adjustParent(child);
			}
		}
	}
	
	public void adjustNavigationTreePatterns() {
		if(parentsAdjusted == false)
			adjustParents();
		adjustNavigationTreePatterns(root);
		navigationTreePatternsAdjusted = true;
	}
	
	private void adjustNavigationTreePatterns(BaseLogicalOperator parent) {
		ArrayList<BaseLogicalOperator> children = parent.getChildren();
		if(children == null)
			return;
		if(parent instanceof BaseUnaryOperator) {
			BaseLogicalOperator child = ((BaseUnaryOperator) parent).getChild();
			adjustNavigationTreePatterns(child);
			((BaseUnaryOperator) parent).setNavigationTreePatterns(child.getNavigationTreePatterns());	
		} else if(parent instanceof BaseBinaryOperator) {
			BaseLogicalOperator leftChild = ((BaseBinaryOperator) parent).getLeft();
			adjustNavigationTreePatterns(leftChild);
			((BaseBinaryOperator) parent).setNavigationTreePatternsLeft(leftChild.getNavigationTreePatterns());
			BaseLogicalOperator rightChild = ((BaseBinaryOperator) parent).getRight();
			adjustNavigationTreePatterns(rightChild);
			((BaseBinaryOperator) parent).setNavigationTreePatternsRight(rightChild.getNavigationTreePatterns());
		}			
	}
	
	/**
	 * Traverses a tree of algebraic operators in pre-order and returns the string
	 * representation.
	 * @param root the root node of the subtree below
	 * @return the string representation of the tree
	 */
	private String traverseAlgebraicOperatorsTree(BaseLogicalOperator root) {
		StringBuilder sb = new StringBuilder();
		//visit this node
		sb.append(root.getName());
		//go to children; the base case is when there are no children (the node is a leaf)
		if(root.getChildren() != null) {
			sb.append(" -> ");
			for(BaseLogicalOperator child : root.getChildren())
				sb.append(traverseAlgebraicOperatorsTree(child));
		}
		return sb.toString();
	}	
	
	public void draw(String folderName, String givenFileName) {
		root.draw(folderName, givenFileName);
	}
}
