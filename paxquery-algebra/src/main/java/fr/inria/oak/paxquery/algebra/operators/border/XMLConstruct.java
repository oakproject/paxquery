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
package fr.inria.oak.paxquery.algebra.operators.border;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;


/**
 * XML results construction logical operator.
 *
 */
public class XMLConstruct extends BaseUnaryOperator {
	
	private final String outputPath;
	
	private final ApplyConstruct apply;


	public XMLConstruct(BaseLogicalOperator child, ApplyConstruct apply, String outputPath) throws PAXQueryExecutionException {
		super(child);
		
		this.ownName = "XMLConstruct";
		this.apply = apply;
		this.outputPath = outputPath;
		this.nestedMetadata = this.getChild().getNRSMD();
		this.visible = true;
	}

	public String getOutputPath() {
		return this.outputPath;
	}
	
	public ApplyConstruct getApply() {
		return this.apply;
	}
	
}
