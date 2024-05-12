package afd;

import javax.swing.UIManager;

public class Main {
    
    
    static {
        //Tradução das caixas de mensagens (JOptionPane).
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.okButtonText", "OK");   
    }
    
    
    public static void main(String[] args) {         
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
    
    
}