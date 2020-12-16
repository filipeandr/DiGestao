package view.Financeiro;

import view.Preparo01.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class CategoriaView implements Initializable {
    
    @FXML
    private Label categoria;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria.setText(PratoView.categoria);
    }     
}
