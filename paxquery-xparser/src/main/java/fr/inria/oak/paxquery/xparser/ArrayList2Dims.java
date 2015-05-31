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
package fr.inria.oak.paxquery.xparser;

import java.util.ArrayList;

public class ArrayList2Dims<T> {
	private ArrayList<ArrayList<T>> list;
	
	/**
	 * Instantiates the class. The list contains numRows rows.
	 * @param numRows initial number of rows the list will contain
	 */
	public ArrayList2Dims(int numRows) {
		list = new ArrayList<ArrayList<T>>();
		for(int i = 0; i < numRows; i++)
			list.add(new ArrayList<T>());
	}

	public int numRows() {
		return list.size();
	}
	
	public int numCols(int row) {
		return list.get(row).size();
	}
	
	public void addRow() {
		list.add(new ArrayList<T>());
	}
	
	public ArrayList<T> getRow(int row) {
		return list.get(row);
	}
	
	public void addElement(int row, T element) {		
		list.get(row).add(element);
	}
	
	/**
	 * Adds an element only if it is not yet contained in the specified row
	 * @param row the affected row
	 * @param element the element to add
	 */
	public void addElementIfNotContained(int row, T element) {
		if(containsElement(row, element) == false)
			addElement(row, element);
	}
	
	public T getElement(int row, int column) {
		return list.get(row).get(column);
	}
	
	public boolean containsElement(int row, T element) {
		return list.get(row).contains(element);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int row = 0; row < list.size(); row++) {
			sb.append("[");	sb.append(row);	sb.append("]");
			sb.append(list.get(row).toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayList2Dims<String> list = new ArrayList2Dims<String>(1);
		list.addElement(0, "hey");
		list.addElement(0, "ho");
		list.addRow();
		list.addElement(1, "a");
		list.addElementIfNotContained(0, "hey");
		
		System.out.println(list.toString());	
	}
	
}
