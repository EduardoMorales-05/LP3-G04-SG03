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
                case "1":
                    mostrarMenuPlatos();
                    break;
                case "2":
                    agregarPedidosEnBucle();
                    break;
                case "3":
                    mostrarPedidos();
                    break;
                case "4":
                    vista.mostrarMensaje(" Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje(" Opción no válida. Inténtalo nuevamente.");
            }
        } while (!opcion.equals("4"));

        vista.cerrarScanner();
    }

    private void mostrarMenuPlatos() {
        vista.mostrarMenuPlatos(modelo.getMenuPlatos());
    }

    private void agregarPedidosEnBucle() {
        boolean continuar;
        do {
            List<String> menu = modelo.getMenuPlatos();
            vista.mostrarMenuPlatos(menu);

            int numeroPlato = vista.solicitarNumeroPlato(menu.size());
            if (numeroPlato == -1) return;

            int cantidad = vista.solicitarCantidad();
            if (cantidad == -1) return;

            String nombrePlato = menu.get(numeroPlato - 1);
            modelo.agregarPedido(new Pedido(nombrePlato, cantidad));
            vista.mostrarMensaje(" Pedido agregado: " + nombrePlato + " x" + cantidad);

            continuar = vista.deseaAgregarOtro();

        } while (continuar);
    }

    private void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }
}
