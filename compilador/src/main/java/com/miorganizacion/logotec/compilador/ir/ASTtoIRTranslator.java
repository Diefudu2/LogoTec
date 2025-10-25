package com.miorganizacion.logotec.compilador.ir;

import java.util.*;
import java.lang.reflect.Field;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * Generador de Representación Intermedia (IR) desde el AST de LogoTec.
 * Convierte el Árbol de Sintaxis Abstracta en código de tres direcciones.
 * 
 * FASE 2: Traducción AST → IR (COMPLETADO + PROCEDIMIENTOS)
 */
public class ASTtoIRTranslator {

    private final IRBuilder builder;
    private final Set<String> declaredVars;
    private final Map<String, ProcedureInfo> procedures;  // Tabla de procedimientos
    private final Set<String> currentProcParams;           // Parámetros del procedimiento actual
    private boolean insideProcedure;                       // Flag: estamos dentro de un procedimiento
    
    /**
     * Resultado de la generación de IR.
     */
    public static final class Result {
        public final List<ThreeAddressInstruction> instructions;
        public final Set<String> declaredVars;
        
        public Result(List<ThreeAddressInstruction> instructions, Set<String> vars) {
            this.instructions = instructions;
            this.declaredVars = vars;
        }
    }

    public ASTtoIRTranslator() {
        this.builder = new IRBuilder();
        this.declaredVars = new LinkedHashSet<>();
        this.procedures = new HashMap<>();
        this.currentProcParams = new HashSet<>();
        this.insideProcedure = false;
    }

    /**
     * Genera código IR para un programa completo.
     */
    public Result generate(ProgramNode program) {
        builder.comment("========================================");
        builder.comment("Programa LogoTec - Código Intermedio");
        builder.comment("========================================");
        
        // Obtener procedimientos y cuerpo principal
        List<ProcDeclNode> procs = getFieldList(program, "decls", ProcDeclNode.class);
        List<StmtNode> mainBody = program.getBody();
        
        // PASO 1: Primera pasada - Recopilar declaraciones de procedimientos
        for (ProcDeclNode proc : procs) {
            registerProcedure(proc);
        }
        
        // PASO 2: Generar código de procedimientos
        for (ProcDeclNode proc : procs) {
            generateProcedure(proc);
        }
        
        // PASO 3: Generar código del programa principal
        builder.comment("");
        builder.comment("========== MAIN PROGRAM ==========");
        builder.label("main");
        
        for (StmtNode stmt : mainBody) {
            if (stmt != null) {
                generateStmt(stmt);
            }
        }
        
        builder.comment("Fin del programa");
        
        return new Result(builder.getInstructions(), declaredVars);
    }

