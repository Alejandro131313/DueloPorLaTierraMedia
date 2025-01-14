package application;


import javafx.fxml.FXML;
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
    private void onNuevaPartida() {
        // Lógica para el botón "Nueva Partida"
        mostrarAlerta("Nueva Partida", "Has seleccionado iniciar una nueva partida.");
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
    

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

