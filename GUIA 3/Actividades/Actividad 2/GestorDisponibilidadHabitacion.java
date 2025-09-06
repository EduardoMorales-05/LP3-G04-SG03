import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorDisponibilidadHabitacion {
    private List<Reserva> reservas;

    public GestorDisponibilidadHabitacion() {
        this.reservas = new ArrayList<>();
    }

    public boolean estaDisponible(int numeroHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        // Validar que las fechas sean correctas
        if (fechaInicio.isAfter(fechaFin)) {
            return false;
        }
        
        if (fechaInicio.isBefore(LocalDate.now())) {
            return false;
        }

        for (Reserva reserva : reservas) {
            if (reserva.getNumeroHabitacion() == numeroHabitacion &&
                !(fechaFin.isBefore(reserva.getFechaInicio()) || fechaInicio.isAfter(reserva.getFechaFin()))) {
                return false;
            }
        }
        return true;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas); // Devolver copia para evitar modificaciones externas
    }

    public List<Reserva> getReservasPorHabitacion(int numeroHabitacion) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroHabitacion() == numeroHabitacion) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }
}
