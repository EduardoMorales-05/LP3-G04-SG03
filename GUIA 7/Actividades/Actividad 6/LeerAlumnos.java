package Acti;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerAlumnos {
    public static void main(String[] args) {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        Alumno alumno;

        try {
            fis = new FileInputStream("alumnos.dat");
            entrada = new ObjectInputStream(fis);
            
            System.out.println("=== ALUMNOS LEÍDOS DEL ARCHIVO ===");
            
            // Leer primer alumno
            alumno = (Alumno) entrada.readObject();
            System.out.println("1. " + alumno);
            
            // Leer segundo alumno
            alumno = (Alumno) entrada.readObject();
            System.out.println("2. " + alumno);
            
            // Leer tercer alumno
            alumno = (Alumno) entrada.readObject();
            System.out.println("3. " + alumno);
            
            System.out.println("=== LECTURA COMPLETADA ===");

        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Clase no encontrada - " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (entrada != null) entrada.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivos: " + e.getMessage());
            }
        }
    }
}
