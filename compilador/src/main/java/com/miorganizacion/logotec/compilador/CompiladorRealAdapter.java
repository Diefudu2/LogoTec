package com.miorganizacion.logotec.compilador;

import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.ANTLRInputStream;

import java.util.ArrayList;
import java.util.List;

public class CompiladorRealAdapter {

    public static ProgramNode compile(String codigoFuente) {
        System.out.println("[CompiladorRealAdapter] Iniciando compilación...");
        
        // Lista para acumular todos los errores
        List<String> allErrors = new ArrayList<>();

        try {
            ANTLRInputStream input = new ANTLRInputStream(codigoFuente);

            // 2. Crear lexer y ACUMULAR errores léxicos
            LogoTecLexer lexer = new LogoTecLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    allErrors.add("Error léxico en línea " + line + ":" + charPositionInLine + " → " + msg);
                }
            });

            // 3. Crear parser y ACUMULAR errores sintácticos
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LogoTecParser parser = new LogoTecParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    allErrors.add("Error sintáctico en línea " + line + ":" + charPositionInLine + " → " + msg);
                }
            });

            // 4. Parsear el programa (continúa aunque haya errores)
            System.out.println("[CompiladorRealAdapter] Parseando programa...");
            LogoTecParser.ProgramContext tree = parser.program();

            // 5. Si hubo errores léxicos/sintácticos, agregarlos antes de los semánticos
            if (!allErrors.isEmpty()) {
                // Los errores semánticos ya están en la excepción del parser
                throw new RuntimeException(String.join("\n", allErrors));
            }

            // 6. Obtener el AST
            System.out.println("[CompiladorRealAdapter] Construyendo AST...");
            ProgramNode programa = tree.node;
            
            if (programa == null) {
                throw new RuntimeException("El parser no generó un AST válido. Verifica la sintaxis del código.");
            }

            System.out.println("[CompiladorRealAdapter] AST generado con éxito.");
            return programa;

        } catch (RuntimeException e) {
            // Capturar errores semánticos del parser y combinar con léxicos/sintácticos
            String semanticErrors = e.getMessage();
            if (!allErrors.isEmpty()) {
                allErrors.add(semanticErrors);
                throw new RuntimeException(String.join("\n", allErrors));
            }
            throw e;
        } catch (Exception e) {
            System.err.println("[CompiladorRealAdapter] Excepción inesperada:");
            e.printStackTrace();
            throw new RuntimeException("Error inesperado durante la compilación", e);
        }
    }
}
