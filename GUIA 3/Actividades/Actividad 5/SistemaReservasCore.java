import java.util.*;
import java.time.LocalDate;

public class SistemaReservasCore {
    
    // INTERFACE PARA HABITACIONES
    public interface IHabitacion {
        int getNumero();
        String getTipo();
        double getPrecio();
        boolean estaDisponible(LocalDate inicio, LocalDate fin);
        String toString();
    }
    
    // INTERFACE PARA CLIENTES
    public interface ICliente {
        String getNombre();
        String getContacto();
        String toString();
    }
    
    // INTERFACE PARA RESERVAS
    public interface IReserva {
        int getNumeroHabitacion();
        LocalDate getFechaInicio();
        LocalDate getFechaFin();
        ICliente getCliente();
        IHabitacion getHabitacion();
        String toString();
    }
    
    // INTERFACE PARA GESTOR DE DISPONIBILIDAD
    public interface IGestorDisponibilidad {
        boolean estaDisponible(int numeroHabitacion, LocalDate fechaInicio, LocalDate fechaFin);
        void agregarReserva(IReserva reserva);
        void cancelarReserva(IReserva reserva);
        List<IReserva> getReservas();
    }
    
    // IMPLEMENTACIONES
    public static class Cliente implements ICliente {
        private String nombre;
        private String contacto;
        
        public Cliente(String nombre, String contacto) {
            this.nombre = nombre;
            this.contacto = contacto;
        }
        
        public String getNombre() { return nombre; }
        public String getContacto() { return contacto; }
        
        @Override
        public String toString() {
            return nombre + " (" + contacto + ")";
        }
    }
    
    public static class Habitacion implements IHabitacion {
        private int numero;
        private String tipo;
        private double precio;
        private IGestorDisponibilidad gestorDisponibilidad;
        
        public Habitacion(int numero, String tipo, double precio, 
                         IGestorDisponibilidad gestorDisponibilidad) {
            this.numero = numero;
            this.tipo = tipo;
            this.precio = precio;
            this.gestorDisponibilidad = gestorDisponibilidad;
        }
        
        public int getNumero() { return numero; }
        public String getTipo() { return tipo; }
        public double getPrecio() { return precio; }
        
        public boolean estaDisponible(LocalDate inicio, LocalDate fin) {
            return gestorDisponibilidad.estaDisponible(numero, inicio, fin);
        }
        
        @Override
        public String toString() {
            return "Habitacion #" + numero + " - " + tipo + " - $" + precio + "/noche";
        }
    }
    
    public static class Reserva implements IReserva {
        private ICliente cliente;
        private IHabitacion habitacion;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        
        public Reserva(ICliente cliente, IHabitacion habitacion, 
                      LocalDate fechaInicio, LocalDate fechaFin) {
            this.cliente = cliente;
            this.habitacion = habitacion;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
        }
        
        public int getNumeroHabitacion() { return habitacion.getNumero(); }
        public LocalDate getFechaInicio() { return fechaInicio; }
        public LocalDate getFechaFin() { return fechaFin; }
        public ICliente getCliente() { return cliente; }
        public IHabitacion getHabitacion() { return habitacion; }
        
        @Override
        public String toString() {
            return "Reserva{cliente=" + cliente.getNombre() +
                   ", habitacion=" + habitacion.getNumero() +
                   ", fechaInicio=" + fechaInicio +
                   ", fechaFin=" + fechaFin + "}";
        }
    }
    
    public static class GestorDisponibilidadHabitacion implements IGestorDisponibilidad {
        private List<IReserva> reservas;
        
        public GestorDisponibilidadHabitacion() {
            this.reservas = new ArrayList<>();
        }
        
        public boolean estaDisponible(int numeroHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
            if (fechaInicio.isAfter(fechaFin)) return false;
            if (fechaInicio.isBefore(LocalDate.now())) return false;
            
            for (IReserva reserva : reservas) {
                if (reserva.getNumeroHabitacion() == numeroHabitacion &&
                    !(fechaFin.isBefore(reserva.getFechaInicio()) || 
                      fechaInicio.isAfter(reserva.getFechaFin()))) {
                    return false;
                }
            }
            return true;
        }
        
        public void agregarReserva(IReserva reserva) {
            reservas.add(reserva);
        }
        
        public void cancelarReserva(IReserva reserva) {
            reservas.remove(reserva);
        }
        
        public List<IReserva> getReservas() {
            return new ArrayList<>(reservas);
        }
    }
}
