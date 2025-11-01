package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.ast.*;
import java.util.*;

/**
 * Generador de código IR desde AST.
 * Alternativa a ASTtoIRTranslator para compatibilidad.
 */
public class IRGenerator {
    
    private final List<ThreeAddressInstruction> instructions;
    private final TempGenerator tempGen;
    private final LabelGenerator labelGen;
    
    public IRGenerator() {
        this.instructions = new ArrayList<>();
        this.tempGen = new TempGenerator();
        this.labelGen = new LabelGenerator();
    }
    
    /**
     * Genera IR desde un ProgramNode.
     */
    public List<ThreeAddressInstruction> generate(ProgramNode program) {
        instructions.clear();
        tempGen.reset();
        labelGen.reset();
        
        // Generar código para procedimientos
        for (ProcDeclNode proc : program.getDecls()) {
            generateProcedure(proc);
        }
        
        // Etiqueta main
        instructions.add(ThreeAddressInstruction.label("main"));
        
        // Generar código para el cuerpo principal
        for (StmtNode stmt : program.getMainBody()) {
            generateStmt(stmt);
        }
        
        return new ArrayList<>(instructions);
    }
    
    private void generateProcedure(ProcDeclNode proc) {
        String procLabel = "proc_" + proc.getName();
        
        instructions.add(ThreeAddressInstruction.label(procLabel));
        instructions.add(ThreeAddressInstruction.comment("Procedimiento: " + proc.getName()));
        
        for (StmtNode stmt : proc.getBody()) {
            generateStmt(stmt);
        }
        
        instructions.add(new ThreeAddressInstruction(IROpcode.RETURN));
    }
    
    private void generateStmt(StmtNode stmt) {
        if (stmt == null) return;
        
        if (stmt instanceof VarDeclNode) {
            VarDeclNode varDecl = (VarDeclNode) stmt;
            instructions.add(ThreeAddressInstruction.comment("haz " + varDecl.getName()));
            Operand value = generateExpr(varDecl.getValue());
            instructions.add(new ThreeAddressInstruction(IROpcode.STORE, 
                Operand.variable(varDecl.getName()), value));
        } else if (stmt instanceof ForwardNode) {
            ForwardNode fwd = (ForwardNode) stmt;
            instructions.add(ThreeAddressInstruction.comment("avanza"));
            Operand dist = generateExpr(fwd.getExpr());
            instructions.add(new ThreeAddressInstruction(IROpcode.FORWARD, dist));
        } else if (stmt instanceof TurnRightNode) {
            TurnRightNode tr = (TurnRightNode) stmt;
            instructions.add(ThreeAddressInstruction.comment("giraderecha"));
            Operand angle = generateExpr(tr.getExpr());
            instructions.add(new ThreeAddressInstruction(IROpcode.TURN_RIGHT, angle));
        } else if (stmt instanceof TurnLeftNode) {
            TurnLeftNode tl = (TurnLeftNode) stmt;
            instructions.add(ThreeAddressInstruction.comment("giraizquierda"));
            Operand angle = generateExpr(tl.getExpr());
            instructions.add(new ThreeAddressInstruction(IROpcode.TURN_LEFT, angle));
        } else if (stmt instanceof PenUpNode) {
            instructions.add(new ThreeAddressInstruction(IROpcode.PEN_UP));
        } else if (stmt instanceof PenDownNode) {
            instructions.add(new ThreeAddressInstruction(IROpcode.PEN_DOWN));
        } else if (stmt instanceof CenterNode) {
            instructions.add(new ThreeAddressInstruction(IROpcode.CENTER));
        } else if (stmt instanceof RepeatNode) {
            generateRepeat((RepeatNode) stmt);
        } else {
            instructions.add(ThreeAddressInstruction.comment("Stmt no soportado: " + stmt.getClass().getSimpleName()));
        }
    }
    
    private void generateRepeat(RepeatNode node) {
        instructions.add(ThreeAddressInstruction.comment("Repite"));
        
        String labelStart = labelGen.next("loop_start");
        String labelEnd = labelGen.next("loop_end");
        
        Operand limitOp = generateExpr(node.getTimes());
        Operand limitTemp = tempGen.nextOperand();
        instructions.add(new ThreeAddressInstruction(IROpcode.MOVE, limitTemp, limitOp));
        
        Operand counterOp = tempGen.nextOperand();
        instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, counterOp, Operand.constant(0)));
        
        instructions.add(ThreeAddressInstruction.label(labelStart));
        
        Operand condOp = tempGen.nextOperand();
        instructions.add(new ThreeAddressInstruction(IROpcode.LT, condOp, counterOp, limitTemp));
        
        instructions.add(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelEnd), condOp));
        
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        Operand newCounterOp = tempGen.nextOperand();
        instructions.add(new ThreeAddressInstruction(IROpcode.ADD, newCounterOp, counterOp, Operand.constant(1)));
        instructions.add(new ThreeAddressInstruction(IROpcode.MOVE, counterOp, newCounterOp));
        
        instructions.add(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelStart)));
        
        instructions.add(ThreeAddressInstruction.label(labelEnd));
    }
    
    private Operand generateExpr(ExprNode expr) {
        if (expr instanceof ConstNode) {
            Object val = ((ConstNode) expr).getValue();
            Operand temp = tempGen.nextOperand();
            if (val instanceof Number) {
                instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, 
                    Operand.constant(((Number) val).doubleValue())));
            } else {
                instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, 
                    Operand.constant(0)));
            }
            return temp;
        }
        
        if (expr instanceof VarRefNode) {
            String varName = ((VarRefNode) expr).getName();
            Operand temp = tempGen.nextOperand();
            instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_VAR, temp, 
                Operand.variable(varName)));
            return temp;
        }
        
        if (expr instanceof AdditionNode) {
            AdditionNode add = (AdditionNode) expr;
            Operand left = generateExpr(add.getLeft());
            Operand right = generateExpr(add.getRight());
            Operand result = tempGen.nextOperand();
            instructions.add(new ThreeAddressInstruction(IROpcode.ADD, result, left, right));
            return result;
        }
        
        if (expr instanceof SubtractionNode) {
            SubtractionNode sub = (SubtractionNode) expr;
            Operand left = generateExpr(sub.getLeft());
            Operand right = generateExpr(sub.getRight());
            Operand result = tempGen.nextOperand();
            instructions.add(new ThreeAddressInstruction(IROpcode.SUB, result, left, right));
            return result;
        }
        
        if (expr instanceof MultiplicationNode) {
            MultiplicationNode mul = (MultiplicationNode) expr;
            Operand left = generateExpr(mul.getLeft());
            Operand right = generateExpr(mul.getRight());
            Operand result = tempGen.nextOperand();
            instructions.add(new ThreeAddressInstruction(IROpcode.MUL, result, left, right));
            return result;
        }
        
        if (expr instanceof DivisionNode) {
            DivisionNode div = (DivisionNode) expr;
            Operand left = generateExpr(div.getLeft());
            Operand right = generateExpr(div.getRight());
            Operand result = tempGen.nextOperand();
            instructions.add(new ThreeAddressInstruction(IROpcode.DIV, result, left, right));
            return result;
        }
        
        // Default
        Operand temp = tempGen.nextOperand();
        instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, Operand.constant(0)));
        return temp;
    }
}
