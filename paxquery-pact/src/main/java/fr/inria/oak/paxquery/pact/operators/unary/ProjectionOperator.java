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

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operators.BaseMapOperator;


/**
 * Projection operator in PACT.
 * 
 */
public class ProjectionOperator extends BaseMapOperator {
	
	private int[] keepColumns;

	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);

		String keepColumnsEncoded = parameters.getString(PACTOperatorsConfiguration.KEEP_COLUMNS_BINARY.toString(), null);
		byte[] keepColumnsBytes = DatatypeConverter.parseBase64Binary(keepColumnsEncoded);
		final int[] keepColumns = (int[]) SerializationUtils.deserialize(keepColumnsBytes);
		this.keepColumns = keepColumns;
	}
	
	@Override
	public void map(Record record, Collector<Record> collector) {
		projection(this.inputRecordsSignature, record, this.keepColumns, collector);
	}
	
	/**
	 * 
	 * @param record
	 * @param projectedColumns
	 * @param collector
	 */
	public static void projection(NestedMetadata inputRecordSignature, Record record, int[] keepColumns, Collector<Record> collector) {
		RecordOperations.project(record, keepColumns);
		collector.collect(record);
	}
	
}
