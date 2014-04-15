package fr.inria.oak.paxquery.xparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import fr.inria.oak.paxquery.common.xml.treepattern.core.PrintingLevel;

public class XQueryMain {
	public static void main(String args[]) throws Exception {
			System.out.println("Enter a valid XQuery expression followed by Enter and Ctrl+D: ");
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(System.in);
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);
			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);
			
			ParseTree tree = parser.xquery();	//begin parsing at start rule
			ParseTreeWalker walker = new ParseTreeWalker();	//create a standard walker
			XQueryProcessor processor = new XQueryProcessor();
			walker.walk(processor,  tree);
	
			System.out.println(tree.toStringTree(parser));
			System.out.println("PatternTree:");
			System.out.println(processor.treePattern.toString(PrintingLevel.SIMPLIFY));
			System.out.println("HashMap:");
			System.out.println(processor.patternNodeMap.toString());
			
	}
	
	public static boolean test_main(String test_query) {
		try {
			System.out.println("XQuery: "+test_query);
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(new java.io.ByteArrayInputStream(test_query.getBytes()));
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);
			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);
			
			ParseTree tree = parser.xquery();	//begin parsing at start rule
			System.out.println(tree.toStringTree(parser));

			return true;
		}
		catch(Exception e) {
			System.out.println("Error in input " + test_query);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static String test_processor(String test_query) {
		try {
			//System.out.println("Enter a valid XQuery expression followed by Enter and Ctrl+D: ");
			System.out.println("XQuery: "+test_query);
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(new java.io.ByteArrayInputStream(test_query.getBytes()));
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);
			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);
			
			ParseTree tree = parser.xquery();	//begin parsing at start rule
			ParseTreeWalker walker = new ParseTreeWalker();	//create a standard walker
			XQueryProcessor processor = new XQueryProcessor();
			walker.walk(processor,  tree);
	
			StringBuilder sb = new StringBuilder();
			sb.append("PT:");	//Pattern Tree
			sb.append(processor.treePattern.toString(PrintingLevel.SIMPLIFY));
			sb.append("---");
			sb.append("HM:");	//Hash Map
			sb.append(processor.patternNodeMap.toString());
			/*System.out.println(tree.toStringTree(parser));
			System.out.println("PatternTree:");
			System.out.println(processor.treePattern.toString(PrintingLevel.SIMPLIFY));
			System.out.println("HashMap:");
			System.out.println(processor.patternNodeMap.toString());*/
						
			System.out.println(sb.toString());
			return sb.toString();
		}
		catch(Exception e) {
			System.out.println("Error in input " + test_query);
			System.out.println(e.getMessage());
			return "";
		}
	}

}
