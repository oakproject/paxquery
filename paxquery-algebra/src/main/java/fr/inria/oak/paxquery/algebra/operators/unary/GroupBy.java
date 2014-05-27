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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;


/**
 * GroupBy logical operator.
 * 
 */
public class GroupBy extends BaseUnaryOperator {
	
	private static final Log logger = LogFactory.getLog(GroupBy.class);

	
	private final int[] groupByColumns;
	
	private final int[] nestColumns;
		

	/**
	 * 
	 * @param child The child operator
	 * @param groupedColumns The columns (at top level) that should be packed together in a group
	 *      If we need to group together columns which are below the top level, give also an
	 *      ancestor path (int[]) as per the second constructor below
	 * @throws XMLStratosphereExecutionException
	 */
	public GroupBy(BaseLogicalOperator child, int[] groupByColumns, int[] nestColumns) throws PAXQueryExecutionException {
		super(child);
		
		this.visible = true;
		this.ownName = "GroupBy";
		this.groupByColumns = groupByColumns;
		this.nestColumns = nestColumns;
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if(this.groupByColumns.length != 0) {
			sb.append(this.groupByColumns[0]);
			for(int i=1;i<this.groupByColumns.length;i++) {
				sb.append(",");
				sb.append(this.groupByColumns[i]);
			}
		}
		sb.append("]");
		this.ownDetails = new String(sb);
	}
	
	@Override
	public void buildNRSMD() {
		for(BaseLogicalOperator op : children)
			op.buildNRSMD();
		//We keep only the columns that are useful at the highest level (the group-by columns,
		//the aggregation columns and the nested column)
		final NestedMetadata groupByNRSMD = NestedMetadataUtils.makeProjectRSMD(children.get(0).getNRSMD(), this.groupByColumns);
		final NestedMetadata nestedNRSMD = NestedMetadataUtils.makeProjectRSMD(children.get(0).getNRSMD(), this.nestColumns);
		
		this.nestedMetadata = NestedMetadataUtils.addNestedField(groupByNRSMD, nestedNRSMD);
	}
	

	public int[] getGroupByColumns() {
		return this.groupByColumns;
	}

	public int[] getNestColumns() {
		return this.nestColumns;
	}
	
}
