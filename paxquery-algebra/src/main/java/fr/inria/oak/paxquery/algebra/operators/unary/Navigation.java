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
package fr.inria.oak.paxquery.algebra.operators.unary;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;


/**
 * Tree pattern navigation logical operator.
 * 
 */
public class Navigation extends BaseUnaryOperator {
	
	private static final Log logger = LogFactory.getLog(Navigation.class);


	public NavigationTreePattern navigationTreePattern;
	
	public int pos;

	
	public Navigation(BaseLogicalOperator op, int pos, NavigationTreePattern navigationTreePattern) {
		super(op);
		
		this.navigationTreePattern = navigationTreePattern;
		this.pos = pos;
		this.ownName = "Navigation";
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(pos + "=>" + navigationTreePattern.toString() );
		sb.append("]");
		this.ownDetails = new String(sb);
		this.visible = true;
	}
	
	@Override
	public void buildNRSMD() {
		for(BaseLogicalOperator op : children)
			op.buildNRSMD();
		this.nestedMetadata = NestedMetadataUtils.appendNRSMD(this.getChild().getNRSMD(),
				NestedMetadataUtils.getNRSMD(this.navigationTreePattern.getRoot(), new HashMap<Integer, HashMap<String, ArrayList<Integer>>>()));
	}
}
