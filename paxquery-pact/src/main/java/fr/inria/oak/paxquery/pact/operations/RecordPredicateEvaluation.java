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
package fr.inria.oak.paxquery.pact.operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flink.types.Record;
import org.apache.flink.types.StringValue;

import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;
import fr.inria.oak.paxquery.pact.datamodel.metadata.MetadataTypesMapping;


/**
 * Evaluation of predicates over records.
 *
 */
public class RecordPredicateEvaluation {
	
	private static final Log logger = LogFactory.getLog(RecordPredicateEvaluation.class);

	
	/**
	 * 
	 * @param inputRecordSignature
	 * @param record
	 * @param pred
	 * @return
	 */
	public static boolean evaluate(NestedMetadata inputRecordSignature, Record record, BasePredicate pred) {
		if(pred instanceof DisjunctivePredicate) {
			return evaluateDisjunctivePredicate(inputRecordSignature, record, (DisjunctivePredicate) pred);
		}
		else if(pred instanceof ConjunctivePredicate) {
			return evaluateConjunctivePredicate(inputRecordSignature, record, (ConjunctivePredicate) pred);
		}
		else
			return evaluateSimplePredicate(inputRecordSignature, record, (SimplePredicate) pred);
	}
	
	private static boolean evaluateDisjunctivePredicate(NestedMetadata inputRecordSignature, Record record, DisjunctivePredicate disjPred) {
		for(ConjunctivePredicate conjPred: disjPred.getConjunctivePreds())
			if(evaluateConjunctivePredicate(inputRecordSignature, record, conjPred))
				return true;
		
		return false;
	}
	
	private static boolean evaluateConjunctivePredicate(NestedMetadata inputRecordSignature, Record record, ConjunctivePredicate conjPred) {
		for(SimplePredicate simplePred: conjPred.getSimplePreds())
			if(!evaluateSimplePredicate(inputRecordSignature, record, simplePred))
				return false;
		
		return true;
	}
	
