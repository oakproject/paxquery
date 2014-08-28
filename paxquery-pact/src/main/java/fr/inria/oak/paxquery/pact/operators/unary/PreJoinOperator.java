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

import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.operators.BaseReduceOperator;


/**
 * Pre-processing join operator in PACT. It works tagging the outer relation of the join. In the XML algebra
 * it is not necessary.
 * 
 */
public class PreJoinOperator extends BaseReduceOperator {
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
	}

	@Override
	public void reduce(Iterator<Record> records, Collector<Record> collector) {
		preJoin(this.inputRecordsSignature, records, collector);
	}
	
	/**
	 * 
	 * @param inputRecordsSignature
	 * @param records
	 * @param collector
	 */
	public static void preJoin(NestedMetadata inputRecordsSignature, Iterator<Record> records, Collector<Record> collector) {
		// Initialize counter
		int i = 0;
		
		// For each record, we attach the value of the counter and we output the record
		while(records.hasNext()) {
			Record record = records.next();
			record.addField(new IntValue(i));
			collector.collect(record);
			i++;
		}
	}

}
