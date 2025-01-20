package Controladores;

import Clases.Jugador;
import Clases.JugadorPartida;
import Clases.Partida;
import Clases.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfigurarPartidaController {

    @FXML
    private TextField nombreComunidad;

    @FXML
    private TextField nombreMordor;

    @FXML
    private void onComenzarPartida() {
        try {
            // Crear jugadores
            Jugador jugador1 = new Jugador(1, nombreComunidad.getText(), 0, 0);
            Jugador jugador2 = new Jugador(2, nombreMordor.getText(), 0, 0);

            // Crear un tablero
            Tablero tablero = new Tablero(1, "Disposición inicial", "fase1");

            // Crear jugador-partida
            JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, null, 0, "Comunidad", null, null, null);
            JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, null, 0, "Sauron", null, null, null);

            // Obtener la fecha y hora actuales
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String horaActual = ahora.format(formatoHora);
            String fechaActual = ahora.format(formatoFecha);

            // Crear la partida
            Partida partida = new Partida(1, horaActual, fechaActual, tablero);

            // Asociar partida a jugadores
            jugadorPartida1.setPartida(partida);
            jugadorPartida2.setPartida(partida);

            // Cargar la vista del tablero
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/Partida.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del tablero
            TableroController tableroController = loader.getController();

            // Pasar los nombres de los jugadores al controlador del tablero
            tableroController.setNombresJugadores(nombreComunidad.getText(), nombreMordor.getText());

            // Cambiar a la nueva escena
            Stage stage = (Stage) nombreComunidad.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tablero de Juego");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
