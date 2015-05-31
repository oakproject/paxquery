/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
		T__27=1, T__26=2, T__25=3, T__24=4, T__23=5, T__22=6, T__21=7, T__20=8, 
		T__19=9, T__18=10, T__17=11, T__16=12, T__15=13, T__14=14, T__13=15, T__12=16, 
		T__11=17, T__10=18, T__9=19, T__8=20, T__7=21, T__6=22, T__5=23, T__4=24, 
		T__3=25, T__2=26, T__1=27, T__0=28, AGGR_FUNCT=29, TEXTFUNCTION=30, SLASH=31, 
		SLASHSLASH=32, OR=33, AND=34, NOT=35, EQ=36, EQ_S=37, NE=38, NE_S=39, 
		LT=40, LT_S=41, LE=42, LE_S=43, GT=44, GT_S=45, GE=46, GE_S=47, OP_ADD=48, 
		OP_SUB=49, OP_MUL=50, VAR=51, LEFTCURL=52, RIGHTCURL=53, OPEN_ATTR_VAR_DOUBLE=54, 
		OPEN_ATTR_VAR_SINGLE=55, CLOSE_ATTR_VAR_DOUBLE=56, CLOSE_ATTR_VAR_SINGLE=57, 
		OPEN_CLOSING_TAG=58, CLOSE_OPENING_TAG=59, QNAME_TOKEN=60, SINGLE_QUOTE=61, 
		DOUBLE_QUOTE=62, COMMA=63, STRING_LITERAL=64, REFERENCE=65, ENTITY_REF=66, 
		CHAR_REF=67, INTEGER_LITERAL=68, DECIMAL_LITERAL=69, DIGITS=70, WS=71;
	public static final String[] tokenNames = {
		"<INVALID>", "'group by'", "'substring'", "'true'", "'return'", "'>>'", 
		"'<<'", "'concat'", "'for'", "':='", "'('", "'is'", "'false'", "'ceiling'", 
		"'mod'", "'['", "'distinct-values'", "'collection'", "']'", "'contains'", 
		"'@'", "'where'", "'let'", "'div'", "'in'", "'floor'", "')'", "'doc'", 
		"'empty'", "AGGR_FUNCT", "'text()'", "'/'", "'//'", "'or'", "'and'", "'not'", 
		"'eq'", "'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", 
		"'>'", "'ge'", "'>='", "'+'", "'-'", "'*'", "VAR", "'{'", "'}'", "OPEN_ATTR_VAR_DOUBLE", 
		"OPEN_ATTR_VAR_SINGLE", "CLOSE_ATTR_VAR_DOUBLE", "CLOSE_ATTR_VAR_SINGLE", 
		"OPEN_CLOSING_TAG", "CLOSE_OPENING_TAG", "QNAME_TOKEN", "'''", "'\"'", 
		"','", "STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", 
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
		RULE_attInner = 27, RULE_attInner2 = 28, RULE_eaName = 29, RULE_arithmeticExpr_xq = 30, 
		RULE_xpath = 31, RULE_expr_xp = 32, RULE_orExpr_xp = 33, RULE_andExpr_xp = 34, 
		RULE_comparativeExpr_xp = 35, RULE_arithmeticExpr_xp = 36, RULE_unaryExpr = 37, 
		RULE_valueExpr = 38, RULE_pathExpr = 39, RULE_relativePathExpr = 40, RULE_relativePathExpr2 = 41, 
		RULE_stepExpr = 42, RULE_axisStep = 43, RULE_forwardStep = 44, RULE_abbrevForwardStep = 45, 
		RULE_nodeTest = 46, RULE_kindTest = 47, RULE_nameTest = 48, RULE_filterExpr = 49, 
		RULE_predicateList_xp = 50, RULE_predicate_xp = 51, RULE_primaryExpr = 52, 
		RULE_literal = 53, RULE_numericLiteral = 54, RULE_parenthesizedExpr = 55, 
		RULE_functionCall = 56, RULE_functionName = 57, RULE_textTest = 58, RULE_qName = 59;
	public static final String[] ruleNames = {
		"xquery", "flwrexpr", "initial", "middle", "forStat", "forBinding", "let", 
		"letBinding", "arithExpr", "pathExpr_xq", "pathExprInner_xq", "aggrExpr", 
		"where", "orExpr_xq", "andExpr_xq", "boolExpr_xq", "boolExprInner_xq", 
		"pred", "vcmp", "ncmp", "contains", "empty", "groupBy", "returnStat", 
		"eleConst", "eleConstInner", "att", "attInner", "attInner2", "eaName", 
		"arithmeticExpr_xq", "xpath", "expr_xp", "orExpr_xp", "andExpr_xp", "comparativeExpr_xp", 
		"arithmeticExpr_xp", "unaryExpr", "valueExpr", "pathExpr", "relativePathExpr", 
		"relativePathExpr2", "stepExpr", "axisStep", "forwardStep", "abbrevForwardStep", 
		"nodeTest", "kindTest", "nameTest", "filterExpr", "predicateList_xp", 
		"predicate_xp", "primaryExpr", "literal", "numericLiteral", "parenthesizedExpr", 
		"functionCall", "functionName", "textTest", "qName"
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
		public TerminalNode EOF() { return getToken(XQueryParser.EOF, 0); }
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
			setState(120); flwrexpr();
			setState(121); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
			setState(123); initial();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 8) | (1L << 21) | (1L << 22))) != 0)) {
				{
				{
				setState(124); middle();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130); returnStat();
			}
		}
		catch (RecognitionException re) {
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
			setState(134);
			switch (_input.LA(1)) {
			case 8:
				enterOuterAlt(_localctx, 1);
				{
				setState(132); forStat();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 2);
				{
				setState(133); let();
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
			setState(139);
			switch (_input.LA(1)) {
			case 8:
			case 22:
				enterOuterAlt(_localctx, 1);
				{
				setState(136); initial();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				setState(137); where();
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 3);
				{
				setState(138); groupBy();
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
			setState(141); match(8);
			setState(142); forBinding();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(143); match(COMMA);
				setState(144); forBinding();
				}
				}
				setState(149);
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
			setState(150); match(VAR);
			setState(151); match(24);
			setState(152); pathExpr_xq();
			}
		}
		catch (RecognitionException re) {
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
			setState(154); match(22);
			setState(155); letBinding();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(156); match(COMMA);
				setState(157); letBinding();
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
			setState(163); match(VAR);
			setState(164); match(9);
			setState(170);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(165); pathExpr_xq();
				}
				break;

			case 2:
				{
				setState(166); flwrexpr();
				}
				break;

			case 3:
				{
				setState(167); aggrExpr();
				}
				break;

			case 4:
				{
				setState(168); arithmeticExpr_xq();
				}
				break;

			case 5:
				{
				setState(169); literal();
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
			setState(186);
			switch (_input.LA(1)) {
			case 16:
				enterOuterAlt(_localctx, 1);
				{
				setState(174); match(16);
				setState(175); match(10);
				setState(176); pathExprInner_xq();
				setState(178);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 13) | (1L << 14) | (1L << 20) | (1L << 23) | (1L << 25) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << OP_SUB) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(177); xpath();
					}
				}

				setState(180); match(26);
				}
				break;
			case 17:
			case 27:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(182); pathExprInner_xq();
				setState(184);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 13) | (1L << 14) | (1L << 20) | (1L << 23) | (1L << 25) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << OP_SUB) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
					{
					setState(183); xpath();
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
			setState(197);
			switch (_input.LA(1)) {
			case 17:
				_localctx = new PathExprInner_xq_collectionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(17);
				setState(189); match(10);
				setState(190); match(STRING_LITERAL);
				setState(191); match(26);
				}
				break;
			case 27:
				_localctx = new PathExprInner_xq_docContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(192); match(27);
				setState(193); match(10);
				setState(194); match(STRING_LITERAL);
				setState(195); match(26);
				}
				break;
			case VAR:
				_localctx = new PathExprInner_xq_VARContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(196); match(VAR);
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
			setState(199); match(AGGR_FUNCT);
			setState(200); match(10);
			setState(201); match(VAR);
			setState(202); match(26);
			}
		}
		catch (RecognitionException re) {
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
			setState(204); match(21);
			setState(205); orExpr_xq();
			}
		}
		catch (RecognitionException re) {
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
			setState(207); andExpr_xq();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(208); match(OR);
				setState(209); andExpr_xq();
				}
				}
				setState(214);
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
			setState(215); boolExpr_xq();
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(216); match(AND);
				setState(217); boolExpr_xq();
				}
				}
				setState(222);
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
			setState(229);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(223); match(NOT);
				setState(224); match(10);
				setState(225); boolExprInner_xq();
				setState(226); match(26);
				}
				break;
			case 19:
			case 28:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(228); boolExprInner_xq();
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
			setState(234);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(231); pred();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); contains();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 3);
				{
				setState(233); empty();
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
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(XQueryParser.STRING_LITERAL, 0); }
		public ArithmeticExpr_xqContext arithmeticExpr_xq(int i) {
			return getRuleContext(ArithmeticExpr_xqContext.class,i);
		}
		public VcmpContext vcmp() {
			return getRuleContext(VcmpContext.class,0);
		}
		public TerminalNode OP_SUB() { return getToken(XQueryParser.OP_SUB, 0); }
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
			setState(236); arithmeticExpr_xq();
			setState(249);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 6) | (1L << 11) | (1L << EQ) | (1L << EQ_S) | (1L << NE) | (1L << NE_S) | (1L << LT) | (1L << LT_S) | (1L << LE) | (1L << LE_S) | (1L << GT) | (1L << GT_S) | (1L << GE) | (1L << GE_S))) != 0)) {
				{
				setState(239);
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
					setState(237); vcmp();
					}
					break;
				case 5:
				case 6:
				case 11:
					{
					setState(238); ncmp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(247);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(241); arithmeticExpr_xq();
					}
					break;
				case STRING_LITERAL:
					{
					setState(242); match(STRING_LITERAL);
					}
					break;
				case OP_SUB:
				case INTEGER_LITERAL:
				case DECIMAL_LITERAL:
					{
					setState(244);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(243); match(OP_SUB);
						}
						break;
					}
					setState(246); numericLiteral();
					}
					break;
				default:
					throw new NoViableAltException(this);
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
			setState(251);
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
			setState(253);
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
			setState(255); match(19);
			setState(256); match(10);
			setState(257); match(VAR);
			setState(258); match(COMMA);
			setState(259); match(STRING_LITERAL);
			setState(260); match(26);
			}
		}
		catch (RecognitionException re) {
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
			setState(262); match(28);
			setState(263); match(10);
			setState(264); match(VAR);
			setState(265); match(26);
			}
		}
		catch (RecognitionException re) {
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
			setState(267); match(1);
			setState(268); match(VAR);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(269); match(COMMA);
				setState(270); match(VAR);
				}
				}
				setState(275);
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
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public EleConstContext eleConst() {
			return getRuleContext(EleConstContext.class,0);
		}
		public AggrExprContext aggrExpr() {
			return getRuleContext(AggrExprContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(4);
			setState(280);
			switch (_input.LA(1)) {
			case LT_S:
				{
				setState(277); eleConst();
				}
				break;
			case AGGR_FUNCT:
				{
				setState(278); aggrExpr();
				}
				break;
			case VAR:
				{
				setState(279); match(VAR);
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
			setState(282); match(LT_S);
			setState(283); eaName();
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 12) | (1L << 13) | (1L << 25) | (1L << NOT) | (1L << QNAME_TOKEN))) != 0)) {
				{
				{
				setState(284); att();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
			switch (_input.LA(1)) {
			case CLOSE_OPENING_TAG:
				{
				setState(290); match(CLOSE_OPENING_TAG);
				}
				break;
			case GT_S:
				{
				{
				setState(291); match(GT_S);
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LT_S || _la==LEFTCURL) {
					{
					setState(297);
					switch (_input.LA(1)) {
					case LT_S:
						{
						setState(292); eleConst();
						}
						break;
					case LEFTCURL:
						{
						setState(293); match(LEFTCURL);
						setState(294); eleConstInner();
						setState(295); match(RIGHTCURL);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(301);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(302); match(OPEN_CLOSING_TAG);
				{
				setState(303); eaName();
				}
				setState(304); match(GT_S);
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
			setState(310);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(308); match(VAR);
				}
				break;
			case AGGR_FUNCT:
				{
				setState(309); aggrExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(312); match(COMMA);
				setState(315);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(313); match(VAR);
					}
					break;
				case AGGR_FUNCT:
					{
					setState(314); aggrExpr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(321);
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
			setState(322); eaName();
			setState(323); match(EQ_S);
			setState(324); attInner();
			}
		}
		catch (RecognitionException re) {
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
			setState(335);
			switch (_input.LA(1)) {
			case OPEN_ATTR_VAR_DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(326); match(OPEN_ATTR_VAR_DOUBLE);
				setState(327); attInner2();
				setState(328); match(CLOSE_ATTR_VAR_DOUBLE);
				}
				break;
			case OPEN_ATTR_VAR_SINGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(330); match(OPEN_ATTR_VAR_SINGLE);
				setState(331); attInner2();
				setState(332); match(CLOSE_ATTR_VAR_SINGLE);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(334); match(STRING_LITERAL);
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
			setState(339);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(337); match(VAR);
				}
				break;
			case AGGR_FUNCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(338); aggrExpr();
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
			setState(343);
			switch (_input.LA(1)) {
			case 2:
			case 3:
			case 7:
			case 12:
			case 13:
			case 25:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(341); functionName();
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(342); match(QNAME_TOKEN);
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
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TerminalNode VAR() { return getToken(XQueryParser.VAR, 0); }
		public TerminalNode OP_ADD() { return getToken(XQueryParser.OP_ADD, 0); }
		public TerminalNode SLASH() { return getToken(XQueryParser.SLASH, 0); }
		public TerminalNode OP_SUB() { return getToken(XQueryParser.OP_SUB, 0); }
		public TerminalNode OP_MUL() { return getToken(XQueryParser.OP_MUL, 0); }
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
		ArithmeticExpr_xqContext _localctx = new ArithmeticExpr_xqContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_arithmeticExpr_xq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(VAR);
			setState(348);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SLASH) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) {
				{
				setState(346);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SLASH) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(347); numericLiteral();
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

	public static class XpathContext extends ParserRuleContext {
		public Expr_xpContext expr_xp() {
			return getRuleContext(Expr_xpContext.class,0);
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
			setState(350); expr_xp();
			}
		}
		catch (RecognitionException re) {
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitExpr_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_xpContext expr_xp() throws RecognitionException {
		Expr_xpContext _localctx = new Expr_xpContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr_xp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352); orExpr_xp();
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> OR() { return getTokens(XQueryParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XQueryParser.OR, i);
		}
		public OrExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr_xp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitOrExpr_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpr_xpContext orExpr_xp() throws RecognitionException {
		OrExpr_xpContext _localctx = new OrExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_orExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); andExpr_xp();
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(355); match(OR);
				setState(356); andExpr_xp();
				}
				}
				setState(361);
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
			return getToken(XQueryParser.AND, i);
		}
		public List<ComparativeExpr_xpContext> comparativeExpr_xp() {
			return getRuleContexts(ComparativeExpr_xpContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(XQueryParser.AND); }
		public AndExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr_xp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAndExpr_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpr_xpContext andExpr_xp() throws RecognitionException {
		AndExpr_xpContext _localctx = new AndExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_andExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362); comparativeExpr_xp();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(363); match(AND);
				setState(364); comparativeExpr_xp();
				}
				}
				setState(369);
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
		public List<TerminalNode> NE_S() { return getTokens(XQueryParser.NE_S); }
		public List<ArithmeticExpr_xpContext> arithmeticExpr_xp() {
			return getRuleContexts(ArithmeticExpr_xpContext.class);
		}
		public TerminalNode GT_S(int i) {
			return getToken(XQueryParser.GT_S, i);
		}
		public TerminalNode LE_S(int i) {
			return getToken(XQueryParser.LE_S, i);
		}
		public List<TerminalNode> GE_S() { return getTokens(XQueryParser.GE_S); }
		public TerminalNode NE_S(int i) {
			return getToken(XQueryParser.NE_S, i);
		}
		public ArithmeticExpr_xpContext arithmeticExpr_xp(int i) {
			return getRuleContext(ArithmeticExpr_xpContext.class,i);
		}
		public List<TerminalNode> LE_S() { return getTokens(XQueryParser.LE_S); }
		public TerminalNode LT_S(int i) {
			return getToken(XQueryParser.LT_S, i);
		}
		public TerminalNode EQ_S(int i) {
			return getToken(XQueryParser.EQ_S, i);
		}
		public List<TerminalNode> EQ_S() { return getTokens(XQueryParser.EQ_S); }
		public List<TerminalNode> GT_S() { return getTokens(XQueryParser.GT_S); }
		public TerminalNode GE_S(int i) {
			return getToken(XQueryParser.GE_S, i);
		}
		public List<TerminalNode> LT_S() { return getTokens(XQueryParser.LT_S); }
		public ComparativeExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparativeExpr_xp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitComparativeExpr_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparativeExpr_xpContext comparativeExpr_xp() throws RecognitionException {
		ComparativeExpr_xpContext _localctx = new ComparativeExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_comparativeExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370); arithmeticExpr_xp();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ_S) | (1L << NE_S) | (1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) {
				{
				{
				setState(371);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ_S) | (1L << NE_S) | (1L << LT_S) | (1L << LE_S) | (1L << GT_S) | (1L << GE_S))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(372); arithmeticExpr_xp();
				}
				}
				setState(377);
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

	public static class ArithmeticExpr_xpContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public TerminalNode OP_ADD(int i) {
			return getToken(XQueryParser.OP_ADD, i);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public List<TerminalNode> OP_ADD() { return getTokens(XQueryParser.OP_ADD); }
		public TerminalNode OP_SUB(int i) {
			return getToken(XQueryParser.OP_SUB, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(XQueryParser.OP_SUB); }
		public List<TerminalNode> OP_MUL() { return getTokens(XQueryParser.OP_MUL); }
		public TerminalNode OP_MUL(int i) {
			return getToken(XQueryParser.OP_MUL, i);
		}
		public ArithmeticExpr_xpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpr_xp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitArithmeticExpr_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpr_xpContext arithmeticExpr_xp() throws RecognitionException {
		ArithmeticExpr_xpContext _localctx = new ArithmeticExpr_xpContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_arithmeticExpr_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378); unaryExpr();
			setState(383);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 14) | (1L << 23) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) {
				{
				{
				setState(379);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 14) | (1L << 23) | (1L << OP_ADD) | (1L << OP_SUB) | (1L << OP_MUL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(380); unaryExpr();
				}
				}
				setState(385);
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
		public ValueExprContext valueExpr() {
			return getRuleContext(ValueExprContext.class,0);
		}
		public TerminalNode OP_SUB(int i) {
			return getToken(XQueryParser.OP_SUB, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(XQueryParser.OP_SUB); }
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
		enterRule(_localctx, 74, RULE_unaryExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(386); match(OP_SUB);
					}
					} 
				}
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			setState(392); valueExpr();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 76, RULE_valueExpr);
		try {
			setState(399);
			switch (_input.LA(1)) {
			case 2:
			case 3:
			case 7:
			case 10:
			case 12:
			case 13:
			case 25:
			case NOT:
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(394); filterExpr();
				setState(396);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(395); pathExpr();
					}
					break;
				}
				}
				break;
			case 14:
			case 20:
			case 23:
			case TEXTFUNCTION:
			case SLASH:
			case SLASHSLASH:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(398); pathExpr();
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
		enterRule(_localctx, 78, RULE_pathExpr);
		try {
			setState(406);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new PathExpr_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(401); match(SLASH);
				setState(402); relativePathExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new PathExpr_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(403); match(SLASHSLASH);
				setState(404); relativePathExpr();
				}
				break;
			case 14:
			case 20:
			case 23:
			case TEXTFUNCTION:
			case QNAME_TOKEN:
				_localctx = new PathExpr_relativePathExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(405); relativePathExpr();
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
		enterRule(_localctx, 80, RULE_relativePathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408); stepExpr();
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SLASH || _la==SLASHSLASH) {
				{
				{
				setState(409); relativePathExpr2();
				}
				}
				setState(414);
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
		enterRule(_localctx, 82, RULE_relativePathExpr2);
		try {
			setState(419);
			switch (_input.LA(1)) {
			case SLASH:
				_localctx = new RelativePathExpr2_slashContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(415); match(SLASH);
				setState(416); stepExpr();
				}
				break;
			case SLASHSLASH:
				_localctx = new RelativePathExpr2_slashslashContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(417); match(SLASHSLASH);
				setState(418); stepExpr();
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
		enterRule(_localctx, 84, RULE_stepExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421); axisStep();
			}
		}
		catch (RecognitionException re) {
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAxisStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AxisStepContext axisStep() throws RecognitionException {
		AxisStepContext _localctx = new AxisStepContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_axisStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423); forwardStep();
			setState(424); predicateList_xp();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 88, RULE_forwardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426); abbrevForwardStep();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 90, RULE_abbrevForwardStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_la = _input.LA(1);
			if (_la==20) {
				{
				setState(428); match(20);
				}
			}

			setState(431); nodeTest();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 92, RULE_nodeTest);
		try {
			setState(435);
			switch (_input.LA(1)) {
			case TEXTFUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(433); kindTest();
				}
				break;
			case 14:
			case 23:
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(434); nameTest();
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
		enterRule(_localctx, 94, RULE_kindTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437); textTest();
			}
		}
		catch (RecognitionException re) {
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitNameTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_nameTest);
		try {
			setState(442);
			switch (_input.LA(1)) {
			case 23:
				enterOuterAlt(_localctx, 1);
				{
				setState(439); match(23);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 2);
				{
				setState(440); match(14);
				}
				break;
			case QNAME_TOKEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(441); qName();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_filterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444); primaryExpr();
			setState(445); predicateList_xp();
			}
		}
		catch (RecognitionException re) {
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPredicateList_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateList_xpContext predicateList_xp() throws RecognitionException {
		PredicateList_xpContext _localctx = new PredicateList_xpContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_predicateList_xp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==15) {
				{
				{
				setState(447); predicate_xp();
				}
				}
				setState(452);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitPredicate_xp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_xpContext predicate_xp() throws RecognitionException {
		Predicate_xpContext _localctx = new Predicate_xpContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_predicate_xp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453); match(15);
			setState(454); expr_xp();
			setState(455); match(18);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 104, RULE_primaryExpr);
		try {
			setState(460);
			switch (_input.LA(1)) {
			case OP_SUB:
			case STRING_LITERAL:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(457); literal();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 2);
				{
				setState(458); parenthesizedExpr();
				}
				break;
			case 2:
			case 3:
			case 7:
			case 12:
			case 13:
			case 25:
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(459); functionCall();
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
		enterRule(_localctx, 106, RULE_literal);
		try {
			setState(464);
			switch (_input.LA(1)) {
			case OP_SUB:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(462); numericLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(463); match(STRING_LITERAL);
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
		public TerminalNode OP_SUB() { return getToken(XQueryParser.OP_SUB, 0); }
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
		enterRule(_localctx, 108, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			_la = _input.LA(1);
			if (_la==OP_SUB) {
				{
				setState(466); match(OP_SUB);
				}
			}

			setState(469);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitParenthesizedExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExprContext parenthesizedExpr() throws RecognitionException {
		ParenthesizedExprContext _localctx = new ParenthesizedExprContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_parenthesizedExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471); match(10);
			setState(472); expr_xp();
			setState(473); match(26);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> COMMA() { return getTokens(XQueryParser.COMMA); }
		public Expr_xpContext expr_xp(int i) {
			return getRuleContext(Expr_xpContext.class,i);
		}
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
		enterRule(_localctx, 112, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475); functionName();
			setState(476); match(10);
			setState(485);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 13) | (1L << 14) | (1L << 20) | (1L << 23) | (1L << 25) | (1L << TEXTFUNCTION) | (1L << SLASH) | (1L << SLASHSLASH) | (1L << NOT) | (1L << OP_SUB) | (1L << QNAME_TOKEN))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITERAL - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (DECIMAL_LITERAL - 64)))) != 0)) {
				{
				setState(477); expr_xp();
				setState(482);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(478); match(COMMA);
					setState(479); expr_xp();
					}
					}
					setState(484);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(487); match(26);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 114, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 7) | (1L << 12) | (1L << 13) | (1L << 25) | (1L << NOT))) != 0)) ) {
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
		enterRule(_localctx, 116, RULE_textTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491); match(TEXTFUNCTION);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 118, RULE_qName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493); match(QNAME_TOKEN);
			}
		}
		catch (RecognitionException re) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3I\u01f2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\2\3\3\3\3\7\3\u0080\n\3\f\3\16\3\u0083\13\3\3\3\3\3\3\4\3\4"+
		"\5\4\u0089\n\4\3\5\3\5\3\5\5\5\u008e\n\5\3\6\3\6\3\6\3\6\7\6\u0094\n\6"+
		"\f\6\16\6\u0097\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u00a1\n\b\f\b"+
		"\16\b\u00a4\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ad\n\t\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\5\13\u00b5\n\13\3\13\3\13\3\13\3\13\5\13\u00bb\n\13\5"+
		"\13\u00bd\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c8\n\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\7\17\u00d5\n\17\f\17\16\17"+
		"\u00d8\13\17\3\20\3\20\3\20\7\20\u00dd\n\20\f\20\16\20\u00e0\13\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u00e8\n\21\3\22\3\22\3\22\5\22\u00ed\n"+
		"\22\3\23\3\23\3\23\5\23\u00f2\n\23\3\23\3\23\3\23\5\23\u00f7\n\23\3\23"+
		"\5\23\u00fa\n\23\5\23\u00fc\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u0112"+
		"\n\30\f\30\16\30\u0115\13\30\3\31\3\31\3\31\3\31\5\31\u011b\n\31\3\32"+
		"\3\32\3\32\7\32\u0120\n\32\f\32\16\32\u0123\13\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\7\32\u012c\n\32\f\32\16\32\u012f\13\32\3\32\3\32\3\32"+
		"\3\32\5\32\u0135\n\32\3\33\3\33\5\33\u0139\n\33\3\33\3\33\3\33\5\33\u013e"+
		"\n\33\7\33\u0140\n\33\f\33\16\33\u0143\13\33\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0152\n\35\3\36\3\36\5\36"+
		"\u0156\n\36\3\37\3\37\5\37\u015a\n\37\3 \3 \3 \5 \u015f\n \3!\3!\3\"\3"+
		"\"\3#\3#\3#\7#\u0168\n#\f#\16#\u016b\13#\3$\3$\3$\7$\u0170\n$\f$\16$\u0173"+
		"\13$\3%\3%\3%\7%\u0178\n%\f%\16%\u017b\13%\3&\3&\3&\7&\u0180\n&\f&\16"+
		"&\u0183\13&\3\'\7\'\u0186\n\'\f\'\16\'\u0189\13\'\3\'\3\'\3(\3(\5(\u018f"+
		"\n(\3(\5(\u0192\n(\3)\3)\3)\3)\3)\5)\u0199\n)\3*\3*\7*\u019d\n*\f*\16"+
		"*\u01a0\13*\3+\3+\3+\3+\5+\u01a6\n+\3,\3,\3-\3-\3-\3.\3.\3/\5/\u01b0\n"+
		"/\3/\3/\3\60\3\60\5\60\u01b6\n\60\3\61\3\61\3\62\3\62\3\62\5\62\u01bd"+
		"\n\62\3\63\3\63\3\63\3\64\7\64\u01c3\n\64\f\64\16\64\u01c6\13\64\3\65"+
		"\3\65\3\65\3\65\3\66\3\66\3\66\5\66\u01cf\n\66\3\67\3\67\5\67\u01d3\n"+
		"\67\38\58\u01d6\n8\38\38\39\39\39\39\3:\3:\3:\3:\3:\7:\u01e3\n:\f:\16"+
		":\u01e6\13:\5:\u01e8\n:\3:\3:\3;\3;\3<\3<\3=\3=\3=\2\2>\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bd"+
		"fhjlnprtvx\2\t\3\2&\61\4\2\7\b\r\r\4\2!!\62\64\b\2\'\'))++--//\61\61\5"+
		"\2\20\20\31\31\62\64\3\2FG\7\2\4\5\t\t\16\17\33\33%%\u01f3\2z\3\2\2\2"+
		"\4}\3\2\2\2\6\u0088\3\2\2\2\b\u008d\3\2\2\2\n\u008f\3\2\2\2\f\u0098\3"+
		"\2\2\2\16\u009c\3\2\2\2\20\u00a5\3\2\2\2\22\u00ae\3\2\2\2\24\u00bc\3\2"+
		"\2\2\26\u00c7\3\2\2\2\30\u00c9\3\2\2\2\32\u00ce\3\2\2\2\34\u00d1\3\2\2"+
		"\2\36\u00d9\3\2\2\2 \u00e7\3\2\2\2\"\u00ec\3\2\2\2$\u00ee\3\2\2\2&\u00fd"+
		"\3\2\2\2(\u00ff\3\2\2\2*\u0101\3\2\2\2,\u0108\3\2\2\2.\u010d\3\2\2\2\60"+
		"\u0116\3\2\2\2\62\u011c\3\2\2\2\64\u0138\3\2\2\2\66\u0144\3\2\2\28\u0151"+
		"\3\2\2\2:\u0155\3\2\2\2<\u0159\3\2\2\2>\u015b\3\2\2\2@\u0160\3\2\2\2B"+
		"\u0162\3\2\2\2D\u0164\3\2\2\2F\u016c\3\2\2\2H\u0174\3\2\2\2J\u017c\3\2"+
		"\2\2L\u0187\3\2\2\2N\u0191\3\2\2\2P\u0198\3\2\2\2R\u019a\3\2\2\2T\u01a5"+
		"\3\2\2\2V\u01a7\3\2\2\2X\u01a9\3\2\2\2Z\u01ac\3\2\2\2\\\u01af\3\2\2\2"+
		"^\u01b5\3\2\2\2`\u01b7\3\2\2\2b\u01bc\3\2\2\2d\u01be\3\2\2\2f\u01c4\3"+
		"\2\2\2h\u01c7\3\2\2\2j\u01ce\3\2\2\2l\u01d2\3\2\2\2n\u01d5\3\2\2\2p\u01d9"+
		"\3\2\2\2r\u01dd\3\2\2\2t\u01eb\3\2\2\2v\u01ed\3\2\2\2x\u01ef\3\2\2\2z"+
		"{\5\4\3\2{|\7\2\2\3|\3\3\2\2\2}\u0081\5\6\4\2~\u0080\5\b\5\2\177~\3\2"+
		"\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\5\60\31\2\u0085\5\3\2\2\2\u0086"+
		"\u0089\5\n\6\2\u0087\u0089\5\16\b\2\u0088\u0086\3\2\2\2\u0088\u0087\3"+
		"\2\2\2\u0089\7\3\2\2\2\u008a\u008e\5\6\4\2\u008b\u008e\5\32\16\2\u008c"+
		"\u008e\5.\30\2\u008d\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2"+
		"\2\2\u008e\t\3\2\2\2\u008f\u0090\7\n\2\2\u0090\u0095\5\f\7\2\u0091\u0092"+
		"\7A\2\2\u0092\u0094\5\f\7\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\13\3\2\2\2\u0097\u0095\3\2\2"+
		"\2\u0098\u0099\7\65\2\2\u0099\u009a\7\32\2\2\u009a\u009b\5\24\13\2\u009b"+
		"\r\3\2\2\2\u009c\u009d\7\30\2\2\u009d\u00a2\5\20\t\2\u009e\u009f\7A\2"+
		"\2\u009f\u00a1\5\20\t\2\u00a0\u009e\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\17\3\2\2\2\u00a4\u00a2\3\2\2"+
		"\2\u00a5\u00a6\7\65\2\2\u00a6\u00ac\7\13\2\2\u00a7\u00ad\5\24\13\2\u00a8"+
		"\u00ad\5\4\3\2\u00a9\u00ad\5\30\r\2\u00aa\u00ad\5> \2\u00ab\u00ad\5l\67"+
		"\2\u00ac\u00a7\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\21\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\23\3\2\2\2\u00b0\u00b1\7\22\2\2\u00b1\u00b2\7\f\2\2\u00b2\u00b4\5\26"+
		"\f\2\u00b3\u00b5\5@!\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6"+
		"\3\2\2\2\u00b6\u00b7\7\34\2\2\u00b7\u00bd\3\2\2\2\u00b8\u00ba\5\26\f\2"+
		"\u00b9\u00bb\5@!\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd"+
		"\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\25\3\2\2\2\u00be"+
		"\u00bf\7\23\2\2\u00bf\u00c0\7\f\2\2\u00c0\u00c1\7B\2\2\u00c1\u00c8\7\34"+
		"\2\2\u00c2\u00c3\7\35\2\2\u00c3\u00c4\7\f\2\2\u00c4\u00c5\7B\2\2\u00c5"+
		"\u00c8\7\34\2\2\u00c6\u00c8\7\65\2\2\u00c7\u00be\3\2\2\2\u00c7\u00c2\3"+
		"\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\27\3\2\2\2\u00c9\u00ca\7\37\2\2\u00ca"+
		"\u00cb\7\f\2\2\u00cb\u00cc\7\65\2\2\u00cc\u00cd\7\34\2\2\u00cd\31\3\2"+
		"\2\2\u00ce\u00cf\7\27\2\2\u00cf\u00d0\5\34\17\2\u00d0\33\3\2\2\2\u00d1"+
		"\u00d6\5\36\20\2\u00d2\u00d3\7#\2\2\u00d3\u00d5\5\36\20\2\u00d4\u00d2"+
		"\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\35\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00de\5 \21\2\u00da\u00db\7$\2\2"+
		"\u00db\u00dd\5 \21\2\u00dc\u00da\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc"+
		"\3\2\2\2\u00de\u00df\3\2\2\2\u00df\37\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1"+
		"\u00e2\7%\2\2\u00e2\u00e3\7\f\2\2\u00e3\u00e4\5\"\22\2\u00e4\u00e5\7\34"+
		"\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e8\5\"\22\2\u00e7\u00e1\3\2\2\2\u00e7"+
		"\u00e6\3\2\2\2\u00e8!\3\2\2\2\u00e9\u00ed\5$\23\2\u00ea\u00ed\5*\26\2"+
		"\u00eb\u00ed\5,\27\2\u00ec\u00e9\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb"+
		"\3\2\2\2\u00ed#\3\2\2\2\u00ee\u00fb\5> \2\u00ef\u00f2\5&\24\2\u00f0\u00f2"+
		"\5(\25\2\u00f1\u00ef\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f9\3\2\2\2\u00f3"+
		"\u00fa\5> \2\u00f4\u00fa\7B\2\2\u00f5\u00f7\7\63\2\2\u00f6\u00f5\3\2\2"+
		"\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\5n8\2\u00f9\u00f3"+
		"\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f6\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb"+
		"\u00f1\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc%\3\2\2\2\u00fd\u00fe\t\2\2\2"+
		"\u00fe\'\3\2\2\2\u00ff\u0100\t\3\2\2\u0100)\3\2\2\2\u0101\u0102\7\25\2"+
		"\2\u0102\u0103\7\f\2\2\u0103\u0104\7\65\2\2\u0104\u0105\7A\2\2\u0105\u0106"+
		"\7B\2\2\u0106\u0107\7\34\2\2\u0107+\3\2\2\2\u0108\u0109\7\36\2\2\u0109"+
		"\u010a\7\f\2\2\u010a\u010b\7\65\2\2\u010b\u010c\7\34\2\2\u010c-\3\2\2"+
		"\2\u010d\u010e\7\3\2\2\u010e\u0113\7\65\2\2\u010f\u0110\7A\2\2\u0110\u0112"+
		"\7\65\2\2\u0111\u010f\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2"+
		"\u0113\u0114\3\2\2\2\u0114/\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u011a\7"+
		"\6\2\2\u0117\u011b\5\62\32\2\u0118\u011b\5\30\r\2\u0119\u011b\7\65\2\2"+
		"\u011a\u0117\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b\61"+
		"\3\2\2\2\u011c\u011d\7+\2\2\u011d\u0121\5<\37\2\u011e\u0120\5\66\34\2"+
		"\u011f\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122\u0134\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0135\7=\2\2\u0125"+
		"\u012d\7/\2\2\u0126\u012c\5\62\32\2\u0127\u0128\7\66\2\2\u0128\u0129\5"+
		"\64\33\2\u0129\u012a\7\67\2\2\u012a\u012c\3\2\2\2\u012b\u0126\3\2\2\2"+
		"\u012b\u0127\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e"+
		"\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7<\2\2\u0131"+
		"\u0132\5<\37\2\u0132\u0133\7/\2\2\u0133\u0135\3\2\2\2\u0134\u0124\3\2"+
		"\2\2\u0134\u0125\3\2\2\2\u0135\63\3\2\2\2\u0136\u0139\7\65\2\2\u0137\u0139"+
		"\5\30\r\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u0139\u0141\3\2\2\2"+
		"\u013a\u013d\7A\2\2\u013b\u013e\7\65\2\2\u013c\u013e\5\30\r\2\u013d\u013b"+
		"\3\2\2\2\u013d\u013c\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u013a\3\2\2\2\u0140"+
		"\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\65\3\2\2"+
		"\2\u0143\u0141\3\2\2\2\u0144\u0145\5<\37\2\u0145\u0146\7\'\2\2\u0146\u0147"+
		"\58\35\2\u0147\67\3\2\2\2\u0148\u0149\78\2\2\u0149\u014a\5:\36\2\u014a"+
		"\u014b\7:\2\2\u014b\u0152\3\2\2\2\u014c\u014d\79\2\2\u014d\u014e\5:\36"+
		"\2\u014e\u014f\7;\2\2\u014f\u0152\3\2\2\2\u0150\u0152\7B\2\2\u0151\u0148"+
		"\3\2\2\2\u0151\u014c\3\2\2\2\u0151\u0150\3\2\2\2\u01529\3\2\2\2\u0153"+
		"\u0156\7\65\2\2\u0154\u0156\5\30\r\2\u0155\u0153\3\2\2\2\u0155\u0154\3"+
		"\2\2\2\u0156;\3\2\2\2\u0157\u015a\5t;\2\u0158\u015a\7>\2\2\u0159\u0157"+
		"\3\2\2\2\u0159\u0158\3\2\2\2\u015a=\3\2\2\2\u015b\u015e\7\65\2\2\u015c"+
		"\u015d\t\4\2\2\u015d\u015f\5n8\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2"+
		"\2\u015f?\3\2\2\2\u0160\u0161\5B\"\2\u0161A\3\2\2\2\u0162\u0163\5D#\2"+
		"\u0163C\3\2\2\2\u0164\u0169\5F$\2\u0165\u0166\7#\2\2\u0166\u0168\5F$\2"+
		"\u0167\u0165\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a"+
		"\3\2\2\2\u016aE\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u0171\5H%\2\u016d\u016e"+
		"\7$\2\2\u016e\u0170\5H%\2\u016f\u016d\3\2\2\2\u0170\u0173\3\2\2\2\u0171"+
		"\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172G\3\2\2\2\u0173\u0171\3\2\2\2"+
		"\u0174\u0179\5J&\2\u0175\u0176\t\5\2\2\u0176\u0178\5J&\2\u0177\u0175\3"+
		"\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"I\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u0181\5L\'\2\u017d\u017e\t\6\2\2\u017e"+
		"\u0180\5L\'\2\u017f\u017d\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2"+
		"\2\2\u0181\u0182\3\2\2\2\u0182K\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0186"+
		"\7\63\2\2\u0185\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0185\3\2\2\2"+
		"\u0187\u0188\3\2\2\2\u0188\u018a\3\2\2\2\u0189\u0187\3\2\2\2\u018a\u018b"+
		"\5N(\2\u018bM\3\2\2\2\u018c\u018e\5d\63\2\u018d\u018f\5P)\2\u018e\u018d"+
		"\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u0192\5P)\2\u0191"+
		"\u018c\3\2\2\2\u0191\u0190\3\2\2\2\u0192O\3\2\2\2\u0193\u0194\7!\2\2\u0194"+
		"\u0199\5R*\2\u0195\u0196\7\"\2\2\u0196\u0199\5R*\2\u0197\u0199\5R*\2\u0198"+
		"\u0193\3\2\2\2\u0198\u0195\3\2\2\2\u0198\u0197\3\2\2\2\u0199Q\3\2\2\2"+
		"\u019a\u019e\5V,\2\u019b\u019d\5T+\2\u019c\u019b\3\2\2\2\u019d\u01a0\3"+
		"\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2\2\2\u019fS\3\2\2\2\u01a0\u019e"+
		"\3\2\2\2\u01a1\u01a2\7!\2\2\u01a2\u01a6\5V,\2\u01a3\u01a4\7\"\2\2\u01a4"+
		"\u01a6\5V,\2\u01a5\u01a1\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6U\3\2\2\2\u01a7"+
		"\u01a8\5X-\2\u01a8W\3\2\2\2\u01a9\u01aa\5Z.\2\u01aa\u01ab\5f\64\2\u01ab"+
		"Y\3\2\2\2\u01ac\u01ad\5\\/\2\u01ad[\3\2\2\2\u01ae\u01b0\7\26\2\2\u01af"+
		"\u01ae\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b2\5^"+
		"\60\2\u01b2]\3\2\2\2\u01b3\u01b6\5`\61\2\u01b4\u01b6\5b\62\2\u01b5\u01b3"+
		"\3\2\2\2\u01b5\u01b4\3\2\2\2\u01b6_\3\2\2\2\u01b7\u01b8\5v<\2\u01b8a\3"+
		"\2\2\2\u01b9\u01bd\7\31\2\2\u01ba\u01bd\7\20\2\2\u01bb\u01bd\5x=\2\u01bc"+
		"\u01b9\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bb\3\2\2\2\u01bdc\3\2\2\2"+
		"\u01be\u01bf\5j\66\2\u01bf\u01c0\5f\64\2\u01c0e\3\2\2\2\u01c1\u01c3\5"+
		"h\65\2\u01c2\u01c1\3\2\2\2\u01c3\u01c6\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c4"+
		"\u01c5\3\2\2\2\u01c5g\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c7\u01c8\7\21\2\2"+
		"\u01c8\u01c9\5B\"\2\u01c9\u01ca\7\24\2\2\u01cai\3\2\2\2\u01cb\u01cf\5"+
		"l\67\2\u01cc\u01cf\5p9\2\u01cd\u01cf\5r:\2\u01ce\u01cb\3\2\2\2\u01ce\u01cc"+
		"\3\2\2\2\u01ce\u01cd\3\2\2\2\u01cfk\3\2\2\2\u01d0\u01d3\5n8\2\u01d1\u01d3"+
		"\7B\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d1\3\2\2\2\u01d3m\3\2\2\2\u01d4\u01d6"+
		"\7\63\2\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d7\3\2\2\2"+
		"\u01d7\u01d8\t\7\2\2\u01d8o\3\2\2\2\u01d9\u01da\7\f\2\2\u01da\u01db\5"+
		"B\"\2\u01db\u01dc\7\34\2\2\u01dcq\3\2\2\2\u01dd\u01de\5t;\2\u01de\u01e7"+
		"\7\f\2\2\u01df\u01e4\5B\"\2\u01e0\u01e1\7A\2\2\u01e1\u01e3\5B\"\2\u01e2"+
		"\u01e0\3\2\2\2\u01e3\u01e6\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e4\u01e5\3\2"+
		"\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e7\u01df\3\2\2\2\u01e7"+
		"\u01e8\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01ea\7\34\2\2\u01eas\3\2\2\2"+
		"\u01eb\u01ec\t\b\2\2\u01ecu\3\2\2\2\u01ed\u01ee\7 \2\2\u01eew\3\2\2\2"+
		"\u01ef\u01f0\7>\2\2\u01f0y\3\2\2\2\64\u0081\u0088\u008d\u0095\u00a2\u00ac"+
		"\u00b4\u00ba\u00bc\u00c7\u00d6\u00de\u00e7\u00ec\u00f1\u00f6\u00f9\u00fb"+
		"\u0113\u011a\u0121\u012b\u012d\u0134\u0138\u013d\u0141\u0151\u0155\u0159"+
		"\u015e\u0169\u0171\u0179\u0181\u0187\u018e\u0191\u0198\u019e\u01a5\u01af"+
		"\u01b5\u01bc\u01c4\u01ce\u01d2\u01d5\u01e4\u01e7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}