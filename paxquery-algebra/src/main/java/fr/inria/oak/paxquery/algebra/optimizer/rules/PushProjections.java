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
package fr.inria.oak.paxquery.algebra.optimizer.rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import fr.inria.oak.paxquery.algebra.logicalplan.LogicalPlan;
import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseBinaryOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.BaseJoinOperator;
import fr.inria.oak.paxquery.algebra.operators.binary.CartesianProduct;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoin;
import fr.inria.oak.paxquery.algebra.operators.binary.LeftOuterNestedJoinWithAggregation;
import fr.inria.oak.paxquery.algebra.operators.border.XMLConstruct;
import fr.inria.oak.paxquery.algebra.operators.border.XMLScan;
import fr.inria.oak.paxquery.algebra.operators.border.XMLTreeConstruct;
import fr.inria.oak.paxquery.algebra.operators.unary.Aggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.BaseUnaryOperator;
import fr.inria.oak.paxquery.algebra.operators.unary.DuplicateElimination;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupBy;
import fr.inria.oak.paxquery.algebra.operators.unary.GroupByWithAggregation;
import fr.inria.oak.paxquery.algebra.operators.unary.Navigation;
import fr.inria.oak.paxquery.algebra.operators.unary.Projection;
import fr.inria.oak.paxquery.algebra.operators.unary.Selection;
import fr.inria.oak.paxquery.algebra.optimizer.rules.PushdownUtility.ColumnsMapping;
import fr.inria.oak.paxquery.algebra.optimizer.rules.PushdownUtility.Pair;
import fr.inria.oak.paxquery.algebra.optimizer.rules.PushdownUtility.ProjectColumn;
import fr.inria.oak.paxquery.common.datamodel.metadata.MetadataTypes;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadata;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePattern;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode;
import fr.inria.oak.paxquery.common.xml.construction.ConstructionTreePatternNode.ContentType;

/**
 * This class contains the methods for pushing down projections in a given logical plan.
 * 
 */
public final class PushProjections implements Logical2Logical {

  private static final Log LOG = LogFactory.getLog(PushProjections.class);

  public static final PushProjections INSTANCE = new PushProjections();

  private PushProjections() {
  }

  /**
   * This method pushes down projections in a given logical plan while keeping the list of columns
   * that result from the plan unchanged.
   *
   * @param op
   *          the root of the logical plan
   * @return the new root of the logical plan
   */
  @Override
  public BaseLogicalOperator transform(BaseLogicalOperator op) {
    assert op instanceof XMLConstruct || op instanceof XMLTreeConstruct;

    // 1. Build the NRSMD of the operators in the tree
    op.getNRSMD();

    // 2. Push projections down
    Set<ProjectColumn> requiredColumnsAbove = Sets.newTreeSet();
    Set<ProjectColumn> opRequiredColumns = getRequiredInputColumns(op, requiredColumnsAbove);
    BaseLogicalOperator child = pushProjections(op.getChildren().get(0), opRequiredColumns);
    LogicalPlan.connect(op, child);
    ColumnsMapping updatedColumns = obtainMapping(opRequiredColumns);
    updateOperatorColumns(op, updatedColumns);
    op.buildOwnDetails();

    // 3. Rebuild NRSMD
    op.resetNRSMD();
    op.getNRSMD();

    return op;
  }

