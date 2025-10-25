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
        
        if (stmt instanceof WhileNode) {
            generateWhileStmt((WhileNode) stmt);
            return;
        }

        if (stmt instanceof DoWhileNode) {
            generateDoWhileStmt((DoWhileNode) stmt);
            return;
        }
        
        if (stmt instanceof UntilNode) {
            generateUntilStmt((UntilNode) stmt);
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
            Operand distance = generateExpr(fwd.getExpr());
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
            Operand degrees = generateExpr(tr.getExpr());
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
            builder.comment("ponpos [<x>, <y>]);
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
                    IROpcode.PARAM, arg, "param " + i
                ));
            }
            
            // Llamar al procedimiento (simulado como salto)
            builder.call(call.getName());
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
            IROpcode.NOP, null, "stmt no reconocido: " + stmt.getClass().getSimpleName()
        ));
    }

    private void generateIfStmt(IfNode stmt) {
        builder.comment("si (<cond>) { ... } else { ... }");
        String elseLabel = builder.getTempGen().newLabel("if_else");
        String endLabel = builder.getTempGen().newLabel("if_end");

        Operand condition = generateExpr(stmt.getCond());
        builder.jumpIfFalse(condition, elseLabel);

        // Bloque 'then'
        for (StmtNode s : stmt.getThenBody()) {
            generateStmt(s);
        }
        builder.jump(endLabel);

        // Bloque 'else'
        builder.label(elseLabel);
        if (stmt.getElseBody() != null && !stmt.getElseBody().isEmpty()) {
            for (StmtNode s : stmt.getElseBody()) {
                generateStmt(s);
            }
        }

        builder.label(endLabel);
    }

    private void generateWhileStmt(WhileNode stmt) {
        builder.comment("mientras (<cond>) { ... }");
        String loopStartLabel = builder.getTempGen().newLabel("while_start");
        String loopEndLabel = builder.getTempGen().newLabel("while_end");

        builder.label(loopStartLabel);

        Operand condition = generateExpr(stmt.getCond());
        builder.jumpIfFalse(condition, loopEndLabel);

        for (StmtNode s : stmt.getBody()) {
            generateStmt(s);
        }

        builder.jump(loopStartLabel);
        builder.label(loopEndLabel);
    }

    private void generateDoWhileStmt(DoWhileNode stmt) {
        builder.comment("haz { ... } mientras (<cond>)");
        String loopStartLabel = builder.getTempGen().newLabel("do_while_start");

        builder.label(loopStartLabel);

        for (StmtNode s : stmt.getBody()) {
            generateStmt(s);
        }

        Operand condition = generateExpr(stmt.getCond());
        builder.jumpIfTrue(condition, loopStartLabel);
    }

    private void generateUntilStmt(UntilNode stmt) {
        builder.comment("hasta (<cond>) { ... }");
        String loopStartLabel = builder.getTempGen().newLabel("until_start");
        String loopEndLabel = builder.getTempGen().newLabel("until_end");

        builder.label(loopStartLabel);

        Operand condition = generateExpr(stmt.getCond());
        builder.jumpIfTrue(condition, loopEndLabel);

        for (StmtNode s : stmt.getBody()) {
            generateStmt(s);
        }

        builder.jump(loopStartLabel);
        builder.label(loopEndLabel);
    }

    private void generateDoUntilStmt(DoUntilNode stmt) {
        builder.comment("haz { ... } hasta (<cond>)");
        String loopStartLabel = builder.getTempGen().newLabel("do_until_start");

        builder.label(loopStartLabel);

        for (StmtNode s : stmt.getBody()) {
            generateStmt(s);
        }

        Operand condition = generateExpr(stmt.getCond());
        builder.jumpIfFalse(condition, loopStartLabel);
    }

    private void generateRepeatStmt(RepeatNode stmt) {
        builder.comment("repite <n> { ... }");
        String loopStartLabel = builder.getTempGen().newLabel("repeat_start");
        String loopEndLabel = builder.getTempGen().newLabel("repeat_end");

        // Inicializar contador
        String counterVar = builder.getTempGen().newVariable("repeat_counter");
        declaredVars.add(counterVar);
        builder.store(counterVar, Operand.literal(0));

        // Obtener el límite
        Operand limit = generateExpr(stmt.getTimes());

        builder.label(loopStartLabel);

        // Condición: if (counter >= limit) goto end
        Operand counter = Operand.variable(counterVar);
        Operand condition = builder.getTempGen().nextOperand();
        builder.compare(IROpcode.CMP_GE, condition, counter, limit);
        builder.jumpIfTrue(condition, loopEndLabel);

        // Cuerpo del bucle
        for (StmtNode s : stmt.getBody()) {
            generateStmt(s);
        }

        // Incrementar contador: counter = counter + 1
        Operand newCounterVal = builder.getTempGen().nextOperand();
        builder.add(newCounterVal, counter, Operand.literal(1));
        builder.store(counterVar, newCounterVal);

        builder.jump(loopStartLabel);
        builder.label(loopEndLabel);
    }

    private Operand generateExpr(ExprNode expr) {
        if (expr == null) {
            return Operand.literal(0); // O un valor nulo si se soporta
        }

        if (expr instanceof ConstNode) {
            Object value = ((ConstNode) expr).getValue();
            if (value instanceof Boolean) {
                return Operand.literal((Boolean) value ? 1 : 0);
            }
            return Operand.literal(value);
        }

        if (expr instanceof VarRefNode) {
            return Operand.variable(((VarRefNode) expr).getName());
        }

        // Operaciones binarias
        if (expr instanceof AdditionNode n) return generateBinaryExpr(IROpcode.ADD, n.getLeft(), n.getRight());
        if (expr instanceof SubtractionNode n) return generateBinaryExpr(IROpcode.SUB, n.getLeft(), n.getRight());
        if (expr instanceof MultiplicationNode n) return generateBinaryExpr(IROpcode.MUL, n.getLeft(), n.getRight());
        if (expr instanceof DivisionNode n) return generateBinaryExpr(IROpcode.DIV, n.getLeft(), n.getRight());
        if (expr instanceof ExponentiationNode n) return generateBinaryExpr(IROpcode.POW, n.getLeft(), n.getRight());

        // Lógica y relacional
        if (expr instanceof LogicalAndNode n) return generateBinaryExpr(IROpcode.AND, n.getLeft(), n.getRight());
        if (expr instanceof LogicalOrNode n) return generateBinaryExpr(IROpcode.OR, n.getLeft(), n.getRight());
        if (expr instanceof EqualsNode n) return generateBinaryExpr(IROpcode.CMP_EQ, n.getLeft(), n.getRight());
        if (expr instanceof NotEqualsNode n) return generateBinaryExpr(IROpcode.CMP_NE, n.getLeft(), n.getRight());
        if (expr instanceof GreaterThanNode n) return generateBinaryExpr(IROpcode.CMP_GT, n.getLeft(), n.getRight());
        if (expr instanceof LessThanNode n) return generateBinaryExpr(IROpcode.CMP_LT, n.getLeft(), n.getRight());
        if (expr instanceof GreaterEqualNode n) return generateBinaryExpr(IROpcode.CMP_GE, n.getLeft(), n.getRight());
        if (expr instanceof LessEqualNode n) return generateBinaryExpr(IROpcode.CMP_LE, n.getLeft(), n.getRight());

        // Unario
        if (expr instanceof LogicalNotNode n) {
            Operand operand = generateExpr(n.getExpr());
            Operand result = builder.getTempGen().nextOperand();
            builder.add(new ThreeAddressInstruction(IROpcode.NOT, result, operand));
            return result;
        }

        // Funciones con múltiples argumentos
        if (expr instanceof ProductNode n) {
            Operand acc = generateExpr(n.getFirst());
            for (ExprNode e : n.getRest()) {
                Operand next = generateExpr(e);
                Operand tempResult = builder.getTempGen().nextOperand();
                builder.add(new ThreeAddressInstruction(IROpcode.MUL, tempResult, acc, next));
                acc = tempResult;
            }
            return acc;
        }
        if (expr instanceof SumNode n) {
            Operand acc = generateExpr(n.getFirst());
            for (ExprNode e : n.getRest()) {
                Operand next = generateExpr(e);
                Operand tempResult = builder.getTempGen().nextOperand();
                builder.add(new ThreeAddressInstruction(IROpcode.ADD, tempResult, acc, next));
                acc = tempResult;
            }
            return acc;
        }
        if (expr instanceof DifferenceNode n) {
            Operand acc = generateExpr(n.getFirst());
            for (ExprNode e : n.getRest()) {
                Operand next = generateExpr(e);
                Operand tempResult = builder.getTempGen().nextOperand();
                builder.add(new ThreeAddressInstruction(IROpcode.SUB, tempResult, acc, next));
                acc = tempResult;
            }
            return acc;
        }

        Operand temp = builder.getTempGen().nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.NOP, temp, "expr no reconocido: " + expr.getClass().getSimpleName()));
        return temp;
    }

    private Operand generateBinaryExpr(IROpcode op, ExprNode left, ExprNode right) {
        Operand leftOp = generateExpr(left);
        Operand rightOp = generateExpr(right);
        Operand result = builder.getTempGen().nextOperand();
        builder.add(new ThreeAddressInstruction(op, result, leftOp, rightOp));
        return result;
    }
}
