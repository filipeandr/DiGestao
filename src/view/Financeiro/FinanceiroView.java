package view.Financeiro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Conexao;
import controlador.ControladorMesas;
import controlador.MudarTela;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.MesaDao;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class FinanceiroView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ControladorMesas cm = new ControladorMesas();
    private final Conexao conex = InicioView.conex;
    private final MesaDao md = new MesaDao();
    private boolean estadoPr = true;
    public static ArrayList<String> dados, quantidadeProduto, valorPedido,
            estadoMesa, estadoPedido, idMesa, categoriaProduto,
            idPedido, idMesa01, data, hora;
    public static ArrayList<Integer> id;
    public static int idAtual, estadoAtual, botao;
    public static String mesaAtual, quantidadePr, valorPr, nomeProduto,
            categoriaPr, aux01, pesquisaMesa, idPedido01, dataPedido, horaPedido;
    public static int quantidadeDados;
    public static double aux, subTotal;

    @FXML
    private VBox pnIEspera;

    @FXML
    private Label total;

    @FXML
    private Button gerarLista;

    @FXML
    private Label extratoCaixa;

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
    private DatePicker dataFinanceiro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subTotal = 0;
        quantidadeDados = md.quantidadeDados("7", 3);
        Node[] nodes = new Node[quantidadeDados];

        dados = md.retornaDados(" pedido", " id_pedido", "7", 4);
        quantidadeProduto = md.retornaDados(" pedido", " id_pedido", "7", 5);
        valorPedido = md.retornaDados(" pedido", " id_pedido", "7", 6);
        estadoPedido = md.retornaDados(" pedido", " id_pedido", "7", 7);
        idMesa01 = md.retornaDados(" pedido", " id_pedido", "7", 8);
        categoriaProduto = md.retornaDados(" pedido", " id_pedido", "7", 9);
        idPedido = md.retornaDados(" pedido", " id_pedido", "7", 11);
        data = md.retornaDados(" pedido", " id_pedido", "7", 12);
        hora = md.retornaDados(" pedido", " id_pedido", "7", 13);

        for (int i = 0; i < nodes.length; i++) {

            if (categoriaProduto.get(i) == null) {
                categoriaProduto.set(i, "a");
            }
            if (estadoPedido.get(i).equals("7") && categoriaProduto.get(i).equals("a")
                    && !dados.get(i).equals("Monte seu prato")) {

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
                valorPr = valorPedido.get(i);
                dataPedido = data.get(i);
                horaPedido = hora.get(i);
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
                    nodes[i] = FXMLLoader.load(getClass().getResource("Produto.fxml"));

                    pnIEspera.getChildren().add(nodes[i]);

                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar produto", "Preparo");
                }
            }

            if (estadoPedido.get(i).equals("7") && categoriaProduto.get(i).equals("a") && dados.get(i).equals("Monte seu prato")) {

                if (idMesa01.get(i).equals("0")) {
                    pesquisaMesa = "Balcão";
                } else {
                    conex.executaSql("select *from mesa where id_mesa = '" + idMesa01.get(i) + "'");

                    try {
                        conex.rs.next();
                        pesquisaMesa = conex.rs.getString("nome_mesa");
                    } catch (SQLException ex) {
                        Logger.getLogger(FinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                quantidadePr = quantidadeProduto.get(i);
                idPedido01 = idPedido.get(i);
                valorPr = valorPedido.get(i);
                dataPedido = data.get(i);
                horaPedido = hora.get(i);
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
                    nodes[i] = FXMLLoader.load(getClass().getResource("Prato.fxml"));

                    pnIEspera.getChildren().add(nodes[i]);

                } catch (IOException e) {
                    TelasMensagem.setErroMensagem("Erro ao tentar adicionar prato", "Preparo");
                }
            }

            total.setText(FormataMoeda.mascaraDinheiro(subTotal, FormataMoeda.DINHEIRO_REAL));
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
        if (actionEvent.getSource() == mesas) {
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == preparo) {
            ci.iniciarPreparo01View(actionEvent);
        }
    }

    public void iniciar(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Financeiro.fxml"));
            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            stage.setScene(scene);
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro iniciar tela Financeiro", "Erro iniciar Tela");
        }
    }

    @FXML
    void gerarPDF(ActionEvent event) {

        String dataFormatada = "";
        double valorTotal = 0;

        if (dataFinanceiro.getValue() != null) {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate lddf = dataFinanceiro.getValue();
            Date ddf = Date.from(lddf.atStartOfDay(defaultZoneId).toInstant());
            SimpleDateFormat formata = new SimpleDateFormat("dd-MM-yyyy");
            dataFormatada = formata.format(ddf);
        }

        String nomeDocumento = "Relatorio_" + dataFormatada;
        String nomePasta = "relatorio\\";
        Document document = new Document(PageSize.A4);

        try {
            File pasta = new File(nomePasta);
            pasta.mkdir();

            PdfWriter.getInstance(document, new FileOutputStream(nomePasta + nomeDocumento + ".pdf"));
            document.open();

            Paragraph titulo = new Paragraph(new Phrase(20F, "Relatório " + dataFormatada + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 18F)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Adicionar cabeçalho
            PdfPTable tabela = new PdfPTable(new float[]{11f, 3f, 4f});

            PdfPCell celulaProduto = new PdfPCell(new Phrase("Produto"));
            celulaProduto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celulaEstoque = new PdfPCell(new Phrase("Pedido"));
            celulaEstoque.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaQuantMin = new PdfPCell(new Phrase("Valor"));
            celulaQuantMin.setHorizontalAlignment(Element.ALIGN_CENTER);

            tabela.addCell(celulaProduto);
            tabela.addCell(celulaEstoque);
            tabela.addCell(celulaQuantMin);

            for (int i = 0; i < dados.size(); i++) {
                String cell1 = dados.get(i);
                String cell2 = idPedido.get(i);
                String cell3 = valorPedido.get(i);
                String dataBanco = data.get(i);

                if (dataBanco.equals(dataFormatada)) {
                    PdfPCell celula1 = new PdfPCell(new Phrase(cell1));
                    celula1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell celula2 = new PdfPCell(new Phrase(cell2));
                    celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell celula3 = new PdfPCell(new Phrase(cell3));
                    celula3.setHorizontalAlignment(Element.ALIGN_CENTER);

                    tabela.addCell(celula1);
                    tabela.addCell(celula2);
                    tabela.addCell(celula3);

                    if (cell3.contains(".") || cell3.contains(",")) {
                        cell3 = cell3.replaceAll("[.]", "");
                        cell3 = cell3.replaceAll("[,]", ".");
                    }
                    cell3 = cell3.replaceAll("[R$ ]", "");
                    valorTotal += Double.parseDouble(cell3);
                }
            }
            
            String cell1 = "###";
            String cell2 = "Total: ";

            PdfPCell celula1 = new PdfPCell(new Phrase(cell1));
            celula1.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celula2 = new PdfPCell(new Phrase(cell2));
            celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celula3 = new PdfPCell(new Phrase(FormataMoeda.mascaraDinheiro(valorTotal, FormataMoeda.DINHEIRO_REAL)));
            celula3.setHorizontalAlignment(Element.ALIGN_CENTER);

            tabela.addCell(celula1);
            tabela.addCell(celula2);
            tabela.addCell(celula3);

            document.add(tabela);
            TelasMensagem.setInfoMensagem("PDF gerado com sucesso!", "Gerar PDF");

        } catch (DocumentException | FileNotFoundException ex) {
            TelasMensagem.setErroMensagem("Erro ao gerar PDF:\n" + ex, "Erro PDF");
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File(nomePasta + nomeDocumento + ".pdf"));
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao abrir PDF:\n" + ex, "Erro PDF");
        }
    }
}
