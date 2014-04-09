/*package fr.inria.oak.paxquery.xparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class XPathMain {
	public static void main(String args[]) throws Exception {
		System.out.println("Enter a valid XPath expression followed by Enter and Ctrl+D: ");
		//create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		//create a lexer that feeds off of input CharStream
		XPathLexer lexer = new XPathLexer(input);
		//create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//create a parser that feeds off the tokens buffer
		XPathParser parser = new XPathParser(tokens);
		
		ParseTree tree = parser.xpath();	//begin parsing at start rule
		System.out.println(tree.toStringTree(parser));
	}
	
	public static boolean test_main(String test_query) {
		try
		{
			System.out.println("Query: "+test_query);
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(new java.io.ByteArrayInputStream(test_query.getBytes()));
			//create a lexer that feeds off of input CharStream
			XPathLexer lexer = new XPathLexer(input);
			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XPathParser parser = new XPathParser(tokens);
			
			ParseTree tree = parser.xpath();	//begin parsing at start rule
			System.out.println(tree.toStringTree(parser));

			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error in input " + test_query);
			System.out.println(e.getMessage());
			return false;
		}
	}

}
*/