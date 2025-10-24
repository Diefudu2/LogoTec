package com.miorganizacion.logotec.compilador.ir;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * Generador de Representación Intermedia (IR) desde el AST de LogoTec.
 * Convierte el Árbol de Sintaxis Abstracta en código de tres direcciones.
 * 
 * FASE 2: Traducción AST → IR
 */
public class IRGenerator {

    private final IRBuilder builder;
    private final Set<String> declaredVars;
    
    /**
     * Resultado de la generación de IR.
     * Contiene las instrucciones generadas y las variables declaradas.
     */
    public static final class Result {
        public final List<ThreeAddressInstruction> instructions;
        public final Set<String> declaredVars;
        
        public Result(List<ThreeAddressInstruction> instructions, Set<String> vars) {
            this.instructions = instructions;
            this.declaredVars = vars;
        }
    }

    public IRGenerator() {
        this.builder = new IRBuilder();
        this.declaredVars = new LinkedHashSet<>();
    }

    /**
     * Genera código IR para un programa completo.
     * @param program Nodo raíz del AST
     * @return Resultado con instrucciones IR y variables declaradas
     */
    public Result generate(ProgramNode program) {
        builder.comment("========================================");
        builder.comment("Programa LogoTec - Código Intermedio");
        builder.comment("========================================");
        
        // Etiqueta de inicio del programa principal
        builder.label("main");
        
        // Generar código para el cuerpo principal
        for (StmtNode stmt : program.getBody()) {
            if (stmt != null) {
                generateStmt(stmt);
            }
        }
        
        builder.comment("Fin del programa");
        
        return new Result(builder.getInstructions(), declaredVars);
    }

