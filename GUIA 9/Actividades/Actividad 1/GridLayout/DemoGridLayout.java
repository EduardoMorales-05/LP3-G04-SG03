package swingdemoo;

import javax.swing.JFrame;

public class DemoGridLayout {
    public static void main(String[] args) {
        String title = "GridLayout - Chuma Condo - Morales Cárdenas";
        MarcoGridLayout marcoGridLayout = new MarcoGridLayout(title);
        
        marcoGridLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoGridLayout.setSize(400, 200);
        marcoGridLayout.setVisible(true);
    }
}
