package fr.inria.oak.paxquery.xparser.mapping;

import java.util.ArrayList;
import java.util.HashMap;

import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;
//import fr.inria.oak.paxquery.common.xml.treepattern.core.PatternNode;
//import fr.inria.oak.paxquery.common.xml.treepattern.core.Variable;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;

/**
 * Maps variable names used in the parse process to their corresponding Variable objects. Also provides (1) access to the node each variable is assigned to and (2) information about the ordering of those variables in the tree pattern forest.
 * There exist two kinds of ordering according to time:
 * -Temporary order: used while the query is being parsed. Variable positions according to this order are input to algebraic operators during the construction of the algebraic tree.
 * -Final order: calculated after the whole query is parsed. Variable positions according to this order should be used as final input for the algebraic operator.
 * @author jalvaro
 *
 */
public class VarMap {
	/**
	 * Binds a variable name with its corresponding variable object
	 */
	public HashMap<String, Variable> variables;

	/**
	 * The temporary position in the tree pattern forest that a new variable will occupy
	 */
	private int nextTemporaryPosition;		

	/**
	 * Binds variable names and the temporal order they occupy in the tree pattern forest, accessible by variable name
	 */
	public HashMap<String, Integer> temporaryPositionsByName;

	/**
	 * Binds variable names and the temporal order they occupy in the tree pattern forest, accessible by temporal position
	 */
	public HashMap<Integer, String> namesByTemporaryPosition; 	

	/**
	 * Binds variable names and the final order they occupy in the tree pattern forest, accessible by variable name
	 */
	public HashMap<String, Integer> finalPositionsByName;		
	
	/**
	 * True if finalOrderByName has been updated with the final positions of variables in the tree pattern forest 
	 */
	private boolean arePositionsFinal;
	
	public VarMap() {
		variables = new HashMap<String, Variable>();
		nextTemporaryPosition = 0;
		temporaryPositionsByName = new HashMap<String, Integer>();
		namesByTemporaryPosition = new HashMap<Integer, String>();
		finalPositionsByName = null;
		arePositionsFinal = false;
	}
	
	/**
	 * Returns the number of vars stored in the varmap. Note this number is different from the next position for a new variable.
	 * @return the number of vars stored in the varmap
	 */
	public int getNumberOfVars() {
		return variables.size();
	}
	
	/**
	 * Returns the position that would be assigned to a new variable.
	 * @return the position that would be assigned to a new variable
	 */
	public int getNextTemporaryPosition() {
		return nextTemporaryPosition;
	}
	
	public Variable getVariable(String varName) {
		if(varName == null)
			return null;
		return variables.get(varName);
	}
	
	/**
	 * Adds a new variable to the varmap and assigns it a temporary position in the pattern tree forest.
	 * @param var the new variable
	 * @return the temporary position
	 */
	public int addNewVariable(Variable var) {
		if(var == null || var.name == null)
			return -1;
		
		variables.put(var.name, var);
		temporaryPositionsByName.put(var.name, nextTemporaryPosition);
		namesByTemporaryPosition.put(nextTemporaryPosition, var.name);
		
		nextTemporaryPosition++;
		return nextTemporaryPosition-1;
	}
	
	/**
	 * Removes the variable given by varName from all inner data structures
	 * @param varName the name of the variable to remove
	 */
	public void removeVariable(String varName) {
		if(arePositionsFinal && finalPositionsByName != null)
			finalPositionsByName.remove(varName);
		int temporaryPosition = getTemporaryPositionByName(varName);
		if(temporaryPosition != -1)
			namesByTemporaryPosition.remove(temporaryPosition);
		temporaryPositionsByName.remove(varName);
		variables.remove(varName);		
	}
	
	/**
	 * Returns the temporary position of the variable whose name matches varName, or -1 if no variable was found with such name
	 * @param varName the name of the variable to search for
	 * @return the temporary position of the variable whose name matches varName, or -1 if no variable was found with such name
	 */
	public int getTemporaryPositionByName(String varName) {
		Integer position = temporaryPositionsByName.get(varName);
		if(position != null)
			return position;
		return -1;
	}
	
	/**
	 * Returns the name of the variable that matches the given temporary position, or null if no variable was found with such temporary position
	 * @param temporaryPosition the temporary position of the variable to search for
	 * @return the name of the variable that matches the given temporary position, or null if no variable was found with such temporary position
	 */
	public String getNameByTemporaryPosition(int temporaryPosition) {
		return namesByTemporaryPosition.get(temporaryPosition);
	}
	
	/**
	 * Returns the final position of the variable whose name matches varName. If not found the returned value is -1. If calculateFinalPositions() was not called earlier the returned value is -1.
	 * @param varName the name of the variable to search for
	 * @return the final position of the variable whose name matches varName, or -1 if not found, or -1 if calculateFinalPositions() was not called earlier
	 */
	public int getFinalPositionByName(String varName) {
		if(arePositionsFinal == false || finalPositionsByName == null)
			return -1;
		
		Integer position = finalPositionsByName.get(varName);
		if(position == null)
			return -1;

		return position;
	}
	
	/**
	 * Returns the name of the variable in the given final position. If not found the returned value is -1. If calculateFinalPositions() was not called earlier the returned value is "".
	 * @param finalPosition the final position of the variable to search for
	 * @return the name of the variable in the given final position, or null if not found, or null if calculateFinalPositions() was not called earlier
	 */
	public String getNameByFinalPosition(int finalPosition) {
		for(String name : finalPositionsByName.keySet()) {
			if(finalPositionsByName.get(name) == finalPosition)
				return name;
		}
		return null;		
	}
	
