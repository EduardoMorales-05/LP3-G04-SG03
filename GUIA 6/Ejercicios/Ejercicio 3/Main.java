import modelo.*;
import vista.*;
import controlador.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Crear jugador
        Jugador jugador = new Jugador("Héroe", 1);
        
        // Inicializar inventario del jugador
        inicializarInventarioJugador(jugador);
        
        // Crear instancias del MVC
        InventarioView vistaInventario = new InventarioView();
        InventarioController controladorInventario = new InventarioController(jugador.getInventario(), vistaInventario);
        
        CombateModel modeloCombate = new CombateModel(jugador);
        CombateView vistaCombate = new CombateView();
        CombateController controladorCombate = new CombateController(modeloCombate, vistaCombate);

        // Mostrar banner de bienvenida
        mostrarBienvenida();
        
        // Menú principal
        int opcion;
        do {
            mostrarMenuPrincipal(jugador);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    controladorInventario.ejecutar();
                    break;
                case 2:
                    controladorCombate.iniciarCombate();
                    break;
                case 3:
                    mostrarEstadoJugador(jugador);
                    break;
                case 4:
                    System.out.println("\n¡Hasta luego, " + jugador.getNombre() + "!");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void inicializarInventarioJugador(Jugador jugador) {
        InventarioModel inventario = jugador.getInventario();
        
        inventario.agregarItem(new Item("Espada", 1, "Arma", "Espada de acero templado"));
        inventario.agregarItem(new Item("Poción de Vida", 5, "Poción", "Restaura 50 puntos de vida"));
        inventario.agregarItem(new Item("Arco", 1, "Arma", "Arco largo de madera"));
        inventario.agregarItem(new Item("Flechas", 20, "Arma", "Flechas para arco"));
        inventario.agregarItem(new Item("Poción de Mana", 3, "Poción", "Restaura 30 puntos de mana"));
        inventario.agregarItem(new Item("Antídoto", 2, "Poción", "Cura venenos"));
        inventario.agregarItem(new Item("Daga", 1, "Arma", "Daga afilada"));
        
        jugador.setArmaEquipada(inventario.buscarItem("Espada"));
    }

    private static void mostrarBienvenida() {
        System.out.println("  SISTEMA DE GESTIÓN RPG       ");
        System.out.println("  INVENTARIO + SISTEMA DE COMBATE      ");
        System.out.println("\nBienvenido al sistema de gestión RPG completo!");
        System.out.println("Tu aventura comienza ahora...\n");
    }

    private static void mostrarMenuPrincipal(Jugador jugador) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" MENÚ PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("Jugador: " + jugador.getNombre() + " | Nivel: " + jugador.getNivel() + 
                         " | Salud: " + jugador.getSalud() + "/" + jugador.getSaludMaxima());
        System.out.println("\n1.  Gestión de Inventario");
        System.out.println("2.   Iniciar Combate");
        System.out.println("3. Ver Estado del Jugador");
        System.out.println("4.  Salir");
        System.out.print("Seleccione una opción: ");
    }

    // MÉTODO QUE FALTABA - CORREGIDO
    private static void mostrarEstadoJugador(Jugador jugador) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("📊 ESTADO DEL JUGADOR");
        System.out.println("=".repeat(40));
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Nivel: " + jugador.getNivel());
        System.out.println("Salud: " + jugador.getSalud() + "/" + jugador.getSaludMaxima());
        System.out.println("Arma equipada: " + 
            (jugador.getArmaEquipada() != null ? jugador.getArmaEquipada().getNombre() : "Ninguna"));
        System.out.println("Items en inventario: " + jugador.getInventario().getTotalItems());
        
        // Mostrar barra de salud visual
        mostrarBarraSaludJugador(jugador.getSalud(), jugador.getSaludMaxima());
        
        System.out.println("=".repeat(40));
    }

    // Método adicional para mostrar barra de salud del jugador
    private static void mostrarBarraSaludJugador(int saludActual, int saludMaxima) {
        int porcentaje = (int) ((double) saludActual / saludMaxima * 100);
        int barras = porcentaje / 5; // 20 caracteres para el 100%
        
        String barra = "█".repeat(barras) + "░".repeat(20 - barras);
        String color = porcentaje > 50 ? "🟢" : porcentaje > 25 ? "🟡" : "🔴";
        
        System.out.printf("Salud: %s [%s] %d%%\n", color, barra, porcentaje);
    }
}
