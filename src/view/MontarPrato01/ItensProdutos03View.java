package view.MontarPrato01;

import controlador.Conexao;
import controlador.ControladorMesas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Dao.MesaDao;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.Mesas01.ItensMesasView;

public class ItensProdutos03View implements Initializable {

    Conexao conex = InicioView.conex;
    private final ControladorMesas cm = new ControladorMesas();
    private final MesaDao md = new MesaDao();

    @FXML
    private Label nomeProduto;

    @FXML
    private Label precoProduto;

    @FXML
    private Label idProduto;

    @FXML
    private Button mais;

    @FXML
    private Button menos;

    @FXML
    private Label quantProduto;

    @FXML
    private Label idMesa;
    
    @FXML
    private Label imagemProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(MontarPrato01View.nomeProduto);
        precoProduto.setText("R$" + MontarPrato01View.precoProduto);
        idProduto.setText(Integer.toString(MontarPrato01View.idAtual));
        idMesa.setText(Integer.toString(MontarPrato01View.idMesa));
    }

    @FXML
    public void quantidadeProduto(ActionEvent actionEvent) {
        String preco = new String();

        if (actionEvent.getSource() == mais) {
            String qm = quantProduto.getText();
            int qmInt = 1;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt >= 0) {
                    qmInt += 1;
                }
            } catch (NumberFormatException e) {
                TelasMensagem.setErroMensagem("Erro de formatação numérica:\n" + e, "Montar prato");
            }
            if (qmInt < 2) {
                qm = Integer.toString(qmInt);
                quantProduto.setText(qm);
            }
            
            cm.montarPrato(Integer.parseInt(idProduto.getText()),Integer.parseInt(ItensMesasView.idMesaAtual),
                nomeProduto.getText(), quantProduto.getText(), precoProduto.getText(), "1");
            
            MontarPrato01View.numProdutos++;
        }
        if (actionEvent.getSource() == menos) {
            String qm = quantProduto.getText();
            int qmInt = 0;
            try {
                qmInt = Integer.parseInt(qm);
                if (qmInt > 0) {
                    qmInt -= 1;
                }
            } catch (NumberFormatException e) {
                TelasMensagem.setErroMensagem("Erro de formatação numérica:\n" + e, "Montar prato");
            }
            qm = Integer.toString(qmInt);
            quantProduto.setText(qm);

            md.excluirItemPrato("pedido", "id_produto", Integer.parseInt(idProduto.getText()));
            
            MontarPrato01View.numProdutos--;
        }
    }
}
