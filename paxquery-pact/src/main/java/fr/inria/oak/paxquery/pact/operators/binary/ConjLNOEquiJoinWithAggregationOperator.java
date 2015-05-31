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

import java.util.Iterator;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;


/**
 * Conjunctive left nested outer equi-join operator in PACT, plus aggregation on a field
 * of the nested record. The operator may project the right nested field.
 * 
 */
public class ConjLNOEquiJoinWithAggregationOperator extends ConjLNOEquiJoinOperator {

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
	public void coGroup(Iterator<Record> records1, Iterator<Record> records2, Collector<Record> collector) {
		conjLNOEquiJoinWithAggregation(this.inputRecordsSignature1, records1, this.inputRecordsSignature2, records2,
				this.nullRecord, this.aggregationColumn, this.aggregationType, this.excludeNestedField, collector);
	}
	
	/**
	 * 
	 * @param inputRecordsSignature1
	 * @param records1
	 * @param inputRecordsSignature2
	 * @param records2
	 * @param outer
	 * @param aggregationColumn
	 * @param aggregationType
	 * @param collector
	 */
	public static void conjLNOEquiJoinWithAggregation(NestedMetadata inputRecordsSignature1, Iterator<Record> records1, NestedMetadata inputRecordsSignature2, Iterator<Record> records2, 
			Record nullRecord, int aggregationColumn, AggregationType aggregationType, boolean excludeNestedField, Collector<Record> collector) {
		coGroupNestedJoin(inputRecordsSignature1, records1, inputRecordsSignature2, records2, 
				null, 0, true, nullRecord, false, aggregationColumn, aggregationType, excludeNestedField, collector);
	}

}
