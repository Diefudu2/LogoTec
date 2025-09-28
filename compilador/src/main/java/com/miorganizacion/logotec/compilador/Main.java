package com.miorganizacion.logotec.compilador;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    private static final String EXTENSION = "logo";

    public static void main(String[] args) throws Exception {
        String program = args.length > 0 ? args[0] : "test/test." + EXTENSION;

        System.out.println("Interpretando archivo " + program);

        // Lexer y parser con ANTLR 4.4
        LogoTecLexer lexer = new LogoTecLexer(new ANTLRFileStream(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogoTecParser parser = new LogoTecParser(tokens);

        LogoTecParser.ProgramContext tree = parser.program();

        LogoTecCustomVisitor visitor = new LogoTecCustomVisitor();
        visitor.visit(tree);

        System.out.println("Interpretación finalizada ✅");
    }
}
