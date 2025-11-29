package lp;

import java.util.ArrayList;
import java.util.List;

// Interfaz Observer
interface Observador {
    void recibirNotificacion(String mensaje);
}

// Clase Concreta Observer
class Usuario implements Observador {
    public String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println( nombre + " ha recibido: " + mensaje);
    }
}

// Clase Subject (Sujeto)
public class SistemaNotificaciones {
    private List<Observador> observadores = new ArrayList<>();

    // Suscribir (attach)
    public void suscribir(Observador observador) {
        observadores.add(observador);
        System.out.println(" Observador suscrito.");
    }

    // Desuscribir (detach)
    public void desuscribir(Observador observador) {
        observadores.remove(observador);
        System.out.println(" Observador desuscrito.");
    }

    // Notificar (notifyObservers)
    public void enviarNotificacion(String mensaje) {
        System.out.println("\n--- NUEVO EVENTO ---");
        for (Observador obs : observadores) {
            obs.recibirNotificacion(mensaje);
        }
        System.out.println("--------------------");
    }
}
