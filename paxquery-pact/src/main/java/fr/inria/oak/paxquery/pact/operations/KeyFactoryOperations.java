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
package fr.inria.oak.paxquery.pact.operations;

import org.apache.flink.api.java.record.operators.CoGroupOperator;
import org.apache.flink.api.java.record.operators.JoinOperator;
import org.apache.flink.api.java.record.operators.ReduceOperator;
import org.apache.flink.types.Key;


/**
 * Class that encapsulates key factory operations for PACT operators.
 *
 */
public class KeyFactoryOperations {
	/*
	public static void addKey(ReduceOperator.Builder builder, Class<? extends Key> keyClass, int keyIndex) {
		builder.keyField(keyClass, keyIndex);
	}

	public static void addKey(CoGroupOperator.Builder builder, Class<? extends Key> keyClass, int keyIndex1, int keyIndex2) {
		builder.keyField(keyClass, keyIndex1, keyIndex2);
	}

	public static void addKey(JoinOperator.Builder builder, Class<? extends Key> keyClass, int keyIndex1, int keyIndex2) {
		builder.keyField(keyClass, keyIndex1, keyIndex2);
	}
	*/
	
	public static void addKey(ReduceOperator.Builder builder, Class<? extends Key<?>> keyClass, int keyIndex) {
		builder.keyField(keyClass, keyIndex);
	}

	public static void addKey(CoGroupOperator.Builder builder, Class<? extends Key<?>> keyClass, int keyIndex1, int keyIndex2) {
		builder.keyField(keyClass, keyIndex1, keyIndex2);
	}

	public static void addKey(JoinOperator.Builder builder, Class<? extends Key<?>> keyClass, int keyIndex1, int keyIndex2) {
		builder.keyField(keyClass, keyIndex1, keyIndex2);
	}	
}
