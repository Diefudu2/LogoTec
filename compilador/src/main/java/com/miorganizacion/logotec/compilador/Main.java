package com.miorganizacion.logotec.compilador;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

/*
Main de la herramienta de compilación/interpr. Punto de entrada CLI.
- Carga un archivo .logo (por defecto test/test.logo).
- Construye lexer y parser generados por ANTLR.
- Invoca el visitor que transforma el parse tree en el AST.
- Actualmente no ejecuta el paso semántico ni la VM; solo arma el AST.
*/

public class Main {
    private static final String EXTENSION = "logo";

    public static void main(String[] args) throws Exception {
    	// Selección de fichero: primer argumento CLI o test/test.logo por defecto
    	String program = args.length > 0 ? args[0] : "test/test." + EXTENSION;

        System.out.println("Interpretando archivo " + program);
        // Creación del lexer a partir del fichero fuente
        // ANTLRFileStream abre el archivo y lo expone como CharStream (ANTLR 4.4 API).
        LogoTecLexer lexer = new LogoTecLexer(new ANTLRFileStream(program));
        // Buffer de tokens generado por el lexer, consumido por el parser.
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //Instancia del parser generado por ANTLR.
        LogoTecParser parser = new LogoTecParser(tokens);

        // Regla top-level: produce ProgramContext (raíz del parse tree).
        LogoTecParser.ProgramContext tree = parser.program();
        
        // Visitor que convierte el parse tree en la jerarquía AST del proyecto.
        // Resultado del visit: AST construido (se ignora el valor retornado aquí).
        LogoTecCustomVisitor visitor = new LogoTecCustomVisitor();
        visitor.visit(tree);

        // Indicación de fin de proceso; en futuras versiones aquí se encadenaría:
        // SemanticAnalyzer -> passes de optimización -> codegen/VM.
        System.out.println("Interpretación finalizada ✅");
    }
}
