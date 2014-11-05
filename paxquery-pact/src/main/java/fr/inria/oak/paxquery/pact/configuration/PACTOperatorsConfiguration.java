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
package fr.inria.oak.paxquery.pact.configuration;


/**
 * Class containing configuration parameters shared by the PACT operators.
 *
 */
public enum PACTOperatorsConfiguration {
	NTP_STRING("paxquery.input.ntp"),
	CTP_BINARY("paxquery.output.ctp"),
	ATTACH_DOCUMENTID_BOOLEAN("paxquery.input.attachdocumentID"),
	APPLY_CONSTRUCT_BINARY("paxquery.output.applyconstruct"),
	NRSMD1_BINARY("paxquery.operation.nrsmd1"),
	NRSMD2_BINARY("paxquery.operation.nrsmd2"),
	PRED_BINARY("paxquery.operation.predicate"),
	PRED_INT("paxquery.operation.predicatenumber"),
	KEEP_COLUMNS_BINARY("paxquery.operation.keepcolumns"),
	GROUP_BY_COLUMNS_BINARY("paxquery.operation.groupbycolumns"),
	NEST_COLUMNS_BINARY("paxquery.operation.nestcolumns"),
	EXCLUDE_NESTED_FIELD_BOOLEAN("paxquery.operation.excludenesting"),
	AGGREGATION_COLUMN_INT("paxquery.operation.aggregationcolumn"),
	COMBINATION_COLUMN_INT("paxquery.operation.combinationcolumn"),
	DUP_ELIM_COLUMNS_BINARY("paxquery.operation.dupelimcolumns"),
	AGGREGATION_PATH_BINARY("paxquery.operation.aggregationpath"),
	AGGREGATION_TYPE_BINARY("paxquery.operation.aggregationtype"),
	ATTACH_DUMMY_COLUMN_BOOLEAN("paxquery.operation.attachdummycolumn"),
	POST_AGGREGATION_COLUMN_INT("paxquery.operation.postaggregationcolumn"),
	UNNEST_PATH_BINARY("paxquery.operation.unnestpath"),
	NAVIGATION_COLUMN_INT("paxquery.operation.navigationcolumn"),
	NESTED_RECORDS_COLUMN_INT("paxquery.operation.nestedrecordscolumn"),
	EVALUATION_COLUMN_INT("paxquery.operation.evaluationresultcolumn");
		
	    
	private final String name;
	
	private PACTOperatorsConfiguration(final String name) {
		this.name = name;
	}
		
	@Override
	public String toString() {
		return this.name;
	}

}