    /**
     * Genera código IR para un statement.
     */
    private void generateStmt(StmtNode stmt) {
        if (stmt == null) return;
        
        // Declaración de variable
        if (stmt instanceof VarDeclNode) {
            VarDeclNode node = (VarDeclNode) stmt;
            String name = getField(node, "name", String.class);
            ExprNode value = getField(node, "value", ExprNode.class);
            
            declaredVars.add(name);
            builder.comment("haz " + name + " = <expr>");
            Operand result = generateExpr(value);
            builder.store(name, result);
            return;
        }
        
        // Asignación de variable
        if (stmt instanceof VarAssignNode) {
            VarAssignNode node = (VarAssignNode) stmt;
            String name = getField(node, "name", String.class);
            ExprNode value = getField(node, "value", ExprNode.class);
            
            declaredVars.add(name);
            builder.comment(name + " = <expr>");
            Operand result = generateExpr(value);
            builder.store(name, result);
            return;
        }
        
        // Repite N [...]
        if (stmt instanceof RepeatNode) {
            RepeatNode node = (RepeatNode) stmt;
            ExprNode timesExpr = getField(node, "timesExpr", ExprNode.class);
            List<StmtNode> body = getFieldList(node, "body", StmtNode.class);
            
            builder.comment("Repite <n> [...]");
            
            // Contador del loop (FIX: generar temporal único por bucle)
            // Antes: Operand counter = Operand.temp("loop_counter");
            Operand counter = builder.getTempGen().nextOperand();
            builder.loadConst(counter, 0);
            
            // Evaluar límite
            Operand limit = generateExpr(timesExpr);
            
            // Etiquetas
            Operand loopStart = builder.getLabelGen().nextOperand("loop_start");
            Operand loopEnd = builder.getLabelGen().nextOperand("loop_end");
            
            // Inicio del loop
            builder.label(loopStart);
            
            // Condición: counter < limit
            Operand condition = builder.getTempGen().nextOperand();
            builder.lt(condition, counter, limit);
            builder.jumpIfFalse(loopEnd, condition);
            
            // Cuerpo del loop
            for (StmtNode s : body) {
                generateStmt(s);
            }
            
            // Incrementar contador
            Operand one = builder.getTempGen().nextOperand();
            builder.loadConst(one, 1);
            Operand newCounter = builder.getTempGen().nextOperand();
            builder.add(newCounter, counter, one);
            builder.move(counter, newCounter);
            
            // Volver al inicio
            builder.jump(loopStart);
            
            // Fin del loop
            builder.label(loopEnd);
            return;
        }
        
        // DoWhile [cuerpo] [condicion]
        if (stmt instanceof DoWhileNode) {
            DoWhileNode node = (DoWhileNode) stmt;
            List<StmtNode> body = getFieldList(node, "body", StmtNode.class);
            ExprNode condition = getField(node, "condition", ExprNode.class);
            
            builder.comment("HazMientras [...]");
            
            Operand loopStart = builder.getLabelGen().nextOperand("dowhile_start");
            
            builder.label(loopStart);
            
            // Ejecutar cuerpo
            for (StmtNode s : body) {
                generateStmt(s);
            }
            
            // Evaluar condición y saltar si es verdadera
            Operand cond = generateExpr(condition);
            builder.jumpIfTrue(loopStart, cond);
            
            return;
        }
        
        // DoUntil [cuerpo] [condicion]
        if (stmt instanceof DoUntilNode) {
            DoUntilNode node = (DoUntilNode) stmt;
            List<StmtNode> body = getFieldList(node, "body", StmtNode.class);
            ExprNode condition = getField(node, "condition", ExprNode.class);
            
            builder.comment("HazHasta [...]");
            
            Operand loopStart = builder.getLabelGen().nextOperand("dountil_start");
            
            builder.label(loopStart);
            
            // Ejecutar cuerpo
            for (StmtNode s : body) {
                generateStmt(s);
            }
            
            // Evaluar condición y saltar si es FALSA
            Operand cond = generateExpr(condition);
            builder.jumpIfFalse(loopStart, cond);
            
            return;
        }
        
        // If
        if (stmt instanceof IfNode) {
            IfNode node = (IfNode) stmt;
            ASTNode condition = getField(node, "condition", ASTNode.class);
            List<ASTNode> thenBody = getFieldList(node, "thenBody", ASTNode.class);
            List<ASTNode> elseBody = getFieldList(node, "elseBody", ASTNode.class);
            
            builder.comment("Si [condición] [entonces] [sino]");
            
            // Evaluar condición
            Operand cond = generateExpr((ExprNode) condition);
            
            // Etiquetas
            Operand elseBranch = builder.getLabelGen().nextOperand("else");
            Operand endIf = builder.getLabelGen().nextOperand("endif");
            
            // Saltar a else si falso
            builder.jumpIfFalse(elseBranch, cond);
            
            // Then branch
            for (ASTNode s : thenBody) {
                if (s instanceof StmtNode) {
                    generateStmt((StmtNode) s);
                }
            }
            builder.jump(endIf);
            
            // Else branch
            builder.label(elseBranch);
            if (elseBody != null) {
                for (ASTNode s : elseBody) {
                    if (s instanceof StmtNode) {
                        generateStmt((StmtNode) s);
                    }
                }
            }
            
            // Fin del if
            builder.label(endIf);
            return;
        }
        
        // Comandos de tortuga
        if (stmt instanceof ForwardNode) {
            ForwardNode node = (ForwardNode) stmt;
            builder.comment("avanza <expr>");
            Operand distance = generateExpr(node.getExpr());
            builder.forward(distance);
            return;
        }
        
        if (stmt instanceof BackwardNode) {
            BackwardNode node = (BackwardNode) stmt;
            ExprNode expr = getField(node, "distance", ExprNode.class);
            builder.comment("retrocede <expr>");
            Operand distance = generateExpr(expr);
            builder.backward(distance);
            return;
        }
        
        if (stmt instanceof TurnRightNode) {
            TurnRightNode node = (TurnRightNode) stmt;
            builder.comment("giraderecha <expr>");
            Operand degrees = generateExpr(node.getExpr());
            builder.turnRight(degrees);
            return;
        }
        
        if (stmt instanceof TurnLeftNode) {
            TurnLeftNode node = (TurnLeftNode) stmt;
            ExprNode expr = getField(node, "degrees", ExprNode.class);
            builder.comment("giraizquierda <expr>");
            Operand degrees = generateExpr(expr);
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
            SetPosNode node = (SetPosNode) stmt;
            ExprNode xExpr = getField(node, "x", ExprNode.class);
            ExprNode yExpr = getField(node, "y", ExprNode.class);
            builder.comment("ponpos [<x>, <y>]");
            Operand x = generateExpr(xExpr);
            Operand y = generateExpr(yExpr);
            builder.setPos(x, y);
            return;
        }
        
        if (stmt instanceof SetHeadingNode) {
            SetHeadingNode node = (SetHeadingNode) stmt;
            ExprNode expr = getField(node, "heading", ExprNode.class);
            builder.comment("ponrumbo <expr>");
            Operand heading = generateExpr(expr);
            builder.add(new ThreeAddressInstruction(IROpcode.SET_HEADING, heading));
            return;
        }
        
        if (stmt instanceof HideTurtleNode) {
            builder.comment("ocultatoruga");
            builder.add(new ThreeAddressInstruction(IROpcode.HIDE_TURTLE));
            return;
        }
        
        if (stmt instanceof ExecBlockNode) {
            ExecBlockNode node = (ExecBlockNode) stmt;
            List<StmtNode> body = getFieldList(node, "body", StmtNode.class);
            for (StmtNode s : body) {
                generateStmt(s);
            }
            return;
        }
        
        // Llamada a procedimiento
        if (stmt instanceof ProcCallNode) {
            ProcCallNode node = (ProcCallNode) stmt;
            String procName = getField(node, "name", String.class);
            List<ExprNode> args = getFieldList(node, "args", ExprNode.class);
            
            builder.comment("call " + procName + "(" + args.size() + " args)");
            
            // Evaluar argumentos y pasarlos como parámetros
            for (int i = 0; i < args.size(); i++) {
                Operand argValue = generateExpr(args.get(i));
                builder.param(i, argValue);
            }
            
            // Llamar al procedimiento
            builder.call(null, "proc_" + procName, args.size());
            return;
        }
        
        // No reconocido
        builder.add(new ThreeAddressInstruction(
            IROpcode.NOP, null, "stmt: " + stmt.getClass().getSimpleName()
        ));
    }

