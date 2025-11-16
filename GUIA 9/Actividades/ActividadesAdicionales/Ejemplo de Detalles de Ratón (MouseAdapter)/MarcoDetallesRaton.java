package swingdemoo;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;
import javax.swing.SwingUtilities; 

public class MarcoDetallesRaton extends JFrame {
    private String detalles; 
    private final JLabel barraEstado; 

    public MarcoDetallesRaton(String title) {
        super(title);

        barraEstado = new JLabel("Haga clic en el raton");
        add(barraEstado, BorderLayout.SOUTH);
        addMouseListener(new ManejadorClicRaton()); 
    }

    private class ManejadorClicRaton extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent evento) {
            
            detalles = String.format("Se oprimió %d vez(veces)",
                evento.getClickCount());

            
            if (SwingUtilities.isRightMouseButton(evento)) 
                detalles += " con el boton derecho del raton";
            else if (SwingUtilities.isMiddleMouseButton(evento)) 
                detalles += " con el boton central del raton";
            else 
                detalles += " con el boton izquierdo del raton";

            barraEstado.setText(detalles);
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "Mouse Details - Chuma Condo - Morales Cárdenas";
                    MarcoDetallesRaton marcoDetallesRaton = new MarcoDetallesRaton(title);
                    marcoDetallesRaton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    marcoDetallesRaton.setSize(400, 150);
                    marcoDetallesRaton.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
