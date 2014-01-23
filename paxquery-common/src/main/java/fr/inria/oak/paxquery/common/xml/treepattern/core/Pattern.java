/*******************************************************************************
 * Copyright (C) 2013, 2014 by Inria and Paris-Sud University
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
package fr.inria.oak.paxquery.common.xml.treepattern.core;

import java.io.Serializable;


/**
 * This is the superclass for the {@link TreePattern}.
 * 
 */
public abstract class Pattern implements Serializable {

	/**
	 * Universal version identifier for Pattern class
	 */
	private static final long serialVersionUID = -1512139584911418130L;

	/**
	 * Unique identifier for the Pattern
	 */
	protected long patternID;
	
	/**
	 * The name of the Pattern
	 */
	protected String name;
	
	public Pattern() {
		super();
	}
	
	public Pattern(long patternID) {
		this.patternID = patternID;
	}
	
	public long getPatternID() {
		return patternID;
	}

	public void setPatternID(long patternID) {
		this.patternID = patternID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * prints the current Pattern
	 */
	public abstract void display();
	
	/**
	 * Method that creates an image representation of the pattern and stores it to a file
	 * @param givenFilename the name of the file where the image will be saved
	 * @param query true if the pattern that we want to draw is a query or false otherwise.
	 * Query nodes will be filled in yellow and view nodes will be filled in blue
	 */
	public abstract void draw(String imagesPath, String givenFilename, boolean query, String backgroundColor, String foregroundColor);
}
