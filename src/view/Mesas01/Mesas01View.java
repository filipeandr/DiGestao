package view.Mesas01;

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
import modelo.Dao.MesaDao;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class Mesas01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    private boolean estadoPr = true;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto, caminhoImagem, idProduto, idPedido;
    public static ArrayList<Integer> id;
    public static int idAtual, estadoAtual;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto, categoriaPr, 
            aux01, caminhoImagemAtual, idProdutoAtual, idPedidoA;
    public static int quantidadeDados;
    public static double aux, subTotal;

    @FXML
    private Label nomeMesa;

    @FXML
    private Button pagar;

    @FXML
    private VBox pnItems;

    @FXML
    private VBox pnItems1;

    @FXML
    private Button adicionarProduto;

    @FXML
    private Button enviarPedido;

    @FXML
    private Button mais;

    @FXML
    private VBox pnlMesas01;

    @FXML
    private VBox pnlMesas02;

    @FXML
    private VBox pnlMesas03;

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
    private Button mesasDisponiveis;

    @FXML
    private Button mesasOcupadas;

    @FXML
    private Button mesasFechamento;

    @FXML
    private Label labelEstado;

    @FXML
    private Label precoTotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int count = 1, count01 = 1;
        
        if(estadoAtual == 0 || estadoAtual == 1){
            labelEstado.setText("Disponíveis");
        } else {
            labelEstado.setText("Ocupadas");
        }

        Node[] nodes = new Node[1];

        if (estadoAtual == 0 || estadoAtual == 1) {
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("Balcao.fxml"));
                pnlMesas01.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                TelasMensagem.setErroMensagem("Erro ao adicionar balcao", "Balcao");
            }
        }
        quantidadeDados = md.quantidadeDados("mesa", 1);
        nodes = new Node[quantidadeDados];
        
        dados = conex.retornaDados(" mesa", " id_mesa", 7);
        id = conex.retornaId(" mesa", " id_mesa", 4);
        estadoMesa = conex.retornaDados(" mesa", " id_mesa", 8);
        
        for (int i = 0; i < nodes.length; i++) {
            if (estadoMesa.get(i).equals("1") && estadoAtual == 0 || estadoMesa.get(i).equals("1") && estadoAtual == 1) {
                mesaAtual = dados.get(i);
                idAtual = id.get(i);
                try {
                    if (count01 == 1 || count01 == 2) {
                        if (count01 == 1) {
                            nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                            pnlMesas02.getChildren().add(nodes[i]);
                        }
                        if (count01 == 2) {
                            nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                            pnlMesas03.getChildren().add(nodes[i]);
                        }
                    } else {
                        switch (count) {
                            case 1:
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                                pnlMesas01.getChildren().add(nodes[i]);
                                break;
                            case 2:
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                                pnlMesas02.getChildren().add(nodes[i]);
                                break;
                            case 3:
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                                count = 0;
                                pnlMesas03.getChildren().add(nodes[i]);
                                break;
                        }
                        count++;
                    }
                    count01++;
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar Itens mesas:\n" + e, "Mesas");
                }
            }
            if (estadoMesa.get(i).equals("2") && estadoAtual == 2) {
                mesaAtual = dados.get(i);
                idAtual = id.get(i);
                try {
                    switch (count) {
                        case 1:
                            nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                            pnlMesas01.getChildren().add(nodes[i]);
                            break;
                        case 2:
                            nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                            pnlMesas02.getChildren().add(nodes[i]);
                            break;
                        case 3:
                            nodes[i] = FXMLLoader.load(getClass().getResource("ItensMesas.fxml"));
                            count = 0;
                            pnlMesas03.getChildren().add(nodes[i]);
                            break;
                    }
                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar mesa:\n" + e, "Mesas");
                }

                count++;
            }
        }

        if (ItensMesasView.pedidoMesa) {
            nomeMesa.setText(ItensMesasView.nomeMesaAtual);
        }

        if (ItensMesasView.pedidoMesa) {
            subTotal = 0;
            if (md.pesquisarEstadoProduto("pedido", "id_mesa", Integer.parseInt(ItensMesasView.idMesaAtual)).equals("1")) {
                estadoPr = false;
                quantidadeDados = md.quantidadeDados("1", 3);
                nodes = new Node[quantidadeDados];

                dados = md.retornaDados(" pedido", " id_pedido", "1", 4);
                quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "1", 5);
                valorPedido = md.retornaDados(" pedido", " id_pedido", "1", 6);
                estadoPedido = md.retornaDados(" pedido", " id_pedido", "1", 7);
                idMesa = md.retornaDados(" pedido", " id_pedido", "1", 8);
                categoriaProduto = md.retornaDados(" pedido", " id_pedido", "1", 9);
                idProduto = md.retornaDados(" pedido", " id_pedido", "1", 14);
                idPedido = md.retornaDados(" pedido", " id_pedido", "1", 11);

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
                        idPedidoA = idPedido.get(i);

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
            }

            if (estadoAtual == 1 || estadoAtual == 0 && estadoPr) {
                if (ItensMesasView.nomeMesaAtual.equals("Balcão")) {
                    for (int j = 2; j < 6; j++) {
                        enviarPedido.setVisible(false);
                        quantidadeDados = md.quantidadeDados(Integer.toString(j), 3);
                        nodes = new Node[quantidadeDados];

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
                                    nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                                    pnItems.getChildren().add(nodes[i]);
                                } catch (IOException e) {
                                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor", "Setor");
                                }
                            }
                        }
                    }
                }
            }

            if (estadoAtual == 2 && estadoPr) {
                for (int j = 2; j < 6; j++) {
                    enviarPedido.setVisible(false);
                    quantidadeDados = md.quantidadeDados(Integer.toString(j), 3);
                    nodes = new Node[quantidadeDados];

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
                                nodes[i] = FXMLLoader.load(getClass().getResource("ItensProdutos.fxml"));
                                pnItems.getChildren().add(nodes[i]);
                            } catch (IOException e) {
                                TelasMensagem.setErroMensagem("Erro ao tentar adicionar setor", "Setor");
                            }
                        }
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
        Parent root;
        
        try {
            root = FXMLLoader.load(getClass().getResource("Mesas01.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Mesas01View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void adicionarMesa(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mais) {
            quantidadeDados = md.quantidadeDados("mesa", 1);
            cm.salvarMesa("Mesa " + (quantidadeDados + 1));

            ci.iniciarMesas01(actionEvent);
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

    @FXML
    void acaoProdutos(ActionEvent actionEvent) {
        idAtual = Integer.parseInt(ItensMesasView.idMesaAtual);
        mesaAtual = ItensMesasView.nomeMesaAtual;
        ci.iniciarMesas02(actionEvent);
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

        ci.iniciarMesas01(event);
    }

    @FXML
    void estadoMesa(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mesasDisponiveis) {
            ItensMesasView.pedidoMesa = false;
            estadoAtual = 1;
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == mesasOcupadas) {
            estadoAtual = 2;
            ItensMesasView.pedidoMesa = false;
            ci.iniciarMesas01(actionEvent);
        }
    }

    @FXML
    void pagar(ActionEvent event) {
        idAtual = Integer.parseInt(ItensMesasView.idMesaAtual);
        mesaAtual = ItensMesasView.nomeMesaAtual;
        ci.iniciaPagar(event);
    }
}
