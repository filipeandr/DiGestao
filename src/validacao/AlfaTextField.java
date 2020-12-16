package validacao;

import javafx.scene.control.TextField;

public class AlfaTextField extends TextField {

    @Override
    public void replaceText(int i, int i1, String string) {
        if (string.matches("[a-zA-Z\\s]") || string.isEmpty()) {
            super.replaceText(i, i1, string);
        }
    }

    @Override
    public void replaceSelection(String string) {
        super.replaceSelection(string);
    }
}
