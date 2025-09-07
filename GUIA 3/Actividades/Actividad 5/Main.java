import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // INICIALIZAR COMPONENTES PRINCIPALES
        SistemaReservasCore.IGestorDisponibilidad gestor = 
            new SistemaReservasCore.GestorDisponibilidadHabitacion();
        
        // CREAR HABITACIONES
        List<SistemaReservasCore.IHabitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new SistemaReservasCore.Habitacion(101, "Individual", 100.0, gestor));
        habitaciones.add(new SistemaReservasCore.Habitacion(102, "Doble", 150.0, gestor));
        habitaciones.add(new ModuloServicios.HabitacionConServicios(201, 200.0, gestor));
        habitaciones.add(new ModuloServicios.HabitacionPremium(301, 300.0, gestor));
        
        // CREAR CLIENTES
        List<SistemaReservasCore.ICliente> clientes = new ArrayList<>();
        clientes.add(new SistemaReservasCore.Cliente("Juan Perez", "juan@email.com"));
        clientes.add(new SistemaReservasCore.Cliente("Maria Lopez", "maria@email.com"));
        
        boolean ejecutando = true;
        
        while (ejecutando) {
            System.out.println("\n=== SISTEMA DE RESERVAS DE HOTEL ===");
            System.out.println("1. Ver habitaciones");
            System.out.println("2. Crear reserva");
            System.out.println("3. Ver reservas");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Usar servicios");
            System.out.println("6. Probar políticas de cancelación");
            System.out.println("7. Salir");
            System.out.print("Seleccione opción: ");
            
            int opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1:
                    System.out.println("\nHABITACIONES DISPONIBLES:");
                    for (int i = 0; i < habitaciones.size(); i++) {
                        System.out.println((i + 1) + ". " + habitaciones.get(i));
                    }
                    break;
                    
                case 2:
                    crearReserva(scanner, habitaciones, clientes, gestor);
                    break;
                    
                case 3:
                    verReservas(gestor);
                    break;
                    
                case 4:
                    cancelarReserva(scanner, gestor);
                    break;
                    
                case 5:
                    usarServicios(scanner, habitaciones);
                    break;
                    
                case 6:
                    probarPoliticasCancelacion(scanner, gestor);
                    break;
                    
                case 7:
                    ejecutando = false;
                    System.out.println("¡Hasta pronto!");
                    break;
                    
                default:
                    System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }
    
    private static void crearReserva(Scanner scanner, List<SistemaReservasCore.IHabitacion> habitaciones,
                                   List<SistemaReservasCore.ICliente> clientes,
                                   SistemaReservasCore.IGestorDisponibilidad gestor) {
        System.out.println("\nCREAR RESERVA");
        
        // Seleccionar cliente
        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
        System.out.print("Seleccione cliente: ");
        int idxCliente = Integer.parseInt(scanner.nextLine()) - 1;
        
        // Seleccionar habitación
        System.out.println("Habitaciones:");
        for (int i = 0; i < habitaciones.size(); i++) {
            System.out.println((i + 1) + ". " + habitaciones.get(i));
        }
        System.out.print("Seleccione habitación: ");
        int idxHabitacion = Integer.parseInt(scanner.nextLine()) - 1;
        
        // Fechas
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());
        
        if (idxCliente >= 0 && idxCliente < clientes.size() &&
            idxHabitacion >= 0 && idxHabitacion < habitaciones.size()) {
            
            SistemaReservasCore.IHabitacion habitacion = habitaciones.get(idxHabitacion);
            if (habitacion.estaDisponible(inicio, fin)) {
                SistemaReservasCore.IReserva reserva = new SistemaReservasCore.Reserva(
                    clientes.get(idxCliente), habitacion, inicio, fin
                );
                gestor.agregarReserva(reserva);
                System.out.println("Reserva creada exitosamente");
            } else {
                System.out.println("Habitación no disponible en esas fechas");
            }
        }
    }
    
    private static void verReservas(SistemaReservasCore.IGestorDisponibilidad gestor) {
        List<SistemaReservasCore.IReserva> reservas = gestor.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas");
        } else {
            System.out.println("\nRESERVAS:");
            for (int i = 0; i < reservas.size(); i++) {
                System.out.println((i + 1) + ". " + reservas.get(i));
            }
        }
    }
    
    private static void cancelarReserva(Scanner scanner, SistemaReservasCore.IGestorDisponibilidad gestor) {
        List<SistemaReservasCore.IReserva> reservas = gestor.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para cancelar");
            return;
        }
        
        verReservas(gestor);
        System.out.print("Seleccione reserva a cancelar: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (idx >= 0 && idx < reservas.size()) {
            gestor.cancelarReserva(reservas.get(idx));
            System.out.println("✅ Reserva cancelada");
        }
    }
    
    private static void usarServicios(Scanner scanner, List<SistemaReservasCore.IHabitacion> habitaciones) {
        System.out.println("\nUSAR SERVICIOS");
        for (int i = 0; i < habitaciones.size(); i++) {
            System.out.println((i + 1) + ". " + habitaciones.get(i));
        }
        
        System.out.print("Seleccione habitación: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (idx >= 0 && idx < habitaciones.size()) {
            SistemaReservasCore.IHabitacion hab = habitaciones.get(idx);
            
            if (hab instanceof ModuloServicios.IServicioLimpieza) {
                System.out.print("¿Solicitar limpieza? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    ((ModuloServicios.IServicioLimpieza) hab).solicitarLimpieza();
                }
            }
            
            if (hab instanceof ModuloServicios.IServicioComida) {
                System.out.print("¿Solicitar comida? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    System.out.print("Tipo de comida: ");
                    String tipo = scanner.nextLine();
                    ((ModuloServicios.IServicioComida) hab).solicitarComida(tipo);
                }
            }
        }
    }
    
    private static void probarPoliticasCancelacion(Scanner scanner, 
                                                  SistemaReservasCore.IGestorDisponibilidad gestor) {
        System.out.println("\n=== PROBAR POLÍTICAS DE CANCELACIÓN ===");
        
        List<SistemaReservasCore.IReserva> reservas = gestor.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para probar políticas");
            return;
        }
        
        // Mostrar reservas
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i));
        }
        
        System.out.print("Seleccione reserva para probar políticas: ");
        int idxReserva = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (idxReserva >= 0 && idxReserva < reservas.size()) {
            SistemaReservasCore.IReserva reserva = reservas.get(idxReserva);
            LocalDate fechaActual = LocalDate.now();
            
            // Mostrar opciones de políticas
            ModuloPoliticasCancelacion.GestorPoliticas.mostrarOpcionesPoliticas();
            
            System.out.print("Seleccione política a probar (1-3): ");
            int opcionPolitica = Integer.parseInt(scanner.nextLine());
            
            ModuloPoliticasCancelacion.IPoliticaCancelacion politica = 
                ModuloPoliticasCancelacion.GestorPoliticas.seleccionarPolitica(opcionPolitica);
            
            // Aplicar política
            ModuloPoliticasCancelacion.GestorPoliticas.aplicarPoliticaCancelacion(
                reserva, politica, fechaActual
            );
            
            // Mostrar información adicional
            System.out.println("\n  Información de la reserva:");
            System.out.println("• Fecha de inicio: " + reserva.getFechaInicio());
            long diasHastaCheckin = ChronoUnit.DAYS.between(fechaActual, reserva.getFechaInicio());
            System.out.println("• Días hasta el check-in: " + diasHastaCheckin);
            System.out.println("• Precio habitación: $" + reserva.getHabitacion().getPrecio());
        }
    }
}
