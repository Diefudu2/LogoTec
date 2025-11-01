package com.miorganizacion.logotec.compilador.ir;

/**
 * Códigos de operación para la Representación Intermedia (IR).
 */
public enum IROpcode {
    // Movimiento de datos
    LOAD_CONST,     // dest = #const
    LOAD_VAR,       // dest = [var]
    STORE,          // [var] = src
    MOVE,           // dest = src
    
    // Aritméticas
    ADD,            // dest = src1 + src2
    SUB,            // dest = src1 - src2
    MUL,            // dest = src1 * src2
    DIV,            // dest = src1 / src2
    MOD,            // dest = src1 % src2
    POW,            // dest = src1 ^ src2
    NEG,            // dest = -src
    
    // Comparaciones
    LT,             // dest = src1 < src2
    GT,             // dest = src1 > src2
    LTE,            // dest = src1 <= src2
    GTE,            // dest = src1 >= src2
    EQ,             // dest = src1 == src2
    NEQ,            // dest = src1 != src2
    
    // Lógicas
    AND,            // dest = src1 && src2
    OR,             // dest = src1 || src2
    NOT,            // dest = !src
    
    // Control de flujo
    LABEL,          // label:
    JUMP,           // goto label
    JUMP_IF_TRUE,   // if (cond) goto label
    JUMP_IF_FALSE,  // if (!cond) goto label
    
    // Procedimientos
    PROC_BEGIN,     // inicio de procedimiento
    PROC_END,       // fin de procedimiento
    CALL,           // llamar procedimiento
    RETURN,         // retornar de procedimiento
    PARAM,          // pasar parámetro
    GET_ARG,        // obtener argumento
    
    // Comandos de tortuga
    FORWARD,        // avanzar
    BACKWARD,       // retroceder
    TURN_RIGHT,     // girar derecha
    TURN_LEFT,      // girar izquierda
    PEN_UP,         // subir lápiz
    PEN_DOWN,       // bajar lápiz
    CENTER,         // centrar
    SET_POS,        // establecer posición (x, y)
    SET_X,          // establecer X
    SET_Y,          // establecer Y
    SET_HEADING,    // establecer rumbo
    SET_COLOR,      // establecer color (r, g, b)
    HIDE_TURTLE,    // ocultar tortuga
    SHOW_TURTLE,    // mostrar tortuga
    WAIT,           // esperar
    
    // Funciones
    RANDOM,         // número aleatorio
    
    // Especiales
    NOP,            // no operación
    COMMENT         // comentario
}
