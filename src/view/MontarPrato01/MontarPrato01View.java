package view.MontarPrato01;

import controlador.Conexao;
import controlador.ControladorMesas;
import controlador.MudarTela;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.stage.WindowEvent;
import modelo.Dao.MesaDao;
import modelo.Dao.ProdutoDao;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;
import view.Mesas01.Mesas01View;

public class MontarPrato01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final Conexao conex = InicioView.conex;
    private final ProdutoDao pd = new ProdutoDao();
    private final ControladorMesas cm = new ControladorMesas();
    private final MesaDao md = new MesaDao();
    ObservableList list = FXCollections.observableArrayList();
    public static ArrayList<String> dados, preco, quantidadeProduto, valorPedido, estadoPedido,
            idMesa01, categoriaPr, produtosAdicionados, categoriaProduto;
    public static ArrayList<Integer> id;
    public static int quantidadeDados, idAtual, idMesa, avancar, numProdutos;
    public static String nomeProduto, precoProduto, nomeMes, quantidadePr, valorPr;
    private boolean padrao = true;
    public static double aux, subTotal;

    @FXML
    private Label nomeMesa;

    @FXML
    private VBox pnItems;

    @FXML
    private Button enviarPedido;

    @FXML
    private VBox pnlItens;

    @FXML
    private Button etpAnterior;

    @FXML
    private Button proxCategoria;

    @FXML
    private Label categoria;

    @FXML
    private Label passos;

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
    private Label precoTotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeMesa.setText(Mesas01View.mesaAtual);
        numProdutos = 0;

        if (md.pesquisarEstadoProduto("pedido", "id_mesa", Integer.parseInt(ItensMesasView.idMesaAtual)).equals("1")) {
            subTotal = 0;
            quantidadeDados = md.quantidadeDados("1", 3);
            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" pedido", " id_pedido", "1", 4);
            quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "1", 5);
            valorPedido = md.retornaDados(" pedido", " id_pedido", "1", 6);
            estadoPedido = md.retornaDados(" pedido", " id_pedido", "1", 7);
            idMesa01 = md.retornaDados(" pedido", " id_pedido", "1", 8);
            categoriaProduto = md.retornaDados(" pedido", " id_pedido", "1", 9);

            for (int i = 0; i < nodes.length; i++) {
                if (categoriaProduto.get(i) == null) {
                    categoriaProduto.set(i, "a");
                }
                if (estadoPedido.get(i).equals("1") && idMesa01.get(i).equals(ItensMesasView.idMesaAtual)
                        && categoriaProduto.get(i).equals("a")) {
                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
                    valorPr = valorPedido.get(i);
                    valorPr = valorPedido.get(i);
                    String preco = valorPr;
                    if (preco.contains(".") || preco.contains(",")) {
                        preco = preco.replaceAll("[.]", "");
                        preco = preco.replaceAll("[,]", ".");
                    }
                    preco = preco.replaceAll("[R$ ]", "");
                    aux = Double.parseDouble(preco);
                    subTotal += aux;
                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos02.fxml"));
                        pnItems.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Montar prato");
                    }
                }
            }

            precoTotal.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
        }

        if (avancar == 0) {
            pnlItens.getChildren().clear();
            quantidadeDados = md.quantidadeDados("Composição", 4);

            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" produto", " descricao", "Composição", 1);
            preco = md.retornaDados(" produto", " descricao", "Composição", 2);
            categoriaPr = md.retornaDados(" produto", " descricao", "Composição", 3);
            id = md.retornaId(" produto", " descricao", "Composição");

            for (int i = 0; i < nodes.length; i++) {
                if (!categoriaPr.get(i).equals("Carnes") && !categoriaPr.get(i).equals("Massas")) {
                    nomeProduto = dados.get(i);
                    precoProduto = preco.get(i);
                    idAtual = id.get(i);

                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                        pnlItens.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto:\n" + e, "Montar prato");
                    }
                }
            }
        }
        if (avancar == 1) {
            pnlItens.getChildren().clear();
            quantidadeDados = md.quantidadeDados("Composição", 4);
            categoria.setText("Carnes");
            passos.setText("Passo 2/3");
            proxCategoria.setText("Massas");

            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" produto", " descricao", "Composição", 1);
            preco = md.retornaDados(" produto", " descricao", "Composição", 2);
            categoriaPr = md.retornaDados(" produto", " descricao", "Composição", 3);
            id = md.retornaId(" produto", " descricao", "Composição");

            for (int i = 0; i < nodes.length; i++) {
                if (categoriaPr.get(i).equals("Carnes")) {
                    nomeProduto = dados.get(i);
                    precoProduto = preco.get(i);
                    idAtual = id.get(i);

                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                        pnlItens.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto:\n" + e, "Montar prato");
                    }
                }
            }
        }

        if (avancar == 2) {
            pnlItens.getChildren().clear();
            quantidadeDados = md.quantidadeDados("Composição", 4);
            categoria.setText("Massas");
            passos.setText("Passo 3/3");
            proxCategoria.setText("Concluir");

            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" produto", " descricao", "Composição", 1);
            preco = md.retornaDados(" produto", " descricao", "Composição", 2);
            categoriaPr = md.retornaDados(" produto", " descricao", "Composição", 3);
            id = md.retornaId(" produto", " descricao", "Composição");

            for (int i = 0; i < nodes.length; i++) {
                if (categoriaPr.get(i).equals("Massas")) {
                    nomeProduto = dados.get(i);
                    precoProduto = preco.get(i);
                    idAtual = id.get(i);

                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                        pnlItens.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto:\n" + e, "Montar prato");
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
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pedido where categoria_pedido "
                    + "is not null and id_prato is null");

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
        avancar = 0;
    }

    public void iniciar(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MontarPrato01.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao iniciar tela de mesas:\n" + ex, "Montar prato");
        }
    }

    private void closeWindowEvent(WindowEvent event) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pedido where categoria_pedido "
                    + "is not null and id_prato is null");

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
    }

    @FXML
    void enviarPedido(ActionEvent event) {
        cm.editarEstadoPedido("2", Integer.parseInt(ItensMesasView.idMesaAtual));

        try {
            PreparedStatement pst = conex.con.prepareStatement("update mesa set estado_mesa = ? where id_mesa = ?");

            pst.setString(1, "2");
            pst.setInt(2, Integer.parseInt(ItensMesasView.idMesaAtual));

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro tentar editar dados no banco:\n" + ex, "Erro banco de dados");
        }
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pedido where categoria_pedido "
                    + "is not null and id_prato is null");

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
        avancar = 0;
        ci.iniciarMesas01(event);
    }

    @FXML
    void avancar(ActionEvent actionEvent) {
        if (actionEvent.getSource() == proxCategoria) {
            if (numProdutos == 0) {
                TelasMensagem.setInfoMensagem("Adicione pelo menos um item", "Produto");
            } else {
                avancar += 1;
                if (avancar == 3) {
                    ci.iniciarMontarPrato02(actionEvent);
                } else {
                    ci.iniciarMontarPrato01(actionEvent);
                }
            }
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pedido where categoria_pedido "
                    + "is not null and id_prato is null");

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
        avancar = 0;
        ci.iniciarMesas02(event);
    }
}
