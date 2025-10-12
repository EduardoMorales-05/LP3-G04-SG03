import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;

        System.out.println("=== DEMOSTRACIÓN try-with-resources ===");
        System.out.println("Leyendo líneas y AÑADIENDO al final de datos.txt");
        System.out.println("Escribe 'FIN' para terminar\n");

        // try-with-resources: PrintWriter se cierra automáticamente
        try (PrintWriter salida = new PrintWriter(new FileWriter("datos.txt", true))) {
            
            System.out.print("Introduce la primera línea: ");
            cadena = sc.nextLine();
            
            while (!cadena.equalsIgnoreCase("FIN")) {
                // Añadir la línea al FINAL del archivo (modo append)
                salida.println(cadena);
                System.out.print("Línea añadida. Siguiente línea o 'FIN': ");
                cadena = sc.nextLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        
        // El Scanner no es parte de try-with-resources en este ejemplo
        sc.close();
        
        System.out.println("\n¡Proceso completado! Recurso PrintWriter cerrado automáticamente.");
    }
}
