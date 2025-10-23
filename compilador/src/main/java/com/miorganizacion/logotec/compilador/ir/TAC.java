package com.miorganizacion.logotec.compilador.ir;

import java.util.*;

public class TAC {
    public enum Op {
        LABEL, GOTO, IF_GOTO,
        ASSIGN, ADD, SUB, MUL, DIV, NEG,
        AND, OR, NOT,
        CMPEQ, CMPNE, CMPLT, CMPLE, CMPGT, CMPGE,
        PARAM, CALL, RET,
        NOP, COMMENT
    }

    public static abstract class Operand { }
    public static final class Temp extends Operand {
        private final String name;
        public Temp(String name){this.name=name;}
        public String getName(){return name;}
        @Override public String toString(){return name;}
    }
    public static final class Imm extends Operand {
        private final Object value;
        public Imm(Object v){this.value=v;}
        public Object getValue(){return value;}
        @Override public String toString(){return String.valueOf(value);}
    }
    public static final class Var extends Operand {
        private final String name;
        public Var(String name){this.name=name;}
        public String getName(){return name;}
        @Override public String toString(){return name;}
    }
    public static final class Label extends Operand {
        private final String name;
        public Label(String name){this.name=name;}
        public String getName(){return name;}
        @Override public String toString(){return name + ":";}
    }

    public static final class Instruction {
        public final Op op;
        public final Operand a, b, r;
        public final String comment;
        public Instruction(Op op, Operand a, Operand b, Operand r){this(op,a,b,r,null);}
        public Instruction(Op op, Operand a, Operand b, Operand r, String c){this.op=op;this.a=a;this.b=b;this.r=r;this.comment=c;}
        public static Instruction label(String name){ return new Instruction(Op.LABEL, new Label(name), null, null); }
        @Override public String toString(){
            String s = op + " " + (a!=null?a:"") + (b!=null?(","+b):"") + (r!=null?(" -> "+r):"");
            return comment==null? s : (s + " ; " + comment);
        }
    }

    private int tempId=0, labelId=0;
    private final List<Instruction> code = new ArrayList<>();

    public Temp newTemp(){ return new Temp("t"+(tempId++)); }
    public String newLabel(){ return "L"+(labelId++); }
    public List<Instruction> code(){ return code; }
    public void emit(Instruction i){ code.add(i); }
    public void emit(Op op, Operand a, Operand b, Operand r){ code.add(new Instruction(op,a,b,r)); }
    public void emit(Op op, Operand a, Operand b, Operand r, String c){ code.add(new Instruction(op,a,b,r,c)); }
}
