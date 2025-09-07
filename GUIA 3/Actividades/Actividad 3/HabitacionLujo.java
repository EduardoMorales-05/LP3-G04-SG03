public class HabitacionLujo extends Habitacion {
    private boolean servicioConcierge;
    private boolean minibarGratis;
    
    public HabitacionLujo(int numero, double precio, 
                         GestorDisponibilidadHabitacion gestorDisponibilidad, 
                         boolean servicioConcierge, boolean minibarGratis) {
        super(numero, "De Lujo", precio, gestorDisponibilidad);
        this.servicioConcierge = servicioConcierge;
        this.minibarGratis = minibarGratis;
    }
    
    public boolean tieneServicioConcierge() {
        return servicioConcierge;
    }
    
    public boolean tieneMinibarGratis() {
        return minibarGratis;
    }
    
    // Método adicional específico de lujo, no viola LSP
    public String getServiciosIncluidos() {
        StringBuilder servicios = new StringBuilder();
        if (servicioConcierge) servicios.append("Concierge, ");
        if (minibarGratis) servicios.append("Minibar gratis, ");
        return servicios.toString();
    }
    
    // NO sobrescribimos métodos que cambien el comportamiento esperado
    // Cumplimos con el contrato de la clase base
}
