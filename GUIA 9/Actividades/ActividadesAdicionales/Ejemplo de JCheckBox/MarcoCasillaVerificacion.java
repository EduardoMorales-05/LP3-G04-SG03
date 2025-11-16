package swingdemoo;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.EventQueue;

public class MarcoCasillaVerificacion extends JFrame {
    private final JTextField campoTexto; 
    private final JCheckBox negritaJCheckBox; 
    private final JCheckBox cursivaJCheckBox; 

    public MarcoCasillaVerificacion(String title) {
        super(title);
        setLayout(new FlowLayout());

        campoTexto = new JTextField("Observe como cambia el estilo de tipo de letra", 20);
        campoTexto.setFont(new Font("Serif", Font.PLAIN, 14));
        add(campoTexto); 

        negritaJCheckBox = new JCheckBox("Negrita"); 
        cursivaJCheckBox = new JCheckBox("Cursiva"); 
        add(negritaJCheckBox); 
        add(cursivaJCheckBox); 

        ManejadorCheckBox manejador = new ManejadorCheckBox();
        negritaJCheckBox.addItemListener(manejador);
        cursivaJCheckBox.addItemListener(manejador);
    }

    private class ManejadorCheckBox implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent evento) {
            Font tipoletra = null; 

            if (negritaJCheckBox.isSelected() && cursivaJCheckBox.isSelected())
                tipoletra = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
            else if (negritaJCheckBox.isSelected())
                tipoletra = new Font("Serif", Font.BOLD, 14);
            else if (cursivaJCheckBox.isSelected())
                tipoletra = new Font("Serif", Font.ITALIC, 14);
            else
                tipoletra = new Font("Serif", Font.PLAIN, 14);

            campoTexto.setFont(tipoletra);
        }
    } 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "JCheckBox - Chuma Condo - Morales Cárdenas";
                    MarcoCasillaVerificacion marcoCasillaVerificacion = 
                        new MarcoCasillaVerificacion(title);
                    marcoCasillaVerificacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    marcoCasillaVerificacion.setSize(275, 100);
                    marcoCasillaVerificacion.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
