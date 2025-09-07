import java.util.*;

public class ModuloServicios {
    
    // INTERFACES SEGREGADAS
    public interface IServicioLimpieza {
        void solicitarLimpieza();
        void reportarProblema(String problema);
    }
    
    public interface IServicioComida {
        void solicitarComida(String tipo);
        void solicitarRoomService(String pedido);
    }
    
    public interface IServicioLavanderia {
        void solicitarLavanderia(int cantidad);
        double obtenerCostoLavanderia();
    }
    
    // IMPLEMENTACIONES
    public static class ServicioLimpieza implements IServicioLimpieza {
        @Override
        public void solicitarLimpieza() {
            System.out.println("Limpieza solicitada - será realizada en 30 minutos");
        }
        
        @Override
        public void reportarProblema(String problema) {
            System.out.println("Problema reportado: " + problema);
        }
    }
    
    public static class ServicioComida implements IServicioComida {
        @Override
        public void solicitarComida(String tipo) {
            System.out.println("Comida " + tipo + " solicitada - entrega en 45 minutos");
        }
        
        @Override
        public void solicitarRoomService(String pedido) {
            System.out.println("Room service: " + pedido + " - entrega en 25 minutos");
        }
    }
    
    // HABITACIONES CON SERVICIOS
    public static class HabitacionConServicios extends SistemaReservasCore.Habitacion 
                                              implements IServicioLimpieza {
        
        private IServicioLimpieza servicioLimpieza;
        
        public HabitacionConServicios(int numero, double precio, 
                                    SistemaReservasCore.IGestorDisponibilidad gestor) {
            super(numero, "Con Servicios", precio, gestor);
            this.servicioLimpieza = new ServicioLimpieza();
        }
        
        @Override
        public void solicitarLimpieza() {
            servicioLimpieza.solicitarLimpieza();
        }
        
        @Override
        public void reportarProblema(String problema) {
            servicioLimpieza.reportarProblema(problema);
        }
    }
    
    public static class HabitacionPremium extends SistemaReservasCore.Habitacion 
                                         implements IServicioLimpieza, IServicioComida {
        
        private IServicioLimpieza servicioLimpieza;
        private IServicioComida servicioComida;
        
        public HabitacionPremium(int numero, double precio, 
                               SistemaReservasCore.IGestorDisponibilidad gestor) {
            super(numero, "Premium", precio, gestor);
            this.servicioLimpieza = new ServicioLimpieza();
            this.servicioComida = new ServicioComida();
        }
        
        @Override
        public void solicitarLimpieza() {
            servicioLimpieza.solicitarLimpieza();
        }
        
        @Override
        public void reportarProblema(String problema) {
            servicioLimpieza.reportarProblema(problema);
        }
        
        @Override
        public void solicitarComida(String tipo) {
            servicioComida.solicitarComida(tipo);
        }
        
        @Override
        public void solicitarRoomService(String pedido) {
            servicioComida.solicitarRoomService(pedido);
        }
    }
}
