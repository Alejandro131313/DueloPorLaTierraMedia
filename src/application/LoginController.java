package application;

import Clases.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;

public class LoginController {

    @FXML private VBox formulario;
    @FXML private VBox botonesInicio;
    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoEmail;
    @FXML private Button btnAccion;

    private boolean esRegistro = false;

    // Mostrar formulario de inicio de sesión
    @FXML
    private void mostrarLogin() {
        esRegistro = false;
        formulario.setVisible(true);
        formulario.setManaged(true);
        campoEmail.setVisible(false);
        campoEmail.setManaged(false);
        btnAccion.setText("Iniciar Sesión");
        botonesInicio.setVisible(false);
        botonesInicio.setManaged(false);
    }

    // Mostrar formulario de registro
    @FXML
    private void mostrarRegistro() {
        esRegistro = true;
        formulario.setVisible(true);
        formulario.setManaged(true);
        campoEmail.setVisible(true);
        campoEmail.setManaged(true);
        btnAccion.setText("Crear Cuenta");
        botonesInicio.setVisible(false);
        botonesInicio.setManaged(false);
    }

    // Ocultar formulario actual
    @FXML
    private void ocultarFormulario() {
        formulario.setVisible(false);
        formulario.setManaged(false);
        botonesInicio.setVisible(true);
        botonesInicio.setManaged(true);
        campoUsuario.clear();
        campoContrasena.clear();
        campoEmail.clear();
    }

    // Acción cuando se pulsa el botón de confirmar login o registro
    @FXML
    private void onAccionFormulario() {
        String usuario = campoUsuario.getText().trim();
        String contrasena = campoContrasena.getText().trim();
        String email = campoEmail.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty() || (esRegistro && email.isEmpty())) {
            mostrarAlerta("Error", "Completa todos los campos.");
            return;
        }

        if (esRegistro) {
            if (Database.insertarUsuario(usuario, contrasena, email)) {
                mostrarAlerta("Éxito", "Usuario creado correctamente.");
                ocultarFormulario();
            } else {
                mostrarAlerta("Error", "No se pudo crear el usuario.");
            }
        } else {
            if (Database.verificarUsuario(usuario, contrasena)) {
                mostrarAlerta("Bienvenido", "Inicio de sesión correcto.");
                cargarPantallaMultijugador();
            } else {
                mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
            }
        }
    }

    // Cargar vista Multijugador
    private void cargarPantallaMultijugador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Multijugador.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Stage stage = (Stage) campoUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modo Multijugador");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la pantalla multijugador.");
        }
    }

    // Salir de la aplicación
    @FXML
    private void onSalir() {
        System.exit(0);
    }

    // Volver al menú principal (Vista.fxml)
    @FXML
    private void onVolverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Stage stage = (Stage) campoUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Duelo por la Tierra Media - Menú Principal");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }
    }

    // Método reutilizable para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
