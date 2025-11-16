package swingdemoo;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.EventQueue;

public class MarcoRastreadorRaton extends JFrame {
    private final JPanel panelRaton; 
    private final JLabel barraEstado; 

    public MarcoRastreadorRaton(String title) {
        super(title);

        panelRaton = new JPanel();
        panelRaton.setBackground(Color.WHITE);
        add(panelRaton, BorderLayout.CENTER); 

        barraEstado = new JLabel("Raton fuera de JPanel");
        add(barraEstado, BorderLayout.SOUTH); 

        ManejadorRaton manejador = new ManejadorRaton();
        panelRaton.addMouseListener(manejador);
        panelRaton.addMouseMotionListener(manejador);
    }

    private class ManejadorRaton implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent evento) {
            barraEstado.setText(String.format("Se hizo clic en [%d, %d]",
                evento.getX(), evento.getY()));
        }

        @Override
        public void mousePressed(MouseEvent evento) {
            barraEstado.setText(String.format("Se oprimio en [%d, %d]",
                evento.getX(), evento.getY()));
        }

        @Override
        public void mouseReleased(MouseEvent evento) {
            barraEstado.setText(String.format("Se solto en [%d, %d]",
                evento.getX(), evento.getY()));
        }

        @Override
        public void mouseEntered(MouseEvent evento) {
            barraEstado.setText(String.format("Raton entro en [%d, %d]",
                evento.getX(), evento.getY()));
            panelRaton.setBackground(Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent evento) {
            barraEstado.setText("Raton fuera de JPanel");
            panelRaton.setBackground(Color.WHITE);
        }

        @Override
        public void mouseDragged(MouseEvent evento) {
            barraEstado.setText(String.format("Se arrastro en [%d, %d]",
                evento.getX(), evento.getY()));
        }

        @Override
        public void mouseMoved(MouseEvent evento) {
            barraEstado.setText(String.format("Se movio en [%d, %d]",
                evento.getX(), evento.getY()));
        }
    } 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "Mouse Tracker - Chuma Condo - Morales Cárdenas";
                    MarcoRastreadorRaton marcoRastreadorRaton = new MarcoRastreadorRaton(title);
                    marcoRastreadorRaton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    marcoRastreadorRaton.setSize(300, 100);
                    marcoRastreadorRaton.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
