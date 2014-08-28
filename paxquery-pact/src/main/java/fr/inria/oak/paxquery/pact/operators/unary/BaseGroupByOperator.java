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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;
import org.apache.flink.types.Value;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.datamodel.metadata.MetadataTypesMapping;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operations.aggregation.BaseAggregationOperation;
import fr.inria.oak.paxquery.pact.operations.aggregation.Count;
import fr.inria.oak.paxquery.pact.operations.aggregation.Max;
import fr.inria.oak.paxquery.pact.operations.aggregation.Min;
import fr.inria.oak.paxquery.pact.operations.aggregation.Sum;
import fr.inria.oak.paxquery.pact.operators.BaseReduceOperator;

/**
 * General groupBy operator to be extended by other PACT operators.
 *
 */
public abstract class BaseGroupByOperator extends BaseReduceOperator {
	
	private static final Log logger = LogFactory.getLog(BaseGroupByOperator.class);

	
	/**
	 * 
	 * @param records
	 * @param columnsToKeep
	 * @param columnsToConcatenate
	 * @param collector
	 */
	protected static void groupBy(NestedMetadata inputRecordsSignature, Iterator<Record> records, int[] groupByColumns,
			int[] nestColumns, int aggregationColumn, AggregationType aggregationType, boolean excludeNestedField,
			boolean attachDummyColumn, Collector<Record> collector) throws InstantiationException, IllegalAccessException {
		BaseAggregationOperation operation = null;
		if(aggregationColumn != -1) {
			switch(aggregationType) {
				case COUNT:
					operation = new Count();
					break;
				case MAX:
					operation = new Max(inputRecordsSignature.types[aggregationColumn]);
					break;
				case MIN:
					operation = new Min(inputRecordsSignature.types[aggregationColumn]);
					break;
				case SUM:
					operation = new Sum(inputRecordsSignature.types[aggregationColumn]);
					break;
				default:
					logger.error("Aggregation type not supported!");
					return;
			}
		}
		
		//Initialize the list to be nested records...
		RecordList resultsToKeep = new RecordList();
		
		//Store the contents for the new record
		NestedMetadata nestedNRSMD = null;
		if(!excludeNestedField)
			nestedNRSMD = inputRecordsSignature.getNestedChild(inputRecordsSignature.getColNo()-1);

		Record record;
		do {
			record = records.next();
			
			if(!excludeNestedField) {
				Record nestedRecord = new Record(nestedNRSMD.getColNo());	
				int k=0;
				for(int columnToNest: nestColumns) {
					Value value = MetadataTypesMapping.getValueClass(nestedNRSMD.getType(k)).newInstance();
					record.getFieldInto(columnToNest, value);
					nestedRecord.setField(k, value);
					k++;
				}
				if(k != 0)
					resultsToKeep.add(nestedRecord);
			}
			if(aggregationColumn != -1) //Logic for aggregation...
				operation.aggregate(record.getField(aggregationColumn, StringValue.class));
		} while(records.hasNext());

		//We create the output record as a copy of the last one
		Record outputRecord = record.createCopy();
		//We project the columns that were kept nested
		RecordOperations.project(outputRecord, groupByColumns);
		//We add the nested field
		if(!excludeNestedField)
			outputRecord.addField(resultsToKeep);
		//We add the result of the aggregation (if any)
		if(aggregationColumn != -1)
			outputRecord.addField(operation.returnResult());
		if(attachDummyColumn)
			outputRecord.addField(new IntValue(1));
		
		//Output the new record
		collector.collect(outputRecord);
	}

}
