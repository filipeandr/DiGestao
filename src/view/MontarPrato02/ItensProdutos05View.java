package view.MontarPrato02;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ItensProdutos05View implements Initializable {

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
        nomeProduto.setText(MontarPrato02View.nomeProduto);
        precoProduto.setText(MontarPrato02View.quantidadePr + " x " + MontarPrato02View.valorPr);
    }
}
