package Controladores;

import Clases.Jugador;
import Clases.JugadorPartida;
import Clases.Partida;
import Clases.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurarPartidaController {

	@FXML
	private TextField nombreComunidad;

	@FXML
	private TextField nombreMordor;

	@FXML
	private void onComenzarPartida() {
		try {
			// Validar los nombres ingresados
			if (nombreComunidad.getText().isEmpty() || nombreMordor.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Datos faltantes");
				alert.setHeaderText("Faltan datos para configurar la partida");
				alert.setContentText("Por favor, ingrese nombres válidos para ambos jugadores.");
				alert.showAndWait();
				return;
			}

			// Generar datos aleatorios para los jugadores
			int edadJugador1 = (int) (Math.random() * (50 - 18 + 1) + 18); // Edad entre 18 y 50
			int victoriasJugador1 = (int) (Math.random() * 101); // Número de victorias entre 0 y 100

			int edadJugador2 = (int) (Math.random() * (50 - 18 + 1) + 18); // Edad entre 18 y 50
			int victoriasJugador2 = (int) (Math.random() * 101); // Número de victorias entre 0 y 100

			// Crear los jugadores con los datos ingresados y aleatorios
			Jugador jugador1 = new Jugador(1, nombreComunidad.getText(), edadJugador1, victoriasJugador1);
			Jugador jugador2 = new Jugador(2, nombreMordor.getText(), edadJugador2, victoriasJugador2);

			// Generar el archivo HTML con los datos de ambos jugadores y guardarlo en el
			// escritorio
			String nombreArchivo = System.getProperty("user.home") + File.separator + "Desktop" + File.separator
					+ "datos_partida.html";
			guardarArchivoHTML(nombreArchivo, jugador1, jugador2);

			// Mostrar mensaje de éxito
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Éxito");
			alert.setHeaderText("Archivo HTML generado");
			alert.setContentText("El archivo se ha guardado correctamente en el escritorio.");
			alert.showAndWait();

			// Cargar la vista del tablero del juego
			cargarVistaTablero(jugador1, jugador2);

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ocurrió un error al configurar la partida");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	private void guardarArchivoHTML(String nombreFichero, Jugador jugador1, Jugador jugador2) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
			// Inicio del HTML
			bw.write("<!DOCTYPE html>");
			bw.newLine();
			bw.write("<html lang=\"es\">");
			bw.newLine();
			bw.write("<head>");
			bw.newLine();
			bw.write("<meta charset=\"UTF-8\">");
			bw.newLine();
			bw.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			bw.newLine();
			bw.write("<title>Datos de los Jugadores</title>");
			bw.newLine();
			bw.write("<style>");
			bw.newLine();

			// Estilos CSS
			bw.write(
					"body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 0; padding: 20px; }");
			bw.newLine();
			bw.write("table { width: 80%; margin: auto; border-collapse: collapse; }");
			bw.newLine();
			bw.write("th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }");
			bw.newLine();
			bw.write("th { background-color: #4CAF50; color: white; }");
			bw.newLine();

			bw.write("</style>");
			bw.newLine();
			bw.write("</head>");
			bw.newLine();
			bw.write("<body>");
			bw.newLine();
			bw.write("<h1 style=\"text-align: center;\">Datos de los Jugadores</h1>");
			bw.newLine();

			// Tabla con los datos de los jugadores
			bw.write("<table>");
			bw.newLine();
			bw.write("<tr><th>ID</th><th>Nombre</th><th>Edad</th><th>Victorias</th></tr>");
			bw.newLine();

			// Datos del Jugador 1
			bw.write("<tr>");
			bw.write("<td>" + jugador1.getId() + "</td>");
			bw.write("<td>" + jugador1.getNombre() + "</td>");
			bw.write("<td>" + jugador1.getEdad() + "</td>");
			bw.write("<td>" + jugador1.getNumeroVictorias() + "</td>");
			bw.write("</tr>");
			bw.newLine();

			// Datos del Jugador 2
			bw.write("<tr>");
			bw.write("<td>" + jugador2.getId() + "</td>");
			bw.write("<td>" + jugador2.getNombre() + "</td>");
			bw.write("<td>" + jugador2.getEdad() + "</td>");
			bw.write("<td>" + jugador2.getNumeroVictorias() + "</td>");
			bw.write("</tr>");
			bw.newLine();

			// Fin de la tabla
			bw.write("</table>");
			bw.newLine();
			bw.write("</body>");
			bw.newLine();
			bw.write("</html>");
		} catch (IOException e) {
			System.out.println("Ocurrió un error al escribir el archivo HTML: " + e.getMessage());
		}
	}

	private void cargarVistaTablero(Jugador jugador1, Jugador jugador2) {
		try {
			// Cargar la vista del tablero
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/fxml/Partida.fxml"));
			Parent root = loader.load();

			// Obtener el controlador del tablero
			TableroController tableroController = loader.getController();

			// Crear la partida y configurar los jugadores
			Tablero tablero = new Tablero(1, "Disposición inicial", "fase1");
			Partida partida = new Partida(1, "12:00", "2025-01-01", tablero);
			JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, partida, 0, "Comunidad", null, null, null);
			JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, partida, 0, "Sauron", null, null, null);

			// Configurar los jugadores y el tablero en el controlador del tablero
			tableroController.inicializarTablero(jugadorPartida1, jugadorPartida2, tablero);

			// Cambiar a la nueva escena
			Stage stage = (Stage) nombreComunidad.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Tablero de Juego");
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se pudo cargar la vista del tablero");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
}
