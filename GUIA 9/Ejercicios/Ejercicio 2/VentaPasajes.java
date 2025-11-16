package swingdemoo;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class VentaPasajes extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private final JTextField txtNombre;
    private final JTextField txtDNI;
    private final JTextField txtFecha;
    private final JComboBox<String> comboOrigen;
    private final JComboBox<String> comboDestino;
    private final String[] ciudades = {"Arequipa", "Lima", "Cusco", "Tacna", "Puno", "Moquegua"};
    private final JRadioButton radioPiso1;
    private final JRadioButton radioPiso2;
    private final ButtonGroup grupoPiso;
    private final JPanel panelPiso;
    private final JList<String> listaServicio;
    private final String[] servicios = {"Económico (S/ 50)", "Standard (S/ 80)", "VIP (S/ 120)"};
    private final JScrollPane scrollServicio;
    private final JCheckBox checkAudifonos;
    private final JCheckBox checkManta;
    private final JCheckBox checkRevistas;
    private final JPanel panelOpcionales;
    private final JButton btnReiniciar;
    private final JButton btnResumen;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentaPasajes frame = new VentaPasajes();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentaPasajes() {
        super("Venta de Pasajes Terrestres - Chuma Condo - Morales Cárdenas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        contentPane.setLayout(new GridLayout(9, 2, 10, 5));

        // 1. Nombre
        contentPane.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        contentPane.add(txtNombre);

        // 2. DNI
        contentPane.add(new JLabel("Documento de Identidad:"));
        txtDNI = new JTextField();
        contentPane.add(txtDNI);

        // 3. Fecha de Viaje
        contentPane.add(new JLabel("Fecha de Viaje (dd/mm/aaaa):"));
        txtFecha = new JTextField();
        contentPane.add(txtFecha);

        // 4. Origen
        contentPane.add(new JLabel("Lugar de Origen:"));
        comboOrigen = new JComboBox<>(ciudades);
        contentPane.add(comboOrigen);

        // 5. Destino
        contentPane.add(new JLabel("Lugar de Destino:"));
        comboDestino = new JComboBox<>(ciudades);
        comboDestino.setSelectedIndex(1); 
        contentPane.add(comboDestino);

        // 6. Piso 
        contentPane.add(new JLabel("Piso del Asiento:"));
        radioPiso1 = new JRadioButton("1er Piso", true);
        radioPiso2 = new JRadioButton("2do Piso", false);
        grupoPiso = new ButtonGroup();
        grupoPiso.add(radioPiso1);
        grupoPiso.add(radioPiso2);
        panelPiso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPiso.add(radioPiso1);
        panelPiso.add(radioPiso2);
        contentPane.add(panelPiso);

        // 7. Calidad de Servicio
        contentPane.add(new JLabel("Calidad de Servicio:"));
        listaServicio = new JList<>(servicios);
        listaServicio.setVisibleRowCount(3);
        listaServicio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollServicio = new JScrollPane(listaServicio);
        contentPane.add(scrollServicio);

        // 8. Opcionales 
        contentPane.add(new JLabel("Servicios Opcionales:"));
        checkAudifonos = new JCheckBox("Audífonos");
        checkManta = new JCheckBox("Manta");
        checkRevistas = new JCheckBox("Revistas");
        panelOpcionales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOpcionales.add(checkAudifonos);
        panelOpcionales.add(checkManta);
        panelOpcionales.add(checkRevistas);
        contentPane.add(panelOpcionales);

        // 9. Botones de Comando
        btnReiniciar = new JButton("Reiniciar");
        btnResumen = new JButton("Mostrar Resumen");
        
        btnReiniciar.addActionListener(this);
        btnResumen.addActionListener(this);
        
        contentPane.add(btnReiniciar);
        contentPane.add(btnResumen);

        pack();
    }


    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnResumen) {
            mostrarResumen();
        } else if (evento.getSource() == btnReiniciar) {
            reiniciarFormulario();
        }
    }

    private void mostrarResumen() {
        String origen = (String) comboOrigen.getSelectedItem();
        String destino = (String) comboDestino.getSelectedItem();

        if (origen != null && origen.equals(destino)) {
            JOptionPane.showMessageDialog(this, 
                                          "El origen y el destino no pueden ser iguales.\nPor favor, seleccione una ruta válida.",
                                          "Error de Ruta",
                                          JOptionPane.ERROR_MESSAGE);
            return; 
        }

        String fechaTexto = txtFecha.getText().trim();
        if (fechaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                                          "Por favor, ingrese una fecha de viaje.",
                                          "Error de Fecha",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); 

        try {
            sdf.parse(fechaTexto);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                                          "El formato de fecha es inválido (debe ser dd/mm/aaaa)\no la fecha no existe (ej. 30/02/2024).",
                                          "Error de Fecha",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("===== RESUMEN DEL PASAJE =====\n\n");
        
        resumen.append("Pasajero: ").append(txtNombre.getText()).append("\n");
        resumen.append("DNI: ").append(txtDNI.getText()).append("\n");
        resumen.append("Fecha: ").append(fechaTexto).append("\n\n"); 

        resumen.append("Ruta: ").append(origen);
        resumen.append("  hacia  ").append(destino).append("\n");

        String piso = radioPiso1.isSelected() ? "1er Piso" : "2do Piso";
        resumen.append("Piso: ").append(piso).append("\n");

        String servicio = listaServicio.getSelectedValue();
        if (servicio == null) {
            servicio = "No seleccionado";
        }
        resumen.append("Servicio: ").append(servicio).append("\n\n");

        resumen.append("Servicios Opcionales:\n");
        if (checkAudifonos.isSelected()) resumen.append("  - Audífonos\n");
        if (checkManta.isSelected()) resumen.append("  - Manta\n");
        if (checkRevistas.isSelected()) resumen.append("  - Revistas\n");
        if (!checkAudifonos.isSelected() && !checkManta.isSelected() && !checkRevistas.isSelected()) {
            resumen.append("  - Ninguno\n");
        }

        JOptionPane.showMessageDialog(this, 
                                      resumen.toString(), 
                                      "Resumen de Compra", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    private void reiniciarFormulario() {
        txtNombre.setText("");
        txtDNI.setText("");
        txtFecha.setText("");
        comboOrigen.setSelectedIndex(0);
        comboDestino.setSelectedIndex(1);
        radioPiso1.setSelected(true);
        listaServicio.clearSelection();
        checkAudifonos.setSelected(false);
        checkManta.setSelected(false);
        checkRevistas.setSelected(false);
        txtNombre.requestFocus();
    }
}
