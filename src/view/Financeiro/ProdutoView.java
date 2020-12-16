package view.Financeiro;

import controlador.Conexao;
import controlador.MudarTela;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    private Label precoProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(FinanceiroView.idPedido01);
        nomeProduto.setText(FinanceiroView.quantidadePr + " x " + FinanceiroView.nomeProduto);
        destinoProduto.setText(FinanceiroView.pesquisaMesa + " vendido em " + FinanceiroView.dataPedido + " às " + FinanceiroView.horaPedido);
        precoProduto.setText(FinanceiroView.valorPr);
    }
}
