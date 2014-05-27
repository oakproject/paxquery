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
package fr.inria.oak.paxquery.algebra.operators.unary;

import java.util.ArrayList;
import java.util.HashMap;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;


/**
 * Projection logical operator.
 *
 */
public class Projection extends BaseUnaryOperator {

	public int[] columns;
	
	
	public Projection(BaseLogicalOperator child, int[] columns) throws PAXQueryExecutionException{
		super(child);

		this.ownName = "Proj";
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < columns.length; i ++){
			if (i > 0){
				sb.append(",");
			}
			sb.append(columns[i]);
		}
		sb.append("]");
		this.ownDetails = new String(sb);
		this.columns = columns;
		this.visible = true;
	}
	
	@Override
	public void buildNRSMD() {
		for(BaseLogicalOperator op : children)
			op.buildNRSMD();
		this.nestedMetadata = NestedMetadataUtils.makeProjectRSMD(children.get(0).getNRSMD(), columns);
	}

}
