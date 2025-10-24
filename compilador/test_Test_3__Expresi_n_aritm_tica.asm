# ========================================
# Código Assembly Generado
# LogoTec Compiler
# ========================================
# Total de instrucciones: 33
# ========================================

.data
    # Variables del programa
    # x: .word 0
    # null: .word 0
    # y: .word 0
    # 
.text
    globl   main                               # Punto de entrada
    # 
    # ========================================
    # Programa LogoTec - Código Intermedio
    # ========================================
main:
    # haz x = <expr>
    li      $t0, 10
    li      $t1, 20
    add     $t2, $t0, $t1
    sw      $t2, x
    # haz y = <expr>
    lw      $t3, null
    li      $t4, 2
    mul     $t5, $t3, $t4
    sw      $t5, y
    # avanza <expr>
    lw      $t6, null
    # Comando tortuga: FORWARD
    move    $a0, $t6
    li      $v0, 1
    syscall 1
    # Fin del programa
    # Terminar programa
    li      $v0, 10
    syscall 10

# ========================================
# Fin del código Assembly
# ========================================
