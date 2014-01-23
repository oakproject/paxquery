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


/**
 * Disjunctive predicate consisting of one or multiple conjunctions.
 *
 */
public class DisjunctivePredicate extends BasePredicate implements Serializable {
	
	private List<ConjunctivePredicate> conjunctivePreds;
	
	
	public DisjunctivePredicate(List<ConjunctivePredicate> conjunctivePreds) {
		this.conjunctivePreds = conjunctivePreds;
	}

	public List<ConjunctivePredicate> getConjunctivePreds() {
		return this.conjunctivePreds;
	}
	
	@Override
	public int[][] getLeftColumns() {
		int[][] leftColumns = new int[this.conjunctivePreds.size()][];
		for(int i=0; i<this.conjunctivePreds.size(); i++)
			leftColumns[i] = this.conjunctivePreds.get(i).getConjunctiveLeftColumns();
		return leftColumns;
	}
	
	@Override
	public int[][] getRightColumns() {
		int[][] rightColumns = new int[this.conjunctivePreds.size()][];
		for(int i=0; i<this.conjunctivePreds.size(); i++)
			rightColumns[i] = this.conjunctivePreds.get(i).getConjunctiveRightColumns();
		return rightColumns;
	}

	@Override
	public boolean isOnlyEqui() {
		for(ConjunctivePredicate cp: this.conjunctivePreds) {
			if(cp.isOnlyEqui())
				continue;
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.conjunctivePreds.get(0).toString());
		sb.append(")");
		for(int i=1; i<this.conjunctivePreds.size(); i++) {
			sb.append(" OR ");
			sb.append("(");
			sb.append(this.conjunctivePreds.get(i).toString());
			sb.append(")");
		}
		return sb.toString();
	}
	
	@Override
	public DisjunctivePredicate clone() {
		List<ConjunctivePredicate> newConjunctivePreds = new ArrayList<ConjunctivePredicate>();
		for(ConjunctivePredicate conjPred: this.conjunctivePreds)
			newConjunctivePreds.add(conjPred.clone());
		return new DisjunctivePredicate(newConjunctivePreds);
	}

}
