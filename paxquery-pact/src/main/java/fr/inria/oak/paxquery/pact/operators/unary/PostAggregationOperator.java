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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.aggregation.BaseAggregationOperation;
import fr.inria.oak.paxquery.pact.operations.aggregation.Count;
import fr.inria.oak.paxquery.pact.operations.aggregation.Max;
import fr.inria.oak.paxquery.pact.operations.aggregation.Min;
import fr.inria.oak.paxquery.pact.operations.aggregation.Sum;
import fr.inria.oak.paxquery.pact.operators.BaseReduceOperator;


/**
 * Post aggregation operator in PACT. If the aggregation is over the complete data set, this operator will
 * combine the results coming from the previous nested aggregation or groupBy with aggregation.
 * 
 */
public class PostAggregationOperator extends BaseReduceOperator {
	
	private static final Log logger = LogFactory.getLog(PostAggregationOperator.class);


	private int aggregationColumn;

	private AggregationType aggregationType;
	
	private int nestedColumn;
	
	private boolean excludeNestedField;
	
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		this.aggregationColumn = parameters.getInteger(PACTOperatorsConfiguration.POST_AGGREGATION_COLUMN_INT.toString(), -1);
		
		String aggregationTypeEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), null);
		byte[] aggregationTypeBytes = DatatypeConverter.parseBase64Binary(aggregationTypeEncoded);
		final AggregationType aggregationType = (AggregationType) SerializationUtils.deserialize(aggregationTypeBytes);
		this.aggregationType = aggregationType;
		
		this.nestedColumn = parameters.getInteger(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), -1);
		
		this.excludeNestedField = parameters.getBoolean(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), false);
	}
	
	@Override
	public void reduce(Iterator<Record> records, Collector<Record> collector) throws InstantiationException, IllegalAccessException {
		postAggregation(this.inputRecordsSignature, records, this.aggregationColumn, this.aggregationType, this.nestedColumn, this.excludeNestedField, collector);
	}
	
	/**
	 * @param inputRecordsSignature
	 * @param records
	 * @param aggregationPath
	 * @param aggregationType
	 * @param collector
	 */
	public static void postAggregation(NestedMetadata inputRecordsSignature, Iterator<Record> records, int aggregationColumn,
			AggregationType aggregationType, int nestedColumn, boolean excludeNestedField, Collector<Record> collector) {
		BaseAggregationOperation operation;
		switch(aggregationType) {
			case COUNT:
				operation = new Count();
				break;
			case MAX:
				operation = new Max(inputRecordsSignature.getCompleteMetadata()[aggregationColumn]);
				break;
			case MIN:
				operation = new Min(inputRecordsSignature.getCompleteMetadata()[aggregationColumn]);
				break;
			case SUM:
				operation = new Sum(inputRecordsSignature.getCompleteMetadata()[aggregationColumn]);
				break;
			default:
				logger.error("Aggregation type not supported!");
				return;
		}
		
		// For each record, we aggregate the proper value and we add it to the list
		RecordList list = new RecordList();
		while(records.hasNext()) {
			Record record = records.next();
			if(!excludeNestedField)
				list.addAll(record.getField(nestedColumn, RecordList.class));
			operation.combineAggregation(record.getField(aggregationColumn, StringValue.class));
		}
		
		Record record = new Record();
		if(!excludeNestedField)
			record.addField(list);
		record.addField(operation.returnResult());
		collector.collect(record);
	}

}
