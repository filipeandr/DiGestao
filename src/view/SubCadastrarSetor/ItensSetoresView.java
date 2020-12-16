package view.SubCadastrarSetor;

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

public class ItensSetoresView implements Initializable {

    private final ControladorProdutos c = new ControladorProdutos();
    private final SubCadastrarSetorView sv = new SubCadastrarSetorView();
    private final MudarTela ci = new MudarTela();
    private final Conexao conex = InicioView.conex;
    private boolean verifica, aux;
    private String salvarCategoria;

    @FXML
    private TextField nomeSetor;

    @FXML
    private Button editar;

    @FXML
    private Button excluir;

    @FXML
    private TextField idSetor;

    public String getSalvarCategoria() {
        return salvarCategoria;
    }

    public void setSalvarCategoria(String salvarCategoria) {
        this.salvarCategoria = salvarCategoria;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeSetor.setText(SubCadastrarSetorView.setorAtual);
        idSetor.setText(Integer.toString(SubCadastrarSetorView.idAtual));
    }

    @FXML
    public void editarOuExcluir(ActionEvent actionEvent) {

        if (actionEvent.getSource() == editar) {
            aux = false;
            verifica = false;
            setSalvarCategoria(nomeSetor.getText());
            nomeSetor.setEditable(true);
            nomeSetor.requestFocus();
            nomeSetor.selectAll();

            new Thread() {
                @Override
                public void run() {
                    while (nomeSetor.isEditable()) {
                        if (!nomeSetor.isFocused()) {
                            nomeSetor.setText("");
                            nomeSetor.setText(getSalvarCategoria());
                            nomeSetor.setEditable(false);
                            aux = true;
                        }
                        if (aux == false) {
                            do {
                                verifica = conex.verificarDadoExistente(nomeSetor.getText().toUpperCase(),
                                        SubCadastrarSetorView.quantidadeDados, SubCadastrarSetorView.dados, verifica);
                            } while (nomeSetor.isFocused() && verifica == false);
                        }
                    }
                }
            }.start();
        }
        if (actionEvent.getSource() == excluir) {
            int resposta = 0;

            if (resposta == TelasMensagem.confirmaMensagem("Deseja remover setor?", "Remover setor")) {
                c.excluirCategoria(Integer.parseInt(idSetor.getText()), 1, "setor", "id_setor");

                try {
                    ci.iniciarSetores(actionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(ItensSetoresView.class.getName()).log(Level.SEVERE, null, ex);
                    TelasMensagem.setErroMensagem("Erro ao iniciar tela de setores:\n" + ex, "Setor");
                }
            }
        }
    }

    @FXML
    public void editarSetor(ActionEvent actionEvent) {
        if (nomeSetor.getText().length() > 0) {
            if (verifica == false) {
                c.editarCategoria(nomeSetor.getText(), Integer.parseInt(idSetor.getText()),
                        2, "setor", "setor", "id_setor");
                nomeSetor.setText(nomeSetor.getText());
                nomeSetor.setEditable(false);
                try {
                    ci.iniciarSetores(actionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(ItensSetoresView.class.getName()).log(Level.SEVERE, null, ex);
                    TelasMensagem.setErroMensagem("Erro ao iniciar tela de setores:\n" + ex, "Setor");
                }
            } else {
                if (nomeSetor.getText().toUpperCase().equals(getSalvarCategoria().toUpperCase()) && aux == false) {
                    nomeSetor.setEditable(false);
                    nomeSetor.setText(getSalvarCategoria());
                } else {
                    nomeSetor.setEditable(false);
                    nomeSetor.setText(getSalvarCategoria());
                    TelasMensagem.setErroMensagem("Setor já cadastrado", "Editar setor");
                }
            }
        } else {
            nomeSetor.setEditable(false);
            nomeSetor.setText(getSalvarCategoria());
        }
    }
}
