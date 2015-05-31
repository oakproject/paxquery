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
package fr.inria.oak.paxquery.pact.operators;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.api.java.record.functions.MapFunction;
import org.apache.flink.configuration.Configuration;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;


/**
 * General Map class to be extended by PACT operators.
 * 
 */
public abstract class BaseMapOperator extends MapFunction {
	
	protected NestedMetadata inputRecordsSignature;
	
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		String inputRecordsSignatureEncoded = parameters.getString(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), null);
		byte[] inputRecordsSignatureBytes = DatatypeConverter.parseBase64Binary(inputRecordsSignatureEncoded);
		final NestedMetadata inputRecordsSignature = (NestedMetadata) SerializationUtils.deserialize(inputRecordsSignatureBytes);
		this.inputRecordsSignature = inputRecordsSignature;
	}

}
