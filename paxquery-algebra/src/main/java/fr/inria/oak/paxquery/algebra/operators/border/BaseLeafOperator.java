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
package fr.inria.oak.paxquery.algebra.operators.border;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;


/**
 * Generic class that a leaf operator extends.
 *
 */
public abstract class BaseLeafOperator extends BaseLogicalOperator {
	
	@Override
	public int recursiveDotString(StringBuffer sb, int parentNo, int firstAvailableNo) {
		int selfNumber = -1;
		if (this.visible) {
			selfNumber = firstAvailableNo;
				
			sb.append(
				selfNumber
					+ " [label=\""
					+ this.ownName);
			
			if(this.ownDetails != null && !this.ownDetails.equals("")) {
				sb.append("("
					+ this.ownDetails
					+ ")");
			}

			sb.append("\"] ; \n");
			if (parentNo >= 100) {
				sb.append(parentNo + " -> " + selfNumber + "\n");
			}
			return (firstAvailableNo + 1);
		}
		return firstAvailableNo;
	}
}
