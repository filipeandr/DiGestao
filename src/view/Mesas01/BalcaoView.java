package view.Mesas01;

import controlador.MudarTela;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class BalcaoView implements Initializable {

    private final MudarTela ci = new MudarTela();

    @FXML
    private Label idMesa;

    @FXML
    private Label nomeBalcao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idMesa.setText("0");
    }

    @FXML
    void pedidos(ActionEvent actionEvent) {
        ItensMesasView.idMesaAtual = idMesa.getText();
        ItensMesasView.nomeMesaAtual = nomeBalcao.getText();
        ItensMesasView.pedidoMesa = true;

        ci.iniciarMesas01(actionEvent);
    }
}
