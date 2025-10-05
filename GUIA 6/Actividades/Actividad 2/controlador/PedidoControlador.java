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
                case "3": eliminarPedido(); break;
                case "4": actualizarPedido(); break;
                case "5": buscarPedido(); break;
                case "6": contarPedidos(); break;
                case "7": vista.mostrarMensaje(" Saliendo del sistema..."); break;
                default: vista.mostrarMensaje(" Opción inválida."); break;
            }
        } while (!opcion.equals("7"));

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
        vista.mostrarMensaje(" Pedido agregado correctamente.");
    }

    private void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }

    private void eliminarPedido() {
        if (modelo.getPedidos().isEmpty()) {
            vista.mostrarMensaje(" No hay pedidos para eliminar.");
            return;
        }

        vista.mostrarPedidos(modelo.getPedidos());
        int index = vista.solicitarIndice();

        if (modelo.eliminarPedido(index)) {
            vista.mostrarMensaje(" Pedido eliminado correctamente.");
        } else {
            vista.mostrarMensaje(" Índice inválido.");
        }
    }

    private void actualizarPedido() {
        if (modelo.getPedidos().isEmpty()) {
            vista.mostrarMensaje(" No hay pedidos para actualizar.");
            return;
        }

        vista.mostrarPedidos(modelo.getPedidos());
        int index = vista.solicitarIndice();

        Pedido pedido = modelo.obtenerPedido(index);
        if (pedido == null) {
            vista.mostrarMensaje(" Índice inválido.");
            return;
        }

        String nuevoNombre = vista.solicitarNuevoNombre();
        String nuevoTipo = vista.solicitarNuevoTipo();

        if (!nuevoNombre.isEmpty()) pedido.setNombrePlato(nuevoNombre);
        if (!nuevoTipo.isEmpty()) pedido.setTipoPlato(nuevoTipo);

        vista.mostrarMensaje(" Pedido actualizado correctamente.");
    }

    private void buscarPedido() {
        if (modelo.getPedidos().isEmpty()) {
            vista.mostrarMensaje(" No hay pedidos para buscar.");
            return;
        }

        String texto = vista.solicitarTextoBusqueda();
        List<Pedido> encontrados = modelo.buscarPorNombreOTipo(texto);

        if (encontrados.isEmpty()) {
            vista.mostrarMensaje(" No se encontraron pedidos con ese criterio.");
        } else {
            vista.mostrarPedidos(encontrados);
        }
    }

    private void contarPedidos() {
        int total = modelo.contarPedidos();
        vista.mostrarMensaje(" Total de pedidos: " + total);

        vista.mostrarMensaje("Por tipo:");
        vista.mostrarMensaje("Entradas: " + modelo.contarPorTipo("Entrada"));
        vista.mostrarMensaje("Platos Fuertes: " + modelo.contarPorTipo("Plato Fuerte"));
        vista.mostrarMensaje("Bebidas: " + modelo.contarPorTipo("Bebida"));
    }
}
