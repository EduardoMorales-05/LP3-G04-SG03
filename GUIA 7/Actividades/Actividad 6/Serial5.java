package Acti;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serial5 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        Alumno a;
        Fecha f;

        try {
            fos = new FileOutputStream("alumnos.dat");
            salida = new ObjectOutputStream(fos);
            
            f = new Fecha(5, 9, 2011);
            a = new Alumno("12345678A", "Lucas González", 20, f);
            salida.writeObject(a);
            
            f = new Fecha(7, 9, 2011);
            a = new Alumno("98765432B", "Anacleto Jiménez", 19, f);
            salida.writeObject(a);
            
            f = new Fecha(8, 9, 2011);
            a = new Alumno("78234212C", "María Zapata", 21, f);
            salida.writeObject(a);
            
            System.out.println("Datos de alumnos serializados correctamente en 'alumnos.dat'");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (fos != null) fos.close();
                if (salida != null) salida.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivos: " + e.getMessage());
            }
        }
    }
}