	/**
	 * Calculates the final positions of variables in the tree pattern forest. 
	 * @return the number of variables in the tree pattern forest.
	 */
	public int calculateFinalPositions(ArrayList<XMLScan> scanOperators) {
		finalPositionsByName = new HashMap<String, Integer>();
		int positionOffset = 0;
		
		//TODO: calculate in a similar way as XQueryUtils.buildVarsPos()
		for(XMLScan scan : scanOperators) {
			positionOffset = traverseTreePatternDFS(scan.getNavigationTreePattern().getRoot(), positionOffset);
		}
		
		arePositionsFinal = true;
		return variables.size();
	}
	
	/**
	 * Returns the final position of a variable with the given temporary position. If the specified temporary position is not found, then the returend value is -1. If calculateFinalPositions() was not called earlier the returned value is -1. 
	 * @param temporaryPosition the temporary position of a variable
	 * @return the final position of a variable with the given temporary position, or -1 if such temporary position is not found, or -1 if calculateFinalPositions() was not called earlier
	 */
	public int translateTemporaryPositionToFinalPosition(int temporaryPosition) {
		String name = getNameByTemporaryPosition(temporaryPosition);
		if(name != null)
			return getFinalPositionByName(name);
		return -1;
	}
	
	/**
	 * Invokes translateTemporaryPositionToFinalPosition(int) for each temporary position in the temporaryPositions array 
	 * @param temporaryPositions an array with temporary positions of one or many variables
	 * @return an array holding the final positions of the variables with the given temporary positions in the input array. The final position for a given variable may be -1 if its temporary position is not found, or if calculateFinalPositions() was not called earlier
	 */
	public int[] translateTemporaryPositionToFinalPosition(int[] temporaryPositions) {
		if(temporaryPositions == null)
			return null;
		
		int[] finalPositions = new int[temporaryPositions.length];
		for(int i = 0; i < temporaryPositions.length; i++)
			finalPositions[i] = translateTemporaryPositionToFinalPosition(temporaryPositions[i]);
		return finalPositions;		
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
	private int traverseTreePatternDFS(NavigationTreePatternNode root, int posInTree) {
		//visit this node
		posInTree = visitNodeInTreePattern(root, posInTree);
		ArrayList<NavigationTreePatternNode> children = root.getChildrenList();
		//recursive call for descendant sub-trees, recursion ends when children.size()==0
		for(NavigationTreePatternNode node : children)
			posInTree = traverseTreePatternDFS(node, posInTree);	
		return posInTree;
	}

	/**
	 * Visits a node in the TreePattern in-order traversing algorithm. Additionally, if different variables of the same storing type (value or content)
	 * point to the same node, it redirects all to the same position. That is: assume we have $aV = 3, $bV = 4, $cC = 5. Then the result is $aV = 3, $bV = 3, $cC = 4
	 * @param node the node to visit
	 * @param varspos stores the result of the traversal in the form of tuples <var_name, patternNode_position_in_the_tree>
	 * @param posInTree position of variables in the tree
	 * @return posInTree
	 */
	private int visitNodeInTreePattern(NavigationTreePatternNode node, int posInTree) {
		String varStoringValue = null;
		String varStoringContent = null;
		ArrayList<Variable> matchingVariables = node.getMatchingVariables();
		
		//save a position for the ID
		if(node.storesID())
			posInTree++;
		
		if(matchingVariables.size()!=0) {
			Variable var;
			//count variables storing value first
			for(int i = 0; i < matchingVariables.size(); i++) {
				var = matchingVariables.get(i);
				if(var.dataType == Variable.VariableDataType.Value) {
					if(varStoringValue == null) { //this means var is the first value-storing variable that we find in this node
						if(getVariable(var.name) == null)
							addNewVariable(var);
						var.positionInForest = posInTree;
						finalPositionsByName.put(var.name, posInTree);
						varStoringValue = var.name;
						posInTree++;
					}
					else //we point other value-storing variables in this node to the same final position, since all these variables actually point to the same column
						finalPositionsByName.put(var.name, finalPositionsByName.get(varStoringValue));
				}
			}
			//then count variables storing content
			for(int i = 0; i < matchingVariables.size(); i++) {
				var = matchingVariables.get(i);
				if(var.dataType == Variable.VariableDataType.Content) {
					if(varStoringContent == null) { //this means var is the first content-storing variable we find in this node
						var.positionInForest = posInTree;
						if(getVariable(var.name) == null)
							addNewVariable(var);
						var.positionInForest = posInTree;
						finalPositionsByName.put(var.name, posInTree);
						posInTree++;
					}
					else //we point other content-storing variables in this node to the same final position, since all these variables actually point to the same column 
						finalPositionsByName.put(var.name, finalPositionsByName.get(varStoringContent));
				}
			}
		
			//then the rest, if any
			for(int i = 0; i < matchingVariables.size(); i++) {
				var = matchingVariables.get(i);
				if(var.dataType != Variable.VariableDataType.Value &&
						var.dataType != Variable.VariableDataType.Content) {
					var.positionInForest = posInTree;
					if(getVariable(var.name) == null)
						addNewVariable(var);
					var.positionInForest = posInTree;
					finalPositionsByName.put(var.name, posInTree);
					posInTree++;
				}
			}
		}
		
		return posInTree;
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--TemporaryPositionsByName: "+temporaryPositionsByName.toString());
		sb.append("\n--FinalPositionsByName: "+finalPositionsByName.toString());
		return sb.toString();
	}
}