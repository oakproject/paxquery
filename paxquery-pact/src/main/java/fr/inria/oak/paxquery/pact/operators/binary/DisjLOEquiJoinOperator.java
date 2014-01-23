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

import java.util.Iterator;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;

import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.types.Record;
import eu.stratosphere.util.Collector;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;

/**
 * Disjunctive left outer equi-join operator in PACT.
 * 
 */
public class DisjLOEquiJoinOperator extends BaseCoGroupJoinOperator {
	
	protected Record nullRecord;

	private DisjunctivePredicate pred;
	
	private int predNumber;
	

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		this.nullRecord = RecordOperations.createNullRecord(this.inputRecordsSignature2);
						
		String predEncoded = parameters.getString(PACTOperatorsConfiguration.PRED_BINARY.toString(), null);
		byte[] predBytes = DatatypeConverter.parseBase64Binary(predEncoded);
		this.pred = (DisjunctivePredicate) SerializationUtils.deserialize(predBytes);
		
		this.predNumber = parameters.getInteger(PACTOperatorsConfiguration.PRED_INT.toString(), -1);
	}
	
	@Override
	public void coGroup(Iterator<Record> records1, Iterator<Record> records2, Collector<Record> collector) {
		disjLOEquiJoin(this.inputRecordsSignature1, records1, this.inputRecordsSignature2, records2, 
				this.nullRecord, this.pred, this.predNumber, collector);			
	}
	
	/**
	 * 
	 * @param inputRecordsSignature1
	 * @param records1
	 * @param inputRecordsSignature2
	 * @param records2
	 * @param pred
	 * @param from
	 * @param outer
	 * @param addMark
	 * @param collector
	 */
	public static void disjLOEquiJoin(NestedMetadata inputRecordsSignature1, Iterator<Record> records1, NestedMetadata inputRecordsSignature2, Iterator<Record> records2, 
			Record nullRecord, DisjunctivePredicate pred, int from, Collector<Record> collector) {
		coGroupJoin(inputRecordsSignature1, records1, inputRecordsSignature2, records2, 
				pred, from, true, nullRecord, true, collector);
	}

}
