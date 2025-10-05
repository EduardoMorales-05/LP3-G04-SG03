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
        System.out.println("1. Ver menú de platos");
        System.out.println("2. Agregar pedido");
        System.out.println("3. Mostrar pedidos actuales");
        System.out.println("4. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarMenuPlatos(List<String> menuPlatos) {
        System.out.println("\n=== MENÚ DEL RESTAURANTE ===");
        for (int i = 0; i < menuPlatos.size(); i++) {
            System.out.println((i + 1) + ". " + menuPlatos.get(i));
        }
    }

    public int solicitarNumeroPlato(int max) {
        System.out.print("Selecciona el número del plato: ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion < 1 || opcion > max) {
                System.out.println(" Opción inválida. Intenta de nuevo.");
                return -1;
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println(" Debes ingresar un número válido.");
            return -1;
        }
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

    public boolean deseaAgregarOtro() {
        System.out.print("¿Deseas agregar otro plato? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la lista.");
        } else {
            System.out.println("\n=== LISTA DE PEDIDOS ===");
            for (Pedido pedido : pedidos) {
                System.out.println("- " + pedido);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
