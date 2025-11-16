package swingdemoo;

import javax.swing.JFrame;

public class DemoBorderLayout {
    public static void main(String[] args) {
        String title = "BorderLayout - Chuma Condo - Morales Cárdenas";
        MarcoBorderLayout marcoBorderLayout = new MarcoBorderLayout(title);
        
        marcoBorderLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoBorderLayout.setSize(400, 200);
        marcoBorderLayout.setVisible(true);
    }
}
