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
package fr.inria.oak.paxquery.pact.operations.aggregation;

import org.apache.flink.types.StringValue;
import org.apache.flink.types.Value;

/**
 * Count aggregation operation.
 *
 */
public class Count extends BaseAggregationOperation {
	
	
	private int count;
	
	public Count() {
		this.count = 0;
	}
	
	@Override
	public void aggregate(Value value) {
		final String stringValue = ((StringValue) value).getValue();
		if(!stringValue.equals("\0"))
			this.count++;
	}
	
	@Override
	public Value returnResult() {
		return new StringValue(String.valueOf(this.count));
	}
	
	@Override
	public void combineAggregation(Value value) {
		final String stringValue = ((StringValue) value).getValue();
		if(!stringValue.equals("\0"))
			this.count += Integer.parseInt(stringValue);		
	}

}
