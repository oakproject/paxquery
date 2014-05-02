package fr.inria.oak.paxquery.xparser;

import fr.inria.oak.paxquery.algebra.operators.border.*;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;
import fr.inria.oak.paxquery.common.xml.treepattern.core.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

public class XQueryProcessor extends XQueryBaseListener {

	/**
	 * Operators
	 */
	public XMLScan scan;
	public XMLConstruct construct;

	/**
	 * Data structures
	 */
	public HashMap<String, Variable> varsPos;	//for use when creating the XMLConstruct operator in return clause
	public HashMap<String, PatternNode> patternNodeMap;
	public TreePattern treePattern;
	public ArrayList<String> applyEeach;		//holds a String array for ApplyConstruct.each
	public ArrayList<Integer> applyFields;		//holds a Integer array for ApplyConstruct.fields

	/**
	 * State variables for parsing
	 */
	private PatternNode lastNode;
	private String lastVarLeftSide;
	private int lastSlashToken;
	private boolean nextNodeIsAttribute;
	private StringBuilder returnXMLTags;		//accumulates XML constant text from the return construction. This text is eventually inserted into XMLConstruct.apply.each
	
	/**
	 * Others
	 */
	public String outputPath;					//file to be printed out 

	
	public XQueryProcessor(String outputPath) {
		this.outputPath = outputPath;
		patternNodeMap = new HashMap<String, PatternNode>();
		treePattern = new TreePattern();
		lastSlashToken = 0;
		nextNodeIsAttribute = false;
		varsPos = new HashMap<String, Variable>();
		applyEeach = new ArrayList<String>();
		applyFields = new ArrayList<Integer>();
	}

	/**
	 * letBinding - enter
	 * store the left-side var so we can assign the xpath tree to it
	 * VAR := whatever
	 */
	public void enterLetBinding(XQueryParser.LetBindingContext ctx) {
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();
	}
	
	/**
	 * letBinding - exit
	 * reset last used var and node
	 */
	public void exitLetBinding(XQueryParser.LetBindingContext ctx) {
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
	}
	
	/**
	 * forBinding - enter
	 * store the left-side var so we can assign the xpath tree to it
	 */	
	public void enterForBinding(XQueryParser.ForBindingContext ctx) { 
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();
	}
	
	/**
	 * forBinding - exit
	 * reset last used var and node
	 */
	public void exitForBinding(XQueryParser.ForBindingContext ctx) { 
		lastVarLeftSide = null;
		lastNode = null;
	}
	
	/**
	 * pathExprInner_xq_collection - enter
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public void enterPathExprInner_xq_collection(XQueryParser.PathExprInner_xq_collectionContext ctx) {
		//root node construction
		String rootTag = "";
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "");
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);		
		//tree pattern construction
		treePattern.setRoot(rootNode);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText();
		System.out.println("STRING_LITERAL: "+pathDocuments);
		scan = new XMLScan(false, treePattern, pathDocuments);
	}
	
	/**
	 * pathExprInner_xq_doc - enter
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public void enterPathExprInner_xq_doc(XQueryParser.PathExprInner_xq_docContext ctx) { 
		//root node construction
		String rootTag = "";
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "");
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);
		//tree pattern construction
		treePattern.setRoot(rootNode);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText();
		System.out.println("STRING_LITERAL: "+pathDocuments);
		scan = new XMLScan(false, treePattern, pathDocuments);
	}	

	/**
	 * pathExpr slash (xpath) - enter
	 * Update lastSlashToken with a slash ('/')
	 */
	public void enterPathExpr_slash(XQueryParser.PathExpr_slashContext ctx) {
		lastSlashToken = XQueryLexer.SLASH;
	}
	
	/**
	 * pathExpr slash (xpath) - exit
	 * Clear lastSlashToken 
	 */
	public void exitPathExpr_slash(XQueryParser.PathExpr_slashContext ctx) { 
		lastSlashToken = 0;
	}
	
	/**
	 * pathExpr slashslash (xpath) - enter
	 * Update lastSlashToken with a slashslash ('//')
	 */
	public void enterPathExpr_slashslash(XQueryParser.PathExpr_slashslashContext ctx) {
		lastSlashToken = XQueryLexer.SLASHSLASH;
	}
	
	/**
	 * pathExpr slash (xpath) - exit
	 * Clear lastSlashToken 
	 */
	public void exitPathExpr_slashslash(XQueryParser.PathExpr_slashslashContext ctx) {
		lastSlashToken = 0;
	}
	
	/**
	 * relativePathExpr2_slash (xpath) - enter
	 * Decides that the next edge is parent-child
	 */
	public void enterRelativePathExpr2_slash(XQueryParser.RelativePathExpr2_slashContext ctx) { 
		lastSlashToken = XQueryLexer.SLASH;		
	}
	
	/**
	 * relativePathExpr2_slashslash (xpath) - enter
	 * Decides that the next edge is ancestor-descendant
	 */	
	public void enterRelativePathExpr2_slashslash(XQueryParser.RelativePathExpr2_slashslashContext ctx) { 
		lastSlashToken = XQueryLexer.SLASHSLASH;		
	}
	
