package view.Pagar;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;

public class PagarView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    private boolean estadoPr = true;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto;
    public static ArrayList<Integer> id;
    public static int idAtual, estadoAtual;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto, categoriaPr, aux01, guardarId;
    public static int quantidadeDados;
    public static double aux, subTotal, calculo, aux02;

    @FXML
    private Label idMesa02;

    @FXML
    private Label precoTotal01;

    @FXML
    private VBox pnItems;

    @FXML
    private TextField porcentagem;

    @FXML
    private TextField desconto;

    @FXML
    private Label total;

    @FXML
    private Label totalPagar;

    @FXML
    private TextField trocoPara;

    @FXML
    private Label troco;

    @FXML
    private Button pagar;

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
    private Button mesas;

    @FXML
    private Button balcao;

    @FXML
    private Button preparo;

    @FXML
    private Button editarPorcentagem;

    @FXML
    private Button editarPreco;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subTotal = 0;
        idMesa02.setText(ItensMesasView.nomeMesaAtual);

        for (int j = 2; j < 6; j++) {
            quantidadeDados = md.quantidadeDados(Integer.toString(j), 3);
            Node[] nodes = new Node[quantidadeDados];

            dados = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 4);
            quantidadeProduto = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 5);
            valorPedido = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 6);
            estadoPedido = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 7);
            idMesa = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 8);
            categoriaProduto = md.retornaDados(" pedido", " id_pedido", Integer.toString(j), 9);

            for (int i = 0; i < nodes.length; i++) {
                if (categoriaProduto.get(i) == null) {
                    categoriaProduto.set(i, "a");
                }
                if (estadoPedido.get(i).equals(Integer.toString(j)) && idMesa.get(i).equals(ItensMesasView.idMesaAtual)
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
                    aux *= Integer.parseInt(quantidadePr);
                    subTotal += aux;
                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos02.fxml"));
                        pnItems.getChildren().add(nodes[i]);
                    } catch (IOException e) {
                        System.out.println("Erro ao tentar adicionar setor");
                    }
                }
            }
            precoTotal01.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
            total.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
            totalPagar.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
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
        if (actionEvent.getSource() == financeiro) {
            ci.iniciarFinanceiro(actionEvent);
        }
        if (actionEvent.getSource() == mesas) {
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == preparo) {
            ci.iniciarPreparo01View(actionEvent);
        }
        if (actionEvent.getSource() == editarPorcentagem) {
            porcentagem.setEditable(true);
            porcentagem.requestFocus();
            porcentagem.selectAll();
        }
        if (actionEvent.getSource() == editarPreco) {
            desconto.setEditable(true);
            desconto.requestFocus();
            desconto.setText("");
        }
    }

    public void iniciar(Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Pagar.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao iniciar tela Pagar:\n" + ex, "Montar prato");
        }
    }

    @FXML
    void sair(ActionEvent event) {
        if (porcentagem.getText().equals("") || trocoPara.getText().equals("")) {
            if (porcentagem.getText().equals("")) {
                porcentagem.setText("0");
                total.requestFocus();
            }
            if (trocoPara.getText().equals("")) {
                total.requestFocus();
            }
        } else {
            String preco = totalPagar.getText();
            String prec = trocoPara.getText();
            if (preco.contains(".") || preco.contains(",")) {
                preco = preco.replaceAll("[.]", "");
                preco = preco.replaceAll("[,]", ".");
            }
            if (prec.contains(".") || prec.contains(",")) {
                prec = prec.replaceAll("[.]", "");
                prec = prec.replaceAll("[,]", ".");
            }
            preco = preco.replaceAll("[R$ ]", "");
            aux = Double.parseDouble(preco);
            aux02 = Double.parseDouble(prec);
            prec = FormataMoeda.mascaraDinheiro(aux02, FormataMoeda.DINHEIRO_REAL);
            prec = prec.replaceAll("[R$ ]", "");
            if (aux02 < aux) {
                TelasMensagem.setErroMensagem("Troco menor que o valor total...", "Erro troco");
                trocoPara.setText("");
            } else {
                calculo = aux02 - aux;
                trocoPara.setText(prec);
                troco.setText(FormataMoeda.mascaraDinheiro(calculo, FormataMoeda.DINHEIRO_REAL));
            }
            porcentagem.setEditable(false);
            total.requestFocus();
        }
    }

    @FXML
    void porcentagem(KeyEvent event) throws InterruptedException {
        if (!porcentagem.getText().equals("")) {
            if (Integer.parseInt(porcentagem.getText()) < 1) {
                porcentagem.setText("1");
                porcentagem.positionCaret(Integer.MAX_VALUE);
            }
            if (Integer.parseInt(porcentagem.getText()) > 100) {
                porcentagem.setText("100");
                porcentagem.positionCaret(Integer.MAX_VALUE);
            }
            String preco = total.getText();
            if (preco.contains(".") || preco.contains(",")) {
                preco = preco.replaceAll("[.]", "");
                preco = preco.replaceAll("[,]", ".");
            }
            preco = preco.replaceAll("[R$ ]", "");
            aux = Double.parseDouble(preco);
            calculo = (Integer.parseInt(porcentagem.getText()) / 100.0) * aux;
            desconto.setText(FormataMoeda.mascaraDinheiro(calculo, FormataMoeda.DINHEIRO_REAL));
            calculo = aux - calculo;
            totalPagar.setText(FormataMoeda.mascaraDinheiro(calculo, FormataMoeda.DINHEIRO_REAL));
        }
    }

    @FXML
    void pagar(ActionEvent event) {
        String pesquisa = new String();

        if (idMesa02.getText().equals("Balcão")) {
            guardarId = "0";
        } else {
            conex.executaSql("select *from mesa where nome_mesa = '" + idMesa02.getText() + "'");

            try {
                conex.rs.next();

                guardarId = conex.rs.getString("id_mesa");
            } catch (SQLException ex) {
                TelasMensagem.setErroMensagem("Erro ao pesquisar id da mesa:\n" + ex, "Erro no banco de dados");
            }
        }
        conex.executaSql("select *from pedido where not estado_pedido = '5' and id_mesa = " + guardarId);;

        try {
            while (conex.rs.next()) {
                pesquisa = conex.rs.getString("estado_pedido");
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao pesquisar estado do produto:\n" + ex, "Erro no banco de dados");
        }

        if (pesquisa.equals("") || pesquisa.equals("6") || pesquisa.equals("7")) {
            pesquisa = "5";
        }
        if (Integer.parseInt(pesquisa) != 5) {
            if (idMesa02.getText().equals("Balcão")) {
                TelasMensagem.setErroMensagem("Ainda há pedidos que não foram entregues...", "Pedido");
            } else {
                TelasMensagem.setErroMensagem("A " + idMesa02.getText() + " ainda possui pedidos que não foram entregues", "Pedido");
            }
        } else {
            Date dataHoraAtual = new Date();
            String data = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);
            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            String fuso = hora.substring(0, 2);
            String horarioFinal = new String();
            int horario = Integer.parseInt(fuso) - 1;
            if (horario < 10) {
                horarioFinal = "0" + horario + hora.substring(2, 8);
                System.out.println(horarioFinal);
            } else {
                horarioFinal = horario + hora.substring(2, 8);
            }

            try {
                PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = '6' where (id_mesa = ?)");
                pst.setInt(1, Integer.parseInt(guardarId));
                pst.execute();
                pst = conex.con.prepareStatement("update pedido set data = ? where (id_mesa = ?)");
                pst.setString(1, data);
                pst.setInt(2, Integer.parseInt(guardarId));
                pst.execute();
                pst = conex.con.prepareStatement("update pedido set hora = ? where (id_mesa = ?)");
                pst.setString(1, horarioFinal);
                pst.setInt(2, Integer.parseInt(guardarId));
                pst.execute();
            } catch (SQLException ex) {
                TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
            }

            ArrayList<String> pesquisaProduto = new ArrayList<String>();
            String pesquisaEstoque = new String();
            int auxEstoque;

            conex.executaSql("select *from pedido where estado_pedido = '6'");
            try {
                while (conex.rs.next()) {
                    pesquisaProduto.add(conex.rs.getString("id_produto"));
                }
            } catch (SQLException ex) {
                TelasMensagem.setErroMensagem("Erro ao editar dados", "Edidtar dado");
            }

            for (int i = 0; i < pesquisaProduto.size(); i++) {
                conex.executaSql("select *from produto where id_produto = " + pesquisaProduto.get(i));
                try {
                    while (conex.rs.next()) {
                        pesquisaEstoque = conex.rs.getString("estoque");

                        PreparedStatement pst = conex.con.prepareStatement("update produto set estoque = ? where id_produto = ?");
                        auxEstoque = Integer.parseInt(pesquisaEstoque) - 1;
                        System.out.println(auxEstoque);
                        pst.setInt(1, auxEstoque);
                        pst.setInt(2, Integer.parseInt(pesquisaProduto.get(i)));
                        pst.execute();
                    }
                } catch (SQLException ex) {
                    TelasMensagem.setErroMensagem("Erro ao editar dados", "Edidtar dado");
                }
            }

            PreparedStatement pst;
            try {
                pst = conex.con.prepareStatement("update pedido set estado_pedido = '7' where (id_mesa = ?)");
                pst.setInt(1, Integer.parseInt(guardarId));
                pst.execute();
                pst = conex.con.prepareStatement("update mesa set estado_mesa = '1' where (id_mesa = ?)");
                pst.setInt(1, Integer.parseInt(guardarId));
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(PagarView.class.getName()).log(Level.SEVERE, null, ex);
            }

            ci.iniciarMesas01(event);
        }
    }
}
