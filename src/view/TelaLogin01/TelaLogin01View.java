package view.TelaLogin01;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.UsuarioDao;
import modelo.dadosTemporarios.SalvarTemporario;
import validacao.AcoesValidacaoCampo;

public class TelaLogin01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final UsuarioDao ud = new UsuarioDao();
    public static boolean chaveAbertura = false;
    public static boolean chaveFechamento = false;
    public static Conexao conex = new Conexao();

    @FXML
    private Button login;

    @FXML
    private Button cadastro;

    @FXML
    private TextField dados;

    @FXML
    private PasswordField senha;

    @FXML
    private Button entrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dados.setTooltip(new Tooltip("Email válido"));
        senha.setTooltip(new Tooltip("Senha válida"));
        if (!chaveAbertura) {
            conex.conexao();
            chaveAbertura = true;

            SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad1.txt");
            SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad2.txt");
            SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad3.txt");
        }
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == cadastro) {
            ci.IniciarCadastro(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin01.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void validar(ActionEvent actionEvent) throws IOException {
        String email = "'" + dados.getText() + "'";
        String senhav = senha.getText();
        String senhaConf = ud.ler("usuario", "email_usuario", email);

        if ("''".equals(email)) {
            AcoesValidacaoCampo.setCampoMensagem(dados, "Email não pode ser nulo");
        } else if ("".equals(senhaConf)) {
            AcoesValidacaoCampo.setCampoMensagem(dados, "Email inválido");
        } else if ("".equals(senhav)) {
            AcoesValidacaoCampo.setCampoMensagem(senha, "Senha não pode ser nula");
        } else if (!senhaConf.equals(senhav)) {
            AcoesValidacaoCampo.setCampoMensagem(senha, "Senha inválida");
        } else {
            if (actionEvent.getSource() == entrar) {
                conex.desconecta();
                chaveFechamento = true;
                ci.IniciarTelaInicial(actionEvent);
            }
        }
    }

    @FXML
    void formatarCampo(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(dados);
        AcoesValidacaoCampo.limpaFormatacao(senha);
    }
}
