package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class MultijugadorController {

    @FXML
    private void onHost(ActionEvent event) {
        mostrarAlerta("Host", "Funcionalidad de host en construcción.");
    }

    @FXML
    private void onUnirse(ActionEvent event) {
        mostrarAlerta("Unirse", "Funcionalidad de unirse a partida en construcción.");
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
