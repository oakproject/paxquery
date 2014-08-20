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
package fr.inria.oak.paxquery.common.xml.construction;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * This class models the construction tree patterns.
 * 
 */
public class ConstructionTreePattern implements Serializable {
 	
	private Set<ConstructionTreePatternNode> nodes; //Tree pattern nodes
 
	private ConstructionTreePatternNode root; //Tree pattern root
	
	private Map<ConstructionTreePatternNode,ConstructionTreePatternEdge> parentEdges; //From child
	private ListMultimap<ConstructionTreePatternNode,ConstructionTreePatternEdge> childrenEdges; //From parent
	
	
	public ConstructionTreePattern(ConstructionTreePatternNode root) {
		this.root = root;
		this.root.setConstructionTreePattern(this);
		
		this.nodes = new HashSet<ConstructionTreePatternNode>();
		this.nodes.add(root);
		
		this.parentEdges = new HashMap<ConstructionTreePatternNode,ConstructionTreePatternEdge>();
		this.childrenEdges = ArrayListMultimap.create();
	}
	
	private ConstructionTreePattern() {
		this.nodes = new HashSet<ConstructionTreePatternNode>();
		
		this.parentEdges = new HashMap<ConstructionTreePatternNode,ConstructionTreePatternEdge>();
		this.childrenEdges = ArrayListMultimap.create();
	}
	
	/**
	 * Adds child node to the tree.
	 * 
	 * @param parent parent of the child to add
	 * @param child child node to add to the tree
	 */
	public void addChild(ConstructionTreePatternNode parent, ConstructionTreePatternNode child) {
		this.nodes.add(child);
		this.connect(parent, child);
	}
	
	private void connect(ConstructionTreePatternNode parent, ConstructionTreePatternNode child) {
		ConstructionTreePatternEdge edge = new ConstructionTreePatternEdge(parent, child);
		this.parentEdges.put(child, edge);
		this.childrenEdges.put(parent, edge);
	}
	
	/**
	 * Adds a subtree to the current tree, under a given parent node.
	 * 
	 * @param parent parent node of the new subtree
	 * @param root root node of the subtree to add under parent
	 */
	public void moveSubtree(ConstructionTreePatternNode parent, ConstructionTreePatternNode root) {
		//Pass to assign nodes to this tree
		Deque<ConstructionTreePatternNode> stack = new ArrayDeque<ConstructionTreePatternNode>();
		stack.push(null);
		ConstructionTreePattern rootCtp = root.getConstructionTreePattern();
		ConstructionTreePatternNode top = root;
		while(top != null) {
			//Add node to this tree and remove from old tree
			this.nodes.add(top);
			rootCtp.nodes.remove(top);
			
			//Connect parent and remove edge from old tree
			if(top == root) { //If we are at the root of the subtree
				this.parentEdges.put(top, new ConstructionTreePatternEdge(parent, root));
			}
			else {
				this.parentEdges.put(top, rootCtp.getParentEdges().get(top));
			}
			rootCtp.parentEdges.remove(top);
			
			//Connect children and remove edges from old tree
			List<ConstructionTreePatternEdge> children = rootCtp.childrenEdges.get(top);
			if(children != null) {
				for(ConstructionTreePatternEdge edge: children) {
					this.childrenEdges.put(top, edge);
					stack.push(edge.getChild());
				}
				rootCtp.childrenEdges.removeAll(top);
			}
			
			//New top
			top = stack.pop();
		}
	}
	
	/**
	 * Adds a subtree to the current tree, under a given parent node.
	 * The subtree is deep copied before being added.
	 * 
	 * @param parent parent node of the new subtree
	 * @param root root node of the subtree to add under parent
	 */
	public void addDeepCopySubtree(ConstructionTreePatternNode parent, ConstructionTreePatternNode root) {
		ConstructionTreePattern deepCopySubtree = deepCopySubtree(root);
		this.moveSubtree(parent, deepCopySubtree.getRoot());
	}
	
	/**
	 * Deep copy of a subtree.
	 * 
	 * @param root root node of the subtree to deep copy
	 */
	public static ConstructionTreePattern deepCopySubtree(ConstructionTreePatternNode root) {
		ConstructionTreePattern newCtp = new ConstructionTreePattern();
		
		Map<ConstructionTreePatternNode,ConstructionTreePatternNode> oldToNew = 
				new HashMap<ConstructionTreePatternNode,ConstructionTreePatternNode>();
		
		//Pass to assign nodes to this tree
		Deque<ConstructionTreePatternNode> stack = new ArrayDeque<ConstructionTreePatternNode>();
		ConstructionTreePattern rootCtp = root.getConstructionTreePattern();
		ConstructionTreePatternNode top = root;
		while(top != null) {
			ConstructionTreePatternNode topCopy = new ConstructionTreePatternNode(
					newCtp, top.getContentType(), top.getVarPath(), top.getValue(), top.isOptional());
			newCtp.nodes.add(topCopy);
			
			if(top == root) {
				newCtp.root = topCopy;
			}
			else {
				newCtp.connect(oldToNew.get(rootCtp.parentEdges.get(top).getParent()), topCopy);
			}
			
			oldToNew.put(top, topCopy);
			
			//Add children
			List<ConstructionTreePatternEdge> children = rootCtp.childrenEdges.get(top);
			if(children != null) {
				for(int i = children.size()-1; i >= 0; i--)
					stack.push(children.get(i).getChild());
			}
			
			//New top
			if(stack.isEmpty())
				top = null;
			else
				top = stack.pop();
		}
		
		return newCtp;
	}

	public Set<ConstructionTreePatternNode> getNodes() {
		return this.nodes;
	}

	public ConstructionTreePatternNode getRoot() {
		return this.root;
	}

	public Map<ConstructionTreePatternNode, ConstructionTreePatternEdge> getParentEdges() {
		return this.parentEdges;
	}

	public ListMultimap<ConstructionTreePatternNode, ConstructionTreePatternEdge> getChildrenEdges() {
		return this.childrenEdges;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringRecursive(root, sb);
		return sb.toString();
	}
	
	private void toStringRecursive(ConstructionTreePatternNode node, StringBuilder sb) {
		sb.append(node.toString());
		ArrayList<ConstructionTreePatternNode> children = node.getChildren();
		if(children.size() > 0) {
			sb.append(" ( ");				
			for(ConstructionTreePatternNode child : children) {
				toStringRecursive(child, sb);
				sb.append(" ");
			}
			sb.append(" ) ");				
		}		
	}

}
