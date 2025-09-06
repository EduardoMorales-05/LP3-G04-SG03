import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorDisponibilidadHabitacion gestor = new GestorDisponibilidadHabitacion();
        ControladorReservas controlador = new ControladorReservas(gestor);

        // Crear algunas habitaciones de ejemplo
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(101, "Individual", 100.0, gestor));
        habitaciones.add(new Habitacion(102, "Doble", 150.0, gestor));
        habitaciones.add(new Habitacion(201, "Suite", 250.0, gestor));

        // Crear algunos clientes de ejemplo
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Juan Pérez", "juan@email.com"));
        clientes.add(new Cliente("María López", "maria@email.com"));
        clientes.add(new Cliente("Carlos Ruiz", "carlos@email.com"));

        boolean ejecutando = true;
        
        while (ejecutando) {
            System.out.println("\nSISTEMA DE GESTIÓN DE RESERVAS DE HOTEL");
            System.out.println("==========================================");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Consultar disponibilidad de habitación");
            System.out.println("3. Crear nueva reserva");
            System.out.println("4. Ver todas las reservas");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Ver clientes registrados");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("\nHABITACIONES DISPONIBLES:");
                    for (Habitacion hab : habitaciones) {
                        System.out.println("• " + hab);
                    }
                    break;

                case 2:
                    System.out.println("\nCONSULTAR DISPONIBILIDAD");
                    System.out.print("Ingrese número de habitación: ");
                    int numeroHab = Integer.parseInt(scanner.nextLine());
                    Habitacion habitacion = buscarHabitacion(habitaciones, numeroHab);
                    if (habitacion != null) {
                        controlador.consultarDisponibilidad(habitacion);
                    } else {
                        System.out.println("Habitación no encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("\n CREAR NUEVA RESERVA");
                    
                    // Seleccionar cliente
                    System.out.println("Clientes disponibles:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i));
                    }
                    System.out.print("Seleccione cliente: ");
                    int idxCliente = Integer.parseInt(scanner.nextLine()) - 1;
                    
                    // Seleccionar habitación
                    System.out.println(" Habitaciones disponibles:");
                    for (int i = 0; i < habitaciones.size(); i++) {
                        System.out.println((i + 1) + ". " + habitaciones.get(i));
                    }
                    System.out.print("Seleccione habitación: ");
                    int idxHabitacion = Integer.parseInt(scanner.nextLine()) - 1;
                    
                    // Ingresar fechas
                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    LocalDate inicio = LocalDate.parse(scanner.nextLine());
                    
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    LocalDate fin = LocalDate.parse(scanner.nextLine());
                    
                    // Validar fechas
                    if (inicio.isBefore(LocalDate.now())) {
                        System.out.println("La fecha de inicio no puede ser en el pasado.");
                        break;
                    }
                    if (fin.isBefore(inicio)) {
                        System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                        break;
                    }
                    
                    // Crear reserva
                    if (idxCliente >= 0 && idxCliente < clientes.size() && 
                        idxHabitacion >= 0 && idxHabitacion < habitaciones.size()) {
                        controlador.crearReserva(
                            clientes.get(idxCliente),
                            habitaciones.get(idxHabitacion),
                            inicio,
                            fin
                        );
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;

                case 4:
                    System.out.println("\n TODAS LAS RESERVAS");
                    controlador.mostrarReservas();
                    break;

                case 5:
                    System.out.println("\nCANCELAR RESERVA");
                    controlador.cancelarReserva();
                    break;

                case 6:
                    System.out.println("\n👥 CLIENTES REGISTRADOS:");
                    for (Cliente cliente : clientes) {
                        System.out.println("• " + cliente);
                    }
                    break;

                case 7:
                    System.out.println("¡Hasta pronto!");
                    ejecutando = false;
                    break;

                default:
                    System.out.println("opción inválida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    private static Habitacion buscarHabitacion(List<Habitacion> habitaciones, int numero) {
        for (Habitacion hab : habitaciones) {
            if (hab.getNumero() == numero) {
                return hab;
            }
        }
        return null;
    }
}
