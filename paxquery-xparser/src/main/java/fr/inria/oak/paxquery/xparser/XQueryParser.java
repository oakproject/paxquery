// Generated from XQuery.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__41=1, T__40=2, T__39=3, T__38=4, T__37=5, T__36=6, T__35=7, T__34=8, 
		T__33=9, T__32=10, T__31=11, T__30=12, T__29=13, T__28=14, T__27=15, T__26=16, 
		T__25=17, T__24=18, T__23=19, T__22=20, T__21=21, T__20=22, T__19=23, 
		T__18=24, T__17=25, T__16=26, T__15=27, T__14=28, T__13=29, T__12=30, 
		T__11=31, T__10=32, T__9=33, T__8=34, T__7=35, T__6=36, T__5=37, T__4=38, 
		T__3=39, T__2=40, T__1=41, T__0=42, OR=43, AND=44, NOT=45, EQ=46, EQ_S=47, 
		NE=48, NE_S=49, LT=50, LT_S=51, LE=52, LE_S=53, GT=54, GT_S=55, GE=56, 
		GE_S=57, VAR=58, LEFTCURL=59, RIGHTCURL=60, OPEN_TAG=61, CLOSE_TAG=62, 
		QNAME_TOKEN=63, STRING_LITERAL=64, REFERENCE=65, ENTITY_REF=66, CHAR_REF=67, 
		INTEGER_LITERAL=68, DECIMAL_LITERAL=69, DIGITS=70, WS=71;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'group by'", "'substring'", "'sum'", "'true'", "'return'", 
		"'>>'", "'<<'", "'max'", "'concat'", "'for'", "'avg'", "':='", "'('", 
		"'is'", "'*'", "'min'", "','", "'false'", "'ceiling'", "'mod'", "'['", 
		"'distinct-values'", "'//'", "'|'", "'collection'", "']'", "'contains'", 
		"'@'", "'text'", "'where'", "'let'", "'count'", "'div'", "'\"'", "'in'", 
		"'floor'", "')'", "'+'", "'doc'", "'-'", "'empty'", "'or'", "'and'", "'not'", 
		"'eq'", "'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", 
		"'>'", "'ge'", "'>='", "VAR", "'{'", "'}'", "OPEN_TAG", "CLOSE_TAG", "QNAME_TOKEN", 
		"STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", 
		"DECIMAL_LITERAL", "DIGITS", "WS"
	};
	public static final int
		RULE_xquery = 0, RULE_flwrexpr = 1, RULE_initial = 2, RULE_middle = 3, 
		RULE_forStat = 4, RULE_forBinding = 5, RULE_let = 6, RULE_letBinding = 7, 
		RULE_arithExpr = 8, RULE_pathExpr_xq = 9, RULE_pathExprInner_xq = 10, 
		RULE_aggrExpr = 11, RULE_where = 12, RULE_orExpr_xq = 13, RULE_andExpr_xq = 14, 
		RULE_boolExpr_xq = 15, RULE_boolExprInner_xq = 16, RULE_pred = 17, RULE_vcmp = 18, 
		RULE_ncmp = 19, RULE_contains = 20, RULE_empty = 21, RULE_groupBy = 22, 
		RULE_returnStat = 23, RULE_eleConst = 24, RULE_eleConstInner = 25, RULE_att = 26, 
		RULE_attInner = 27, RULE_eaName = 28, RULE_arithmeticExpr_xq = 29, RULE_xpath = 30, 
		RULE_expr = 31, RULE_orExpr = 32, RULE_orExpr2 = 33, RULE_andExpr = 34, 
		RULE_andExpr2 = 35, RULE_equalityExpr = 36, RULE_equalityExpr2 = 37, RULE_relationalExpr = 38, 
		RULE_relationalExpr2 = 39, RULE_additiveExpr = 40, RULE_additiveExpr2 = 41, 
		RULE_multiplicativeExpr = 42, RULE_multiplicativeExpr2 = 43, RULE_unaryExpr = 44, 
		RULE_unionExpr = 45, RULE_valueExpr = 46, RULE_pathExpr = 47, RULE_relativePathExpr = 48, 
		RULE_stepExpr = 49, RULE_axisStep = 50, RULE_forwardStep = 51, RULE_abbrevForwardStep = 52, 
		RULE_nodeTest = 53, RULE_kindTest = 54, RULE_nameTest = 55, RULE_filterExpr = 56, 
		RULE_predicateList = 57, RULE_predicate = 58, RULE_primaryExpr = 59, RULE_literal = 60, 
		RULE_numericLiteral = 61, RULE_parenthesizedExpr = 62, RULE_functionCall = 63, 
		RULE_functionName = 64, RULE_textTest = 65, RULE_qName = 66;
	public static final String[] ruleNames = {
		"xquery", "flwrexpr", "initial", "middle", "forStat", "forBinding", "let", 
		"letBinding", "arithExpr", "pathExpr_xq", "pathExprInner_xq", "aggrExpr", 
		"where", "orExpr_xq", "andExpr_xq", "boolExpr_xq", "boolExprInner_xq", 
		"pred", "vcmp", "ncmp", "contains", "empty", "groupBy", "returnStat", 
		"eleConst", "eleConstInner", "att", "attInner", "eaName", "arithmeticExpr_xq", 
		"xpath", "expr", "orExpr", "orExpr2", "andExpr", "andExpr2", "equalityExpr", 
		"equalityExpr2", "relationalExpr", "relationalExpr2", "additiveExpr", 
		"additiveExpr2", "multiplicativeExpr", "multiplicativeExpr2", "unaryExpr", 
		"unionExpr", "valueExpr", "pathExpr", "relativePathExpr", "stepExpr", 
		"axisStep", "forwardStep", "abbrevForwardStep", "nodeTest", "kindTest", 
		"nameTest", "filterExpr", "predicateList", "predicate", "primaryExpr", 
		"literal", "numericLiteral", "parenthesizedExpr", "functionCall", "functionName", 
		"textTest", "qName"
	};

	@Override
	public String getGrammarFileName() { return "XQuery.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class XqueryContext extends ParserRuleContext {
		public FlwrexprContext flwrexpr() {
			return getRuleContext(FlwrexprContext.class,0);
		}
		public XqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXquery(this);
		}
	}

	public final XqueryContext xquery() throws RecognitionException {
		XqueryContext _localctx = new XqueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); flwrexpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlwrexprContext extends ParserRuleContext {
		public List<MiddleContext> middle() {
			return getRuleContexts(MiddleContext.class);
		}
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public MiddleContext middle(int i) {
			return getRuleContext(MiddleContext.class,i);
		}
		public InitialContext initial() {
			return getRuleContext(InitialContext.class,0);
		}
		public FlwrexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flwrexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFlwrexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFlwrexpr(this);
		}
	}

	public final FlwrexprContext flwrexpr() throws RecognitionException {
		FlwrexprContext _localctx = new FlwrexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_flwrexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); initial();
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 11) | (1L << 31) | (1L << 32))) != 0)) {
				{
				{
				setState(137); middle();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(143); returnStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitialContext extends ParserRuleContext {
		public ForStatContext forStat() {
			return getRuleContext(ForStatContext.class,0);
		}
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public InitialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterInitial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitInitial(this);
		}
	}

	public final InitialContext initial() throws RecognitionException {
		InitialContext _localctx = new InitialContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initial);
		try {
			setState(147);
			switch (_input.LA(1)) {
			case 11:
				enterOuterAlt(_localctx, 1);
				{
				setState(145); forStat();
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 2);
				{
				setState(146); let();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MiddleContext extends ParserRuleContext {
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public InitialContext initial() {
			return getRuleContext(InitialContext.class,0);
		}
		public GroupByContext groupBy() {
			return getRuleContext(GroupByContext.class,0);
		}
		public MiddleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_middle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterMiddle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitMiddle(this);
		}
	}

	public final MiddleContext middle() throws RecognitionException {
		MiddleContext _localctx = new MiddleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_middle);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case 11:
			case 32:
				enterOuterAlt(_localctx, 1);
				{
				setState(149); initial();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 2);
				{
				setState(150); where();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(151); groupBy();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatContext extends ParserRuleContext {
		public List<ForBindingContext> forBinding() {
			return getRuleContexts(ForBindingContext.class);
		}
		public ForBindingContext forBinding(int i) {
			return getRuleContext(ForBindingContext.class,i);
		}
		public ForStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterForStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitForStat(this);
		}
	}

	public final ForStatContext forStat() throws RecognitionException {
		ForStatContext _localctx = new ForStatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_forStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(11);
			setState(155); forBinding();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==18) {
				{
				{
				setState(156); match(18);
				setState(157); forBinding();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForBindingContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public PathExpr_xqContext pathExpr_xq() {
			return getRuleContext(PathExpr_xqContext.class,0);
		}
		public ForBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterForBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitForBinding(this);
		}
	}

	public final ForBindingContext forBinding() throws RecognitionException {
		ForBindingContext _localctx = new ForBindingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_forBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); match(VAR);
			setState(164); match(36);
			setState(165); pathExpr_xq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetContext extends ParserRuleContext {
		public List<LetBindingContext> letBinding() {
			return getRuleContexts(LetBindingContext.class);
		}
		public LetBindingContext letBinding(int i) {
			return getRuleContext(LetBindingContext.class,i);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitLet(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_let);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); match(32);
			setState(168); letBinding();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==18) {
				{
				{
				setState(169); match(18);
				setState(170); letBinding();
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetBindingContext extends ParserRuleContext {
		public ArithmeticExpr_xqContext arithmeticExpr_xq() {
			return getRuleContext(ArithmeticExpr_xqContext.class,0);
		}
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public AggrExprContext aggrExpr() {
			return getRuleContext(AggrExprContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public FlwrexprContext flwrexpr() {
			return getRuleContext(FlwrexprContext.class,0);
		}
		public PathExpr_xqContext pathExpr_xq() {
			return getRuleContext(PathExpr_xqContext.class,0);
		}
		public LetBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterLetBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitLetBinding(this);
		}
	}

	public final LetBindingContext letBinding() throws RecognitionException {
		LetBindingContext _localctx = new LetBindingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_letBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(VAR);
			setState(177); match(13);
			setState(183);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(178); pathExpr_xq();
				}
				break;

			case 2:
				{
				setState(179); flwrexpr();
				}
				break;

			case 3:
				{
				setState(180); aggrExpr();
				}
				break;

			case 4:
				{
				setState(181); arithmeticExpr_xq(0);
				}
				break;

			case 5:
				{
				setState(182); literal();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithExprContext extends ParserRuleContext {
		public ArithExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitArithExpr(this);
		}
	}

	public final ArithExprContext arithExpr() throws RecognitionException {
		ArithExprContext _localctx = new ArithExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arithExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathExpr_xqContext extends ParserRuleContext {
		public XpathContext xpath() {
			return getRuleContext(XpathContext.class,0);
		}
		public PathExprInner_xqContext pathExprInner_xq() {
			return getRuleContext(PathExprInner_xqContext.class,0);
		}
		public PathExpr_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExpr_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExpr_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExpr_xq(this);
		}
	}

	public final PathExpr_xqContext pathExpr_xq() throws RecognitionException {
		PathExpr_xqContext _localctx = new PathExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pathExpr_xq);
		int _la;
		try {
			setState(199);
			switch (_input.LA(1)) {
			case 23:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); match(23);
				setState(188); match(14);
				setState(189); pathExprInner_xq();
				setState(191);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << 5) | (1L << 10) | (1L << 14) | (1L << 19) | (1L << 20) | (1L << 21) | (1L << 24) | (1L << 29) | (1L << 30) | (1L << 34) | (1L << 37) | (1L << 41) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(190); xpath();
					}
				}

				setState(193); match(38);
				}
				break;
			case 26:
			case 40:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); pathExprInner_xq();
				setState(197);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << 5) | (1L << 10) | (1L << 14) | (1L << 19) | (1L << 20) | (1L << 21) | (1L << 24) | (1L << 29) | (1L << 30) | (1L << 34) | (1L << 37) | (1L << 41) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(196); xpath();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathExprInner_xqContext extends ParserRuleContext {
		public PathExprInner_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExprInner_xq; }
	 
		public PathExprInner_xqContext() { }
		public void copyFrom(PathExprInner_xqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PathExprInner_xq_docContext extends PathExprInner_xqContext {
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public PathExprInner_xq_docContext(PathExprInner_xqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExprInner_xq_doc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExprInner_xq_doc(this);
		}
	}
	public static class PathExprInner_xq_VARContext extends PathExprInner_xqContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public PathExprInner_xq_VARContext(PathExprInner_xqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExprInner_xq_VAR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExprInner_xq_VAR(this);
		}
	}
	public static class PathExprInner_xq_collectionContext extends PathExprInner_xqContext {
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public PathExprInner_xq_collectionContext(PathExprInner_xqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExprInner_xq_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExprInner_xq_collection(this);
		}
	}

	public final PathExprInner_xqContext pathExprInner_xq() throws RecognitionException {
		PathExprInner_xqContext _localctx = new PathExprInner_xqContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pathExprInner_xq);
		try {
			setState(210);
			switch (_input.LA(1)) {
			case 26:
				_localctx = new PathExprInner_xq_collectionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(201); match(26);
				setState(202); match(14);
				setState(203); match(STRING_LITERAL);
				setState(204); match(38);
				}
				break;
			case 40:
				_localctx = new PathExprInner_xq_docContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(205); match(40);
				setState(206); match(14);
				setState(207); match(STRING_LITERAL);
				setState(208); match(38);
				}
				break;
			case VAR:
				_localctx = new PathExprInner_xq_VARContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(209); match(VAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggrExprContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public AggrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAggrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAggrExpr(this);
		}
	}

	public final AggrExprContext aggrExpr() throws RecognitionException {
		AggrExprContext _localctx = new AggrExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_aggrExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 9) | (1L << 12) | (1L << 17) | (1L << 33))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(213); match(14);
			setState(214); match(VAR);
			setState(215); match(38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereContext extends ParserRuleContext {
		public OrExpr_xqContext orExpr_xq() {
			return getRuleContext(OrExpr_xqContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitWhere(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); match(31);
			setState(218); orExpr_xq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpr_xqContext extends ParserRuleContext {
		public List<AndExpr_xqContext> andExpr_xq() {
			return getRuleContexts(AndExpr_xqContext.class);
		}
		public AndExpr_xqContext andExpr_xq(int i) {
			return getRuleContext(AndExpr_xqContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(XQueryParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XQueryParser.OR, i);
		}
		public OrExpr_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterOrExpr_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitOrExpr_xq(this);
		}
	}

	public final OrExpr_xqContext orExpr_xq() throws RecognitionException {
		OrExpr_xqContext _localctx = new OrExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_orExpr_xq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); andExpr_xq();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(221); match(OR);
				setState(222); andExpr_xq();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpr_xqContext extends ParserRuleContext {
		public TerminalNode AND(int i) {
			return getToken(XQueryParser.AND, i);
		}
		public List<BoolExpr_xqContext> boolExpr_xq() {
			return getRuleContexts(BoolExpr_xqContext.class);
		}
		public BoolExpr_xqContext boolExpr_xq(int i) {
			return getRuleContext(BoolExpr_xqContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(XQueryParser.AND); }
		public AndExpr_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAndExpr_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAndExpr_xq(this);
		}
	}

	public final AndExpr_xqContext andExpr_xq() throws RecognitionException {
		AndExpr_xqContext _localctx = new AndExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_andExpr_xq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); boolExpr_xq();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(229); match(AND);
				setState(230); boolExpr_xq();
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolExpr_xqContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(XQueryParser.NOT, 0); }
		public BoolExprInner_xqContext boolExprInner_xq() {
			return getRuleContext(BoolExprInner_xqContext.class,0);
		}
		public BoolExpr_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterBoolExpr_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitBoolExpr_xq(this);
		}
	}

	public final BoolExpr_xqContext boolExpr_xq() throws RecognitionException {
		BoolExpr_xqContext _localctx = new BoolExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_boolExpr_xq);
		try {
			setState(242);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(236); match(NOT);
				setState(237); match(14);
				setState(238); boolExprInner_xq();
				setState(239); match(38);
				}
				break;
			case 14:
			case 28:
			case 41:
			case 42:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(241); boolExprInner_xq();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolExprInner_xqContext extends ParserRuleContext {
		public PredContext pred() {
			return getRuleContext(PredContext.class,0);
		}
		public EmptyContext empty() {
			return getRuleContext(EmptyContext.class,0);
		}
		public ContainsContext contains() {
			return getRuleContext(ContainsContext.class,0);
		}
		public BoolExprInner_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExprInner_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterBoolExprInner_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitBoolExprInner_xq(this);
		}
	}

	public final BoolExprInner_xqContext boolExprInner_xq() throws RecognitionException {
		BoolExprInner_xqContext _localctx = new BoolExprInner_xqContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolExprInner_xq);
		try {
			setState(247);
			switch (_input.LA(1)) {
			case 14:
			case 41:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(244); pred();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 2);
				{
				setState(245); contains();
				}
				break;
			case 42:
				enterOuterAlt(_localctx, 3);
				{
				setState(246); empty();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredContext extends ParserRuleContext {
		public List<ArithmeticExpr_xqContext> arithmeticExpr_xq() {
			return getRuleContexts(ArithmeticExpr_xqContext.class);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ArithmeticExpr_xqContext arithmeticExpr_xq(int i) {
			return getRuleContext(ArithmeticExpr_xqContext.class,i);
		}
		public VcmpContext vcmp() {
			return getRuleContext(VcmpContext.class,0);
		}
		public NcmpContext ncmp() {
			return getRuleContext(NcmpContext.class,0);
		}
		public PredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPred(this);
		}
	}

	public final PredContext pred() throws RecognitionException {
		PredContext _localctx = new PredContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pred);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); arithmeticExpr_xq(0);
			setState(258);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 8) | (1L << 15) | (1L << EQ) | (1L << EQ_S) | (1L << NE) | (1L << NE_S) | (1L << LT) | (1L << LT_S) | (1L << LE) | (1L << LE_S) | (1L << GT) | (1L << GT_S) | (1L << GE) | (1L << GE_S))) != 0)) {
				{
				setState(252);
				switch (_input.LA(1)) {
				case EQ:
				case EQ_S:
				case NE:
				case NE_S:
				case LT:
				case LT_S:
				case LE:
				case LE_S:
				case GT:
				case GT_S:
				case GE:
				case GE_S:
					{
					setState(250); vcmp();
					}
					break;
				case 7:
				case 8:
				case 15:
					{
					setState(251); ncmp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(256);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(254); arithmeticExpr_xq(0);
					}
					break;

				case 2:
					{
					setState(255); literal();
					}
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VcmpContext extends ParserRuleContext {
		public TerminalNode NE_S() { return getToken(XQueryParser.NE_S, 0); }
		public TerminalNode GE() { return getToken(XQueryParser.GE, 0); }
		public TerminalNode LE_S() { return getToken(XQueryParser.LE_S, 0); }
		public TerminalNode LT() { return getToken(XQueryParser.LT, 0); }
		public TerminalNode GT() { return getToken(XQueryParser.GT, 0); }
		public TerminalNode LE() { return getToken(XQueryParser.LE, 0); }
		public TerminalNode EQ() { return getToken(XQueryParser.EQ, 0); }
		public TerminalNode EQ_S() { return getToken(XQueryParser.EQ_S, 0); }
		public TerminalNode GT_S() { return getToken(XQueryParser.GT_S, 0); }
		public TerminalNode NE() { return getToken(XQueryParser.NE, 0); }
		public TerminalNode GE_S() { return getToken(XQueryParser.GE_S, 0); }
		public TerminalNode LT_S() { return getToken(XQueryParser.LT_S, 0); }
		public VcmpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vcmp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterVcmp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitVcmp(this);
		}
	}

	public final VcmpContext vcmp() throws RecognitionException {
		VcmpContext _localctx = new VcmpContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_vcmp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << EQ_S) | (1L << NE) | (1L << NE_S) | (1L << LT) | (1L << LT_S) | (1L << LE) | (1L << LE_S) | (1L << GT) | (1L << GT_S) | (1L << GE) | (1L << GE_S))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NcmpContext extends ParserRuleContext {
		public NcmpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ncmp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNcmp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNcmp(this);
		}
	}

	public final NcmpContext ncmp() throws RecognitionException {
		NcmpContext _localctx = new NcmpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ncmp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 8) | (1L << 15))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainsContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public ContainsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterContains(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitContains(this);
		}
	}

	public final ContainsContext contains() throws RecognitionException {
		ContainsContext _localctx = new ContainsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_contains);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); match(28);
			setState(265); match(14);
			setState(266); match(VAR);
			setState(267); match(18);
			setState(268); match(STRING_LITERAL);
			setState(269); match(38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public EmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEmpty(this);
		}
	}

	public final EmptyContext empty() throws RecognitionException {
		EmptyContext _localctx = new EmptyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_empty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271); match(42);
			setState(272); match(14);
			setState(273); match(VAR);
			setState(274); match(38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupByContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(XQueryParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(XQueryParser.VAR, i);
		}
		public GroupByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterGroupBy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitGroupBy(this);
		}
	}

	public final GroupByContext groupBy() throws RecognitionException {
		GroupByContext _localctx = new GroupByContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_groupBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(2);
			setState(277); match(VAR);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==18) {
				{
				{
				setState(278); match(18);
				setState(279); match(VAR);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(XQueryParser.VAR); }
		public EleConstContext eleConst() {
			return getRuleContext(EleConstContext.class,0);
		}
		public List<AggrExprContext> aggrExpr() {
			return getRuleContexts(AggrExprContext.class);
		}
		public TerminalNode VAR(int i) {
			return getToken(XQueryParser.VAR, i);
		}
		public AggrExprContext aggrExpr(int i) {
			return getRuleContext(AggrExprContext.class,i);
		}
		public ReturnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitReturnStat(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_returnStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); match(6);
			setState(293);
			switch (_input.LA(1)) {
			case LT_S:
				{
				setState(286); eleConst();
				}
				break;
			case 4:
			case 9:
			case 12:
			case 17:
			case 33:
			case VAR:
				{
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(289);
					switch (_input.LA(1)) {
					case 4:
					case 9:
					case 12:
					case 17:
					case 33:
						{
						setState(287); aggrExpr();
						}
						break;
					case VAR:
						{
						setState(288); match(VAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(291); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 9) | (1L << 12) | (1L << 17) | (1L << 33) | (1L << VAR))) != 0) );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EleConstContext extends ParserRuleContext {
		public List<AttContext> att() {
			return getRuleContexts(AttContext.class);
		}
		public EleConstContext eleConst(int i) {
			return getRuleContext(EleConstContext.class,i);
		}
		public List<EaNameContext> eaName() {
			return getRuleContexts(EaNameContext.class);
		}
		public EleConstInnerContext eleConstInner(int i) {
			return getRuleContext(EleConstInnerContext.class,i);
		}
		public TerminalNode OPEN_TAG() { return getToken(XQueryParser.OPEN_TAG, 0); }
		public TerminalNode CLOSE_TAG() { return getToken(XQueryParser.CLOSE_TAG, 0); }
		public TerminalNode GT_S(int i) {
			return getToken(XQueryParser.GT_S, i);
		}
		public EaNameContext eaName(int i) {
			return getRuleContext(EaNameContext.class,i);
		}
		public TerminalNode RIGHTCURL(int i) {
			return getToken(XQueryParser.RIGHTCURL, i);
		}
		public AttContext att(int i) {
			return getRuleContext(AttContext.class,i);
		}
		public List<EleConstContext> eleConst() {
			return getRuleContexts(EleConstContext.class);
		}
		public List<TerminalNode> LEFTCURL() { return getTokens(XQueryParser.LEFTCURL); }
		public List<TerminalNode> RIGHTCURL() { return getTokens(XQueryParser.RIGHTCURL); }
		public List<TerminalNode> GT_S() { return getTokens(XQueryParser.GT_S); }
		public TerminalNode LEFTCURL(int i) {
			return getToken(XQueryParser.LEFTCURL, i);
		}
		public List<EleConstInnerContext> eleConstInner() {
			return getRuleContexts(EleConstInnerContext.class);
		}
		public TerminalNode LT_S() { return getToken(XQueryParser.LT_S, 0); }
		public EleConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eleConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEleConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEleConst(this);
		}
	}

	public final EleConstContext eleConst() throws RecognitionException {
		EleConstContext _localctx = new EleConstContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_eleConst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295); match(LT_S);
			setState(296); eaName();
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==QNAME_TOKEN) {
				{
				{
				setState(297); att();
				}
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(320);
			switch (_input.LA(1)) {
			case CLOSE_TAG:
				{
				setState(303); match(CLOSE_TAG);
				}
				break;
			case GT_S:
				{
				{
				setState(304); match(GT_S);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 5) | (1L << 10) | (1L << 19) | (1L << 20) | (1L << 30) | (1L << 37) | (1L << NOT) | (1L << LT_S) | (1L << LEFTCURL) | (1L << QNAME_TOKEN))) != 0)) {
					{
					setState(311);
					switch (_input.LA(1)) {
					case LT_S:
						{
						setState(305); eleConst();
						}
						break;
					case LEFTCURL:
						{
						setState(306); match(LEFTCURL);
						setState(307); eleConstInner();
						setState(308); match(RIGHTCURL);
						}
						break;
					case 3:
					case 5:
					case 10:
					case 19:
					case 20:
					case 30:
					case 37:
					case NOT:
					case QNAME_TOKEN:
						{
						setState(310); eaName();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(316); match(OPEN_TAG);
				{
				setState(317); eaName();
				}
				setState(318); match(GT_S);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EleConstInnerContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(XQueryParser.VAR); }
		public List<AggrExprContext> aggrExpr() {
			return getRuleContexts(AggrExprContext.class);
		}
		public TerminalNode VAR(int i) {
			return getToken(XQueryParser.VAR, i);
		}
		public AggrExprContext aggrExpr(int i) {
			return getRuleContext(AggrExprContext.class,i);
		}
		public EleConstInnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eleConstInner; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEleConstInner(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEleConstInner(this);
		}
	}

	public final EleConstInnerContext eleConstInner() throws RecognitionException {
		EleConstInnerContext _localctx = new EleConstInnerContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_eleConstInner);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 9) | (1L << 12) | (1L << 17) | (1L << 33) | (1L << VAR))) != 0)) {
				{
				{
				setState(324);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(322); match(VAR);
					}
					break;
				case 4:
				case 9:
				case 12:
				case 17:
				case 33:
					{
					setState(323); aggrExpr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(327);
				_la = _input.LA(1);
				if (_la==18) {
					{
					setState(326); match(18);
					}
				}

				}
				}
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttContext extends ParserRuleContext {
		public TerminalNode QNAME_TOKEN() { return getToken(XQueryParser.QNAME_TOKEN, 0); }
		public List<AttInnerContext> attInner() {
			return getRuleContexts(AttInnerContext.class);
		}
		public List<EaNameContext> eaName() {
			return getRuleContexts(EaNameContext.class);
		}
		public List<TerminalNode> LEFTCURL() { return getTokens(XQueryParser.LEFTCURL); }
		public List<TerminalNode> RIGHTCURL() { return getTokens(XQueryParser.RIGHTCURL); }
		public AttInnerContext attInner(int i) {
			return getRuleContext(AttInnerContext.class,i);
		}
		public EaNameContext eaName(int i) {
			return getRuleContext(EaNameContext.class,i);
		}
		public TerminalNode LEFTCURL(int i) {
			return getToken(XQueryParser.LEFTCURL, i);
		}
		public TerminalNode RIGHTCURL(int i) {
			return getToken(XQueryParser.RIGHTCURL, i);
		}
		public AttContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_att; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAtt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAtt(this);
		}
	}

	public final AttContext att() throws RecognitionException {
		AttContext _localctx = new AttContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_att);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334); match(QNAME_TOKEN);
			setState(335); match(EQ_S);
			setState(336); match(35);
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 5) | (1L << 10) | (1L << 19) | (1L << 20) | (1L << 30) | (1L << 37) | (1L << NOT) | (1L << LEFTCURL) | (1L << QNAME_TOKEN))) != 0)) {
				{
				setState(342);
				switch (_input.LA(1)) {
				case LEFTCURL:
					{
					setState(337); match(LEFTCURL);
					setState(338); attInner();
					setState(339); match(RIGHTCURL);
					}
					break;
				case 3:
				case 5:
				case 10:
				case 19:
				case 20:
				case 30:
				case 37:
				case NOT:
				case QNAME_TOKEN:
					{
					setState(341); eaName();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(347); match(35);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttInnerContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public AggrExprContext aggrExpr() {
			return getRuleContext(AggrExprContext.class,0);
		}
		public AttInnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attInner; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAttInner(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAttInner(this);
		}
	}

	public final AttInnerContext attInner() throws RecognitionException {
		AttInnerContext _localctx = new AttInnerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attInner);
		try {
			setState(351);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(349); match(VAR);
				}
				break;
			case 4:
			case 9:
			case 12:
			case 17:
			case 33:
				enterOuterAlt(_localctx, 2);
				{
				setState(350); aggrExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EaNameContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode QNAME_TOKEN() { return getToken(XQueryParser.QNAME_TOKEN, 0); }
		public EaNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eaName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEaName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEaName(this);
		}
	}

	public final EaNameContext eaName() throws RecognitionException {
		EaNameContext _localctx = new EaNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_eaName);
		try {
			setState(355);
			switch (_input.LA(1)) {
			case 3:
			case 5:
			case 10:
			case 19:
			case 20:
			case 30:
			case 37:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(353); functionName();
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(354); match(QNAME_TOKEN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticExpr_xqContext extends ParserRuleContext {
		public List<ArithmeticExpr_xqContext> arithmeticExpr_xq() {
			return getRuleContexts(ArithmeticExpr_xqContext.class);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public ArithmeticExpr_xqContext arithmeticExpr_xq(int i) {
			return getRuleContext(ArithmeticExpr_xqContext.class,i);
		}
		public ArithmeticExpr_xqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpr_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterArithmeticExpr_xq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitArithmeticExpr_xq(this);
		}
	}

	public final ArithmeticExpr_xqContext arithmeticExpr_xq() throws RecognitionException {
		return arithmeticExpr_xq(0);
	}

	private ArithmeticExpr_xqContext arithmeticExpr_xq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticExpr_xqContext _localctx = new ArithmeticExpr_xqContext(_ctx, _parentState);
		ArithmeticExpr_xqContext _prevctx = _localctx;
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_arithmeticExpr_xq, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(358); match(14);
				setState(359); arithmeticExpr_xq(0);
				setState(360); match(38);
				}
				break;

			case 2:
				{
				setState(363);
				_la = _input.LA(1);
				if (_la==41) {
					{
					setState(362); match(41);
					}
				}

				setState(365); numericLiteral();
				}
				break;

			case 3:
				{
				setState(367);
				_la = _input.LA(1);
				if (_la==41) {
					{
					setState(366); match(41);
					}
				}

				setState(369); match(VAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(378);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(372);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(373);
						_la = _input.LA(1);
						if ( !(_la==39 || _la==41) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(374); arithmeticExpr_xq(6);
						}
						break;

					case 2:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(375);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(376);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 16) | (1L << 21) | (1L << 34))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(377); arithmeticExpr_xq(5);
						}
						break;
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class XpathContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public XpathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xpath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXpath(this);
		}
	}

	public final XpathContext xpath() throws RecognitionException {
		XpathContext _localctx = new XpathContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_xpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385); orExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExprContext extends ParserRuleContext {
		public OrExpr2Context orExpr2() {
			return getRuleContext(OrExpr2Context.class,0);
		}
		public AndExprContext andExpr() {
			return getRuleContext(AndExprContext.class,0);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_orExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); andExpr();
			setState(388); orExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpr2Context extends ParserRuleContext {
		public OrExpr2Context orExpr2() {
			return getRuleContext(OrExpr2Context.class,0);
		}
		public AndExprContext andExpr() {
			return getRuleContext(AndExprContext.class,0);
		}
		public TerminalNode OR() { return getToken(XQueryParser.OR, 0); }
		public OrExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterOrExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitOrExpr2(this);
		}
	}

	public final OrExpr2Context orExpr2() throws RecognitionException {
		OrExpr2Context _localctx = new OrExpr2Context(_ctx, getState());
		enterRule(_localctx, 66, RULE_orExpr2);
		try {
			setState(395);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(390); match(OR);
				setState(391); andExpr();
				setState(392); orExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public AndExpr2Context andExpr2() {
			return getRuleContext(AndExpr2Context.class,0);
		}
		public EqualityExprContext equalityExpr() {
			return getRuleContext(EqualityExprContext.class,0);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_andExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397); equalityExpr();
			setState(398); andExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpr2Context extends ParserRuleContext {
		public AndExpr2Context andExpr2() {
			return getRuleContext(AndExpr2Context.class,0);
		}
		public EqualityExprContext equalityExpr() {
			return getRuleContext(EqualityExprContext.class,0);
		}
		public TerminalNode AND() { return getToken(XQueryParser.AND, 0); }
		public AndExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAndExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAndExpr2(this);
		}
	}

	public final AndExpr2Context andExpr2() throws RecognitionException {
		AndExpr2Context _localctx = new AndExpr2Context(_ctx, getState());
		enterRule(_localctx, 70, RULE_andExpr2);
		try {
			setState(405);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(400); match(AND);
				setState(401); equalityExpr();
				setState(402); andExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExprContext extends ParserRuleContext {
		public RelationalExprContext relationalExpr() {
			return getRuleContext(RelationalExprContext.class,0);
		}
		public EqualityExpr2Context equalityExpr2() {
			return getRuleContext(EqualityExpr2Context.class,0);
		}
		public EqualityExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEqualityExpr(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_equalityExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407); relationalExpr();
			setState(408); equalityExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpr2Context extends ParserRuleContext {
		public TerminalNode NE_S() { return getToken(XQueryParser.NE_S, 0); }
		public TerminalNode EQ_S() { return getToken(XQueryParser.EQ_S, 0); }
		public RelationalExprContext relationalExpr() {
			return getRuleContext(RelationalExprContext.class,0);
		}
		public EqualityExpr2Context equalityExpr2() {
			return getRuleContext(EqualityExpr2Context.class,0);
		}
		public EqualityExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterEqualityExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitEqualityExpr2(this);
		}
	}

	public final EqualityExpr2Context equalityExpr2() throws RecognitionException {
		EqualityExpr2Context _localctx = new EqualityExpr2Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_equalityExpr2);
		try {
			setState(419);
			switch (_input.LA(1)) {
			case EQ_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(410); match(EQ_S);
				setState(411); relationalExpr();
				setState(412); equalityExpr2();
				}
				break;
			case NE_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(414); match(NE_S);
				setState(415); relationalExpr();
				setState(416); equalityExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
			case OR:
			case AND:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExprContext extends ParserRuleContext {
		public AdditiveExprContext additiveExpr() {
			return getRuleContext(AdditiveExprContext.class,0);
		}
		public RelationalExpr2Context relationalExpr2() {
			return getRuleContext(RelationalExpr2Context.class,0);
		}
		public RelationalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRelationalExpr(this);
		}
	}

	public final RelationalExprContext relationalExpr() throws RecognitionException {
		RelationalExprContext _localctx = new RelationalExprContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_relationalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421); additiveExpr();
			setState(422); relationalExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpr2Context extends ParserRuleContext {
		public AdditiveExprContext additiveExpr() {
			return getRuleContext(AdditiveExprContext.class,0);
		}
		public TerminalNode LE_S() { return getToken(XQueryParser.LE_S, 0); }
		public RelationalExpr2Context relationalExpr2() {
			return getRuleContext(RelationalExpr2Context.class,0);
		}
		public TerminalNode GT_S() { return getToken(XQueryParser.GT_S, 0); }
		public TerminalNode GE_S() { return getToken(XQueryParser.GE_S, 0); }
		public TerminalNode LT_S() { return getToken(XQueryParser.LT_S, 0); }
		public RelationalExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRelationalExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRelationalExpr2(this);
		}
	}

	public final RelationalExpr2Context relationalExpr2() throws RecognitionException {
		RelationalExpr2Context _localctx = new RelationalExpr2Context(_ctx, getState());
		enterRule(_localctx, 78, RULE_relationalExpr2);
		try {
			setState(441);
			switch (_input.LA(1)) {
			case LT_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(424); match(LT_S);
				setState(425); additiveExpr();
				setState(426); relationalExpr2();
				}
				break;
			case GT_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(428); match(GT_S);
				setState(429); additiveExpr();
				setState(430); relationalExpr2();
				}
				break;
			case LE_S:
				enterOuterAlt(_localctx, 3);
				{
				setState(432); match(LE_S);
				setState(433); additiveExpr();
				setState(434); relationalExpr2();
				}
				break;
			case GE_S:
				enterOuterAlt(_localctx, 4);
				{
				setState(436); match(GE_S);
				setState(437); additiveExpr();
				setState(438); relationalExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
				enterOuterAlt(_localctx, 5);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExprContext extends ParserRuleContext {
		public AdditiveExpr2Context additiveExpr2() {
			return getRuleContext(AdditiveExpr2Context.class,0);
		}
		public MultiplicativeExprContext multiplicativeExpr() {
			return getRuleContext(MultiplicativeExprContext.class,0);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAdditiveExpr(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_additiveExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443); multiplicativeExpr();
			setState(444); additiveExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpr2Context extends ParserRuleContext {
		public AdditiveExpr2Context additiveExpr2() {
			return getRuleContext(AdditiveExpr2Context.class,0);
		}
		public MultiplicativeExprContext multiplicativeExpr() {
			return getRuleContext(MultiplicativeExprContext.class,0);
		}
		public AdditiveExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAdditiveExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAdditiveExpr2(this);
		}
	}

	public final AdditiveExpr2Context additiveExpr2() throws RecognitionException {
		AdditiveExpr2Context _localctx = new AdditiveExpr2Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_additiveExpr2);
		try {
			setState(455);
			switch (_input.LA(1)) {
			case 39:
				enterOuterAlt(_localctx, 1);
				{
				setState(446); match(39);
				setState(447); multiplicativeExpr();
				setState(448); additiveExpr2();
				}
				break;
			case 41:
				enterOuterAlt(_localctx, 2);
				{
				setState(450); match(41);
				setState(451); multiplicativeExpr();
				setState(452); additiveExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
			case LT_S:
			case LE_S:
			case GT_S:
			case GE_S:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExprContext extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public MultiplicativeExpr2Context multiplicativeExpr2() {
			return getRuleContext(MultiplicativeExpr2Context.class,0);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitMultiplicativeExpr(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_multiplicativeExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457); unaryExpr();
			setState(458); multiplicativeExpr2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpr2Context extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public MultiplicativeExpr2Context multiplicativeExpr2() {
			return getRuleContext(MultiplicativeExpr2Context.class,0);
		}
		public MultiplicativeExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterMultiplicativeExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitMultiplicativeExpr2(this);
		}
	}

	public final MultiplicativeExpr2Context multiplicativeExpr2() throws RecognitionException {
		MultiplicativeExpr2Context _localctx = new MultiplicativeExpr2Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_multiplicativeExpr2);
		try {
			setState(473);
			switch (_input.LA(1)) {
			case 16:
				enterOuterAlt(_localctx, 1);
				{
				setState(460); match(16);
				setState(461); unaryExpr();
				setState(462); multiplicativeExpr2();
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 2);
				{
				setState(464); match(34);
				setState(465); unaryExpr();
				setState(466); multiplicativeExpr2();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 3);
				{
				setState(468); match(21);
				setState(469); unaryExpr();
				setState(470); multiplicativeExpr2();
				}
				break;
			case 2:
			case 6:
			case 11:
			case 18:
			case 27:
			case 31:
			case 32:
			case 38:
			case 39:
			case 41:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
			case LT_S:
			case LE_S:
			case GT_S:
			case GE_S:
				enterOuterAlt(_localctx, 4);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExprContext extends ParserRuleContext {
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public UnionExprContext unionExpr() {
			return getRuleContext(UnionExprContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitUnaryExpr(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_unaryExpr);
		try {
			setState(478);
			switch (_input.LA(1)) {
			case 1:
			case 3:
			case 5:
			case 10:
			case 14:
			case 19:
			case 20:
			case 21:
			case 24:
			case 29:
			case 30:
			case 34:
			case 37:
			case NOT:
			case QNAME_TOKEN:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(475); unionExpr();
				}
				break;
			case 41:
				enterOuterAlt(_localctx, 2);
				{
				setState(476); match(41);
				setState(477); unaryExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionExprContext extends ParserRuleContext {
		public List<ValueExprContext> valueExpr() {
			return getRuleContexts(ValueExprContext.class);
		}
		public ValueExprContext valueExpr(int i) {
			return getRuleContext(ValueExprContext.class,i);
		}
		public UnionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterUnionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitUnionExpr(this);
		}
	}

	public final UnionExprContext unionExpr() throws RecognitionException {
		UnionExprContext _localctx = new UnionExprContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_unionExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480); valueExpr();
			setState(485);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==25) {
				{
				{
				setState(481); match(25);
				setState(482); valueExpr();
				}
				}
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueExprContext extends ParserRuleContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public PathExprContext pathExpr() {
			return getRuleContext(PathExprContext.class,0);
		}
		public FilterExprContext filterExpr() {
			return getRuleContext(FilterExprContext.class,0);
		}
		public ValueExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterValueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitValueExpr(this);
		}
	}

	public final ValueExprContext valueExpr() throws RecognitionException {
		ValueExprContext _localctx = new ValueExprContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_valueExpr);
		int _la;
		try {
			setState(494);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(488); filterExpr();
				setState(491);
				_la = _input.LA(1);
				if (_la==1 || _la==24) {
					{
					setState(489);
					_la = _input.LA(1);
					if ( !(_la==1 || _la==24) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(490); relativePathExpr();
					}
				}

				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(493); pathExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathExprContext extends ParserRuleContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public PathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExpr(this);
		}
	}

	public final PathExprContext pathExpr() throws RecognitionException {
		PathExprContext _localctx = new PathExprContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_pathExpr);
		try {
			setState(503);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(496); match(1);
				setState(498);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(497); relativePathExpr();
					}
					break;
				}
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(500); match(24);
				setState(501); relativePathExpr();
				}
				}
				break;
			case 21:
			case 29:
			case 30:
			case 34:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(502); relativePathExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelativePathExprContext extends ParserRuleContext {
		public List<StepExprContext> stepExpr() {
			return getRuleContexts(StepExprContext.class);
		}
		public StepExprContext stepExpr(int i) {
			return getRuleContext(StepExprContext.class,i);
		}
		public RelativePathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relativePathExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRelativePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRelativePathExpr(this);
		}
	}

	public final RelativePathExprContext relativePathExpr() throws RecognitionException {
		RelativePathExprContext _localctx = new RelativePathExprContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505); stepExpr();
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==1 || _la==24) {
				{
				{
				setState(506);
				_la = _input.LA(1);
				if ( !(_la==1 || _la==24) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(507); stepExpr();
				}
				}
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StepExprContext extends ParserRuleContext {
		public AxisStepContext axisStep() {
			return getRuleContext(AxisStepContext.class,0);
		}
		public StepExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterStepExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitStepExpr(this);
		}
	}

	public final StepExprContext stepExpr() throws RecognitionException {
		StepExprContext _localctx = new StepExprContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513); axisStep();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AxisStepContext extends ParserRuleContext {
		public PredicateListContext predicateList() {
			return getRuleContext(PredicateListContext.class,0);
		}
		public ForwardStepContext forwardStep() {
			return getRuleContext(ForwardStepContext.class,0);
		}
		public AxisStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_axisStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAxisStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAxisStep(this);
		}
	}

	public final AxisStepContext axisStep() throws RecognitionException {
		AxisStepContext _localctx = new AxisStepContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515); forwardStep();
			setState(516); predicateList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForwardStepContext extends ParserRuleContext {
		public AbbrevForwardStepContext abbrevForwardStep() {
			return getRuleContext(AbbrevForwardStepContext.class,0);
		}
		public ForwardStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forwardStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterForwardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitForwardStep(this);
		}
	}

	public final ForwardStepContext forwardStep() throws RecognitionException {
		ForwardStepContext _localctx = new ForwardStepContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518); abbrevForwardStep();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbbrevForwardStepContext extends ParserRuleContext {
		public NodeTestContext nodeTest() {
			return getRuleContext(NodeTestContext.class,0);
		}
		public AbbrevForwardStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abbrevForwardStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAbbrevForwardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAbbrevForwardStep(this);
		}
	}

	public final AbbrevForwardStepContext abbrevForwardStep() throws RecognitionException {
		AbbrevForwardStepContext _localctx = new AbbrevForwardStepContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			_la = _input.LA(1);
			if (_la==29) {
				{
				setState(520); match(29);
				}
			}

			setState(523); nodeTest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeTestContext extends ParserRuleContext {
		public KindTestContext kindTest() {
			return getRuleContext(KindTestContext.class,0);
		}
		public NameTestContext nameTest() {
			return getRuleContext(NameTestContext.class,0);
		}
		public NodeTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNodeTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNodeTest(this);
		}
	}

	public final NodeTestContext nodeTest() throws RecognitionException {
		NodeTestContext _localctx = new NodeTestContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_nodeTest);
		try {
			setState(527);
			switch (_input.LA(1)) {
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(525); kindTest();
				}
				break;
			case 21:
			case 34:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(526); nameTest();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KindTestContext extends ParserRuleContext {
		public TextTestContext textTest() {
			return getRuleContext(TextTestContext.class,0);
		}
		public KindTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kindTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterKindTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitKindTest(this);
		}
	}

	public final KindTestContext kindTest() throws RecognitionException {
		KindTestContext _localctx = new KindTestContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529); textTest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameTestContext extends ParserRuleContext {
		public NameTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameTest; }
	 
		public NameTestContext() { }
		public void copyFrom(NameTestContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NameTest_qNameContext extends NameTestContext {
		public QNameContext qName() {
			return getRuleContext(QNameContext.class,0);
		}
		public NameTest_qNameContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNameTest_qName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNameTest_qName(this);
		}
	}
	public static class NameTest_divContext extends NameTestContext {
		public NameTest_divContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNameTest_div(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNameTest_div(this);
		}
	}
	public static class NameTEst_modContext extends NameTestContext {
		public NameTEst_modContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNameTEst_mod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNameTEst_mod(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_nameTest);
		try {
			setState(534);
			switch (_input.LA(1)) {
			case 34:
				_localctx = new NameTest_divContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(531); match(34);
				}
				break;
			case 21:
				_localctx = new NameTEst_modContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(532); match(21);
				}
				break;
			case QNAME_TOKEN:
				_localctx = new NameTest_qNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(533); qName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterExprContext extends ParserRuleContext {
		public PredicateListContext predicateList() {
			return getRuleContext(PredicateListContext.class,0);
		}
		public PrimaryExprContext primaryExpr() {
			return getRuleContext(PrimaryExprContext.class,0);
		}
		public FilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFilterExpr(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536); primaryExpr();
			setState(537); predicateList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateListContext extends ParserRuleContext {
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPredicateList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPredicateList(this);
		}
	}

	public final PredicateListContext predicateList() throws RecognitionException {
		PredicateListContext _localctx = new PredicateListContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_predicateList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==22) {
				{
				{
				setState(539); predicate();
				}
				}
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545); match(22);
			setState(546); expr();
			setState(547); match(27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExprContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParenthesizedExprContext parenthesizedExpr() {
			return getRuleContext(ParenthesizedExprContext.class,0);
		}
		public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPrimaryExpr(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_primaryExpr);
		try {
			setState(552);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(549); literal();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 2);
				{
				setState(550); parenthesizedExpr();
				}
				break;
			case 3:
			case 5:
			case 10:
			case 19:
			case 20:
			case 30:
			case 37:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(551); functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_literal);
		try {
			setState(556);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(554); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(555); match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(XQueryParser.INTEGER_LITERAL, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(XQueryParser.DECIMAL_LITERAL, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			_la = _input.LA(1);
			if ( !(_la==INTEGER_LITERAL || _la==DECIMAL_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesizedExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenthesizedExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterParenthesizedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitParenthesizedExpr(this);
		}
	}

	public final ParenthesizedExprContext parenthesizedExpr() throws RecognitionException {
		ParenthesizedExprContext _localctx = new ParenthesizedExprContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560); match(14);
			setState(561); expr();
			setState(562); match(38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564); functionName();
			setState(565); match(14);
			setState(574);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << 5) | (1L << 10) | (1L << 14) | (1L << 19) | (1L << 20) | (1L << 21) | (1L << 24) | (1L << 29) | (1L << 30) | (1L << 34) | (1L << 37) | (1L << 41) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
				{
				setState(566); expr();
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==18) {
					{
					{
					setState(567); match(18);
					setState(568); expr();
					}
					}
					setState(573);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(576); match(38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionNameContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(XQueryParser.NOT, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFunctionName(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 5) | (1L << 10) | (1L << 19) | (1L << 20) | (1L << 30) | (1L << 37) | (1L << NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextTestContext extends ParserRuleContext {
		public TextTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterTextTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitTextTest(this);
		}
	}

	public final TextTestContext textTest() throws RecognitionException {
		TextTestContext _localctx = new TextTestContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_textTest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(580); match(30);
			setState(583);
			_la = _input.LA(1);
			if (_la==14) {
				{
				setState(581); match(14);
				setState(582); match(38);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QNameContext extends ParserRuleContext {
		public TerminalNode QNAME_TOKEN() { return getToken(XQueryParser.QNAME_TOKEN, 0); }
		public QNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterQName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitQName(this);
		}
	}

	public final QNameContext qName() throws RecognitionException {
		QNameContext _localctx = new QNameContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585); match(QNAME_TOKEN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 29: return arithmeticExpr_xq_sempred((ArithmeticExpr_xqContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmeticExpr_xq_sempred(ArithmeticExpr_xqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 5);

		case 1: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3I\u024e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\3\3\3\3\7\3\u008d\n"+
		"\3\f\3\16\3\u0090\13\3\3\3\3\3\3\4\3\4\5\4\u0096\n\4\3\5\3\5\3\5\5\5\u009b"+
		"\n\5\3\6\3\6\3\6\3\6\7\6\u00a1\n\6\f\6\16\6\u00a4\13\6\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\7\b\u00ae\n\b\f\b\16\b\u00b1\13\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u00ba\n\t\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u00c2\n\13\3\13"+
		"\3\13\3\13\3\13\5\13\u00c8\n\13\5\13\u00ca\n\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00d5\n\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\7\17\u00e2\n\17\f\17\16\17\u00e5\13\17\3\20\3\20\3\20\7\20\u00ea"+
		"\n\20\f\20\16\20\u00ed\13\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f5"+
		"\n\21\3\22\3\22\3\22\5\22\u00fa\n\22\3\23\3\23\3\23\5\23\u00ff\n\23\3"+
		"\23\3\23\5\23\u0103\n\23\5\23\u0105\n\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\7\30\u011b\n\30\f\30\16\30\u011e\13\30\3\31\3\31\3\31\3\31\6\31\u0124"+
		"\n\31\r\31\16\31\u0125\5\31\u0128\n\31\3\32\3\32\3\32\7\32\u012d\n\32"+
		"\f\32\16\32\u0130\13\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u013a"+
		"\n\32\f\32\16\32\u013d\13\32\3\32\3\32\3\32\3\32\5\32\u0143\n\32\3\33"+
		"\3\33\5\33\u0147\n\33\3\33\5\33\u014a\n\33\7\33\u014c\n\33\f\33\16\33"+
		"\u014f\13\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0159\n\34\f"+
		"\34\16\34\u015c\13\34\3\34\3\34\3\35\3\35\5\35\u0162\n\35\3\36\3\36\5"+
		"\36\u0166\n\36\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u016e\n\37\3\37\3\37"+
		"\5\37\u0172\n\37\3\37\5\37\u0175\n\37\3\37\3\37\3\37\3\37\3\37\3\37\7"+
		"\37\u017d\n\37\f\37\16\37\u0180\13\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3"+
		"#\3#\3#\5#\u018e\n#\3$\3$\3$\3%\3%\3%\3%\3%\5%\u0198\n%\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01a6\n\'\3(\3(\3(\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u01bc\n)\3*\3*\3*\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\5+\u01ca\n+\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3"+
		"-\3-\5-\u01dc\n-\3.\3.\3.\5.\u01e1\n.\3/\3/\3/\7/\u01e6\n/\f/\16/\u01e9"+
		"\13/\3\60\3\60\3\60\5\60\u01ee\n\60\3\60\5\60\u01f1\n\60\3\61\3\61\5\61"+
		"\u01f5\n\61\3\61\3\61\3\61\5\61\u01fa\n\61\3\62\3\62\3\62\7\62\u01ff\n"+
		"\62\f\62\16\62\u0202\13\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\66\5\66"+
		"\u020c\n\66\3\66\3\66\3\67\3\67\5\67\u0212\n\67\38\38\39\39\39\59\u0219"+
		"\n9\3:\3:\3:\3;\7;\u021f\n;\f;\16;\u0222\13;\3<\3<\3<\3<\3=\3=\3=\5=\u022b"+
		"\n=\3>\3>\5>\u022f\n>\3?\3?\3@\3@\3@\3@\3A\3A\3A\3A\3A\7A\u023c\nA\fA"+
		"\16A\u023f\13A\5A\u0241\nA\3A\3A\3B\3B\3C\3C\3C\5C\u024a\nC\3D\3D\3D\2"+
		"\3<E\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@"+
		"BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\2\n\7\2\6\6\13"+
		"\13\16\16\23\23##\3\2\60;\4\2\t\n\21\21\4\2))++\5\2\22\22\27\27$$\4\2"+
		"\3\3\32\32\3\2FG\t\2\5\5\7\7\f\f\25\26  \'\'//\u0257\2\u0088\3\2\2\2\4"+
		"\u008a\3\2\2\2\6\u0095\3\2\2\2\b\u009a\3\2\2\2\n\u009c\3\2\2\2\f\u00a5"+
		"\3\2\2\2\16\u00a9\3\2\2\2\20\u00b2\3\2\2\2\22\u00bb\3\2\2\2\24\u00c9\3"+
		"\2\2\2\26\u00d4\3\2\2\2\30\u00d6\3\2\2\2\32\u00db\3\2\2\2\34\u00de\3\2"+
		"\2\2\36\u00e6\3\2\2\2 \u00f4\3\2\2\2\"\u00f9\3\2\2\2$\u00fb\3\2\2\2&\u0106"+
		"\3\2\2\2(\u0108\3\2\2\2*\u010a\3\2\2\2,\u0111\3\2\2\2.\u0116\3\2\2\2\60"+
		"\u011f\3\2\2\2\62\u0129\3\2\2\2\64\u014d\3\2\2\2\66\u0150\3\2\2\28\u0161"+
		"\3\2\2\2:\u0165\3\2\2\2<\u0174\3\2\2\2>\u0181\3\2\2\2@\u0183\3\2\2\2B"+
		"\u0185\3\2\2\2D\u018d\3\2\2\2F\u018f\3\2\2\2H\u0197\3\2\2\2J\u0199\3\2"+
		"\2\2L\u01a5\3\2\2\2N\u01a7\3\2\2\2P\u01bb\3\2\2\2R\u01bd\3\2\2\2T\u01c9"+
		"\3\2\2\2V\u01cb\3\2\2\2X\u01db\3\2\2\2Z\u01e0\3\2\2\2\\\u01e2\3\2\2\2"+
		"^\u01f0\3\2\2\2`\u01f9\3\2\2\2b\u01fb\3\2\2\2d\u0203\3\2\2\2f\u0205\3"+
		"\2\2\2h\u0208\3\2\2\2j\u020b\3\2\2\2l\u0211\3\2\2\2n\u0213\3\2\2\2p\u0218"+
		"\3\2\2\2r\u021a\3\2\2\2t\u0220\3\2\2\2v\u0223\3\2\2\2x\u022a\3\2\2\2z"+
		"\u022e\3\2\2\2|\u0230\3\2\2\2~\u0232\3\2\2\2\u0080\u0236\3\2\2\2\u0082"+
		"\u0244\3\2\2\2\u0084\u0246\3\2\2\2\u0086\u024b\3\2\2\2\u0088\u0089\5\4"+
		"\3\2\u0089\3\3\2\2\2\u008a\u008e\5\6\4\2\u008b\u008d\5\b\5\2\u008c\u008b"+
		"\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\5\60\31\2\u0092\5\3\2\2"+
		"\2\u0093\u0096\5\n\6\2\u0094\u0096\5\16\b\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\7\3\2\2\2\u0097\u009b\5\6\4\2\u0098\u009b\5\32\16"+
		"\2\u0099\u009b\5.\30\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u0099"+
		"\3\2\2\2\u009b\t\3\2\2\2\u009c\u009d\7\r\2\2\u009d\u00a2\5\f\7\2\u009e"+
		"\u009f\7\24\2\2\u009f\u00a1\5\f\7\2\u00a0\u009e\3\2\2\2\u00a1\u00a4\3"+
		"\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\13\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a6\7<\2\2\u00a6\u00a7\7&\2\2\u00a7\u00a8\5\24"+
		"\13\2\u00a8\r\3\2\2\2\u00a9\u00aa\7\"\2\2\u00aa\u00af\5\20\t\2\u00ab\u00ac"+
		"\7\24\2\2\u00ac\u00ae\5\20\t\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2"+
		"\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\17\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\u00b3\7<\2\2\u00b3\u00b9\7\17\2\2\u00b4\u00ba\5\24\13\2"+
		"\u00b5\u00ba\5\4\3\2\u00b6\u00ba\5\30\r\2\u00b7\u00ba\5<\37\2\u00b8\u00ba"+
		"\5z>\2\u00b9\u00b4\3\2\2\2\u00b9\u00b5\3\2\2\2\u00b9\u00b6\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\21\3\2\2\2\u00bb\u00bc\3\2\2"+
		"\2\u00bc\23\3\2\2\2\u00bd\u00be\7\31\2\2\u00be\u00bf\7\20\2\2\u00bf\u00c1"+
		"\5\26\f\2\u00c0\u00c2\5> \2\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c4\7(\2\2\u00c4\u00ca\3\2\2\2\u00c5\u00c7\5\26"+
		"\f\2\u00c6\u00c8\5> \2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca"+
		"\3\2\2\2\u00c9\u00bd\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\25\3\2\2\2\u00cb"+
		"\u00cc\7\34\2\2\u00cc\u00cd\7\20\2\2\u00cd\u00ce\7B\2\2\u00ce\u00d5\7"+
		"(\2\2\u00cf\u00d0\7*\2\2\u00d0\u00d1\7\20\2\2\u00d1\u00d2\7B\2\2\u00d2"+
		"\u00d5\7(\2\2\u00d3\u00d5\7<\2\2\u00d4\u00cb\3\2\2\2\u00d4\u00cf\3\2\2"+
		"\2\u00d4\u00d3\3\2\2\2\u00d5\27\3\2\2\2\u00d6\u00d7\t\2\2\2\u00d7\u00d8"+
		"\7\20\2\2\u00d8\u00d9\7<\2\2\u00d9\u00da\7(\2\2\u00da\31\3\2\2\2\u00db"+
		"\u00dc\7!\2\2\u00dc\u00dd\5\34\17\2\u00dd\33\3\2\2\2\u00de\u00e3\5\36"+
		"\20\2\u00df\u00e0\7-\2\2\u00e0\u00e2\5\36\20\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\35\3\2\2"+
		"\2\u00e5\u00e3\3\2\2\2\u00e6\u00eb\5 \21\2\u00e7\u00e8\7.\2\2\u00e8\u00ea"+
		"\5 \21\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\37\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7/\2\2"+
		"\u00ef\u00f0\7\20\2\2\u00f0\u00f1\5\"\22\2\u00f1\u00f2\7(\2\2\u00f2\u00f5"+
		"\3\2\2\2\u00f3\u00f5\5\"\22\2\u00f4\u00ee\3\2\2\2\u00f4\u00f3\3\2\2\2"+
		"\u00f5!\3\2\2\2\u00f6\u00fa\5$\23\2\u00f7\u00fa\5*\26\2\u00f8\u00fa\5"+
		",\27\2\u00f9\u00f6\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa"+
		"#\3\2\2\2\u00fb\u0104\5<\37\2\u00fc\u00ff\5&\24\2\u00fd\u00ff\5(\25\2"+
		"\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u0103"+
		"\5<\37\2\u0101\u0103\5z>\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103"+
		"\u0105\3\2\2\2\u0104\u00fe\3\2\2\2\u0104\u0105\3\2\2\2\u0105%\3\2\2\2"+
		"\u0106\u0107\t\3\2\2\u0107\'\3\2\2\2\u0108\u0109\t\4\2\2\u0109)\3\2\2"+
		"\2\u010a\u010b\7\36\2\2\u010b\u010c\7\20\2\2\u010c\u010d\7<\2\2\u010d"+
		"\u010e\7\24\2\2\u010e\u010f\7B\2\2\u010f\u0110\7(\2\2\u0110+\3\2\2\2\u0111"+
		"\u0112\7,\2\2\u0112\u0113\7\20\2\2\u0113\u0114\7<\2\2\u0114\u0115\7(\2"+
		"\2\u0115-\3\2\2\2\u0116\u0117\7\4\2\2\u0117\u011c\7<\2\2\u0118\u0119\7"+
		"\24\2\2\u0119\u011b\7<\2\2\u011a\u0118\3\2\2\2\u011b\u011e\3\2\2\2\u011c"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d/\3\2\2\2\u011e\u011c\3\2\2\2"+
		"\u011f\u0127\7\b\2\2\u0120\u0128\5\62\32\2\u0121\u0124\5\30\r\2\u0122"+
		"\u0124\7<\2\2\u0123\u0121\3\2\2\2\u0123\u0122\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0128\3\2\2\2\u0127"+
		"\u0120\3\2\2\2\u0127\u0123\3\2\2\2\u0128\61\3\2\2\2\u0129\u012a\7\65\2"+
		"\2\u012a\u012e\5:\36\2\u012b\u012d\5\66\34\2\u012c\u012b\3\2\2\2\u012d"+
		"\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0142\3\2"+
		"\2\2\u0130\u012e\3\2\2\2\u0131\u0143\7@\2\2\u0132\u013b\79\2\2\u0133\u013a"+
		"\5\62\32\2\u0134\u0135\7=\2\2\u0135\u0136\5\64\33\2\u0136\u0137\7>\2\2"+
		"\u0137\u013a\3\2\2\2\u0138\u013a\5:\36\2\u0139\u0133\3\2\2\2\u0139\u0134"+
		"\3\2\2\2\u0139\u0138\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7?"+
		"\2\2\u013f\u0140\5:\36\2\u0140\u0141\79\2\2\u0141\u0143\3\2\2\2\u0142"+
		"\u0131\3\2\2\2\u0142\u0132\3\2\2\2\u0143\63\3\2\2\2\u0144\u0147\7<\2\2"+
		"\u0145\u0147\5\30\r\2\u0146\u0144\3\2\2\2\u0146\u0145\3\2\2\2\u0147\u0149"+
		"\3\2\2\2\u0148\u014a\7\24\2\2\u0149\u0148\3\2\2\2\u0149\u014a\3\2\2\2"+
		"\u014a\u014c\3\2\2\2\u014b\u0146\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b"+
		"\3\2\2\2\u014d\u014e\3\2\2\2\u014e\65\3\2\2\2\u014f\u014d\3\2\2\2\u0150"+
		"\u0151\7A\2\2\u0151\u0152\7\61\2\2\u0152\u015a\7%\2\2\u0153\u0154\7=\2"+
		"\2\u0154\u0155\58\35\2\u0155\u0156\7>\2\2\u0156\u0159\3\2\2\2\u0157\u0159"+
		"\5:\36\2\u0158\u0153\3\2\2\2\u0158\u0157\3\2\2\2\u0159\u015c\3\2\2\2\u015a"+
		"\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2\2\2\u015c\u015a\3\2"+
		"\2\2\u015d\u015e\7%\2\2\u015e\67\3\2\2\2\u015f\u0162\7<\2\2\u0160\u0162"+
		"\5\30\r\2\u0161\u015f\3\2\2\2\u0161\u0160\3\2\2\2\u01629\3\2\2\2\u0163"+
		"\u0166\5\u0082B\2\u0164\u0166\7A\2\2\u0165\u0163\3\2\2\2\u0165\u0164\3"+
		"\2\2\2\u0166;\3\2\2\2\u0167\u0168\b\37\1\2\u0168\u0169\7\20\2\2\u0169"+
		"\u016a\5<\37\2\u016a\u016b\7(\2\2\u016b\u0175\3\2\2\2\u016c\u016e\7+\2"+
		"\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0175"+
		"\5|?\2\u0170\u0172\7+\2\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173\u0175\7<\2\2\u0174\u0167\3\2\2\2\u0174\u016d\3\2"+
		"\2\2\u0174\u0171\3\2\2\2\u0175\u017e\3\2\2\2\u0176\u0177\f\7\2\2\u0177"+
		"\u0178\t\5\2\2\u0178\u017d\5<\37\b\u0179\u017a\f\6\2\2\u017a\u017b\t\6"+
		"\2\2\u017b\u017d\5<\37\7\u017c\u0176\3\2\2\2\u017c\u0179\3\2\2\2\u017d"+
		"\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f=\3\2\2\2"+
		"\u0180\u017e\3\2\2\2\u0181\u0182\5@!\2\u0182?\3\2\2\2\u0183\u0184\5B\""+
		"\2\u0184A\3\2\2\2\u0185\u0186\5F$\2\u0186\u0187\5D#\2\u0187C\3\2\2\2\u0188"+
		"\u0189\7-\2\2\u0189\u018a\5F$\2\u018a\u018b\5D#\2\u018b\u018e\3\2\2\2"+
		"\u018c\u018e\3\2\2\2\u018d\u0188\3\2\2\2\u018d\u018c\3\2\2\2\u018eE\3"+
		"\2\2\2\u018f\u0190\5J&\2\u0190\u0191\5H%\2\u0191G\3\2\2\2\u0192\u0193"+
		"\7.\2\2\u0193\u0194\5J&\2\u0194\u0195\5H%\2\u0195\u0198\3\2\2\2\u0196"+
		"\u0198\3\2\2\2\u0197\u0192\3\2\2\2\u0197\u0196\3\2\2\2\u0198I\3\2\2\2"+
		"\u0199\u019a\5N(\2\u019a\u019b\5L\'\2\u019bK\3\2\2\2\u019c\u019d\7\61"+
		"\2\2\u019d\u019e\5N(\2\u019e\u019f\5L\'\2\u019f\u01a6\3\2\2\2\u01a0\u01a1"+
		"\7\63\2\2\u01a1\u01a2\5N(\2\u01a2\u01a3\5L\'\2\u01a3\u01a6\3\2\2\2\u01a4"+
		"\u01a6\3\2\2\2\u01a5\u019c\3\2\2\2\u01a5\u01a0\3\2\2\2\u01a5\u01a4\3\2"+
		"\2\2\u01a6M\3\2\2\2\u01a7\u01a8\5R*\2\u01a8\u01a9\5P)\2\u01a9O\3\2\2\2"+
		"\u01aa\u01ab\7\65\2\2\u01ab\u01ac\5R*\2\u01ac\u01ad\5P)\2\u01ad\u01bc"+
		"\3\2\2\2\u01ae\u01af\79\2\2\u01af\u01b0\5R*\2\u01b0\u01b1\5P)\2\u01b1"+
		"\u01bc\3\2\2\2\u01b2\u01b3\7\67\2\2\u01b3\u01b4\5R*\2\u01b4\u01b5\5P)"+
		"\2\u01b5\u01bc\3\2\2\2\u01b6\u01b7\7;\2\2\u01b7\u01b8\5R*\2\u01b8\u01b9"+
		"\5P)\2\u01b9\u01bc\3\2\2\2\u01ba\u01bc\3\2\2\2\u01bb\u01aa\3\2\2\2\u01bb"+
		"\u01ae\3\2\2\2\u01bb\u01b2\3\2\2\2\u01bb\u01b6\3\2\2\2\u01bb\u01ba\3\2"+
		"\2\2\u01bcQ\3\2\2\2\u01bd\u01be\5V,\2\u01be\u01bf\5T+\2\u01bfS\3\2\2\2"+
		"\u01c0\u01c1\7)\2\2\u01c1\u01c2\5V,\2\u01c2\u01c3\5T+\2\u01c3\u01ca\3"+
		"\2\2\2\u01c4\u01c5\7+\2\2\u01c5\u01c6\5V,\2\u01c6\u01c7\5T+\2\u01c7\u01ca"+
		"\3\2\2\2\u01c8\u01ca\3\2\2\2\u01c9\u01c0\3\2\2\2\u01c9\u01c4\3\2\2\2\u01c9"+
		"\u01c8\3\2\2\2\u01caU\3\2\2\2\u01cb\u01cc\5Z.\2\u01cc\u01cd\5X-\2\u01cd"+
		"W\3\2\2\2\u01ce\u01cf\7\22\2\2\u01cf\u01d0\5Z.\2\u01d0\u01d1\5X-\2\u01d1"+
		"\u01dc\3\2\2\2\u01d2\u01d3\7$\2\2\u01d3\u01d4\5Z.\2\u01d4\u01d5\5X-\2"+
		"\u01d5\u01dc\3\2\2\2\u01d6\u01d7\7\27\2\2\u01d7\u01d8\5Z.\2\u01d8\u01d9"+
		"\5X-\2\u01d9\u01dc\3\2\2\2\u01da\u01dc\3\2\2\2\u01db\u01ce\3\2\2\2\u01db"+
		"\u01d2\3\2\2\2\u01db\u01d6\3\2\2\2\u01db\u01da\3\2\2\2\u01dcY\3\2\2\2"+
		"\u01dd\u01e1\5\\/\2\u01de\u01df\7+\2\2\u01df\u01e1\5Z.\2\u01e0\u01dd\3"+
		"\2\2\2\u01e0\u01de\3\2\2\2\u01e1[\3\2\2\2\u01e2\u01e7\5^\60\2\u01e3\u01e4"+
		"\7\33\2\2\u01e4\u01e6\5^\60\2\u01e5\u01e3\3\2\2\2\u01e6\u01e9\3\2\2\2"+
		"\u01e7\u01e5\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8]\3\2\2\2\u01e9\u01e7\3"+
		"\2\2\2\u01ea\u01ed\5r:\2\u01eb\u01ec\t\7\2\2\u01ec\u01ee\5b\62\2\u01ed"+
		"\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef\u01f1\5`"+
		"\61\2\u01f0\u01ea\3\2\2\2\u01f0\u01ef\3\2\2\2\u01f1_\3\2\2\2\u01f2\u01f4"+
		"\7\3\2\2\u01f3\u01f5\5b\62\2\u01f4\u01f3\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5"+
		"\u01fa\3\2\2\2\u01f6\u01f7\7\32\2\2\u01f7\u01fa\5b\62\2\u01f8\u01fa\5"+
		"b\62\2\u01f9\u01f2\3\2\2\2\u01f9\u01f6\3\2\2\2\u01f9\u01f8\3\2\2\2\u01fa"+
		"a\3\2\2\2\u01fb\u0200\5d\63\2\u01fc\u01fd\t\7\2\2\u01fd\u01ff\5d\63\2"+
		"\u01fe\u01fc\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u0201"+
		"\3\2\2\2\u0201c\3\2\2\2\u0202\u0200\3\2\2\2\u0203\u0204\5f\64\2\u0204"+
		"e\3\2\2\2\u0205\u0206\5h\65\2\u0206\u0207\5t;\2\u0207g\3\2\2\2\u0208\u0209"+
		"\5j\66\2\u0209i\3\2\2\2\u020a\u020c\7\37\2\2\u020b\u020a\3\2\2\2\u020b"+
		"\u020c\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u020e\5l\67\2\u020ek\3\2\2\2"+
		"\u020f\u0212\5n8\2\u0210\u0212\5p9\2\u0211\u020f\3\2\2\2\u0211\u0210\3"+
		"\2\2\2\u0212m\3\2\2\2\u0213\u0214\5\u0084C\2\u0214o\3\2\2\2\u0215\u0219"+
		"\7$\2\2\u0216\u0219\7\27\2\2\u0217\u0219\5\u0086D\2\u0218\u0215\3\2\2"+
		"\2\u0218\u0216\3\2\2\2\u0218\u0217\3\2\2\2\u0219q\3\2\2\2\u021a\u021b"+
		"\5x=\2\u021b\u021c\5t;\2\u021cs\3\2\2\2\u021d\u021f\5v<\2\u021e\u021d"+
		"\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221"+
		"u\3\2\2\2\u0222\u0220\3\2\2\2\u0223\u0224\7\30\2\2\u0224\u0225\5@!\2\u0225"+
		"\u0226\7\35\2\2\u0226w\3\2\2\2\u0227\u022b\5z>\2\u0228\u022b\5~@\2\u0229"+
		"\u022b\5\u0080A\2\u022a\u0227\3\2\2\2\u022a\u0228\3\2\2\2\u022a\u0229"+
		"\3\2\2\2\u022by\3\2\2\2\u022c\u022f\5|?\2\u022d\u022f\7B\2\2\u022e\u022c"+
		"\3\2\2\2\u022e\u022d\3\2\2\2\u022f{\3\2\2\2\u0230\u0231\t\b\2\2\u0231"+
		"}\3\2\2\2\u0232\u0233\7\20\2\2\u0233\u0234\5@!\2\u0234\u0235\7(\2\2\u0235"+
		"\177\3\2\2\2\u0236\u0237\5\u0082B\2\u0237\u0240\7\20\2\2\u0238\u023d\5"+
		"@!\2\u0239\u023a\7\24\2\2\u023a\u023c\5@!\2\u023b\u0239\3\2\2\2\u023c"+
		"\u023f\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u0241\3\2"+
		"\2\2\u023f\u023d\3\2\2\2\u0240\u0238\3\2\2\2\u0240\u0241\3\2\2\2\u0241"+
		"\u0242\3\2\2\2\u0242\u0243\7(\2\2\u0243\u0081\3\2\2\2\u0244\u0245\t\t"+
		"\2\2\u0245\u0083\3\2\2\2\u0246\u0249\7 \2\2\u0247\u0248\7\20\2\2\u0248"+
		"\u024a\7(\2\2\u0249\u0247\3\2\2\2\u0249\u024a\3\2\2\2\u024a\u0085\3\2"+
		"\2\2\u024b\u024c\7A\2\2\u024c\u0087\3\2\2\2=\u008e\u0095\u009a\u00a2\u00af"+
		"\u00b9\u00c1\u00c7\u00c9\u00d4\u00e3\u00eb\u00f4\u00f9\u00fe\u0102\u0104"+
		"\u011c\u0123\u0125\u0127\u012e\u0139\u013b\u0142\u0146\u0149\u014d\u0158"+
		"\u015a\u0161\u0165\u016d\u0171\u0174\u017c\u017e\u018d\u0197\u01a5\u01bb"+
		"\u01c9\u01db\u01e0\u01e7\u01ed\u01f0\u01f4\u01f9\u0200\u020b\u0211\u0218"+
		"\u0220\u022a\u022e\u023d\u0240\u0249";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}