// Generated from XQuery.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XQueryParser#attInner2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttInner2(@NotNull XQueryParser.AttInner2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#equalityExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr2(@NotNull XQueryParser.EqualityExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#orExpr_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr_xq(@NotNull XQueryParser.OrExpr_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#forBinding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForBinding(@NotNull XQueryParser.ForBindingContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#nameTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameTest(@NotNull XQueryParser.NameTestContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(@NotNull XQueryParser.AdditiveExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#arithExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithExpr(@NotNull XQueryParser.ArithExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExpr_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExpr_xq(@NotNull XQueryParser.PathExpr_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#empty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty(@NotNull XQueryParser.EmptyContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(@NotNull XQueryParser.PredicateContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#eaName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEaName(@NotNull XQueryParser.EaNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#predicateList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateList(@NotNull XQueryParser.PredicateListContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPred(@NotNull XQueryParser.PredContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(@NotNull XQueryParser.ParenthesizedExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(@NotNull XQueryParser.ReturnStatContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#boolExprInner_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExprInner_xq(@NotNull XQueryParser.BoolExprInner_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(@NotNull XQueryParser.LetContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExpr_slash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExpr_slash(@NotNull XQueryParser.PathExpr_slashContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#numericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(@NotNull XQueryParser.NumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#att}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtt(@NotNull XQueryParser.AttContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#letBinding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBinding(@NotNull XQueryParser.LetBindingContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#vcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVcmp(@NotNull XQueryParser.VcmpContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(@NotNull XQueryParser.OrExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#ncmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNcmp(@NotNull XQueryParser.NcmpContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#additiveExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr2(@NotNull XQueryParser.AdditiveExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#relationalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(@NotNull XQueryParser.RelationalExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#kindTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKindTest(@NotNull XQueryParser.KindTestContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#forStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(@NotNull XQueryParser.ForStatContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#andExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr2(@NotNull XQueryParser.AndExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#contains}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContains(@NotNull XQueryParser.ContainsContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExprInner_xq_doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExprInner_xq_doc(@NotNull XQueryParser.PathExprInner_xq_docContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(@NotNull XQueryParser.FunctionCallContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#aggrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggrExpr(@NotNull XQueryParser.AggrExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#forwardStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForwardStep(@NotNull XQueryParser.ForwardStepContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#equalityExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(@NotNull XQueryParser.EqualityExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#qName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQName(@NotNull XQueryParser.QNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExprInner_xq_VAR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExprInner_xq_VAR(@NotNull XQueryParser.PathExprInner_xq_VARContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#nodeTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeTest(@NotNull XQueryParser.NodeTestContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#andExpr_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr_xq(@NotNull XQueryParser.AndExpr_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExpr_slashslash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExpr_slashslash(@NotNull XQueryParser.PathExpr_slashslashContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#flwrexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlwrexpr(@NotNull XQueryParser.FlwrexprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#groupBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupBy(@NotNull XQueryParser.GroupByContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull XQueryParser.LiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#eleConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEleConst(@NotNull XQueryParser.EleConstContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbbrevForwardStep(@NotNull XQueryParser.AbbrevForwardStepContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXpath(@NotNull XQueryParser.XpathContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(@NotNull XQueryParser.UnaryExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#valueExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExpr(@NotNull XQueryParser.ValueExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(@NotNull XQueryParser.PrimaryExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#relativePathExpr2_slash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelativePathExpr2_slash(@NotNull XQueryParser.RelativePathExpr2_slashContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(@NotNull XQueryParser.WhereContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull XQueryParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#orExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr2(@NotNull XQueryParser.OrExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#middle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiddle(@NotNull XQueryParser.MiddleContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr2(@NotNull XQueryParser.MultiplicativeExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXquery(@NotNull XQueryParser.XqueryContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#initial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitial(@NotNull XQueryParser.InitialContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(@NotNull XQueryParser.FunctionNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#eleConstInner}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEleConstInner(@NotNull XQueryParser.EleConstInnerContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#filterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpr(@NotNull XQueryParser.FilterExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#unionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionExpr(@NotNull XQueryParser.UnionExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#axisStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisStep(@NotNull XQueryParser.AxisStepContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#relativePathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelativePathExpr(@NotNull XQueryParser.RelativePathExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#relationalExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr2(@NotNull XQueryParser.RelationalExpr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#arithmeticExpr_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpr_xq(@NotNull XQueryParser.ArithmeticExpr_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#textTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextTest(@NotNull XQueryParser.TextTestContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#attInner}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttInner(@NotNull XQueryParser.AttInnerContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExpr_relativePathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExpr_relativePathExpr(@NotNull XQueryParser.PathExpr_relativePathExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#pathExprInner_xq_collection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExprInner_xq_collection(@NotNull XQueryParser.PathExprInner_xq_collectionContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#boolExpr_xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr_xq(@NotNull XQueryParser.BoolExpr_xqContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(@NotNull XQueryParser.MultiplicativeExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(@NotNull XQueryParser.AndExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#relativePathExpr2_slashslash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelativePathExpr2_slashslash(@NotNull XQueryParser.RelativePathExpr2_slashslashContext ctx);

	/**
	 * Visit a parse tree produced by {@link XQueryParser#stepExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepExpr(@NotNull XQueryParser.StepExprContext ctx);
}