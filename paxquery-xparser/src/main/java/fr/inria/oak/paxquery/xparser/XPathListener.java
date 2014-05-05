// Generated from XPath.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link XPathParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr2(@NotNull XPathParser.EqualityExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr2(@NotNull XPathParser.EqualityExpr2Context ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(@NotNull XPathParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(@NotNull XPathParser.AdditiveExprContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull XPathParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull XPathParser.PredicateContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void enterPredicateList(@NotNull XPathParser.PredicateListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void exitPredicateList(@NotNull XPathParser.PredicateListContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull XPathParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull XPathParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr2(@NotNull XPathParser.OrExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr2(@NotNull XPathParser.OrExpr2Context ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr2(@NotNull XPathParser.MultiplicativeExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr2(@NotNull XPathParser.MultiplicativeExpr2Context ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(@NotNull XPathParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(@NotNull XPathParser.OrExprContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr2(@NotNull XPathParser.AdditiveExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr2(@NotNull XPathParser.AdditiveExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnionExpr(@NotNull XPathParser.UnionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnionExpr(@NotNull XPathParser.UnionExprContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(@NotNull XPathParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(@NotNull XPathParser.RelationalExprContext ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr2(@NotNull XPathParser.AndExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr2(@NotNull XPathParser.AndExpr2Context ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr2(@NotNull XPathParser.RelationalExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr2(@NotNull XPathParser.RelationalExpr2Context ctx);

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
	 * Enter a parse tree produced by {@link XPathParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(@NotNull XPathParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(@NotNull XPathParser.MultiplicativeExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(@NotNull XPathParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(@NotNull XPathParser.EqualityExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XPathParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(@NotNull XPathParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(@NotNull XPathParser.AndExprContext ctx);

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