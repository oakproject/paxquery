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
package fr.inria.oak.paxquery.pact.operators.unary;

import java.util.Iterator;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;


/**
 * Post-processing left nested outer join operator in PACT.
 * 
 */
public class PostLNOJoinOperator extends BasePostJoinOperator {
	
	protected int nestedRecordsColumn;

	protected int evaluationColumn;
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		this.nestedRecordsColumn = parameters.getInteger(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), -1);

		this.evaluationColumn = parameters.getInteger(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), -1);
	}

	@Override
	public void reduce(Iterator<Record> records, Collector<Record> collector) {
		postLNOJoin(this.inputRecordsSignature, records,
				this.nestedRecordsColumn, this.evaluationColumn, collector);
	}
	
	/**
	 * 
	 * @param inputRecordsSignature
	 * @param records
	 * @param outer
	 * @param nested
	 * @param recordIdentifierColumn
	 * @param nestedRecordsColumn
	 * @param evaluationColumn
	 * @param collector
	 */
	public static void postLNOJoin(NestedMetadata inputRecordsSignature, Iterator<Record> records,
			int nestedRecordsColumn, int evaluationColumn, Collector<Record> collector) {
		postJoin(inputRecordsSignature, records, true, true, nestedRecordsColumn, 
				evaluationColumn, -1, null, false, collector);
	}

}
