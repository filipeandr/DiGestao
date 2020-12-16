package view.Mesas02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItensProdutosView02 implements Initializable {

    @FXML
    private Button excluir;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;
    
    @FXML
    private Label imagemProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeProduto.setText(Mesas02View.nomeProduto);
        precoProduto.setText(Mesas02View.quantidadePr + " x " + Mesas02View.valorPr);
        
        String strImagem = Mesas02View.caminhoImagemAtual;
        double width = 46.0;
        double heigth = 47.0;

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
}
