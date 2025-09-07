import java.time.LocalDate;

public class ServicioCancelacion {
    private GestorPoliticasCancelacion gestorPoliticas;
    
    public ServicioCancelacion() {
        this.gestorPoliticas = new GestorPoliticasCancelacion();
    }
    
    public boolean procesarCancelacion(Reserva reserva, String tipoPolitica, LocalDate fechaActual) {
        PoliticaCancelacion politica = gestorPoliticas.getPolitica(tipoPolitica);
        
        if (politica.puedeCancelar(reserva, fechaActual)) {
            double penalizacion = politica.calcularPenalizacion(reserva, fechaActual);
            System.out.println("Cancelacion exitosa. Politica aplicada: " + politica.getNombrePolitica());
            System.out.println("Penalizacion: " + penalizacion + "%");
            return true;
        } else {
            System.out.println("No se puede cancelar con la politica " + politica.getNombrePolitica());
            return false;
        }
    }
    
    public void mostrarOpcionesPoliticas() {
        gestorPoliticas.mostrarPoliticasDisponibles();
    }
}
