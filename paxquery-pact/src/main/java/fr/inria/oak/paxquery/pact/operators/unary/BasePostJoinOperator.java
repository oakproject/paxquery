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
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operations.aggregation.BaseAggregationOperation;
import fr.inria.oak.paxquery.pact.operations.aggregation.Count;
import fr.inria.oak.paxquery.pact.operations.aggregation.Max;
import fr.inria.oak.paxquery.pact.operations.aggregation.Min;
import fr.inria.oak.paxquery.pact.operations.aggregation.Sum;
import fr.inria.oak.paxquery.pact.operators.BaseReduceOperator;

/**
 * General post-join operator to be extended by other PACT operators.
 * 
 */
public abstract class BasePostJoinOperator extends BaseReduceOperator {
	
	private static final Log logger = LogFactory.getLog(BasePostJoinOperator.class);

	
	protected static void postJoin(NestedMetadata inputRecordsSignature, Iterator<Record> records, boolean outer, boolean nested,
			int recordIdentifierColumn, int nestedRecordsColumn, int evaluationColumn, int combinationColumn,
			AggregationType aggregationType, boolean excludeNestedField, Collector<Record> collector) {
		//CREATING AGGREGATION IN CASE WE NEED IT
		BaseAggregationOperation operation = null;
		if(combinationColumn != -1) {
			switch(aggregationType) {
				case COUNT:
					operation = new Count();
					break;
				case MAX:
					operation = new Max(inputRecordsSignature.types[combinationColumn]);
					break;
				case MIN:
					operation = new Min(inputRecordsSignature.types[combinationColumn]);
					break;
				case SUM:
					operation = new Sum(inputRecordsSignature.types[combinationColumn]);
					break;
				default:
					logger.error("Aggregation type not supported!");
					return;
			}
		}
		
		if(!outer) { //INNER NESTED JOIN - WE JUST NEED TO FUSE THE NESTED LISTS AND COMBINE THE AGGREGATION
			RecordList newListNestedRecords = new RecordList();
			
			Record record;
			do {
				record = records.next();
				if(!excludeNestedField)
					newListNestedRecords.addAll(record.getField(nestedRecordsColumn, RecordList.class));
				
				if(combinationColumn != -1)
					operation.combineAggregation(record.getField(combinationColumn, StringValue.class));
			} while(records.hasNext());
			
			int[] keepColumns = new int[recordIdentifierColumn];
			for(int i=0; i<recordIdentifierColumn; i++)
				keepColumns[i] = i;
			
			RecordOperations.project(record, keepColumns);
			if(!excludeNestedField)
				record.addField(newListNestedRecords);
			if(combinationColumn != -1)
				record.addField(operation.returnResult());
			collector.collect(record);
		}
		else { //OUTER -NESTED AND NOT NESTED- JOIN
			RecordList newListNestedRecords = new RecordList();
			
			boolean evaluation = false;
			Record record;
			do {
				record = records.next();
				if(!excludeNestedField) {
					if(record.getField(evaluationColumn, IntValue.class).getValue() == 1) {
						newListNestedRecords.addAll(record.getField(nestedRecordsColumn, RecordList.class));
						evaluation = true;
					}
				}
				if(combinationColumn != -1)
					operation.combineAggregation(record.getField(combinationColumn, StringValue.class));
			} while(records.hasNext());
			
			if(!excludeNestedField && !evaluation)
				newListNestedRecords = record.getField(nestedRecordsColumn, RecordList.class);
			
			int[] keepColumns = new int[recordIdentifierColumn];
			for(int i=0; i<recordIdentifierColumn; i++)
				keepColumns[i] = i;
			
			RecordOperations.project(record, keepColumns);
			
			if(nested) { //OUTER NESTED JOIN
				if(!excludeNestedField)
					record.addField(newListNestedRecords);
				if(combinationColumn != -1)
					record.addField(operation.returnResult());
				collector.collect(record);
			}
			else { //OUTER JOIN - WE UNNEST AT THE SAME TIME THAT WE EMIT THE RECORDS
				for(Record rightRecord: newListNestedRecords) {
					Record recordCopy = record.createCopy();
					RecordOperations.concatenate(recordCopy,rightRecord);
					collector.collect(recordCopy);
				}
			}
		}
	}

}
