import vista.CarritoVista;
import controlador.CarritoControlador;

public class Main {
    public static void main(String[] args) {
        CarritoVista vista = new CarritoVista();
        CarritoControlador controlador = new CarritoControlador(vista);
        controlador.iniciar();
    }
}
