package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    private List<Pedido> pedidos;
    private List<Pedido> historial;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Pedido> getHistorial() {
        return historial;
    }

    public boolean eliminarPedido(int index) {
        if (index >= 0 && index < pedidos.size()) {
            Pedido eliminado = pedidos.remove(index);
            eliminado.setEstado("Eliminado");
            historial.add(eliminado);
            return true;
        }
        return false;
    }

    public Pedido obtenerPedido(int index) {
        if (index >= 0 && index < pedidos.size()) {
            return pedidos.get(index);
        }
        return null;
    }

    public void marcarComoCompleto(int index) {
        if (index >= 0 && index < pedidos.size()) {
            Pedido pedido = pedidos.get(index);
            pedido.setEstado("Completo");
            historial.add(pedido);
        }
    }

    public List<Pedido> filtrarPorEstado(String estado) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado().equalsIgnoreCase(estado)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public int contarPendientes() {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado().equalsIgnoreCase("Pendiente")) {
                contador++;
            }
        }
        return contador;
    }
}
