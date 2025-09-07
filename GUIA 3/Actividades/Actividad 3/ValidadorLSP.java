import java.time.LocalDate;
import java.util.List;

public class ValidadorLSP {
    
    public static void testSustitucionLSP(List<Habitacion> habitaciones) {
        System.out.println("=== TEST PRINCIPIO DE SUSTITUCION DE LISKOV ===");
        System.out.println("Verificando que todas las habitaciones pueden sustituir a la clase base...");
        
        for (Habitacion habitacion : habitaciones) {
            System.out.println("\n--- Probando: " + habitacion.getClass().getSimpleName() + " #" + habitacion.getNumero() + " ---");
            
            // Test 1: Precio debe ser válido (no negativo)
            testPrecioNoNegativo(habitacion);
            
            // Test 2: Disponibilidad debe comportarse consistentemente
            testComportamientoDisponibilidad(habitacion);
            
            // Test 3: Métodos base deben funcionar
            testMetodosBase(habitacion);
            
            // Test 4: Puede ser usada en contextos que esperan Habitacion
            testUsoEnContextoBase(habitacion);
        }
    }
    
    private static void testPrecioNoNegativo(Habitacion habitacion) {
        if (habitacion.getPrecio() >= 0) {
            System.out.println("✓ Precio válido: $" + habitacion.getPrecio());
        } else {
            System.out.println("✗ VIOLACIÓN LSP: Precio negativo");
        }
    }
    
    private static void testComportamientoDisponibilidad(Habitacion habitacion) {
        try {
            // Debe poder llamarse sin lanzar excepciones inesperadas
            LocalDate futuro = LocalDate.now().plusDays(10);
            boolean disponible = habitacion.estaDisponible(futuro, futuro.plusDays(2));
            System.out.println("✓ Comportamiento disponibilidad consistente: " + disponible);
        } catch (Exception e) {
            System.out.println("✗ VIOLACIÓN LSP: Excepción inesperada en estaDisponible: " + e.getMessage());
        }
    }
    
    private static void testMetodosBase(Habitacion habitacion) {
        try {
            // Todos los métodos de la clase base deben funcionar
            int numero = habitacion.getNumero();
            String tipo = habitacion.getTipo();
            String descripcion = habitacion.toString();
            
            System.out.println("✓ Métodos base funcionan: #" + numero + " - " + tipo);
        } catch (Exception e) {
            System.out.println("✗ VIOLACIÓN LSP: Error en métodos base: " + e.getMessage());
        }
    }
    
    private static void testUsoEnContextoBase(Habitacion habitacion) {
        // Simular uso en un contexto que espera Habitacion
        try {
            SistemaReservas sistema = new SistemaReservas();
            boolean resultado = sistema.puedeReservar(habitacion, LocalDate.now().plusDays(5), 3);
            System.out.println("✓ Puede usarse en contexto base: " + resultado);
        } catch (Exception e) {
            System.out.println("✗ VIOLACIÓN LSP: No puede usarse en contexto base: " + e.getMessage());
        }
    }
}

// Clase auxiliar para testing
class SistemaReservas {
    public boolean puedeReservar(Habitacion habitacion, LocalDate fecha, int dias) {
        // Este método espera cualquier Habitacion (base o derivada)
        return habitacion.estaDisponible(fecha, fecha.plusDays(dias)) && 
               habitacion.getPrecio() > 0;
    }
}
