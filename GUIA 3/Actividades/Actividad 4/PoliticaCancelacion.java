import java.time.LocalDate;

public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva, LocalDate fechaActual);
    double calcularPenalizacion(Reserva reserva, LocalDate fechaActual);
    String getNombrePolitica();
}
