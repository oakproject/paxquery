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
package fr.inria.oak.paxquery.pact.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.api.java.record.io.FileOutputFormat;
import eu.stratosphere.api.java.record.operators.FileDataSink;
import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;

/**
 * Output format that generates XML results out of records.
 *
 */
@Deprecated
public class XmlOutputFormat extends FileOutputFormat {
	
	private static final Log logger = LogFactory.getLog(XmlOutputFormat.class);
	
	private Writer wrt;
	
	private NestedMetadata signature;
	
	private ApplyConstruct apply;

	
	@Override
	public void configure(Configuration parameters) {
		super.configure(parameters);
				
		// read your own parameters
		String recordsSignatureEncoded = parameters.getString(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), null);
		byte[] recordsSignatureBytes = DatatypeConverter.parseBase64Binary(recordsSignatureEncoded);
		final NestedMetadata signature = (NestedMetadata) SerializationUtils.deserialize(recordsSignatureBytes);
		this.signature = signature;
		
		String applyConstructEncoded = parameters.getString(PACTOperatorsConfiguration.APPLY_CONSTRUCT_BINARY.toString(), null);
		byte[] applyConstructBytes = DatatypeConverter.parseBase64Binary(applyConstructEncoded);
		final ApplyConstruct apply = (ApplyConstruct) SerializationUtils.deserialize(applyConstructBytes);
		this.apply = apply;
	}
	
	/*
	@Override
	public void open(int taskNumber) throws IOException {
		super.open(taskNumber);
				
		this.wrt = new OutputStreamWriter(new BufferedOutputStream(this.stream, 4096));
		this.wrt.write(this.apply.getBefore());
	}
	*/
	@Override
	public void open(int taskNumber, int numTasks) throws IOException {
		super.open(taskNumber, numTasks);
				
		this.wrt = new OutputStreamWriter(new BufferedOutputStream(this.stream, 4096));
		this.wrt.write(this.apply.getBefore());
	}
	
	@Override
	public void close() throws IOException {
		this.wrt.write(this.apply.getAfter());
		this.wrt.close();
		super.close();
	}	

	@Override
	public void writeRecord(Record record) throws IOException {		
		boolean allNull = true;
		
		StringBuilder sb = new StringBuilder();		

		int posFields;
		int posNested = 0;
		for(posFields=0; posFields<this.apply.getFields().length; posFields++) {
			sb.append(this.apply.getEach()[posFields]);
			
			int field = this.apply.getFields()[posFields];
			if(this.signature.getType(field).equals(MetadataTypes.TUPLE_TYPE)) {
				allNull &= this.writeRecord(sb, record.getField(field, RecordList.class),
						this.signature.getNestedChild(field),
						this.apply.getNested()[posNested++]);
			}
			else {
				final StringValue v = record.getField(field, StringValue.class);
				if(!v.getValue().equals("\0")) {
					allNull = false;
					sb.append(v.getValue());
				}
			}
		}
		while(posFields<this.apply.getEach().length)
			sb.append(this.apply.getEach()[posFields++]);
		
		if(!allNull) 
			this.wrt.write(sb.toString());
	}
	
	private boolean writeRecord(StringBuilder sb, RecordList listRecords, NestedMetadata nestedMetadata, ApplyConstruct apply) throws IOException {
		boolean allNull = true;
		
		int pos = sb.length();
		
		sb.append(apply.getBefore());
		
		for(Record record: listRecords) {
			int posFields;
			int posNested = 0;
			for(posFields=0; posFields<apply.getFields().length; posFields++) {
				sb.append(apply.getEach()[posFields]);
				
				int field = apply.getFields()[posFields];
				if(nestedMetadata.getType(field).equals(MetadataTypes.TUPLE_TYPE)) {
					allNull &= this.writeRecord(sb, record.getField(field, RecordList.class),
							nestedMetadata.getNestedChild(field),
							apply.getNested()[posNested++]);
				}
				else {
					final StringValue v = record.getField(field, StringValue.class);
					if(!v.getValue().equals("\0")) {
						allNull = false;
						sb.append(v.getValue());
					}
				}
			}
			while(posFields<apply.getEach().length)
				sb.append(apply.getEach()[posFields++]);
		}

		sb.append(apply.getAfter());
		
		if(allNull)
			sb.setLength(pos);

		return allNull;
	}
	
	// ============================================================================================
	
	/**
	 * Creates a configuration builder that can be used to set the input format's parameters to the config in a fluent
	 * fashion.
	 * 
	 * @return A config builder for setting parameters.
	 */
	public static ConfigBuilder configureRecordFormat(FileDataSink target) {
		return new ConfigBuilder(target.getParameters());
	}
	
	/**
	 * Abstract builder used to set parameters to the input format's configuration in a fluent way.
	 */
	protected static abstract class AbstractConfigBuilder<T> extends FileOutputFormat.AbstractConfigBuilder<T>
	{		
		// --------------------------------------------------------------------
		
		/**
		 * Creates a new builder for the given configuration.
		 * 
		 * @param targetConfig The configuration into which the parameters will be written.
		 */
		protected AbstractConfigBuilder(Configuration config) {
			super(config);
		}
		
		// --------------------------------------------------------------------
		
		public T setSignature(NestedMetadata signature) {
			final String encodedSignature = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(signature));
			this.config.setString(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(),
					String.valueOf(encodedSignature));

			@SuppressWarnings("unchecked")
			T ret = (T) this;
			return ret;
		}
		
		public T setApply(ApplyConstruct apply) {
			final String encodedApplyConstruct = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(apply));
			this.config.setString(PACTOperatorsConfiguration.APPLY_CONSTRUCT_BINARY.toString(),
					String.valueOf(encodedApplyConstruct));
			
			@SuppressWarnings("unchecked")
			T ret = (T) this;
			return ret;
		}
	}
	
	/**
	 * A builder used to set parameters to the input format's configuration in a fluent way.
	 */
	public static final class ConfigBuilder extends AbstractConfigBuilder<ConfigBuilder>
	{
		/**
		 * Creates a new builder for the given configuration.
		 * 
		 * @param targetConfig The configuration into which the parameters will be written.
		 */
		protected ConfigBuilder(Configuration targetConfig) {
			super(targetConfig);
		}
		
	}
}
