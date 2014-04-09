// Generated from XQuery.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryParser}.
 */
public interface XQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XQueryParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr2(@NotNull XQueryParser.EqualityExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#equalityExpr2}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr2(@NotNull XQueryParser.EqualityExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#orExpr_xq}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr_xq(@NotNull XQueryParser.OrExpr_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#orExpr_xq}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr_xq(@NotNull XQueryParser.OrExpr_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#forBinding}.
	 * @param ctx the parse tree
	 */
	void enterForBinding(@NotNull XQueryParser.ForBindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forBinding}.
	 * @param ctx the parse tree
	 */
	void exitForBinding(@NotNull XQueryParser.ForBindingContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(@NotNull XQueryParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(@NotNull XQueryParser.AdditiveExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(@NotNull XQueryParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#arithExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(@NotNull XQueryParser.ArithExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pathExpr_xq}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr_xq(@NotNull XQueryParser.PathExpr_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pathExpr_xq}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr_xq(@NotNull XQueryParser.PathExpr_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#empty}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(@NotNull XQueryParser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#empty}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(@NotNull XQueryParser.EmptyContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull XQueryParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull XQueryParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#eaName}.
	 * @param ctx the parse tree
	 */
	void enterEaName(@NotNull XQueryParser.EaNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#eaName}.
	 * @param ctx the parse tree
	 */
	void exitEaName(@NotNull XQueryParser.EaNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void enterPredicateList(@NotNull XQueryParser.PredicateListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#predicateList}.
	 * @param ctx the parse tree
	 */
	void exitPredicateList(@NotNull XQueryParser.PredicateListContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pred}.
	 * @param ctx the parse tree
	 */
	void enterPred(@NotNull XQueryParser.PredContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pred}.
	 * @param ctx the parse tree
	 */
	void exitPred(@NotNull XQueryParser.PredContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(@NotNull XQueryParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#parenthesizedExpr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(@NotNull XQueryParser.ParenthesizedExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(@NotNull XQueryParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(@NotNull XQueryParser.ReturnStatContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#boolExprInner_xq}.
	 * @param ctx the parse tree
	 */
	void enterBoolExprInner_xq(@NotNull XQueryParser.BoolExprInner_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#boolExprInner_xq}.
	 * @param ctx the parse tree
	 */
	void exitBoolExprInner_xq(@NotNull XQueryParser.BoolExprInner_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(@NotNull XQueryParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(@NotNull XQueryParser.LetContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(@NotNull XQueryParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(@NotNull XQueryParser.NumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#nameTEst_mod}.
	 * @param ctx the parse tree
	 */
	void enterNameTEst_mod(@NotNull XQueryParser.NameTEst_modContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#nameTEst_mod}.
	 * @param ctx the parse tree
	 */
	void exitNameTEst_mod(@NotNull XQueryParser.NameTEst_modContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#att}.
	 * @param ctx the parse tree
	 */
	void enterAtt(@NotNull XQueryParser.AttContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#att}.
	 * @param ctx the parse tree
	 */
	void exitAtt(@NotNull XQueryParser.AttContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#letBinding}.
	 * @param ctx the parse tree
	 */
	void enterLetBinding(@NotNull XQueryParser.LetBindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#letBinding}.
	 * @param ctx the parse tree
	 */
	void exitLetBinding(@NotNull XQueryParser.LetBindingContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#vcmp}.
	 * @param ctx the parse tree
	 */
	void enterVcmp(@NotNull XQueryParser.VcmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#vcmp}.
	 * @param ctx the parse tree
	 */
	void exitVcmp(@NotNull XQueryParser.VcmpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(@NotNull XQueryParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(@NotNull XQueryParser.OrExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#ncmp}.
	 * @param ctx the parse tree
	 */
	void enterNcmp(@NotNull XQueryParser.NcmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#ncmp}.
	 * @param ctx the parse tree
	 */
	void exitNcmp(@NotNull XQueryParser.NcmpContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr2(@NotNull XQueryParser.AdditiveExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#additiveExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr2(@NotNull XQueryParser.AdditiveExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(@NotNull XQueryParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(@NotNull XQueryParser.RelationalExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void enterKindTest(@NotNull XQueryParser.KindTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#kindTest}.
	 * @param ctx the parse tree
	 */
	void exitKindTest(@NotNull XQueryParser.KindTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#forStat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(@NotNull XQueryParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forStat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(@NotNull XQueryParser.ForStatContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr2(@NotNull XQueryParser.AndExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr2(@NotNull XQueryParser.AndExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#contains}.
	 * @param ctx the parse tree
	 */
	void enterContains(@NotNull XQueryParser.ContainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#contains}.
	 * @param ctx the parse tree
	 */
	void exitContains(@NotNull XQueryParser.ContainsContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pathExprInner_xq_doc}.
	 * @param ctx the parse tree
	 */
	void enterPathExprInner_xq_doc(@NotNull XQueryParser.PathExprInner_xq_docContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pathExprInner_xq_doc}.
	 * @param ctx the parse tree
	 */
	void exitPathExprInner_xq_doc(@NotNull XQueryParser.PathExprInner_xq_docContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pathExpr}.
	 * @param ctx the parse tree
	 */
	void enterPathExpr(@NotNull XQueryParser.PathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pathExpr}.
	 * @param ctx the parse tree
	 */
	void exitPathExpr(@NotNull XQueryParser.PathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(@NotNull XQueryParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(@NotNull XQueryParser.FunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#aggrExpr}.
	 * @param ctx the parse tree
	 */
	void enterAggrExpr(@NotNull XQueryParser.AggrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#aggrExpr}.
	 * @param ctx the parse tree
	 */
	void exitAggrExpr(@NotNull XQueryParser.AggrExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void enterForwardStep(@NotNull XQueryParser.ForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forwardStep}.
	 * @param ctx the parse tree
	 */
	void exitForwardStep(@NotNull XQueryParser.ForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(@NotNull XQueryParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(@NotNull XQueryParser.EqualityExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#qName}.
	 * @param ctx the parse tree
	 */
	void enterQName(@NotNull XQueryParser.QNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#qName}.
	 * @param ctx the parse tree
	 */
	void exitQName(@NotNull XQueryParser.QNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pathExprInner_xq_VAR}.
	 * @param ctx the parse tree
	 */
	void enterPathExprInner_xq_VAR(@NotNull XQueryParser.PathExprInner_xq_VARContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pathExprInner_xq_VAR}.
	 * @param ctx the parse tree
	 */
	void exitPathExprInner_xq_VAR(@NotNull XQueryParser.PathExprInner_xq_VARContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void enterNodeTest(@NotNull XQueryParser.NodeTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void exitNodeTest(@NotNull XQueryParser.NodeTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#nameTest_qName}.
	 * @param ctx the parse tree
	 */
	void enterNameTest_qName(@NotNull XQueryParser.NameTest_qNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#nameTest_qName}.
	 * @param ctx the parse tree
	 */
	void exitNameTest_qName(@NotNull XQueryParser.NameTest_qNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#andExpr_xq}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr_xq(@NotNull XQueryParser.AndExpr_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#andExpr_xq}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr_xq(@NotNull XQueryParser.AndExpr_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#flwrexpr}.
	 * @param ctx the parse tree
	 */
	void enterFlwrexpr(@NotNull XQueryParser.FlwrexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#flwrexpr}.
	 * @param ctx the parse tree
	 */
	void exitFlwrexpr(@NotNull XQueryParser.FlwrexprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#nameTest_div}.
	 * @param ctx the parse tree
	 */
	void enterNameTest_div(@NotNull XQueryParser.NameTest_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#nameTest_div}.
	 * @param ctx the parse tree
	 */
	void exitNameTest_div(@NotNull XQueryParser.NameTest_divContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void enterGroupBy(@NotNull XQueryParser.GroupByContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void exitGroupBy(@NotNull XQueryParser.GroupByContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull XQueryParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull XQueryParser.LiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#eleConst}.
	 * @param ctx the parse tree
	 */
	void enterEleConst(@NotNull XQueryParser.EleConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#eleConst}.
	 * @param ctx the parse tree
	 */
	void exitEleConst(@NotNull XQueryParser.EleConstContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void enterAbbrevForwardStep(@NotNull XQueryParser.AbbrevForwardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#abbrevForwardStep}.
	 * @param ctx the parse tree
	 */
	void exitAbbrevForwardStep(@NotNull XQueryParser.AbbrevForwardStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 */
	void enterXpath(@NotNull XQueryParser.XpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 */
	void exitXpath(@NotNull XQueryParser.XpathContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(@NotNull XQueryParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(@NotNull XQueryParser.UnaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void enterValueExpr(@NotNull XQueryParser.ValueExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#valueExpr}.
	 * @param ctx the parse tree
	 */
	void exitValueExpr(@NotNull XQueryParser.ValueExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(@NotNull XQueryParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(@NotNull XQueryParser.PrimaryExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(@NotNull XQueryParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(@NotNull XQueryParser.WhereContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull XQueryParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull XQueryParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr2(@NotNull XQueryParser.OrExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#orExpr2}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr2(@NotNull XQueryParser.OrExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#middle}.
	 * @param ctx the parse tree
	 */
	void enterMiddle(@NotNull XQueryParser.MiddleContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#middle}.
	 * @param ctx the parse tree
	 */
	void exitMiddle(@NotNull XQueryParser.MiddleContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr2(@NotNull XQueryParser.MultiplicativeExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#multiplicativeExpr2}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr2(@NotNull XQueryParser.MultiplicativeExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXquery(@NotNull XQueryParser.XqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXquery(@NotNull XQueryParser.XqueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#initial}.
	 * @param ctx the parse tree
	 */
	void enterInitial(@NotNull XQueryParser.InitialContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#initial}.
	 * @param ctx the parse tree
	 */
	void exitInitial(@NotNull XQueryParser.InitialContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(@NotNull XQueryParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(@NotNull XQueryParser.FunctionNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#eleConstInner}.
	 * @param ctx the parse tree
	 */
	void enterEleConstInner(@NotNull XQueryParser.EleConstInnerContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#eleConstInner}.
	 * @param ctx the parse tree
	 */
	void exitEleConstInner(@NotNull XQueryParser.EleConstInnerContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(@NotNull XQueryParser.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(@NotNull XQueryParser.FilterExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnionExpr(@NotNull XQueryParser.UnionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#unionExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnionExpr(@NotNull XQueryParser.UnionExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void enterAxisStep(@NotNull XQueryParser.AxisStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#axisStep}.
	 * @param ctx the parse tree
	 */
	void exitAxisStep(@NotNull XQueryParser.AxisStepContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelativePathExpr(@NotNull XQueryParser.RelativePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#relativePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelativePathExpr(@NotNull XQueryParser.RelativePathExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr2(@NotNull XQueryParser.RelationalExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#relationalExpr2}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr2(@NotNull XQueryParser.RelationalExpr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#arithmeticExpr_xq}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpr_xq(@NotNull XQueryParser.ArithmeticExpr_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#arithmeticExpr_xq}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpr_xq(@NotNull XQueryParser.ArithmeticExpr_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#textTest}.
	 * @param ctx the parse tree
	 */
	void enterTextTest(@NotNull XQueryParser.TextTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#textTest}.
	 * @param ctx the parse tree
	 */
	void exitTextTest(@NotNull XQueryParser.TextTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#attInner}.
	 * @param ctx the parse tree
	 */
	void enterAttInner(@NotNull XQueryParser.AttInnerContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#attInner}.
	 * @param ctx the parse tree
	 */
	void exitAttInner(@NotNull XQueryParser.AttInnerContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#pathExprInner_xq_collection}.
	 * @param ctx the parse tree
	 */
	void enterPathExprInner_xq_collection(@NotNull XQueryParser.PathExprInner_xq_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#pathExprInner_xq_collection}.
	 * @param ctx the parse tree
	 */
	void exitPathExprInner_xq_collection(@NotNull XQueryParser.PathExprInner_xq_collectionContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#boolExpr_xq}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr_xq(@NotNull XQueryParser.BoolExpr_xqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#boolExpr_xq}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr_xq(@NotNull XQueryParser.BoolExpr_xqContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(@NotNull XQueryParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(@NotNull XQueryParser.MultiplicativeExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(@NotNull XQueryParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(@NotNull XQueryParser.AndExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link XQueryParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void enterStepExpr(@NotNull XQueryParser.StepExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void exitStepExpr(@NotNull XQueryParser.StepExprContext ctx);
}