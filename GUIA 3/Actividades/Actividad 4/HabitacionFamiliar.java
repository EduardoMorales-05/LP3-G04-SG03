public class HabitacionFamiliar extends Habitacion {
    private int capacidadNinios;
    private boolean cunaDisponible;
    
    public HabitacionFamiliar(int numero, double precio, 
                             GestorDisponibilidadHabitacion gestorDisponibilidad, 
                             int capacidadNinios, boolean cunaDisponible) {
        super(numero, "Familiar", precio, gestorDisponibilidad);
        this.capacidadNinios = capacidadNinios;
        this.cunaDisponible = cunaDisponible;
    }
    
    public int getCapacidadNinios() {
        return capacidadNinios;
    }
    
    public boolean tieneCunaDisponible() {
        return cunaDisponible;
    }
    
    // Método adicional específico, no viola LSP
    public boolean esAptaParaFamilia(int adultos, int ninios) {
        return ninios <= capacidadNinios;
    }
    
    // Cumple con LSP: No altera el comportamiento base
}
