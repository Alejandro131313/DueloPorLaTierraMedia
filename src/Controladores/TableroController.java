package Controladores;

import java.util.HashMap;
import java.util.Map;

import Clases.Carta;
import Clases.Tablero;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

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
    private Label nombreJugadorComunidad;

    @FXML
    private Label nombreJugadorMordor;

    private final int[][] posiciones = {
            {0, 5}, {0, 7},
            {1, 4}, {1, 6}, {1, 8},
            {2, 3}, {2, 5}, {2, 7}, {2, 9},
            {3, 2}, {3, 4}, {3, 6}, {3, 8}, {3, 10},
            {4, 1}, {4, 3}, {4, 5}, {4, 7}, {4, 9}, {4, 11}
    };

    public void setNombresJugadores(String comunidad, String mordor) {
        nombreJugadorComunidad.setText(comunidad);
        nombreJugadorMordor.setText(mordor);
    }

    public void initialize() {
        // Inicializar el tablero con las cartas de la fase 1
        tablero = new Tablero(1, "Disposición inicial", "fase1");

        // Colocar todas las cartas en la pirámide
        for (int i = 0; i < posiciones.length; i++) {
            Carta carta = tablero.getCartasCapitulo1().get(i);
            Button cartaBtn = crearBotonCarta(carta, i < 14); // Todas excepto la última fila están bloqueadas
            int row = posiciones[i][0];
            int col = posiciones[i][1];
            tableroCentral.add(cartaBtn, col, row);
            mapaCartasBotones.put(carta, cartaBtn);
        }
    }

    private Button crearBotonCarta(Carta carta, boolean bloqueada) {
        Button cartaBtn = new Button("Carta " + carta.getId());
        cartaBtn.setDisable(bloqueada); // Bloquear si no está habilitada inicialmente
        cartaBtn.setOnAction(e -> {
            System.out.println("Carta seleccionada: " + carta.getNombre());
            cartasJugador1.getItems().add(carta.getNombre());
            cartaBtn.setVisible(false); // Ocultar el botón después de seleccionarlo
            actualizarCartasHabilitadas();
        });
        return cartaBtn;
    }

    private boolean puedeRobarse(Carta carta) {
        // Obtener posición de la carta
        int indexCarta = tablero.getCartasCapitulo1().indexOf(carta);
        if (indexCarta == -1) return false; // Si no está en la pirámide, no se puede robar

        int[] posCarta = posiciones[indexCarta];

        // Si está en la última fila, siempre puede ser robada
        if (posCarta[0] == 4) {
            return true;
        }

        // Verificar las dos cartas directamente debajo
        int cartasDebajo = 0;
        for (int i = 0; i < posiciones.length; i++) {
            if (posiciones[i][0] == posCarta[0] + 1 &&
                (posiciones[i][1] == posCarta[1] - 1 || posiciones[i][1] == posCarta[1] + 1)) {
                Carta cartaDebajo = tablero.getCartasCapitulo1().get(i);
                if (mapaCartasBotones.get(cartaDebajo).isVisible()) {
                    return false; // Si alguna carta debajo no ha sido robada, no puede ser robada
                }
                cartasDebajo++;
            }
        }

        return cartasDebajo == 2; // Puede robarse si ambas cartas debajo están robadas
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
}

