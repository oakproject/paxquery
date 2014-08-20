package fr.inria.oak.paxquery.xparser.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Stores equivalences between the temporal position of a variable and the final position for the same variable
 * @author jalvaro
 *
 */
public class VariablePositionEquivalences {
	HashMap<Integer, Integer> tempFinal;	//key = temporal position; value = final position
	
	public VariablePositionEquivalences() {
		tempFinal = new HashMap<Integer, Integer>();
	}

	/**
	 * Sets an equivalence (temporal position, final position)
	 * @param temporalPosition of a variable
	 * @param finalPosition of the same variable
	 */
	public void addEquivalence(int temporalPosition, int finalPosition) {
		tempFinal.put(temporalPosition, finalPosition);
	}
	
	/**
	 * Returns the final position that corresponds to the specified temporal position
	 * @param temporalPosition of a variable
	 * @return the final position of the same variable
	 */
	public int getEquivalence(int temporalPosition) {
		return tempFinal.get(temporalPosition);
	}	
	
	/**
	 * Returns an Integer[] array with final positions that correspond to those temporal positions specified as input
	 * @param temporalPositions an Integer[] array with temporal positions for variables
	 * @return the corresponding final positions according to this equivalence
	 */
	public int[] getEquivalence(int[] temporalPositions) {
		if(temporalPositions == null)
			return null;
		
		int[] finalPositions = new int[temporalPositions.length];
		for(int i = 0; i < temporalPositions.length; i++)
			finalPositions[i] = getEquivalence(temporalPositions[i]);
		return finalPositions;
	}
	
	/**
	 * Returns an ArrayList<Integer> list with final positions that correspond to those temporal positions specified as input
	 * @param temporalPositions an ArrayList<Integer> list with temporal positions for variables
	 * @return the corresponding final positions according to this equivalence
	 */
	public ArrayList<Integer> getEquivalence(List<Integer> temporalPositions) {
		if(temporalPositions == null)
			return null;
		
		ArrayList<Integer> finalPositions = new ArrayList<Integer>();
		for(int i = 0; i < temporalPositions.size(); i++)
			finalPositions.add(getEquivalence(temporalPositions.get(i)));
		return finalPositions;
	}
}
