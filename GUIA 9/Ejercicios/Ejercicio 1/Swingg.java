package swingdemoo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Swingg extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JButton btnOk;
    private JButton btnReset;
    private JLabel lblResultado;
    private JPanel panelFormulario;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Swingg frame = new Swingg();
                    frame.setVisible(true);
                    // Centrar la ventana
                    frame.setLocationRelativeTo(null); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Swingg() {
        setTitle("Demo Frame Chuma Condo - Morales Cárdenas "); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 180); 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        contentPane.setLayout(new BorderLayout(10, 10));

        panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));

        panelFormulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Edad"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        btnOk = new JButton("OK");
        btnReset = new JButton("Reset");
        panelFormulario.add(btnOk);
        panelFormulario.add(btnReset);

        lblResultado = new JLabel(" ");
        lblResultado.setHorizontalAlignment(JLabel.CENTER);

        contentPane.add(panelFormulario, BorderLayout.CENTER);
        contentPane.add(lblResultado, BorderLayout.SOUTH);

        btnOk.addActionListener(this);
        btnReset.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            procesarVotacion();
        } else if (e.getSource() == btnReset) {
            reiniciarFormulario();
        }
    }

    private void procesarVotacion() {
        String nombre = txtNombre.getText();
        String edadTexto = txtEdad.getText();
        
        try {
            int edad = Integer.parseInt(edadTexto);
            
            if (edad >= 18) {
                lblResultado.setText(nombre + " Felicidades puedes votar !");
            } else {
                lblResultado.setText(nombre + " No eres mayor de edad todavia ");
            }
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese nuevamente ");
        }
    }

    private void reiniciarFormulario() {
        txtNombre.setText("");
        txtEdad.setText("");
        lblResultado.setText(" ");
        txtNombre.requestFocus();
    }
}
