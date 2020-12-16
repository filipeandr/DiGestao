package view.TelaLogin04;

import controlador.ControladorLogin;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.dadosTemporarios.SalvarTemporario;
import validacao.AcoesValidacaoCampo;
import validacao.TelasMensagem;
import validacao.ValidacoesInternas;

public class TelaLogin04View implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    private final MudarTela ci = new MudarTela();
    private final ControladorLogin cl = new ControladorLogin();

    @FXML
    private Button login;

    @FXML
    private ComboBox<String> estado;

    @FXML
    private Button etpAnterior;

    @FXML
    private Button concluir;

    @FXML
    private TextField cidade;

    @FXML
    private TextField numResidencia;

    @FXML
    private TextField rua;

    @FXML
    private TextField cep;

    @FXML
    private TextField bairro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.removeAll(list);
        String ac = "AC";
        String al = "AL";
        String ap = "AP";
        String am = "AM";
        String ba = "BA";
        String ce = "CE";
        String es = "ES";
        String go = "GO";
        String ma = "MA";
        String mt = "MT";
        String ms = "MS";
        String mg = "MG";
        String pa = "PA";
        String pb = "PB";
        String pr = "PR";
        String pe = "PE";
        String pi = "PI";
        String rj = "RJ";
        String rn = "RN";
        String rs = "RS";
        String ro = "RO";
        String rr = "RR";
        String sc = "SC";
        String sp = "SP";
        String se = "SE";
        String to = "TO";
        String df = "DF";
        list.addAll(ac, al, ap, am, ba, ce, es, go, ma, mt, ms, mg, pa, pb, pr,
                pe, pi, rj, rn, rs, ro, rr, sc, sp, se, to, df);
        estado.getItems().addAll(list);

        cidade.setTooltip(new Tooltip("- Somente caracteres alfabéticos\n- Entre 3 e 30 caracteres"));
        numResidencia.setTooltip(new Tooltip("- Somente caracteres numéricos\n- Entre 1 e 6 caracteres"));
        rua.setTooltip(new Tooltip("- Somente caracteres alfanuméricos\n- Entre 3 e 50 caracteres"));
        bairro.setTooltip(new Tooltip("- Somente caracteres alfanuméricos\n- Entre 1 e 30 caracteres"));
        cep.setTooltip(new Tooltip("- CEP válido"));
        estado.getSelectionModel().select(go);
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == etpAnterior) {
            ci.IniciarCadastro2(actionEvent);
        }
        if (actionEvent.getSource() == login) {
            ci.IniciarLogin(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin04.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void dados(ActionEvent actionEvent) throws IOException {
        String cid, numR, r, c, ba, est;

        cid = cidade.getText();
        numR = numResidencia.getText();
        r = rua.getText();
        c = cep.getText();
        ba = bairro.getText();
        est = estado.getSelectionModel().getSelectedItem();

        if (actionEvent.getSource() == concluir) {
            boolean erro = false;

            if (!ValidacoesInternas.validarTamanho(cid, 3, 30)) {
                AcoesValidacaoCampo.setCampoMensagem(cidade, "Cidade inválida");
                erro = true;
            }

            if (!ValidacoesInternas.validarTamanho(ba, 1, 30)) {
                AcoesValidacaoCampo.setCampoMensagem(bairro, "Bairro inválido");
                erro = true;
            }

            if (!ValidacoesInternas.validarTamanho(r, 3, 50)) {
                AcoesValidacaoCampo.setCampoMensagem(rua, "Rua inválida");
                erro = true;
            }

            if (!ValidacoesInternas.validarTamanho(numR, 1, 6)) {
                AcoesValidacaoCampo.setCampoMensagem(numResidencia, "Número inválido");
                erro = true;
            }

            if (!ValidacoesInternas.validarTamanho(c, 8, 8)) {
                AcoesValidacaoCampo.setCampoMensagem(cep, "CEP inválido");
                erro = true;
            }

            if (!erro) {
                String dados = cid + ";" + ba + ";" + r + ";" + numR + ";" + c + ";" + est;
                SalvarTemporario.salvarDados(dados, "src/modelo/dadosTemporarios/arqCad3.txt");
                cl.dados();
                ci.IniciarLogin(actionEvent);
            } else {
                TelasMensagem.setErroMensagem("Campo(s) inválido(s)...", "Erro no cadastro");
            }
        }
    }

    @FXML
    void formatarCampo(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(cidade);
        AcoesValidacaoCampo.limpaFormatacao(bairro);
        AcoesValidacaoCampo.limpaFormatacao(rua);
        AcoesValidacaoCampo.limpaFormatacao(numResidencia);
        AcoesValidacaoCampo.limpaFormatacao(cep);
    }
}