  private static BaseLogicalOperator pushProjections(BaseLogicalOperator op,
          Set<ProjectColumn> columnsRequiredAbove) {
    // 1. The set of columns that the logical operator should have. It is built with the set of
    // columnsRequiredAbove and the columns referred by any predicate that the logical operator
    // would have.
    Set<ProjectColumn> columnsRequiredBelow = getRequiredInputColumns(op, columnsRequiredAbove);
    Set<ProjectColumn> columnsOperator = getOperatorColumns(op, columnsRequiredAbove,
            columnsRequiredBelow);
    // Update columns required above
    for (ProjectColumn column : columnsRequiredAbove) {
      if (!column.nestedColumns.isEmpty()) {
        for (ProjectColumn opColumn : columnsOperator) {
          if (column.pos == opColumn.pos) {
            Set<ProjectColumn> nestedFields = Sets.newTreeSet();
            for (ProjectColumn nestedField : opColumn.nestedColumns) {
              nestedFields.add(nestedField.copy());
            }
            column.nestedColumns = nestedFields;
          }
        }
      }
    }

    // if (LOG.isDebugEnabled()) {
    System.out.println("op : " + op);
    System.out.println("columnsRequiredAbove : " + columnsRequiredAbove);
    System.out.println("columnsRequiredBelow : " + columnsRequiredBelow);
    System.out.println("columnsOperator : " + columnsOperator);
    // }

    // Children created by the method as we push projections.
    if (op instanceof BaseUnaryOperator) {
      BaseLogicalOperator newChild = pushProjections(op.getChildren().get(0), columnsRequiredBelow);
      LogicalPlan.connect(op, newChild);
    } else if (op instanceof BaseBinaryOperator) {
      BaseBinaryOperator binaryOp = (BaseBinaryOperator) op;
      Pair<Set<ProjectColumn>, Set<ProjectColumn>> splitFields = splitFieldRequiredForBinaryOperator(
              binaryOp, columnsRequiredBelow);
      BaseLogicalOperator newLeftChild = pushProjections(op.getChildren().get(0), splitFields.left);
      BaseLogicalOperator newRightChild = pushProjections(op.getChildren().get(1),
              splitFields.right);
      LogicalPlan.connect(op, newLeftChild, newRightChild);
    }

    // 2. Create a project operator on top, if necessary.
    BaseLogicalOperator returnOp = op;
    Set<Integer> columnPositionsRequiredAbove = getTopLevelSet(columnsRequiredAbove);
    Set<Integer> opFieldsPositions = getTopLevelSet(columnsOperator);
    if (returnOp.getNRSMD().colNo > columnPositionsRequiredAbove.size()) {
      int projCols[] = new int[columnPositionsRequiredAbove.size()];
      int here = 0;
      int above = 0;
      for (int i = 0; i < op.getNRSMD().colNo; i++) {
        if (columnPositionsRequiredAbove.contains(i)) {
          projCols[above++] = here++;
        } else if (opFieldsPositions.contains(i)) {
          here++;
        }
      }
      Projection proj = new Projection(returnOp, projCols);
      returnOp = proj;
    }

    // 3. Update the operator using the position mapping
    ColumnsMapping updatedColumns = obtainMapping(columnsRequiredBelow);
    updateOperatorColumns(op, updatedColumns);
    op.buildOwnDetails();

    return returnOp;
  }

  private static Pair<Set<ProjectColumn>, Set<ProjectColumn>> splitFieldRequiredForBinaryOperator(
          BaseBinaryOperator binop, Set<ProjectColumn> requiredFromBelow) {
    Set<ProjectColumn> leftFields = Sets.newTreeSet();
    Set<ProjectColumn> rightFields = Sets.newTreeSet();
    int numberLeftFields = binop.getLeft().getNRSMD().getColNo();
    for (ProjectColumn column : requiredFromBelow) {
      if (column.pos < numberLeftFields) {
        leftFields.add(column.copy());
      } else {
        rightFields.add(column.copy(column.pos - numberLeftFields));
      }
    }

    return new Pair<Set<ProjectColumn>, Set<ProjectColumn>>(leftFields, rightFields);
  }

