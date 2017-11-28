// Generated from Co2pN.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Co2pNLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOOLEAN=1, CHAR=2, CONST=3, DO=4, ELSE=5, FLOAT=6, FOR=7, IF=8, INT=9, 
		NEW=10, NULL=11, OBJECT=12, RETURN=13, STRING=14, STRUCT=15, THEN=16, 
		THIS=17, VOID=18, WHILE=19, ADDR=20, DEREF=21, IntegerLiteral=22, FloatingPointLiteral=23, 
		BooleanLiteral=24, CharacterLiteral=25, StringLiteral=26, NullLiteral=27, 
		LPAREN=28, RPAREN=29, LBRACE=30, RBRACE=31, LBRACK=32, RBRACK=33, SEMI=34, 
		COMMA=35, DOT=36, ASSIGN=37, GT=38, LT=39, BANG=40, EQUAL=41, LE=42, GE=43, 
		NOTEQUAL=44, AND=45, OR=46, ADD=47, SUB=48, MUL=49, DIV=50, MOD=51, WS=52, 
		COMMENT=53, LINE_COMMENT=54, Identifier=55;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"BOOLEAN", "CHAR", "CONST", "DO", "ELSE", "FLOAT", "FOR", "IF", "INT", 
		"NEW", "NULL", "OBJECT", "RETURN", "STRING", "STRUCT", "THEN", "THIS", 
		"VOID", "WHILE", "ADDR", "DEREF", "IntegerLiteral", "DecimalIntegerLiteral", 
		"DecimalNumeral", "Digits", "Digit", "NonZeroDigit", "FloatingPointLiteral", 
		"DecimalFloatingPointLiteral", "ExponentPart", "ExponentIndicator", "SignedInteger", 
		"Sign", "FloatTypeSuffix", "BooleanLiteral", "CharacterLiteral", "SingleCharacter", 
		"StringLiteral", "StringCharacters", "StringCharacter", "EscapeSequence", 
		"NullLiteral", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
		"SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "BANG", "EQUAL", "LE", "GE", 
		"NOTEQUAL", "AND", "OR", "ADD", "SUB", "MUL", "DIV", "MOD", "WS", "COMMENT", 
		"LINE_COMMENT", "Identifier", "Co2pNLetter", "Co2pNLetterOrDigit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'varbool'", "'varch'", "'const'", "'perform'", "'else'", "'varfp'", 
		"'for'", "'if'", "'varint'", "'new'", "'null'", "'var'", "'return'", "'varstr'", 
		"'struct'", "'then'", "'self'", "'void'", "'while'", "'@'", "'^'", null, 
		null, null, null, null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", 
		"'DESU!'", "','", "'.'", "'='", "'>'", "'<'", "'!'", "'=='", "'<='", "'>='", 
		"'!='", "'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BOOLEAN", "CHAR", "CONST", "DO", "ELSE", "FLOAT", "FOR", "IF", 
		"INT", "NEW", "NULL", "OBJECT", "RETURN", "STRING", "STRUCT", "THEN", 
		"THIS", "VOID", "WHILE", "ADDR", "DEREF", "IntegerLiteral", "FloatingPointLiteral", 
		"BooleanLiteral", "CharacterLiteral", "StringLiteral", "NullLiteral", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
		"DOT", "ASSIGN", "GT", "LT", "BANG", "EQUAL", "LE", "GE", "NOTEQUAL", 
		"AND", "OR", "ADD", "SUB", "MUL", "DIV", "MOD", "WS", "COMMENT", "LINE_COMMENT", 
		"Identifier"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Co2pNLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Co2pN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u01de\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\5\31\u010b\n\31\5\31"+
		"\u010d\n\31\3\32\3\32\7\32\u0111\n\32\f\32\16\32\u0114\13\32\3\33\3\33"+
		"\5\33\u0118\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\5\36\u0121\n\36\3"+
		"\36\5\36\u0124\n\36\3\36\5\36\u0127\n\36\3\36\3\36\3\36\5\36\u012c\n\36"+
		"\3\36\5\36\u012f\n\36\3\36\3\36\3\36\5\36\u0134\n\36\3\36\3\36\3\36\5"+
		"\36\u0139\n\36\3\37\3\37\3\37\3 \3 \3!\5!\u0141\n!\3!\3!\3\"\3\"\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0152\n$\3%\3%\3%\3%\3%\3%\3%\3%\5%\u015c"+
		"\n%\3&\3&\3\'\3\'\5\'\u0162\n\'\3\'\3\'\3(\6(\u0167\n(\r(\16(\u0168\3"+
		")\3)\5)\u016d\n)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66"+
		"\3\66\3\67\3\67\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3=\3>"+
		"\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\6D\u01af\nD\rD\16D\u01b0\3D\3"+
		"D\3E\3E\3E\3E\7E\u01b9\nE\fE\16E\u01bc\13E\3E\3E\3E\3E\3E\3F\3F\7F\u01c5"+
		"\nF\fF\16F\u01c8\13F\3F\3F\3G\3G\7G\u01ce\nG\fG\16G\u01d1\13G\3H\3H\3"+
		"H\3H\5H\u01d7\nH\3I\3I\3I\3I\5I\u01dd\nI\3\u01ba\2J\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\2\61\2\63\2\65\2\67\29\31;\2=\2?\2A\2C\2E\2G\32I\33K"+
		"\2M\34O\2Q\2S\2U\35W\36Y\37[ ]!_\"a#c$e%g&i\'k(m)o*q+s,u-w.y/{\60}\61"+
		"\177\62\u0081\63\u0083\64\u0085\65\u0087\66\u0089\67\u008b8\u008d9\u008f"+
		"\2\u0091\2\3\2\20\3\2\63;\4\2GGgg\4\2--//\6\2FFHHffhh\6\2\f\f\17\17))"+
		"^^\6\2\f\f\17\17$$^^\n\2$$))^^ddhhppttvv\5\2\13\f\16\17\"\"\4\2\f\f\17"+
		"\17\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001"+
		"\7\2&&\62;C\\aac|\2\u01e7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\29\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2M\3\2\2\2\2U\3\2\2\2"+
		"\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c"+
		"\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2"+
		"\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2"+
		"\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2"+
		"\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\3"+
		"\u0093\3\2\2\2\5\u009b\3\2\2\2\7\u00a1\3\2\2\2\t\u00a7\3\2\2\2\13\u00af"+
		"\3\2\2\2\r\u00b4\3\2\2\2\17\u00ba\3\2\2\2\21\u00be\3\2\2\2\23\u00c1\3"+
		"\2\2\2\25\u00c8\3\2\2\2\27\u00cc\3\2\2\2\31\u00d1\3\2\2\2\33\u00d5\3\2"+
		"\2\2\35\u00dc\3\2\2\2\37\u00e3\3\2\2\2!\u00ea\3\2\2\2#\u00ef\3\2\2\2%"+
		"\u00f4\3\2\2\2\'\u00f9\3\2\2\2)\u00ff\3\2\2\2+\u0101\3\2\2\2-\u0103\3"+
		"\2\2\2/\u0105\3\2\2\2\61\u010c\3\2\2\2\63\u010e\3\2\2\2\65\u0117\3\2\2"+
		"\2\67\u0119\3\2\2\29\u011b\3\2\2\2;\u0138\3\2\2\2=\u013a\3\2\2\2?\u013d"+
		"\3\2\2\2A\u0140\3\2\2\2C\u0144\3\2\2\2E\u0146\3\2\2\2G\u0151\3\2\2\2I"+
		"\u015b\3\2\2\2K\u015d\3\2\2\2M\u015f\3\2\2\2O\u0166\3\2\2\2Q\u016c\3\2"+
		"\2\2S\u016e\3\2\2\2U\u0171\3\2\2\2W\u0173\3\2\2\2Y\u0175\3\2\2\2[\u0177"+
		"\3\2\2\2]\u0179\3\2\2\2_\u017b\3\2\2\2a\u017d\3\2\2\2c\u017f\3\2\2\2e"+
		"\u0185\3\2\2\2g\u0187\3\2\2\2i\u0189\3\2\2\2k\u018b\3\2\2\2m\u018d\3\2"+
		"\2\2o\u018f\3\2\2\2q\u0191\3\2\2\2s\u0194\3\2\2\2u\u0197\3\2\2\2w\u019a"+
		"\3\2\2\2y\u019d\3\2\2\2{\u01a0\3\2\2\2}\u01a3\3\2\2\2\177\u01a5\3\2\2"+
		"\2\u0081\u01a7\3\2\2\2\u0083\u01a9\3\2\2\2\u0085\u01ab\3\2\2\2\u0087\u01ae"+
		"\3\2\2\2\u0089\u01b4\3\2\2\2\u008b\u01c2\3\2\2\2\u008d\u01cb\3\2\2\2\u008f"+
		"\u01d6\3\2\2\2\u0091\u01dc\3\2\2\2\u0093\u0094\7x\2\2\u0094\u0095\7c\2"+
		"\2\u0095\u0096\7t\2\2\u0096\u0097\7d\2\2\u0097\u0098\7q\2\2\u0098\u0099"+
		"\7q\2\2\u0099\u009a\7n\2\2\u009a\4\3\2\2\2\u009b\u009c\7x\2\2\u009c\u009d"+
		"\7c\2\2\u009d\u009e\7t\2\2\u009e\u009f\7e\2\2\u009f\u00a0\7j\2\2\u00a0"+
		"\6\3\2\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7p\2\2\u00a4"+
		"\u00a5\7u\2\2\u00a5\u00a6\7v\2\2\u00a6\b\3\2\2\2\u00a7\u00a8\7r\2\2\u00a8"+
		"\u00a9\7g\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac\7q\2\2"+
		"\u00ac\u00ad\7t\2\2\u00ad\u00ae\7o\2\2\u00ae\n\3\2\2\2\u00af\u00b0\7g"+
		"\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7g\2\2\u00b3\f"+
		"\3\2\2\2\u00b4\u00b5\7x\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7\7t\2\2\u00b7"+
		"\u00b8\7h\2\2\u00b8\u00b9\7r\2\2\u00b9\16\3\2\2\2\u00ba\u00bb\7h\2\2\u00bb"+
		"\u00bc\7q\2\2\u00bc\u00bd\7t\2\2\u00bd\20\3\2\2\2\u00be\u00bf\7k\2\2\u00bf"+
		"\u00c0\7h\2\2\u00c0\22\3\2\2\2\u00c1\u00c2\7x\2\2\u00c2\u00c3\7c\2\2\u00c3"+
		"\u00c4\7t\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7v\2\2"+
		"\u00c7\24\3\2\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7"+
		"y\2\2\u00cb\26\3\2\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce\7w\2\2\u00ce\u00cf"+
		"\7n\2\2\u00cf\u00d0\7n\2\2\u00d0\30\3\2\2\2\u00d1\u00d2\7x\2\2\u00d2\u00d3"+
		"\7c\2\2\u00d3\u00d4\7t\2\2\u00d4\32\3\2\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7"+
		"\7g\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7w\2\2\u00d9\u00da\7t\2\2\u00da"+
		"\u00db\7p\2\2\u00db\34\3\2\2\2\u00dc\u00dd\7x\2\2\u00dd\u00de\7c\2\2\u00de"+
		"\u00df\7t\2\2\u00df\u00e0\7u\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7t\2\2"+
		"\u00e2\36\3\2\2\2\u00e3\u00e4\7u\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6\7"+
		"t\2\2\u00e6\u00e7\7w\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7v\2\2\u00e9 "+
		"\3\2\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7j\2\2\u00ec\u00ed\7g\2\2\u00ed"+
		"\u00ee\7p\2\2\u00ee\"\3\2\2\2\u00ef\u00f0\7u\2\2\u00f0\u00f1\7g\2\2\u00f1"+
		"\u00f2\7n\2\2\u00f2\u00f3\7h\2\2\u00f3$\3\2\2\2\u00f4\u00f5\7x\2\2\u00f5"+
		"\u00f6\7q\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7f\2\2\u00f8&\3\2\2\2\u00f9"+
		"\u00fa\7y\2\2\u00fa\u00fb\7j\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7n\2\2"+
		"\u00fd\u00fe\7g\2\2\u00fe(\3\2\2\2\u00ff\u0100\7B\2\2\u0100*\3\2\2\2\u0101"+
		"\u0102\7`\2\2\u0102,\3\2\2\2\u0103\u0104\5/\30\2\u0104.\3\2\2\2\u0105"+
		"\u0106\5\61\31\2\u0106\60\3\2\2\2\u0107\u010d\7\62\2\2\u0108\u010a\5\67"+
		"\34\2\u0109\u010b\5\63\32\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010d\3\2\2\2\u010c\u0107\3\2\2\2\u010c\u0108\3\2\2\2\u010d\62\3\2\2"+
		"\2\u010e\u0112\5\65\33\2\u010f\u0111\5\65\33\2\u0110\u010f\3\2\2\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\64\3\2\2"+
		"\2\u0114\u0112\3\2\2\2\u0115\u0118\7\62\2\2\u0116\u0118\5\67\34\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118\66\3\2\2\2\u0119\u011a\t\2\2"+
		"\2\u011a8\3\2\2\2\u011b\u011c\5;\36\2\u011c:\3\2\2\2\u011d\u011e\5\63"+
		"\32\2\u011e\u0120\5g\64\2\u011f\u0121\5\63\32\2\u0120\u011f\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0124\5=\37\2\u0123\u0122\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0127\5E#\2\u0126\u0125"+
		"\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0139\3\2\2\2\u0128\u0129\5g\64\2\u0129"+
		"\u012b\5\63\32\2\u012a\u012c\5=\37\2\u012b\u012a\3\2\2\2\u012b\u012c\3"+
		"\2\2\2\u012c\u012e\3\2\2\2\u012d\u012f\5E#\2\u012e\u012d\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012f\u0139\3\2\2\2\u0130\u0131\5\63\32\2\u0131\u0133\5"+
		"=\37\2\u0132\u0134\5E#\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0139\3\2\2\2\u0135\u0136\5\63\32\2\u0136\u0137\5E#\2\u0137\u0139\3\2"+
		"\2\2\u0138\u011d\3\2\2\2\u0138\u0128\3\2\2\2\u0138\u0130\3\2\2\2\u0138"+
		"\u0135\3\2\2\2\u0139<\3\2\2\2\u013a\u013b\5? \2\u013b\u013c\5A!\2\u013c"+
		">\3\2\2\2\u013d\u013e\t\3\2\2\u013e@\3\2\2\2\u013f\u0141\5C\"\2\u0140"+
		"\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\5\63"+
		"\32\2\u0143B\3\2\2\2\u0144\u0145\t\4\2\2\u0145D\3\2\2\2\u0146\u0147\t"+
		"\5\2\2\u0147F\3\2\2\2\u0148\u0149\7v\2\2\u0149\u014a\7t\2\2\u014a\u014b"+
		"\7w\2\2\u014b\u0152\7g\2\2\u014c\u014d\7h\2\2\u014d\u014e\7c\2\2\u014e"+
		"\u014f\7n\2\2\u014f\u0150\7u\2\2\u0150\u0152\7g\2\2\u0151\u0148\3\2\2"+
		"\2\u0151\u014c\3\2\2\2\u0152H\3\2\2\2\u0153\u0154\7)\2\2\u0154\u0155\5"+
		"K&\2\u0155\u0156\7)\2\2\u0156\u015c\3\2\2\2\u0157\u0158\7)\2\2\u0158\u0159"+
		"\5S*\2\u0159\u015a\7)\2\2\u015a\u015c\3\2\2\2\u015b\u0153\3\2\2\2\u015b"+
		"\u0157\3\2\2\2\u015cJ\3\2\2\2\u015d\u015e\n\6\2\2\u015eL\3\2\2\2\u015f"+
		"\u0161\7$\2\2\u0160\u0162\5O(\2\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2"+
		"\2\u0162\u0163\3\2\2\2\u0163\u0164\7$\2\2\u0164N\3\2\2\2\u0165\u0167\5"+
		"Q)\2\u0166\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0166\3\2\2\2\u0168"+
		"\u0169\3\2\2\2\u0169P\3\2\2\2\u016a\u016d\n\7\2\2\u016b\u016d\5S*\2\u016c"+
		"\u016a\3\2\2\2\u016c\u016b\3\2\2\2\u016dR\3\2\2\2\u016e\u016f\7^\2\2\u016f"+
		"\u0170\t\b\2\2\u0170T\3\2\2\2\u0171\u0172\5\27\f\2\u0172V\3\2\2\2\u0173"+
		"\u0174\7*\2\2\u0174X\3\2\2\2\u0175\u0176\7+\2\2\u0176Z\3\2\2\2\u0177\u0178"+
		"\7}\2\2\u0178\\\3\2\2\2\u0179\u017a\7\177\2\2\u017a^\3\2\2\2\u017b\u017c"+
		"\7]\2\2\u017c`\3\2\2\2\u017d\u017e\7_\2\2\u017eb\3\2\2\2\u017f\u0180\7"+
		"F\2\2\u0180\u0181\7G\2\2\u0181\u0182\7U\2\2\u0182\u0183\7W\2\2\u0183\u0184"+
		"\7#\2\2\u0184d\3\2\2\2\u0185\u0186\7.\2\2\u0186f\3\2\2\2\u0187\u0188\7"+
		"\60\2\2\u0188h\3\2\2\2\u0189\u018a\7?\2\2\u018aj\3\2\2\2\u018b\u018c\7"+
		"@\2\2\u018cl\3\2\2\2\u018d\u018e\7>\2\2\u018en\3\2\2\2\u018f\u0190\7#"+
		"\2\2\u0190p\3\2\2\2\u0191\u0192\7?\2\2\u0192\u0193\7?\2\2\u0193r\3\2\2"+
		"\2\u0194\u0195\7>\2\2\u0195\u0196\7?\2\2\u0196t\3\2\2\2\u0197\u0198\7"+
		"@\2\2\u0198\u0199\7?\2\2\u0199v\3\2\2\2\u019a\u019b\7#\2\2\u019b\u019c"+
		"\7?\2\2\u019cx\3\2\2\2\u019d\u019e\7(\2\2\u019e\u019f\7(\2\2\u019fz\3"+
		"\2\2\2\u01a0\u01a1\7~\2\2\u01a1\u01a2\7~\2\2\u01a2|\3\2\2\2\u01a3\u01a4"+
		"\7-\2\2\u01a4~\3\2\2\2\u01a5\u01a6\7/\2\2\u01a6\u0080\3\2\2\2\u01a7\u01a8"+
		"\7,\2\2\u01a8\u0082\3\2\2\2\u01a9\u01aa\7\61\2\2\u01aa\u0084\3\2\2\2\u01ab"+
		"\u01ac\7\'\2\2\u01ac\u0086\3\2\2\2\u01ad\u01af\t\t\2\2\u01ae\u01ad\3\2"+
		"\2\2\u01af\u01b0\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b3\bD\2\2\u01b3\u0088\3\2\2\2\u01b4\u01b5\7=\2"+
		"\2\u01b5\u01b6\7<\2\2\u01b6\u01ba\3\2\2\2\u01b7\u01b9\13\2\2\2\u01b8\u01b7"+
		"\3\2\2\2\u01b9\u01bc\3\2\2\2\u01ba\u01bb\3\2\2\2\u01ba\u01b8\3\2\2\2\u01bb"+
		"\u01bd\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bd\u01be\7<\2\2\u01be\u01bf\7=\2"+
		"\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\bE\2\2\u01c1\u008a\3\2\2\2\u01c2\u01c6"+
		"\7=\2\2\u01c3\u01c5\n\n\2\2\u01c4\u01c3\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6"+
		"\u01c4\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c9\3\2\2\2\u01c8\u01c6\3\2"+
		"\2\2\u01c9\u01ca\bF\2\2\u01ca\u008c\3\2\2\2\u01cb\u01cf\5\u008fH\2\u01cc"+
		"\u01ce\5\u0091I\2\u01cd\u01cc\3\2\2\2\u01ce\u01d1\3\2\2\2\u01cf\u01cd"+
		"\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u008e\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d2"+
		"\u01d7\t\13\2\2\u01d3\u01d7\n\f\2\2\u01d4\u01d5\t\r\2\2\u01d5\u01d7\t"+
		"\16\2\2\u01d6\u01d2\3\2\2\2\u01d6\u01d3\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7"+
		"\u0090\3\2\2\2\u01d8\u01dd\t\17\2\2\u01d9\u01dd\n\f\2\2\u01da\u01db\t"+
		"\r\2\2\u01db\u01dd\t\16\2\2\u01dc\u01d8\3\2\2\2\u01dc\u01d9\3\2\2\2\u01dc"+
		"\u01da\3\2\2\2\u01dd\u0092\3\2\2\2\32\2\u010a\u010c\u0112\u0117\u0120"+
		"\u0123\u0126\u012b\u012e\u0133\u0138\u0140\u0151\u015b\u0161\u0168\u016c"+
		"\u01b0\u01ba\u01c6\u01cf\u01d6\u01dc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}