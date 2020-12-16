package view.TelaLogin03;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.dadosTemporarios.SalvarTemporario;
import validacao.AcoesValidacaoCampo;
import validacao.TelasMensagem;
import validacao.ValidacoesInternas;

public class TelaLogin03View implements Initializable {

    private final MudarTela ci = new MudarTela();

    @FXML
    private Button login;

    @FXML
    private TextField nomeFantasia;

    @FXML
    private TextField celular;

    @FXML
    private Button etpAnterior;

    @FXML
    private Button proxPasso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeFantasia.setTooltip(new Tooltip("- Somente caracteres alfabéticos\n- Entre 3 e 60 caracteres"));
        celular.setTooltip(new Tooltip("- Número de celular válido com DDD"));

        File temporario = new File("src/modelo/dadosTemporarios/arqCad2.txt");
        if (temporario.isFile()) {
            try {
                FileInputStream temp = new FileInputStream("src/modelo/dadosTemporarios/arqCad2.txt");
                InputStreamReader input = new InputStreamReader(temp);
                BufferedReader br = new BufferedReader(input);

                String linha;
                linha = br.readLine();
                String[] dados = linha.split(";");

                if (linha != null) {
                    nomeFantasia.setText(dados[0]);
                    celular.setText(dados[1]);
                }

                br.close();
                input.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(TelaLogin03View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TelaLogin03View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == etpAnterior) {
            ci.IniciarCadastro(actionEvent);
        }
        if (actionEvent.getSource() == login) {
            ci.IniciarLogin(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin03.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void dados(ActionEvent actionEvent) throws IOException {
        String nf, cel;

        nf = nomeFantasia.getText();
        cel = celular.getText();

        if (actionEvent.getSource() == proxPasso) {
            boolean erro = false;

            if (!ValidacoesInternas.validarTamanho(nf, 3, 50)) {
                AcoesValidacaoCampo.setCampoMensagem(nomeFantasia, "Nome Fantasia inválido");
                erro = true;
            }

            if (!ValidacoesInternas.validarCelular(cel)) {
                AcoesValidacaoCampo.setCampoMensagem(celular, "Celular inválido");
                erro = true;
            }

            if (!erro) {
                String dados = nf + ";" + cel;
                SalvarTemporario.salvarDados(dados, "src/modelo/dadosTemporarios/arqCad2.txt");
                ci.IniciarCadastro3(actionEvent);
            } else {
                TelasMensagem.setErroMensagem("Campo(s) inválido(s)...", "Erro no cadastro");
            }
        }
    }

    @FXML
    void formatarCampo(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(nomeFantasia);
        AcoesValidacaoCampo.limpaFormatacao(celular);
    }
}
