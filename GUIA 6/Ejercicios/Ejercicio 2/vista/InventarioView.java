package vista;

import modelo.Item;
import java.util.List;

public class InventarioView {
    
    public void mostrarInventario(List<Item> items) {
        System.out.println("\n=== INVENTARIO ===");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.printf("%-3s %-15s %-8s %-10s %-20s%n", 
                "No.", "NOMBRE", "CANTIDAD", "TIPO", "DESCRIPCIÓN");
            System.out.println("----------------------------------------------------------------");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%-3d %-15s %-8d %-10s %-20s%n",
                    i + 1,  // Mostrar número de orden (comenzando desde 1)
                    item.getNombre(),
                    item.getCantidad(),
                    item.getTipo(),
                    item.getDescripcion());
            }
            System.out.println("Total de items: " + items.size());
        }
        System.out.println();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDetallesItem(Item item) {
        if (item != null) {
            System.out.println("\n=== DETALLES DEL ITEM ===");
            System.out.println("Nombre: " + item.getNombre());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Descripción: " + item.getDescripcion());
        } else {
            System.out.println("Item no encontrado.");
        }
        System.out.println();
    }

    // NUEVO MÉTODO: Mostrar detalles con número de orden
    public void mostrarDetallesItemConIndice(Item item, int indice) {
        if (item != null) {
            System.out.println("\n=== DETALLES DEL ITEM (No. " + (indice + 1) + ") ===");
            System.out.println("Nombre: " + item.getNombre());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Descripción: " + item.getDescripcion());
        } else {
            System.out.println("Item no encontrado.");
        }
        System.out.println();
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIOS ===");
        System.out.println("1. Agregar item");
        System.out.println("2. Eliminar item");
        System.out.println("3. Ver inventario completo");
        System.out.println("4. Buscar item por nombre");
        System.out.println("5. Buscar item por número de orden");
        System.out.println("6. Mostrar detalles de item");
        System.out.println("7. Usar item");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // NUEVO MÉTODO: Mostrar opciones de búsqueda
    public void mostrarMenuBusqueda() {
        System.out.println("\n=== OPCIONES DE BÚSQUEDA ===");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por número de orden");
        System.out.print("Seleccione método de búsqueda: ");
    }
}
