// Generated from XParser.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XParserParser}.
 */
public interface XParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XParserParser#qName}.
	 * @param ctx the parse tree
	 */
	void enterQName(@NotNull XParserParser.QNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#qName}.
	 * @param ctx the parse tree
	 */
	void exitQName(@NotNull XParserParser.QNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr2(@NotNull XParserParser.EqualityExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr2(@NotNull XParserParser.EqualityExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void enterNodeTest(@NotNull XParserParser.NodeTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void exitNodeTest(@NotNull XParserParser.NodeTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void enterNameTest(@NotNull XParserParser.NameTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void exitNameTest(@NotNull XParserParser.NameTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(@NotNull XParserParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(@NotNull XParserParser.AdditiveExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull XParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull XParserParser.LiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void enterAbbrevForwardStep(@NotNull XParserParser.AbbrevForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void exitAbbrevForwardStep(@NotNull XParserParser.AbbrevForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull XParserParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull XParserParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(@NotNull XParserParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(@NotNull XParserParser.UnaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void enterValueExpr(@NotNull XParserParser.ValueExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void exitValueExpr(@NotNull XParserParser.ValueExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void enterPredicateList(@NotNull XParserParser.PredicateListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void exitPredicateList(@NotNull XParserParser.PredicateListContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(@NotNull XParserParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(@NotNull XParserParser.PrimaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(@NotNull XParserParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(@NotNull XParserParser.ParenthesizedExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull XParserParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull XParserParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr2(@NotNull XParserParser.OrExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr2(@NotNull XParserParser.OrExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(@NotNull XParserParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(@NotNull XParserParser.NumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr2(@NotNull XParserParser.MultiplicativeExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr2(@NotNull XParserParser.MultiplicativeExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(@NotNull XParserParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(@NotNull XParserParser.FunctionNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull XParserParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull XParserParser.StartContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(@NotNull XParserParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(@NotNull XParserParser.OrExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(@NotNull XParserParser.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(@NotNull XParserParser.FilterExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr2(@NotNull XParserParser.AdditiveExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr2(@NotNull XParserParser.AdditiveExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnionExpr(@NotNull XParserParser.UnionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnionExpr(@NotNull XParserParser.UnionExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void enterAxisStep(@NotNull XParserParser.AxisStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void exitAxisStep(@NotNull XParserParser.AxisStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(@NotNull XParserParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(@NotNull XParserParser.RelationalExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void enterKindTest(@NotNull XParserParser.KindTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void exitKindTest(@NotNull XParserParser.KindTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr2(@NotNull XParserParser.AndExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr2(@NotNull XParserParser.AndExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelativePathExpr(@NotNull XParserParser.RelativePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelativePathExpr(@NotNull XParserParser.RelativePathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr2(@NotNull XParserParser.RelationalExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr2(@NotNull XParserParser.RelationalExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#textTest}.
	 * @param ctx the parse tree
	 */
	void enterTextTest(@NotNull XParserParser.TextTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#textTest}.
	 * @param ctx the parse tree
	 */
	void exitTextTest(@NotNull XParserParser.TextTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#pathExpr}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr(@NotNull XParserParser.PathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#pathExpr}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr(@NotNull XParserParser.PathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(@NotNull XParserParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(@NotNull XParserParser.FunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void enterForwardStep(@NotNull XParserParser.ForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void exitForwardStep(@NotNull XParserParser.ForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(@NotNull XParserParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(@NotNull XParserParser.MultiplicativeExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(@NotNull XParserParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(@NotNull XParserParser.EqualityExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(@NotNull XParserParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(@NotNull XParserParser.AndExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XParserParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void enterStepExpr(@NotNull XParserParser.StepExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XParserParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void exitStepExpr(@NotNull XParserParser.StepExprContext ctx);
}