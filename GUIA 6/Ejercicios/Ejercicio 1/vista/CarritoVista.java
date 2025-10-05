package vista;

import modelo.Producto;
import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner scanner;

    public CarritoVista() {
        scanner = new Scanner(System.in);
    }

    // Menú inicial
    public String mostrarMenuInicio() {
        System.out.println("\n=== SISTEMA DE CARRITO DE COMPRAS ===");
        System.out.println("¿Cómo deseas entrar?");
        System.out.println("1. Administrador ");
        System.out.println("2. Cliente ");
        System.out.println("3. Salir ");
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    // Login admin
    public String solicitarUsuarioAdmin() {
        System.out.print("Usuario admin: ");
        return scanner.nextLine();
    }

    public String solicitarContrasenaAdmin() {
        System.out.print("Contraseña: ");
        return scanner.nextLine();
    }

    // Cliente
    public String solicitarNombreCliente() {
        System.out.print("Ingresa tu nombre de usuario: ");
        return scanner.nextLine();
    }

    // Menús principales
    public void mostrarMenuAdmin() {
        System.out.println("\n=== PANEL ADMINISTRADOR ===");
        System.out.println("1. Agregar producto al catálogo");
        System.out.println("2. Listar productos del catálogo");
        System.out.println("3. Ver historial de compras");
        System.out.println("4. Cerrar sesión");
    }

    public void mostrarMenuCliente(String cliente) {
        System.out.println("\n=== PANEL CLIENTE (" + cliente + ") ===");
        System.out.println("1. Ver catálogo");
        System.out.println("2. Agregar producto al carrito");
        System.out.println("3. Ver carrito");
        System.out.println("4. Eliminar producto del carrito");
        System.out.println("5. Calcular envío");
        System.out.println("6. Realizar compra");
        System.out.println("7. Cerrar sesión");
    }

    // Solicitudes generales
    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public String solicitarNombreProducto() {
        System.out.print("Nombre del producto: ");
        return scanner.nextLine();
    }

    public double solicitarPrecioProducto() {
        System.out.print("Precio del producto: ");
        try {
            double precio = Double.parseDouble(scanner.nextLine());
            if (precio <= 0) {
                System.out.println(" El precio debe ser mayor a 0.");
                return -1;
            }
            return precio;
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un número válido.");
            return -1;
        }
    }

    public int solicitarCantidad() {
        System.out.print("Cantidad: ");
        try {
            int cantidad = Integer.parseInt(scanner.nextLine());
            if (cantidad <= 0) {
                System.out.println(" La cantidad debe ser mayor a 0.");
                return -1;
            }
            return cantidad;
        } catch (NumberFormatException e) {
            System.out.println(" Ingresa un número válido.");
            return -1;
        }
    }

    public int solicitarIndiceProducto() {
        System.out.print("Introduce el número del producto: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida.");
            return -1;
        }
    }

    // Mostrar información
    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            for (int i = 0; i < productos.size(); i++) {
                System.out.println((i + 1) + ". " + productos.get(i));
            }
        }
    }

    public void mostrarHistorial(List<String> historial) {
        if (historial.isEmpty()) {
            System.out.println(" No hay compras registradas.");
        } else {
            System.out.println("\n=== HISTORIAL DE COMPRAS ===");
            for (String h : historial) {
                System.out.println(h + "\n---------------------");
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
