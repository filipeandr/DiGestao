package validacao;

import javafx.scene.control.TextField;

public class AcoesValidacaoCampo {

    public static void setCampoMensagem(TextField tf, String mensagem) {
        tf.setText("");
        tf.setStyle("");
        tf.setStyle("-fx-prompt-text-fill: red;" + "-fx-background-color:transparent;");
        tf.setPromptText(mensagem);
    }

    public static void limpaFormatacao(TextField tf) {
        tf.setPromptText("");
        tf.setStyle("");
        tf.setStyle("-fx-background-color:transparent;");
    }
}
