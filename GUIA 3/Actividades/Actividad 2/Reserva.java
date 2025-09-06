import java.time.LocalDate;

public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getNumeroHabitacion() {
        return habitacion.getNumero();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente.getNombre() +
                ", habitacion=" + habitacion.getNumero() +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
