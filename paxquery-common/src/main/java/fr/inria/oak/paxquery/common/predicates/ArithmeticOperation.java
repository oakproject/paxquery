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
package fr.inria.oak.paxquery.common.predicates;

import java.io.Serializable;

/**
 * Operations supported in predicates.
 *
 */
public class ArithmeticOperation implements Serializable {
    
	public static enum Operation {
    	ADD,
    	MUL,
    	SUB,
    	DIV;
    	
    	public static Operation parse(String op_symbol) {
    		Operation op;
    		switch(op_symbol) {
    			case "+":
    				op = Operation.ADD;
    				break;
    			case "*":
    				op = Operation.MUL;
    				break;
    			case "-":
    				op = Operation.SUB;
    				break;
    			case "/":
    				op = Operation.DIV;
    				break;
    			case "div":
    				op = Operation.DIV;
    				break;
    			default:
    				op = Operation.ADD;
    				break;
    		}
    		return op;
    	}
    }
    
    
    private final double x;
    
    private Operation operation;

    
    public ArithmeticOperation(Operation operation, double x) {
    	this.x = x;
    	this.operation = operation;
    }

    public double calculate(double x) {
        switch(this.operation) {
	        case ADD:
	            return x + this.x;
	        case MUL:
	        	return x * this.x;
	        case SUB:
	            return x - this.x;
	        case DIV:
	            return x / this.x;
	        default:
	        	return 0.0;
        }
    }
    
    @Override
	public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append(this.operation.toString());
    	sb.append(" ");
    	sb.append(String.valueOf(this.x));
    	return sb.toString();
    }
    
}
