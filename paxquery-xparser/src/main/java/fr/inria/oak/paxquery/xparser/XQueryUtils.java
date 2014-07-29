package fr.inria.oak.paxquery.xparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLConstruct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.xml.treepattern.core.PatternNode;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePattern;
import fr.inria.oak.paxquery.common.xml.treepattern.core.Variable;

public class XQueryUtils {
	
	private static int auxVarCounter = 0;
	
	/**
	 * Populates the HashMap<Integer, String> varspos with patternNodes that match variables and their position 
	 * within the tree. That is, containing pairs with the shape <patterNode_position_in_the_tree, var_name>
	 * @param treePattern The tree to traverse
	 * @param varspos the data structure to populate
	 * @param startingPosInTree an initial offset for the position of variables
	 */
	public static void buildVarsPos(HashMap<String, Variable> varspos, TreePattern treePattern, int startingPosInTree) {
		//HashMap<String, Variable> varspos = new HashMap<String, Variable>();
		traverseTreePatternDFS(treePattern.getRoot(), varspos, startingPosInTree);		
	}
	
	/**
	 * Traverses a TreePattern whose root is "root" and fills varspos with the following tuples:
	 * < var_name, patternNode_position_in_the_tree>
	 * The tree is traversed the Pre-order way. Recursive method.
	 * @param root the root of this subtree
	 * @param varspos stores the result of the traversal in the form of tuples <var_name, patternNode_position_in_the_tree>
	 * @param posInTree position of variables in the tree
	 * @return posInTree
	 */
	private static int traverseTreePatternDFS(PatternNode root, HashMap<String, Variable> varspos, int posInTree) {
		//visit this node
		posInTree = visitNodeInTreePattern(root, varspos, posInTree);
		ArrayList<PatternNode> children = root.getChildrenList();
		//recursive call for descendant sub-trees, recursivite ends when children.size()==0
		for(PatternNode node : children)
			posInTree = traverseTreePatternDFS(node, varspos, posInTree);	
		return posInTree;
	}

	/**
	 * Visits a node in the TreePattern in-order traversing algorithm.
	 * @param node the node to visit
	 * @param varspos stores the result of the traversal in the form of tuples <var_name, patternNode_position_in_the_tree>
	 * @param posInTree position of variables in the tree
	 * @return posInTree
	 */
	private static int visitNodeInTreePattern(PatternNode node, HashMap<String, Variable> varspos, int posInTree) {
		ArrayList<Variable> matchingVariables = node.getMatchingVariables();

		//save a position for the ID
		if(node.storesID())
			posInTree++;
		
		if(matchingVariables.size()!=0) {
			//count variables storing value first
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType == Variable.VariableDataType.Value) {
					matchingVariables.get(i).positionInForest = posInTree;
					varspos.put(matchingVariables.get(i).name,  matchingVariables.get(i));
					posInTree++;
				}
			}
			//then count variables storing content
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType == Variable.VariableDataType.Content) {
					matchingVariables.get(i).positionInForest = posInTree;
					varspos.put(matchingVariables.get(i).name, matchingVariables.get(i));
					posInTree++;
				}
			}
		
			//then the rest, if any
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType != Variable.VariableDataType.Value &&
						matchingVariables.get(i).dataType != Variable.VariableDataType.Content) {
					matchingVariables.get(i).positionInForest = posInTree;
					varspos.put(matchingVariables.get(i).name,  matchingVariables.get(i));
					posInTree++;
				}
			}
		}
		
		return posInTree;
	}
	
	/**
	 * Prints the HashMap<String, Variable> varsPos in a specific way for unit testing purposes
	 * @param varsPos the data structure to print
	 * @return the output string
	 */
	public static String varsPosToString(HashMap<String, Variable> varsPos) {
		StringBuilder sb = new StringBuilder();
		int keySetSize = varsPos.keySet().size();
		int counter = 0;
		sb.append('{');
		for(String varName : varsPos.keySet()) {
			Variable var = varsPos.get(varName);
			sb.append(var.name);
			if(var.dataType == Variable.VariableDataType.Content)
				sb.append("C");
			else if(var.dataType == Variable.VariableDataType.Value)
				sb.append("V");
			sb.append("=");
			sb.append(var.positionInForest);
			
			if(counter < keySetSize-1)
				sb.append(',');
			counter++;
		}
		sb.append('}');
		return sb.toString();
	}
	
	/**
	 * Goes through the tree of algebraic operators in pre-order and prints it out.
	 * Note that the root of such tree will always be an XMLConstruct object
	 * @param root the root of the tree, always an XMLConstruct operator
	 * @return
	 */
	public static String algebraicTreeToString(XMLConstruct root) {
		return traverseAlgebraicOperatorsTree(root);		
	}
	
	/**
	 * Traverses a tree of algebraic operators in pre-order and returns the string
	 * representation.
	 * @param root the root node of the subtree below
	 * @return the string representation of the tree
	 */
	private static String traverseAlgebraicOperatorsTree(BaseLogicalOperator root) {
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

	
	public static int findVarInPatternTree(ArrayList<XMLScan> scans, HashMap<String, PatternNode> patternNodeMap, String varName) {
		PatternNode node = patternNodeMap.get(varName);
		if(node == null)
			return -1;
		
		TreePattern tp = node.getTreePattern();
		if(tp == null)
			return -1;
		
		for(int i = 0; i < scans.size(); i++) {
			if(tp == scans.get(i).getNavigationTreePattern())
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns a name for an auxiliar variable wich will point to a node within an XPath predicate
	 * E.g:
	 * let $mainVarName := whatever/lastNodeOutsideXPathPredicateName[lastNodeInsideXPathPredicateName = 'whatever']
	 * Consecuently, the auxiliar variable with the returned name will point to the "lastNodeInsideXPathPredicateName" node.
	 */
	public static String buildAuxVariableName(String mainVarName, String lastNodeOutsideXPathPredicateName, String lastNodeInsideXPathPredicateName) {
		return mainVarName + ":" + lastNodeOutsideXPathPredicateName + ":" + lastNodeInsideXPathPredicateName;
	}
	
	/**
	 * Returns a name for an auxiliar variable. The shape of the name is "auxVar"+auxVarCounter.
	 * auxVarCounter is autoincremented after each call.
	 */
	public static String getNextAuxVariableName() {
		return "auxVar"+auxVarCounter++;
	}

	/**
	 * Removes first and last character, assuming they're simple (') or double quotes (")
	 * @param inputString
	 * @return
	 */
	public static String sanitizeStringLiteral(String inputString) {
		if(inputString != null && inputString != "") {
			if(inputString.charAt(0) == '"' && inputString.charAt(inputString.length()-1) == '"' 
					|| inputString.charAt(0) == '\'' && inputString.charAt(inputString.length()-1) == '\'') {
				if(inputString.length() > 2)
					inputString = inputString.substring(1, inputString.length()-1);
				else
					inputString = "";
			}
		}
		return inputString;
	}
}
