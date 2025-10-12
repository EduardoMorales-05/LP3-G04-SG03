package defaul;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFilePrueba {

    public static void main(String[] args) throws IOException {
        
        // PRIMERO: CREAR el archivo datos.txt
        try {
            FileOutputStream fos = new FileOutputStream("datos.txt");
            String contenido = "Línea 1: Hola Mundo\n" +
                              "Línea 2: Java FileInputStream\n" +
                              "Línea 3: Manejo de archivos\n" +
                              "Línea 4: Actividad 4 completada\n";
            fos.write(contenido.getBytes());
            fos.close();
            System.out.println("✓ Archivo datos.txt creado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
            return;
        }

        // SEGUNDO: LEER el archivo datos.txt
        FileInputStream file;
        byte b[] = new byte[1024];
        try {
            file = new FileInputStream("datos.txt");
            int bytesLeidos = file.read(b);
            String s = new String(b, 0, bytesLeidos);
            
            // Mostrar contenido en consola
            System.out.println("\n=== CONTENIDO DE datos.txt ===");
            System.out.println(s);
            System.out.println("===============================");
            System.out.println("Bytes leídos: " + bytesLeidos);
            
            file.close();
        }
        catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
}
