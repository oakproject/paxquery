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
package fr.inria.oak.paxquery.algebra.operators;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;


/**
 * Generic class that a logical operator extends.
 *
 */
public abstract class BaseLogicalOperator {
	
	private static final Log logger = LogFactory.getLog(BaseLogicalOperator.class);
	
	
	protected String ownName;
	
	protected String ownDetails;

	protected NestedMetadata nestedMetadata;
	
	protected boolean visible;

	protected ArrayList<BaseLogicalOperator> children;
	
	protected BaseLogicalOperator parent;
	
	protected int[] modifiedFields;


	/*
	 * ABSTRACT METHODS.
	 */
	public abstract int getJoinDepth();
	
	public abstract String getName();
	
	public abstract int recursiveDotString(
		StringBuffer sb,
		int parentNo,
		int firstAvailableNo);
	
	public abstract void recDisplayNRSMD();


	/*
	 * CLASS METHODS. 
	 */
	public void draw(String folder, String givenFileName) {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph  g{\n edge [dir=\"back\"]\n");
		recursiveDotString(sb, 0, 100);
		try {
			if (givenFileName == null){
				givenFileName = "" + System.currentTimeMillis();
			}
			String pathName = "";
			int lastSlash = givenFileName.lastIndexOf(File.separator);
			if (lastSlash > 0){
				pathName = givenFileName.substring(0, lastSlash);
				File dir = new File(pathName);
				dir.mkdirs();
			}
			
			String fileNameDot =  new String(givenFileName + ".dot");
			String fileNamePNG = new String(folder + File.separator + givenFileName + "-LogPlan.png");
			
			FileWriter file = new FileWriter(fileNameDot);
			file.write(new String(sb));
			file.write("}\n");
			file.close();
			Runtime r = Runtime.getRuntime();
			String com = new String("/usr/local/bin/dot -Tpng " + fileNameDot + " -o " + fileNamePNG);
			Process p = r.exec(com);
			p.waitFor();
			logger.debug("Logical plan drawn.");
			Process p2=r.exec("rm "+fileNameDot+"\n");
			p2.waitFor();
		} catch (Exception e) {
			logger.error("Exception: ",e);
		}
	}

	public NestedMetadata getNRSMD() {
		if(this.nestedMetadata == null)
			buildNRSMD();
				
		return this.nestedMetadata;
	}
	
	public abstract void buildNRSMD();
		
	public void resetNRSMD() {
		for(BaseLogicalOperator child : children)
			child.resetNRSMD();
		nestedMetadata = null;
	}

	public ArrayList<BaseLogicalOperator> getChildren() {
		return this.children;
	}
	
	public BaseLogicalOperator getParent() {
		return this.parent;
	}
	
	public void buildOwnDetails() {}
}

