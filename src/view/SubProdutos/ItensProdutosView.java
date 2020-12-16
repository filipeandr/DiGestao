package view.SubProdutos;

import controlador.Conexao;
import controlador.ControladorProdutos;
import controlador.MudarTela;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.SubCadastrarSetor.SubCadastrarSetorView;
import view.SubCategorias.ItensCategoriasView;

public class ItensProdutosView implements Initializable {

    private final ControladorProdutos c = new ControladorProdutos();
    private final SubCadastrarSetorView sv = new SubCadastrarSetorView();
    private final MudarTela ci = new MudarTela();
    private final Conexao conex = InicioView.conex;
    private boolean verifica, aux;
    private String salvarCategoria, str;
    public static boolean editando = false;
    public static int idAtual;

    @FXML
    private TextField idProduto;

    @FXML
    private Label imagemProduto;

    @FXML
    private Button visibilidade;

    @FXML
    private Button excluir;

    @FXML
    private TextField idCategoria;

    @FXML
    private Button editar;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField categoriaProduto;

    @FXML
    private TextField preco;

    @FXML
    private TextField estoque;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(SubProdutosView.dadosAtual);
        categoriaProduto.setText(SubProdutosView.categoriaAtual);
        preco.setText("R$ " + SubProdutosView.precoAtual);
        idProduto.setText(Integer.toString(SubProdutosView.idAtual));
        estoque.setText(SubProdutosView.estoqueAtual);

        String strImagem = SubProdutosView.caminhoImagemAtual;
        double width = 58.0;
        double heigth = 58.0;

        try {
            FileInputStream inputstream = new FileInputStream(strImagem);
            Image image = new Image(inputstream);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(heigth);
            imageView.setFitWidth(width);
            imagemProduto.setGraphic(imageView);
        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    public void editarOuExcluir(ActionEvent actionEvent) {
        if (actionEvent.getSource() == editar) {
            idAtual = Integer.parseInt(idProduto.getText());
            editando = true;
            try {
                ci.iniciarCadastrarProdutos(actionEvent);
            } catch (IOException ex) {
                Logger.getLogger(ItensProdutosView.class.getName()).log(Level.SEVERE, null, ex);
                TelasMensagem.setErroMensagem("Erro ao tentar editar produto:\n" + ex, "Produtos");
            }
        }
        if (actionEvent.getSource() == excluir) {
            int resposta = 0;

            if (resposta == TelasMensagem.confirmaMensagem("Deseja remover categoria?", "Remover produto")) {
                c.excluirCategoria(Integer.parseInt(idProduto.getText()), 3, "produto", "id_produto");

                try {
                    ci.iniciarSubProdutos(actionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(ItensCategoriasView.class.getName()).log(Level.SEVERE, null, ex);
                    TelasMensagem.setErroMensagem("Erro ao tentar excluir produto:\n" + ex, "Produtos");
                }
            }
        }
    }

    @FXML
    public void editarEstoque(ActionEvent actionEvent) {
        try {
            int valor = Integer.parseInt(estoque.getText());
            c.setEditaProdutoCadastrado(Integer.toString(valor), Integer.parseInt(idProduto.getText()), 1, "produto", "estoque", "id_produto");
            idProduto.requestFocus();
        } catch (NumberFormatException e) {
            TelasMensagem.setErroMensagem("Número inválido", "Alterar estoque");
        }
    }
}
