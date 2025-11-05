import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Importar RichTextFX (asegúrate de tener la dependencia en el classpath)
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

// Si editorCodigo es RichTextFX CodeArea, habilitar números de línea
// import org.fxmisc.richtext.CodeArea;
// import org.fxmisc.richtext.LineNumberFactory;

public class MainController {
    @FXML
    private void initialize() {
        // Activar números de línea si el editor es CodeArea
        if (editorCodigo instanceof CodeArea) {
            CodeArea ca = (CodeArea) editorCodigo;
            ca.setParagraphGraphicFactory(LineNumberFactory.get(ca));
        } else {
            // Fallback (no hay gutter). Mantener resaltado por selección ante errores.
            System.out.println("[MainController] Editor sin soporte de gutter; se usará solo resaltado por selección.");
        }
    }

    @FXML
    private void compilar() {
        String codigo = editorCodigo.getText();
        
        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarError("El editor está vacío. Escribe código LogoTec para compilar.");
            return;
        }
        
        try {
            // Compilar
            astActual = CompiladorRealAdapter.compile(codigo);
            
            if (astActual != null) {
                mostrarMensaje("✅ Compilación exitosa. " + contarLineas(codigo) + " líneas procesadas.");
            }
        } catch (Exception e) {
            String rawError = e.getMessage();
            // Resaltar líneas detectadas en el mensaje
            resaltarLineasDesdeMensaje(rawError);
            // Mostrar mensaje con marca visual
            String errorMsg = formatearErrorConLinea(rawError);
            mostrarError("❌ Error de compilación:\n" + errorMsg);
        }
    }
    
    /**
     * Cuenta las líneas de código
     */
    private int contarLineas(String codigo) {
        if (codigo == null || codigo.isEmpty()) return 0;
        return codigo.split("\n", -1).length;
    }
    
    /**
     * Formatea el mensaje de error resaltando números de línea
     */
    private String formatearErrorConLinea(String mensaje) {
        if (mensaje == null) return "Error desconocido";
        // Normaliza “línea ” y añade icono
        return mensaje.replaceAll("(?i)l[ií]nea\\s", "📍 Línea ");
    }
    
    /**
     * Resalta una línea específica en el editor (para errores)
     */
    public void resaltarLinea(int numeroLinea) {
        String texto = editorCodigo.getText();
        String[] lineas = texto.split("\n", -1);
        
        if (numeroLinea < 1 || numeroLinea > lineas.length) return;
        
        // Calcular posición de inicio de la línea
        int inicio = 0;
        for (int i = 0; i < numeroLinea - 1; i++) {
            inicio += lineas[i].length() + 1; // +1 por el \n
        }
        
        int fin = inicio + lineas[numeroLinea - 1].length();
        
        // Seleccionar la línea con error
        editorCodigo.selectRange(inicio, fin);
        editorCodigo.requestFocus();
    }
    
    /**
     * Mueve el caret al inicio de una línea (para asegurar el scroll).
     */
    private void scrollToLine(int numeroLinea) {
        String texto = editorCodigo.getText();
        String[] lineas = texto.split("\n", -1);
        if (numeroLinea < 1 || numeroLinea > lineas.length) return;

        int inicio = 0;
        for (int i = 0; i < numeroLinea - 1; i++) {
            inicio += lineas[i].length() + 1;
        }
        // Posicionar caret al inicio de la línea y pedir foco
        editorCodigo.positionCaret(inicio);
        editorCodigo.requestFocus();
    }

    /**
     * Resalta las líneas mencionadas en un mensaje de error
     */
    private void resaltarLineasDesdeMensaje(String mensaje) {
        if (mensaje == null || mensaje.isEmpty()) return;
        // Soporta “línea N” y “línea N:M”
        Pattern pattern = Pattern.compile("(?i)l[ií]nea\\s+(\\d+)(?::\\d+)?");
        Matcher matcher = pattern.matcher(mensaje);
        Set<Integer> lineas = new LinkedHashSet<>();
        while (matcher.find()) {
            try {
                lineas.add(Integer.parseInt(matcher.group(1)));
            } catch (NumberFormatException ignored) { }
        }
        // Si no se detectó ninguna, seleccionar la primera línea
        if (lineas.isEmpty()) {
            scrollToLine(1);
            resaltarLinea(1);
            return;
        }
        // Ir primero a la primera línea para asegurar scroll
        int primera = lineas.iterator().next();
        scrollToLine(primera);
        // Luego resaltar todas las líneas detectadas
        for (Integer linea : lineas) {
            resaltarLinea(linea);
        }
    }
}
