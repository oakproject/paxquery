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
package fr.inria.oak.paxquery.pact.operations;

import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;
import org.apache.flink.types.Value;

import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;


/**
 * Generic operations over records.
 *
 */
public class RecordOperations {
	
	public static void concatenate(Record record1, Record record2) {
		int[] copyFrom = new int[record2.getNumFields()];
		int[] copyTo = new int[record2.getNumFields()];
		for(int i=0;i<record2.getNumFields();i++) {
			copyFrom[i] = i;
			copyTo[i] = record1.getNumFields() + i;
		}
		record1.copyFrom(record2, copyFrom, copyTo);
	}
	
	public static void project(Record record, int[] keepColumns) {
		int j=0, k=0;
		// For each column that we need to project...
		int initialNumberFields = record.getNumFields();
		for(int i=0; i<initialNumberFields; i++) {
			if(j >= keepColumns.length || i != keepColumns[j]) {
				record.removeField(i-k);
				k++;
			}
			else
				j++;
		}
		record.updateBinaryRepresenation();
	}
	
	public static Record createNullRecord(NestedMetadata signature) {
		Record nullRecord = new Record();
		addNullRecord(nullRecord, signature);	
		return nullRecord;
	}
	
	public static void addNullRecord(Record record, NestedMetadata signature) {		
		for(int i=0; i<signature.getColNo(); i++) {
			if(signature.getCompleteMetadata()[i] == MetadataTypes.TUPLE_TYPE) {
				RecordList list = new RecordList();
				list.add(createNullRecord(signature.getNestedChild(i)));
				record.addField(list);
			}
			else
				record.addField(createNullField());
		}
	}
	
	public static Value createNullField() {
		return new StringValue("\0");
	}
	
}
