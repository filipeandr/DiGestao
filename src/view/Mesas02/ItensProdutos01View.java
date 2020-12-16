package view.Mesas02;

import controlador.MudarTela;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItensProdutos01View implements Initializable {

    private final MudarTela ci = new MudarTela();
    public static String nomePr, precoPr, idPr, idMes;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;

    @FXML
    private Label idProduto;

    @FXML
    private Label idMesa;
    
    @FXML
    private Label imagemProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(Mesas02View.nomeProduto);
        precoProduto.setText("R$" + Mesas02View.precoProduto);
        idProduto.setText(Integer.toString(Mesas02View.idAtual));
        idMesa.setText(Integer.toString(Mesas02View.idMesa));

        String strImagem = Mesas02View.caminhoImagemAtual;
        double width = 57.0;
        double heigth = 52.0;

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
    void adicionarProduto(ActionEvent event) {
        idPr = idProduto.getText();
        nomePr = nomeProduto.getText();
        precoPr = precoProduto.getText();
        idMes = idMesa.getText();

        ci.iniciarMesas03(event);
    }
}
