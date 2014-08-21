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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.api.common.operators.FileDataSink;
import eu.stratosphere.api.java.record.io.FileOutputFormat;
import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePattern;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternEdge;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;

/**
 * Output format that generates XML results out of records based on
 * a construction tree pattern.
 *
 */
public class XmlConsTreePatternOutputFormat extends FileOutputFormat {
	
	private static final Log logger = LogFactory.getLog(XmlConsTreePatternOutputFormat.class);
	
	private Writer wrt;
	
	private NestedMetadata signature;
	
	private ConstructionTreePattern ctp;

	
	@Override
	public void configure(Configuration parameters) {
		super.configure(parameters);
				
		// read your own parameters
		String recordsSignatureEncoded = parameters.getString(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), null);
		byte[] recordsSignatureBytes = DatatypeConverter.parseBase64Binary(recordsSignatureEncoded);
		final NestedMetadata signature = (NestedMetadata) SerializationUtils.deserialize(recordsSignatureBytes);
		this.signature = signature;
		
		String ctpEncoded = parameters.getString(PACTOperatorsConfiguration.CTP_BINARY.toString(), null);
		byte[] ctpBytes = DatatypeConverter.parseBase64Binary(ctpEncoded);
		final ConstructionTreePattern ctp = (ConstructionTreePattern) SerializationUtils.deserialize(ctpBytes);
		this.ctp = ctp;
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
	}
	
	@Override
	public void close() throws IOException {
		this.wrt.close();
		super.close();
	}	

	@Override
	public void writeRecord(Record record) throws IOException {		
		RecordList recordList = new RecordList();
		recordList.add(record);
		StringBuilder sb = writeRecord(recordList, this.signature, new ConstructionTreePattern[] {this.ctp}, new AtomicBoolean[] {new AtomicBoolean()})[0];
		this.wrt.append(sb);
	}
	
	private StringBuilder[] writeRecord(RecordList listRecords, NestedMetadata signature, ConstructionTreePattern[] ctps, AtomicBoolean[] nullResults) throws IOException {
		StringBuilder[] result = new StringBuilder[ctps.length];
		for(int i=0; i<result.length; i++) {
			result[i] = new StringBuilder();
		}
		
		for(Record record: listRecords) { //For each record
			for(int i=0; i<result.length; i++) { //For each CTP
				ConstructionTreePattern ctp = ctps[i];
				ConstructionTreePatternNode ctpNode = ctp.getRoot();
				List<ConstructionTreePatternEdge> childrenEdges = ctp.getChildrenEdges().get(ctpNode);
				
				StringBuilder[] resultChildren = null;
				AtomicBoolean[] nullResultChildren = null;
				if(childrenEdges != null && childrenEdges.size() != 0) {
					//Create list CTPs from child nodes
					ConstructionTreePattern[] newCtps = new ConstructionTreePattern[childrenEdges.size()];
					for(int j=0; j<newCtps.length; j++) {
						newCtps[j] = ConstructionTreePattern.deepCopySubtree(childrenEdges.get(j).getChild());
					}
					//Create list records
					RecordList newListRecords;
					NestedMetadata newSignature;
					if(ctpNode.getContentType() == ContentType.VARIABLE_PATH) {
						newListRecords = record.getField(ctpNode.getVarPath().get(0), RecordList.class);
						newSignature = signature.getNestedChild(ctpNode.getVarPath().get(0));
					}
					else {
						newListRecords = listRecords;
						newSignature = signature;
					}
					//Holder for booleans for null results
					nullResultChildren = new AtomicBoolean[childrenEdges.size()];
					for(int j=0; j<nullResultChildren.length; j++) {
						nullResultChildren[j] = new AtomicBoolean();
					}
					//Obtain result children
					resultChildren = writeRecord(newListRecords, newSignature, newCtps, nullResultChildren);
				}
				
				//Construct the subtree starting at this node
				StringBuilder ctpNodeResult = new StringBuilder();
				boolean allNull = allNullUnderNode(nullResultChildren);
				if(!ctpNode.isOptional() || !allNull) {
					//
					if(ctpNode.getContentType() == ContentType.ELEMENT) {
						ctpNodeResult.append("<" + ctpNode.getValue());
						int k;
						for(k=0; 
								childrenEdges != null && k<childrenEdges.size() && childrenEdges.get(k).getChild().getContentType() == ContentType.ATTRIBUTE;
								k++) {
							ctpNodeResult.append(" " + resultChildren[k].toString());
						}
						if(childrenEdges == null || k == childrenEdges.size()) {
							ctpNodeResult.append("/>");
						}
						else {
							ctpNodeResult.append(">");
							for(; k<childrenEdges.size(); k++) {
								ctpNodeResult.append(" " + resultChildren[k].toString());
							}
							ctpNodeResult.append("</" + ctpNode.getValue() + ">");
						}
					}
					else if(ctpNode.getContentType() == ContentType.ATTRIBUTE) {
						ctpNodeResult.append(ctpNode.getValue() + "=\"" + resultChildren[0].toString() + "\"");
					}
					else if(ctpNode.getContentType() == ContentType.ELEMENT_VALUE) {
						ctpNodeResult.append(ctpNode.getValue());
					}
					else if(ctpNode.getContentType() == ContentType.ATTRIBUTE_VALUE) {
						ctpNodeResult.append(ctpNode.getValue());
					}
					else if(ctpNode.getContentType() == ContentType.VARIABLE_PATH &&
							(childrenEdges == null || childrenEdges.size() == 0)) {
						allNull = true;
						//Create content from the record
						List<Integer> varPath = ctpNode.getVarPath();
						if(varPath.size() == 1) {
							StringValue v = record.getField(varPath.get(0), StringValue.class);
							if(!v.getValue().equals("\0")) {
								ctpNodeResult.append(v);
							}
							else {
								allNull = false;
							}
						}
						else {
							RecordList list = record.getField(varPath.get(0), RecordList.class);
							for(int k=1;k<varPath.size()-1;k++) {
								RecordList newList = new RecordList();
								for(Record nestedRecord: list) {
									newList.addAll(nestedRecord.getField(varPath.get(k), RecordList.class));
								}
								list = newList;
							}
							for(Record nestedRecord: list) {
								StringValue v = nestedRecord.getField(varPath.get(varPath.size()-1), StringValue.class);
								if(!v.getValue().equals("\0")) {
									ctpNodeResult.append(v);
								}
								else {
									allNull = false;
								}
							}
						}
					}
					else { //childrenEdges != null
						//Copy content from children
						for(int k=0; k<resultChildren.length; k++) {
							ctpNodeResult.append(resultChildren[k].toString());
						}
					}
				}
				result[i] = ctpNodeResult;
				nullResults[i].set(allNull);
			}
		}
		
		return result;
	}
	
	//Return true if all elements in the array are true
	private boolean allNullUnderNode(AtomicBoolean[] nullResults) {
		if(nullResults == null) {
			return false;
		}

		for(AtomicBoolean nullResult: nullResults) {
			if(!nullResult.get()) {
				return false;
			}
		}
		
		return true;
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
		
		public T setConstructionTreePattern(ConstructionTreePattern ctp) {
			final String encodedCtp = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(ctp));
			this.config.setString(PACTOperatorsConfiguration.CTP_BINARY.toString(),
					String.valueOf(encodedCtp));
			
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
