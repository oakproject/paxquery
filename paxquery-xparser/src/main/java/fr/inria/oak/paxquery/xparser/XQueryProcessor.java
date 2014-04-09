package fr.inria.oak.paxquery.xparser;

import fr.inria.oak.paxquery.common.xml.treepattern.core.*;

import java.util.HashMap;

public class XQueryProcessor extends XQueryBaseListener {
	public HashMap<String, PatternNode> patternNodeMap;
	public TreePattern treePattern;
	private String lastPatternNodeRef = "";
	
	public XQueryProcessor() {
		patternNodeMap = new HashMap<String, PatternNode>();
		treePattern = new TreePattern();
	}

	public void enterPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) { 
/*		System.out.println("Found VAR");
		PatternNode rootNode = new PatternNode("", "", -1, "", "");
		rootNode.setTag(ctx.getChild(0).getText());
		lastPatternNodeRef = ctx.getChild(0).getText();
		patternNodeMap.put(rootNode.getTag(), rootNode);*/
	}

	
	public void enterXpath ( XQueryParser.XpathContext ctx ) {
//		System.out.println("Entering Xpath path at " + ctx.parent.getText());		
	}
	
	public void enterNameTest_qName ( XQueryParser.NameTest_qNameContext ctx ) {
		//nodes.add(ctx.qName().getText());
		
	}

}
