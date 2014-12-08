package fr.inria.oak.paxquery.xparser.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.Plan;
import org.apache.flink.api.common.Program;
import org.apache.flink.api.common.ProgramDescription;
import org.apache.flink.client.LocalExecutor;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.optimizer.Optimizer;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.PrintingLevel;
import fr.inria.oak.paxquery.translation.Logical2Pact;
import fr.inria.oak.paxquery.xparser.XQueryLexer;
import fr.inria.oak.paxquery.xparser.XQueryParser;
import fr.inria.oak.paxquery.xparser.XQueryVisitorImplementation;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class XClient implements Program, ProgramDescription {

	private static final long serialVersionUID = 1808382941034073434L;

	@Override
	public String getDescription() {
		return "Parameters: file://[queryfile] file://[resultsfile] <numbersubtasks> <drawtrees graph_path>\nor\nParameters: query_string file://[resultsfile] <numbersubtasks> <drawtrees graph_path>";
	}
	
	@Override
	public Plan getPlan(String... args) {
		// parse program parameters
		final String query = (args.length > 0 ? args[0] : "");
		final String outputfile = (args.length > 1 ? args[1] : "");
		final int noSubtasks = (args.length > 2 ? Integer.parseInt(args[2]) : -1);
		final boolean drawTrees = (args.length > 3 && args[3].compareTo("drawtrees")==0 ? true : false);
		final String graphsPath = (args.length > 4 ? args[4] : outputfile);
			
		Plan plan = null;
		BaseLogicalOperator op = null;

		if(drawTrees) {
			deleteOldGraphFiles(graphsPath);
			generateQueryWeb(query, graphsPath);
		}
		
		try {
			InputStream inputStream;
			
			if(query.startsWith("file://")) {
				Path pathToQueryFile = new Path(query);
				final FileSystem fs = pathToQueryFile.getFileSystem();
				inputStream = fs.open(pathToQueryFile);
			}
			else
				inputStream = new ByteArrayInputStream(query.getBytes("UTF-8"));
			//original algebraic plan
			LogicalPlan logPlan = this.parseQuery(inputStream, outputfile, drawTrees, graphsPath); 
			//op = logPlan.getRoot();
			op = logPlan.getRoot();
			
		} catch(Exception e) {
			System.err.println("Query malformed or not supported yet.");
			e.printStackTrace();
			System.exit(1);
		}
		
		//create the PACT plan
		try {
			System.out.println("Translating algebraic plan to PACT plan.");
			plan = Logical2Pact.planTranslate(op);
			plan.setDefaultParallelism(noSubtasks);
			//System.out.println("Plans finished.");
			
			if(drawTrees) {
				//PACT drawing
				String planJSON = LocalExecutor.getPlanAsJSON(plan);
				String planDOT = pactJSONtoDOT(planJSON);
				//printDOTFile(outputfile, planDOT);
				printDOTFile(graphsPath, planDOT);
			}
				
			
		} catch(Exception e) {
			System.err.println("Error executing the query.");
			e.printStackTrace();
		}
		
		return plan;
	}
	
	private LogicalPlan parseQuery(InputStream inputStream, String outputpath, boolean drawTrees, String graphsPath) throws Exception {
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
		String normalizedOutputPath = outputpath.startsWith("file://") ? outputpath : "file://"+outputpath;
		XQueryVisitorImplementation loader = new XQueryVisitorImplementation(normalizedOutputPath);
		loader.visit(tree);

		//print output
		System.out.println("Creating algebraic plan.");
		System.out.println("Original algebraic plan: ");
		printParseDetails(tree, parser, loader);
		//draw Logical Plan 
		if(drawTrees) {
			NavigationTreePattern.setGraphicsPath(graphsPath);
			NavigationTreePattern.resetPrintCardinal();
			NavigationTreePattern.setPrintGraphics(true);
			//drawLogicalPlan(loader.logicalPlan, outputpath, false);
			drawLogicalPlan(loader.logicalPlan, graphsPath, false);
			generateTreePatternsWeb(graphsPath);
		}
		
		//optimized algebraic plan
		System.out.println("Optimizing algebraic plan.");
		(new Optimizer()).optimize(loader.logicalPlan);
		System.out.println("Optimized algebraic plan: ");
		printParseDetails(tree,parser,loader);
		//draw Logical Plan 
		if(drawTrees) {
			XMLScan.resetColorCounter();
			NavigationTreePattern.setPrintGraphics(false);
			//drawLogicalPlan(loader.logicalPlan, outputpath, true);
			drawLogicalPlan(loader.logicalPlan, graphsPath, true);
		}
		
		
	
		return loader.logicalPlan;
	}
	
	private void generateTreePatternsWeb(String graphsPath) {
		try {
		//String webstring = read stub
		File stubFile = new File(FileSystems.getDefault().getPath(graphsPath, "..", "..", "stubs", "treepatterns.html").toString());
		String stubString = FileUtils.readFileToString(stubFile);
		
		//StringBuilder dynamicstring = create dynamic part
		File graphsFolder = new File(graphsPath);
		int tpcounter = 0;
		StringBuilder dynamicString = new StringBuilder();
		//for(File file : FileUtils.iterateFiles(stubFile, new String[] {".png"}, false))
		for(File file : FileUtils.listFiles(graphsFolder, new String[] {"png"}, false)) {
			if(file.getName().startsWith("tp")) {
				//dynamicString.append("<h1>Tree Pattern ");
				//dynamicString.append(tpcounter);
				//dynamicString.append("</h1>");
				dynamicString.append("<img src=\"images/plans/");
				dynamicString.append(file.getName());
				dynamicString.append("\" alt=\"\" /><br><hr>");
				tpcounter++;
			}
		}
		
		//webstring.replace("%REPLACE%", dynamicstring)
		stubString = stubString.replaceAll("%REPLACE%", dynamicString.toString());
		//write webstring to file
		File resultFile = new File(FileSystems.getDefault().getPath(graphsPath, "..", "..", "treepatterns.html").toString());
		FileUtils.writeStringToFile(resultFile, stubString.toString());
		} catch(Exception e) {
			System.out.println("Exception creating the tree patterns web file: " + e.getMessage());
		}
	}
	
	private void generateQueryWeb(String queryPath, String graphsPath) {
		try {
			File stubFile = new File(FileSystems.getDefault().getPath(graphsPath, "..", "..", "stubs", "query.html").toString());
			String stubString = FileUtils.readFileToString(stubFile);
			File queryFile = null;
			if(queryPath.startsWith("file://")) 
				queryFile = new File(FileSystems.getDefault().getPath(queryPath.substring("file://".length())).toString());
			else
				queryFile = new File(FileSystems.getDefault().getPath(queryPath).toString());
			//String queryString = FileUtils.readFileToString(queryFile);
			List<String> queryLines = FileUtils.readLines(queryFile);
			
			//stubString = stubString.replaceAll("%REPLACE%", "hey");
			StringBuilder sb = new StringBuilder();
			int index = stubString.indexOf("%REPLACE%");
			sb.append(stubString.substring(0, index));
			//sb.append(queryString);
			for(String line : queryLines) {
				sb.append(line.replaceAll("<", "&lt;"));
				sb.append("<br>");
			}
			sb.append(stubString.substring(index+"%REPLACE%".length()));
			
			File resultFile = new File(FileSystems.getDefault().getPath(graphsPath, "..", "..", "query.html").toString());
			FileUtils.writeStringToFile(resultFile, sb.toString());			
		} catch(Exception e) {
			System.out.println("Exception creating the query web file: " + e.getMessage());
		}
	}
	
	private void deleteOldGraphFiles(String graphsPath) {
		try {
			FileUtils.cleanDirectory(new File(graphsPath));
			//java.nio.file.Path pathDOT = FileSystems.getDefault().getPath(graphsPath, "*.dot");
			//java.nio.file.Path pathPNG = FileSystems.getDefault().getPath(graphsPath, "*.png");
			//boolean successDOT = Files.deleteIfExists(pathDOT);
			//boolean successPNG = Files.deleteIfExists(pathPNG);
		} catch(IOException ioe) {
			System.out.println("Exception deleting old graph files: " + ioe.getMessage());
		}
	}
	
	private void printParseDetails(ParseTree tree, XQueryParser parser, XQueryVisitorImplementation loader) {
		System.out.println();
		System.out.println("PARSE OUTPUT: ");
		System.out.println(tree.toStringTree(parser));
		
		System.out.println();
		System.out.println("TREE PATTERNS:");
		for(int i = 0; i < loader.navigationTreePatterns.size(); i++) {
			System.out.println("TreePattern ("+i+"): ");
			System.out.println(loader.navigationTreePatterns.get(i).toString(PrintingLevel.SIMPLIFY));
		}
		System.out.println();
		System.out.println("PATTERN-NODE MAP:");
		System.out.println(loader.patternNodeMap.toString());
		System.out.println();
		System.out.println("TEMPORARY VAR POSITIONS: ");
		System.out.println(loader.varMap.toString());
		System.out.println();
		System.out.println("LOGICAL PLAN:");
		System.out.println(loader.logicalPlan.toString());
		System.out.println();
		System.out.println("CONSTRUCTION TREE:");
		System.out.println(loader.constructionTreePattern.toString());
		System.out.println();
	}
	
	private void drawLogicalPlan(LogicalPlan logicalPlan, String outputPath, boolean optimized) {
		//String givenName = optimized ? outputPath.substring(outputPath.lastIndexOf("/")+1, outputPath.lastIndexOf("."))+"-optimized" : outputPath.substring(outputPath.lastIndexOf("/")+1, outputPath.lastIndexOf("."))+"-initial";
		String givenName;
		/*if(outputPath.endsWith("/") == true)
			givenName = outputPath;
		else
			givenName = outputPath + "/";*/
		if(optimized)
			givenName = "xoutput-optimized";
		else
			givenName = "xoutput-initial";

		if(outputPath.startsWith("file://"))
			outputPath = outputPath.substring("file://".length());
		//String folderName = outputPath.substring(0, outputPath.lastIndexOf("/"));
		String folderName = outputPath;
		
		logicalPlan.draw(folderName, givenName);
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
	
			//System.out.println("Creating plan");
			Plan plan = client.getPlan(args);
			
			
			//System.out.println("Plan created");
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
	
	private static String pactJSONtoDOT(String pact_json) {

		int colorIndex = 0;
		String[] color = new String[] {"#ffcfbf", "#99ff99", "#ff99cc"};
		String[] fillColor = new String[] {"#ff9999", "#cfffbf", "#ffbfef"};
		
		StringBuilder output = new StringBuilder();
		String newline = System.getProperty("line.separator");
		try {
			output.append("digraph PACT {"); output.append(newline);
			output.append("size =\"4,4\""); output.append(newline);

			JSONParser parser = new JSONParser();
			JSONObject mainobject = (JSONObject) parser.parse(pact_json);
			JSONArray array = (JSONArray) mainobject.get("nodes");
			for(Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				//print node
				Number id = (Number) jsonobject.get("id");
				String pact = (String) jsonobject.get("pact");
				String contents = (String) jsonobject.get("contents");
				output.append("N"+id+" [label=\"");
				output.append(pact);
				if(pact.compareTo("Data Source") == 0) {
					output.append("\" color=\"" + color[colorIndex] + "\" style=\"filled\" fillcolor=\"" + fillColor[colorIndex] + "\" shape=box];");
					colorIndex = (colorIndex + 1) % color.length;
				}
				else if(pact.compareTo("Data Sink") == 0)
					output.append("\" shape=box];");
				else
					output.append(" ("+contents+")\" shape=box];");
				output.append(newline);
				//print edges
				JSONArray preds_array = (JSONArray) jsonobject.get("predecessors"); 
				if(preds_array != null) {
					for(Object preds_object : preds_array) {
						Number pred_id = (Number) ((JSONObject)preds_object).get("id");
						output.append("N"+id+" -> N"+pred_id+" [dir=back];");
						output.append(newline);
					}
				}
			}
			output.append("}");
				
		} catch (ParseException je) {
			return "";
		}

		return output.toString();		
	}
	
	private static void printDOTFile(String filePath, String dotString) {
		String filePathDot = filePath;
		String filePathPNG = filePath;
		if(filePathDot.startsWith("file://")) {
			filePathDot = filePathDot.substring("file://".length());
			filePathPNG = filePathPNG.substring("file://".length());
		}
		/*int extensionIndex = filePathDot.lastIndexOf(".");
		if(extensionIndex > -1) {
			filePathDot = filePathDot.subSequence(0, extensionIndex) + "-pact.dot";
			filePathPNG = filePathPNG.subSequence(0,  extensionIndex) + "-pact.png";
		}
		else {
			filePathDot = filePathDot + "-pact.dot";
			filePathPNG = filePathPNG + "-pact.png";
		}*/
		if(filePath.endsWith("/") == true) {
			filePathDot = filePathDot + "xoutput-pact.dot";
			filePathPNG = filePathPNG + "xoutput-pact.png";
		}
		else {
			filePathDot = filePathDot + "/xoutput-pact.dot";
			filePathPNG = filePathPNG + "/xoutput-pact.png";
		}
			
		
		try {
			//print the dot file
			FileWriter writer = new FileWriter(filePathDot, false);
			writer.write(dotString);
			writer.close();			
			Runtime r = Runtime.getRuntime();
			String com = new String("/usr/local/bin/dot -Tpng " + filePathDot + " -o " + filePathPNG);
			Process p = r.exec(com);
			p.waitFor();
			//System.out.println("PACT plan drawn.");
		}
		catch(IOException ioe) {
			System.out.println("Error with pact file: "+ioe.getMessage());
		}
		catch(InterruptedException ie) {
			System.out.println("Error with pact file: "+ie.getMessage());
		}
	}
}
