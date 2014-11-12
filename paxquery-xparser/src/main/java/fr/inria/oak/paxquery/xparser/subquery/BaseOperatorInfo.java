package fr.inria.oak.paxquery.xparser.subquery;

import java.util.ArrayList;

import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;

public abstract class BaseOperatorInfo {
	public ArrayList<XMLScan> outerXMLScans;	//TPs in these scans are used to build GroupBy.GroupByColumns
	public XMLScan innerXMLScan;				//TPs in these scans are used to build GroupBy.nestColumns
	public Variable outerVariable;				//the variable to which the subquery is assigned
	public Variable returnedVariable;			//the variable returned inside the subquery
	public VarMap varMap;						//a varmap with all variables in the query

	public BaseOperatorInfo(ArrayList<XMLScan> outerXMLScans, XMLScan innerXMLScan, Variable outerVariable, Variable returnedVariable, VarMap varMap) {
		this.outerXMLScans = outerXMLScans;
		this.innerXMLScan = innerXMLScan;
		this.outerVariable = outerVariable;
		this.returnedVariable = returnedVariable;
		this.varMap = varMap;
	}
	
	/**
	 * Updates the internal state of a BaseLogicalOperator. The operator itself is declared in inheriting classes.
	 */
	public abstract void updateOperatorState() throws PAXQueryExecutionException;
}
