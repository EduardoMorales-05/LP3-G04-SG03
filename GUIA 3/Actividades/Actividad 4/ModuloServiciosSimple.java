import java.util.List;
import java.util.Scanner;

// MÓDULO DE SERVICIOS BÁSICOS - PRINCIPIO ISP
// Interfaces minimalistas para servicios de hotel

interface ServicioLimpieza {
    void solicitarLimpieza();
}

interface ServicioComida {
    void solicitarComida(String tipo);
}

// Habitaciones con servicios específicos
class HabitacionConLimpieza extends Habitacion implements ServicioLimpieza {
    public HabitacionConLimpieza(int numero, double precio, GestorDisponibilidadHabitacion gestor) {
        super(numero, "Con Limpieza", precio, gestor);
    }
    
    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza solicitada para habitación " + getNumero());
    }
}

class HabitacionCompleta extends Habitacion implements ServicioLimpieza, ServicioComida {
    public HabitacionCompleta(int numero, double precio, GestorDisponibilidadHabitacion gestor) {
        super(numero, "Completa", precio, gestor);
    }
    
    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza premium solicitada");
    }
    
    @Override
    public void solicitarComida(String tipo) {
        System.out.println("Comida " + tipo + " solicitada");
    }
}

// Gestor simple de servicios
class GestorServicios {
    public static void mostrarServicios(Habitacion habitacion) {
        System.out.println("\nServicios disponibles:");
        
        if (habitacion instanceof ServicioLimpieza) {
            System.out.println("- Limpieza");
        }
        if (habitacion instanceof ServicioComida) {
            System.out.println("- Comida");
        }
    }
    
    public static void usarServicios(Habitacion habitacion, String servicio) {
        switch (servicio.toLowerCase()) {
            case "limpieza":
                if (habitacion instanceof ServicioLimpieza) {
                    ((ServicioLimpieza) habitacion).solicitarLimpieza();
                } else {
                    System.out.println("Servicio no disponible");
                }
                break;
                
            case "comida":
                if (habitacion instanceof ServicioComida) {
                    System.out.print("Tipo de comida: ");
                    Scanner tempScanner = new Scanner(System.in);
                    String tipoComida = tempScanner.nextLine();
                    ((ServicioComida) habitacion).solicitarComida(tipoComida);
                } else {
                    System.out.println("Servicio no disponible");
                }
                break;
                
            default:
                System.out.println("Servicio no reconocido");
        }
    }
}

// Integración con sistema existente
class IntegradorServicios {
    public static void agregarHabitacionesConServicios(List<Habitacion> habitaciones, 
                                                     GestorDisponibilidadHabitacion gestor) {
        // Agregar nuevas habitaciones con servicios
        habitaciones.add(new HabitacionConLimpieza(301, 120.0, gestor));
        habitaciones.add(new HabitacionCompleta(302, 200.0, gestor));
        System.out.println("Habitaciones con servicios agregadas");
    }
    
    public static void gestionarServicios(List<Habitacion> habitaciones) {
        System.out.println("\n=== GESTIÓN DE SERVICIOS ===");
        
        // Mostrar habitaciones
        for (int i = 0; i < habitaciones.size(); i++) {
            Habitacion h = habitaciones.get(i);
            System.out.println((i + 1) + ". Habitación " + h.getNumero() + " - " + h.getTipo());
        }
        
        System.out.print("Seleccione habitación: ");
        Scanner scanner = new Scanner(System.in);
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (idx >= 0 && idx < habitaciones.size()) {
            Habitacion hab = habitaciones.get(idx);
            GestorServicios.mostrarServicios(hab);
            
            System.out.print("Servicio a usar (limpieza/comida): ");
            String servicio = scanner.nextLine();
            GestorServicios.usarServicios(hab, servicio);
        }
    }
}
