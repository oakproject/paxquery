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

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;


/**
 * Post-processing left nested outer join operator in PACT, with aggregation.
 * 
 */
public class PostLNOJoinWithAggregationOperator extends PostLNOJoinOperator {
	
	private int combinationColumn;

	private AggregationType aggregationType;
	
	private boolean excludeNestedField;
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);

		this.combinationColumn = parameters.getInteger(PACTOperatorsConfiguration.COMBINATION_COLUMN_INT.toString(), -1);

		String aggregationTypeEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), null);
		byte[] aggregationTypeBytes = DatatypeConverter.parseBase64Binary(aggregationTypeEncoded);
		final AggregationType aggregationType = (AggregationType) SerializationUtils.deserialize(aggregationTypeBytes);
		this.aggregationType = aggregationType;
		
		this.excludeNestedField = parameters.getBoolean(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), false);
	}

	@Override
	public void reduce(Iterator<Record> records, Collector<Record> collector) {
		postLNOJoinWithAggregation(this.inputRecordsSignature, records,
				this.nestedRecordsColumn, this.evaluationColumn,
				this.combinationColumn, this.aggregationType, this.excludeNestedField, collector);
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
	 * @param aggregationColumn
	 * @param aggregationType
	 * @param collector
	 */
	public static void postLNOJoinWithAggregation(NestedMetadata inputRecordsSignature, Iterator<Record> records,
			int nestedRecordsColumn, int evaluationColumn, int combinationColumn,
			AggregationType aggregationType, boolean excludeNestedField, Collector<Record> collector) {
		postJoin(inputRecordsSignature, records, true, true, nestedRecordsColumn,
				evaluationColumn, combinationColumn, aggregationType, excludeNestedField, collector);
	}

}
