package swingdemoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppRegistroTemperatura extends JFrame {

    private final JTextField[] tempFields = new JTextField[7];
    private final PanelGraficoLinea graficoPanel;

    private final String[] days = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public AppRegistroTemperatura() {
        super("Registro y Visualización de Temperatura Diaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 7, 10, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Ingresar Temperaturas (°C)"));
        
        for (int i = 0; i < 7; i++) {
            inputPanel.add(new JLabel(days[i], SwingConstants.CENTER));
        }
        for (int i = 0; i < 7; i++) {
            tempFields[i] = new JTextField("20", 5); // Valor inicial
            tempFields[i].setHorizontalAlignment(JTextField.CENTER);
            inputPanel.add(tempFields[i]);
        }
        
        JButton showGraphButton = new JButton("Mostrar Gráfico");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(showGraphButton);

        graficoPanel = new PanelGraficoLinea();
        graficoPanel.setBorder(BorderFactory.createEtchedBorder());

        showGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> temps = new ArrayList<>();
                try {
                    for (JTextField field : tempFields) {
                        temps.add(Integer.parseInt(field.getText().trim()));
                    }
                    graficoPanel.setTemperaturas(temps);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppRegistroTemperatura.this, 
                        "Por favor, ingrese valores numéricos válidos para todas las temperaturas.", 
                        "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(graficoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppRegistroTemperatura().setVisible(true);
            }
        });
    }
}
