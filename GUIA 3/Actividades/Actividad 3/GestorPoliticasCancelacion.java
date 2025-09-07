import java.util.HashMap;
import java.util.Map;

public class GestorPoliticasCancelacion {
    private Map<String, PoliticaCancelacion> politicas;
    
    public GestorPoliticasCancelacion() {
        politicas = new HashMap<>();
        politicas.put("flexible", new PoliticaCancelacionFlexible());
        politicas.put("moderada", new PoliticaCancelacionModerada());
        politicas.put("estricta", new PoliticaCancelacionEstricta());
    }
    
    public PoliticaCancelacion getPolitica(String tipo) {
        return politicas.getOrDefault(tipo, new PoliticaCancelacionFlexible());
    }
    
    public void mostrarPoliticasDisponibles() {
        System.out.println("Politicas de cancelacion disponibles:");
        System.out.println("1. flexible - Cancelar hasta 24h antes (sin penalizacion)");
        System.out.println("2. moderada - Cancelar hasta 72h antes (50% penalizacion)");
        System.out.println("3. estricta - Cancelar hasta 7 dias antes (20% penalizacion)");
    }
}
