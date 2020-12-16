package view.Mesas03;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import validacao.FormataMoeda;
import static validacao.FormataMoeda.mascaraDinheiro;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;
import view.Mesas02.ItensProdutos01View;

public class Mesas03View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final MesaDao md = new MesaDao();
    private final Conexao conex = InicioView.conex;
    ObservableList list = FXCollections.observableArrayList();
    public static ArrayList<String> dados, preco, quantidadeProduto, valorPedido,
            estadoPedido, idMesa01, categoriaProduto, tipo, idMesa,
            caminhoImagem, idProduto;
    public static ArrayList<Integer> id;
    public static int quantidadeDados, idAtual;
    public static String nomeProduto, precoProduto, nomeMes, quantidadePr,
            valorPr, caminhoImagemAtual, idProdutoAtual;
    private boolean padrao = true;
    public static double aux, subTotal;

    @FXML
    private Label nomeMesa;

    @FXML
    private Label precoTotal1;

    @FXML
    private VBox pnItems;

    @FXML
    private Button mais;

    @FXML
    private Button menos;

    @FXML
    private Label quantProduto;

    @FXML
    private Label precoTotal;

    @FXML
    private Button adicionarProduto;

    @FXML
    private Label nomeProduto01;

    @FXML
    private Label precoProduto01;

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
        nomeProduto01.setText(ItensProdutos01View.nomePr);
        precoProduto01.setText(ItensProdutos01View.precoPr);
        precoTotal.setText(ItensProdutos01View.precoPr);
        nomeMesa.setText(ItensMesasView.nomeMesaAtual);

        if (ItensMesasView.pedidoMesa) {
            subTotal = 0;
            quantidadeDados = md.quantidadeDados("1", 3);
            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" pedido", " id_pedido", "1", 4);
            quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "1", 5);
            valorPedido = md.retornaDados(" pedido", " id_pedido", "1", 6);
            estadoPedido = md.retornaDados(" pedido", " id_pedido", "1", 7);
            idMesa = md.retornaDados(" pedido", " id_pedido", "1", 8);
            categoriaProduto = md.retornaDados(" pedido", " id_pedido", "1", 9);
            idProduto = md.retornaDados(" pedido", " id_pedido", "1", 14);

            for (int i = 0; i < nodes.length; i++) {
                if (categoriaProduto.get(i) == null) {
                    categoriaProduto.set(i, "a");
                }
                if (estadoPedido.get(i).equals("1") && idMesa.get(i).equals(ItensMesasView.idMesaAtual)
                        && categoriaProduto.get(i).equals("a")) {
                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
                    valorPr = valorPedido.get(i);
                    valorPr = valorPedido.get(i);
                    idProdutoAtual = idProduto.get(i);

                    caminhoImagemAtual = pesquisarCaminhoImagem(idProdutoAtual);
                    if (caminhoImagemAtual == null) {
                        caminhoImagemAtual = "";
                    }

                    String preco = valorPr;
                    if (preco.contains(".") || preco.contains(",")) {
                        preco = preco.replaceAll("[.]", "");
                        preco = preco.replaceAll("[,]", ".");
                    }
                    preco = preco.replaceAll("[R$ ]", "");
                    aux = Double.parseDouble(preco);
                    aux *= Integer.parseInt(quantidadePr);
                    subTotal += aux;
                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                        pnItems.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Mesas");
                    }
                }
            }
            precoTotal1.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
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
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Mesas03.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao iniciar tela de mesas:\n" + ex, "Mesas");
        }
    }

    @FXML
    public void adicionarProduto(ActionEvent actionEvent) {
        cm.salvarProduto(Integer.parseInt(ItensProdutos01View.idPr), Integer.parseInt(ItensMesasView.idMesaAtual),
                nomeProduto01.getText(), quantProduto.getText(), precoProduto01.getText(), "1");

        ci.iniciarMesas02(actionEvent);
    }

    @FXML
    public void quantidadeProduto(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mais) {
            String qm = quantProduto.getText();
            int qmInt = 1;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt >= 1) {
                    qmInt += 1;

                    String preco = precoProduto01.getText();
                    if (preco.contains(".") || preco.contains(",")) {
                        preco = preco.replaceAll("[.]", "");
                        preco = preco.replaceAll("[,]", ".");
                    }
                    preco = preco.replaceAll("[R$ ]", "");

                    double pT = qmInt * Double.parseDouble(preco);
                    preco = FormataMoeda.mascaraDinheiro(pT, FormataMoeda.DINHEIRO_REAL);
                    precoTotal.setText(preco);
                }
            } catch (NumberFormatException e) {
            }
            qm = Integer.toString(qmInt);
            quantProduto.setText(qm);
        }
        if (actionEvent.getSource() == menos) {
            String qm = quantProduto.getText();
            int qmInt = 0;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt > 1) {
                    qmInt -= 1;

                    String preco = precoProduto01.getText();
                    if (preco.contains(".") || preco.contains(",")) {
                        preco = preco.replaceAll("[.]", "");
                        preco = preco.replaceAll("[,]", ".");
                    }
                    preco = preco.replaceAll("[R$ ]", "");

                    double pT = qmInt * Double.parseDouble(preco);
                    preco = mascaraDinheiro(pT, FormataMoeda.DINHEIRO_REAL);
                    precoTotal.setText(preco);
                }
            } catch (NumberFormatException e) {
                TelasMensagem.setErroMensagem("Erro de formatação numérica:\n" + e, "Mesas");
            }
            qm = Integer.toString(qmInt);
            quantProduto.setText(qm);
        }
    }

    public String pesquisarCaminhoImagem(String id) {
        String pesquisa = new String();
        conex.executaSql("select *from produto where id_produto = " + id);
        try {
            conex.rs.next();
            pesquisa = conex.rs.getString("caminho_imagem");
        } catch (SQLException ex) {
        }
        return pesquisa;
    }
}
