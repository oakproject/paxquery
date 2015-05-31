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

import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;

import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operators.BaseCrossOperator;

/**
 * Cartesian product operator in PACT.
 * 
 */
public class CartesianProductOperator extends BaseCrossOperator {


	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
	}
	
	@Override
	public Record cross(Record record1, Record record2) {
		RecordOperations.concatenate(record1,record2);
		return record1;
	}
	
}
