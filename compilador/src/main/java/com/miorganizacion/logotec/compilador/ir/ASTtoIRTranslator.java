package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.ast.*;
import java.util.*;

/**
 * Traductor de AST a Representación Intermedia (IR).
 * Genera código de tres direcciones desde el AST.
 */
public class ASTtoIRTranslator {
    
    private List<ThreeAddressInstruction> instructions;
    private TempGenerator tempGen;
    private LabelGenerator labelGen;
    private Map<String, ProcedureInfo> procedures;
    
    public ASTtoIRTranslator() {
        this.instructions = new ArrayList<>();
        this.tempGen = new TempGenerator();
        this.labelGen = new LabelGenerator();
        this.procedures = new HashMap<>();
    }
    
    /**
     * Genera IR desde un ProgramNode
     */
    public Result generate(ProgramNode program) {
        instructions.clear();
        tempGen.reset();
        labelGen.reset();
        procedures.clear();
        
        // Primero, registrar todos los procedimientos
        for (ProcDeclNode proc : program.getDecls()) {
            registerProcedure(proc);
        }
        
        // Generar código para procedimientos
        for (ProcDeclNode proc : program.getDecls()) {
            generateProcedure(proc);
        }
        
        // Etiqueta main
        emit(ThreeAddressInstruction.label("main"));
        
        // Generar código para el cuerpo principal
        for (StmtNode stmt : program.getMainBody()) {
            generateStmt(stmt);
        }
        
        // DEBUG: Imprimir IR generado
        System.out.println("\n=== IR GENERADO ===");
        int addr = 0;
        for (ThreeAddressInstruction instr : instructions) {
            System.out.printf("%4d: %s%n", addr++, instr);
        }
        System.out.println("===================\n");
        
        return new Result(new ArrayList<>(instructions), procedures);
    }
    
    private void registerProcedure(ProcDeclNode proc) {
        ProcedureInfo info = new ProcedureInfo(proc.getName(), proc.getParams().size());
        procedures.put(proc.getName(), info);
    }
    
    private void generateProcedure(ProcDeclNode proc) {
        String procLabel = "proc_" + proc.getName();
        
        // Etiqueta del procedimiento
        emit(ThreeAddressInstruction.label(procLabel));
        emit(ThreeAddressInstruction.comment("Procedimiento: " + proc.getName()));
        emit(new ThreeAddressInstruction(IROpcode.PROC_BEGIN, Operand.label(proc.getName())));
        
        // Obtener argumentos
        List<String> params = proc.getParams();
        for (int i = 0; i < params.size(); i++) {
            String paramName = params.get(i);
            Operand dest = Operand.variable(paramName);
            emit(new ThreeAddressInstruction(IROpcode.GET_ARG, dest, Operand.constant(i)));
        }
        
        // Generar cuerpo
        for (StmtNode stmt : proc.getBody()) {
            generateStmt(stmt);
        }
        
        // Retorno implícito
        emit(new ThreeAddressInstruction(IROpcode.RETURN));
        emit(new ThreeAddressInstruction(IROpcode.PROC_END, Operand.label(proc.getName())));
        emit(ThreeAddressInstruction.comment("Fin procedimiento: " + proc.getName()));
    }
    
