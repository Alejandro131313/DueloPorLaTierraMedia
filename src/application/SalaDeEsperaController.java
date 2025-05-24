package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;

import Clases.Jugador;
import Clases.JugadorPartida;
import Clases.Tablero;
import Clases.Unidad;
import Clases.Mensajes;
import Controladores.TableroController;
import Controladores.TableroNetworkController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SalaDeEsperaController {
	 private static final Logger LOGGER = Logger.getLogger(SalaDeEsperaController.class.getName());
	 

    @FXML
    private Label mensajeLabel;

    private TableroNetworkController redController;
    final public void setRedController(TableroNetworkController redController) {
        this.redController = redController;
        new Thread(() -> {
            esperarTurno();
        }).start();
    }

    private void esperarTurno() {
        try {
            LOGGER.info("Esperando mensaje del servidor...");
            while (true) {
            	final Object obj = redController.leerSiguienteMensaje();
                LOGGER.info("Mensaje recibido: " + obj);

                if (obj instanceof Mensajes) {
                	final Mensajes mensaje = (Mensajes) obj;
                    if (mensaje.getTipo() == Mensajes.Tipo.TURNO_JUGADOR) {
                        final String rol = (String) mensaje.getContenido();                   
                        Platform.runLater(() -> {
                            cargarPantallaPartida(rol);
                        });
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cargarPantallaPartida(final String rol) {
        try {
        	final FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/Partida.fxml"));
        	final Parent root = loader.load();

        	final TableroController controller = loader.getController();
            controller.setRedController(redController);
            redController.setTableroController(controller);

            final Tablero tablero = new Tablero(1, "modoPiramidal", "fase1");

            JugadorPartida jugador1;
            JugadorPartida jugador2;

            if ("JUGADOR1".equals(rol)) {
                jugador1 = new JugadorPartida(new Jugador("Jugador1"), null, 10, "Comunidad", null, null, new ArrayList<Unidad>());
                jugador2 = new JugadorPartida(new Jugador("Jugador2"), null, 10, "Mordor", null, null, new ArrayList<Unidad>());
            } else {
                jugador1 = new JugadorPartida(new Jugador("Jugador2"), null, 10, "Mordor", null, null, new ArrayList<Unidad>());
                jugador2 = new JugadorPartida(new Jugador("Jugador1"), null, 10, "Comunidad", null, null, new ArrayList<Unidad>());
            }

            controller.inicializarTablero(jugador1, jugador2, tablero);

            final Stage stage = (Stage) mensajeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Duelo por la Tierra Media - Partida en curso");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error", e);
        }
    }
}

