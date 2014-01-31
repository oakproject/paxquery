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
package fr.inria.oak.paxquery.translation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.api.common.Plan;
import eu.stratosphere.api.common.operators.FileDataSink;
import eu.stratosphere.api.common.operators.FileDataSource;
import eu.stratosphere.api.common.operators.GenericDataSink;
import eu.stratosphere.api.common.operators.Operator;
import eu.stratosphere.api.java.record.operators.CoGroupOperator;
import eu.stratosphere.api.java.record.operators.CrossOperator;
import eu.stratosphere.api.java.record.operators.JoinOperator;
import eu.stratosphere.api.java.record.operators.MapOperator;
import eu.stratosphere.api.java.record.operators.ReduceOperator;
import eu.stratosphere.types.IntValue;
import eu.stratosphere.types.StringValue;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.binary.Join;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterJoin;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoinWithAggregation;
import fr.inria.oak.paxquery.algebra.operators.border.XMLConstruct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.DuplicateElimination;
import fr.inria.oak.paxquery.algebra.operators.unary.Flatten;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupByWithAggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.Navigation;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.algebra.optimizer.pushdown.PushdownUtility;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.predicates.DisjunctivePredicate;
import fr.inria.oak.paxquery.common.xml.treepattern.core.TreePatternUtils;
import fr.inria.oak.paxquery.pact.configuration.PACTOperatorsConfiguration;
import fr.inria.oak.paxquery.pact.datamodel.metadata.MetadataTypesMapping;
import fr.inria.oak.paxquery.pact.io.XmlNavInputFormat;
import fr.inria.oak.paxquery.pact.io.XmlOutputFormat;
import fr.inria.oak.paxquery.pact.operations.KeyFactoryOperations;
import fr.inria.oak.paxquery.pact.operators.binary.CartesianProductOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ConjEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ConjLNOEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ConjLNOEquiJoinWithAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ConjLOEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.DisjEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.DisjLNOEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.DisjLNOEquiJoinWithAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.binary.DisjLOEquiJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ThetaJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ThetaLNOJoinOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ThetaLNOJoinWithAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.binary.ThetaLOJoinOperator;
import fr.inria.oak.paxquery.pact.operators.unary.DuplicateEliminationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.FlattenOperator;
import fr.inria.oak.paxquery.pact.operators.unary.GroupByOperator;
import fr.inria.oak.paxquery.pact.operators.unary.GroupByWithAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.NavigationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.NestedAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.PostAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.PostLNOJoinOperator;
import fr.inria.oak.paxquery.pact.operators.unary.PostLNOJoinWithAggregationOperator;
import fr.inria.oak.paxquery.pact.operators.unary.PostLOJoinOperator;
import fr.inria.oak.paxquery.pact.operators.unary.PreJoinOperator;
import fr.inria.oak.paxquery.pact.operators.unary.ProjectionOperator;
import fr.inria.oak.paxquery.pact.operators.unary.SelectionOperator;

/**
 * Translation of a logical expression into a PACT plan.
 * 
 */
public class Logical2Pact {
	
	private static final Log logger = LogFactory.getLog(Logical2Pact.class);
	
	
	public static final Plan planTranslate(BaseLogicalOperator initialOp) {
//		LogicalOperator log = LogicalRewriting.push(initialOp);
		BaseLogicalOperator log = initialOp;

		logger.debug("After pushing: " + log.getName());
				
		GenericDataSink result;
		if (log instanceof XMLConstruct)
			result = planTranslate((XMLConstruct) log);
		else
			throw new PAXQueryExecutionException("The top operator must translate into a data sink!");
		
		Plan resultPactPlan = new Plan(result);
		return resultPactPlan;
	}
	
	private static final GenericDataSink planTranslate(XMLConstruct as) {
		//Generate plan
		Operator[] childPlan = translate(as.getChild());
		
		//Store translation in a list
		List<Operator> children = new ArrayList<Operator>();
		for(int i=0; i<childPlan.length; i++)
			children.add(childPlan[i]);
		
		//Create FileDataSink
		FileDataSink result = new FileDataSink(XmlOutputFormat.class, as.getOutputPath(), children, "Construct XML");
		XmlOutputFormat.configureRecordFormat(result)
			.setSignature(as.getNRSMD())
			.setApply(as.getApply());
//		result.setDegreeOfParallelism(1);
		
		return result;
	}

	private static final Operator[] translate(BaseLogicalOperator log) {
		Operator[] translation = null;

		if (log instanceof XMLScan)
			translation = translate((XMLScan) log);
		else if (log instanceof Selection)
			translation = translate((Selection) log);
		else if (log instanceof Projection)
			translation = translate((Projection) log);
		else if (log instanceof Navigation)
			translation = translate((Navigation) log);
		else if (log instanceof GroupBy)
			translation = translate((GroupBy) log);
		else if (log instanceof Flatten)
			translation = translate((Flatten) log);
		else if (log instanceof Aggregation)
			translation = translate((Aggregation) log);
		else if (log instanceof DuplicateElimination)
			translation = translate((DuplicateElimination) log);
		else if (log instanceof CartesianProduct)
			translation = translate((CartesianProduct) log);
		else if (log instanceof Join)
			translation = translate((Join) log);
		else if (log instanceof LeftOuterJoin)
			translation = translate((LeftOuterJoin) log);
		else if (log instanceof LeftOuterNestedJoin)
			translation = translate((LeftOuterNestedJoin) log);
		else
			throw new PAXQueryExecutionException("Translation not implemented for operator " + log.getName());
		
		return translation;
	}

