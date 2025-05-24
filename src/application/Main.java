package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(final Stage primaryStage) {
        try {
            // Carga vista fxml
            final AnchorPane root = FXMLLoader.load(getClass().getResource("Vista.fxml"));
            final Scene scene = new Scene(root);

            //forzado de render por software
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

            //hoja de estilos
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setTitle("Duelo por la Tierra Media");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar la aplicación", e);
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
