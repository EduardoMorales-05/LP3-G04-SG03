package ejercicios;

import java.util.Scanner;

public class MainGestor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        
        System.out.println("🎮 GESTOR DE PERSONAJES DE VIDEOJUEGO 🎮");
        
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(scanner, "Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    gestor.mostrarPersonajes();
                    break;
                case 2:
                    añadirPersonaje(gestor, scanner);
                    break;
                case 3:
                    buscarPersonaje(gestor, scanner);
                    break;
                case 4:
                    modificarPersonaje(gestor, scanner);
                    break;
                case 5:
                    borrarPersonaje(gestor, scanner);
                    break;
                case 6:
                    gestor.mostrarEstadisticas();
                    break;
                case 7:
                    salir = true;
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Mostrar todos los personajes");
        System.out.println("2. Añadir nuevo personaje");
        System.out.println("3. Buscar personaje por nombre");
        System.out.println("4. Modificar personaje");
        System.out.println("5. Borrar personaje");
        System.out.println("6. Mostrar estadísticas");
        System.out.println("7. Salir");
    }

    private static void añadirPersonaje(Gestor gestor, Scanner scanner) {
        System.out.println("\n--- AÑADIR PERSONAJE ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        int vida = leerEntero(scanner, "Vida: ");
        int ataque = leerEntero(scanner, "Ataque: ");
        int defensa = leerEntero(scanner, "Defensa: ");
        int alcance = leerEntero(scanner, "Alcance: ");
        
        try {
            Personaje nuevo = new Personaje(nombre, vida, ataque, defensa, alcance);
            if (gestor.añadirPersonaje(nuevo)) {
                System.out.println("✅ Personaje añadido correctamente.");
            } else {
                System.out.println("❌ El personaje ya existe.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void buscarPersonaje(Gestor gestor, Scanner scanner) {
        System.out.print("\nNombre del personaje a buscar: ");
        String nombre = scanner.nextLine();
        
        Personaje p = gestor.buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("\n✅ PERSONAJE ENCONTRADO:");
            System.out.printf("%-12s %-6s %-8s %-10s %-8s%n", 
                "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE");
            System.out.println("------------------------------------------------");
            System.out.println(p);
        } else {
            System.out.println("❌ Personaje no encontrado.");
        }
    }

    private static void modificarPersonaje(Gestor gestor, Scanner scanner) {
        System.out.print("\nNombre del personaje a modificar: ");
        String nombre = scanner.nextLine();
        
        Personaje p = gestor.buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("Personaje actual: " + p);
            System.out.println("Ingrese nuevos valores (0 para mantener actual):");
            
            int vida = leerEntero(scanner, "Nueva vida (" + p.getVida() + "): ");
            int ataque = leerEntero(scanner, "Nuevo ataque (" + p.getAtaque() + "): ");
            int defensa = leerEntero(scanner, "Nueva defensa (" + p.getDefensa() + "): ");
            int alcance = leerEntero(scanner, "Nuevo alcance (" + p.getAlcance() + "): ");
            
            if (vida == 0) vida = p.getVida();
            if (ataque == 0) ataque = p.getAtaque();
            if (defensa == 0) defensa = p.getDefensa();
            if (alcance == 0) alcance = p.getAlcance();
            
            if (gestor.modificarPersonaje(nombre, vida, ataque, defensa, alcance)) {
                System.out.println("✅ Personaje modificado correctamente.");
            }
        } else {
            System.out.println("❌ Personaje no encontrado.");
        }
    }

    private static void borrarPersonaje(Gestor gestor, Scanner scanner) {
        System.out.print("\nNombre del personaje a borrar: ");
        String nombre = scanner.nextLine();
        
        if (gestor.borrarPersonaje(nombre)) {
            System.out.println("✅ Personaje borrado correctamente.");
        } else {
            System.out.println("❌ Personaje no encontrado.");
        }
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido.");
            }
        }
    }
}
