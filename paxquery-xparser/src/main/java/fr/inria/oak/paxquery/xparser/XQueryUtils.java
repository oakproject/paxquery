package fr.inria.oak.paxquery.xparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;

public class XQueryUtils {
	
	private static int auxVarCounter = 0;
	
	/**
	 * Returns the index of the XMLScan operator in "scans" that contains the variable with name "varName", or -1 if the variable is not contained in any operator.
	 * @param scans an ArrayList containing XMLScan operators
	 * @param patternNodeMap links variable names to NavigationTreePatternNodes that store the variable
	 * @param varName the name of the variable
	 * @return the index of the XMLScan operator in "scans" that contains "varName", or -1 if "varName" was not found
	 */
	public static int findVarInPatternTree(ArrayList<XMLScan> scans, HashMap<String, NavigationTreePatternNode> patternNodeMap, String varName) {
		NavigationTreePatternNode node = patternNodeMap.get(varName);
		if(node == null)
			return -1;
		
		NavigationTreePattern tp = node.getTreePattern();
		if(tp == null)
			return -1;
		
		for(int i = 0; i < scans.size(); i++) {
			if(tp == scans.get(i).getNavigationTreePattern())
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the index of the XMLScan operator in "scans" that contains the tree pattern "tp", or -1 if the tree pattern is not contained in any operator.
	 * @param scans an ArrayList containing XMLScan operators
	 * @param tp the tree pattern looked for
	 * @return the index of the XMLScan operator in "scans" that contanis tree pattern "tp"
	 */
	public static int findTreePatternIndexInScans(ArrayList<XMLScan> scans, NavigationTreePattern tp) {
		for(int i = 0; i < scans.size(); i++) {
			if(tp == scans.get(i).getNavigationTreePattern())
				return i;
		}
		return -1;
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
	
	/**
	 * Converts a List<Integer> into a int[]
	 * @param list
	 * @return
	 */
	public static int[] IntegerListToIntArray(List<Integer> list) {
		int[] array = null;
		if(list != null) {
			array = new int[list.size()];
			for(int i = 0; i < list.size(); i++)
				array[i] = list.get(i);
		}
		return array;
	}
}