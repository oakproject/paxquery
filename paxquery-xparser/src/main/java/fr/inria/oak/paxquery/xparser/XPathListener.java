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
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XPathParser#predicate_xp}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_xp(@NotNull XPathParser.Predicate_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#predicate_xp}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_xp(@NotNull XPathParser.Predicate_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#qName}.
	 * @param ctx the parse tree
	 */
	void enterQName(@NotNull XPathParser.QNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#qName}.
	 * @param ctx the parse tree
	 */
	void exitQName(@NotNull XPathParser.QNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#predicateList_xp}.
	 * @param ctx the parse tree
	 */
	void enterPredicateList_xp(@NotNull XPathParser.PredicateList_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#predicateList_xp}.
	 * @param ctx the parse tree
	 */
	void exitPredicateList_xp(@NotNull XPathParser.PredicateList_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void enterNodeTest(@NotNull XPathParser.NodeTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void exitNodeTest(@NotNull XPathParser.NodeTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#pathExpr_slashslash}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr_slashslash(@NotNull XPathParser.PathExpr_slashslashContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#pathExpr_slashslash}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr_slashslash(@NotNull XPathParser.PathExpr_slashslashContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void enterNameTest(@NotNull XPathParser.NameTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void exitNameTest(@NotNull XPathParser.NameTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#andExpr_xp}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr_xp(@NotNull XPathParser.AndExpr_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#andExpr_xp}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr_xp(@NotNull XPathParser.AndExpr_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#comparativeExpr_xp}.
	 * @param ctx the parse tree
	 */
	void enterComparativeExpr_xp(@NotNull XPathParser.ComparativeExpr_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#comparativeExpr_xp}.
	 * @param ctx the parse tree
	 */
	void exitComparativeExpr_xp(@NotNull XPathParser.ComparativeExpr_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull XPathParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull XPathParser.LiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void enterAbbrevForwardStep(@NotNull XPathParser.AbbrevForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void exitAbbrevForwardStep(@NotNull XPathParser.AbbrevForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#xpath}.
	 * @param ctx the parse tree
	 */
	void enterXpath(@NotNull XPathParser.XpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#xpath}.
	 * @param ctx the parse tree
	 */
	void exitXpath(@NotNull XPathParser.XpathContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(@NotNull XPathParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(@NotNull XPathParser.UnaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void enterValueExpr(@NotNull XPathParser.ValueExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void exitValueExpr(@NotNull XPathParser.ValueExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(@NotNull XPathParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(@NotNull XPathParser.PrimaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(@NotNull XPathParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(@NotNull XPathParser.ParenthesizedExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#relativePathExpr2_slash}.
	 * @param ctx the parse tree
	 */
	void enterRelativePathExpr2_slash(@NotNull XPathParser.RelativePathExpr2_slashContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#relativePathExpr2_slash}.
	 * @param ctx the parse tree
	 */
	void exitRelativePathExpr2_slash(@NotNull XPathParser.RelativePathExpr2_slashContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#pathExpr_slash}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr_slash(@NotNull XPathParser.PathExpr_slashContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#pathExpr_slash}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr_slash(@NotNull XPathParser.PathExpr_slashContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(@NotNull XPathParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(@NotNull XPathParser.NumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(@NotNull XPathParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(@NotNull XPathParser.FunctionNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(@NotNull XPathParser.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(@NotNull XPathParser.FilterExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void enterAxisStep(@NotNull XPathParser.AxisStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void exitAxisStep(@NotNull XPathParser.AxisStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void enterKindTest(@NotNull XPathParser.KindTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void exitKindTest(@NotNull XPathParser.KindTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelativePathExpr(@NotNull XPathParser.RelativePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelativePathExpr(@NotNull XPathParser.RelativePathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#arithmeticExpr_xp}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpr_xp(@NotNull XPathParser.ArithmeticExpr_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#arithmeticExpr_xp}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpr_xp(@NotNull XPathParser.ArithmeticExpr_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#textTest}.
	 * @param ctx the parse tree
	 */
	void enterTextTest(@NotNull XPathParser.TextTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#textTest}.
	 * @param ctx the parse tree
	 */
	void exitTextTest(@NotNull XPathParser.TextTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(@NotNull XPathParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(@NotNull XPathParser.FunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#expr_xp}.
	 * @param ctx the parse tree
	 */
	void enterExpr_xp(@NotNull XPathParser.Expr_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#expr_xp}.
	 * @param ctx the parse tree
	 */
	void exitExpr_xp(@NotNull XPathParser.Expr_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#orExpr_xp}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr_xp(@NotNull XPathParser.OrExpr_xpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#orExpr_xp}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr_xp(@NotNull XPathParser.OrExpr_xpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#pathExpr_relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr_relativePathExpr(@NotNull XPathParser.PathExpr_relativePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#pathExpr_relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr_relativePathExpr(@NotNull XPathParser.PathExpr_relativePathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void enterForwardStep(@NotNull XPathParser.ForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void exitForwardStep(@NotNull XPathParser.ForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#relativePathExpr2_slashslash}.
	 * @param ctx the parse tree
	 */
	void enterRelativePathExpr2_slashslash(@NotNull XPathParser.RelativePathExpr2_slashslashContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#relativePathExpr2_slashslash}.
	 * @param ctx the parse tree
	 */
	void exitRelativePathExpr2_slashslash(@NotNull XPathParser.RelativePathExpr2_slashslashContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void enterStepExpr(@NotNull XPathParser.StepExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void exitStepExpr(@NotNull XPathParser.StepExprContext ctx);
}