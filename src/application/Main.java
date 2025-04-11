package application;
	
import Clases.Jugador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga vista fxml
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("VistaInicial.fxml"));
            Scene scene = new Scene(root);
            
            //forzado de render por software
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            
            // hoja de estilos
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            

            primaryStage.setTitle("Duelo por la tierra media");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}