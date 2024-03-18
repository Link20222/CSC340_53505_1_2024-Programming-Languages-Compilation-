%%

([a-zA-Z]+[0-9]*)*       {printf("\nID              || %s\n", yytext);}
"+"|"*"|"="              {printf("\nOperator        || %s\n", yytext);}
")"                      {printf("\nclosing bracket || %s\n", yytext);}
"("                      {printf("\nopening bracket || %s\n", yytext);}
[ \n\t\f\v]              {;}
.                        {printf("\n=========================================================\n");printf("This String ||  %s  ||not acceptable by the language!!", yytext);printf("\n=========================================================\n\n");}
([0-9]+[a-zA-Z]*)*       {printf("\n=========================================================\n");printf("This String ||  %s  ||not acceptable by the language!!", yytext);printf("\n=========================================================\n\n");}

%%

int yywrap(){
return 1;
}

int main(){
printf("Enter your code, please:\n\n");
yylex();
system("pause");
return 0;
}