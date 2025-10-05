package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;
import java.util.List;

public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenuPrincipal();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": mostrarPedidos(); break;
                case "3": marcarComoCompleto(); break;
                case "4": mostrarPorEstado(); break;
                case "5": mostrarContadorPendientes(); break;
                case "6": mostrarHistorial(); break;
                case "7": eliminarPedido(); break;
                case "8": vista.mostrarMensaje("👋 Saliendo del sistema..."); break;
                default: vista.mostrarMensaje("⚠️ Opción inválida."); break;
            }
        } while (!opcion.equals("8"));

        vista.cerrarScanner();
    }

    private void agregarPedido() {
        String nombre = vista.solicitarNombrePlato();
        String tipo = vista.solicitarTipoPlato();
        int cantidad = vista.solicitarCantidad();

        if (nombre.isEmpty() || tipo.isEmpty() || cantidad <= 0) {
            vista.mostrarMensaje("⚠️ Datos inválidos. Pedido no agregado.");
            return;
        }

        modelo.agregarPedido(new Pedido(nombre, tipo, cantidad));
        vista.mostrarMensaje("✅ Pedido agregado correctamente (Estado: Pendiente).");
    }

    private void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }

    private void marcarComoCompleto() {
        List<Pedido> pedidos = modelo.getPedidos();
        if (pedidos.isEmpty()) {
            vista.mostrarMensaje("⚠️ No hay pedidos para marcar.");
            return;
        }

        vista.mostrarPedidos(pedidos);
        int index = vista.solicitarIndice();

        Pedido pedido = modelo.obtenerPedido(index);
        if (pedido == null) {
            vista.mostrarMensaje("⚠️ Índice inválido.");
            return;
        }

        if (pedido.getEstado().equalsIgnoreCase("Completo")) {
            vista.mostrarMensaje("✅ El pedido ya está completo.");
            return;
        }

        modelo.marcarComoCompleto(index);
        pedido.setEstado("Completo");
        vista.mostrarMensaje("✅ Pedido marcado como completo.");
    }

    private void mostrarPorEstado() {
        String estado = vista.solicitarEstado();
        List<Pedido> filtrados = modelo.filtrarPorEstado(estado);
        if (filtrados.isEmpty()) {
            vista.mostrarMensaje("❌ No hay pedidos con estado " + estado + ".");
        } else {
            vista.mostrarPedidos(filtrados);
        }
    }

    private void mostrarContadorPendientes() {
        int pendientes = modelo.contarPendientes();
        vista.mostrarMensaje("📋 Pedidos pendientes: " + pendientes);
    }

    private void mostrarHistorial() {
        vista.mostrarHistorial(modelo.getHistorial());
    }

    private void eliminarPedido() {
        if (modelo.getPedidos().isEmpty()) {
            vista.mostrarMensaje("⚠️ No hay pedidos para eliminar.");
            return;
        }

        vista.mostrarPedidos(modelo.getPedidos());
        int index = vista.solicitarIndice();

        if (modelo.eliminarPedido(index)) {
            vista.mostrarMensaje("🗑️ Pedido eliminado y agregado al historial.");
        } else {
            vista.mostrarMensaje("⚠️ Índice inválido.");
        }
    }
}
