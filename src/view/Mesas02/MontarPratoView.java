package view.Mesas02;

import controlador.MudarTela;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MontarPratoView implements Initializable {

    private final MudarTela ci = new MudarTela();
    public static String idMes;

    @FXML
    private Label idMesa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idMesa.setText(Integer.toString(Mesas02View.idMesa));
    }

    @FXML
    void adicionarProduto(ActionEvent event) {
        idMes = idMesa.getText();
        ci.iniciarMontarPrato01(event);
    }
}
