package view.SubCadastrar;
//
import controlador.Conexao;
import controlador.ControladorProdutos;
import controlador.MudarTela;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import modelo.Dao.ProdutoDao;
import validacao.AcoesValidacaoCampo;
import validacao.FormataMoeda;
import validacao.TelasMensagem;
import validacao.ValidacoesInternas;
import view.Inicial.InicioView;
import view.SubProdutos.ItensProdutosView;

public class SubCadastrarView implements Initializable {

    private final MudarTela ci = new MudarTela();
    private final ProdutoDao pd = new ProdutoDao();
    private final Conexao conex = InicioView.conex;
    private final FormataMoeda m = new FormataMoeda();
    private final ControladorProdutos cp = new ControladorProdutos();
    ObservableList list = FXCollections.observableArrayList();
    public static ArrayList<String> dados;
    public static int quantidadeDados;
    public static int idAtual;
    private String aux;
    JFileChooser chooser = new JFileChooser();
    File f, destino;
    public String caminhoImagem = "";

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
    private TextField preco;

    @FXML
    private Button menos;

    @FXML
    private Button mais;

    @FXML
    private TextField quantidadeMinima;

    @FXML
    private ComboBox<String> categoria;

    @FXML
    private ComboBox<String> unidade;

    @FXML
    private ComboBox<String> setorPreparo;

    @FXML
    private ComboBox<String> tipoProduto;

    @FXML
    private TextField codigo;

    @FXML
    private TextField detalhes;

    @FXML
    private TextField valorMesas;

    @FXML
    private CheckBox checkMesas;

    @FXML
    private TextField valorBalcao;

    @FXML
    private CheckBox checkBalcao;

    @FXML
    private Button salvar;

    @FXML
    private Button cancelar;

    @FXML
    private TextField descricao;

    @FXML
    private Label labelImagem;

    @FXML
    private Button btImagem;

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
        list.removeAll(list);
        String u = "Unidade-UN";
        String l = "Litro-L";
        String g = "Grama-g";
        list.addAll(u, l, g);
        unidade.getItems().addAll(list);

        if (ItensProdutosView.editando == false) {
            unidade.getSelectionModel().select(0);
        } else {
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 1);

