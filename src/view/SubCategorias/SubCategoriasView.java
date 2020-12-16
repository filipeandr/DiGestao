package view.SubCategorias;

import controlador.Conexao;
import controlador.ControladorProdutos;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.ProdutoDao;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import validacao.AcoesValidacaoCampo;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class SubCategoriasView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorProdutos c = new ControladorProdutos();
    private final Conexao conex = InicioView.conex;
    private final ProdutoDao pd = new ProdutoDao();
    public static ArrayList<String> dados;
    protected static ArrayList<Integer> id;
    private boolean verifica = false;
    public static int quantidadeDados;
    public static String categoriaAtual;
    public static int idAtual;

    @FXML
    private Button usuario;

    @FXML
    private Button notificacao;

    @FXML
    private Button caixa;

    @FXML
    private Button subProdutos;

    @FXML
    private Button categorias;

    @FXML
    private Button estoque;

    @FXML
    private Button reposicao;

    @FXML
    private Button setores;

    @FXML
    private VBox pnItems = null;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField categoria;

    @FXML
    private Button salvar;

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

    @FXML
    private ImageView infoAlerta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quantidadeDados = pd.quantidadeDados("categoria");

        Node[] nodes = new Node[quantidadeDados];

        dados = conex.retornaDados(" categoria", " categoria", 1);
        id = conex.retornaId(" categoria", " categoria", 1);

        for (int i = 0; i < nodes.length; i++) {
            categoriaAtual = dados.get(i);
            idAtual = id.get(i);
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("ItensCategorias.fxml"));
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                TelasMensagem.setErroMensagem("Erro ao tentar adicionar categoria:\n" + e, "Categoria");
            }
        }
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == inicio) {
            
        }
        if (actionEvent.getSource() == operacoesCaixa) {
            
        }
        if (actionEvent.getSource() == vendas) {
            
        }
        if (actionEvent.getSource() == financeiro) {
            ci.iniciarFinanceiro(actionEvent);
        }
        if (actionEvent.getSource() == subProdutos) {
            ci.iniciarSubProdutos(actionEvent);
        }
        if (actionEvent.getSource() == estoque) {
            
        }
        if (actionEvent.getSource() == reposicao) {
            ci.iniciarReposicao(actionEvent);
        }
        if (actionEvent.getSource() == setores) {
            ci.iniciarSetores(actionEvent);
        }
        if (actionEvent.getSource() == mesas) {
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == preparo) {
            ci.iniciarPreparo01View(actionEvent);
        }
    }

    public void iniciar(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SubCategorias.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(SubCategoriasView.class.getName()).log(Level.SEVERE, null, ex);
            TelasMensagem.setErroMensagem("Erro na inicialização\n" + ex, "Categoria");
        }
    }

    @FXML
    public void dados(ActionEvent actionEvent) throws IOException {
        if (categoria.getText().length() > 0) {
            if (actionEvent.getSource() == salvar) {
                if (verifica == false) {
                    c.dados(categoria.getText(), 1, "categoria", "categoria");
                    ci.iniciarCategorias(actionEvent);
                } else {
                    verifica = false;
                    AcoesValidacaoCampo.setCampoMensagem(categoria, "Categoria já foi cadastrada");
                    infoAlerta.setVisible(true);
                }
            }
        } else {
            verifica = false;
            AcoesValidacaoCampo.setCampoMensagem(categoria, "Informe a categoria");
            infoAlerta.setVisible(true);
        }

    }

    @FXML
    void verificar(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(categoria);
        infoAlerta.setVisible(false);

        new Thread() {
            @Override
            public void run() {
                do {
                    verifica = conex.verificarDadoExistente(categoria.getText().toUpperCase(),
                            quantidadeDados, dados, verifica);
                } while (categoria.isFocused());
            }
        }.start();
    }
}
