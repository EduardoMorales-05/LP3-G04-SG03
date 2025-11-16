package swingdemoo;

import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;


public class MarcoFlowLayout extends JFrame implements ActionListener {
 private final JButton botonJButtonIzquierda; 
 private final JButton botonJButtonCentro;    
 private final JButton botonJButtonDerecha;   
 private final FlowLayout esquema; 
 private final Container contenedor; 


 public MarcoFlowLayout(String title) {
     super(title);

     esquema = new FlowLayout();
     contenedor = getContentPane();
     setLayout(esquema);

     botonJButtonIzquierda = new JButton("Izquierda");
     botonJButtonCentro = new JButton("Centro");
     botonJButtonDerecha = new JButton("Derecha");

     add(botonJButtonIzquierda); 
     add(botonJButtonCentro); 
     add(botonJButtonDerecha); 

     botonJButtonIzquierda.addActionListener(this);
     botonJButtonCentro.addActionListener(this);
     botonJButtonDerecha.addActionListener(this);
 }

 @Override
 public void actionPerformed(ActionEvent evento) {
     if (evento.getSource() == botonJButtonIzquierda) {
         esquema.setAlignment(FlowLayout.LEFT);
     } else if (evento.getSource() == botonJButtonCentro) {
         esquema.setAlignment(FlowLayout.CENTER);
     } else if (evento.getSource() == botonJButtonDerecha) {
         esquema.setAlignment(FlowLayout.RIGHT);
     }
     
     esquema.layoutContainer(contenedor);
 }
}
