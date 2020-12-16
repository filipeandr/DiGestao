package view.Preparo02;

import controlador.Conexao;
import controlador.ControladorMesas;
import controlador.MudarTela;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Dao.MesaDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class Prato01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    public static String categoria, pesquisaPrato;
    public static int quantidadeDados, numPrato;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, idMesa01, idPrato, idPedido;
    public static String mesaAtual, quantidadePr, valorPr, nomePr, categoriaPr, aux01;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nomeProduto.setText(Preparo02View.quantidadePr + " x Monte seu Prato");
        destinoProduto.setText(Preparo02View.pesquisaMesa);

        quantidadeDados = md.quantidadeDados("4", 3);
        Node[] nodes = new Node[quantidadeDados];
        Node[] nodes02 = new Node[1];

        dados = md.retornaDados(" pedido", " id_pedido", "4", 4);
        quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "4", 5);
        estadoPedido = md.retornaDados(" pedido", " id_pedido", "4", 7);
        categoriaProduto = md.retornaDados(" pedido", " id_pedido", "4", 9);
        idPrato = md.retornaDados(" pedido", " id_pedido", "4", 10);

        for (int i = 0; i < nodes.length; i++) {
            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("4") && categoriaProduto.get(i).equals("g")
                    && idPrato.get(i).equals(Preparo02View.idPedido01)) {
                id.setText(Preparo02View.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
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
            if (estadoPedido.get(i).equals("4") && categoriaProduto.get(i).equals("c")
                    && idPrato.get(i).equals(Preparo02View.idPedido01)) {
                id.setText(Preparo02View.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
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
            if (estadoPedido.get(i).equals("4") && categoriaProduto.get(i).equals("m")
                    && idPrato.get(i).equals(Preparo02View.idPedido01)) {
                id.setText(Preparo02View.idPedido01);
                nomePr = dados.get(i);
                quantidadePr = quantidadeProduto.get(i);
                try {
                    if (count == 0) {
                        categoria = "Massas";
                        nodes02[0] = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
                        pnlPratos.getChildren().add(nodes02[0]);
                    }
                    nodes[i] = FXMLLoader.load(getClass().getResource("ItemPrato.fxml"));
                    pnlPratos.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar item prato", "Setor");
                }
                count++;
            }
        }
    }

    @FXML
    void enviar(ActionEvent event) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = '5' where id_pedido = ?");
            pst.setInt(1, Integer.parseInt(id.getText()));
            pst.execute();

            pst = conex.con.prepareStatement("update pedido set estado_pedido = ? where (categoria_pedido = 'g') "
                    + "or (categoria_pedido = 'c') or (categoria_pedido = 'm') and  (id_prato = ?)");
            pst.setString(1, "5");
            pst.setInt(2, Integer.parseInt(id.getText()));
            pst.execute();

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
        }

        ci.iniciarPreparo02View(event);
    }
}
