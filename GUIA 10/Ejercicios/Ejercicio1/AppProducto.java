package swingdemoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AppProducto extends JFrame {

    private Producto producto;

    private JTextField nombreField;
    private JTextField precioField;
    private JTextField cantidadStockField;
    private JTextField categoriaField;
    private JButton actualizarButton;
    private JLabel infoLabel; 

    public AppProducto() {
        super("Gestión de Producto (Binding Manual)");
        
        producto = new Producto("Laptop", 1200.50, 5, "Electrónica");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();

        nombreField = new JTextField(producto.getNombre(), 20);
        precioField = new JTextField(String.valueOf(producto.getPrecio()), 20);
        cantidadStockField = new JTextField(String.valueOf(producto.getCantidadStock()), 20);
        categoriaField = new JTextField(producto.getCategoria(), 20);
        
        actualizarButton = new JButton("Actualizar Producto");
        infoLabel = new JLabel(formatProductInfo(producto, "Datos iniciales cargados"));
        
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    String nombre = nombreField.getText();
                    double precio = Double.parseDouble(precioField.getText());
                    int cantidadStock = Integer.parseInt(cantidadStockField.getText());
                    String categoria = categoriaField.getText();

                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCantidadStock(cantidadStock);
                    producto.setCategoria(categoria);

                    infoLabel.setText(formatProductInfo(producto, "Modelo actualizado:"));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppProducto.this, 
                        "Error: Precio y Cantidad deben ser números válidos.", "Error de Validación", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int y = 0; 

        gbc.gridx = 0; gbc.gridy = y; add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.weightx = 1.0; add(nombreField, gbc);

        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0; add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.weightx = 1.0; add(precioField, gbc);

        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0; add(new JLabel("Cantidad en Stock:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.weightx = 1.0; add(cantidadStockField, gbc);

        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0; add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.weightx = 1.0; add(categoriaField, gbc);

        gbc.gridx = 0; gbc.gridy = y; 
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 15, 5); 
        add(actualizarButton, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(infoLabel, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private String formatProductInfo(Producto p, String titulo) {
        return String.format(
            "<html><b>%s</b><br>" +
            "&nbsp;Nombre: %s<br>" +
            "&nbsp;Precio: $%.2f<br>" +
            "&nbsp;Stock: %d unidades<br>" +
            "&nbsp;Categoría: %s</html>",
            titulo, p.getNombre(), p.getPrecio(), p.getCantidadStock(), p.getCategoria()
        );
    }
}
