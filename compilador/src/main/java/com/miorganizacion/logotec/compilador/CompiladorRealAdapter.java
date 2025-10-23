package com.miorganizacion.logotec.compilador;

import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.ANTLRInputStream;

public class CompiladorRealAdapter {

    public static ProgramNode compile(String codigoFuente) {
        System.out.println("[CompiladorRealAdapter] Iniciando compilación...");

        try {
            // 1. Crear flujo de caracteres desde el código fuente
            //CharStream input = CharStreams.fromString(codigoFuente);
        	ANTLRInputStream input = new ANTLRInputStream(codigoFuente);


            // 2. Crear lexer y capturar errores léxicos
            LogoTecLexer lexer = new LogoTecLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new RuntimeException("Error léxico en línea " + line + ":" + charPositionInLine + " → " + msg);
                }
            });

            // 3. Crear parser y capturar errores sintácticos
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LogoTecParser parser = new LogoTecParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new RuntimeException("Error sintáctico en línea " + line + ":" + charPositionInLine + " → " + msg);
                }
            });

            // 4. Parsear el programa
            System.out.println("[CompiladorRealAdapter] Parseando programa...");
            LogoTecParser.ProgramContext tree = parser.program();

            // 5. Obtener el AST directamente del contexto del parser
            // La gramática usa acciones embebidas que construyen el AST automáticamente
            System.out.println("[CompiladorRealAdapter] Construyendo AST...");
            ProgramNode programa = tree.node;
            
            if (programa == null) {
                throw new RuntimeException("El parser no generó un AST válido. Verifica la sintaxis del código.");
            }

            System.out.println("[CompiladorRealAdapter] AST generado con éxito.");
            return programa;

        } catch (RuntimeException e) {
            System.err.println("[CompiladorRealAdapter] Error durante la compilación:");
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("[CompiladorRealAdapter] Excepción inesperada:");
            e.printStackTrace();
            throw new RuntimeException("Error inesperado durante la compilación", e);
        }
    }
}
