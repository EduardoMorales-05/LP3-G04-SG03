package lp;

// Interfaz Command
interface Comando {
    void ejecutar();
}

// Receiver
class Televisor {
    private boolean encendido = false;
    private int volumen = 10;
    private int canal = 1;

    public void encender() {
        encendido = true;
        System.out.println("📺 Televisor ENCENDIDO");
    }

    public void apagar() {
        encendido = false;
        System.out.println("📺 Televisor APAGADO");
    }

    public void subirVolumen() {
        if (encendido) {
            volumen++;
            System.out.println("🔊 Volumen subido a: " + volumen);
        } else {
            System.out.println("⚠️ El televisor está apagado.");
        }
    }

    public void cambiarCanal(int nuevoCanal) {
        if (encendido) {
            canal = nuevoCanal;
            System.out.println("📺 Canal cambiado a: " + canal);
        } else {
            System.out.println("⚠️ El televisor está apagado.");
        }
    }
}

// Comandos Concretos
class EncenderTV implements Comando {
    private Televisor televisor;

    public EncenderTV(Televisor televisor) {
        this.televisor = televisor;
    }

    @Override
    public void ejecutar() {
        televisor.encender();
    }
}

class ApagarTV implements Comando {
    private Televisor televisor;

    public ApagarTV(Televisor televisor) {
        this.televisor = televisor;
    }

    @Override
    public void ejecutar() {
        televisor.apagar();
    }
}

class SubirVolumen implements Comando {
    private Televisor televisor;

    public SubirVolumen(Televisor televisor) {
        this.televisor = televisor;
    }

    @Override
    public void ejecutar() {
        televisor.subirVolumen();
    }
}

class ComandoCanal implements Comando {
    private Televisor televisor;
    private int canal;

    public ComandoCanal(Televisor televisor, int canal) {
        this.televisor = televisor;
        this.canal = canal;
    }

    @Override
    public void ejecutar() {
        televisor.cambiarCanal(canal);
    }
}

public class ControlRemoto {
    private Comando comando;

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void presionarBoton() {
        if (comando != null) {
            comando.ejecutar();
        } else {
            System.out.println("⚠️ No hay comando asignado.");
        }
    }
}
