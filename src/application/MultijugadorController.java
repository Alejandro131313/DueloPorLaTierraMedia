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

import java.util.logging.Level;
import java.util.logging.Logger;

public class MultijugadorController {
	 private static final Logger LOGGER = Logger.getLogger(MultijugadorController.class.getName());

	@FXML
	private void onHost(final ActionEvent event) {
	    new Thread(() -> {
	    	final DueloServer server = new DueloServer(2396);
	        server.iniciar();  
	    }).start();

	    try {
	        Thread.sleep(500);
	        final  DueloClient client = new DueloClient("localhost", 2396);
	        client.conectar();
	        
	        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/SalaDeEspera.fxml"));
	        final Parent root = loader.load();
	        
	        /*URL fxmlURL = getClass().getResource("/application/fxml/SalaDeEspera.fxml");
	        FXMLLoader loader = new FXMLLoader(fxmlURL);
	        Parent root = loader.load();
	        */

	        final SalaDeEsperaController controller = loader.getController();
	        controller.setRedController(client.getRedController());

	        final Scene scene = new Scene(root);
	        final Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.setTitle("Sala de Espera - Esperando jugador...");

	    } catch (Exception e) {
	        LOGGER.log(Level.SEVERE, "Error al cargar SalaDeEspera:", e);
	        mostrarAlerta("Error", "No se pudo iniciar la partida.");
	    }
	}
    @FXML
    private void onUnirse(final ActionEvent event) {
        try {
        	final DueloClient client = new DueloClient("localhost", 2396);
            client.conectar();

            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/SalaDeEspera.fxml"));
            final Parent root = loader.load();

            final SalaDeEsperaController controller = loader.getController();
            controller.setRedController(client.getRedController()); // Correcto

            final Scene scene = new Scene(root);
            final Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sala de Espera - Conectando...");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo conectar a la partida.");
        }
    }
    @FXML
    private void onEstadisticas(final ActionEvent event) {
        mostrarAlerta("Estadísticas", "Estadísticas aún no disponibles.");
    }

    @FXML
    private void onVolver(final ActionEvent event) {
        try {
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaInicial.fxml"));
        	final Parent root = loader.load();

            // Aplicar CSS
        	final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            final Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Duelo por la Tierra Media - Menú Inicial");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }
    }

    private void mostrarAlerta(final String titulo, final String mensaje) {
    	final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
