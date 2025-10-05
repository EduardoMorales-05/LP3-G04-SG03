package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombateModel {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private Enemigo enemigoActual;
    private Random random;
    private boolean combateActivo;
    private int turno;

    public CombateModel(Jugador jugador) {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>();
        this.random = new Random();
        this.combateActivo = false;
        this.turno = 0;
    }

    // Getters
    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }
    public Enemigo getEnemigoActual() { return enemigoActual; }
    public boolean isCombateActivo() { return combateActivo; }
    public int getTurno() { return turno; }

    // Métodos de gestión de combate
    public void iniciarCombate(List<Enemigo> enemigosCombate) {
        this.enemigos = new ArrayList<>(enemigosCombate);
        this.enemigoActual = enemigos.get(0);
        this.combateActivo = true;
        this.turno = 1;
    }

    public void atacarEnemigo() {
        if (!combateActivo || enemigoActual == null) return;

        int daño = jugador.atacar();
        enemigoActual.recibirDaño(daño);
        
        if (!enemigoActual.estaVivo()) {
            enemigos.remove(enemigoActual);
            if (!enemigos.isEmpty()) {
                enemigoActual = enemigos.get(0);
            } else {
                combateActivo = false;
            }
        }
    }

    public void atacarJugador() {
        if (!combateActivo || enemigoActual == null) return;

        int daño = enemigoActual.atacar();
        jugador.recibirDaño(daño);
        
        if (!jugador.estaVivo()) {
            combateActivo = false;
        }
    }

    public void usarItem(String nombreItem) {
        if (!combateActivo) return;
        jugador.usarItem(nombreItem);
    }

    public void cambiarArma(String nombreArma) {
        Item arma = jugador.getInventario().buscarItem(nombreArma);
        if (arma != null && arma.getTipo().equals("Arma")) {
            jugador.setArmaEquipada(arma);
        }
    }

    public boolean isCombateTerminado() {
        return !combateActivo || !jugador.estaVivo() || enemigos.isEmpty();
    }

    public boolean isVictoria() {
        return !combateActivo && jugador.estaVivo() && enemigos.isEmpty();
    }

    public String getResultadoCombate() {
        if (isVictoria()) {
            return "¡Victoria! Has derrotado a todos los enemigos.";
        } else if (!jugador.estaVivo()) {
            return "Derrota... El jugador ha sido vencido.";
        } else {
            return "Combate en curso...";
        }
    }

    public List<Enemigo> generarEnemigosAleatorios(int cantidad) {
        List<Enemigo> enemigosGenerados = new ArrayList<>();
        String[] nombres = {"Goblin", "Orco", "Esqueleto", "Araña Gigante", "Lobo Salvaje"};
        String[] tipos = {"Normal", "Normal", "Normal", "Élite", "Jefe"};

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            int nivel = jugador.getNivel() + random.nextInt(3) - 1;
            if (nivel < 1) nivel = 1;
            
            String tipo = tipos[random.nextInt(tipos.length)];
            enemigosGenerados.add(new Enemigo(nombre, nivel, tipo));
        }

        return enemigosGenerados;
    }

    public void siguienteTurno() {
        turno++;
    }
}
