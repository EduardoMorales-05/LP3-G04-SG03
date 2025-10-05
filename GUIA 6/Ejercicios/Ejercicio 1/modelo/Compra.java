package modelo;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<String> historial;

    public Compra() {
        historial = new ArrayList<>();
    }

    public void registrarCompra(List<Producto> productos, double total, String cliente) {
        StringBuilder detalle = new StringBuilder("🛍️ Compra de " + cliente + ":\n");
        for (Producto p : productos) {
            detalle.append("- ").append(p.getNombre())
                   .append(" x").append(p.getCantidad())
                   .append(" = S/ ").append(p.getSubtotal()).append("\n");
        }
        detalle.append("Total pagado: S/ ").append(total);
        historial.add(detalle.toString());
    }

    public List<String> getHistorial() {
        return historial;
    }
}
