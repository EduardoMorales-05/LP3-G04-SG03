package swingdemoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AppSoundEffects extends JFrame {

    public AppSoundEffects() {
        super("Reproductor de Efectos de Sonido (5 segundos)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");

        btnAplausos.addActionListener(e -> playAudio("aplausos.wav"));
        btnCampana.addActionListener(e -> playAudio("campana.wav"));
        btnExplosion.addActionListener(e -> playAudio("explosion.wav"));

        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);

        pack();
        setLocationRelativeTo(null);
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();

            Timer timer = new Timer(4000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clip.isRunning()) {
                        clip.stop(); 
                    }
                    clip.close(); 
                }
            });
            timer.setRepeats(false);
            timer.start();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    timer.stop(); 
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir el sonido: " + filePath);
            JOptionPane.showMessageDialog(null, 
                "No se pudo cargar o reproducir el archivo: " + filePath + 
                "\nAsegúrese de que el archivo WAV existe en la carpeta del proyecto.", 
                "Error de Multimedia", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppSoundEffects().setVisible(true);
            }
        });
    }
}
