package Acti;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DemoJFileChooser extends JFrame {
    private final JTextArea areaSalida;

    public DemoJFileChooser() {
        super("Demo de JFileChooser");
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        add(scrollPane, BorderLayout.CENTER);
        
        JButton botonAnalizar = new JButton("Seleccionar Archivo/Directorio");
        botonAnalizar.addActionListener(e -> {
            try {
                analizarRuta();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(botonAnalizar);
        add(panelBoton, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void analizarRuta() throws IOException {
        Path ruta = obtenerRutaArchivoDirectorio();

        if (ruta != null && Files.exists(ruta)) {
            StringBuilder builder = new StringBuilder();
            builder.append("=== INFORMACIÓN DEL ARCHIVO/DIRECTORIO ===\n\n");
            builder.append(String.format("Nombre: %s%n%n", ruta.getFileName()));
            builder.append(String.format("Tipo: %s%n", Files.isDirectory(ruta) ? "Directorio" : "Archivo"));
            builder.append(String.format("Ruta absoluta: %s%n", ruta.isAbsolute() ? "Sí" : "No"));
            builder.append(String.format("Última modificación: %s%n", Files.getLastModifiedTime(ruta)));
            builder.append(String.format("Tamaño: %,d bytes%n", Files.size(ruta)));
            builder.append(String.format("Ruta: %s%n", ruta));
            builder.append(String.format("Ruta absoluta: %s%n", ruta.toAbsolutePath()));

            if (Files.isDirectory(ruta)) {
                builder.append(String.format("%n=== CONTENIDO DEL DIRECTORIO ===%n"));
                try (DirectoryStream<Path> flujoDirectorio = Files.newDirectoryStream(ruta)) {
                    int contador = 0;
                    for (Path p : flujoDirectorio) {
                        String tipo = Files.isDirectory(p) ? "[DIR]  " : "[FILE] ";
                        builder.append(String.format("%s %s%n", tipo, p.getFileName()));
                        contador++;
                    }
                    builder.append(String.format("%nTotal de elementos: %d%n", contador));
                }
            }
            areaSalida.setText(builder.toString());
        } else if (ruta != null) {
            JOptionPane.showMessageDialog(this, 
                ruta.getFileName() + " no existe.", 
                "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Path obtenerRutaArchivoDirectorio() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setDialogTitle("Seleccione un archivo o directorio");
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        selectorArchivos.setApproveButtonText("Seleccionar");
        
        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            return selectorArchivos.getSelectedFile().toPath();
        }
        return null;
    }
}
