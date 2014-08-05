package fr.inria.oak.paxquery.xparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import fr.inria.oak.paxquery.common.xml.navigation.PrintingLevel;

public class XQueryMain {
	public static void main(String args[]) {
		try {
			System.out.println("Enter a valid XQuery expression followed by Enter and Ctrl+D: ");
			
			//VISITOR VERSION
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(System.in);
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);

			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);

			ParseTree tree = parser.xquery();
			XQueryVisitorImplementation loader = new XQueryVisitorImplementation("");
			loader.visit(tree);
			
			System.out.println(tree.toStringTree(parser));
			for(int i = 0; i < loader.navigationTreePatterns.size(); i++) {
				System.out.println("PatternTree ("+i+"): ");
				System.out.println(loader.navigationTreePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
			}
			System.out.println("HashMap:");
			System.out.println(loader.patternNodeMap.toString());
			System.out.println("each:");
			System.out.println(loader.applyEach.toString());
			System.out.println("fields:");
			System.out.println(loader.applyFields.toString());
			System.out.println("Algebraic tree:");
			System.out.println(XQueryUtils.algebraicTreeToString(loader.construct));
		} catch(Exception e) {
			System.out.println("Query malformed or not supported yet.");
		}
		
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
			
			ParseTree tree = parser.xquery();						//begin parsing at start rule
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
			//System.out.println("XQuery: "+test_query);
			
			//VISITOR VERSION
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(new java.io.ByteArrayInputStream(test_query.getBytes()));
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);

			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);

			ParseTree tree = parser.xquery();
			XQueryVisitorImplementation loader = new XQueryVisitorImplementation("");
			loader.visit(tree);
			
			System.out.println("applyEach: "+loader.applyEach);
			System.out.println("applyFields: "+loader.applyFields);
			
			//Print out normalized results
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < loader.navigationTreePatterns.size(); i++) {
				sb.append("PT"+i+":");	//Pattern Tree
				sb.append(loader.navigationTreePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
				if(i < loader.navigationTreePatterns.size()-1)
					sb.append(",");
			}
			sb.append("---");
			sb.append("HM:");	//Hash Map
			sb.append(loader.patternNodeMap.toString());
			sb.append("---");
			sb.append("AT:");	//Algebraic Tree
			sb.append(XQueryUtils.algebraicTreeToString(loader.construct));
						
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
