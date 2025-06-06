package Clases;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un jugador. Incluye información como ID, nombre, edad y número de victorias. 
 * 
 * También permite exportar los datos a un archivo HTML.
 * 
 * @author Alejandro
 * @version 1.0
 */

public class Jugador {

	private static final Logger LOGGER = Logger.getLogger(Jugador.class.getName());

	private int idJugador;
	private String nombre;
	private int edad;
	private int numeroVictorias;

	public Jugador(final int idJugador, final String nombre, final int edad, final int numeroVictorias) {
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.edad = edad;
		this.numeroVictorias = numeroVictorias;
	}

	public Jugador(final String nombre) {
		this.nombre = nombre;
	}

	// Getters y Setters
	public int getId() {
		return idJugador;
	}

	public void setId(final int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(final int edad) {
		this.edad = edad;
	}

	public int getNumeroVictorias() {
		return numeroVictorias;
	}

	public void setNumeroVictorias(final int numeroVictorias) {
		this.numeroVictorias = numeroVictorias;
	}

	/**
	 * Método para escribir un archivo HTML con la información de múltiples
	 * jugadores.
	 * 
	 * @param nombreFichero Nombre del archivo HTML.
	 * @param jugadores     Lista de jugadores a incluir.
	 */
	public static void escribirEnFicheroHTML(String nombreFichero, final Jugador... jugadores) {
		if (!nombreFichero.endsWith(".html")) {
			nombreFichero += ".html";
		}

		try (BufferedWriter buffWrit = new BufferedWriter(new FileWriter(nombreFichero))) {
			buffWrit.write("<!DOCTYPE html>");
			buffWrit.newLine();
			buffWrit.write("<html lang=\"es\">");
			buffWrit.newLine();
			buffWrit.write("<head>");
			buffWrit.newLine();
			buffWrit.write("<meta charset=\"UTF-8\">");
			buffWrit.newLine();
			buffWrit.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			buffWrit.newLine();
			buffWrit.write("<title>Datos de los Jugadores</title>");
			buffWrit.newLine();
			buffWrit.write("<style>");
			buffWrit.newLine();

			// Estilos CSS
			buffWrit.write("body { font-family: 'Arial', sans-serif; background-color: #f5f5f5; color: #333; }");
			buffWrit.newLine();
			buffWrit.write("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
			buffWrit.newLine();
			buffWrit.write("th, td { padding: 10px; text-align: center; border: 1px solid #ccc; }");
			buffWrit.newLine();
			buffWrit.write("th { background-color: #4CAF50; color: white; }");
			buffWrit.newLine();
			buffWrit.write("tr:nth-child(even) { background-color: #f9f9f9; }");
			buffWrit.newLine();

			buffWrit.write("</style>");
			buffWrit.newLine();
			buffWrit.write("</head>");
			buffWrit.newLine();
			buffWrit.write("<body>");
			buffWrit.newLine();
			buffWrit.write("<h1 style=\"text-align: center;\">Datos de los Jugadores</h1>");
			buffWrit.newLine();

			// Inicio de la tabla
			buffWrit.write("<table>");
			buffWrit.newLine();
			buffWrit.write("<tr><th>ID</th><th>Nombre</th><th>Edad</th><th>Victorias</th></tr>");
			buffWrit.newLine();

			// Agregar una fila por cada jugador
			for (final Jugador jugador : jugadores) {
				buffWrit.write("<tr>");
				buffWrit.write("<td>" + jugador.getId() + "</td>");
				buffWrit.write("<td>" + jugador.getNombre() + "</td>");
				buffWrit.write("<td>" + jugador.getEdad() + "</td>");
				buffWrit.write("<td>" + jugador.getNumeroVictorias() + "</td>");
				buffWrit.write("</tr>");
				buffWrit.newLine();
			}

			// Fin de la tabla
			buffWrit.write("</table>");
			buffWrit.newLine();
			buffWrit.write("</body>");
			buffWrit.newLine();
			buffWrit.write("</html>");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error al escribir el fichero HTML", e);
		}

		// Intentar abrir el archivo automáticamente
		try {
			final File archivo = new File(nombreFichero);
			if (archivo.exists() && Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(archivo.toURI());
			} else {
				LOGGER.warning("No se pudo abrir el fichero automáticamente.");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error al intentar abrir el fichero", e);
		}
	}
}
