package com.miorganizacion.logotec.vm;

import com.miorganizacion.logotec.compilador.backend.BytecodeInterpreter;
import com.miorganizacion.logotec.compilador.backend.ExecutableGenerator;
import com.miorganizacion.logotec.compilador.backend.ObjectCodeGenerator;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;

/**
 * Máquina Virtual Standalone para ejecutar archivos .ltbc de LogoTec.
 * 
 * Este programa permite ejecutar archivos objeto compilados (.ltbc)
 * sin necesidad de abrir el IDE completo.
 * 
 * Uso:
 *   java -jar logotec-vm.jar programa.ltbc [opciones]
 * 
 * Opciones:
 *   -d, --debug      Modo debug (muestra instrucciones)
 *   -i, --info       Solo muestra información del archivo
 *   -h, --help       Muestra ayuda
 * 
 * Ejemplos:
 *   java -jar logotec-vm.jar cuadrado.ltbc
 *   java -jar logotec-vm.jar mandala.ltbc --debug
 *   java -jar logotec-vm.jar roseta.ltbc --info
 * 
 * @author Equipo LogoTec
 */
public class VMMain {
    
    private static final String VERSION = "1.0.0";
    
    public static void main(String[] args) {
        printBanner();
        
        // Parsear argumentos
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }
        
        String filePath = null;
        boolean debugMode = false;
        boolean infoOnly = false;
        
        for (String arg : args) {
            if (arg.equals("-d") || arg.equals("--debug")) {
                debugMode = true;
            } else if (arg.equals("-i") || arg.equals("--info")) {
                infoOnly = true;
            } else if (arg.equals("-h") || arg.equals("--help")) {
                printUsage();
                System.exit(0);
            } else if (!arg.startsWith("-")) {
                filePath = arg;
            }
        }
        
        if (filePath == null) {
            System.err.println("❌ Error: Debe especificar un archivo .ltbc");
            printUsage();
            System.exit(1);
        }
        
