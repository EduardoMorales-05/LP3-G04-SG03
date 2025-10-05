package controlador;

import modelo.InventarioModel;
import modelo.Item;
import vista.InventarioView;
import java.util.Scanner;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;
    private Scanner scanner;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.scanner = new Scanner(System.in);
    }

    public void agregarItem() {
        System.out.print("Nombre del item: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Tipo (Arma/Poción): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        Item nuevoItem = new Item(nombre, cantidad, tipo, descripcion);
        modelo.agregarItem(nuevoItem);
        vista.mostrarMensaje("Item agregado exitosamente.");
    }

    public void eliminarItem() {
        vista.mostrarMenuBusqueda();
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("Nombre del item a eliminar: ");
            String nombre = scanner.nextLine();
            
            if (modelo.eliminarItemPorNombre(nombre)) {
                vista.mostrarMensaje("Item eliminado exitosamente.");
            } else {
                vista.mostrarMensaje("Item no encontrado.");
            }
        } else if (opcion == 2) {
            vista.mostrarInventario(modelo.obtenerItems());
            System.out.print("Número de orden del item a eliminar: ");
            int numeroOrden = scanner.nextInt();
            scanner.nextLine();
            
            if (modelo.eliminarItemPorIndice(numeroOrden - 1)) {
                vista.mostrarMensaje("Item eliminado exitosamente.");
            } else {
                vista.mostrarMensaje("Número de orden inválido.");
            }
        } else {
            vista.mostrarMensaje("Opción inválida.");
        }
    }

    public void verInventario() {
        vista.mostrarInventario(modelo.obtenerItems());
    }

    public void buscarPorNumeroOrden() {
        vista.mostrarInventario(modelo.obtenerItems());
        if (modelo.getTotalItems() > 0) {
            System.out.print("Ingrese el número de orden del item: ");
            int numeroOrden = scanner.nextInt();
            scanner.nextLine();
            
            Item item = modelo.buscarPorIndice(numeroOrden - 1);
            if (item != null) {
                vista.mostrarDetallesItemConIndice(item, numeroOrden - 1);
            } else {
                vista.mostrarMensaje("Número de orden inválido. Use números del 1 al " + modelo.getTotalItems());
            }
        }
    }

    public void buscarItem() {
        System.out.print("Nombre del item a buscar: ");
        String nombre = scanner.nextLine();
        
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            int indice = modelo.obtenerIndicePorNombre(nombre);
            vista.mostrarDetallesItemConIndice(item, indice);
        } else {
            vista.mostrarMensaje("Item no encontrado.");
        }
    }

    public void mostrarDetalles() {
        vista.mostrarMenuBusqueda();
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            buscarItem();
        } else if (opcion == 2) {
            buscarPorNumeroOrden();
        } else {
            vista.mostrarMensaje("Opción inválida.");
        }
    }

    public void usarItem() {
        System.out.print("Nombre del item a usar: ");
        String nombre = scanner.nextLine();
        
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            item.usarItem();
            if (item.getCantidad() == 0) {
                modelo.eliminarItemPorNombre(nombre);
            }
        } else {
            vista.mostrarMensaje("Item no encontrado.");
        }
    }

    public void ejecutar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: agregarItem(); break;
                case 2: eliminarItem(); break;
                case 3: verInventario(); break;
                case 4: buscarItem(); break;
                case 5: buscarPorNumeroOrden(); break;
                case 6: mostrarDetalles(); break;
                case 7: usarItem(); break;
                case 8: vista.mostrarMensaje("Volviendo al menú principal..."); break;
                default: vista.mostrarMensaje("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);
    }
}
