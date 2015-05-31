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

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;


/**
 * Left nested outer theta-join operator in PACT, plus aggregation on a field
 * of the nested record. The operator may project the right nested field.
 * 
 */
public class ThetaLNOJoinWithAggregationOperator extends ThetaLNOJoinOperator {
	
	private int aggregationColumn;

	private AggregationType aggregationType;
	
	private boolean excludeNestedField;
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
			
		this.aggregationColumn = parameters.getInteger(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), -1);

		String aggregationTypeEncoded = parameters.getString(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), null);
		byte[] aggregationTypeBytes = DatatypeConverter.parseBase64Binary(aggregationTypeEncoded);
		final AggregationType aggregationType = (AggregationType) SerializationUtils.deserialize(aggregationTypeBytes);
		this.aggregationType = aggregationType;
		
		this.excludeNestedField = parameters.getBoolean(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), false);
	}

	@Override
	public Record cross(Record record1, Record record2) {
		return thetaLNOJoinWithAggregation(this.inputRecordsSignature1, record1, this.inputRecordsSignature2, record2,
				this.pred, this.nullRecord, this.aggregationColumn, this.aggregationType, this.excludeNestedField);
	}
	
	/**
	 * 
	 * @param inputRecordSignature1
	 * @param record1
	 * @param inputRecordSignature2
	 * @param record2
	 * @param pred
	 * @param aggregationColumn
	 * @param aggregationType
	 * @param collector
	 */
	public static Record thetaLNOJoinWithAggregation(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, BasePredicate pred, 
			Record nullRecord, int aggregationColumn, AggregationType aggregationType, boolean excludeNestedField) {
		return crossJoin(inputRecordSignature1, record1, inputRecordSignature2, record2, pred, 
				true, nullRecord, true, true, aggregationColumn, aggregationType, excludeNestedField);
	}

}
