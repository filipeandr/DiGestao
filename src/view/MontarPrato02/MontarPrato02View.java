package view.MontarPrato02;

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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import validacao.FormataMoeda;
import static validacao.FormataMoeda.mascaraDinheiro;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;
import view.MontarPrato01.MontarPrato01View;

public class MontarPrato02View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    public static String categoria, pesquisaPrato;
    public static int quantidadeDados, numPrato;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, idMesa01, idPrato;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto, categoriaPr, aux01;
    public static double aux, subTotal;

    @FXML
    private Label idMesa02;

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
    private Label precoProduto;

    @FXML
    private VBox pnlItens;

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
    private VBox pnlProdutos;

    @FXML
    private Label precoTotal01;

    @FXML
    private Button enviarPedido;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idMesa02.setText(ItensMesasView.nomeMesaAtual);

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

            precoTotal01.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
            subTotal = 0;
        }

        categoria = "Guarnições";

        quantidadeDados = md.quantidadeDados("1", 3);
        Node[] nodes = new Node[quantidadeDados];
        Node[] nodes02 = new Node[1];

        try {
            nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
            pnlProdutos.getChildren().add(nodes02[0]);
        } catch (IOException ex) {
            Logger.getLogger(MontarPrato02View.class.getName()).log(Level.SEVERE, null, ex);
        }

        dados = md.retornaDados(" pedido", " id_pedido", "1", 4);
        quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "1", 5);
        valorPedido = md.retornaDados(" pedido", " id_pedido", "1", 6);
        estadoPedido = md.retornaDados(" pedido", " id_pedido", "1", 7);
        idMesa = md.retornaDados(" pedido", " id_pedido", "1", 8);
        categoriaProduto = md.retornaDados(" pedido", " id_pedido", "1", 9);
        idPrato = md.retornaDados(" pedido", " id_pedido", "1", 10);

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) != null) {
                if (estadoPedido.get(i).equals("1") && idMesa.get(i).equals(ItensMesasView.idMesaAtual)
                        && categoriaProduto.get(i).equals("g") && idPrato.get(i).equals("a")) {
                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
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
                        nodes[i] = FXMLLoader.load(getClass().getResource("Produto.fxml"));
                        pnlProdutos.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Montar prato");
                    }
                }
            }
        }

        categoria = "Carnes";

        try {
            nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
            pnlProdutos.getChildren().add(nodes02[0]);
        } catch (IOException ex) {
            Logger.getLogger(MontarPrato02View.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) != null) {
                if (estadoPedido.get(i).equals("1") && idMesa.get(i).equals(ItensMesasView.idMesaAtual)
                        && categoriaProduto.get(i).equals("c") && idPrato.get(i).equals("a")) {
                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
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
                        nodes[i] = FXMLLoader.load(getClass().getResource("Produto.fxml"));
                        pnlProdutos.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Montar prato");
                    }
                }
            }
        }

        categoria = "Massas";

        try {
            nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
            pnlProdutos.getChildren().add(nodes02[0]);
        } catch (IOException ex) {
            Logger.getLogger(MontarPrato02View.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) != null) {
                if (estadoPedido.get(i).equals("1") && idMesa.get(i).equals(ItensMesasView.idMesaAtual)
                        && categoriaProduto.get(i).equals("m") && idPrato.get(i).equals("a")) {
                    nomeProduto = dados.get(i);
                    quantidadePr = quantidadeProduto.get(i);
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
                        nodes[i] = FXMLLoader.load(getClass().getResource("Produto.fxml"));
                        pnlProdutos.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor:\n" + e, "Montar prato");
                    }
                }
            }
        }

        precoTotal.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
        aux01 = precoTotal.getText();
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
            root = FXMLLoader.load(getClass().getResource("MontarPrato02.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao iniciar tela de montagem de prato:\n" + ex, "Montar prato");
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
    public void adicionarProduto(ActionEvent actionEvent) {
        numPrato = md.quantidadeDados("Monte seu prato", 5) + 1;
        cm.montarPrato(0, Integer.parseInt(ItensMesasView.idMesaAtual),
                nomeProduto01.getText(), quantProduto.getText(), aux01, "1");
        System.out.println("");

        conex.executaSql("select *from pedido where num_prato = '" + numPrato + "'");
        try {
            conex.rs.next();
            pesquisaPrato = conex.rs.getString("id_pedido");
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao pesquisar prato", "Montar prato");
        }

        try {

            PreparedStatement pst = conex.con.prepareStatement("update pedido set id_prato = ? where (categoria_pedido = 'g') and  (id_prato is null)");
            pst.setInt(1, Integer.parseInt(pesquisaPrato));
            pst.execute();

            pst = conex.con.prepareStatement("update pedido set id_prato = ? where (categoria_pedido ='c') and  (id_prato is null)");
            pst.setInt(1, Integer.parseInt(pesquisaPrato));
            pst.execute();

            pst = conex.con.prepareStatement("update pedido set id_prato = ? where (categoria_pedido ='m') and  (id_prato is null)");
            pst.setInt(1, Integer.parseInt(pesquisaPrato));
            pst.execute();

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao tentar editar dados banco", "Erro banco de dados");
        }
        MontarPrato01View.avancar = 0;
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

                    String preco = aux01;
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

                    String preco = aux01;
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
                TelasMensagem.setErroMensagem("Erro de formatação numérica:\n" + e, "Montar prato");
            }
            qm = Integer.toString(qmInt);
            quantProduto.setText(qm);
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
        ci.iniciarMesas01(event);
    }
}
