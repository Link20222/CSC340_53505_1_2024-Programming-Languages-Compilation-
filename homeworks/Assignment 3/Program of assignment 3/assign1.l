%{
#include <stdio.h>     
#include <stdlib.h>
#include "assign1.tab.h"
%}

%option noyywrap

%%

[0-9]+  {yylval.i = atof(yytext); return INT;}
"+"|"*"|"("|")"  {return yytext[0];}
[ \n\t\f\v]              {return 0;} 
.  {return 0;}

%%