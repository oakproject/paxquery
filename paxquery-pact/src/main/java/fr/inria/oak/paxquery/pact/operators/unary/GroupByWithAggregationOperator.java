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
 * GroupBy operator in PACT, plus aggregation on a field of the grouped records. The group field with the nested records
 * may be projected after calculating the aggregation result.
 * 
 */
public class GroupByWithAggregationOperator extends GroupByOperator {
	
	private int aggregationColumn;

	private AggregationType aggregationType;
	
	private boolean excludeNestedField;
	
	private boolean attachDummyColumn;

	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		this.aggregationColumn = parameters.getInteger(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), -1);

		String aggregationTypeEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), null);
		byte[] aggregationTypeBytes = DatatypeConverter.parseBase64Binary(aggregationTypeEncoded);
		final AggregationType aggregationType = (AggregationType) SerializationUtils.deserialize(aggregationTypeBytes);
		this.aggregationType = aggregationType;
		
		this.excludeNestedField = parameters.getBoolean(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), false);
		
		this.attachDummyColumn = parameters.getBoolean(PACTOperatorsConfiguration.ATTACH_DUMMY_COLUMN_BOOLEAN.toString(), false);
	}

	@Override
	public void reduce(Iterator<Record> records, Collector<Record> collector) throws InstantiationException, IllegalAccessException {
		groupByWithAggregation(this.inputRecordsSignature, records, this.groupByColumns, this.nestColumns,
				this.aggregationColumn, this.aggregationType, this.excludeNestedField, this.attachDummyColumn, collector);
	}
	
	public static void groupByWithAggregation(NestedMetadata inputRecordsSignature, Iterator<Record> records, int[] groupByColumns,
			int[] nestColumns, int aggregationColumn, AggregationType aggregationType, boolean excludeNestedField,
			boolean attachDummyColumn, Collector<Record> collector) throws InstantiationException, IllegalAccessException {
		groupBy(inputRecordsSignature, records, groupByColumns, nestColumns, aggregationColumn, aggregationType, excludeNestedField, attachDummyColumn, collector);		
	}

	
	
}