    private void generateStmt(StmtNode stmt) {
        if (stmt == null) return;
        
        if (stmt instanceof VarDeclNode) {
            generateVarDecl((VarDeclNode) stmt);
        } else if (stmt instanceof VarAssignNode) {
            generateVarAssign((VarAssignNode) stmt);
        } else if (stmt instanceof ForwardNode) {
            generateForward((ForwardNode) stmt);
        } else if (stmt instanceof BackwardNode) {
            generateBackward((BackwardNode) stmt);
        } else if (stmt instanceof TurnRightNode) {
            generateTurnRight((TurnRightNode) stmt);
        } else if (stmt instanceof TurnLeftNode) {
            generateTurnLeft((TurnLeftNode) stmt);
        } else if (stmt instanceof PenUpNode) {
            emit(new ThreeAddressInstruction(IROpcode.PEN_UP));
        } else if (stmt instanceof PenDownNode) {
            emit(new ThreeAddressInstruction(IROpcode.PEN_DOWN));
        } else if (stmt instanceof CenterNode) {
            emit(new ThreeAddressInstruction(IROpcode.CENTER));
        } else if (stmt instanceof RepeatNode) {
            generateRepeat((RepeatNode) stmt);
        } else if (stmt instanceof IfNode) {
            generateIf((IfNode) stmt);
        } else if (stmt instanceof WhileNode) {
            generateWhile((WhileNode) stmt);
        } else if (stmt instanceof UntilNode) {
            generateUntil((UntilNode) stmt);
        } else if (stmt instanceof DoWhileNode) {
            generateDoWhile((DoWhileNode) stmt);
        } else if (stmt instanceof DoUntilNode) {
            generateDoUntil((DoUntilNode) stmt);
        } else if (stmt instanceof ProcCallNode) {
            generateProcCall((ProcCallNode) stmt);
        } else if (stmt instanceof ExecBlockNode) {
            for (StmtNode s : ((ExecBlockNode) stmt).getBody()) {
                generateStmt(s);
            }
        } else if (stmt instanceof SetPosNode) {
            generateSetPos((SetPosNode) stmt);
        } else if (stmt instanceof SetHeadingNode) {
            generateSetHeading((SetHeadingNode) stmt);
        } else if (stmt instanceof SetColorNode) {
            generateSetColor((SetColorNode) stmt);
        } else if (stmt instanceof SetPenColorNode) {
            generateSetPenColor((SetPenColorNode) stmt);
        } else if (stmt instanceof SetXNode) {
            generateSetX((SetXNode) stmt);
        } else if (stmt instanceof SetYNode) {
            generateSetY((SetYNode) stmt);
        } else if (stmt instanceof HideTurtleNode) {
            emit(new ThreeAddressInstruction(IROpcode.HIDE_TURTLE));
        } else if (stmt instanceof ShowTurtleNode) {
            emit(new ThreeAddressInstruction(IROpcode.SHOW_TURTLE));
        } else if (stmt instanceof WaitNode) {
            generateWait((WaitNode) stmt);
        } else if (stmt instanceof IncNode) {
            generateInc((IncNode) stmt);
        } else {
            emit(ThreeAddressInstruction.comment("Stmt no soportado: " + stmt.getClass().getSimpleName()));
        }
    }
    
