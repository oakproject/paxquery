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
package fr.inria.oak.paxquery.common.xml.treepattern.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.predicates.BasePredicate.PredicateType;
import fr.inria.oak.paxquery.common.xml.treepattern.core.Variable.VariableDataType;


/**
 * Class that represents each node of a {@link TreePattern}.
 * 
 */
public final class PatternNode implements Serializable, Comparable<PatternNode> {
	
	/**
	 * Universal version identifier for PatternNode class
	 */
	private static final long serialVersionUID = -5271882998433575092L;
	
	private final String startNamespaceDelimiter;
	private final String endNamespaceDelimiter;
	
	private static final Log logger = LogFactory.getLog(PatternNode.class);

	/**
	 * This variable keeps a global counter of nodes and it is used
	 * to number uniquely the nodes of several patterns.
	 */
	public static AtomicInteger globalNodeCounter = new AtomicInteger(1);

	/* Node related properties */
	/**
	 * If true, this node represents an attribute, and though, only attributes match.
	 * Otherwise, this node represents an element, and though, only elements match.
	 */
	private boolean isAttribute;
	
	/**
	 * If not null, this node (either an attribute or an element) is assigned to one or more variables in the query. 
	 * Otherwise, this node (either an attribute or an element) is not assigned to any variable.
	 * For example, variable $i would be stored in matchingVariables of “name” for the sentence $i := <whatever>/name 
	 */
	//private HashSet<String> matchingVariables;	
	private ArrayList<Variable> matchingVariables;
	
	/**
	 * If the xam is built from a XAM, this is the hash code of the XAM node
	 * from which it originated.
	 */
	private int nodeCode;
	
	/* ID related properties */
	/**
	 * If true, the ID of this element is needed. When {@link #storesID} is set to
	 * true, needs ID is always set to true.
	 */
	private boolean storesID;
	
	/**
	 * If true, the ID of this element is (R)equired, must be known in order to access
	 * the data stored in the xam.
	 */
	private boolean requiresID;
	
	/**
	 * If true, "ID i" was specified for this node in the XAM file, so the identity ID for
	 * this node should be stored. 
	 */
	private boolean identityID;

	/**
	 * If true, "ID o" was specified for this node in the XAM file, so the order preserving ID for
	 * this node should be stored. 
	 */
	private boolean orderID;

	/**
	 * If true, "ID s" was specified for this node in the XAM file, so the structural ID for
	 * this node should be stored. 
	 */
	private boolean structID;

	/**
	 * If true, "ID u" was specified for this node in the XAM file, so the update ID for
	 * this node should be stored. 
	 */
	private boolean updateID;
	
	/* Tag related properties */
	/**
	 * If true, the tag of this element is stored.
	 */
	private boolean storesTag;	
	
	/**
	 * If true, the Tag of this element is (R)equired, must be known in order to access
	 * the data stored in the xam.
	 */
	private boolean requiresTag;
	
	/**
	 * If true, there is a selection on tag.
	 */
	private boolean selectOnTag;
	
	/**
	 * The namespace of this node.
	 */
	private String namespace;
	
	/**
	 * The tag of this node ([Tag="a"]), "" for the xam root, "*" for any tag.
	 */
	private String tag;
	
	/* Value related properties */
	/**
	 * If true, the value of this element is stored.
	 */
	private boolean storesValue;	
	
	/**
	 * If true, the value of this element is (R)equired, must be known in order to access
	 * the data stored in the xam.
	 */
	private boolean requiresVal;
	
	/**
	 * If true, there is a selection on value.
	 */
	private boolean selectOnValue;
	
	private PredicateType selectOnValuePredicate;

	/**
	 * If selectOnValue is true, this is what the value should be ([Val="a"]).
	 */
	private String stringValue;
	
	/**
	 * If selectOnValue is true, this is what the value should be ([Val=a]).
	 */
	private double doubleValue;
	
	/* Content related properties */
	/**
	 * If true, the content of this element is stored.
	 */
	private boolean storesContent;

	/* Edges to other nodes */
	/**
	 * Edge connecting this node to its parent.
	 */
	private PatternEdge parentEdge;
	
	/**
	 * Edges connecting this node to its children.
	 */
	private ArrayList<PatternEdge> edges;

	/**
	 * top returning node
	 * -1: not computed yet
	 * 0: false
	 * 1: true
	 */
	private int isTopReturningNode;
	
	/**
	 * 
	 */
	private int nestingDepth;

	/**
	 * True if this node has been obtained by navigating inside a Cont attribute
	 */
	private boolean virtual;
	
	
	/**
	 * Creates a new node with no children. All properties are set to false
	 * by default.
	 * 
	 * @param namespace the namespace associated to this node
	 * @param tag the tag associated to this node
	 * @param nodeCode the code associated to this node
	 */
	public PatternNode(String namespace, String tag, int nodeCode,
			String startNamespaceDelimiter, String endNamespaceDelimiter) {
		this.namespace = namespace;
		this.tag = tag;
		this.storesTag = false;
		if (tag == null || tag.compareTo("*") == 0) {
			this.namespace = "";
			this.tag = "*";
		} else {
			this.selectOnTag = true;
		}
		this.edges = (new ArrayList<PatternEdge>());
		this.matchingVariables = new ArrayList<Variable>();
		this.storesContent = false;
		this.storesValue = false;
		this.storesID = false;
		this.requiresID = false;
		this.requiresTag = false;
		this.requiresVal = false;
		this.isTopReturningNode = -1;
		this.nodeCode = nodeCode;
		
		this.startNamespaceDelimiter = startNamespaceDelimiter;
		this.endNamespaceDelimiter = endNamespaceDelimiter;
	}
	
