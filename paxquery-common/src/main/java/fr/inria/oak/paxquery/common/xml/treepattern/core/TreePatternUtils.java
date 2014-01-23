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

import java.io.FileReader;
import java.io.StringReader;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.configuration.GlobalConfiguration;
import fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ASTStart;
import fr.inria.oak.paxquery.common.xml.treepattern.test.parser.ParseException;
import fr.inria.oak.paxquery.common.xml.treepattern.test.parser.TreePatternParser;
import fr.inria.oak.paxquery.common.xml.treepattern.test.parser.TreePatternParserVisitor;
import fr.inria.oak.paxquery.common.xml.treepattern.test.parser.TreePatternParserVisitorImplementation;


/**
 * Class with all the utilities that we can use for parsing {@link TreePattern}s
 * from files or String objects and vice versa.
 * 
 */
public class TreePatternUtils {
		
	private static final Log logger = LogFactory.getLog(TreePatternUtils.class);
	
	private static final String START_NAMESPACE_DELIMITER = GlobalConfiguration.ConfigurationParameters.START_DELIMITER.getString();
	private static final String END_NAMESPACE_DELIMITER = GlobalConfiguration.ConfigurationParameters.END_DELIMITER.getString();
	
	
	/**
	 * 
	 */
	public static String getParsableStringFromTreePattern(TreePattern p) {
		String nodes = "" ;
		String edges = "" ;
		String file = "" ;
		
		LinkedList<PatternNode> nodesList = p.getNodes();

		try {
			for (int j = 0; j < p.getNodesNo(); j++) {
				PatternNode dummy = nodesList.get(j);

				if (dummy.isAttribute()) {
					nodes += "A: ";
				} else
					nodes += "E: ";
				nodes += dummy.getNodeCode();

				if (dummy.storesID()) {
					nodes += " ID ";
					if(dummy.isIdentityIDType())
						nodes += "i";
					else if(dummy.isOrderIDType())
						nodes += "o";
					else if(dummy.isStructIDType())
						nodes += "s";
					else if(dummy.isUpdateIDType())
						nodes += "u";
				}

				if (dummy.requiresID()) {
					nodes += " R ";
				}
				if (dummy.selectsTag()) {
					if(dummy.getNamespace().compareTo("") != 0)
						nodes += " [Tag=\"" + START_NAMESPACE_DELIMITER + dummy.getNamespace() + END_NAMESPACE_DELIMITER + dummy.getTag() + "\"]";
					else
						nodes += " [Tag=\"" + dummy.getTag() + "\"]";
				} 
				if (dummy.storesTag()) {
					nodes += " Tag";
				}
				
				if (dummy.requiresTag()) {
					nodes += " R ";
				}
				
				if (dummy.selectsValue()) {
					nodes += " [Val" + dummy.getSelectOnValuePredicate().toString() + (dummy.getStringValue() != null ? "\"" + dummy.getStringValue() + "\"" : (int)dummy.getDoubleValue()) + "]";
				}
				if (dummy.storesValue()) {
					nodes += " Val";
				}

				if (dummy.requiresVal()) {
					nodes += " R ";
				}
				if (dummy.storesContent()) {
					nodes += " Cont";
				}

				nodes += "\n";

				for (int k = 0; k < dummy.getEdges().size(); k++) {
					PatternEdge edge = dummy.getEdges().get(k);
					edges += edge.n1.getNodeCode() + "," + edge.n2.getNodeCode();
					edges += (edge.isParent()) ? " / " : " // ";
					edges += edge.isNested() ? "n" : "";
					edges += edge.isOptional() ? "o" : "j";
					edges += "\n";

				}
			}
		} catch (Exception e) {
			logger.error("Exception: ",e);
		}
		file = (p.isOrdered() ? "o " : " ")
				+ (p.getRoot().getEdges().get(0).isParent() ? "/\n" : "\n") + nodes + ";\n" + edges;
		return file;
	}
	
	/**
	 * 
	 */
	public static TreePattern getTreePatternFromFile(String tPatFileName) {
		try {
			// parse the query - for now the translation from XQuery is not done,
			// so the query is already a XAM file
			TreePatternParser parser = new TreePatternParser(new FileReader(tPatFileName));
	
		    //parsing the xams of the file
		    ASTStart st = parser.Start(tPatFileName);
		    TreePatternParserVisitor v = new TreePatternParserVisitorImplementation(START_NAMESPACE_DELIMITER, END_NAMESPACE_DELIMITER);
		    TreePattern tPat = (TreePattern) st.jjtAccept(v, null);
		    		    
		    //give names to the JoinedPattern and the included Patterns
		    String jPatName = tPatFileName.substring( tPatFileName.lastIndexOf(System.getProperty("file.separator")) + 1 );
		    tPat.setName(jPatName);
		    
		    return tPat;
		} catch(Exception e){
	    	logger.error("Exception: ",e);
	    	return null;
	    }
	}
	
	/**
	 * 
	 */
	public static TreePattern getTreePatternFromString(String tPatString, String tPatFileName) {
	    try{
	    	// parse the query - for now the translation from XQuery is not done,
	    	// so the query is already a XAM file
	    	TreePatternParser parser = new TreePatternParser(new StringReader(tPatString));

	    	//parsing the xams of the file
			ASTStart st = parser.Start(tPatFileName);
	    	TreePatternParserVisitor v = new TreePatternParserVisitorImplementation(START_NAMESPACE_DELIMITER, END_NAMESPACE_DELIMITER);
	    	TreePattern tPat = (TreePattern) st.jjtAccept(v, null);
	    
	    	//give names to the JoinedPattern and the included Patterns
	    	String jPatName = tPatFileName.substring( tPatFileName.lastIndexOf("/") + 1 );
	    	tPat.setName(jPatName);
		    
	    	return tPat;
	    }
	    catch(ParseException e){
	    	logger.error("Exception: ",e);
	    	return null;
	    }

	}
}
