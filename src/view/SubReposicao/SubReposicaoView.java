package view.SubReposicao;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Dao.ProdutoDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class SubReposicaoView implements Initializable {

    private final MudarTela ci;
    private final Conexao conex = InicioView.conex;
    private final ProdutoDao pd = new ProdutoDao();
    public static int quantidadeDados, idAtual, auxReposicao;
    public static String dadosAtual, dadosReposicao, quantMinimaAtual, estoqueAtual;
    public static ArrayList<String> dados, quantMinima, dadosEstoque;
    protected static ArrayList<Integer> id;

    public SubReposicaoView() {

        ci = new MudarTela();
    }

    @FXML
    private Button usuario;

    @FXML
    private Button notificacao;

    @FXML
    private Button caixa;

    @FXML
    private Button subProdutos;

    @FXML
    private Button categorias;

    @FXML
    private Button estoque;

    @FXML
    private Button reposicao;

    @FXML
    private Button setores;

    @FXML
    private TextField pesquisarItem;

    @FXML
    private Button gerarLista;

    @FXML
    private Button mesas;

    @FXML
    private Button balcao;

    @FXML
    private Button preparo;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dados = conex.retornaDados(" produto", " descricao", 3);
        dadosEstoque = conex.retornaDados(" produto", " descricao", 6);
        quantMinima = conex.retornaDados(" produto", " descricao", 9);
        id = conex.retornaId(" produto", " descricao", 3);
    }

    @FXML
    public void clicar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == inicio) {
            
        }
        if (actionEvent.getSource() == operacoesCaixa) {
            
        }
        if (actionEvent.getSource() == vendas) {
            
        }
        if (actionEvent.getSource() == financeiro) {
            ci.iniciarFinanceiro(actionEvent);
        }
        if (actionEvent.getSource() == subProdutos) {
            ci.iniciarSubProdutos(actionEvent);
        }
        if (actionEvent.getSource() == categorias) {
            ci.iniciarCategorias(actionEvent);
        }
        if (actionEvent.getSource() == estoque) {
            
        }
        if (actionEvent.getSource() == setores) {
            ci.iniciarSetores(actionEvent);
        }
        if (actionEvent.getSource() == mesas) {
            ci.iniciarMesas01(actionEvent);
        }
        if (actionEvent.getSource() == preparo) {
            ci.iniciarPreparo01View(actionEvent);
        }
    }

    public void iniciar(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SubReposicao.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void pesquisaItem(ActionEvent actionEvent) {
        String texto = pesquisarItem.getText();
        conex.executaSql("select * from produto where descricao like '%" + texto + "%'");
        try {
            while (conex.rs.next()) {
                texto = conex.rs.getString("descricao");
                System.out.println(texto);
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao tentar pesquisar produto:\n" + ex, "Reposição");
        }
    }

    @FXML
    public void gerarPDF(ActionEvent actionEvent) throws IOException {
        // Pegar data
        Date dt = new Date();
        SimpleDateFormat formata = new SimpleDateFormat("dd-MM-yyyy");
        String data = formata.format(dt);

        // Documento e diretório
        String nomeDocumento = "Reposicao_" + data;
        String nomePasta = "reposicao\\";
        Document document = new Document(PageSize.A4);

        try {
            // Se a pasta não estiver criada, ela é criada nessa linha
            File pasta = new File(nomePasta);
            pasta.mkdir();

            // MANIPULAÇÃO DOCUMENTO E TABELA
            PdfWriter.getInstance(document, new FileOutputStream(nomePasta + nomeDocumento + ".pdf"));
            document.open();

            // Adicionar título
            Paragraph titulo = new Paragraph(new Phrase(20F, "Reposição de Estoque\n\n", FontFactory.getFont(FontFactory.HELVETICA, 18F)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Adicionar cabeçalho
            PdfPTable tabela = new PdfPTable(new float[]{11f, 3f, 4f, 3.5f});

            PdfPCell celulaProduto = new PdfPCell(new Phrase("Produto"));
            celulaProduto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celulaEstoque = new PdfPCell(new Phrase("Estoque"));
            celulaEstoque.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaQuantMin = new PdfPCell(new Phrase("Quantidade Mínima"));
            celulaQuantMin.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaReposicao = new PdfPCell(new Phrase("Reposição"));
            celulaReposicao.setHorizontalAlignment(Element.ALIGN_CENTER);

            tabela.addCell(celulaProduto);
            tabela.addCell(celulaEstoque);
            tabela.addCell(celulaQuantMin);
            tabela.addCell(celulaReposicao);

            // Adicionar itens para reposição
            for (int i = 0; i < dados.size(); i++) {
                dadosAtual = dados.get(i);
                estoqueAtual = dadosEstoque.get(i);
                quantMinimaAtual = quantMinima.get(i);
                idAtual = id.get(i);

                auxReposicao = Integer.parseInt(quantMinimaAtual) - Integer.parseInt(estoqueAtual);
                dadosReposicao = Integer.toString(auxReposicao);

                if (auxReposicao >= 0) {
                    PdfPCell celula1 = new PdfPCell(new Phrase(dadosAtual));
                    celula1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell celula2 = new PdfPCell(new Phrase(estoqueAtual));
                    celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell celula3 = new PdfPCell(new Phrase(quantMinimaAtual));
                    celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell celula4 = new PdfPCell(new Phrase(dadosReposicao));
                    celula4.setHorizontalAlignment(Element.ALIGN_CENTER);

                    tabela.addCell(celula1);
                    tabela.addCell(celula2);
                    tabela.addCell(celula3);
                    tabela.addCell(celula4);
                }
            }

            // Inserir dados da tabela no documento
            document.add(tabela);
            TelasMensagem.setInfoMensagem("PDF gerado com sucesso!", "Gerar PDF");

            //Exceções
        } catch (DocumentException | FileNotFoundException ex) {
            TelasMensagem.setErroMensagem("Erro ao gerar PDF:\n" + ex, "Erro PDF");
        } finally {
            document.close();
        }

        // Abrir documento gerado
        try {
            Desktop.getDesktop().open(new File(nomePasta + nomeDocumento + ".pdf"));
        } catch (IOException ex) {
            TelasMensagem.setErroMensagem("Erro ao abrir PDF:\n" + ex, "Erro PDF");
        }
    }
}
