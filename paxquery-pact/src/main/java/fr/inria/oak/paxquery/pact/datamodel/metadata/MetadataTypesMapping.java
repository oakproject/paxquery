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
package fr.inria.oak.paxquery.pact.datamodel.metadata;

import org.apache.flink.types.DoubleValue;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Key;
import org.apache.flink.types.LongValue;
import org.apache.flink.types.NullValue;
import org.apache.flink.types.StringValue;
import org.apache.flink.types.Value;

import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;


/**
 * Class representing the mapping from each metadata type to the corresponding PACT
 * data model type.
 *
 */
public class MetadataTypesMapping {
	
	/*
	public static Class<? extends Key> getKeyClass(MetadataTypes metadataTypes) {
		switch(metadataTypes) {
			case STRUCTURAL_ID:
				return StringValue.class;
			case ORDERED_ID:
				return StringValue.class;
			case UPDATE_ID:
				return StringValue.class;
			case NULL_ID:
				return NullValue.class;
			case TUPLE_TYPE:
				return null;
			case STRING_TYPE:
				return StringValue.class;
			case INTEGER_TYPE:
				return IntValue.class;
			case LONG_TYPE:
				return LongValue.class;
			case DOUBLE_TYPE:
				return DoubleValue.class;
			default:
				return null;
		}
	}
	*/
	public static Class<? extends Key<?>> getKeyClass(MetadataTypes metadataTypes) {
		switch(metadataTypes) {
			case STRUCTURAL_ID:
				return StringValue.class;
			case ORDERED_ID:
				return StringValue.class;
			case UPDATE_ID:
				return StringValue.class;
			case NULL_ID:
				return NullValue.class;
			case TUPLE_TYPE:
				return null;
			case STRING_TYPE:
				return StringValue.class;
			case INTEGER_TYPE:
				return IntValue.class;
			case LONG_TYPE:
				return LongValue.class;
			case DOUBLE_TYPE:
				return DoubleValue.class;
			default:
				return null;
		}
	}

	
	/**
	 * @param keyColumns
	 * @return
	 */
	public static Class<? extends Key>[] getKeyClasses(MetadataTypes[] keyColumns) {
		Class<? extends Key>[] keyClasses = new Class[keyColumns.length];
		
		for(int i=0; i<keyClasses.length; i++)
			keyClasses[i] = getKeyClass(keyColumns[i]);
		
		return keyClasses;
	}
	
	public static Class<? extends Value> getValueClass(MetadataTypes metadataTypes) {
		switch(metadataTypes) {
			case STRUCTURAL_ID:
				return StringValue.class;
			case ORDERED_ID:
				return StringValue.class;
			case UPDATE_ID:
				return StringValue.class;
			case NULL_ID:
				return NullValue.class;
			case TUPLE_TYPE:
				return RecordList.class;
			case STRING_TYPE:
				return StringValue.class;
			case INTEGER_TYPE:
				return IntValue.class;
			case LONG_TYPE:
				return LongValue.class;
			case DOUBLE_TYPE:
				return DoubleValue.class;
			default:
				return null;
		}

	}
	
}
