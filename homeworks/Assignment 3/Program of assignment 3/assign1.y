%{
void yyerror (char *s);
extern int yylex();
#include <stdio.h>     
#include <stdlib.h>
#include <ctype.h>

%}

%union {int i;}
%token <i> INT
%type <i> E

%%
S : E          {printf("the output is : %d", $1);}
  ;
E : INT '+' E  {$$ = $1 + $3;}
  | INT '*' E  {$$ = $1 * $3;}
  | '(' E ')' {$$ = $2;}
  | INT      {$$ = $1;}
  ;
%%


int main (void) {
	yyparse();
	return 0;
}

void yyerror (char *s) {fprintf (stderr, "%s\n", s);
exit(1);} 