    /**
     * Genera código IR para un statement (sentencia).
     */
    private void generateStmt(StmtNode stmt) {
        if (stmt == null) return;
        
        // ==================== DECLARACIONES Y ASIGNACIONES ====================
        
        if (stmt instanceof VarDeclNode) {
            VarDeclNode varDecl = (VarDeclNode) stmt;
            String varName = varDecl.getVariable();
            declaredVars.add(varName);
            
            builder.comment("haz " + varName + " = <expr>");
            Operand value = generateExpr(varDecl.getExpression());
            builder.store(varName, value);
            return;
        }
        
        if (stmt instanceof VarAssignNode) {
            VarAssignNode assign = (VarAssignNode) stmt;
            String varName = assign.getVariable();
            declaredVars.add(varName);
            
            builder.comment(varName + " = <expr>");
            Operand value = generateExpr(assign.getExpression());
            builder.store(varName, value);
            return;
        }
        
        // ==================== BLOQUES ====================
        
        if (stmt instanceof ExecBlockNode) {
            ExecBlockNode block = (ExecBlockNode) stmt;
            for (StmtNode s : block.getBody()) {
                generateStmt(s);
            }
            return;
        }
        
        // ==================== ESTRUCTURAS DE CONTROL ====================
        
        if (stmt instanceof IfNode) {
            generateIfStmt((IfNode) stmt);
            return;
        }
        
        if (stmt instanceof DoWhileNode) {
            generateDoWhileStmt((DoWhileNode) stmt);
            return;
        }
        
        if (stmt instanceof DoUntilNode) {
            generateDoUntilStmt((DoUntilNode) stmt);
            return;
        }
        
        if (stmt instanceof RepeatNode) {
            generateRepeatStmt((RepeatNode) stmt);
            return;
        }
        
        // ==================== COMANDOS DE TORTUGA ====================
        
        if (stmt instanceof ForwardNode) {
            ForwardNode fwd = (ForwardNode) stmt;
            builder.comment("avanza <expr>");
            Operand distance = generateExpr(fwd.getExpression());
            builder.forward(distance);
            return;
        }
        
        if (stmt instanceof BackwardNode) {
            BackwardNode bwd = (BackwardNode) stmt;
            builder.comment("retrocede <expr>");
            Operand distance = generateExpr(bwd.getExpression());
            builder.backward(distance);
            return;
        }
        
        if (stmt instanceof TurnRightNode) {
            TurnRightNode tr = (TurnRightNode) stmt;
            builder.comment("giraderecha <expr>");
            Operand degrees = generateExpr(tr.getExpression());
            builder.turnRight(degrees);
            return;
        }
        
        if (stmt instanceof TurnLeftNode) {
            TurnLeftNode tl = (TurnLeftNode) stmt;
            builder.comment("giraizquierda <expr>");
            Operand degrees = generateExpr(tl.getExpression());
            builder.turnLeft(degrees);
            return;
        }
        
        if (stmt instanceof PenUpNode) {
            builder.comment("subelapiz");
            builder.penUp();
            return;
        }
        
        if (stmt instanceof PenDownNode) {
            builder.comment("bajalapiz");
            builder.penDown();
            return;
        }
        
        if (stmt instanceof CenterNode) {
            builder.comment("centro");
            builder.center();
            return;
        }
        
        if (stmt instanceof SetPosNode) {
            SetPosNode sp = (SetPosNode) stmt;
            builder.comment("ponpos [<x>, <y>]");
            Operand x = generateExpr(sp.getX());
            Operand y = generateExpr(sp.getY());
            builder.setPos(x, y);
            return;
        }
        
        if (stmt instanceof SetColorNode) {
            SetColorNode sc = (SetColorNode) stmt;
            builder.comment("poncolorlapiz [<r>, <g>, <b>]");
            Operand r = generateExpr(sc.getR());
            Operand g = generateExpr(sc.getG());
            Operand b = generateExpr(sc.getB());
            builder.setColor(r, g, b);
            return;
        }
        
        if (stmt instanceof SetHeadingNode) {
            SetHeadingNode sh = (SetHeadingNode) stmt;
            builder.comment("ponrumbo <expr>");
            Operand heading = generateExpr(sh.getExpression());
            builder.add(new ThreeAddressInstruction(
                IROpcode.SET_HEADING, heading
            ));
            return;
        }
        
        if (stmt instanceof HideTurtleNode) {
            builder.comment("ocultatoruga");
            builder.add(new ThreeAddressInstruction(IROpcode.HIDE_TURTLE));
            return;
        }
        
        if (stmt instanceof ShowTurtleNode) {
            builder.comment("aparecetortuga");
            builder.add(new ThreeAddressInstruction(IROpcode.SHOW_TURTLE));
            return;
        }
        
        // ==================== PROCEDIMIENTOS ====================
        
        if (stmt instanceof ProcCallNode) {
            ProcCallNode call = (ProcCallNode) stmt;
            builder.comment("Llamada a procedimiento: " + call.getName());
            
            // Pasar parámetros (en orden inverso para stack)
            List<ExprNode> args = call.getArgs();
            for (int i = args.size() - 1; i >= 0; i--) {
                Operand arg = generateExpr(args.get(i));
                builder.add(new ThreeAddressInstruction(
                    IROpcode.PRINT, arg, "param " + i
                ));
            }
            
            // Llamar al procedimiento (simulado como salto)
            builder.jump(call.getName());
            return;
        }
        
        // ==================== OTROS ====================
        
        if (stmt instanceof IncNode) {
            IncNode inc = (IncNode) stmt;
            VarRefNode varRef = inc.getVar();
            String varName = varRef.getName();
            declaredVars.add(varName);
            
            builder.comment(varName + " += <expr>");
            Operand current = Operand.variable(varName);
            Operand delta = generateExpr(inc.getDelta());
            Operand result = builder.getTempGen().nextOperand();
            builder.add(result, current, delta);
            builder.store(varName, result);
            return;
        }
        
        // No reconocido - agregar NOP con comentario
        builder.add(new ThreeAddressInstruction(
            IROpcode.NOP, null, "stmt: " + stmt.getClass().getSimpleName()
        ));
    }