    /**
     * Genera código IR para una expresión.
     * @return Operando que contiene el resultado de la expresión
     */
    private Operand generateExpr(ExprNode expr) {
        if (expr == null) {
            Operand t = builder.getTempGen().nextOperand();
            builder.loadConst(t, 0);
            return t;
        }
        
        // Constante
        if (expr instanceof ConstNode) {
            ConstNode node = (ConstNode) expr;
            Object value = getField(node, "value", Object.class);
            
            Operand temp = builder.getTempGen().nextOperand();
            
            if (value instanceof Boolean) {
                builder.loadConst(temp, (Boolean) value ? 1 : 0);
            } else if (value instanceof Number) {
                builder.loadConst(temp, ((Number) value).doubleValue());
            } else {
                builder.loadConst(temp, 0);
            }
            
            return temp;
        }
        
        // Referencia a variable
        if (expr instanceof VarRefNode) {
            VarRefNode node = (VarRefNode) expr;
            String name = node.getName();
            declaredVars.add(name);
            
            Operand temp = builder.getTempGen().nextOperand();
            builder.loadVar(temp, name);
            return temp;
        }
        
        // Suma
        if (expr instanceof AdditionNode) {
            AdditionNode node = (AdditionNode) expr;
            ExprNode left = getField(node, "left", ExprNode.class);
            ExprNode right = getField(node, "right", ExprNode.class);
            
            Operand opLeft = generateExpr(left);
            Operand opRight = generateExpr(right);
            Operand result = builder.getTempGen().nextOperand();
            builder.add(result, opLeft, opRight);
            return result;
        }
        
        // Resta (DifferenceNode - lista)
        if (expr instanceof DifferenceNode) {
            DifferenceNode node = (DifferenceNode) expr;
            ExprNode first = getField(node, "first", ExprNode.class);
            List<ExprNode> rest = getFieldList(node, "rest", ExprNode.class);
            
            Operand result = generateExpr(first);
            
            for (ExprNode e : rest) {
                Operand right = generateExpr(e);
                Operand newResult = builder.getTempGen().nextOperand();
                builder.sub(newResult, result, right);
                result = newResult;
            }
            
            return result;
        }
        
        // Resta binaria
        if (expr instanceof SubtractionNode) {
            SubtractionNode node = (SubtractionNode) expr;
            ExprNode left = getField(node, "left", ExprNode.class);
            ExprNode right = getField(node, "right", ExprNode.class);
            
            Operand opLeft = generateExpr(left);
            Operand opRight = generateExpr(right);
            Operand result = builder.getTempGen().nextOperand();
            builder.sub(result, opLeft, opRight);
            return result;
        }
        
        // Multiplicación
        if (expr instanceof MultiplicationNode) {
            MultiplicationNode node = (MultiplicationNode) expr;
            ExprNode left = getField(node, "left", ExprNode.class);
            ExprNode right = getField(node, "right", ExprNode.class);
            
            Operand opLeft = generateExpr(left);
            Operand opRight = generateExpr(right);
            Operand result = builder.getTempGen().nextOperand();
            builder.mul(result, opLeft, opRight);
            return result;
        }
        
        // División
        if (expr instanceof DivisionNode) {
            DivisionNode node = (DivisionNode) expr;
            ExprNode left = getField(node, "left", ExprNode.class);
            ExprNode right = getField(node, "right", ExprNode.class);
            
            Operand opLeft = generateExpr(left);
            Operand opRight = generateExpr(right);
            Operand result = builder.getTempGen().nextOperand();
            builder.div(result, opLeft, opRight);
            return result;
        }
        
        // Potencia
        if (expr instanceof ExponentiationNode) {
            ExponentiationNode node = (ExponentiationNode) expr;
            ExprNode left = getField(node, "left", ExprNode.class);
            ExprNode right = getField(node, "right", ExprNode.class);
            
            Operand base = generateExpr(left);
            Operand exponent = generateExpr(right);
            Operand result = builder.getTempGen().nextOperand();
            builder.pow(result, base, exponent);
            return result;
        }
        
        // Comparaciones
        if (expr instanceof EqualsNode) {
            return generateComparison(expr, IROpcode.EQ);
        }
        if (expr instanceof NotEqualsNode) {
            return generateComparison(expr, IROpcode.NEQ);
        }
        if (expr instanceof LessThanNode) {
            return generateComparison(expr, IROpcode.LT);
        }
        if (expr instanceof GreaterThanNode) {
            return generateComparison(expr, IROpcode.GT);
        }
        if (expr instanceof LessEqualNode) {
            return generateComparison(expr, IROpcode.LTE);
        }
        if (expr instanceof GreaterEqualNode) {
            return generateComparison(expr, IROpcode.GTE);
        }
        
        // Lógicas
        if (expr instanceof LogicalAndNode) {
            return generateComparison(expr, IROpcode.AND);
        }
        if (expr instanceof LogicalOrNode) {
            return generateComparison(expr, IROpcode.OR);
        }
        if (expr instanceof LogicalNotNode) {
            LogicalNotNode node = (LogicalNotNode) expr;
            ExprNode operand = getField(node, "expr", ExprNode.class);
            Operand op = generateExpr(operand);
            Operand result = builder.getTempGen().nextOperand();
            builder.add(new ThreeAddressInstruction(IROpcode.NOT, result, op));
            return result;
        }
        
        // No reconocido - retornar temporal con 0
        Operand temp = builder.getTempGen().nextOperand();
        builder.loadConst(temp, 0);
        builder.comment("expr no reconocida: " + expr.getClass().getSimpleName());
        return temp;
    }

