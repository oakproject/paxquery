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
package fr.inria.oak.paxquery.pact.operators.binary;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operations.RecordPredicateEvaluation;
import fr.inria.oak.paxquery.pact.operations.aggregation.BaseAggregationOperation;
import fr.inria.oak.paxquery.pact.operations.aggregation.Count;
import fr.inria.oak.paxquery.pact.operations.aggregation.Max;
import fr.inria.oak.paxquery.pact.operations.aggregation.Min;
import fr.inria.oak.paxquery.pact.operations.aggregation.Sum;
import fr.inria.oak.paxquery.pact.operators.BaseCrossOperator;

/**
 * General Cross operator to be extended by Join operators in PACT.
 * 
 */
public abstract class BaseCrossJoinOperator extends BaseCrossOperator {
	
	private static final Log logger = LogFactory.getLog(BaseCrossJoinOperator.class);
	
	
	protected static Record crossJoin(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, BasePredicate pred, 
			boolean outer, Record nullRecord, boolean nested, boolean addMark, int aggregationColumn, AggregationType aggregationType, boolean excludeNestedField) {
		BaseAggregationOperation operation = null;
		if(aggregationColumn != -1) {
			switch(aggregationType) {
				case COUNT:
					operation = new Count();
					break;
				case MAX:
					operation = new Max(inputRecordSignature2.types[aggregationColumn]);
					break;
				case MIN:
					operation = new Min(inputRecordSignature2.types[aggregationColumn]);
					break;
				case SUM:
					operation = new Sum(inputRecordSignature2.types[aggregationColumn]);
					break;
				default:
					logger.error("Aggregation type not supported!");
					return null;
			}
		}
		
		// Evaluate predicate on the pair of records
		final boolean evaluation = RecordPredicateEvaluation.evaluate(inputRecordSignature1, record1, inputRecordSignature2, record2, pred);
			
		if(evaluation) {
			if(nested) {
				if(!excludeNestedField) {
					RecordList listNestedRecord = new RecordList();
					listNestedRecord.add(record2);
					record1.addField(listNestedRecord);
					if(addMark)
						record1.addField(new IntValue(1));
				}
				//We add the value of the aggregation column (if any)
				if(aggregationColumn != -1) {
					operation.aggregate(record2.getField(aggregationColumn, StringValue.class));
					record1.addField(operation.returnResult());
				}
			}
			else {
				RecordOperations.concatenate(record1,record2);
				if(addMark)
					record1.addField(new IntValue(1));
			}

		}
		else {
			if(outer) {
				if(nested) {
					if(!excludeNestedField) {
						//We put the null record in a list of records
						RecordList listNestedRecord = new RecordList();
						if(nullRecord != null)
							listNestedRecord.add(nullRecord);
						else
							listNestedRecord.add(RecordOperations.createNullRecord(inputRecordSignature2));
						record1.addField(listNestedRecord);
						if(addMark)
							record1.addField(new IntValue(0));
					}
					//We add the value of the aggregation column (if any)
					if(aggregationColumn != -1) {
						operation.aggregate(RecordOperations.createNullField());
						record1.addField(operation.returnResult());
					}
				}
				else {
					if(nullRecord != null)
						RecordOperations.concatenate(record1,nullRecord);
					else
						RecordOperations.concatenate(record1,RecordOperations.createNullRecord(inputRecordSignature2));
					if(addMark)
						record1.addField(new IntValue(0));
				}

			}
		}
		
		return record1;
	}
	

}
