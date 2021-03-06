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
package fr.inria.oak.paxquery.xparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.border.XMLTreeConstruct;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.DuplicateElimination;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.common.aggregation.AggregationType;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.ArithmeticOperation;
import fr.inria.oak.paxquery.common.predicates.ArithmeticOperation.Operation;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.predicates.BasePredicate.PredicateType;
import fr.inria.oak.paxquery.common.predicates.ConjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.predicates.SimplePredicate;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePattern;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;
import fr.inria.oak.paxquery.common.xml.navigation.Variable;
import fr.inria.oak.paxquery.xparser.XQueryParser.AttInner2Context;
import fr.inria.oak.paxquery.xparser.XQueryParser.AttInnerContext;
import fr.inria.oak.paxquery.xparser.mapping.LogicalPlanRemapper;
import fr.inria.oak.paxquery.xparser.mapping.VarMap;
import fr.inria.oak.paxquery.xparser.operatorinfo.BaseNestingOperatorInfo;
import fr.inria.oak.paxquery.xparser.operatorinfo.GroupByInfo;
import fr.inria.oak.paxquery.xparser.operatorinfo.LeftOuterNestedJoinInfo;


public class XQueryVisitorImplementation extends XQueryBaseVisitor<Void> {

	public enum StatementType { NONE, LET, LET_OUTER, FOR };
	
	/**
	 * Operators
	 */
	public ArrayList<XMLScan> scans;			//the leaves of the algebraic operators tree
	public XMLTreeConstruct construct;		//the new root of the algebraic operators tree
	public BaseLogicalOperator constructChild;	//pointer to the immediate descendant of the XMLconstruct operator

	/**
	 * Data structures
	 */
	public LogicalPlan logicalPlan;				//stores the tree of logical operators
	public VarMap varMap;						//stores info about the variables and their relation to pattern nodes

	public HashMap<String, NavigationTreePatternNode> patternNodeMap;	//each tuple <String, PatternNode> stores the name of a variable and the PatternNode it addresses
	public ArrayList<NavigationTreePattern> navigationTreePatterns;	//the list of all TreePattern objects built for a given query
	public ArrayList2Dims<NavigationTreePattern> navigationTreePatternsInsideSubquery;	//rows: subquery level (0: first subquery)
	public ArrayList<Boolean> whereStatementInSubqueryLevel;	//if whereStatementInSubqueryLevel.get(0) == true means that the first subquery level contains a "where" statement
	public ArrayList<Boolean> operatorsProcessedInSubqueryLevel;//if operatorsProcessedInSubqueryLevel.get(0) == true means that the operators containing the affected Navigation Trees have already been included in the main algebraic operator tree (this is useful when processin the return statement in a subquery: if we have included the affected operators when processing an attribute, then we should not include them again when processing a subsequent eleConstInner, for example) 
	
	public ConstructionTreePattern constructionTreePattern;	//holds the XML tree indicated in the return statement. To be used with the XMLConstruct operator
	public ConstructionTreePatternNode lastConstructionTreePatternNode;		
	public ConstructionTreePattern subqueryConstructionTreePattern;
	public ConstructionTreePatternNode lastSubqueryConstructionTreePatternNode;
	
	
	/**
	 * State variables for parsing
	 */
	private int treePatternNameCounter;
	private NavigationTreePattern lastTreePattern;
	private NavigationTreePatternNode lastNode;
	private NavigationTreePatternNode lastNodeInsideXPathPredicate;
	private String lastVarLeftSide;
	private String lastVarXPathPredicate;
	private String outerVarSubquery;	
	private int lastSlashToken;
	private boolean nextNodeIsAttribute;
	private boolean insideReturn;
	private ArrayList<Boolean> treePatternVisited = null; //for algebraic operators tree construction
	private Stack<BasePredicate> predicateStack;
	private boolean insideXPathPredicate = false;	//for XPath predicate building
	private int lastTreePatternInsideXPathPredicateIndex;
	private StatementType currentStatement = StatementType.NONE;
	private boolean doNesting = true;
	private int subqueryLevel = -1;	//-1 implies no subquery found. The first subquery is subqueryLevel 0
	private boolean subqueryWithWhere = false;
	private boolean operatorsProcessedInSubquery = false;
	private ArrayList<BaseNestingOperatorInfo> subqueryInfoList;
	private boolean notOccurrence = false;
	private HashMap<String, DuplicateElimination> dupelimNavigationTreePatterns;	//list of NavigationTreePattern.getName() that have a DuplicateElimination on its way to the root
	
	
	
	/**
	 * Others
	 */
	public String outputPath;					//file to be printed out 

	public XQueryVisitorImplementation(String outputPath) {
		this.outputPath = outputPath;
		logicalPlan = new LogicalPlan();
		varMap = new VarMap();
		patternNodeMap = new HashMap<String, NavigationTreePatternNode>();
		navigationTreePatterns = new ArrayList<NavigationTreePattern>();
		navigationTreePatternsInsideSubquery = new ArrayList2Dims<NavigationTreePattern>(0);
		subqueryInfoList = new ArrayList<BaseNestingOperatorInfo>();
		whereStatementInSubqueryLevel = new ArrayList<Boolean>();
		operatorsProcessedInSubqueryLevel = new ArrayList<Boolean>();
		lastSlashToken = 0;
		nextNodeIsAttribute = false;
		insideReturn = false;
		scans = new ArrayList<XMLScan>();
		treePatternVisited = new ArrayList<Boolean>();
		predicateStack = new Stack<BasePredicate>();
		constructionTreePattern = null;	//instantiated at visitReturnStat
		treePatternNameCounter = 0;
		dupelimNavigationTreePatterns = new HashMap<String, DuplicateElimination>();
	}
	
	/**
	 * xquery
	 * Starting rule. We build the XMLConstruct object here, after having processed the query
	 */
	public Void visitXquery(XQueryParser.XqueryContext ctx) { 
		visitChildren(ctx);
				
		//we instantiate the XMLConstruct operator here rather than in exitReturnStat since we can have several return clauses but just one XMLConstruct operator.
		construct = new XMLTreeConstruct(constructChild, constructionTreePattern, outputPath);
		
		//variable remapping; final positions of variables are calculated for every operator in logicalPlan
		logicalPlan.setRoot(construct);
		logicalPlan.setLeaves(scans);
		LogicalPlanRemapper.remapLogicalPlan(logicalPlan, varMap);

		return null;
	}


	/**
	 * letBinding
	 * store the left-side var so we can assign the xpath tree to it
	 * VAR := whatever
	 */
	public Void visitLetBinding(XQueryParser.LetBindingContext ctx) {
		currentStatement = StatementType.LET;
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();		
		//do visit, ctx.getChild(2) can be (pathExpr_xq | flwrexpr | aggrExpr | arithmeticExpr_xq | literal)
		visit(ctx.getChild(2));
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		currentStatement = StatementType.NONE;
		
		return null;	//// Java says must return something even when Void
	}

	/**
	 * forBinding
	 * store the left-side var so we can assign the xpath tree to it
	 */	
	public Void visitForBinding(XQueryParser.ForBindingContext ctx) { 
		currentStatement = StatementType.FOR;
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();
		//do visit
		visit(ctx.pathExpr_xq());
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		currentStatement = StatementType.NONE;
		
		return null;
	}
	
	/**
	 * flwrexpr
	 * If we're inside a LET, increment subquery level. At the end, decrease subquery level.
	 */
	public Void visitFlwrexpr(XQueryParser.FlwrexprContext ctx) { 
		if(currentStatement == StatementType.LET) {
			outerVarSubquery = lastVarLeftSide;
			subqueryLevel++;
			navigationTreePatternsInsideSubquery.addRow();
			whereStatementInSubqueryLevel.add(false);
			operatorsProcessedInSubqueryLevel.add(false);
		}
		
		visitChildren(ctx); 

		if(subqueryLevel >= 0)
			subqueryLevel--;
		
		return null;
	}

	
	/**
	 * visitPathExpr_xq
	 */
	/*public Void visitPathExpr_xq(XQueryParser.PathExpr_xqContext ctx) { 
		//by visiting the children we build the tree pattern and set up the XMLScan algop
		visitChildren(ctx); 

		if(ctx.getText().startsWith("distinct-values")) {
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, lastVarLeftSide);
			if(constructChild == null) {
				constructChild = scans.get(patternTreeIndex);
				constructChild = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
				treePatternVisited.set(patternTreeIndex, true);
			}
			else if(constructChild != null && treePatternVisited.get(patternTreeIndex) == false) {
				DuplicateElimination dupel = new DuplicateElimination(scans.get(patternTreeIndex), new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
				constructChild = new CartesianProduct(constructChild, dupel);
				treePatternVisited.set(patternTreeIndex, true);
			}
			else if(constructChild != null && treePatternVisited.get(patternTreeIndex) == true)
				constructChild = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
		}
		
		return null;
	}*/
	public Void visitPathExpr_xq(XQueryParser.PathExpr_xqContext ctx) { 
		//by visiting the children we build the tree pattern and set up the XMLScan algop
		visitChildren(ctx); 

		if(ctx.getText().startsWith("distinct-values")) {
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, lastVarLeftSide);
			Variable var = varMap.getVariable(lastVarLeftSide);
			//NavigationTreePattern affectedTP = patternNodeMap.get(patternTreeIndex).getTreePattern();
			//DuplicateElimination affectedDupElim = dupelimNavigationTreePatterns.get(affectedTP.getName());

			//in case the dupelim already exists
			if(var.getTreePattern() != null && dupelimNavigationTreePatterns.get(var.getTreePattern().getName()) != null) {
				DuplicateElimination affectedDupElim = dupelimNavigationTreePatterns.get(var.getTreePattern().getName());
				int[] oldColumns = affectedDupElim.getColumns();
				int[] newColumns = new int[oldColumns.length+1];
				for(int i = 0; i < oldColumns.length; i++)
					newColumns[i] = oldColumns[i];
				newColumns[newColumns.length-1] = varMap.getTemporaryPositionByName(lastVarLeftSide);
				affectedDupElim.setColumns(newColumns);
			}
			else {
				DuplicateElimination dupel = null;
				if(constructChild == null) {
					//constructChild = scans.get(patternTreeIndex);
					//constructChild = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					//dupel = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					dupel = new DuplicateElimination(scans.get(patternTreeIndex), new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					//constructChild.setParent(dupel);
					scans.get(patternTreeIndex).setParent(dupel);
					if(subqueryLevel == -1)
						constructChild = dupel;
					treePatternVisited.set(patternTreeIndex, true);
				}
				else if(constructChild != null && treePatternVisited.get(patternTreeIndex) == false) {
					//DuplicateElimination dupel = new DuplicateElimination(scans.get(patternTreeIndex), new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					dupel = new DuplicateElimination(scans.get(patternTreeIndex), new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					scans.get(patternTreeIndex).setParent(dupel);
					if(subqueryLevel == -1)
						constructChild = new CartesianProduct(constructChild, dupel);
					treePatternVisited.set(patternTreeIndex, true);
				}
				else if(constructChild != null && treePatternVisited.get(patternTreeIndex) == true) {
					//constructChild = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					dupel = new DuplicateElimination(constructChild, new int[] {varMap.getTemporaryPositionByName(lastVarLeftSide)});
					if(subqueryLevel == -1)
						constructChild = dupel;
				}
				if(var.getTreePattern() != null)
					dupelimNavigationTreePatterns.put(var.getTreePattern().getName(), dupel);
			}
		}
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_collection - enter
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 * TODO: merge with visitPathExprInner_xq_doc
	 */
	public Void visitPathExprInner_xq_collection(XQueryParser.PathExprInner_xq_collectionContext ctx) {
		//avoid nesting if we're in a "let $a := collection()" statement
		if(currentStatement == StatementType.LET)
			currentStatement = StatementType.LET_OUTER;
		//root node construction
		String rootTag = " ";
		lastTreePattern = new NavigationTreePattern();
		lastTreePattern.setName(Integer.toString(treePatternNameCounter));
		treePatternNameCounter++;
		int rootCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
		NavigationTreePatternNode rootNode = new NavigationTreePatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode);
		rootNode.addMatchingVariables(newVar);
		//rootNode.setStoresContent(true);
		varMap.addNewVariable(newVar);
		
