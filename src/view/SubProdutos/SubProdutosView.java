package view.SubProdutos;

import controlador.Conexao;
import controlador.ControladorProdutos;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.ProdutoDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class SubProdutosView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorProdutos c = new ControladorProdutos();
    private final Conexao conex = InicioView.conex;
    private final ProdutoDao pd = new ProdutoDao();
    public static ArrayList<String> dados, nomeCategoria, preco, dadosEstoque, caminhoImagem;
    protected static ArrayList<Integer> id;
    private boolean verifica = false;
    public static int quantidadeDados, idAtual;
    public static String dadosAtual, categoriaAtual, precoAtual, estoqueAtual, caminhoImagemAtual;
    private static boolean flagPesquisa = false;
    private static List<String> dadosPesquisa = new ArrayList<String>();

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
    private TextField pesquisarProdutos;

    @FXML
    private VBox pnItems = null;

    @FXML
    private Button cadastrar;

    @FXML
    private ChoiceBox<String> categoria;

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
        quantidadeDados = pd.quantidadeDados("produto");

        Node[] nodes = new Node[quantidadeDados];

        dados = conex.retornaDados(" produto", " descricao", 3);
        nomeCategoria = conex.retornaDados(" produto", " descricao", 4);
        preco = conex.retornaDados(" produto", " descricao", 5);
        dadosEstoque = conex.retornaDados(" produto", " descricao", 6);
        caminhoImagem = conex.retornaDados(" produto", " descricao", 10);
        id = conex.retornaId(" produto", " descricao", 3);

        for (int i = 0; i < nodes.length; i++) {
            dadosAtual = dados.get(i);
            categoriaAtual = nomeCategoria.get(i);
            precoAtual = preco.get(i);
            estoqueAtual = dadosEstoque.get(i);
            idAtual = id.get(i);

            caminhoImagemAtual = caminhoImagem.get(i);
            if(caminhoImagemAtual == null){
                caminhoImagemAtual = "";
            }
            
            try {
                if (flagPesquisa) {
                    if (dadosPesquisa.contains(dadosAtual)) {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                        pnItems.getChildren().add(nodes[i]);
                    }
                } else {
                    nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                    pnItems.getChildren().add(nodes[i]);
                }
            } catch (IOException e) {
                TelasMensagem.setErroMensagem("Erro ao tentar carregar produtos", "Produtos");
            }
        }
        flagPesquisa = false;
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
        if (actionEvent.getSource() == categorias) {
            ci.iniciarCategorias(actionEvent);
        }
        if (actionEvent.getSource() == estoque) {
            
        }
        if (actionEvent.getSource() == reposicao) {
            ci.iniciarReposicao(actionEvent);
        }
        if (actionEvent.getSource() == setores) {
            ci.iniciarSetores(actionEvent);
        }
        if (actionEvent.getSource() == cadastrar) {
            ci.iniciarCadastrarProdutos(actionEvent);
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
            Parent root = FXMLLoader.load(getClass().getResource("SubProdutos.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(SubProdutosView.class.getName()).log(Level.SEVERE, null, ex);
            TelasMensagem.setErroMensagem("Erro na inicialização\n" + ex, "Produtos");
        }
    }

    @FXML
    public void pesquisaProduto(ActionEvent actionEvent) {
        String texto = pesquisarProdutos.getText();

        if (!dadosPesquisa.isEmpty()) {
            dadosPesquisa.clear();
        }
        conex.executaSql("select * from produto where descricao like '%" + texto + "%'");

        try {
            while (conex.rs.next()) {
                texto = conex.rs.getString("descricao");
                dadosPesquisa.add(texto);
            }

            flagPesquisa = true;
            if (dadosPesquisa.isEmpty()) {
                dadosPesquisa.add(texto);
            }

            try {
                ci.iniciarSubProdutos(actionEvent);
            } catch (IOException ex) {
                Logger.getLogger(SubProdutosView.class.getName()).log(Level.SEVERE, null, ex);
                TelasMensagem.setErroMensagem("Erro ao tentar iniciar tela de produtos:\n" + ex, "Produtos");
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao tentar pesquisar produtos:\n" + ex, "Produto");
        }
    }
}
