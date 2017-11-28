grammar Co2pN;


/* ************************************************************************
 * PARSER
 * ************************************************************************/
 
/* ************************************
 * Compilation Unit
 * ************************************/
 
compilationUnit
	:	memberDeclaration* EOF
	;

memberDeclaration
	:	methodDeclaration
	|	constDeclaration
	|	structDeclaration
	;

/* ************************************
 * Method
 * ************************************/
 
methodDeclaration
	:	(typeType|VOID) Identifier formalParameters methodBody
	;
formalParameters
	:	LPAREN formalParameterList? RPAREN
	;
formalParameterList
	:	formalParameter (COMMA formalParameter)*
	;
formalParameter
	: typeType? Identifier
	;
methodBody
	:	block
	;
	
/* ************************************
 * Constant
 * ************************************/
 
constDeclaration
	:	CONST Identifier ASSIGN literal SEMI
	;

/* ************************************
 * Structures
 * ************************************/
 
structDeclaration
	:	STRUCT Identifier structBody
	;
structBody
	:	LBRACE structMemberDeclaration* RBRACE
	;
structMemberDeclaration
	:	typeType Identifier SEMI
	;

/* ************************************
 * Variable Types
 * ************************************/

typeType
	:	varType (LBRACK RBRACK)?
	|	varType DEREF
	;
varType
	:	primitiveType
	|	structType
	;
primitiveType
	:	BOOLEAN
	|	CHAR
	|	INT
	|	FLOAT
	|	STRING
	|	OBJECT
	;
structType
	:	Identifier
	;
	
literal
	:	IntegerLiteral
	|	FloatingPointLiteral
	|	CharacterLiteral
	|	StringLiteral
	|	BooleanLiteral
	|	NULL
	;

/* ************************************
 * Statements and Blocks
 * ************************************/
 
block
	:	LBRACE blockStatement* RBRACE
	;
blockStatement
	:	variableDeclaration
	|	statement
	;

variableDeclaration
	:	typeType Identifier (ASSIGN expression)? SEMI
	;

statement
	:	block
	|	IF parExpression THEN statement  (ELSE statement)?
	|	FOR LPAREN forControl RPAREN statement
	|	WHILE parExpression statement
	|	DO statement WHILE parExpression SEMI
	|	RETURN expression? SEMI
	|	SEMI
	|	statementExpression SEMI
	;
	
forControl
	:	forInit? SEMI expression? SEMI forUpdate?
	;
forInit
	:	typeType? Identifier ASSIGN expression
	;
forUpdate
	:	expressionList
	;
	
/* ************************************
 * Expressions
 * ************************************/
 
parExpression
	:	LPAREN expression RPAREN
	;
expressionList
	:	expression (COMMA expression)*
	;
statementExpression
	:	expression
	;
expression
	:	primary
	|	expression DOT Identifier
	|	expression LBRACK expression RBRACK
	|	expression LPAREN expressionList? RPAREN
	|	NEW creator
	|	LPAREN typeType RPAREN expression
	|	(ADDR|DEREF) expression
	|	(ADD|SUB) expression
	|	(BANG) expression
	|	expression (MUL|DIV|MOD) expression
	|	expression (ADD|SUB) expression
	|	expression (LE|GE|GT|LT) expression
	|	expression (EQUAL|NOTEQUAL) expression
	|	expression AND expression
	|	expression OR expression
	|	<assoc=right> expression ASSIGN expression
	;
	
primary
	:	LPAREN expression RPAREN
	|	literal
	|	Identifier
	;
	
creator
	:	typeType arrayDim?
	;
arrayDim
	:	LBRACK expression RBRACK
	;


/* ************************************************************************
 * LEXER
 * ************************************************************************/

/* ************************************
 * Keywords
 * ************************************/

BOOLEAN	: 'varbool';
CHAR	: 'varch';
CONST	: 'const';
DO		: 'perform';
ELSE	: 'else';
FLOAT	: 'varfp';
FOR		: 'for';
IF		: 'if';
INT		: 'varint';
NEW		: 'new';
NULL	: 'null';
OBJECT	: 'var';
RETURN	: 'return';
STRING	: 'varstr';
STRUCT	: 'struct';
THEN	: 'then';
THIS	: 'self';
VOID	: 'void';
WHILE	: 'while';


ADDR	: '@';
DEREF	: '^';


/* ************************************
 * Literals
 * ************************************/
// Integer Literals

IntegerLiteral
	:	DecimalIntegerLiteral
	;

fragment
DecimalIntegerLiteral
	:	DecimalNumeral
	;

fragment
DecimalNumeral
	:	'0'
	|	NonZeroDigit Digits?
	;

fragment
Digits
	:	Digit Digit*
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
NonZeroDigit
	:	[1-9]
	;

// Floating-Point Literals

FloatingPointLiteral
	:	DecimalFloatingPointLiteral
	;

fragment
DecimalFloatingPointLiteral
	:	Digits DOT Digits? ExponentPart? FloatTypeSuffix?
	|	DOT Digits ExponentPart? FloatTypeSuffix?
	|	Digits ExponentPart FloatTypeSuffix?
	|	Digits FloatTypeSuffix
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;

fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;

fragment
Sign
	:	[+-]
	;

fragment
FloatTypeSuffix
	:	[fFdD]
	;

// Boolean Literals

BooleanLiteral
	:	'true'
	|	'false'
	;

// Character Literals

CharacterLiteral
	:	'\'' SingleCharacter '\''
	|	'\'' EscapeSequence '\''
	;

fragment
SingleCharacter
	:	~['\\\r\n]
	;

// String Literals
StringLiteral
	:	'"' StringCharacters? '"'
	;
fragment
StringCharacters
	:	StringCharacter+
	;
fragment
StringCharacter
	:	~["\\\r\n]
	|	EscapeSequence
	;
	
// Escape Sequences for Character and String Literals
fragment
EscapeSequence
	:	'\\' [btnfr"'\\]
	;

// Null Literal

NullLiteral
	:	NULL
	;

/* ************************************
 * Separators
 * ************************************/

LPAREN		  : '(';
RPAREN		  : ')';
LBRACE		  : '{';
RBRACE		  : '}';
LBRACK		  : '[';
RBRACK		  : ']';
SEMI			: 'DESU!';
COMMA			: ',';
DOT			 : '.';

/* ************************************
 * Operators
 * ************************************/

ASSIGN		  : '=';
GT			  : '>';
LT			  : '<';
BANG			: '!';
EQUAL			: '==';
LE			  : '<=';
GE			  : '>=';
NOTEQUAL		: '!=';
AND			 : '&&';
OR			  : '||';
ADD			 : '+';
SUB			 : '-';
MUL			 : '*';
DIV			 : '/';
MOD			 : '%';


/* ************************************
 * Whitespace and Comments
 * ************************************/

WS  :  [ \t\r\n\u000C]+ -> skip
	;

COMMENT
	:	';:' .*? ':;' -> skip
	;

LINE_COMMENT
	:	';' ~[\r\n]* -> skip
	;


/* ************************************
 * Identifiers
 * ************************************/
 
Identifier
	:	Co2pNLetter Co2pNLetterOrDigit*
	;

fragment
Co2pNLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
	;

fragment
Co2pNLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
	;
