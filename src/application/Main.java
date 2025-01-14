package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista desde el archivo FXML
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Vista.fxml"));
            // Crear la escena con el diseño cargado
            Scene scene = new Scene(root);
            
            // Aplicar la hoja de estilos CSS
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            // Configurar la ventana principal
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