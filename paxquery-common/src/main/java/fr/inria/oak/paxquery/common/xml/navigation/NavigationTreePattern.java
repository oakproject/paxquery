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
package fr.inria.oak.paxquery.common.xml.navigation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class models the navigation tree patterns.
 * 
 */
public final class NavigationTreePattern implements Serializable {

	/**
	 * Universal version identifier for TreePattern class
	 */
	private static final long serialVersionUID = 6770099296197279852L;

	private static final Log logger = LogFactory.getLog(NavigationTreePattern.class);
	
	/**
	 * Unique identifier for the Pattern
	 */
	private long patternID;
	
	/**
	 * The name of the Pattern
	 */
	private String name;
	
	private NavigationTreePatternNode root;

	private boolean ordered;
	
	private BitSet paths;
	
	
	public NavigationTreePattern() {
	}
	
	public NavigationTreePattern(NavigationTreePatternNode r, boolean ordered) {
		this.root = r;
		this.ordered = ordered;
	}

	public long getPatternID() {
		return patternID;
	}

	public void setPatternID(long patternID) {
		this.patternID = patternID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ordered
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * @param ordered the ordered to set
	 */
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(NavigationTreePatternNode root) {
		this.root = root;
	}

	/**
	 * Returns the XamNode which is the "top" node of this xam
	 * 
	 * @return
	 */
	public NavigationTreePatternNode getRoot() {
		return root;
	}

	/**
	 * Counts the number of nodes in this subtree. Does not count the root.
	 * 
	 * @return number of nodes for which there will be stacks.
	 */
	public int getNodesNo() {
		return (root.getNumberOfNodes() - 1);
	}
	
	/**
	 * Counts the number of nodes in this subtree that return an
	 * ID, Cont, Val. Considers the root in the counting
	 * 
	 * @return number of nodes for which there will be stacks.
	 */
	public int getRetNodesNo() {
		return (root.getNumberOfReturningNodes());
	}
	
	/**
	 * Gets the complete list of nodes that belong to this tree pattern.
	 * @return the list of nodes
	 */
	public LinkedList<NavigationTreePatternNode> getNodes() {
		if (root != null)
			return root.getNodes();
		return null;
	}
	
	/**
	 * The following method is used for obtaining the simplify representation
	 * of a tree pattern. It is equivalent to calling the method
	 * {@link #toString(PrintingLevel.SIMPLIFY)}.
	 */
	public String toString(){
		return (this.root.treeToString(PrintingLevel.SIMPLIFY));
	}
	
	/**
	 * The following method is used for obtaining the representation
	 * of a tree pattern.
	 * We can specify several level of details while transforming the
	 * tree into a String.
	 * The levels of detail are defined in the {@link PrintingLevel}
	 * enumeration.
	 * 
	 * @param level of detail
	 * @return the tree representation
	 */
	public String toString(PrintingLevel level){
		return (this.root.treeToString(level));
	}

	/**
	 * The following method is used for displaying the simplify representation
	 * of a tree pattern.
	 */
	public void display() {
		if(logger.isInfoEnabled())
			logger.info("#>#>  " + this.root.treeToString(PrintingLevel.SIMPLIFY));
	}
	
	/**
	 * Get a String ready for using in dot and get an image representation of
	 * the pattern.
	 * @param givenFilename
	 * @return
	 */
	public String getDotString(String givenFilename, String backgroundColor, String foregroundColor) {
		StringBuffer sb = new StringBuffer();

		sb.append("graph  " + givenFilename + "{\n");
		sb.append("node [fontname=\"Lucida Grande\" fontsize=18 color=\"white\"]\n");
		this.root.drawTree(sb, backgroundColor, foregroundColor);
		sb.append("}\n");
		
		return new String(sb);
	}
	
	/**
	 * Method that creates an image representation of the pattern and stores it to a file
	 * @param givenFilename the name of the file where the image will be saved
	 * @param query true if the pattern that we want to draw is a query or false otherwise.
	 * Query nodes will be filled in yellow and view nodes will be filled in blue
	 */
	public void draw(String imagesPath, String givenFilename, boolean query, String backgroundColor, String foregroundColor) 
	{
		String fileName;
		Calendar cal = new GregorianCalendar();
		if(givenFilename == null) {
			if(query)
				fileName = "xam-" + "-" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "-Query";
			else
				fileName = "xam-" + "-" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		} else {
			if(query)
				fileName = givenFilename + "-Query";
			else
				fileName = givenFilename;
		}
		
		String sb = getDotString(fileName, backgroundColor, foregroundColor);
		
		try 
		{
			String fileNameDot =  new String(fileName + ".dot");
			String fileNamePNG;
			if(fileName.contains("/"))
				fileNamePNG = new String(fileName + ".png");
			else
				fileNamePNG = new String(imagesPath + File.separator + fileName + ".png");
			FileWriter file = new FileWriter(fileNameDot);
			
			// writing the  .dot file to disk
			file.write(sb);
			file.close();
			
			// calling GraphViz
			Runtime r = Runtime.getRuntime();
			String com = new String("/usr/local/bin/dot -Tpng " + fileNameDot + " -o " + fileNamePNG);
			Process p = r.exec(com);
			p.waitFor();
			// removing the .dot file
			Process p2=r.exec("rm "+fileNameDot+"\n");
			p2.waitFor();
		}
		catch (IOException e) {
			logger.error("IOException: ",e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException: ",e);
		}
	}

	public final NavigationTreePattern deepCopy(){
		NavigationTreePattern p = new NavigationTreePattern(this.root.deepCopy(), this.ordered);
		p.name = this.name;
		return p;
	}

	/**
	 * Gives fresh numbers to all nodes in a tree pattern.
	 * 
	 */
	public void renumberNodes() {
		Stack<NavigationTreePatternNode> st = new Stack<NavigationTreePatternNode>();
		st.push(this.root);
		while (!st.empty()){
			NavigationTreePatternNode pn = st.pop();
			if (pn.getNodeCode() == -1){
				// nothing
				//Parameters.logger.debug("-1 node! Left unchanged");
			}
			else{
				pn.setNodeCode(NavigationTreePatternNode.globalNodeCounter.getAndIncrement());
			}
			Iterator<NavigationTreePatternEdge> ie = pn.getEdges().iterator();
			while (ie.hasNext()){
				st.push(ie.next().n2);
			}
		}
	}
	
	/**
	 * Same as {@link #renumberNodes()}, but it starts the numbering from the localCounter.
	 * 
	 * @author Konstantinos KARANASOS
	 */
	public void renumberNodes(int localCounter) {
		Stack<NavigationTreePatternNode> st = new Stack<NavigationTreePatternNode>();
		st.push(this.root);
		while (!st.empty()){
			NavigationTreePatternNode pn = st.pop();
			if (pn.getNodeCode() == -1){
				// nothing
				//Parameters.logger.debug("-1 node! Left unchanged");
			}
			else{
				pn.setNodeCode(localCounter);
				localCounter++;
			}
			Iterator<NavigationTreePatternEdge> ie = pn.getEdges().iterator();
			while (ie.hasNext()){
				st.push(ie.next().n2);
			}
		}
	}
	
	/**
	 * Calls {@link #renumberNodes(int)}, using as localCounter the nodeCode of the first node of the given Pattern.
	 * However, if the nodeCode of the first nodes (immediately below the root) of the two patterns are the same, the 
	 * renumbering is not performed (so as to avoid unnecessary renumbering).
	 * @author Konstantinos KARANASOS
	 */
	public void renumberNodes(NavigationTreePattern patToUse) {
		int patToUseFirstNodeCode = patToUse.getRoot().getEdges().get(0).n2.getNodeCode();
		if (this.getRoot().getEdges().get(0).n2.getNodeCode() != patToUseFirstNodeCode)
			renumberNodes(patToUse.getRoot().getEdges().get(0).n2.getNodeCode());
	}
	
	/**
	 * Method that transforms the tree pattern its equivalent in a XAM file.
	 *
	 * @return the tree pattern representation for a XAM file
	 */
	public String toXAMString(){
		StringBuffer nodeBuffer = new StringBuffer();
		nodeBuffer.append(this.ordered?"o":"");
		NavigationTreePatternEdge rootEdge = ((NavigationTreePatternEdge)this.root.getEdges().get(0));
		nodeBuffer.append(rootEdge.isParent()?"/\n":"");
		StringBuffer edgeBuffer = new StringBuffer();
		NavigationTreePatternNode realRoot = rootEdge.n2;
		realRoot.toXAMString(nodeBuffer, edgeBuffer, 0, 1);
		return new String(nodeBuffer) + "\n;\n" + new String(edgeBuffer);
	}
	
	public BitSet getBitMap() {
		return paths;
	}

	public void setPathBitMap(BitSet arg0) {
		this.paths = arg0;
	}	

	/**
	 * Returns a xam that contains all my nodes except the hierarchy under n.
	 * In other words: all n ancestors and their descendants except for those that are also
	 * the descendants of n.
	 * @param n
	 * @return
	 */
	public NavigationTreePattern getCopyAbove(NavigationTreePatternNode n){
		//NodeInformation.Parameters.logger.debug("Getting copy of " + this.toString() + " above XAMNodeHashCode: "  + 
		//		n.XAMNodeHashCode + " nodeCode: " + n.nodeCode);
		NavigationTreePattern p = this.deepCopy();
		NavigationTreePatternNode copyOfN = p.root.locate(n.getNodeCode());
		assert (copyOfN != null) : "Erroneous call to getCopyAbove";
		copyOfN.cleanEdges();
		return p;
	}
	
	public NavigationTreePattern getPathAbove(NavigationTreePatternNode n1) {
		NavigationTreePattern p = this.deepCopy();
		p.root.pruneAllButPathTo(n1);
		return p;
	}
	
	public void minimize() {
		// TODO logical minimization a la Amer-Yahia		
	}
		
	public ArrayList<String> getColumnsNames() {
		LinkedList<NavigationTreePatternNode> nodes = this.getNodes();
		LinkedList<String> list = new LinkedList<String>();
		list.add("Document ID");
		for(NavigationTreePatternNode node:nodes)
			list.addAll(node.getColumnsName());
		return new ArrayList<String>(list);
	}
	
	public List<Boolean> getColumnsStoringValue() {
		LinkedList<NavigationTreePatternNode> nodes = this.getNodes();
		List<Boolean> list = new ArrayList<Boolean>();
		list.add(false);
		for(NavigationTreePatternNode node:nodes)
			list.addAll(node.getColumnsStoringValue());
		return list;
	}
	
	public List<Boolean> getColumnsBelongingToAttribute() {
		LinkedList<NavigationTreePatternNode> nodes = this.getNodes();
		List<Boolean> list = new ArrayList<Boolean>();
		list.add(false);
		for(NavigationTreePatternNode node:nodes)
			list.addAll(node.getColumnsBelongingToAttribute());
		return list;
	}

	/**
	 * This method will return an {@link ArrayList} containing 3 strings each representing
	 * a transcription of the TreePattern into a aquax expression.
	 * The first string will contain the only the needed nodes from the pattern.
	 * The second string will contain all the nodes in the pattern.
	 * The third string will have the needed nodes as mandatory and the others as optional. 
	 * 
	 * @return an {@link ArrayList} containing 3 {@link String} objects with the significance described above.
	 */
	public ArrayList<String> convertToAquax() {
		
		// the list that will contain the aquax form for the pattern
		ArrayList<String> aquaxTranslations = new ArrayList<String>();
		LinkedList<NavigationTreePatternNode> optionalNodes = this.getNodes();
		LinkedList<NavigationTreePatternNode> neededNodes = new LinkedList<NavigationTreePatternNode>();
		
		for(NavigationTreePatternNode node : optionalNodes) {
			if (node.nodeStoresSomething()) {
				neededNodes.add(node);
			}
		}

		optionalNodes.removeAll(neededNodes);
		
		// creating the first string that contains all the needed Nodes
		StringBuffer sb = new StringBuffer(), sb2 = new StringBuffer();
		
		for (NavigationTreePatternNode n : neededNodes) {
			if (n.getParentEdge().n1.getNodeCode() == -1)
				sb.append(n.getNodeCode() + ":" + 0 + ":m " + n.getTag() + " ");
			else
				sb.append(n.getNodeCode() + ":" + n.getParentEdge().n1.getNodeCode() + ":m " + n.getTag() + " ");
		}

		aquaxTranslations.add(sb.toString());
		
		sb2.append(sb);
		
		// second and third strings containing both the needed and the other nodes, in one as mandatory, in the other as optional
		for (NavigationTreePatternNode n : optionalNodes) {
			if (n.getParentEdge().n1.getNodeCode() == -1) {
				sb.append(n.getNodeCode() + ":" + 0 + ":m " + n.getTag() + " ");
				sb2.append(n.getNodeCode() + ":" + 0 + ":o " + n.getTag() + " ");
			}
			else {
				sb.append(n.getNodeCode() + ":" + n.getParentEdge().n1.getNodeCode() + ":m " + n.getTag() + " ");
				sb2.append(n.getNodeCode() + ":" + n.getParentEdge().n1.getNodeCode() + ":o " + n.getTag() + " ");
			}
		}
		
		aquaxTranslations.add(sb.toString());
		aquaxTranslations.add(sb2.toString());
		
		return aquaxTranslations;
	}
	
	/**
	 * Gets the normal form of a tree pattern.
	 * @return the normal tree pattern
	 */
	public NavigationTreePattern getNormalizedTreePattern() {
		NavigationTreePattern normalizedTreePattern = deepCopy();
		
		int counter = 1;
		TreeSet<NavigationTreePatternNode> sortedSetChildrenNodes = new TreeSet<NavigationTreePatternNode>();
		int newCounter = counter + normalizedTreePattern.root.getEdges().size();
		for(NavigationTreePatternEdge edge:normalizedTreePattern.root.getEdges()) {
			newCounter = recGetNormalizedTreePattern(edge.n2,newCounter);
			sortedSetChildrenNodes.add(edge.n2);
		}
		
		normalizedTreePattern.root.cleanEdges();
		for(NavigationTreePatternNode node:sortedSetChildrenNodes.descendingSet()) {
			node.setNodeCode(counter);
			counter++;
			node.getParentEdge().n1.addEdge(node.getParentEdge());
		}
				
		return normalizedTreePattern;
	}
	
	/**
	 * Recursive method called in order to create the normalized tree pattern.
	 * 
	 * @param node a tree pattern node, we will order its children
	 * @param counter the value of the counter that we are using for renumbering the tree pattern nodes
	 * @return the counter that we are using for renumbering the tree pattern nodes
	 */
	private int recGetNormalizedTreePattern(NavigationTreePatternNode node, int counter) {
		if(node.getEdges().isEmpty())
			return counter;
		
		TreeSet<NavigationTreePatternNode> sortedSetChildrenNodes = new TreeSet<NavigationTreePatternNode>();
		int newCounter = counter + node.getEdges().size();
		for(NavigationTreePatternEdge edge:node.getEdges()) {
			newCounter = recGetNormalizedTreePattern(edge.n2, newCounter);
			sortedSetChildrenNodes.add(edge.n2);
		}
	
		node.cleanEdges();
		for(NavigationTreePatternNode child:sortedSetChildrenNodes.descendingSet()) {
	
			child.setNodeCode(counter);
			counter++;
			child.getParentEdge().n1.addEdge(child.getParentEdge());
		}
		
		return newCounter;
	}
	
	public NavigationTreePattern deepCopyWithReplace(NavigationTreePatternNode unfoldingNode, NavigationTreePatternNode pNew) {
		return new NavigationTreePattern(root.deepCopyWithReplace(unfoldingNode, pNew), this.ordered);
	}
	
	public void parseUnrequiredData(String tagOfTuplesToKeep) {
		this.root.parseUnrequiredData(tagOfTuplesToKeep);
	}
}
