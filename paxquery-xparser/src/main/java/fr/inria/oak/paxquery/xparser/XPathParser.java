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
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, AGGR_FUNCT=14, TEXTFUNCTION=15, SLASH=16, 
		SLASHSLASH=17, OR=18, AND=19, NOT=20, EQ=21, EQ_S=22, NE=23, NE_S=24, 
		LT=25, LT_S=26, LE=27, LE_S=28, GT=29, GT_S=30, GE=31, GE_S=32, OP_ADD=33, 
		OP_SUB=34, OP_MUL=35, VAR=36, LEFTCURL=37, RIGHTCURL=38, OPEN_ATTR_VAR_DOUBLE=39, 
		OPEN_ATTR_VAR_SINGLE=40, CLOSE_ATTR_VAR_DOUBLE=41, CLOSE_ATTR_VAR_SINGLE=42, 
		OPEN_CLOSING_TAG=43, CLOSE_OPENING_TAG=44, QNAME_TOKEN=45, SINGLE_QUOTE=46, 
		DOUBLE_QUOTE=47, COMMA=48, STRING_LITERAL=49, REFERENCE=50, ENTITY_REF=51, 
		CHAR_REF=52, INTEGER_LITERAL=53, DECIMAL_LITERAL=54, DIGITS=55, WS=56;
	public static final String[] tokenNames = {
		"<INVALID>", "'substring'", "'true'", "'ceiling'", "'mod'", "'['", "'concat'", 
		"']'", "'@'", "'div'", "'('", "'floor'", "')'", "'false'", "AGGR_FUNCT", 
		"'text()'", "'/'", "'//'", "'or'", "'and'", "'not'", "'eq'", "'='", "'ne'", 
		"'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", "'>'", "'ge'", "'>='", 
		"'+'", "'-'", "'*'", "VAR", "'{'", "'}'", "OPEN_ATTR_VAR_DOUBLE", "OPEN_ATTR_VAR_SINGLE", 
		"CLOSE_ATTR_VAR_DOUBLE", "CLOSE_ATTR_VAR_SINGLE", "OPEN_CLOSING_TAG", 
		"CLOSE_OPENING_TAG", "QNAME_TOKEN", "'''", "'\"'", "','", "STRING_LITERAL", 
		"REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", "DECIMAL_LITERAL", 
		"DIGITS", "WS"
	};
	public static final int
		RULE_xpath = 0, RULE_expr_xp = 1, RULE_orExpr_xp = 2, RULE_andExpr_xp = 3, 
		RULE_comparativeExpr_xp = 4, RULE_arithmeticExpr_xp = 5, RULE_unaryExpr = 6, 
		RULE_valueExpr = 7, RULE_pathExpr = 8, RULE_relativePathExpr = 9, RULE_relativePathExpr2 = 10, 
		RULE_stepExpr = 11, RULE_axisStep = 12, RULE_forwardStep = 13, RULE_abbrevForwardStep = 14, 
		RULE_nodeTest = 15, RULE_kindTest = 16, RULE_nameTest = 17, RULE_filterExpr = 18, 
		RULE_predicateList_xp = 19, RULE_predicate_xp = 20, RULE_primaryExpr = 21, 
		RULE_literal = 22, RULE_numericLiteral = 23, RULE_parenthesizedExpr = 24, 
		RULE_functionCall = 25, RULE_functionName = 26, RULE_textTest = 27, RULE_qName = 28;
	public static final String[] ruleNames = {
		"xpath", "expr_xp", "orExpr_xp", "andExpr_xp", "comparativeExpr_xp", "arithmeticExpr_xp", 
		"unaryExpr", "valueExpr", "pathExpr", "relativePathExpr", "relativePathExpr2", 
		"stepExpr", "axisStep", "forwardStep", "abbrevForwardStep", "nodeTest", 
		"kindTest", "nameTest", "filterExpr", "predicateList_xp", "predicate_xp", 
		"primaryExpr", "literal", "numericLiteral", "parenthesizedExpr", "functionCall", 
		"functionName", "textTest", "qName"
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
		public Expr_xpContext expr_xp() {
			return getRuleContext(Expr_xpContext.class,0);
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
			setState(58); expr_xp();
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

	public static class Expr_xpContext extends ParserRuleContext {
		public OrExpr_xpContext orExpr_xp() {
			return getRuleContext(OrExpr_xpContext.class,0);
		}
		public Expr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitExpr_xp(this);
		}
	}

	public final Expr_xpContext expr_xp() throws RecognitionException {
		Expr_xpContext _localctx = new Expr_xpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr_xp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); orExpr_xp();
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
			setState(62); andExpr_xp();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(63); match(OR);
				setState(64); andExpr_xp();
				}
				}
				setState(69);
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
		public ComparativeExpr_xpContext comparativeExpr_xp(int i) {
			return getRuleContext(ComparativeExpr_xpContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(XPathParser.AND, i);
		}
		public List<ComparativeExpr_xpContext> comparativeExpr_xp() {
			return getRuleContexts(ComparativeExpr_xpContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(XPathParser.AND); }
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
			setState(70); comparativeExpr_xp();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(71); match(AND);
				setState(72); comparativeExpr_xp();
				}
				}
				setState(77);
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

	public static class ComparativeExpr_xpContext extends ParserRuleContext {
		public TerminalNode NE_S() { return getToken(XPathParser.NE_S, 0); }
		public ArithmeticExpr_xpContext arithmeticExpr_xp(int i) {
			return getRuleContext(ArithmeticExpr_xpContext.class,i);
		}
		public List<ArithmeticExpr_xpContext> arithmeticExpr_xp() {
			return getRuleContexts(ArithmeticExpr_xpContext.class);
		}
		public TerminalNode LE_S() { return getToken(XPathParser.LE_S, 0); }
		public TerminalNode EQ_S() { return getToken(XPathParser.EQ_S, 0); }
		public TerminalNode GT_S() { return getToken(XPathParser.GT_S, 0); }
		public TerminalNode GE_S() { return getToken(XPathParser.GE_S, 0); }
		public TerminalNode LT_S() { return getToken(XPathParser.LT_S, 0); }
		public ComparativeExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparativeExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterComparativeExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitComparativeExpr_xp(this);
		}
	}

	public final ComparativeExpr_xpContext comparativeExpr_xp() throws RecognitionException {
		ComparativeExpr_xpContext _localctx = new ComparativeExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comparativeExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); arithmeticExpr_xp();
			setState(81);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ_S) | (1L << NE_S) | (1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) {
				{
				setState(79);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ_S) | (1L << NE_S) | (1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(80); arithmeticExpr_xp();
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

	public static class ArithmeticExpr_xpContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public TerminalNode OP_ADD() { return getToken(XPathParser.OP_ADD, 0); }
		public TerminalNode OP_SUB() { return getToken(XPathParser.OP_SUB, 0); }
		public TerminalNode OP_MUL() { return getToken(XPathParser.OP_MUL, 0); }
		public ArithmeticExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpr_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterArithmeticExpr_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitArithmeticExpr_xp(this);
		}
	}

	public final ArithmeticExpr_xpContext arithmeticExpr_xp() throws RecognitionException {
		ArithmeticExpr_xpContext _localctx = new ArithmeticExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arithmeticExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); unaryExpr();
			setState(86);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 9) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) {
				{
				setState(84);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 9) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(85); unaryExpr();
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

	public static class UnaryExprContext extends ParserRuleContext {
		public ValueExprContext valueExpr() {
			return getRuleContext(ValueExprContext.class,0);
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
		enterRule(_localctx, 12, RULE_unaryExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(88); match(OP_SUB);
					}
					} 
				}
				setState(93);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(94); valueExpr();
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
		enterRule(_localctx, 14, RULE_valueExpr);
		try {
			setState(101);
			switch (_input.LA(1)) {
			case 1:
			case 2:
			case 3:
			case 6:
			case 10:
			case 11:
			case 13:
			case NOT:
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); filterExpr();
				setState(98);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(97); pathExpr();
					}
					break;
				}
				}
				break;
			case 4:
			case 8:
			case 9:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(100); pathExpr();
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
		enterRule(_localctx, 16, RULE_pathExpr);
		try {
			setState(108);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(103); match(SLASH);
				setState(104); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(105); match(SLASHSLASH);
				setState(106); relativePathExpr();
				}
				break;
			case 4:
			case 8:
			case 9:
			case TEXTFUNCTION:
			case QNAME_TOKEN:
				_localctx = new PathExpr_relativePathExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(107); relativePathExpr();
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
		enterRule(_localctx, 18, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); stepExpr();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(111); relativePathExpr2();
				}
				}
				setState(116);
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
		enterRule(_localctx, 20, RULE_relativePathExpr2);
		try {
			setState(121);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(117); match(SLASH);
				setState(118); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(119); match(SLASHSLASH);
				setState(120); stepExpr();
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
		enterRule(_localctx, 22, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); axisStep();
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
		public PredicateList_xpContext predicateList_xp() {
			return getRuleContext(PredicateList_xpContext.class,0);
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
		enterRule(_localctx, 24, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); forwardStep();
			setState(126); predicateList_xp();
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
		enterRule(_localctx, 26, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); abbrevForwardStep();
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
		enterRule(_localctx, 28, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if (_la==8) {
				{
				setState(130); match(8);
				}
			}

			setState(133); nodeTest();
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
		enterRule(_localctx, 30, RULE_nodeTest);
		try {
			setState(137);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(135); kindTest();
				}
				break;
			case 4:
			case 9:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(136); nameTest();
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
		enterRule(_localctx, 32, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); textTest();
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
		enterRule(_localctx, 34, RULE_nameTest);
		try {
			setState(144);
			switch (_input.LA(1)) {
			case 9:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); match(9);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 2);
				{
				setState(142); match(4);
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(143); qName();
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
		public PrimaryExprContext primaryExpr() {
			return getRuleContext(PrimaryExprContext.class,0);
		}
		public PredicateList_xpContext predicateList_xp() {
			return getRuleContext(PredicateList_xpContext.class,0);
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
		enterRule(_localctx, 36, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); primaryExpr();
			setState(147); predicateList_xp();
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

	public static class PredicateList_xpContext extends ParserRuleContext {
		public List<Predicate_xpContext> predicate_xp() {
			return getRuleContexts(Predicate_xpContext.class);
		}
		public Predicate_xpContext predicate_xp(int i) {
			return getRuleContext(Predicate_xpContext.class,i);
		}
		public PredicateList_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateList_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPredicateList_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPredicateList_xp(this);
		}
	}

	public final PredicateList_xpContext predicateList_xp() throws RecognitionException {
		PredicateList_xpContext _localctx = new PredicateList_xpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_predicateList_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(149); predicate_xp();
				}
				}
				setState(154);
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

	public static class Predicate_xpContext extends ParserRuleContext {
		public Expr_xpContext expr_xp() {
			return getRuleContext(Expr_xpContext.class,0);
		}
		public Predicate_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_xp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPredicate_xp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPredicate_xp(this);
		}
	}

	public final Predicate_xpContext predicate_xp() throws RecognitionException {
		Predicate_xpContext _localctx = new Predicate_xpContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_predicate_xp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); match(5);
			setState(156); expr_xp();
			setState(157); match(7);
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
		enterRule(_localctx, 42, RULE_primaryExpr);
		try {
			setState(162);
			switch (_input.LA(1)) {
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(159); literal();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 2);
				{
				setState(160); parenthesizedExpr();
				}
				break;
			case 1:
			case 2:
			case 3:
			case 6:
			case 11:
			case 13:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(161); functionCall();
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
		enterRule(_localctx, 44, RULE_literal);
		try {
			setState(166);
			switch (_input.LA(1)) {
			case OP_SUB:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(164); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(165); match(STRING_LITERAL);
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
		enterRule(_localctx, 46, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if (_la==OP_SUB) {
				{
				setState(168); match(OP_SUB);
				}
			}

			setState(171);
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
		public Expr_xpContext expr_xp() {
			return getRuleContext(Expr_xpContext.class,0);
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
		enterRule(_localctx, 48, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); match(10);
			setState(174); expr_xp();
			setState(175); match(12);
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
		public List<Expr_xpContext> expr_xp() {
			return getRuleContexts(Expr_xpContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(XPathParser.COMMA); }
		public Expr_xpContext expr_xp(int i) {
			return getRuleContext(Expr_xpContext.class,i);
		}
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
		enterRule(_localctx, 50, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177); functionName();
			setState(178); match(10);
			setState(187);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 4) | (1L << 6) | (1L << 8) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 13) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << OP_SUB) | (1L << QNAME_TOKEN) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DECIMAL_LITERAL))) != 0)) {
				{
				setState(179); expr_xp();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(180); match(COMMA);
					setState(181); expr_xp();
					}
					}
					setState(186);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(189); match(12);
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
		enterRule(_localctx, 52, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 6) | (1L << 11) | (1L << 13) | (1L << NOT))) != 0)) ) {
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
		enterRule(_localctx, 54, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); match(TEXTFUNCTION);
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
		enterRule(_localctx, 56, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); match(QNAME_TOKEN);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3:\u00c8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\4\7\4D\n\4\f\4\16\4G\13\4\3\5\3\5\3\5\7\5L\n\5\f\5\16\5O\13\5\3"+
		"\6\3\6\3\6\5\6T\n\6\3\7\3\7\3\7\5\7Y\n\7\3\b\7\b\\\n\b\f\b\16\b_\13\b"+
		"\3\b\3\b\3\t\3\t\5\te\n\t\3\t\5\th\n\t\3\n\3\n\3\n\3\n\3\n\5\no\n\n\3"+
		"\13\3\13\7\13s\n\13\f\13\16\13v\13\13\3\f\3\f\3\f\3\f\5\f|\n\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\5\20\u0086\n\20\3\20\3\20\3\21\3\21\5\21"+
		"\u008c\n\21\3\22\3\22\3\23\3\23\3\23\5\23\u0093\n\23\3\24\3\24\3\24\3"+
		"\25\7\25\u0099\n\25\f\25\16\25\u009c\13\25\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\5\27\u00a5\n\27\3\30\3\30\5\30\u00a9\n\30\3\31\5\31\u00ac\n\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\7\33\u00b9\n\33"+
		"\f\33\16\33\u00bc\13\33\5\33\u00be\n\33\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:\2\6\b\2\30\30\32\32\34\34\36\36  \"\"\5\2\6\6\13\13#%"+
		"\3\2\678\7\2\3\5\b\b\r\r\17\17\26\26\u00c0\2<\3\2\2\2\4>\3\2\2\2\6@\3"+
		"\2\2\2\bH\3\2\2\2\nP\3\2\2\2\fU\3\2\2\2\16]\3\2\2\2\20g\3\2\2\2\22n\3"+
		"\2\2\2\24p\3\2\2\2\26{\3\2\2\2\30}\3\2\2\2\32\177\3\2\2\2\34\u0082\3\2"+
		"\2\2\36\u0085\3\2\2\2 \u008b\3\2\2\2\"\u008d\3\2\2\2$\u0092\3\2\2\2&\u0094"+
		"\3\2\2\2(\u009a\3\2\2\2*\u009d\3\2\2\2,\u00a4\3\2\2\2.\u00a8\3\2\2\2\60"+
		"\u00ab\3\2\2\2\62\u00af\3\2\2\2\64\u00b3\3\2\2\2\66\u00c1\3\2\2\28\u00c3"+
		"\3\2\2\2:\u00c5\3\2\2\2<=\5\4\3\2=\3\3\2\2\2>?\5\6\4\2?\5\3\2\2\2@E\5"+
		"\b\5\2AB\7\24\2\2BD\5\b\5\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\7"+
		"\3\2\2\2GE\3\2\2\2HM\5\n\6\2IJ\7\25\2\2JL\5\n\6\2KI\3\2\2\2LO\3\2\2\2"+
		"MK\3\2\2\2MN\3\2\2\2N\t\3\2\2\2OM\3\2\2\2PS\5\f\7\2QR\t\2\2\2RT\5\f\7"+
		"\2SQ\3\2\2\2ST\3\2\2\2T\13\3\2\2\2UX\5\16\b\2VW\t\3\2\2WY\5\16\b\2XV\3"+
		"\2\2\2XY\3\2\2\2Y\r\3\2\2\2Z\\\7$\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]"+
		"^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\5\20\t\2a\17\3\2\2\2bd\5&\24\2ce\5\22"+
		"\n\2dc\3\2\2\2de\3\2\2\2eh\3\2\2\2fh\5\22\n\2gb\3\2\2\2gf\3\2\2\2h\21"+
		"\3\2\2\2ij\7\22\2\2jo\5\24\13\2kl\7\23\2\2lo\5\24\13\2mo\5\24\13\2ni\3"+
		"\2\2\2nk\3\2\2\2nm\3\2\2\2o\23\3\2\2\2pt\5\30\r\2qs\5\26\f\2rq\3\2\2\2"+
		"sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\25\3\2\2\2vt\3\2\2\2wx\7\22\2\2x|\5\30"+
		"\r\2yz\7\23\2\2z|\5\30\r\2{w\3\2\2\2{y\3\2\2\2|\27\3\2\2\2}~\5\32\16\2"+
		"~\31\3\2\2\2\177\u0080\5\34\17\2\u0080\u0081\5(\25\2\u0081\33\3\2\2\2"+
		"\u0082\u0083\5\36\20\2\u0083\35\3\2\2\2\u0084\u0086\7\n\2\2\u0085\u0084"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\5 \21\2\u0088"+
		"\37\3\2\2\2\u0089\u008c\5\"\22\2\u008a\u008c\5$\23\2\u008b\u0089\3\2\2"+
		"\2\u008b\u008a\3\2\2\2\u008c!\3\2\2\2\u008d\u008e\58\35\2\u008e#\3\2\2"+
		"\2\u008f\u0093\7\13\2\2\u0090\u0093\7\6\2\2\u0091\u0093\5:\36\2\u0092"+
		"\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093%\3\2\2\2"+
		"\u0094\u0095\5,\27\2\u0095\u0096\5(\25\2\u0096\'\3\2\2\2\u0097\u0099\5"+
		"*\26\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b)\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7\7\2\2"+
		"\u009e\u009f\5\4\3\2\u009f\u00a0\7\t\2\2\u00a0+\3\2\2\2\u00a1\u00a5\5"+
		".\30\2\u00a2\u00a5\5\62\32\2\u00a3\u00a5\5\64\33\2\u00a4\u00a1\3\2\2\2"+
		"\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5-\3\2\2\2\u00a6\u00a9\5"+
		"\60\31\2\u00a7\u00a9\7\63\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a7\3\2\2\2"+
		"\u00a9/\3\2\2\2\u00aa\u00ac\7$\2\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\t\4\2\2\u00ae\61\3\2\2\2\u00af\u00b0"+
		"\7\f\2\2\u00b0\u00b1\5\4\3\2\u00b1\u00b2\7\16\2\2\u00b2\63\3\2\2\2\u00b3"+
		"\u00b4\5\66\34\2\u00b4\u00bd\7\f\2\2\u00b5\u00ba\5\4\3\2\u00b6\u00b7\7"+
		"\62\2\2\u00b7\u00b9\5\4\3\2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bd\u00b5\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c0\7\16\2\2\u00c0\65\3\2\2\2\u00c1\u00c2\t\5\2\2\u00c2\67\3\2\2\2"+
		"\u00c3\u00c4\7\21\2\2\u00c49\3\2\2\2\u00c5\u00c6\7/\2\2\u00c6;\3\2\2\2"+
		"\25EMSX]dgnt{\u0085\u008b\u0092\u009a\u00a4\u00a8\u00ab\u00ba\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}