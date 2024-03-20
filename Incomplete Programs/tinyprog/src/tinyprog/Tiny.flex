%%

%class TinyLexical
%standalone
digit = [0-9]
number = {digit}+
letter = [a-zA-Z]
identifier = {letter}+
newline=\n
whitespace = [\t]+

%%
"if" {System.out.println("IF");}
"then" {System.out.println("THEN");}
"else" {System.out.println("ELSE");}
"end" {System.out.println("END");}
"repeat" {System.out.println("REPEAT");}
"until" {System.out.println("UNTIL");}
"read" {System.out.println("READ");}
"write" {System.out.println("WRITE");}
"(" {System.out.println("LEFT_PAREN");}
")" {System.out.println("RIGHT_PAREN");}
":" {System.out.println("COLON");}
"," {System.out.println("COMMA");}
";" {System.out.println("SEMICOLON");}
"=" {System.out.println("ASSIGNMENT");}
"+" {System.out.println("ADD");}
"-" {System.out.println("SUBTRACT");}
"*" {System.out.println("MULTIPLY");}
"/" {System.out.println("DIVIDE");}
"==" {System.out.println("EQUALS");}
">" {System.out.println("GREATER_THAN");}
"<" {System.out.println("LESS_THAN");}
">=" {System.out.println("GREATER_THAN_OR_EQUAL_TO");}
"<=" {System.out.println("LESS_THAN_OR_EQUAL_TO");}
"!=" {System.out.println("NOT_EQUAL_TO");}
{number} {System.out.printf("NUM(%d)\n", Integer.parseInt(yytext()));}
{identifier} {System.out.printf("ID(%s)\n", yytext());}
{whitespace} {/* skip white spaces*/}
{newline} {/* skip new lines*/}
. {System.out.printf("UNKNOWN SYMBOL(%s)\n",    yytext());}