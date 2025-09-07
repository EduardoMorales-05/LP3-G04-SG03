import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelacionFlexible implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva, LocalDate fechaActual) {
        long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
        return diasAntes >= 1;
    }

    @Override
    public double calcularPenalizacion(Reserva reserva, LocalDate fechaActual) {
        return 0.0;
    }

    @Override
    public String getNombrePolitica() {
        return "Flexible";
    }
}
