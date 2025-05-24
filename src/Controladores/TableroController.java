package Controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Clases.Carta;
import Clases.DueloClient;
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
import java.util.logging.Logger;


public class TableroController {
	 private static final Logger logger = Logger.getLogger(TableroController.class.getName());

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
	private int posicionComunidad = 14; 
	private DueloClient cliente;
	


	private void moverFicha(int posicionActual, int cantidad, String colorFicha) {
	    int nuevaPosicion = posicionActual + cantidad;

	    if (colorFicha.equals("red") && (posicionSauron + cantidad) >= posicionComunidad) {
	        posicionSauron = posicionComunidad;
	        verificarVictoria();
	        return;
	    }
	    
	    if (colorFicha.equals("blue") && (posicionComunidad + cantidad) >= 29) {
	        posicionComunidad = 29;
	        verificarVictoria();
	        return;
	    }

	    if (nuevaPosicion >= 0 && nuevaPosicion < pistaCarrera.getChildren().size()) {
	        Label labelActual = (Label) pistaCarrera.getChildren().get(posicionActual);
	        labelActual.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-border-color: black;");

	        posicionActual = nuevaPosicion;

	        Label nuevoLabel = (Label) pistaCarrera.getChildren().get(posicionActual);
	        nuevoLabel.setStyle("-fx-border-color: " + colorFicha + "; -fx-background-color: " + obtenerColorFondoFicha(colorFicha) + "; -fx-padding: 10;");

	        logger.info("Ficha (" + colorFicha + ") se movió a la posición: " + (posicionActual + 1));
	    } else {
	        logger.info("Movimiento fuera de rango para la ficha (" + colorFicha + ").");
	    }

	    if (colorFicha.equals("red")) {
	        posicionSauron = posicionActual;
	    } else if (colorFicha.equals("blue")) {
	        posicionComunidad = posicionActual;
	    }

	    verificarVictoria();
	}
	private TableroNetworkController redController;
	
	public void setCliente(DueloClient cliente) {
	    this.cliente = cliente;
	}
	
	public void setRedController(TableroNetworkController redController) {
	    this.redController = redController;
	    
	}
	
	private void procesarMovimientoPorCarta(Carta carta) {
	    String efecto = carta.getEfecto();

	    if (efecto.startsWith("avance+")) {
	        int avance = Integer.parseInt(efecto.replace("avance+", ""));

	        if (jugadorActual.equals(jugadorComunidad)) {
	            if (posicionComunidad + avance >= 29) {
	                posicionComunidad = 29;
	                verificarVictoria();
	                return;
	            }
	            moverFicha(posicionComunidad, avance, "blue");
	            if (posicionSauron + avance >= posicionComunidad) {
	                posicionSauron = posicionComunidad;
	                verificarVictoria();
	                return;
	            }
	            moverFicha(posicionSauron, avance, "red");
	        } else if (jugadorActual.equals(jugadorMordor)) {
	            if (posicionSauron + avance >= posicionComunidad) {
	                posicionSauron = posicionComunidad;
	                verificarVictoria();
	                return;
	            }
	            moverFicha(posicionSauron, avance, "red");
	        }
	    } else {
	        logger.info("La carta no afecta el movimiento de las fichas.");
	    }
	}
	private String obtenerColorFondoFicha(String colorFicha) {
	    if (colorFicha.equals("red")) {
	        return "pink"; // Color de fondo para Sauron
	    } else if (colorFicha.equals("blue")) {
	        return "green"; // Color de fondo para La Comunidad
	    } else {
	        return "yellow"; // Color por defecto para otras fichas
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
		//conexi´n de red
		//this.redController = new TableroNetworkController();
		if (this.redController != null) {
		    this.redController.setTablero(tablero);
		    this.redController.setTableroController(this);
		}


	}
	
	private Image cargarImagenIconos(String ruta, double ancho, double alto) {
	    return new Image(getClass().getResource(ruta).toExternalForm(), ancho, alto, true, true);
	}

	private void inicializarIconos() {
		//JugadorComunidad
	    iconoOroComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoOro.png", 30, 30));
	    iconoFuerzaComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoFuerza.png", 30, 30));
	    iconoValorComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoValor.png", 30, 30));
	    iconoAstuciaComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoAstucia.png", 30, 30));
	    iconoSabiduriaComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoSabiduria.png", 30, 30));
	    iconoCoronaComunidad.setImage(cargarImagenIconos("/Images/Fichas/IconoCorona.png", 30, 30));

	    //Jugadr Mordor
	    iconoOroMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoOro.png", 30, 30));
	    iconoFuerzaMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoFuerza.png", 30, 30));
	    iconoValorMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoValor.png", 30, 30));
	    iconoAstuciaMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoAstucia.png", 30, 30));
	    iconoSabiduriaMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoSabiduria.png", 30, 30));
	    iconoCoronaMordor.setImage(cargarImagenIconos("/Images/Fichas/IconoCorona.png", 30, 30));
	}

	private void inicializarIconosRazas() {
	    //Jugador Comunidad
	    iconoHobbitsComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaHobbits.png", 40, 40));
	    iconoEnanosComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaEnanos.png", 40, 40));
	    iconoHumanosComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaHumanos.png", 40, 40));
	    iconoElfosComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaElfos.png", 40, 40));
	    iconoMagosComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaMagos.png", 40, 40));
	    iconoEntsComunidad.setImage(cargarImagenIconos("/Images/Fichas/FichaEnts.png", 40, 40));

	    //Jugador Mordor
	    iconoHobbitsMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaHobbits.png", 40, 40));
	    iconoEnanosMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaEnanos.png", 40, 40));
	    iconoHumanosMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaHumanos.png", 40, 40));
	    iconoElfosMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaElfos.png", 40, 40));
	    iconoMagosMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaMagos.png", 40, 40));
	    iconoEntsMordor.setImage(cargarImagenIconos("/Images/Fichas/FichaEnts.png", 40, 40));
	}

	private void configurarTablero() {
		tableroCentral.getChildren().clear();
		botonLugaresClave.getChildren().clear();
		mapaCartasBotones.clear();

		//Obtener cartas de la fase actual
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
		//mostrarRazasComoBotones();
	}

	private Button crearBotonCarta(Carta carta, boolean bloqueada) {
		Button cartaBtn = new Button();
		cartaBtn.setStyle("-fx-padding: 4; -fx-border-color: black; -fx-border-radius: 4;");
		//antigua manera de geenrar cartas, se prueba otra para comprobar si mejora rendimiento
		//javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm()));
		//imageView.setFitWidth(70);
		//imageView.setFitHeight(110);
		javafx.scene.image.Image imagen = new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm(),70, 110, true, true);
	    ImageView imageView = new ImageView(imagen);
	    cartaBtn.setGraphic(imageView);

		cartaBtn.setGraphic(imageView);

		// configuración inicial
		cartaBtn.setDisable(bloqueada);
		cartaBtn.setOnAction(e -> {
			if (!puedeRobarse(carta)) {
			      mostrarAlerta("No puedes robar esta carta", "Debes desbloquear primero las cartas inferiores.");
			      return;
			  }

			 if (redController != null) {
				 	redController.robarCartaDesdeCliente(carta.getId());
			    } else {
			        procesarCartaRobada(carta);
			    }
			
			/* Prueba control de carta por servidor
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
				verificarCambioDeFase(); 
				cambiarTurno();
				actualizarCartasHabilitadas();
			} else {
				mostrarAlerta("No puedes robar esta carta",
						"Debes cumplir los requisitos o desbloquear las cartas inferiores.");
			}*/
		});

		// opcion para descartar carta
		cartaBtn.setOnContextMenuRequested(e -> {
			descartarCarta(carta, cartaBtn);
		});

		return cartaBtn;
	}
	