  private static Set<ProjectColumn> getRequiredInputColumns(BaseLogicalOperator op,
          Set<ProjectColumn> columnsRequiredAbove) {
    final Set<ProjectColumn> requiredColumns = deriveRequiredInputColumns(op, columnsRequiredAbove);
    Set<ProjectColumn> newRequiredColumns = requiredColumns;
    if (op.getChildren() != null && !op.getChildren().isEmpty()) {
      newRequiredColumns = Sets.newTreeSet();
      int numberColumns = 0;
      for (BaseLogicalOperator child : op.getChildren()) {
        if (child instanceof LeftOuterNestedJoin) {
          LeftOuterNestedJoin lonj = (LeftOuterNestedJoin) child;
          Set<Integer> cols = PushdownUtility.getPredicateColumns(lonj.getPred());
          final int nestedColumnPos = lonj instanceof LeftOuterNestedJoinWithAggregation ? numberColumns
                  + lonj.getNRSMD().getColNo() - 2
                  : numberColumns + lonj.getNRSMD().getColNo() - 1;
          final ProjectColumn nestedColumn = new ProjectColumn(nestedColumnPos);
          for (ProjectColumn column : requiredColumns) {
            if (column.pos == nestedColumnPos) {
              for (ProjectColumn nested : column.nestedColumns) {
                nestedColumn.nestedColumns.add(nested.copy());
              }
            } else if (column.pos < child.getNRSMD().getColNo()) {
              newRequiredColumns.add(column.copy());
            }
          }
          final int numberColsLeftInput = numberColumns + lonj.getLeft().getNRSMD().getColNo();
          for (int pos : cols) {
            if (pos >= numberColsLeftInput) {
              nestedColumn.nestedColumns.add(new ProjectColumn(pos - numberColsLeftInput));
            }
          }
          newRequiredColumns.add(nestedColumn);
        } else {
          for (ProjectColumn column : requiredColumns) {
            if (column.pos < numberColumns + child.getNRSMD().getColNo()) {
              newRequiredColumns.add(column.copy());
            }
          }
        }
        numberColumns += child.getNRSMD().getColNo();
      }
    }

    return newRequiredColumns;
  }

