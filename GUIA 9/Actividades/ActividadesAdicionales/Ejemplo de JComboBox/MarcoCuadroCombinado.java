package swingdemoo;

import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.EventQueue;


public class MarcoCuadroCombinado extends JFrame {
    private final JComboBox<String> imagenesJComboBox;
    private final JLabel etiqueta; 

    private static final String nombres[] = 
        {"insecto1.gif", "insecto2.gif", "insectviaje.gif", "insectanim.gif"};
    private final Icon[] iconos = new Icon[nombres.length];

    public MarcoCuadroCombinado(String title) {
        super(title);
        setLayout(new FlowLayout()); 

        try {
            for (int i = 0; i < nombres.length; i++) {
                iconos[i] = new ImageIcon(getClass().getResource(nombres[i]));
            }
        } catch (NullPointerException e) {
             System.err.println("Error: No se pudieron cargar las imágenes.");
             System.err.println("Error");
             for (int i = 0; i < nombres.length; i++) {
                iconos[i] = new ImageIcon(); 
             }
        } catch (Exception e) {
             e.printStackTrace();
        }

        imagenesJComboBox = new JComboBox<String>(nombres); 
        imagenesJComboBox.setMaximumRowCount(3); 
        add(imagenesJComboBox); 

        etiqueta = new JLabel(iconos[0]); 
        add(etiqueta); 

        imagenesJComboBox.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent evento) {
                    if (evento.getStateChange() == ItemEvent.SELECTED)
                        etiqueta.setIcon(iconos[
                            imagenesJComboBox.getSelectedIndex()]);
                }
            } 
        ); 
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "JComboBox - Chuma Condo - Morales Cárdenas";
                    MarcoCuadroCombinado marcoCuadroCombinado = new MarcoCuadroCombinado(title);
                    marcoCuadroCombinado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    marcoCuadroCombinado.setSize(350, 150);
                    marcoCuadroCombinado.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