            switch (aux) {
                case ("0"):
                    unidade.getSelectionModel().select(0);
                    break;
                case ("1"):
                    unidade.getSelectionModel().select(1);
                    break;
                case ("2"):
                    unidade.getSelectionModel().select(2);
                    break;
            }
        }

        list.removeAll(list);
        String p = "Produto";
        String c = "Composição";
        list.addAll(p, c);
        tipoProduto.getItems().addAll(list);
        tipoProduto.getSelectionModel().select(0);

        if (ItensProdutosView.editando == false) {
            tipoProduto.getSelectionModel().select(0);
        } else {
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 2);

            switch (aux) {
                case ("Produto"):
                    tipoProduto.getSelectionModel().select(0);
                    break;
                case ("Composição"):
                    tipoProduto.getSelectionModel().select(1);
                    break;
            }
        }

        quantidadeDados = pd.quantidadeDados("setor");
        dados = conex.retornaDados(" setor", " setor", 2);
        list.removeAll(list);

        for (int i = 0; i < quantidadeDados; i++) {
            list.add(dados.get(i));
        }

        setorPreparo.getItems().addAll(list);

        if (ItensProdutosView.editando == false) {
            for (int i = 0; i < quantidadeDados; i++) {
                if (dados.get(i).equals("Cozinha")) {
                    setorPreparo.getSelectionModel().select(i);
                    break;
                }
            }
        } else {
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 3);

            for (int i = 0; i < quantidadeDados; i++) {
                if (dados.get(i).equals(aux)) {
                    setorPreparo.getSelectionModel().select(i);
                    break;
                }
            }
        }

        quantidadeDados = pd.quantidadeDados("categoria");
        dados = conex.retornaDados(" categoria", " categoria", 1);
        list.removeAll(list);

        for (int i = 0; i < quantidadeDados; i++) {
            list.add(dados.get(i));
        }

        categoria.getItems().addAll(list);

        if (ItensProdutosView.editando == false) {
            quantidadeMinima.setText("0");
        } else {
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 5);

            quantidadeMinima.setText(aux);
        }

        if (ItensProdutosView.editando == true) {
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 4);

            for (int i = 0; i < quantidadeDados; i++) {
                if (dados.get(i).equals(aux)) {
                    categoria.getSelectionModel().select(i);
                    break;
                }
            }

            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 6);
            descricao.setText(aux);
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 7);
            aux = aux.replaceAll("[a-zA-Z\\s$]", "");
            preco.setText(aux);
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 8);
            detalhes.setText(aux);
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 9);
            codigo.setText(aux);
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 10);
            valorMesas.setText(aux);
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 11);
            valorBalcao.setText(aux);

            // Imagem
            aux = pd.pesquisar("produto", "id_produto", ItensProdutosView.idAtual, 12);
            if (!"".equals(aux) && aux != null) {
                try {
                    FileInputStream inputstream = new FileInputStream(aux);
                    Image image = new Image(inputstream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(70.0);
                    imageView.setFitWidth(70.0);
                    labelImagem.setGraphic(imageView);
                    btImagem.setText("");
                    caminhoImagem = aux;
                } catch (FileNotFoundException ex) {
                    TelasMensagem.setErroMensagem("Erro ao carregar imagem:\n" + ex, "Cadastro");
                }
            }
        }
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
        if (actionEvent.getSource() == reposicao) {
            ci.iniciarReposicao(actionEvent);
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
        Parent root = FXMLLoader.load(getClass().getResource("SubCadastrar.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setScene(scene);
    }

    @FXML
    public void opQuantidadeMinima(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == mais) {
            String qm = quantidadeMinima.getText();
            int qmInt = 1;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt >= 0) {
                    qmInt += 1;
                }
            } catch (NumberFormatException e) {
            }
            qm = Integer.toString(qmInt);
            quantidadeMinima.setText(qm);
        }
        if (actionEvent.getSource() == menos) {
            String qm = quantidadeMinima.getText();
            int qmInt = 0;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt >= 1) {
                    qmInt -= 1;
                }
            } catch (NumberFormatException e) {
            }
            qm = Integer.toString(qmInt);
            quantidadeMinima.setText(qm);
        }
    }

    @FXML
    public void salvarCancelar(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == salvar) {
            String desc, prec, quant, cod, vm, vb, det, unid = "1";
            boolean erro = false;

            desc = descricao.getText();
            prec = preco.getText();
            cod = codigo.getText();
            quant = quantidadeMinima.getText();
            vm = valorMesas.getText();
            vb = valorBalcao.getText();
            det = detalhes.getText();

            if (prec.contains(".") || prec.contains(",")) {
                prec = prec.replaceAll("[.]", "");
                prec = prec.replaceAll("[,]", ".");
            }
            if (desc.equals("")) {
                AcoesValidacaoCampo.setCampoMensagem(descricao, "Descrição não pode ser nula");
                erro = true;
            }
            if (prec.equals("") || !ValidacoesInternas.validarPreco(prec)) {
                AcoesValidacaoCampo.setCampoMensagem(preco, "Valor inválido");
                erro = true;
            } else {
                prec = FormataMoeda.mascaraDinheiro(Double.parseDouble(prec), FormataMoeda.DINHEIRO_REAL);
                prec = prec.replaceAll("[R$]", "");
            }
            if (cod.equals("")) {
                AcoesValidacaoCampo.setCampoMensagem(codigo, "Código não pode ser nulo");
                erro = true;
            }

            if (checkMesas.isSelected()) {
                if (vm.equals("")) {
                    AcoesValidacaoCampo.setCampoMensagem(valorMesas, "Valor inválido");
                    erro = true;
                } else if (!ValidacoesInternas.validarPreco(vm)) {
                    AcoesValidacaoCampo.setCampoMensagem(valorMesas, "Valor inválido");
                    erro = true;
                } else {
                    vm = ValidacoesInternas.formataPreco(vm);
                }
            } else {
                vm = "";
            }

            if (checkBalcao.isSelected()) {
                if (vb.equals("")) {
                    AcoesValidacaoCampo.setCampoMensagem(valorBalcao, "Valor inválido");
                    erro = true;
                } else if (!ValidacoesInternas.validarPreco(vb)) {
                    AcoesValidacaoCampo.setCampoMensagem(valorBalcao, "Valor inválido");
                    erro = true;
                } else {
                    vb = ValidacoesInternas.formataPreco(vb);
                }
            } else {
                vb = "";
            }

            if (unidade.getValue().equals("Unidade-UN")) {
                unid = "0";
            }
            if (unidade.getValue().equals("Litro-L")) {
                unid = "1";
            }
            if (unidade.getValue().equals("Grama-g")) {
                unid = "2";
            }

            if (!erro) {
                if (!ItensProdutosView.editando) {
                    cp.dadosProdutos(categoria.getValue(), prec, setorPreparo.getValue(),
                            quant, unid, det, cod, vm, vb, desc, tipoProduto.getValue(), caminhoImagem);
                    ci.iniciarSubProdutos(actionEvent);
                } else {
                    cp.setEditaProdutoCadastrado(categoria.getValue(), ItensProdutosView.idAtual, 1, "produto", "categoria", "id_produto");
                    cp.setEditaProdutoCadastrado(det, ItensProdutosView.idAtual, 2, "produto", "detalhes", "id_produto");
                    cp.setEditaProdutoCadastrado(cod, ItensProdutosView.idAtual, 3, "produto", "codigo", "id_produto");
                    cp.setEditaProdutoCadastrado(desc, ItensProdutosView.idAtual, 4, "produto", "descricao", "id_produto");
                    cp.setEditaProdutoCadastrado(prec, ItensProdutosView.idAtual, 5, "produto", "preco", "id_produto");
                    cp.setEditaProdutoCadastrado(setorPreparo.getValue(), ItensProdutosView.idAtual, 6, "produto", "setor_preparo", "id_produto");
                    cp.setEditaProdutoCadastrado(quant, ItensProdutosView.idAtual, 7, "produto", "quantidade_minima", "id_produto");
                    cp.setEditaProdutoCadastrado(unid, ItensProdutosView.idAtual, 8, "produto", "unidade", "id_produto");
                    cp.setEditaProdutoCadastrado(vm, ItensProdutosView.idAtual, 9, "produto", "preco_mesa", "id_produto");
                    cp.setEditaProdutoCadastrado(vb, ItensProdutosView.idAtual, 10, "produto", "preco_balcao", "id_produto");
                    cp.setEditaProdutoCadastrado(tipoProduto.getValue(), ItensProdutosView.idAtual, 11, "produto", "tipo", "id_produto");
                    cp.setEditaProdutoCadastrado(caminhoImagem, ItensProdutosView.idAtual, 14, "produto", "caminho_imagem", "id_produto");
                    ItensProdutosView.editando = false;
                    ci.iniciarSubProdutos(actionEvent);
                }
            } else {
                TelasMensagem.setErroMensagem("Campo(s) inválido(s)...", "Erro no cadastro de produtos");
            }
        }

        if (actionEvent.getSource() == cancelar) {
            ItensProdutosView.editando = false;
            ci.iniciarSubProdutos(actionEvent);
        }
    }

    @FXML
    void formatarCampo(MouseEvent event) {
        AcoesValidacaoCampo.limpaFormatacao(descricao);
        AcoesValidacaoCampo.limpaFormatacao(codigo);
        AcoesValidacaoCampo.limpaFormatacao(preco);
        AcoesValidacaoCampo.limpaFormatacao(valorMesas);
        AcoesValidacaoCampo.limpaFormatacao(valorBalcao);
    }

    @FXML
    public void adicionarImagem(ActionEvent actionEvent) throws IOException {
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        String img = f.getAbsolutePath();

        // Tamanho da label
        double width = labelImagem.getWidth();
        double heigth = labelImagem.getHeight();

        // Armazenar imagem
        FileInputStream inputstream = new FileInputStream(img);
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);

        // Redimensionar imagem
        imageView.setFitHeight(heigth);
        imageView.setFitWidth(width);

        // Set imagem
        labelImagem.setGraphic(imageView);

        // Copiar imagem para diretório do programa
        // Pegar nome da imagem
        String NomeDoArquivo = f.getName();

        // Pegar diretório completo com nome do arquivo
        File destino = new File("src\\imagens\\produtos\\" + NomeDoArquivo);

        // Pegar diretório sem o nome do arquivo
        File pasta = new File("src\\imagens\\produtos");

        destino = destino.getAbsoluteFile();
        pasta = pasta.getAbsoluteFile();

        try {
            // Se a pasta não estiver criada, ela é criada nessa linha
            pasta.mkdir();
            // Copia arquivo para pasta dentro do programa
            Files.copy(f.toPath(), destino.toPath());

            btImagem.setText("");
            caminhoImagem = destino.getPath();

        } catch (IOException ex) {
            if (destino.isFile()) {
                btImagem.setText("");
                caminhoImagem = destino.getPath();
            } else {
                TelasMensagem.setErroMensagem("Erro ao inserir imagem:\n" + ex, "Cadastro");
                caminhoImagem = "";
            }
        }
    }
}
