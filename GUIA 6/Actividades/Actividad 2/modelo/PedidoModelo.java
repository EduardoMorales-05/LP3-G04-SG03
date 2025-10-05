package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean eliminarPedido(int index) {
        if (index >= 0 && index < pedidos.size()) {
            pedidos.remove(index);
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

    public List<Pedido> buscarPorNombreOTipo(String texto) {
        List<Pedido> encontrados = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().equalsIgnoreCase(texto) ||
                p.getTipoPlato().equalsIgnoreCase(texto)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public int contarPedidos() {
        return pedidos.size();
    }

    public int contarPorTipo(String tipo) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getTipoPlato().equalsIgnoreCase(tipo)) {
                contador++;
            }
        }
        return contador;
    }
}