    /**
     * Genera IR para RepeatNode (REPITE n [...])
     */
    private void generateRepeat(RepeatNode node) {
        emit(ThreeAddressInstruction.comment("Repite"));
        
        // Generar etiquetas únicas
        String labelStart = labelGen.next("loop_start");
        String labelEnd = labelGen.next("loop_end");
        
        // Evaluar el límite (número de repeticiones)
        // IMPORTANTE: Esto genera código para cargar el valor de 'n' si es variable
        Operand limitOp = generateExpr(node.getTimes());
        
        // Copiar el límite a un temporal (para no perderlo si la variable cambia)
        Operand limitTemp = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.MOVE, limitTemp, limitOp));
        
        // Inicializar contador a 0
        Operand counterOp = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.LOAD_CONST, counterOp, Operand.constant(0)));
        
        // Etiqueta de inicio del ciclo
        emit(ThreeAddressInstruction.label(labelStart));
        
        // Condición: counter < limit
        Operand condOp = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.LT, condOp, counterOp, limitTemp));
        
        // Si la condición es falsa (counter >= limit), saltar al final
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelEnd), condOp));
        
        // Generar cuerpo del ciclo
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        // Incrementar contador: counter = counter + 1
        Operand newCounterOp = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.ADD, newCounterOp, counterOp, Operand.constant(1)));
        
        // IMPORTANTE: Actualizar el contador original
        emit(new ThreeAddressInstruction(IROpcode.MOVE, counterOp, newCounterOp));
        
        // Saltar al inicio del ciclo
        emit(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelStart)));
        
        // Etiqueta de fin del ciclo
        emit(ThreeAddressInstruction.label(labelEnd));
        
        emit(ThreeAddressInstruction.comment("Fin Repite"));
    }
    
    private void generateVarDecl(VarDeclNode node) {
        emit(ThreeAddressInstruction.comment("haz " + node.getName() + " = <expr>"));
        Operand value = generateExpr(node.getValue());
        emit(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable(node.getName()), value));
    }
    
    private void generateVarAssign(VarAssignNode node) {
        emit(ThreeAddressInstruction.comment("asignar " + node.getName() + " = <expr>"));
        Operand value = generateExpr(node.getExpression());
        emit(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable(node.getName()), value));
    }
    
    private void generateForward(ForwardNode node) {
        emit(ThreeAddressInstruction.comment("avanza <expr>"));
        Operand dist = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.FORWARD, dist));
    }
    
    private void generateBackward(BackwardNode node) {
        emit(ThreeAddressInstruction.comment("retrocede <expr>"));
        Operand dist = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.BACKWARD, dist));
    }
    
    private void generateTurnRight(TurnRightNode node) {
        emit(ThreeAddressInstruction.comment("giraderecha <expr>"));
        Operand angle = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.TURN_RIGHT, angle));
    }
    
    private void generateTurnLeft(TurnLeftNode node) {
        emit(ThreeAddressInstruction.comment("giraizquierda <expr>"));
        Operand angle = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.TURN_LEFT, angle));
    }
    
    private void generateIf(IfNode node) {
        emit(ThreeAddressInstruction.comment("SI <cond>"));
        
        String labelElse = labelGen.next("else");
        String labelEnd = labelGen.next("endif");
        
        // Evaluar condición
        Operand cond = generateExpr(node.getCond());
        
        // Si es falso, saltar a else
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelElse), cond));
        
        // Bloque then
        for (StmtNode stmt : node.getThenBody()) {
            generateStmt(stmt);
        }
        
        // Saltar al final (evitar else)
        emit(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelEnd)));
        
        // Etiqueta else
        emit(ThreeAddressInstruction.label(labelElse));
        
        // Bloque else (si existe)
        if (node.getElseBody() != null) {
            for (StmtNode stmt : node.getElseBody()) {
                generateStmt(stmt);
            }
        }
        
        // Etiqueta fin
        emit(ThreeAddressInstruction.label(labelEnd));
    }
    
    private void generateWhile(WhileNode node) {
        emit(ThreeAddressInstruction.comment("MIENTRAS <cond>"));
        
        String labelStart = labelGen.next("while_start");
        String labelEnd = labelGen.next("while_end");
        
        // Etiqueta inicio
        emit(ThreeAddressInstruction.label(labelStart));
        
        // Evaluar condición
        Operand cond = generateExpr(node.getCond());
        
        // Si es falso, saltar al final
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelEnd), cond));
        
        // Cuerpo
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        // Volver al inicio
        emit(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelStart)));
        
        // Etiqueta fin
        emit(ThreeAddressInstruction.label(labelEnd));
    }
    
    private void generateUntil(UntilNode node) {
        emit(ThreeAddressInstruction.comment("HASTA <cond>"));
        
        String labelStart = labelGen.next("until_start");
        String labelEnd = labelGen.next("until_end");
        
        emit(ThreeAddressInstruction.label(labelStart));
        
        Operand cond = generateExpr(node.getCondition());
        
        // HASTA: se ejecuta mientras la condición sea FALSA
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_TRUE, Operand.label(labelEnd), cond));
        
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        emit(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelStart)));
        emit(ThreeAddressInstruction.label(labelEnd));
    }
    
    private void generateDoWhile(DoWhileNode node) {
        emit(ThreeAddressInstruction.comment("HAZ.MIENTRAS"));
        
        String labelStart = labelGen.next("dowhile_start");
        
        emit(ThreeAddressInstruction.label(labelStart));
        
        // Ejecutar cuerpo primero
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        // Evaluar condición al final
        Operand cond = generateExpr(node.getCond());
        
        // Si es verdadero, volver al inicio
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_TRUE, Operand.label(labelStart), cond));
    }
    
    private void generateDoUntil(DoUntilNode node) {
        emit(ThreeAddressInstruction.comment("HAZ.HASTA"));
        
        String labelStart = labelGen.next("dountil_start");
        
        emit(ThreeAddressInstruction.label(labelStart));
        
        // Ejecutar cuerpo primero
        for (StmtNode stmt : node.getBody()) {
            generateStmt(stmt);
        }
        
        // Evaluar condición al final
        Operand cond = generateExpr(node.getCond());
        
        // Si es FALSO, volver al inicio (se ejecuta HASTA que sea verdadero)
        emit(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelStart), cond));
    }
    
    private void generateProcCall(ProcCallNode node) {
        emit(ThreeAddressInstruction.comment("llamar " + node.getName()));
        
        // Pasar parámetros
        List<ExprNode> args = node.getArgs();
        for (int i = 0; i < args.size(); i++) {
            Operand argValue = generateExpr(args.get(i));
            emit(new ThreeAddressInstruction(IROpcode.PARAM, Operand.constant(i), argValue));
        }
        
        // Llamar al procedimiento
        String procLabel = "proc_" + node.getName();
        emit(new ThreeAddressInstruction(IROpcode.CALL, null, Operand.label(procLabel), Operand.constant(args.size())));
    }
    
    private void generateSetPos(SetPosNode node) {
        Operand x = generateExpr(node.getX());
        Operand y = generateExpr(node.getY());
        emit(new ThreeAddressInstruction(IROpcode.SET_POS, x, y));
    }
    
    private void generateSetHeading(SetHeadingNode node) {
        Operand angle = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.SET_HEADING, angle));
    }
    
    private void generateSetColor(SetColorNode node) {
        Operand r = generateExpr(node.getR());
        Operand g = generateExpr(node.getG());
        Operand b = generateExpr(node.getB());
        emit(new ThreeAddressInstruction(IROpcode.SET_COLOR, r, g, b));
    }
    
    private void generateSetPenColor(SetPenColorNode node) {
        String color = node.getColor();
        int r, g, b;
        switch (color) {
            case "negro": r = 0; g = 0; b = 0; break;
            case "azul":  r = 0; g = 0; b = 255; break;
            case "rojo":  r = 255; g = 0; b = 0; break;
            default:
                throw new IllegalArgumentException("Color no soportado en IR: " + color);
        }

        emit(ThreeAddressInstruction.comment("poncolorlapiz " + color));
        emit(new ThreeAddressInstruction(
            IROpcode.SET_COLOR,
            Operand.constant(r),
            Operand.constant(g),
            Operand.constant(b)
        ));
    }
    
    private void generateSetX(SetXNode node) {
        Operand x = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.SET_X, x));
    }
    
    private void generateSetY(SetYNode node) {
        Operand y = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.SET_Y, y));
    }
    
    private void generateWait(WaitNode node) {
        Operand time = generateExpr(node.getExpr());
        emit(new ThreeAddressInstruction(IROpcode.WAIT, time));
    }
    
    private void generateInc(IncNode node) {
        String varName = node.getVar().getName();
        Operand delta = generateExpr(node.getDelta());
        Operand varOp = Operand.variable(varName);
        
        // Cargar valor actual
        Operand currentVal = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.LOAD_VAR, currentVal, varOp));
        
        // Sumar delta
        Operand newVal = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.ADD, newVal, currentVal, delta));
        
        // Guardar nuevo valor
        emit(new ThreeAddressInstruction(IROpcode.STORE, varOp, newVal));
    }
    
    /**
     * Genera IR para una expresión y retorna el operando donde queda el resultado.
     */
    private Operand generateExpr(ExprNode expr) {
        if (expr instanceof ConstNode) {
            Object val = ((ConstNode) expr).getValue();
            Operand temp = tempGen.nextOperand();
            if (val instanceof Number) {
                emit(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, Operand.constant(((Number) val).doubleValue())));
            } else if (val instanceof Boolean) {
                emit(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, Operand.constant((Boolean) val ? 1 : 0)));
            } else {
                emit(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, Operand.constant(0)));
            }
            return temp;
        }
        
        if (expr instanceof VarRefNode) {
            String varName = ((VarRefNode) expr).getName();
            Operand temp = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.LOAD_VAR, temp, Operand.variable(varName)));
            return temp;
        }
        
        if (expr instanceof AdditionNode) {
            AdditionNode add = (AdditionNode) expr;
            Operand left = generateExpr(add.getLeft());
            Operand right = generateExpr(add.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.ADD, result, left, right));
            return result;
        }
        
        if (expr instanceof SubtractionNode) {
            SubtractionNode sub = (SubtractionNode) expr;
            Operand left = generateExpr(sub.getLeft());
            Operand right = generateExpr(sub.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.SUB, result, left, right));
            return result;
        }
        
        if (expr instanceof MultiplicationNode) {
            MultiplicationNode mul = (MultiplicationNode) expr;
            Operand left = generateExpr(mul.getLeft());
            Operand right = generateExpr(mul.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.MUL, result, left, right));
            return result;
        }
        
        if (expr instanceof DivisionNode) {
            DivisionNode div = (DivisionNode) expr;
            Operand left = generateExpr(div.getLeft());
            Operand right = generateExpr(div.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.DIV, result, left, right));
            return result;
        }
        
        if (expr instanceof ExponentiationNode) {
            ExponentiationNode pow = (ExponentiationNode) expr;
            Operand left = generateExpr(pow.getLeft());
            Operand right = generateExpr(pow.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.POW, result, left, right));
            return result;
        }
        
        // Comparaciones
        if (expr instanceof LessThanNode) {
            LessThanNode lt = (LessThanNode) expr;
            Operand left = generateExpr(lt.getLeft());
            Operand right = generateExpr(lt.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.LT, result, left, right));
            return result;
        }
        
        if (expr instanceof GreaterThanNode) {
            GreaterThanNode gt = (GreaterThanNode) expr;
            Operand left = generateExpr(gt.getLeft());
            Operand right = generateExpr(gt.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.GT, result, left, right));
            return result;
        }
        
        if (expr instanceof LessEqualNode) {
            LessEqualNode le = (LessEqualNode) expr;
            Operand left = generateExpr(le.getLeft());
            Operand right = generateExpr(le.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.LTE, result, left, right));
            return result;
        }
        
        if (expr instanceof GreaterEqualNode) {
            GreaterEqualNode ge = (GreaterEqualNode) expr;
            Operand left = generateExpr(ge.getLeft());
            Operand right = generateExpr(ge.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.GTE, result, left, right));
            return result;
        }
        
        if (expr instanceof EqualsNode) {
            EqualsNode eq = (EqualsNode) expr;
            Operand left = generateExpr(eq.getLeft());
            Operand right = generateExpr(eq.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.EQ, result, left, right));
            return result;
        }
        
        if (expr instanceof NotEqualsNode) {
            NotEqualsNode ne = (NotEqualsNode) expr;
            Operand left = generateExpr(ne.getLeft());
            Operand right = generateExpr(ne.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.NEQ, result, left, right));
            return result;
        }
        
        if (expr instanceof EqualsFuncNode) {
            EqualsFuncNode eq = (EqualsFuncNode) expr;
            Operand left = generateExpr(eq.getLeft());
            Operand right = generateExpr(eq.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.EQ, result, left, right));
            return result;
        }
        
        if (expr instanceof LogicalAndNode) {
            LogicalAndNode and = (LogicalAndNode) expr;
            Operand left = generateExpr(and.getLeft());
            Operand right = generateExpr(and.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.AND, result, left, right));
            return result;
        }
        
        if (expr instanceof LogicalOrNode) {
            LogicalOrNode or = (LogicalOrNode) expr;
            Operand left = generateExpr(or.getLeft());
            Operand right = generateExpr(or.getRight());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.OR, result, left, right));
            return result;
        }
        
        if (expr instanceof LogicalNotNode) {
            LogicalNotNode not = (LogicalNotNode) expr;
            Operand operand = generateExpr(not.getExpr());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.NOT, result, operand));
            return result;
        }
        
        if (expr instanceof RandomNode) {
            RandomNode rand = (RandomNode) expr;
            Operand max = generateExpr(rand.getExpr());
            Operand result = tempGen.nextOperand();
            emit(new ThreeAddressInstruction(IROpcode.RANDOM, result, max));
            return result;
        }
        
        // Default: retornar constante 0
        Operand temp = tempGen.nextOperand();
        emit(new ThreeAddressInstruction(IROpcode.LOAD_CONST, temp, Operand.constant(0)));
        return temp;
    }
    
    private void emit(ThreeAddressInstruction instr) {
        instructions.add(instr);
    }
    
    /**
     * Resultado de la generación de IR
     */
    public static class Result {
        public final List<ThreeAddressInstruction> instructions;
        public final Map<String, ProcedureInfo> procedures;
        
        public Result(List<ThreeAddressInstruction> instructions, Map<String, ProcedureInfo> procedures) {
            this.instructions = instructions;
            this.procedures = procedures;
        }
    }
}
