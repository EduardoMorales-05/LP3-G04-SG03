package lp;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// --- PATRÓN COMMAND + OBSERVER + STRATEGY: CASA INTELIGENTE COMPLETA ---

// --- PATRÓN STRATEGY ---
// 1. Interfaz Estrategia: Define el comportamiento de energía/brillo.
interface EstrategiaEnergia {
    int getBrillo();
    String getNombreModo();
}

// 2. Estrategias Concretas
class ModoNormal implements EstrategiaEnergia {
    @Override
    public int getBrillo() { return 100; }
    @Override
    public String getNombreModo() { return "Normal"; }
}

class ModoEco implements EstrategiaEnergia {
    @Override
    public int getBrillo() { return 50; }
    @Override
    public String getNombreModo() { return "Eco (Ahorro)"; }
}

class ModoNoche implements EstrategiaEnergia {
    @Override
    public int getBrillo() { return 20; }
    @Override
    public String getNombreModo() { return "Noche (Tenue)"; }
}

// --- PATRÓN OBSERVER ---
// 3. Interfaz Observador
interface ObservadorLuz {
    void actualizar(String estado);
}

// 4. Observador Concreto
class AppMovil implements ObservadorLuz {
    @Override
    public void actualizar(String estado) {
        System.out.println("   [AppMovil] Notificacion: La luz ahora esta " + estado);
    }
}

// --- PATRÓN COMMAND (RECEPTOR + SUJETO OBSERVER + CONTEXTO STRATEGY) ---
// 5. RECEPTOR (Receiver), SUJETO (Subject) y CONTEXTO (Context)
class Luz {
    private boolean encendida = false;
    private List<ObservadorLuz> observadores = new ArrayList<>();
    private EstrategiaEnergia estrategia; // Strategy

    public Luz() {
        this.estrategia = new ModoNormal(); // Estrategia por defecto
    }

    // Métodos Strategy
    public void setEstrategia(EstrategiaEnergia estrategia) {
        this.estrategia = estrategia;
        System.out.println("[Config] Modo de energia cambiado a: " + estrategia.getNombreModo());
        if (encendida) {
            System.out.println("[Luz] -> Ajustando brillo a " + estrategia.getBrillo() + "%");
        }
    }

    // Métodos Observer
    public void agregarObservador(ObservadorLuz obs) {
        observadores.add(obs);
    }

    public void notificar(String estado) {
        for (ObservadorLuz obs : observadores) {
            obs.actualizar(estado);
        }
    }

    // Métodos de Acción
    public void encender() {
        if (!encendida) {
            encendida = true;
            System.out.println("[Luz] -> ENCENDIDA (Modo: " + estrategia.getNombreModo() + ", Brillo: " + estrategia.getBrillo() + "%)");
            notificar("ENCENDIDA (" + estrategia.getNombreModo() + ")");
        } else {
            System.out.println("[Luz] -> Ya estaba encendida. (Brillo actual: " + estrategia.getBrillo() + "%)");
        }
    }

    public void apagar() {
        if (encendida) {
            encendida = false;
            System.out.println("[Luz] -> APAGADA (Oscuridad...)");
            notificar("APAGADA");
        } else {
            System.out.println("[Luz] -> Ya estaba apagada.");
        }
    }
}

// 6. INTERFAZ COMANDO
interface Comando {
    void ejecutar();
    void deshacer();
}

// 7. COMANDOS CONCRETOS
class ComandoEncender implements Comando {
    private Luz luz;
    public ComandoEncender(Luz luz) { this.luz = luz; }
    @Override
    public void ejecutar() { luz.encender(); }
    @Override
    public void deshacer() { luz.apagar(); }
}

class ComandoApagar implements Comando {
    private Luz luz;
    public ComandoApagar(Luz luz) { this.luz = luz; }
    @Override
    public void ejecutar() { luz.apagar(); }
    @Override
    public void deshacer() { luz.encender(); }
}

// 8. INVOCADOR
class ControlInteligente {
    private Comando comandoActual;
    private Comando ultimoComandoEjecutado;

    public void setComando(Comando comando) {
        this.comandoActual = comando;
    }

    public void presionarBoton() {
        if (comandoActual != null) {
            System.out.print("[Control] Boton presionado: ");
            comandoActual.ejecutar();
            ultimoComandoEjecutado = comandoActual;
        }
    }

    public void presionarDeshacer() {
        if (ultimoComandoEjecutado != null) {
            System.out.print("[Control] Deshaciendo ultimo cambio: ");
            ultimoComandoEjecutado.deshacer();
            ultimoComandoEjecutado = null;
        } else {
            System.out.println("[Control] No hay nada que deshacer.");
        }
    }
}

// 9. CLIENTE (Main)
public class AppCasa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Luz luzSala = new Luz();
        ControlInteligente control = new ControlInteligente();
        
        AppMovil miCelular = new AppMovil();
        luzSala.agregarObservador(miCelular);
        System.out.println("[Config] Celular conectado a la Luz de la Sala.");

        Comando encender = new ComandoEncender(luzSala);
        Comando apagar = new ComandoApagar(luzSala);

        int opcion = -1;

        System.out.println("==========================================");
        System.out.println("   CASA INTELIGENTE (3 PATRONES)          ");
        System.out.println("==========================================");

        while (opcion != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Encender Luz (Command)");
            System.out.println("2. Apagar Luz (Command)");
            System.out.println("3. Deshacer ultima accion (Command)");
            System.out.println("4. Cambiar Modo de Energia (Strategy)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Entrada invalida.");
                scanner.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    control.setComando(encender);
                    control.presionarBoton();
                    break;
                case 2:
                    control.setComando(apagar);
                    control.presionarBoton();
                    break;
                case 3:
                    control.presionarDeshacer();
                    break;
                case 4:
                    System.out.println("Seleccione Modo:");
                    System.out.println("1. Normal (100%)");
                    System.out.println("2. Eco (50%)");
                    System.out.println("3. Noche (20%)");
                    System.out.print("Opcion: ");
                    int modo = scanner.nextInt();
                    switch (modo) {
                        case 1: luzSala.setEstrategia(new ModoNormal()); break;
                        case 2: luzSala.setEstrategia(new ModoEco()); break;
                        case 3: luzSala.setEstrategia(new ModoNoche()); break;
                        default: System.out.println("Modo invalido.");
                    }
                    break;
                case 0:
                    System.out.println("\nSaliendo de la app...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
        scanner.close();
    }
}
