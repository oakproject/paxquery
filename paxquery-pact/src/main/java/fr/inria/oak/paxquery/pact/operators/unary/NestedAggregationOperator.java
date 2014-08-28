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

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.IntValue;
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
import fr.inria.oak.paxquery.pact.operators.BaseMapOperator;


/**
 * Aggregation operator in PACT. It works over field of records that belong to a nested collection.
 * 
 */
public class NestedAggregationOperator extends BaseMapOperator {
	
	private static final Log logger = LogFactory.getLog(NestedAggregationOperator.class);


	private int[] aggregationPath;

	private AggregationType aggregationType;
	
	private boolean attachDummyColumn;

	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		String aggregationPathEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_PATH_BINARY.toString(), null);
		byte[] aggregationPathBytes = DatatypeConverter.parseBase64Binary(aggregationPathEncoded);
		final int[] aggregationPath = (int[]) SerializationUtils.deserialize(aggregationPathBytes);
		this.aggregationPath = aggregationPath;
		
		String aggregationTypeEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), null);
		byte[] aggregationTypeBytes = DatatypeConverter.parseBase64Binary(aggregationTypeEncoded);
		final AggregationType aggregationType = (AggregationType) SerializationUtils.deserialize(aggregationTypeBytes);
		this.aggregationType = aggregationType;
		
		this.attachDummyColumn = parameters.getBoolean(PACTOperatorsConfiguration.ATTACH_DUMMY_COLUMN_BOOLEAN.toString(), false);

	}
	
	@Override
	public void map(Record record, Collector<Record> collector) throws InstantiationException, IllegalAccessException {
		nestedAggregation(this.inputRecordsSignature, record, this.aggregationPath, this.aggregationType, this.attachDummyColumn, collector);
	}
	
	/**
	 * @param inputRecordsSignature
	 * @param record
	 * @param aggregationPath
	 * @param aggregationType
	 * @param collector
	 */
	public static void nestedAggregation(NestedMetadata inputRecordsSignature, Record record, int[] aggregationPath, AggregationType aggregationType, boolean attachDummyColumn,
			Collector<Record> collector) {
		nestedAggregationRec(inputRecordsSignature, record, aggregationPath, 0, aggregationType, attachDummyColumn, collector);
		
		collector.collect(record);
	}
	
	private static void nestedAggregationRec(NestedMetadata inputRecordsSignature, Record record, int[] aggregationPath, int pos, AggregationType aggregationType, boolean attachDummyColumn,
			Collector<Record> collector) {
		if(pos != aggregationPath.length - 2) {
			RecordList list = record.getField(aggregationPath[pos++], RecordList.class);
			for(Record nestedRecord: list)
				nestedAggregationRec(inputRecordsSignature, nestedRecord, aggregationPath, pos, aggregationType, attachDummyColumn, collector);
		}
		else {
			BaseAggregationOperation operation;
			switch(aggregationType) {
				case COUNT:
					operation = new Count();
					break;
				case MAX:
					operation = new Max(inputRecordsSignature.getNestedType(aggregationPath));
					break;
				case MIN:
					operation = new Min(inputRecordsSignature.getNestedType(aggregationPath));
					break;
				case SUM:
					operation = new Sum(inputRecordsSignature.getNestedType(aggregationPath));
					break;
				default:
					logger.error("Aggregation type not supported!");
					return;
			}
			
			// For each record, we aggregate the proper value and we add it to the list
			RecordList list = record.getField(aggregationPath[pos++], RecordList.class);
			for(Record nestedRecord: list)
				operation.aggregate(nestedRecord.getField(aggregationPath[pos], StringValue.class));
			record.addField(operation.returnResult());
			if(attachDummyColumn)
				record.addField(new IntValue(1));
		}

	}

}
