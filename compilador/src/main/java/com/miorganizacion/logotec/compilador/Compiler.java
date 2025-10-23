package com.miorganizacion.logotec.compilador;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.compilador.opt.*;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.backend.*;
import com.miorganizacion.logotec.compilador.antlr.*; // Ajustar al paquete real generado por ANTLR

public class Compiler {

    public void compile(Path sourceFile, Path outAsm) throws Exception {
        String src = Files.readString(sourceFile);

        // Parse
        LogoTecLexer lexer = new LogoTecLexer(CharStreams.fromString(src));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogoTecParser parser = new LogoTecParser(tokens);
        LogoTecParser.ProgramContext tree = parser.program();

        ProgramNode program = tree.node;

        // Optimiza AST
        AstOptimizer opt = new AstOptimizer();
        ProgramNode optimized = opt.optimize(program);

        // IR
        IRGenerator irgen = new IRGenerator();
        IRGenerator.Result ir = irgen.generate(optimized);

        // Ensamblador
        MipsGenerator mips = new MipsGenerator();
        String asm = mips.generate(ir.tac.code(), ir.vars);

        Files.createDirectories(outAsm.getParent());
        Files.writeString(outAsm, asm);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("uso: Compiler <input.logo> <out.s>");
            System.exit(1);
        }
        new Compiler().compile(Paths.get(args[0]), Paths.get(args[1]));
    }
}