	private static boolean evaluateSimplePredicate(NestedMetadata inputRecordSignature, Record record, SimplePredicate simplePred) {
		try {
			String numericPattern = "^-?\\d+([,\\.]\\d+)?([eE]-?\\d+)?$";
			boolean isValue1Number, isValue2Number;
			
			double value1, value2;
	
			//We are evaluating a predicate over two columns!
			if(simplePred.getStringConstant() == null && simplePred.getDoubleConstant() == -1) {
				switch(simplePred.getPredCode()) {
					case PREDICATE_EQUAL:
						isValue1Number = Pattern.matches(numericPattern, record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						isValue2Number = Pattern.matches(numericPattern, record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(isValue1Number && isValue2Number) {
							//both columns contain numbers
							value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
							if(simplePred.getOperation1() != null)
								value1 = simplePred.getOperation1().calculate(value1);
							value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
							if(simplePred.getOperation2() != null)
								value2 = simplePred.getOperation2().calculate(value2);
							return value1 == value2;
						}
						else if(!isValue1Number && !isValue2Number)	//both columns contain non-number strings
							return record.getField(simplePred.getColumn1(), MetadataTypesMapping.getValueClass(inputRecordSignature.getType(simplePred.getColumn1())))
									.equals(record.getField(simplePred.getColumn2(), MetadataTypesMapping.getValueClass(inputRecordSignature.getType(simplePred.getColumn2()))));
						else	//a number and a non-number, result must be false
							return false;
					case PREDICATE_NOTEQUAL:
						isValue1Number = Pattern.matches(numericPattern, record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						isValue2Number = Pattern.matches(numericPattern, record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(isValue1Number && isValue2Number) {
							//both columns contain numbers
							value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
							if(simplePred.getOperation1() != null)
								value1 = simplePred.getOperation1().calculate(value1);
							value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
							if(simplePred.getOperation2() != null)
								value2 = simplePred.getOperation2().calculate(value2);
							return value1 != value2;
						}
						else //at least one column contains a non-number, test for non-equality
							return !record.getField(simplePred.getColumn1(), MetadataTypesMapping.getValueClass(inputRecordSignature.getType(simplePred.getColumn1())))
									.equals(record.getField(simplePred.getColumn2(), MetadataTypesMapping.getValueClass(inputRecordSignature.getType(simplePred.getColumn2()))));
					case PREDICATE_SMALLEROREQUALTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 <= value2;
					case PREDICATE_SMALLERTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 < value2;
					case PREDICATE_GREATEROREQUALTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 >= value2;
					case PREDICATE_GREATERTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record.getField(simplePred.getColumn2(), StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 > value2;	
					default:
						throw new PAXQueryExecutionException("Predicate " + simplePred.toString() + " not implemented yet!");
				}
			}
			else if(simplePred.getDoubleConstant() != -1) {
				//We are evaluating a predicate over a column and a double constant
				switch(simplePred.getPredCode()) {
					case PREDICATE_EQUAL:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 == simplePred.getDoubleConstant();
					case PREDICATE_NOTEQUAL:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 != simplePred.getDoubleConstant();
					case PREDICATE_SMALLEROREQUALTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 <= simplePred.getDoubleConstant();
					case PREDICATE_SMALLERTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 < simplePred.getDoubleConstant();
					case PREDICATE_GREATEROREQUALTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 >= simplePred.getDoubleConstant();
					case PREDICATE_GREATERTHAN:
						value1 = Double.parseDouble(record.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						return value1 > simplePred.getDoubleConstant();
					default:
						throw new PAXQueryExecutionException("Predicate " + simplePred.toString() + " not implemented yet!");
				}
			}
			
			//We are evaluating a predicate over a column and a string constant
			switch(simplePred.getPredCode()) {
				case PREDICATE_EQUAL:
					if (simplePred.getStringConstant().startsWith("~")) {
						Pattern p = Pattern.compile("(^|\\s+|[^a-zA-Z0-9]+)" + simplePred.getStringConstant().substring(1, simplePred.getStringConstant().length()) + "($|\\s+|[^a-zA-Z0-9]+)", Pattern.MULTILINE);
						Matcher m = p.matcher(record.getField(simplePred.getColumn1(), StringValue.class));
						return m.find();
					}
					return record.getField(simplePred.getColumn1(), StringValue.class).toString()
							.equals(simplePred.getStringConstant());
				case PREDICATE_NOTEQUAL:
					if (simplePred.getStringConstant().startsWith("~")) {
						Pattern p = Pattern.compile("(^|\\s+|[^a-zA-Z0-9]+)" + simplePred.getStringConstant().substring(1, simplePred.getStringConstant().length()) + "($|\\s+|[^a-zA-Z0-9]+)", Pattern.MULTILINE);
						Matcher m = p.matcher(record.getField(simplePred.getColumn1(), StringValue.class));
						return !m.find();
					}
					return !record.getField(simplePred.getColumn1(), StringValue.class).toString()
							.equals(simplePred.getStringConstant());
				default:
					throw new PAXQueryExecutionException("Predicate " + simplePred.toString() + " not implemented yet!");
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param inputRecordSignature1
	 * @param record1
	 * @param inputRecordSignature2
	 * @param record2
	 * @param pred
	 * @return
	 */
	public static boolean evaluate(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, BasePredicate pred) {
		if(pred instanceof DisjunctivePredicate) {
			return evaluateDisjunctivePredicate(inputRecordSignature1, record1, inputRecordSignature2, record2, (DisjunctivePredicate) pred);
		}
		else if(pred instanceof ConjunctivePredicate) {
			return evaluateConjunctivePredicate(inputRecordSignature1, record1, inputRecordSignature2, record2, (ConjunctivePredicate) pred);
		}
		else
			return evaluateSimplePredicate(inputRecordSignature1, record1, inputRecordSignature2, record2, (SimplePredicate) pred);
	}
	
	private static boolean evaluateDisjunctivePredicate(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, DisjunctivePredicate disjPred) {
		for(ConjunctivePredicate conjPred: disjPred.getConjunctivePreds())
			if(evaluateConjunctivePredicate(inputRecordSignature1, record1, inputRecordSignature2, record2, conjPred))
				return true;
		
		return false;
	}
	
	private static boolean evaluateConjunctivePredicate(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, ConjunctivePredicate conjPred) {
		for(SimplePredicate simplePred: conjPred.getSimplePreds())
			if(!evaluateSimplePredicate(inputRecordSignature1, record1, inputRecordSignature2, record2, simplePred))
				return false;
		
		return true;
	}
	
	private static boolean evaluateSimplePredicate(NestedMetadata inputRecordSignature1, Record record1, NestedMetadata inputRecordSignature2, Record record2, SimplePredicate simplePred) {
		try {
			//We are evaluating a predicate over two columns!
			if(simplePred.getStringConstant() == null && simplePred.getDoubleConstant() == -1) {
				final int column2 = simplePred.getColumn2() - record1.getNumFields();
				double value1, value2;
				switch(simplePred.getPredCode()) {
					case PREDICATE_EQUAL:
						return record1.getField(simplePred.getColumn1(), MetadataTypesMapping.getValueClass(inputRecordSignature1.getType(simplePred.getColumn1())))
								.equals(record2.getField(column2, MetadataTypesMapping.getValueClass(inputRecordSignature2.getType(column2))));
					case PREDICATE_NOTEQUAL:
						return !record1.getField(simplePred.getColumn1(), MetadataTypesMapping.getValueClass(inputRecordSignature1.getType(simplePred.getColumn1())))
								.equals(record2.getField(column2, MetadataTypesMapping.getValueClass(inputRecordSignature2.getType(column2))));
					case PREDICATE_SMALLEROREQUALTHAN:
						value1 = Double.parseDouble(record1.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record2.getField(column2, StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 <= value2;
					case PREDICATE_SMALLERTHAN:
						value1 = Double.parseDouble(record1.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record2.getField(column2, StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 < value2;
					case PREDICATE_GREATEROREQUALTHAN:
						value1 = Double.parseDouble(record1.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record2.getField(column2, StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 >= value2;
					case PREDICATE_GREATERTHAN:
						value1 = Double.parseDouble(record1.getField(simplePred.getColumn1(), StringValue.class).getValue());
						if(simplePred.getOperation1() != null)
							value1 = simplePred.getOperation1().calculate(value1);
						value2 = Double.parseDouble(record2.getField(column2, StringValue.class).getValue());
						if(simplePred.getOperation2() != null)
							value2 = simplePred.getOperation2().calculate(value2);
						return value1 > value2;	
					default:
						throw new PAXQueryExecutionException("Predicate " + simplePred.toString() + " not implemented yet!");
				}
			}
			
			//We are evaluating a predicate over a column and a constant
			throw new PAXQueryExecutionException("A predicate with a constant value should not be used for a join! There may be something wrong in the plan!");
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
