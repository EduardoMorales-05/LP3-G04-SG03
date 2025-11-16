package swingdemoo;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MarcoBoton extends JFrame {
    private final JButton botonJButtonSimple;
    private final JButton botonJButtonElegante;

    public MarcoBoton(String title) {
        super(title);
        setLayout(new FlowLayout());

        botonJButtonSimple = new JButton("Boton simple");
        add(botonJButtonSimple);

        Icon insecto1 = null;
        Icon insecto2 = null;

        try {

            insecto1 = new ImageIcon(getClass().getResource("insecto1.png"));
            insecto2 = new ImageIcon(getClass().getResource("insecto2.png"));
            
            if (insecto1.getIconWidth() == -1 || insecto2.getIconWidth() == -1) {
                 throw new NullPointerException("No se encontraron las imágenes.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: No se pudieron cargar las imágenes 'insecto1.png' o 'insecto2.png'.");
            System.err.println("Error");
        }
        
        botonJButtonElegante = new JButton("Boton elegante", insecto1); 
        botonJButtonElegante.setRolloverIcon(insecto2); 
        add(botonJButtonElegante); 

        ManejadorBoton manejador = new ManejadorBoton();
        botonJButtonElegante.addActionListener(manejador);
        botonJButtonSimple.addActionListener(manejador);
    }

    private class ManejadorBoton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            JOptionPane.showMessageDialog(MarcoBoton.this, String.format(
                "Usted oprimio: %s", evento.getActionCommand()));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "JButton - Chuma Condo - Morales Cárdenas";
                    MarcoBoton marcoBoton = new MarcoBoton(title);
                    marcoBoton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    marcoBoton.setSize(500, 110);
                    marcoBoton.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
