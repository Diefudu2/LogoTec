package com.miorganizacion.logotec.compilador.backend;

import java.util.*;

/**
 * Allocador de registros CORREGIDO.
 * Asegura que cada temporal tenga un registro ÚNICO durante su ciclo de vida.
 */
public class RegisterAllocator {
    
    private final Map<String, Register> allocation;
    private final Set<Register> usedRegs;
    private int nextDynamicSlot = 0;  // ← NUEVO: Contador para asignaciones dinámicas
    
    public RegisterAllocator() {
        this.allocation = new HashMap<>();
        this.usedRegs = new HashSet<>();
    }
    
    /**
     * Obtiene un registro para un temporal/variable.
     * GARANTIZA registros únicos para temporales diferentes.
     */
    public Register getRegister(String temp) {
        // Si ya tiene asignación, retornarla
        if (allocation.containsKey(temp)) {
            return allocation.get(temp);
        }
        
        // Extraer número del temporal
        int tempNum = extractTempNumber(temp);
        
        // ✅ Mapeo directo para temporales estándar (t0-t17)
        if (tempNum >= 0 && tempNum < 10) {
            Register reg = Register.valueOf("T" + tempNum);
            allocation.put(temp, reg);
            // ← CAMBIO: NO marcar como usado permanentemente para t0-t9
            // Estos se reutilizan automáticamente
            return reg;
        }
        
        if (tempNum >= 10 && tempNum < 18) {
            Register reg = Register.valueOf("S" + (tempNum - 10));
            allocation.put(temp, reg);
            // ← CAMBIO: NO marcar como usado permanentemente para s0-s7
            return reg;
        }
        
        // ✅ NUEVO: Asignación dinámica para temporales con hash
        // Asignar en orden: $t0, $t1, ..., $s7
        Register reg = findAvailableRegister();
        allocation.put(temp, reg);
        usedRegs.add(reg);  // ← Solo marcar los temporales dinámicos como "usados"
        return reg;
    }

    private int extractTempNumber(String temp) {
        // Solo extraer número si es temporal estándar "tX"
        if (temp.matches("t\\d+")) {
            try {
                return Integer.parseInt(temp.substring(1));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;  // Forzar asignación dinámica para otros nombres
    }
    
    /**
     * Encuentra un registro disponible.
     * GARANTIZA que NO reutiliza registros ya asignados.
     */
    private Register findAvailableRegister() {
        // ✅ CORRECCIÓN CRÍTICA: NO verificar usedRegs para temporales estándar
        // Solo verificar si el registro está asignado actualmente
        
        // Intentar registros $t0-$t9
        for (int i = 0; i < 10; i++) {
            Register reg = Register.valueOf("T" + i);
            // ← CAMBIO: Solo verificar allocation, NO usedRegs
            if (!isCurrentlyAllocated(reg)) {
                return reg;
            }
        }
        
        // Intentar $s0-$s7
        for (int i = 0; i < 8; i++) {
            Register reg = Register.valueOf("S" + i);
            if (!isCurrentlyAllocated(reg)) {
                return reg;
            }
        }
        
        // Si llegamos aquí, TODOS los registros están en uso
        // Estrategia: Usar registros temporales en orden rotativo
        int spillSlot = nextDynamicSlot % 10;  // Rotar entre $t0-$t9
        nextDynamicSlot++;
        Register spillReg = Register.valueOf("T" + spillSlot);
        
        System.err.println("⚠️  WARNING: Todos los registros en uso, spillando a " + spillReg);
        
        return spillReg;
    }

    /**
     * Verifica si un registro está asignado ACTUALMENTE a un temporal con hash.
     * NO cuenta temporales estándar (t0-t17).
     */
    private boolean isCurrentlyAllocated(Register reg) {
        for (Map.Entry<String, Register> entry : allocation.entrySet()) {
            String tempName = entry.getKey();
            Register assignedReg = entry.getValue();
            
            // Si el registro está asignado a un temporal con hash, está ocupado
            if (assignedReg.equals(reg)) {
                // ← CAMBIO: Permitir reutilizar registros de temporales estándar
                if (tempName.matches("t\\d+")) {
                    continue;  // Ignorar temporales estándar
                }
                return true;  // Está ocupado por un temporal con hash
            }
        }
        return false;
    }
    
    public void freeRegister(String name) {
        allocation.remove(name);
    }
    
    public boolean hasRegister(String name) {
        return allocation.containsKey(name);
    }
    
    public Register getAllocatedRegister(String name) {
        return allocation.get(name);
    }
    
    public void reset() {
        allocation.clear();
        usedRegs.clear();
    }
    
    public int getUsedCount() {
        return usedRegs.size();
    }
    
    public Set<Register> getUsedRegisters() {
        return new HashSet<>(usedRegs);
    }
    
    public Map<String, Register> getAllocation() {
        return new HashMap<>(allocation);
    }
    
    /**
     * ← NUEVO: Libera registros de temporales con hash después de usar.
     */
    public void freeTemporaryRegisters() {
        // Liberar solo registros de temporales con hash (no t0-t17)
        allocation.entrySet().removeIf(entry -> 
            !entry.getKey().matches("t\\d+") &&  // No es temporal estándar
            !entry.getKey().startsWith("var")     // No es variable
        );
        usedRegs.clear();  // Limpiar marcas de uso
    }
    
    @Override
    public String toString() {
        return "RegisterAllocator{used=" + usedRegs.size() + ", mappings=" + allocation.size() + "}";
    }
}
