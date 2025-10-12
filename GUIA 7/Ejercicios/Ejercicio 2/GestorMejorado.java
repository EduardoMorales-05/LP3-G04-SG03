package ejercicios;

import java.io.*;
import java.util.*;

public class GestorMejorado {
    private List<PersonajeMejorado> personajes;
    private static final String ARCHIVO = "personajes_mejorados.txt";
    private static final String ARCHIVO_IMPORTACION = "personajes_importar.txt";
    private Scanner scanner;

    public GestorMejorado() {
        personajes = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarPersonajes();
    }

    // ========== MÉTODOS PÚBLICOS PRINCIPALES ==========
    
    public void mostrarMenu() {
        System.out.println("\n🎮 GESTOR DE PERSONAJES MEJORADO 🎮");
        System.out.println("1. Mostrar todos los personajes");
        System.out.println("2. Añadir nuevo personaje");
        System.out.println("3. Buscar personaje por nombre");
        System.out.println("4. Modificar personaje");
        System.out.println("5. Borrar personaje");
        System.out.println("6. Filtrar personajes por atributos");
        System.out.println("7. Cargar personajes aleatorios");
        System.out.println("8. Actualizar atributo individual");
        System.out.println("9. Mostrar estadísticas");
        System.out.println("10. Importar personajes desde archivo");
        System.out.println("11. Subir nivel de personaje");
        System.out.println("12. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: mostrarPersonajes(); break;
            case 2: añadirPersonaje(); break;
            case 3: buscarPersonaje(); break;
            case 4: modificarPersonaje(); break;
            case 5: borrarPersonaje(); break;
            case 6: filtrarPersonajes(); break;
            case 7: cargarPersonajesAleatorios(); break;
            case 8: actualizarAtributoIndividual(); break;
            case 9: mostrarEstadisticas(); break;
            case 10: importarPersonajes(); break;
            case 11: subirNivelPersonaje(); break;
            case 12: System.out.println("¡Hasta pronto!"); break;
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
        System.out.printf("%-12s %-6s %-8s %-10s %-8s %-6s%n", 
            "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE", "NIVEL");
        System.out.println("--------------------------------------------------------");
        
        for (PersonajeMejorado p : personajes) {
            System.out.println(p);
        }
    }

    private void añadirPersonaje() {
        System.out.println("\n--- AÑADIR PERSONAJE ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        if (buscarPersonaje(nombre) != null) {
            System.out.println("❌ El personaje '" + nombre + "' ya existe.");
            return;
        }
        
        int vida = leerEntero("Vida: ");
        int ataque = leerEntero("Ataque: ");
        int defensa = leerEntero("Defensa: ");
        int alcance = leerEntero("Alcance: ");
        int nivel = leerEntero("Nivel: ");
        
        try {
            PersonajeMejorado nuevo = new PersonajeMejorado(nombre, vida, ataque, defensa, alcance, nivel);
            personajes.add(nuevo);
            guardarCambios();
            System.out.println("✅ Personaje '" + nombre + "' añadido correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarPersonaje() {
        System.out.print("\nNombre del personaje a buscar: ");
        String nombre = scanner.nextLine();
        
        PersonajeMejorado p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("\n✅ PERSONAJE ENCONTRADO:");
            System.out.printf("%-12s %-6s %-8s %-10s %-8s %-6s%n", 
                "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE", "NIVEL");
            System.out.println("--------------------------------------------------------");
            System.out.println(p);
        } else {
            System.out.println("❌ Personaje '" + nombre + "' no encontrado.");
        }
    }

    private void modificarPersonaje() {
        System.out.print("\nNombre del personaje a modificar: ");
        String nombre = scanner.nextLine();
        
        PersonajeMejorado p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("Personaje actual:");
            System.out.println(p);
            
            System.out.println("\nIngrese nuevos valores:");
            int vida = leerEntero("Nueva vida (" + p.getVida() + "): ");
            int ataque = leerEntero("Nuevo ataque (" + p.getAtaque() + "): ");
            int defensa = leerEntero("Nueva defensa (" + p.getDefensa() + "): ");
            int alcance = leerEntero("Nuevo alcance (" + p.getAlcance() + "): ");
            int nivel = leerEntero("Nuevo nivel (" + p.getNivel() + "): ");
            
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            p.setNivel(nivel);
            
            guardarCambios();
            System.out.println("✅ Personaje '" + nombre + "' modificado correctamente.");
        } else {
            System.out.println("❌ Personaje '" + nombre + "' no encontrado.");
        }
    }

    private void borrarPersonaje() {
        System.out.print("\nNombre del personaje a borrar: ");
        String nombre = scanner.nextLine();
        
        PersonajeMejorado p = buscarPersonaje(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarCambios();
            System.out.println("✅ Personaje '" + nombre + "' borrado correctamente.");
        } else {
            System.out.println("❌ Personaje '" + nombre + "' no encontrado.");
        }
    }

    // ========== NUEVAS FUNCIONALIDADES ==========

    // 1. FILTRAR PERSONAJES POR ATRIBUTOS
    private void filtrarPersonajes() {
        System.out.println("\n--- FILTRAR PERSONAJES POR ATRIBUTO ---");
        System.out.println("Atributos disponibles: vida, ataque, defensa, alcance, nivel");
        System.out.print("Ingrese el atributo para filtrar: ");
        String atributo = scanner.nextLine();
        
        System.out.print("¿Orden ascendente? (s/n): ");
        boolean ascendente = scanner.nextLine().equalsIgnoreCase("s");
        
        // Ordenar la lista
        List<PersonajeMejorado> listaOrdenada = new ArrayList<>(personajes);
        listaOrdenada.sort((p1, p2) -> {
            int valor1 = p1.getAtributo(atributo);
            int valor2 = p2.getAtributo(atributo);
            return ascendente ? Integer.compare(valor1, valor2) : Integer.compare(valor2, valor1);
        });

        System.out.println("\n=== PERSONAJES ORDENADOS POR " + atributo.toUpperCase() + 
                         " (" + (ascendente ? "ASCENDENTE" : "DESCENDENTE") + ") ===");
        System.out.printf("%-12s %-6s %-8s %-10s %-8s %-6s%n", 
            "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE", "NIVEL");
        System.out.println("--------------------------------------------------------");
        
        for (PersonajeMejorado p : listaOrdenada) {
            System.out.println(p);
        }
    }

    // 2. CARGAR PERSONAJES ALEATORIOS
    private void cargarPersonajesAleatorios() {
        System.out.print("\n¿Cuántos personajes aleatorios desea crear? ");
        int cantidad = leerEntero("Cantidad: ");
        
        String[] nombres = {"Dragón", "Mago", "Guerrero", "Arquero", "Caballero", "Brujo", "Ninja", "Samurái"};
        Random random = new Random();
        int creados = 0;

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[random.nextInt(nombres.length)] + " " + (i + 1);
            
            // Verificar si ya existe
            if (buscarPersonaje(nombre) == null) {
                int vida = random.nextInt(10) + 1;
                int ataque = random.nextInt(8) + 1;
                int defensa = random.nextInt(8) + 1;
                int alcance = random.nextInt(5) + 1;
                int nivel = random.nextInt(5) + 1;

                PersonajeMejorado p = new PersonajeMejorado(nombre, vida, ataque, defensa, alcance, nivel);
                personajes.add(p);
                creados++;
                System.out.println("✅ Creado: " + nombre);
            }
        }
        
        guardarCambios();
        System.out.println("\n✅ Se crearon " + creados + " personajes aleatorios.");
    }

