package view.Preparo01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ItemPrato implements Initializable {
    
    @FXML
    private Label nomeProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(PratoView.quantidadePr + " x " + PratoView.nomePr);
    }     
}
