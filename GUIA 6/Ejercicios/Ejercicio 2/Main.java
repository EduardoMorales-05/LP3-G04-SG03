import modelo.InventarioModel;
import modelo.Item;
import vista.InventarioView;
import controlador.InventarioController;

public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo, vista y controlador
        InventarioModel modelo = new InventarioModel();
        InventarioView vista = new InventarioView();
        InventarioController controlador = new InventarioController(modelo, vista);

        // Agregar items de ejemplo
        modelo.agregarItem(new Item("Espada", 1, "Arma", "Espada de acero templado"));
        modelo.agregarItem(new Item("Poción de Vida", 3, "Poción", "Restaura 50 puntos de vida"));
        modelo.agregarItem(new Item("Arco", 1, "Arma", "Arco largo de madera"));
        modelo.agregarItem(new Item("Flechas", 20, "Arma", "Flechas para arco"));
        modelo.agregarItem(new Item("Poción de Mana", 2, "Poción", "Restaura 30 puntos de mana"));

        // Mostrar mensaje de bienvenida
        System.out.println("  SISTEMA DE GESTIÓN DE INVENTARIOS");
        System.out.println("Inventario cargado con " + modelo.getTotalItems() + " items iniciales.");
        
        // Iniciar la aplicación
        controlador.ejecutar();
    }
}