	private static final Operator[] translate(XMLScan xp) {
		FileDataSource navigationExtraction = new FileDataSource(XmlNavInputFormat.class, xp.getPathDocuments(), "Parse XML");
		if(xp.getNavigationTreePattern() != null)
			XmlNavInputFormat.configureXmlNavInputFormat(navigationExtraction)
					.setNavigationTreePattern(xp.getNavigationTreePattern())
					.setAttachDocumentID(xp.isAttachDocumentID());
		else
			XmlNavInputFormat.configureXmlNavInputFormat(navigationExtraction)
					.setAttachDocumentID(xp.isAttachDocumentID());
		return new Operator[]{navigationExtraction};
	}
	
	private static final Operator[] translate(Selection sel) {
		Operator[] childPlan = translate(sel.getChild());

		// create MapOperator for selecting some records
		MapOperator selection = MapOperator.builder(SelectionOperator.class)
			.input(childPlan)
			.name("Select")
			.build();
				
		// selection configuration
		final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(sel.getNRSMD()));
		selection.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
		final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(sel.getPred()));
		selection.setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
				
		return new Operator[]{selection};
	}
	
	private static final Operator[] translate(Projection proj) {
		Operator[] childPlan = translate(proj.getChild());

		// create MapOperator for projecting a column
		MapOperator projection = MapOperator.builder(ProjectionOperator.class)
			.input(childPlan)
			.name("Proj")
			.build();
		
		// projection configuration
		final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(proj.getNRSMD()));
		projection.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
		final String encodedKeepColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(proj.columns));
		projection.setParameter(PACTOperatorsConfiguration.KEEP_COLUMNS_BINARY.toString(), encodedKeepColumns);
		
		return new Operator[]{projection};
	}
	
	private static final Operator[] translate(Navigation nav) {
		Operator[] childPlan = translate(nav.getChild());

		// create MapOperator for navigating in a column
		MapOperator navigation = MapOperator.builder(NavigationOperator.class)
			.input(childPlan)
			.name("TPNav")
			.build();
		
		// navigation configuration
		final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nav.getNRSMD()));
		navigation.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
		navigation.setParameter(PACTOperatorsConfiguration.NAVIGATION_COLUMN_INT.toString(), nav.pos);
		navigation.setParameter(PACTOperatorsConfiguration.NTP_STRING.toString(),
				TreePatternUtils.getParsableStringFromTreePattern(nav.navigationTreePattern));
		
		return new Operator[]{navigation};
	}
	
	private static final Operator[] translate(Flatten flat) {
		Operator[] childPlan = translate(flat.getChild());

		// create MapOperator to flatten tuples
		MapOperator flatten = MapOperator.builder(FlattenOperator.class)
				.input(childPlan)
				.name("Flatten")
				.build();

		// flatten configuration
		final String encodedFlattenNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(flat.getNRSMD()));
		flatten.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedFlattenNRSMD);
		final String encodedUnnestPath = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(flat.getUnnestPath()));
		flatten.setParameter(PACTOperatorsConfiguration.UNNEST_PATH_BINARY.toString(), encodedUnnestPath);

		return new Operator[]{flatten};
	}
	
	private static final Operator[] translate(GroupBy gb) {
		final boolean withAggregation = gb instanceof GroupByWithAggregation;
		
		Operator[] childPlan = translate(gb.getChild());

		// create ReduceOperator for grouping
		ReduceOperator.Builder groupByBuilder;
		if(withAggregation)
			groupByBuilder = ReduceOperator.builder(GroupByWithAggregationOperator.class)
			.input(childPlan)
			.name("GroupByAgg");
		else
			groupByBuilder = ReduceOperator.builder(GroupByOperator.class)
				.input(childPlan)
				.name("GroupBy");
		for(int column: gb.getGroupByColumns())
			KeyFactoryOperations.addKey(groupByBuilder, MetadataTypesMapping.getKeyClass(gb.getChild().getNRSMD().getType(column)), column);
		ReduceOperator groupBy = groupByBuilder.build();

		// groupBy configuration
		final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(gb.getNRSMD()));
		groupBy.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
		final String encodedGroupByColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize((gb.getGroupByColumns())));
		groupBy.setParameter(PACTOperatorsConfiguration.GROUP_BY_COLUMNS_BINARY.toString(), encodedGroupByColumns);
		final String encodedNestColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize((gb.getNestColumns())));
		groupBy.setParameter(PACTOperatorsConfiguration.NEST_COLUMNS_BINARY.toString(), encodedNestColumns);
		if(withAggregation) {
			GroupByWithAggregation gba = (GroupByWithAggregation) gb;
			
			groupBy.setParameter(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), gba.getAggregationColumn());

			final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(gba.getAggregationType()));
			groupBy.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
			
			groupBy.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), gba.isExcludeNestedField());
		}
			
		return new Operator[]{groupBy};
	}
	
	private static final Operator[] translate(DuplicateElimination dupElim) {
		Operator[] childPlan = translate(dupElim.getChild());

		// create ReduceOperator for removing records
		ReduceOperator.Builder duplicateEliminationBuilder = ReduceOperator.builder(DuplicateEliminationOperator.class)
			.input(childPlan)
			.name("DupElim");
		for(int column: dupElim.columns)
			KeyFactoryOperations.addKey(duplicateEliminationBuilder, MetadataTypesMapping.getKeyClass(dupElim.getChild().getNRSMD().getType(column)), column);
		ReduceOperator duplicateElimination = duplicateEliminationBuilder.build();
		
		// projection configuration
		final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(dupElim.getNRSMD()));
		duplicateElimination.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
		final String encodedDuplicateEliminationColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(dupElim.columns));
		duplicateElimination.setParameter(PACTOperatorsConfiguration.DUP_ELIM_COLUMNS_BINARY.toString(), encodedDuplicateEliminationColumns);
		
		return new Operator[]{duplicateElimination};
	}
	
	private static final Operator[] translate(Aggregation aggr) {
		Operator[] childPlan = translate(aggr.getChild());

		Operator aggregation;
		if(aggr.getAggregationPath().length > 1) {
			// create MapOperator for aggregating
			aggregation = MapOperator.builder(NestedAggregationOperator.class)
				.input(childPlan)
				.name("Aggr")
				.build();
			
			// aggregation configuration
			final String encodedNRSMD = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(aggr.getNRSMD()));
			aggregation.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD);
			final String aggregationPath = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(aggr.getAggregationPath()));
			aggregation.setParameter(PACTOperatorsConfiguration.AGGREGATION_PATH_BINARY.toString(), aggregationPath);
			final String aggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(aggr.getAggregationType()));
			aggregation.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), aggregationType);
		}
		else {
			//First, we create the NRSMD of the (pre) groupBy
			NestedMetadata groupByNRSMD = aggr.getNRSMD();
			MetadataTypes[] attScanMeta = new MetadataTypes[1];
			attScanMeta[0] = MetadataTypes.INTEGER_TYPE;
			final NestedMetadata auxColumnNRSMD = new NestedMetadata(1,attScanMeta);
			groupByNRSMD = NestedMetadataUtils.appendNRSMD(groupByNRSMD, auxColumnNRSMD);
			
			//Then, we create ReduceOperator for grouping using the document ID column
			ReduceOperator.Builder groupByBuilder = ReduceOperator.builder(GroupByWithAggregationOperator.class)
					.input(childPlan)
					.name("GroupByAgg");
			KeyFactoryOperations.addKey(groupByBuilder, StringValue.class, aggr.getDocumentIDColumn());
			ReduceOperator groupBy = groupByBuilder.build();
			
			// groupBy configuration
			final String encodedNRSMDGroupBy = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(groupByNRSMD));
			groupBy.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDGroupBy);
			
			final String encodedGroupByColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(new int[]{}));
			groupBy.setParameter(PACTOperatorsConfiguration.GROUP_BY_COLUMNS_BINARY.toString(), encodedGroupByColumns);

			final NestedMetadata childNRSMD = aggr.getChild().getNRSMD();
			int[] nestColumns = new int[childNRSMD.getColNo()];
			for(int i=0; i<childNRSMD.getColNo(); i++)
				nestColumns[i] = i;
			final String encodedNestColumns = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nestColumns));
			groupBy.setParameter(PACTOperatorsConfiguration.NEST_COLUMNS_BINARY.toString(), encodedNestColumns);
			
			groupBy.setParameter(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), aggr.getAggregationPath()[0]);

			final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(aggr.getAggregationType()));
			groupBy.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
			
			groupBy.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), aggr.isExcludeNestedField());
			
			groupBy.setParameter(PACTOperatorsConfiguration.ATTACH_DUMMY_COLUMN_BOOLEAN.toString(), true);
			
			
			// create ReduceOperator for aggregating
			ReduceOperator.Builder aggregationBuilder = ReduceOperator.builder(PostAggregationOperator.class)
				.input(groupBy)
				.name("PostAggr");
			KeyFactoryOperations.addKey(aggregationBuilder, IntValue.class, groupByNRSMD.colNo-1);
			aggregation = aggregationBuilder.build();
			
			//Post-aggregation configuration
			final String encodedNRSMDPostAggregation = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(aggr.getNRSMD()));
			aggregation.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPostAggregation);
			if(aggr.isExcludeNestedField())
				aggregation.setParameter(PACTOperatorsConfiguration.POST_AGGREGATION_COLUMN_INT.toString(), 0);
			else {
				aggregation.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), 0);
				aggregation.setParameter(PACTOperatorsConfiguration.POST_AGGREGATION_COLUMN_INT.toString(), 1);
			}
			
			aggregation.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
			
			aggregation.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), aggr.isExcludeNestedField());
		}
		
		return new Operator[]{aggregation};
	}
	
	private static final Operator[] translate(CartesianProduct cp) {
		Operator[] childPlan1 = translate(cp.getLeft());
		Operator[] childPlan2 = translate(cp.getRight());

		// create CrossOperator for cartesian product
		CrossOperator cartesianProduct = CrossOperator.builder(CartesianProductOperator.class)
				.input1(childPlan1)
				.input2(childPlan2)
				.name("Product")
				.build();

		// cartesian product configuration
		final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(cp.getLeft().getNRSMD()));
		cartesianProduct.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
		final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(cp.getRight().getNRSMD()));
		cartesianProduct.setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
				
		return new Operator[]{cartesianProduct};
	}
	
	private static final Operator[] translate(Join j) {
		Operator[] childPlan1 = translate(j.getLeft());
		Operator[] childPlan2 = translate(j.getRight());

		Operator[] join;
		
		if(!j.getPred().isOnlyEqui()) { //THETA INNER JOIN
			// create CrossOperator for theta join
			join = new Operator[] {
					CrossOperator.builder(ThetaJoinOperator.class)
					.input1(childPlan1)
					.input2(childPlan2)
					.name("JoinEval")
					.build()};

			//  for theta join configuration
			final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getLeft().getNRSMD()));
			join[0].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getRight().getNRSMD()));
			join[0].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getPred()));
			join[0].setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
		}
		else if(j.getPred() instanceof DisjunctivePredicate 
				&& ((DisjunctivePredicate)j.getPred()).getConjunctivePreds().size() != 1) { //DISJ INNER JOIN
			// create CoGroup contracts for disjunctive equi join
			DisjunctivePredicate disjPred = (DisjunctivePredicate) j.getPred();
			final int[][] leftColumns = j.getPred().getLeftColumns();
			final int[][] rightColumns = j.getPred().getRightColumns();
			
			//Parameters that will be used later for configuring each contract
			final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getLeft().getNRSMD()));
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getRight().getNRSMD()));
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(disjPred));
			
			final NestedMetadata leftNRSMD = j.getLeft().getNRSMD();

			//We create the join with the number of contracts needed for evaluation
			join = new Operator[disjPred.getConjunctivePreds().size()];			
			for(int i=0; i<join.length; i++) {
				JoinOperator.Builder conjEquiJoinBuilder = JoinOperator.builder (
						DisjEquiJoinOperator.class,
						MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[i][0])),
						leftColumns[i][0],
						rightColumns[i][0]-leftNRSMD.getColNo() );
				conjEquiJoinBuilder.input1(childPlan1).input2(childPlan2)
						.name("JoinEval(" + i + ")");
				
				for(int k=1; k<leftColumns[i].length; k++)
					KeyFactoryOperations.addKey (
							conjEquiJoinBuilder,
							MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[i][k])),
							leftColumns[i][k],
							rightColumns[i][k]-leftNRSMD.getColNo() );
				
				join[i] = conjEquiJoinBuilder.build();	
				// join configuration
				join[i].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
				join[i].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
				join[i].setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
				join[i].setParameter(PACTOperatorsConfiguration.PRED_INT.toString(), i);
			}
		}
		else { //CONJ INNER JOIN
			// create JoinOperator for conjunctive equi join
			final int[] leftColumns = j.getPred().getLeftColumns()[0];
			final int[] rightColumns = j.getPred().getRightColumns()[0];
			
			final NestedMetadata leftNRSMD = j.getLeft().getNRSMD();
			
			JoinOperator.Builder conjEquiJoinBuilder = JoinOperator.builder (
					ConjEquiJoinOperator.class,
					MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[0])),
					leftColumns[0],
					rightColumns[0]-leftNRSMD.getColNo() );
			conjEquiJoinBuilder.input1(childPlan1).input2(childPlan2)
					.name("JoinConcat");
			
			for(int i=1; i<leftColumns.length; i++)
				KeyFactoryOperations.addKey (
						conjEquiJoinBuilder,
						MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[i])),
						leftColumns[i],
						rightColumns[i]-leftNRSMD.getColNo() );
			
			join = new Operator[] {conjEquiJoinBuilder.build()};
			// for equi join configuration
			final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getLeft().getNRSMD()));
			join[0].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(j.getRight().getNRSMD()));
			join[0].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
		}
		
		return join;
	}
	
	private static final Operator[] translate(LeftOuterJoin loj) {
		Operator[] childPlan1 = translate(loj.getLeft());
		Operator[] childPlan2 = translate(loj.getRight());

		Operator[] conjLeftOuterJoin;
		
		if(!loj.getPred().isOnlyEqui()) { // THETA
			// 1) create ReduceOperator for pre join processing
			ReduceOperator.Builder preJoinBuilder = ReduceOperator.builder(PreJoinOperator.class)
					.input(childPlan1)
					.name("PreLOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(loj.getLeft().getNRSMD().getType(loj.getDocumentIDColumn())),
					loj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(loj.getLeft().getNRSMD().getType(loj.getNodeIDColumn())),
					loj.getNodeIDColumn());
			ReduceOperator preJoin = preJoinBuilder.build();

			// preJoin configuration
			// we need to create the new NRSMD for the right side
			NestedMetadata nrsmdPreJoinRightSide = new NestedMetadata(1, new MetadataTypes[] {MetadataTypes.INTEGER_TYPE});
			final NestedMetadata nrsmdPreJoin = NestedMetadataUtils.appendNRSMD(loj.getLeft().getNRSMD(), nrsmdPreJoinRightSide);
			final String encodedNRSMDPreJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPreJoin));
			preJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
			
			// 2) create CrossOperator for join processing
			CrossOperator thetaJoin = CrossOperator.builder(ThetaLOJoinOperator.class)
					.input1(preJoin)
					.input2(childPlan2)
					.name("LOJoinEval")
					.build();

			// theta join configuration
			thetaJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(loj.getRight().getNRSMD()));
			thetaJoin.setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
			
			//Change the right side of the predicate to accommodate the new field on the left
			final int[][] rightColumns = loj.getPred().getRightColumns();
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i=0; i<rightColumns.length; i++)
				for(int j=0; j<rightColumns[i].length; j++)
					map.put(rightColumns[i][j], rightColumns[i][j]+1);
			BasePredicate pred = PushdownUtility.updatePredicate(loj.getPred(), map);
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(pred));
			thetaJoin.setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
		
			// 3) create ReduceOperator for post join processing
			ReduceOperator.Builder postJoinBuilder = ReduceOperator.builder(PostLOJoinOperator.class)
					.input(thetaJoin)
					.name("PostLOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(loj.getDocumentIDColumn())),
					loj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(loj.getNodeIDColumn())),
					loj.getNodeIDColumn());
			//Record ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(MetadataTypes.INTEGER_TYPE),
					loj.getLeft().getNRSMD().getColNo());
			ReduceOperator postJoin = postJoinBuilder.build();

			// postJoin configuration
			// we create the definitive NRSMD
			final NestedMetadata nrsmdPostJoin = loj.getNRSMD();
			final String encodedNRSMDPostJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPostJoin));
			postJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPostJoin);
			postJoin.setParameter(PACTOperatorsConfiguration.RECORD_IDENTIFIER_COLUMN_INT.toString(), loj.getLeft().getNRSMD().getColNo());
			postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), loj.getLeft().getNRSMD().getColNo()+1);
			postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), loj.getNRSMD().getColNo());
			
			conjLeftOuterJoin = new Operator[]{postJoin};
		}
		else if(loj.getPred() instanceof DisjunctivePredicate 
				&& ((DisjunctivePredicate)loj.getPred()).getConjunctivePreds().size() != 1) { // DISJ EQUI
			// 1) create ReduceOperator for pre join processing
			ReduceOperator.Builder preJoinBuilder = ReduceOperator.builder(PreJoinOperator.class)
					.input(childPlan1)
					.name("PreLOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(loj.getLeft().getNRSMD().getType(loj.getDocumentIDColumn())),
					loj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(loj.getLeft().getNRSMD().getType(loj.getNodeIDColumn())),
					loj.getNodeIDColumn());
			ReduceOperator preJoin = preJoinBuilder.build();

			// preJoin configuration
			// we need to create the new NRSMD for the right side
			NestedMetadata nrsmdPreJoinRightSide = new NestedMetadata(1, new MetadataTypes[] {MetadataTypes.INTEGER_TYPE});
			final NestedMetadata nrsmdPreJoin = NestedMetadataUtils.appendNRSMD(loj.getLeft().getNRSMD(), nrsmdPreJoinRightSide);
			final String encodedNRSMDPreJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPreJoin));
			preJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
						
			// 2) create CoGroup contracts for join processing
			// create CoGroup contracts for disjunctive equi join
			DisjunctivePredicate disjPred = (DisjunctivePredicate) loj.getPred();
			final int[][] leftColumns = loj.getPred().getLeftColumns();
			final int[][] rightColumns = loj.getPred().getRightColumns();
			
			//Parameters that will be used later for configuring each contract
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(loj.getRight().getNRSMD()));

			//Change the right side of the predicate to accommodate the new field on the left
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i=0; i<rightColumns.length; i++)
				for(int j=0; j<rightColumns[i].length; j++)
					map.put(rightColumns[i][j], rightColumns[i][j]+1);
			disjPred = (DisjunctivePredicate) PushdownUtility.updatePredicate(disjPred, map);
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(disjPred));
			
			//We create the join with the number of contracts needed for evaluation
			Operator[] disjJoin = new Operator[disjPred.getConjunctivePreds().size()];			
			for(int i=0; i<disjJoin.length; i++) {
				CoGroupOperator.Builder conjEquiJoinBuilder = CoGroupOperator.builder (
						DisjLOEquiJoinOperator.class,
						MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(leftColumns[i][0])),
						leftColumns[i][0],
						rightColumns[i][0]-loj.getLeft().getNRSMD().getColNo() );
				conjEquiJoinBuilder.input1(preJoin).input2(childPlan2)
						.name("LOJoinEval(" + i + ")");
				
				for(int k=1; k<leftColumns[i].length; k++)
					KeyFactoryOperations.addKey (
							conjEquiJoinBuilder,
							MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(leftColumns[i][k])),
							leftColumns[i][k],
							rightColumns[i][k]-loj.getLeft().getNRSMD().getColNo() );
				
				disjJoin[i] = conjEquiJoinBuilder.build();			
				// join configuration
				disjJoin[i].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.PRED_INT.toString(), i);
			}
			
			// 3) create ReduceOperator for post join processing
			ReduceOperator.Builder postJoinBuilder = ReduceOperator.builder(PostLOJoinOperator.class)
					.input(disjJoin)
					.name("PostLOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(loj.getDocumentIDColumn())),
					loj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(loj.getNodeIDColumn())),
					loj.getNodeIDColumn());
			//Record ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(MetadataTypes.INTEGER_TYPE),
					loj.getLeft().getNRSMD().getColNo());
			ReduceOperator postJoin = postJoinBuilder.build();

			// postJoin configuration
			// we create the definitive NRSMD
			final NestedMetadata nrsmdPostJoin = loj.getNRSMD();
			final String encodedNRSMDPostJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPostJoin));
			postJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPostJoin);
			postJoin.setParameter(PACTOperatorsConfiguration.RECORD_IDENTIFIER_COLUMN_INT.toString(), loj.getLeft().getNRSMD().getColNo());
			postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), loj.getLeft().getNRSMD().getColNo()+1);
			postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), loj.getNRSMD().getColNo());
			
			conjLeftOuterJoin = new Operator[]{postJoin};
		}
		else { // CONJ EQUI
			// create JoinOperator for conjunctive equi join
			final int[] leftColumns = loj.getPred().getLeftColumns()[0];
			final int[] rightColumns = loj.getPred().getRightColumns()[0];
			
			final NestedMetadata leftNRSMD = loj.getLeft().getNRSMD();
			
			CoGroupOperator.Builder conjLeftOuterEquiJoinBuilder = CoGroupOperator.builder (
					ConjLOEquiJoinOperator.class,
					MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[0])),
					leftColumns[0],
					rightColumns[0]-leftNRSMD.getColNo());
			conjLeftOuterEquiJoinBuilder.input1(childPlan1).input2(childPlan2)
					.name("LOJoinConcat");
			
			for(int i=1; i<leftColumns.length; i++)
				KeyFactoryOperations.addKey(
						conjLeftOuterEquiJoinBuilder,
						MetadataTypesMapping.getKeyClass(leftNRSMD.getType(leftColumns[i])),
						leftColumns[i],
						rightColumns[i]-leftNRSMD.getColNo() );
			
			conjLeftOuterJoin = new Operator[] {conjLeftOuterEquiJoinBuilder.build()};
			// for equi join configuration
			final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(loj.getLeft().getNRSMD()));
			conjLeftOuterJoin[0].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(loj.getRight().getNRSMD()));
			conjLeftOuterJoin[0].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
		}
		
		return conjLeftOuterJoin;
	}
	
	private static final Operator[] translate(LeftOuterNestedJoin lonj) {
		final boolean withAggregation = lonj instanceof LeftOuterNestedJoinWithAggregation;

		Operator[] childPlan1 = translate(lonj.getLeft());
		Operator[] childPlan2 = translate(lonj.getRight());
		
		Operator[] conjLeftOuterNestedJoin;

		if(!lonj.getPred().isOnlyEqui()) { // THETA
			// 1) create ReduceOperator for pre join processing
			ReduceOperator.Builder preJoinBuilder = ReduceOperator.builder(PreJoinOperator.class)
					.input(childPlan1)
					.name("PreLNOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(lonj.getDocumentIDColumn())),
					lonj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(lonj.getNodeIDColumn())),
					lonj.getNodeIDColumn());
			ReduceOperator preJoin = preJoinBuilder.build();

			// preJoin configuration
			// we need to create the new NRSMD for the right side
			NestedMetadata nrsmdPreJoinRightSide = new NestedMetadata(1, new MetadataTypes[] {MetadataTypes.INTEGER_TYPE});
			final NestedMetadata nrsmdPreJoin = NestedMetadataUtils.appendNRSMD(lonj.getLeft().getNRSMD(), nrsmdPreJoinRightSide);
			final String encodedNRSMDPreJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPreJoin));
			preJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
			
			// 2) create CrossOperator for join processing
			CrossOperator thetaJoin;
			if(withAggregation)
				thetaJoin = CrossOperator.builder(ThetaLNOJoinWithAggregationOperator.class)
						.input1(preJoin)
						.input2(childPlan2)
						.name("LNOJoinEvalAgg")
						.build();
			else
				thetaJoin = CrossOperator.builder(ThetaLNOJoinOperator.class)
						.input1(preJoin)
						.input2(childPlan2)
						.name("LNOJoinEval")
						.build();

			// theta join configuration
			thetaJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonj.getRight().getNRSMD()));
			thetaJoin.setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);

			//Change the right side of the predicate to accommodate the new field on the left
			final int[][] rightColumns = lonj.getPred().getRightColumns();
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i=0; i<rightColumns.length; i++)
				for(int j=0; j<rightColumns[i].length; j++)
					map.put(rightColumns[i][j], rightColumns[i][j]+1);
			BasePredicate pred = PushdownUtility.updatePredicate(lonj.getPred(), map);
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(pred));
			thetaJoin.setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
			if(withAggregation) {
				LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) lonj;
				
				thetaJoin.setParameter(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), lonja.getAggregationColumn()-lonja.getLeft().getNRSMD().getColNo());

				final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonja.getAggregationType()));
				thetaJoin.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
			
				thetaJoin.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), lonja.isExcludeNestedField());
			}
			
			// 3) create ReduceOperator for post join processing
			ReduceOperator.Builder postJoinBuilder;
			if(withAggregation)
				postJoinBuilder = ReduceOperator.builder(PostLNOJoinWithAggregationOperator.class)
						.input(thetaJoin)
						.name("PostLNOJoinAgg");
			else
				postJoinBuilder = ReduceOperator.builder(PostLNOJoinOperator.class)
						.input(thetaJoin)
						.name("PostLNOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(lonj.getDocumentIDColumn())),
					lonj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(lonj.getNodeIDColumn())),
					lonj.getNodeIDColumn());
			//Record ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(MetadataTypes.INTEGER_TYPE),
					lonj.getLeft().getNRSMD().getColNo());
			ReduceOperator postJoin = postJoinBuilder.build();

			// postJoin configuration
			// we create the definitive NRSMD
			final NestedMetadata nrsmdPostJoin = lonj.getNRSMD();
			final String encodedNRSMDPostJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPostJoin));
			postJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPostJoin);
			postJoin.setParameter(PACTOperatorsConfiguration.RECORD_IDENTIFIER_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo());
			if(withAggregation) {
				LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) lonj;
				
				if(lonja.isExcludeNestedField())
					postJoin.setParameter(PACTOperatorsConfiguration.COMBINATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);					
				else {
					postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);
					postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+2);
					postJoin.setParameter(PACTOperatorsConfiguration.COMBINATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+3);
				}

				final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonja.getAggregationType()));
				postJoin.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
				
				postJoin.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), lonja.isExcludeNestedField());
			}
			else {
				postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);
				postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+2);
			}
			
			conjLeftOuterNestedJoin = new Operator[]{postJoin};
		}
		else if(lonj.getPred() instanceof DisjunctivePredicate 
				&& ((DisjunctivePredicate)lonj.getPred()).getConjunctivePreds().size() != 1) { // DISJ EQUI
			// 1) create ReduceOperator for pre join processing
			ReduceOperator.Builder preJoinBuilder = ReduceOperator.builder(PreJoinOperator.class)
					.input(childPlan1)
					.name("PreLNOJoin");
			//Document ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(lonj.getDocumentIDColumn())),
					lonj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(preJoinBuilder,
					MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(lonj.getNodeIDColumn())),
					lonj.getNodeIDColumn());
			ReduceOperator preJoin = preJoinBuilder.build();

			// preJoin configuration
			// we need to create the new NRSMD for the right side
			NestedMetadata nrsmdPreJoinRightSide = new NestedMetadata(1, new MetadataTypes[] {MetadataTypes.INTEGER_TYPE});
			final NestedMetadata nrsmdPreJoin = NestedMetadataUtils.appendNRSMD(lonj.getLeft().getNRSMD(), nrsmdPreJoinRightSide);
			final String encodedNRSMDPreJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPreJoin));
			preJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
						
			// 2) create CoGroup contracts for join processing
			// create CoGroup contracts for disjunctive equi join
			DisjunctivePredicate disjPred = (DisjunctivePredicate) lonj.getPred();
			final int[][] leftColumns = lonj.getPred().getLeftColumns();
			final int[][] rightColumns = lonj.getPred().getRightColumns();
			
			//Parameters that will be used later for configuring each contract
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonj.getRight().getNRSMD()));
			
			//Change the right side of the predicate to accommodate the new field on the left
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i=0; i<rightColumns.length; i++)
				for(int j=0; j<rightColumns[i].length; j++)
					map.put(rightColumns[i][j], rightColumns[i][j]+1);
			disjPred = (DisjunctivePredicate) PushdownUtility.updatePredicate(disjPred, map);
			final String encodedPredicate = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(disjPred));
			
			//We create the join with the number of contracts needed for evaluation
			Operator[] disjJoin = new Operator[disjPred.getConjunctivePreds().size()];			
			for(int i=0; i<disjJoin.length; i++) {
				CoGroupOperator.Builder conjEquiJoinBuilder;
				if(withAggregation) {
					conjEquiJoinBuilder = CoGroupOperator.builder (
							DisjLNOEquiJoinWithAggregationOperator.class,
							MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(leftColumns[i][0])),
							leftColumns[i][0],
							rightColumns[i][0]-lonj.getLeft().getNRSMD().getColNo() );
					conjEquiJoinBuilder.input1(preJoin).input2(childPlan2)
							.name("LNOJoinEvalAgg(" + i + ")");
				}
				else {
					conjEquiJoinBuilder = CoGroupOperator.builder (
							DisjLNOEquiJoinOperator.class,
							MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(leftColumns[i][0])),
							leftColumns[i][0],
							rightColumns[i][0]-lonj.getLeft().getNRSMD().getColNo() );
					conjEquiJoinBuilder.input1(preJoin).input2(childPlan2)
							.name("LNOJoinEval(" + i + ")");
				}	

				for(int k=1; k<leftColumns[i].length; k++)
					KeyFactoryOperations.addKey (
							conjEquiJoinBuilder,
							MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(leftColumns[i][k])),
							leftColumns[i][k],
							rightColumns[i][k]-lonj.getLeft().getNRSMD().getColNo() );
				
				disjJoin[i] = conjEquiJoinBuilder.build();			
				// join configuration
				disjJoin[i].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPreJoin);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.PRED_BINARY.toString(), encodedPredicate);
				disjJoin[i].setParameter(PACTOperatorsConfiguration.PRED_INT.toString(), i);
				if(withAggregation) {
					LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) lonj;
					
					disjJoin[i].setParameter(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), lonja.getAggregationColumn()-lonja.getLeft().getNRSMD().getColNo());

					final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonja.getAggregationType()));
					disjJoin[i].setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
					
					disjJoin[i].setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), lonja.isExcludeNestedField());
				}
			}
			
			// 3) create ReduceOperator for post join processing
			ReduceOperator.Builder postJoinBuilder;
			if(withAggregation)
				postJoinBuilder = ReduceOperator.builder(PostLNOJoinWithAggregationOperator.class)
						.input(disjJoin)
						.name("PostLNOJoinAgg");
			else
				postJoinBuilder = ReduceOperator.builder(PostLNOJoinOperator.class)
						.input(disjJoin)
						.name("PostLNOJoin");
			
			//Document ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(lonj.getDocumentIDColumn())),
					lonj.getDocumentIDColumn());
			//Node ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(nrsmdPreJoin.getType(lonj.getNodeIDColumn())),
					lonj.getNodeIDColumn());
			//Record ID column
			KeyFactoryOperations.addKey(postJoinBuilder,
					MetadataTypesMapping.getKeyClass(MetadataTypes.INTEGER_TYPE),
					lonj.getLeft().getNRSMD().getColNo());
			ReduceOperator postJoin = postJoinBuilder.build();

			// postJoin configuration
			// we create the definitive NRSMD
			final NestedMetadata nrsmdPostJoin = lonj.getNRSMD();
			final String encodedNRSMDPostJoin = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(nrsmdPostJoin));
			postJoin.setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMDPostJoin);
			postJoin.setParameter(PACTOperatorsConfiguration.RECORD_IDENTIFIER_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo());
			if(withAggregation) {
				LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) lonj;
				
				if(lonja.isExcludeNestedField())
					postJoin.setParameter(PACTOperatorsConfiguration.COMBINATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);					
				else {
					postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);
					postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+2);
					postJoin.setParameter(PACTOperatorsConfiguration.COMBINATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+3);
				}

				final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonja.getAggregationType()));
				postJoin.setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
				
				postJoin.setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), lonja.isExcludeNestedField());
			}
			else {
				postJoin.setParameter(PACTOperatorsConfiguration.NESTED_RECORDS_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+1);
				postJoin.setParameter(PACTOperatorsConfiguration.EVALUATION_COLUMN_INT.toString(), lonj.getLeft().getNRSMD().getColNo()+2);
			}
			
			conjLeftOuterNestedJoin = new Operator[]{postJoin};
		}
		else { // CONJ EQUI
			// create JoinOperator for conjunctive equi join
			int[] leftColumns = lonj.getPred().getLeftColumns()[0];
			int[] rightColumns = lonj.getPred().getRightColumns()[0];
			
			CoGroupOperator.Builder conjLeftOuterNestedEquiJoinBuilder;
			if(withAggregation)
				conjLeftOuterNestedEquiJoinBuilder = CoGroupOperator.builder(ConjLNOEquiJoinWithAggregationOperator.class, MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(leftColumns[0])), leftColumns[0], rightColumns[0]-lonj.getLeft().getNRSMD().getColNo())
						.input1(childPlan1)
						.input2(childPlan2)
						.name("LNOJoinConcatAgg");	
			else
				conjLeftOuterNestedEquiJoinBuilder = CoGroupOperator.builder(ConjLNOEquiJoinOperator.class, MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(leftColumns[0])), leftColumns[0], rightColumns[0]-lonj.getLeft().getNRSMD().getColNo())
						.input1(childPlan1)
						.input2(childPlan2)
						.name("LNOJoinConcat");
			
			for(int i=1; i<leftColumns.length; i++)
				KeyFactoryOperations.addKey(conjLeftOuterNestedEquiJoinBuilder, MetadataTypesMapping.getKeyClass(lonj.getLeft().getNRSMD().getType(leftColumns[i])), leftColumns[i], rightColumns[i]-lonj.getLeft().getNRSMD().getColNo());
			conjLeftOuterNestedJoin = new Operator[] {conjLeftOuterNestedEquiJoinBuilder.build()};
			
			// for equi join configuration
			final String encodedNRSMD1 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonj.getLeft().getNRSMD()));
			conjLeftOuterNestedJoin[0].setParameter(PACTOperatorsConfiguration.NRSMD1_BINARY.toString(), encodedNRSMD1);
			final String encodedNRSMD2 = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonj.getRight().getNRSMD()));
			conjLeftOuterNestedJoin[0].setParameter(PACTOperatorsConfiguration.NRSMD2_BINARY.toString(), encodedNRSMD2);
			if(withAggregation) {
				LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) lonj;
				
				conjLeftOuterNestedJoin[0].setParameter(PACTOperatorsConfiguration.AGGREGATION_COLUMN_INT.toString(), lonja.getAggregationColumn()-lonja.getLeft().getNRSMD().getColNo());

				final String encodedAggregationType = DatatypeConverter.printBase64Binary(SerializationUtils.serialize(lonja.getAggregationType()));
				conjLeftOuterNestedJoin[0].setParameter(PACTOperatorsConfiguration.AGGREGATION_TYPE_BINARY.toString(), encodedAggregationType);
				
				conjLeftOuterNestedJoin[0].setParameter(PACTOperatorsConfiguration.EXCLUDE_NESTED_FIELD_BOOLEAN.toString(), lonja.isExcludeNestedField());
			}
		}
			
		return conjLeftOuterNestedJoin;
	}

}
