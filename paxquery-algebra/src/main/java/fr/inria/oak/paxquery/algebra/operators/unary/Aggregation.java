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
package fr.inria.oak.paxquery.algebra.operators.unary;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;


/**
 * Aggregation logical operator.
 *
 */
public class Aggregation extends BaseUnaryOperator {
	
	private static final Log logger = LogFactory.getLog(Aggregation.class);
	
	private final int[] aggregationPath;
	
	private final AggregationType aggregationType;
	
	private final boolean excludeNestedField;
	
	private final int documentIDColumn;
	

	public Aggregation(BaseLogicalOperator child, int[] aggregationPath, AggregationType aggregationType) {
		super(child);

		this.aggregationPath = aggregationPath;
		this.aggregationType = aggregationType;
		this.documentIDColumn = -1;
		this.excludeNestedField = false;
		
		MetadataTypes[] attScanMeta = new MetadataTypes[1];
		attScanMeta[0] = MetadataTypes.STRING_TYPE;
		NestedMetadata aggregationColumnNRSMD = new NestedMetadata(1,attScanMeta);
		if(aggregationPath.length > 1) {
			final int length = aggregationPath.length - 2;
			if(length != 0) {
				int[] pathExceptLasts = new int[length];
				System.arraycopy(aggregationPath, 0, pathExceptLasts, 0, length);
				NestedMetadata newNestedNRSMD = NestedMetadataUtils.appendNRSMD(child.getNRSMD().getNestedChild(pathExceptLasts), aggregationColumnNRSMD);
				this.nestedMetadata = NestedMetadataUtils.addNestedField(child.getNRSMD(), pathExceptLasts, newNestedNRSMD);
			}
			else
				this.nestedMetadata = NestedMetadataUtils.appendNRSMD(child.getNRSMD(), aggregationColumnNRSMD);
		}
		else {
			NestedMetadata emptyNRSMD = NestedMetadataUtils.emptyNRSMD();
			NestedMetadata nestedNRSMD = NestedMetadataUtils.addNestedField(emptyNRSMD, child.getNRSMD());
			
			this.nestedMetadata = NestedMetadataUtils.appendNRSMD(nestedNRSMD, aggregationColumnNRSMD);
		}
		
		this.visible = true;
		this.ownName = "Aggregation";
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < this.aggregationPath.length; i ++){
			sb.append(this.aggregationPath[i]);
			if (i < this.aggregationPath.length - 1){
				sb.append(".");
			}
		}
		sb.append("]");
		this.ownDetails = new String(sb);
	}
	
	public Aggregation(BaseLogicalOperator child, int[] aggregationPath, AggregationType aggregationType,
			int documentIDColumn, boolean excludeNestedField) {
		super(child);

		this.aggregationPath = aggregationPath;
		this.aggregationType = aggregationType;
		this.documentIDColumn = documentIDColumn;
		this.excludeNestedField = excludeNestedField;
		
		MetadataTypes[] attScanMeta = new MetadataTypes[1];
		attScanMeta[0] = MetadataTypes.STRING_TYPE;
		NestedMetadata aggregationColumnNRSMD = new NestedMetadata(1,attScanMeta);
		if(aggregationPath.length > 1) {
			final int length = aggregationPath.length - 2;
			if(length != 0) {
				int[] pathExceptLasts = new int[length];
				System.arraycopy(aggregationPath, 0, pathExceptLasts, 0, length);
				NestedMetadata newNestedNRSMD = NestedMetadataUtils.appendNRSMD(child.getNRSMD().getNestedChild(pathExceptLasts), aggregationColumnNRSMD);
				this.nestedMetadata = NestedMetadataUtils.addNestedField(child.getNRSMD(), pathExceptLasts, newNestedNRSMD);
			}
			else
				this.nestedMetadata = NestedMetadataUtils.appendNRSMD(child.getNRSMD(), aggregationColumnNRSMD);
		}
		else {
			NestedMetadata emptyNRSMD = NestedMetadataUtils.emptyNRSMD();
			if(!this.excludeNestedField) {
				NestedMetadata nestedNRSMD = NestedMetadataUtils.addNestedField(emptyNRSMD, child.getNRSMD());
				this.nestedMetadata = NestedMetadataUtils.appendNRSMD(nestedNRSMD, aggregationColumnNRSMD);
			}
			else
				this.nestedMetadata = NestedMetadataUtils.appendNRSMD(emptyNRSMD, aggregationColumnNRSMD);
		}
		
		this.visible = true;
		this.ownName = "Aggregation";
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < this.aggregationPath.length; i ++){
			sb.append(this.aggregationPath[i]);
			if (i < this.aggregationPath.length - 1){
				sb.append(".");
			}
		}
		sb.append("]");
		this.ownDetails = new String(sb);
	}
	
	
	public int[] getAggregationPath() {
		return this.aggregationPath;
	}
	
	public AggregationType getAggregationType() {
		return this.aggregationType;
	}
	
	public boolean isExcludeNestedField() {
		return this.excludeNestedField;
	}
	
	public int getDocumentIDColumn() {
		return this.documentIDColumn;
	}
		
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Aggregation((BaseLogicalOperator)getChild().clone(), this.aggregationPath, this.aggregationType, this.documentIDColumn, this.excludeNestedField);
	}
	
}