	/**
	 * pathExprInner_xq_VAR - enter
	 * Retrieve the appropriate tree for further expansion in the following case: another_var := VAR/whatever
	 */
	public void enterPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) {
		String var_to_expand = ctx.VAR().getText();
		lastNode = patternNodeMap.get(var_to_expand);
	}
	
	/**
	 * abbrevForwardStep (xpath) - enter
	 * Decide whether the next qName represents an xml element (e.g. whatever/element) or attribute (e.g. whatever/@attribute)
	 */
	public void enterAbbrevForwardStep(XQueryParser.AbbrevForwardStepContext ctx) {
		if(ctx.getText().startsWith("@")) 
			nextNodeIsAttribute = true;	//must exist another way of detecting tokens
	}
	
	/**
	 * nameTest_qName - enter (in XPath)
	 * Create a pattern node, set it as content storage, attach it to the appropriate tree and store it in patternNodeMap
	 * Xpath: whatever/node/whatever
	 */
	public void enterNameTest_qName(XQueryParser.NameTest_qNameContext ctx) {
		//create a new node and append it to the last tree
		String tag = ctx.qName().QNAME_TOKEN().getText();
		int nodeCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode node = new PatternNode("", tag, nodeCode, "", "");
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;
		lastNode.addEdge(node, parent, false, false); 		//parameters: addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)
		lastNode.removeMatchingVariables(lastVarLeftSide);
		
		if(lastNode.checkAnyMatchingVariableStoresValue() == false)
			lastNode.setStoresValue(false);
		if(lastNode.checkAnyMatchingVariableStoresContent() == false)
			lastNode.setStoresContent(false);
		Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content);
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
	}
	
	/**
	 * textTest - enter (in XPath)
	 * Set the last pattern node in the tree as value storage when the following situation is found in XPath: whatever/text() or whatever//text()
	 * NOTE: we currently treat both whatever/text() and whatever//text() as whatever/text(), this is something we need to change in the future (see Github's issue stratosphere/paxquery#1)
	 * TODO: treat whatever/text() and whatever//text() differently (will need to consider grammar rules pathExpr and relativePathExpr2 too)
	 */
	public void enterTextTest(XQueryParser.TextTestContext ctx) {
		Variable matchingVar = lastNode.getMatchingVariable(lastVarLeftSide);
	
		if(matchingVar != null) {
			matchingVar.dataType = Variable.VariableDataType.Value;
			if(lastNode.checkAnyMatchingVariableStoresContent() == false)
				lastNode.setStoresContent(false);	//no vars storing content anymore
			lastNode.setStoresValue(true);
		}
		else {
			//lastVarLeftSide does not occur in lastNode.matchingVariables
			Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Value);
			lastNode.addMatchingVariables(newVar);
			lastNode.setStoresValue(true);
			patternNodeMap.put(lastVarLeftSide, lastNode);
		}
	}
	
	/**
	 * returnStat - enter
	 * Builds varsPos before parsing on. After that "manually" visits the rules within the return (using custom methods that don't override those in XQueryBaseListener)
	 * and builds the data structures needed by XMLConstruct
	 * 
	 * Terminates the program when an attribute is used as an XML value in the input query
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 */
	public void enterReturnStat(XQueryParser.ReturnStatContext ctx) {
		System.out.println("patternNodeMap: "+patternNodeMap);
		//go through the whole tree annotating vars and their position in the tree
		varsPos = XQueryProcessorUtils.buildVarsPos(treePattern);
		System.out.println("varsPos: "+XQueryProcessorUtils.varsPosToString(varsPos));
		returnXMLTags = new StringBuilder();
		//manually visit the next rule
		if(ctx.eleConst()!=null) {
			try {
				customVisitEleConst(ctx.eleConst());
			}
			catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				System.exit(-1);
			}
		}
		else {
			for(int i = 0; i < ctx.children.size(); i++) {
				//either it is an aggrExpr rule
				if(ctx.getChild(i).getPayload().getClass() == XQueryParser.AggrExprContext.class) 
					customVisitAggrExpr((XQueryParser.AggrExprContext)ctx.getChild(i).getPayload());
				//or a VAR
				else if(ctx.getChild(i).getText().startsWith("$"))
					storeVarAndXMLText(ctx.getChild(i).getText());					
			}			
		}		
	}

	/**
	 * returnStat - exit
	 * Updates applyEach with the XML text at the right of the last variable
	 */
	public void exitReturnStat(XQueryParser.ReturnStatContext ctx) { 
		storeXMLText();		
	}	

	//CUSTOM VISITS, MANUALLY TRIGGERED BY METHODS IN THIS CLASS	

	/**
	 * visitEleConst - custom enter
	 * @throws Exception if an attribute is used in the input query as an XML value
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 */
	public void customVisitEleConst(XQueryParser.EleConstContext ctx) throws Exception {
		System.out.println("Entering enterEleConst: "+ctx.getText());
		//append '<' and the element name
		returnXMLTags.append(ctx.LT_S()).append(ctx.eaName(0).getText());

		//manually visit all attributes
		for(int i = 0; i < ctx.children.size(); i++) {
			if(ctx.children.get(i).getPayload().getClass() == XQueryParser.AttContext.class) {
				System.out.println("Attribute class detected!");
				customVisitAtt((XQueryParser.AttContext)ctx.children.get(i).getPayload());
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
					customVisitEleConst((XQueryParser.EleConstContext)ctx.getChild(i).getPayload());
				else if(ctx.getChild(i).getText().startsWith("{")) {
					if(i+1 < ctx.children.size())
						customVisitEleConstInner((XQueryParser.EleConstInnerContext)ctx.getChild(i+1).getPayload());
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
	}

	/**
	 * visitAtt - custom enter
	 */
	public void customVisitAtt(XQueryParser.AttContext ctx) {
		returnXMLTags.append(" ").append(ctx.eaName().getText()).append('=');
		customVisitAttInner(ctx.attInner());
	}

	/**
	 * visitAttInner - custom enter
	 */
	public void customVisitAttInner(XQueryParser.AttInnerContext ctx) {
		//attInner : STRING_LITERAL
		if(ctx.STRING_LITERAL()!=null)
			returnXMLTags.append(ctx.STRING_LITERAL());
		//attInner : DOUBLE_QUOTE LEFTCURL attInner2 RIGHTCURL DOUBLE_QUOTE 
		else if(ctx.OPEN_ATTR_VAR_DOUBLE() != null && ctx.CLOSE_ATTR_VAR_DOUBLE() != null) {
			returnXMLTags.append("\"");
			//invoke attInner2
			customVisitAttInner2(ctx.attInner2());
			returnXMLTags.append("\"");
		}
		//attInner : SINGLE_QUOTE LEFTCURL attInner2 RIGHTCURL SINGLE_QUOTE
		else if(ctx.OPEN_ATTR_VAR_SINGLE() != null && ctx.CLOSE_ATTR_VAR_SINGLE() != null) {
			returnXMLTags.append("'");
			//invoke attInner2
			customVisitAttInner2(ctx.attInner2());
			returnXMLTags.append("'");
		}	
	}	

	/**
	 * visitAttInner2 - custom enter
	 */
	public void customVisitAttInner2(XQueryParser.AttInner2Context ctx) {
		//attInner2 : VAR
		if(ctx.VAR() != null) {
			storeVarAndXMLText(ctx.VAR().getText());
		}
		//attInner2 : aggrExpr
		else if(ctx.aggrExpr() != null) {
			//invoke aggrExpr
			customVisitAggrExpr(ctx.aggrExpr());
		}
	}

	/**
	 * aggrExpr - custom enter
	 * TODO: currently we store the aggregation expresion as XML text: obviously we need to treat this differently
	 */
	public void customVisitAggrExpr(XQueryParser.AggrExprContext ctx) {
		//add the function name and the left parenthesis
		returnXMLTags.append(ctx.AGGR_FUNCT().getText()).append('(');
		//store the var's position and the XML text so far
		storeVarAndXMLText(ctx.VAR().getText());
		//add the right parenthesis
		returnXMLTags.append(')');
	}

	/**
	 * eleConstInner - custom enter
	 * @throws Exception if an attribute is used in the input query as an XML value
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 * 
	 * TODO: if a variable within an XML element stores an attribute, then we currently throw an exception (since that is illegal). What we 
	 * really should do is including it in the element's XML tag as an attribute
	 * e.g: <item>@attrib</item> --> <item attrib="@attrib"></item>
	 */
	void customVisitEleConstInner(XQueryParser.EleConstInnerContext ctx) throws Exception {
		for(int i = 0; i < ctx.children.size(); i++) {
			ParseTree child = ctx.getChild(i);
			//an aggrExpr rule
			if(child.getPayload().getClass() == XQueryParser.AggrExprContext.class)
				customVisitAggrExpr((XQueryParser.AggrExprContext)child.getPayload());
			//a variable
			else if(child.getText().startsWith("$")) {
				//check that the variable is not an attribute, otherwise throw exception
				PatternNode node = patternNodeMap.get(child.getText());
				if(node != null && node.isAttribute())
					throw new Exception("It is illegal to use an attribute as an XML value");
				storeVarAndXMLText(child.getText());					
			}
			//we don't care about the commas
		}		
	}
	
	/**
	 * xquery - enter
	 * We instantiate the XMLConstruct operator here rather than in exitReturnStat since we can have several return clauses but just one XMLConstruct operator.
	 */
	public void exitXquery(XQueryParser.XqueryContext ctx) { 
		ApplyConstruct apply = new ApplyConstruct("", new String[]{}, "", new int[]{}, null);
		construct = new XMLConstruct(scan, apply, outputPath);
	}

	/**
	 * Appends a new variable position to the applyFields data structure
	 * @param var_text the name of the variable
	 */
	private void storeVarAndXMLText(String var_text) {
		//store the current piece of XML string in applyEach
		storeXMLText();
		//store the variable position in the tree into appyFields
		applyFields.add(varsPos.get(var_text).positionInTree);
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
