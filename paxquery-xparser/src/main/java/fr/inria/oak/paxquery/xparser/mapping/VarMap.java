package fr.inria.oak.paxquery.xparser.mapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;
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
	
	private ArrayList<Variable> subqueryOuterVariables;	//pointers to those variables in "variables" that contain a Subquery data type

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

	public VarMap() {
		variables = new HashMap<String, Variable>();
		nextTemporaryPosition = 0;
		temporaryPositionsByName = new HashMap<String, Integer>();
		namesByTemporaryPosition = new HashMap<Integer, String>();
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
	 * Adds a list of variables to the varmap by calling the addNewVariable(Variable) method for each one.
	 * @param vars the list of variables
	 */
	public void addNewVariables(List<Variable> vars) {
		if(vars == null)
			return;
		
		for(Variable var : vars)
			addNewVariable(var);
	}
	
	/**
	 * Removes the variable given by varName from all inner data structures
	 * @param varName the name of the variable to remove
	 */
	public void removeVariable(String varName) {
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
	
	public void setTemporaryPositionByName(String varName, int newTemporaryPosition) {
		temporaryPositionsByName.put(varName, newTemporaryPosition);
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
	 * Calculates the final positions of variables in the tree pattern forest than hangs from this operator.
	 * NOTE: the final positions returned are for operator only. Other operators may use different final position values.
	 * @return a VariablePositionEquivalences object that contains the equivalence between the temporal positions and the final positions of variables 
	 */
	/*public VariablePositionEquivalences calculateFinalPositions(ArrayList<BaseLogicalOperator> scanOperators) {
		VariablePositionEquivalences equivalences = new VariablePositionEquivalences();
		int positionOffset = 0;
		int specialPositionOffset = 0;	//for variables not related to XMLScans: those related to groupBy, LONJoin, Aggregations...
		boolean considerSpecial = false;

		//find the variable which stores a subquery (if any)
		buildSubqueryOuterVariables();	//find the variable that holds a subquery (if any)
		for(int i = 0; i < scanOperators.size(); i++) {
			if(scanOperators.get(i) instanceof XMLScan) {
				XMLScan scan = (XMLScan) scanOperators.get(i);
				//add the variable storing the document ID (if needed)
				if(scan.isAttachDocumentID() == true) {
					considerSpecial = true;
					Variable var = getVariable("IDTP-"+scan.getNavigationTreePattern().getName());
					equivalences.addEquivalence(getTemporaryPositionByName(var.name), positionOffset);
					positionOffset++;
					specialPositionOffset++;
				}
				else
					considerSpecial = false;
				//add the variables in the tree patterns
				if(positionOffset < specialPositionOffset)
					positionOffset = specialPositionOffset-1;
				int newPositionOffset = traverseTreePatternDFS(scan.getNavigationTreePattern().getRoot(), equivalences, positionOffset);
				if(scan.isAttachDocumentID())
					specialPositionOffset += (newPositionOffset - positionOffset);
				positionOffset = newPositionOffset;
			}
			else if(scanOperators.get(i) instanceof Aggregation) {
				Aggregation aggr = (Aggregation) scanOperators.get(i);
				if(considerSpecial) {
					equivalences.addEquivalence(getTemporaryPositionByName(aggr.getOuterVariable().name), specialPositionOffset);
					specialPositionOffset++;
				}
				else {
					equivalences.addEquivalence(getTemporaryPositionByName(aggr.getOuterVariable().name), positionOffset);
					positionOffset++;
				}
			}
			//add the variable storing the subquery, if all scans on the left are marked as attaching a documentID and the next does not
			if(i < scanOperators.size()-1 && scanOperators.get(i+1) instanceof XMLScan && ((XMLScan)scanOperators.get(i+1)).isAttachDocumentID() == false 
					&& subqueryOuterVariables != null && subqueryOuterVariables.size() > 0) {
				equivalences.addEquivalence(getTemporaryPositionByName(subqueryOuterVariables.get(0).name), specialPositionOffset);
				specialPositionOffset++;
				//deliberatedly don't increase positionOffset
			}
		}
		return equivalences;
	}*/
	public VariablePositionEquivalences calculateFinalPositions(ArrayList<BaseLogicalOperator> scanOperators) {
		VariablePositionEquivalences equivalences = new VariablePositionEquivalences();
		int positionOffset = 0;
		int specialPositionOffset = 0;	//for variables not related to XMLScans: those related to groupBy, LONJoin, Aggregations...
		boolean considerSpecial = false;

		//find the variable which stores a subquery (if any)
		buildSubqueryOuterVariables();	//find the variable that holds a subquery (if any)
		for(int i = 0; i < scanOperators.size(); i++) {
			if(scanOperators.get(i) instanceof XMLScan) {
				XMLScan scan = (XMLScan) scanOperators.get(i);
				//add the variable storing the document ID (if needed)
				if(scan.isAttachDocumentID() == true) {
					considerSpecial = true;
					Variable var = getVariable("IDTP-"+scan.getNavigationTreePattern().getName());
					equivalences.addEquivalence(getTemporaryPositionByName(var.name), positionOffset);
					positionOffset++;
					specialPositionOffset++;
				}
				else
					considerSpecial = false;
				//add the variables in the tree patterns
				if(positionOffset < specialPositionOffset)
					positionOffset = specialPositionOffset-1;
				int newPositionOffset = traverseTreePatternDFS(scan.getNavigationTreePattern().getRoot(), equivalences, positionOffset);
				if(scan.isAttachDocumentID())
					specialPositionOffset += (newPositionOffset - positionOffset);
				positionOffset = newPositionOffset;
			}
			else if(scanOperators.get(i) instanceof Aggregation) {
				Aggregation aggr = (Aggregation) scanOperators.get(i);
				Variable innerVar = aggr.getInnerVariable();
				if(innerVar.dataType == Variable.VariableDataType.Subquery)
					considerSpecial = true;				
				if(considerSpecial) {
					equivalences.addEquivalence(getTemporaryPositionByName(aggr.getOuterVariable().name), specialPositionOffset);
					specialPositionOffset++;
				}
				else {
					equivalences.addEquivalence(getTemporaryPositionByName(aggr.getOuterVariable().name), positionOffset);
					positionOffset++;
				}
			}
			//add the variable storing the subquery, if all scans on the left are marked as attaching a documentID and the next does not
			if(i < scanOperators.size()-1 && scanOperators.get(i+1) instanceof XMLScan && ((XMLScan)scanOperators.get(i+1)).isAttachDocumentID() == false 
					&& subqueryOuterVariables != null && subqueryOuterVariables.size() > 0) {
				equivalences.addEquivalence(getTemporaryPositionByName(subqueryOuterVariables.get(0).name), specialPositionOffset);
				specialPositionOffset++;
				//deliberatedly don't increase positionOffset
			}
		}
		return equivalences;
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
	private int traverseTreePatternDFS(NavigationTreePatternNode root, VariablePositionEquivalences equivalences, int posInTree) {
		//visit this node
		posInTree = visitNodeInTreePattern(root, equivalences, posInTree);
		ArrayList<NavigationTreePatternNode> children = root.getChildrenList();
		//recursive call for descendant sub-trees, recursion ends when children.size()==0
		for(NavigationTreePatternNode node : children)
			posInTree = traverseTreePatternDFS(node, equivalences, posInTree);	
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
	private int visitNodeInTreePattern(NavigationTreePatternNode node, VariablePositionEquivalences equivalences, int posInTree) {
		String varStoringValue = null;
		String varStoringContent = null;
		ArrayList<Variable> matchingVariables = node.getMatchingVariables();
		int posInTreeFirstVariable = -1;
		
		//save a position for the ID
		if(node.storesID()) {
			ArrayList<Variable> vars = node.getMatchingVariablesStoringID();
			if(vars != null && vars.size() > 0) {
				equivalences.addEquivalence(getTemporaryPositionByName(vars.get(0).name), posInTree);
				posInTree++;
			}
		}
			
		if(matchingVariables.size()!=0) {
			Variable var;
			//count variables storing value first
			for(int i = 0; i < matchingVariables.size(); i++) {
				var = matchingVariables.get(i);
				if(var.dataType == Variable.VariableDataType.Value) {
					if(varStoringValue == null) { //this means var is the first value-storing variable that we find in this node
						posInTreeFirstVariable = posInTree;
						equivalences.addEquivalence(getTemporaryPositionByName(var.name), posInTreeFirstVariable);
						varStoringValue = var.name;
						posInTree++;
					}
					else //we point other value-storing variables in this node to the same final position, since all these variables actually point to the same column
						equivalences.addEquivalence(getTemporaryPositionByName(var.name), posInTreeFirstVariable);
				}
			}
			//then count variables storing content
			for(int i = 0; i < matchingVariables.size(); i++) {
				var = matchingVariables.get(i);
				if(var.dataType == Variable.VariableDataType.Content) {
					if(varStoringContent == null) { //this means var is the first content-storing variable we find in this node
						posInTreeFirstVariable = posInTree;
						equivalences.addEquivalence(getTemporaryPositionByName(var.name), posInTreeFirstVariable);
						varStoringContent = var.name;
						posInTree++;
					}
					else //we point other content-storing variables in this node to the same final position, since all these variables actually point to the same column 
						equivalences.addEquivalence(getTemporaryPositionByName(var.name), posInTreeFirstVariable);
				}
			}
		}
		return posInTree;
	}

	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--TemporaryPositionsByName: "+temporaryPositionsByName.toString());
		return sb.toString();
	}
	
	/**
	 * Prints final positions of the variables in VarMap in a sorted by ascending column position order
	 * @param equivalences
	 * @return
	 */
	public String printNamesFinals(VariablePositionEquivalences equivalences) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		String varName;
		Iterator<String> iterator = temporaryPositionsByName.keySet().iterator();
		while(iterator.hasNext()) {
			try {
				varName = iterator.next();
				int eq = equivalences.getEquivalence(temporaryPositionsByName.get(varName));
				Object[] couple = new Object[2];
				couple[0] = varName;
				couple[1] = new Integer(eq);
				list.add(couple);
			} catch(Exception e) {}
		}
		list.sort(new Comparator<Object[]>() { public int compare(Object[] a, Object[] b) {return ((Integer)a[1]).compareTo((Integer)b[1]);}});
		Iterator<Object[]> iterator2 = list.iterator();
		while(iterator2.hasNext()) {
			Object[] couple = iterator2.next();
			String name = (String)couple[0];
			Integer column = (Integer)couple[1];
			sb.append(name);
			sb.append("=");
			sb.append(column);
			if(iterator2.hasNext())
				sb.append(", ");
		}
		
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Returns an ArrayList<Variable> with pointers to those variables contained in the hashmap that contain a "Subquery" data type.
	 * These list is not ordered because we are currently assuming there will only be one such "subquery" variable, but we should
	 * order the list by order of occurrence if we plan to have more variables
	 */
	public void buildSubqueryOuterVariables() {
		subqueryOuterVariables = new ArrayList<Variable>();
		for(String varName : variables.keySet()) {
			if(variables.get(varName).dataType == Variable.VariableDataType.Subquery)
				subqueryOuterVariables.add(variables.get(varName));
		}
	}
}