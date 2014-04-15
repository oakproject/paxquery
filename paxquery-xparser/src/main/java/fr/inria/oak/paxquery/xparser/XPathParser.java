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
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, TEXTFUNCTION=19, SLASH=20, SLASHSLASH=21, OR=22, AND=23, 
		NOT=24, EQ=25, EQ_S=26, NE=27, NE_S=28, LT=29, LT_S=30, LE=31, LE_S=32, 
		GT=33, GT_S=34, GE=35, GE_S=36, VAR=37, LEFTCURL=38, RIGHTCURL=39, OPEN_TAG=40, 
		CLOSE_TAG=41, QNAME_TOKEN=42, STRING_LITERAL=43, REFERENCE=44, ENTITY_REF=45, 
		CHAR_REF=46, INTEGER_LITERAL=47, DECIMAL_LITERAL=48, DIGITS=49, WS=50;
	public static final String[] tokenNames = {
		"<INVALID>", "'substring'", "'true'", "'ceiling'", "'mod'", "'['", "'|'", 
		"'concat'", "']'", "'@'", "'div'", "'('", "'floor'", "')'", "'*'", "'+'", 
		"','", "'-'", "'false'", "'text()'", "'/'", "'//'", "'or'", "'and'", "'not'", 
		"'eq'", "'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", 
		"'>'", "'ge'", "'>='", "VAR", "'{'", "'}'", "OPEN_TAG", "CLOSE_TAG", "QNAME_TOKEN", 
		"STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", 
		"DECIMAL_LITERAL", "DIGITS", "WS"
	};
	public static final int
		RULE_xpath = 0, RULE_expr = 1, RULE_orExpr = 2, RULE_orExpr2 = 3, RULE_andExpr = 4, 
		RULE_andExpr2 = 5, RULE_equalityExpr = 6, RULE_equalityExpr2 = 7, RULE_relationalExpr = 8, 
		RULE_relationalExpr2 = 9, RULE_additiveExpr = 10, RULE_additiveExpr2 = 11, 
		RULE_multiplicativeExpr = 12, RULE_multiplicativeExpr2 = 13, RULE_unaryExpr = 14, 
		RULE_unionExpr = 15, RULE_valueExpr = 16, RULE_pathExpr = 17, RULE_relativePathExpr = 18, 
		RULE_relativePathExpr2 = 19, RULE_stepExpr = 20, RULE_axisStep = 21, RULE_forwardStep = 22, 
		RULE_abbrevForwardStep = 23, RULE_nodeTest = 24, RULE_kindTest = 25, RULE_nameTest = 26, 
		RULE_filterExpr = 27, RULE_predicateList = 28, RULE_predicate = 29, RULE_primaryExpr = 30, 
		RULE_literal = 31, RULE_numericLiteral = 32, RULE_parenthesizedExpr = 33, 
		RULE_functionCall = 34, RULE_functionName = 35, RULE_textTest = 36, RULE_qName = 37;
	public static final String[] ruleNames = {
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
			setState(76); expr();
			}
		}
		catch (RecognitionException re) {
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
			setState(78); orExpr();
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_orExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); andExpr();
			setState(81); orExpr2();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode OR() { return getToken(XPathParser.OR, 0); }
		public OrExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterOrExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitOrExpr2(this);
		}
	}

	public final OrExpr2Context orExpr2() throws RecognitionException {
		OrExpr2Context _localctx = new OrExpr2Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_orExpr2);
		try {
			setState(88);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(83); match(OR);
				setState(84); andExpr();
				setState(85); orExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 16:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_andExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); equalityExpr();
			setState(91); andExpr2();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode AND() { return getToken(XPathParser.AND, 0); }
		public AndExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAndExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAndExpr2(this);
		}
	}

	public final AndExpr2Context andExpr2() throws RecognitionException {
		AndExpr2Context _localctx = new AndExpr2Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_andExpr2);
		try {
			setState(98);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(93); match(AND);
				setState(94); equalityExpr();
				setState(95); andExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 16:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitEqualityExpr(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_equalityExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); relationalExpr();
			setState(101); equalityExpr2();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode NE_S() { return getToken(XPathParser.NE_S, 0); }
		public TerminalNode EQ_S() { return getToken(XPathParser.EQ_S, 0); }
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterEqualityExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitEqualityExpr2(this);
		}
	}

	public final EqualityExpr2Context equalityExpr2() throws RecognitionException {
		EqualityExpr2Context _localctx = new EqualityExpr2Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_equalityExpr2);
		try {
			setState(112);
			switch (_input.LA(1)) {
			case EQ_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(103); match(EQ_S);
				setState(104); relationalExpr();
				setState(105); equalityExpr2();
				}
				break;
			case NE_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(107); match(NE_S);
				setState(108); relationalExpr();
				setState(109); equalityExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 16:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelationalExpr(this);
		}
	}

	public final RelationalExprContext relationalExpr() throws RecognitionException {
		RelationalExprContext _localctx = new RelationalExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_relationalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); additiveExpr();
			setState(115); relationalExpr2();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode LE_S() { return getToken(XPathParser.LE_S, 0); }
		public RelationalExpr2Context relationalExpr2() {
			return getRuleContext(RelationalExpr2Context.class,0);
		}
		public TerminalNode GT_S() { return getToken(XPathParser.GT_S, 0); }
		public TerminalNode GE_S() { return getToken(XPathParser.GE_S, 0); }
		public TerminalNode LT_S() { return getToken(XPathParser.LT_S, 0); }
		public RelationalExpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelationalExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelationalExpr2(this);
		}
	}

	public final RelationalExpr2Context relationalExpr2() throws RecognitionException {
		RelationalExpr2Context _localctx = new RelationalExpr2Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_relationalExpr2);
		try {
			setState(134);
			switch (_input.LA(1)) {
			case LT_S:
				enterOuterAlt(_localctx, 1);
				{
				setState(117); match(LT_S);
				setState(118); additiveExpr();
				setState(119); relationalExpr2();
				}
				break;
			case GT_S:
				enterOuterAlt(_localctx, 2);
				{
				setState(121); match(GT_S);
				setState(122); additiveExpr();
				setState(123); relationalExpr2();
				}
				break;
			case LE_S:
				enterOuterAlt(_localctx, 3);
				{
				setState(125); match(LE_S);
				setState(126); additiveExpr();
				setState(127); relationalExpr2();
				}
				break;
			case GE_S:
				enterOuterAlt(_localctx, 4);
				{
				setState(129); match(GE_S);
				setState(130); additiveExpr();
				setState(131); relationalExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 16:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAdditiveExpr(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_additiveExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); multiplicativeExpr();
			setState(137); additiveExpr2();
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAdditiveExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAdditiveExpr2(this);
		}
	}

	public final AdditiveExpr2Context additiveExpr2() throws RecognitionException {
		AdditiveExpr2Context _localctx = new AdditiveExpr2Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_additiveExpr2);
		try {
			setState(148);
			switch (_input.LA(1)) {
			case 15:
				enterOuterAlt(_localctx, 1);
				{
				setState(139); match(15);
				setState(140); multiplicativeExpr();
				setState(141); additiveExpr2();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 2);
				{
				setState(143); match(17);
				setState(144); multiplicativeExpr();
				setState(145); additiveExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 16:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMultiplicativeExpr(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multiplicativeExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); unaryExpr();
			setState(151); multiplicativeExpr2();
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMultiplicativeExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMultiplicativeExpr2(this);
		}
	}

	public final MultiplicativeExpr2Context multiplicativeExpr2() throws RecognitionException {
		MultiplicativeExpr2Context _localctx = new MultiplicativeExpr2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_multiplicativeExpr2);
		try {
			setState(166);
			switch (_input.LA(1)) {
			case 14:
				enterOuterAlt(_localctx, 1);
				{
				setState(153); match(14);
				setState(154); unaryExpr();
				setState(155); multiplicativeExpr2();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 2);
				{
				setState(157); match(10);
				setState(158); unaryExpr();
				setState(159); multiplicativeExpr2();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 3);
				{
				setState(161); match(4);
				setState(162); unaryExpr();
				setState(163); multiplicativeExpr2();
				}
				break;
			case EOF:
			case 8:
			case 13:
			case 15:
			case 16:
			case 17:
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnaryExpr(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_unaryExpr);
		try {
			setState(171);
			switch (_input.LA(1)) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 7:
			case 9:
			case 10:
			case 11:
			case 12:
			case 18:
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
				setState(168); unionExpr();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 2);
				{
				setState(169); match(17);
				setState(170); unaryExpr();
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnionExpr(this);
		}
	}

	public final UnionExprContext unionExpr() throws RecognitionException {
		UnionExprContext _localctx = new UnionExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unionExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); valueExpr();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==6) {
				{
				{
				setState(174); match(6);
				setState(175); valueExpr();
				}
				}
				setState(180);
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
		enterRule(_localctx, 32, RULE_valueExpr);
		try {
			setState(186);
			switch (_input.LA(1)) {
			case 1:
			case 2:
			case 3:
			case 7:
			case 11:
			case 12:
			case 18:
			case NOT:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(181); filterExpr();
				setState(183);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(182); pathExpr();
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
				setState(185); pathExpr();
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
		enterRule(_localctx, 34, RULE_pathExpr);
		try {
			setState(193);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(SLASH);
				setState(189); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(190); match(SLASHSLASH);
				setState(191); relativePathExpr();
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
				setState(192); relativePathExpr();
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
		enterRule(_localctx, 36, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); stepExpr();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(196); relativePathExpr2();
				}
				}
				setState(201);
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
		enterRule(_localctx, 38, RULE_relativePathExpr2);
		try {
			setState(206);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(202); match(SLASH);
				setState(203); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204); match(SLASHSLASH);
				setState(205); stepExpr();
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
		enterRule(_localctx, 40, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); axisStep();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 42, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); forwardStep();
			setState(211); predicateList();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 44, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); abbrevForwardStep();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 46, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(215); match(9);
				}
			}

			setState(218); nodeTest();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 48, RULE_nodeTest);
		try {
			setState(222);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(220); kindTest();
				}
				break;
			case 4:
			case 10:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(221); nameTest();
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
		enterRule(_localctx, 50, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); textTest();
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNameTest_qName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNameTest_qName(this);
		}
	}
	public static class NameTest_divContext extends NameTestContext {
		public NameTest_divContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNameTest_div(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNameTest_div(this);
		}
	}
	public static class NameTEst_modContext extends NameTestContext {
		public NameTEst_modContext(NameTestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNameTEst_mod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNameTEst_mod(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_nameTest);
		try {
			setState(229);
			switch (_input.LA(1)) {
			case 10:
				_localctx = new NameTest_divContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(226); match(10);
				}
				break;
			case 4:
				_localctx = new NameTEst_modContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(227); match(4);
				}
				break;
			case QNAME_TOKEN:
				_localctx = new NameTest_qNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(228); qName();
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
		enterRule(_localctx, 54, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); primaryExpr();
			setState(232); predicateList();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 56, RULE_predicateList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(234); predicate();
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
		enterRule(_localctx, 58, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); match(5);
			setState(241); expr();
			setState(242); match(8);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 60, RULE_primaryExpr);
		try {
			setState(247);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(244); literal();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 2);
				{
				setState(245); parenthesizedExpr();
				}
				break;
			case 1:
			case 2:
			case 3:
			case 7:
			case 12:
			case 18:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(246); functionCall();
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
		enterRule(_localctx, 62, RULE_literal);
		try {
			setState(251);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(250); match(STRING_LITERAL);
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
		enterRule(_localctx, 64, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
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
		enterRule(_localctx, 66, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(11);
			setState(256); expr();
			setState(257); match(13);
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); functionName();
			setState(260); match(11);
			setState(269);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 4) | (1L << 7) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 17) | (1L << 18) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << QNAME_TOKEN) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << DECIMAL_LITERAL))) != 0)) {
				{
				setState(261); expr();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==16) {
					{
					{
					setState(262); match(16);
					setState(263); expr();
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(271); match(13);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << 7) | (1L << 12) | (1L << 18) | (1L << NOT))) != 0)) ) {
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
		enterRule(_localctx, 72, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(TEXTFUNCTION);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(QNAME_TOKEN);
			}
		}
		catch (RecognitionException re) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\64\u011a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\5\5[\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7e\n"+
		"\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\ts\n\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u0089\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\5\r\u0097\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a9\n\17\3\20\3\20\3\20\5\20"+
		"\u00ae\n\20\3\21\3\21\3\21\7\21\u00b3\n\21\f\21\16\21\u00b6\13\21\3\22"+
		"\3\22\5\22\u00ba\n\22\3\22\5\22\u00bd\n\22\3\23\3\23\3\23\3\23\3\23\5"+
		"\23\u00c4\n\23\3\24\3\24\7\24\u00c8\n\24\f\24\16\24\u00cb\13\24\3\25\3"+
		"\25\3\25\3\25\5\25\u00d1\n\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31"+
		"\5\31\u00db\n\31\3\31\3\31\3\32\3\32\5\32\u00e1\n\32\3\33\3\33\3\34\3"+
		"\34\3\34\5\34\u00e8\n\34\3\35\3\35\3\35\3\36\7\36\u00ee\n\36\f\36\16\36"+
		"\u00f1\13\36\3\37\3\37\3\37\3\37\3 \3 \3 \5 \u00fa\n \3!\3!\5!\u00fe\n"+
		"!\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3$\7$\u010b\n$\f$\16$\u010e\13$\5$\u0110"+
		"\n$\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\2\2(\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL\2\4\3\2\61\62\7\2\3\5\t\t\16\16"+
		"\24\24\32\32\u0112\2N\3\2\2\2\4P\3\2\2\2\6R\3\2\2\2\bZ\3\2\2\2\n\\\3\2"+
		"\2\2\fd\3\2\2\2\16f\3\2\2\2\20r\3\2\2\2\22t\3\2\2\2\24\u0088\3\2\2\2\26"+
		"\u008a\3\2\2\2\30\u0096\3\2\2\2\32\u0098\3\2\2\2\34\u00a8\3\2\2\2\36\u00ad"+
		"\3\2\2\2 \u00af\3\2\2\2\"\u00bc\3\2\2\2$\u00c3\3\2\2\2&\u00c5\3\2\2\2"+
		"(\u00d0\3\2\2\2*\u00d2\3\2\2\2,\u00d4\3\2\2\2.\u00d7\3\2\2\2\60\u00da"+
		"\3\2\2\2\62\u00e0\3\2\2\2\64\u00e2\3\2\2\2\66\u00e7\3\2\2\28\u00e9\3\2"+
		"\2\2:\u00ef\3\2\2\2<\u00f2\3\2\2\2>\u00f9\3\2\2\2@\u00fd\3\2\2\2B\u00ff"+
		"\3\2\2\2D\u0101\3\2\2\2F\u0105\3\2\2\2H\u0113\3\2\2\2J\u0115\3\2\2\2L"+
		"\u0117\3\2\2\2NO\5\4\3\2O\3\3\2\2\2PQ\5\6\4\2Q\5\3\2\2\2RS\5\n\6\2ST\5"+
		"\b\5\2T\7\3\2\2\2UV\7\30\2\2VW\5\n\6\2WX\5\b\5\2X[\3\2\2\2Y[\3\2\2\2Z"+
		"U\3\2\2\2ZY\3\2\2\2[\t\3\2\2\2\\]\5\16\b\2]^\5\f\7\2^\13\3\2\2\2_`\7\31"+
		"\2\2`a\5\16\b\2ab\5\f\7\2be\3\2\2\2ce\3\2\2\2d_\3\2\2\2dc\3\2\2\2e\r\3"+
		"\2\2\2fg\5\22\n\2gh\5\20\t\2h\17\3\2\2\2ij\7\34\2\2jk\5\22\n\2kl\5\20"+
		"\t\2ls\3\2\2\2mn\7\36\2\2no\5\22\n\2op\5\20\t\2ps\3\2\2\2qs\3\2\2\2ri"+
		"\3\2\2\2rm\3\2\2\2rq\3\2\2\2s\21\3\2\2\2tu\5\26\f\2uv\5\24\13\2v\23\3"+
		"\2\2\2wx\7 \2\2xy\5\26\f\2yz\5\24\13\2z\u0089\3\2\2\2{|\7$\2\2|}\5\26"+
		"\f\2}~\5\24\13\2~\u0089\3\2\2\2\177\u0080\7\"\2\2\u0080\u0081\5\26\f\2"+
		"\u0081\u0082\5\24\13\2\u0082\u0089\3\2\2\2\u0083\u0084\7&\2\2\u0084\u0085"+
		"\5\26\f\2\u0085\u0086\5\24\13\2\u0086\u0089\3\2\2\2\u0087\u0089\3\2\2"+
		"\2\u0088w\3\2\2\2\u0088{\3\2\2\2\u0088\177\3\2\2\2\u0088\u0083\3\2\2\2"+
		"\u0088\u0087\3\2\2\2\u0089\25\3\2\2\2\u008a\u008b\5\32\16\2\u008b\u008c"+
		"\5\30\r\2\u008c\27\3\2\2\2\u008d\u008e\7\21\2\2\u008e\u008f\5\32\16\2"+
		"\u008f\u0090\5\30\r\2\u0090\u0097\3\2\2\2\u0091\u0092\7\23\2\2\u0092\u0093"+
		"\5\32\16\2\u0093\u0094\5\30\r\2\u0094\u0097\3\2\2\2\u0095\u0097\3\2\2"+
		"\2\u0096\u008d\3\2\2\2\u0096\u0091\3\2\2\2\u0096\u0095\3\2\2\2\u0097\31"+
		"\3\2\2\2\u0098\u0099\5\36\20\2\u0099\u009a\5\34\17\2\u009a\33\3\2\2\2"+
		"\u009b\u009c\7\20\2\2\u009c\u009d\5\36\20\2\u009d\u009e\5\34\17\2\u009e"+
		"\u00a9\3\2\2\2\u009f\u00a0\7\f\2\2\u00a0\u00a1\5\36\20\2\u00a1\u00a2\5"+
		"\34\17\2\u00a2\u00a9\3\2\2\2\u00a3\u00a4\7\6\2\2\u00a4\u00a5\5\36\20\2"+
		"\u00a5\u00a6\5\34\17\2\u00a6\u00a9\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u009b"+
		"\3\2\2\2\u00a8\u009f\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9"+
		"\35\3\2\2\2\u00aa\u00ae\5 \21\2\u00ab\u00ac\7\23\2\2\u00ac\u00ae\5\36"+
		"\20\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\37\3\2\2\2\u00af\u00b4"+
		"\5\"\22\2\u00b0\u00b1\7\b\2\2\u00b1\u00b3\5\"\22\2\u00b2\u00b0\3\2\2\2"+
		"\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5!\3"+
		"\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b9\58\35\2\u00b8\u00ba\5$\23\2\u00b9"+
		"\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00bd\5$"+
		"\23\2\u00bc\u00b7\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd#\3\2\2\2\u00be\u00bf"+
		"\7\26\2\2\u00bf\u00c4\5&\24\2\u00c0\u00c1\7\27\2\2\u00c1\u00c4\5&\24\2"+
		"\u00c2\u00c4\5&\24\2\u00c3\u00be\3\2\2\2\u00c3\u00c0\3\2\2\2\u00c3\u00c2"+
		"\3\2\2\2\u00c4%\3\2\2\2\u00c5\u00c9\5*\26\2\u00c6\u00c8\5(\25\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\'\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\7\26\2\2\u00cd\u00d1"+
		"\5*\26\2\u00ce\u00cf\7\27\2\2\u00cf\u00d1\5*\26\2\u00d0\u00cc\3\2\2\2"+
		"\u00d0\u00ce\3\2\2\2\u00d1)\3\2\2\2\u00d2\u00d3\5,\27\2\u00d3+\3\2\2\2"+
		"\u00d4\u00d5\5.\30\2\u00d5\u00d6\5:\36\2\u00d6-\3\2\2\2\u00d7\u00d8\5"+
		"\60\31\2\u00d8/\3\2\2\2\u00d9\u00db\7\13\2\2\u00da\u00d9\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\5\62\32\2\u00dd\61\3\2"+
		"\2\2\u00de\u00e1\5\64\33\2\u00df\u00e1\5\66\34\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00df\3\2\2\2\u00e1\63\3\2\2\2\u00e2\u00e3\5J&\2\u00e3\65\3\2\2\2\u00e4"+
		"\u00e8\7\f\2\2\u00e5\u00e8\7\6\2\2\u00e6\u00e8\5L\'\2\u00e7\u00e4\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\67\3\2\2\2\u00e9\u00ea"+
		"\5> \2\u00ea\u00eb\5:\36\2\u00eb9\3\2\2\2\u00ec\u00ee\5<\37\2\u00ed\u00ec"+
		"\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		";\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\7\7\2\2\u00f3\u00f4\5\4\3\2"+
		"\u00f4\u00f5\7\n\2\2\u00f5=\3\2\2\2\u00f6\u00fa\5@!\2\u00f7\u00fa\5D#"+
		"\2\u00f8\u00fa\5F$\2\u00f9\u00f6\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00f8"+
		"\3\2\2\2\u00fa?\3\2\2\2\u00fb\u00fe\5B\"\2\u00fc\u00fe\7-\2\2\u00fd\u00fb"+
		"\3\2\2\2\u00fd\u00fc\3\2\2\2\u00feA\3\2\2\2\u00ff\u0100\t\2\2\2\u0100"+
		"C\3\2\2\2\u0101\u0102\7\r\2\2\u0102\u0103\5\4\3\2\u0103\u0104\7\17\2\2"+
		"\u0104E\3\2\2\2\u0105\u0106\5H%\2\u0106\u010f\7\r\2\2\u0107\u010c\5\4"+
		"\3\2\u0108\u0109\7\22\2\2\u0109\u010b\5\4\3\2\u010a\u0108\3\2\2\2\u010b"+
		"\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0110\3\2"+
		"\2\2\u010e\u010c\3\2\2\2\u010f\u0107\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\u0111\3\2\2\2\u0111\u0112\7\17\2\2\u0112G\3\2\2\2\u0113\u0114\t\3\2\2"+
		"\u0114I\3\2\2\2\u0115\u0116\7\25\2\2\u0116K\3\2\2\2\u0117\u0118\7,\2\2"+
		"\u0118M\3\2\2\2\27Zdr\u0088\u0096\u00a8\u00ad\u00b4\u00b9\u00bc\u00c3"+
		"\u00c9\u00d0\u00da\u00e0\u00e7\u00ef\u00f9\u00fd\u010c\u010f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}