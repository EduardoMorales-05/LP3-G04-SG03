package modelo;

public class Pedido {
    private String nombrePlato;
    private int cantidad;

    public Pedido(String nombrePlato, int cantidad) {
        this.nombrePlato = nombrePlato;
        this.cantidad = cantidad;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return nombrePlato + " x" + cantidad;
    }
}
