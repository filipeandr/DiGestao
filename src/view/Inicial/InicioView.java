package view.Inicial;

import controlador.Conexao;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.TelaLogin01.TelaLogin01View;

public class InicioView implements Initializable {

    private final MudarTela ci;
    public static Conexao conex;

    public InicioView() {
        ci = new MudarTela();
    }

    @FXML
    private Button usuario;

    @FXML
    private Button notificacao;

    @FXML
    private Button caixa;

    @FXML
    private Button mesas;

    @FXML
    private Button balcao;

    @FXML
    private Button preparo;

    @FXML
    private Button inicio;

    @FXML
    private Button operacoesCaixa;

    @FXML
    private Button vendas;

    @FXML
    private Button produtos;

    @FXML
    private Button financeiro;

    @FXML
    private TextField pesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conex = new Conexao();
        conex.conexao();
        TelaLogin01View.chaveAbertura = false;
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException, Exception {
        if (actionEvent.getSource() == inicio) {
            
        }
        if (actionEvent.getSource() == operacoesCaixa) {
            
        }
        if (actionEvent.getSource() == vendas) {
            
        }
        if (actionEvent.getSource() == produtos) {
            ci.iniciarProdutos(actionEvent);
        }
        if (actionEvent.getSource() == financeiro) {
            ci.iniciarFinanceiro(actionEvent);
        }
        if (actionEvent.getSource() == mesas) {
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == preparo) {
            ci.iniciarPreparo01View(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }
}
