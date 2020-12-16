package view.Preparo01;

import controlador.Conexao;
import controlador.MudarTela;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class ProdutoView implements Initializable {

    private final Conexao conex = InicioView.conex;
    private final MudarTela ci = new MudarTela();

    @FXML
    private Label nomeProduto;

    @FXML
    private Label destinoProduto;

    @FXML
    private Button preparoProduto;

    @FXML
    private Button prontoProduto;

    @FXML
    private Label id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Preparo01View.botao == 1) {
            prontoProduto.setVisible(false);
        } else if (Preparo01View.botao == 2) {
            preparoProduto.setVisible(false);
            prontoProduto.setVisible(true);
        }

        id.setText(Preparo01View.idPedido01);
        nomeProduto.setText(Preparo01View.quantidadePr + " x " + Preparo01View.nomeProduto);
        destinoProduto.setText(Preparo01View.pesquisaMesa);
    }

    @FXML
    void enviar(ActionEvent event) {
        if (event.getSource() == preparoProduto) {

            try {
                PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = '3' where id_pedido = ?");
                pst.setInt(1, Integer.parseInt(id.getText()));
                pst.execute();

            } catch (SQLException ex) {
                TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
            }

            ci.iniciarPreparo01View(event);
        }
        if (event.getSource() == prontoProduto) {

            try {
                PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = '4' where id_pedido = ?");
                pst.setInt(1, Integer.parseInt(id.getText()));
                pst.execute();

            } catch (SQLException ex) {
                TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
            }

            ci.iniciarPreparo01View(event);
        }
    }
}
