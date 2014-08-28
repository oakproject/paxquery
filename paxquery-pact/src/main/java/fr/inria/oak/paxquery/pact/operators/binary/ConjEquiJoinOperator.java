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

import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operators.BaseMatchOperator;


/**
 * Conjunctive equi-join operator in PACT.
 * 
 */
public class ConjEquiJoinOperator extends BaseMatchOperator {
	

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
	}
	
	@Override
	public void join(Record record1, Record record2, Collector<Record> collector) {
		RecordOperations.concatenate(record1,record2);
		collector.collect(record1);
	}

}
