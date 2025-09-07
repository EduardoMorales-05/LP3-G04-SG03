import java.time.LocalDate;

public class Habitacion {
    private int numero;
    private String tipo;
    private double precio;
    private GestorDisponibilidadHabitacion gestorDisponibilidad;

    public Habitacion(int numero, String tipo, double precio, GestorDisponibilidadHabitacion gestorDisponibilidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.gestorDisponibilidad = gestorDisponibilidad;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean estaDisponible(LocalDate inicio, LocalDate fin) {
        return gestorDisponibilidad.estaDisponible(numero, inicio, fin);
    }

    @Override
    public String toString() {
        return "Habitacion #" + numero + " - " + tipo + " - $" + precio + "/noche";
    }
}
