package defaul;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrearAgenda {

    public static void main(String[] args) {
        System.out.println("=== CREANDO ARCHIVO AGENDA.TXT ===");
        
        // Datos de ejemplo para la agenda
        String[][] contactos = {
            {"Juan", "292929", "Calle Uno 123"},
            {"Maria", "202020", "Jr. Camana 209"},
            {"Daniel", "848484", "Av. Ejercito 298"},
            {"Rosa", "272727", "Urb. Rosales A-10"},
            {"Pedro", "313131", "Av. Libertad 456"},
            {"Ana", "444444", "Calle Real 789"},
            {"Luis", "555555", "Plaza Central 321"},
            {"Carmen", "666666", "Av. Sol 654"},
            {"Carlos", "777777", "Calle Luna 987"},
            {"Laura", "888888", "Urb. Jardines 147"}
        };
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("agenda.txt"))) {
            
            for (String[] contacto : contactos) {
                writer.println(contacto[0]);    // Nombre
                writer.println(contacto[1]);    // Teléfono
                writer.println(contacto[2]);    // Dirección
            }
            
            System.out.println("Archivo 'agenda.txt' creado exitosamente");
            System.out.println("Total de contactos agregados: " + contactos.length);
            System.out.println("El archivo esta listo para usar con AppContactos");
            
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        
        // Mostrar contenido creado
        System.out.println("=== CONTENIDO CREADO ===");
        for (String[] contacto : contactos) {
            System.out.println(contacto[0] + " | " + contacto[1] + " | " + contacto[2]);
        }
    }
}
