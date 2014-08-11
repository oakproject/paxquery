package fr.inria.oak.paxquery.xparser.client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eu.stratosphere.api.common.Plan;
import eu.stratosphere.api.common.Program;
import eu.stratosphere.api.common.ProgramDescription;
import eu.stratosphere.core.fs.FileSystem;
import eu.stratosphere.core.fs.Path;
import eu.stratosphere.client.LocalExecutor;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.xml.navigation.PrintingLevel;
import fr.inria.oak.paxquery.translation.Logical2Pact;
import fr.inria.oak.paxquery.xparser.XQueryLexer;
import fr.inria.oak.paxquery.xparser.XQueryParser;
import fr.inria.oak.paxquery.xparser.XQueryUtils;
import fr.inria.oak.paxquery.xparser.XQueryVisitorImplementation;

public class XClient implements Program, ProgramDescription {

	private static final long serialVersionUID = 1808382941034073434L;

	@Override
	public String getDescription() {
		return "Parameters: file://[queryfile] file://[resultsfile] [numbersubtasks]\nor\nParameters: query_string file://[resultsfile] [numbersubtasks]";
	}
	
	@Override
	public Plan getPlan(String... args) {
		// parse program parameters
		final String query = (args.length > 0 ? args[0] : "");
		final String outputfile = (args.length > 1 ? args[1] : "");
		final int noSubtasks = (args.length > 2 ? Integer.parseInt(args[2]) : -1);
			
		Plan plan = null;
		BaseLogicalOperator op = null;
		
		try {
			InputStream inputStream;
			
			if(query.startsWith("file://")) {
				Path pathToQueryFile = new Path(query);
				final FileSystem fs = pathToQueryFile.getFileSystem();
				inputStream = fs.open(pathToQueryFile);
			}
			else
				inputStream = new ByteArrayInputStream(query.getBytes("UTF-8"));
			op = this.parseQuery(inputStream, outputfile);
			
		} catch(Exception e) {
			System.err.println("Query malformed or not supported yet.");
			e.printStackTrace();
			System.exit(1);
		}
		
		//create the logical plan
		try {
			plan = Logical2Pact.planTranslate(op);
			plan.setDefaultParallelism(noSubtasks);
			
		} catch(Exception e) {
			System.err.println("Error executing the query.");
			e.printStackTrace();
		}
		
		return plan;
	}
	
	private BaseLogicalOperator parseQuery(InputStream inputStream, String outputpath) throws Exception {
		//VISITOR VERSION
		//create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(inputStream);
		//create a lexer that feeds off of input CharStream
		XQueryLexer lexer = new XQueryLexer(input);

		//create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//create a parser that feeds off the tokens buffer
		XQueryParser parser = new XQueryParser(tokens);

		ParseTree tree = parser.xquery();
		XQueryVisitorImplementation loader = new XQueryVisitorImplementation("file://"+outputpath);
		loader.visit(tree);

		printParseDetails(tree, parser, loader);
		
		return loader.construct;
	}
	
	private void printParseDetails(ParseTree tree, XQueryParser parser, XQueryVisitorImplementation loader) {
		System.out.println("Parse output: ");
		System.out.println(tree.toStringTree(parser));
		for(int i = 0; i < loader.navigationTreePatterns.size(); i++) {
			System.out.println("PatternTree ("+i+"): ");
			System.out.println(loader.navigationTreePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
		}
		System.out.println("PatternNodeMap:");
		System.out.println(loader.patternNodeMap.toString());
		System.out.println("VarMap: ");
		System.out.println(loader.varMap.toString());
		System.out.println("each:");
		System.out.println(loader.applyEach.toString());
		System.out.println("fields:");
		System.out.println(loader.applyFields.toString());
		System.out.println("Algebraic tree:");
		System.out.println(XQueryUtils.algebraicTreeToString(loader.construct));

	}
	
	public static void main(String[] args) {

		XClient client = new XClient();

		try {
			if(args.length < 3) {
				System.err.println(client.getDescription());
				System.exit(1);
			}
			
			//delete output file (only temporary)
			if(args.length > 2 && args[1].startsWith("file://")) {
				String path = args[1].substring(7);
				(new java.io.File(path)).delete();
			}
	
			System.out.println("Creating plan");
			Plan plan = client.getPlan(args);
			System.out.println("Plan created");
			LocalExecutor.execute(plan);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test_main(String query, String outputfile, String procs) {
		try {
			//delete output file (only temporary)
			(new java.io.File(outputfile)).delete();
						
			XClient client = new XClient();
						
			System.out.println("Creating plan");
			Plan plan = client.getPlan(query, outputfile, procs);
			System.out.println("Plan created");
			LocalExecutor.execute(plan);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
