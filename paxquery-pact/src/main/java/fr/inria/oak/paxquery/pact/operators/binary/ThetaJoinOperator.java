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
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;

/**
 * Theta-join operator in PACT.
 * 
 */
public class ThetaJoinOperator extends BaseCrossJoinOperator {
	
	private BasePredicate pred;


	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		String predEncoded = parameters.getString(PACTOperatorsConfiguration.PRED_BINARY.toString(), null);
		byte[] predBytes = DatatypeConverter.parseBase64Binary(predEncoded);
		this.pred = (BasePredicate) SerializationUtils.deserialize(predBytes);
	}

	@Override
	public Record cross(Record record1, Record record2) {
		return thetaJoin(this.inputRecordsSignature1, record1, this.inputRecordsSignature2, record2,
				this.pred);
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
	 */
	public static Record thetaJoin(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, BasePredicate pred) {
		return crossJoin(inputRecordSignature1, record1, inputRecordSignature2, record2, pred, 
			false, null, false, false, -1, null, false);
	}

}