    private void genStmt(StmtNode s) {
        if (s instanceof VarDeclNode v) {
            vars.add(v.getName());
            Operand rhs = genExpr(v.getExpr());
            tac.emit(Op.ASSIGN, rhs, null, new Var(v.getName()));
            return;
        }
        if (s instanceof VarAssignNode v) {
            vars.add(v.getName());
            Operand rhs = genExpr(v.getExpr());
            tac.emit(Op.ASSIGN, rhs, null, new Var(v.getName()));
            return;
        }
        if (s instanceof ExecBlockNode b) {
            for (StmtNode i : b.getBody()) genStmt(i);
            return;
        }
        if (s instanceof IfNode i) {
            Operand c = genCond(i.getCond());
            String lElse = tac.newLabel(), lEnd = tac.newLabel();
            tac.emit(Op.IF_GOTO, c, new Label(lElse), null); // saltar a else si c == 0
            for (StmtNode t : i.getThenBody()) genStmt(t);
            tac.emit(Op.GOTO, new Label(lEnd), null, null);
            emit(Instruction.label(lElse));
            if (i.getElseBody()!=null) for (StmtNode e : i.getElseBody()) genStmt(e);
            emit(Instruction.label(lEnd));
            return;
        }
        if (s instanceof WhileNode w) {
            String lTop = tac.newLabel(), lExit = tac.newLabel();
            emit(Instruction.label(lTop));
            Operand c = genCond(w.getCond());
            tac.emit(Op.IF_GOTO, c, new Label(lExit), null);
            for (StmtNode b : w.getBody()) genStmt(b);
            tac.emit(Op.GOTO, new Label(lTop), null, null);
            emit(Instruction.label(lExit));
            return;
        }
        if (s instanceof DoWhileNode dw) {
            String lTop = tac.newLabel();
            emit(Instruction.label(lTop));
            for (StmtNode b : dw.getBody()) genStmt(b);
            Operand c = genCond(dw.getCond());
            // repetir si c != 0
            TAC.Temp one = tac.newTemp();
            tac.emit(Op.ASSIGN, new Imm(0), null, one);
            tac.emit(Op.CMPNE, c, one, one);
            tac.emit(Op.IF_GOTO, one, new Label(lTop), null);
            return;
        }
        if (s instanceof RepeatNode r) {
            Temp i = tac.newTemp();
            Temp limit = (Temp)genExpr(r.getTimes());
            tac.emit(Op.ASSIGN, new Imm(0), null, i);
            String lTop = tac.newLabel(), lExit = tac.newLabel();
            emit(Instruction.label(lTop));
            Temp cmp = tac.newTemp();
            tac.emit(Op.CMPLT, i, limit, cmp);
            tac.emit(Op.IF_GOTO, cmp, new Label(lExit), null);
            for (StmtNode b : r.getBody()) genStmt(b);
            Temp one = tac.newTemp();
            tac.emit(Op.ASSIGN, new Imm(1), null, one);
            Temp sum = tac.newTemp();
            tac.emit(Op.ADD, i, one, sum);
            tac.emit(Op.ASSIGN, sum, null, i);
            tac.emit(Op.GOTO, new Label(lTop), null, null);
            emit(Instruction.label(lExit));
            return;
        }
        if (s instanceof ProcCallNode pc) {
            // Pasa args como PARAM y CALL
            List<ExprNode> args = pc.getArgs();
            for (int k = args.size()-1; k>=0; k--) {
                tac.emit(Op.PARAM, genExpr(args.get(k)), null, null);
            }
            tac.emit(Op.CALL, new Imm(pc.getName()), new Imm(args.size()), null);
            return;
        }

        // Comandos turtle => llamadas a runtime
        if (s instanceof ForwardNode n) { emitRuntime("rt_forward", List.of(n.getExpr())); return; }
        if (s instanceof BackwardNode n) { emitRuntime("rt_backward", List.of(n.getExpr())); return; }
        if (s instanceof TurnRightNode n) { emitRuntime("rt_turn_right", List.of(n.getExpr())); return; }
        if (s instanceof TurnLeftNode n) { emitRuntime("rt_turn_left", List.of(n.getExpr())); return; }
        if (s instanceof SetPosNode n) { emitRuntime("rt_setpos", List.of(n.getX(), n.getY())); return; }
        if (s instanceof SetHeadingNode n) { emitRuntime("rt_setheading", List.of(n.getExpr())); return; }
        if (s instanceof SetXNode n) { emitRuntime("rt_setx", List.of(n.getExpr())); return; }
        if (s instanceof SetYNode n) { emitRuntime("rt_sety", List.of(n.getExpr())); return; }
        if (s instanceof WaitNode n) { emitRuntime("rt_wait", List.of(n.getExpr())); return; }
        if (s instanceof IncNode n) {
            vars.add(n.getVar().getName());
            Temp sum = tac.newTemp();
            tac.emit(Op.ADD, new Var(n.getVar().getName()), genExpr(n.getDelta()), sum);
            tac.emit(Op.ASSIGN, sum, null, new Var(n.getVar().getName()));
            return;
        }

        tac.emit(Op.NOP, null, null, null, s.getClass().getSimpleName());
    }