    /**
     * Helper para generar comparaciones binarias.
     */
    private Operand generateComparison(ExprNode expr, IROpcode opcode) {
        ExprNode left = getField(expr, "left", ExprNode.class);
        ExprNode right = getField(expr, "right", ExprNode.class);
        
        Operand opLeft = generateExpr(left);
        Operand opRight = generateExpr(right);
        Operand result = builder.getTempGen().nextOperand();
        
        builder.add(new ThreeAddressInstruction(opcode, result, opLeft, opRight));
        return result;
    }

    /**
     * Reflexión: Obtiene un campo privado de un objeto.
     */
    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName, Class<T> type) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reflexión: Obtiene una lista de un campo privado.
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> getFieldList(Object obj, String fieldName, Class<T> elementType) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (List<T>) field.get(obj);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    
    // ==================== MANEJO DE PROCEDIMIENTOS ====================
    
    /**
     * Registra un procedimiento en la tabla de procedimientos.
     * Primera pasada: solo recopilamos información.
     */
    private void registerProcedure(ProcDeclNode node) {
        String name = getField(node, "name", String.class);
        List<String> params = getFieldList(node, "params", String.class);
        
        String label = "proc_" + name;
        ProcedureInfo info = new ProcedureInfo(name, params, label);
        procedures.put(name, info);
        
        builder.comment("Procedure declared: " + name + "(" + String.join(", ", params) + ")");
    }
    
    /**
     * Genera código IR para un procedimiento completo.
     */
    private void generateProcedure(ProcDeclNode node) {
        String name = getField(node, "name", String.class);
        List<String> params = getFieldList(node, "params", String.class);
        List<StmtNode> body = getFieldList(node, "body", StmtNode.class);
        
        String label = "proc_" + name;
        ProcedureInfo info = procedures.get(name);
        
        builder.comment("");
        builder.comment("========== PROCEDURE: " + name + " ==========");
        builder.label(label);
        builder.procBegin(name);
        
        // Marcar que estamos dentro de un procedimiento
        insideProcedure = true;
        currentProcParams.clear();
        currentProcParams.addAll(params);
        
        // Obtener argumentos y almacenarlos en variables locales
        for (int i = 0; i < params.size(); i++) {
            String paramName = params.get(i);
            Operand temp = builder.getTempGen().nextOperand();
            builder.getArg(temp, i);
            builder.store(paramName, temp);
            builder.comment("  param " + paramName + " = arg[" + i + "]");
        }
        
        // Generar código del cuerpo
        builder.comment("  --- body ---");
        for (StmtNode stmt : body) {
            generateStmt(stmt);
        }
        
        // Retorno implícito si no hay return explícito
        builder.comment("  --- end body ---");
        builder.returnStmt(null);
        builder.procEnd(name);
        
        // Salir del contexto del procedimiento
        insideProcedure = false;
        currentProcParams.clear();
    }
}
