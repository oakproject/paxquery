grammar XQuery;

import XLexer, XPath;
//import XPath;

/*
 * CONSIDERATIONS
 * -Using STRING_LITERAL as URI
 * -USING STRING_LITERAL as C
 * -Using QNAME_TOKEN names as ENAME
 * -Using QNAME_TOKEN names as ANAME
 */
 
/*
 * TODO:
 * -Scape characters
 */



/* Non-terminals */
xquery : flwrexpr EOF ;
flwrexpr : initial middle* returnStat ;
initial : forStat 
		| let ;
middle : initial 
		| where 
		| groupBy ;
forStat : 'for' forBinding (COMMA forBinding)* ;
forBinding : VAR 'in' pathExpr_xq ;
let : 'let' letBinding (COMMA letBinding)* ;
letBinding : VAR ':=' ( pathExpr_xq | flwrexpr | aggrExpr | arithmeticExpr_xq | literal) ;
arithExpr : ;
pathExpr_xq : 'distinct-values' '(' pathExprInner_xq (xpath)? ')' | pathExprInner_xq (xpath)? ;
pathExprInner_xq : 'collection' '(' STRING_LITERAL ')'  #pathExprInner_xq_collection 
				   | 'doc' '(' STRING_LITERAL ')' 		#pathExprInner_xq_doc
				   | VAR 								#pathExprInner_xq_VAR
				   ;								
aggrExpr : AGGR_FUNCT '(' VAR ')' ;
where : 'where' orExpr_xq ;
orExpr_xq : andExpr_xq (OR andExpr_xq)* ;
andExpr_xq : boolExpr_xq (AND boolExpr_xq)* ;
boolExpr_xq : NOT '(' boolExprInner_xq ')' | boolExprInner_xq ;
boolExprInner_xq : pred | contains | empty ;
pred : arithmeticExpr_xq ( ( vcmp | ncmp ) ( arithmeticExpr_xq | STRING_LITERAL | (OP_SUB)? numericLiteral ) )?;
vcmp : EQ | NE | LT | LE | GT | GE | EQ_S | NE_S | LT_S | LE_S | GT_S | GE_S ;
ncmp : 'is' | '<<' | '>>' ;
contains : 'contains' '(' VAR COMMA STRING_LITERAL ')' ;
empty : 'empty' '(' VAR ')' ;
groupBy : 'group by' VAR (COMMA VAR)* ;
returnStat : 'return' ( eleConst | aggrExpr | VAR ) ;
eleConst : LT_S eaName (att)* (CLOSE_OPENING_TAG | (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S )) ;
eleConstInner : ( VAR | aggrExpr ) ( COMMA ( VAR | aggrExpr ) )* ;
att : eaName '=' attInner ;
attInner : OPEN_ATTR_VAR_DOUBLE attInner2 CLOSE_ATTR_VAR_DOUBLE  
			| OPEN_ATTR_VAR_SINGLE attInner2 CLOSE_ATTR_VAR_SINGLE 
			| STRING_LITERAL ;
attInner2 : VAR | aggrExpr ;
eaName : functionName | QNAME_TOKEN ;
arithmeticExpr_xq : VAR ( ARITH_OP (OP_SUB)? numericLiteral )? ;
