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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TableroController {

    @FXML
    private GridPane tableroCentral;

    @FXML
    private ListView<String> cartasJugador1;

    @FXML
    private ListView<String> cartasJugador2;

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
    private Label hobbitsComunidad, enanosComunidad, humanosComunidad, elfosComunidad, magosComunidad, entsComunidad;

    @FXML
    private Label oroMordor, fuerzaMordor, valorMordor, astuciaMordor, sabiduriaMordor, coronaMordor;
    @FXML
    private Label hobbitsMordor, enanosMordor, humanosMordor, elfosMordor, magosMordor, entsMordor;

    private JugadorPartida jugadorActual;
    private JugadorPartida jugadorComunidad;
    private JugadorPartida jugadorMordor;

    private final int[][] posiciones = {
            {0, 5}, {0, 7},
            {1, 4}, {1, 6}, {1, 8},
            {2, 3}, {2, 5}, {2, 7}, {2, 9},
            {3, 2}, {3, 4}, {3, 6}, {3, 8}, {3, 10},
            {4, 1}, {4, 3}, {4, 5}, {4, 7}, {4, 9}, {4, 11}
    };
    
    private final int[][] posicionesFase2 = {
            {4, 1}, {4, 3}, {4, 5}, {4, 7}, {4, 9}, {4, 11},
            {3, 2}, {3, 4}, {3, 6}, {3, 8}, {3, 10},
            {2, 3}, {2, 5}, {2, 7}, {2, 9},
            {1, 4}, {1, 6}, {1, 8},
            {0, 5}, {0, 7}
    };
    
    private final int[][] posicionesFase3 = {
            {0, 5}, {0, 7},
            {1, 4}, {1, 6}, {1, 8},
            {2, 3}, {2, 5}, {2, 7}, {2, 9},
            {3, 2}, {3, 4}, {3, 6}, {3, 8}, {3, 10},
            {4, 1}, {4, 3}, {4, 5}, {4, 7}, {4, 9}, {4, 11}
    };
    
    
    
    
    public void inicializarTablero(JugadorPartida jugadorPartida1, JugadorPartida jugadorPartida2, Tablero tablero) {
        this.jugadorActual = jugadorPartida1; // Configurar el jugador actual como jugador de la Comunidad al inicio
        this.jugadorComunidad = jugadorPartida1;
        this.jugadorMordor = jugadorPartida2;
        this.tablero = tablero;

        // Actualizar los nombres de los jugadores
        nombreJugadorComunidad.setText(jugadorComunidad.getJugador().getNombre());
        nombreJugadorMordor.setText(jugadorMordor.getJugador().getNombre());

        // Configurar el tablero de juego (por ejemplo, las cartas y lugares clave)
        configurarTablero();
    }

    private void configurarTablero() {
        // Obtener las cartas de la fase actual
        List<Carta> cartasFaseActual = tablero.obtenerCartasDelCapituloActual();
        
        // Asegurarse de que hay suficientes cartas para construir la pirámide
        if (cartasFaseActual.size() < posiciones.length) {
            throw new IllegalStateException("No hay suficientes cartas para construir la pirámide de esta fase.");
        }

        // Colocar todas las cartas en la pirámide
        for (int i = 0; i < posiciones.length; i++) {
            Carta carta = cartasFaseActual.get(i);
            Button cartaBtn = crearBotonCarta(carta, i < 14); // Todas excepto la última fila están bloqueadas
            int row = posiciones[i][0];
            int col = posiciones[i][1];
            tableroCentral.add(cartaBtn, col, row);
            mapaCartasBotones.put(carta, cartaBtn);
        }

        // Configurar lugares clave y razas
        tablero.mezclarLugaresClave();
        List<LugarClave> seleccionados = tablero.seleccionarTresLugaresClave();
        mostrarLugaresClaveComoBotones(seleccionados);
        mostrarRazasComoBotones();
    }

    private Button crearBotonCarta(Carta carta, boolean bloqueada) {
        Button cartaBtn = new Button();

        // Configurar el estilo del botón
        cartaBtn.setStyle("-fx-padding: 5; -fx-border-color: black; -fx-border-radius: 5;");

        // Cargar la imagen directamente
        javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(
                new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm())
        );
        imagen.setFitWidth(90);
        imagen.setFitHeight(140);
        cartaBtn.setGraphic(imagen);

        // Configurar el estado inicial del botón
        cartaBtn.setDisable(bloqueada);
        cartaBtn.setOnAction(e -> {
            if (!tablero.comprobarRecursosFase1(carta, jugadorActual)) {
                mostrarAlerta("No puedes robar esta carta", "No tienes suficientes recursos para robar esta carta.");
                return;
            }

            if (puedeRobarse(carta)) {
                if (jugadorActual.equals(jugadorComunidad)) {
                    cartasJugador1.getItems().add(carta.getNombre());
                } else {
                    cartasJugador2.getItems().add(carta.getNombre());
                }
                cartaBtn.setVisible(false); // Ocultar el botón después de seleccionarlo
                tablero.aplicarEfectoCartaFase1(carta, jugadorActual); // Aplica el efecto según la fase
                actualizarContadores(jugadorActual, jugadorActual.equals(jugadorComunidad));
                cambiarTurno();
                actualizarCartasHabilitadas();
            } else {
                mostrarAlerta("No puedes robar esta carta", "Debes cumplir los requisitos o desbloquear las cartas inferiores.");
            }
        });

        return cartaBtn;
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

    private void cambiarTurno() {
        if (jugadorActual.equals(jugadorComunidad)) {
            jugadorActual = jugadorMordor;
        } else {
            jugadorActual = jugadorComunidad;
        }
    }

    private boolean puedeRobarse(Carta carta) {
        int indexCarta = tablero.obtenerCartasDelCapituloActual().indexOf(carta);
        if (indexCarta == -1) return false;

        int[] posCarta = posiciones[indexCarta];

        if (posCarta[0] == 4) return true;

        for (int i = 0; i < posiciones.length; i++) {
            if (posiciones[i][0] == posCarta[0] + 1 &&
                (posiciones[i][1] == posCarta[1] - 1 || posiciones[i][1] == posCarta[1] + 1)) {
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
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(getClass().getResource(lugar.getImagenRuta()).toExternalForm()));
            imagen.setFitWidth(140);
            imagen.setFitHeight(140);
            botonLugar.setGraphic(imagen);
            botonLugar.setOnAction(event -> System.out.println("Lugar seleccionado: " + lugar.getNombre()));
            botonLugaresClave.getChildren().add(botonLugar);
        }
    }

    private void mostrarRazasComoBotones() {
        botonRazas.getChildren().clear();
        for (Razas raza : tablero.getRazas()) {
            Button botonRaza = new Button();
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(raza.getImagenRuta()));
            imagen.setFitWidth(60);
            imagen.setFitHeight(60);
            botonRaza.setGraphic(imagen);
            botonRaza.setOnAction(event -> {
                if (tablero.obtenerFichaDeRaza(raza.getNombre())) {
                    if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0) botonRaza.setDisable(true);
                }
            });
            if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0) botonRaza.setDisable(true);
            botonRazas.getChildren().add(botonRaza);
        }
    }
}