	public void procesarCartaRobada(Carta carta) {
	    Button boton = mapaCartasBotones.get(carta);
	    if (boton != null) {
	        boton.setVisible(false);
	    }

	    if (jugadorActual.equals(jugadorComunidad)) {
	        cartasJugador1.getItems().add(carta.getNombre());
	    } else {
	        cartasJugador2.getItems().add(carta.getNombre());
	    }

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
	    verificarCambioDeFase();
	    cambiarTurno();
	    actualizarCartasHabilitadas();
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
		verificarVictoria();
		actualizarFondoTurno();
		logger.info("Posicion Comunidad" + posicionComunidad);
		logger.info("Posicion Sauron" + posicionSauron);

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
			botonLugar.setStyle("-fx-padding: 4; -fx-border-color: black; -fx-border-radius: 4;");
			/*javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(getClass().getResource(lugar.getImagenRuta()).toExternalForm()));
			imagen.setFitWidth(110);
			imagen.setFitHeight(110);*/
	        javafx.scene.image.Image imagen = new javafx.scene.image.Image(getClass().getResource(lugar.getImagenRuta()).toExternalForm(),110, 110, true, true);
	        ImageView imageView = new ImageView(imagen);
	        botonLugar.setGraphic(imageView);

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

	/*Se comenta este método para futuras versiones
	 * 
	 * private void mostrarRazasComoBotones() {
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
	}*/

	private void verificarVictoria() {
		if (posicionComunidad >= 28) {
			mostrarAlerta("¡Victoria!", "La Comunidad ha llegado al final del camino y ha ganado la partida.");
			finalizarJuego();
			return;
		}

		if (posicionSauron >= posicionComunidad) {
			mostrarAlerta("¡Derrota!", "Sauron ha alcanzado o superado a la Comunidad y ha ganado la partida.");
			finalizarJuego();
			return;
		}

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

		logger.info("No se cumplen condiciones de victoria. El juego continúa.");
	}

	private boolean tieneFichasDeTodasLasRazas(JugadorPartida jugador) {
		return jugador.getHobbits() > 0 && jugador.getEnanos() > 0 && jugador.getHumanos() > 0 && jugador.getElfos() > 0
				&& jugador.getMagos() > 0 && jugador.getEnts() > 0;
	}

	private void finalizarJuego() {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Fin del Juego");
		alerta.setHeaderText("¡Juego Terminado!");

		String mensajeFinal;
		if (posicionComunidad >= 28 || tieneFichasDeTodasLasRazas(jugadorComunidad)
				|| lugaresClaveRobadosComunidad.getItems().size() >= 3) {
			mensajeFinal = "¡La Comunidad ha ganado la partida!";
			mensajeFinal += "¡El bien triunfa sobre el mal en la Tierra Media!";
		} else {
			mensajeFinal = "¡Sauron ha ganado la partida!";
			mensajeFinal += "La oscuridad domina la Tierra Media";
		}

		alerta.setContentText(mensajeFinal);

		alerta.showAndWait();

		mostrarPantallaFinal(mensajeFinal);


		logger.info("El juego ha terminado.");
		System.exit(0); 
	}

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
