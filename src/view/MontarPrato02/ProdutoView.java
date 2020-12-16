package view.MontarPrato02;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ProdutoView implements Initializable {

    @FXML
    private Label nomeProduto;

    @FXML
    private Label precoProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(MontarPrato02View.nomeProduto);
        precoProduto.setText(MontarPrato02View.quantidadePr + " x " + MontarPrato02View.valorPr);
    }
}
