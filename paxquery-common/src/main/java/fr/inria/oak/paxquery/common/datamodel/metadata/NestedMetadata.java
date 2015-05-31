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
package fr.inria.oak.paxquery.common.datamodel.metadata;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;

/**
 * Nested result set metadata.
 * 
 */
public class NestedMetadata implements Serializable {
	
	private static final Log logger = LogFactory.getLog(NestedMetadata.class);
	
	
	/**
	 * the number of columns
	 */
	public int colNo;

	/**
	 * for each column state the actual data type of the column
	 */
	public MetadataTypes[] types;

	NestedMetadata[] nestedChildren;

	int stringNo;
	int prePostIDsNo;
	int ordpathIDsNo;
	int integerIDsNo;
	int nestedNo;


	/**
	 * The Strings which constitute the column names
	 */
	String[] colNames;


	/**
	 * To be used for un-nested RSMDs
	 * 
	 * @param colNo
	 * @param types
	 */	
	public NestedMetadata(int colNo, MetadataTypes[] types) throws PAXQueryExecutionException {
		this.colNo = colNo;
		this.types = types;
		this.colNames = new String[colNo];
		int iNested = 0;
		for (int i = 0; i < types.length; i++) {
			if (types[i] == MetadataTypes.TUPLE_TYPE) {
				iNested++;
			}
		}
		
		this.nestedChildren = new NestedMetadata[iNested];
		countTypes(types);
	}

	// deep copy
	/*
	 * This constructor lacks of the init for statistic
	 * and it is used only for Logical Operator
	 */
	public NestedMetadata(MetadataTypes[] types, NestedMetadata[] newNestedChildren) throws PAXQueryExecutionException {
		this.colNo = types.length;
		// sometimes there are 0 columns, for non-contributing nodes. Hopefully
		// not a problem...
		// if (this.colNo == 0){
		//  	throw new XMLStratosphereExecutionException("O columns");
		// }
		this.types = new MetadataTypes[types.length];
		for (int i = 0; i < this.types.length; i++) {
			this.types[i] = types[i];
		}
		
		this.nestedChildren = new NestedMetadata[newNestedChildren.length];
		
		for (int i = 0; i < nestedChildren.length; i++) {
			this.nestedChildren[i] =
				new NestedMetadata(
					newNestedChildren[i].types,
					newNestedChildren[i].nestedChildren);
		}
		this.colNames = new String[colNo];
		countTypes(types);
		this.nestedNo = nestedChildren.length;
	}

	/**
	 * Construct a new NRSMD object
	 * 
	 * @param colNo -
	 *            no of columns in the tuple
	 * @param types -
	 *            for each column the code of the data-type describing that
	 *            field
	 * 
	 * @param colNames -
	 *            colum names
	 * @param nestedChildren -
	 *            nested metadata children
	 */
	public NestedMetadata(int colNo, MetadataTypes[] types, String[] colNames, NestedMetadata[] nestedChildren) throws PAXQueryExecutionException {
		this.colNo = colNo;
		this.types = types;
		this.colNames = colNames;
		this.nestedChildren = nestedChildren;
		countTypes(types);
		this.nestedNo = nestedChildren.length;
	}

	/**
	 * Computes the metadata of a tuple that only holds the direct attributes
	 * of this one (erasing all nested children).
	 * 
	 * @return @throws
	 *         XMLStratosphereExecutionException
	 */
	public NestedMetadata flatProjection() throws PAXQueryExecutionException {
		int k = this.colNo - this.nestedChildren.length;
		MetadataTypes[] newTypes = new MetadataTypes[k];
		//System.out.println("There will be " + k + " columns in the result");
		String[] newNames = new String[k];
		int j = 0;
		for (int i = 0; i < this.colNo; i++) {
			if (types[i] != MetadataTypes.TUPLE_TYPE) {
				newTypes[j] = types[i];
				//System.out.println("Type at " + j + " " +
				// TupleMetadataType.decodeConstant(newTypes[j]));
				newNames[j] = colNames[i];
				j++;
			}
		}
		return new NestedMetadata(k, newTypes, newNames, new NestedMetadata[0]);
	}



	public final String[] getColNames() {
		return colNames;
	}

	private void countTypes(MetadataTypes[] types) throws PAXQueryExecutionException {
		//System.out.println("Counting metadata types");
		this.stringNo = 0;
		this.prePostIDsNo = 0;
		this.ordpathIDsNo = 0;
		this.integerIDsNo = 0;
		this.nestedNo = 0;
		for (int i = 0; i < types.length; i++) {
			switch (types[i]) {
				case STRING_TYPE :
					this.stringNo++;
					//System.out.println("Counting: at " + i + " string");
					break;
				case INTEGER_TYPE :
					this.integerIDsNo++;
					break;
				case UPDATE_ID :
					this.ordpathIDsNo++;
					break;
				case STRUCTURAL_ID :
					this.prePostIDsNo++;
					break;
				case TUPLE_TYPE :
					this.nestedNo++;
					break;
				default :
					throw new PAXQueryExecutionException(
						"Unknown type at " + i + ": " + types[i]);
			}
		}
	}

