package app2;

import javax.swing.SwingUtilities;

public class GerenciadorContatos2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContatosGUI();
            }
        });
    }
    
}