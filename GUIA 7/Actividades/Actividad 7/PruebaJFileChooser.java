package Acti;

public class PruebaJFileChooser {
    public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            DemoJFileChooser aplicacion = new DemoJFileChooser();
            aplicacion.setVisible(true);
        });
    }
}
