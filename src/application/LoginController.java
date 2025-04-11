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
                // Cargar Vista.fxml aquí
                // ...
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Vista.fxml"));
            } else {
                mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
            }
        }
    }

    @FXML
    private void onSalir() {
        System.exit(0);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

