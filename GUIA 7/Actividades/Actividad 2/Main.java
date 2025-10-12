import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Ruta más simple en tu proyecto actual
        String rutaArchivo = "datos.txt";
        
        System.out.println("Archivo se creará en: " + new java.io.File(rutaArchivo).getAbsolutePath());
        
        try (PrintWriter salida = new PrintWriter(new FileWriter(rutaArchivo))) {
            System.out.println("Introduce texto. Para acabar escribe FIN:");
            String cadena;
            
            while (true) {
                cadena = sc.nextLine();
                if (cadena.equalsIgnoreCase("FIN")) break;
                salida.println(cadena);
                System.out.println("✓ Línea guardada");
            }
            
            System.out.println("✓ Texto guardado en: " + rutaArchivo);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
