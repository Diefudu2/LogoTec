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
            case LABEL:
                out.append(i.a).append('\n');
                break;
            case GOTO:
                out.append("j ").append(((Label)i.a).getName()).append('\n');
                break;
            case IF_GOTO: {
                // convención: a==0 -> branch
                String ra = reg(i.a);
                out.append("beq ").append(ra).append(", $zero, ").append(((Label)i.b).getName()).append('\n');
                break;
            }
            case ASSIGN: {
                if (i.a instanceof Imm) {
                    Imm im = (Imm) i.a;
                    if (im.getValue() instanceof Integer) {
                        Integer v = (Integer) im.getValue();
                        out.append("li ").append(reg(i.r)).append(", ").append(v).append('\n');
                    } else {
                        out.append("# assign imm ").append(im).append(" -> ").append(reg(i.r)).append('\n');
                    }
                } else {
                    out.append("move ").append(reg(i.r)).append(", ").append(reg(i.a)).append('\n');
                }
                break;
            }
            case ADD:
                emitBin("add", i);
                break;
            case SUB:
                emitBin("sub", i);
                break;
            case MUL:
                emitBin("mul", i);
                break;
            case DIV:
                emitBin("div", i);
                break;
            case NEG:
                out.append("neg ").append(reg(i.r)).append(", ").append(reg(i.a)).append('\n');
                break;
            case AND:
                emitBin("and", i);
                break;
            case OR:
                emitBin("or", i);
                break;
            case NOT: {
                String ra = reg(i.a);
                String rr = reg(i.r);
                out.append("seq ").append(rr).append(", ").append(ra).append(", $zero\n"); // rr = (a==0)
                out.append("andi ").append(rr).append(", ").append(rr).append(", 1\n");
                break;
            }
            case CMPEQ:
                emitCmp("seq", i);
                break;
            case CMPNE:
                emitCmp("sne", i);
                break;
            case CMPLT:
                emitCmp("slt", i);
                break;
            case CMPLE:
                emitCmp("sle", i);
                break;
            case CMPGT:
                emitCmp("sgt", i);
                break;
            case CMPGE:
                emitCmp("sge", i);
                break;
            case PARAM:
                out.append("# PARAM ").append(opStr(i.a)).append('\n');
                break;
            case CALL:
                out.append("# CALL ").append(((Imm)i.a).getValue()).append(" nargs=").append(((Imm)i.b).getValue()).append('\n');
                break;
            case RET:
                out.append("jr $ra\n");
                break;
            case NOP:
                out.append("# NOP ").append(i.comment != null ? i.comment : "").append('\n');
                break;
            case COMMENT:
                out.append("# ").append(i.comment != null ? i.comment : "").append('\n');
                break;
            default:
                out.append("# op ").append(i.op).append(" no soportado\n");
                break;
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
        if (o instanceof Temp) {
            Temp t = (Temp) o;
            return regFor(t.getName());
        }
        if (o instanceof Var) {
            Var v = (Var) o;
            // cargar var de memoria a un temporal y devolver el reg
            String rt = regFor("_tmp_" + v.getName());
            out.append("lw ").append(rt).append(", ").append(v.getName()).append('\n');
            return rt;
        }
        if (o instanceof Imm) {
            Imm im = (Imm) o;
            String rt = regFor("_imm");
            if (im.getValue() instanceof Integer) {
                Integer v = (Integer) im.getValue();
                out.append("li ").append(rt).append(", ").append(v).append('\n');
            } else {
                out.append("# imm ").append(im.getValue()).append(" -> ").append(rt).append('\n');
            }
            return rt;
        }
        if (o instanceof Label) {
            Label l = (Label) o;
            return l.getName();
        }
        return "$zero";
    }

    private String regFor(String key) {
        return tempReg.computeIfAbsent(key, k -> pool.isEmpty() ? "$t9" : pool.removeFirst());
    }

    private String opStr(Operand o) {
        if (o instanceof Imm) {
            Imm im = (Imm) o;
            return String.valueOf(im.getValue());
        }
        if (o instanceof Temp) {
            Temp t = (Temp) o;
            return t.getName();
        }
        if (o instanceof Var) {
            Var v = (Var) o;
            return v.getName();
        }
        return "";
    }
}
