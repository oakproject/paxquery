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
package fr.inria.oak.paxquery.common.xml.construction;

import java.io.Serializable;


/**
 * Contruction pattern.
 *
 */
public class ApplyConstruct implements Serializable {
	
	private final String before;
	private final String[] each;
	private final String after;
	
	private final int[] fields;
	private final ApplyConstruct[] nested;
	
	
	public ApplyConstruct(String before, String[] each, String after, int[] fields, ApplyConstruct[] nested) {
		this.before = before;
		this.each = each;
		this.after = after;
		
		this.fields = fields;
		this.nested = nested;
	}


	public String getBefore() {
		return this.before;
	}


	public String getAfter() {
		return this.after;
	}


	public int[] getFields() {
		return this.fields;
	}


	public String[] getEach() {
		return this.each;
	}


	public ApplyConstruct[] getNested() {
		return this.nested;
	}
}
