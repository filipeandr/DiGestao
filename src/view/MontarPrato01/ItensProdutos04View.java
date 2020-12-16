package view.MontarPrato01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ItensProdutos04View implements Initializable {

    @FXML
    private Button excluir;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;
    
    @FXML
    private Label imagemProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(MontarPrato01View.nomeProduto);
        precoProduto.setText(MontarPrato01View.quantidadePr + " x " + MontarPrato01View.valorPr);
    }
}
