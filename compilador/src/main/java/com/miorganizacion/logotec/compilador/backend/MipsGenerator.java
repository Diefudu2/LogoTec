package com.miorganizacion.logotec.compilador.backend;

import java.util.*;
import com.miorganizacion.logotec.compilador.ir.TAC;
import com.miorganizacion.logotec.compilador.ir.TAC.*;

public class MipsGenerator {

    private final Map<String, String> tempReg = new HashMap<>();
    private final Deque<String> pool = new ArrayDeque<>(List.of("$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7","$t8","$t9"));
    private final StringBuilder out = new StringBuilder();

    public String generate(List<Instruction> code, Set<String> vars) {
        // Sección de datos: variables globales como words
        out.append(".data\n");
        for (String v : vars) out.append(v).append(": .word 0\n");

        out.append("\n.text\n.globl main\n");

        for (Instruction i : code) emit(i);
        return out.toString();
    }

    private void emit(Instruction i) {
        switch (i.op) {
            case LABEL -> out.append(i.a).append('\n');
            case GOTO -> out.append("j ").append(((Label)i.a).getName()).append('\n');
            case IF_GOTO -> { // convención: a==0 -> branch
                String ra = reg(i.a);
                out.append("beq ").append(ra).append(", $zero, ").append(((Label)i.b).getName()).append('\n');
            }
            case ASSIGN -> {
                if (i.a instanceof Imm im) {
                    if (im.getValue() instanceof Integer v) out.append("li ").append(reg(i.r)).append(", ").append(v).append('\n');
                    else out.append("# assign imm ").append(im).append(" -> ").append(reg(i.r)).append('\n');
                } else {
                    out.append("move ").append(reg(i.r)).append(", ").append(reg(i.a)).append('\n');
                }
            }
            case ADD -> emitBin("add", i);
            case SUB -> emitBin("sub", i);
            case MUL -> emitBin("mul", i);
            case DIV -> emitBin("div", i);
            case NEG -> out.append("neg ").append(reg(i.r)).append(", ").append(reg(i.a)).append('\n');
            case AND -> emitBin("and", i);
            case OR  -> emitBin("or", i);
            case NOT -> {
                String ra = reg(i.a), rr = reg(i.r);
                out.append("seq ").append(rr).append(", ").append(ra).append(", $zero\n"); // rr = (a==0)
                out.append("andi ").append(rr).append(", ").append(rr).append(", 1\n");
            }
            case CMPEQ -> emitCmp("seq", i);
            case CMPNE -> emitCmp("sne", i);
            case CMPLT -> emitCmp("slt", i);
            case CMPLE -> emitCmp("sle", i);
            case CMPGT -> emitCmp("sgt", i);
            case CMPGE -> emitCmp("sge", i);
            case PARAM -> out.append("# PARAM ").append(opStr(i.a)).append('\n');
            case CALL -> out.append("# CALL ").append(((Imm)i.a).getValue()).append(" nargs=").append(((Imm)i.b).getValue()).append('\n');
            case RET -> out.append("jr $ra\n");
            case NOP -> out.append("# NOP ").append(i.comment!=null?i.comment:"").append('\n');
            case COMMENT -> out.append("# ").append(i.comment!=null?i.comment:"").append('\n');
            default -> out.append("# op ").append(i.op).append(" no soportado\n");
        }
    }

    private void emitBin(String op, Instruction i) {
        out.append(op).append(" ").append(reg(i.r)).append(", ").append(reg(i.a)).append(", ").append(reg(i.b)).append('\n');
    }

    private void emitCmp(String op, Instruction i) {
        out.append(op).append(" ").append(reg(i.r)).append(", ").append(reg(i.a)).append(", ").append(reg(i.b)).append('\n');
        out.append("andi ").append(reg(i.r)).append(", ").append(reg(i.r)).append(", 1\n");
    }

    private String reg(Operand o) {
        if (o instanceof Temp t) return regFor(t.getName());
        if (o instanceof Var v) {
            // cargar var de memoria a un temporal y devolver el reg
            String rt = regFor("_tmp_"+v.getName());
            out.append("lw ").append(rt).append(", ").append(v.getName()).append('\n');
            return rt;
        }
        if (o instanceof Imm im) {
            String rt = regFor("_imm");
            if (im.getValue() instanceof Integer v) out.append("li ").append(rt).append(", ").append(v).append('\n');
            else out.append("# imm ").append(im.getValue()).append(" -> ").append(rt).append('\n');
            return rt;
        }
        if (o instanceof Label l) return l.getName();
        return "$zero";
    }

    private String regFor(String key) {
        return tempReg.computeIfAbsent(key, k -> pool.isEmpty() ? "$t9" : pool.removeFirst());
    }

    private String opStr(Operand o) {
        if (o instanceof Imm im) return String.valueOf(im.getValue());
        if (o instanceof Temp t) return t.getName();
        if (o instanceof Var v) return v.getName();
        return "";
    }
}
