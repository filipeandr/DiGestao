package view.TelaLogin01;

import controlador.Conexao;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.Inicial.InicioView;

public class TelaLogin01 extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin01.fxml"));

        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        stage.setWidth(1050);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
        if(!TelaLogin01View.chaveAbertura){
            InicioView.conex.desconecta();
        }
        if(!TelaLogin01View.chaveFechamento){
            TelaLogin01View.conex.desconecta();
        }
        
        System.exit(0);
    }
}
