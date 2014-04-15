package fr.inria.oak.paxquery.xparser;

import fr.inria.oak.paxquery.common.xml.treepattern.core.*;

import java.util.HashMap;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class XQueryProcessor extends XQueryBaseListener {
	public HashMap<String, PatternNode> patternNodeMap;
	public TreePattern treePattern;

	private String lastVarLeftSide;
	private PatternNode lastNode;
	
	private int lastSlashToken;
	private boolean nextNodeIsAttribute;
	
	public XQueryProcessor() {
		patternNodeMap = new HashMap<String, PatternNode>();
		treePattern = new TreePattern();
		lastSlashToken = 0;
		nextNodeIsAttribute = false;
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
		nextNodeIsAttribute = false;	//not really needed since we reset it after checking it
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
	 * Start building a pattern tree: create a root node
	 */
	public void enterPathExprInner_xq_collection(XQueryParser.PathExprInner_xq_collectionContext ctx) {
		String rootTag = "root";
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "");
		rootNode.addMatchingVariables(lastVarLeftSide);
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);		
		treePattern.setRoot(rootNode);
	}
	
	/**
	 * pathExprInner_xq_collection - enter
	 * Start building a pattern tree: create a root node
	 */
	public void enterPathExprInner_xq_doc(XQueryParser.PathExprInner_xq_docContext ctx) { 
		String rootTag = "root";
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "");
		rootNode.addMatchingVariables(lastVarLeftSide);
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);
		treePattern.setRoot(rootNode);
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
		String tag = ctx.qName().QNAME_TOKEN().getText();
		int nodeCode = PatternNode.globalNodeCounter.getAndIncrement();
		//create a new node and append it to the last tree
		PatternNode node = new PatternNode("", tag, nodeCode, "", "");
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;
		//parameters -> addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)
		lastNode.addEdge(node, parent, false, false);
		lastNode.removeMatchingVariables(lastVarLeftSide);
		lastNode.setStoresContent(false);
		lastNode.setStoresValue(false);
		node.addMatchingVariables(lastVarLeftSide);
		if(nextNodeIsAttribute) {
			node.setAttribute(true);
			nextNodeIsAttribute = false;
		}
		else
			node.setStoresContent(true);
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
		lastNode.setStoresContent(false);
		lastNode.setStoresValue(true);		
	}
}
