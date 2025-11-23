package swingdemoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AppAudioControl extends JFrame {

    private Clip audioClip;
    private long clipPosition = 0; 
    private final String AUDIO_FILE = "himno.wav"; 

    public AppAudioControl() {
        super("Reproductor de Música con Control Clip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnReproducir = new JButton("Reproducir");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");


        btnReproducir.addActionListener(e -> startPlayback());

        btnPausar.addActionListener(e -> pausePlayback());

        btnReanudar.addActionListener(e -> resumePlayback());

        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);

        initializeClip(); 
        
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeClip() {
        try {
            File audioFile = new File(AUDIO_FILE);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip(); 
            audioClip.open(audioStream);
            System.out.println("Audio cargado: " + AUDIO_FILE);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar la pista de audio: " + AUDIO_FILE + 
                "\nAsegúrese de que el archivo WAV existe y está en el directorio.", 
                "Error de Carga", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
          
            for (Component comp : getContentPane().getComponents()) {
                if (comp instanceof JButton) comp.setEnabled(false);
            }
        }
    }

    private void startPlayback() {
        if (audioClip != null) {
            audioClip.stop(); 
            audioClip.setMicrosecondPosition(0); 
            audioClip.start();
            clipPosition = 0; 
        }
    }

    private void pausePlayback() {
        if (audioClip != null && audioClip.isRunning()) {
            clipPosition = audioClip.getMicrosecondPosition();
            audioClip.stop(); 
        }
    }

    private void resumePlayback() {
        if (audioClip != null) {
            audioClip.setMicrosecondPosition(clipPosition); 
            audioClip.start(); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppAudioControl().setVisible(true);
            }
        });
    }
}
