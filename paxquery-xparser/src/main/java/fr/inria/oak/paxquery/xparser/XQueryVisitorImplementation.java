package fr.inria.oak.paxquery.xparser;

import fr.inria.oak.paxquery.algebra.operators.border.*;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;
import fr.inria.oak.paxquery.common.xml.treepattern.core.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class XQueryVisitorImplementation extends XQueryBaseVisitor<Void> {
	/**
	 * Operators
	 */
	public ArrayList<XMLScan> scans;
	public XMLConstruct construct;

	/**
	 * Data structures
	 */
	public HashMap<String, Variable> varsPos;	//for use when creating the XMLConstruct operator in return clause
	public HashMap<String, PatternNode> patternNodeMap;	//each tuple <String, PatternNode> stores the name of a variable and the PatternNode it addresses
	public ArrayList<TreePattern> treePatterns;	//the list of all TreePattern objects built for a given query
	public ArrayList<String> applyEeach;		//holds a String array for ApplyConstruct.each
	public ArrayList<Integer> applyFields;		//holds a Integer array for ApplyConstruct.fields

	/**
	 * State variables for parsing
	 */
	private TreePattern lastTreePattern;
	private PatternNode lastNode;
	private String lastVarLeftSide;
	private int lastSlashToken;
	private boolean nextNodeIsAttribute;
	private StringBuilder returnXMLTags;		//accumulates XML constant text from the return construction. This text is eventually inserted into XMLConstruct.apply.each
	private boolean insideReturn;
	
	/**
	 * Others
	 */
	public String outputPath;					//file to be printed out 

	public XQueryVisitorImplementation(String outputPath) {
		this.outputPath = outputPath;
		patternNodeMap = new HashMap<String, PatternNode>();
		treePatterns = new ArrayList<TreePattern>();
		lastSlashToken = 0;
		nextNodeIsAttribute = false;
		insideReturn = false;
		scans = new ArrayList<XMLScan>();
		varsPos = new HashMap<String, Variable>();
		applyEeach = new ArrayList<String>();
		applyFields = new ArrayList<Integer>();
	}
	
	/**
	 * xquery
	 * Starting rule. We build the XMLConstruct object here, after having processed the query
	 * TODO: so far we connect the XMLConstruct object to the first of the XMLScans, this needs to be addressed (we need a binary algebraic operator)
	 */
	public Void visitXquery(XQueryParser.XqueryContext ctx) { 
		visitChildren(ctx);
		//we instantiate the XMLConstruct operator here rather than in exitReturnStat 
		//since we can have several return clauses but just one XMLConstruct operator.
		ApplyConstruct apply = new ApplyConstruct("", new String[]{}, "", new int[]{}, null);
		//so far we connect the XMLConstruct object to the first of the XMLScans, this needs to be addressed
		construct = new XMLConstruct(scans.get(0), apply, outputPath);
		
		return null;
	}

	/**
	 * letBinding
	 * store the left-side var so we can assign the xpath tree to it
	 * VAR := whatever
	 */
	public Void visitLetBinding(XQueryParser.LetBindingContext ctx) {
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();		
		//do visit, ctx.getChild(2) can be (pathExpr_xq | flwrexpr | aggrExpr | arithmeticExpr_xq | literal)
		visit(ctx.getChild(2));
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		
		return null;	//// Java says must return something even when Void
	}

	/**
	 * forBinding
	 * store the left-side var so we can assign the xpath tree to it
	 */	
	public Void visitForBinding(XQueryParser.ForBindingContext ctx) { 
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();
		//do visit
		visit(ctx.pathExpr_xq());
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_collection - enter
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public Void visitPathExprInner_xq_collection(XQueryParser.PathExprInner_xq_collectionContext ctx) {
		//root node construction
		String rootTag = "";
		lastTreePattern = new TreePattern();
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);		
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		treePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText();
		System.out.println("STRING_LITERAL: "+pathDocuments);
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
		//nothing to visit
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_doc
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public Void visitPathExprInner_xq_doc(XQueryParser.PathExprInner_xq_docContext ctx) { 
		//root node construction
		String rootTag = "";
		lastTreePattern = new TreePattern();
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		treePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText();
		System.out.println("STRING_LITERAL: "+pathDocuments);
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
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
	 * Retrieve the appropriate tree and node for further expansion in the following case: another_var := VAR/whatever
	 */
	public Void visitPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) {
		String var_to_expand = ctx.VAR().getText();
		lastNode = patternNodeMap.get(var_to_expand);
		lastTreePattern = lastNode.getTreePattern();
		
		return null;
	}

	/**
	 * abbrevForwardStep (xpath)
	 * Decide whether the next qName represents an xml element (e.g. whatever/element) or attribute (e.g. whatever/@attribute)
	 */
	public Void visitAbbrevForwardStep(XQueryParser.AbbrevForwardStepContext ctx) {
		if(ctx.getText().startsWith("@")) 
			nextNodeIsAttribute = true;	//must exist another way of detecting tokens
		visit(ctx.nodeTest());
		
		return null;
	}

	/**
	 * nameTest_qName (in XPath)
	 * Create a pattern node, set it as content storage, attach it to the appropriate tree and store it in patternNodeMap
	 * Xpath: whatever/node/whatever
	 * TODO: what with 'div' and 'mod' ?
	 */
	public Void visitNameTest(XQueryParser.NameTestContext ctx) { 
		String tag = "";
		if(ctx.qName() != null)
			tag = ctx.qName().QNAME_TOKEN().getText();
		else if(ctx.getChild(0).getText().compareTo("div") == 0)
			tag = "div";
		else if(ctx.getChild(0).getText().compareTo("mod") == 0)
			tag = "mod";
		
		int nodeCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode node = new PatternNode("", tag, nodeCode, "", "", lastTreePattern);
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;
		lastNode.addEdge(node, parent, false, false); 		//parameters: addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)
		lastNode.removeMatchingVariables(lastVarLeftSide);
		
		if(lastNode.checkAnyMatchingVariableStoresValue() == false)
			lastNode.setStoresValue(false);
		if(lastNode.checkAnyMatchingVariableStoresContent() == false)
			lastNode.setStoresContent(false);
		Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, node);
		node.addMatchingVariables(newVar);
		node.setStoresContent(true);
		if(nextNodeIsAttribute) {
			//attributes store values
			newVar.dataType = Variable.VariableDataType.Value;
			node.setAttribute(true);
			node.setStoresValue(true);
			node.setStoresContent(false);
			nextNodeIsAttribute = false;
		}
		//update the affected variable so it points to this node
		patternNodeMap.put(lastVarLeftSide, node);
		lastNode = node;	//for further expansion

		return null;
	}


	/**
	 * textTest (in XPath)
	 * Set the last pattern node in the tree as value storage when the following situation is found in XPath: whatever/text() or whatever//text()
	 * NOTE: we currently treat both whatever/text() and whatever//text() as whatever/text(), this is something we need to change in the future (see Github's issue stratosphere/paxquery#1)
	 * TODO: treat whatever/text() and whatever//text() differently (will need to consider grammar rules pathExpr and relativePathExpr2 too)
	 */
	public Void visitTextTest(XQueryParser.TextTestContext ctx) {
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
			lastNode.addMatchingVariables(newVar);
			lastNode.setStoresValue(true);
			patternNodeMap.put(lastVarLeftSide, lastNode);
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
		//go through all pattern trees annotating vars and their overall positions
		for(int i = 0; i < treePatterns.size(); i++) {
			XQueryProcessorUtils.buildVarsPos(varsPos, treePatterns.get(i), varsPos.size());
		}
		System.out.println("varsPos: "+XQueryProcessorUtils.varsPosToString(varsPos));
		returnXMLTags = new StringBuilder();
		//manually visit the next rule
		if(ctx.eleConst()!=null) {
			try {
				visitEleConst(ctx.eleConst());
			}
			catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				System.exit(-1);
			}
		}
		//either it is an aggrExpr rule
		else if(ctx.aggrExpr() != null)
			visitAggrExpr(ctx.aggrExpr());
		//or a VAR
		else if(ctx.getChild(1).getText().startsWith("$"))
			storeVarAndXMLText(ctx.getChild(1).getText());					
		
		storeXMLText();		
		insideReturn = false;

		return null;
	}
	
	/**
	 * visitEleConst
	 */
	public Void visitEleConst(XQueryParser.EleConstContext ctx) {
		System.out.println("Entering enterEleConst: "+ctx.getText());
		//append '<' and the element name
		returnXMLTags.append(ctx.LT_S()).append(ctx.eaName(0).getText());

		//manually visit all attributes
		for(int i = 0; i < ctx.children.size(); i++) {
			if(ctx.children.get(i).getPayload().getClass() == XQueryParser.AttContext.class) {
				System.out.println("Attribute class detected!");
				visitAtt((XQueryParser.AttContext)ctx.children.get(i).getPayload());
				//append the rest of the eleConst rule!
			}
		}
		//eleConst : LT_S eaName att* CLOSE_OPENING_TAG
		if(ctx.CLOSE_OPENING_TAG()!=null) {
			System.out.println("Printing  /> ");
			returnXMLTags.append(ctx.CLOSE_OPENING_TAG());	
		}
		//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
		else if(ctx.GT_S().size() > 0) {
			returnXMLTags.append(ctx.GT_S(0).getText());

			for(int i = 0; i < ctx.children.size(); i++) {
				if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class) 
					visitEleConst((XQueryParser.EleConstContext)ctx.getChild(i).getPayload());
				else if(ctx.getChild(i).getText().startsWith("{")) {
					if(i+1 < ctx.children.size())
						visitEleConstInner((XQueryParser.EleConstInnerContext)ctx.getChild(i+1).getPayload());
				}					
			}
			//append the rest of the rule
			if(ctx.OPEN_CLOSING_TAG() != null) 
				returnXMLTags.append(ctx.OPEN_CLOSING_TAG().getText());
			if(ctx.eaName().size() > 1) 
				returnXMLTags.append(ctx.eaName(1).getText());
			if(ctx.GT_S().size() > 1) 
				returnXMLTags.append(ctx.GT_S(1).getText());			
		}
		return null;
	}

	/**
	 * att
	 */
	public Void visitAtt(XQueryParser.AttContext ctx) {
		returnXMLTags.append(" ").append(ctx.eaName().getText()).append('=');
		visitAttInner(ctx.attInner());
		return null;
	}

	/**
	 * visitAttInner - custom enter
	 */
	public Void visitAttInner(XQueryParser.AttInnerContext ctx) {
		//attInner : STRING_LITERAL
		if(ctx.STRING_LITERAL()!=null)
			returnXMLTags.append(ctx.STRING_LITERAL());
		//attInner : DOUBLE_QUOTE LEFTCURL attInner2 RIGHTCURL DOUBLE_QUOTE 
		else if(ctx.OPEN_ATTR_VAR_DOUBLE() != null && ctx.CLOSE_ATTR_VAR_DOUBLE() != null) {
			returnXMLTags.append("\"");
			//invoke attInner2
			visitAttInner2(ctx.attInner2());
			returnXMLTags.append("\"");
		}
		//attInner : SINGLE_QUOTE LEFTCURL attInner2 RIGHTCURL SINGLE_QUOTE
		else if(ctx.OPEN_ATTR_VAR_SINGLE() != null && ctx.CLOSE_ATTR_VAR_SINGLE() != null) {
			returnXMLTags.append("'");
			//invoke attInner2
			visitAttInner2(ctx.attInner2());
			returnXMLTags.append("'");
		}	
		return null;
	}	

	/**
	 * visitAttInner2
	 */
	public Void visitAttInner2(XQueryParser.AttInner2Context ctx) {
		//attInner2 : VAR
		if(ctx.VAR() != null) {
			storeVarAndXMLText(ctx.VAR().getText());
		}
		//attInner2 : aggrExpr
		else if(ctx.aggrExpr() != null) {
			//invoke aggrExpr
			visitAggrExpr(ctx.aggrExpr());
		}
		return null;
	}

	/**
	 * aggrExpr
	 * TODO: currently we store the aggregation expression as XML text: obviously we need to treat this differently
	 */
	public Void visitAggrExpr(XQueryParser.AggrExprContext ctx) {
		if(insideReturn) {
			//add the function name and the left parenthesis
			String aggrfunct = ctx.AGGR_FUNCT().getText();
			//returnXMLTags.append(ctx.AGGR_FUNCT().getText()).append('(');
			returnXMLTags.append(aggrfunct).append('(');
			//store the var's position and the XML text so far
			storeVarAndXMLText(ctx.VAR().getText());
			//add the right parenthesis
			returnXMLTags.append(')');
		}
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
		for(int i = 0; i < ctx.children.size(); i++) {
			ParseTree child = ctx.getChild(i);
			//an aggrExpr rule
			if(child.getPayload().getClass() == XQueryParser.AggrExprContext.class)
				visitAggrExpr((XQueryParser.AggrExprContext)child.getPayload());
			//a variable
			else if(child.getText().startsWith("$")) {
				//check that the variable is not an attribute, otherwise throw exception
				PatternNode node = patternNodeMap.get(child.getText());
				if(node != null && node.isAttribute())
				{
					System.out.println("It is illegal to use an attribute as an XML value");
					System.exit(-1);
				}
				storeVarAndXMLText(child.getText());					
			}
			//we don't care about the commas
		}		
		return null;
	}	
	
	/**
	 * Appends a new variable position to the applyFields data structure
	 * @param var_text the name of the variable
	 */
	private void storeVarAndXMLText(String var_text) {
		//store the current piece of XML string in applyEach
		storeXMLText();
		//store the variable overall position position in the trees into appyFields
		applyFields.add(varsPos.get(var_text).positionInForest);
	}

	/**
	 * Stores the text in returnXMLTags in the applyEach data structure
	 */
	private void storeXMLText() {
		//store the current piece of XML string in applyEach (even if it's empty)
		applyEeach.add(returnXMLTags.toString());
		//clear the StringBuilder object (faster than instantiating a new one)
		returnXMLTags.setLength(0);
	}
}
