import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ModuloPoliticasCancelacion {
    
    // INTERFACE PARA POLÍTICAS DE CANCELACIÓN
    public interface IPoliticaCancelacion {
        boolean puedeCancelar(SistemaReservasCore.IReserva reserva, LocalDate fechaActual);
        double calcularPenalizacion(SistemaReservasCore.IReserva reserva, LocalDate fechaActual);
        String getNombrePolitica();
    }
    
    // IMPLEMENTACIONES DE POLÍTICAS
    public static class PoliticaFlexible implements IPoliticaCancelacion {
        @Override
        public boolean puedeCancelar(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            return diasAntes >= 1;
        }
        
        @Override
        public double calcularPenalizacion(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            return 0.0;
        }
        
        @Override
        public String getNombrePolitica() {
            return "Flexible (Cancelar hasta 24h antes, sin penalización)";
        }
    }
    
    public static class PoliticaModerada implements IPoliticaCancelacion {
        @Override
        public boolean puedeCancelar(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            return diasAntes >= 3;
        }
        
        @Override
        public double calcularPenalizacion(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            if (diasAntes >= 3) {
                return 50.0;
            }
            return 100.0;
        }
        
        @Override
        public String getNombrePolitica() {
            return "Moderada (Cancelar hasta 72h antes, 50% penalización)";
        }
    }
    
    public static class PoliticaEstricta implements IPoliticaCancelacion {
        @Override
        public boolean puedeCancelar(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            return diasAntes >= 7;
        }
        
        @Override
        public double calcularPenalizacion(SistemaReservasCore.IReserva reserva, LocalDate fechaActual) {
            long diasAntes = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            if (diasAntes >= 7) {
                return 20.0;
            }
            return 100.0;
        }
        
        @Override
        public String getNombrePolitica() {
            return "Estricta (Cancelar hasta 7 días antes, 20% penalización)";
        }
    }
    
    // GESTOR DE POLÍTICAS
    public static class GestorPoliticas {
        public static IPoliticaCancelacion seleccionarPolitica(int opcion) {
            switch (opcion) {
                case 1: return new PoliticaFlexible();
                case 2: return new PoliticaModerada();
                case 3: return new PoliticaEstricta();
                default: return new PoliticaFlexible();
            }
        }
        
        public static void mostrarOpcionesPoliticas() {
            System.out.println("\n=== POLÍTICAS DE CANCELACIÓN ===");
            System.out.println("1. " + new PoliticaFlexible().getNombrePolitica());
            System.out.println("2. " + new PoliticaModerada().getNombrePolitica());
            System.out.println("3. " + new PoliticaEstricta().getNombrePolitica());
        }
        
        public static void aplicarPoliticaCancelacion(SistemaReservasCore.IReserva reserva, 
                                                     IPoliticaCancelacion politica, 
                                                     LocalDate fechaActual) {
            if (politica.puedeCancelar(reserva, fechaActual)) {
                double penalizacion = politica.calcularPenalizacion(reserva, fechaActual);
                System.out.println("Cancelación permitida con política: " + politica.getNombrePolitica());
                System.out.println(" Penalización: $" + penalizacion);
            } else {
                System.out.println("No se puede cancelar con la política: " + politica.getNombrePolitica());
            }
        }
    }
}
