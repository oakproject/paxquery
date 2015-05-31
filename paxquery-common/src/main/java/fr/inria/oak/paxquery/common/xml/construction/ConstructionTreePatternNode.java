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
package fr.inria.oak.paxquery.common.xml.construction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents each node of a {@link ConstructionTreePattern}.
 * 
 */
public final class ConstructionTreePatternNode implements Serializable {
	
	private static final long serialVersionUID = -3853137057985492439L;

	public enum ContentType { 
		ELEMENT, 			// The node stores an XML element node (e.g. <*element* attribute="attribute_value">element_value</*element*>, marked between *)
		ATTRIBUTE, 			// The node stores an XML attribute node (e.g. <element *attribute*="attribute_value">element_value</element>, marked between *)
		ATTRIBUTE_VALUE, 	// The node stores the value of an XML attribute node if literal (e.g. <element attribute="*attribute_value*">element_value</element>, marked between *)
		ELEMENT_VALUE, 		// The node stores the value of an XML element node if literal (e.g. <element attribute="attribute_value">*element_value*</element>, marked between *)
		VARIABLE_PATH };	// The node stores the variable path in an XML element node or attribute (e.g. <element attribute="attribute_value">{*$a*}</element>, marked between *)
	
	private ConstructionTreePattern ctp;

	private ContentType contentType;	// What does this node store?
	private List<Integer> varPath; 		// Path with variable positions
	private String value;				// Either XML node tag, XML attribute tag, XML content (if literal) or attribute content (if literal)
	private boolean optional;			// Is it an optional node?
	private boolean variablePathRemapped;// Has the varmap been remapped?
	private String outerVariable = null;	//if != -1 then this varpath is nested and this subtractFromVarpath stores the column of the outer variable; positions of inner columns should be calculated by subtracting subtractFromVarpath  
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, ContentType contentType, String value, boolean optional) {
		this(ctp, contentType, null, value, optional);
	}
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, ContentType contentType, List<Integer> varPath, boolean optional) {
		this(ctp, contentType, varPath, null, optional);
	}
	
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, ContentType contentType, int[] varPath, boolean optional) {
		this(ctp, contentType, null, null, optional);		

		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int var : varPath)
			list.add(var);
		this.setVarPath(list);
	}
	
	public ConstructionTreePatternNode(ContentType contentType, String value, boolean optional) {
		this(null, contentType, null, value, optional);
	}
	
	public ConstructionTreePatternNode(ContentType contentType, List<Integer> varPath, boolean optional) {
		this(null, contentType, varPath, optional);
	}
		
	public ConstructionTreePatternNode(ConstructionTreePattern ctp, ContentType contentType, List<Integer> varPath, String value, boolean optional) {
		this.ctp = ctp;
		this.varPath = varPath;
		this.value = value;
		this.contentType = contentType;
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
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
		
	public ContentType getContentType() {
		return this.contentType;
	}
	
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
		
	public boolean isOptional() {
		return this.optional;
	}
	
	public void setOptional(boolean isOptional) {
		this.optional = isOptional;
	}

	public boolean isVariablePathRemaped() {
		return variablePathRemapped;
	}
	
	public void setVariablePathRemapped(boolean value) {
		variablePathRemapped = true;
	}

	
	public ConstructionTreePatternNode getParent() {
		if(ctp == null)
			return null;

		ConstructionTreePatternEdge edge = ctp.getParentEdges().get(this);
		if(edge != null)
			return edge.getParent();
		
		return null;
	}
	
	public ArrayList<ConstructionTreePatternNode> getChildren() {
		ArrayList<ConstructionTreePatternNode> children = new ArrayList<ConstructionTreePatternNode>();
		if(ctp == null)
			return children;
		
		for(ConstructionTreePatternEdge edge : ctp.getChildrenEdges().get(this))
			children.add(edge.getChild());
					
		return children;			
	}
	
	public String toString() {
		String output;
		if(contentType == ContentType.VARIABLE_PATH)
			output = optional ? contentType + " " + varPath + " optional" : contentType + " " +varPath;
		else
			output = optional ? contentType + " " + value + " optional" : contentType + " " + value; 
		return output;
	}
	
	/*
	 * Sets this.ctp = ctp, and does the same for this' children
	 * Recursive method
	 */
	/*public void setCTPFromHere(ConstructionTreePattern anotherCTP) {
		this.ctp = anotherCTP;
		
		for(ConstructionTreePatternNode child : this.getChildren()){
			child.setCTPFromHere(anotherCTP);
		}		
	}*/
	public void setCTPFromHere(ConstructionTreePattern anotherCTP) {
		for(ConstructionTreePatternNode child : this.getChildren()){
			child.setCTPFromHere(anotherCTP);
		}		
		this.ctp = anotherCTP;
	}
	
	public void setOuterVariable(String name) {
		this.outerVariable = name;
	}
	
	public String getOuterVariable() {
		return this.outerVariable;
	}
}