    // 3. ACTUALIZAR ATRIBUTOS INDIVIDUALES
    private void actualizarAtributoIndividual() {
        System.out.print("\nNombre del personaje: ");
        String nombre = scanner.nextLine();
        
        PersonajeMejorado p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("Atributos disponibles: vida, ataque, defensa, alcance, nivel");
            System.out.print("Ingrese el atributo a actualizar: ");
            String atributo = scanner.nextLine();
            
            System.out.print("Nuevo valor para " + atributo + ": ");
            int nuevoValor = leerEntero("Valor: ");
            
            boolean actualizado = false;
            switch (atributo.toLowerCase()) {
                case "vida": 
                    p.setVida(nuevoValor); 
                    actualizado = true;
                    break;
                case "ataque": 
                    p.setAtaque(nuevoValor); 
                    actualizado = true;
                    break;
                case "defensa": 
                    p.setDefensa(nuevoValor); 
                    actualizado = true;
                    break;
                case "alcance": 
                    p.setAlcance(nuevoValor); 
                    actualizado = true;
                    break;
                case "nivel": 
                    p.setNivel(nuevoValor); 
                    actualizado = true;
                    break;
                default: 
                    System.out.println("❌ Atributo no válido.");
            }
            
            if (actualizado) {
                guardarCambios();
                System.out.println("✅ " + atributo + " actualizado a " + nuevoValor + " para " + nombre);
            }
        } else {
            System.out.println("❌ Personaje no encontrado.");
        }
    }

    // 4. MOSTRAR ESTADÍSTICAS
    private void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas.");
            return;
        }

        int totalVida = 0, totalAtaque = 0, totalDefensa = 0, totalAlcance = 0, totalNivel = 0;
        
        for (PersonajeMejorado p : personajes) {
            totalVida += p.getVida();
            totalAtaque += p.getAtaque();
            totalDefensa += p.getDefensa();
            totalAlcance += p.getAlcance();
            totalNivel += p.getNivel();
        }

        int total = personajes.size();
        
        System.out.println("\n=== ESTADÍSTICAS GENERALES ===");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Vida promedio: %.2f%n", (double) totalVida / total);
        System.out.printf("Ataque promedio: %.2f%n", (double) totalAtaque / total);
        System.out.printf("Defensa promedio: %.2f%n", (double) totalDefensa / total);
        System.out.printf("Alcance promedio: %.2f%n", (double) totalAlcance / total);
        System.out.printf("Nivel promedio: %.2f%n", (double) totalNivel / total);
    }

    // 5. IMPORTAR PERSONAJES DESDE ARCHIVO
    private void importarPersonajes() {
        System.out.println("\n--- IMPORTAR PERSONAJES DESDE ARCHIVO ---");
        System.out.println("El archivo debe llamarse: " + ARCHIVO_IMPORTACION);
        System.out.println("Formato por línea: nombre,vida,ataque,defensa,alcance,nivel");
        
        File archivo = new File(ARCHIVO_IMPORTACION);
        if (!archivo.exists()) {
            System.out.println("❌ El archivo " + ARCHIVO_IMPORTACION + " no existe.");
            return;
        }

        int importados = 0;
        try (Scanner fileScanner = new Scanner(archivo)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        PersonajeMejorado p = PersonajeMejorado.fromFileString(line);
                        if (buscarPersonaje(p.getNombre()) == null) {
                            personajes.add(p);
                            importados++;
                            System.out.println("✅ Importado: " + p.getNombre());
                        } else {
                            System.out.println("❌ Duplicado: " + p.getNombre());
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Error en línea: " + line);
                    }
                }
            }
            guardarCambios();
            System.out.println("\n✅ Se importaron " + importados + " personajes.");
        } catch (FileNotFoundException e) {
            System.out.println("❌ Error al leer archivo: " + e.getMessage());
        }
    }

    // 6. SUBIR NIVEL DE PERSONAJE
    private void subirNivelPersonaje() {
        System.out.print("\nNombre del personaje a subir de nivel: ");
        String nombre = scanner.nextLine();
        
        PersonajeMejorado p = buscarPersonaje(nombre);
        if (p != null) {
            System.out.println("Atributos antes del subir nivel:");
            System.out.println(p);
            
            p.subirNivel();
            guardarCambios();
            
            System.out.println("\n✅ " + nombre + " subió al nivel " + p.getNivel());
            System.out.println("Atributos después del subir nivel:");
            System.out.println(p);
        } else {
            System.out.println("❌ Personaje no encontrado.");
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private PersonajeMejorado buscarPersonaje(String nombre) {
        for (PersonajeMejorado p : personajes) {
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
                    System.out.println("❌ El valor debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido.");
            }
        }
    }

    private void guardarCambios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (PersonajeMejorado p : personajes) {
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
                        PersonajeMejorado p = PersonajeMejorado.fromFileString(line);
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

    private void crearPersonajesPorDefecto() {
        personajes.add(new PersonajeMejorado("Caballero", 4, 2, 4, 2, 1));
        personajes.add(new PersonajeMejorado("Guerrero", 2, 4, 2, 4, 1));
        personajes.add(new PersonajeMejorado("Arquero", 2, 4, 1, 8, 1));
        guardarCambios();
    }
}
