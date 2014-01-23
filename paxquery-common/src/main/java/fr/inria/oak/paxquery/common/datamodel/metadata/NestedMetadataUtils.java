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
package fr.inria.oak.paxquery.common.datamodel.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inria.oak.paxquery.common.exception.PAXQueryException;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.CompactDynamicDeweyScheme;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeIDScheme;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeIDSchemeAssignator;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.OrderedIntegerIDScheme;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.PrePostDepthIDScheme;
import fr.inria.oak.paxquery.common.xml.treepattern.core.PatternEdge;
import fr.inria.oak.paxquery.common.xml.treepattern.core.PatternNode;


/**
 * Utility methods to operate with nested metadata.
 *
 */
public class NestedMetadataUtils {
	
	private static final Log logger = LogFactory.getLog(NestedMetadataUtils.class);

	
	// needed when naming columns while traversing the XAM
	private static AtomicInteger nodeCount = new AtomicInteger(0);


	public static NestedMetadata unnestField(NestedMetadata nestedMetadata, int[] ancPath)
		throws PAXQueryExecutionException {
		return recUnnestField(nestedMetadata, ancPath, 0);
	}

	/**
	 * @param nrsmd
	 * @param ancPath
	 * @param i
	 * @return
	 */
	private static NestedMetadata recUnnestField(NestedMetadata nestedMetadata, int[] ancPath, int from) throws PAXQueryExecutionException {
		if (from == ancPath.length - 1) {
			NestedMetadata res = unnestField(nestedMetadata, ancPath[from]);
			return res;
		}
		
		NestedMetadata child = nestedMetadata.getNestedChild(ancPath[from]);
		NestedMetadata childResult = recUnnestField(child, ancPath, from + 1);
		NestedMetadata res = new NestedMetadata(nestedMetadata.types, nestedMetadata.nestedChildren);
		res.setNestedChild(ancPath[from], childResult);
		return res;
	}

	/**
	 * This method unnests the field fld. It replaces the field fld with all
	 * the fields of the nested tuples appearing there.
	 */
	public static NestedMetadata unnestField(NestedMetadata nestedMetadata, int fld) throws PAXQueryExecutionException {
		NestedMetadata childNRSMD = nestedMetadata.getNestedChild(fld);

		MetadataTypes[] auxTypes = new MetadataTypes[nestedMetadata.colNo - 1 + childNRSMD.colNo];
		NestedMetadata[] auxNRSMDs = new NestedMetadata[nestedMetadata.nestedNo - 1 + childNRSMD.nestedNo];
		
		int iAuxTypes = 0;
		int iAuxNRSMDs = 0;

		// copying the first fields, before the unnesting column
		for (int i = 0; i < fld; i++) {
			auxTypes[iAuxTypes] = nestedMetadata.types[i];
			iAuxTypes++;
			if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE) {
				auxNRSMDs[iAuxNRSMDs] = nestedMetadata.nestedChildren[iAuxNRSMDs];
				iAuxNRSMDs++;
			}
		}
		// now copy all fields from the freshly unnested child
		int jChildNestedNRSMD = 0;
		for (int j = 0; j < childNRSMD.colNo; j++) {
			auxTypes[iAuxTypes] = childNRSMD.types[j];
			iAuxTypes++;
			if (childNRSMD.types[j] == MetadataTypes.TUPLE_TYPE) {
				auxNRSMDs[iAuxNRSMDs] =
					childNRSMD.nestedChildren[jChildNestedNRSMD];
				iAuxNRSMDs++;//????
				jChildNestedNRSMD++;
			}
		}
		
