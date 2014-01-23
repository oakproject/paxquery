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
package fr.inria.oak.paxquery.common.predicates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Conjunctive predicate consisting of one or multiple simple predicates.
 *
 */
public class ConjunctivePredicate extends BasePredicate implements Serializable {
	
	private static final Log logger = LogFactory.getLog(ConjunctivePredicate.class);

	
	private List<SimplePredicate> simplePreds;
	
	
	public ConjunctivePredicate(List<SimplePredicate> simplePreds) {
		this.simplePreds = simplePreds;
	}

	public List<SimplePredicate> getSimplePreds() {
		return this.simplePreds;
	}
	
	@Override
	public int[][] getLeftColumns() {
		int[][] leftColumns = new int[1][];
		leftColumns[1] = getConjunctiveLeftColumns();
		return leftColumns;
	}
	
	public int[] getConjunctiveLeftColumns() {
		int[] leftColumns = new int[this.simplePreds.size()];
		for(int i=0; i<this.simplePreds.size(); i++)
			leftColumns[i] = this.simplePreds.get(i).getColumn1();
		return leftColumns;
	}
	
	@Override
	public int[][] getRightColumns() {
		int[][] rightColumns = new int[1][];
		rightColumns[1] = getConjunctiveRightColumns();
		return rightColumns;
	}
	
	public int[] getConjunctiveRightColumns() {
		int[] rightColumns = new int[this.simplePreds.size()];
		for(int i=0; i<this.simplePreds.size(); i++) {
			int rightColumn = this.simplePreds.get(i).getColumn2();
			if(rightColumn != -1)
				rightColumns[i] = rightColumn;
			else {
				logger.error("This predicate does not compare with another columns, but with a constant value!");
				return null;
			}
		}
		return rightColumns;
	}
	
	@Override
	public boolean isOnlyEqui() {
		for(SimplePredicate sp: this.simplePreds) {
			if(sp.isOnlyEqui())
				continue;
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.simplePreds.get(0).toString());
		for(int i=1; i<this.simplePreds.size(); i++) {
			sb.append(" AND ");
			sb.append(this.simplePreds.get(i).toString());
		}
		return sb.toString();
	}

	@Override
	public ConjunctivePredicate clone() {
		List<SimplePredicate> newSimplePreds = new ArrayList<SimplePredicate>();
		for(SimplePredicate simplePred: this.simplePreds)
			newSimplePreds.add(simplePred.clone());
		return new ConjunctivePredicate(newSimplePreds);
	}
	

}
