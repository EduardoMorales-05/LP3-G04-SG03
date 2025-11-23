package swingdemoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GeneradorFigurasInteractivo extends JFrame {

    private Color colorActual = Color.RED;
    private int figuraIndex = 0; 
    private int nivelComplejidad = 1; 
    private final Random random = new Random();
    
    private final DibujoPanel dibujoPanel;
    private final JLabel nivelLabel;

    public GeneradorFigurasInteractivo() {
        super("Generador de Figuras Interactivo (Con Niveles)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        dibujoPanel = new DibujoPanel();
        add(dibujoPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        nivelLabel = new JLabel("Nivel: ⭐");
        controlPanel.add(nivelLabel);

        JButton generarButton = new JButton("Generar Nueva Figura");
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                figuraIndex = random.nextInt(3); 
                nivelComplejidad = random.nextInt(3) + 1;
                dibujoPanel.setTransformacionAleatoria();
                
                actualizarNivelLabel();
                dibujoPanel.repaint();
                
                playAudio("sample-3s.wav"); 
            }
        });
        controlPanel.add(generarButton);
        
        controlPanel.add(new JLabel("Elegir Color:"));
        
        JButton redButton = crearBotonColor(Color.RED, "Rojo");
        JButton blueButton = crearBotonColor(Color.BLUE, "Azul");
        JButton greenButton = crearBotonColor(Color.GREEN, "Verde");

        controlPanel.add(redButton);
        controlPanel.add(blueButton);
        controlPanel.add(greenButton);

        add(controlPanel, BorderLayout.NORTH);

        setSize(600, 450);
        setLocationRelativeTo(null);
    }
    
    private void actualizarNivelLabel() {
        String estrellas = "";
        for (int i = 0; i < nivelComplejidad; i++) {
            estrellas += "⭐";
        }
        nivelLabel.setText("Nivel: " + estrellas);
    }

    private JButton crearBotonColor(Color color, String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorActual = color;
                dibujoPanel.repaint();
            }
        });
        return button;
    }

    class DibujoPanel extends JPanel {
        
        private double rotationAngle = 0;
        private double scaleFactor = 1.0;
        private int translateX = 0;

        public DibujoPanel() {
            setBackground(Color.LIGHT_GRAY);
        }

        public void setTransformacionAleatoria() {
            rotationAngle = random.nextDouble() * 360; 
            scaleFactor = 0.5 + random.nextDouble() * 1.5; 
            translateX = random.nextInt(100) - 50; 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            
            g2d.setColor(colorActual);
            
            g2d.translate(centerX + translateX, centerY);
            g2d.rotate(Math.toRadians(rotationAngle));    
            g2d.scale(scaleFactor, scaleFactor);          
            
            int size = 100;
            int halfSize = size / 2;
            
            g2d.setStroke(new BasicStroke(nivelComplejidad * 1.5f)); 
            
            if (figuraIndex == 0) {
                g2d.fillRect(-halfSize, -halfSize, size, size);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(-halfSize, -halfSize, size, size);
            } else if (figuraIndex == 1) {
                g2d.fillOval(-halfSize, -halfSize, size, size);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(-halfSize, -halfSize, size, size);
            } else {
                int numPuntos = nivelComplejidad * 2 + 1;
                int[] xPoints = new int[numPuntos];
                int[] yPoints = new int[numPuntos];
                
                for (int i = 0; i < numPuntos; i++) {
                    double angle = 2 * Math.PI * i / numPuntos;
                    xPoints[i] = (int) (size / 2 * Math.cos(angle));
                    yPoints[i] = (int) (size / 2 * Math.sin(angle));
                }
                
                g2d.fillPolygon(xPoints, yPoints, numPuntos);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(xPoints, yPoints, numPuntos);
            }
        }
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir el sonido: " + filePath);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GeneradorFigurasInteractivo().setVisible(true);
            }
        });
    }
}
