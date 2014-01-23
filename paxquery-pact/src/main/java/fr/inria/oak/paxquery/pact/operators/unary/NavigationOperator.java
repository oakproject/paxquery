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

import java.io.StringReader;
import java.util.Iterator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.configuration.Configuration;
import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import eu.stratosphere.util.Collector;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePattern;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePatternUtils;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.operations.xml.navigation.SingleDocumentExtractor;
import fr.inria.oak.paxquery.pact.operators.BaseMapOperator;


/**
 * Tree pattern navigation operator in PACT.
 * 
 */
public class NavigationOperator extends BaseMapOperator {
	
	private static final Log logger = LogFactory.getLog(NavigationOperator.class);


	private int column;
	
	private TreePattern navigationTreePattern;
	

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		final int column = parameters.getInteger(PACTOperatorsConfiguration.NAVIGATION_COLUMN_INT.toString(), -1);
		this.column = column;
		
		final TreePattern ntp = TreePatternUtils.getTreePatternFromString(
				parameters.getString(PACTOperatorsConfiguration.NTP_STRING.toString(), null),
				"queryTest");
		this.navigationTreePattern = ntp;
	}
	
	@Override
	public void map(Record record, Collector<Record> collector) {
		navigation(this.inputRecordsSignature, record, this.column, this.navigationTreePattern, collector);
	}

	/**
	 * 
	 * @param record
	 * @param treePattern
	 * @param collector
	 */
	public static void navigation(NestedMetadata inputRecordSignature, Record record, int column, TreePattern navigationTreePattern, Collector<Record> collector) {
		XMLInputFactory factory = XMLInputFactory.newInstance();

		try {
			StringValue value = record.getField(column, StringValue.class);
			XMLStreamReader streamReader = factory.createXMLStreamReader(new StringReader(value.getValue()));

			SingleDocumentExtractor extractor = new SingleDocumentExtractor(
					navigationTreePattern,
					streamReader);
		
			while(streamReader.hasNext()) {
			    streamReader.next();
			    if(streamReader.getEventType() == XMLStreamConstants.START_ELEMENT) {			    	
			    	extractor.startElement();
			    }
			    else if(streamReader.getEventType() == XMLStreamConstants.END_ELEMENT) {
			    	extractor.endElement();
			    }
			    else if(streamReader.getEventType() == XMLStreamConstants.CHARACTERS) {
			    	extractor.characters();
			    }
			    			    
		    	if(extractor.getRecords().size() != 0) {
		    		Iterator<Record> pactRecordsIterator = extractor.getRecords().iterator();
		    		while(pactRecordsIterator.hasNext()) {
						Record originalRecord = record.createCopy();
						originalRecord.concatenate(pactRecordsIterator.next());
		    			collector.collect(originalRecord);
		    		}

		    		extractor.getRecords().clear();
		    	}
			}

		} catch (XMLStreamException e) {
			logger.error("XMLStreamException", e);
		}
	}
}
