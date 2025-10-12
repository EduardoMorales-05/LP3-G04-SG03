import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Agenda {
    private ArrayPersona lista;
    
    public Agenda() {
        this.lista = cargaContactos();
        System.out.println("Agenda cargada. Contactos encontrados: " + lista.getTamano());
    }
    
    public void bucle() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        
        System.out.println("\n=== SISTEMA DE AGENDA ===");
        System.out.println("Busque contactos por nombre (Enter para salir)");
        
        while (true) {
            System.out.print("\nIngrese nombre a buscar: ");
            nombre = sc.nextLine().trim();
            
            if (nombre.isEmpty()) {
                System.out.println("Saliendo del sistema...");
                break;
            }
            
            lista.buscarPorNombre(nombre);
        }
        
        sc.close();
    }
    
    private ArrayPersona cargaContactos() {
        ArrayPersona directorio = new ArrayPersona();
        
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.txt"))) {
            String nombre, telefono, direccion;
            
            while ((nombre = br.readLine()) != null) {
                telefono = br.readLine();
                direccion = br.readLine();
                
                if (telefono != null && direccion != null) {
                    Persona nuevaPersona = new Persona(nombre.trim(), telefono.trim(), direccion.trim());
                    directorio.addArray(nuevaPersona);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error al cargar la agenda: " + e.getMessage());
            System.out.println("Asegúrate de que el archivo 'agenda.txt' existe");
        }
        
        return directorio;
    }
}
