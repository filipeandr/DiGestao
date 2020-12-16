package view.Pagar;

import view.MontarPrato02.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ItensProdutos06View implements Initializable {

    @FXML
    private Button excluir;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(PagarView.nomeProduto);
        precoProduto.setText(PagarView.quantidadePr + " x " + PagarView.valorPr);
    }
}
