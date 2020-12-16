package validacao;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class TelasMensagem {

    public static void setErroMensagem(String texto, String titulo) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(texto, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void setInfoMensagem(String texto, String titulo) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(texto, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static int confirmaMensagem(String texto, String titulo) {
        Toolkit.getDefaultToolkit().beep();
        Object[] options = {"Confirmar", "Cancelar"};
        int resposta = JOptionPane.showOptionDialog(null, texto, titulo, JOptionPane.DEFAULT_OPTION, 
                JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        return resposta;
    }
}