		// now copy the remaining fields
		for (int i = fld + 1; i < nestedMetadata.colNo; i++) {
			auxTypes[iAuxTypes] = nestedMetadata.types[i];
			iAuxTypes++;
			if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE) {
				auxNRSMDs[iAuxNRSMDs] = nestedMetadata.nestedChildren[iAuxNRSMDs + 1];
				iAuxNRSMDs++;
			}
		}

		NestedMetadata auxNRSMD = new NestedMetadata(auxTypes, auxNRSMDs);
		//auxNRSMD.display();
		return auxNRSMD;

	}
	
	
	public static NestedMetadata emptyNRSMD() throws PAXQueryExecutionException {
		int colNo = 0;
		MetadataTypes[] types = new MetadataTypes[colNo];
		return new NestedMetadata(colNo, types);
	}

	//Statistic
	public static NestedMetadata addNestedField(NestedMetadata n1, NestedMetadata n2) throws PAXQueryExecutionException {
		int colNo = n1.colNo + 1;
		MetadataTypes[] types = new MetadataTypes[colNo];
		String[] colNames = new String[colNo];
		NestedMetadata[] nestedChildren = new NestedMetadata[n1.nestedChildren.length + 1];

		for (int i = 0; i < n1.colNo; i++) {
			types[i] = n1.types[i];
			colNames[i] = n1.colNames[i];
		}
		types[n1.colNo] = MetadataTypes.TUPLE_TYPE;
		colNames[n1.colNo] = n2.colNames[0];
		for (int i = 0; i < n1.nestedChildren.length; i++) {
			nestedChildren[i] = n1.nestedChildren[i];
		}
		nestedChildren[n1.nestedChildren.length] = n2;
		
		return new NestedMetadata(colNo, types, colNames, nestedChildren);
	}

	public static NestedMetadata appendNRSMD(NestedMetadata n1, NestedMetadata n2) throws PAXQueryExecutionException {
		int colNo = n1.colNo + n2.colNo;
		MetadataTypes[] types = new MetadataTypes[colNo];
		String[] colNames = new String[colNo];
		NestedMetadata[] nestedChildren =
			new NestedMetadata[n1.nestedChildren.length + n2.nestedChildren.length];

		for (int i = 0; i < n1.colNo; i++) {
			types[i] = n1.types[i];
			colNames[i] = n1.colNames[i];
		}
		for (int i = n1.colNo; i < colNo; i++) {
			types[i] = n2.types[i - n1.colNo];
			colNames[i] = n2.colNames[i - n1.colNo];
		}
		for (int i = 0; i < n1.nestedChildren.length; i++) {
			nestedChildren[i] = n1.nestedChildren[i];
		}
		for (int i = n1.nestedChildren.length;
			i < nestedChildren.length;
			i++) {
			nestedChildren[i] = n2.nestedChildren[i - n1.nestedChildren.length];
		}
		
		return new NestedMetadata(colNo, types, colNames, nestedChildren);
	}

	/**
	 * Computes the NRSMD associated strictly to the current node (does not
	 * look into nested children)/
	 * 
	 * @param node
	 * @return @throws
	 *         XMLStratosphereExecutionException
	 * @throws XMLStratosphereException
	 */
	public static NestedMetadata getStrictNRSMD(PatternNode node) throws PAXQueryExecutionException, PAXQueryException {
		//System.out.println("Making strict node RSMD for " + node.tag);
		ArrayList<MetadataTypes> nTypes = new ArrayList<MetadataTypes>();
		MetadataTypes[] types;
		NestedMetadata[] nestedChildren;

		if (node.storesID()) {
			MetadataTypes idType = IDTypes(NodeIDSchemeAssignator.getIDScheme(node));
			nTypes.add(idType);
			//System.out.println("In metadata for " + node.tag + " added
			// ID type: " +
			//		idType);

		}
		if (node.storesTag()) {
			MetadataTypes type = MetadataTypes.STRING_TYPE;
			nTypes.add(type);
		}
		if (node.storesValue()) {
			MetadataTypes type = MetadataTypes.STRING_TYPE;
			nTypes.add(type);
		}
		if (node.storesContent()) {
			MetadataTypes type = MetadataTypes.STRING_TYPE;
			nTypes.add(type);
		}
		types = new MetadataTypes[nTypes.size()];
		for (int i = 0; i < types.length; i++) {
			types[i] = nTypes.get(i);
		}
		nestedChildren = new NestedMetadata[0];
		
		//Not used in the estimation -> 
		return new NestedMetadata(types, nestedChildren);
	}

	/**
	 * Produces the NRSMD for a given PatternNode.
	 * 
	 * Adds in mappings: 
	 * 
	 * -- for every hash code of a XAM node from which the
	 * pattern node originated 
	 * 
	 *     -- a hash map containing: 
	 *            
	 *              -- on "ID", a ArrayList which is the address, inside the NRSMD, 
	 *                 of the node's ID, if the node's ID is stored 
	 * 
	 *              -- on "Tag", a ArrayList which is the address, inside the
	 *                 NRSMD, of the node's Tag, if the node's Tag is stored 
	 * 
	 *              -- on "Val", a ArrayList which is the address, inside the NRSMD, 
	 *                 of the node's Value if the node's Val is stored 
	 * 
	 *              -- on "Cont", a ArrayList which is the address, inside the NRSMD, 
	 *                 of the node's Cont, if the node's Cont is stored
	 * 
	 * @param node
	 * @return @throws
	 *         XMLStratosphereException
	 * @throws XMLStratosphereExecutionException
	 */

	public static NestedMetadata getNRSMD(PatternNode node, HashMap<Integer, HashMap<String, ArrayList<Integer>>> mappings)
		throws PAXQueryExecutionException {
		return getNRSMD(node, mappings, new ArrayList<Integer>(), false);
	}

	public static NestedMetadata getNRSMD(PatternNode node, HashMap<Integer, HashMap<String, ArrayList<Integer>>> mappings,
		ArrayList<Integer> address, boolean nestedInParent) throws PAXQueryExecutionException {
		
		ArrayList<MetadataTypes> nTypes = new ArrayList<MetadataTypes>();
		ArrayList nNested = new ArrayList();

		//System.out.println("\nGET ON "+ node.tag + " ADDRESS IS: ");
		//display(address);

		MetadataTypes[] types;
		NestedMetadata[] nestedChildren;

		// gather information for this and all children into types and
		// nestedChildren
		enrichNRSMD(node, nTypes, nNested, mappings, address, nestedInParent);

		types = new MetadataTypes[nTypes.size()];
		for (int i = 0; i < types.length; i++) {
			types[i] = nTypes.get(i);
		}
		nestedChildren = new NestedMetadata[nNested.size()];
		for (int i = 0; i < nestedChildren.length; i++) {
			nestedChildren[i] = (NestedMetadata) nNested.get(i);
		}
		
		//This is used only for the Leaf Operator, so the
		//statistic will be initialized in the class XamEstimation
		NestedMetadata aux = new NestedMetadata(types, nestedChildren);
		return aux;
	}

	/**
	 * Gather information about this and all children in two ArrayLists of types
	 * and nested children
	 * 
	 * @param node
	 * @param nTypes
	 * @param nNested
	 * @throws XMLStratosphereException
	 * @throws XMLStratosphereExecutionException
	 */
	private static void enrichNRSMD(PatternNode node, ArrayList<MetadataTypes> nTypes, ArrayList nNested, HashMap<Integer, HashMap<String, ArrayList<Integer>>> mappings,
		ArrayList<Integer> currentAddress, boolean nestedInParent) throws PAXQueryExecutionException {

		HashMap thisNodesAttributes = new HashMap();
		int thisNodesAttributeCount = 0;
		int thisNodesCount = 0;

		//System.out.println("\nENRICH ON " + node.tag + " node address is:
		// ");
		//display(currentAddress);

		if (nestedInParent) {
			thisNodesAttributeCount = 0;
			if (currentAddress.size() > 0) {
				thisNodesCount =
					((Integer) currentAddress.get(0)).intValue();
			}
		} else { // not nested
			if (currentAddress.size() > 0) {
				thisNodesCount =
					((Integer) currentAddress
						.get(currentAddress.size() - 1))
						.intValue();
				thisNodesAttributeCount = thisNodesCount;
				currentAddress.remove(currentAddress.size() - 1);
			}
		}

		//System.out.println("STARTING WITH ATTRCOUNT " +
		// thisNodesAttributeCount + " AND NODE COUNT "+
		//		thisNodesCount);

		if (mappings != null) {
			mappings.put(
				(new Integer(node.globalNodeCounter.get())),
				thisNodesAttributes);
		}
		if (nestedInParent) {
			thisNodesAttributes.put("Node", currentAddress);
		}
		boolean thisNodeHasContributed = false;

		if (node.storesID()) {
			MetadataTypes idType = IDTypes(NodeIDSchemeAssignator.getIDScheme(node));
			nTypes.add(idType);
			//System.out.println("In metadata for " + node.tag + " added
			// ID type: " + idTypes[j]);

			ArrayList v = new ArrayList();
			v.add(new Integer(thisNodesAttributeCount));
			thisNodesAttributes.put("ID", append(currentAddress, v));
			thisNodesAttributeCount++;
			if (!nestedInParent) {
				thisNodesCount++;
			}
			thisNodeHasContributed = true;
		}

		if (node.storesTag() && (!node.selectsTag())) {
			nTypes.add(MetadataTypes.STRING_TYPE);

			ArrayList v = new ArrayList();
			v.add(new Integer(thisNodesAttributeCount));
			thisNodesAttributes.put("Tag", append(currentAddress, v));
			thisNodesAttributeCount++;
			thisNodeHasContributed = true;
		}
		if (node.storesValue()) {
			nTypes.add(MetadataTypes.STRING_TYPE);

			ArrayList v = new ArrayList();
			v.add(new Integer(thisNodesAttributeCount));
			thisNodesAttributes.put("Val", append(currentAddress, v));
			thisNodesAttributeCount++;
			thisNodeHasContributed = true;
		}
		if (node.storesContent()) {
			nTypes.add(MetadataTypes.STRING_TYPE);

			ArrayList v = new ArrayList();
			v.add(new Integer(thisNodesAttributeCount));
			thisNodesAttributes.put("Cont", append(currentAddress, v));
			thisNodesAttributeCount++;
			thisNodeHasContributed = true;
		}

		int k = 0;
		Iterator it = node.getEdges().iterator();
		while (it.hasNext()) {
			PatternEdge pe = (PatternEdge) it.next();

			if (pe.isNested()) {

				ArrayList v = new ArrayList();
				v.add(new Integer(thisNodesAttributeCount + k));
				v = append(currentAddress, v);

				NestedMetadata aux = getNRSMD(pe.n2, mappings, v, true);
				nNested.add(aux);
				nTypes.add(MetadataTypes.TUPLE_TYPE);
				k++;
			} else {

				ArrayList v = new ArrayList();
				v.add(new Integer(thisNodesAttributeCount + k));
				v = append(currentAddress, v);

				enrichNRSMD(pe.n2, nTypes, nNested, mappings, v, false);
				k += flatAttributeNumber(pe.n2);
			}
		}
		//System.out.println("ENRICH ON " + node.tag + " OVER");
	}

	/**
	 * @param node
	 * @return
	 */
	private static int flatAttributeNumber(PatternNode node) {
		int n = 0;
		if (node.storesID()) {
			n++;
		}
		if (node.storesTag()) {
			n++;
		}
		if (node.storesValue()) {
			n++;
		}
		if (node.storesContent()) {
			n++;
		}
		Iterator it = node.getEdges().iterator();
		while (it.hasNext()) {
			PatternEdge e2 = (PatternEdge) it.next();
			if (!e2.isNested()) {
				int k = flatAttributeNumber(e2.n2);
				n += k;
			} else {
				n++;
			}
		}
		return n;
	}

	public static NestedMetadata makeRequiredNRSMD(PatternNode pn)
		throws PAXQueryExecutionException {
		nodeCount = new AtomicInteger(0);
		return requiredNRSMD(pn);
	}

	/**
	 * Constructs the NRSMD of only the required fields (and includes hierarchy
	 * needed in order to have those required fields).
	 * 
	 * @param pn:
	 *            NavigationTreePattern Node
	 * @return @throws
	 *         XMLStratosphereExecutionException
	 */
	private static NestedMetadata requiredNRSMD(PatternNode pn)
		throws PAXQueryExecutionException {
		//System.out.println("\nMaking required RSMD for " + pn.tag);

		NestedMetadata res = null;

		int iString = 0;
		int iID = 0;
		int iNested = 0;

		if (pn.requiresID()) {
			//System.out.println(pn.tag + " requires ID !");
			iID++;
		} else {
			//System.out.println(pn.tag + " does not require ID !");
		}
		if (pn.requiresTag()) {
			iString++;
		}
		if (pn.requiresVal()) {
			//System.out.println(pn.tag + " requires value !");
			iString++;
		} else {
			//System.out.println(pn.tag + " does not require value !");
		}

		boolean noMore = false;
		if (pn.getEdges() == null) {
			noMore = true;
		}
		if (pn.getEdges().size() == 0) {
			noMore = true;
		}

		int newColNo = iString + iID + iNested;
		MetadataTypes[] newTypes = new MetadataTypes[newColNo];
		String[] newNames = new String[newColNo];

		// Collect whatever we had so far.
		if (iString + iID + iNested > 0) {
			//System.out.println("Collecting fields !");
			int j = 0;
			if (iID > 0) {
				if (pn.isIdentityIDType()) {
					newTypes[j] = MetadataTypes.INTEGER_TYPE;
					newNames[j] = "id" + nodeCount;
				} else {
					if (pn.isOrderIDType()) {
						newTypes[j] = MetadataTypes.INTEGER_TYPE;
						newNames[j] = "id" + nodeCount;
					} else {
						if (pn.isStructIDType()) {
							newTypes[j] = MetadataTypes.STRUCTURAL_ID;
							newNames[j] = "id" + nodeCount;
						} else {
							if (pn.isUpdateIDType()) {
								newTypes[j] = MetadataTypes.UPDATE_ID;
								newNames[j] = "id" + nodeCount;
							}
						}
					}
				}
				//System.out.println(
				//	"Collected an ID type at "
				//		+ j
				//		+ " in required metadata of "
				//		+ pn.tag);
				j++;
			}
			if (iString > 0) {
				if (pn.requiresTag()) {
					newTypes[j] = MetadataTypes.STRING_TYPE;
					newNames[j] = "tag" + nodeCount;
					j++;
				}
				if (pn.requiresVal()) {
					newTypes[j] = MetadataTypes.STRING_TYPE;
					newNames[j] = "val" + nodeCount;
					//System.out.println(
					//	pn.tag + " requires value ! String field at " + j);
				} else {
					//System.out.println(
					//	pn.tag + " does not, after all, require value");
				}
			}
		}

		if (noMore) {
			NestedMetadata[] nestedChildren = new NestedMetadata[0];
			//System.out.println("No children, returning new RSMD");
			NestedMetadata vn = new NestedMetadata(newColNo, newTypes, newNames, nestedChildren);
			//vn.display();
			//System.out.println("End of required RSMD for " + pn.tag + "
			// 1\n");
			return vn;
		}
		
		res = new NestedMetadata(newColNo, newTypes, newNames, new NestedMetadata[iNested]);
		
		// this node has children; collect also what is required for them
		//System.out.println(
		//	"Prior to examining children of " + pn.tag + " we had:");
		//res.display();
		//System.out.println("Looking at children of " + pn.tag);
		Iterator ie = pn.getEdges().iterator();
		while (ie.hasNext()) {
			PatternEdge pe = (PatternEdge) ie.next();
			PatternNode n2 = pe.n2;
			if (n2.requiresSomething()) {
				//System.out.println(
				//	"Gathering required metadata from "
				//		+ pn.tag
				//		+ ". Looking "
				//		+ " at "
				//		+ n2.tag
				//		+ " which also needs some fields.");
				nodeCount.incrementAndGet();
				NestedMetadata childRequiredNRSMD = requiredNRSMD(n2);
				//System.out.println("Child metadata for " + n2.tag + " is:
				// ");
				if (childRequiredNRSMD == null) {
					//System.out.println("null");
				} else {
					//childRequiredNRSMD.display();
				}
				if (childRequiredNRSMD != null) {
					if (res == null) {
						// this is the first added thing
						if (pe.isNested()) {
							res =
								addNestedField(
									emptyNRSMD(),
									childRequiredNRSMD);
						} else {
							res = childRequiredNRSMD;
						}
						//System.out.println("Copied RSMD from child " +
						// n2.tag);
					} else {
						// there was something else
						if (pe.isNested()) {
							// nest this NRSMD in the parent one, and
							// collect it
							res = addNestedField(res, childRequiredNRSMD);
							iNested++;
							//	System.out.println(
							//		"Non-null required RSMD for nested child "
							//			+ n2.tag);
						} else {
							// just do a cartesian product metadata
							res = appendNRSMD(res, childRequiredNRSMD);
							//	System.out.println(
							//		"Null required RSMD for unnested child "
							//			+ n2.tag);
						}
					}
				} else { // if this child's required NRSMD is null, ignore
					// it
					// nothing
				}
			}
		}
		//System.out.println("Returning: ");
		//res.display();
		//System.out.println("End of requiredRSMD for " + pn.tag + " 2\n");
		return res;
	}
	
	
	private static final MetadataTypes IDTypes(NodeIDScheme sch) throws PAXQueryExecutionException {
		if (sch instanceof OrderedIntegerIDScheme) {
			return MetadataTypes.INTEGER_TYPE;
		}
		if (sch instanceof PrePostDepthIDScheme) {
			return MetadataTypes.STRUCTURAL_ID;
		}
		if (sch instanceof CompactDynamicDeweyScheme) {
			return MetadataTypes.UPDATE_ID;
		}
		throw new PAXQueryExecutionException("Could not find metadata type for ID scheme "
				+ sch.getClass().getName());
	}
	
	/**
	 * Performs a first-level (simple) projection
	 * 
	 * @param nrsmd
	 * @param keepColumns
	 * @return
	 */
	public static NestedMetadata makeProjectRSMD(NestedMetadata nestedMetadata, int[] keepColumns)
		throws PAXQueryExecutionException {
		MetadataTypes[] newTypes = new MetadataTypes[keepColumns.length];
		int iChildren = 0;
		NestedMetadata[] childrenPositions = new NestedMetadata[keepColumns.length];
		//System.out.println("\nProjecting from ");
		//nrsmd.display();
		//System.out.println(" on ");
		//for (int i = 0; i < keepColumns.length; i ++){
		//	System.out.println(keepColumns[i]);
		//}
		for (int i = 0; i < keepColumns.length; i++) {
			int kAux2 = keepColumns[i];
			MetadataTypes kAux = nestedMetadata.types[kAux2];
			newTypes[i] = kAux;
			
			if (newTypes[i] == MetadataTypes.TUPLE_TYPE) {
				childrenPositions[iChildren] =
					nestedMetadata.getNestedChild(keepColumns[i]);
				iChildren++;
			}
		}
		NestedMetadata[] children = new NestedMetadata[iChildren];
		for (int i = 0; i < children.length; i++) {
			children[i] = childrenPositions[i];
		}
		
		return new NestedMetadata(newTypes, children);
	}
	
	/**
	 * @param nrsmd
	 * @param ancPath
	 * @param nrsmd2
	 * @return
	 */
	public static NestedMetadata addNestedField(NestedMetadata n1, int[] ancPath, NestedMetadata n2) throws PAXQueryExecutionException {
		if (ancPath.length == 0)
			return appendNRSMD(n1, n2);

		// make a deep copy of n1
		// Should add the statistic information of n1
		NestedMetadata newN1 = new NestedMetadata(n1.types, n1.nestedChildren);

		// go modify in the first child
		NestedMetadata child = newN1.getNestedChild(ancPath[0]);
		int[] aux = new int[ancPath.length - 1];
		for (int i = 1; i < ancPath.length; i++) {
			aux[i - 1] = ancPath[i];
		}
		// put the transformed child in place
		NestedMetadata transformedChild = addNestedField(child, aux, n2);
		newN1.setNestedChild(ancPath[0], transformedChild);
		return newN1;
	}
	
	private static ArrayList append(ArrayList v1, ArrayList v2) {
		ArrayList v3 = new ArrayList();

		Iterator it1 = v1.iterator();
		while (it1.hasNext()) {
			v3.add(it1.next());
		}
		Iterator it2 = v2.iterator();
		while (it2.hasNext()) {
			v3.add(it2.next());
		}
		return v3;
	}


	private static final void display(ArrayList v) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < v.size(); i++) {
			sb.append((Integer) v.get(i));
			if (i < v.size() - 1) {
				sb.append(".");
			}
		}
		sb.append("]");
		System.out.println(sb);
	}

	/**
	 * @param nrsmd
	 * @param ArrayList
	 * @param ArrayList2
	 * @return
	 */
	public static NestedMetadata nestNRSMD(NestedMetadata nestedMetadata, int[] nestColumns) throws PAXQueryExecutionException {
		MetadataTypes[] newTypes = new MetadataTypes[nestedMetadata.colNo - nestColumns.length + 1];
		int nestedChildrenNo = 1;
		
		NestedMetadata nestedGroupNRSMD = makeProjectRSMD(nestedMetadata, nestColumns);
		logger.info(nestedMetadata.toString());
		logger.info(nestedGroupNRSMD.toString());

		
		// there is going to be at least the newly nested child
		for (int i = 0; i < nestedMetadata.colNo; i ++){
			if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE) {
				boolean found = false;
				for(int j=0; j<nestColumns.length; j++) {
			         if(nestColumns[j] == i) {
			        	 found = true;
			        	 break;
			         }
				}
				if (!found)
					nestedChildrenNo++;
			}
		}
		
		//System.out.println("There will be " + nestedChildrenNo + " nested children");
		NestedMetadata[] newChildren = new NestedMetadata[nestedChildrenNo];
		
		int iNewChildren = 0;
		int iNewTypes = 0;
		
		// g1 is the lowest index among the indices of the columns to be
		// nested.
		int g1 = Integer.MAX_VALUE;
		for (int i = 0; i < nestColumns.length; i ++){
			int k = nestColumns[i];
			if (g1 > k){
				g1 = k;
			}
		}
		
		for (int i = 0; i < nestedMetadata.colNo; i ++){
			if (i < g1){
				newTypes[iNewTypes] = nestedMetadata.types[i];
				iNewTypes ++;
				if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE){
					newChildren[iNewChildren] = nestedMetadata.getNestedChild(i);
					iNewChildren ++;
				}
			}
			if (i == g1){
				newTypes[iNewTypes] = MetadataTypes.TUPLE_TYPE;
				iNewTypes ++;
				newChildren[iNewChildren] = nestedGroupNRSMD;
				iNewChildren ++;
			}
			
			if (i > g1) {
				boolean found = false;
				for(int j=0; j<nestColumns.length; j++) {
			         if(nestColumns[j] == i) {
			        	 found = true;
			        	 break;
			         }
				}
				if (!found) {
					newTypes[iNewTypes] = nestedMetadata.types[i];
					iNewTypes ++;
					if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE){
						newChildren[iNewChildren] = nestedMetadata.getNestedChild(i);
						iNewChildren ++;
					}
				}
			}
		}
		return new NestedMetadata(newTypes, newChildren);
	}

	/**
	 * @param nrsmd
	 * @param is
	 * @param ArrayList
	 * @param ArrayList2
	 * @return
	 */
	public static NestedMetadata nestNRSMD(NestedMetadata nestedMetadata, int[] ancPath, int[] nestColumns) throws PAXQueryExecutionException {
		return recNestNRSMD(nestedMetadata, ancPath, nestColumns, 0);
	}

	/**
	 * @param nrsmd
	 * @param is
	 * @param groupByColumns
	 * @param groupedColumns
	 * @param i
	 * @return
	 */
	private static NestedMetadata recNestNRSMD(NestedMetadata nestedMetadata, int[] ancPath, int[] nestColumns, int from) throws PAXQueryExecutionException {
		if (from == ancPath.length){
			NestedMetadata aux = nestNRSMD(nestedMetadata, nestColumns);
			return aux;
		}

		NestedMetadata thisChild = nestedMetadata.getNestedChild(ancPath[from]);
		NestedMetadata thisChildNested = recNestNRSMD(thisChild, ancPath, nestColumns, from + 1);
		
		// must find the index in nrsmd's nested children of thisChild
		int thisChildIndex = -1;
		int iAux = 0;
		for (int i = 0; i < nestedMetadata.types.length; i ++){
			if (nestedMetadata.types[i] == MetadataTypes.TUPLE_TYPE){
				if (i == ancPath[from]){
					thisChildIndex = iAux;
					break;
				}
				iAux ++;
			}
		}
			
		MetadataTypes[] types = new MetadataTypes[nestedMetadata.types.length];
		for (int i = 0; i < nestedMetadata.types.length; i ++){
			types[i] = nestedMetadata.types[i];
		}
		NestedMetadata[] children = new NestedMetadata[nestedMetadata.nestedChildren.length];
		for (int i = 0; i < children.length; i ++){
			if (i == thisChildIndex){
				children[i] =  thisChildNested;
			}
			else{
				children[i] = nestedMetadata.nestedChildren[i];
			}
		}
		
		return new NestedMetadata(types, children);
	}

}
