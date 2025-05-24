package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import java.net.URL; 
import Clases.DueloClient;
import Clases.DueloServer;
import javafx.event.ActionEvent;
import java.util.logging.Logger;

public class MultijugadorController {
	 private static final Logger logger = Logger.getLogger(MultijugadorController.class.getName());

	@FXML
	private void onHost(ActionEvent event) {
	    new Thread(() -> {
	        DueloServer server = new DueloServer(2396);
	        server.iniciar();  
	    }).start();

	    try {
	        Thread.sleep(500);
	        DueloClient client = new DueloClient("localhost", 2396);
	        client.conectar();
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/SalaDeEspera.fxml"));
            Parent root = loader.load();
	        
	        /*URL fxmlURL = getClass().getResource("/application/fxml/SalaDeEspera.fxml");
	        FXMLLoader loader = new FXMLLoader(fxmlURL);
	        Parent root = loader.load();
	        */

	        SalaDeEsperaController controller = loader.getController();
	        controller.setRedController(client.getRedController());

	        Scene scene = new Scene(root);
	        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.setTitle("Sala de Espera - Esperando jugador...");

	    } catch (Exception e) {
	        logger.info("Error al cargar SalaDeEspera:");
	        e.printStackTrace();
	        mostrarAlerta("Error", "No se pudo iniciar la partida.");
	    }
	}

    @FXML
    private void onUnirse(ActionEvent event) {
        try {
            DueloClient client = new DueloClient("localhost", 2396);
            client.conectar();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/SalaDeEspera.fxml"));
            Parent root = loader.load();

            SalaDeEsperaController controller = loader.getController();
            controller.setRedController(client.getRedController()); // Correcto

            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sala de Espera - Conectando...");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo conectar a la partida.");
        }
    }
    @FXML
    private void onEstadisticas(ActionEvent event) {
        mostrarAlerta("Estadísticas", "Estadísticas aún no disponibles.");
    }

    @FXML
    private void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaInicial.fxml"));
            Parent root = loader.load();

            // Aplicar CSS
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Duelo por la Tierra Media - Menú Inicial");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
