import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelacionEstricta implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva, LocalDate fechaActual) {
        long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
        return diasAntes >= 7;
    }

    @Override
    public double calcularPenalizacion(Reserva reserva, LocalDate fechaActual) {
        long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
        if (diasAntes >= 7) {
            return 20.0;
        }
        return 100.0;
    }

    @Override
    public String getNombrePolitica() {
        return "Estricta";
    }
}
