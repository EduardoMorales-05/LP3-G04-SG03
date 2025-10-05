package controlador;

import modelo.*;
import vista.CarritoVista;
import java.util.ArrayList;
import java.util.List;

public class CarritoControlador {
    private List<Producto> catalogo;
    private Carrito carrito;
    private Compra compra;
    private CarritoVista vista;

    public CarritoControlador(CarritoVista vista) {
        this.catalogo = new ArrayList<>();
        this.carrito = new Carrito();
        this.compra = new Compra();
        this.vista = vista;

        // Productos iniciales
        catalogo.add(new Producto("Laptop", 2500, 0));
        catalogo.add(new Producto("Mouse", 80, 0));
        catalogo.add(new Producto("Teclado", 120, 0));
    }

    public void iniciar() {
        String tipo;
        do {
            tipo = vista.mostrarMenuInicio();

            switch (tipo) {
                case "1": iniciarAdmin(); break;
                case "2": iniciarCliente(vista.solicitarNombreCliente()); break;
                case "3": vista.mostrarMensaje("👋 Saliendo del sistema..."); break;
                default: vista.mostrarMensaje("⚠️ Opción inválida."); break;
            }

        } while (!tipo.equals("3"));
    }

    // === ADMIN ===
    private void iniciarAdmin() {
        String usuario = vista.solicitarUsuarioAdmin();
        String contrasena = vista.solicitarContrasenaAdmin();

        if (!usuario.equals("admin") || !contrasena.equals("admin")) {
            vista.mostrarMensaje("❌ Credenciales incorrectas. Acceso denegado.");
            return;
        }

        String opcion;
        do {
            vista.mostrarMenuAdmin();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1": agregarProductoCatalogo(); break;
                case "2": listarCatalogo(); break;
                case "3": verHistorial(); break;
                case "4": vista.mostrarMensaje("👋 Cerrando sesión de admin..."); break;
                default: vista.mostrarMensaje("⚠️ Opción inválida."); break;
            }

        } while (!opcion.equals("4"));
    }

    // === CLIENTE ===
    private void iniciarCliente(String cliente) {
        String opcion;
        boolean continuar = true;

        do {
            vista.mostrarMenuCliente(cliente);
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1": listarCatalogo(); break;
                case "2": agregarAlCarrito(); break;
                case "3": verCarrito(); break;
                case "4": eliminarDelCarrito(); break;
                case "5": calcularEnvio(); break;
                case "6": continuar = realizarCompra(cliente); break;
                case "7":
                    vista.mostrarMensaje("👋 Cerrando sesión del cliente...");
                    continuar = false;
                    break;
                default:
                    vista.mostrarMensaje("⚠️ Opción inválida.");
            }

        } while (continuar);
    }

    // === FUNCIONES ADMIN ===
    private void agregarProductoCatalogo() {
        String nombre = vista.solicitarNombreProducto();
        double precio = vista.solicitarPrecioProducto();

        if (nombre.isEmpty() || precio <= 0) {
            vista.mostrarMensaje("⚠️ Datos inválidos. No se agregó el producto.");
            return;
        }

        catalogo.add(new Producto(nombre, precio, 0));
        vista.mostrarMensaje("✅ Producto agregado al catálogo.");
    }

    // === FUNCIONES CLIENTE ===
    private void listarCatalogo() {
        vista.mostrarProductos(catalogo);
    }

    private void agregarAlCarrito() {
        if (catalogo.isEmpty()) {
            vista.mostrarMensaje("⚠️ No hay productos en el catálogo.");
            return;
        }

        vista.mostrarProductos(catalogo);
        int index = vista.solicitarIndiceProducto();

        if (index < 0 || index >= catalogo.size()) {
            vista.mostrarMensaje("⚠️ Índice inválido.");
            return;
        }

        int cantidad = vista.solicitarCantidad();
        if (cantidad <= 0) return;

        Producto base = catalogo.get(index);
        carrito.agregarProducto(new Producto(base.getNombre(), base.getPrecio(), cantidad));
        vista.mostrarMensaje("🛒 Producto agregado al carrito.");
    }

    private void verCarrito() {
        List<Producto> productos = carrito.getProductos();
        if (productos.isEmpty()) {
            vista.mostrarMensaje("🛒 El carrito está vacío.");
        } else {
            vista.mostrarProductos(productos);
            vista.mostrarMensaje("💰 Total: S/ " + carrito.calcularTotal());
        }
    }

    private void eliminarDelCarrito() {
        if (carrito.estaVacio()) {
            vista.mostrarMensaje("⚠️ El carrito está vacío.");
            return;
        }

        verCarrito();
        int index = vista.solicitarIndiceProducto();
        carrito.eliminarProducto(index);
        vista.mostrarMensaje("🗑️ Producto eliminado del carrito.");
    }

    private void calcularEnvio() {
        if (carrito.estaVacio()) {
            vista.mostrarMensaje("⚠️ No hay productos en el carrito.");
            return;
        }

        double total = carrito.calcularTotal();
        double envio = (total >= 100) ? 0 : 15;
        vista.mostrarMensaje(envio == 0 ? "🚚 Envío gratis" : "Costo de envío: S/ 15");
    }

    private boolean realizarCompra(String cliente) {
        if (carrito.estaVacio()) {
            vista.mostrarMensaje("⚠️ No puedes realizar una compra vacía.");
            return true;
        }

        double total = carrito.calcularTotal();

        // Descuento automático
        if (total >= 3000) {
            vista.mostrarMensaje("🎉 Felicidades, tu compra supera los S/ 3000. Se aplicará un 10% de descuento.");
            total *= 0.9;
        } else {
            vista.mostrarMensaje("ℹ️ Para aplicar un descuento, la compra debe superar los S/ 3000.");
        }

        compra.registrarCompra(new ArrayList<>(carrito.getProductos()), total, cliente);
        carrito.vaciar();
        vista.mostrarMensaje("✅ Compra realizada con éxito. Total pagado: S/ " + total);

        System.out.print("¿Deseas realizar otra compra? (S/N): ");
        String respuesta = new java.util.Scanner(System.in).nextLine().trim().toUpperCase();

        if (respuesta.equals("S")) {
            vista.mostrarMensaje("🔁 Regresando al menú del cliente...");
            return true;
        } else {
            vista.mostrarMensaje("👋 Gracias por tu compra. Cerrando sesión...");
            return false;
        }
    }

    private void verHistorial() {
        vista.mostrarHistorial(compra.getHistorial());
    }
}
