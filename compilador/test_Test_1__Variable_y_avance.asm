# ========================================
# Código Assembly Generado
# LogoTec Compiler
# ========================================
# Total de instrucciones: 25
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
    li      $t0, 50
    sw      $t0, lado
    # avanza <expr>
    lw      $t1, null
    # Comando tortuga: FORWARD
    move    $a0, $t1
    li      $v0, 1
    syscall 1
    # Fin del programa
    # Terminar programa
    li      $v0, 10
    syscall 10

# ========================================
# Fin del código Assembly
# ========================================
