package com.calculadora.analizadores;
import java_cup.runtime.Symbol;


%%

%class Lexico
%public
%line
%char
%cup
%unicode
%ignorecase
%init{
	yyline = 1;
	yychar = 1;
%init}


BLANCOS=[ \r\t]+
D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?

%%



"(" {return new Symbol(sym.PARIZQ,yyline,(int)yychar, yytext());}
")" {return new Symbol(sym.PARDER,yyline,(int)yychar, yytext());}
"[" {return new Symbol(sym.CORIZQ,yyline,(int)yychar, yytext());}
"]" {return new Symbol(sym.CORDER,yyline,(int)yychar, yytext());}

"+" {return new Symbol(sym.MAS,yyline,(int)yychar, yytext());}
"-" {return new Symbol(sym.MENOS,yyline,(int)yychar, yytext());}
"*" {return new Symbol(sym.POR,yyline,(int)yychar, yytext());}
"/" {return new Symbol(sym.DIVIDIDO,yyline,(int)yychar, yytext());}

\n {yychar=1;}

{BLANCOS} {}
{D} {return new Symbol(sym.ENTERO,yyline,(int)yychar, yytext());}
{DD} {return new Symbol(sym.DECIMAL,yyline,(int)yychar, yytext());}


. {
	System.out.println("Este es un error lexico: "+yytext()+
	", en la linea: "+yyline+", en la columna: "+yychar);
}