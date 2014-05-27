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
/*
 * Created on Aug 26, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.inria.oak.paxquery.algebra.operators.binary;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;


/**
 * Cartesian product logical operator.
 *
 */
public class CartesianProduct extends BaseBinaryOperator {

	public CartesianProduct(BaseLogicalOperator left, BaseLogicalOperator right) throws PAXQueryExecutionException{
		super(left,right);
		this.ownName = "CartProd";
		this.visible = true;
	}
	
	public void buildNRSMD() {
		if(this.children != null && this.children.size() >= 2)
			this.nestedMetadata = NestedMetadataUtils.appendNRSMD(this.children.get(0).getNRSMD(), this.children.get(1).getNRSMD());
	}
	
}
