package Controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Clases.Carta;
import Clases.JugadorPartida;
import Clases.LugarClave;
import Clases.Razas;
import Clases.Tablero;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableroController {

	@FXML
	private GridPane tableroCentral;

	@FXML
	private ListView<String> cartasJugador1;

	@FXML
	private ListView<String> cartasJugador2;
	@FXML
	private ListView<String> lugaresClaveRobadosComunidad;

	@FXML
	private ListView<String> lugaresClaveRobadosMordor;

	private Tablero tablero;
	private Map<Carta, Button> mapaCartasBotones = new HashMap<>();
	@FXML
	private HBox botonLugaresClave;

	@FXML
	private HBox botonRazas;

	@FXML
	private Label nombreJugadorComunidad;

	@FXML
	private Label nombreJugadorMordor;

	@FXML
	private Label oroComunidad, fuerzaComunidad, valorComunidad, astuciaComunidad, sabiduriaComunidad, coronaComunidad;
	@FXML
	private ImageView iconoOroComunidad, iconoFuerzaComunidad, iconoValorComunidad, iconoAstuciaComunidad,
			iconoSabiduriaComunidad, iconoCoronaComunidad;
	@FXML
	private Label hobbitsComunidad, enanosComunidad, humanosComunidad, elfosComunidad, magosComunidad, entsComunidad;
	@FXML
	private ImageView iconoHobbitsComunidad, iconoEnanosComunidad, iconoHumanosComunidad, iconoElfosComunidad,
			iconoMagosComunidad, iconoEntsComunidad;

	@FXML
	private Label oroMordor, fuerzaMordor, valorMordor, astuciaMordor, sabiduriaMordor, coronaMordor;
	@FXML
	private ImageView iconoOroMordor, iconoFuerzaMordor, iconoValorMordor, iconoAstuciaMordor, iconoSabiduriaMordor,
			iconoCoronaMordor;
	@FXML
	private Label hobbitsMordor, enanosMordor, humanosMordor, elfosMordor, magosMordor, entsMordor;
	@FXML
	private ImageView iconoHobbitsMordor, iconoEnanosMordor, iconoHumanosMordor, iconoElfosMordor, iconoMagosMordor,
			iconoEntsMordor;

	@FXML
	private HBox pistaCarrera;

	private JugadorPartida jugadorActual;
	private JugadorPartida jugadorComunidad;
	private JugadorPartida jugadorMordor;

	private int posicionSauron = 0;
	private int posicionComunidad = 27;

	private void moverFicha(int posicionActual, int cantidad, String colorFicha) {
		int nuevaPosicion = posicionActual + cantidad;

		if (nuevaPosicion >= 0 && nuevaPosicion < pistaCarrera.getChildren().size()) {

			Label labelActual = (Label) pistaCarrera.getChildren().get(posicionActual);
			labelActual.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-border-color: black;");

			posicionActual = nuevaPosicion;

			Label nuevoLabel = (Label) pistaCarrera.getChildren().get(posicionActual);
			nuevoLabel.setStyle("-fx-border-color: " + colorFicha + "; -fx-background-color: "
					+ obtenerColorFondoFicha(colorFicha) + "; -fx-padding: 10;");

			System.out.println("Ficha (" + colorFicha + ") se movió a la posición: " + (posicionActual + 1));
		} else {

			System.out.println("Movimiento fuera de rango para la ficha (" + colorFicha + ").");
		}

		if (colorFicha.equals("red")) {
			posicionSauron = posicionActual;
		} else if (colorFicha.equals("blue")) {
			posicionComunidad = posicionActual;
		}
	}

	// Método auxiliar para obtener el color de fondo según la ficha
	private String obtenerColorFondoFicha(String colorFicha) {
		if (colorFicha.equals("red")) {
			return "pink"; // Color de fondo para Sauron
		} else if (colorFicha.equals("blue")) {
			return "green"; // Color de fondo para La Comunidad
		} else {
			return "yellow"; // Color por defecto para otras fichas
		}
	}

	private void procesarMovimientoPorCarta(Carta carta) {
		String efecto = carta.getEfecto();

		if (efecto.startsWith("avance+")) {
			int avance = Integer.parseInt(efecto.replace("avance+", ""));

			// Verificar el turno del jugador actual
			if (jugadorActual.equals(jugadorComunidad)) {
				// La Comunidad roba y avanza SU ficha (blue)
				moverFicha(posicionComunidad, avance, "blue");
			} else if (jugadorActual.equals(jugadorMordor)) {
				// Sauron roba y avanza SU ficha (red)
				moverFicha(posicionSauron, avance, "red");
			}

		} else {
			System.out.println("La carta no afecta el movimiento de las fichas.");
		}
	}

	private final int[][] posiciones = { { 0, 5 }, { 0, 7 }, { 1, 4 }, { 1, 6 }, { 1, 8 }, { 2, 3 }, { 2, 5 }, { 2, 7 },
			{ 2, 9 }, { 3, 2 }, { 3, 4 }, { 3, 6 }, { 3, 8 }, { 3, 10 }, { 4, 1 }, { 4, 3 }, { 4, 5 }, { 4, 7 },
			{ 4, 9 }, { 4, 11 } };

	public void inicializarTablero(JugadorPartida jugadorPartida1, JugadorPartida jugadorPartida2, Tablero tablero) {
		this.jugadorActual = jugadorPartida1; // Config inicial
		this.jugadorMordor = jugadorPartida1;
		this.jugadorComunidad = jugadorPartida2;
		this.tablero = tablero;
		inicializarIconos();
		inicializarIconosRazas();
		// Actualizr nombrs de jguadores
		nombreJugadorComunidad.setText(jugadorComunidad.getJugador().getNombre());
		nombreJugadorMordor.setText(jugadorMordor.getJugador().getNombre());

		Label labelSauron = (Label) pistaCarrera.getChildren().get(posicionSauron);
		labelSauron.setStyle("-fx-border-color: red; -fx-background-color:pink; -fx-padding: 10;");

		Label labelComunidad = (Label) pistaCarrera.getChildren().get(posicionComunidad);
		labelComunidad.setStyle("-fx-border-color: blue; -fx-background-color: green; -fx-padding: 10;");

		configurarTablero();
		actualizarFondoTurno();
	}

	private void inicializarIconos() {
		// JugadorComunidad
		iconoOroComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoOro.png").toExternalForm()));
		iconoFuerzaComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoFuerza.png").toExternalForm()));
		iconoValorComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoValor.png").toExternalForm()));
		iconoAstuciaComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoAstucia.png").toExternalForm()));
		iconoSabiduriaComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoSabiduria.png").toExternalForm()));
		iconoCoronaComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoCorona.png").toExternalForm()));

		// JugadorMordor
		iconoOroMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoOro.png").toExternalForm()));
		iconoFuerzaMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoFuerza.png").toExternalForm()));
		iconoValorMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoValor.png").toExternalForm()));
		iconoAstuciaMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoAstucia.png").toExternalForm()));
		iconoSabiduriaMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoSabiduria.png").toExternalForm()));
		iconoCoronaMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/IconoCorona.png").toExternalForm()));
	}

	private void inicializarIconosRazas() {
		// JugadorComunidad
		iconoHobbitsComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
		iconoEnanosComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
		iconoHumanosComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
		iconoElfosComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
		iconoMagosComunidad
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
		iconoEntsComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));

		// JugadorMordor
		iconoHobbitsMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
		iconoEnanosMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
		iconoHumanosMordor
				.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
		iconoElfosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
		iconoMagosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
		iconoEntsMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));
	}

	private void configurarTablero() {
		tableroCentral.getChildren().clear();
		botonLugaresClave.getChildren().clear();
		mapaCartasBotones.clear();

		// Obtener cartas de la fase actual
		List<Carta> cartasFaseActual = tablero.obtenerCartasDelCapituloActual();

		int[][] posicionesActuales;
		switch (tablero.getCapitulo()) {
		case "fase2":
			posicionesActuales = posiciones;
			break;
		case "fase3":
			posicionesActuales = posiciones;
			break;
		default:
			posicionesActuales = posiciones;
		}

		// Colocar todas las cartas enpirámide
		for (int i = 0; i < posicionesActuales.length; i++) {
			if (i >= cartasFaseActual.size())
				break;
			Carta carta = cartasFaseActual.get(i);
			Button cartaBtn = crearBotonCarta(carta, i < 14); // Todas excepto la última fila están bloqueadas
			int row = posicionesActuales[i][0];
			int col = posicionesActuales[i][1];
			tableroCentral.add(cartaBtn, col, row);
			mapaCartasBotones.put(carta, cartaBtn);
		}

		// Config lugares clave y razas
		tablero.mezclarLugaresClave();
		List<LugarClave> seleccionados = tablero.seleccionarTresLugaresClave();
		mostrarLugaresClaveComoBotones(seleccionados);
		mostrarRazasComoBotones();
	}

	private Button crearBotonCarta(Carta carta, boolean bloqueada) {
		Button cartaBtn = new Button();
		cartaBtn.setStyle("-fx-padding: 4; -fx-border-color: black; -fx-border-radius: 4;");
		javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(
				new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm()));
		imagen.setFitWidth(90);
		imagen.setFitHeight(140);
		cartaBtn.setGraphic(imagen);

		// configuración inicial
		cartaBtn.setDisable(bloqueada);
		cartaBtn.setOnAction(e -> {
			boolean recursosSuficientes = false;
			switch (tablero.getCapitulo()) {
			case "fase1":
				recursosSuficientes = tablero.comprobarRecursosFase1(carta, jugadorActual);

				break;
			case "fase2":
				recursosSuficientes = tablero.comprobarRecursosFase2(carta, jugadorActual);

				break;
			case "fase3":
				recursosSuficientes = tablero.comprobarRecursosFase3(carta, jugadorActual);

				break;
			}

			if (!recursosSuficientes) {
				mostrarAlerta("No puedes robar esta carta", "No tienes suficientes recursos para robar esta carta.");
				return;
			}

			// se puede robar?
			if (puedeRobarse(carta)) {
				if (jugadorActual.equals(jugadorComunidad)) {
					cartasJugador1.getItems().add(carta.getNombre());
				} else {
					cartasJugador2.getItems().add(carta.getNombre());
				}
				cartaBtn.setVisible(false);

				// aplicar efecto
				switch (tablero.getCapitulo()) {
				case "fase1":
					tablero.aplicarEfectoCartaFase1(carta, jugadorActual);
					procesarMovimientoPorCarta(carta);
					break;
				case "fase2":
					tablero.aplicarEfectoCartaFase2(carta, jugadorActual);
					procesarMovimientoPorCarta(carta);
					break;
				case "fase3":
					tablero.aplicarEfectoCartaFase3(carta, jugadorActual);
					procesarMovimientoPorCarta(carta);
					break;
				}

				actualizarContadores(jugadorActual, jugadorActual.equals(jugadorComunidad));
				verificarCambioDeFase(); // Verificar si todas las cartas han sido jugadas
				cambiarTurno();
				actualizarCartasHabilitadas();
			} else {
				mostrarAlerta("No puedes robar esta carta",
						"Debes cumplir los requisitos o desbloquear las cartas inferiores.");
			}
		});

		// opcion para descartar carta
		cartaBtn.setOnContextMenuRequested(e -> {
			descartarCarta(carta, cartaBtn);
		});

		return cartaBtn;
	}

	private void verificarCambioDeFase() {
		boolean todasCartasJugadas = mapaCartasBotones.values().stream().noneMatch(Button::isVisible);

		if (todasCartasJugadas) {
			// limpia
			tableroCentral.getChildren().clear();
			botonLugaresClave.getChildren().clear();
			mapaCartasBotones.clear();
			switch (tablero.getCapitulo()) {
			case "fase1":
				tablero.setCapitulo("fase2");
				configurarTablero();
				break;
			case "fase2":
				tablero.setCapitulo("fase3");
				configurarTablero();
				break;
			case "fase3":
				mostrarAlerta("Juego terminado", "No quedan más cartas por jugar.");
				break;
			}
		}
	}

	private void descartarCarta(Carta carta, Button cartaBtn) {
		Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
		confirmacion.setTitle("Descartar carta");
		confirmacion.setHeaderText(null);
		confirmacion.setContentText("¿Estás seguro de que deseas descartar esta carta?");
		confirmacion.showAndWait().ifPresent(response -> {
			if (response.getText().equals("Aceptar")) {
				cartaBtn.setVisible(false);
				tablero.eliminarCarta(carta);
				cambiarTurno();
				verificarCambioDeFase();
				actualizarCartasHabilitadas();
			}
		});
	}

	private void actualizarContadores(JugadorPartida jugador, boolean esComunidad) {
		if (esComunidad) {
			oroComunidad.setText(String.valueOf(jugador.getOro()));
			fuerzaComunidad.setText(String.valueOf(jugador.getFuerza()));
			valorComunidad.setText(String.valueOf(jugador.getValor()));
			astuciaComunidad.setText(String.valueOf(jugador.getAstucia()));
			sabiduriaComunidad.setText(String.valueOf(jugador.getSabiduria()));
			coronaComunidad.setText(String.valueOf(jugador.getCorona()));
			hobbitsComunidad.setText(String.valueOf(jugador.getHobbits()));
			enanosComunidad.setText(String.valueOf(jugador.getEnanos()));
			humanosComunidad.setText(String.valueOf(jugador.getHumanos()));
			elfosComunidad.setText(String.valueOf(jugador.getElfos()));
			magosComunidad.setText(String.valueOf(jugador.getMagos()));
			entsComunidad.setText(String.valueOf(jugador.getEnts()));
		} else {
			oroMordor.setText(String.valueOf(jugador.getOro()));
			fuerzaMordor.setText(String.valueOf(jugador.getFuerza()));
			valorMordor.setText(String.valueOf(jugador.getValor()));
			astuciaMordor.setText(String.valueOf(jugador.getAstucia()));
			sabiduriaMordor.setText(String.valueOf(jugador.getSabiduria()));
			coronaMordor.setText(String.valueOf(jugador.getCorona()));
			hobbitsMordor.setText(String.valueOf(jugador.getHobbits()));
			enanosMordor.setText(String.valueOf(jugador.getEnanos()));
			humanosMordor.setText(String.valueOf(jugador.getHumanos()));
			elfosMordor.setText(String.valueOf(jugador.getElfos()));
			magosMordor.setText(String.valueOf(jugador.getMagos()));
			entsMordor.setText(String.valueOf(jugador.getEnts()));
		}
	}

	private void actualizarFondoTurno() {
		if (jugadorActual.equals(jugadorComunidad)) {
			nombreJugadorComunidad.getParent().setStyle("-fx-background-color: lightgreen;");
			nombreJugadorMordor.getParent().setStyle("-fx-background-color: transparent;");
		} else {
			nombreJugadorMordor.getParent().setStyle("-fx-background-color: lightcoral;");
			nombreJugadorComunidad.getParent().setStyle("-fx-background-color: transparent;");
		}
	}

	private void cambiarTurno() {
		if (jugadorActual.equals(jugadorComunidad)) {
			jugadorActual = jugadorMordor;
		} else {
			jugadorActual = jugadorComunidad;
		}
		verificarVictoria(); // Verificar si alguien ha ganado
		actualizarFondoTurno();
		System.out.println("Posicion Comunidad" + posicionComunidad);
		System.out.println("Posicion Sauron" + posicionSauron);

	}

	private boolean puedeRobarse(Carta carta) {
		int indexCarta = tablero.obtenerCartasDelCapituloActual().indexOf(carta);
		if (indexCarta == -1)
			return false;

		int[] posCarta = posiciones[indexCarta];

		if (posCarta[0] == 4)
			return true;

		for (int i = 0; i < posiciones.length; i++) {
			if (posiciones[i][0] == posCarta[0] + 1
					&& (posiciones[i][1] == posCarta[1] - 1 || posiciones[i][1] == posCarta[1] + 1)) {
				Carta cartaDebajo = tablero.obtenerCartasDelCapituloActual().get(i);
				if (mapaCartasBotones.get(cartaDebajo).isVisible()) {
					return false;
				}
			}
		}
		return true;
	}

	private void mostrarAlerta(String titulo, String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle(titulo);
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	private void actualizarCartasHabilitadas() {
		for (Map.Entry<Carta, Button> entry : mapaCartasBotones.entrySet()) {
			Carta carta = entry.getKey();
			Button boton = entry.getValue();
			if (boton.isVisible() && puedeRobarse(carta)) {
				boton.setDisable(false);
			}
		}
	}

	private void mostrarLugaresClaveComoBotones(List<LugarClave> lugares) {
		botonLugaresClave.getChildren().clear();
		for (LugarClave lugar : lugares) {
			Button botonLugar = new Button();
			javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(
					new javafx.scene.image.Image(getClass().getResource(lugar.getImagenRuta()).toExternalForm()));
			imagen.setFitWidth(140);
			imagen.setFitHeight(140);
			botonLugar.setGraphic(imagen);

			// tiene los recurss?
			botonLugar.setOnAction(event -> manejarRoboLugarClave(lugar, botonLugar));
			botonLugaresClave.getChildren().add(botonLugar);
		}

	}

	private void manejarRoboLugarClave(LugarClave lugar, Button botonLugar) {
		if (tablero.comprobarRecursosLugarClave(lugar, jugadorActual)) {
			// aplicar el efecto del lugar clave
			tablero.aplicarEfectoLugarClave(lugar, jugadorActual);

			// ocultar el botón del lugar clave robado
			botonLugar.setVisible(false);

			// añadir el nombre del lugar clave robado a la `ListView` correspondiente
			if (jugadorActual.equals(jugadorComunidad)) {
				lugaresClaveRobadosComunidad.getItems().add(lugar.getNombre());
			} else if (jugadorActual.equals(jugadorMordor)) {
				lugaresClaveRobadosMordor.getItems().add(lugar.getNombre());
			}

			// actualizar los contadores del jugador actual
			actualizarContadores(jugadorActual, jugadorActual.equals(jugadorComunidad));
			cambiarTurno();
		} else {
			// mostrar alerta si no tiene los recursos necesarios
			mostrarAlerta("No puedes robar este lugar clave",
					"No tienes suficientes recursos para robar este lugar clave.");
		}
	}

	private void mostrarRazasComoBotones() {
		botonRazas.getChildren().clear();
		for (Razas raza : tablero.getRazas()) {
			Button botonRaza = new Button();
			javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(
					new javafx.scene.image.Image(raza.getImagenRuta()));
			imagen.setFitWidth(60);
			imagen.setFitHeight(60);
			botonRaza.setGraphic(imagen);
			botonRaza.setOnAction(event -> {
				if (tablero.obtenerFichaDeRaza(raza.getNombre())) {
					if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0)
						botonRaza.setDisable(true);
				}
			});
			if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0)
				botonRaza.setDisable(true);
			botonRazas.getChildren().add(botonRaza);
		}
	}

	private void verificarVictoria() {
		// La Comunidad gana si llega al final del camino
		if (posicionComunidad >= 28) {
			mostrarAlerta("¡Victoria!", "La Comunidad ha llegado al final del camino y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		// Sauron gana si alcanza o supera a la Comunidad en la pista de carrera
		if (posicionSauron >= posicionComunidad) {
			mostrarAlerta("¡Derrota!", "Sauron ha alcanzado o superado a la Comunidad y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		// Gana el jugador que haya conquistado más de 3 lugares clave
		if (lugaresClaveRobadosComunidad.getItems().size() >= 3) {
			mostrarAlerta("¡Victoria!", "La Comunidad ha conquistado más de 3 lugares clave y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		if (lugaresClaveRobadosMordor.getItems().size() >= 3) {
			mostrarAlerta("¡Derrota!", "Sauron ha conquistado más de 3 lugares clave y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		// La Comunidad o Sauron ganan si tienen al menos una ficha de cada raza
		if (tieneFichasDeTodasLasRazas(jugadorComunidad)) {
			mostrarAlerta("¡Victoria!", "La Comunidad ha reunido una ficha de cada raza y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		if (tieneFichasDeTodasLasRazas(jugadorMordor)) {
			mostrarAlerta("¡Derrota!", "Sauron ha reunido una ficha de cada raza y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		System.out.println("No se cumplen condiciones de victoria. El juego continúa.");
	}

	// Método auxiliar para verificar si un jugador tiene una ficha de cada raza
	private boolean tieneFichasDeTodasLasRazas(JugadorPartida jugador) {
		return jugador.getHobbits() > 0 && jugador.getEnanos() > 0 && jugador.getHumanos() > 0 && jugador.getElfos() > 0
				&& jugador.getMagos() > 0 && jugador.getEnts() > 0;
	}

	private void finalizarJuego() {
		// Crear una alerta personalizada para el mensaje de victoria/derrota
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Fin del Juego");
		alerta.setHeaderText("¡Juego Terminado!");

		// Determinar el ganador basándonos en las condiciones del juego
		String mensajeFinal;
		if (posicionComunidad >= 28 || tieneFichasDeTodasLasRazas(jugadorComunidad)
				|| lugaresClaveRobadosComunidad.getItems().size() >= 3) {
			mensajeFinal = "🎉¡La Comunidad ha ganado la partida! 🏆\n\n";
			mensajeFinal += "¡El bien triunfa sobre el mal en la Tierra Media!🌿✨";
		} else {
			mensajeFinal = "💀¡Sauron ha ganado la partida! 🔥\n\n";
			mensajeFinal += "La oscuridad domina la Tierra Media🕳️🖤";
		}

		// Agregar el mensaje final a la alerta
		alerta.setContentText(mensajeFinal);

		// Mostrar la alerta
		alerta.showAndWait();

		// Acción guapa: Abrir ventana con animaciones o imágenes (opcional)
		mostrarPantallaFinal(mensajeFinal);

		// Salir del juego (puedes personalizar esta acción para reiniciar o regresar al
		// menú)
		System.out.println("El juego ha terminado.");
		System.exit(0); // Salir del juego (puedes reemplazar con un cambio de escena si deseas regresar
						// al menú principal)
	}

	// Método para mostrar pantalla final con diseño atractivo
	private void mostrarPantallaFinal(String mensaje) {
		Stage ventanaFinal = new Stage();
		ventanaFinal.setTitle("Resultado Final");

		// Crear un diseño visual para la ventana
		VBox diseño = new VBox(20);
		diseño.setAlignment(Pos.CENTER);
		diseño.setStyle("-fx-background-color: #333; -fx-padding: 20px;");

		Label etiquetaTitulo = new Label("Fin del Juego");
		etiquetaTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

		Label etiquetaMensaje = new Label(mensaje);
		etiquetaMensaje.setWrapText(true);
		etiquetaMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

		// Botón para cerrar la ventana
		Button botonCerrar = new Button("Cerrar");
		botonCerrar.setStyle(
				"-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
		botonCerrar.setOnAction(event -> ventanaFinal.close());

		// Agregar elementos al diseño
		diseño.getChildren().addAll(etiquetaTitulo, etiquetaMensaje, botonCerrar);

		// Mostrar la ventana
		Scene escena = new Scene(diseño, 400, 300);
		ventanaFinal.setScene(escena);
		ventanaFinal.show();
	}

}
