package com.miorganizacion.logotec.compilador.ir;

/**
 * Enum que define todos los opcodes (códigos de operación) soportados
 * en la Representación Intermedia (IR) de tres direcciones.
 */
public enum IROpcode {
    // ==================== OPERACIONES DE MOVIMIENTO ====================
    /** Cargar un valor constante: LOAD_CONST dest, #valor */
    LOAD_CONST,
    
    /** Cargar desde una variable: LOAD_VAR dest, [variable] */
    LOAD_VAR,
    
    /** Almacenar en una variable: STORE [variable], source */
    STORE,
    
    /** Mover entre temporales: MOVE dest, source */
    MOVE,
    
    // ==================== OPERACIONES ARITMÉTICAS ====================
    /** Suma: ADD dest, op1, op2 */
    ADD,
    
    /** Resta: SUB dest, op1, op2 */
    SUB,
    
    /** Multiplicación: MUL dest, op1, op2 */
    MUL,
    
    /** División: DIV dest, op1, op2 */
    DIV,
    
    /** Módulo: MOD dest, op1, op2 */
    MOD,
    
    /** Potencia: POW dest, base, exponente */
    POW,
    
    /** Negación unaria: NEG dest, op */
    NEG,
    
    // ==================== OPERACIONES DE COMPARACIÓN ====================
    /** Igual a: EQ dest, op1, op2 */
    EQ,
    
    /** Diferente de: NEQ dest, op1, op2 */
    NEQ,
    
    /** Menor que: LT dest, op1, op2 */
    LT,
    
    /** Mayor que: GT dest, op1, op2 */
    GT,
    
    /** Menor o igual que: LTE dest, op1, op2 */
    LTE,
    
    /** Mayor o igual que: GTE dest, op1, op2 */
    GTE,
    
    // ==================== OPERACIONES LÓGICAS ====================
    /** AND lógico: AND dest, op1, op2 */
    AND,
    
    /** OR lógico: OR dest, op1, op2 */
    OR,
    
    /** NOT lógico: NOT dest, op */
    NOT,
    
    // ==================== CONTROL DE FLUJO ====================
    /** Etiqueta: LABEL nombre */
    LABEL,
    
    /** Salto incondicional: JUMP etiqueta */
    JUMP,
    
    /** Salto condicional si es falso: JUMP_IF_FALSE etiqueta, condicion */
    JUMP_IF_FALSE,
    
    /** Salto condicional si es verdadero: JUMP_IF_TRUE etiqueta, condicion */
    JUMP_IF_TRUE,
    
    // ==================== COMANDOS DE TORTUGA ====================
    /** Avanzar: FORWARD distancia */
    FORWARD,
    
    /** Retroceder: BACKWARD distancia */
    BACKWARD,
    
    /** Girar a la derecha: TURN_RIGHT grados */
    TURN_RIGHT,
    
    /** Girar a la izquierda: TURN_LEFT grados */
    TURN_LEFT,
    
    /** Bajar el lápiz: PEN_DOWN */
    PEN_DOWN,
    
    /** Levantar el lápiz: PEN_UP */
    PEN_UP,
    
    /** Centrar la tortuga: CENTER */
    CENTER,
    
    /** Establecer color: SET_COLOR r, g, b */
    SET_COLOR,
    
    /** Establecer posición: SET_POS x, y */
    SET_POS,
    
    /** Ocultar tortuga: HIDE_TURTLE */
    HIDE_TURTLE,
    
    /** Mostrar tortuga: SHOW_TURTLE */
    SHOW_TURTLE,
    
    /** Establecer rumbo: SET_HEADING grados */
    SET_HEADING,
    
    // ==================== OPERACIONES DE I/O ====================
    /** Imprimir valor: PRINT valor */
    PRINT,
    
    // ==================== OPERACIONES ESPECIALES ====================
    /** No operación (placeholder): NOP */
    NOP,
    
    /** Comentario en el IR: COMMENT texto */
    COMMENT,
    
    // ==================== PROCEDIMIENTOS Y FUNCIONES ====================
    /** Inicio de definición de procedimiento: PROC_BEGIN nombre */
    PROC_BEGIN,
    
    /** Fin de definición de procedimiento: PROC_END nombre */
    PROC_END,
    
    /** Llamada a procedimiento: CALL dest, nombre, num_args */
    CALL,
    
    /** Retorno de procedimiento: RETURN [valor_opcional] */
    RETURN,
    
    /** Parámetro de procedimiento: PARAM index, valor */
    PARAM,
    
    /** Obtener argumento: GET_ARG dest, index */
    GET_ARG
}
