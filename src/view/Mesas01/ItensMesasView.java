package view.Mesas01;

import controlador.MudarTela;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItensMesasView implements Initializable {

    private final MudarTela ci = new MudarTela();
    public static String nomeMesaAtual, idMesaAtual;
    public static boolean pedidoMesa = false;

    @FXML
    private Label idMesa;

    @FXML
    private Label nomeMesa;

    @FXML
    private Button mesaPedido;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeMesa.setText(Mesas01View.mesaAtual);
        idMesa.setText(Integer.toString(Mesas01View.idAtual));
    }

    @FXML
    void pedidos(ActionEvent actionEvent) {
        idMesaAtual = idMesa.getText();
        nomeMesaAtual = nomeMesa.getText();
        pedidoMesa = true;

        ci.iniciarMesas01(actionEvent);
    }
}
