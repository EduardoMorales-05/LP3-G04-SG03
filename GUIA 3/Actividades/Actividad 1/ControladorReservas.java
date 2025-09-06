import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ControladorReservas {
    private GestorDisponibilidadHabitacion gestorDisponibilidad;
    private Scanner scanner;

    public ControladorReservas(GestorDisponibilidadHabitacion gestorDisponibilidad) {
        this.gestorDisponibilidad = gestorDisponibilidad;
        this.scanner = new Scanner(System.in);
    }

    public boolean crearReserva(Cliente cliente, Habitacion habitacion, LocalDate inicio, LocalDate fin) {
        if (habitacion.estaDisponible(inicio, fin)) {
            Reserva nuevaReserva = new Reserva(cliente, habitacion, inicio, fin);
            gestorDisponibilidad.agregarReserva(nuevaReserva);
            System.out.println(" Reserva creada exitosamente!");
            System.out.println(" Detalles: " + nuevaReserva);
            return true;
        } else {
            System.out.println(" Habitación " + habitacion.getNumero() + " no disponible en esas fechas.");
            return false;
        }
    }

    public void mostrarReservas() {
        List<Reserva> reservas = gestorDisponibilidad.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("📭 No hay reservas registradas.");
        } else {
            System.out.println(" Lista de Reservas:");
            for (int i = 0; i < reservas.size(); i++) {
                System.out.println((i + 1) + ". " + reservas.get(i));
            }
        }
    }

    public void consultarDisponibilidad(Habitacion habitacion) {
        System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());
        
        System.out.print(" Ingrese fecha de fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());

        if (habitacion.estaDisponible(inicio, fin)) {
            System.out.println(" Habitación " + habitacion.getNumero() + " DISPONIBLE del " + inicio + " al " + fin);
        } else {
            System.out.println(" Habitación " + habitacion.getNumero() + " NO DISPONIBLE en esas fechas.");
        }
    }

    public void cancelarReserva() {
        mostrarReservas();
        if (gestorDisponibilidad.getReservas().isEmpty()) {
            return;
        }

        System.out.print("❓ Ingrese el número de reserva a cancelar: ");
        int numeroReserva = Integer.parseInt(scanner.nextLine()) - 1;
        
        List<Reserva> reservas = gestorDisponibilidad.getReservas();
        if (numeroReserva >= 0 && numeroReserva < reservas.size()) {
            Reserva reserva = reservas.get(numeroReserva);
            gestorDisponibilidad.cancelarReserva(reserva);
            System.out.println(" Reserva cancelada exitosamente: " + reserva);
        } else {
            System.out.println(" Número de reserva inválido.");
        }
    }
}
