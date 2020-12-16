package view.SubCategorias;

import controlador.Conexao;
import controlador.ControladorProdutos;
import controlador.MudarTela;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.SubCadastrarSetor.SubCadastrarSetorView;

public class ItensCategoriasView implements Initializable {

    private final ControladorProdutos c = new ControladorProdutos();
    private final SubCadastrarSetorView sv = new SubCadastrarSetorView();
    private final MudarTela ci = new MudarTela();
    private final Conexao conex = InicioView.conex;
    private boolean verifica, aux;
    private String salvarCategoria, str;

    @FXML
    private TextField nomeCategoria;

    @FXML
    private Button editar;

    @FXML
    private Button excluir;

    @FXML
    private TextField idCategoria;

    public String getSalvarCategoria() {
        return salvarCategoria;
    }

    public void setSalvarCategoria(String salvarCategoria) {
        this.salvarCategoria = salvarCategoria;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeCategoria.setText(SubCategoriasView.categoriaAtual);
        idCategoria.setText(Integer.toString(SubCategoriasView.idAtual));
    }

    @FXML
    public void editarOuExcluir(ActionEvent actionEvent) {

        if (actionEvent.getSource() == editar) {
            aux = false;
            verifica = false;
            setSalvarCategoria(nomeCategoria.getText());
            nomeCategoria.setEditable(true);
            nomeCategoria.requestFocus();
            nomeCategoria.selectAll();

            new Thread() {
                @Override
                public void run() {
                    while (nomeCategoria.isEditable()) {
                        if (!nomeCategoria.isFocused()) {
                            nomeCategoria.setText("");
                            nomeCategoria.setText(getSalvarCategoria());
                            nomeCategoria.setEditable(false);
                            aux = true;
                        }
                        if (aux == false) {
                            do {
                                verifica = conex.verificarDadoExistente(nomeCategoria.getText().toUpperCase(),
                                        SubCategoriasView.quantidadeDados, SubCategoriasView.dados, verifica);
                            } while (nomeCategoria.isFocused());
                        }
                    }
                }
            }.start();
        }
        if (actionEvent.getSource() == excluir) {
            int resposta = 0;

            if (resposta == TelasMensagem.confirmaMensagem("Deseja remover categoria?", "Remover categoria")) {
                c.excluirCategoria(Integer.parseInt(idCategoria.getText()), 1, "categoria", "id_categoria");

                try {
                    ci.iniciarCategorias(actionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(ItensCategoriasView.class.getName()).log(Level.SEVERE, null, ex);
                    TelasMensagem.setErroMensagem("Erro ao tentar excluir categoria:\n" + ex, "Categoria");
                }
            }
        }
    }

    @FXML
    public void editarCategoria(ActionEvent actionEvent) {
        if (nomeCategoria.getText().length() > 0) {
            if (verifica == false) {
                c.editarCategoria(nomeCategoria.getText(), Integer.parseInt(idCategoria.getText()),
                        1, "categoria", "categoria", "id_categoria");
                nomeCategoria.setText(nomeCategoria.getText());
                nomeCategoria.setEditable(false);
                try {
                    ci.iniciarCategorias(actionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(ItensCategoriasView.class.getName()).log(Level.SEVERE, null, ex);
                    TelasMensagem.setErroMensagem("Erro ao tentar editar categoria:\n" + ex, "Categoria");
                }
            } else {
                if (nomeCategoria.getText().toUpperCase().equals(getSalvarCategoria().toUpperCase()) && aux == false) {
                    nomeCategoria.setEditable(false);
                    nomeCategoria.setText(getSalvarCategoria());
                } else {
                    nomeCategoria.setEditable(false);
                    nomeCategoria.setText(getSalvarCategoria());
                    TelasMensagem.setErroMensagem("Categoria já cadastrada", "Editar categoria");
                }
            }
        } else {
            nomeCategoria.setEditable(false);
            nomeCategoria.setText(getSalvarCategoria());
        }
    }
}
