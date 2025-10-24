package com.miorganizacion.logotec.compilador.backend;

import java.util.*;

/**
 * Asignador de registros para traducir temporales de IR a registros físicos.
 * Usa una estrategia simple de asignación con spilling a memoria si es necesario.
 * 
 * FASE 3: Register Allocation
 */
public class RegisterAllocator {
    
    // Pool de registros disponibles
    private final Queue<Register> availableRegisters;
    
    // Mapa: temporal IR → registro físico
    private final Map<String, Register> allocation;
    
    // Temporales que se han desbordado a memoria
    private final Set<String> spilledTemps;
    
    // Contador de offsets en el stack para spilling
    private int stackOffset;
    
    public RegisterAllocator() {
        this.availableRegisters = new LinkedList<>();
        this.allocation = new HashMap<>();
        this.spilledTemps = new HashSet<>();
        this.stackOffset = 0;
        
        // Inicializar pool con registros temporales
        availableRegisters.addAll(Arrays.asList(Register.TEMP_REGISTERS));
    }
    
    /**
     * Obtiene el registro asignado a un temporal.
     * Si no tiene registro, asigna uno (o hace spill si es necesario).
     */
    public Register getRegister(String tempName) {
        // Si ya está asignado, retornar
        if (allocation.containsKey(tempName)) {
            return allocation.get(tempName);
        }
        
        // Si hay registros disponibles, asignar uno
        if (!availableRegisters.isEmpty()) {
            Register reg = availableRegisters.poll();
            allocation.put(tempName, reg);
            return reg;
        }
        
        // No hay registros libres - hacer spill (usar $t0 como temporal)
        // En una implementación real, se elegiría el registro menos usado
        spilledTemps.add(tempName);
        return Register.T0; // Registro temporal para valores spilleados
    }
    
    /**
     * Libera el registro asignado a un temporal.
     */
    public void freeRegister(String tempName) {
        if (allocation.containsKey(tempName)) {
            Register reg = allocation.remove(tempName);
            if (!spilledTemps.contains(tempName)) {
                availableRegisters.offer(reg);
            }
        }
    }
    
    /**
     * Verifica si un temporal ha sido spilleado a memoria.
     */
    public boolean isSpilled(String tempName) {
        return spilledTemps.contains(tempName);
    }
    
    /**
     * Obtiene el offset en el stack para un temporal spilleado.
     */
    public int getStackOffset(String tempName) {
        if (!spilledTemps.contains(tempName)) {
            // Asignar nuevo offset
            spilledTemps.add(tempName);
            stackOffset += 4; // 4 bytes por palabra
        }
        return stackOffset;
    }
    
    /**
     * Libera todos los registros (para iniciar un nuevo bloque).
     */
    public void reset() {
        availableRegisters.clear();
        availableRegisters.addAll(Arrays.asList(Register.TEMP_REGISTERS));
        allocation.clear();
        spilledTemps.clear();
        stackOffset = 0;
    }
    
    /**
     * Obtiene el mapa completo de asignación.
     */
    public Map<String, Register> getAllocation() {
        return new HashMap<>(allocation);
    }
    
    /**
     * Obtiene el tamaño total del stack necesario para spilling.
     */
    public int getTotalStackSize() {
        return stackOffset;
    }
}
