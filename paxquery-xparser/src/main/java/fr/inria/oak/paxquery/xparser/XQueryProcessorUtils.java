package fr.inria.oak.paxquery.xparser;

import java.util.ArrayList;
import java.util.HashMap;

import fr.inria.oak.paxquery.common.xml.treepattern.core.PatternNode;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePattern;
import fr.inria.oak.paxquery.common.xml.treepattern.core.Variable;

public class XQueryProcessorUtils {
	
	/**
	 * Builds a HashMap<Integer, String> populated by patternNodes that match variables and their position 
	 * within the tree. That is, containing pairs with the shape <patterNode_position_in_the_tree, var_name>
	 * @param treePattern The tree to traverse
	 * @return a HashMap<Integer, String> populated by <var_name, patterNode_position_in_the_tree>
	 */
	public static HashMap<String, Variable> buildVarsPos(TreePattern treePattern) {
		HashMap<String, Variable> varspos = new HashMap<String, Variable>();
		traverseTreePatternDFS(treePattern.getRoot(), varspos, 0);
		
		return varspos;
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
		System.out.println("Visiting "+node.getTag());
		ArrayList<Variable> matchingVariables = node.getMatchingVariables();

		//save a position for the ID
		if(node.storesID())
			posInTree++;
		
		if(matchingVariables.size()!=0) {
			//count variables storing value first
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType == Variable.VariableDataType.Value) {
					matchingVariables.get(i).positionInTree = posInTree;
					varspos.put(matchingVariables.get(i).name,  matchingVariables.get(i));
					posInTree++;
				}
			}
			//then count variables storing content
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType == Variable.VariableDataType.Content) {
					matchingVariables.get(i).positionInTree = posInTree;
					varspos.put(matchingVariables.get(i).name, matchingVariables.get(i));
					posInTree++;
				}
			}
		
			//then the rest, if any
			for(int i = 0; i < matchingVariables.size(); i++) {
				if(matchingVariables.get(i).dataType != Variable.VariableDataType.Value &&
						matchingVariables.get(i).dataType != Variable.VariableDataType.Content) {
					matchingVariables.get(i).positionInTree = posInTree;
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
			sb.append(var.positionInTree);
			if(counter < keySetSize-1)
				sb.append(',');
			counter++;
		}
		sb.append('}');
		return sb.toString();
	}
}
