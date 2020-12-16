package view.Mesas01;

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
import javafx.scene.control.TextField;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class ItensProdutosView implements Initializable {
    
    Conexao conex = InicioView.conex;
    private final MudarTela ci = new MudarTela();

    @FXML
    private Button excluir;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;
    
    @FXML
    private Label idPedido;
    
    @FXML
    private Label imagemProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(Mesas01View.nomeProduto);        
        precoProduto.setText(Mesas01View.quantidadePr + " x " + Mesas01View.valorPr);
        idPedido.setText(Mesas01View.idPedidoA);
    }
    
    @FXML
    void excluirPedido(ActionEvent event) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pedido where id_pedido =?");

            pst.setInt(1, Integer.parseInt(idPedido.getText()));

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
        
        ci.iniciarMesas01(event);
    }
}
