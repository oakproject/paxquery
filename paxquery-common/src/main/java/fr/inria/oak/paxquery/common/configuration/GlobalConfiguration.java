/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
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
package fr.inria.oak.paxquery.common.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Very simple class that stores globally shared variables.
 *
 */
public class GlobalConfiguration {
	
	private static final Log logger = LogFactory.getLog(GlobalConfiguration.class);
	
	public enum ConfigurationParameters {
		START_DELIMITER("extractor.namespace.startdelimiter", "{"),
		END_DELIMITER("extractor.namespace.enddelimiter", "}");
			
		    
		private final String name;
		private final String value;
		
		private ConfigurationParameters(final String name, final String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getString() {
			return this.value;
		}

	}
}
