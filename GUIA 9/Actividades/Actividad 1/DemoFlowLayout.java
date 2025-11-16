package swingdemoo;

import javax.swing.JFrame;

public class DemoFlowLayout {
 public static void main(String[] args) {

     String title = "FlowLayout - Chuma Condo - Morales Cárdenas"; 
     MarcoFlowLayout marcoFlowLayout = new MarcoFlowLayout(title);
     
     // Configuración de la ventana
     marcoFlowLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     marcoFlowLayout.setSize(550, 90);
     marcoFlowLayout.setVisible(true);
 }
}
