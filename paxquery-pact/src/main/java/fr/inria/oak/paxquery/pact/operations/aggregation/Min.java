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
package fr.inria.oak.paxquery.pact.operations.aggregation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.types.StringValue;
import org.apache.flink.types.Value;

import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;

/**
 * Min aggregation operation.
 *
 */
public class Min extends BaseAggregationOperation {
	
	private static final Log logger = LogFactory.getLog(Min.class);

	
	private final MetadataTypes type;
	
	private double minDouble;
	
	private int count;
	
	public Min(MetadataTypes type) {
		this.type = type;
		
		switch(this.type) {
			case STRING_TYPE:
				this.minDouble = Double.POSITIVE_INFINITY;
				break;
			default:
				logger.error("Min aggregation not supported for this data type!");
		}
		
		this.count = 0;
	}
	
	@Override
	public void aggregate(Value value) {
		switch(this.type) {
			case STRING_TYPE:
				final String stringValue = ((StringValue) value).getValue();
				if(!stringValue.equals("\0")) {
					final double newDouble = Double.parseDouble(stringValue);
					if(newDouble < this.minDouble)
						this.minDouble = newDouble;
					this.count++;
				}
				break;
			default:
				logger.error("Min aggregation not supported for this data type!");
		}
	}
	
	@Override
	public Value returnResult() {
		switch(this.type) {
			case STRING_TYPE:
				if(this.count == 0)
					return new StringValue("\0");
				return new StringValue(String.valueOf(this.minDouble));
			default:
				logger.error("Min aggregation not supported for this data type!");
				return null;
		}
	}
	
	@Override
	public void combineAggregation(Value value) {
		this.aggregate(value);
	}

}
