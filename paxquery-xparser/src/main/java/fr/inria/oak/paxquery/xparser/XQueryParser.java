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
		T__38=1, T__37=2, T__36=3, T__35=4, T__34=5, T__33=6, T__32=7, T__31=8, 
		T__30=9, T__29=10, T__28=11, T__27=12, T__26=13, T__25=14, T__24=15, T__23=16, 
		T__22=17, T__21=18, T__20=19, T__19=20, T__18=21, T__17=22, T__16=23, 
		T__15=24, T__14=25, T__13=26, T__12=27, T__11=28, T__10=29, T__9=30, T__8=31, 
		T__7=32, T__6=33, T__5=34, T__4=35, T__3=36, T__2=37, T__1=38, T__0=39, 
		TEXTFUNCTION=40, SLASH=41, SLASHSLASH=42, OR=43, AND=44, NOT=45, EQ=46, 
		EQ_S=47, NE=48, NE_S=49, LT=50, LT_S=51, LE=52, LE_S=53, GT=54, GT_S=55, 
		GE=56, GE_S=57, VAR=58, LEFTCURL=59, RIGHTCURL=60, OPEN_TAG=61, CLOSE_TAG=62, 
		QNAME_TOKEN=63, STRING_LITERAL=64, REFERENCE=65, ENTITY_REF=66, CHAR_REF=67, 
		INTEGER_LITERAL=68, DECIMAL_LITERAL=69, DIGITS=70, WS=71;
	public static final String[] tokenNames = {
		"<INVALID>", "'group by'", "'substring'", "'sum'", "'true'", "'return'", 
		"'>>'", "'<<'", "'max'", "'concat'", "'for'", "'avg'", "':='", "'('", 
		"'is'", "'*'", "'min'", "','", "'false'", "'ceiling'", "'mod'", "'['", 
		"'distinct-values'", "'|'", "'collection'", "']'", "'contains'", "'@'", 
		"'where'", "'let'", "'count'", "'div'", "'\"'", "'in'", "'floor'", "')'", 
		"'+'", "'doc'", "'-'", "'empty'", "'text()'", "'/'", "'//'", "'or'", "'and'", 
		"'not'", "'eq'", "'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", 
		"'gt'", "'>'", "'ge'", "'>='", "VAR", "'{'", "'}'", "OPEN_TAG", "CLOSE_TAG", 
		"QNAME_TOKEN", "STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", 
		"INTEGER_LITERAL", "DECIMAL_LITERAL", "DIGITS", "WS"
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
		RULE_relativePathExpr2 = 49, RULE_stepExpr = 50, RULE_axisStep = 51, RULE_forwardStep = 52, 
		RULE_abbrevForwardStep = 53, RULE_nodeTest = 54, RULE_kindTest = 55, RULE_nameTest = 56, 
		RULE_filterExpr = 57, RULE_predicateList = 58, RULE_predicate = 59, RULE_primaryExpr = 60, 
		RULE_literal = 61, RULE_numericLiteral = 62, RULE_parenthesizedExpr = 63, 
		RULE_functionCall = 64, RULE_functionName = 65, RULE_textTest = 66, RULE_qName = 67;
	public static final String[] ruleNames = {
		"xquery", "flwrexpr", "initial", "middle", "forStat", "forBinding", "let", 
		"letBinding", "arithExpr", "pathExpr_xq", "pathExprInner_xq", "aggrExpr", 
		"where", "orExpr_xq", "andExpr_xq", "boolExpr_xq", "boolExprInner_xq", 
		"pred", "vcmp", "ncmp", "contains", "empty", "groupBy", "returnStat", 
		"eleConst", "eleConstInner", "att", "attInner", "eaName", "arithmeticExpr_xq", 
		"xpath", "expr", "orExpr", "orExpr2", "andExpr", "andExpr2", "equalityExpr", 
		"equalityExpr2", "relationalExpr", "relationalExpr2", "additiveExpr", 
		"additiveExpr2", "multiplicativeExpr", "multiplicativeExpr2", "unaryExpr", 
		"unionExpr", "valueExpr", "pathExpr", "relativePathExpr", "relativePathExpr2", 
		"stepExpr", "axisStep", "forwardStep", "abbrevForwardStep", "nodeTest", 
		"kindTest", "nameTest", "filterExpr", "predicateList", "predicate", "primaryExpr", 
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
			setState(136); flwrexpr();
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
			setState(138); initial();
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 10) | (1L << 28) | (1L << 29))) != 0)) {
				{
				{
				setState(139); middle();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(145); returnStat();
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
			setState(149);
			switch (_input.LA(1)) {
			case 10:
				enterOuterAlt(_localctx, 1);
				{
				setState(147); forStat();
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 2);
				{
				setState(148); let();
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
			setState(154);
			switch (_input.LA(1)) {
			case 10:
			case 29:
				enterOuterAlt(_localctx, 1);
				{
				setState(151); initial();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 2);
				{
				setState(152); where();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 3);
				{
				setState(153); groupBy();
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
			setState(156); match(10);
			setState(157); forBinding();
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==17) {
				{
				{
				setState(158); match(17);
				setState(159); forBinding();
				}
				}
				setState(164);
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
			setState(165); match(VAR);
			setState(166); match(33);
			setState(167); pathExpr_xq();
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
			setState(169); match(29);
			setState(170); letBinding();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==17) {
				{
				{
				setState(171); match(17);
				setState(172); letBinding();
				}
				}
				setState(177);
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
			setState(178); match(VAR);
			setState(179); match(12);
			setState(185);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(180); pathExpr_xq();
				}
				break;

			case 2:
				{
				setState(181); flwrexpr();
				}
				break;

			case 3:
				{
				setState(182); aggrExpr();
				}
				break;

			case 4:
				{
				setState(183); arithmeticExpr_xq(0);
				}
				break;

			case 5:
				{
				setState(184); literal();
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
			setState(201);
			switch (_input.LA(1)) {
			case 22:
				enterOuterAlt(_localctx, 1);
				{
				setState(189); match(22);
				setState(190); match(13);
				setState(191); pathExprInner_xq();
				setState(193);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 13) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 27) | (1L << 31) | (1L << 34) | (1L << 38) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(192); xpath();
					}
				}

				setState(195); match(35);
				}
				break;
			case 24:
			case 37:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(197); pathExprInner_xq();
				setState(199);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 13) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 27) | (1L << 31) | (1L << 34) | (1L << 38) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(198); xpath();
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
			setState(212);
			switch (_input.LA(1)) {
			case 24:
				_localctx = new PathExprInner_xq_collectionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203); match(24);
				setState(204); match(13);
				setState(205); match(STRING_LITERAL);
				setState(206); match(35);
				}
				break;
			case 37:
				_localctx = new PathExprInner_xq_docContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(207); match(37);
				setState(208); match(13);
				setState(209); match(STRING_LITERAL);
				setState(210); match(35);
				}
				break;
			case VAR:
				_localctx = new PathExprInner_xq_VARContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(211); match(VAR);
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
			setState(214);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 8) | (1L << 11) | (1L << 16) | (1L << 30))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(215); match(13);
			setState(216); match(VAR);
			setState(217); match(35);
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
			setState(219); match(28);
			setState(220); orExpr_xq();
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
			setState(222); andExpr_xq();
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(223); match(OR);
				setState(224); andExpr_xq();
				}
				}
				setState(229);
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
			setState(230); boolExpr_xq();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(231); match(AND);
				setState(232); boolExpr_xq();
				}
				}
				setState(237);
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
			setState(244);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); match(NOT);
				setState(239); match(13);
				setState(240); boolExprInner_xq();
				setState(241); match(35);
				}
				break;
			case 13:
			case 26:
			case 38:
			case 39:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(243); boolExprInner_xq();
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
			setState(249);
			switch (_input.LA(1)) {
			case 13:
			case 38:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(246); pred();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 2);
				{
				setState(247); contains();
				}
				break;
			case 39:
				enterOuterAlt(_localctx, 3);
				{
				setState(248); empty();
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
			setState(251); arithmeticExpr_xq(0);
			setState(260);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 14) | (1L << EQ) | (1L << EQ_S) | (1L << NE) | (1L << NE_S) | (1L << LT) | (1L << LT_S) | (1L << LE) | (1L << LE_S) | (1L << GT) | (1L << GT_S) | (1L << GE) | (1L << GE_S))) != 0)) {
				{
				setState(254);
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
					setState(252); vcmp();
					}
					break;
				case 6:
				case 7:
				case 14:
					{
					setState(253); ncmp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(258);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(256); arithmeticExpr_xq(0);
					}
					break;

				case 2:
					{
					setState(257); literal();
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
			setState(262);
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
			setState(264);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 14))) != 0)) ) {
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
			setState(266); match(26);
			setState(267); match(13);
			setState(268); match(VAR);
			setState(269); match(17);
			setState(270); match(STRING_LITERAL);
			setState(271); match(35);
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
			setState(273); match(39);
			setState(274); match(13);
			setState(275); match(VAR);
			setState(276); match(35);
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
			setState(278); match(1);
			setState(279); match(VAR);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==17) {
				{
				{
				setState(280); match(17);
				setState(281); match(VAR);
				}
				}
				setState(286);
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
			setState(287); match(5);
			setState(295);
			switch (_input.LA(1)) {
			case LT_S:
				{
				setState(288); eleConst();
				}
				break;
			case 3:
			case 8:
			case 11:
			case 16:
			case 30:
			case VAR:
				{
				setState(291); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(291);
					switch (_input.LA(1)) {
					case 3:
					case 8:
					case 11:
					case 16:
					case 30:
						{
						setState(289); aggrExpr();
						}
						break;
					case VAR:
						{
						setState(290); match(VAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(293); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 8) | (1L << 11) | (1L << 16) | (1L << 30) | (1L << VAR))) != 0) );
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
			setState(297); match(LT_S);
			setState(298); eaName();
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==QNAME_TOKEN) {
				{
				{
				setState(299); att();
				}
				}
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			switch (_input.LA(1)) {
			case CLOSE_TAG:
				{
				setState(305); match(CLOSE_TAG);
				}
				break;
			case GT_S:
				{
				{
				setState(306); match(GT_S);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 18) | (1L << 19) | (1L << 34) | (1L << NOT) | (1L << LT_S) | (1L << LEFTCURL) | (1L << QNAME_TOKEN))) != 0)) {
					{
					setState(313);
					switch (_input.LA(1)) {
					case LT_S:
						{
						setState(307); eleConst();
						}
						break;
					case LEFTCURL:
						{
						setState(308); match(LEFTCURL);
						setState(309); eleConstInner();
						setState(310); match(RIGHTCURL);
						}
						break;
					case 2:
					case 4:
					case 9:
					case 18:
					case 19:
					case 34:
					case NOT:
					case QNAME_TOKEN:
						{
						setState(312); eaName();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(317);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(318); match(OPEN_TAG);
				{
				setState(319); eaName();
				}
				setState(320); match(GT_S);
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
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 8) | (1L << 11) | (1L << 16) | (1L << 30) | (1L << VAR))) != 0)) {
				{
				{
				setState(326);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(324); match(VAR);
					}
					break;
				case 3:
				case 8:
				case 11:
				case 16:
				case 30:
					{
					setState(325); aggrExpr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(329);
				_la = _input.LA(1);
				if (_la==17) {
					{
					setState(328); match(17);
					}
				}

				}
				}
				setState(335);
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
			setState(336); match(QNAME_TOKEN);
			setState(337); match(EQ_S);
			setState(338); match(32);
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 18) | (1L << 19) | (1L << 34) | (1L << NOT) | (1L << LEFTCURL) | (1L << QNAME_TOKEN))) != 0)) {
				{
				setState(344);
				switch (_input.LA(1)) {
				case LEFTCURL:
					{
					setState(339); match(LEFTCURL);
					setState(340); attInner();
					setState(341); match(RIGHTCURL);
					}
					break;
				case 2:
				case 4:
				case 9:
				case 18:
				case 19:
				case 34:
				case NOT:
				case QNAME_TOKEN:
					{
					setState(343); eaName();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(349); match(32);
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
			setState(353);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(351); match(VAR);
				}
				break;
			case 3:
			case 8:
			case 11:
			case 16:
			case 30:
				enterOuterAlt(_localctx, 2);
				{
				setState(352); aggrExpr();
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
			setState(357);
			switch (_input.LA(1)) {
			case 2:
			case 4:
			case 9:
			case 18:
			case 19:
			case 34:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(355); functionName();
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(356); match(QNAME_TOKEN);
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
			setState(372);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(360); match(13);
				setState(361); arithmeticExpr_xq(0);
				setState(362); match(35);
				}
				break;

			case 2:
				{
				setState(365);
				_la = _input.LA(1);
				if (_la==38) {
					{
					setState(364); match(38);
					}
				}

				setState(367); numericLiteral();
				}
				break;

			case 3:
				{
				setState(369);
				_la = _input.LA(1);
				if (_la==38) {
					{
					setState(368); match(38);
					}
				}

				setState(371); match(VAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(382);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(380);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(374);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(375);
						_la = _input.LA(1);
						if ( !(_la==36 || _la==38) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(376); arithmeticExpr_xq(6);
						}
						break;

					case 2:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(377);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(378);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 15) | (1L << 20) | (1L << 31))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(379); arithmeticExpr_xq(5);
						}
						break;
					}
					} 
				}
				setState(384);
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
			setState(385); expr();
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
			setState(387); orExpr();
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
			setState(389); andExpr();
			setState(390); orExpr2();
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
			setState(397);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(392); match(OR);
				setState(393); andExpr();
				setState(394); orExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
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
			setState(399); equalityExpr();
			setState(400); andExpr2();
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
			setState(407);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(402); match(AND);
				setState(403); equalityExpr();
				setState(404); andExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
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
			setState(409); relationalExpr();
			setState(410); equalityExpr2();
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
			setState(421);
			switch (_input.LA(1)) {
			case EQ_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(412); match(EQ_S);
				setState(413); relationalExpr();
				setState(414); equalityExpr2();
				}
				break;
			case NE_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(416); match(NE_S);
				setState(417); relationalExpr();
				setState(418); equalityExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
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
			setState(423); additiveExpr();
			setState(424); relationalExpr2();
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
			setState(443);
			switch (_input.LA(1)) {
			case LT_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(426); match(LT_S);
				setState(427); additiveExpr();
				setState(428); relationalExpr2();
				}
				break;
			case GT_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(430); match(GT_S);
				setState(431); additiveExpr();
				setState(432); relationalExpr2();
				}
				break;
			case LE_S:
				enterOuterAlt(_localctx, 3);
				{
				setState(434); match(LE_S);
				setState(435); additiveExpr();
				setState(436); relationalExpr2();
				}
				break;
			case GE_S:
				enterOuterAlt(_localctx, 4);
				{
				setState(438); match(GE_S);
				setState(439); additiveExpr();
				setState(440); relationalExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
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
			setState(445); multiplicativeExpr();
			setState(446); additiveExpr2();
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
			setState(457);
			switch (_input.LA(1)) {
			case 36:
				enterOuterAlt(_localctx, 1);
				{
				setState(448); match(36);
				setState(449); multiplicativeExpr();
				setState(450); additiveExpr2();
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 2);
				{
				setState(452); match(38);
				setState(453); multiplicativeExpr();
				setState(454); additiveExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
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
			setState(459); unaryExpr();
			setState(460); multiplicativeExpr2();
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
			setState(475);
			switch (_input.LA(1)) {
			case 15:
				enterOuterAlt(_localctx, 1);
				{
				setState(462); match(15);
				setState(463); unaryExpr();
				setState(464); multiplicativeExpr2();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 2);
				{
				setState(466); match(31);
				setState(467); unaryExpr();
				setState(468); multiplicativeExpr2();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 3);
				{
				setState(470); match(20);
				setState(471); unaryExpr();
				setState(472); multiplicativeExpr2();
				}
				break;
			case 1:
			case 5:
			case 10:
			case 17:
			case 25:
			case 28:
			case 29:
			case 35:
			case 36:
			case 38:
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
			setState(480);
			switch (_input.LA(1)) {
			case 2:
			case 4:
			case 9:
			case 13:
			case 18:
			case 19:
			case 20:
			case 27:
			case 31:
			case 34:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case NOT:
			case QNAME_TOKEN:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(477); unionExpr();
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 2);
				{
				setState(478); match(38);
				setState(479); unaryExpr();
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
			setState(482); valueExpr();
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==23) {
				{
				{
				setState(483); match(23);
				setState(484); valueExpr();
				}
				}
				setState(489);
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
		try {
			setState(495);
			switch (_input.LA(1)) {
			case 2:
			case 4:
			case 9:
			case 13:
			case 18:
			case 19:
			case 34:
			case NOT:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(490); filterExpr();
				setState(492);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(491); pathExpr();
					}
					break;
				}
				}
				break;
			case 20:
			case 27:
			case 31:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(494); pathExpr();
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

	public static class PathExprContext extends ParserRuleContext {
		public PathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExpr; }
	 
		public PathExprContext() { }
		public void copyFrom(PathExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PathExpr_slashslashContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public TerminalNode SLASHSLASH() { return getToken(XQueryParser.SLASHSLASH, 0); }
		public PathExpr_slashslashContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExpr_slashslash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExpr_slashslash(this);
		}
	}
	public static class PathExpr_slashContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(XQueryParser.SLASH, 0); }
		public PathExpr_slashContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExpr_slash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExpr_slash(this);
		}
	}
	public static class PathExpr_relativePathExprContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public PathExpr_relativePathExprContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterPathExpr_relativePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitPathExpr_relativePathExpr(this);
		}
	}

	public final PathExprContext pathExpr() throws RecognitionException {
		PathExprContext _localctx = new PathExprContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_pathExpr);
		try {
			setState(502);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(497); match(SLASH);
				setState(498); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(499); match(SLASHSLASH);
				setState(500); relativePathExpr();
				}
				break;
			case 20:
			case 27:
			case 31:
			case TEXTFUNCTION:
			case QNAME_TOKEN:
				_localctx = new PathExpr_relativePathExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(501); relativePathExpr();
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
		public RelativePathExpr2Context relativePathExpr2(int i) {
			return getRuleContext(RelativePathExpr2Context.class,i);
		}
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public List<RelativePathExpr2Context> relativePathExpr2() {
			return getRuleContexts(RelativePathExpr2Context.class);
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
			setState(504); stepExpr();
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(505); relativePathExpr2();
				}
				}
				setState(510);
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

	public static class RelativePathExpr2Context extends ParserRuleContext {
		public RelativePathExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relativePathExpr2; }
	 
		public RelativePathExpr2Context() { }
		public void copyFrom(RelativePathExpr2Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RelativePathExpr2_slashContext extends RelativePathExpr2Context {
		public TerminalNode SLASH() { return getToken(XQueryParser.SLASH, 0); }
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public RelativePathExpr2_slashContext(RelativePathExpr2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRelativePathExpr2_slash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRelativePathExpr2_slash(this);
		}
	}
	public static class RelativePathExpr2_slashslashContext extends RelativePathExpr2Context {
		public TerminalNode SLASHSLASH() { return getToken(XQueryParser.SLASHSLASH, 0); }
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public RelativePathExpr2_slashslashContext(RelativePathExpr2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRelativePathExpr2_slashslash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRelativePathExpr2_slashslash(this);
		}
	}

	public final RelativePathExpr2Context relativePathExpr2() throws RecognitionException {
		RelativePathExpr2Context _localctx = new RelativePathExpr2Context(_ctx, getState());
		enterRule(_localctx, 98, RULE_relativePathExpr2);
		try {
			setState(515);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(511); match(SLASH);
				setState(512); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(513); match(SLASHSLASH);
				setState(514); stepExpr();
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
		enterRule(_localctx, 100, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517); axisStep();
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
		enterRule(_localctx, 102, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519); forwardStep();
			setState(520); predicateList();
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
		enterRule(_localctx, 104, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522); abbrevForwardStep();
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
		enterRule(_localctx, 106, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			_la = _input.LA(1);
			if (_la==27) {
				{
				setState(524); match(27);
				}
			}

			setState(527); nodeTest();
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
		enterRule(_localctx, 108, RULE_nodeTest);
		try {
			setState(531);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(529); kindTest();
				}
				break;
			case 20:
			case 31:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(530); nameTest();
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
		enterRule(_localctx, 110, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533); textTest();
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
		enterRule(_localctx, 112, RULE_nameTest);
		try {
			setState(538);
			switch (_input.LA(1)) {
			case 31:
				_localctx = new NameTest_divContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(535); match(31);
				}
				break;
			case 20:
				_localctx = new NameTEst_modContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(536); match(20);
				}
				break;
			case QNAME_TOKEN:
				_localctx = new NameTest_qNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(537); qName();
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
		enterRule(_localctx, 114, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540); primaryExpr();
			setState(541); predicateList();
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
		enterRule(_localctx, 116, RULE_predicateList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==21) {
				{
				{
				setState(543); predicate();
				}
				}
				setState(548);
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
		enterRule(_localctx, 118, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549); match(21);
			setState(550); expr();
			setState(551); match(25);
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
		enterRule(_localctx, 120, RULE_primaryExpr);
		try {
			setState(556);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(553); literal();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 2);
				{
				setState(554); parenthesizedExpr();
				}
				break;
			case 2:
			case 4:
			case 9:
			case 18:
			case 19:
			case 34:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(555); functionCall();
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
		enterRule(_localctx, 122, RULE_literal);
		try {
			setState(560);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(558); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(559); match(STRING_LITERAL);
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
		enterRule(_localctx, 124, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
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
		enterRule(_localctx, 126, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564); match(13);
			setState(565); expr();
			setState(566); match(35);
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
		enterRule(_localctx, 128, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568); functionName();
			setState(569); match(13);
			setState(578);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 13) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 27) | (1L << 31) | (1L << 34) | (1L << 38) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
				{
				setState(570); expr();
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==17) {
					{
					{
					setState(571); match(17);
					setState(572); expr();
					}
					}
					setState(577);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(580); match(35);
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
		enterRule(_localctx, 130, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 9) | (1L << 18) | (1L << 19) | (1L << 34) | (1L << NOT))) != 0)) ) {
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
		enterRule(_localctx, 132, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584); match(TEXTFUNCTION);
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
		enterRule(_localctx, 134, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(586); match(QNAME_TOKEN);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3I\u024f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\3\3\3\7\3\u008f"+
		"\n\3\f\3\16\3\u0092\13\3\3\3\3\3\3\4\3\4\5\4\u0098\n\4\3\5\3\5\3\5\5\5"+
		"\u009d\n\5\3\6\3\6\3\6\3\6\7\6\u00a3\n\6\f\6\16\6\u00a6\13\6\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\7\b\u00b0\n\b\f\b\16\b\u00b3\13\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u00bc\n\t\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u00c4\n"+
		"\13\3\13\3\13\3\13\3\13\5\13\u00ca\n\13\5\13\u00cc\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d7\n\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\7\17\u00e4\n\17\f\17\16\17\u00e7\13\17\3\20\3\20\3\20"+
		"\7\20\u00ec\n\20\f\20\16\20\u00ef\13\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u00f7\n\21\3\22\3\22\3\22\5\22\u00fc\n\22\3\23\3\23\3\23\5\23\u0101"+
		"\n\23\3\23\3\23\5\23\u0105\n\23\5\23\u0107\n\23\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\7\30\u011d\n\30\f\30\16\30\u0120\13\30\3\31\3\31\3\31\3\31\6"+
		"\31\u0126\n\31\r\31\16\31\u0127\5\31\u012a\n\31\3\32\3\32\3\32\7\32\u012f"+
		"\n\32\f\32\16\32\u0132\13\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7"+
		"\32\u013c\n\32\f\32\16\32\u013f\13\32\3\32\3\32\3\32\3\32\5\32\u0145\n"+
		"\32\3\33\3\33\5\33\u0149\n\33\3\33\5\33\u014c\n\33\7\33\u014e\n\33\f\33"+
		"\16\33\u0151\13\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u015b"+
		"\n\34\f\34\16\34\u015e\13\34\3\34\3\34\3\35\3\35\5\35\u0164\n\35\3\36"+
		"\3\36\5\36\u0168\n\36\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0170\n\37\3"+
		"\37\3\37\5\37\u0174\n\37\3\37\5\37\u0177\n\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\7\37\u017f\n\37\f\37\16\37\u0182\13\37\3 \3 \3!\3!\3\"\3\"\3\"\3"+
		"#\3#\3#\3#\3#\5#\u0190\n#\3$\3$\3$\3%\3%\3%\3%\3%\5%\u019a\n%\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01a8\n\'\3(\3(\3(\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u01be\n)\3*\3*\3*\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\3+\5+\u01cc\n+\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3-\3-\5-\u01de\n-\3.\3.\3.\5.\u01e3\n.\3/\3/\3/\7/\u01e8\n/\f/"+
		"\16/\u01eb\13/\3\60\3\60\5\60\u01ef\n\60\3\60\5\60\u01f2\n\60\3\61\3\61"+
		"\3\61\3\61\3\61\5\61\u01f9\n\61\3\62\3\62\7\62\u01fd\n\62\f\62\16\62\u0200"+
		"\13\62\3\63\3\63\3\63\3\63\5\63\u0206\n\63\3\64\3\64\3\65\3\65\3\65\3"+
		"\66\3\66\3\67\5\67\u0210\n\67\3\67\3\67\38\38\58\u0216\n8\39\39\3:\3:"+
		"\3:\5:\u021d\n:\3;\3;\3;\3<\7<\u0223\n<\f<\16<\u0226\13<\3=\3=\3=\3=\3"+
		">\3>\3>\5>\u022f\n>\3?\3?\5?\u0233\n?\3@\3@\3A\3A\3A\3A\3B\3B\3B\3B\3"+
		"B\7B\u0240\nB\fB\16B\u0243\13B\5B\u0245\nB\3B\3B\3C\3C\3D\3D\3E\3E\3E"+
		"\2\3<F\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<"+
		">@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\2\t\7"+
		"\2\5\5\n\n\r\r\22\22  \3\2\60;\4\2\b\t\20\20\4\2&&((\5\2\21\21\26\26!"+
		"!\3\2FG\b\2\4\4\6\6\13\13\24\25$$//\u0256\2\u008a\3\2\2\2\4\u008c\3\2"+
		"\2\2\6\u0097\3\2\2\2\b\u009c\3\2\2\2\n\u009e\3\2\2\2\f\u00a7\3\2\2\2\16"+
		"\u00ab\3\2\2\2\20\u00b4\3\2\2\2\22\u00bd\3\2\2\2\24\u00cb\3\2\2\2\26\u00d6"+
		"\3\2\2\2\30\u00d8\3\2\2\2\32\u00dd\3\2\2\2\34\u00e0\3\2\2\2\36\u00e8\3"+
		"\2\2\2 \u00f6\3\2\2\2\"\u00fb\3\2\2\2$\u00fd\3\2\2\2&\u0108\3\2\2\2(\u010a"+
		"\3\2\2\2*\u010c\3\2\2\2,\u0113\3\2\2\2.\u0118\3\2\2\2\60\u0121\3\2\2\2"+
		"\62\u012b\3\2\2\2\64\u014f\3\2\2\2\66\u0152\3\2\2\28\u0163\3\2\2\2:\u0167"+
		"\3\2\2\2<\u0176\3\2\2\2>\u0183\3\2\2\2@\u0185\3\2\2\2B\u0187\3\2\2\2D"+
		"\u018f\3\2\2\2F\u0191\3\2\2\2H\u0199\3\2\2\2J\u019b\3\2\2\2L\u01a7\3\2"+
		"\2\2N\u01a9\3\2\2\2P\u01bd\3\2\2\2R\u01bf\3\2\2\2T\u01cb\3\2\2\2V\u01cd"+
		"\3\2\2\2X\u01dd\3\2\2\2Z\u01e2\3\2\2\2\\\u01e4\3\2\2\2^\u01f1\3\2\2\2"+
		"`\u01f8\3\2\2\2b\u01fa\3\2\2\2d\u0205\3\2\2\2f\u0207\3\2\2\2h\u0209\3"+
		"\2\2\2j\u020c\3\2\2\2l\u020f\3\2\2\2n\u0215\3\2\2\2p\u0217\3\2\2\2r\u021c"+
		"\3\2\2\2t\u021e\3\2\2\2v\u0224\3\2\2\2x\u0227\3\2\2\2z\u022e\3\2\2\2|"+
		"\u0232\3\2\2\2~\u0234\3\2\2\2\u0080\u0236\3\2\2\2\u0082\u023a\3\2\2\2"+
		"\u0084\u0248\3\2\2\2\u0086\u024a\3\2\2\2\u0088\u024c\3\2\2\2\u008a\u008b"+
		"\5\4\3\2\u008b\3\3\2\2\2\u008c\u0090\5\6\4\2\u008d\u008f\5\b\5\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\5\60\31\2\u0094"+
		"\5\3\2\2\2\u0095\u0098\5\n\6\2\u0096\u0098\5\16\b\2\u0097\u0095\3\2\2"+
		"\2\u0097\u0096\3\2\2\2\u0098\7\3\2\2\2\u0099\u009d\5\6\4\2\u009a\u009d"+
		"\5\32\16\2\u009b\u009d\5.\30\2\u009c\u0099\3\2\2\2\u009c\u009a\3\2\2\2"+
		"\u009c\u009b\3\2\2\2\u009d\t\3\2\2\2\u009e\u009f\7\f\2\2\u009f\u00a4\5"+
		"\f\7\2\u00a0\u00a1\7\23\2\2\u00a1\u00a3\5\f\7\2\u00a2\u00a0\3\2\2\2\u00a3"+
		"\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\13\3\2\2"+
		"\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7<\2\2\u00a8\u00a9\7#\2\2\u00a9\u00aa"+
		"\5\24\13\2\u00aa\r\3\2\2\2\u00ab\u00ac\7\37\2\2\u00ac\u00b1\5\20\t\2\u00ad"+
		"\u00ae\7\23\2\2\u00ae\u00b0\5\20\t\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3"+
		"\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\17\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b4\u00b5\7<\2\2\u00b5\u00bb\7\16\2\2\u00b6\u00bc\5\24"+
		"\13\2\u00b7\u00bc\5\4\3\2\u00b8\u00bc\5\30\r\2\u00b9\u00bc\5<\37\2\u00ba"+
		"\u00bc\5|?\2\u00bb\u00b6\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00b8\3\2\2"+
		"\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\21\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\23\3\2\2\2\u00bf\u00c0\7\30\2\2\u00c0\u00c1\7\17\2\2\u00c1"+
		"\u00c3\5\26\f\2\u00c2\u00c4\5> \2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\7%\2\2\u00c6\u00cc\3\2\2\2\u00c7"+
		"\u00c9\5\26\f\2\u00c8\u00ca\5> \2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cc"+
		"\25\3\2\2\2\u00cd\u00ce\7\32\2\2\u00ce\u00cf\7\17\2\2\u00cf\u00d0\7B\2"+
		"\2\u00d0\u00d7\7%\2\2\u00d1\u00d2\7\'\2\2\u00d2\u00d3\7\17\2\2\u00d3\u00d4"+
		"\7B\2\2\u00d4\u00d7\7%\2\2\u00d5\u00d7\7<\2\2\u00d6\u00cd\3\2\2\2\u00d6"+
		"\u00d1\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7\27\3\2\2\2\u00d8\u00d9\t\2\2"+
		"\2\u00d9\u00da\7\17\2\2\u00da\u00db\7<\2\2\u00db\u00dc\7%\2\2\u00dc\31"+
		"\3\2\2\2\u00dd\u00de\7\36\2\2\u00de\u00df\5\34\17\2\u00df\33\3\2\2\2\u00e0"+
		"\u00e5\5\36\20\2\u00e1\u00e2\7-\2\2\u00e2\u00e4\5\36\20\2\u00e3\u00e1"+
		"\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\35\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ed\5 \21\2\u00e9\u00ea\7.\2\2"+
		"\u00ea\u00ec\5 \21\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\37\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f1\7/\2\2\u00f1\u00f2\7\17\2\2\u00f2\u00f3\5\"\22\2\u00f3\u00f4\7"+
		"%\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f7\5\"\22\2\u00f6\u00f0\3\2\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7!\3\2\2\2\u00f8\u00fc\5$\23\2\u00f9\u00fc\5*\26\2"+
		"\u00fa\u00fc\5,\27\2\u00fb\u00f8\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fa"+
		"\3\2\2\2\u00fc#\3\2\2\2\u00fd\u0106\5<\37\2\u00fe\u0101\5&\24\2\u00ff"+
		"\u0101\5(\25\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2"+
		"\2\2\u0102\u0105\5<\37\2\u0103\u0105\5|?\2\u0104\u0102\3\2\2\2\u0104\u0103"+
		"\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0100\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"%\3\2\2\2\u0108\u0109\t\3\2\2\u0109\'\3\2\2\2\u010a\u010b\t\4\2\2\u010b"+
		")\3\2\2\2\u010c\u010d\7\34\2\2\u010d\u010e\7\17\2\2\u010e\u010f\7<\2\2"+
		"\u010f\u0110\7\23\2\2\u0110\u0111\7B\2\2\u0111\u0112\7%\2\2\u0112+\3\2"+
		"\2\2\u0113\u0114\7)\2\2\u0114\u0115\7\17\2\2\u0115\u0116\7<\2\2\u0116"+
		"\u0117\7%\2\2\u0117-\3\2\2\2\u0118\u0119\7\3\2\2\u0119\u011e\7<\2\2\u011a"+
		"\u011b\7\23\2\2\u011b\u011d\7<\2\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2"+
		"\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f/\3\2\2\2\u0120\u011e"+
		"\3\2\2\2\u0121\u0129\7\7\2\2\u0122\u012a\5\62\32\2\u0123\u0126\5\30\r"+
		"\2\u0124\u0126\7<\2\2\u0125\u0123\3\2\2\2\u0125\u0124\3\2\2\2\u0126\u0127"+
		"\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129"+
		"\u0122\3\2\2\2\u0129\u0125\3\2\2\2\u012a\61\3\2\2\2\u012b\u012c\7\65\2"+
		"\2\u012c\u0130\5:\36\2\u012d\u012f\5\66\34\2\u012e\u012d\3\2\2\2\u012f"+
		"\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0144\3\2"+
		"\2\2\u0132\u0130\3\2\2\2\u0133\u0145\7@\2\2\u0134\u013d\79\2\2\u0135\u013c"+
		"\5\62\32\2\u0136\u0137\7=\2\2\u0137\u0138\5\64\33\2\u0138\u0139\7>\2\2"+
		"\u0139\u013c\3\2\2\2\u013a\u013c\5:\36\2\u013b\u0135\3\2\2\2\u013b\u0136"+
		"\3\2\2\2\u013b\u013a\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d"+
		"\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0141\7?"+
		"\2\2\u0141\u0142\5:\36\2\u0142\u0143\79\2\2\u0143\u0145\3\2\2\2\u0144"+
		"\u0133\3\2\2\2\u0144\u0134\3\2\2\2\u0145\63\3\2\2\2\u0146\u0149\7<\2\2"+
		"\u0147\u0149\5\30\r\2\u0148\u0146\3\2\2\2\u0148\u0147\3\2\2\2\u0149\u014b"+
		"\3\2\2\2\u014a\u014c\7\23\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2"+
		"\u014c\u014e\3\2\2\2\u014d\u0148\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d"+
		"\3\2\2\2\u014f\u0150\3\2\2\2\u0150\65\3\2\2\2\u0151\u014f\3\2\2\2\u0152"+
		"\u0153\7A\2\2\u0153\u0154\7\61\2\2\u0154\u015c\7\"\2\2\u0155\u0156\7="+
		"\2\2\u0156\u0157\58\35\2\u0157\u0158\7>\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u015b\5:\36\2\u015a\u0155\3\2\2\2\u015a\u0159\3\2\2\2\u015b\u015e\3\2"+
		"\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015f\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015f\u0160\7\"\2\2\u0160\67\3\2\2\2\u0161\u0164\7<\2\2"+
		"\u0162\u0164\5\30\r\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u01649\3"+
		"\2\2\2\u0165\u0168\5\u0084C\2\u0166\u0168\7A\2\2\u0167\u0165\3\2\2\2\u0167"+
		"\u0166\3\2\2\2\u0168;\3\2\2\2\u0169\u016a\b\37\1\2\u016a\u016b\7\17\2"+
		"\2\u016b\u016c\5<\37\2\u016c\u016d\7%\2\2\u016d\u0177\3\2\2\2\u016e\u0170"+
		"\7(\2\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u0177\5~@\2\u0172\u0174\7(\2\2\u0173\u0172\3\2\2\2\u0173\u0174\3\2\2"+
		"\2\u0174\u0175\3\2\2\2\u0175\u0177\7<\2\2\u0176\u0169\3\2\2\2\u0176\u016f"+
		"\3\2\2\2\u0176\u0173\3\2\2\2\u0177\u0180\3\2\2\2\u0178\u0179\f\7\2\2\u0179"+
		"\u017a\t\5\2\2\u017a\u017f\5<\37\b\u017b\u017c\f\6\2\2\u017c\u017d\t\6"+
		"\2\2\u017d\u017f\5<\37\7\u017e\u0178\3\2\2\2\u017e\u017b\3\2\2\2\u017f"+
		"\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181=\3\2\2\2"+
		"\u0182\u0180\3\2\2\2\u0183\u0184\5@!\2\u0184?\3\2\2\2\u0185\u0186\5B\""+
		"\2\u0186A\3\2\2\2\u0187\u0188\5F$\2\u0188\u0189\5D#\2\u0189C\3\2\2\2\u018a"+
		"\u018b\7-\2\2\u018b\u018c\5F$\2\u018c\u018d\5D#\2\u018d\u0190\3\2\2\2"+
		"\u018e\u0190\3\2\2\2\u018f\u018a\3\2\2\2\u018f\u018e\3\2\2\2\u0190E\3"+
		"\2\2\2\u0191\u0192\5J&\2\u0192\u0193\5H%\2\u0193G\3\2\2\2\u0194\u0195"+
		"\7.\2\2\u0195\u0196\5J&\2\u0196\u0197\5H%\2\u0197\u019a\3\2\2\2\u0198"+
		"\u019a\3\2\2\2\u0199\u0194\3\2\2\2\u0199\u0198\3\2\2\2\u019aI\3\2\2\2"+
		"\u019b\u019c\5N(\2\u019c\u019d\5L\'\2\u019dK\3\2\2\2\u019e\u019f\7\61"+
		"\2\2\u019f\u01a0\5N(\2\u01a0\u01a1\5L\'\2\u01a1\u01a8\3\2\2\2\u01a2\u01a3"+
		"\7\63\2\2\u01a3\u01a4\5N(\2\u01a4\u01a5\5L\'\2\u01a5\u01a8\3\2\2\2\u01a6"+
		"\u01a8\3\2\2\2\u01a7\u019e\3\2\2\2\u01a7\u01a2\3\2\2\2\u01a7\u01a6\3\2"+
		"\2\2\u01a8M\3\2\2\2\u01a9\u01aa\5R*\2\u01aa\u01ab\5P)\2\u01abO\3\2\2\2"+
		"\u01ac\u01ad\7\65\2\2\u01ad\u01ae\5R*\2\u01ae\u01af\5P)\2\u01af\u01be"+
		"\3\2\2\2\u01b0\u01b1\79\2\2\u01b1\u01b2\5R*\2\u01b2\u01b3\5P)\2\u01b3"+
		"\u01be\3\2\2\2\u01b4\u01b5\7\67\2\2\u01b5\u01b6\5R*\2\u01b6\u01b7\5P)"+
		"\2\u01b7\u01be\3\2\2\2\u01b8\u01b9\7;\2\2\u01b9\u01ba\5R*\2\u01ba\u01bb"+
		"\5P)\2\u01bb\u01be\3\2\2\2\u01bc\u01be\3\2\2\2\u01bd\u01ac\3\2\2\2\u01bd"+
		"\u01b0\3\2\2\2\u01bd\u01b4\3\2\2\2\u01bd\u01b8\3\2\2\2\u01bd\u01bc\3\2"+
		"\2\2\u01beQ\3\2\2\2\u01bf\u01c0\5V,\2\u01c0\u01c1\5T+\2\u01c1S\3\2\2\2"+
		"\u01c2\u01c3\7&\2\2\u01c3\u01c4\5V,\2\u01c4\u01c5\5T+\2\u01c5\u01cc\3"+
		"\2\2\2\u01c6\u01c7\7(\2\2\u01c7\u01c8\5V,\2\u01c8\u01c9\5T+\2\u01c9\u01cc"+
		"\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb\u01c2\3\2\2\2\u01cb\u01c6\3\2\2\2\u01cb"+
		"\u01ca\3\2\2\2\u01ccU\3\2\2\2\u01cd\u01ce\5Z.\2\u01ce\u01cf\5X-\2\u01cf"+
		"W\3\2\2\2\u01d0\u01d1\7\21\2\2\u01d1\u01d2\5Z.\2\u01d2\u01d3\5X-\2\u01d3"+
		"\u01de\3\2\2\2\u01d4\u01d5\7!\2\2\u01d5\u01d6\5Z.\2\u01d6\u01d7\5X-\2"+
		"\u01d7\u01de\3\2\2\2\u01d8\u01d9\7\26\2\2\u01d9\u01da\5Z.\2\u01da\u01db"+
		"\5X-\2\u01db\u01de\3\2\2\2\u01dc\u01de\3\2\2\2\u01dd\u01d0\3\2\2\2\u01dd"+
		"\u01d4\3\2\2\2\u01dd\u01d8\3\2\2\2\u01dd\u01dc\3\2\2\2\u01deY\3\2\2\2"+
		"\u01df\u01e3\5\\/\2\u01e0\u01e1\7(\2\2\u01e1\u01e3\5Z.\2\u01e2\u01df\3"+
		"\2\2\2\u01e2\u01e0\3\2\2\2\u01e3[\3\2\2\2\u01e4\u01e9\5^\60\2\u01e5\u01e6"+
		"\7\31\2\2\u01e6\u01e8\5^\60\2\u01e7\u01e5\3\2\2\2\u01e8\u01eb\3\2\2\2"+
		"\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea]\3\2\2\2\u01eb\u01e9\3"+
		"\2\2\2\u01ec\u01ee\5t;\2\u01ed\u01ef\5`\61\2\u01ee\u01ed\3\2\2\2\u01ee"+
		"\u01ef\3\2\2\2\u01ef\u01f2\3\2\2\2\u01f0\u01f2\5`\61\2\u01f1\u01ec\3\2"+
		"\2\2\u01f1\u01f0\3\2\2\2\u01f2_\3\2\2\2\u01f3\u01f4\7+\2\2\u01f4\u01f9"+
		"\5b\62\2\u01f5\u01f6\7,\2\2\u01f6\u01f9\5b\62\2\u01f7\u01f9\5b\62\2\u01f8"+
		"\u01f3\3\2\2\2\u01f8\u01f5\3\2\2\2\u01f8\u01f7\3\2\2\2\u01f9a\3\2\2\2"+
		"\u01fa\u01fe\5f\64\2\u01fb\u01fd\5d\63\2\u01fc\u01fb\3\2\2\2\u01fd\u0200"+
		"\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ffc\3\2\2\2\u0200"+
		"\u01fe\3\2\2\2\u0201\u0202\7+\2\2\u0202\u0206\5f\64\2\u0203\u0204\7,\2"+
		"\2\u0204\u0206\5f\64\2\u0205\u0201\3\2\2\2\u0205\u0203\3\2\2\2\u0206e"+
		"\3\2\2\2\u0207\u0208\5h\65\2\u0208g\3\2\2\2\u0209\u020a\5j\66\2\u020a"+
		"\u020b\5v<\2\u020bi\3\2\2\2\u020c\u020d\5l\67\2\u020dk\3\2\2\2\u020e\u0210"+
		"\7\35\2\2\u020f\u020e\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0211\3\2\2\2"+
		"\u0211\u0212\5n8\2\u0212m\3\2\2\2\u0213\u0216\5p9\2\u0214\u0216\5r:\2"+
		"\u0215\u0213\3\2\2\2\u0215\u0214\3\2\2\2\u0216o\3\2\2\2\u0217\u0218\5"+
		"\u0086D\2\u0218q\3\2\2\2\u0219\u021d\7!\2\2\u021a\u021d\7\26\2\2\u021b"+
		"\u021d\5\u0088E\2\u021c\u0219\3\2\2\2\u021c\u021a\3\2\2\2\u021c\u021b"+
		"\3\2\2\2\u021ds\3\2\2\2\u021e\u021f\5z>\2\u021f\u0220\5v<\2\u0220u\3\2"+
		"\2\2\u0221\u0223\5x=\2\u0222\u0221\3\2\2\2\u0223\u0226\3\2\2\2\u0224\u0222"+
		"\3\2\2\2\u0224\u0225\3\2\2\2\u0225w\3\2\2\2\u0226\u0224\3\2\2\2\u0227"+
		"\u0228\7\27\2\2\u0228\u0229\5@!\2\u0229\u022a\7\33\2\2\u022ay\3\2\2\2"+
		"\u022b\u022f\5|?\2\u022c\u022f\5\u0080A\2\u022d\u022f\5\u0082B\2\u022e"+
		"\u022b\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022d\3\2\2\2\u022f{\3\2\2\2"+
		"\u0230\u0233\5~@\2\u0231\u0233\7B\2\2\u0232\u0230\3\2\2\2\u0232\u0231"+
		"\3\2\2\2\u0233}\3\2\2\2\u0234\u0235\t\7\2\2\u0235\177\3\2\2\2\u0236\u0237"+
		"\7\17\2\2\u0237\u0238\5@!\2\u0238\u0239\7%\2\2\u0239\u0081\3\2\2\2\u023a"+
		"\u023b\5\u0084C\2\u023b\u0244\7\17\2\2\u023c\u0241\5@!\2\u023d\u023e\7"+
		"\23\2\2\u023e\u0240\5@!\2\u023f\u023d\3\2\2\2\u0240\u0243\3\2\2\2\u0241"+
		"\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0245\3\2\2\2\u0243\u0241\3\2"+
		"\2\2\u0244\u023c\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0246\3\2\2\2\u0246"+
		"\u0247\7%\2\2\u0247\u0083\3\2\2\2\u0248\u0249\t\b\2\2\u0249\u0085\3\2"+
		"\2\2\u024a\u024b\7*\2\2\u024b\u0087\3\2\2\2\u024c\u024d\7A\2\2\u024d\u0089"+
		"\3\2\2\2<\u0090\u0097\u009c\u00a4\u00b1\u00bb\u00c3\u00c9\u00cb\u00d6"+
		"\u00e5\u00ed\u00f6\u00fb\u0100\u0104\u0106\u011e\u0125\u0127\u0129\u0130"+
		"\u013b\u013d\u0144\u0148\u014b\u014f\u015a\u015c\u0163\u0167\u016f\u0173"+
		"\u0176\u017e\u0180\u018f\u0199\u01a7\u01bd\u01cb\u01dd\u01e2\u01e9\u01ee"+
		"\u01f1\u01f8\u01fe\u0205\u020f\u0215\u021c\u0224\u022e\u0232\u0241\u0244";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}