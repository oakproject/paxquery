/*******************************************************************************
 * Copyright (C) 2013, 2014 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fr.inria.oak.paxquery.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.api.common.Plan;
import eu.stratosphere.api.common.Program;
import eu.stratosphere.api.common.ProgramDescription;
import eu.stratosphere.core.fs.FSDataInputStream;
import eu.stratosphere.core.fs.FileSystem;
import eu.stratosphere.core.fs.Path;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.test.parser.LogicalPlanParser;
import fr.inria.oak.paxquery.translation.Logical2Pact;


/**
 * The query processor based on algebra expressions. It parses the logical plan
 * taken from the filesystem, it translates it into PACT, and it executes the
 * resulting PACT program.
 *
 */
public class QueryProcessor implements Program, ProgramDescription {
	
	private static final Log logger = LogFactory.getLog(QueryProcessor.class);


	@Override
	public String getDescription() {
		return "Parameters: [logplanfile] [numbersubtasks]";
	}


	@Override
	public Plan getPlan(String... args) {
		// parse program parameters
		final String logplanfile   = (args.length > 0 ? args[0] : "");
		final int noSubtasks       = (args.length > 1 ? Integer.parseInt(args[1]) : -1);
		
		Plan plan = null;
		
		try {
			Path pathToPlanFile = new Path(logplanfile);
			final FileSystem fs = pathToPlanFile.getFileSystem();
			FSDataInputStream fsdis = fs.open(pathToPlanFile);

			BaseLogicalOperator logPlan = LogicalPlanParser.parseFile(fsdis);
					
			plan = Logical2Pact.planTranslate(logPlan);
			plan.setDefaultParallelism(noSubtasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return plan;
	}
}
