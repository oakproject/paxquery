package fr.inria.oak.paxquery.xparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import fr.inria.oak.paxquery.common.xml.treepattern.core.PrintingLevel;

public class XQueryMain {
	public static void main(String args[]) {
		try {
			System.out.println("Enter a valid XQuery expression followed by Enter and Ctrl+D: ");
		
			/*
			//LISTENER VERSION
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(System.in);
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);

			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);
			
			ParseTree tree = parser.xquery();						//begin parsing at start rule
			ParseTreeWalker walker = new ParseTreeWalker();			//create a standard walker
			XQueryProcessor processor = new XQueryProcessor("");
			walker.walk(processor,  tree);
	
			System.out.println(tree.toStringTree(parser));
			System.out.println("PatternTree:");
			System.out.println(processor.treePattern.toString(PrintingLevel.SIMPLIFY));
			System.out.println("HashMap:");
			System.out.println(processor.patternNodeMap.toString());
			System.out.println("each:");
			System.out.println(processor.applyEeach.toString());
			System.out.println("fields:");
			System.out.println(processor.applyFields.toString());
			*/
			
			
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
			for(int i = 0; i < loader.treePatterns.size(); i++) {
				System.out.println("PatternTree ("+i+"): ");
				System.out.println(loader.treePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
			}
			System.out.println("HashMap:");
			System.out.println(loader.patternNodeMap.toString());
			System.out.println("each:");
			System.out.println(loader.applyEeach.toString());
			System.out.println("fields:");
			System.out.println(loader.applyFields.toString());
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
			System.out.println("XQuery: "+test_query);
			/*
			//LISTENER VERSION
			//create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(new java.io.ByteArrayInputStream(test_query.getBytes()));
			//create a lexer that feeds off of input CharStream
			XQueryLexer lexer = new XQueryLexer(input);
			//create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			//create a parser that feeds off the tokens buffer
			XQueryParser parser = new XQueryParser(tokens);
			
			ParseTree tree = parser.xquery();						//begin parsing at start rule
			ParseTreeWalker walker = new ParseTreeWalker();			//create a standard walker
			XQueryProcessor processor = new XQueryProcessor("");
			walker.walk(processor,  tree);
	
			StringBuilder sb = new StringBuilder();
			sb.append("PT:");	//Pattern Tree
			sb.append(processor.treePattern.toString(PrintingLevel.SIMPLIFY));
			sb.append("---");
			sb.append("HM:");	//Hash Map
			sb.append(processor.patternNodeMap.toString());
			sb.append("---");
			sb.append("VP:");	//Vars Pos
			sb.append(XQueryProcessorUtils.varsPosToString(processor.varsPos));
			System.out.println(tree.toStringTree(parser));
						
			System.out.println(sb.toString());
			return sb.toString();
			*/
			
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
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < loader.treePatterns.size(); i++) {
				sb.append("PT"+i+":");	//Pattern Tree
				sb.append(loader.treePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
				if(i < loader.treePatterns.size()-1)
					sb.append(",");
			}
			sb.append("---");
			sb.append("HM:");	//Hash Map
			sb.append(loader.patternNodeMap.toString());
			sb.append("---");
			sb.append("VP:");	//Vars Pos
			sb.append(XQueryUtils.varsPosToString(loader.varsPos));
			System.out.println(tree.toStringTree(parser));
						
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