  /*
   * <p>This method returns the columns that are referred to by the predicate of a given logical
   * operator.</p>
   * 
   * <p>Supported predicates: {@link SimplePredicate}, {@link ConjunctivePredicate}</p>
   * 
   * @param op a logical operator whose predicates will be read
   * 
   * @return a set of the columns to which the predicates refer
   */
  private static Set<ProjectColumn> deriveRequiredInputColumns(BaseLogicalOperator op,
          Set<ProjectColumn> columnsRequiredAbove) {
    final Set<ProjectColumn> requiredColumns = Sets.newTreeSet();

    if (op instanceof XMLConstruct) {
      assert columnsRequiredAbove.isEmpty();
      XMLConstruct xmlConstruct = (XMLConstruct) op;
      requiredColumns.addAll(deriveRequiredInputColumns(xmlConstruct.getApply(),
              xmlConstruct.getNRSMD()));
    } else if (op instanceof XMLTreeConstruct) {
      assert columnsRequiredAbove.isEmpty();
      XMLTreeConstruct xmlConstruct = (XMLTreeConstruct) op;
      requiredColumns.addAll(deriveRequiredInputColumns(xmlConstruct.getConstructionTreePattern()));
    } else if (op instanceof LeftOuterNestedJoinWithAggregation) {
      // Nested outer join with aggregation
      LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) op;
      Set<Integer> cols = PushdownUtility.getPredicateColumns(lonja.getPred());
      for (int col : cols) {
        requiredColumns.add(new ProjectColumn(col));
      }
      if (lonja.getDocumentIDColumn() != -1) {
        requiredColumns.add(new ProjectColumn(lonja.getDocumentIDColumn()));
      }
      for (int col : lonja.getNodeIDColumns()) {
        requiredColumns.add(new ProjectColumn(col));
      }
      requiredColumns.add(new ProjectColumn(lonja.getAggregationColumn()));
      final int leftNumberColumns = lonja.getNRSMD().getColNo() - 2;
      final int nestedField = leftNumberColumns;
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos < leftNumberColumns) {
          requiredColumns.add(column.copy());
        } else if (column.pos == nestedField) {
          for (ProjectColumn nestedColumn : column.nestedColumns) {
            requiredColumns.add(nestedColumn.copy(leftNumberColumns + nestedColumn.pos));
          }
        }
      }
    } else if (op instanceof LeftOuterNestedJoin) {
      // Nested outer join
      LeftOuterNestedJoin lonj = (LeftOuterNestedJoin) op;
      Set<Integer> cols = PushdownUtility.getPredicateColumns(lonj.getPred());
      for (int col : cols) {
        requiredColumns.add(new ProjectColumn(col));
      }
      if (lonj.getDocumentIDColumn() != -1) {
        requiredColumns.add(new ProjectColumn(lonj.getDocumentIDColumn()));
      }
      for (int col : lonj.getNodeIDColumns()) {
        requiredColumns.add(new ProjectColumn(col));
      }
      final int leftNumberColumns = lonj.getNRSMD().getColNo() - 1;
      final int nestedField = leftNumberColumns;
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos < leftNumberColumns) {
          requiredColumns.add(column.copy());
        } else if (column.pos == nestedField) {
          for (ProjectColumn nestedColumn : column.nestedColumns) {
            requiredColumns.add(nestedColumn.copy(leftNumberColumns + nestedColumn.pos));
          }
        }
      }
    } else if (op instanceof BaseJoinOperator) {
      // Any other kind of join
      Set<Integer> cols = PushdownUtility.getPredicateColumns(((BaseJoinOperator) op).getPred());
      for (int col : cols) {
        requiredColumns.add(new ProjectColumn(col));
      }
      for (ProjectColumn column : columnsRequiredAbove) {
        requiredColumns.add(column.copy());
      }
    } else if (op instanceof CartesianProduct) {
      // Cartesian product
      for (ProjectColumn column : columnsRequiredAbove) {
        requiredColumns.add(column.copy());
      }
    } else if (op instanceof Aggregation) {
      // Aggregation
      Aggregation agg = (Aggregation) op;
      if (agg.getDocumentIDColumn() != -1) {
        requiredColumns.add(new ProjectColumn(agg.getDocumentIDColumn()));
      }
      requiredColumns.add(new ProjectColumn(agg.getAggregationPath()[0]));
      int limit;
      if (agg.getAggregationPath().length == 2) {
        limit = agg.getNRSMD().getColNo() - 1;
      } else if (agg.getAggregationPath().length == 1) {
        limit = 0;
      } else {
        limit = agg.getNRSMD().getColNo();
      }
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos < limit) {
          requiredColumns.add(column.copy());
        }
      }
    } else if (op instanceof GroupBy) {
      // Grouping
      GroupBy gb = (GroupBy) op;
      final boolean aggregate = gb instanceof GroupByWithAggregation;
      for (int pos : gb.getGroupByColumns()) {
        requiredColumns.add(new ProjectColumn(pos));
      }
      for (int pos : gb.getReduceByColumns()) {
        requiredColumns.add(new ProjectColumn(pos));
      }
      final int nestedColumnPos = aggregate ? op.getNRSMD().getColNo() - 2 : op.getNRSMD()
              .getColNo() - 1;
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos == nestedColumnPos) {
          for (int i = 0; i < gb.getNestColumns().length; i++) {
            boolean added = false;
            for (ProjectColumn nested : column.nestedColumns) {
              if (nested.pos == i) {
                requiredColumns.add(nested.copy(gb.getNestColumns()[i]));
                added = true;
                break;
              }
            }
            if (!added) {
              requiredColumns.add(new ProjectColumn(i));
            }
          }
        }
      }
      if (aggregate) {
        requiredColumns
                .add(new ProjectColumn(((GroupByWithAggregation) gb).getAggregationColumn()));
      }
    } else if (op instanceof Selection) {
      // Selection
      for (ProjectColumn column : columnsRequiredAbove) {
        requiredColumns.add(column.copy());
      }
      Set<Integer> cols = PushdownUtility.getPredicateColumns(((Selection) op).getPred());
      for (int col : cols) {
        requiredColumns.add(new ProjectColumn(col));
      }
    } else if (op instanceof DuplicateElimination) {
      // DuplicateElimination
      for (ProjectColumn column : columnsRequiredAbove) {
        requiredColumns.add(column.copy());
      }
      for (int col : ((DuplicateElimination) op).getColumns()) {
        requiredColumns.add(new ProjectColumn(col));
      }
    } else if (op instanceof Navigation) {
      // Navigation
      Navigation pnop = (Navigation) op;
      // We add the column that we need for the navigation
      requiredColumns.add(new ProjectColumn(pnop.pos));
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos < pnop.getChild().getNRSMD().getColNo() && column.pos != pnop.pos) {
          requiredColumns.add(column.copy());
        }
      }
    }

    return requiredColumns;
  }

  private static Set<ProjectColumn> deriveRequiredInputColumns(ApplyConstruct apply,
          NestedMetadata signature) {
    Set<ProjectColumn> requiredColumns = Sets.newTreeSet();
    int posNested = 0;
    for (int field : apply.getFields()) {
      ProjectColumn column;
      if (signature.getType(field).equals(MetadataTypes.TUPLE_TYPE)) {
        Set<ProjectColumn> nestedColumns = deriveRequiredInputColumns(
                apply.getNested()[posNested++], signature.getNestedChild(field));
        column = new ProjectColumn(field, nestedColumns);
      } else {
        column = new ProjectColumn(field);
      }
      requiredColumns.add(column);
    }
    return requiredColumns;
  }

  private static Set<ProjectColumn> deriveRequiredInputColumns(ConstructionTreePattern ctp) {
    Set<ProjectColumn> requiredColumns = Sets.newTreeSet();
    deriveRequiredInputColumns(ctp.getRoot(), requiredColumns, 0);
    return requiredColumns;
  }

  private static void deriveRequiredInputColumns(ConstructionTreePatternNode node,
          Set<ProjectColumn> requiredColumns, int pos) {

    if (node.getContentType() == ContentType.VARIABLE_PATH) {
      int field = node.getVarPath().get(pos);
      ProjectColumn targetColumn = null;
      for (ProjectColumn column : requiredColumns) {
        if (column.pos == field) {
          targetColumn = column;
        }
      }
      if (targetColumn == null) {
        targetColumn = new ProjectColumn(field);
        requiredColumns.add(targetColumn);
      }
      if (pos + 1 < node.getVarPath().size()) {
        deriveRequiredInputColumns(node, targetColumn.nestedColumns, pos + 1);
      } else {
        for (ConstructionTreePatternNode child : node.getChildren()) {
          deriveRequiredInputColumns(child, targetColumn.nestedColumns, 0);
        }
      }
    } else {
      for (ConstructionTreePatternNode child : node.getChildren()) {
        deriveRequiredInputColumns(child, requiredColumns, 0);
      }
    }
  }

  private static Set<ProjectColumn> getOperatorColumns(BaseLogicalOperator op,
          Set<ProjectColumn> columnsRequiredAbove, Set<ProjectColumn> columnsRequiredBelow) {
    final Set<ProjectColumn> outputColumns = Sets.newTreeSet();

    if (op instanceof XMLConstruct) {
      outputColumns.addAll(columnsRequiredBelow);
    } else if (op instanceof XMLTreeConstruct) {
      outputColumns.addAll(columnsRequiredBelow);
    } else if (op instanceof LeftOuterNestedJoin) {
      // Nested outer join
      LeftOuterNestedJoin lonj = (LeftOuterNestedJoin) op;
      final boolean aggregate = lonj instanceof LeftOuterNestedJoinWithAggregation;
      final int aggregationCol = aggregate ? ((LeftOuterNestedJoinWithAggregation) lonj)
              .getAggregationColumn() : -1;
      Set<Integer> cols = PushdownUtility.getPredicateColumns(lonj.getPred());
      final int numberColsLeftInput = lonj.getLeft().getNRSMD().getColNo();
      final int nestedColumnPos = aggregate ? lonj.getNRSMD().getColNo() - 2 : lonj.getNRSMD()
              .getColNo() - 1;
      final ProjectColumn nestedColumn = new ProjectColumn(nestedColumnPos);
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos == nestedColumnPos) {
          for (ProjectColumn nested : column.nestedColumns) {
            nestedColumn.nestedColumns.add(nested.copy());
          }
        } else {
          outputColumns.add(column.copy());
        }
      }
      for (ProjectColumn column : columnsRequiredBelow) {
        if (column.pos < numberColsLeftInput) {
          outputColumns.add(column.copy());
        } else if (!cols.contains(column.pos) && aggregate ? column.pos != aggregationCol : true) {
          nestedColumn.nestedColumns.add(column.copy(column.pos - numberColsLeftInput));
        }
      }
      outputColumns.add(nestedColumn);
      if (aggregate) {
        outputColumns.add(new ProjectColumn(lonj.getNRSMD().getColNo() - 1));
      }
    } else if (op instanceof BaseJoinOperator) {
      outputColumns.addAll(columnsRequiredBelow);
    } else if (op instanceof CartesianProduct) {
      outputColumns.addAll(columnsRequiredBelow);
    } else if (op instanceof GroupBy) {
      // Grouping with aggregation
      GroupBy gb = (GroupBy) op;
      final boolean aggregate = gb instanceof GroupByWithAggregation;
      final int nestedColumnPos = aggregate ? gb.getNRSMD().getColNo() - 2 : gb.getNRSMD()
              .getColNo() - 1;
      final ProjectColumn nestedColumn = new ProjectColumn(nestedColumnPos);
      for (ProjectColumn column : columnsRequiredAbove) {
        if (column.pos == nestedColumnPos) {
          for (ProjectColumn nested : column.nestedColumns) {
            nestedColumn.nestedColumns.add(nested.copy());
          }
        } else {
          outputColumns.add(column.copy());
        }
      }
      outputColumns.add(nestedColumn);
      for (int pos : gb.getGroupByColumns()) {
    	outputColumns.add(new ProjectColumn(pos));
      }
      for (int pos : gb.getReduceByColumns()) {
    	outputColumns.add(new ProjectColumn(pos));
      }
      if (aggregate) {
        outputColumns.add(new ProjectColumn(gb.getNRSMD().getColNo() - 1));
      }
    } else if (op instanceof Aggregation) {
      // Aggregation
      Aggregation agg = (Aggregation) op;
      outputColumns.addAll(columnsRequiredBelow);
      if (agg.getAggregationPath().length != 1) {
        outputColumns.add(new ProjectColumn(agg.getNRSMD().getColNo() - 1));
      }
    } else if (op instanceof Selection || op instanceof DuplicateElimination) {
      // Selection
      outputColumns.addAll(columnsRequiredBelow);
    } else if (op instanceof Navigation) {
      // Navigation
      Navigation pnop = (Navigation) op;
      outputColumns.addAll(getRequiredInputColumns(pnop, columnsRequiredAbove));
      outputColumns.addAll(generateColumnSet(op.getNRSMD(),pnop.getChild().getNRSMD().getColNo()));
    } else if (op instanceof XMLScan) {
      // XMLScan
      outputColumns.addAll(generateFullColumnSet(op.getNRSMD()));
    }

    return outputColumns;
  }

  private static ColumnsMapping obtainMapping(Set<ProjectColumn> requiredFromBelow) {
    ColumnsMapping resultMapping = new ColumnsMapping();
    int here = 0;
    Iterator<ProjectColumn> it = requiredFromBelow.iterator();
    while (it.hasNext()) {
      ProjectColumn column = it.next();
      resultMapping.mappingColumns.put(column.pos, here++);
      if (!column.nestedColumns.isEmpty()) {
        ColumnsMapping nestedMapping = obtainMapping(column.nestedColumns);
        resultMapping.nestedMappingColumns.put(column.pos, nestedMapping);
      }
    }
    return resultMapping;
  }

  private static void updateOperatorColumns(BaseLogicalOperator op, ColumnsMapping updatedColumns) {
    final Map<Integer, Integer> mappingColumns = updatedColumns.mappingColumns;

    if (op instanceof XMLConstruct) {
      XMLConstruct xmlConstruct = (XMLConstruct) op;
      updateDataStructure(xmlConstruct.getApply(), xmlConstruct.getNRSMD(), updatedColumns);
    } else if (op instanceof XMLTreeConstruct) {
      XMLTreeConstruct xmlConstruct = (XMLTreeConstruct) op;
      updateDataStructure(xmlConstruct.getConstructionTreePattern(), updatedColumns);
    } else if (op instanceof LeftOuterNestedJoin) {
      // Nested outer join
      LeftOuterNestedJoin lonj = (LeftOuterNestedJoin) op;
      BasePredicate newPred = PushdownUtility.updatePredicate(lonj.getPred(), mappingColumns);
      lonj.setPred(newPred);
      if (lonj.getDocumentIDColumn() != -1) {
        lonj.setDocumentIDColumn(mappingColumns.get(lonj.getDocumentIDColumn()));
      }
      int[] newNodeIDColumns = new int[lonj.getNodeIDColumns().length];
      for (int i = 0; i < lonj.getNodeIDColumns().length; i++) {
        newNodeIDColumns[i] = mappingColumns.get(lonj.getNodeIDColumns()[i]);
      }
      lonj.setNodeIDColumns(newNodeIDColumns);
      if (op instanceof LeftOuterNestedJoinWithAggregation) {
        LeftOuterNestedJoinWithAggregation lonja = (LeftOuterNestedJoinWithAggregation) op;
        lonja.setAggregationColumn(mappingColumns.get(lonja.getAggregationColumn()));
      }
    } else if (op instanceof BaseJoinOperator) {
      // Any other kind of join
      BaseJoinOperator join = (BaseJoinOperator) op;
      BasePredicate newPred = PushdownUtility.updatePredicate(join.getPred(), mappingColumns);
      join.setPred(newPred);
    } else if (op instanceof Aggregation) {
      // Aggregation
      Aggregation agg = (Aggregation) op;
      agg.getAggregationPath()[0] = mappingColumns.get(agg.getAggregationPath()[0]);
    } else if (op instanceof GroupBy) {
      // Grouping
      GroupBy gb = (GroupBy) op;
      final int[] newGroupByColumns = new int[gb.getGroupByColumns().length];
      for (int i = 0; i < gb.getGroupByColumns().length; i++) {
        newGroupByColumns[i] = mappingColumns.get(gb.getGroupByColumns()[i]);
      }
      gb.setGroupByColumns(newGroupByColumns);
      final List<Integer> newNestColumns = Lists.newArrayList();
      for (int i = 0; i < gb.getNestColumns().length; i++) {
        if (mappingColumns.containsKey(gb.getNestColumns()[i])) {
          newNestColumns.add(mappingColumns.get(gb.getNestColumns()[i]));
        }
      }
      final int[] newNestColumnsArray = new int[newNestColumns.size()];
      for (int i = 0; i < newNestColumns.size(); i++) {
    	  newNestColumnsArray[i] = newNestColumns.get(i);
      }
      gb.setNestColumns(newNestColumnsArray);
      final int[] newReduceByColumns = new int[gb.getReduceByColumns().length];
      for (int i = 0; i < gb.getReduceByColumns().length; i++) {
        newReduceByColumns[i] = mappingColumns.get(gb.getReduceByColumns()[i]);
      }
      gb.setReduceByColumns(newReduceByColumns);
      if (gb instanceof GroupByWithAggregation) {
        GroupByWithAggregation gba = (GroupByWithAggregation) gb;
        gba.setAggregationColumn(mappingColumns.get(gba.getAggregationColumn()));
      }
    } else if (op instanceof Selection) {
      // Selection
      Selection select = (Selection) op;
      BasePredicate newPred = PushdownUtility.updatePredicate(select.getPred(), mappingColumns);
      select.setPred(newPred);
    } else if (op instanceof DuplicateElimination) {
      // Selection
      DuplicateElimination duplicateElimination = (DuplicateElimination) op;
      int[] newColumns = new int[duplicateElimination.getColumns().length];
      for (int i = 0; i < duplicateElimination.getColumns().length; i++) {
        newColumns[i] = mappingColumns.get(duplicateElimination.getColumns()[i]);
      }
      duplicateElimination.setColumns(newColumns);
    } else if (op instanceof Navigation) {
      // Navigation
      Navigation pnop = (Navigation) op;
      // We add the column that we need for the navigation
      pnop.pos = mappingColumns.get(pnop.pos);
    }
  }

  private static void updateDataStructure(ApplyConstruct apply, NestedMetadata signature,
          ColumnsMapping updatedColumns) {
    final Map<Integer, Integer> mappingColumns = updatedColumns.mappingColumns;
    final Map<Integer, ColumnsMapping> nestedMappingColumns = updatedColumns.nestedMappingColumns;

    int[] newFields = new int[apply.getFields().length];
    int i = 0;
    int posNested = 0;
    for (int field : apply.getFields()) {
      newFields[i++] = mappingColumns.get(field);
      if (signature.getType(field).equals(MetadataTypes.TUPLE_TYPE)) {
        updateDataStructure(apply.getNested()[posNested++], signature.getNestedChild(field),
                nestedMappingColumns.get(field));
      }
    }
    apply.setFields(newFields);
  }

  private static void updateDataStructure(ConstructionTreePattern ctp, ColumnsMapping updatedColumns) {
    updateDataStructure(ctp.getRoot(), updatedColumns, 0);
  }

  private static void updateDataStructure(ConstructionTreePatternNode node, ColumnsMapping updatedColumns,
          int pos) {
    final Map<Integer, Integer> mappingColumns = updatedColumns.mappingColumns;
    final Map<Integer, ColumnsMapping> nestedMappingColumns = updatedColumns.nestedMappingColumns;

    if (node.getContentType() == ContentType.VARIABLE_PATH) {
      int field = node.getVarPath().get(pos);
      node.getVarPath().set(pos, mappingColumns.get(field));
      if (pos + 1 < node.getVarPath().size()) {
        updateDataStructure(node, nestedMappingColumns.get(field), pos + 1);
      } else {
        for (ConstructionTreePatternNode child : node.getChildren()) {
          updateDataStructure(child, nestedMappingColumns.get(field), 0);
        }
      }
    } else {
      for (ConstructionTreePatternNode child : node.getChildren()) {
        updateDataStructure(child, updatedColumns, 0);
      }
    }
  }

  private static Set<ProjectColumn> generateFullColumnSet(NestedMetadata signature) {
    return generateColumnSet(signature, 0);
  }

  private static Set<ProjectColumn> generateColumnSet(NestedMetadata signature, int initialPos) {
    Set<ProjectColumn> columns = Sets.newTreeSet();
    for (int i = initialPos; i < signature.getColNo(); i++) {
      final ProjectColumn column;
      if (signature.getType(i).equals(MetadataTypes.TUPLE_TYPE)) {
        Set<ProjectColumn> nestedColumns = generateFullColumnSet(signature.getNestedChild(i));
        column = new ProjectColumn(i, nestedColumns);
      } else {
        column = new ProjectColumn(i);
      }
      columns.add(column);
    }
    return columns;
  }

  private static Set<Integer> getTopLevelSet(Set<ProjectColumn> columns) {
    Set<Integer> topLevelColumns = Sets.newTreeSet();
    for (ProjectColumn column : columns) {
      topLevelColumns.add(column.pos);
    }
    return topLevelColumns;
  }

}
