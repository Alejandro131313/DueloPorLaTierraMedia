package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import Clases.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class VistaController {
		

    @FXML
    private void onTutorial() {
        // URL del video de YouTube
        String videoUrl = "https://www.youtube.com/watch?v=ZEScRyJ_KuM&t=203s"; 
        
        // Crea un WebView para cargar la URL de YouTube
        WebView webView = new WebView();
        webView.getEngine().load(videoUrl);

        // Crear un StackPane para contener el WebView
        StackPane root = new StackPane();
        root.getChildren().add(webView);

        // Crear una nueva escena con el WebView
        Scene scene = new Scene(root, 800, 600);

        // Crear una nueva ventana (Stage) para mostrar el video
        Stage videoStage = new Stage();
        videoStage.setTitle("Tutorial");
        videoStage.setScene(scene);
        videoStage.show();
    }
    @FXML
    private void onNuevaPartida(javafx.event.ActionEvent event) {
        try {
            // Cargar la vista de selección de facción
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/SeleccionFaccion.fxml"));
            Parent root = loader.load();

            // Cambiar a la nueva escena
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Configurar Partida");
        } catch (Exception e) {
            e.printStackTrace();
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
            String nombreArchivo = "datos_partida.html"; // Asegúrate de que el nombre coincide

            // Crear referencia al archivo
            File archivo = new File(nombreArchivo);

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
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir el archivo HTML: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
 
}

