package Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Clases.Carta;
import Clases.LugarClave;
import Clases.Razas;
import Clases.Tablero;
import javafx.fxml.FXML;
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
    private void mostrarLugaresClaveComoBotones(List<LugarClave> lugares) {
    	botonLugaresClave.getChildren().clear(); // Limpiar los botones previos

        for (LugarClave lugar : lugares) {
            // Crear un botón para cada lugar clave
            Button botonLugar = new Button();
            botonLugar.setText(""); 
            botonLugar.setMinSize(120, 120); // Tamaño mínimo del botón
            botonLugar.setPrefSize(140, 140); // Tamaño preferido del botón
            botonLugar.setMaxSize(140, 140); // Tamaño máximo del botón
            botonLugar.setStyle("-fx-padding: 10;");
            
            // Agregar imagen al botón
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(getClass().getResource(lugar.getImagenRuta()).toExternalForm()));
            imagen.setFitWidth(140); // Ajustar ancho de la imagen
            imagen.setFitHeight(140); // Ajustar alto de la imagen
            botonLugar.setGraphic(imagen);

            // Configurar acción para el botón
            botonLugar.setOnAction(event -> {
                System.out.println("Lugar seleccionado: " + lugar.getNombre());
                // Implementar lógica para manejar la selección del lugar clave
            });

            // Agregar el botón al HBox
            botonLugaresClave.getChildren().add(botonLugar);
        }
    }
    

    private void mostrarRazasComoBotones() {
        botonRazas.getChildren().clear(); // Limpiar los botones previos

        for (Razas raza : tablero.getRazas()) {
            // Crear un botón para cada raza
            Button botonRaza = new Button();
            botonRaza.setStyle("-fx-padding: 10; -fx-font-size: 14px; -fx-font-weight: bold;");
            
            // Agregar imagen al botón
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(raza.getImagenRuta()));
            imagen.setFitWidth(60);
            imagen.setFitHeight(60);
            botonRaza.setGraphic(imagen);

            // Configurar acción del botón
            botonRaza.setOnAction(event -> {
                if (tablero.obtenerFichaDeRaza(raza.getNombre())) {
                    System.out.println("Ficha obtenida: " + raza.getNombre());
                    if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0) {
                        botonRaza.setDisable(true); // Deshabilitar botón si no quedan fichas
                    }
                } else {
                    System.out.println("No quedan fichas de: " + raza.getNombre());
                }
            });

            // Deshabilitar botón si no quedan fichas
            if (tablero.consultarFichasDeRaza(raza.getNombre()) == 0) {
                botonRaza.setDisable(true);
            }

            botonRazas.getChildren().add(botonRaza); // Agregar el botón al HBox
        }
    }
}