    private void emitRuntime(String name, List<ExprNode> args) {
        for (int k = args.size()-1; k>=0; k--) tac.emit(Op.PARAM, genExpr(args.get(k)), null, null);
        tac.emit(Op.CALL, new Imm(name), new Imm(args.size()), null);
    }

    private Operand genCond(ExprNode e) {
        Operand v = genExpr(e);
        // Se asume 0 = false, !=0 = true
        return v;
    }

    private Operand genExpr(ExprNode e) {
        if (e instanceof ConstNode c) {
            Object v = c.getValue();
            if (v instanceof Boolean b) return new Imm(b ? 1 : 0);
            return new Imm(v);
        }
        if (e instanceof VarRefNode v) {
            vars.add(v.getName());
            return new Var(v.getName());
        }
        if (e instanceof AdditionNode n) return bin(n, Op.ADD);
        if (e instanceof SubtractionNode n) return bin(n, Op.SUB);
        if (e instanceof MultiplicationNode n) return bin(n, Op.MUL);
        if (e instanceof DivisionNode n) return bin(n, Op.DIV);
        if (e instanceof ExponentiationNode n) {
            // pow(a,b) naive: call runtime
            Temp tA = (Temp)genExpr(n.getLeft()); Temp tB = (Temp)genExpr(n.getRight());
            tac.emit(Op.PARAM, tB, null, null);
            tac.emit(Op.PARAM, tA, null, null);
            Temp dst = tac.newTemp();
            tac.emit(Op.CALL, new Imm("rt_pow"), new Imm(2), dst);
            return dst;
        }
        if (e instanceof LogicalAndNode n) return bin(n, Op.AND);
        if (e instanceof LogicalOrNode n) return bin(n, Op.OR);
        if (e instanceof LogicalNotNode n) {
            Operand a = genExpr(n.getExpr());
            Temp t = tac.newTemp();
            tac.emit(Op.NOT, a, null, t);
            return t;
        }
        if (e instanceof EqualsNode n) return bin(n, Op.CMPEQ);
        if (e instanceof NotEqualsNode n) return bin(n, Op.CMPNE);
        if (e instanceof GreaterThanNode n) return bin(n, Op.CMPGT);
        if (e instanceof LessThanNode n) return bin(n, Op.CMPLT);
        if (e instanceof GreaterEqualNode n) return bin(n, Op.CMPGE);
        if (e instanceof LessEqualNode n) return bin(n, Op.CMPLE);

        if (e instanceof ProductNode n) return n.getRest().stream().reduce(genExpr(n.getFirst()),
            (acc, x) -> {
                Temp t = tac.newTemp();
                tac.emit(Op.MUL, acc, genExpr(x), t);
                return t;
            }, (a,b)->b);

        if (e instanceof SumNode n) return n.getRest().stream().reduce(genExpr(n.getFirst()),
            (acc, x) -> {
                Temp t = tac.newTemp();
                tac.emit(Op.ADD, acc, genExpr(x), t);
                return t;
            }, (a,b)->b);

        if (e instanceof DifferenceNode n) {
            Iterator<ExprNode> it = n.getRest().iterator();
            Operand acc = genExpr(n.getFirst());
            while (it.hasNext()) {
                Temp t = tac.newTemp();
                tac.emit(Op.SUB, acc, genExpr(it.next()), t);
                acc = t;
            }
            return acc;
        }

        Temp t = tac.newTemp();
        tac.emit(Op.NOP, null, null, t, "expr:" + e.getClass().getSimpleName());
        return t;
    }

    private Operand bin(BinaryNode n, Op op) {
        Operand a = genExpr(n.getLeft());
        Operand b = genExpr(n.getRight());
        Temp t = tac.newTemp();
        tac.emit(op, a, b, t);
        return t;
    }

    private void emit(Instruction i){ tac.emit(i); }
}
