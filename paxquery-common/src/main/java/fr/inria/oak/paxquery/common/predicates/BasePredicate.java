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

import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;


/**
 * Generic class that predicates extend.
 *
 */
public abstract class BasePredicate implements Serializable {
	
	
	/**
	 * Enumerated type representing the possible types of predicates.
	 * 
	 * @author Jesus CAMACHO RODRIGUEZ
	 *
	 * @created 16/12/2010
	 */
	public enum PredicateType {
		PREDICATE_EQUAL,
		PREDICATE_NOTEQUAL,
		PREDICATE_SMALLEROREQUALTHAN,
		PREDICATE_SMALLERTHAN,
		PREDICATE_GREATEROREQUALTHAN,
		PREDICATE_GREATERTHAN,
		PREDICATE_PARENT,
		PREDICATE_CHILD,
		PREDICATE_ANCESTOR,
		PREDICATE_DESCENDANT,
		PREDICATE_BEFORE;
		
		/**
		 * Returns the string representation of the current value of the enumerated type.
		 * @return the string representation for this predicate
		 */
		@Override
		public String toString() {
			switch(this) {
				case PREDICATE_EQUAL:
					return "=";
				case PREDICATE_NOTEQUAL:
					return "!=";
				case PREDICATE_SMALLEROREQUALTHAN:
					return "<=";
				case PREDICATE_SMALLERTHAN:
					return "<";
				case PREDICATE_GREATEROREQUALTHAN:
					return ">=";
				case PREDICATE_GREATERTHAN:
					return ">";
				case PREDICATE_ANCESTOR:
					return "<<";
				case PREDICATE_PARENT:
					return "<";
				case PREDICATE_BEFORE:
					return " before ";
				case PREDICATE_CHILD:
					return ">";
				case PREDICATE_DESCENDANT:
					return ">>";
				default:
					return null;
			}
		}
		
		/**
		 * Returns the string representation for debugging purposes (more clear than the
		 * representation returned by {@link #toString()} method) of the current value
		 * of the enumerated type.
		 * @return the debugging string representation for this predicate
		 */
		public String toDebugString(){
			switch(this) {
				case PREDICATE_EQUAL:
					return "=";
				case PREDICATE_NOTEQUAL:
					return "!=";
				case PREDICATE_SMALLEROREQUALTHAN:
					return "<=";
				case PREDICATE_SMALLERTHAN:
					return "<";
				case PREDICATE_GREATEROREQUALTHAN:
					return ">=";
				case PREDICATE_GREATERTHAN:
					return ">";
				case PREDICATE_ANCESTOR:
					return " ancestor of ";
				case PREDICATE_PARENT:
					return " parent of ";
				case PREDICATE_BEFORE:
					return " before ";
				case PREDICATE_CHILD:
					return " child of";
				case PREDICATE_DESCENDANT:
					return " descendant of ";
				default:
					return null;
			}
		}

		/**
		 * Returns the reverse type for this predicate. If this predicate does not
		 * have a reverse, then the method returns the same predicate.
		 * @return the reverse type
		 */
		public PredicateType revert() {
			switch (this) {
				case PREDICATE_PARENT:
					return PredicateType.PREDICATE_CHILD;
				case PREDICATE_CHILD:
					return PredicateType.PREDICATE_PARENT;
				case PREDICATE_ANCESTOR:
					return PredicateType.PREDICATE_DESCENDANT;
				case PREDICATE_DESCENDANT:
					return PredicateType.PREDICATE_ANCESTOR;
				default:
					throw new PAXQueryExecutionException("Not supported revert operation for type " + this.toString());
			}
		}		
	}
	
	public abstract int[][] getLeftColumns();
	
	public abstract int[][] getRightColumns();

	public abstract boolean isOnlyEqui();
	
	@Override
	public abstract BasePredicate clone();

}
