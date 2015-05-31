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
package fr.inria.oak.paxquery.algebra.operators.unary;

import java.util.Arrays;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;


/**
 * Flatten logical operator.
 *
 */
public class Flatten extends BaseUnaryOperator {
	
	private final int[] unnestPath;
	

	public Flatten(BaseLogicalOperator child) {
		super(child);

		this.unnestPath = calculateCompleteUnnestPath(child.getNRSMD());
		this.visible = true;
		this.ownName = "Flatten";

		this.ownDetails = new String("");
	}
	
	public Flatten(BaseLogicalOperator child, int[] unnestPath) {
		super(child);

		this.unnestPath = unnestPath;
		this.visible = true;
		this.ownName = "Flatten";
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < this.unnestPath.length; i ++){
			sb.append(this.unnestPath[i]);
			if (i < this.unnestPath.length - 1){
				sb.append(".");
			}
		}
		sb.append("]");
		this.ownDetails = new String(sb);
	}
	
	
	public Flatten(BaseLogicalOperator child, int column) {
		super(child);
		
		this.unnestPath = new int[]{column};
		this.visible = true;
		this.ownName = "Flatten";
		this.ownDetails = "" + column + "";
		int[] keepMask = new int[child.getNRSMD().colNo - 1];
		int iMask = 0;
		for (int i = 0; i < child.getNRSMD().colNo; i ++){
			if (i != column){
				keepMask[iMask] = i;
				iMask ++;
			}
		}
	}
	
	@Override
	public void buildNRSMD() {
		for(BaseLogicalOperator op : children)
			op.buildNRSMD();
		this.nestedMetadata = NestedMetadataUtils.unnestField(children.get(0).getNRSMD(), this.unnestPath);
	}

	public int[] getUnnestPath() {
		return this.unnestPath;
	}
	
	private static int[] calculateCompleteUnnestPath(NestedMetadata nestedMetadata) {
		return recCalculateCompleteUnnestPath(nestedMetadata, 0);
	}
	
	private static int[] recCalculateCompleteUnnestPath(NestedMetadata nestedMetadata, int pos) {
		int[] unnestPath = new int[nestedMetadata.getColNo()];

		int j=0;
		for(int i=0; i<nestedMetadata.getColNo(); i++) {
			if(nestedMetadata.getCompleteMetadata()[i] == MetadataTypes.TUPLE_TYPE) {
				unnestPath[j++] = pos + i;
				
				int[] unnestPathChild = recCalculateCompleteUnnestPath(nestedMetadata.getNestedChild(i), pos + i + 1);
				int[] newUnnestPath = new int[unnestPath.length + unnestPathChild.length];
				System.arraycopy(unnestPath, 0, newUnnestPath, 0, j);
				System.arraycopy(unnestPathChild, 0, newUnnestPath, j, unnestPathChild.length);
			}
		}

		return Arrays.copyOf(unnestPath, j);
	}
	
}
