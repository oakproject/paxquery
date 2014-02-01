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

import java.io.IOException;
import java.util.Iterator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.api.common.io.statistics.BaseStatistics;
import eu.stratosphere.api.common.operators.FileDataSource;
import eu.stratosphere.api.java.record.io.FileInputFormat;
import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.core.fs.FileInputSplit;
import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePattern;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePatternUtils;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operations.xml.navigation.SingleDocumentExtractor;


/**
 * Input format that takes a collection of XML documents, and for each document generates
 * records based on the embeddings of a navigation tree pattern in the document.
 *
 */
public class XmlNavInputFormat extends FileInputFormat {
	
	private static final Log logger = LogFactory.getLog(XmlNavInputFormat.class);
	
	
	private String documentID;
	
	private boolean reachedEnd;
	
	private TreePattern navigationTreePattern;
	
	private XMLStreamReader streamReader;
		
	private SingleDocumentExtractor extractor;
		
	private boolean attachDocumentID;
	
	private Iterator<Record> pactRecordsIterator;
		
	
	@Override
	public void configure(Configuration parameters) {
		super.configure(parameters);
		
		this.init();
		
		// read your own parameters
		final TreePattern ntp = TreePatternUtils.getTreePatternFromString(
				parameters.getString(PACTOperatorsConfiguration.NTP_STRING.toString(), null),
				"NavigationTreePattern");
		this.navigationTreePattern = ntp;
		
		final boolean attachDocumentID = parameters.getBoolean(PACTOperatorsConfiguration.ATTACH_DOCUMENTID_BOOLEAN.toString(), false);
		this.attachDocumentID = attachDocumentID;
	}
	
	private void init() {
		this.reachedEnd = false;
	}
	

	@Override
	public FileBaseStatistics getStatistics(BaseStatistics cachedStatistics) {
		//TODO: How to gather statistics?
		return null;
	}
	
	@Override
	public boolean reachedEnd() throws IOException {
		if(this.reachedEnd) {
			this.init();
			return true;
		}
		return false;
	}
	
	@Override
	public void open(FileInputSplit split) throws IOException {
		super.open(split);
		
		this.documentID = split.getPath().toString();
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			this.streamReader = factory.createXMLStreamReader(this.stream);
		} catch (XMLStreamException e) {
			logger.error("XMLStreamException", e);
		}

		this.extractor = new SingleDocumentExtractor(
				this.navigationTreePattern,
				this.streamReader);
	}

	@Override
	public boolean nextRecord(Record record) throws IOException {
    	if(this.pactRecordsIterator!=null) {
    		if(this.pactRecordsIterator.hasNext()) {
    			if(this.attachDocumentID) {
    				record.addField(new StringValue(this.documentID));
    				RecordOperations.concatenate(record,this.pactRecordsIterator.next());
    			}
    			else
    				this.pactRecordsIterator.next().copyTo(record);
    			
    			return true;
    		}

    		this.pactRecordsIterator = null;
    		this.extractor.getRecords().clear();
    	}
		
		try {
			while(this.streamReader.hasNext()) {
			    this.streamReader.next();
			    if(this.streamReader.getEventType() == XMLStreamConstants.START_ELEMENT) {			    	
			    	this.extractor.startElement();
			    }
			    else if(this.streamReader.getEventType() == XMLStreamConstants.END_ELEMENT) {
			    	this.extractor.endElement();
			    }
			    else if(this.streamReader.getEventType() == XMLStreamConstants.CHARACTERS) {
			    	this.extractor.characters();
			    }
			    else if(this.streamReader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
			    	this.reachedEnd = true;
			    }
			    			    
		    	if(this.extractor.getRecords().size() != 0) {
		    		this.pactRecordsIterator = this.extractor.getRecords().iterator();
	    			if(this.attachDocumentID) {
	    				record.addField(new StringValue(this.documentID));
	    				RecordOperations.concatenate(record,this.pactRecordsIterator.next());
	    			}
	    			else
	    				this.pactRecordsIterator.next().copyTo(record);

		    		return true;
		    	}

			}
						
			return false;
		} catch (XMLStreamException e) {
			logger.error("XMLStreamException", e);
			return false;
		}
	}
	
	
	
	// ============================================================================================
	
	/**
	 * Creates a configuration builder that can be used to set the input format's parameters to the config in a fluent
	 * fashion.
	 * 
	 * @return A config builder for setting parameters.
	 */
	public static ConfigBuilder configureXmlNavInputFormat(FileDataSource target) {
		return new ConfigBuilder(target.getParameters());
	}
	
	/**
	 * Abstract builder used to set parameters to the input format's configuration in a fluent way.
	 */
	protected static abstract class AbstractConfigBuilder<T>
	{
		/**
		 * The configuration into which the parameters will be written.
		 */
		protected final Configuration config;
		
		// --------------------------------------------------------------------
		
		/**
		 * Creates a new builder for the given configuration.
		 * 
		 * @param targetConfig The configuration into which the parameters will be written.
		 */
		protected AbstractConfigBuilder(Configuration targetConfig) {
			this.config = targetConfig;
		}
		
		// --------------------------------------------------------------------
		
		public T setNavigationTreePattern(TreePattern ntp) {
			this.config.setString(PACTOperatorsConfiguration.NTP_STRING.toString(),
					TreePatternUtils.getParsableStringFromTreePattern(ntp));
			@SuppressWarnings("unchecked")
			T ret = (T) this;
			return ret;
		}
		
		public T setAttachDocumentID(boolean attachDocumentID) {
			this.config.setBoolean(PACTOperatorsConfiguration.ATTACH_DOCUMENTID_BOOLEAN.toString(), attachDocumentID);
			@SuppressWarnings("unchecked")
			T ret = (T) this;
			return ret;
		}
	}
	
	/**
	 * A builder used to set parameters to the input format's configuration in a fluent way.
	 */
	public static class ConfigBuilder extends AbstractConfigBuilder<ConfigBuilder>
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
