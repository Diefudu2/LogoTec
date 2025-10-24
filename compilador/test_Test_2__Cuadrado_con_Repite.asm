# ========================================
# Código Assembly Generado
# LogoTec Compiler
# ========================================
# Total de instrucciones: 43
# ========================================

.data
    # Variables del programa
    # lado: .word 0
    # null: .word 0
    # 
.text
    globl   main                               # Punto de entrada
    # 
    # ========================================
    # Programa LogoTec - Código Intermedio
    # ========================================
main:
    # haz lado = <expr>
    li      $t0, 100
    sw      $t0, lado
    # Repite <n> [...]
    li      $t1, 0
    li      $t2, 4
L1_loop_start:
    # Comparación: slt
    slt     $t3, $t1, $t2
    beqz    $t3, L2_loop_end
    # avanza <expr>
    lw      $t4, null
    # Comando tortuga: FORWARD
    move    $a0, $t4
    li      $v0, 1
    syscall 1
    # giraderecha <expr>
    li      $t5, 90
    # Comando tortuga: TURN_RIGHT
    move    $a0, $t5
    li      $v0, 3
    syscall 3
    li      $t6, 1
    add     $t7, $t1, $t6
    move    $t1, $t7
    j       L1_loop_start
L2_loop_end:
    # Fin del programa
    # Terminar programa
    li      $v0, 10
    syscall 10

# ========================================
# Fin del código Assembly
# ========================================
