package com.miorganizacion.logotec.vm;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.backend.*;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga.Tipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Interfaz gráfica principal para ejecutar programas LogoTec.
 * Incluye editor de código, canvas de dibujo y controles.
 */
public class VMMain extends JFrame {
    
    private JTextArea editorArea;
    private JTextArea consoleArea;
    private TurtleCanvas canvas;
    private JButton btnCompilar;
    private JButton btnEjecutar;
    private JButton btnLimpiar;
    private JButton btnCargar;
    private JButton btnGuardar;
    private JSlider speedSlider;
    
    // Estado de compilación
    private ObjectCodeGenerator.Result compiledCode;
    private boolean isCompiled = false;
    
    public VMMain() {
        super("LogoTec IDE - Máquina Virtual");
        initUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
    
    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        
        // Panel izquierdo: Editor
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Editor de Código"));
        
        editorArea = new JTextArea();
        editorArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        editorArea.setTabSize(2);
        editorArea.setText(getDefaultCode());
        JScrollPane editorScroll = new JScrollPane(editorArea);
        editorScroll.setPreferredSize(new Dimension(400, 500));
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnCompilar = new JButton("Compilar");
        btnCompilar.addActionListener(e -> compilar());
        
        btnEjecutar = new JButton("Ejecutar");
        btnEjecutar.addActionListener(e -> ejecutar());
        btnEjecutar.setEnabled(false);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiar());
        
        btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(e -> cargarArchivo());
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarArchivo());
        
        buttonPanel.add(btnCompilar);
        buttonPanel.add(btnEjecutar);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(new JLabel("  "));
        buttonPanel.add(btnCargar);
        buttonPanel.add(btnGuardar);
        
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
        
        consoleArea = new JTextArea(8, 40);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        consoleArea.setEditable(false);
        consoleArea.setBackground(new Color(30, 30, 30));
        consoleArea.setForeground(Color.GREEN);
        JScrollPane consoleScroll = new JScrollPane(consoleArea);
        consoleScroll.setBorder(BorderFactory.createTitledBorder("Consola"));
        
        // Nuevo: divisor ajustable para editor/consola
        JSplitPane consoleSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorScroll, consoleScroll);
        consoleSplit.setResizeWeight(0.75);
        consoleSplit.setOneTouchExpandable(true);
        leftPanel.add(consoleSplit, BorderLayout.CENTER);
        
        // Panel derecho: Canvas
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Área de Dibujo"));
        
        canvas = new TurtleCanvas();
        canvas.setPreferredSize(new Dimension(600, 600));
        canvas.setBackground(Color.WHITE);
        rightPanel.add(canvas, BorderLayout.CENTER);
        
        // Control de velocidad
        JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        speedPanel.add(new JLabel("Velocidad:"));
        speedSlider = new JSlider(1, 100, 50);
        speedSlider.setPreferredSize(new Dimension(200, 30));
        speedPanel.add(speedSlider);
        rightPanel.add(speedPanel, BorderLayout.SOUTH);
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(450);
        add(splitPane, BorderLayout.CENTER);
    }
    
    private String getDefaultCode() {
        return "// Programa de ejemplo - Cuadrado\n" +
               "Haz lado 100\n" +
               "\n" +
               "centro\n" +
               "bajalapiz\n" +
               "\n" +
               "avanza lado\n" +
               "giraderecha 90\n" +
               "avanza lado\n" +
               "giraderecha 90\n" +
               "avanza lado\n" +
               "giraderecha 90\n" +
               "avanza lado\n" +
               "\n" +
               "subelapiz\n";
    }
    
    private void compilar() {
        String codigo = editorArea.getText();
        consoleArea.setText("");
        log("╔═══════════════════════════════════════════════════════╗");
        log("║              Compilando programa...                   ║");
        log("╚═══════════════════════════════════════════════════════╝\n");
        
        try {
            // Paso 1: AST
            log("🔧 Generando AST...");
            ProgramNode ast = CompiladorRealAdapter.compile(codigo);
            if (ast == null) {
                log("❌ Error: No se pudo generar el AST");
                return;
            }
            log("✅ AST generado");
            
            // Paso 2: IR
            log("🔧 Generando código intermedio (IR)...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(ast);
            log("✅ IR generado: " + irResult.instructions.size() + " instrucciones");
            
            // Paso 3: Assembly
            log("🔧 Generando Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);
            log("✅ Assembly generado: " + asmCode.size() + " instrucciones");
            
            // Paso 4: Bytecode
            log("🔧 Generando Bytecode...");
            ObjectCodeGenerator objGen = new ObjectCodeGenerator();
            compiledCode = objGen.generate(asmCode);
            log("✅ Bytecode generado: " + compiledCode.bytecode.size() + " instrucciones");
            
            isCompiled = true;
            btnEjecutar.setEnabled(true);
            
            log("\n═══════════════════════════════════════════════════════");
            log("✅ COMPILACIÓN EXITOSA");
            log("   Listo para ejecutar");
            log("═══════════════════════════════════════════════════════");
            
        } catch (Exception e) {
            log("\n❌ Error de compilación:");
            log("   " + e.getMessage());
            isCompiled = false;
            btnEjecutar.setEnabled(false);
        }
    }
    
    private void ejecutar() {
        if (!isCompiled || compiledCode == null) {
            log("⚠️ Primero debe compilar el programa");
            return;
        }
        
        log("\n╔═══════════════════════════════════════════════════════╗");
        log("║      Ejecutando Bytecode en Máquina Virtual          ║");
        log("╚═══════════════════════════════════════════════════════╝\n");
        
        try {
            log("🔧 Cargando programa en la VM...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.loadProgram(compiledCode);
            log("✅ Programa cargado: " + compiledCode.bytecode.size() + " instrucciones");
            
            log("\n🚀 Ejecutando bytecode...");
            long startTime = System.currentTimeMillis();
            vm.execute();
            long endTime = System.currentTimeMillis();
            log("✅ Ejecución completada en " + (endTime - startTime) + "ms");
            
            // Obtener acciones
            List<AccionTortuga> acciones = vm.getAcciones();
            
            log("\n═══════════════════════════════════════════════════════");
            log("✅ EJECUCIÓN EXITOSA");
            log("   Acciones generadas: " + acciones.size());
            log("═══════════════════════════════════════════════════════");
            
            // Debug: mostrar primeras acciones
            if (!acciones.isEmpty()) {
                log("\n🔍 DEBUG - Primeras 5 acciones:");
                for (int i = 0; i < Math.min(5, acciones.size()); i++) {
                    log("   " + i + ": " + acciones.get(i));
                }
                if (acciones.size() > 5) {
                    log("🔍 DEBUG - Últimas 5 acciones:");
                    for (int i = Math.max(5, acciones.size() - 5); i < acciones.size(); i++) {
                        log("   " + i + ": " + acciones.get(i));
                    }
                }
            }
            
            // Animar el dibujo
            animarDibujo(acciones);
            
        } catch (Exception e) {
            log("\n❌ Error de ejecución:");
            log("   " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void animarDibujo(List<AccionTortuga> acciones) {
        canvas.reset();
        
        int delay = 101 - speedSlider.getValue(); // Invertir para que más = más rápido
        delay = Math.max(10, delay * 3);
        
        log("\n🎨 Iniciando animación del dibujo...");
        log("⏱️  Delay: " + delay + "ms, Acciones/frame: 1");
        log("⏱️  Total frames: " + acciones.size() + ", Duración estimada: " + (acciones.size() * delay / 1000.0) + " segundos");
        
        final int finalDelay = delay;
        
        SwingWorker<Void, AccionTortuga> worker = new SwingWorker<Void, AccionTortuga>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (AccionTortuga accion : acciones) {
                    publish(accion);
                    Thread.sleep(finalDelay);
                }
                return null;
            }
            
            @Override
            protected void process(List<AccionTortuga> chunks) {
                for (AccionTortuga accion : chunks) {
                    canvas.procesarAccion(accion);
                }
            }
            
            @Override
            protected void done() {
                log("\n✅ Animación finalizada");
            }
        };
        
        worker.execute();
    }
    
    private void limpiar() {
        canvas.reset();
        consoleArea.setText("");
        isCompiled = false;
        compiledCode = null;
        btnEjecutar.setEnabled(false);
        log("🧹 Canvas y estado limpiados");
    }
    
    private void cargarArchivo() {
        JFileChooser fc = new JFileChooser("test");
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("LogoTec files", "logo"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                StringBuilder content = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                editorArea.setText(content.toString());
                log("📂 Archivo cargado: " + file.getName());
                isCompiled = false;
                btnEjecutar.setEnabled(false);
            } catch (IOException e) {
                log("❌ Error al cargar archivo: " + e.getMessage());
            }
        }
    }
    
    private void guardarArchivo() {
        JFileChooser fc = new JFileChooser("test");
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("LogoTec files", "logo"));
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                if (!file.getName().endsWith(".logo")) {
                    file = new File(file.getAbsolutePath() + ".logo");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(editorArea.getText());
                writer.close();
                log("💾 Archivo guardado: " + file.getName());
            } catch (IOException e) {
                log("❌ Error al guardar archivo: " + e.getMessage());
            }
        }
    }
    
    private void log(String message) {
        consoleArea.append(message + "\n");
        consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
    }
    
    // ==================== CANVAS DE TORTUGA ====================
    
    private class TurtleCanvas extends JPanel {
        private double x, y;
        private double angle;
        private boolean penDown;
        private Color penColor;
        private java.util.List<Line> lines;
        
        public TurtleCanvas() {
            lines = new ArrayList<>();
            reset();
        }
        
        public void reset() {
            x = 0;
            y = 0;
            angle = -90; // Apuntando hacia arriba
            penDown = true;
            penColor = Color.BLACK;
            lines.clear();
            repaint();
        }
        
        public void procesarAccion(AccionTortuga accion) {
            double valor = accion.getValor();
            
            switch (accion.getTipo()) {
                case AVANZAR:
                    mover(valor);
                    break;
                case GIRAR:
                    angle += valor;
                    break;
                case BAJAR_LAPIZ:
                    penDown = true;
                    break;
                case LEVANTAR_LAPIZ:
                    penDown = false;
                    break;
                case MOVER_A_POSICION:
                    x = accion.getX() + getWidth() / 2.0;
                    y = getHeight() / 2.0 - accion.getY();
                    break;
                case CAMBIAR_RUMBO:
                    angle = -90 + valor; // Ajustar para que 0 = arriba
                    break;
                case CAMBIAR_COLOR:
                    int r = accion.getR();
                    int g = accion.getG();
                    int b = accion.getB();
                    System.out.println("🎨 Cambiando color a RGB(" + r + ", " + g + ", " + b + ")");
                    penColor = new Color(
                        Math.max(0, Math.min(255, r)),
                        Math.max(0, Math.min(255, g)),
                        Math.max(0, Math.min(255, b))
                    );
                    break;
                default:
                    break;
            }
            repaint();
        }
        
        private void mover(double distancia) {
            double centerX = getWidth() / 2.0;
            double centerY = getHeight() / 2.0;
            
            double startX = centerX + x;
            double startY = centerY - y;
            
            double rad = Math.toRadians(angle);
            x += distancia * Math.cos(rad);
            y -= distancia * Math.sin(rad);
            
            double endX = centerX + x;
            double endY = centerY - y;
            
            if (penDown) {
                lines.add(new Line(startX, startY, endX, endY, penColor));
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2));
            
            // Dibujar líneas
            for (Line line : lines) {
                g2d.setColor(line.color);
                g2d.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
            }
            
            // Dibujar tortuga
            double centerX = getWidth() / 2.0;
            double centerY = getHeight() / 2.0;
            double tx = centerX + x;
            double ty = centerY - y;
            
            g2d.setColor(Color.GREEN);
            int size = 10;
            double rad = Math.toRadians(angle);
            int[] xPoints = {
                (int) (tx + size * Math.cos(rad)),
                (int) (tx + size * Math.cos(rad + 2.5)),
                (int) (tx + size * Math.cos(rad - 2.5))
            };
            int[] yPoints = {
                (int) (ty + size * Math.sin(rad)),
                (int) (ty + size * Math.sin(rad + 2.5)),
                (int) (ty + size * Math.sin(rad - 2.5))
            };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        
        private class Line {
            double x1, y1, x2, y2;
            Color color;
            
            Line(double x1, double y1, double x2, double y2, Color color) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
                this.color = color;
            }
        }
    }
    
    // ==================== MAIN ====================
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Usar look and feel por defecto
            }
            new VMMain().setVisible(true);
        });
    }
}
