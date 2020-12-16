package view.Preparo02;

import controlador.Conexao;
import controlador.MudarTela;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(Preparo02View.idPedido01);
        nomeProduto.setText(Preparo02View.quantidadePr + " x " + Preparo02View.nomeProduto);
        destinoProduto.setText(Preparo02View.pesquisaMesa);
    }

    @FXML
    void enviar(ActionEvent event) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = '5' where id_pedido = ?");
            pst.setInt(1, Integer.parseInt(id.getText()));
            pst.execute();

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
        }

        ci.iniciarPreparo02View(event);
    }
}
