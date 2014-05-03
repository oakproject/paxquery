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
		T__31=1, T__30=2, T__29=3, T__28=4, T__27=5, T__26=6, T__25=7, T__24=8, 
		T__23=9, T__22=10, T__21=11, T__20=12, T__19=13, T__18=14, T__17=15, T__16=16, 
		T__15=17, T__14=18, T__13=19, T__12=20, T__11=21, T__10=22, T__9=23, T__8=24, 
		T__7=25, T__6=26, T__5=27, T__4=28, T__3=29, T__2=30, T__1=31, T__0=32, 
		AGGR_FUNCT=33, TEXTFUNCTION=34, SLASH=35, SLASHSLASH=36, OR=37, AND=38, 
		NOT=39, EQ=40, EQ_S=41, NE=42, NE_S=43, LT=44, LT_S=45, LE=46, LE_S=47, 
		GT=48, GT_S=49, GE=50, GE_S=51, VAR=52, LEFTCURL=53, RIGHTCURL=54, OPEN_ATTR_VAR_DOUBLE=55, 
		OPEN_ATTR_VAR_SINGLE=56, CLOSE_ATTR_VAR_DOUBLE=57, CLOSE_ATTR_VAR_SINGLE=58, 
		OPEN_CLOSING_TAG=59, CLOSE_OPENING_TAG=60, QNAME_TOKEN=61, SINGLE_QUOTE=62, 
		DOUBLE_QUOTE=63, COMMA=64, STRING_LITERAL=65, REFERENCE=66, ENTITY_REF=67, 
		CHAR_REF=68, INTEGER_LITERAL=69, DECIMAL_LITERAL=70, DIGITS=71, WS=72;
	public static final String[] tokenNames = {
		"<INVALID>", "'group by'", "'substring'", "'true'", "'return'", "'>>'", 
		"'<<'", "'concat'", "'for'", "':='", "'('", "'is'", "'*'", "'false'", 
		"'ceiling'", "'mod'", "'['", "'distinct-values'", "'|'", "'collection'", 
		"']'", "'contains'", "'@'", "'where'", "'let'", "'div'", "'in'", "'floor'", 
		"')'", "'+'", "'doc'", "'-'", "'empty'", "AGGR_FUNCT", "'text()'", "'/'", 
		"'//'", "'or'", "'and'", "'not'", "'eq'", "'='", "'ne'", "'!='", "'lt'", 
		"'<'", "'le'", "'<='", "'gt'", "'>'", "'ge'", "'>='", "VAR", "'{'", "'}'", 
		"OPEN_ATTR_VAR_DOUBLE", "OPEN_ATTR_VAR_SINGLE", "CLOSE_ATTR_VAR_DOUBLE", 
		"CLOSE_ATTR_VAR_SINGLE", "OPEN_CLOSING_TAG", "CLOSE_OPENING_TAG", "QNAME_TOKEN", 
		"'''", "'\"'", "','", "STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", 
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
		RULE_attInner = 27, RULE_attInner2 = 28, RULE_eaName = 29, RULE_arithmeticExpr_xq = 30, 
		RULE_xpath = 31, RULE_expr = 32, RULE_orExpr = 33, RULE_orExpr2 = 34, 
		RULE_andExpr = 35, RULE_andExpr2 = 36, RULE_equalityExpr = 37, RULE_equalityExpr2 = 38, 
		RULE_relationalExpr = 39, RULE_relationalExpr2 = 40, RULE_additiveExpr = 41, 
		RULE_additiveExpr2 = 42, RULE_multiplicativeExpr = 43, RULE_multiplicativeExpr2 = 44, 
		RULE_unaryExpr = 45, RULE_unionExpr = 46, RULE_valueExpr = 47, RULE_pathExpr = 48, 
		RULE_relativePathExpr = 49, RULE_relativePathExpr2 = 50, RULE_stepExpr = 51, 
		RULE_axisStep = 52, RULE_forwardStep = 53, RULE_abbrevForwardStep = 54, 
		RULE_nodeTest = 55, RULE_kindTest = 56, RULE_nameTest = 57, RULE_filterExpr = 58, 
		RULE_predicateList = 59, RULE_predicate = 60, RULE_primaryExpr = 61, RULE_literal = 62, 
		RULE_numericLiteral = 63, RULE_parenthesizedExpr = 64, RULE_functionCall = 65, 
		RULE_functionName = 66, RULE_textTest = 67, RULE_qName = 68;
	public static final String[] ruleNames = {
		"xquery", "flwrexpr", "initial", "middle", "forStat", "forBinding", "let", 
		"letBinding", "arithExpr", "pathExpr_xq", "pathExprInner_xq", "aggrExpr", 
		"where", "orExpr_xq", "andExpr_xq", "boolExpr_xq", "boolExprInner_xq", 
		"pred", "vcmp", "ncmp", "contains", "empty", "groupBy", "returnStat", 
		"eleConst", "eleConstInner", "att", "attInner", "attInner2", "eaName", 
		"arithmeticExpr_xq", "xpath", "expr", "orExpr", "orExpr2", "andExpr", 
		"andExpr2", "equalityExpr", "equalityExpr2", "relationalExpr", "relationalExpr2", 
		"additiveExpr", "additiveExpr2", "multiplicativeExpr", "multiplicativeExpr2", 
		"unaryExpr", "unionExpr", "valueExpr", "pathExpr", "relativePathExpr", 
		"relativePathExpr2", "stepExpr", "axisStep", "forwardStep", "abbrevForwardStep", 
		"nodeTest", "kindTest", "nameTest", "filterExpr", "predicateList", "predicate", 
		"primaryExpr", "literal", "numericLiteral", "parenthesizedExpr", "functionCall", 
		"functionName", "textTest", "qName"
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXquery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XqueryContext xquery() throws RecognitionException {
		XqueryContext _localctx = new XqueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); flwrexpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFlwrexpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlwrexprContext flwrexpr() throws RecognitionException {
		FlwrexprContext _localctx = new FlwrexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_flwrexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); initial();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 8) | (1L << 23) | (1L << 24))) != 0)) {
				{
				{
				setState(141); middle();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147); returnStat();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitInitial(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitialContext initial() throws RecognitionException {
		InitialContext _localctx = new InitialContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initial);
		try {
			setState(151);
			switch (_input.LA(1)) {
			case 8:
				enterOuterAlt(_localctx, 1);
				{
				setState(149); forStat();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 2);
				{
				setState(150); let();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitMiddle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MiddleContext middle() throws RecognitionException {
		MiddleContext _localctx = new MiddleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_middle);
		try {
			setState(156);
			switch (_input.LA(1)) {
			case 8:
			case 24:
				enterOuterAlt(_localctx, 1);
				{
				setState(153); initial();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 2);
				{
				setState(154); where();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 3);
				{
				setState(155); groupBy();
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public ForBindingContext forBinding(int i) {
			return getRuleContext(ForBindingContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public ForStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitForStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatContext forStat() throws RecognitionException {
		ForStatContext _localctx = new ForStatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_forStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(8);
			setState(159); forBinding();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(160); match(COMMA);
				setState(161); forBinding();
				}
				}
				setState(166);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitForBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForBindingContext forBinding() throws RecognitionException {
		ForBindingContext _localctx = new ForBindingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_forBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); match(VAR);
			setState(168); match(26);
			setState(169); pathExpr_xq();
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public LetBindingContext letBinding(int i) {
			return getRuleContext(LetBindingContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_let);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(24);
			setState(172); letBinding();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(173); match(COMMA);
				setState(174); letBinding();
				}
				}
				setState(179);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitLetBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBindingContext letBinding() throws RecognitionException {
		LetBindingContext _localctx = new LetBindingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_letBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(VAR);
			setState(181); match(9);
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(182); pathExpr_xq();
				}
				break;

			case 2:
				{
				setState(183); flwrexpr();
				}
				break;

			case 3:
				{
				setState(184); aggrExpr();
				}
				break;

			case 4:
				{
				setState(185); arithmeticExpr_xq(0);
				}
				break;

			case 5:
				{
				setState(186); literal();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitArithExpr(this);
			else return visitor.visitChildren(this);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExpr_xq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathExpr_xqContext pathExpr_xq() throws RecognitionException {
		PathExpr_xqContext _localctx = new PathExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pathExpr_xq);
		int _la;
		try {
			setState(203);
			switch (_input.LA(1)) {
			case 17:
				enterOuterAlt(_localctx, 1);
				{
				setState(191); match(17);
				setState(192); match(10);
				setState(193); pathExprInner_xq();
				setState(195);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 13) | (1L << 14) | (1L << 15) | (1L << 22) | (1L << 25) | (1L << 27) | (1L << 31) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (STRING_LITERAL - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (DECIMAL_LITERAL - 65)))) != 0)) {
					{
					setState(194); xpath();
					}
				}

				setState(197); match(28);
				}
				break;
			case 19:
			case 30:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(199); pathExprInner_xq();
				setState(201);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 13) | (1L << 14) | (1L << 15) | (1L << 22) | (1L << 25) | (1L << 27) | (1L << 31) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (STRING_LITERAL - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (DECIMAL_LITERAL - 65)))) != 0)) {
					{
					setState(200); xpath();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExprInner_xq_doc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PathExprInner_xq_VARContext extends PathExprInner_xqContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public PathExprInner_xq_VARContext(PathExprInner_xqContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExprInner_xq_VAR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PathExprInner_xq_collectionContext extends PathExprInner_xqContext {
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public PathExprInner_xq_collectionContext(PathExprInner_xqContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExprInner_xq_collection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathExprInner_xqContext pathExprInner_xq() throws RecognitionException {
		PathExprInner_xqContext _localctx = new PathExprInner_xqContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pathExprInner_xq);
		try {
			setState(214);
			switch (_input.LA(1)) {
			case 19:
				_localctx = new PathExprInner_xq_collectionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(205); match(19);
				setState(206); match(10);
				setState(207); match(STRING_LITERAL);
				setState(208); match(28);
				}
				break;
			case 30:
				_localctx = new PathExprInner_xq_docContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(209); match(30);
				setState(210); match(10);
				setState(211); match(STRING_LITERAL);
				setState(212); match(28);
				}
				break;
			case VAR:
				_localctx = new PathExprInner_xq_VARContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(213); match(VAR);
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
		public TerminalNode AGGR_FUNCT() { return getToken(XQueryParser.AGGR_FUNCT, 0); }
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public AggrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggrExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAggrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggrExprContext aggrExpr() throws RecognitionException {
		AggrExprContext _localctx = new AggrExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_aggrExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); match(AGGR_FUNCT);
			setState(217); match(10);
			setState(218); match(VAR);
			setState(219); match(28);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(23);
			setState(222); orExpr_xq();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitOrExpr_xq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpr_xqContext orExpr_xq() throws RecognitionException {
		OrExpr_xqContext _localctx = new OrExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_orExpr_xq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); andExpr_xq();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(225); match(OR);
				setState(226); andExpr_xq();
				}
				}
				setState(231);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAndExpr_xq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpr_xqContext andExpr_xq() throws RecognitionException {
		AndExpr_xqContext _localctx = new AndExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_andExpr_xq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); boolExpr_xq();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(233); match(AND);
				setState(234); boolExpr_xq();
				}
				}
				setState(239);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitBoolExpr_xq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolExpr_xqContext boolExpr_xq() throws RecognitionException {
		BoolExpr_xqContext _localctx = new BoolExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_boolExpr_xq);
		try {
			setState(246);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(240); match(NOT);
				setState(241); match(10);
				setState(242); boolExprInner_xq();
				setState(243); match(28);
				}
				break;
			case 10:
			case 21:
			case 31:
			case 32:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(245); boolExprInner_xq();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitBoolExprInner_xq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolExprInner_xqContext boolExprInner_xq() throws RecognitionException {
		BoolExprInner_xqContext _localctx = new BoolExprInner_xqContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolExprInner_xq);
		try {
			setState(251);
			switch (_input.LA(1)) {
			case 10:
			case 31:
			case VAR:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(248); pred();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				setState(249); contains();
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 3);
				{
				setState(250); empty();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredContext pred() throws RecognitionException {
		PredContext _localctx = new PredContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pred);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); arithmeticExpr_xq(0);
			setState(262);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 6) | (1L << 11) | (1L << EQ) | (1L << EQ_S) | (1L << NE) | (1L << NE_S) | (1L << LT) | (1L << LT_S) | (1L << LE) | (1L << LE_S) | (1L << GT) | (1L << GT_S) | (1L << GE) | (1L << GE_S))) != 0)) {
				{
				setState(256);
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
					setState(254); vcmp();
					}
					break;
				case 5:
				case 6:
				case 11:
					{
					setState(255); ncmp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(260);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(258); arithmeticExpr_xq(0);
					}
					break;

				case 2:
					{
					setState(259); literal();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitVcmp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VcmpContext vcmp() throws RecognitionException {
		VcmpContext _localctx = new VcmpContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_vcmp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNcmp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NcmpContext ncmp() throws RecognitionException {
		NcmpContext _localctx = new NcmpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ncmp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 6) | (1L << 11))) != 0)) ) {
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
		public TerminalNode COMMA() { return getToken(XQueryParser.COMMA, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public ContainsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitContains(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainsContext contains() throws RecognitionException {
		ContainsContext _localctx = new ContainsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_contains);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); match(21);
			setState(269); match(10);
			setState(270); match(VAR);
			setState(271); match(COMMA);
			setState(272); match(STRING_LITERAL);
			setState(273); match(28);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyContext empty() throws RecognitionException {
		EmptyContext _localctx = new EmptyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_empty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(32);
			setState(276); match(10);
			setState(277); match(VAR);
			setState(278); match(28);
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode VAR(int i) {
			return getToken(XQueryParser.VAR, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public GroupByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBy; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitGroupBy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByContext groupBy() throws RecognitionException {
		GroupByContext _localctx = new GroupByContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_groupBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); match(1);
			setState(281); match(VAR);
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(282); match(COMMA);
				setState(283); match(VAR);
				}
				}
				setState(288);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_returnStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289); match(4);
			setState(297);
			switch (_input.LA(1)) {
			case LT_S:
				{
				setState(290); eleConst();
				}
				break;
			case AGGR_FUNCT:
			case VAR:
				{
				setState(293); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(293);
					switch (_input.LA(1)) {
					case AGGR_FUNCT:
						{
						setState(291); aggrExpr();
						}
						break;
					case VAR:
						{
						setState(292); match(VAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(295); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AGGR_FUNCT || _la==VAR );
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
		public TerminalNode OPEN_CLOSING_TAG() { return getToken(XQueryParser.OPEN_CLOSING_TAG, 0); }
		public List<EleConstContext> eleConst() {
			return getRuleContexts(EleConstContext.class);
		}
		public List<TerminalNode> LEFTCURL() { return getTokens(XQueryParser.LEFTCURL); }
		public List<TerminalNode> RIGHTCURL() { return getTokens(XQueryParser.RIGHTCURL); }
		public TerminalNode CLOSE_OPENING_TAG() { return getToken(XQueryParser.CLOSE_OPENING_TAG, 0); }
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEleConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EleConstContext eleConst() throws RecognitionException {
		EleConstContext _localctx = new EleConstContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_eleConst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); match(LT_S);
			setState(300); eaName();
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 13) | (1L << 14) | (1L << 27) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0)) {
				{
				{
				setState(301); att();
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(323);
			switch (_input.LA(1)) {
			case CLOSE_OPENING_TAG:
				{
				setState(307); match(CLOSE_OPENING_TAG);
				}
				break;
			case GT_S:
				{
				{
				setState(308); match(GT_S);
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LT_S || _la==LEFTCURL) {
					{
					setState(314);
					switch (_input.LA(1)) {
					case LT_S:
						{
						setState(309); eleConst();
						}
						break;
					case LEFTCURL:
						{
						setState(310); match(LEFTCURL);
						setState(311); eleConstInner();
						setState(312); match(RIGHTCURL);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(318);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(319); match(OPEN_CLOSING_TAG);
				{
				setState(320); eaName();
				}
				setState(321); match(GT_S);
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public List<AggrExprContext> aggrExpr() {
			return getRuleContexts(AggrExprContext.class);
		}
		public TerminalNode VAR(int i) {
			return getToken(XQueryParser.VAR, i);
		}
		public AggrExprContext aggrExpr(int i) {
			return getRuleContext(AggrExprContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public EleConstInnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eleConstInner; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEleConstInner(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EleConstInnerContext eleConstInner() throws RecognitionException {
		EleConstInnerContext _localctx = new EleConstInnerContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_eleConstInner);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(325); match(VAR);
				}
				break;
			case AGGR_FUNCT:
				{
				setState(326); aggrExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(329); match(COMMA);
				setState(332);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(330); match(VAR);
					}
					break;
				case AGGR_FUNCT:
					{
					setState(331); aggrExpr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(338);
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
		public EaNameContext eaName() {
			return getRuleContext(EaNameContext.class,0);
		}
		public AttInnerContext attInner() {
			return getRuleContext(AttInnerContext.class,0);
		}
		public AttContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_att; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAtt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttContext att() throws RecognitionException {
		AttContext _localctx = new AttContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_att);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339); eaName();
			setState(340); match(EQ_S);
			setState(341); attInner();
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
		public TerminalNode CLOSE_ATTR_VAR_DOUBLE() { return getToken(XQueryParser.CLOSE_ATTR_VAR_DOUBLE, 0); }
		public TerminalNode OPEN_ATTR_VAR_DOUBLE() { return getToken(XQueryParser.OPEN_ATTR_VAR_DOUBLE, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public TerminalNode CLOSE_ATTR_VAR_SINGLE() { return getToken(XQueryParser.CLOSE_ATTR_VAR_SINGLE, 0); }
		public AttInner2Context attInner2() {
			return getRuleContext(AttInner2Context.class,0);
		}
		public TerminalNode OPEN_ATTR_VAR_SINGLE() { return getToken(XQueryParser.OPEN_ATTR_VAR_SINGLE, 0); }
		public AttInnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attInner; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAttInner(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttInnerContext attInner() throws RecognitionException {
		AttInnerContext _localctx = new AttInnerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attInner);
		try {
			setState(352);
			switch (_input.LA(1)) {
			case OPEN_ATTR_VAR_DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(343); match(OPEN_ATTR_VAR_DOUBLE);
				setState(344); attInner2();
				setState(345); match(CLOSE_ATTR_VAR_DOUBLE);
				}
				break;
			case OPEN_ATTR_VAR_SINGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(347); match(OPEN_ATTR_VAR_SINGLE);
				setState(348); attInner2();
				setState(349); match(CLOSE_ATTR_VAR_SINGLE);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(351); match(STRING_LITERAL);
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

	public static class AttInner2Context extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public AggrExprContext aggrExpr() {
			return getRuleContext(AggrExprContext.class,0);
		}
		public AttInner2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attInner2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAttInner2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttInner2Context attInner2() throws RecognitionException {
		AttInner2Context _localctx = new AttInner2Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_attInner2);
		try {
			setState(356);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(354); match(VAR);
				}
				break;
			case AGGR_FUNCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(355); aggrExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEaName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EaNameContext eaName() throws RecognitionException {
		EaNameContext _localctx = new EaNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_eaName);
		try {
			setState(360);
			switch (_input.LA(1)) {
			case 2:
			case 3:
			case 7:
			case 13:
			case 14:
			case 27:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(358); functionName();
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(359); match(QNAME_TOKEN);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitArithmeticExpr_xq(this);
			else return visitor.visitChildren(this);
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
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_arithmeticExpr_xq, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(363); match(10);
				setState(364); arithmeticExpr_xq(0);
				setState(365); match(28);
				}
				break;

			case 2:
				{
				setState(368);
				_la = _input.LA(1);
				if (_la==31) {
					{
					setState(367); match(31);
					}
				}

				setState(370); numericLiteral();
				}
				break;

			case 3:
				{
				setState(372);
				_la = _input.LA(1);
				if (_la==31) {
					{
					setState(371); match(31);
					}
				}

				setState(374); match(VAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(385);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(383);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(377);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(378);
						_la = _input.LA(1);
						if ( !(_la==29 || _la==31) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(379); arithmeticExpr_xq(6);
						}
						break;

					case 2:
						{
						_localctx = new ArithmeticExpr_xqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_arithmeticExpr_xq);
						setState(380);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(381);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 15) | (1L << 25))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(382); arithmeticExpr_xq(5);
						}
						break;
					}
					} 
				}
				setState(387);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXpath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XpathContext xpath() throws RecognitionException {
		XpathContext _localctx = new XpathContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_xpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388); expr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390); orExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_orExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392); andExpr();
			setState(393); orExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitOrExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpr2Context orExpr2() throws RecognitionException {
		OrExpr2Context _localctx = new OrExpr2Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_orExpr2);
		try {
			setState(400);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(395); match(OR);
				setState(396); andExpr();
				setState(397); orExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_andExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402); equalityExpr();
			setState(403); andExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAndExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpr2Context andExpr2() throws RecognitionException {
		AndExpr2Context _localctx = new AndExpr2Context(_ctx, getState());
		enterRule(_localctx, 72, RULE_andExpr2);
		try {
			setState(410);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(405); match(AND);
				setState(406); equalityExpr();
				setState(407); andExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case OR:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_equalityExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412); relationalExpr();
			setState(413); equalityExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitEqualityExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpr2Context equalityExpr2() throws RecognitionException {
		EqualityExpr2Context _localctx = new EqualityExpr2Context(_ctx, getState());
		enterRule(_localctx, 76, RULE_equalityExpr2);
		try {
			setState(424);
			switch (_input.LA(1)) {
			case EQ_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(415); match(EQ_S);
				setState(416); relationalExpr();
				setState(417); equalityExpr2();
				}
				break;
			case NE_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(419); match(NE_S);
				setState(420); relationalExpr();
				setState(421); equalityExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case OR:
			case AND:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExprContext relationalExpr() throws RecognitionException {
		RelationalExprContext _localctx = new RelationalExprContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_relationalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426); additiveExpr();
			setState(427); relationalExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRelationalExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpr2Context relationalExpr2() throws RecognitionException {
		RelationalExpr2Context _localctx = new RelationalExpr2Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_relationalExpr2);
		try {
			setState(446);
			switch (_input.LA(1)) {
			case LT_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(429); match(LT_S);
				setState(430); additiveExpr();
				setState(431); relationalExpr2();
				}
				break;
			case GT_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(433); match(GT_S);
				setState(434); additiveExpr();
				setState(435); relationalExpr2();
				}
				break;
			case LE_S:
				enterOuterAlt(_localctx, 3);
				{
				setState(437); match(LE_S);
				setState(438); additiveExpr();
				setState(439); relationalExpr2();
				}
				break;
			case GE_S:
				enterOuterAlt(_localctx, 4);
				{
				setState(441); match(GE_S);
				setState(442); additiveExpr();
				setState(443); relationalExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_additiveExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448); multiplicativeExpr();
			setState(449); additiveExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAdditiveExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpr2Context additiveExpr2() throws RecognitionException {
		AdditiveExpr2Context _localctx = new AdditiveExpr2Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_additiveExpr2);
		try {
			setState(460);
			switch (_input.LA(1)) {
			case 29:
				enterOuterAlt(_localctx, 1);
				{
				setState(451); match(29);
				setState(452); multiplicativeExpr();
				setState(453); additiveExpr2();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 2);
				{
				setState(455); match(31);
				setState(456); multiplicativeExpr();
				setState(457); additiveExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
			case LT_S:
			case LE_S:
			case GT_S:
			case GE_S:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_multiplicativeExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462); unaryExpr();
			setState(463); multiplicativeExpr2();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitMultiplicativeExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpr2Context multiplicativeExpr2() throws RecognitionException {
		MultiplicativeExpr2Context _localctx = new MultiplicativeExpr2Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_multiplicativeExpr2);
		try {
			setState(478);
			switch (_input.LA(1)) {
			case 12:
				enterOuterAlt(_localctx, 1);
				{
				setState(465); match(12);
				setState(466); unaryExpr();
				setState(467); multiplicativeExpr2();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 2);
				{
				setState(469); match(25);
				setState(470); unaryExpr();
				setState(471); multiplicativeExpr2();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 3);
				{
				setState(473); match(15);
				setState(474); unaryExpr();
				setState(475); multiplicativeExpr2();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 20:
			case 23:
			case 24:
			case 28:
			case 29:
			case 31:
			case OR:
			case AND:
			case EQ_S:
			case NE_S:
			case LT_S:
			case LE_S:
			case GT_S:
			case GE_S:
			case COMMA:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_unaryExpr);
		try {
			setState(483);
			switch (_input.LA(1)) {
			case 2:
			case 3:
			case 7:
			case 10:
			case 13:
			case 14:
			case 15:
			case 22:
			case 25:
			case 27:
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
				setState(480); unionExpr();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 2);
				{
				setState(481); match(31);
				setState(482); unaryExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitUnionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionExprContext unionExpr() throws RecognitionException {
		UnionExprContext _localctx = new UnionExprContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_unionExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485); valueExpr();
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==18) {
				{
				{
				setState(486); match(18);
				setState(487); valueExpr();
				}
				}
				setState(492);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitValueExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueExprContext valueExpr() throws RecognitionException {
		ValueExprContext _localctx = new ValueExprContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_valueExpr);
		try {
			setState(498);
			switch (_input.LA(1)) {
			case 2:
			case 3:
			case 7:
			case 10:
			case 13:
			case 14:
			case 27:
			case NOT:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(493); filterExpr();
				setState(495);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(494); pathExpr();
					}
					break;
				}
				}
				break;
			case 15:
			case 22:
			case 25:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(497); pathExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExpr_slashslash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PathExpr_slashContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(XQueryParser.SLASH, 0); }
		public PathExpr_slashContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExpr_slash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PathExpr_relativePathExprContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public PathExpr_relativePathExprContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPathExpr_relativePathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathExprContext pathExpr() throws RecognitionException {
		PathExprContext _localctx = new PathExprContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_pathExpr);
		try {
			setState(505);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(500); match(SLASH);
				setState(501); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(502); match(SLASHSLASH);
				setState(503); relativePathExpr();
				}
				break;
			case 15:
			case 22:
			case 25:
			case TEXTFUNCTION:
			case QNAME_TOKEN:
				_localctx = new PathExpr_relativePathExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(504); relativePathExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRelativePathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelativePathExprContext relativePathExpr() throws RecognitionException {
		RelativePathExprContext _localctx = new RelativePathExprContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507); stepExpr();
			setState(511);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(508); relativePathExpr2();
				}
				}
				setState(513);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRelativePathExpr2_slash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelativePathExpr2_slashslashContext extends RelativePathExpr2Context {
		public TerminalNode SLASHSLASH() { return getToken(XQueryParser.SLASHSLASH, 0); }
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public RelativePathExpr2_slashslashContext(RelativePathExpr2Context ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRelativePathExpr2_slashslash(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelativePathExpr2Context relativePathExpr2() throws RecognitionException {
		RelativePathExpr2Context _localctx = new RelativePathExpr2Context(_ctx, getState());
		enterRule(_localctx, 100, RULE_relativePathExpr2);
		try {
			setState(518);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(514); match(SLASH);
				setState(515); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(516); match(SLASHSLASH);
				setState(517); stepExpr();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitStepExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepExprContext stepExpr() throws RecognitionException {
		StepExprContext _localctx = new StepExprContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520); axisStep();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAxisStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AxisStepContext axisStep() throws RecognitionException {
		AxisStepContext _localctx = new AxisStepContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522); forwardStep();
			setState(523); predicateList();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitForwardStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForwardStepContext forwardStep() throws RecognitionException {
		ForwardStepContext _localctx = new ForwardStepContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525); abbrevForwardStep();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAbbrevForwardStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbbrevForwardStepContext abbrevForwardStep() throws RecognitionException {
		AbbrevForwardStepContext _localctx = new AbbrevForwardStepContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			_la = _input.LA(1);
			if (_la==22) {
				{
				setState(527); match(22);
				}
			}

			setState(530); nodeTest();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNodeTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeTestContext nodeTest() throws RecognitionException {
		NodeTestContext _localctx = new NodeTestContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_nodeTest);
		try {
			setState(534);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(532); kindTest();
				}
				break;
			case 15:
			case 25:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(533); nameTest();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitKindTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KindTestContext kindTest() throws RecognitionException {
		KindTestContext _localctx = new KindTestContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536); textTest();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNameTest_qName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameTest_divContext extends NameTestContext {
		public NameTest_divContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNameTest_div(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameTEst_modContext extends NameTestContext {
		public NameTEst_modContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNameTEst_mod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_nameTest);
		try {
			setState(541);
			switch (_input.LA(1)) {
			case 25:
				_localctx = new NameTest_divContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(538); match(25);
				}
				break;
			case 15:
				_localctx = new NameTEst_modContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(539); match(15);
				}
				break;
			case QNAME_TOKEN:
				_localctx = new NameTest_qNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(540); qName();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543); primaryExpr();
			setState(544); predicateList();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPredicateList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateListContext predicateList() throws RecognitionException {
		PredicateListContext _localctx = new PredicateListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_predicateList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==16) {
				{
				{
				setState(546); predicate();
				}
				}
				setState(551);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552); match(16);
			setState(553); expr();
			setState(554); match(20);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_primaryExpr);
		try {
			setState(559);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(556); literal();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 2);
				{
				setState(557); parenthesizedExpr();
				}
				break;
			case 2:
			case 3:
			case 7:
			case 13:
			case 14:
			case 27:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(558); functionCall();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_literal);
		try {
			setState(563);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(561); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(562); match(STRING_LITERAL);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNumericLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitParenthesizedExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExprContext parenthesizedExpr() throws RecognitionException {
		ParenthesizedExprContext _localctx = new ParenthesizedExprContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567); match(10);
			setState(568); expr();
			setState(569); match(28);
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XQueryParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571); functionName();
			setState(572); match(10);
			setState(581);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 13) | (1L << 14) | (1L << 15) | (1L << 22) | (1L << 25) | (1L << 27) | (1L << 31) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (STRING_LITERAL - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (DECIMAL_LITERAL - 65)))) != 0)) {
				{
				setState(573); expr();
				setState(578);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(574); match(COMMA);
					setState(575); expr();
					}
					}
					setState(580);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(583); match(28);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 13) | (1L << 14) | (1L << 27) | (1L << NOT))) != 0)) ) {
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitTextTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextTestContext textTest() throws RecognitionException {
		TextTestContext _localctx = new TextTestContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587); match(TEXTFUNCTION);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitQName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QNameContext qName() throws RecognitionException {
		QNameContext _localctx = new QNameContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589); match(QNAME_TOKEN);
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
		case 30: return arithmeticExpr_xq_sempred((ArithmeticExpr_xqContext)_localctx, predIndex);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3J\u0252\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2\3\3\3\3"+
		"\7\3\u0091\n\3\f\3\16\3\u0094\13\3\3\3\3\3\3\4\3\4\5\4\u009a\n\4\3\5\3"+
		"\5\3\5\5\5\u009f\n\5\3\6\3\6\3\6\3\6\7\6\u00a5\n\6\f\6\16\6\u00a8\13\6"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u00b2\n\b\f\b\16\b\u00b5\13\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00be\n\t\3\n\3\n\3\13\3\13\3\13\3\13\5\13"+
		"\u00c6\n\13\3\13\3\13\3\13\3\13\5\13\u00cc\n\13\5\13\u00ce\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d9\n\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\7\17\u00e6\n\17\f\17\16\17\u00e9\13\17\3\20"+
		"\3\20\3\20\7\20\u00ee\n\20\f\20\16\20\u00f1\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00f9\n\21\3\22\3\22\3\22\5\22\u00fe\n\22\3\23\3\23\3"+
		"\23\5\23\u0103\n\23\3\23\3\23\5\23\u0107\n\23\5\23\u0109\n\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\7\30\u011f\n\30\f\30\16\30\u0122\13\30\3\31\3\31"+
		"\3\31\3\31\6\31\u0128\n\31\r\31\16\31\u0129\5\31\u012c\n\31\3\32\3\32"+
		"\3\32\7\32\u0131\n\32\f\32\16\32\u0134\13\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\7\32\u013d\n\32\f\32\16\32\u0140\13\32\3\32\3\32\3\32\3\32"+
		"\5\32\u0146\n\32\3\33\3\33\5\33\u014a\n\33\3\33\3\33\3\33\5\33\u014f\n"+
		"\33\7\33\u0151\n\33\f\33\16\33\u0154\13\33\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0163\n\35\3\36\3\36\5\36"+
		"\u0167\n\36\3\37\3\37\5\37\u016b\n\37\3 \3 \3 \3 \3 \3 \5 \u0173\n \3"+
		" \3 \5 \u0177\n \3 \5 \u017a\n \3 \3 \3 \3 \3 \3 \7 \u0182\n \f \16 \u0185"+
		"\13 \3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3$\5$\u0193\n$\3%\3%\3%\3&\3&"+
		"\3&\3&\3&\5&\u019d\n&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u01ab"+
		"\n(\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u01c1"+
		"\n*\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u01cf\n,\3-\3-\3-\3.\3.\3."+
		"\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\5.\u01e1\n.\3/\3/\3/\5/\u01e6\n/\3\60\3"+
		"\60\3\60\7\60\u01eb\n\60\f\60\16\60\u01ee\13\60\3\61\3\61\5\61\u01f2\n"+
		"\61\3\61\5\61\u01f5\n\61\3\62\3\62\3\62\3\62\3\62\5\62\u01fc\n\62\3\63"+
		"\3\63\7\63\u0200\n\63\f\63\16\63\u0203\13\63\3\64\3\64\3\64\3\64\5\64"+
		"\u0209\n\64\3\65\3\65\3\66\3\66\3\66\3\67\3\67\38\58\u0213\n8\38\38\3"+
		"9\39\59\u0219\n9\3:\3:\3;\3;\3;\5;\u0220\n;\3<\3<\3<\3=\7=\u0226\n=\f"+
		"=\16=\u0229\13=\3>\3>\3>\3>\3?\3?\3?\5?\u0232\n?\3@\3@\5@\u0236\n@\3A"+
		"\3A\3B\3B\3B\3B\3C\3C\3C\3C\3C\7C\u0243\nC\fC\16C\u0246\13C\5C\u0248\n"+
		"C\3C\3C\3D\3D\3E\3E\3F\3F\3F\2\3>G\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\2\b\3\2*\65\4\2\7\b\r\r\4\2\37\37!!\5\2\16\16"+
		"\21\21\33\33\3\2GH\7\2\4\5\t\t\17\20\35\35))\u0257\2\u008c\3\2\2\2\4\u008e"+
		"\3\2\2\2\6\u0099\3\2\2\2\b\u009e\3\2\2\2\n\u00a0\3\2\2\2\f\u00a9\3\2\2"+
		"\2\16\u00ad\3\2\2\2\20\u00b6\3\2\2\2\22\u00bf\3\2\2\2\24\u00cd\3\2\2\2"+
		"\26\u00d8\3\2\2\2\30\u00da\3\2\2\2\32\u00df\3\2\2\2\34\u00e2\3\2\2\2\36"+
		"\u00ea\3\2\2\2 \u00f8\3\2\2\2\"\u00fd\3\2\2\2$\u00ff\3\2\2\2&\u010a\3"+
		"\2\2\2(\u010c\3\2\2\2*\u010e\3\2\2\2,\u0115\3\2\2\2.\u011a\3\2\2\2\60"+
		"\u0123\3\2\2\2\62\u012d\3\2\2\2\64\u0149\3\2\2\2\66\u0155\3\2\2\28\u0162"+
		"\3\2\2\2:\u0166\3\2\2\2<\u016a\3\2\2\2>\u0179\3\2\2\2@\u0186\3\2\2\2B"+
		"\u0188\3\2\2\2D\u018a\3\2\2\2F\u0192\3\2\2\2H\u0194\3\2\2\2J\u019c\3\2"+
		"\2\2L\u019e\3\2\2\2N\u01aa\3\2\2\2P\u01ac\3\2\2\2R\u01c0\3\2\2\2T\u01c2"+
		"\3\2\2\2V\u01ce\3\2\2\2X\u01d0\3\2\2\2Z\u01e0\3\2\2\2\\\u01e5\3\2\2\2"+
		"^\u01e7\3\2\2\2`\u01f4\3\2\2\2b\u01fb\3\2\2\2d\u01fd\3\2\2\2f\u0208\3"+
		"\2\2\2h\u020a\3\2\2\2j\u020c\3\2\2\2l\u020f\3\2\2\2n\u0212\3\2\2\2p\u0218"+
		"\3\2\2\2r\u021a\3\2\2\2t\u021f\3\2\2\2v\u0221\3\2\2\2x\u0227\3\2\2\2z"+
		"\u022a\3\2\2\2|\u0231\3\2\2\2~\u0235\3\2\2\2\u0080\u0237\3\2\2\2\u0082"+
		"\u0239\3\2\2\2\u0084\u023d\3\2\2\2\u0086\u024b\3\2\2\2\u0088\u024d\3\2"+
		"\2\2\u008a\u024f\3\2\2\2\u008c\u008d\5\4\3\2\u008d\3\3\2\2\2\u008e\u0092"+
		"\5\6\4\2\u008f\u0091\5\b\5\2\u0090\u008f\3\2\2\2\u0091\u0094\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2"+
		"\2\2\u0095\u0096\5\60\31\2\u0096\5\3\2\2\2\u0097\u009a\5\n\6\2\u0098\u009a"+
		"\5\16\b\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\7\3\2\2\2\u009b"+
		"\u009f\5\6\4\2\u009c\u009f\5\32\16\2\u009d\u009f\5.\30\2\u009e\u009b\3"+
		"\2\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\t\3\2\2\2\u00a0\u00a1"+
		"\7\n\2\2\u00a1\u00a6\5\f\7\2\u00a2\u00a3\7B\2\2\u00a3\u00a5\5\f\7\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\13\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7\66\2\2\u00aa\u00ab"+
		"\7\34\2\2\u00ab\u00ac\5\24\13\2\u00ac\r\3\2\2\2\u00ad\u00ae\7\32\2\2\u00ae"+
		"\u00b3\5\20\t\2\u00af\u00b0\7B\2\2\u00b0\u00b2\5\20\t\2\u00b1\u00af\3"+
		"\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\17\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7\66\2\2\u00b7\u00bd\7\13"+
		"\2\2\u00b8\u00be\5\24\13\2\u00b9\u00be\5\4\3\2\u00ba\u00be\5\30\r\2\u00bb"+
		"\u00be\5> \2\u00bc\u00be\5~@\2\u00bd\u00b8\3\2\2\2\u00bd\u00b9\3\2\2\2"+
		"\u00bd\u00ba\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\21"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\23\3\2\2\2\u00c1\u00c2\7\23\2\2\u00c2"+
		"\u00c3\7\f\2\2\u00c3\u00c5\5\26\f\2\u00c4\u00c6\5@!\2\u00c5\u00c4\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\36\2\2\u00c8"+
		"\u00ce\3\2\2\2\u00c9\u00cb\5\26\f\2\u00ca\u00cc\5@!\2\u00cb\u00ca\3\2"+
		"\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00c1\3\2\2\2\u00cd"+
		"\u00c9\3\2\2\2\u00ce\25\3\2\2\2\u00cf\u00d0\7\25\2\2\u00d0\u00d1\7\f\2"+
		"\2\u00d1\u00d2\7C\2\2\u00d2\u00d9\7\36\2\2\u00d3\u00d4\7 \2\2\u00d4\u00d5"+
		"\7\f\2\2\u00d5\u00d6\7C\2\2\u00d6\u00d9\7\36\2\2\u00d7\u00d9\7\66\2\2"+
		"\u00d8\u00cf\3\2\2\2\u00d8\u00d3\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\27"+
		"\3\2\2\2\u00da\u00db\7#\2\2\u00db\u00dc\7\f\2\2\u00dc\u00dd\7\66\2\2\u00dd"+
		"\u00de\7\36\2\2\u00de\31\3\2\2\2\u00df\u00e0\7\31\2\2\u00e0\u00e1\5\34"+
		"\17\2\u00e1\33\3\2\2\2\u00e2\u00e7\5\36\20\2\u00e3\u00e4\7\'\2\2\u00e4"+
		"\u00e6\5\36\20\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3"+
		"\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\35\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea"+
		"\u00ef\5 \21\2\u00eb\u00ec\7(\2\2\u00ec\u00ee\5 \21\2\u00ed\u00eb\3\2"+
		"\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\37\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\7)\2\2\u00f3\u00f4\7\f\2\2"+
		"\u00f4\u00f5\5\"\22\2\u00f5\u00f6\7\36\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f9"+
		"\5\"\22\2\u00f8\u00f2\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9!\3\2\2\2\u00fa"+
		"\u00fe\5$\23\2\u00fb\u00fe\5*\26\2\u00fc\u00fe\5,\27\2\u00fd\u00fa\3\2"+
		"\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe#\3\2\2\2\u00ff\u0108"+
		"\5> \2\u0100\u0103\5&\24\2\u0101\u0103\5(\25\2\u0102\u0100\3\2\2\2\u0102"+
		"\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0107\5> \2\u0105\u0107\5~@\2"+
		"\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0102"+
		"\3\2\2\2\u0108\u0109\3\2\2\2\u0109%\3\2\2\2\u010a\u010b\t\2\2\2\u010b"+
		"\'\3\2\2\2\u010c\u010d\t\3\2\2\u010d)\3\2\2\2\u010e\u010f\7\27\2\2\u010f"+
		"\u0110\7\f\2\2\u0110\u0111\7\66\2\2\u0111\u0112\7B\2\2\u0112\u0113\7C"+
		"\2\2\u0113\u0114\7\36\2\2\u0114+\3\2\2\2\u0115\u0116\7\"\2\2\u0116\u0117"+
		"\7\f\2\2\u0117\u0118\7\66\2\2\u0118\u0119\7\36\2\2\u0119-\3\2\2\2\u011a"+
		"\u011b\7\3\2\2\u011b\u0120\7\66\2\2\u011c\u011d\7B\2\2\u011d\u011f\7\66"+
		"\2\2\u011e\u011c\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121/\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u012b\7\6\2\2"+
		"\u0124\u012c\5\62\32\2\u0125\u0128\5\30\r\2\u0126\u0128\7\66\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0127\3\2"+
		"\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0124\3\2\2\2\u012b"+
		"\u0127\3\2\2\2\u012c\61\3\2\2\2\u012d\u012e\7/\2\2\u012e\u0132\5<\37\2"+
		"\u012f\u0131\5\66\34\2\u0130\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130"+
		"\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0145\3\2\2\2\u0134\u0132\3\2\2\2\u0135"+
		"\u0146\7>\2\2\u0136\u013e\7\63\2\2\u0137\u013d\5\62\32\2\u0138\u0139\7"+
		"\67\2\2\u0139\u013a\5\64\33\2\u013a\u013b\78\2\2\u013b\u013d\3\2\2\2\u013c"+
		"\u0137\3\2\2\2\u013c\u0138\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2"+
		"\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141"+
		"\u0142\7=\2\2\u0142\u0143\5<\37\2\u0143\u0144\7\63\2\2\u0144\u0146\3\2"+
		"\2\2\u0145\u0135\3\2\2\2\u0145\u0136\3\2\2\2\u0146\63\3\2\2\2\u0147\u014a"+
		"\7\66\2\2\u0148\u014a\5\30\r\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2"+
		"\u014a\u0152\3\2\2\2\u014b\u014e\7B\2\2\u014c\u014f\7\66\2\2\u014d\u014f"+
		"\5\30\r\2\u014e\u014c\3\2\2\2\u014e\u014d\3\2\2\2\u014f\u0151\3\2\2\2"+
		"\u0150\u014b\3\2\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153"+
		"\3\2\2\2\u0153\65\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0156\5<\37\2\u0156"+
		"\u0157\7+\2\2\u0157\u0158\58\35\2\u0158\67\3\2\2\2\u0159\u015a\79\2\2"+
		"\u015a\u015b\5:\36\2\u015b\u015c\7;\2\2\u015c\u0163\3\2\2\2\u015d\u015e"+
		"\7:\2\2\u015e\u015f\5:\36\2\u015f\u0160\7<\2\2\u0160\u0163\3\2\2\2\u0161"+
		"\u0163\7C\2\2\u0162\u0159\3\2\2\2\u0162\u015d\3\2\2\2\u0162\u0161\3\2"+
		"\2\2\u01639\3\2\2\2\u0164\u0167\7\66\2\2\u0165\u0167\5\30\r\2\u0166\u0164"+
		"\3\2\2\2\u0166\u0165\3\2\2\2\u0167;\3\2\2\2\u0168\u016b\5\u0086D\2\u0169"+
		"\u016b\7?\2\2\u016a\u0168\3\2\2\2\u016a\u0169\3\2\2\2\u016b=\3\2\2\2\u016c"+
		"\u016d\b \1\2\u016d\u016e\7\f\2\2\u016e\u016f\5> \2\u016f\u0170\7\36\2"+
		"\2\u0170\u017a\3\2\2\2\u0171\u0173\7!\2\2\u0172\u0171\3\2\2\2\u0172\u0173"+
		"\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u017a\5\u0080A\2\u0175\u0177\7!\2\2"+
		"\u0176\u0175\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a"+
		"\7\66\2\2\u0179\u016c\3\2\2\2\u0179\u0172\3\2\2\2\u0179\u0176\3\2\2\2"+
		"\u017a\u0183\3\2\2\2\u017b\u017c\f\7\2\2\u017c\u017d\t\4\2\2\u017d\u0182"+
		"\5> \b\u017e\u017f\f\6\2\2\u017f\u0180\t\5\2\2\u0180\u0182\5> \7\u0181"+
		"\u017b\3\2\2\2\u0181\u017e\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0183\u0184\3\2\2\2\u0184?\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0187"+
		"\5B\"\2\u0187A\3\2\2\2\u0188\u0189\5D#\2\u0189C\3\2\2\2\u018a\u018b\5"+
		"H%\2\u018b\u018c\5F$\2\u018cE\3\2\2\2\u018d\u018e\7\'\2\2\u018e\u018f"+
		"\5H%\2\u018f\u0190\5F$\2\u0190\u0193\3\2\2\2\u0191\u0193\3\2\2\2\u0192"+
		"\u018d\3\2\2\2\u0192\u0191\3\2\2\2\u0193G\3\2\2\2\u0194\u0195\5L\'\2\u0195"+
		"\u0196\5J&\2\u0196I\3\2\2\2\u0197\u0198\7(\2\2\u0198\u0199\5L\'\2\u0199"+
		"\u019a\5J&\2\u019a\u019d\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u0197\3\2\2"+
		"\2\u019c\u019b\3\2\2\2\u019dK\3\2\2\2\u019e\u019f\5P)\2\u019f\u01a0\5"+
		"N(\2\u01a0M\3\2\2\2\u01a1\u01a2\7+\2\2\u01a2\u01a3\5P)\2\u01a3\u01a4\5"+
		"N(\2\u01a4\u01ab\3\2\2\2\u01a5\u01a6\7-\2\2\u01a6\u01a7\5P)\2\u01a7\u01a8"+
		"\5N(\2\u01a8\u01ab\3\2\2\2\u01a9\u01ab\3\2\2\2\u01aa\u01a1\3\2\2\2\u01aa"+
		"\u01a5\3\2\2\2\u01aa\u01a9\3\2\2\2\u01abO\3\2\2\2\u01ac\u01ad\5T+\2\u01ad"+
		"\u01ae\5R*\2\u01aeQ\3\2\2\2\u01af\u01b0\7/\2\2\u01b0\u01b1\5T+\2\u01b1"+
		"\u01b2\5R*\2\u01b2\u01c1\3\2\2\2\u01b3\u01b4\7\63\2\2\u01b4\u01b5\5T+"+
		"\2\u01b5\u01b6\5R*\2\u01b6\u01c1\3\2\2\2\u01b7\u01b8\7\61\2\2\u01b8\u01b9"+
		"\5T+\2\u01b9\u01ba\5R*\2\u01ba\u01c1\3\2\2\2\u01bb\u01bc\7\65\2\2\u01bc"+
		"\u01bd\5T+\2\u01bd\u01be\5R*\2\u01be\u01c1\3\2\2\2\u01bf\u01c1\3\2\2\2"+
		"\u01c0\u01af\3\2\2\2\u01c0\u01b3\3\2\2\2\u01c0\u01b7\3\2\2\2\u01c0\u01bb"+
		"\3\2\2\2\u01c0\u01bf\3\2\2\2\u01c1S\3\2\2\2\u01c2\u01c3\5X-\2\u01c3\u01c4"+
		"\5V,\2\u01c4U\3\2\2\2\u01c5\u01c6\7\37\2\2\u01c6\u01c7\5X-\2\u01c7\u01c8"+
		"\5V,\2\u01c8\u01cf\3\2\2\2\u01c9\u01ca\7!\2\2\u01ca\u01cb\5X-\2\u01cb"+
		"\u01cc\5V,\2\u01cc\u01cf\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce\u01c5\3\2\2"+
		"\2\u01ce\u01c9\3\2\2\2\u01ce\u01cd\3\2\2\2\u01cfW\3\2\2\2\u01d0\u01d1"+
		"\5\\/\2\u01d1\u01d2\5Z.\2\u01d2Y\3\2\2\2\u01d3\u01d4\7\16\2\2\u01d4\u01d5"+
		"\5\\/\2\u01d5\u01d6\5Z.\2\u01d6\u01e1\3\2\2\2\u01d7\u01d8\7\33\2\2\u01d8"+
		"\u01d9\5\\/\2\u01d9\u01da\5Z.\2\u01da\u01e1\3\2\2\2\u01db\u01dc\7\21\2"+
		"\2\u01dc\u01dd\5\\/\2\u01dd\u01de\5Z.\2\u01de\u01e1\3\2\2\2\u01df\u01e1"+
		"\3\2\2\2\u01e0\u01d3\3\2\2\2\u01e0\u01d7\3\2\2\2\u01e0\u01db\3\2\2\2\u01e0"+
		"\u01df\3\2\2\2\u01e1[\3\2\2\2\u01e2\u01e6\5^\60\2\u01e3\u01e4\7!\2\2\u01e4"+
		"\u01e6\5\\/\2\u01e5\u01e2\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6]\3\2\2\2\u01e7"+
		"\u01ec\5`\61\2\u01e8\u01e9\7\24\2\2\u01e9\u01eb\5`\61\2\u01ea\u01e8\3"+
		"\2\2\2\u01eb\u01ee\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"_\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ef\u01f1\5v<\2\u01f0\u01f2\5b\62\2\u01f1"+
		"\u01f0\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f5\3\2\2\2\u01f3\u01f5\5b"+
		"\62\2\u01f4\u01ef\3\2\2\2\u01f4\u01f3\3\2\2\2\u01f5a\3\2\2\2\u01f6\u01f7"+
		"\7%\2\2\u01f7\u01fc\5d\63\2\u01f8\u01f9\7&\2\2\u01f9\u01fc\5d\63\2\u01fa"+
		"\u01fc\5d\63\2\u01fb\u01f6\3\2\2\2\u01fb\u01f8\3\2\2\2\u01fb\u01fa\3\2"+
		"\2\2\u01fcc\3\2\2\2\u01fd\u0201\5h\65\2\u01fe\u0200\5f\64\2\u01ff\u01fe"+
		"\3\2\2\2\u0200\u0203\3\2\2\2\u0201\u01ff\3\2\2\2\u0201\u0202\3\2\2\2\u0202"+
		"e\3\2\2\2\u0203\u0201\3\2\2\2\u0204\u0205\7%\2\2\u0205\u0209\5h\65\2\u0206"+
		"\u0207\7&\2\2\u0207\u0209\5h\65\2\u0208\u0204\3\2\2\2\u0208\u0206\3\2"+
		"\2\2\u0209g\3\2\2\2\u020a\u020b\5j\66\2\u020bi\3\2\2\2\u020c\u020d\5l"+
		"\67\2\u020d\u020e\5x=\2\u020ek\3\2\2\2\u020f\u0210\5n8\2\u0210m\3\2\2"+
		"\2\u0211\u0213\7\30\2\2\u0212\u0211\3\2\2\2\u0212\u0213\3\2\2\2\u0213"+
		"\u0214\3\2\2\2\u0214\u0215\5p9\2\u0215o\3\2\2\2\u0216\u0219\5r:\2\u0217"+
		"\u0219\5t;\2\u0218\u0216\3\2\2\2\u0218\u0217\3\2\2\2\u0219q\3\2\2\2\u021a"+
		"\u021b\5\u0088E\2\u021bs\3\2\2\2\u021c\u0220\7\33\2\2\u021d\u0220\7\21"+
		"\2\2\u021e\u0220\5\u008aF\2\u021f\u021c\3\2\2\2\u021f\u021d\3\2\2\2\u021f"+
		"\u021e\3\2\2\2\u0220u\3\2\2\2\u0221\u0222\5|?\2\u0222\u0223\5x=\2\u0223"+
		"w\3\2\2\2\u0224\u0226\5z>\2\u0225\u0224\3\2\2\2\u0226\u0229\3\2\2\2\u0227"+
		"\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228y\3\2\2\2\u0229\u0227\3\2\2\2"+
		"\u022a\u022b\7\22\2\2\u022b\u022c\5B\"\2\u022c\u022d\7\26\2\2\u022d{\3"+
		"\2\2\2\u022e\u0232\5~@\2\u022f\u0232\5\u0082B\2\u0230\u0232\5\u0084C\2"+
		"\u0231\u022e\3\2\2\2\u0231\u022f\3\2\2\2\u0231\u0230\3\2\2\2\u0232}\3"+
		"\2\2\2\u0233\u0236\5\u0080A\2\u0234\u0236\7C\2\2\u0235\u0233\3\2\2\2\u0235"+
		"\u0234\3\2\2\2\u0236\177\3\2\2\2\u0237\u0238\t\6\2\2\u0238\u0081\3\2\2"+
		"\2\u0239\u023a\7\f\2\2\u023a\u023b\5B\"\2\u023b\u023c\7\36\2\2\u023c\u0083"+
		"\3\2\2\2\u023d\u023e\5\u0086D\2\u023e\u0247\7\f\2\2\u023f\u0244\5B\"\2"+
		"\u0240\u0241\7B\2\2\u0241\u0243\5B\"\2\u0242\u0240\3\2\2\2\u0243\u0246"+
		"\3\2\2\2\u0244\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0248\3\2\2\2\u0246"+
		"\u0244\3\2\2\2\u0247\u023f\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2"+
		"\2\2\u0249\u024a\7\36\2\2\u024a\u0085\3\2\2\2\u024b\u024c\t\7\2\2\u024c"+
		"\u0087\3\2\2\2\u024d\u024e\7$\2\2\u024e\u0089\3\2\2\2\u024f\u0250\7?\2"+
		"\2\u0250\u008b\3\2\2\2;\u0092\u0099\u009e\u00a6\u00b3\u00bd\u00c5\u00cb"+
		"\u00cd\u00d8\u00e7\u00ef\u00f8\u00fd\u0102\u0106\u0108\u0120\u0127\u0129"+
		"\u012b\u0132\u013c\u013e\u0145\u0149\u014e\u0152\u0162\u0166\u016a\u0172"+
		"\u0176\u0179\u0181\u0183\u0192\u019c\u01aa\u01c0\u01ce\u01e0\u01e5\u01ec"+
		"\u01f1\u01f4\u01fb\u0201\u0208\u0212\u0218\u021f\u0227\u0231\u0235\u0244"+
		"\u0247";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}