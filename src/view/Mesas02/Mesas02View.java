package view.Mesas02;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import modelo.Dao.ProdutoDao;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;
import view.Mesas01.Mesas01View;

public class Mesas02View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final Conexao conex = InicioView.conex;
    private final ProdutoDao pd = new ProdutoDao();
    private final ControladorMesas cm = new ControladorMesas();
    private final MesaDao md = new MesaDao();
    ObservableList list = FXCollections.observableArrayList();
    public static ArrayList<String> dados, preco, quantidadeProduto, valorPedido,
            estadoPedido, idMesa01, categoriaProduto, tipo, caminhoImagem, idProduto;
    public static ArrayList<Integer> id;
    public static int quantidadeDados, idAtual, idMesa;
    public static String nomeProduto, precoProduto, nomeMes, quantidadePr, valorPr, caminhoImagemAtual, idProdutoAtual;
    private boolean padrao = true;
    public static double aux, subTotal;

    @FXML
    private VBox pnItems;

    @FXML
    private Button salvar;

    @FXML
    private VBox pnlProduto1;

    @FXML
    private VBox pnlProduto2;

    @FXML
    private ComboBox<String> categoria;

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
    private Label nomeMesa;

    @FXML
    private Button enviarPedido;

    @FXML
    private Label precoTotal;

    @FXML
    private Label setarCategoria;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quantidadeDados = pd.quantidadeDados("categoria");
        dados = conex.retornaDados(" categoria", " categoria", 1);
        list.removeAll(list);

        for (int i = 0; i < quantidadeDados; i++) {
            if (!"Carnes".equals(dados.get(i)) && !"Massas".equals(dados.get(i))) {
                list.add(dados.get(i));
            }
        }

        categoria.getItems().addAll(list);

        nomeMesa.setText(Mesas01View.mesaAtual);

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
            idProduto = md.retornaDados(" pedido", " id_pedido", "1", 14);

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
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos02.fxml"));
                        pnItems.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Mesas");
                    }
                }
            }

            precoTotal.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
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
            Parent root = FXMLLoader.load(getClass().getResource("Mesas02.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao iniciar tela de mesas:\n" + ex, "Mesas");
        }
    }

    @FXML
    void selecaoCategoria(ActionEvent event) {
        
        
        
        
        
        
        
        
        
        
        
        //_______________________________________________________________
        
        int count = 1;
        pnlProduto1.getChildren().clear();
        pnlProduto2.getChildren().clear();
        Node[] nodes = new Node[1];
        
        setarCategoria.setText(categoria.getValue());

        if (categoria.getValue().equals("Monte seu prato")) {
            idMesa = Mesas01View.idAtual;
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("MontarPrato.fxml"));
                pnlProduto1.getChildren().add(nodes[0]);
            } catch (IOException e) {
                TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto:\n" + e, "Mesas");
            }
        } else {
            quantidadeDados = md.quantidadeDados(categoria.getValue(), 2);

            nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" produto", " descricao", categoria.getValue(), 1);
            preco = md.retornaDados(" produto", " descricao", categoria.getValue(), 2);
            tipo = md.retornaTipoProduto(" produto", " descricao", categoria.getValue());
            id = md.retornaId(" produto", " descricao", categoria.getValue());

            for (int i = 0; i < nodes.length; i++) {
                if (tipo.get(i).equals("Produto")) {
                    nomeProduto = dados.get(i);
                    precoProduto = preco.get(i);
                    idAtual = id.get(i);
                    idMesa = Mesas01View.idAtual;

                    caminhoImagemAtual = pesquisarCaminhoImagem(Integer.toString(idAtual));
                    if (caminhoImagemAtual == null) {
                        caminhoImagemAtual = "";
                    }

                    try {
                        switch (count) {
                            case 1:
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                                pnlProduto1.getChildren().add(nodes[i]);
                                break;
                            case 2:
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                                pnlProduto2.getChildren().add(nodes[i]);
                                count = 0;
                                break;
                        }
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto:\n" + e, "Mesas");
                    }
                    count++;
                }
            }
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
            TelasMensagem.setErroMensagem("Erro ao tentar editar dados no banco:\n" + ex, "Erro banco de dados");
        }
        ci.iniciarMesas01(event);
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
