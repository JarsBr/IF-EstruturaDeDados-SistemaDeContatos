package app;

import javax.swing.SwingUtilities;

public class GerenciadorContatos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContatosGUI();
            }
        });
    }
    
}