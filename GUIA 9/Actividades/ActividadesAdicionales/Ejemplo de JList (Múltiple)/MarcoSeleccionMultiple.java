package swingdemoo;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.EventQueue;

public class MarcoSeleccionMultiple extends JFrame {
    private final JList<String> listaJListColores; 
    private final JList<String> listaJListCopia; 
    private JButton botonJButtonCopiar; 
    private static final String[] nombresColores = {"Negro", "Azul", "Cyan",
        "Gris oscuro", "Gris", "Verde", "Gris claro", "Magenta", "Naranja",
        "Rosa", "Rojo", "Blanco", "Amarillo"};

    public MarcoSeleccionMultiple(String title) {
        super(title);
        setLayout(new FlowLayout());

        listaJListColores = new JList<String>(nombresColores); 
        listaJListColores.setVisibleRowCount(5); 
        listaJListColores.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(listaJListColores)); 

        botonJButtonCopiar = new JButton("Copiar >>>"); 
        botonJButtonCopiar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    listaJListCopia.setListData(
                        listaJListColores.getSelectedValuesList().toArray(
                            new String[0]));
                }
            } 
        ); 

        add(botonJButtonCopiar); 

        listaJListCopia = new JList<String>(); 
        listaJListCopia.setVisibleRowCount(5); 
        listaJListCopia.setFixedCellWidth(100); 
        listaJListCopia.setFixedCellHeight(15); 
        listaJListCopia.setSelectionMode(
            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        add(new JScrollPane(listaJListCopia)); 
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String title = "JList (Multiple) - Chuma Condo - Morales Cárdenas";
                    MarcoSeleccionMultiple marcoSeleccionMultiple =
                        new MarcoSeleccionMultiple(title);
                    marcoSeleccionMultiple.setDefaultCloseOperation(
                        JFrame.EXIT_ON_CLOSE);
                    marcoSeleccionMultiple.setSize(350, 140);
                    marcoSeleccionMultiple.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
