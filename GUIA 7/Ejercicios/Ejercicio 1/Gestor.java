package ejercicios;

import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes;
    private static final String ARCHIVO = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarPersonajes();
    }

    // Añadir personaje
    public boolean añadirPersonaje(Personaje p) {
        for (Personaje existente : personajes) {
            if (existente.getNombre().equalsIgnoreCase(p.getNombre())) {
                return false; // Personaje ya existe
            }
        }
        personajes.add(p);
        guardarCambios();
        return true;
    }

    // Mostrar todos los personajes
    public void mostrarPersonajes() {
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        System.out.printf("%-12s %-6s %-8s %-10s %-8s%n", 
            "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE");
        System.out.println("------------------------------------------------");
        
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }

    // Buscar personaje por nombre
    public Personaje buscarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Modificar personaje
    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            guardarCambios();
            return true;
        }
        return false;
    }

    // Borrar personaje
    public boolean borrarPersonaje(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarCambios();
            return true;
        }
        return false;
    }

    // Guardar cambios en archivo
    private void guardarCambios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Personaje p : personajes) {
                writer.println(p.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Cargar personajes desde archivo
    private void cargarPersonajes() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            // Crear personajes por defecto si el archivo no existe
            crearPersonajesPorDefecto();
            return;
        }

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Personaje p = Personaje.fromFileString(line);
                        personajes.add(p);
                    } catch (Exception e) {
                        System.out.println("Error al cargar línea: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }

    // Crear personajes por defecto
    private void crearPersonajesPorDefecto() {
        personajes.add(new Personaje("Caballero", 4, 2, 4, 2));
        personajes.add(new Personaje("Guerrero", 2, 4, 2, 4));
        personajes.add(new Personaje("Arquero", 2, 4, 1, 8));
        guardarCambios();
    }

    // Estadísticas
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas.");
            return;
        }

        int totalVida = 0, totalAtaque = 0, totalDefensa = 0, totalAlcance = 0;
        
        for (Personaje p : personajes) {
            totalVida += p.getVida();
            totalAtaque += p.getAtaque();
            totalDefensa += p.getDefensa();
            totalAlcance += p.getAlcance();
        }

        int total = personajes.size();
        
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Vida promedio: %.2f%n", (double) totalVida / total);
        System.out.printf("Ataque promedio: %.2f%n", (double) totalAtaque / total);
        System.out.printf("Defensa promedio: %.2f%n", (double) totalDefensa / total);
        System.out.printf("Alcance promedio: %.2f%n", (double) totalAlcance / total);
    }
}
