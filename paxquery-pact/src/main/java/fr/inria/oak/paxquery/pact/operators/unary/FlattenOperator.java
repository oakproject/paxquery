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
package fr.inria.oak.paxquery.pact.operators.unary;

import java.util.Iterator;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.metadata.MetadataTypesMapping;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;
import fr.inria.oak.paxquery.pact.operators.BaseMapOperator;


/**
 * Flatten operator in PACT.
 * 
 */
public class FlattenOperator extends BaseMapOperator {
	
	private int[] unnestPath;

	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		
		String unnestPathEncoded = parameters.getString(PACTOperatorsConfiguration.UNNEST_PATH_BINARY.toString(), null);
		byte[] unnestPathBytes = DatatypeConverter.parseBase64Binary(unnestPathEncoded);
		final int[] unnestPath = (int[]) SerializationUtils.deserialize(unnestPathBytes);
		this.unnestPath = unnestPath;
	}
	
	@Override
	public void map(Record record, Collector<Record> collector) {
		flatten(this.inputRecordsSignature, record, this.unnestPath, collector);
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param inputRecordsSignature
	 * @param record
	 * @param unnestPath
	 * @param collector
	 */
	public static void flatten(NestedMetadata inputRecordsSignature, Record record, int[] unnestPath, Collector<Record> collector) {
		RecordList outputRecords = flatten(inputRecordsSignature, record, unnestPath);
		
		for(Record output: outputRecords)
			collector.collect(output);
	}
	
	private static RecordList flatten(NestedMetadata inputRecordsSignature, Record record, int[] unnestPath) {
		// We initialize the list and insert the record
		RecordList outputRecords = new RecordList();
		outputRecords.add(record);
		
		return flatten(inputRecordsSignature, outputRecords, unnestPath, 0);
	}
	
	private static RecordList flatten(NestedMetadata inputRecordsSignature, RecordList nestedList, int[] unnestPath, int pos) {
		// We create the array of lists that will contain the flatten records
		RecordList[] outputRecords = new RecordList[nestedList.size()];
		
		// For each record...
		for(int k=0; k<nestedList.size(); k++) {
			// We initialize the list
			outputRecords[k] = new RecordList();
			Record newRecord = new Record();
			outputRecords[k].add(newRecord);
			
			// We get the record to flatten
			Record record = nestedList.get(k);
			
			int lengthRecord = record.getNumFields();
			int j = pos;
			for(int i=0; i<lengthRecord; i++) {
				// We compensate after unnesting
				while(j < unnestPath.length && i+pos > unnestPath[j])
					j++;
				
				// If we need to unnest this field in the record
				if(j < unnestPath.length && i+pos == unnestPath[j]) {
					RecordList list = flatten(inputRecordsSignature.getNestedChild(i), record.getField(i, RecordList.class), unnestPath, j+1);
					RecordList newOutputRecords = new RecordList();
					for(Record output: outputRecords[k]) {
						Iterator<Record> iterator = list.iterator();
						
						while(iterator.hasNext()) {
							Record copy = output.createCopy();
							RecordOperations.concatenate(copy,iterator.next());
							newOutputRecords.add(copy);
						}
					}
					outputRecords[k] = newOutputRecords;
					
					int numFieldsUnnested = list.get(0).getNumFields();
					i += numFieldsUnnested;
					lengthRecord += numFieldsUnnested - 1;
					
					j++;
				}
				else { // Otherwise we will just append the field to the records already in the list
					for(Record output: outputRecords[k])
						output.addField(record.getField(i, MetadataTypesMapping.getValueClass(inputRecordsSignature.getType(i))));
				}
			}
		}
		
		RecordList outputList = new RecordList();
		for(RecordList listForOneRecord: outputRecords)
			outputList.addAll(listForOneRecord);
		return outputList;
	}

}
