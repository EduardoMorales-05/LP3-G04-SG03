package modelo;

public class Pedido {
    private String nombrePlato;
    private String tipoPlato;
    private int cantidad;
    private String estado; // Pendiente, Completo, Eliminado

    public Pedido(String nombrePlato, String tipoPlato, int cantidad) {
        this.nombrePlato = nombrePlato;
        this.tipoPlato = tipoPlato;
        this.cantidad = cantidad;
        this.estado = "Pendiente";
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(String tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombrePlato + " (" + tipoPlato + ") x" + cantidad + " - Estado: " + estado;
    }
}
