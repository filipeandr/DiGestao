package view.Financeiro;

import controlador.Conexao;
import controlador.ControladorMesas;
import controlador.MudarTela;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Dao.MesaDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;


public class PratoView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    public static String categoria, pesquisaPrato;
    public static int quantidadeDados, numPrato;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, idMesa01, 
            idPrato, idPedido;
    public static String mesaAtual, quantidadePr, valorPr, nomePr, 
            categoriaPr, aux01;
    public static double aux, subTotal;
    private int count = 0;

    @FXML
    private Label id;

    @FXML
    private Label nomeProduto;

    @FXML
    private Label destinoProduto;

    @FXML
    private VBox pnlPratos;

    @FXML
    private Label precoProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(FinanceiroView.quantidadePr + " x Monte seu Prato");
        destinoProduto.setText(FinanceiroView.pesquisaMesa + " vendido em " + FinanceiroView.dataPedido + " às " + FinanceiroView.horaPedido);
        precoProduto.setText(FinanceiroView.valorPr);

        quantidadeDados = md.quantidadeDados("7", 3);
        Node[] nodes = new Node[quantidadeDados];
        Node[] nodes02 = new Node[1];

        dados = md.retornaDados(" pedido", " id_pedido", "7", 4);
        quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "7", 5);
        valorPedido = md.retornaDados(" pedido", " id_pedido", "7", 6);
        estadoPedido = md.retornaDados(" pedido", " id_pedido", "7", 7);
        categoriaProduto = md.retornaDados(" pedido", " id_pedido", "7", 9);
        idPrato = md.retornaDados(" pedido", " id_pedido", "7", 10);
        

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("7") && categoriaProduto.get(i).equals("g")
                    && idPrato.get(i).equals(FinanceiroView.idPedido01)) {
                id.setText(FinanceiroView.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
                valorPr = valorPedido.get(i);
                try {
                    if (count == 0) {
                        categoria = "Guarnições";
                        nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
                        pnlPratos.getChildren().add(nodes02[0]);
                    }
                    nodes[i] = FXMLLoader.load(getClass().getResource("ItemPrato.fxml"));
                    pnlPratos.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar item prato", "Prato");
                }
                count++;
            }
        }
        count = 0;

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("7") && categoriaProduto.get(i).equals("c")
                    && idPrato.get(i).equals(FinanceiroView.idPedido01)) {
                id.setText(FinanceiroView.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
                valorPr = valorPedido.get(i);
                try {
                    if (count == 0) {
                        categoria = "Carnes";
                        nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
                        pnlPratos.getChildren().add(nodes02[0]);
                    }
                    nodes[i] = FXMLLoader.load(getClass().getResource("ItemPrato.fxml"));
                    pnlPratos.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar item prato", "Prato");
                }

                count++;
            }
        }
        count = 0;

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("7") && categoriaProduto.get(i).equals("m")
                    && idPrato.get(i).equals(FinanceiroView.idPedido01)) {
                id.setText(FinanceiroView.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
                valorPr = valorPedido.get(i);
                try {
                    if (count == 0) {
                        categoria = "Massas";
                        nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
                        pnlPratos.getChildren().add(nodes02[0]);
                    }
                    nodes[i] = FXMLLoader.load(getClass().getResource("ItemPrato.fxml"));
                    pnlPratos.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar item prato", "Prato");
                }
                count++;
            }
        }
    }
}
