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
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;

/**
 * Left nested outer theta-join operator in PACT.
 * 
 */
public class ThetaLNOJoinOperator extends BaseCrossJoinOperator {
	
	protected Record nullRecord;

	protected BasePredicate pred;


	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);

		this.nullRecord = RecordOperations.createNullRecord(this.inputRecordsSignature2);
			
		String predEncoded = parameters.getString(PACTOperatorsConfiguration.PRED_BINARY.toString(), null);
		byte[] predBytes = DatatypeConverter.parseBase64Binary(predEncoded);
		this.pred = (BasePredicate) SerializationUtils.deserialize(predBytes);
	}

	@Override
	public void cross(Record record1, Record record2, Collector<Record> collector) {
		thetaLNOJoin(this.inputRecordsSignature1, record1, this.inputRecordsSignature2, record2,
				this.pred, this.nullRecord, collector);
	}

	/**
	 * 
	 * 
	 * @param inputRecordSignature1
	 * @param record1
	 * @param inputRecordSignature2
	 * @param record2
	 * @param pred
	 * @param outer
	 * @param nested
	 * @param addMark
	 * @param collector
	 */
	public static void thetaLNOJoin(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, BasePredicate pred, 
			Record nullRecord, Collector<Record> collector) {
		crossJoin(inputRecordSignature1, record1, inputRecordSignature2, record2, pred, 
				true, nullRecord, true, true, -1, null, false, collector);
	}
	
}