		patternNodeMap.put(lastVarLeftSide, rootNode);

		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);		
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		navigationTreePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = XQueryUtils.sanitizeStringLiteral(ctx.STRING_LITERAL().getText());
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
		treePatternVisited.add(false);
		//nothing to visit
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_doc
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 * TODO: merge with visitPathExprInner_xq_doc
	 */
	public Void visitPathExprInner_xq_doc(XQueryParser.PathExprInner_xq_docContext ctx) { 
		//avoid nesting if we're in a "let $a := doc()" statement
		if(currentStatement == StatementType.LET)
			currentStatement = StatementType.LET_OUTER;
		//root node construction
		String rootTag = "";
		lastTreePattern = new NavigationTreePattern();
		int rootCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
		NavigationTreePatternNode rootNode = new NavigationTreePatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode);
		rootNode.addMatchingVariables(newVar);
		//rootNode.setStoresContent(true);
		varMap.addNewVariable(newVar);
	
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		navigationTreePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = XQueryUtils.sanitizeStringLiteral(ctx.STRING_LITERAL().getText());
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
		treePatternVisited.add(false);
		//nothing to visit
		
		return null;
	}	
	
	/**
	 * pathExpr slash (xpath)
	 * Update lastSlashToken with a slash ('/')
	 */
	public Void visitPathExpr_slash(XQueryParser.PathExpr_slashContext ctx) {
		lastSlashToken = XQueryLexer.SLASH;
		visit(ctx.relativePathExpr());
		lastSlashToken = 0;
		
		return null;
	}
	
	/**
	 * pathExpr slashslash (xpath)
	 * Update lastSlashToken with a slashslash ('//')
	 */
	public Void visitPathExpr_slashslash(XQueryParser.PathExpr_slashslashContext ctx) {
		lastSlashToken = XQueryLexer.SLASHSLASH;
		visit(ctx.relativePathExpr());
		lastSlashToken = 0;
		
		return null;
	}
	
	/**
	 * pathExpr_relativePathExpr (xpath)
	 */
	public Void visitPathExpr_relativePathExpr(XQueryParser.PathExpr_relativePathExprContext ctx) {
		visit(ctx.relativePathExpr());

		return null;
	}
	
	
	/**
	 * relativePathExpr2_slash (xpath)
	 * Decides that the next edge is parent-child
	 */
	public Void visitRelativePathExpr2_slash(XQueryParser.RelativePathExpr2_slashContext ctx) { 
		lastSlashToken = XQueryLexer.SLASH;	
		visit(ctx.stepExpr());

		return null;
	}

	/**
	 * relativePathExpr2_slashslash
	 * Decides that the next edge is ancestor-descendant
	 */
	public Void visitRelativePathExpr2_slashslash(XQueryParser.RelativePathExpr2_slashslashContext ctx) {
		lastSlashToken = XQueryLexer.SLASHSLASH;		
		visit(ctx.stepExpr());
		
		return null;
	}

	/**
	 * pathExprInner_xq_VAR
	 * Retrieves the appropriate tree and node for further expansion in the following case: another_var := VAR/whatever
	 * TODO: merge with visitPathExprInner_xq_doc and visitPathExprInner_xq_collection
	 */
	/*public Void visitPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) {
		String var_to_expand = ctx.VAR().getText();
		lastNode = varMap.getVariable(var_to_expand).node;
		
		lastTreePattern = lastNode.getTreePattern();
		
		return null;
	}*/
	public Void visitPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) {
		String var_to_expand = ctx.VAR().getText();
		lastNode = varMap.getVariable(var_to_expand).node;
		lastTreePattern = lastNode.getTreePattern();

		//if the same tree is accessed outside and inside the subquery then we need to clone it
		if(subqueryLevel > -1 && scans.size() == 1 && navigationTreePatternsInsideSubquery.containsElement(subqueryLevel, lastTreePattern) == false ) {
			//newTreePattern = clone(lastTreePattern)
			//NavigationTreePattern.deepCopy() does not copy references to Variables, this is ok since we don't want them in the new tree 
			NavigationTreePattern newTreePattern = lastTreePattern.deepCopy();
			newTreePattern.setName(Integer.toString(treePatternNameCounter));
			treePatternNameCounter++;
			//lastNode = findNodeInTP(lastNode, newTreePattern)
			int nodeCode = lastNode.getNodeCode();
			NavigationTreePatternNode newNode = newTreePattern.getRoot().locate(nodeCode);	//obtain the node VAR is pointing to, this time in the new tree
			//lastNode = newTreePattern.getRoot().locate(nodeCode);	//obtain the node VAR is pointing to, this time in the new tree
			newTreePattern.getRoot().pruneAllButPathTo(newNode);	//prune the rest of the tree, since we don't need that in the new tree
			//navigationTreePatternsInsideSubquery.add(newTreePattern)
			navigationTreePatternsInsideSubquery.addElement(subqueryLevel, newTreePattern);
			//scans.add(new XMLScan(newTreePattern))
			int tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, lastTreePattern);
			scans.add(new XMLScan(false, newTreePattern, scans.get(tpIndex).getPathDocuments()));
			navigationTreePatterns.add(newTreePattern);
			treePatternVisited.add(false);
			//lastTreePattern = newTreePattern
			lastNode = newNode;
			lastTreePattern = newTreePattern;
		}
		
		return null;
	}
	
	public Void visitBoolExpr_xq(XQueryParser.BoolExpr_xqContext ctx) { 
		if(ctx.NOT() != null) {
			notOccurrence = true;
			visitChildren(ctx);
			notOccurrence = false;
		}
		else
			visitChildren(ctx);		
		
		//return visitChildren(ctx); 
		return null;
	}

	
	/**
	 * where
	 * Increase whereHits by one.
	 */
	public Void visitWhere(XQueryParser.WhereContext ctx) { 				
		visitChildren(ctx); 
		
		if(subqueryLevel == -1) {
			//no subquery
			BasePredicate predicate = predicateStack.pop();
			if(constructChild != null && predicate != null)
				constructChild = new Selection(constructChild, predicate);
		}
		else //subquery with where statement
			subqueryWithWhere = true;
		
		return null;
	}
	
	/**
	 * orExpr_xq
	 * Takes all ConjunctivePredicates ath the top of the stack and instantiates a DisjunctivePredicate, then pushes it into the stack.
	 */
	public Void visitOrExpr_xq(XQueryParser.OrExpr_xqContext ctx) { 
		visitChildren(ctx); 
		
		ArrayList<ConjunctivePredicate> conjPreds = new ArrayList<ConjunctivePredicate>();
		
		//collect all pending ConjunctivePredicates
		while(predicateStack.empty() == false && predicateStack.peek() instanceof ConjunctivePredicate) 
			conjPreds.add((ConjunctivePredicate) predicateStack.pop());
		
		if(conjPreds.size() > 0) {
			DisjunctivePredicate disjPred = new DisjunctivePredicate(conjPreds);
			predicateStack.push(disjPred);
		}
		
		return null;
	}

	/**
	 * andExpr_xq
	 * Takes all SimplePredicates at the top of the stack and instantiates a ConjunctivePredicate, then pushes it into the stack.
	 */
	public Void visitAndExpr_xq(XQueryParser.AndExpr_xqContext ctx) { 
		visitChildren(ctx); 
		
		ArrayList<SimplePredicate> simplePreds = new ArrayList<SimplePredicate>();
		
		//collect all pending SimplePredicates
		while(predicateStack.empty() == false && predicateStack.peek() instanceof SimplePredicate) {
			simplePreds.add((SimplePredicate) predicateStack.pop());			
		}
		
		if(simplePreds.size() > 0) {
			ConjunctivePredicate conjPred = new ConjunctivePredicate(simplePreds);
			predicateStack.push(conjPred);
		}
		
		return null;
	}
	
	/**
	 * pred
	 * Builds an XQuery predicate
	 */
	/*public Void visitPred(XQueryParser.PredContext ctx) {
		SimplePredicate predicate = null;
		PredicateType predType = PredicateType.parse(ctx.vcmp().getText());
		String leftExpr = ctx.arithmeticExpr_xq(0).VAR().getText();
		String rightExpr = "";
		ArithmeticOperation arithOpLeft = null;
		ArithmeticOperation arithOpRight = null;
				
		int patternTreeIndexLeft = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, leftExpr);
		//left arith-expr, if any
		if(ctx.arithmeticExpr_xq(0).numericLiteral()!=null) {
			//the arithmetic operator is in the 1-th position of the arithmeticExpr_xq child
			String arithOp = ctx.arithmeticExpr_xq(0).children.get(1).getText();
			String operand_string = ctx.arithmeticExpr_xq(0).numericLiteral().getText();
			arithOpLeft = new ArithmeticOperation(Operation.parse(arithOp), Double.parseDouble(operand_string));
		}
		
		int patternTreeIndexRight = -1;
		if(ctx.arithmeticExpr_xq(1) != null && ctx.arithmeticExpr_xq(1).VAR() != null) {
			rightExpr = ctx.arithmeticExpr_xq(1).VAR().getText();
			patternTreeIndexRight = XQueryUtils.findVarInPatternTree(scans,  patternNodeMap, rightExpr);
		}
		
		//We build the predicate now, it can be...
		// VAR ARITH_OP op string_literal,
		if(ctx.STRING_LITERAL() != null) {
			rightExpr = XQueryUtils.sanitizeStringLiteral(ctx.STRING_LITERAL().getText());
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, rightExpr, predType);
			if(subqueryLevel == -1) {
				if(constructChild == null)
					constructChild = scans.get(patternTreeIndexLeft);
				else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
					constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
				//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains the left variable
				treePatternVisited.set(patternTreeIndexLeft, true);
			}
		}
		// VAR ARITH_OP op ('-')? numeric_literal, or
		else if(ctx.numericLiteral() != null) {
			rightExpr = ctx.OP_SUB()!=null ? ctx.OP_SUB().getText()+ctx.numericLiteral().getText() : ctx.numericLiteral().getText();
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, Double.parseDouble(rightExpr), predType);
			if(subqueryLevel == -1) {
				//build the operator
				if(constructChild == null)
					constructChild = scans.get(patternTreeIndexLeft);
				else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
					constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
				//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains the left variable
				treePatternVisited.set(patternTreeIndexLeft, true);
			}
		}
		// VAR ARITH_OP op VAR ARITH_OP
		else if(ctx.arithmeticExpr_xq(1) != null && ctx.arithmeticExpr_xq(1).VAR() != null) {   
			//if needed, pile cartesian products up to include all variables in the algebraic tree
			if(patternTreeIndexRight != -1) {
				if(patternTreeIndexRight == patternTreeIndexLeft) {
					//TODO: i think this predicate can be erased, since a new predicate is built at the end of the method. This is probably an error.
					predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), predType);
					if(subqueryLevel == -1) {
						if(constructChild == null)
							constructChild = scans.get(patternTreeIndexLeft);
						else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
							constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
						//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains both variables
						treePatternVisited.set(patternTreeIndexLeft, true);
					}
				}
				else {
					if(subqueryLevel == -1) {
						if(constructChild == null) {
							if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexLeft, true);
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(scans.get(patternTreeIndexLeft),  scans.get(patternTreeIndexRight));
							}						
						}
						else {
							if(treePatternVisited.get(patternTreeIndexLeft) == true && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexRight));
							}
							else if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == true) {
								constructChild = new CartesianProduct(scans.get(patternTreeIndexRight), constructChild);
								treePatternVisited.set(patternTreeIndexLeft, true);
							}
							else if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexLeft, true);
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexRight));
							}
						}
					}
				}
			}			
			//right arith-expr, if any
			if(ctx.arithmeticExpr_xq(1).numericLiteral() != null) {
				//the arithmetic operator is in the 1-th position of the arithmeticExpr_xq child
				String arithOp = ctx.arithmeticExpr_xq(1).children.get(1).getText();
				String operand_string = ctx.arithmeticExpr_xq(1).numericLiteral().getText();
				arithOpRight = new ArithmeticOperation(Operation.parse(arithOp), Double.parseDouble(operand_string));
			}
			
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), arithOpRight, predType);
		}
	
		//insert the new predicate in the predicate stack
		predicateStack.push(predicate);
		
		return null;
	}*/
	public Void visitPred(XQueryParser.PredContext ctx) {
		SimplePredicate predicate = null;
		PredicateType predType = notOccurrence ? negatedPredType(PredicateType.parse(ctx.vcmp().getText())) : PredicateType.parse(ctx.vcmp().getText());
		//PredicateType predType = PredicateType.parse(ctx.vcmp().getText());
		String leftExpr = ctx.arithmeticExpr_xq(0).VAR().getText();
		String rightExpr = "";
		ArithmeticOperation arithOpLeft = null;
		ArithmeticOperation arithOpRight = null;
				
		int patternTreeIndexLeft = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, leftExpr);
		//left arith-expr, if any
		if(ctx.arithmeticExpr_xq(0).numericLiteral()!=null) {
			//the arithmetic operator is in the 1-th position of the arithmeticExpr_xq child
			String arithOp = ctx.arithmeticExpr_xq(0).children.get(1).getText();
			String operand_string = ctx.arithmeticExpr_xq(0).numericLiteral().getText();
			arithOpLeft = new ArithmeticOperation(Operation.parse(arithOp), Double.parseDouble(operand_string));
		}
		
		int patternTreeIndexRight = -1;
		if(ctx.arithmeticExpr_xq(1) != null && ctx.arithmeticExpr_xq(1).VAR() != null) {
			rightExpr = ctx.arithmeticExpr_xq(1).VAR().getText();
			patternTreeIndexRight = XQueryUtils.findVarInPatternTree(scans,  patternNodeMap, rightExpr);
		}
		
		//We build the predicate now, it can be...
		// VAR ARITH_OP op string_literal,
		if(ctx.STRING_LITERAL() != null) {
			rightExpr = XQueryUtils.sanitizeStringLiteral(ctx.STRING_LITERAL().getText());
			Variable leftVar = varMap.getVariable(leftExpr);
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), leftVar, arithOpLeft, rightExpr, predType);
			if(leftVar.dataType != Variable.VariableDataType.Aggregation) {
				if(subqueryLevel == -1) {
					if(constructChild == null)
						constructChild = scans.get(patternTreeIndexLeft);
					else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
						constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
					//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains the left variable
					treePatternVisited.set(patternTreeIndexLeft, true);
				}
			}
		}
		// VAR ARITH_OP op ('-')? numeric_literal, or
		else if(ctx.numericLiteral() != null) {
			rightExpr = ctx.OP_SUB()!=null ? ctx.OP_SUB().getText()+ctx.numericLiteral().getText() : ctx.numericLiteral().getText();
			Variable leftVar = varMap.getVariable(leftExpr);
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), leftVar, arithOpLeft, Double.parseDouble(rightExpr), predType);
			if(leftVar.dataType != Variable.VariableDataType.Aggregation) {
				if(subqueryLevel == -1) {
					//build the operator
					if(constructChild == null)
						constructChild = scans.get(patternTreeIndexLeft);
					else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
						constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
					//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains the left variable
					treePatternVisited.set(patternTreeIndexLeft, true);
				}
			}
		}
		// VAR ARITH_OP op VAR ARITH_OP
		else if(ctx.arithmeticExpr_xq(1) != null && ctx.arithmeticExpr_xq(1).VAR() != null) {   
			//right arith-expr, if any
			if(ctx.arithmeticExpr_xq(1).numericLiteral() != null) {
				//the arithmetic operator is in the 1-th position of the arithmeticExpr_xq child
				String arithOp = ctx.arithmeticExpr_xq(1).children.get(1).getText();
				String operand_string = ctx.arithmeticExpr_xq(1).numericLiteral().getText();
				arithOpRight = new ArithmeticOperation(Operation.parse(arithOp), Double.parseDouble(operand_string));
			}

			//if needed, pile cartesian products up to include all variables in the algebraic tree
			if(patternTreeIndexRight != -1) {
				if(patternTreeIndexRight == patternTreeIndexLeft) {
					//TODO: i think this predicate can be erased, since a new predicate is built at the end of the method. This is probably an error.
					predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), predType);
					if(subqueryLevel == -1) {
						if(constructChild == null)
							constructChild = scans.get(patternTreeIndexLeft);
						else if(constructChild != null && treePatternVisited.get(patternTreeIndexLeft) == false)
							constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
						//in case constructChild != null $$ treePatternVisited.get(patternTreeIndexLeft) == true do nothing since constructChild already contains both variables
						treePatternVisited.set(patternTreeIndexLeft, true);
					}
				}
				else {
					if(subqueryLevel == -1) {
						if(constructChild == null) {
							if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexLeft, true);
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(scans.get(patternTreeIndexLeft),  scans.get(patternTreeIndexRight));
							}						
						}
						else {
							if(treePatternVisited.get(patternTreeIndexLeft) == true && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexRight));
							}
							else if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == true) {
								constructChild = new CartesianProduct(scans.get(patternTreeIndexLeft), constructChild);
								treePatternVisited.set(patternTreeIndexLeft, true);
							}
							else if(treePatternVisited.get(patternTreeIndexLeft) == false && treePatternVisited.get(patternTreeIndexRight) == false) {
								treePatternVisited.set(patternTreeIndexLeft, true);
								treePatternVisited.set(patternTreeIndexRight, true);
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexLeft));
								constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndexRight));
							}
						}
					}
					else {
						//we're in a subquery, check that the left var belongs to the outer tree and the right var belongs to the inner tree and switch if needed
						NavigationTreePattern innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
						//adjustLeftRightInSubqueryPredicate(varMap.getVariable(leftExpr), varMap.getVariable(rightExpr), innerTP);
						if(varMap.getVariable(leftExpr).getTreePattern() != varMap.getVariable(rightExpr).getTreePattern() && varMap.getVariable(leftExpr).getTreePattern() == innerTP) {
							String auxExpr = leftExpr;
							leftExpr = rightExpr;
							rightExpr = auxExpr;
							predType = complementaryPredType(predType);
							ArithmeticOperation auxArithOp = arithOpLeft;
							arithOpLeft = arithOpRight;
							arithOpRight = auxArithOp;
						}
					}
				}
			}			
			
			predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), arithOpRight, predType);
		}
	
		//insert the new predicate in the predicate stack
		predicateStack.push(predicate);
		
		return null;
	}
	
	private PredicateType negatedPredType(PredicateType predType) {
		switch(predType) {
		case PREDICATE_SMALLEROREQUALTHAN:
			return PredicateType.PREDICATE_GREATERTHAN;
		case PREDICATE_SMALLERTHAN:
			return PredicateType.PREDICATE_GREATEROREQUALTHAN;
		case PREDICATE_GREATEROREQUALTHAN:
			return PredicateType.PREDICATE_SMALLERTHAN;
		case PREDICATE_GREATERTHAN:
			return PredicateType.PREDICATE_SMALLEROREQUALTHAN;
		case PREDICATE_EQUAL:
			return PredicateType.PREDICATE_NOTEQUAL;
		case PREDICATE_NOTEQUAL:
			return PredicateType.PREDICATE_EQUAL;
		default:
			return predType;		
		}
	}
	
	private PredicateType complementaryPredType(PredicateType predType) {
		switch(predType) {
		case PREDICATE_SMALLEROREQUALTHAN:
			return PredicateType.PREDICATE_GREATEROREQUALTHAN;
		case PREDICATE_SMALLERTHAN:
			return PredicateType.PREDICATE_GREATERTHAN;
		case PREDICATE_GREATEROREQUALTHAN:
			return PredicateType.PREDICATE_SMALLEROREQUALTHAN;
		case PREDICATE_GREATERTHAN:
			return PredicateType.PREDICATE_SMALLERTHAN;
		default:
			return predType;
		}
	}
	
	public Void visitEmpty(XQueryParser.EmptyContext ctx) {
		String varName = ctx.VAR().getText();
		PredicateType predType = notOccurrence ? PredicateType.PREDICATE_NOTEQUAL : PredicateType.PREDICATE_EQUAL;
		
		//find var in tree pattern
		int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
		if(patternTreeIndex != -1) {
			if(subqueryLevel == -1) {
				if(constructChild == null) {
					constructChild = scans.get(patternTreeIndex);
					treePatternVisited.set(patternTreeIndex, true);
				}
				predicateStack.push(new SimplePredicate(varMap.getTemporaryPositionByName(varName), varMap.getVariable(varName), "\u0000", predType));
			}			
		}		
		return null;
	}
	
	public Void visitContains(XQueryParser.ContainsContext ctx) {
		String varName = ctx.VAR().getText();
		//String literal = "~" + ctx.STRING_LITERAL().getText();
		String literal = "~" + ctx.STRING_LITERAL().getText().substring(1, ctx.STRING_LITERAL().getText().length()-1); 
		PredicateType predType = notOccurrence ? PredicateType.PREDICATE_NOTEQUAL : PredicateType.PREDICATE_EQUAL;
		
		//find var in tree pattern
		int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
		if(patternTreeIndex != -1) {
			if(subqueryLevel == -1) {
				if(constructChild == null) {
					constructChild = scans.get(patternTreeIndex);
					treePatternVisited.set(patternTreeIndex, true);
				}
				predicateStack.push(new SimplePredicate(varMap.getTemporaryPositionByName(varName), varMap.getVariable(varName), literal, predType));
			}
		}
				
		return null;
	}
	

	/**
	 * predicate_xp
	 * predicate_xp : '[' expr_xp ']' ;
	 */
	public Void visitPredicate_xp(XQueryParser.Predicate_xpContext ctx) { 
		insideXPathPredicate = true;
		
		lastVarXPathPredicate = "";
		lastNodeInsideXPathPredicate = null;
		
		visitChildren(ctx); 
				
		//we create a Selection object for every predicate ([expr_xp]) we find, even if there is more than one predicate for a given tree
		if(predicateStack.empty() == false && predicateStack.peek() instanceof DisjunctivePredicate) {
			if(constructChild != null)
				constructChild = new Selection(constructChild, (DisjunctivePredicate)predicateStack.pop());
			else
				constructChild = new Selection(scans.get(lastTreePatternInsideXPathPredicateIndex), (DisjunctivePredicate)predicateStack.pop());
		}
		 
		insideXPathPredicate = false;
		
		return null;
	}

	/**
	 * orExpr_xp
	 * orExpr_xp : andExpr_xp (OR andExpr_xp)* ;
	 */
	public Void visitOrExpr_xp(XQueryParser.OrExpr_xpContext ctx) { 
		visitChildren(ctx); 

		//if we're inside an XPath predicate we build a DisjunctivePredicate with the ConjunctivePredicate objects in the predicate stack
		if(insideXPathPredicate) {
			ArrayList<ConjunctivePredicate> conjPreds = new ArrayList<ConjunctivePredicate>();
			
			//collect all pending ConjunctivePredicates
			while(predicateStack.empty() == false && predicateStack.peek() instanceof ConjunctivePredicate)
				conjPreds.add((ConjunctivePredicate) predicateStack.pop());
			
			if(conjPreds.size() > 0) {
				DisjunctivePredicate disjPred = new DisjunctivePredicate(conjPreds);
				predicateStack.push(disjPred);
			}
		}
		
		return null;
	}
	
	/**
	 * andExpr_xp
	 * andExpr_xp : equalityExpr_xp ( AND equalityExpr_xp )* ;
	 */
	public Void visitAndExpr_xp(XQueryParser.AndExpr_xpContext ctx) { 
		visitChildren(ctx); 

		//if we're inside an XPath predicate we build a ConjunctivePredicate with the SimplePredicate objects in the predicate stack
		if(insideXPathPredicate) {
			ArrayList<SimplePredicate> simplePreds = new ArrayList<SimplePredicate>();
			
			//collect all pending SimplePredicates
			while(predicateStack.empty() == false && predicateStack.peek() instanceof SimplePredicate)
				simplePreds.add((SimplePredicate) predicateStack.pop());
			
			if(simplePreds.size() > 0) {
				ConjunctivePredicate conjPred = new ConjunctivePredicate(simplePreds);
				predicateStack.push(conjPred);
			}
		}
		
		return null;
	}

	/**
	 * comparativeExpr_xp
	 * comparativeExpr_xp : arithmeticExpr_xp ( ( EQ_S | NE_S | LT_S | GT_S | LE_S | GE_S) arithmeticExpr_xp)* ;
	 */
	/*public Void visitComparativeExpr_xp(XQueryParser.ComparativeExpr_xpContext ctx) {
		if(insideXPathPredicate == false) {
			visitChildren(ctx); 
		}
		else {
			//check the xpath predicate has a given form 
			if(ctx.arithmeticExpr_xp().size() != 2 || ctx.arithmeticExpr_xp(0).unaryExpr(0).valueExpr().pathExpr() == null)
				throw new PAXQueryExecutionException("This type of XPath predicate is not supported yet: "+ctx.getText());
			
			SimplePredicate predicate = null;
			PredicateType predType = PredicateType.parse(ctx.getChild(1).getText());
			String leftExpr;
			String rightExpr;
			ArithmeticOperation arithOpLeft = null;
			ArithmeticOperation arithOpRight = null;
			Operation leftOp = null;
			Operation rightOp = null;
			double leftOperand = 0;
			double rightOperand = 0;
			Double rightDoubleLiteral = null;
			String rightStringLiteral = null;
			
			//manually visit the left arithmetic expression (it should contain a valid xpath expression)
			lastNodeInsideXPathPredicate = null;	//clear lastNodeInsideXPathPredicate
			lastVarXPathPredicate = "";
			visitChildren(ctx.arithmeticExpr_xp(0));
			leftExpr = lastVarXPathPredicate;
			//in case the left expression is a real arithmetic expression 
			if(ctx.arithmeticExpr_xp(0).children.size() > 1) {
				leftOp = Operation.parse(ctx.arithmeticExpr_xp(0).getChild(1).getText());
				if(ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr() != null &&
						ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal() != null &&	
						ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null)  {
					if(ctx.arithmeticExpr_xp(0).unaryExpr().size() > 1 && ctx.arithmeticExpr_xp(0).unaryExpr(1).OP_SUB().size() > 0)
						leftOperand = Double.parseDouble("-"+ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					else
						leftOperand = Double.parseDouble(ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					arithOpLeft = new ArithmeticOperation(leftOp, leftOperand);
				}
				else
					throw new PAXQueryExecutionException("This type of XPath predicate is not supported yet: "+ctx.getText());
			}
			//now visit the right arithmetic expression (it may contain (1) xpath with arithmetic expression, (2) double literal, or (3) string literal
			lastNodeInsideXPathPredicate = null;	//clear lastNodeInsideXPathPredicate
			lastVarXPathPredicate = "";	//clear lastVarXPathPredicate
			visitChildren(ctx.arithmeticExpr_xp(1));
			rightExpr = lastVarXPathPredicate;
			//in case the right expression is a real arithmetic expression
			if(ctx.arithmeticExpr_xp(1).children.size() > 1) {
				rightOp = Operation.parse(ctx.arithmeticExpr_xp(1).getChild(1).getText());
				if(ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr() != null &&
						ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal() != null &&
								ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null) {
					if(ctx.arithmeticExpr_xp(1).unaryExpr().size() > 1 && ctx.arithmeticExpr_xp(1).unaryExpr(1).OP_SUB().size() > 0)
						rightOperand = Double.parseDouble("-"+ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					else
						rightOperand = Double.parseDouble(ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					arithOpRight = new ArithmeticOperation(rightOp, rightOperand);
				}
			} 
			else if (ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr() != null &&
					ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal() != null) {
				//double literal case
				if(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null) {
					if(ctx.arithmeticExpr_xp(1).unaryExpr(0).OP_SUB().size() == 0)	//positive number
						rightDoubleLiteral = Double.parseDouble(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					else //negative number
						rightDoubleLiteral = Double.parseDouble("-"+ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
				}
				//string literal case
				else if(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().STRING_LITERAL() != null) {
					rightStringLiteral = XQueryUtils.sanitizeStringLiteral(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().STRING_LITERAL().getText());
				}
				
			}

			int patternTreeIndexLeft = XQueryUtils.findVarInPatternTree(scans,  patternNodeMap,  leftExpr);
			//now instantiate the predicate
			if(leftExpr.compareTo("") != 0) {
				if(rightExpr.compareTo("") != 0)
					predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), arithOpRight, predType);
					
				else if(rightStringLiteral != null) {
					predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, rightStringLiteral, predType);
				}
				else if(rightDoubleLiteral != null)
					predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, rightDoubleLiteral, predType);
				if(predicate != null)
					predicateStack.push(predicate);
				lastTreePatternInsideXPathPredicateIndex = patternTreeIndexLeft;
				treePatternVisited.set(patternTreeIndexLeft, true);
			}
		}
		
		return null;
	}*/
	public Void visitComparativeExpr_xp(XQueryParser.ComparativeExpr_xpContext ctx) {
		if(insideXPathPredicate == false) {
			visitChildren(ctx); 
		}
		else {
			//check the xpath predicate has a given form 
			if(ctx.arithmeticExpr_xp().size() != 2 || ctx.arithmeticExpr_xp(0).unaryExpr(0).valueExpr().pathExpr() == null)
				throw new PAXQueryExecutionException("This type of XPath predicate is not supported yet: "+ctx.getText());
			
			SimplePredicate predicate = null;
			PredicateType predType = PredicateType.parse(ctx.getChild(1).getText());
			String leftExpr;
			String rightExpr;
			ArithmeticOperation arithOpLeft = null;
			ArithmeticOperation arithOpRight = null;
			Operation leftOp = null;
			Operation rightOp = null;
			double leftOperand = 0;
			double rightOperand = 0;
			Double rightDoubleLiteral = null;
			String rightStringLiteral = null;
			
			//manually visit the left arithmetic expression (it should contain a valid xpath expression)
			lastNodeInsideXPathPredicate = null;	//clear lastNodeInsideXPathPredicate
			lastVarXPathPredicate = "";
			visitChildren(ctx.arithmeticExpr_xp(0));
			leftExpr = lastVarXPathPredicate;
			//in case the left expression is a real arithmetic expression 
			if(ctx.arithmeticExpr_xp(0).children.size() > 1) {
				leftOp = Operation.parse(ctx.arithmeticExpr_xp(0).getChild(1).getText());
				if(ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr() != null &&
						ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal() != null &&	
						ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null)  {
					if(ctx.arithmeticExpr_xp(0).unaryExpr().size() > 1 && ctx.arithmeticExpr_xp(0).unaryExpr(1).OP_SUB().size() > 0)
						leftOperand = Double.parseDouble("-"+ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					else
						leftOperand = Double.parseDouble(ctx.arithmeticExpr_xp(0).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					arithOpLeft = new ArithmeticOperation(leftOp, leftOperand);
				}
				else
					throw new PAXQueryExecutionException("This type of XPath predicate is not supported yet: "+ctx.getText());
			}
			//now visit the right arithmetic expression (it may contain only (1) double literal, or (3) string literal
			//lastNodeInsideXPathPredicate = null;	//clear lastNodeInsideXPathPredicate
			//lastVarXPathPredicate = "";	//clear lastVarXPathPredicate
			//visitChildren(ctx.arithmeticExpr_xp(1));
			//rightExpr = lastVarXPathPredicate;
			//in case the right expression is a real arithmetic expression
			//if(ctx.arithmeticExpr_xp(1).children.size() > 1) {
			//	rightOp = Operation.parse(ctx.arithmeticExpr_xp(1).getChild(1).getText());
			//	if(ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr() != null &&
			//			ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal() != null &&
			//					ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null) {
			//		if(ctx.arithmeticExpr_xp(1).unaryExpr().size() > 1 && ctx.arithmeticExpr_xp(1).unaryExpr(1).OP_SUB().size() > 0)
			//			rightOperand = Double.parseDouble("-"+ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
			//		else
			//			rightOperand = Double.parseDouble(ctx.arithmeticExpr_xp(1).unaryExpr(1).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
			//		arithOpRight = new ArithmeticOperation(rightOp, rightOperand);
			//	}
			//} 
			//else
			if (ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr() != null &&
					ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal() != null) {
				//double literal case
				if(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral() != null) {
					if(ctx.arithmeticExpr_xp(1).unaryExpr(0).OP_SUB().size() == 0)	//positive number
						rightDoubleLiteral = Double.parseDouble(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
					else //negative number
						rightDoubleLiteral = Double.parseDouble("-"+ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().numericLiteral().getText());
				}
				//string literal case
				else if(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().STRING_LITERAL() != null) {
					rightStringLiteral = XQueryUtils.sanitizeStringLiteral(ctx.arithmeticExpr_xp(1).unaryExpr(0).valueExpr().filterExpr().primaryExpr().literal().STRING_LITERAL().getText());
				}
				
			}

			//int patternTreeIndexLeft = XQueryUtils.findVarInPatternTree(scans,  patternNodeMap,  leftExpr);
			//now instantiate the predicate
			//if(leftExpr.compareTo("") != 0) {
				//if(rightExpr.compareTo("") != 0)
				//	//predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), leftExpr, arithOpLeft, varMap.getTemporaryPositionByName(rightExpr), rightExpr, arithOpRight, predType);
				//	predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, varMap.getTemporaryPositionByName(rightExpr), varMap.getVariable(rightExpr), arithOpRight, predType);
					
				//else 
				if(rightStringLiteral != null) {
					//predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), leftExpr, arithOpLeft, rightStringLiteral, predType);
					//predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, rightStringLiteral, predType);
					lastNodeInsideXPathPredicate.setSelectOnValue(true, predType, rightStringLiteral);
					//lastNodeInsideXPathPredicate.setStoresValue(true);
				}
				else if(rightDoubleLiteral != null) {
					//predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), leftExpr, arithOpLeft, rightDoubleLiteral, predType);
					//predicate = new SimplePredicate(varMap.getTemporaryPositionByName(leftExpr), varMap.getVariable(leftExpr), arithOpLeft, rightDoubleLiteral, predType);
					lastNodeInsideXPathPredicate.setSelectOnValue(true, predType, rightDoubleLiteral);
					//lastNodeInsideXPathPredicate.setStoresValue(true);
				}
				//if(predicate != null)
				//	predicateStack.push(predicate);
				//lastTreePatternInsideXPathPredicateIndex = patternTreeIndexLeft;
				//treePatternVisited.set(patternTreeIndexLeft, true);					
			//}
		}
		
		return null;
	}
	
	/**
	 * groupBy
	 * groupBy : 'group by' VAR (COMMA VAR)* ;
	 */
	public Void visitGroupBy(XQueryParser.GroupByContext ctx) { 
		return visitChildren(ctx);
	}

	/**
	 * abbrevForwardStep (xpath)
	 * Decide whether the next qName represents an xml element (e.g. whatever/element) or attribute (e.g. whatever/@attribute)
	 */
	public Void visitAbbrevForwardStep(XQueryParser.AbbrevForwardStepContext ctx) {
		if(ctx.getText().startsWith("@")) 
			nextNodeIsAttribute = true;	//there must exist another way of detecting tokens
		visit(ctx.nodeTest());
		
		return null;
	}

	/**
	 * nameTest_qName (in XPath)
	 * Create a pattern node, set it as content storage, attach it to the appropriate tree and store it in patternNodeMap
	 * Xpath: whatever/node/whatever
	 */
	/*public Void visitNameTest(XQueryParser.NameTestContext ctx) { 
		String tag = "";
		if(ctx.qName() != null)
			tag = ctx.qName().QNAME_TOKEN().getText();
		else if(ctx.getChild(0).getText().compareTo("div") == 0)
			tag = "div";
		else if(ctx.getChild(0).getText().compareTo("mod") == 0)
			tag = "mod";
		
		//check whether the node already exists, we need to consider the type of edge too
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;

		int nodeCode = -1;
		NavigationTreePatternNode childNode = null;
				
		//NOT IN A XPATH PREDICATE
		if(insideXPathPredicate == false) {	
			childNode = lastNode.getChild(tag, parent);
			
			//If the node child does not exist then create a new one
			if(childNode == null) {
				nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
				childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
				
				if(doNesting) {
					//handle let-nesting
					if(currentStatement == StatementType.LET) {
						lastNode.addEdge(childNode, parent, true, true);
						currentStatement = StatementType.NONE;
					}
					else
						lastNode.addEdge(childNode, parent, false, false); 		//parameters: addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)
				}
				else
					lastNode.addEdge(childNode, parent, false, false);	//don't handle let-nesting
			}

			lastNode.removeMatchingVariables(lastVarLeftSide);
			varMap.removeVariable(lastVarLeftSide);
			if(lastNode.checkAnyMatchingVariableStoresValue() == false)
				lastNode.setStoresValue(false);
			if(lastNode.checkAnyMatchingVariableStoresContent() == false)
				lastNode.setStoresContent(false);
			Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, childNode);
			varMap.addNewVariable(newVar);
			childNode.addMatchingVariables(newVar);
			childNode.setStoresContent(true);
			if(nextNodeIsAttribute) {
				//attributes store values
				newVar.dataType = Variable.VariableDataType.Value;
				childNode.setAttribute(true);
				childNode.setStoresValue(true);
				childNode.setStoresContent(false);
				nextNodeIsAttribute = false;
			}
			//update the affected variable so it points to this node
			patternNodeMap.put(lastVarLeftSide, childNode);
			lastNode = childNode;	//for further expansion				
		}
		//INSIDE AN XPATH PREDICATE
		else {	
			if(lastNodeInsideXPathPredicate == null) {
				childNode = lastNode.getChild(tag, true);
				if(childNode == null) {
					nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
					childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
					lastNode.addEdge(childNode, true, false, false);
				}				
				if(lastVarXPathPredicate == "")
					lastVarXPathPredicate = XQueryUtils.getNextAuxVariableName();
			}
			else {
				childNode = lastNodeInsideXPathPredicate.getChild(tag, parent);
				if(childNode == null) {
					nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
					childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
					//boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;
					lastNodeInsideXPathPredicate.addEdge(childNode, parent, false, false);
				}					
				//remove the last variable from the last node inside the predicate
				lastNodeInsideXPathPredicate.removeMatchingVariables(lastVarXPathPredicate);
				varMap.removeVariable(lastVarXPathPredicate);
				if(lastNodeInsideXPathPredicate.checkAnyMatchingVariableStoresValue() == false)
					lastNodeInsideXPathPredicate.setStoresValue(false);
				if(lastNodeInsideXPathPredicate.checkAnyMatchingVariableStoresContent() == false)
					lastNodeInsideXPathPredicate.setStoresContent(false);
			}
			//add a new aux variable to the appropriate node
			Variable newVar = new Variable(lastVarXPathPredicate, Variable.VariableDataType.Content, childNode);
			varMap.addNewVariable(newVar);
			childNode.addMatchingVariables(newVar);
			childNode.setStoresContent(true);
			if(nextNodeIsAttribute) {
				newVar.dataType = Variable.VariableDataType.Value;
				childNode.setAttribute(true);
				childNode.setStoresValue(true);
				childNode.setStoresContent(false);
				nextNodeIsAttribute = false;
			}
			//update the affected variable so it points to this node
			patternNodeMap.put(lastVarXPathPredicate, childNode);
			lastNodeInsideXPathPredicate = childNode;
		}
		
		if(subqueryLevel > -1)	//we're inside a subquery, add this tree pattern to the list of those tree patterns in the inner side of the subquery
			navigationTreePatternsInsideSubquery.addElementIfNotContained(subqueryLevel, lastTreePattern);
			
		return null;
	}*/
	public Void visitNameTest(XQueryParser.NameTestContext ctx) { 
		String tag = "";
		if(ctx.qName() != null)
			tag = ctx.qName().QNAME_TOKEN().getText();
		else if(ctx.getChild(0).getText().compareTo("div") == 0)
			tag = "div";
		else if(ctx.getChild(0).getText().compareTo("mod") == 0)
			tag = "mod";
		
		//check whether the node already exists, we need to consider the type of edge too
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;

		int nodeCode = -1;
		NavigationTreePatternNode childNode = null;
				
		//NOT IN A XPATH PREDICATE
		if(insideXPathPredicate == false) {	
			childNode = lastNode.getChild(tag, parent);
			
			//If the node child does not exist then create a new one
			if(childNode == null) {
				nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
				childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
				
				if(doNesting) {
					//handle let-nesting
					if(currentStatement == StatementType.LET) {
						lastNode.addEdge(childNode, parent, true, true);
						currentStatement = StatementType.NONE;
					}
					else
						lastNode.addEdge(childNode, parent, false, false); 		//parameters: addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)
				}
				else
					lastNode.addEdge(childNode, parent, false, false);	//don't handle let-nesting
			}

			lastNode.removeMatchingVariables(lastVarLeftSide);
			varMap.removeVariable(lastVarLeftSide);
			if(lastNode.checkAnyMatchingVariableStoresValue() == false)
				lastNode.setStoresValue(false);
			if(lastNode.checkAnyMatchingVariableStoresContent() == false)
				lastNode.setStoresContent(false);
			Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, childNode);
			varMap.addNewVariable(newVar);
			childNode.addMatchingVariables(newVar);
			childNode.setStoresContent(true);
			if(nextNodeIsAttribute) {
				//attributes store values
				newVar.dataType = Variable.VariableDataType.Value;
				childNode.setAttribute(true);
				childNode.setStoresValue(true);
				childNode.setStoresContent(false);
				nextNodeIsAttribute = false;
			}
			//update the affected variable so it points to this node
			patternNodeMap.put(lastVarLeftSide, childNode);
			lastNode = childNode;	//for further expansion				
		}
		//INSIDE AN XPATH PREDICATE
		else {	
			boolean nested = currentStatement == StatementType.LET ? true : false;
			boolean optional = currentStatement == StatementType.LET ? true : false;
			if(lastNodeInsideXPathPredicate == null) {
				childNode = lastNode.getChild(tag, true);
				if(childNode == null) {
					nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
					childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
					lastNode.addEdge(childNode, true, false, false);
					//lastNode.addEdge(childNode, true, nested, optional);
				}				
				if(lastVarXPathPredicate == "")
					lastVarXPathPredicate = XQueryUtils.getNextAuxVariableName();
			}
			else {
				childNode = lastNodeInsideXPathPredicate.getChild(tag, parent);
				if(childNode == null) {
					nodeCode = NavigationTreePatternNode.globalNodeCounter.getAndIncrement();
					childNode = new NavigationTreePatternNode("", tag, nodeCode, "", "", lastTreePattern);
					//lastNodeInsideXPathPredicate.addEdge(childNode, parent, false, false);
					lastNodeInsideXPathPredicate.addEdge(childNode, parent, nested, optional);
				}					
				//remove the last variable from the last node inside the predicate
				lastNodeInsideXPathPredicate.removeMatchingVariables(lastVarXPathPredicate);
				varMap.removeVariable(lastVarXPathPredicate);
				if(lastNodeInsideXPathPredicate.checkAnyMatchingVariableStoresValue() == false)
					lastNodeInsideXPathPredicate.setStoresValue(false);
				if(lastNodeInsideXPathPredicate.checkAnyMatchingVariableStoresContent() == false)
					lastNodeInsideXPathPredicate.setStoresContent(false);
			}
			
			//add a new aux variable to the appropriate node
			Variable newVar = new Variable(lastVarXPathPredicate, Variable.VariableDataType.Content, childNode);
			varMap.addNewVariable(newVar);
			childNode.addMatchingVariables(newVar);
			childNode.setStoresContent(true);
			if(nextNodeIsAttribute) {
				//newVar.dataType = Variable.VariableDataType.Value;
				childNode.setAttribute(true);
				childNode.setStoresValue(true);
				childNode.setStoresContent(false);
				nextNodeIsAttribute = false;
			}
			//update the affected variable so it points to this node
			//patternNodeMap.put(lastVarXPathPredicate, childNode);
			lastNodeInsideXPathPredicate = childNode;		
		}
		
		if(subqueryLevel > -1)	//we're inside a subquery, add this tree pattern to the list of those tree patterns in the inner side of the subquery
			navigationTreePatternsInsideSubquery.addElementIfNotContained(subqueryLevel, lastTreePattern);
			
		return null;
	}


	/**
	 * textTest (in XPath)
	 * Set the last pattern node in the tree as value storage when the following situation is found in XPath: whatever/text() or whatever//text()
	 * NOTE: we currently treat both whatever/text() and whatever//text() as whatever/text(), this is something we need to change in the future (see Github's issue stratosphere/paxquery#1)
	 * TODO: treat whatever/text() and whatever//text() differently (will need to consider grammar rules pathExpr and relativePathExpr2 too)
	 */
	public Void visitTextTest(XQueryParser.TextTestContext ctx) {
		if(insideXPathPredicate == false) {
			Variable matchingVar = lastNode.getMatchingVariable(lastVarLeftSide);
		
			if(matchingVar != null) {
				matchingVar.dataType = Variable.VariableDataType.Value;
				if(lastNode.checkAnyMatchingVariableStoresContent() == false)
					lastNode.setStoresContent(false);	//no vars storing content anymore
				lastNode.setStoresValue(true);
			}
			else {
				//lastVarLeftSide does not exist in lastNode.matchingVariables
				Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Value, lastNode);
				varMap.addNewVariable(newVar);
				lastNode.addMatchingVariables(newVar);
				lastNode.setStoresValue(true);
				patternNodeMap.put(lastVarLeftSide, lastNode);
			}
		}
		else {
			Variable matchingVar = lastNodeInsideXPathPredicate.getMatchingVariable(lastVarXPathPredicate);
			
			if(matchingVar != null) {
				matchingVar.dataType = Variable.VariableDataType.Value;
				if(lastNodeInsideXPathPredicate.checkAnyMatchingVariableStoresContent() == false)
					lastNodeInsideXPathPredicate.setStoresContent(false);	//no vars storing content any more
				lastNodeInsideXPathPredicate.setStoresValue(true);
			}
			else {
				//lastVarXPathPredicate does not exist in lastNodeInsideXPathPredicate.matchingVariables
				Variable newVar = new Variable(lastVarXPathPredicate, Variable.VariableDataType.Value, lastNodeInsideXPathPredicate);
				varMap.addNewVariable(newVar);
				lastNodeInsideXPathPredicate.addMatchingVariables(newVar);
				lastNodeInsideXPathPredicate.setStoresValue(true);
				patternNodeMap.put(lastVarXPathPredicate, lastNodeInsideXPathPredicate);
			}
		}

		return null;
	}
	
	/**
	 * returnStat
	 * Builds varsPos before parsing on. After that it visits the rules within the return
	 * and builds the data structures needed by XMLConstruct.
	 * 
	 * Terminates the program when an attribute is used as an XML value in the input query
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 */
	public Void visitReturnStat(XQueryParser.ReturnStatContext ctx) {
		insideReturn = true;
		operatorsProcessedInSubquery = false;
		//adjust parents
		if(constructChild != null) {
			logicalPlan.setRoot(constructChild);
			logicalPlan.adjustParents();
		}
		//prepare subquery-related operators
		if(subqueryLevel == -1) {
			for(BaseNestingOperatorInfo info : subqueryInfoList) {
				try {
					info.updateOperatorState();
				} catch(PAXQueryExecutionException paxe) {
					throw paxe;
				}
			}
		}
		//manually visit the next rule
		if(ctx.eleConst()!=null) {
			visit(ctx.eleConst());

			//update all varpaths in the nested ctp with the position of the outer variable
			//if(subqueryLevel > -1) {
			//	Variable outerVarObject = varMap.getVariable(outerVarSubquery);
			//	outerVarObject.updateNestedCTPWithVarPosition(varMap.getTemporaryPositionByName(outerVarObject.name));
			//}
		}
		//if not, we have an aggrExpr or a VAR
		else if(ctx.aggrExpr() != null || ctx.VAR() != null) {
			String varName;
			if(ctx.aggrExpr() != null)	//we have an aggrExpr
				varName = ctx.aggrExpr().VAR().getText();			 
			else	//we have a VAR
				varName = ctx.VAR().getText();

			Variable var = varMap.getVariable(varName);
			if(subqueryLevel == -1) {	//not in a subquery
				boolean buildConstructionTreePattern = true;
				ConstructionTreePatternNode root = null;
				int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
				if(var != null && (patternTreeIndex != -1 || var.dataType == Variable.VariableDataType.Subquery || var.dataType == Variable.VariableDataType.Aggregation)) {
					//for the case treePatternVisited.get(patternTreeIndex)==true the XMLScan associated to the returned variable is already in the algebraic tree, so we do nothing
					//for the case treePatternVisited.get(patternTreeIndex)==false the XMLScan associated to the returned variable has not been included in the algebraic tree, hence we plug the XMLScan to constructChild
					if(patternTreeIndex != -1 && treePatternVisited.get(patternTreeIndex) == false) {
						if(constructChild == null)
							constructChild = scans.get(patternTreeIndex);
						else
							constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndex));
						treePatternVisited.set(patternTreeIndex, true);
					}
					//create the root ConstructionTreePatternNode and instantiate the ConstructionTreePattern
					int[] varpath = null; 
					if(var.dataType == Variable.VariableDataType.Subquery && var.nestedCTPRoot != null) {
						//root = var.nestedCTPRoot;
						//constructionTreePattern.addDeepCopySubtreeDuplicateVarpaths(lastConstructionTreePatternNode, var.nestedCTPRoot);
						root = ConstructionTreePattern.deepCopySubtreeDuplicateVarpaths(var.nestedCTPRoot).getRoot();
						constructionTreePattern = root.getConstructionTreePattern();
						lastConstructionTreePatternNode = root;
						buildConstructionTreePattern = false;
					}
					else {
						if(var.node != null && var.node.getNestingDepth() > 0) {
							//nested let
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
						}
						else	//for or non-nested let
							varpath = new int[] {varMap.getTemporaryPositionByName(varName)};
						root = new ConstructionTreePatternNode(null,ContentType.VARIABLE_PATH, varpath, false);
					}
					
					if(ctx.aggrExpr() != null) {
						if(varpath == null) {
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
						}
						//REDO: for the case of subqueries, we need to check if aggregationtype is COUNT. In that case, calculate the varpath and input it to the Aggregation operator. If not COUNT, then Exception("you can not SUM,MAX,AVG,etc XML nodes!)
						//instantiate an Aggregation operator
						AggregationType aggrType = XQueryUtils.StringToAggregationType(ctx.aggrExpr().AGGR_FUNCT().getText());
						Variable aggrVar = new Variable(XQueryUtils.getNextAuxVariableName(), Variable.VariableDataType.Aggregation);
						varMap.addNewVariable(aggrVar);						
						Aggregation aggregation = new Aggregation(constructChild, varpath, aggrType);
						aggregation.setOuterVariable(aggrVar);
						aggregation.setInnerVariable(var);
						constructChild = aggregation;
						varpath = new int[] {varMap.getTemporaryPositionByName(aggrVar.name)};
						root = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpath, false);
						buildConstructionTreePattern = true;
					}
					
					//if(constructionTreePattern == null) {
					if(buildConstructionTreePattern) {
						//ConstructionTreePatternNode root = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpath, false);
						constructionTreePattern = new ConstructionTreePattern(root);
						root.setConstructionTreePattern(constructionTreePattern);
						lastConstructionTreePatternNode = root;
					}
				}	
				else
					throw new PAXQueryExecutionException("The variable "+ varName +" was not previously assigned.");
			}	
			else {	//inside a subquery. So far we assume there is only one inner TP (one TP being navigated in the subquery and nowhere else)
				if(subqueryWithWhere == true) {
					//if(scans.size() != 2)
					//	throw new PAXQueryExecutionException("Currently only one outer Tree Pattern and one inner Tree Pattern are supported in Left Nested Outer Joins.");
					//get the inner TP
					NavigationTreePattern innerTP = null;
					BaseLogicalOperator rightChild = null;
					int tpIndex = -1;
					if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) == 1) {
						innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
						if(innerTP != null) {
							logicalPlan.setLeaves(scans);
							rightChild = logicalPlan.getTopFromLeaf(innerTP);
							tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
							if(tpIndex != -1)
								treePatternVisited.set(tpIndex,  true);
						}
					}
					else 
						throw new PAXQueryExecutionException("Currently only one inner Tree Pattern is supported in Left Nested Outer Joins.");
					
					//for the other trees: create CPs if needed
					//if constructChild == null, do CP with all outer XMLScans 
					ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
					if(constructChild == null) {// && scans.size() > 1) {
						if(scans.size() > 1) {
							for(int i = 0; i < scans.size(); i++) {
								if(logicalPlan.getTopFromLeaf(scans.get(i)) != rightChild) {
									if(constructChild == null)
										constructChild = scans.get(i);
									else
										constructChild = new CartesianProduct(constructChild, scans.get(i));
									treePatternVisited.set(i, true);
									outerScans.add(scans.get(i));
								}
							}
						}
						else {
							constructChild = scans.get(0);
							treePatternVisited.set(0, true);
							outerScans.add(scans.get(0));
						}
					}
					else {
						for(int i = 0; i < scans.size(); i++) {
							BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(i));
							if(topFromLeaf != rightChild) {
								if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
									constructChild = new CartesianProduct(constructChild, topFromLeaf);
								treePatternVisited.set(i,  true);
								outerScans.add(scans.get(i));
							}
						}
						
					}		
					//create the variable that contains the query
					Variable outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
					varMap.addNewVariable(outerVarObject);

					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					//outerVarObject.nestedPaths.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true) {
						list.add(-1);
					//	outerVarObject.nestedPaths.add(varMap.getTemporaryPositionByName(varName));
					}
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);

					//create a CTPNode with a varpath for the innervariable of the outervariable
					//ConstructionTreePatternNode ctpnode = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpath, false);
					ConstructionTreePatternNode ctpnode = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarObject.name);
					
					//create a CTPNode with the varpath of the outervariable itself
					int[] varpathouter = new int[1];
					varpathouter[0] = varMap.getTemporaryPositionByName(outerVarObject.name);
					ConstructionTreePatternNode ctpnodeouter = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpathouter, false);
					
					
					//instantiate the inner construction tree pattern
					//subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnode);
					subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnodeouter);
					ctpnode.setConstructionTreePattern(subqueryConstructionTreePattern);					
					ctpnodeouter.setConstructionTreePattern(subqueryConstructionTreePattern);
					subqueryConstructionTreePattern.addChild(ctpnodeouter, ctpnode);
					
					//add the variable that contains the subquery
					//connect the new variable to the new CTP
					//outerVarObject.setNestedCTP(ctpnode);
					outerVarObject.setNestedCTP(ctpnodeouter);
					//varMap.addNewVariable(outerVarObject);
					
					//outerVarObject.updateNestedCTPWithVarPosition(varMap.getTemporaryPositionByName(outerVarObject.name));

					
					
					
					
					
					//get the predicate
					BasePredicate predicate = predicateStack.pop();
					if(predicate == null)
						throw new PAXQueryExecutionException("The predicate for the Left Outer Nested Join does not exist.");
					//instantiate the LeftOuterNestedJoin operator
					constructChild = new LeftOuterNestedJoin(constructChild, rightChild, predicate, -1, new int[0]);
					//instantiate a LeftOuterNestedJoinInfo for later update of the GroupBy operator
					subqueryInfoList.add(new LeftOuterNestedJoinInfo((LeftOuterNestedJoin)constructChild , outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
					
					subqueryWithWhere = false;
				}
				else {	//subquery without a where, we build a chain of Cartesian Products with a GroupBy on top
					ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
					//BaseOperator rightChild = inner tree
					NavigationTreePattern innerTP = null;
					BaseLogicalOperator rightChild = null;
					int tpIndex = -1;
					if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) > 0) {
						innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
						if(innerTP != null) {
							logicalPlan.setLeaves(scans);
							rightChild = logicalPlan.getTopFromLeaf(innerTP);		
							tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
							if(tpIndex != -1)
								treePatternVisited.set(tpIndex, true);
						}
					}				
					//for the other trees: create CPs if needed
					//if constructChild == null, do CP with all outer XMLScans 
					if(constructChild == null) {// && scans.size() > 1) {
						if(scans.size() > 1) {
							for(int i = 0; i < scans.size(); i++) {
								if(logicalPlan.getTopFromLeaf(scans.get(i)) != rightChild) {
									if(constructChild == null)
										constructChild = scans.get(i);
									else
										constructChild = new CartesianProduct(constructChild, scans.get(i));
									treePatternVisited.set(i, true);
									outerScans.add(scans.get(i));
								}
							}
						}
						else {
							constructChild = scans.get(0);
							treePatternVisited.set(0, true);
							outerScans.add(scans.get(0));
						}
					}
					else {
						for(int i = 0; i < scans.size(); i++) {
							BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(i));
							if(topFromLeaf != rightChild) {
								if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
									constructChild = new CartesianProduct(constructChild, topFromLeaf);
								treePatternVisited.set(i,  true);
								outerScans.add(scans.get(i));
							}
						}
					}				
					//instantiate CP between the outer and the inner scans
					constructChild = new CartesianProduct(constructChild, rightChild);
					
					
					//add the variable that contains the subquery
					Variable outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
					varMap.addNewVariable(outerVarObject);

					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true)
						list.add(-1);
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);

					//crate a CTPNode with a varpath for the innervariable of the outervariable
					ConstructionTreePatternNode ctpnode = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarObject.name);
					//subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnode);
					//ctpnode.setConstructionTreePattern(subqueryConstructionTreePattern);

					//////////
					//create a CTPNode with the varpath of the outervariable itself
					int[] varpathouter = new int[1];
					varpathouter[0] = varMap.getTemporaryPositionByName(outerVarObject.name);
					ConstructionTreePatternNode ctpnodeouter = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, varpathouter, false);

					//instantiate the inner construction tree pattern
					//subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnode);
					subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnodeouter);
					//ctpnode.setConstructionTreePattern(subqueryConstructionTreePattern);					
					ctpnodeouter.setConstructionTreePattern(subqueryConstructionTreePattern);
					subqueryConstructionTreePattern.addChild(ctpnodeouter, ctpnode);
					
					//add the variable that contains the subquery
					//connect the new variable to the new CTP
					//outerVarObject.setNestedCTP(ctpnode);
					outerVarObject.setNestedCTP(ctpnodeouter);
					//varMap.addNewVariable(outerVarObject);

					///////////

					
					
					//add the variable that contains the subquery
					//Variable outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
					//connect the new variable to the new CTP
					//outerVarObject.setNestedCTP(ctpnode);
					//varMap.addNewVariable(outerVarObject);
					//outerVarObject.updateNestedCTPWithVarPosition(varMap.getTemporaryPositionByName(outerVarObject.name));
					//instantiate a temporary empty GroupBy operator
					constructChild = new GroupBy(constructChild, new int[0], new int[0], new int[0]);
					//instantiate a XQueryGroupByInfo for later update of the GroupBy operator
					subqueryInfoList.add(new GroupByInfo((GroupBy)constructChild, outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
				}
			}
		}		
		operatorsProcessedInSubquery = false;
		insideReturn = false;

		return null;
	}
	
	/**
	 * visitEleConst
	 * TODO: for the case eleConst : LT_S eaName att* CLOSE_OPENING_TAG we just plug the first XMLScan object to the XMLConstruct object. ANY BETTER SOLUTION?
	 */
	/*public Void visitEleConst(XQueryParser.EleConstContext ctx) {
		String tag = ctx.eaName(0).getText();
		ConstructionTreePatternNode node = new ConstructionTreePatternNode(ContentType.ELEMENT, tag, false);
		if(subqueryLevel == -1) {	//not in a sub-query
			if(constructionTreePattern == null)
				constructionTreePattern = new ConstructionTreePattern(node);
			else {
				constructionTreePattern.addChild(lastConstructionTreePatternNode, node);
				node.setConstructionTreePattern(constructionTreePattern);
			}
			lastConstructionTreePatternNode = node;
		}
		else {	//inside a sub-query
			if(subqueryConstructionTreePattern == null)
				subqueryConstructionTreePattern = new ConstructionTreePattern(node);
			else {
				subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, node);
				node.setConstructionTreePattern(subqueryConstructionTreePattern);
			}
			lastSubqueryConstructionTreePatternNode = node;
		}
		
		//manually visit all attributes
		for(int i = 0; i < ctx.att().size(); i++)
			visit(ctx.att(i));
		//if "eleConst : LT_S eaName att* CLOSE_OPENING_TAG" then we're done

		if(subqueryLevel == -1) {	//not inside a sub-query
			//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
			if(ctx.GT_S().size() > 0) {
				for(int i = 0; i < ctx.children.size(); i++) {
					lastConstructionTreePatternNode = node;
					//we find an eleConst rule
					if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class ||
							ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstInnerContext.class) {
						visit(ctx.getChild(i));
					}
				}
				lastConstructionTreePatternNode = node.getParent();					
			}
		}
		else {	//inside a sub-query
			//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
			if(ctx.GT_S().size() > 0) {
				for(int i = 0; i < ctx.children.size(); i++) {
					lastSubqueryConstructionTreePatternNode = node;
					//we find an eleConst rule
					if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class ||
							ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstInnerContext.class) {
						visit(ctx.getChild(i));
					}
				}
				lastSubqueryConstructionTreePatternNode = node.getParent();					
			}
		}
		
		return null;
	}*/
	public Void visitEleConst(XQueryParser.EleConstContext ctx) {
		String tag = ctx.eaName(0).getText();
		ConstructionTreePatternNode node = new ConstructionTreePatternNode(ContentType.ELEMENT, tag, false);
		if(subqueryLevel == -1) {	//not in a sub-query
			if(constructionTreePattern == null)
				constructionTreePattern = new ConstructionTreePattern(node);
			else {
				constructionTreePattern.addChild(lastConstructionTreePatternNode, node);
				node.setConstructionTreePattern(constructionTreePattern);
			}
			lastConstructionTreePatternNode = node;
		}
		else {	//inside a sub-query
			if(subqueryConstructionTreePattern == null) {
				Variable outerVarObject = varMap.getVariable(outerVarSubquery);
				//if(varMap.getVariable(outerVarSubquery) == null) {
				if(outerVarObject == null) {
					outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
					varMap.addNewVariable(outerVarObject);
				}
				
				int[] outervar_varpath = { varMap.getTemporaryPositionByName(outerVarSubquery) };
				ConstructionTreePatternNode ctpnodeouter = new ConstructionTreePatternNode(null, ContentType.VARIABLE_PATH, outervar_varpath, false);
				subqueryConstructionTreePattern = new ConstructionTreePattern(ctpnodeouter);
				subqueryConstructionTreePattern.addChild(ctpnodeouter, node);
				ctpnodeouter.setConstructionTreePattern(subqueryConstructionTreePattern);
				node.setConstructionTreePattern(subqueryConstructionTreePattern);
				outerVarObject.setNestedCTP(subqueryConstructionTreePattern.getRoot());
				
				
			}
			else {
				subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, node);
				node.setConstructionTreePattern(subqueryConstructionTreePattern);
			}
			lastSubqueryConstructionTreePatternNode = node;
		}
		
		//manually visit all attributes
		for(int i = 0; i < ctx.att().size(); i++)
			visit(ctx.att(i));
		//if "eleConst : LT_S eaName att* CLOSE_OPENING_TAG" then we're done

		if(subqueryLevel == -1) {	//not inside a sub-query
			//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
			if(ctx.GT_S().size() > 0) {
				for(int i = 0; i < ctx.children.size(); i++) {
					lastConstructionTreePatternNode = node;
					//we find an eleConst rule
					if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class ||
							ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstInnerContext.class) {
						visit(ctx.getChild(i));
					}
				}
				lastConstructionTreePatternNode = node.getParent();					
			}
		}
		else {	//inside a sub-query
			//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
			if(ctx.GT_S().size() > 0) {
				for(int i = 0; i < ctx.children.size(); i++) {
					lastSubqueryConstructionTreePatternNode = node;
					//we find an eleConst rule
					if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class ||
							ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstInnerContext.class) {
						visit(ctx.getChild(i));
					}
				}
				lastSubqueryConstructionTreePatternNode = node.getParent();					
			}
		}
		
		return null;
	}


	/**
	 * att
	 */
	public Void visitAtt(XQueryParser.AttContext ctx) {
		//ConstructionTreePatternNode auxNode;
		ConstructionTreePatternNode attNode; 
		if(subqueryLevel == -1) {	//not in a sub-query
			//XML node for the attribute itself
			attNode = new ConstructionTreePatternNode(constructionTreePattern, ContentType.ATTRIBUTE, ctx.eaName().getText(), false);
			constructionTreePattern.addChild(lastConstructionTreePatternNode, attNode);
			lastConstructionTreePatternNode = attNode;
			//XML node for the attribute content
			AttInnerContext attInner = ctx.attInner();
			if(attInner.attInner2() == null) 	//then we have attInner : STRING_LITERAL
				constructionTreePattern.addChild(attNode, new ConstructionTreePatternNode(constructionTreePattern, ContentType.ATTRIBUTE_VALUE, attInner.STRING_LITERAL().getText(), false));
			else {
				AttInner2Context attInner2 = attInner.attInner2();
				String varName;
				if(attInner2.aggrExpr() != null)
					varName = attInner2.aggrExpr().VAR().getText();
				else
					varName = attInner2.VAR().getText();
				Variable var = varMap.getVariable(varName);
				int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
				if(var != null && (patternTreeIndex != -1 || var.dataType == Variable.VariableDataType.Subquery) || var.dataType == Variable.VariableDataType.Aggregation) {
					//for the case treePatternVisited.get(patternTreeIndex)==true the XMLScan associated to the returned variable is already in the algebraic tree, so we do nothing
					//for the case treePatternVisited.get(patternTreeIndex)==false the XMLScan associated to the returned variable has not been included in the algebraic tree, hence we plug the XMLScan to constructChild
					if(patternTreeIndex != -1 && treePatternVisited.get(patternTreeIndex) == false) {
						if(constructChild == null)
							constructChild = scans.get(patternTreeIndex);
						else
							constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndex));
						treePatternVisited.set(patternTreeIndex, true);
					}
					//create the root ConstructionTreePatternNode and instantiate the ConstructionTreePattern
					int[] varpath = null; 
					ConstructionTreePatternNode ctpNodeToAdd = null;
					if(var.dataType == Variable.VariableDataType.Subquery && var.nestedCTPRoot != null) {
						var.nestedCTPRoot.setCTPFromHere(constructionTreePattern);
						ctpNodeToAdd = var.nestedCTPRoot;
					}
					else {
						if(var.node != null && var.node.getNestingDepth() > 0) {
							//we have a nested let
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
							
						}
						else	//for or non-nested let
							varpath = new int[] {varMap.getTemporaryPositionByName(varName)};
						ctpNodeToAdd = new ConstructionTreePatternNode(constructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					}				
					if(attInner2.aggrExpr() != null) {
						//varpath for the Aggregation operator
						if(varpath == null) {
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
						}
						//REDO: for the case of subqueries, we need to check if aggregationtype is COUNT. In that case, calculate the varpath and input it to the Aggregation operator. If not COUNT, then Exception("you can not SUM,MAX,AVG,etc XML nodes!)
						//instantiate an Aggregation operator
						AggregationType aggrType = XQueryUtils.StringToAggregationType(attInner2.aggrExpr().AGGR_FUNCT().getText());
						Variable aggrVar = new Variable(XQueryUtils.getNextAuxVariableName(), Variable.VariableDataType.Aggregation);
						varMap.addNewVariable(aggrVar);						
						Aggregation aggregation = new Aggregation(constructChild, varpath, aggrType);
						aggregation.setOuterVariable(aggrVar);
						aggregation.setInnerVariable(var);
						constructChild = aggregation;
						//varpath for the CTPNode
						varpath = new int[] {varMap.getTemporaryPositionByName(aggrVar.name)};
						ctpNodeToAdd = new ConstructionTreePatternNode(constructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					}
					
					constructionTreePattern.addDeepCopySubtreeDuplicateVarpaths(lastConstructionTreePatternNode, ctpNodeToAdd);
					lastConstructionTreePatternNode = lastConstructionTreePatternNode.getParent();
				}
			}
		}
		else {	//inside a subquery. So far we assume there is only one inner TP (one TP being navigated in the subquery and nowhere else)
			//XML node for the attribute itself
			attNode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.ATTRIBUTE, ctx.eaName().getText(), false);
			subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, attNode);
			lastSubqueryConstructionTreePatternNode = attNode;
			//XML node for the attribute content
			AttInnerContext attInner = ctx.attInner();
			if(attInner.attInner2() == null) 	//then we have attInner : STRING_LITERAL
				subqueryConstructionTreePattern.addChild(attNode, new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.ATTRIBUTE_VALUE, attInner.STRING_LITERAL().getText(), false));
			else {
				AttInner2Context attInner2 = attInner.attInner2();
				String varName;
				if(attInner2.aggrExpr() != null)
					varName = attInner2.aggrExpr().VAR().getText();
				else
					varName = attInner2.VAR().getText();

				Variable var = varMap.getVariable(varName);
				int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);

				ConstructionTreePatternNode ctpnode = null;
				Variable outerVarObject = null;
				//if(operatorsProcessedInSubquery == false) {	//we need to process the operators in this subquery
				if(subqueryWithWhere == true) {
					if(operatorsProcessedInSubquery == false) {	//we need to process the operators in this subquery
						//get the inner TP
						NavigationTreePattern innerTP = null;
						BaseLogicalOperator rightChild = null;
						int tpIndex = -1;
						if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) == 1) {
							innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
							if(innerTP != null) {
								logicalPlan.setLeaves(scans);
								rightChild = logicalPlan.getTopFromLeaf(innerTP);
								tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
								if(tpIndex != -1)
									treePatternVisited.set(tpIndex,  true);
							}
						}
						else 
							throw new PAXQueryExecutionException("Currently only one inner Tree Pattern is supported in Left Nested Outer Joins.");
						
						//for the other trees: create CPs if needed
						//if constructChild == null, do CP with all outer XMLScans 
						ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
						if(constructChild == null) {// && scans.size() > 1) {
							if(scans.size() > 1) {
								for(int i = 0; i < scans.size(); i++) {
									if(logicalPlan.getTopFromLeaf(scans.get(i)) != rightChild) {
										if(constructChild == null)
											constructChild = scans.get(i);
										else
											constructChild = new CartesianProduct(constructChild, scans.get(i));
										treePatternVisited.set(i, true);
										outerScans.add(scans.get(i));
									}
								}
							}
							else {
								constructChild = scans.get(0);
								treePatternVisited.set(0, true);
								outerScans.add(scans.get(0));
							}
						}
						else {
							for(int i = 0; i < scans.size(); i++) {
								BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(i)); 
								if(topFromLeaf != rightChild) {
									if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
										constructChild = new CartesianProduct(constructChild, topFromLeaf);
									treePatternVisited.set(i,  true);
									outerScans.add(scans.get(i));
								}
							}
						}	
						
						//add the variable that contains the subquery
						outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);					
						outerVarObject.setNestedCTP(subqueryConstructionTreePattern.getRoot());
						varMap.addNewVariable(outerVarObject);
						
						//get the predicate
						BasePredicate predicate = predicateStack.pop();
						if(predicate == null)
							throw new PAXQueryExecutionException("The predicate for the Left Outer Nested Join does not exist.");

						//instantiate the LeftOuterNestedJoin operator
						constructChild = new LeftOuterNestedJoin(constructChild, rightChild, predicate, -1, new int[0]);
						//instantiate a LeftOuterNestedJoinInfo for later update of the GroupBy operator
						subqueryInfoList.add(new LeftOuterNestedJoinInfo((LeftOuterNestedJoin)constructChild , outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
						
						operatorsProcessedInSubquery = true;
					}
					
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true)
						list.add(-1);
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);
					//create a CTPNode with a varpath for the innervariable of the outervariable
					ctpnode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarSubquery);					
					subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, ctpnode);
					lastSubqueryConstructionTreePatternNode = lastSubqueryConstructionTreePatternNode.getParent();
				}
				else {	//subquery without a where, we build a chain of Cartesian Products with a GroupBy on top
					if(operatorsProcessedInSubquery == false) {	//we need to process the operators in this subquery
						ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
						//BaseOperator rightChild = inner tree
						NavigationTreePattern innerTP = null;
						BaseLogicalOperator rightChild = null;
						int tpIndex = -1;
						if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) > 0) {
							innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
							if(innerTP != null) {
								logicalPlan.setLeaves(scans);
								rightChild = logicalPlan.getTopFromLeaf(innerTP);		
								tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
								if(tpIndex != -1)
									treePatternVisited.set(tpIndex, true);
							}
						}				
						//for the other trees: create CPs if needed
						//if constructChild == null, do CP with all outer XMLScans 
						if(constructChild == null) {// && scans.size() > 1) {
							if(scans.size() > 1) {
								for(int i = 0; i < scans.size(); i++) {
									if(logicalPlan.getTopFromLeaf(scans.get(i)) != rightChild) {
										if(constructChild == null)
											constructChild = scans.get(i);
										else
											constructChild = new CartesianProduct(constructChild, scans.get(i));
										treePatternVisited.set(i, true);
										outerScans.add(scans.get(i));
									}
								}
							}
							else {
								constructChild = scans.get(0);
								treePatternVisited.set(0, true);
								outerScans.add(scans.get(0));
							}
						}
						else {
							for(int i = 0; i < scans.size(); i++) {
								BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(i));
								if(topFromLeaf != rightChild) {
								//if(scans.get(i) != rightChild) {
									//BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(i)); 
									if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
										constructChild = new CartesianProduct(constructChild, topFromLeaf);
									treePatternVisited.set(i,  true);
									outerScans.add(scans.get(i));
								}
							}
						}				
						//instantiate CP between the outer and the inner scans
						constructChild = new CartesianProduct(constructChild, rightChild);
						
						//add the variable that contains the subquery
						outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
						//connect the new variable to the new CTP
						outerVarObject.setNestedCTP(subqueryConstructionTreePattern.getRoot());
						varMap.addNewVariable(outerVarObject);
						//instantiate a temporary empty GroupBy operator
						constructChild = new GroupBy(constructChild, new int[0], new int[0], new int[0]);
						//instantiate a XQueryGroupByInfo for later update of the GroupBy operator
						subqueryInfoList.add(new GroupByInfo((GroupBy)constructChild, outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
						
						operatorsProcessedInSubquery = true;
					}
					
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true)
						list.add(-1);
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);
					//create a CTPNode with a varpath for the innervariable of the outervariable
					ctpnode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarSubquery);
					subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, ctpnode);
					lastSubqueryConstructionTreePatternNode = lastSubqueryConstructionTreePatternNode.getParent();
				}
			}
		}
		return null;
	}

	/**
	 * aggrExpr
	 * TODO: currently we don't consider the aggregated expresion but the VAR within: obviously we need to address this differently
	 */
	/*public Void visitAggrExpr(XQueryParser.AggrExprContext ctx) {
		String aggrType = ctx.AGGR_FUNCT().getText();
		String varName = ctx.VAR().getText();
		Variable var = varMap.getVariable(varName);
		int[] varpath = null;
		
		if((var.node != null && var.node.getNestingDepth() > 0) || var.dataType == Variable.VariableDataType.Subquery) {
			//we have a nested let
			varpath = new int[2];
			varpath[0] = varMap.getTemporaryPositionByName(varName);
			varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
			if(subqueryLevel == -1) {
				//outside a subquery, we build upon constructChild
				if(constructChild == null) {
					int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
					constructChild = scans.get(patternTreeIndex);
					treePatternVisited.set(patternTreeIndex, true);
				}
				Variable aggrVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Aggregation);
				varMap.addNewVariable(aggrVar);
				constructChild = new Aggregation(constructChild, varpath, XQueryUtils.StringToAggregationType(aggrType), aggrVar, var);
				
			}
			else {
				//inside a subquery, we don't build upon constructChild
				int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
				Variable aggrVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Aggregation);
				varMap.addNewVariable(aggrVar);
				Aggregation aggrOperator = new Aggregation(scans.get(patternTreeIndex), varpath, XQueryUtils.StringToAggregationType(aggrType), aggrVar, var);
				scans.get(patternTreeIndex).setParent(aggrOperator);
				treePatternVisited.set(patternTreeIndex, true);
			}
		}
		else
			throw new PAXQueryExecutionException("The variable " + varName + " is not nested, therefore it can not be aggregated.");
		
		return null;
	}*/
	public Void visitAggrExpr(XQueryParser.AggrExprContext ctx) {
		String aggrType = ctx.AGGR_FUNCT().getText();
		String varName = ctx.VAR().getText();
		Variable var = varMap.getVariable(varName);
		int[] varpath = null;
		
		if((var.node != null && var.node.getNestingDepth() > 0) || var.dataType == Variable.VariableDataType.Subquery) {
			//we have a nested let
			varpath = new int[2];
			varpath[0] = varMap.getTemporaryPositionByName(varName);
			//varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
			//if(var.nestedPaths.size() > 0)
			if(var.dataType == Variable.VariableDataType.Subquery && var.nestedCTPRoot.getContentType() == ContentType.VARIABLE_PATH && var.nestedCTPRoot.getVarPath().size() > 1)
				varpath[1] = var.nestedCTPRoot.getVarPath().get(1);
			else
				varpath[1] = -1;
			
			if(subqueryLevel == -1) {
				//outside a subquery, we build upon constructChild
				if(constructChild == null) {
					int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
					constructChild = scans.get(patternTreeIndex);
					treePatternVisited.set(patternTreeIndex, true);
				}
				Variable aggrVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Aggregation);
				varMap.addNewVariable(aggrVar);
				constructChild = new Aggregation(constructChild, varpath, XQueryUtils.StringToAggregationType(aggrType), aggrVar, var);
				
			}
			else {
				//inside a subquery, we don't build upon constructChild
				int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
				Variable aggrVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Aggregation);
				varMap.addNewVariable(aggrVar);
				Aggregation aggrOperator = new Aggregation(scans.get(patternTreeIndex), varpath, XQueryUtils.StringToAggregationType(aggrType), aggrVar, var);
				scans.get(patternTreeIndex).setParent(aggrOperator);
				treePatternVisited.set(patternTreeIndex, true);
			}
		}
		else
			throw new PAXQueryExecutionException("The variable " + varName + " is not nested, therefore it can not be aggregated.");
		
		return null;
	}
	
	/**
	 * eleConstInner
	 * Terminates the execution if an attribute is used in the input query as an XML value
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 * 
	 * TODO: if a variable within an XML element stores an attribute, then we currently terminate the execution
	 * (since that is illegal). What we really should do is including it in the element's XML tag as an attribute
	 * e.g: <item>@attrib</item> --> <item attrib="@attrib"></item>
	 */		
	public Void visitEleConstInner(XQueryParser.EleConstInnerContext ctx) {
		String varName;
		boolean isAggrExpr = false;
		Variable outerVarObject = null;
		for(int i = 0; i < ctx.children.size(); i++) {
			varName = null;
			ParseTree child = ctx.getChild(i);
			//decide whether we have aggrExpr or VAR
			isAggrExpr = (!(child instanceof TerminalNode) && child.getPayload().getClass() == XQueryParser.AggrExprContext.class) ? true : false;

			////if VAR
			if(isAggrExpr)
				varName = ((XQueryParser.AggrExprContext)child).VAR().getText();
			else
				varName = child.getText();
			if(varName.startsWith("$") == false)
				continue;
			Variable var = varMap.getVariable(varName);
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
			
			if(subqueryLevel == -1) {	//not in a subquery
				if(var != null && (patternTreeIndex != -1 || var.dataType == Variable.VariableDataType.Subquery || var.dataType == Variable.VariableDataType.Aggregation)) {
					//for the case treePatternVisited.get(patternTreeIndex)==true the XMLScan associated to the returned variable is already in the algebraic tree, so we do nothing
					//for the case treePatternVisited.get(patternTreeIndex)==false the XMLScan associated to the returned variable has not been included in the algebraic tree, hence we plug the XMLScan to constructChild
					if(patternTreeIndex != -1 && treePatternVisited.get(patternTreeIndex) == false) {
						if(constructChild == null)
							constructChild = scans.get(patternTreeIndex);
						else
							constructChild = new CartesianProduct(constructChild, scans.get(patternTreeIndex));
						treePatternVisited.set(patternTreeIndex, true);
					}
					//create the root ConstructionTreePatternNode and instantiate the ConstructionTreePattern
					int[] varpath = null; 
					ConstructionTreePatternNode ctpNodeToAdd = null;
					if(var.dataType == Variable.VariableDataType.Subquery && var.nestedCTPRoot != null) {
						ctpNodeToAdd = var.nestedCTPRoot;
					}
					else {
						if(var.node != null && var.node.getNestingDepth() > 0) {
							//we have a nested let
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
						}
						else	//for or non-nested let
							varpath = new int[] {varMap.getTemporaryPositionByName(varName)};
						ctpNodeToAdd = new ConstructionTreePatternNode(constructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
						
						//check whether the varpath belongs to an attribute in the source document: in that case, make it hang from an new attribute node in the output
						//if(var.node != null && var.node.isAttribute() == true)
						//	artificialAttributeNodeName = var.node.getTag();
					}
					
					if(isAggrExpr) {
						//varpath for the Aggregation operator
						if(varpath == null) {
							varpath = new int[2];
							varpath[0] = varMap.getTemporaryPositionByName(varName);
							varpath[1] = -1;	//will be translated into a 0, which is the position of the nested tuples within this tuple. This is done to avoid confusions with the real 0-positioned variable, or to prevent crashes if no 0 position is found in varMap
						}
						//REDO: for the case of subqueries, we need to check if aggregationtype is COUNT. In that case, calculate the varpath and input it to the Aggregation operator. If not COUNT, then Exception("you can not SUM,MAX,AVG,etc XML nodes!)
						//instantiate an Aggregation operator
						AggregationType aggrType = XQueryUtils.StringToAggregationType(((XQueryParser.AggrExprContext)child).AGGR_FUNCT().getText());
						Variable aggrVar = new Variable(XQueryUtils.getNextAuxVariableName(), Variable.VariableDataType.Aggregation);
						varMap.addNewVariable(aggrVar);						
						Aggregation aggregation = new Aggregation(constructChild, varpath, aggrType);
						aggregation.setOuterVariable(aggrVar);
						aggregation.setInnerVariable(var);
						constructChild = aggregation;
						//varpath for the CTPNode
						varpath = new int[] {varMap.getTemporaryPositionByName(aggrVar.name)};
						ctpNodeToAdd = new ConstructionTreePatternNode(constructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					}
					
					if(var.node != null && var.node.isAttribute() == true && isAggrExpr == false)	{ //create the artificial attribute node in the output
						ConstructionTreePatternNode artificialAttributeNode = new ConstructionTreePatternNode(constructionTreePattern, ContentType.ATTRIBUTE, var.node.getTag(), false);
						//artificialAttributeNode.set
						constructionTreePattern.addChild(lastConstructionTreePatternNode, artificialAttributeNode);
						constructionTreePattern.addChild(artificialAttributeNode, ctpNodeToAdd);
					}
					else
						constructionTreePattern.addDeepCopySubtreeDuplicateVarpaths(lastConstructionTreePatternNode, ctpNodeToAdd);
				}	
			}
			else {	//inside a subquery. So far we assume there is only one inner TP (one TP being navigated in the subquery and nowhere else)
				ConstructionTreePatternNode ctpnode = null;
				if(subqueryWithWhere == true) {
					if(operatorsProcessedInSubquery == false) {	//we need to process the operators in this subquery
						//get the inner TP
						NavigationTreePattern innerTP = null;
						BaseLogicalOperator rightChild = null;
						int tpIndex = -1;
						if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) == 1) {
							innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
							if(innerTP != null) {
								logicalPlan.setLeaves(scans);
								rightChild = logicalPlan.getTopFromLeaf(innerTP);
								tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
								if(tpIndex != -1)
									treePatternVisited.set(tpIndex,  true);
							}
						}
						else 
							throw new PAXQueryExecutionException("Currently only one inner Tree Pattern is supported in Left Nested Outer Joins.");
						
						//for the other trees: create CPs if needed
						//if constructChild == null, do CP with all outer XMLScans 
						ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
						if(constructChild == null) {// && scans.size() > 1) {
							if(scans.size() > 1) {
								for(int scan_index = 0; scan_index < scans.size(); scan_index++) {
									if(logicalPlan.getTopFromLeaf(scans.get(scan_index)) != rightChild) {
										if(constructChild == null)
											constructChild = scans.get(scan_index);
										else
											constructChild = new CartesianProduct(constructChild, scans.get(scan_index));
										treePatternVisited.set(scan_index, true);
										outerScans.add(scans.get(scan_index));
									}
								}
							}
							else {
								constructChild = scans.get(0);
								treePatternVisited.set(0, true);
								outerScans.add(scans.get(0));
							}
						}
						else {
							for(int scan_index = 0; scan_index < scans.size(); scan_index++) {
								BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(scan_index)); 
								if(topFromLeaf != rightChild) {
									if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
										constructChild = new CartesianProduct(constructChild, topFromLeaf);
									treePatternVisited.set(scan_index,  true);
									outerScans.add(scans.get(scan_index));
								}
							}
						}		
						
						//instantiate the outer variable
						outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
						outerVarObject.setNestedCTP(subqueryConstructionTreePattern.getRoot());
						varMap.addNewVariable(outerVarObject);
						
						//process the predicate
						BasePredicate predicate = predicateStack.pop();
						if(predicate == null)
							throw new PAXQueryExecutionException("The predicate for the Left Outer Nested Join does not exist.");
						
						//instantiate the LeftOuterNestedJoin operator
						constructChild = new LeftOuterNestedJoin(constructChild, rightChild, predicate, -1, new int[0]);
						//instantiate a LeftOuterNestedJoinInfo for later update of the GroupBy operator
						subqueryInfoList.add(new LeftOuterNestedJoinInfo((LeftOuterNestedJoin)constructChild , outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
						
						operatorsProcessedInSubquery = true;
					}
						

					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true)
						list.add(-1);
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);
					//create a CTPNode with a varpath for the innervariable of the outervariable
					ctpnode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarSubquery);
					
					if(var.node != null && var.node.isAttribute() == true && isAggrExpr == false)	{ //create the artificial attribute node in the output
						ConstructionTreePatternNode artificialAttributeNode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.ATTRIBUTE, var.node.getTag(), false);
						//artificialAttributeNode.set
						subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, artificialAttributeNode);
						subqueryConstructionTreePattern.addChild(artificialAttributeNode, ctpnode);
					}
					else
						subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, ctpnode);
				}
				else {	//subquery without a where, we build a chain of Cartesian Products with a GroupBy on top
					if(operatorsProcessedInSubquery == false) {	//we need to process the operators in this subquery
						ArrayList<BaseLogicalOperator> outerScans = new ArrayList<BaseLogicalOperator>();
						//BaseOperator rightChild = inner tree
						NavigationTreePattern innerTP = null;
						BaseLogicalOperator rightChild = null;
						int tpIndex = -1;
						if(navigationTreePatternsInsideSubquery.numCols(subqueryLevel) > 0) {
							innerTP = navigationTreePatternsInsideSubquery.getElement(subqueryLevel, 0);
							if(innerTP != null) {
								logicalPlan.setLeaves(scans);
								rightChild = logicalPlan.getTopFromLeaf(innerTP);		
								tpIndex = XQueryUtils.findTreePatternIndexInScans(scans, innerTP);
								if(tpIndex != -1)
									treePatternVisited.set(tpIndex, true);
							}
						}				
						//for the other trees: create CPs if needed
						//if constructChild == null, do CP with all outer XMLScans 
						if(constructChild == null) {// && scans.size() > 1) {
							if(scans.size() > 1) {
								for(int scan_index = 0; scan_index < scans.size(); scan_index++) {
									if(logicalPlan.getTopFromLeaf(scans.get(scan_index)) != rightChild) {
										if(constructChild == null)
											constructChild = scans.get(scan_index);
										else
											constructChild = new CartesianProduct(constructChild, scans.get(scan_index));
										treePatternVisited.set(scan_index, true);
										outerScans.add(scans.get(scan_index));
									}
								}
							}
							else {
								constructChild = scans.get(0);
								treePatternVisited.set(0, true);
								outerScans.add(scans.get(0));
							}
						}
						else {
							for(int scan_index = 0; scan_index < scans.size(); scan_index++) {
								BaseLogicalOperator topFromLeaf = logicalPlan.getTopFromLeaf(scans.get(scan_index));
								if(topFromLeaf != rightChild) {
									if(constructChild != topFromLeaf)	//if the i-th scan is not in the main logical tree then do a cartesian product between both trees
										constructChild = new CartesianProduct(constructChild, topFromLeaf);
									treePatternVisited.set(scan_index,  true);
									outerScans.add(scans.get(scan_index));
								}
							}
						}				
						//instantiate CP between the outer and the inner scans
						constructChild = new CartesianProduct(constructChild, rightChild);
						
						//add the variable that contains the subquery
						outerVarObject = new Variable(outerVarSubquery, Variable.VariableDataType.Subquery);
						outerVarObject.setNestedCTP(subqueryConstructionTreePattern.getRoot());
						varMap.addNewVariable(outerVarObject);
						//outerVarObject.updateNestedCTPWithVarPosition(varMap.getTemporaryPositionByName(outerVarObject.name));
						
						//instantiate a temporary empty GroupBy operator
						constructChild = new GroupBy(constructChild, new int[0], new int[0], new int[0]);
						//instantiate a XQueryGroupByInfo for later update of the GroupBy operator
						subqueryInfoList.add(new GroupByInfo((GroupBy)constructChild, outerScans, rightChild, outerVarObject, varMap.getVariable(varName), varMap));
						
						operatorsProcessedInSubquery = true;
					}
					
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(varMap.getTemporaryPositionByName(varName));
					boolean nested = (var.dataType != Variable.VariableDataType.Aggregation) && (var.node.getNestingDepth() > 0);
					if(nested == true)
						list.add(-1);
					int[] varpath = XQueryUtils.IntegerListToIntArray(list);
					//create a CTPNode with a varpath for the innervariable of the outervariable
					ctpnode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.VARIABLE_PATH, varpath, false);
					ctpnode.setOuterVariable(outerVarSubquery);
					
					if(var.node != null && var.node.isAttribute() == true && isAggrExpr == false)	{ //create the artificial attribute node in the output
						ConstructionTreePatternNode artificialAttributeNode = new ConstructionTreePatternNode(subqueryConstructionTreePattern, ContentType.ATTRIBUTE, var.node.getTag(), false);
						//artificialAttributeNode.set
						subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, artificialAttributeNode);
						subqueryConstructionTreePattern.addChild(artificialAttributeNode, ctpnode);
					}
					else
						subqueryConstructionTreePattern.addChild(lastSubqueryConstructionTreePatternNode, ctpnode);
				}
			}
		}

		return null;
	}
}