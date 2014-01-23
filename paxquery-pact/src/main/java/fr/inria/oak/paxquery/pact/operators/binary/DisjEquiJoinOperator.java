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
package fr.inria.oak.paxquery.pact.operators.binary;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;

import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.types.Record;
import eu.stratosphere.util.Collector;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordPredicateEvaluation;
import fr.inria.oak.paxquery.pact.operators.BaseMatchOperator;

/**
 * Disjunctive equi-join operator in PACT.
 * 
 */
public class DisjEquiJoinOperator extends BaseMatchOperator {

	private DisjunctivePredicate pred;
	
	private int predNumber;
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);

		String predEncoded = parameters.getString(PACTOperatorsConfiguration.PRED_BINARY.toString(), null);
		byte[] predBytes = DatatypeConverter.parseBase64Binary(predEncoded);
		this.pred = (DisjunctivePredicate) SerializationUtils.deserialize(predBytes);
		
		this.predNumber = parameters.getInteger(PACTOperatorsConfiguration.PRED_INT.toString(), -1);
	}
	
	@Override
	public void join(Record record1, Record record2, Collector<Record> collector) {
		disjEquiJoin(this.inputRecordsSignature1, record1, this.inputRecordsSignature2, record2, 
				this.pred, this.predNumber, false, collector);	
	}

	/**
	 * 
	 * 
	 * @param inputRecordSignature1
	 * @param record1
	 * @param inputRecordSignature2
	 * @param record2
	 * @param pred
	 * @param collector
	 */
	public static void disjEquiJoin(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2,
			DisjunctivePredicate pred, int from, boolean nested, Collector<Record> collector) {
		//First we extract the predicates that we need to reevaluate
		ConjunctivePredicate[] conjPreds = new ConjunctivePredicate[from];
		for(int i=0; i<from; i++)
			conjPreds[i] = pred.getConjunctivePreds().get(i);
		
		//We check if any of the previous predicates evaluate to true
		boolean evaluation = false;
		for(int i=0; i<from; i++) {
			evaluation = RecordPredicateEvaluation.evaluate(inputRecordSignature1, record1, inputRecordSignature2, record2, conjPreds[i]);
			if(evaluation)
				break;
		}
						
		//If any of them does, we do not emit the record. Like this we will not generate
		//duplicates for the next operation
		if(!evaluation) {
			if(nested) {
				RecordList listNestedRecord = new RecordList();
				listNestedRecord.add(record2);
				record1.addField(listNestedRecord);
				collector.collect(record1);
			}
			else {
				record1.concatenate(record2);
				collector.collect(record1);
			}
		}
	}
	
}
