package Main;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ContadorPalabras {

    public static void main(String[] args) {
        System.out.println("=== CONTADOR DE PALABRAS CON JFileChooser ===");
        
        boolean continuar = true;
        while (continuar) {
            try {
                // Usar JFileChooser para seleccionar archivo
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccione el archivo de texto a analizar");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                
                int resultado = fileChooser.showOpenDialog(null);
                
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    System.out.println("Analizando archivo: " + archivoSeleccionado.getName());
                    analizarArchivo(archivoSeleccionado);
                } else {
                    System.out.println("Selección cancelada por el usuario.");
                }
                
                // Preguntar si desea continuar
                int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea analizar otro archivo?",
                    "Continuar",
                    JOptionPane.YES_NO_OPTION);
                
                continuar = (respuesta == JOptionPane.YES_OPTION);
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                    "Error: " + e.getMessage() + "\nIntente con otro archivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        System.out.println("Programa terminado.");
    }

    public static void analizarArchivo(File archivo) throws IOException {
        // Leer contenido del archivo
        String contenido = leerArchivoCompleto(archivo);
        
        // Calcular estadísticas
        int totalLineas = contarLineas(contenido);
        int totalPalabras = contarPalabras(contenido);
        int totalCaracteres = contarCaracteres(contenido);
        double promedioPalabrasPorLinea = totalLineas > 0 ? (double) totalPalabras / totalLineas : 0;
        Map<String, Integer> palabrasFrecuentes = obtenerPalabrasMasFrecuentes(contenido);
        
        // Mostrar resultados en consola
        mostrarResultadosEnConsola(archivo.getName(), totalLineas, totalPalabras, 
                                 totalCaracteres, promedioPalabrasPorLinea, palabrasFrecuentes);
    }

    private static String leerArchivoCompleto(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        
        // Eliminar último salto de línea si existe
        if (contenido.length() > 0 && contenido.charAt(contenido.length() - 1) == '\n') {
            contenido.setLength(contenido.length() - 1);
        }
        
        return contenido.toString();
    }

    private static int contarLineas(String contenido) {
        if (contenido.isEmpty()) return 0;
        int count = 1;
        for (int i = 0; i < contenido.length(); i++) {
            if (contenido.charAt(i) == '\n') count++;
        }
        return count;
    }

    private static int contarPalabras(String contenido) {
        if (contenido.isEmpty()) return 0;
        
        int contador = 0;
        boolean enPalabra = false;
        
        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                if (!enPalabra) {
                    contador++;
                    enPalabra = true;
                }
            } else {
                enPalabra = false;
            }
        }
        return contador;
    }

    private static int contarCaracteres(String contenido) {
        return contenido.replace("\n", "").replace("\r", "").length();
    }

    private static Map<String, Integer> obtenerPalabrasMasFrecuentes(String contenido) {
        Map<String, Integer> frecuencia = new HashMap<>();
        StringBuilder palabraActual = new StringBuilder();
        
        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                palabraActual.append(Character.toLowerCase(c));
            } else {
                if (palabraActual.length() > 0) {
                    String palabra = palabraActual.toString();
                    frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
                    palabraActual.setLength(0);
                }
            }
        }
        
        // Última palabra
        if (palabraActual.length() > 0) {
            String palabra = palabraActual.toString();
            frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
        }
        
        // Ordenar por frecuencia
        return ordenarPorFrecuencia(frecuencia);
    }

    private static Map<String, Integer> ordenarPorFrecuencia(Map<String, Integer> frecuencia) {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(frecuencia.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        Map<String, Integer> resultado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.put(entry.getKey(), entry.getValue());
        }
        return resultado;
    }

    private static void mostrarResultadosEnConsola(String nombreArchivo, int totalLineas, 
                                                 int totalPalabras, int totalCaracteres,
                                                 double promedioPalabrasPorLinea,
                                                 Map<String, Integer> palabrasFrecuentes) {
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("RESULTADOS DEL ANÁLISIS: " + nombreArchivo);
        System.out.println("=".repeat(70));
        
        System.out.println("• Total de líneas: " + totalLineas);
        System.out.println("• Total de palabras: " + totalPalabras);
        System.out.println("• Total de caracteres (sin fin de línea): " + totalCaracteres);
        System.out.printf("• Promedio de palabras por línea: %.2f%n", promedioPalabrasPorLinea);
        
        System.out.println("\n--- PALABRAS MÁS FRECUENTES ---");
        int contador = 0;
        for (Map.Entry<String, Integer> entry : palabrasFrecuentes.entrySet()) {
            if (contador >= 10) break;
            System.out.printf("  %-15s → %2d ocurrencia%s%n",
                entry.getKey(), entry.getValue(), entry.getValue() > 1 ? "s" : "");
            contador++;
        }
        System.out.println("=".repeat(70));
    }
}
