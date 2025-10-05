package controlador;

import modelo.CombateModel;
import modelo.Jugador;
import modelo.Enemigo;
import modelo.Item;
import vista.CombateView;
import java.util.List;
import java.util.Scanner;

public class CombateController {
    private CombateModel modelo;
    private CombateView vista;
    private Scanner scanner;

    public CombateController(CombateModel modelo, CombateView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarCombate() {
        List<Enemigo> enemigos = modelo.generarEnemigosAleatorios(2);
        modelo.iniciarCombate(enemigos);

        vista.mostrarMensajeCombate("¡Combate iniciado! Te enfrentas a:");
        for (Enemigo enemigo : enemigos) {
            vista.mostrarMensajeCombate("- " + enemigo.getNombre() + " (Nivel " + enemigo.getNivel() + ")");
        }

        while (!modelo.isCombateTerminado()) {
            ejecutarTurno();
        }

        vista.mostrarResultadoCombate(modelo.getResultadoCombate());
        
        if (modelo.isVictoria()) {
            otorgarRecompensas();
        } else {
            modelo.getJugador().curarCompletamente();
        }
    }

    private void ejecutarTurno() {
        vista.mostrarEstadoCombate(
            modelo.getJugador(), 
            modelo.getEnemigos(), 
            modelo.getEnemigoActual(), 
            modelo.getTurno()
        );

        boolean accionValida = false;
        while (!accionValida) {
            vista.mostrarMenuCombate();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    modelo.atacarEnemigo();
                    vista.mostrarMensajeCombate("Atacas al " + modelo.getEnemigoActual().getNombre() + "!");
                    accionValida = true;
                    break;
                case 2:
                    usarObjeto();
                    accionValida = true;
                    break;
                case 3:
                    cambiarArma();
                    break;
                case 4:
                    vista.mostrarEstadoCombate(
                        modelo.getJugador(), 
                        modelo.getEnemigos(), 
                        modelo.getEnemigoActual(), 
                        modelo.getTurno()
                    );
                    break;
                case 5:
                    if (intentarHuir()) {
                        return;
                    }
                    accionValida = true;
                    break;
                default:
                    vista.mostrarMensajeError("Opción inválida.");
            }
        }

        if (modelo.isCombateTerminado()) {
            return;
        }

        vista.mostrarSeparador();
        Enemigo enemigoActual = modelo.getEnemigoActual();
        vista.mostrarAccionEnemigo(enemigoActual.realizarAccionAleatoria());
        modelo.atacarJugador();
        vista.mostrarMensajeCombate(enemigoActual.getNombre() + " te ataca!");

        modelo.siguienteTurno();
    }

    private void usarObjeto() {
        vista.mostrarMenuObjetos();
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                modelo.usarItem("Poción de Vida");
                break;
            case 2:
                modelo.usarItem("Poción de Mana");
                break;
            case 3:
                modelo.usarItem("Antídoto");
                break;
            case 4:
                vista.mostrarMensajeCombate("Regresando al menú de combate...");
                break;
            default:
                vista.mostrarMensajeError("Objeto no válido.");
        }
    }

    private void cambiarArma() {
        vista.mostrarMenuArmas();
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                modelo.cambiarArma("Espada");
                vista.mostrarMensajeCombate("Has equipado la Espada.");
                break;
            case 2:
                modelo.cambiarArma("Arco");
                vista.mostrarMensajeCombate("Has equipado el Arco.");
                break;
            case 3:
                modelo.cambiarArma("Daga");
                vista.mostrarMensajeCombate("Has equipado la Daga.");
                break;
            case 4:
                vista.mostrarMensajeCombate("Regresando al menú de combate...");
                break;
            default:
                vista.mostrarMensajeError("Arma no válida.");
        }
    }

    private boolean intentarHuir() {
        if (Math.random() > 0.5) {
            vista.mostrarMensajeCombate("¡Logras huir del combate!");
            modelo.getJugador().recibirDaño(10);
            return true;
        } else {
            vista.mostrarMensajeCombate("¡No logras huir! El enemigo te ataca.");
            modelo.atacarJugador();
            return false;
        }
    }

    private void otorgarRecompensas() {
        Jugador jugador = modelo.getJugador();
        vista.mostrarMensajeCombate("¡Has ganado el combate!");
        
        jugador.getInventario().agregarItem(new Item("Poción de Vida", 2, "Poción", "Restaura salud"));
        jugador.getInventario().agregarItem(new Item("Oro", 50, "Recurso", "Monedas de oro"));
        
        vista.mostrarMensajeCombate("Recompensas obtenidas:");
        vista.mostrarMensajeCombate("- 2 Pociones de Vida");
        vista.mostrarMensajeCombate("- 50 monedas de Oro");
        
        // Posibilidad de subir de nivel
        if (Math.random() > 0.7) {
            jugador.subirNivel();
            vista.mostrarMensajeCombate("¡Has subido al nivel " + jugador.getNivel() + "!");
        }
    }
}
