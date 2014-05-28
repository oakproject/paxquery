package fr.inria.oak.paxquery.xparser.client;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;

import eu.stratosphere.api.common.Plan;
import eu.stratosphere.api.common.Program;
import eu.stratosphere.api.common.ProgramDescription;
import eu.stratosphere.core.fs.FSDataInputStream;
import eu.stratosphere.core.fs.FileSystem;
import eu.stratosphere.core.fs.Path;
import eu.stratosphere.client.LocalExecutor;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.translation.Logical2Pact;
import fr.inria.oak.paxquery.xparser.XQueryLexer;
import fr.inria.oak.paxquery.xparser.XQueryParser;
import fr.inria.oak.paxquery.xparser.XQueryVisitorImplementation;

public class XClient implements Program, ProgramDescription {

	@Override
	public String getDescription() {
		return "Parameters: [queryfile] [numbersubtasks]";
	}
	
	@Override
	public Plan getPlan(String... args) {
		// parse program parameters
		final String queryfile = (args.length > 0 ? args[0] : "");
		final int noSubtasks = (args.length > 1 ? Integer.parseInt(args[1]) : -1);
			
		Plan plan = null;
		BaseLogicalOperator op = null;
		
		//parse the query with the XQueryVisitorImplementation
		try {
			Path pathToQueryFile = new Path(queryfile);
			final FileSystem fs = pathToQueryFile.getFileSystem();
			FSDataInputStream fsdis = fs.open(pathToQueryFile);

			op = this.parseQuery(fsdis);			
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
	
	private BaseLogicalOperator parseQuery(InputStream inputStream) throws Exception {
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
		XQueryVisitorImplementation loader = new XQueryVisitorImplementation("file:///Users/jalvaro/xoutput.txt");
		loader.visit(tree);
		
		return loader.construct;
	}
	
	public static void main(String[] args) {
		try {
			//only temporary
			(new java.io.File("/Users/jalvaro/xoutput.txt")).delete();

			XClient client = new XClient();
			
			if(args.length < 2) {
				System.err.println(client.getDescription());
				System.exit(1);
			}
			
			System.out.println("Creating plan");
			Plan plan = client.getPlan(args);
			System.out.println("Plan created");
			LocalExecutor.execute(plan);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
