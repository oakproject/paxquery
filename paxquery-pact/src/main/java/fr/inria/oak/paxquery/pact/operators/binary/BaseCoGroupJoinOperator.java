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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operations.RecordPredicateEvaluation;
import fr.inria.oak.paxquery.pact.operations.aggregation.BaseAggregationOperation;
import fr.inria.oak.paxquery.pact.operations.aggregation.Count;
import fr.inria.oak.paxquery.pact.operations.aggregation.Max;
import fr.inria.oak.paxquery.pact.operations.aggregation.Min;
import fr.inria.oak.paxquery.pact.operations.aggregation.Sum;
import fr.inria.oak.paxquery.pact.operators.BaseCoGroupOperator;

/**
 * General CoGroup operator to be extended by Join operators in PACT.
 * 
 */
public abstract class BaseCoGroupJoinOperator extends BaseCoGroupOperator {
	
	private static final Log logger = LogFactory.getLog(BaseCoGroupJoinOperator.class);

	
	protected static void coGroupJoin(NestedMetadata inputRecordsSignature1, Iterator<Record> records1, NestedMetadata inputRecordsSignature2, Iterator<Record> records2, 
			DisjunctivePredicate pred, int from, boolean outer, Record nullRecord, boolean addMark, Collector<Record> collector) {
		if(records1.hasNext()) {
			//If there are records in the right
			if(records2.hasNext()) {
				//First we extract the predicates that we need to reevaluate
				ConjunctivePredicate[] conjPreds = new ConjunctivePredicate[from];
				for(int i=0; i<from; i++)
					conjPreds[i] = pred.getConjunctivePreds().get(i);
				
				//Take the first left record
				Record record1 = records1.next();
				
				//In the first iteration we will cache all the elements in the
				//right iterator
				List<Record> cachedRecords2 = new ArrayList<Record>();
				
				do {
					Record record2 = records2.next().createCopy();
					cachedRecords2.add(record2);
					
					//We check if any of the previous predicates evaluate to true
					boolean evaluation = false;
					for(int i=0; i<from; i++) {
						evaluation = RecordPredicateEvaluation.evaluate(inputRecordsSignature1, record1, inputRecordsSignature2, record2, conjPreds[i]);
						if(evaluation)
							break;
					}
					
					//If any of them does, we do not emit the record. Like this we will not generate
					//duplicates for the next operation
					if(!evaluation) {
						Record newRecord = record1.createCopy();
						RecordOperations.concatenate(newRecord,record2);
						if(addMark)
							newRecord.addField(new IntValue(1));
						collector.collect(newRecord);
					}
				} while(records2.hasNext());
	
				//Then, in the next iterations we use the cached records
				while (records1.hasNext()) {
					record1 = records1.next();
						
					for(Record record2: cachedRecords2) {
						//We check if any of the previous predicates evaluate to true
						boolean evaluation = false;
						for(int i=0; i<from; i++) {
							evaluation = RecordPredicateEvaluation.evaluate(inputRecordsSignature1, record1, inputRecordsSignature2, record2, conjPreds[i]);
							if(evaluation)
								break;
						}
						
						//If any of them does, we do not emit the record. Like this we will not generate
						//duplicates for the next operation
						if(!evaluation) {
							Record newRecord = record1.createCopy();
							RecordOperations.concatenate(newRecord,record2);
							if(addMark)
								newRecord.addField(new IntValue(1));
							collector.collect(newRecord);
						}
					}
				}
			}
			else {
				if(outer) { //ONLY IF IT IS AN OUTER JOIN, OTHERWISE WE DO NOT OUTPUT ANYTHING
					//We concatenate every record in the left with the a null record and
					//and we add a boolean with the false evaluation
					do {
						Record record1 = records1.next();
						if(nullRecord != null)
							RecordOperations.concatenate(record1,nullRecord);
						else
							RecordOperations.addNullRecord(record1, inputRecordsSignature2);
						if(addMark)
							record1.addField(new IntValue(0));
						collector.collect(record1);
					} while(records1.hasNext());
				}
			}
		}
	}
	
	
	protected static void coGroupNestedJoin(NestedMetadata inputRecordsSignature1, Iterator<Record> records1, NestedMetadata inputRecordsSignature2, Iterator<Record> records2, 
			DisjunctivePredicate pred, int from, boolean outer, Record nullRecord, boolean addMark, int aggregationColumn, AggregationType aggregationType,
			boolean excludeNestedField, Collector<Record> collector) {
		if(records1.hasNext()) {
			BaseAggregationOperation operation = null;
			if(aggregationColumn != -1) {
				switch(aggregationType) {
					case COUNT:
						operation = new Count();
						break;
					case MAX:
						operation = new Max(inputRecordsSignature2.types[aggregationColumn]);
						break;
					case MIN:
						operation = new Min(inputRecordsSignature2.types[aggregationColumn]);
						break;
					case SUM:
						operation = new Sum(inputRecordsSignature2.types[aggregationColumn]);
						break;
					default:
						logger.error("Aggregation type not supported!");
						return;
				}
			}
			
			//If there are records in the right
			if(records2.hasNext()) {
				//First we extract the predicates that we need to reevaluate
				ConjunctivePredicate[] conjPreds = new ConjunctivePredicate[from];
				for(int i=0; i<from; i++)
					conjPreds[i] = pred.getConjunctivePreds().get(i);
				
				//Take the first left record
				Record record1 = records1.next();
				
				//In the first iteration we will cache all the elements in the
				//right iterator
				RecordList cachedRecords2 = new RecordList();
				
				//Evaluate against all right records
				RecordList listNestedRecords = new RecordList();
					
				do {
					Record record2 = records2.next().createCopy();
					cachedRecords2.add(record2);
						
					//We check if any of the previous predicates evaluate to true
					boolean evaluation = false;
					for(int i=0; i<from; i++) {
						evaluation = RecordPredicateEvaluation.evaluate(inputRecordsSignature1, record1, inputRecordsSignature2, record2, conjPreds[i]);
						if(evaluation)
							break;
					}
						
					//If any of them does, we do not emit the record. Like this we will not generate
					//duplicates for the next operation
					if(!evaluation) {
						if(aggregationColumn != -1)
							operation.aggregate(record2.getField(aggregationColumn, StringValue.class));
						
						if(!excludeNestedField)
							listNestedRecords.add(record2);
					}
				
				} while(records2.hasNext());
					
				if(!listNestedRecords.isEmpty()) {
					if(!excludeNestedField) {
						record1.addField(listNestedRecords);
						if(addMark)
							record1.addField(new IntValue(1));
					}
					//We add the result of the aggregation (if any)
					if(aggregationColumn != -1)
						record1.addField(operation.returnResult());
					collector.collect(record1);
				}
	
				//Then, in the next iterations we use the cached records
				while (records1.hasNext()) {
					record1 = records1.next();
					
					if(aggregationColumn != -1) {
						switch(aggregationType) {
							case COUNT:
								operation = new Count();
								break;
							case MAX:
								operation = new Max(inputRecordsSignature2.types[aggregationColumn]);
								break;
							case MIN:
								operation = new Min(inputRecordsSignature2.types[aggregationColumn]);
								break;
							case SUM:
								operation = new Sum(inputRecordsSignature2.types[aggregationColumn]);
								break;
							default:
								logger.error("Aggregation type not supported!");
								return;
						}
					}
					
					if(from != 0) {
						listNestedRecords = new RecordList();

						for(Record record2: cachedRecords2) {
							//We check if any of the previous predicates evaluate to true
							boolean evaluation = false;
							for(int i=0; i<from; i++) {
								evaluation = RecordPredicateEvaluation.evaluate(inputRecordsSignature1, record1, inputRecordsSignature2, record2, conjPreds[i]);
								if(evaluation)
									break;
							}
							
							//If any of them does, we do not emit the record. Like this we will not generate
							//duplicates for the next operation
							if(!evaluation) {
								if(aggregationColumn != -1)
									operation.aggregate(record2.getField(aggregationColumn, StringValue.class));
								
								if(!excludeNestedField)
									listNestedRecords.add(record2);
							}

						}
					}
					
					if(!listNestedRecords.isEmpty()) {
						if(!excludeNestedField) {
							record1.addField(listNestedRecords);
							if(addMark)
								record1.addField(new IntValue(1));
						}
						//We add the result of the aggregation (if any)
						if(aggregationColumn != -1)
							record1.addField(operation.returnResult());
						collector.collect(record1);
					}
				}
			}
			else {
				if(outer) { //ONLY IF IT IS AN OUTER JOIN, OTHERWISE WE DO NOT OUTPUT ANYTHING
					//We create a null record and we put it in a list of records
					RecordList listNestedRecords = new RecordList();
					if(!excludeNestedField) {
						if(nullRecord != null)
							listNestedRecords.add(nullRecord);
						else
							listNestedRecords.add(RecordOperations.createNullRecord(inputRecordsSignature2));
					}
					
					//We concatenate every record in the left with the list with the null record and
					//and we add a boolean with the false evaluation
					do {
						Record record1 = records1.next();
						if(!excludeNestedField) {
							record1.addField(listNestedRecords);
							if(addMark)
								record1.addField(new IntValue(0));
						}
						//We add the result of the aggregation (if any)
						if(aggregationColumn != -1)
							record1.addField(operation.returnResult());
						collector.collect(record1);
					} while(records1.hasNext());
				}
			}
		}
	}

}
