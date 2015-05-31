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
package fr.inria.oak.paxquery.pact.operations.xml.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class encapsulates the functions of stack in the particular case when we will
 * put on the stack ExtractorMatches.
 * The stack knows "it is related to" other stacks, i.e. the stack corresponding to the
 * xam node one level up, and the stacks corresponding to the children (in the xam)
 * of the node corresponding to this stack.
 * 
 * Its proximity to the DataExtractor enables this one to search in the stack from the bottom up,
 * i.e. from the oldest to the most recent element on the stack.
 * Another non-stack style operation retrieves the entry from the stack corresponding to a given
 * number.
 * 
 */
public class ExtractorMatchStack {
	
	private static final Log logger = LogFactory.getLog(ExtractorMatchStack.class);
	
	/**
	 * If it is an attribute. If false, it is an element.
	 */
	public boolean isAttribute;

	/**
	 * The tags of nodes that are matched here, or "*" if any tag matches.
	 */
	public String tag;

	/*
	 * ArrayList of StackEntries.
	 */
	private TreeMap<Integer, ExtractorMatch> entries;
	
	/**
	 * Last open entry in this stack. May be the top of the stack in fact ? In
	 * this case, it would suffice to use the last occupied position in the
	 * array. Needs more thinking.
	 */
	public ExtractorMatch dnop;

	/**
	 * The parent stack: this one is created for the query xam node that is
	 * a parent of the node, for which this stack is created.
	 */
	public ExtractorMatchStack parentStack;

	/**
	 * Stacks created for xam nodes, that are children of the node for
	 * which this stack has been created.
	 */
	ArrayList<Object> childrenStacks;
	
	public ExtractorMatchStack(String tag, boolean isAttribute, ExtractorMatchStack parentStack) {		
		this.tag = tag;
		this.isAttribute = isAttribute;
		this.entries = new TreeMap<Integer, ExtractorMatch>();
		this.dnop = null;
		this.parentStack = parentStack;
		this.childrenStacks = new ArrayList<Object>();
	}

	public void push(ExtractorMatch s) {
		this.entries.put(s.no, s);
		s.theStack = this;
	}

	public void pop() {
		ExtractorMatch victim = this.entries.get(this.entries.lastKey());
		this.entries.remove(victim.no);
	}

	public ExtractorMatch top() {
		if (this.entries.size() > 0){
			return this.entries.get(this.entries.lastKey());
		}
		return null;
	}

	public ExtractorMatch findEntry(int k) {
		return this.entries.get(k);
	}
	
	public void removeEntry(ExtractorMatch se) {
		this.entries.remove(se.no);
	}

	public int getEntriesSize() {
		if (this.entries != null){
			return this.entries.size();
		}
		return 0;
	}

	public void display() 
	{
		if(logger.isDebugEnabled())
			logger.debug(this.toString());
	}
	
	public String toString()
	{
		StringBuffer retStr = new StringBuffer();
		
		int tagLength = this.tag.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tagLength + 6; i++) {
			sb.append("=");
		}
		retStr.append("\n" + "== " + tag + " == \n" + sb);

		Iterator<ExtractorMatch> it = entries.values().iterator();
		while (it.hasNext()) {
			StringBuffer line = new StringBuffer("| ");
			ExtractorMatch se = it.next();
			line.append(se.no);
			if (se.ownParent != null) {
				line.append(" <" + se.ownParent.no);
			}
			line.append(" [");
			Iterator<ArrayList<ExtractorMatch>> it2 = se.childrenByStack.values().iterator();
			while (it2.hasNext()) {
				Iterator<ExtractorMatch> it3 = it2.next().iterator();
				while (it3.hasNext()) {
					ExtractorMatch sChild = it3.next();
					line.append((sChild.erased ? "***" : "") + sChild.no + " ");
				}
			}
			line.append("] |");
			retStr.append(line);
		}

		retStr.append(sb);
		
		return retStr.toString();
	}

}
