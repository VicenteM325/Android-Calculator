#! /bin/bash
echo "STARTING COMPILING"
java -jar jflex-full-1.9.1.jar Lexico.jflex
java -jar java-cup-11b.jar -parser Sintactico -symbols sym Sintactico.cup