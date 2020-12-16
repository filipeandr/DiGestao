package view.Preparo01;

import controlador.Conexao;
import controlador.ControladorMesas;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import modelo.Dao.ProdutoDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class Preparo01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    private final ProdutoDao pd = new ProdutoDao();
    ObservableList list = FXCollections.observableArrayList();
    ObservableList list01 = FXCollections.observableArrayList();
    private boolean estadoPr = true;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, idPedido, idMesa01,
            setores, idProduto;
    public static ArrayList<Integer> id;
    public static int idAtual, estadoAtual, botao;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto,
            categoriaPr, aux01, pesquisaMesa, idPedido01, setorPrepraro;
    public static int quantidadeDados;
    public static double aux, subTotal;
    private String pesquisa01;

    @FXML
    private VBox pnIEspera;

    @FXML
    private Button preparando;

    @FXML
    private Button pronto;

    @FXML
    private VBox pnlPreparo;

    @FXML
    private Button usuario;

    @FXML
    private Button notificacao;

    @FXML
    private Button caixa;

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
    private Button mesas;

    @FXML
    private Button balcao;

    @FXML
    private Button preparo;

    @FXML
    private ComboBox<String> setor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quantidadeDados = pd.quantidadeDados("setor");
        dados = conex.retornaDados(" setor", " setor", 2);
        list.removeAll(list);

        for (int i = 0; i < quantidadeDados; i++) {
            list.add(dados.get(i));
        }

        setor.getItems().addAll(list);

        for (int j = 2; j < 4; j++) {
            list01.clear();
            quantidadeDados = md.quantidadeDados(Integer.toString(j), 3);
            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 4);
            quantidadeProduto = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 5);
            estadoPedido = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 7);
            idMesa01 = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 8);
            categoriaProduto = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 9);
            idPedido = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 11);
            idProduto = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 14);

            for (int i = 0; i < nodes.length; i++) {

                if (categoriaProduto.get(i) == null) {
                    categoriaProduto.set(i, "a");
                }
                if (estadoPedido.get(i).equals(Integer.toString(j)) && categoriaProduto.get(i).equals("a")
                        && !dados.get(i).equals("Monte seu prato")) {
                    if (j == 2) {
                        botao = 1;
                    } else {
                        botao = 2;
                    }

                    if (idMesa01.get(i).equals("0")) {
                        pesquisaMesa = "Balcão";
                    } else {
                        conex.executaSql("select *from mesa where id_mesa = '" + idMesa01.get(i) + "'");

                        try {
                            conex.rs.next();
                            pesquisaMesa = conex.rs.getString("nome_mesa");
                        } catch (SQLException ex) {
                            TelasMensagem.setErroMensagem("Erro ao pesquisar mesa", "Preparo");
                        }
                    }

                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
                    idPedido01 = idPedido.get(i);

                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("Produto.fxml"));
                        if (j == 2) {
                            pnIEspera.getChildren().add(nodes[i]);
                        } else {
                            pnlPreparo.getChildren().add(nodes[i]);
                        }
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto", "Preparo");
                    }
                }

                if (estadoPedido.get(i).equals(Integer.toString(j)) && categoriaProduto.get(i).equals("a") && dados.get(i).equals("Monte seu prato")) {
                    if (j == 2) {
                        botao = 1;
                    } else {
                        botao = 2;
                    }

                    if (idMesa01.get(i).equals("0")) {
                        pesquisaMesa = "Balcão";
                    } else {
                        conex.executaSql("select *from mesa where id_mesa = '" + idMesa01.get(i) + "'");

                        try {
                            conex.rs.next();
                            pesquisaMesa = conex.rs.getString("nome_mesa");
                        } catch (SQLException ex) {
                            Logger.getLogger(Preparo01View.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    quantidadePr = quantidadeProduto.get(i);
                    idPedido01 = idPedido.get(i);

                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("Prato.fxml"));
                        if (j == 2) {
                            pnIEspera.getChildren().add(nodes[i]);
                        } else {
                            pnlPreparo.getChildren().add(nodes[i]);
                        }
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar prato", "Preparo");
                    }
                }
            }
        }
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

    public void iniciar(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Preparo01.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro iniciar tela Preparo01", "Erro iniciar Tela");
        }
    }

    @FXML
    void telasPreparo(ActionEvent event) {
        if (event.getSource() == pronto) {
            ci.iniciarPreparo02View(event);
        }
    }

    @FXML
    void selecaoSetor(ActionEvent event) {
        setorPrepraro = setor.getValue();
        ci.iniciarPreparo01View(event);
    }
}
