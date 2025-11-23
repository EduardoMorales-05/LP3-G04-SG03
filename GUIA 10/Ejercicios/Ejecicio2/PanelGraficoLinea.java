package swingdemoo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelGraficoLinea extends JPanel {

    private List<Integer> temperaturas;
    private final String[] dias = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};

    public PanelGraficoLinea() {
        this.temperaturas = new ArrayList<>();
        setPreferredSize(new Dimension(600, 350));
        setBackground(Color.WHITE);
    }

    public void setTemperaturas(List<Integer> temperaturas) {
        this.temperaturas = temperaturas;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (temperaturas.size() < 7) {
            g2d.setColor(Color.GRAY);
            g2d.drawString("Ingrese 7 temperaturas y presione 'Mostrar Gráfico'", getWidth() / 2 - 150, getHeight() / 2);
            return;
        }


        int padding = 40;
        int labelPadding = 20;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding - labelPadding;
        
        int minTemp = 0;
        int maxTemp = 40; 
        int range = maxTemp - minTemp;
        int nPoints = temperaturas.size();
        int pointSize = 8;
        
        g2d.setColor(Color.BLACK);
        // Eje X (Base)
        g2d.drawLine(padding, height + padding, width + padding, height + padding);
        // Eje Y (Lateral)
        g2d.drawLine(padding, padding, padding, height + padding);

        for (int i = 0; i < nPoints; i++) {
            int x = padding + i * width / (nPoints - 1);
            g2d.drawLine(x, height + padding, x, height + padding + 5);
            g2d.drawString(dias[i], x - 10, height + padding + labelPadding);
        }
        
        g2d.drawString(String.valueOf(maxTemp) + "°C", 5, padding + 5);
        g2d.drawString(String.valueOf(minTemp) + "°C", 5, height + padding - 5);
        g2d.drawLine(padding - 5, padding, padding, padding); 


        
        // Configuración para la línea
        g2d.setColor(new Color(200, 0, 0)); 
        g2d.setStroke(new BasicStroke(2f)); 

        List<Point> graphPoints = new ArrayList<>();

        for (int temp : temperaturas) {

            int x = padding + graphPoints.size() * width / (nPoints - 1);
            
            int y = height + padding - (int)(((double)(temp - minTemp) / range) * height);
            
            graphPoints.add(new Point(x, y));
        }


        g2d.setColor(new Color(0, 100, 200)); 
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            Point p1 = graphPoints.get(i);
            Point p2 = graphPoints.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }


        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.RED);
        int i = 0;
        for (Point point : graphPoints) {
            g2d.fillOval(point.x - pointSize / 2, point.y - pointSize / 2, pointSize, pointSize);

            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(temperaturas.get(i)) + "°", point.x - 5, point.y - 10);
            i++;
        }
    }
}