	/**
	 * Compare method for pattern nodes based on their namespace and tag.
	 * 
	 * @return the result of the comparison
	 */
	public int compareTo(PatternNode pn) {
		int result = nodeExtendedString().compareTo(pn.nodeExtendedString());
		if(result == 0) {
			return treeToString(PrintingLevel.EXTENDED).compareTo(pn.treeToString(PrintingLevel.EXTENDED));
		}
		return result;
	}
	
	
	public void setVirtual(boolean isVirtual){
		this.virtual = isVirtual;
	}
	
	
	/**
	 * Counts the nodes in this subtree.
	 * 
	 * @return the number of xam nodes
	 */
	public int getNumberOfNodes() {
		if (this.edges == null) {
			return 1;
		}
		if (this.edges.size() == 0) {
			return 1;
		}
		int aux = 1;
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			aux += ((PatternEdge) it.next()).n2.getNumberOfNodes();
		}
		return aux;
	}
	
	/**
	 * Counts the nodes in this subtree that return ID, Cont and Val.
	 * 
	 * @return the number of xam nodes
	 */
	public int getNumberOfReturningNodes() {
		int aux = 0;
		if(this.storesContent || this.storesID || this.storesValue){
			aux++;
		}
		if (this.edges == null){
			return aux;
		}
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			aux += ((PatternEdge) it.next()).n2.getNumberOfReturningNodes();
		}
		return aux;
	}
	
	public int pruneAllButPathTo(PatternNode n1) {
		// Parameters.logger.info("pruneAllButPathTo " + n1.tag + " in " +
		// this.tag);
		if (this.nodeCode == n1.nodeCode) {
			this.edges = (new ArrayList<PatternEdge>());
			// Parameters.logger.info("I am the target, erasing children");
			return 1;
		}
		ArrayList<PatternEdge> newEdges = (new ArrayList<PatternEdge>());
		Iterator<PatternEdge> it = this.edges.iterator();
		while (it.hasNext()) {
			PatternEdge thisEdge = it.next();
			PatternNode child = thisEdge.n2;
			// Parameters.logger.info("Moving to child " + child.tag);
			int k = child.pruneAllButPathTo(n1);
			// Parameters.logger.info("Child " + child.tag + " returned " + k);
			if (k == 1) {
				newEdges.add(thisEdge);
				this.edges = newEdges;
				// Parameters.logger.info("Copying child " + child.tag + ",
				// returning 1");
				return 1;
			}
		}
		// Parameters.logger.info("Erasing all children, returning");
		this.edges = newEdges;
		return 2;
	}
	
	/**
	 * Returns the node of the subtree whose {@link #nodeCode} is 
	 * equal to the value introduced as parameter.
	 * 
	 * @param nx2
	 * @return
	 */
	public PatternNode locate(Integer nx2) {
		// Parameters.logger.info("Locate " + nx2 + " on " + this.tag + " " +
		// this.XAMNodeHashCode);
		if (this.nodeCode == nx2.intValue()) {
			return this;
		} else {
			Iterator<PatternEdge> it = this.edges.iterator();
			while (it.hasNext()) {
				PatternNode n2 = it.next().n2.locate(nx2);
				if (n2 != null) {
					return n2;
				}
			}
		}
		return null;
	}
	
	/**
	 * Recursive method that removes the storage requirements of the subtree
	 * starting from this node, skipping the nodes with the tag specified in
	 * the parameter.
	 * 
	 * @param tagOfTuplesToKeep tag of the nodes whose storage requirements
	 * we want to preserve
	 */
	protected void parseUnrequiredData(String tagOfTuplesToKeep) {
		if(!this.tag.equals(tagOfTuplesToKeep)) {
			this.removeRequirementsToStoreData();
		}
		for(PatternEdge edge : this.edges) {
			edge.n2.parseUnrequiredData(tagOfTuplesToKeep);
		}
	}
	
	/**
	 * Removes the storage requirements from this node. It sets to false
	 * {@link #storesID}, {@link #storesTag}, {@link #storesValue} and
	 * {@link #storesContent}.
	 */
	private void removeRequirementsToStoreData() {
		this.storesID = false;
		this.storesValue = false;
		this.storesContent = false;
		this.storesTag = false;
	}
	
	/* Copy methods */
	/**
	 * Recursive method that returns the copy of the subtree starting from
	 * this node. This method will preserve the {@link #nodeCode} of the
	 * nodes of the subtree.
	 * 
	 * @return the root of the subtree copied
	 */
	public PatternNode deepCopy() {
		PatternNode aux = new PatternNode(this.namespace, this.tag, this.nodeCode, this.startNamespaceDelimiter, this.endNamespaceDelimiter);

		aux.setStoresContent(this.storesContent);
		aux.setStoresID(this.storesID);
		aux.setStoresTag(this.storesTag);
		aux.setStoresValue(this.storesValue);

		aux.setRequiresID(this.requiresID);
		aux.setRequiresTag(this.requiresTag);
		aux.setRequiresVal(this.requiresVal);
		
		aux.setNamespace(this.namespace);
		aux.setSelectOnTag(this.selectOnTag, this.namespace, this.tag);
		if(this.selectOnValue && this.stringValue == null)
			aux.setSelectOnValue(this.selectOnValue, this.selectOnValuePredicate, this.doubleValue);			
		else
			aux.setSelectOnValue(this.selectOnValue, this.selectOnValuePredicate, this.stringValue);

		aux.identityID = this.identityID;
		aux.orderID = this.orderID;
		aux.updateID = this.updateID;
		aux.structID = this.structID;

		aux.isAttribute = this.isAttribute;

		aux.virtual = this.virtual;

		aux.edges = (new ArrayList<PatternEdge>());
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			PatternEdge pe = (PatternEdge) it.next();
			PatternNode childCopy = pe.n2.deepCopy();
			aux.addEdge(childCopy, pe.isParent(), pe.isNested(), pe.isOptional());
		}
		return aux;
	}
	
	/**
	 * Recursive method that returns the copy of the subtree starting from this
	 * node, but replacing the old node introduced as the first parameters for
	 * a new node introduced as the second parameter. This method will preserve
	 * the {@link #nodeCode} of the nodes of the subtree.
	 * 
	 * @param oldNode the old node that we want to replace
	 * @param newNode the new node that will replace the old node
	 * @return the root of the subtree copied
	 */
	public PatternNode deepCopyWithReplace(PatternNode oldNode, PatternNode newNode) {
		if (this == oldNode){
			return newNode;
		}
		
		PatternNode aux = new PatternNode(this.namespace, this.tag, this.nodeCode, this.startNamespaceDelimiter, this.endNamespaceDelimiter);
		
		aux.storesContent = this.storesContent;
		aux.storesID = this.storesID;
		aux.storesTag = this.storesTag;
		aux.storesValue = this.storesValue;
		
		aux.setRequiresID(this.requiresID);
		aux.setRequiresTag(this.requiresTag);
		aux.setRequiresVal(this.requiresVal);
		
		aux.selectOnTag = this.selectOnTag;
		
		aux.selectOnValue = this.selectOnValue;
		if (aux.selectOnValue){
			aux.stringValue = this.stringValue;
		}
		
		aux.identityID = this.identityID;
		aux.orderID = this.orderID;
		aux.updateID = this.updateID;
		aux.structID = this.structID;
		
		aux.isAttribute = this.isAttribute;	
		
		aux.edges = new ArrayList<PatternEdge>();
		
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()){
			PatternEdge pe = it.next();
			PatternNode childCopy = null;
			if (pe.n2 == oldNode){
				childCopy = newNode;
			} else{
				childCopy = pe.n2.deepCopyWithReplace(oldNode, newNode);
			}
			aux.addEdge(childCopy, pe.isParent(), pe.isNested(), pe.isOptional());
		}
		return aux;
	}
	
	/**
	 * Copies a node. This method will preserve the {@link #nodeCode}
	 * of the node.
	 * 
	 * @return the copy of the node
	 */
	public PatternNode nodeCopy() {
		PatternNode aux = new PatternNode(this.namespace, this.tag, this.nodeCode, this.startNamespaceDelimiter, this.endNamespaceDelimiter);

		aux.storesContent = this.storesContent;
		aux.storesID = this.storesID;
		aux.storesTag = this.storesTag;
		aux.storesValue = this.storesValue;

		aux.setRequiresID(this.requiresID);
		aux.setRequiresTag(this.requiresTag);
		aux.setRequiresVal(this.requiresVal);
		
		aux.selectOnTag = this.selectOnTag;
		if (aux.selectOnTag) {
			aux.namespace = this.namespace;
			aux.tag = this.tag;
		}

		aux.selectOnValue = this.selectOnValue;
		if (aux.selectOnValue) {
			aux.stringValue = this.stringValue;
		}

		aux.identityID = this.identityID;
		aux.orderID = this.orderID;
		aux.updateID = this.updateID;
		aux.structID = this.structID;

		aux.isAttribute = this.isAttribute;

		aux.virtual = this.virtual;
		
		return aux;
	}
	
	/**
	 * Method that copies the value of {@link #storesID}, {@link #storesValue} and
	 * {@link #storesContent}, if they are true, from the node introduced in
	 * the parameters to this node.
	 * 
	 * @param node the node whose storage parameters we will copy
	 */
	public void copyAttributesFrom(PatternNode node) {
		if (!this.storesValue && node.storesValue){
			this.storesValue = true;
		}
		if (!this.storesContent && node.storesContent){
			this.storesContent = true;
		}
		if (!this.storesID && node.storesID){
			this.storesID = true;
		}
	}
	
	/**
	 * Copies all the children of the node introduced in the parameters
	 * as the children of this node.
	 * 
	 * @param node the node whose children we want to copy
	 */
	public void copyChildrenFrom(PatternNode node) {
		Iterator<PatternEdge> it = node.edges.iterator();
		while (it.hasNext()) {
			PatternEdge e = it.next();
			PatternNode child = e.n2;
			addEdge(child.deepCopy(), e.isParent(), e.isNested(), e.isOptional());
		}
	}
	
	/**
	 * 
	 * @param edge
	 */
	public void copyVirtualChild(PatternEdge edge){
		PatternNode childCopy = edge.n2.deepCopy();
		// marking childCopy and its subtree as virtual:
		Stack<PatternNode> st = new Stack<PatternNode>();
		st.push(childCopy);
		while (!st.empty()) {
			PatternNode pn = st.pop();
			// Parameters.logger.info("Set virtual node: " + pn.tag);
			pn.virtual = true;
			pn.nodeCode = PatternNode.globalNodeCounter.getAndIncrement();
			// virtual nodes obtained by navigation cannot store ID
			pn.storesID = false;
			Iterator<PatternEdge> pnChildren = pn.edges.iterator();
			while (pnChildren.hasNext()) {
				PatternEdge pnEdge = pnChildren.next();
				st.push(pnEdge.n2);
			}
		}
		addEdge(childCopy, edge.isParent(), edge.isNested(), edge.isOptional());
	}
	
	/* Auxiliary functions to getters and setters */
	
	/**
	 * Returns true if there is some required field underneath this node.
	 * 
	 * @return if there is some required field below
	 */
	public  boolean requiresSomething() {
		if (this.requiresID || this.requiresTag || this.requiresVal) {
			// Parameters.logger.info(this.tag + " requires id: " + this.requiresID
			// +
			// " requires tag: " + this.requiresTag + " requires value: " +
			// this.requiresValue);
			return true;
		}
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			if (((PatternEdge) it.next()).n2.requiresSomething()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Recursive method that returns true if any node in the subtree
	 * starting from this node stores the ID, the tag, the value or
	 * the content.
	 * 
	 * @return if the subtree starting from this node stores something
	 */
	public boolean deepStoresSomething() {
		if (this.storesID) {
			return true;
		}
		if (this.storesTag) {
			return true;
		}
		if (this.storesValue) {
			return true;
		}
		if (this.storesContent) {
			return true;
		}
		if (this.edges != null) {
			Iterator<PatternEdge> it = edges.iterator();
			while (it.hasNext()) {
				PatternNode child = ((PatternEdge) it.next()).n2;
				if (child.deepStoresSomething()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Recursive method that returns true if this node stores the ID,
	 * the tag, the value or the content.
	 * 
	 * @return if this node stores something
	 */
	public boolean nodeStoresSomething() {
		if(this.storesID || this.storesTag ||
				this.storesValue || this.storesContent)
			return true;	
		return false;
	}
	
	/**
	 * Returns what properties - consisting of ID, Val and Cont - are stored for a node
	 * 
	 * @return a boolean array of size 3 where the 0th position represents ID, 1st Val and 3rd Cont.
	 * These positions are set to <code>true</code> if they are stored for this node, otherwise <code>false</code>.
	 */
	public boolean[] getStoredProperties() {
		boolean[] storedProperties = new boolean[3];
		if(this.storesID) {
			storedProperties[0] = true;
		} else {
			storedProperties[0] = false;
		}
		if(this.storesValue) {
			storedProperties[1] = true;
		} else {
			storedProperties[1] = false;
		}
		if(this.storesContent) {
			storedProperties[2] = true;
		} else {
			storedProperties[2] = false;
		}
		return storedProperties;
	}
	 
	/**
	 * Returns the columns required for this node
	 * 
	 * @param storedProperties		the boolean array returned from getStoredProperties
	 * 								@see fr.inria.gemo.vip2p.xam.PatternNode#getStoredProperties()
	 * @return						the columns required for this node
	 */
	public int[] getStoredColumns(boolean[] storedProperties) {
		ArrayList<Integer> columns = new ArrayList<Integer>();
		
		for(int i = 0; i<3; i++) {
			if(storedProperties[i] == true) {
				columns.add(i);				
			}
		}
		
		int[] columnsArray = new int[columns.size()];
		for(int j = 0; j<columns.size(); j++) {
			columnsArray[j] = columns.get(j); 
		}
		return columnsArray;
	}

	public PatternNode getRoot(PatternNode n1) {
		if (n1.parentEdge == null) {
			return n1;
		} else {
			return getRoot(n1.parentEdge.n1);
		}
	}
	
	/**
	 *  Returns the descendants of this node in BFS order
	 *  
	 *  @return all the descendant of the node in BFS order
	 *  
	 * */
	public ArrayList<PatternNode> getBFSOrderedDescendants(){
		ArrayList<PatternNode> nodes = new ArrayList<PatternNode>();
		
		//BFS uses Queue data structure
		Queue<PatternNode> q=new LinkedList<PatternNode>();
		q.add(this);
		nodes.add(this);
				
		while(!q.isEmpty()){
			PatternNode n=q.remove();
			
			for(PatternNode child: n.getChildrenList()){
				nodes.add(child);
				q.add(child);
			}
		}

		return nodes;
	}
	
	
	protected List<Boolean> getColumnsStoringValue()
	{
		List<Boolean> list = new ArrayList<Boolean>();

		if(this.storesID)
			list.add(false);
		if(this.storesTag)
			list.add(false);
		if(this.storesValue)
			list.add(true);
		if(this.storesContent)
			list.add(false);
			
		return list;
	}
	
	protected List<Boolean> getColumnsBelongingToAttribute()
	{
		List<Boolean> list = new ArrayList<Boolean>();

		if(this.isAttribute) {
			if(this.storesID)
				list.add(true);
			if(this.storesTag)
				list.add(true);
			if(this.storesValue)
				list.add(true);
			if(this.storesContent)
				list.add(true);
		}
		else {
			if(this.storesID)
				list.add(false);
			if(this.storesTag)
				list.add(false);
			if(this.storesValue)
				list.add(false);
			if(this.storesContent)
				list.add(false);
		}
			
		return list;
	}
	
	
	public LinkedList<PatternNode> getNodes() {
		if (this.edges == null) {
			return null;
		}
		if (this.edges.size() == 0) {
			return null;
		}
		LinkedList<PatternNode> nodes = new LinkedList<PatternNode>();
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			nodes = ((PatternEdge) it.next()).n2.getNodesRec(nodes);
		}
		return nodes;
	}
	
	private LinkedList<PatternNode> getNodesRec(LinkedList<PatternNode> nodes) {
		nodes.add(this);
		if (this.edges != null) {
			Iterator<PatternEdge> it = edges.iterator();
			while (it.hasNext()) {
				nodes = ((PatternEdge) it.next()).n2.getNodesRec(nodes);
			}
		}
		return nodes;
	}
	
	/**
	 * Adds an edge connecting this node to a child node. It also marks the
	 * child node as being existential, if this is the case.
	 * 
	 * @param child
	 *            The child node.
	 * @param parent
	 *            Whether or not the edge is a parent-child edge (otherwise, it
	 *            is ancestor-descendant).
	 * @param nested
	 * 
	 */
	public void addEdge(PatternNode child, boolean parent, boolean nested, boolean optional) {
		PatternEdge e = new PatternEdge(this, child, parent, nested, optional);

		this.edges.add(e);
		child.parentEdge = e;
	}
	
	/**
	 * 
	 * @param pe
	 */
	public void addEdge(PatternEdge pe) {
		if(this.edges == null)
			this.edges = new ArrayList<PatternEdge>();
		this.edges.add(pe);
		pe.n2.parentEdge = pe;
	}
	
	public void removeEdge(PatternEdge pe) {
		edges.remove(pe);
	}
	
	/**
	 * 
	 */
	public void cleanEdges() {
		this.edges = new ArrayList<PatternEdge>();
	}
	
	/**
	 * This method returns a list of the children on the specific xam node
	 * 
	 * @return List of children.
	 */
	public ArrayList<PatternNode> getChildrenList() {
		ArrayList<PatternNode> children = new ArrayList<PatternNode>();
		for(PatternEdge pe: edges) {
			PatternNode n2 = pe.n2;
			children.add(n2);
		}
		return children;
	}
	
	/* Getters/setters */
	/* Node related getters/setters */
	/**
	 * If true, this node represents an attribute, and though, only attributes match.
	 * Otherwise, this node represents an element, and though, only elements match.
	 * 
	 * @return the isAttribute
	 */
	public boolean isAttribute() {
		return this.isAttribute;
	}
	
	/**
	 * If set to true, this node represents an attribute, and though, only attributes match.
	 * Otherwise, this node represents an element, and though, only elements match.
	 * 
	 * @param isAttribute the isAttribute to set
	 */
	public void setAttribute(boolean isAttribute) {
		this.isAttribute = isAttribute;
	}
	
	/**
	 * If not null, this node (either an attribute or an element) is assigned to one or more variables in the query. 
	 * Otherwise, this node (either an attribute or an element) is not assigned to any variable.
	 * A String array is provided rather than an iterator to prevent deletion.
	 */
/*	public String[] getMatchingVariables() {
		if(matchingVariables == null)
			//matchingVariables = new HashSet<String>();
			matchingVariables = new ArrayList<Variable>();
		return this.matchingVariables.toArray(new String[0]);	//new String[0] indicates the type of the returned array
	}*/
	
	public ArrayList<Variable> getMatchingVariables() {
		return this.matchingVariables;
	}

	//returns the names of those variables storing value (if any)
	public ArrayList<String> getMatchingVariablesStoringValue() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Variable var : matchingVariables) {
			if(var.dataType == Variable.VariableDataType.Value)
				list.add(var.name);
		}
		
		return list;
	}
	
	//returns the names of those variables storing content (if any)
	public ArrayList<String> getMatchingVariablesStoringContent() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Variable var : matchingVariables) {
			if(var.dataType == Variable.VariableDataType.Content)
				list.add(var.name);
		}
		
		return list;			
	}
	
	public boolean checkAnyMatchingVariableStoresValue()	{
		for(Variable var: matchingVariables) {
			if(var.dataType == Variable.VariableDataType.Value)
				return true;
		}
		return false;		
	}
	
	public boolean checkAnyMatchingVariableStoresContent() {
		for(Variable var: matchingVariables) {
			if(var.dataType == Variable.VariableDataType.Content)
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of matching variables.
     */
/*	public int getMatchingVariablesSize() {
		if(matchingVariables == null)
			matchingVariables = new HashSet<String>();
		return matchingVariables.size();
	}*/
	public int getMatchingVariablesSize() {
		return matchingVariables.size();
	}

	/**
	 * Marks this node as assigned to those variables indicated by {@link #variables}.
	 */
/*	public void addMatchingVariables(String... variables) {
		if(matchingVariables == null)
			matchingVariables = new HashSet<String>();
		for(String variable : variables)
			matchingVariables.add(variable);
	}*/
	public void addMatchingVariables(Variable... variables) {
		for(Variable var : variables)
			matchingVariables.add(var);
	}

	/**
	 * Removes all matching variables from this node.
     */
/*	public void clearMatchingVariables() {
		if(matchingVariables == null)
			matchingVariables = new HashSet<String>();
		else
			matchingVariables.clear();
	}*/
	
	public void clearMatchingVariables() {
		matchingVariables.clear();
	}
	

	/**
	 * Removes the matching variable indicated by {@link #variables} (if present).
	 */
/*	public void removeMatchingVariables(String... variables) {
		if(matchingVariables == null)
			matchingVariables = new HashSet<String>();
		else {
			for(String variable : variables)
				matchingVariables.remove(variable);
		}
	}*/
	/*public void removeMatchingVariables(Variable... variables) {
		for(Variable var : variables)
			matchingVariables.remove(var);
	}*/
	
	public void removeMatchingVariables(String... variableNames) {
		for(int i = matchingVariables.size()-1; i >= 0; i--) {
			for(int j = 0; j < variableNames.length; j++) {
				if(matchingVariables.get(i).name.compareTo(variableNames[j]) == 0)
					matchingVariables.remove(i);
			}
		}
	}
	
	public Variable getMatchingVariable(String variableName) {
		for(int i = 0; i < matchingVariables.size(); i++) {
			if(matchingVariables.get(i).name.compareTo(variableName) == 0)
				return matchingVariables.get(i);
		}
		return null;
	}
		
	/**
	 * If the xam is built from a XAM, this is the hash code of the XAM node
	 * from which it originated.
	 * 
	 * @return the {@link #nodeCode}
	 */
	public int getNodeCode() {
		return nodeCode;
	}

	/**
	 * Set the hash code of the XAM node.
	 * 
	 * @param nodeCode the nodeCode to set
	 */
	public void setNodeCode(int nodeCode) {
		this.nodeCode = nodeCode;
	}

	/* ID related getters/setters */
	/**
	 * If true, the ID of this element is needed. When {@link #storesID} is set to
	 * true, needs ID is always set to true.
	 * 
	 * @return the storesID
	 */
	public boolean storesID() {
		return this.storesID;
	}
	
	/**
	 * This method sets the variable {@link #storesID} to the given value.
	 * 
	 * @param storesID the storesID to set
	 */
	public void setStoresID(boolean storesID) {
		this.storesID = storesID;
	}
	
	/**
	 * If true, the ID of this element is (R)equired,must be known in order to access
	 * the data stored in the xam.
	 * @return the requiresID;
	 */
	public boolean requiresID(){
		return this.requiresID;
	}
	
	/**
	 * This method sets the variable {@link #requiresID} to the given value.
	 * 
	 * @param requireID the requireID to set
	 */
	public void setRequiresID(boolean requiresID){
		this.requiresID = requiresID;
	}
	
	/**
	 * If true, "ID i" was specified for this node in the XAM file, so the identity ID for
	 * this node should be stored. 
	 * 
	 * @return the identityID
	 */
	public boolean isIdentityIDType() {
		return this.identityID;
	}
	
	/**
	 * If true, "ID o" was specified for this node in the XAM file, so the order preserving ID for
	 * this node should be stored.
	 * 
	 * @return the orderID
	 */
	public boolean isOrderIDType() {
		return this.orderID;
	}
	
	/**
	 * If true, "ID s" was specified for this node in the XAM file, so the structural ID for
	 * this node should be stored. 
	 * 
	 * @return the structID
	 */
	public boolean isStructIDType() {
		return this.structID;
	}
	
	/**
	 * If true, "ID u" was specified for this node in the XAM file, so the update ID for
	 * this node should be stored. 
	 * 
	 * @return the updateID
	 */
	public boolean isUpdateIDType() {
		return this.updateID;
	}
	
	/**
	 * This method sets which type of ID will be stored for this node.
	 * 
	 * @param identityID to set
	 * @param orderID to set
	 * @param structID to set
	 * @param updateID to set
	 */
	public void setIDType(boolean identityID, boolean orderID, boolean structID, boolean updateID) {
		this.identityID = identityID;
		this.orderID = orderID;
		this.structID = structID;
		this.updateID = updateID;
	}
	
	/* Tag related getters/setters */
	/**
	 * If true, the tag of this element is stored.
	 * 
	 * @return the storesTag
	 */
	public boolean storesTag() {
		return this.storesTag;
	}
	
	/**
	 * If it is set to true, the tag of this element is stored. 
	 * 
	 * @param storesTag the storesTag to set
	 */
	public void setStoresTag(boolean storesTag) {
		this.storesTag = storesTag;
	}
	
	/**
	 * If true, the Tag of this element is (R)equired,must be known in order to access
	 * the data stored in the xam.
	 * @return the requiresID;
	 */
	public boolean requiresTag(){
		return this.requiresTag;
	}
	
	/**
	 * This method sets the variable {@link #requiresTag} to the given value.
	 * 
	 * @param requireTag the requireTag to set
	 */
	public void setRequiresTag(boolean requiresTag){
		this.requiresTag = requiresTag;
	}
	
	/**
	 * If true, there is a selection on tag.
	 * 
	 * @return the selectOnTag
	 */
	public boolean selectsTag() {
		return this.selectOnTag;
	}
	
	/**
	 * If it is set to true, there is a selection on the namespace and
	 * tag specified.
	 * 
	 * @param selectOnTag
	 * @param namespace
	 * @param tag
	 */
	public void setSelectOnTag(boolean selectOnTag, String namespace, String tag) {
		this.selectOnTag = selectOnTag;
		if (selectOnTag) {
			this.namespace = namespace;
			this.tag = tag;
		} else {
			this.namespace = "";
			this.tag = "*";
		}
	}
	
	/**
	 * Returns the namespace stored for this node.
	 * 
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}
	
	/**
	 * Sets the {@link #namespace} for this node.
	 * 
	 * @param namespace the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
		
	}
	
	/**
	 * Returns the tag stored for this node.
	 * 
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Sets the {@link #tag} for this node.
	 * 
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/* Value related getters/setters */
	/**
	 * If true, the value of this element is stored.
	 * 
	 * @return the storesValue
	 */
	public boolean storesValue() {
		return this.storesValue;
	}
	
	/**
	 * If set to true, the value of this element is stored.
	 * 
	 * @param storesValue the storesValue to set
	 */
	public void setStoresValue(boolean storesValue) {
		this.storesValue = storesValue;
	}	
	
	/**
	 * If true, the value of this element is (R)equired,must be known in order to access
	 * the data stored in the xam.
	 * @return the requiresVal;
	 */
	public boolean requiresVal(){
		return this.requiresVal;
	}
	
	/**
	 * This method sets the variable {@link #requiresVal} to the given value.
	 * 
	 * @param requireID the requireID to set
	 */
	public void setRequiresVal(boolean requiresVal){
		this.requiresVal = requiresVal;
	}
	
	/**
	 * If true, there is a selection on value.
	 * 
	 * @return the selectOnValue
	 */
	public boolean selectsValue() {
		return this.selectOnValue;
	}
	
	public PredicateType getSelectOnValuePredicate() {
		return this.selectOnValuePredicate;
	}
	
	/**
	 * Returns the {@link #value} for this node.
	 * 
	 * @return the value
	 */
	public String getStringValue() {
		return this.stringValue;
	}
	
	public double getDoubleValue() {
		return this.doubleValue;
	}
	
	/**
	 * If it is set to true, there is a selection on the value specified.
	 * 
	 * @param selectOnValue
	 * @param value
	 */
	public void setSelectOnValue(boolean selectOnValue, PredicateType predType, String value) {
		this.selectOnValue = selectOnValue;
		if (selectOnValue) {
			this.selectOnValuePredicate = predType;
			this.stringValue = value;
			this.doubleValue = -1;
		}
	}
	
	/**
	 * If it is set to true, there is a selection on the value specified.
	 * 
	 * @param selectOnValue
	 * @param value
	 */
	public void setSelectOnValue(boolean selectOnValue, PredicateType predType, double value) {
		this.selectOnValue = selectOnValue;
		if (selectOnValue) {
			this.selectOnValuePredicate = predType;
			this.stringValue = null;
			this.doubleValue = value;
		}
	}
	
	/* Content related getters/setters */
	/**
	 * If true, the content of this element is stored.
	 * 
	 * @return the storesContent
	 */
	public boolean storesContent() {
		return this.storesContent;
	}
	
	/**
	 * If set to true, the content of this element is stored.
	 * 
	 * @param storesContent the storesContent
	 */
	public void setStoresContent(boolean storesContent) {
		this.storesContent = storesContent;
	}

	/* Edges related getters/setters */
	/**
	 * Returns the edge that connects this node to its parent/ancestor.
	 * 
	 * @return the parentEdge
	 */
	public PatternEdge getParentEdge() {
		return this.parentEdge;
	}
	
	/**
	 * Returns the list of edges that connect this node to its children/descendants.
	 * 
	 * @return the edges
	 */
	public ArrayList<PatternEdge> getEdges() {
		return this.edges;
	}
	
	/**
	 * Sets the list of edges that connect this node to its children/descendants.
	 * 
	 * @param edges the edges
	 */
	public void setEdges(ArrayList<PatternEdge> edges) {
		this.edges = edges;
	}
	
	/**
	 * Returns true if this node is the top returning node of the tree pattern.
	 * Assumes that the node returns at least an ID, so we don't check for that.
	 * Also assumes that the node selects on the tag and does not return it.
	 * @return isTopReturningNode
	 */
	public boolean isTopReturningNode() {
		if (this.isTopReturningNode == 0){
			return false;
		}
		if (this.isTopReturningNode == 1){
			return true;
		}
		// otherwise, it is -1, and we need to look
		if (this.parentEdge == null){
			return true;
		}
		PatternNode n2 = this.parentEdge.n1;
		Stack<PatternNode> sn = new Stack<PatternNode>();
		sn.push(n2);
		while (!sn.empty()){
			PatternNode n3 = sn.pop();
			if (n3 == null){
				this.isTopReturningNode = 1;
				return true;
			}
			if (n3.storesID || n3.storesValue || (n3.storesContent)){
				this.isTopReturningNode = 0;
				return false;
			}
			if (n3.parentEdge != null){
				sn.push(n3.parentEdge.n1);
			}
		}
		this.isTopReturningNode = 1;
		return true;
	}
	
	/**
	 * The nested depth of this node.
	 * 
	 * @return the nestingDepth
	 */
	public int getNestingDepth() {
		return this.nestingDepth;
	}
	
	/**
	 * Builds the nested depth from this node.
	 */
	public void setNestingDepth() {
		Stack<PatternNode> st = new Stack<PatternNode>();
		st.push(this);
		int currentDepth = 0;
		Stack<Integer> st2 = new Stack<Integer>();
		st2.push(new Integer(currentDepth));

		while (!st.empty()) {
			PatternNode x = (PatternNode) st.pop();
			Integer y = (Integer) st2.pop();
			x.nestingDepth = y.intValue();

			Iterator<PatternEdge> it = x.edges.iterator();
			while (it.hasNext()) {
				PatternEdge e = it.next();
				st.push(e.n2);
				if (e.isNested()) {
					st2.push(new Integer(x.nestingDepth + 1));
				} else {
					st2.push(y);
				}
			}
		}
	}

	/**
	 * Returns true if this node is virtual.
	 * 
	 * @return the virtual
	 */
	public boolean isVirtual() {
		return virtual;
	}
	
	/* Displaying and printing methods */
	/**
	 * Recursive method that transforms the subtree starting from this node
	 * into its equivalent in a XAM file.
	 * 
	 * @param nodeBuffer buffer that will be filled with the definition
	 * of each node
	 * @param edgeBuffer buffer that will be filled with the definition
	 * of the edges (relationship between the nodes)
	 * @param parentNo the node code of the last parent
	 * @param availableNo the node code of the actual node
	 * @return the next available node code
	 */
	protected int toXAMString(StringBuffer nodeBuffer, StringBuffer edgeBuffer, int parentNo, int availableNo) {
		nodeBuffer.append("\n");
		int myCode = availableNo;
		int nextAvailable = availableNo + 1;
		// Parameters.logger.info("toXAMString on " + this.tag + " tag value: " +
		// this.tagValue
		// + " needs Tag: " + this.needsTag + " selects on tag: " +
		// this.selectOnTag);
		if (!this.isAttribute) {
			nodeBuffer.append("E: ");
		} else {
			nodeBuffer.append("A: ");
		}
		nodeBuffer.append(availableNo);
		nodeBuffer.append(" ");
		if (this.storesID) {
			nodeBuffer.append(" ID ");
			if (this.updateID) {
				nodeBuffer.append("u ");
			}
			if (this.structID
					|| (!this.orderID && !this.updateID && !this.structID)) {
				nodeBuffer.append("s ");
			}
			if (this.orderID || this.identityID) {
				nodeBuffer.append("i ");
			}
		}
		if (this.requiresID) {
			nodeBuffer.append(" R ");
		}
		if (this.selectOnTag) {
			nodeBuffer.append("[Tag=\"");
			if (!this.namespace.equals(""))
				nodeBuffer.append(startNamespaceDelimiter + this.namespace + endNamespaceDelimiter);
			nodeBuffer.append(this.tag + "\"] ");
		}
		if (this.storesTag) {
			nodeBuffer.append("Tag ");
		}
		if (this.requiresTag){
			nodeBuffer.append(" R ");
		}
		
		if (this.selectOnValue) {
			nodeBuffer.append("[Val=\"" + this.stringValue + "\"] ");
		}
		if (this.storesValue) {
			nodeBuffer.append("Val ");
		}
		if (this.requiresVal) {
			nodeBuffer.append(" R ");
		}
		if (this.storesContent) {
			nodeBuffer.append("Cont ");
		}
		Iterator<PatternEdge> itE = this.edges.iterator();
		while (itE.hasNext()) {
			PatternEdge pe = (PatternEdge) itE.next();
			PatternNode child = pe.n2;
			edgeBuffer.append(myCode + "," + nextAvailable + " ");
			if (pe.isParent()) {
				edgeBuffer.append("/");
			} else {
				edgeBuffer.append("//");
			}
			if (pe.isNested()) {
				edgeBuffer.append("n");
			}
			if (pe.isOptional()) {
				edgeBuffer.append("o");
			}
			else {
				edgeBuffer.append("j");
			}
			edgeBuffer.append("\n");
			nextAvailable = child.toXAMString(nodeBuffer, edgeBuffer, myCode, nextAvailable);
		}
		return nextAvailable;
	}
	
	/**
	 * This method informally display the tree starting from this node.
	 * Optional edges are marked with "?". Edges that lead to children
	 * are nested within parenthesis in the parent.
	 * We can specify several level of details while transforming the
	 * tree into a String.
	 * The levels of detail are defined in the {@link PrintingLevel}
	 * enumeration.
	 * 
	 * @param level of detail
	 * @return the tree representation
	 */
	public String treeToString(PrintingLevel level) {
		StringBuffer sb = new StringBuffer();
		this.recTreeToString(sb, level);
		return new String(sb);
	}

	private void recTreeToString(StringBuffer sb, PrintingLevel level) {
		switch (level) {
			case SIMPLIFY:
				sb.append(nodeString());
				break;
			case EXTENDED:
				sb.append(nodeExtendedString());
				break;
			default:
				logger.error("Printing level not defined properly: I don't know how to print patterns!");
		}
		if (edges.size() > 1) {
			sb.append("(");
		}
		Iterator<PatternEdge> it = edges.iterator();
		while (it.hasNext()) {
			PatternEdge e = (PatternEdge) it.next();
			if (e.isParent()) {
				sb.append("/");
			} else {
				sb.append("//");
			}
			e.n2.recTreeToString(sb, level);
			sb.append(" ");
		}
		if (edges.size() > 1) {
			sb.append(")");
		}

	}
	
	/**
	 * Prints the node simplify representation.
	 * 
	 * @return the simplify node representation
	 */
	public String toString() {
		return nodeString();
	}
	
	/*
	 * Node simplify representation.
	 * 
	 * @return the simplify node representation
	 */
	private String nodeString() {
		StringBuffer sb = new StringBuffer();
		if (virtual) {
			sb.append("~");
		}
		sb.append(this.isAttribute ? "@" : "");
		if (!this.namespace.equals(""))
			sb.append(this.namespace + ":");
		sb.append(this.tag);// + ":" + this.nodeCode);
		if (this.storesID){
			sb.append("*");
		}
		if (this.storesContent){
			sb.append("C");
		}
		if(this.storesValue){
			sb.append("V");
		}
		if (this.selectOnValue) {
			sb.append(" {=" + this.stringValue + "} ");
		}
		return new String(sb);
	}
	
	/*
	 * Extended node representation used for comparing uniquely
	 * two nodes in the method {@link #compareTo(PatternNode)}.
	 * 
	 * @return the extended full node representation
	 */
	private String nodeExtendedString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.virtual ? "~" : "");
		sb.append(this.isAttribute ? "@" : "");
		if (this.selectOnTag) {
			if (!this.namespace.equals(""))
				sb.append(this.namespace + ":");
			sb.append(this.tag);
			sb.append("(" + this.getNodeCode() + ")");
		}
		if (this.storesTag) {
			sb.append("_S");
		}
		if (this.requiresTag) {
			sb.append("R");
		}
		if (this.storesID) {
			sb.append("*");
		}
		if (this.requiresID) {
			sb.append("R");
		}
		if (this.selectOnValue)
			sb.append("#V{=" + this.stringValue + "}");
		if (this.storesValue) {
			sb.append("#V");
		}
		if (this.requiresVal) {
			sb.append("R");
		}
		if (this.storesContent)
			sb.append("#C");
		return new String(sb);
	}

	/**
 	 * The following method adds the code for the graphical representation of this
 	 * XAM node to the StringBuffer and then calls the recursiveDraw of its children.
	 * 
	 * @author Spyros ZOUPANOS
	 */
	public void drawTree(StringBuffer sb, String backgroundColor, String foregroundColor)
	{		
		sb.append(-this.hashCode() + " [ fontname=\"Lucida Grande\" fontcolor=\"white\" ] ; \n");

		for(int i =0; i<edges.size(); i++)
			edges.get(i).n2.recursiveDrawTree(sb, -this.hashCode(), backgroundColor, foregroundColor);
	}
	
	//TODO: draw matching variables
	private void recursiveDrawTree(StringBuffer sb, int parentNodeCode, String backgroundColor, String foregroundColor)
	{		
		sb.append(nodeCode + " [");
		
		if (storesContent)
			sb.append(" shape=\"trapezium\" color=\"" + foregroundColor + "\" style=\"filled\" fillcolor=\"" + backgroundColor + "\" ");
		else if (storesValue)
			sb.append(" color=\"" + foregroundColor + "\" style=\"filled\" fillcolor=\"" + backgroundColor + "\" ");
		else
			sb.append(" color=\"" + foregroundColor + "\" ");
		
		if (this.selectOnTag && this.tag.length() > 0) {
			sb.append(" label=\"");
			if (this.isAttribute)
				sb.append("@");
			if (this.namespace.equals(""))
					sb.append( this.tag );
			else if(this.namespace.length() > 15)
				sb.append( this.namespace.substring(0, 6) + "..." + this.namespace.substring(this.namespace.length()-6, this.namespace.length()) + ":" + this.tag );
			else
				sb.append( this.namespace + ":" + this.tag );
			if (this.storesID)
				sb.append(" &bull;");
			if (this.selectOnValue)
				sb.append("\\n[\\\"" + this.stringValue + "\\\"]");
			sb.append("\" ");
		}

		sb.append("] ; \n"); 
		
		// if we want to design a parent-child edge, we add it one time
		sb.append(parentNodeCode + " -- " + nodeCode + " ");
		if(parentEdge.isOptional())
			 sb.append("[style=\"dashed\"] "); 
		if(parentEdge.isNested())
			 sb.append("[headlabel=\"n\", labeldistance=3.5, labelangle=20] "); 
		sb.append("; \n");
		if(!parentEdge.isParent()) {
			// if we want to design an ancestor-descendant edge, we add it one more time
			sb.append(parentNodeCode + " -- " + nodeCode + " ");
			if(parentEdge.isOptional())
				 sb.append("[style=\"dashed\"] "); 
			sb.append("; \n");
		}
		
		for(int i =0; i<edges.size(); i++)
			edges.get(i).n2.recursiveDrawTree(sb, nodeCode, backgroundColor, foregroundColor);
	}
	
	/**
	 * Returns the name of the columns that this node stores.
	 * It is used at the ViP2P GUI.
	 * 
	 * @return the list of String
	 */
	protected ArrayList<String> getColumnsName()
	{
		ArrayList<String> list = new ArrayList<String>();

		if(this.storesID)
			list.add(nodeCode + ".ID");
		if(this.storesTag)
			list.add(nodeCode + ".Tag");
		if(this.storesValue)
			list.add(nodeCode + ".Val");
		if(this.storesContent)
			list.add(nodeCode + ".Cont");
			
		return list;
	}

}
