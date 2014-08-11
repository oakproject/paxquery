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
import java.util.List;


/**
 * Class that represents each node of a {@link ConstructionTreePattern}.
 * 
 */
public final class ConstructionTreePatternNode implements Serializable {
	
	private ConstructionTreePattern ctp;
	
	private List<Integer> varPath; 	// Path with variable positions
	private String tag;				// Tag
	private boolean attribute;		// Is it an XML attribute?
	private boolean optional;		// Is it an optional node?
	
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, String tag, boolean attribute, boolean optional) {
		this(ctp, null, tag, attribute, optional);
	}
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, List<Integer> varPath, boolean optional) {
		this(ctp, varPath, null, false, optional);
	}
	
	public ConstructionTreePatternNode(String tag, boolean attribute, boolean optional) {
		this(null, null, tag, attribute, optional);
	}
	
	public ConstructionTreePatternNode(List<Integer> varPath, boolean optional) {
		this(null, varPath, null, false, optional);
	}
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, List<Integer> varPath, String tag, boolean attribute, boolean optional) {
		this.ctp = ctp;
		this.varPath = varPath;
		this.tag = tag;
		this.attribute = attribute;
		this.optional = optional;
	}
	
	
	public ConstructionTreePattern getConstructionTreePattern() {
		return this.ctp;
	}

	public void setConstructionTreePattern(ConstructionTreePattern ctp) {
		this.ctp = ctp;
	}

	public List<Integer> getVarPath() {
		return this.varPath;
	}
	
	public void setVarPath(List<Integer> varPath) {
		this.varPath = varPath;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public boolean isAttribute() {
		return this.attribute;
	}
	
	public void setAttribute(boolean isAttribute) {
		this.attribute = isAttribute;
	}
	
	public boolean isOptional() {
		return this.optional;
	}
	
	public void setOptiona(boolean isOptional) {
		this.optional = isOptional;
	}
}
