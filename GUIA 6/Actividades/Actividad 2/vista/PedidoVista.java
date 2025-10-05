package vista;

import java.util.List;
import java.util.Scanner;
import modelo.Pedido;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE PEDIDOS ===");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Mostrar pedidos");
        System.out.println("3. Eliminar pedido");
        System.out.println("4. Actualizar pedido");
        System.out.println("5. Buscar pedido");
        System.out.println("6. Contar pedidos");
        System.out.println("7. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public String solicitarNombrePlato() {
        System.out.print("Introduce el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipoPlato() {
        System.out.print("Introduce el tipo del plato (Entrada, Plato Fuerte, Bebida, etc.): ");
        return scanner.nextLine();
    }

    public int solicitarCantidad() {
        System.out.print("Introduce la cantidad: ");
        try {
            int cantidad = Integer.parseInt(scanner.nextLine());
            if (cantidad <= 0) {
                System.out.println(" La cantidad debe ser mayor a 0.");
                return -1;
            }
            return cantidad;
        } catch (NumberFormatException e) {
            System.out.println(" Debes ingresar un número válido.");
            return -1;
        }
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("\n=== LISTA DE PEDIDOS ===");
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println((i + 1) + ". " + pedidos.get(i));
            }
        }
    }

    public int solicitarIndice() {
        System.out.print("Introduce el número del pedido: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida.");
            return -1;
        }
    }

    public String solicitarTextoBusqueda() {
        System.out.print("Introduce el nombre o tipo del pedido a buscar: ");
        return scanner.nextLine();
    }

    public String solicitarNuevoNombre() {
        System.out.print("Introduce el nuevo nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarNuevoTipo() {
        System.out.print("Introduce el nuevo tipo del plato: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
