package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    private List<Pedido> pedidos;
    private List<String> menuPlatos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();

        // Lista de platos disponibles
        menuPlatos = new ArrayList<>();
        menuPlatos.add("Lomo Saltado");
        menuPlatos.add("Aji de Gallina");
        menuPlatos.add("Ceviche");
        menuPlatos.add("Arroz Chaufa");
        menuPlatos.add("Pollo a la Brasa");
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<String> getMenuPlatos() {
        return menuPlatos;
    }
}
