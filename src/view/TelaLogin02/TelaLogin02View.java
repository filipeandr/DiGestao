package view.TelaLogin02;

import controlador.MudarTela;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.dadosTemporarios.SalvarTemporario;
import validacao.AcoesValidacaoCampo;
import validacao.TelasMensagem;
import validacao.ValidacoesInternas;
import view.TelaLogin03.TelaLogin03View;

public class TelaLogin02View implements Initializable {

    private final MudarTela ci = new MudarTela();

    @FXML
    private Button login;

    @FXML
    private TextField nome;

    @FXML
    private TextField empresa;

    @FXML
    private TextField email;

    @FXML
    private TextField celular;

    @FXML
    private PasswordField senha;

    @FXML
    private PasswordField confirmarSenha;

    @FXML
    private Button proxPasso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nome.setTooltip(new Tooltip("- Somente caracteres alfabéticos\n- Entre 3 e 60 caracteres"));
        empresa.setTooltip(new Tooltip("- Somente caracteres alfabéticos\n- Entre 3 e 50 caracteres"));
        email.setTooltip(new Tooltip("- Email válido"));
        celular.setTooltip(new Tooltip("- Número de celular válido com DDD"));
        senha.setTooltip(new Tooltip("- Senha com no mínimo 6 caracteres"));
        confirmarSenha.setTooltip(new Tooltip("- A confirmação de senha deve ser igual à senha"));

        File temporario = new File("src/modelo/dadosTemporarios/arqCad1.txt");
        if (temporario.isFile()) {
            try {
                FileInputStream temp = new FileInputStream("src/modelo/dadosTemporarios/arqCad1.txt");
                InputStreamReader input = new InputStreamReader(temp);
                BufferedReader br = new BufferedReader(input);

                String linha;
                linha = br.readLine();
                String[] dados = linha.split(";");
                
                if (linha != null) {
                    nome.setText(dados[0]);
                    empresa.setText(dados[1]);
                    email.setText(dados[2]);
                    celular.setText(dados[3]);
                    senha.setText(dados[4]);
                    confirmarSenha.setText(dados[4]);
                }
                
                br.close();
                input.close();
            } catch (IOException e) {
            }
        }
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == login) {
            ci.IniciarLogin(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin02.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void dados(ActionEvent actionEvent) throws IOException {
        String nm, em, ema, cel, sen, cf;

        nm = nome.getText();
        em = empresa.getText();
        ema = email.getText();
        cel = celular.getText();
        sen = senha.getText();
        cf = confirmarSenha.getText();

        if (actionEvent.getSource() == proxPasso) {
            boolean erro = false;

            if (!ValidacoesInternas.validarTamanho(nm, 3, 60)) {
                AcoesValidacaoCampo.setCampoMensagem(nome, "Nome inválido");
                erro = true;
            }
            if (!ValidacoesInternas.validarTamanho(em, 3, 50)) {
                AcoesValidacaoCampo.setCampoMensagem(empresa, "Nome de empresa inválido");
                erro = true;
            }
            if (!ValidacoesInternas.validarEmail(ema)) {
                AcoesValidacaoCampo.setCampoMensagem(email, "Email inválido");
                erro = true;
            }
            if (!ValidacoesInternas.validarCelular(cel)) {
                AcoesValidacaoCampo.setCampoMensagem(celular, "Celular inválido");
                erro = true;
            }
            if (!ValidacoesInternas.validarTamanho(sen, 6, 99)) {
                AcoesValidacaoCampo.setCampoMensagem(senha, "Senha inválida");
                erro = true;
            }
            if (!ValidacoesInternas.validarConfSenha(sen, cf)) {
                AcoesValidacaoCampo.setCampoMensagem(confirmarSenha, "Confirmação de senha inválida");
                erro = true;
            }

            if (!erro) {
                String dados = nm + ";" + em + ";" + ema + ";" + cel + ";" + sen;
                SalvarTemporario.salvarDados(dados, "src/modelo/dadosTemporarios/arqCad1.txt");
                ci.IniciarCadastro2(actionEvent);
            } else {
                TelasMensagem.setErroMensagem("Campo(s) inválido(s)...", "Erro no cadastro");
            }
        }
    }

    @FXML
    void formatarCampo(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(nome);
        AcoesValidacaoCampo.limpaFormatacao(empresa);
        AcoesValidacaoCampo.limpaFormatacao(email);
        AcoesValidacaoCampo.limpaFormatacao(celular);
        AcoesValidacaoCampo.limpaFormatacao(senha);
        AcoesValidacaoCampo.limpaFormatacao(confirmarSenha);
    }
}
