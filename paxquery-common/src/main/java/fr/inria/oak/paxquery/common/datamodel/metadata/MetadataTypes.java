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
package fr.inria.oak.paxquery.common.datamodel.metadata;


/**
 * Enumerated type representing the possible types of metadata in a tuple.
 * 
 */
public enum MetadataTypes {
	STRUCTURAL_ID,
	ORDERED_ID,
	UPDATE_ID,
	NULL_ID,
    TUPLE_TYPE,
    STRING_TYPE,
    INTEGER_TYPE,
    LONG_TYPE,
    DOUBLE_TYPE;
	
	
	/**
	 * Gets a String and returns the corresponding member of the enumerated type.
	 * @return the type that corresponds to the value passed to the method
	 */
	public static MetadataTypes getTypeEnum(String stringType) {
		if (stringType.equals("STRUCTURAL_ID"))
			return STRUCTURAL_ID;
		else if (stringType.equals("UPDATE_ID"))
			return UPDATE_ID;
		else if (stringType.equals("NULL_ID"))
			return NULL_ID;
		else if (stringType.equals("TUPLE_TYPE"))
			return TUPLE_TYPE;
		else if (stringType.equals("STRING_TYPE"))
			return STRING_TYPE;
		else if (stringType.equals("INTEGER_TYPE"))
			return INTEGER_TYPE;
		else
			return null;
	}
	
	/**
	 * Returns the string representation of the current value of the enumerated type.
	 * @return string representation for the correspondent type
	 */
	@Override
	public String toString() {
		switch(this) {
			case STRUCTURAL_ID:
				return "STRUCTURAL_ID";
			case UPDATE_ID:
				return "UPDATE_ID";
			case NULL_ID:
				return "NULL_ID";
			case TUPLE_TYPE:
				return "TUPLE_TYPE";
			case STRING_TYPE:
				return "STRING_TYPE";
			case INTEGER_TYPE:
				return "INTEGER_TYPE";
			default:
				return null;
		}
	}
	
}
