package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VistaController {
		
    private static final Logger LOGGER = Logger.getLogger(VistaController.class.getName());

    @FXML
    private void onTutorial() {
        // URL del video de YouTube
        final String videoUrl = "https://www.youtube.com/watch?v=ZEScRyJ_KuM&t=203s"; 
        
        // Crea un WebView para cargar la URL de YouTube
        final WebView webView = new WebView();
        webView.getEngine().load(videoUrl);

        // Crear un StackPane para contener el WebView
        final StackPane root = new StackPane();
        root.getChildren().add(webView);

        // Crear una nueva escena con el WebView
        final Scene scene = new Scene(root, 800, 600);

        // Crear una nueva ventana (Stage) para mostrar el video
        final Stage videoStage = new Stage();
        videoStage.setTitle("Tutorial");
        videoStage.setScene(scene);
        videoStage.show();
    }

    
    @FXML
    private void onNuevaPartida(final javafx.event.ActionEvent event) {
        try {
            // Cargar la vista de selección de facción
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/SeleccionFaccion.fxml"));
        	final Parent root = loader.load();

            // Cambiar a la nueva escena
        	final Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Configurar Partida");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar la vista de selección de facción", e);
        }
    }


    @FXML
    private void onCargarPartida() {
        // Lógica para el botón "Cargar Partida"
        mostrarAlerta("Cargar Partida", "Has seleccionado cargar una partida existente.");
    }
    
    
    @FXML
    private void onSalir() {
        // Lógica para el botón "Salir"
        System.exit(0);
    }
    @FXML
    private void onmostrarJugador() {
    	try {
            // Nombre del archivo generado previamente en ConfigurarPartidaController
    		final String nombreArchivo = "datos_partida.html"; // Asegúrate de que el nombre coincide

            // Crear referencia al archivo
    		final File archivo = new File(nombreArchivo);

            // Verificar si el archivo existe
            if (archivo.exists()) {
                // Intentar abrir el archivo en el navegador
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(archivo.toURI());
                } else {
                    mostrarAlerta("Error", "Tu sistema no soporta la apertura automática de archivos.");
                }
            } else {
                mostrarAlerta("Archivo no encontrado", "El archivo HTML no existe. Genera el archivo antes de intentar abrirlo.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo abrir el archivo HTML", e);
            mostrarAlerta("Error", "No se pudo abrir el archivo HTML: " + e.getMessage());
        }
    }

    private void mostrarAlerta(final String titulo, final String mensaje) {
    	final Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
 
    @FXML
    private void onMultijugador(javafx.event.ActionEvent event) {
        try {
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaInicial.fxml"));
        	final Parent root = loader.load();

        	final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            final Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene); // Usamos scene que ya creamos, no otra nueva
            stage.setTitle("Duelo por la Tierra Media - Inicio");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar VistaInicial.fxml", e);
        }
    }


    
}

