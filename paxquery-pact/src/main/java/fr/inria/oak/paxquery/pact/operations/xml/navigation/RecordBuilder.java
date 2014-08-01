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
package fr.inria.oak.paxquery.pact.operations.xml.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.types.Record;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryException;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePattern;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternEdge;
import fr.inria.oak.paxquery.common.xml.navigation.NavigationTreePatternNode;
import fr.inria.oak.paxquery.common.xml.nodeidentifier.NodeIDScheme;
import fr.inria.oak.paxquery.pact.datamodel.type.RecordList;
import fr.inria.oak.paxquery.pact.operations.RecordOperations;

/**
 * This class collects the tuple building functionality from the extractor, in
 * this case, nested record building.
 * 
 */
public class RecordBuilder {
	
	private static final Log logger = LogFactory.getLog(RecordBuilder.class);
	

	public HashMap<NavigationTreePatternNode, ExtractorMatchStack> stacksByNodes;

	public HashMap<NavigationTreePatternNode, NodeIDScheme> schemesByNodes;

	boolean thisIsFirstAttribute;

	public int tupleCount;

	NestedMetadata currentTupleNRSMD;
	NestedMetadata nestedKeyNRSMD;
	NestedMetadata flatKeyNRSMD;

	/**
	 * for the case when the data is unordered and we need to make up integer tuple keys
	 */
	NestedMetadata integerKeyRSMD;
	
	
	/**
	 * The database must have been opened and functional by now.
	 * 
	 * @param db
	 * @throws ULoadException
	 */
	public RecordBuilder() {
		this.thisIsFirstAttribute = true;
		this.tupleCount = 0;
		this.currentTupleNRSMD = null;
		this.nestedKeyNRSMD = null;
		this.flatKeyNRSMD = null;
		
		int colNo = 1;
		MetadataTypes[] types = new MetadataTypes[1];
		types[0] = MetadataTypes.STRING_TYPE;
		try{
			this.integerKeyRSMD = new NestedMetadata(colNo, types);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setStacksByNodes(HashMap<NavigationTreePatternNode, ExtractorMatchStack> hm) {
		this.stacksByNodes = hm;
	}

	public void setSchemesByNodes(HashMap<NavigationTreePatternNode, NodeIDScheme> hm) {
		this.schemesByNodes = hm;
	}
	
	public void produceTuples(ExtractorMatch em, NavigationTreePatternNode pn,
			List<Record> v, NavigationTreePattern p, int i) {
		logger.error("Method produceTuples for multiple documents not implemented yet!");
	}
	
	public final void produceTuples(ExtractorMatch em, NavigationTreePatternNode pn, List<Record> v, NavigationTreePattern x) {
		this.thisIsFirstAttribute = true;

		try {
			List<Record> v2 = build(em, pn);

			Iterator<Record> it = v2.iterator();
			while (it.hasNext()) {
				Record tuple = it.next();
				this.tupleCount++;
				v.add(tuple);
			}
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}

	/**
	 * Returns a vector of NTuples corresponding to the matches of em for this
	 * pattern node.
	 * 
	 * @param em
	 * @param pn
	 * @return
	 */
	private final List<Record> build(ExtractorMatch em, NavigationTreePatternNode pn) throws PAXQueryException, PAXQueryExecutionException {
		List<Record> res = new ArrayList<Record>();
		Record rootTuple = new Record();

		addAtomicAttributes(rootTuple, em, pn);

		if (pn.getEdges() == null) {
			res.add(rootTuple);

			if(logger.isDebugEnabled())
				logger.debug("End of build 1");
			
			return res;
		}
		if (pn.getEdges().size() == 0) {
			res.add(rootTuple);

			if(logger.isDebugEnabled())
				logger.debug("End of build 2");
			
			return res;
		}
		// there are children

		int iChild = -1;

		List<List<Record>>[] vChildren = new ArrayList[pn.getEdges().size()];

		boolean[] contributing = new boolean[pn.getEdges().size()];
		int childrenContributing = 0;

		boolean[] nesteds = new boolean[pn.getEdges().size()];
		for (int i = 0; i < pn.getEdges().size(); i++) {
			vChildren[i] = new ArrayList();
			nesteds[i] = false;
			contributing[i] = false;
		}

		
		Iterator<NavigationTreePatternEdge> it = pn.getEdges().iterator();
		while (it.hasNext() ) {
			iChild++;
			NavigationTreePatternEdge pe = it.next();

			NavigationTreePatternNode child = pe.n2;

			if (pe.isNested()) {
				nesteds[iChild] = true;
			}


			if(logger.isDebugEnabled())
				logger.debug("From "  + pn.getTag() + " going to child " + child.getTag());
			
			//If no child stores anything...
			if (!child.deepStoresSomething())
				continue;

			ExtractorMatchStack sChild = this.stacksByNodes.get(child);

			List<ExtractorMatch> childMatches = em.childrenByStack.get(sChild);

			if (childMatches != null) {
				
				if(logger.isDebugEnabled())
					logger.info("In " + pn.getTag() + " there are " + childMatches.size() + 
						" matches for " + child.getTag());

				Iterator<ExtractorMatch> thisChildMatches = childMatches.iterator();
				while (thisChildMatches.hasNext()) {
					ExtractorMatch emChild = thisChildMatches.next();

					if (emChild.erased)
					    continue;
				       
					// vector of tuples resulting from this match for this child:
					List<Record> vAux = build(emChild, child);
					
					if(logger.isDebugEnabled())
						logger.debug(vAux.size() + " tuples from this match of " + child.getTag());

					// collect this vector in vChildren[i]
					vChildren[iChild].add(vAux);
					
					if(logger.isDebugEnabled())
						logger.debug("In " + pn.getTag() + " added for " + iChild + 
								"-th child node a vector of " + vAux.size());
				}
			} else {
				if(logger.isDebugEnabled())
					logger.debug("In " + pn.getTag()  + " there are no matches for " + child.getTag());
				
				// no matches for this child
				List<Record> vChild = new ArrayList<Record>();
				NestedMetadata childRSMD = NestedMetadataUtils.getNRSMD(child, new HashMap());
				if (pe.isNested()) {
					
					if (pe.isOptional()) {
						Record nullPactRecord = RecordOperations.createNullRecord(childRSMD);
						vChild.add(nullPactRecord);
					}
				} else {
					if (pe.isOptional()){
						Record tChild = RecordOperations.createNullRecord(childRSMD);
						vChild.add(tChild);
					}
				}
				// In any case, collect the contribution of this child
				vChildren[iChild].add(vChild);
				
				if(logger.isDebugEnabled())
					logger.debug("In " + pn.getTag() + " for child " + child.getTag() + " got one vector");
			}
			if (vChildren[iChild].size() > 0) {
				if(logger.isDebugEnabled())
					logger.debug("Child number " + iChild + " contributes");
				
				contributing[iChild] = true;
				childrenContributing++;
			}
		}
		if (childrenContributing > 0) {
			res = newCartProd(rootTuple, vChildren, nesteds, contributing, nesteds.length);
		} else {
			res.add(rootTuple);
		}

		if(logger.isDebugEnabled())
			logger.debug("End of build 3");
		
		return res;
	}

	private List<Record> newCartProd(Record rootTuple, List<List<Record>>[] vChildren,
		boolean[] nesteds, boolean[] contributing, int n) throws PAXQueryExecutionException {

		List<Record> v = new ArrayList<Record>();
		if (n == 0) {
			v.add(rootTuple);
			return v;
		}
		
		List<Record> allButLast = newCartProd(rootTuple, vChildren, nesteds, contributing, n - 1);

		if (!contributing[n - 1]) {
			return allButLast;
		}

		if (nesteds[n - 1]) {
			Iterator<Record> iPrevious = allButLast.iterator();

			while (iPrevious.hasNext()) {
				Record nt = iPrevious.next();

				RecordList nestedField = new RecordList();
				Iterator<List<Record>> itChildren = vChildren[n - 1].iterator();
				while (itChildren.hasNext()) {
					List<Record> v2 = itChildren.next();
					Iterator<Record> it2 = v2.iterator();
					while (it2.hasNext()) {
						Record nt2 = it2.next();
						nestedField.add(nt2);
					}
				}
				// continue, fix NRSMD nt.addNestedField(nestedField);

				//System.out.println("Resulting enriched tuple: ");
				Record newTuple;
				if(nt.getNumFields() != 0)
					newTuple = nt.createCopy();
				else
					newTuple = nt;
				newTuple.addField(nestedField);
				v.add(newTuple);
			}
		} else {
			Iterator<Record> iPrevious = allButLast.iterator();
			while (iPrevious.hasNext()) {
				Record tPrevious = iPrevious.next();

				Iterator<List<Record>> itChildren = vChildren[n - 1].iterator();
				while (itChildren.hasNext()) {
					List<Record> v2 = itChildren.next();

					Iterator<Record> it2 = v2.iterator();

					while (it2.hasNext()) {
						Record nt2 = it2.next();
						
						if(tPrevious.getNumFields() > 0) {
							Record appendedTuple = tPrevious.createCopy();
							RecordOperations.concatenate(appendedTuple,nt2);
							v.add(appendedTuple);
						}
						else
							v.add(nt2);
					}
				}
			}
		}

		return v;
	}

	private final List<Record> backtrack(ExtractorMatch em, NavigationTreePatternNode pn)
		throws PAXQueryException, PAXQueryExecutionException {
		return build(em, pn);
	}

	private final List<Record> simpleCartProd(List<Record> v1, List<Record> v2) {
		if (v1 == null) {
			return v2;
		}
		if (v2 == null) {
			return v1;
		}
		List<Record> v = new ArrayList<Record>();
		Iterator<Record> it1 = v1.iterator();
		while (it1.hasNext()) {
			Record s1 = it1.next();
			Iterator<Record> it2 = v2.iterator();
			while (it2.hasNext()) {
				Record s2 = it2.next();
				v.add(s1);
				v.add(s2);
			}
		}
		return v;
	}

	/**
	 * This method builds a vector with all atomic attributes corresponding to
	 * a match em for a pattern node pn. This can mean one or more of the
	 * following: ID, Tag, Value, and Content. These attributes are by
	 * definition siblings at the same tuple level. They are not nested.
	 * 
	 * @param em
	 *            The match which contains the data.
	 * @param pn
	 *            The pattern node which gives the information that we should
	 *            extract.
	 * @return
	 */
	private final List<Record> addAtomicAttributes(
		Record tuple,
		ExtractorMatch em,
		NavigationTreePatternNode pn) {
		
		List<Record> v = new ArrayList<Record>();
		
		int iID = 0;
		int iString = 0;

		if (pn.storesID()) {
			if (em != null) {
				tuple.addField(new StringValue(em.getID().toString()));
				iID++;
			} else {
				if (pn.isOrderIDType()) {
					tuple.addField(new StringValue("\0"));
					iID++;
				} else {
					if (pn.isUpdateIDType()) {
						tuple.addField(new StringValue("\0"));
						iID++;
					} else {
						if (pn.isStructIDType()) {
							tuple.addField(new StringValue("\0"));
							iID++;
						}
					}
				}
			}
		}

		if (pn.storesTag()) {
			if (em != null) {
				tuple.addField(new StringValue(em.getTag()));
			} else {
				tuple.addField(new StringValue("\0"));
			}
			iString++;
		}

		if (pn.storesValue()) {
			if (em != null) {
				if (em.getVal() != null) {
					tuple.addField(new StringValue(em.getVal()));
				} else {
					tuple.addField(new StringValue("\0"));
				}
			} else {
				tuple.addField(new StringValue("\0"));
			}
			iString++;
		}
		if (pn.storesContent()) {
			if (em != null) {
				if (em.getContent() != null) {
					tuple.addField(new StringValue(em.getContent()));
				} else {
					tuple.addField(new StringValue("\0"));
				}
				iString++;
			}
		}
		if (iID > 0 || iString > 0) {
			v.add(tuple);
		}
		return v;
	}
}
