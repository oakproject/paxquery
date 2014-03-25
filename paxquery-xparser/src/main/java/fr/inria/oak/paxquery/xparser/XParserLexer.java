// Generated from XParser.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XParserLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__29=1, T__28=2, T__27=3, T__26=4, T__25=5, T__24=6, T__23=7, T__22=8, 
		T__21=9, T__20=10, T__19=11, T__18=12, T__17=13, T__16=14, T__15=15, T__14=16, 
		T__13=17, T__12=18, T__11=19, T__10=20, T__9=21, T__8=22, T__7=23, T__6=24, 
		T__5=25, T__4=26, T__3=27, T__2=28, T__1=29, T__0=30, QNAME_TOKEN=31, 
		STRING_LITERAL=32, INTEGER_LITERAL=33, DECIMAL_LITERAL=34, DIGITS=35, 
		NCNAME_TOK=36, LOCAL_PART=37, NMSTART=38, NMCHAR=39, LETTER=40, BASE_CHAR=41, 
		IDEOGRAPHIC=42, COMBINING_CHAR=43, DIGIT=44, EXTENDER=45, WS=46;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'/'", "'substring'", "'true'", "'!='", "'concat'", "'='", "'<='", "'('", 
		"'*'", "','", "'false'", "'ceiling'", "'mod'", "'['", "'>='", "'//'", 
		"'|'", "'<'", "']'", "'>'", "'@'", "'or'", "'text'", "'div'", "'floor'", 
		"')'", "'and'", "'+'", "'not'", "'-'", "QNAME_TOKEN", "STRING_LITERAL", 
		"INTEGER_LITERAL", "DECIMAL_LITERAL", "DIGITS", "NCNAME_TOK", "LOCAL_PART", 
		"NMSTART", "NMCHAR", "LETTER", "BASE_CHAR", "IDEOGRAPHIC", "COMBINING_CHAR", 
		"DIGIT", "EXTENDER", "WS"
	};
	public static final String[] ruleNames = {
		"T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", 
		"T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", 
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "QNAME_TOKEN", "STRING_LITERAL", 
		"INTEGER_LITERAL", "DECIMAL_LITERAL", "DIGITS", "NCNAME_TOK", "LOCAL_PART", 
		"NMSTART", "NMCHAR", "LETTER", "BASE_CHAR", "IDEOGRAPHIC", "COMBINING_CHAR", 
		"DIGIT", "EXTENDER", "WS"
	};


	public XParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\60\u0120\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3 \3 \3 \5 \u00cd\n \3 \3 \3!\3!\3!\7!\u00d4\n!\f!\16"+
		"!\u00d7\13!\3!\3!\3!\3!\7!\u00dd\n!\f!\16!\u00e0\13!\3!\5!\u00e3\n!\3"+
		"\"\3\"\3#\3#\3#\3#\3#\7#\u00ec\n#\f#\16#\u00ef\13#\5#\u00f1\n#\3$\6$\u00f4"+
		"\n$\r$\16$\u00f5\3%\3%\7%\u00fa\n%\f%\16%\u00fd\13%\3&\3&\3\'\3\'\5\'"+
		"\u0103\n\'\3(\3(\3(\3(\3(\5(\u010a\n(\3)\3)\5)\u010e\n)\3*\3*\3+\3+\3"+
		",\3,\3-\3-\3.\3.\3/\6/\u011b\n/\r/\16/\u011c\3/\3/\2\2\60\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60\3\2\f\4\2$$))\4\2\"\"))\3\2\62;\4\2/\60aa\u00cb"+
		"\2C\\c|\u00c2\u00d8\u00da\u00f8\u00fa\u0133\u0136\u0140\u0143\u014a\u014c"+
		"\u0180\u0182\u01c5\u01cf\u01f2\u01f6\u01f7\u01fc\u0219\u0252\u02aa\u02bd"+
		"\u02c3\u0388\u0388\u038a\u038c\u038e\u038e\u0390\u03a3\u03a5\u03d0\u03d2"+
		"\u03d8\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03e2\u03e4\u03f5\u0403"+
		"\u040e\u0410\u0451\u0453\u045e\u0460\u0483\u0492\u04c6\u04c9\u04ca\u04cd"+
		"\u04ce\u04d2\u04ed\u04f0\u04f7\u04fa\u04fb\u0533\u0558\u055b\u055b\u0563"+
		"\u0588\u05d2\u05ec\u05f2\u05f4\u0623\u063c\u0643\u064c\u0673\u06b9\u06bc"+
		"\u06c0\u06c2\u06d0\u06d2\u06d5\u06d7\u06d7\u06e7\u06e8\u0907\u093b\u093f"+
		"\u093f\u095a\u0963\u0987\u098e\u0991\u0992\u0995\u09aa\u09ac\u09b2\u09b4"+
		"\u09b4\u09b8\u09bb\u09de\u09df\u09e1\u09e3\u09f2\u09f3\u0a07\u0a0c\u0a11"+
		"\u0a12\u0a15\u0a2a\u0a2c\u0a32\u0a34\u0a35\u0a37\u0a38\u0a3a\u0a3b\u0a5b"+
		"\u0a5e\u0a60\u0a60\u0a74\u0a76\u0a87\u0a8d\u0a8f\u0a8f\u0a91\u0a93\u0a95"+
		"\u0aaa\u0aac\u0ab2\u0ab4\u0ab5\u0ab7\u0abb\u0abf\u0abf\u0ae2\u0ae2\u0b07"+
		"\u0b0e\u0b11\u0b12\u0b15\u0b2a\u0b2c\u0b32\u0b34\u0b35\u0b38\u0b3b\u0b3f"+
		"\u0b3f\u0b5e\u0b5f\u0b61\u0b63\u0b87\u0b8c\u0b90\u0b92\u0b94\u0b97\u0b9b"+
		"\u0b9c\u0b9e\u0b9e\u0ba0\u0ba1\u0ba5\u0ba6\u0baa\u0bac\u0bb0\u0bb7\u0bb9"+
		"\u0bbb\u0c07\u0c0e\u0c10\u0c12\u0c14\u0c2a\u0c2c\u0c35\u0c37\u0c3b\u0c62"+
		"\u0c63\u0c87\u0c8e\u0c90\u0c92\u0c94\u0caa\u0cac\u0cb5\u0cb7\u0cbb\u0ce0"+
		"\u0ce0\u0ce2\u0ce3\u0d07\u0d0e\u0d10\u0d12\u0d14\u0d2a\u0d2c\u0d3b\u0d62"+
		"\u0d63\u0e03\u0e30\u0e32\u0e32\u0e34\u0e35\u0e42\u0e47\u0e83\u0e84\u0e86"+
		"\u0e86\u0e89\u0e8a\u0e8c\u0e8c\u0e8f\u0e8f\u0e96\u0e99\u0e9b\u0ea1\u0ea3"+
		"\u0ea5\u0ea7\u0ea7\u0ea9\u0ea9\u0eac\u0ead\u0eaf\u0eb0\u0eb2\u0eb2\u0eb4"+
		"\u0eb5\u0ebf\u0ebf\u0ec2\u0ec6\u0f42\u0f49\u0f4b\u0f6b\u10a2\u10c7\u10d2"+
		"\u10f8\u1102\u1102\u1104\u1105\u1107\u1109\u110b\u110b\u110d\u110e\u1110"+
		"\u1114\u113e\u113e\u1140\u1140\u1142\u1142\u114e\u114e\u1150\u1150\u1152"+
		"\u1152\u1156\u1157\u115b\u115b\u1161\u1163\u1165\u1165\u1167\u1167\u1169"+
		"\u1169\u116b\u116b\u116f\u1170\u1174\u1175\u1177\u1177\u11a0\u11a0\u11aa"+
		"\u11aa\u11ad\u11ad\u11b0\u11b1\u11b9\u11ba\u11bc\u11bc\u11be\u11c4\u11ed"+
		"\u11ed\u11f2\u11f2\u11fb\u11fb\u1e02\u1e9d\u1ea2\u1efb\u1f02\u1f17\u1f1a"+
		"\u1f1f\u1f22\u1f47\u1f4a\u1f4f\u1f52\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f"+
		"\u1f5f\u1f61\u1f7f\u1f82\u1fb6\u1fb8\u1fbe\u1fc0\u1fc0\u1fc4\u1fc6\u1fc8"+
		"\u1fce\u1fd2\u1fd5\u1fd8\u1fdd\u1fe2\u1fee\u1ff4\u1ff6\u1ff8\u1ffe\u2128"+
		"\u2128\u212c\u212d\u2130\u2130\u2182\u2184\u3043\u3096\u30a3\u30fc\u3107"+
		"\u312e\uac02\ud7a5\5\2\u3009\u3009\u3023\u302b\u4e02\u9fa7X\2\u0302\u0347"+
		"\u0362\u0363\u0485\u0488\u0593\u05a3\u05a5\u05bb\u05bd\u05bf\u05c1\u05c1"+
		"\u05c3\u05c4\u05c6\u05c6\u064d\u0654\u0672\u0672\u06d8\u06e6\u06e9\u06ea"+
		"\u06ec\u06ef\u0903\u0905\u093e\u093e\u0940\u094f\u0953\u0956\u0964\u0965"+
		"\u0983\u0985\u09be\u09be\u09c0\u09c6\u09c9\u09ca\u09cd\u09cf\u09d9\u09d9"+
		"\u09e4\u09e5\u0a04\u0a04\u0a3e\u0a3e\u0a40\u0a44\u0a49\u0a4a\u0a4d\u0a4f"+
		"\u0a72\u0a73\u0a83\u0a85\u0abe\u0abe\u0ac0\u0ac7\u0ac9\u0acb\u0acd\u0acf"+
		"\u0b03\u0b05\u0b3e\u0b3e\u0b40\u0b45\u0b49\u0b4a\u0b4d\u0b4f\u0b58\u0b59"+
		"\u0b84\u0b85\u0bc0\u0bc4\u0bc8\u0bca\u0bcc\u0bcf\u0bd9\u0bd9\u0c03\u0c05"+
		"\u0c40\u0c46\u0c48\u0c4a\u0c4c\u0c4f\u0c57\u0c58\u0c84\u0c85\u0cc0\u0cc6"+
		"\u0cc8\u0cca\u0ccc\u0ccf\u0cd7\u0cd8\u0d04\u0d05\u0d40\u0d45\u0d48\u0d4a"+
		"\u0d4c\u0d4f\u0d59\u0d59\u0e33\u0e33\u0e36\u0e3c\u0e49\u0e50\u0eb3\u0eb3"+
		"\u0eb6\u0ebb\u0ebd\u0ebe\u0eca\u0ecf\u0f1a\u0f1b\u0f37\u0f37\u0f39\u0f39"+
		"\u0f3b\u0f3b\u0f40\u0f41\u0f73\u0f86\u0f88\u0f8d\u0f92\u0f97\u0f99\u0f99"+
		"\u0f9b\u0faf\u0fb3\u0fb9\u0fbb\u0fbb\u20d2\u20de\u20e3\u20e3\u302c\u3031"+
		"\u309b\u309c\21\2\62;\u0662\u066b\u06f2\u06fb\u0968\u0971\u09e8\u09f1"+
		"\u0a68\u0a71\u0ae8\u0af1\u0b68\u0b71\u0be9\u0bf1\u0c68\u0c71\u0ce8\u0cf1"+
		"\u0d68\u0d71\u0e52\u0e5b\u0ed2\u0edb\u0f22\u0f2b\f\2\u00b9\u00b9\u02d2"+
		"\u02d3\u0389\u0389\u0642\u0642\u0e48\u0e48\u0ec8\u0ec8\u3007\u3007\u3033"+
		"\u3037\u309f\u30a0\u30fe\u3100\5\2\13\f\17\17\"\"\u0130\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5a\3\2\2\2\7k\3\2\2"+
		"\2\tp\3\2\2\2\13s\3\2\2\2\rz\3\2\2\2\17|\3\2\2\2\21\177\3\2\2\2\23\u0081"+
		"\3\2\2\2\25\u0083\3\2\2\2\27\u0085\3\2\2\2\31\u008b\3\2\2\2\33\u0093\3"+
		"\2\2\2\35\u0097\3\2\2\2\37\u0099\3\2\2\2!\u009c\3\2\2\2#\u009f\3\2\2\2"+
		"%\u00a1\3\2\2\2\'\u00a3\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3\2\2\2-\u00a9\3"+
		"\2\2\2/\u00ac\3\2\2\2\61\u00b1\3\2\2\2\63\u00b5\3\2\2\2\65\u00bb\3\2\2"+
		"\2\67\u00bd\3\2\2\29\u00c1\3\2\2\2;\u00c3\3\2\2\2=\u00c7\3\2\2\2?\u00cc"+
		"\3\2\2\2A\u00e2\3\2\2\2C\u00e4\3\2\2\2E\u00f0\3\2\2\2G\u00f3\3\2\2\2I"+
		"\u00f7\3\2\2\2K\u00fe\3\2\2\2M\u0102\3\2\2\2O\u0109\3\2\2\2Q\u010d\3\2"+
		"\2\2S\u010f\3\2\2\2U\u0111\3\2\2\2W\u0113\3\2\2\2Y\u0115\3\2\2\2[\u0117"+
		"\3\2\2\2]\u011a\3\2\2\2_`\7\61\2\2`\4\3\2\2\2ab\7u\2\2bc\7w\2\2cd\7d\2"+
		"\2de\7u\2\2ef\7v\2\2fg\7t\2\2gh\7k\2\2hi\7p\2\2ij\7i\2\2j\6\3\2\2\2kl"+
		"\7v\2\2lm\7t\2\2mn\7w\2\2no\7g\2\2o\b\3\2\2\2pq\7#\2\2qr\7?\2\2r\n\3\2"+
		"\2\2st\7e\2\2tu\7q\2\2uv\7p\2\2vw\7e\2\2wx\7c\2\2xy\7v\2\2y\f\3\2\2\2"+
		"z{\7?\2\2{\16\3\2\2\2|}\7>\2\2}~\7?\2\2~\20\3\2\2\2\177\u0080\7*\2\2\u0080"+
		"\22\3\2\2\2\u0081\u0082\7,\2\2\u0082\24\3\2\2\2\u0083\u0084\7.\2\2\u0084"+
		"\26\3\2\2\2\u0085\u0086\7h\2\2\u0086\u0087\7c\2\2\u0087\u0088\7n\2\2\u0088"+
		"\u0089\7u\2\2\u0089\u008a\7g\2\2\u008a\30\3\2\2\2\u008b\u008c\7e\2\2\u008c"+
		"\u008d\7g\2\2\u008d\u008e\7k\2\2\u008e\u008f\7n\2\2\u008f\u0090\7k\2\2"+
		"\u0090\u0091\7p\2\2\u0091\u0092\7i\2\2\u0092\32\3\2\2\2\u0093\u0094\7"+
		"o\2\2\u0094\u0095\7q\2\2\u0095\u0096\7f\2\2\u0096\34\3\2\2\2\u0097\u0098"+
		"\7]\2\2\u0098\36\3\2\2\2\u0099\u009a\7@\2\2\u009a\u009b\7?\2\2\u009b "+
		"\3\2\2\2\u009c\u009d\7\61\2\2\u009d\u009e\7\61\2\2\u009e\"\3\2\2\2\u009f"+
		"\u00a0\7~\2\2\u00a0$\3\2\2\2\u00a1\u00a2\7>\2\2\u00a2&\3\2\2\2\u00a3\u00a4"+
		"\7_\2\2\u00a4(\3\2\2\2\u00a5\u00a6\7@\2\2\u00a6*\3\2\2\2\u00a7\u00a8\7"+
		"B\2\2\u00a8,\3\2\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7t\2\2\u00ab.\3\2"+
		"\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7z\2\2\u00af\u00b0"+
		"\7v\2\2\u00b0\60\3\2\2\2\u00b1\u00b2\7f\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4"+
		"\7x\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8"+
		"\7q\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba\7t\2\2\u00ba\64\3\2\2\2\u00bb\u00bc"+
		"\7+\2\2\u00bc\66\3\2\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7p\2\2\u00bf\u00c0"+
		"\7f\2\2\u00c08\3\2\2\2\u00c1\u00c2\7-\2\2\u00c2:\3\2\2\2\u00c3\u00c4\7"+
		"p\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7v\2\2\u00c6<\3\2\2\2\u00c7\u00c8"+
		"\7/\2\2\u00c8>\3\2\2\2\u00c9\u00ca\5I%\2\u00ca\u00cb\7<\2\2\u00cb\u00cd"+
		"\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00cf\5K&\2\u00cf@\3\2\2\2\u00d0\u00d5\7$\2\2\u00d1\u00d4\5]/\2\u00d2"+
		"\u00d4\n\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d8\u00e3\7$\2\2\u00d9\u00de\7)\2\2\u00da\u00dd\5]/\2"+
		"\u00db\u00dd\n\3\2\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd\u00e0"+
		"\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e1\u00e3\7)\2\2\u00e2\u00d0\3\2\2\2\u00e2\u00d9\3\2"+
		"\2\2\u00e3B\3\2\2\2\u00e4\u00e5\5G$\2\u00e5D\3\2\2\2\u00e6\u00e7\7\60"+
		"\2\2\u00e7\u00f1\5G$\2\u00e8\u00e9\5G$\2\u00e9\u00ed\7\60\2\2\u00ea\u00ec"+
		"\t\4\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00e6\3\2"+
		"\2\2\u00f0\u00e8\3\2\2\2\u00f1F\3\2\2\2\u00f2\u00f4\5Y-\2\u00f3\u00f2"+
		"\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"H\3\2\2\2\u00f7\u00fb\5M\'\2\u00f8\u00fa\5O(\2\u00f9\u00f8\3\2\2\2\u00fa"+
		"\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fcJ\3\2\2\2"+
		"\u00fd\u00fb\3\2\2\2\u00fe\u00ff\5I%\2\u00ffL\3\2\2\2\u0100\u0103\5Q)"+
		"\2\u0101\u0103\7a\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103N\3"+
		"\2\2\2\u0104\u010a\5Q)\2\u0105\u010a\5W,\2\u0106\u010a\5[.\2\u0107\u010a"+
		"\5Y-\2\u0108\u010a\t\5\2\2\u0109\u0104\3\2\2\2\u0109\u0105\3\2\2\2\u0109"+
		"\u0106\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010aP\3\2\2\2"+
		"\u010b\u010e\5S*\2\u010c\u010e\5U+\2\u010d\u010b\3\2\2\2\u010d\u010c\3"+
		"\2\2\2\u010eR\3\2\2\2\u010f\u0110\t\6\2\2\u0110T\3\2\2\2\u0111\u0112\t"+
		"\7\2\2\u0112V\3\2\2\2\u0113\u0114\t\b\2\2\u0114X\3\2\2\2\u0115\u0116\t"+
		"\t\2\2\u0116Z\3\2\2\2\u0117\u0118\t\n\2\2\u0118\\\3\2\2\2\u0119\u011b"+
		"\t\13\2\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2"+
		"\u011c\u011d\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\b/\2\2\u011f^\3\2"+
		"\2\2\21\2\u00cc\u00d3\u00d5\u00dc\u00de\u00e2\u00ed\u00f0\u00f5\u00fb"+
		"\u0102\u0109\u010d\u011c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}