        // Verificar que el archivo existe
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("❌ Error: Archivo no encontrado: " + filePath);
            System.exit(1);
        }
        
        // Verificar que es un archivo .ltbc válido
        if (!ExecutableGenerator.isValidLTBC(filePath)) {
            System.err.println("❌ Error: El archivo no es un .ltbc válido");
            System.exit(1);
        }
        
        try {
            // Modo info: solo muestra información
            if (infoOnly) {
                ExecutableGenerator.FileInfo info = ExecutableGenerator.getFileInfo(filePath);
                System.out.println("\n" + info);
                System.exit(0);
            }
            
            // Cargar archivo
            System.out.println("📂 Cargando: " + file.getName());
            ObjectCodeGenerator.Result program = ExecutableGenerator.loadFromFile(filePath);
            
            // Crear VM
            System.out.println("\n🔧 Inicializando Máquina Virtual...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.setDebugMode(debugMode);
            vm.loadProgram(program);
            
            // Ejecutar
            System.out.println("\n🚀 Ejecutando bytecode...\n");
            long startTime = System.currentTimeMillis();
            vm.execute();
            long endTime = System.currentTimeMillis();
            
            // Obtener acciones
            List<AccionTortuga> acciones = vm.getAcciones();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ EJECUCIÓN COMPLETADA");
            System.out.println("=".repeat(60));
            System.out.println("⏱️  Tiempo: " + (endTime - startTime) + "ms");
            System.out.println("🐢 Acciones generadas: " + acciones.size());
            System.out.println();
            
            // Mostrar algunas acciones (primeras 10)
            if (acciones.size() > 0) {
                System.out.println("📋 Primeras acciones:");
                int limit = Math.min(10, acciones.size());
                for (int i = 0; i < limit; i++) {
                    AccionTortuga accion = acciones.get(i);
                    System.out.printf("  %2d. %-20s → %s%n", 
                                    i + 1, 
                                    accion.getTipo(), 
                                    accion.getValor());
                }
                if (acciones.size() > 10) {
                    System.out.println("  ... (" + (acciones.size() - 10) + " acciones más)");
                }
            }
            
            // Mostrar ventana gráfica con la tortuga
            System.out.println("\n🎨 Abriendo ventana de visualización...");
            displayTurtleGraphics(acciones, file.getName());
            
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la ejecución:");
            System.err.println("   " + e.getMessage());
            
            if (debugMode) {
                System.err.println("\n📋 Stack trace:");
                e.printStackTrace();
            }
            
            System.exit(1);
        }
    }
    
    /**
     * Muestra el banner de la aplicación
     */
    private static void printBanner() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║          LogoTec Virtual Machine v" + VERSION + "          ║");
        System.out.println("║        Ejecutor de archivos .ltbc compilados         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Muestra información de uso
     */
    private static void printUsage() {
        System.out.println("Uso:");
        System.out.println("  java -jar logotec-vm.jar <archivo.ltbc> [opciones]");
        System.out.println();
        System.out.println("Opciones:");
        System.out.println("  -d, --debug    Modo debug (muestra ejecución instrucción por instrucción)");
        System.out.println("  -i, --info     Solo muestra información del archivo sin ejecutar");
        System.out.println("  -h, --help     Muestra esta ayuda");
        System.out.println();
        System.out.println("Ejemplos:");
        System.out.println("  java -jar logotec-vm.jar cuadrado.ltbc");
        System.out.println("  java -jar logotec-vm.jar mandala.ltbc --debug");
        System.out.println("  java -jar logotec-vm.jar programa.ltbc --info");
    }
    
    /**
     * Muestra ventana gráfica con el dibujo de la tortuga
     */
    private static void displayTurtleGraphics(List<AccionTortuga> acciones, String titulo) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("LogoTec VM - " + titulo);
            TurtleCanvas canvas = new TurtleCanvas(acciones);
            
            frame.add(canvas);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            // Iniciar animación
            canvas.animate();
        });
    }
    
    /**
     * Canvas que dibuja las acciones de la tortuga
     */
    static class TurtleCanvas extends JPanel {
        private final List<AccionTortuga> acciones;
        private int currentFrame = 0;
        private Timer animationTimer;
        
        // Estado de la tortuga
        private double x = 400;
        private double y = 300;
        private double angle = 0;  // En grados, 0 = arriba
        private boolean penDown = true;
        
        // Líneas dibujadas
        private java.util.List<Line2D.Double> lineas = new java.util.ArrayList<>();
        
        public TurtleCanvas(List<AccionTortuga> acciones) {
            this.acciones = acciones;
            setBackground(Color.WHITE);
        }
        
        public void animate() {
            if (acciones.isEmpty()) {
                return;
            }
            
            // Animación a 60fps
            animationTimer = new Timer(16, e -> {
                if (currentFrame < acciones.size()) {
                    AccionTortuga accion = acciones.get(currentFrame);
                    procesarAccion(accion);
                    currentFrame++;
                    repaint();
                } else {
                    animationTimer.stop();
                    System.out.println("\n✅ Animación completada");
                }
            });
            animationTimer.start();
        }
        
        private void procesarAccion(AccionTortuga accion) {
            double valor = accion.getValor();
            
            switch (accion.getTipo()) {
                case AVANZAR:
                    mover(valor);
                    break;
                case RETROCEDER:
                    mover(-valor);
                    break;
                case GIRAR:
                case GIRAR_DERECHA:
                    angle += valor;
                    break;
                case GIRAR_IZQUIERDA:
                    angle -= valor;
                    break;
                case BAJAR_LAPIZ:
                    penDown = true;
                    break;
                case LEVANTAR_LAPIZ:
                case SUBIR_LAPIZ:
                    penDown = false;
                    break;
                case CENTRAR:
                    x = getWidth() / 2.0;
                    y = getHeight() / 2.0;
                    angle = 0;
                    break;
                case OCULTAR_TORTUGA:
                case MOSTRAR_TORTUGA:
                    // No afecta el dibujo
                    break;
            }
        }
        
        private void mover(double distancia) {
            double radianes = Math.toRadians(angle - 90);
            double newX = x + distancia * Math.cos(radianes);
            double newY = y + distancia * Math.sin(radianes);
            
            if (penDown) {
                lineas.add(new Line2D.Double(x, y, newX, newY));
            }
            
            x = newX;
            y = newY;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            // Anti-aliasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                              RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibujar líneas
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            for (Line2D.Double linea : lineas) {
                g2.draw(linea);
            }
            
            // Dibujar tortuga
            g2.setColor(Color.RED);
            int turtleSize = 10;
            g2.fillOval((int)(x - turtleSize/2), (int)(y - turtleSize/2), 
                       turtleSize, turtleSize);
            
            // Dirección de la tortuga
            double radianes = Math.toRadians(angle - 90);
            int arrowLen = 15;
            int arrowX = (int)(x + arrowLen * Math.cos(radianes));
            int arrowY = (int)(y + arrowLen * Math.sin(radianes));
            g2.drawLine((int)x, (int)y, arrowX, arrowY);
            
            // Info
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("Frame: " + currentFrame + "/" + acciones.size(), 10, 20);
            g2.drawString("Líneas: " + lineas.size(), 10, 40);
        }
    }
}
