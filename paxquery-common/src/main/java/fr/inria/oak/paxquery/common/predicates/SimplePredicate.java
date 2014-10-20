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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.xml.navigation.Variable;


/**
 * Simplest predicate. Comparison between two columns or a column and a constant.
 *
 */
public class SimplePredicate extends BasePredicate implements Serializable {
	
	private static final Log logger = LogFactory.getLog(SimplePredicate.class);

	private int column1;
	private Variable variable1;
	private final ArithmeticOperation operation1;
	private int column2;
	private Variable variable2;
	private final ArithmeticOperation operation2;	
	private final String stringConstant;
	private final double doubleConstant;
	private final PredicateType predCode;

	
	public SimplePredicate(int column1, int column2, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = null;
		this.column2 = column2;
		this.operation2 = null;
		this.stringConstant = null;
		this.doubleConstant = -1;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, Variable variable1, int column2, Variable variable2, PredicateType predCode) {
		this.column1 = column1;
		this.variable1 = variable1;
		this.operation1 = null;
		this.column2 = column2;
		this.variable2 = variable2;
		this.operation2 = null;
		this.stringConstant = null;
		this.doubleConstant = -1;
		this.predCode = predCode;
	}

	
	public SimplePredicate(int column1, String stringConstant, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = null;
		this.stringConstant = stringConstant;
		this.doubleConstant = -1;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, ArithmeticOperation operation1, String stringConstant, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = operation1;
		this.stringConstant = stringConstant;
		this.doubleConstant = -1;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, Variable variable1, ArithmeticOperation operation1, String stringConstant, PredicateType predCode) {
		this.column1 = column1;
		this.variable1 = variable1;
		this.operation1 = operation1;
		this.stringConstant = stringConstant;
		this.doubleConstant = -1;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, double doubleConstant, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = null;
		this.stringConstant = null;
		this.doubleConstant = doubleConstant;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, ArithmeticOperation operation1, double doubleConstant, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = operation1;
		this.stringConstant = null;
		this.doubleConstant = doubleConstant;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, Variable variable1, ArithmeticOperation operation1, double doubleConstant, PredicateType predCode) {
		this.column1 = column1;
		this.variable1 = variable1;
		this.operation1 = operation1;
		this.stringConstant = null;
		this.doubleConstant = doubleConstant;
		this.column2 = -1;
		this.operation2 = null;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, ArithmeticOperation operation1, int column2, ArithmeticOperation operation2, PredicateType predCode) {
		this.column1 = column1;
		this.operation1 = operation1;
		this.column2 = column2;
		this.operation2 = operation2;
		this.stringConstant = null;
		this.doubleConstant = -1;
		this.predCode = predCode;
	}
	
	public SimplePredicate(int column1, Variable variable1, ArithmeticOperation operation1, int column2, Variable variable2, ArithmeticOperation operation2, PredicateType predCode) {
		this.column1 = column1;
		this.variable1 = variable1;
		this.operation1 = operation1;
		this.column2 = column2;
		this.variable2 = variable2;
		this.operation2 = operation2;
		this.stringConstant = null;
		this.doubleConstant = -1;
		this.predCode = predCode;
	}

	public int getColumn1() {
		return this.column1;
	}
	
	public void setColumn1(int column1) {
		this.column1 = column1;
	}
	
	public Variable getVariable1() {
		return this.variable1;
	}
	
	public void setVariable1(Variable variable1) {
		this.variable1 = variable1;
	}
	
	public ArithmeticOperation getOperation1() {
		return this.operation1;
	}

	public int getColumn2() {
		return this.column2;
	}
	
	public void setColumn2(int column2) {
		this.column2 = column2;
	}
	
	public Variable getVariable2() {
		return this.variable2;
	}
	
	public void setVariable2(Variable variable2) {
		this.variable2 = variable2;
	}
	
	public ArithmeticOperation getOperation2() {
		return this.operation2;
	}
	
	public String getStringConstant() {
		return this.stringConstant;
	}
	
	public double getDoubleConstant() {
		return this.doubleConstant;
	}
	
	public boolean comparesToConstant() {
		return column2 == -1;
	}
	
	public boolean comparesToVariable() {
		return column2 != -1;
	}
	
//	public PactString getPactStringConstant() {
//		if(this.pactStringConstant == null) {
//			final PactString constant = new PactString(this.stringConstant);
//			this.pactStringConstant = constant;
//		}
//		
//		return this.pactStringConstant;
//	}
	
//	public PactDouble getPactDoubleConstant() {
//		if(this.pactDoubleConstant == null) {
//			final PactDouble constant = new PactDouble(this.doubleConstant);
//			this.pactDoubleConstant = constant;
//		}
//		
//		return this.pactDoubleConstant;
//	}
	
	public PredicateType getPredCode() {
		return this.predCode;
	}
	
	@Override
	public int[][] getLeftColumns() {
		int[][] leftColumns = new int[1][1];
		leftColumns[0][0] = this.column1;
		return leftColumns;
	}
	
	@Override
	public int[][] getRightColumns() {
		if(this.column2 != -1) {
			int[][] rightColumns = new int[1][1];
			rightColumns[0][0] = this.column2;
			return rightColumns;
		}

		logger.error("This predicate does not compare with another columns, but with a constant value!");
		return null;
	}
	
	@Override
	public boolean isOnlyEqui() {
		return this.predCode == PredicateType.PREDICATE_EQUAL;
	}	
	
	/*
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.operation1 != null)
			sb.append(this.column1).append(" ").append(this.operation1.toString());
		else
			sb.append(this.column1);
		sb.append(this.predCode.toString());
		if(this.stringConstant != null)
			sb.append(this.stringConstant);
		else if(this.doubleConstant != -1)
			sb.append(this.doubleConstant);
		else if(this.operation2 != null)
			sb.append(this.column2).append(" ").append(this.operation2.toString());
		else
			sb.append(this.column2);
		return sb.toString();
	}*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String var1name = this.variable1.name.startsWith("$") && this.variable1.name.length() > 1 ? this.variable1.name.substring(1) : this.variable1.name;
		String var2name = null;
		if(this.operation1 != null)
			sb.append(var1name).append(" ").append(this.operation1.toString());
		else
			sb.append(var1name).append(" ");
		sb.append(this.predCode.toString()+" ");
		if(this.stringConstant != null)
			sb.append(this.stringConstant);
		else if(this.doubleConstant != -1)
			sb.append(this.doubleConstant);
		else {
			var2name = this.variable2.name != null && this.variable2.name.startsWith("$") && this.variable2.name.length() > 1 ? this.variable2.name.substring(1) : this.variable2.name;
			if(this.operation2 != null)
				sb.append(" ").append(var2name).append(" ").append(this.operation2.toString());
			else
				sb.append(" ").append(var2name);
		}
		return sb.toString();
	}
	
	@Override
	public SimplePredicate clone() {
		if(this.stringConstant != null)
			return new SimplePredicate(this.column1, this.stringConstant, this.predCode);
		else if(this.doubleConstant != -1)
			return new SimplePredicate(this.column1, this.doubleConstant, this.predCode);
		else
			return new SimplePredicate(this.column1, this.operation1, this.column2, this.operation2, this.predCode);
	}
	
}
