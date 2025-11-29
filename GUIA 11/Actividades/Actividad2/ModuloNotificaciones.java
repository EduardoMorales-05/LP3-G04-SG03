package lp;

import java.util.ArrayList;
import java.util.List;

// --- PATRÓN OBSERVER ---

// Interfaz Observador
interface ObservadorTienda {
    void recibirAlerta(String mensaje);
}

// Observador Concreto
class Cliente implements ObservadorTienda {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirAlerta(String mensaje) {
        System.out.println("[Cliente: " + nombre + "] Nueva notificación: " + mensaje);
    }
}

// Sujeto 
class CanalOfertas {
    private List<ObservadorTienda> suscriptores = new ArrayList<>();

    public void suscribir(ObservadorTienda cliente) {
        suscriptores.add(cliente);
        System.out.println("Cliente suscrito al canal de ofertas.");
    }

    public void desuscribir(ObservadorTienda cliente) {
        suscriptores.remove(cliente);
        System.out.println("Cliente desuscrito.");
    }

    public void enviarOferta(String oferta) {
        System.out.println("\n--- ENVIANDO OFERTA A TODOS LOS SUSCRIPTORES ---");
        for (ObservadorTienda cliente : suscriptores) {
            cliente.recibirAlerta(oferta);
        }
        System.out.println("--------------------------------------------------\n");
    }
}

// Clase Módulo 
public class ModuloNotificaciones {
    private CanalOfertas canal;

    public ModuloNotificaciones() {
        this.canal = new CanalOfertas();
    }

    public void registrarNuevoCliente(String nombre) {
        Cliente nuevoCliente = new Cliente(nombre);
        canal.suscribir(nuevoCliente);
    }

    public void lanzarPromocion(String textoPromocion) {
        canal.enviarOferta(textoPromocion);
    }
}