	public void display() {
		StringBuffer sb = new StringBuffer();
		recDisplay(sb);
		logger.info(sb);
	}

	public void recDisplay(StringBuffer sb) {
		sb.append("(");
		if (this.types == null) {
			throw new Error("Null types");
		}
		int iNested = 0;
		for (int i = 0; i < this.types.length; i++) {
			//System.out.println(i + ": " + TupleMetadataType.decodeConstant(types[i])
			// + " ");
			if (i > 0) {
				sb.append(" ");
			}
			if (this.types[i] != MetadataTypes.TUPLE_TYPE) {
				sb.append(i + ":" + this.types[i]);
			} else {
				sb.append("[");
				//System.out.println("Out of " + nestedChildren.length + "
				// children MDs, looking at " +
				//		iNested);
				if (this.nestedChildren[iNested] == null) {
					sb.append("null");
				} else {
					this.nestedChildren[iNested].recDisplay(sb);
				}
				iNested++;
				sb.append("]");
			}
		}
		sb.append(")");
	}

	/**
	 * @param k
	 *            the index in the total columns
	 * @return the nested child metadata at that index, if any
	 */
	public NestedMetadata getNestedChild(int k) {
		//System.out.println("\n\nNRSMD: getting nested child at " + k + 
			//	" out of " + this.nestedNo);
		int iNested = 0;
		for (int i = 0; i < this.types.length; i++) {
			if (this.types[i] == MetadataTypes.TUPLE_TYPE) {
				if (i == k)
					return this.nestedChildren[iNested];
				iNested++;
			}
		}
		throw new Error("No nested child at " + k);
	}

	void setNestedChild(int k, NestedMetadata nestedMetadata) {
		int iNested = 0;
		for (int i = 0; i < this.types.length; i++) {
			if (this.types[i] == MetadataTypes.TUPLE_TYPE) {
				if (i == k)
					this.nestedChildren[iNested] = nestedMetadata;
				iNested++;
			}
		}
		throw new Error("No nested child at " + k);
	}

	public MetadataTypes[] getCompleteMetadata() {
		return types;
	}
	
	/**
	 * @param keyColumns
	 * @return
	 */
	public MetadataTypes[] getTypes(int[] keyColumns) {
		MetadataTypes[] types = new MetadataTypes[keyColumns.length];
		
		for(int i=0; i<types.length; i++)
			types[i] = getType(keyColumns[i]);
		
		return types;
	}
	
	/**
	 * @param keyColumns
	 * @return
	 */
	public MetadataTypes getType(int keyColumn) {
		return this.types[keyColumn];
	}
	
	/**
	 * @param idx:
	 *            a sequence of integer indexes, which represent a navigation
	 *            path within the tuple
	 * @return the type found at the end of that navigation path.
	 */
	public MetadataTypes getNestedType(int[] idx) {
		return recGetNestedType(idx, 0);
	}
	
	private MetadataTypes recGetNestedType(int[] idx, int from) {
		if (from == idx.length - 1) {
			return this.types[idx[from]];
		}
		
		return this.getNestedChild(idx[from]).recGetNestedType(idx, from + 1);
	}
	
	public int getColNo() {
		return colNo;
	}

	public boolean equals(Object o) {
		NestedMetadata other = null;
		try {
			other = (NestedMetadata) o;
		} catch (ClassCastException cce) {
			return false;
		}

		if (this.colNo != other.colNo) {
			//System.out.println("Unequal 1");
			return false;
		}
		for (int i = 0; i < this.colNo; i++) {
			if (this.types[i] != other.types[i]) {
				//System.out.println("Unequal 2 " + i);
				return false;
			}
		}
		for (int i = 0; i < this.nestedNo; i++) {
			if (!this.nestedChildren[i].equals(other.nestedChildren[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param aux
	 * @return
	 */
	public NestedMetadata getNestedChild(int[] aux) {
		return recGetNestedChild(aux, 0);
	}
	private NestedMetadata recGetNestedChild(int[] aux, int n) {
		if (n >= aux.length){
			throw new Error("\n\nNRSMD: getting nested child at " + n +
					"-th position in an array of length " + aux.length + 
					" out of " + this.nestedNo + " nested children");
		}
		if (n == aux.length - 1) {
			return this.getNestedChild(aux[n]);
		} else {
			int k = aux[n];
			return this.getNestedChild(k).recGetNestedChild(aux, (n + 1));
		}
	}

	public String toString() {
		String ts = "";
		for (int i=0; i < types.length; i ++) {
			ts += types[i] + " ";
		}
		ts += "\n";
//		int atomic=0;
//		for (int i=0; i < types.length; i++) {
//			if (types[i] != TupleMetadataType.TUPLE_TYPE) {
//				ts += atomicAttrStats[atomic].averageSize+ "     ";
//				atomic++;
//			}
//		}
//		
//		ts += "\n";
//		atomic =0;
//		for (int i=0; i < types.length; i++) {
//			if (types[i] != TupleMetadataType.TUPLE_TYPE) {
//			ts +=atomicAttrStats[i].numDistinctValues + "     ";
//			atomic++;
//			}
//		}
		return ts;
	}

}
