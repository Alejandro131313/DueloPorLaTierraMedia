package application;

import Clases.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

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
        // Limpiar estilos anteriores
        campoUsuario.setStyle(null);
        campoContrasena.setStyle(null);
        campoEmail.setStyle(null);

        String usuario = campoUsuario.getText().trim();
        String contrasena = campoContrasena.getText().trim();
        String email = campoEmail.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty() || (esRegistro && email.isEmpty())) {
            mostrarAlerta("Error", "Completa todos los campos.");
            if (usuario.isEmpty()) campoUsuario.setStyle("-fx-border-color: red;");
            if (contrasena.isEmpty()) campoContrasena.setStyle("-fx-border-color: red;");
            if (esRegistro && email.isEmpty()) campoEmail.setStyle("-fx-border-color: red;");
            return;
        }

        if (esRegistro) {
            if (!usuario.matches("^[a-zA-Z0-9]{4,16}$")) {
                campoUsuario.setStyle("-fx-border-color: red;");
                mostrarAlerta("Error", "El nombre de usuario debe tener entre 4 y 16 caracteres alfanuméricos sin símbolos especiales.");
                return;
            }

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                campoEmail.setStyle("-fx-border-color: red;");
                mostrarAlerta("Error", "Introduce un correo electrónico válido.");
                return;
            }

            if (!contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{6,}$")) {
                campoContrasena.setStyle("-fx-border-color: red;");
                mostrarAlerta("Error", "La contraseña debe contener: mayúscula, minúscula, números y un símbolo especial.");
                return;
            }

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
                campoUsuario.setStyle("-fx-border-color: red;");
                campoContrasena.setStyle("-fx-border-color: red;");
                mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
            }
        }
    }


    // Cargar vista Multijugador
    private void cargarPantallaMultijugador() {
        try {
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Multijugador.fxml"));
        	final Parent root = loader.load();
        	final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            final Stage stage = (Stage) campoUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modo Multijugador");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar la pantalla multijugador", e);
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
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista.fxml"));
        	final Parent root = loader.load();
        	final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Stage stage = (Stage) campoUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Duelo por la Tierra Media - Menú Principal");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar el menú principal", e);
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }
    }

    // Método reutilizable para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
    	final Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
