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
		if(temporalPosition == -1)
			return 0;
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
