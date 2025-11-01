package com.miorganizacion.logotec.compilador.backend;

import java.util.*;

/**
 * Allocador simple de registros.
 * Asigna registros temporales ($t0-$t9) a variables y temporales del IR.
 * 
 * Estrategia: First-fit con spilling cuando se agotan los registros.
 */
public class RegisterAllocator {
    
    // Pool de registros temporales disponibles
    private final Deque<Register> availableRegs;
    
    // Mapeo de nombres (temporales/variables) a registros
    private final Map<String, Register> allocation;
    
    // Registros usados (para estadísticas)
    private final Set<Register> usedRegs;
    
    public RegisterAllocator() {
        this.availableRegs = new ArrayDeque<Register>();
        this.allocation = new HashMap<String, Register>();
        this.usedRegs = new HashSet<Register>();
        
        // Inicializar pool con registros temporales
        initializePool();
    }
    
    private void initializePool() {
        availableRegs.clear();
        // Usar $t0-$t9 para temporales
        availableRegs.add(Register.T0);
        availableRegs.add(Register.T1);
        availableRegs.add(Register.T2);
        availableRegs.add(Register.T3);
        availableRegs.add(Register.T4);
        availableRegs.add(Register.T5);
        availableRegs.add(Register.T6);
        availableRegs.add(Register.T7);
        availableRegs.add(Register.T8);
        availableRegs.add(Register.T9);
    }
    
    /**
     * Obtiene un registro para un nombre dado (temporal o variable).
     * Si ya tiene uno asignado, lo retorna. Si no, asigna uno nuevo.
     */
    public Register getRegister(String name) {
        // ¿Ya tiene registro asignado?
        Register existing = allocation.get(name);
        if (existing != null) {
            return existing;
        }
        
        // Asignar nuevo registro
        Register reg;
        if (!availableRegs.isEmpty()) {
            reg = availableRegs.removeFirst();
        } else {
            // Spilling: reusar $t9 si se agotan
            reg = Register.T9;
        }
        
        allocation.put(name, reg);
        usedRegs.add(reg);
        return reg;
    }
    
    /**
     * Libera el registro asociado a un nombre.
     */
    public void freeRegister(String name) {
        Register reg = allocation.remove(name);
        if (reg != null && reg.isTemporary()) {
            availableRegs.addLast(reg);
        }
    }
    
    /**
     * Verifica si un nombre tiene registro asignado.
     */
    public boolean hasRegister(String name) {
        return allocation.containsKey(name);
    }
    
    /**
     * Obtiene el registro asignado sin crear uno nuevo.
     */
    public Register getAllocatedRegister(String name) {
        return allocation.get(name);
    }
    
    /**
     * Reinicia el allocador.
     */
    public void reset() {
        allocation.clear();
        usedRegs.clear();
        initializePool();
    }
    
    /**
     * Obtiene el número de registros usados.
     */
    public int getUsedCount() {
        return usedRegs.size();
    }
    
    /**
     * Obtiene todos los registros usados.
     */
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>(usedRegs);
    }
    
    /**
     * Obtiene el mapeo actual.
     */
    public Map<String, Register> getAllocation() {
        return new HashMap<String, Register>(allocation);
    }
    
    @Override
    public String toString() {
        return "RegisterAllocator{used=" + usedRegs.size() + ", available=" + availableRegs.size() + "}";
    }
}
