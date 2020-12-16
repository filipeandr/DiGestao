package view.Preparo02;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class Preparo02View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    private boolean estadoPr = true;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, idPedido, idMesa01;
    public static ArrayList<Integer> id;
    public static int idAtual, estadoAtual, botao;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto,
            categoriaPr, aux01, pesquisaMesa, idPedido01;
    public static int quantidadeDados;
    public static double aux, subTotal;

    @FXML
    private VBox pnlPronto;

    @FXML
    private Button pronto;

    @FXML
    private Button preparando;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        quantidadeDados = md.quantidadeDados("4", 3);
        Node[] nodes = new Node[quantidadeDados];

        dados = md.retornaDados(" pedido", " id_pedido", "4", 4);
        quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "4", 5);
        estadoPedido = md.retornaDados(" pedido", " id_pedido", "4", 7);
        idMesa01 = md.retornaDados(" pedido", " id_pedido", "4", 8);
        categoriaProduto = md.retornaDados(" pedido", " id_pedido", "4", 9);
        idPedido = md.retornaDados(" pedido", " id_pedido", "4", 11);

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("4") && categoriaProduto.get(i).equals("a") && !dados.get(i).equals("Monte seu prato")) {
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

                    pnlPronto.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto", "Preparo");
                }
            }

            if (estadoPedido.get(i).equals("4") && categoriaProduto.get(i).equals("a") && dados.get(i).equals("Monte seu prato")) {
                if (idMesa01.get(i).equals("0")) {
                    pesquisaMesa = "Balcão";
                } else {
                    conex.executaSql("select *from mesa where id_mesa = '" + idMesa01.get(i) + "'");

                    try {
                        conex.rs.next();
                        pesquisaMesa = conex.rs.getString("nome_mesa");
                    } catch (SQLException ex) {
                        Logger.getLogger(Preparo02View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                quantidadePr = quantidadeProduto.get(i);
                idPedido01 = idPedido.get(i);

                try {
                    nodes[i] = FXMLLoader.load(getClass().getResource("Prato.fxml"));

                    pnlPronto.getChildren().add(nodes[i]);

                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar prato", "Preparo");
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
            Parent root = FXMLLoader.load(getClass().getResource("Preparo02.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro iniciar tela Preparo02", "Erro iniciar Tela");
        }
    }

    @FXML
    void telasPreparo(ActionEvent event) {
        if (event.getSource() == preparando) {
            ci.iniciarPreparo01View(event);
        }
    }
}
