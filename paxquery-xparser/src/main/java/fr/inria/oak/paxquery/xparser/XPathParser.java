// Generated from XPath.g4 by ANTLR 4.2
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
public class XPathParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__13=1, T__12=2, T__11=3, T__10=4, T__9=5, T__8=6, T__7=7, T__6=8, T__5=9, 
		T__4=10, T__3=11, T__2=12, T__1=13, T__0=14, AGGR_FUNCT=15, TEXTFUNCTION=16, 
		SLASH=17, SLASHSLASH=18, OR=19, AND=20, NOT=21, EQ=22, EQ_S=23, NE=24, 
		NE_S=25, LT=26, LT_S=27, LE=28, LE_S=29, GT=30, GT_S=31, GE=32, GE_S=33, 
		OP_ADD=34, OP_SUB=35, OP_MUL=36, VAR=37, LEFTCURL=38, RIGHTCURL=39, OPEN_ATTR_VAR_DOUBLE=40, 
		OPEN_ATTR_VAR_SINGLE=41, CLOSE_ATTR_VAR_DOUBLE=42, CLOSE_ATTR_VAR_SINGLE=43, 
		OPEN_CLOSING_TAG=44, CLOSE_OPENING_TAG=45, QNAME_TOKEN=46, SINGLE_QUOTE=47, 
		DOUBLE_QUOTE=48, COMMA=49, STRING_LITERAL=50, REFERENCE=51, ENTITY_REF=52, 
		CHAR_REF=53, INTEGER_LITERAL=54, DECIMAL_LITERAL=55, DIGITS=56, WS=57;
	public static final String[] tokenNames = {
		"<INVALID>", "'substring'", "'true'", "'ceiling'", "'mod'", "'['", "'|'", 
		"'concat'", "']'", "'@'", "'div'", "'('", "'floor'", "')'", "'false'", 
		"AGGR_FUNCT", "'text()'", "'/'", "'//'", "'or'", "'and'", "'not'", "'eq'", 
		"'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", "'>'", "'ge'", 
		"'>='", "'+'", "'-'", "'*'", "VAR", "'{'", "'}'", "OPEN_ATTR_VAR_DOUBLE", 
		"OPEN_ATTR_VAR_SINGLE", "CLOSE_ATTR_VAR_DOUBLE", "CLOSE_ATTR_VAR_SINGLE", 
		"OPEN_CLOSING_TAG", "CLOSE_OPENING_TAG", "QNAME_TOKEN", "'''", "'\"'", 
		"','", "STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", 
		"DECIMAL_LITERAL", "DIGITS", "WS"
	};
	public static final int
		RULE_xpath = 0, RULE_expr = 1, RULE_orExpr_xp = 2, RULE_andExpr_xp = 3, 
		RULE_equalityExpr_xp = 4, RULE_relationalExpr_xp = 5, RULE_additiveExpr_xp = 6, 
		RULE_multiplicativeExpr_xp = 7, RULE_unaryExpr = 8, RULE_unionExpr = 9, 
		RULE_valueExpr = 10, RULE_pathExpr = 11, RULE_relativePathExpr = 12, RULE_relativePathExpr2 = 13, 
		RULE_stepExpr = 14, RULE_axisStep = 15, RULE_forwardStep = 16, RULE_abbrevForwardStep = 17, 
		RULE_nodeTest = 18, RULE_kindTest = 19, RULE_nameTest = 20, RULE_filterExpr = 21, 
		RULE_predicateList = 22, RULE_predicate = 23, RULE_primaryExpr = 24, RULE_literal = 25, 
		RULE_numericLiteral = 26, RULE_parenthesizedExpr = 27, RULE_functionCall = 28, 
		RULE_functionName = 29, RULE_textTest = 30, RULE_qName = 31;
	public static final String[] ruleNames = {
		"xpath", "expr", "orExpr_xp", "andExpr_xp", "equalityExpr_xp", "relationalExpr_xp", 
		"additiveExpr_xp", "multiplicativeExpr_xp", "unaryExpr", "unionExpr", 
		"valueExpr", "pathExpr", "relativePathExpr", "relativePathExpr2", "stepExpr", 
		"axisStep", "forwardStep", "abbrevForwardStep", "nodeTest", "kindTest", 
		"nameTest", "filterExpr", "predicateList", "predicate", "primaryExpr", 
		"literal", "numericLiteral", "parenthesizedExpr", "functionCall", "functionName", 
		"textTest", "qName"
	};

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XPathParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterXpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitXpath(this);
		}
	}

	public final XpathContext xpath() throws RecognitionException {
		XpathContext _localctx = new XpathContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); expr();
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
		public OrExpr_xpContext orExpr_xp() {
			return getRuleContext(OrExpr_xpContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); orExpr_xp();
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

	public static class OrExpr_xpContext extends ParserRuleContext {
		public List<AndExpr_xpContext> andExpr_xp() {
			return getRuleContexts(AndExpr_xpContext.class);
		}
		public AndExpr_xpContext andExpr_xp(int i) {
			return getRuleContext(AndExpr_xpContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(XPathParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XPathParser.OR, i);
		}
		public OrExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterOrExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitOrExpr_xp(this);
		}
	}

	public final OrExpr_xpContext orExpr_xp() throws RecognitionException {
		OrExpr_xpContext _localctx = new OrExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_orExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); andExpr_xp();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(69); match(OR);
				setState(70); andExpr_xp();
				}
				}
				setState(75);
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

	public static class AndExpr_xpContext extends ParserRuleContext {
		public List<EqualityExpr_xpContext> equalityExpr_xp() {
			return getRuleContexts(EqualityExpr_xpContext.class);
		}
		public TerminalNode AND(int i) {
			return getToken(XPathParser.AND, i);
		}
		public List<TerminalNode> AND() { return getTokens(XPathParser.AND); }
		public EqualityExpr_xpContext equalityExpr_xp(int i) {
			return getRuleContext(EqualityExpr_xpContext.class,i);
		}
		public AndExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAndExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAndExpr_xp(this);
		}
	}

	public final AndExpr_xpContext andExpr_xp() throws RecognitionException {
		AndExpr_xpContext _localctx = new AndExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_andExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); equalityExpr_xp();
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(77); match(AND);
				setState(78); equalityExpr_xp();
				}
				}
				setState(83);
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

	public static class EqualityExpr_xpContext extends ParserRuleContext {
		public List<RelationalExpr_xpContext> relationalExpr_xp() {
			return getRuleContexts(RelationalExpr_xpContext.class);
		}
		public RelationalExpr_xpContext relationalExpr_xp(int i) {
			return getRuleContext(RelationalExpr_xpContext.class,i);
		}
		public List<TerminalNode> NE_S() { return getTokens(XPathParser.NE_S); }
		public TerminalNode NE_S(int i) {
			return getToken(XPathParser.NE_S, i);
		}
		public TerminalNode EQ_S(int i) {
			return getToken(XPathParser.EQ_S, i);
		}
		public List<TerminalNode> EQ_S() { return getTokens(XPathParser.EQ_S); }
		public EqualityExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterEqualityExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitEqualityExpr_xp(this);
		}
	}

	public final EqualityExpr_xpContext equalityExpr_xp() throws RecognitionException {
		EqualityExpr_xpContext _localctx = new EqualityExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_equalityExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); relationalExpr_xp();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ_S || _la==NE_S) {
				{
				{
				setState(85);
				_la = _input.LA(1);
				if ( !(_la==EQ_S || _la==NE_S) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(86); relationalExpr_xp();
				}
				}
				setState(91);
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

	public static class RelationalExpr_xpContext extends ParserRuleContext {
		public List<AdditiveExpr_xpContext> additiveExpr_xp() {
			return getRuleContexts(AdditiveExpr_xpContext.class);
		}
		public AdditiveExpr_xpContext additiveExpr_xp(int i) {
			return getRuleContext(AdditiveExpr_xpContext.class,i);
		}
		public List<TerminalNode> LE_S() { return getTokens(XPathParser.LE_S); }
		public TerminalNode LT_S(int i) {
			return getToken(XPathParser.LT_S, i);
		}
		public TerminalNode GT_S(int i) {
			return getToken(XPathParser.GT_S, i);
		}
		public List<TerminalNode> GT_S() { return getTokens(XPathParser.GT_S); }
		public TerminalNode LE_S(int i) {
			return getToken(XPathParser.LE_S, i);
		}
		public TerminalNode GE_S(int i) {
			return getToken(XPathParser.GE_S, i);
		}
		public List<TerminalNode> GE_S() { return getTokens(XPathParser.GE_S); }
		public List<TerminalNode> LT_S() { return getTokens(XPathParser.LT_S); }
		public RelationalExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelationalExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelationalExpr_xp(this);
		}
	}

	public final RelationalExpr_xpContext relationalExpr_xp() throws RecognitionException {
		RelationalExpr_xpContext _localctx = new RelationalExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_relationalExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); additiveExpr_xp();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) {
				{
				{
				setState(93);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(94); additiveExpr_xp();
				}
				}
				setState(99);
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

	public static class AdditiveExpr_xpContext extends ParserRuleContext {
		public TerminalNode OP_ADD(int i) {
			return getToken(XPathParser.OP_ADD, i);
		}
		public List<MultiplicativeExpr_xpContext> multiplicativeExpr_xp() {
			return getRuleContexts(MultiplicativeExpr_xpContext.class);
		}
		public List<TerminalNode> OP_ADD() { return getTokens(XPathParser.OP_ADD); }
		public TerminalNode OP_SUB(int i) {
			return getToken(XPathParser.OP_SUB, i);
		}
		public MultiplicativeExpr_xpContext multiplicativeExpr_xp(int i) {
			return getRuleContext(MultiplicativeExpr_xpContext.class,i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(XPathParser.OP_SUB); }
		public AdditiveExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAdditiveExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAdditiveExpr_xp(this);
		}
	}

	public final AdditiveExpr_xpContext additiveExpr_xp() throws RecognitionException {
		AdditiveExpr_xpContext _localctx = new AdditiveExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_additiveExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); multiplicativeExpr_xp();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_ADD || _la==OP_SUB) {
				{
				{
				setState(101);
				_la = _input.LA(1);
				if ( !(_la==OP_ADD || _la==OP_SUB) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(102); multiplicativeExpr_xp();
				}
				}
				setState(107);
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

	public static class MultiplicativeExpr_xpContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public List<TerminalNode> OP_MUL() { return getTokens(XPathParser.OP_MUL); }
		public TerminalNode OP_MUL(int i) {
			return getToken(XPathParser.OP_MUL, i);
		}
		public MultiplicativeExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMultiplicativeExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMultiplicativeExpr_xp(this);
		}
	}

	public final MultiplicativeExpr_xpContext multiplicativeExpr_xp() throws RecognitionException {
		MultiplicativeExpr_xpContext _localctx = new MultiplicativeExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multiplicativeExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); unaryExpr();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 10) | (1L << OP_MUL))) != 0)) {
				{
				{
				setState(109);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 10) | (1L << OP_MUL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(110); unaryExpr();
				}
				}
				setState(115);
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

	public static class UnaryExprContext extends ParserRuleContext {
		public UnionExprContext unionExpr() {
			return getRuleContext(UnionExprContext.class,0);
		}
		public TerminalNode OP_SUB(int i) {
			return getToken(XPathParser.OP_SUB, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(XPathParser.OP_SUB); }
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnaryExpr(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unaryExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(116); match(OP_SUB);
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(122); unionExpr();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnionExpr(this);
		}
	}

	public final UnionExprContext unionExpr() throws RecognitionException {
		UnionExprContext _localctx = new UnionExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unionExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); valueExpr();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==6) {
				{
				{
				setState(125); match(6);
				setState(126); valueExpr();
				}
				}
				setState(131);
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterValueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitValueExpr(this);
		}
	}

	public final ValueExprContext valueExpr() throws RecognitionException {
		ValueExprContext _localctx = new ValueExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_valueExpr);
		try {
			setState(137);
			switch (_input.LA(1)) {
			case 1:
			case 2:
			case 3:
			case 7:
			case 11:
			case 12:
			case 14:
			case NOT:
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(132); filterExpr();
				setState(134);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(133); pathExpr();
					}
					break;
				}
				}
				break;
			case 4:
			case 9:
			case 10:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(136); pathExpr();
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
		public TerminalNode SLASHSLASH() { return getToken(XPathParser.SLASHSLASH, 0); }
		public PathExpr_slashslashContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPathExpr_slashslash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPathExpr_slashslash(this);
		}
	}
	public static class PathExpr_slashContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(XPathParser.SLASH, 0); }
		public PathExpr_slashContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPathExpr_slash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPathExpr_slash(this);
		}
	}
	public static class PathExpr_relativePathExprContext extends PathExprContext {
		public RelativePathExprContext relativePathExpr() {
			return getRuleContext(RelativePathExprContext.class,0);
		}
		public PathExpr_relativePathExprContext(PathExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPathExpr_relativePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPathExpr_relativePathExpr(this);
		}
	}

	public final PathExprContext pathExpr() throws RecognitionException {
		PathExprContext _localctx = new PathExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pathExpr);
		try {
			setState(144);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(139); match(SLASH);
				setState(140); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(141); match(SLASHSLASH);
				setState(142); relativePathExpr();
				}
				break;
			case 4:
			case 9:
			case 10:
			case TEXTFUNCTION:
			case QNAME_TOKEN:
				_localctx = new PathExpr_relativePathExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(143); relativePathExpr();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelativePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelativePathExpr(this);
		}
	}

	public final RelativePathExprContext relativePathExpr() throws RecognitionException {
		RelativePathExprContext _localctx = new RelativePathExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); stepExpr();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(147); relativePathExpr2();
				}
				}
				setState(152);
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
		public TerminalNode SLASH() { return getToken(XPathParser.SLASH, 0); }
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public RelativePathExpr2_slashContext(RelativePathExpr2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelativePathExpr2_slash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelativePathExpr2_slash(this);
		}
	}
	public static class RelativePathExpr2_slashslashContext extends RelativePathExpr2Context {
		public TerminalNode SLASHSLASH() { return getToken(XPathParser.SLASHSLASH, 0); }
		public StepExprContext stepExpr() {
			return getRuleContext(StepExprContext.class,0);
		}
		public RelativePathExpr2_slashslashContext(RelativePathExpr2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelativePathExpr2_slashslash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelativePathExpr2_slashslash(this);
		}
	}

	public final RelativePathExpr2Context relativePathExpr2() throws RecognitionException {
		RelativePathExpr2Context _localctx = new RelativePathExpr2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_relativePathExpr2);
		try {
			setState(157);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153); match(SLASH);
				setState(154); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(155); match(SLASHSLASH);
				setState(156); stepExpr();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterStepExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitStepExpr(this);
		}
	}

	public final StepExprContext stepExpr() throws RecognitionException {
		StepExprContext _localctx = new StepExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); axisStep();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAxisStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAxisStep(this);
		}
	}

	public final AxisStepContext axisStep() throws RecognitionException {
		AxisStepContext _localctx = new AxisStepContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); forwardStep();
			setState(162); predicateList();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterForwardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitForwardStep(this);
		}
	}

	public final ForwardStepContext forwardStep() throws RecognitionException {
		ForwardStepContext _localctx = new ForwardStepContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); abbrevForwardStep();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAbbrevForwardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAbbrevForwardStep(this);
		}
	}

	public final AbbrevForwardStepContext abbrevForwardStep() throws RecognitionException {
		AbbrevForwardStepContext _localctx = new AbbrevForwardStepContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(166); match(9);
				}
			}

			setState(169); nodeTest();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNodeTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNodeTest(this);
		}
	}

	public final NodeTestContext nodeTest() throws RecognitionException {
		NodeTestContext _localctx = new NodeTestContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_nodeTest);
		try {
			setState(173);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(171); kindTest();
				}
				break;
			case 4:
			case 10:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(172); nameTest();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterKindTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitKindTest(this);
		}
	}

	public final KindTestContext kindTest() throws RecognitionException {
		KindTestContext _localctx = new KindTestContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); textTest();
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
		public QNameContext qName() {
			return getRuleContext(QNameContext.class,0);
		}
		public NameTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNameTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNameTest(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_nameTest);
		try {
			setState(180);
			switch (_input.LA(1)) {
			case 10:
				enterOuterAlt(_localctx, 1);
				{
				setState(177); match(10);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 2);
				{
				setState(178); match(4);
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(179); qName();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFilterExpr(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); primaryExpr();
			setState(183); predicateList();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPredicateList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPredicateList(this);
		}
	}

	public final PredicateListContext predicateList() throws RecognitionException {
		PredicateListContext _localctx = new PredicateListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_predicateList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(185); predicate();
				}
				}
				setState(190);
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); match(5);
			setState(192); expr();
			setState(193); match(8);
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPrimaryExpr(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_primaryExpr);
		try {
			setState(198);
			switch (_input.LA(1)) {
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(195); literal();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 2);
				{
				setState(196); parenthesizedExpr();
				}
				break;
			case 1:
			case 2:
			case 3:
			case 7:
			case 12:
			case 14:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(197); functionCall();
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
		public TerminalNode STRING_LITERAL() { return getToken(XPathParser.STRING_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_literal);
		try {
			setState(202);
			switch (_input.LA(1)) {
			case OP_SUB:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(200); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); match(STRING_LITERAL);
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
		public TerminalNode INTEGER_LITERAL() { return getToken(XPathParser.INTEGER_LITERAL, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(XPathParser.DECIMAL_LITERAL, 0); }
		public TerminalNode OP_SUB() { return getToken(XPathParser.OP_SUB, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_la = _input.LA(1);
			if (_la==OP_SUB) {
				{
				setState(204); match(OP_SUB);
				}
			}

			setState(207);
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterParenthesizedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitParenthesizedExpr(this);
		}
	}

	public final ParenthesizedExprContext parenthesizedExpr() throws RecognitionException {
		ParenthesizedExprContext _localctx = new ParenthesizedExprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); match(11);
			setState(210); expr();
			setState(211); match(13);
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
		public List<TerminalNode> COMMA() { return getTokens(XPathParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XPathParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); functionName();
			setState(214); match(11);
			setState(223);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 4) | (1L << 7) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << OP_SUB) | (1L << QNAME_TOKEN) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DECIMAL_LITERAL))) != 0)) {
				{
				setState(215); expr();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(216); match(COMMA);
					setState(217); expr();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(225); match(13);
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
		public TerminalNode NOT() { return getToken(XPathParser.NOT, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFunctionName(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 7) | (1L << 12) | (1L << 14) | (1L << NOT))) != 0)) ) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterTextTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitTextTest(this);
		}
	}

	public final TextTestContext textTest() throws RecognitionException {
		TextTestContext _localctx = new TextTestContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229); match(TEXTFUNCTION);
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
		public TerminalNode QNAME_TOKEN() { return getToken(XPathParser.QNAME_TOKEN, 0); }
		public QNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterQName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitQName(this);
		}
	}

	public final QNameContext qName() throws RecognitionException {
		QNameContext _localctx = new QNameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(QNAME_TOKEN);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u00ec\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\5\3\5\3\5\7"+
		"\5R\n\5\f\5\16\5U\13\5\3\6\3\6\3\6\7\6Z\n\6\f\6\16\6]\13\6\3\7\3\7\3\7"+
		"\7\7b\n\7\f\7\16\7e\13\7\3\b\3\b\3\b\7\bj\n\b\f\b\16\bm\13\b\3\t\3\t\3"+
		"\t\7\tr\n\t\f\t\16\tu\13\t\3\n\7\nx\n\n\f\n\16\n{\13\n\3\n\3\n\3\13\3"+
		"\13\3\13\7\13\u0082\n\13\f\13\16\13\u0085\13\13\3\f\3\f\5\f\u0089\n\f"+
		"\3\f\5\f\u008c\n\f\3\r\3\r\3\r\3\r\3\r\5\r\u0093\n\r\3\16\3\16\7\16\u0097"+
		"\n\16\f\16\16\16\u009a\13\16\3\17\3\17\3\17\3\17\5\17\u00a0\n\17\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\23\5\23\u00aa\n\23\3\23\3\23\3\24\3\24"+
		"\5\24\u00b0\n\24\3\25\3\25\3\26\3\26\3\26\5\26\u00b7\n\26\3\27\3\27\3"+
		"\27\3\30\7\30\u00bd\n\30\f\30\16\30\u00c0\13\30\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\5\32\u00c9\n\32\3\33\3\33\5\33\u00cd\n\33\3\34\5\34\u00d0"+
		"\n\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\7\36\u00dd"+
		"\n\36\f\36\16\36\u00e0\13\36\5\36\u00e2\n\36\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3!\2\2\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@\2\b\4\2\31\31\33\33\6\2\35\35\37\37!!##\3\2$%\5\2\6\6\f\f"+
		"&&\3\289\7\2\3\5\t\t\16\16\20\20\27\27\u00e4\2B\3\2\2\2\4D\3\2\2\2\6F"+
		"\3\2\2\2\bN\3\2\2\2\nV\3\2\2\2\f^\3\2\2\2\16f\3\2\2\2\20n\3\2\2\2\22y"+
		"\3\2\2\2\24~\3\2\2\2\26\u008b\3\2\2\2\30\u0092\3\2\2\2\32\u0094\3\2\2"+
		"\2\34\u009f\3\2\2\2\36\u00a1\3\2\2\2 \u00a3\3\2\2\2\"\u00a6\3\2\2\2$\u00a9"+
		"\3\2\2\2&\u00af\3\2\2\2(\u00b1\3\2\2\2*\u00b6\3\2\2\2,\u00b8\3\2\2\2."+
		"\u00be\3\2\2\2\60\u00c1\3\2\2\2\62\u00c8\3\2\2\2\64\u00cc\3\2\2\2\66\u00cf"+
		"\3\2\2\28\u00d3\3\2\2\2:\u00d7\3\2\2\2<\u00e5\3\2\2\2>\u00e7\3\2\2\2@"+
		"\u00e9\3\2\2\2BC\5\4\3\2C\3\3\2\2\2DE\5\6\4\2E\5\3\2\2\2FK\5\b\5\2GH\7"+
		"\25\2\2HJ\5\b\5\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\7\3\2\2\2M"+
		"K\3\2\2\2NS\5\n\6\2OP\7\26\2\2PR\5\n\6\2QO\3\2\2\2RU\3\2\2\2SQ\3\2\2\2"+
		"ST\3\2\2\2T\t\3\2\2\2US\3\2\2\2V[\5\f\7\2WX\t\2\2\2XZ\5\f\7\2YW\3\2\2"+
		"\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\13\3\2\2\2][\3\2\2\2^c\5\16\b\2_`"+
		"\t\3\2\2`b\5\16\b\2a_\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\r\3\2\2\2"+
		"ec\3\2\2\2fk\5\20\t\2gh\t\4\2\2hj\5\20\t\2ig\3\2\2\2jm\3\2\2\2ki\3\2\2"+
		"\2kl\3\2\2\2l\17\3\2\2\2mk\3\2\2\2ns\5\22\n\2op\t\5\2\2pr\5\22\n\2qo\3"+
		"\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\21\3\2\2\2us\3\2\2\2vx\7%\2\2wv"+
		"\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\5\24\13\2"+
		"}\23\3\2\2\2~\u0083\5\26\f\2\177\u0080\7\b\2\2\u0080\u0082\5\26\f\2\u0081"+
		"\177\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2"+
		"\2\u0084\25\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\5,\27\2\u0087\u0089"+
		"\5\30\r\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\3\2\2\2"+
		"\u008a\u008c\5\30\r\2\u008b\u0086\3\2\2\2\u008b\u008a\3\2\2\2\u008c\27"+
		"\3\2\2\2\u008d\u008e\7\23\2\2\u008e\u0093\5\32\16\2\u008f\u0090\7\24\2"+
		"\2\u0090\u0093\5\32\16\2\u0091\u0093\5\32\16\2\u0092\u008d\3\2\2\2\u0092"+
		"\u008f\3\2\2\2\u0092\u0091\3\2\2\2\u0093\31\3\2\2\2\u0094\u0098\5\36\20"+
		"\2\u0095\u0097\5\34\17\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\33\3\2\2\2\u009a\u0098\3\2\2"+
		"\2\u009b\u009c\7\23\2\2\u009c\u00a0\5\36\20\2\u009d\u009e\7\24\2\2\u009e"+
		"\u00a0\5\36\20\2\u009f\u009b\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\35\3\2"+
		"\2\2\u00a1\u00a2\5 \21\2\u00a2\37\3\2\2\2\u00a3\u00a4\5\"\22\2\u00a4\u00a5"+
		"\5.\30\2\u00a5!\3\2\2\2\u00a6\u00a7\5$\23\2\u00a7#\3\2\2\2\u00a8\u00aa"+
		"\7\13\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2"+
		"\u00ab\u00ac\5&\24\2\u00ac%\3\2\2\2\u00ad\u00b0\5(\25\2\u00ae\u00b0\5"+
		"*\26\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\'\3\2\2\2\u00b1\u00b2"+
		"\5> \2\u00b2)\3\2\2\2\u00b3\u00b7\7\f\2\2\u00b4\u00b7\7\6\2\2\u00b5\u00b7"+
		"\5@!\2\u00b6\u00b3\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7"+
		"+\3\2\2\2\u00b8\u00b9\5\62\32\2\u00b9\u00ba\5.\30\2\u00ba-\3\2\2\2\u00bb"+
		"\u00bd\5\60\31\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3"+
		"\2\2\2\u00be\u00bf\3\2\2\2\u00bf/\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2"+
		"\7\7\2\2\u00c2\u00c3\5\4\3\2\u00c3\u00c4\7\n\2\2\u00c4\61\3\2\2\2\u00c5"+
		"\u00c9\5\64\33\2\u00c6\u00c9\58\35\2\u00c7\u00c9\5:\36\2\u00c8\u00c5\3"+
		"\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\63\3\2\2\2\u00ca"+
		"\u00cd\5\66\34\2\u00cb\u00cd\7\64\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd\65\3\2\2\2\u00ce\u00d0\7%\2\2\u00cf\u00ce\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\t\6\2\2\u00d2\67\3\2\2"+
		"\2\u00d3\u00d4\7\r\2\2\u00d4\u00d5\5\4\3\2\u00d5\u00d6\7\17\2\2\u00d6"+
		"9\3\2\2\2\u00d7\u00d8\5<\37\2\u00d8\u00e1\7\r\2\2\u00d9\u00de\5\4\3\2"+
		"\u00da\u00db\7\63\2\2\u00db\u00dd\5\4\3\2\u00dc\u00da\3\2\2\2\u00dd\u00e0"+
		"\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e1\u00d9\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e4\7\17\2\2\u00e4;\3\2\2\2\u00e5\u00e6\t\7\2\2\u00e6=\3"+
		"\2\2\2\u00e7\u00e8\7\22\2\2\u00e8?\3\2\2\2\u00e9\u00ea\7\60\2\2\u00ea"+
		"A\3\2\2\2\30KS[cksy\u0083\u0088\u008b\u0092\u0098\u009f\u00a9\u00af\u00b6"+
		"\u00be\u00c8\u00cc\u00cf\u00de\u00e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}