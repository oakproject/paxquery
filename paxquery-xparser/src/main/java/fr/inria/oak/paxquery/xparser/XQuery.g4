grammar XQuery;

import XLexer, XPath;
//import XPath;

/*
 * CONSIDERATIONS
 * -Using STRING_LITERAL as URI
 * -USING STRING_LITERAL as C
 * -Using QNAME_TOKEN as ENAME
 * -Using QNAME_TOKEN as ANAME
 * -Using '$' NCNAME_TOK as VAR
 */
 
/*
 * TODO:
 * -Scape characters
 */



/* Non-terminals */
xquery : flwrexpr ;
flwrexpr : initial middle* returnStat ;
initial : forStat 
		| let ;
middle : initial 
		| where 
		| groupBy ;
forStat : 'for' forBinding (',' forBinding)* ;
forBinding : VAR 'in' pathExpr_xq ;
let : 'let' letBinding (',' letBinding)* ;
letBinding : VAR ':=' ( pathExpr_xq | flwrexpr | aggrExpr | arithmeticExpr_xq | literal) ;
arithExpr : ;
pathExpr_xq : 'distinct-values' '(' pathExprInner_xq (xpath)? ')' | pathExprInner_xq (xpath)? ;
pathExprInner_xq : 'collection' '(' STRING_LITERAL ')'  #pathExprInner_xq_collection 
				   | 'doc' '(' STRING_LITERAL ')' 		#pathExprInner_xq_doc
				   | VAR 								#pathExprInner_xq_VAR
				   ;								
aggrExpr : ('count' | 'avg' | 'max' | 'min' | 'sum' ) '(' VAR ')' ;
where : 'where' orExpr_xq ;
orExpr_xq : andExpr_xq (OR andExpr_xq)* ;
andExpr_xq : boolExpr_xq (AND boolExpr_xq)* ;
boolExpr_xq : NOT '(' boolExprInner_xq ')' | boolExprInner_xq ;
boolExprInner_xq : pred | contains | empty ;
//pred : VAR ( ( vcmp | ncmp ) ( VAR | literal) )?;
pred : arithmeticExpr_xq ( ( vcmp | ncmp ) ( arithmeticExpr_xq | literal) )?;
vcmp : EQ | NE | LT | LE | GT | GE | EQ_S | NE_S | LT_S | LE_S | GT_S | GE_S ;
ncmp : 'is' | '<<' | '>>' ;
contains : 'contains' '(' VAR ',' STRING_LITERAL ')' ;
empty : 'empty' '(' VAR ')' ;
groupBy : 'group by' VAR (',' VAR)* ;
returnStat : 'return' ( eleConst | (aggrExpr | VAR )+ ) ;
eleConst : LT_S eaName att* (CLOSE_TAG | (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL | eaName )* OPEN_TAG (eaName) GT_S )) ;
eleConstInner : ( ( VAR | aggrExpr ) (',')? )* ;
att : QNAME_TOKEN '=' '\"' ( LEFTCURL attInner RIGHTCURL | eaName)* '\"' ;
attInner : VAR | aggrExpr ;
eaName : functionName | QNAME_TOKEN ;
arithmeticExpr_xq : arithmeticExpr_xq ( '+' | '-' ) arithmeticExpr_xq
					| arithmeticExpr_xq ( '*' | 'div' | 'mod' ) arithmeticExpr_xq
					| '(' arithmeticExpr_xq ')' 
					| ( '-' )? numericLiteral
					| ( '-' )? VAR ;
