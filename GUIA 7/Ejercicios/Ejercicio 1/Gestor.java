package ejercicios;

import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes;
    private static final String ARCHIVO = "personajes.txt";
    private Scanner scanner;

    public Gestor() {
        personajes = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarPersonajes();
    }

    // ========== MÉTODOS PÚBLICOS PRINCIPALES ==========
    
    public void mostrarMenu() {
        System.out.println("\n🎮 GESTOR DE PERSONAJES 🎮");
        System.out.println("1. Mostrar todos los personajes");
        System.out.println("2. Añadir nuevo personaje");
        System.out.println("3. Buscar personaje por nombre");
        System.out.println("4. Modificar personaje");
        System.out.println("5. Borrar personaje");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: mostrarPersonajes(); break;
            case 2: añadirPersonaje(); break;
            case 3: buscarPersonaje(); break;
            case 4: modificarPersonaje(); break;
            case 5: borrarPersonaje(); break;
            case 6: System.out.println("¡Hasta pronto!"); break;
            default: System.out.println("Opción no válida.");
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }

    // ========== FUNCIONALIDADES BÁSICAS ==========

    private void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE PERSONAJES ===");
        System.out.printf("%-12s %-6s %-8s %-10s %-8s%n", 
            "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE");
        System.out.println("------------------------------------------------");
        
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }

    private void añadirPersonaje() {
        System.out.println("\n--- AÑADIR PERSONAJE ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        // Verificar si ya existe
        if (buscarPersonaje(nombre) != null) {
            System.out.println(" El personaje '" + nombre + "' ya existe.");
            return;
        }
        
        int vida = leerEntero("Vida: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int alcance = leerEntero("Alcance: ");
        
        try {
            Personaje nuevo = new Personaje(nombre, vida, ataque, defensa, alcance);
            personajes.add(nuevo);
            guardarCambios();
            System.out.println(" Personaje '" + nombre + "' añadido correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void buscarPersonaje() {
        System.out.print("\nNombre del personaje a buscar: ");
        String nombre = scanner.nextLine();
        
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("\n PERSONAJE ENCONTRADO:");
            System.out.printf("%-12s %-6s %-8s %-10s %-8s%n", 
                "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE");
            System.out.println("------------------------------------------------");
            System.out.println(p);
        } else {
            System.out.println(" Personaje '" + nombre + "' no encontrado.");
        }
    }

    private void modificarPersonaje() {
        System.out.print("\nNombre del personaje a modificar: ");
        String nombre = scanner.nextLine();
        
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("Personaje actual:");
            System.out.println(p);
            
            System.out.println("\nIngrese nuevos valores:");
            int vida = leerEntero("Nueva vida (" + p.getVida() + "): ");
            int ataque = leerEntero("Nuevo ataque (" + p.getAtaque() + "): ");
            int defensa = leerEntero("Nueva defensa (" + p.getDefensa() + "): ");
            int alcance = leerEntero("Nuevo alcance (" + p.getAlcance() + "): ");
            
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            
            guardarCambios();
            System.out.println(" Personaje '" + nombre + "' modificado correctamente.");
        } else {
            System.out.println(" Personaje '" + nombre + "' no encontrado.");
        }
    }

    private void borrarPersonaje() {
        System.out.print("\nNombre del personaje a borrar: ");
        String nombre = scanner.nextLine();
        
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarCambios();
            System.out.println(" Personaje '" + nombre + "' borrado correctamente.");
        } else {
            System.out.println(" Personaje '" + nombre + "' no encontrado.");
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private Personaje buscarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println(" El valor debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Por favor, ingrese un número válido.");
            }
        }
    }

    private void guardarCambios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Personaje p : personajes) {
                writer.println(p.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private void cargarPersonajes() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            crearPersonajesPorDefecto();
            return;
        }

        try (Scanner fileScanner = new Scanner(archivo)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Personaje p = Personaje.fromFileString(line);
                        personajes.add(p);
                    } catch (Exception e) {
                        System.out.println("Error al cargar línea: " + line);
                    }
                }
            }
            System.out.println(" Personajes cargados desde archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }

    private void crearPersonajesPorDefecto() {
        personajes.add(new Personaje("Caballero", 4, 2, 4, 2));
        personajes.add(new Personaje("Guerrero", 2, 4, 2, 4));
        personajes.add(new Personaje("Arquero", 2, 4, 1, 8));
        guardarCambios();
        System.out.println(" Personajes por defecto creados.");
    }
}
