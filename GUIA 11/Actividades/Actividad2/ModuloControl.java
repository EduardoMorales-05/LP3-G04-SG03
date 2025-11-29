package lp;

// --- PATRÓN COMMAND ---

// Interfaz Comando
interface ComandoDispositivo {
    void ejecutar();
}

// Receptor
class PantallaPublicitaria {
    private String id;

    public PantallaPublicitaria(String id) {
        this.id = id;
    }

    public void encender() {
        System.out.println("📺 Pantalla [" + id + "] ENCENDIDA.");
    }

    public void apagar() {
        System.out.println("📺 Pantalla [" + id + "] APAGADA.");
    }

    public void cambiarAnuncio(String anuncio) {
        System.out.println("📺 Pantalla [" + id + "] mostrando: " + anuncio);
    }
}

// Comandos Concretos
class EncenderPantalla implements ComandoDispositivo {
    private PantallaPublicitaria pantalla;

    public EncenderPantalla(PantallaPublicitaria pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void ejecutar() {
        pantalla.encender();
    }
}

class ApagarPantalla implements ComandoDispositivo {
    private PantallaPublicitaria pantalla;

    public ApagarPantalla(PantallaPublicitaria pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void ejecutar() {
        pantalla.apagar();
    }
}

class CambiarAnuncio implements ComandoDispositivo {
    private PantallaPublicitaria pantalla;
    private String anuncio;

    public CambiarAnuncio(PantallaPublicitaria pantalla, String anuncio) {
        this.pantalla = pantalla;
        this.anuncio = anuncio;
    }

    @Override
    public void ejecutar() {
        pantalla.cambiarAnuncio(anuncio);
    }
}

// Invocador 
class ControlRemotoTienda {
    private ComandoDispositivo comando;

    public void setComando(ComandoDispositivo comando) {
        this.comando = comando;
    }

    public void presionarBoton() {
        if (comando != null) {
            comando.ejecutar();
        }
    }
}

// Clase Módulo 
public class ModuloControl {
    private PantallaPublicitaria pantallaPrincipal;
    private ControlRemotoTienda control;

    public ModuloControl() {
        this.pantallaPrincipal = new PantallaPublicitaria("MAIN-01");
        this.control = new ControlRemotoTienda();
    }

    public void iniciarSistemaVisual() {
        System.out.println("⚙️ Iniciando sistemas visuales...");
        control.setComando(new EncenderPantalla(pantallaPrincipal));
        control.presionarBoton();
        
        control.setComando(new CambiarAnuncio(pantallaPrincipal, "BIENVENIDOS A LA TIENDA"));
        control.presionarBoton();
    }

    public void apagarSistemaVisual() {
        System.out.println("⚙️ Apagando sistemas visuales...");
        control.setComando(new ApagarPantalla(pantallaPrincipal));
        control.presionarBoton();
    }
}
