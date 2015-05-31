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
package fr.inria.oak.paxquery.algebra.test.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface LogicalPlanParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int COMMA = 7;
  /** RegularExpression Id. */
  int LPAREN = 8;
  /** RegularExpression Id. */
  int RPAREN = 9;
  /** RegularExpression Id. */
  int LSBRACKET = 10;
  /** RegularExpression Id. */
  int RSBRACKET = 11;
  /** RegularExpression Id. */
  int PREDCODE = 12;
  /** RegularExpression Id. */
  int ARITHCODE = 13;
  /** RegularExpression Id. */
  int AGGREGATIONTYPE = 14;
  /** RegularExpression Id. */
  int AND = 15;
  /** RegularExpression Id. */
  int OR = 16;
  /** RegularExpression Id. */
  int DOLLAR = 17;
  /** RegularExpression Id. */
  int TREEPATTERNSTRING = 18;
  /** RegularExpression Id. */
  int CARTESIANPRODUCT = 19;
  /** RegularExpression Id. */
  int JOIN = 20;
  /** RegularExpression Id. */
  int LEFTOUTERJOIN = 21;
  /** RegularExpression Id. */
  int LEFTOUTERNESTEDJOIN = 22;
  /** RegularExpression Id. */
  int LEFTOUTERNESTEDJOINWITHAGGREGATION = 23;
  /** RegularExpression Id. */
  int XMLCONSTRUCT = 24;
  /** RegularExpression Id. */
  int NAVIGATION = 25;
  /** RegularExpression Id. */
  int PROJECTION = 26;
  /** RegularExpression Id. */
  int SELECTION = 27;
  /** RegularExpression Id. */
  int FLATTEN = 28;
  /** RegularExpression Id. */
  int GROUPBY = 29;
  /** RegularExpression Id. */
  int GROUPBYWITHAGGREGATION = 30;
  /** RegularExpression Id. */
  int DUPLICATEELIMINATION = 31;
  /** RegularExpression Id. */
  int AGGREGATION = 32;
  /** RegularExpression Id. */
  int XMLSCAN = 33;
  /** RegularExpression Id. */
  int NUMBER = 34;
  /** RegularExpression Id. */
  int NAME = 35;
  /** RegularExpression Id. */
  int STR = 36;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "<token of kind 3>",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "\",\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "<PREDCODE>",
    "<ARITHCODE>",
    "<AGGREGATIONTYPE>",
    "\"&&\"",
    "\"||\"",
    "\"$\"",
    "<TREEPATTERNSTRING>",
    "\"CartesianProduct\"",
    "\"Join\"",
    "\"LeftOuterJoin\"",
    "\"LeftOuterNestedJoin\"",
    "\"LeftOuterNestedJoinWithAggregation\"",
    "\"XMLConstruct\"",
    "\"Navigation\"",
    "\"Projection\"",
    "\"Selection\"",
    "\"Flatten\"",
    "\"GroupBy\"",
    "\"GroupByWithAggregation\"",
    "\"DuplicateElimination\"",
    "\"Aggregation\"",
    "\"XMLScan\"",
    "<NUMBER>",
    "<NAME>",
    "<STR>",
  };

}
