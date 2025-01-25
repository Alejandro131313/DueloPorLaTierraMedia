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
 // Lista y mapa para las razas
    private List<Razas> razas;
    private Map<Razas, Integer> fichasDisponibles; // Controlar las fichas restantes
    
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
        initializeRazas();
    }
    

    private Button crearBotonCarta(Carta carta, boolean bloqueada) {
        Button cartaBtn = new Button("Carta " + carta.getId());
        // Estilo del botón
        cartaBtn.setStyle("-fx-padding: 5; -fx-border-color: black; -fx-border-radius: 5;");

        try {
            // Depurar la ruta
            System.out.println("Intentando cargar imagen: " + carta.getImagenRuta());
            System.out.println("Ruta absoluta: " + getClass().getResource(carta.getImagenRuta()));
            if (getClass().getResource(carta.getImagenRuta()) == null) {
                throw new NullPointerException("Recurso no encontrado: " + carta.getImagenRuta());
            }

            // Cargar la imagen
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(
                    new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm())
            );
            imagen.setFitWidth(90);
            imagen.setFitHeight(140);
            cartaBtn.setGraphic(imagen);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen para carta: " + carta.getNombre());
            e.printStackTrace();
            cartaBtn.setText(carta.getNombre()); // Mostrar el nombre como fallback
        }
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
    
    // Inicializar y mostrar razas
    private void initializeRazas() {
        // Inicializar lista de razas
        razas = new ArrayList<>();
        razas.add(new Razas(1, "Hobbits", getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
        razas.add(new Razas(2, "Enanos", getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
        razas.add(new Razas(3, "Humanos", getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
        razas.add(new Razas(4, "Elfos", getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
        razas.add(new Razas(5, "Magos", getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
        razas.add(new Razas(6, "Ents", getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));

        // Inicializar mapa de fichas disponibles
        fichasDisponibles = new HashMap<>();
        for (Razas raza : razas) {
            fichasDisponibles.put(raza, 3); // Cada raza tiene 3 fichas inicialmente
        }

        // Mostrar botones para las razas
        mostrarRazasComoBotones();
    }

    private void mostrarRazasComoBotones() {
        botonRazas.getChildren().clear(); // Limpiar los botones previos

        for (Razas raza : razas) {
            // Crear un botón para cada raza
            Button botonRaza = new Button(raza.getNombre());
            botonRaza.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10;");
            javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(raza.getImagenRuta()));
            imagen.setFitWidth(50); // Ajustar ancho de la imagen
            imagen.setFitHeight(50); // Ajustar alto de la imagen
            botonRaza.setGraphic(imagen);
            // Configurar acción para el botón
            botonRaza.setOnAction(event -> {
                int fichasRestantes = fichasDisponibles.get(raza);
                if (fichasRestantes > 0) {
                    fichasDisponibles.put(raza, fichasRestantes - 1); // Reducir la cantidad disponible
                    System.out.println("Jugador obtuvo ficha de " + raza.getNombre() + ". Fichas restantes: " + (fichasRestantes - 1));

                    // Deshabilitar el botón si ya no quedan fichas
                    if (fichasDisponibles.get(raza) == 0) {
                        botonRaza.setDisable(true);
                    }
                }
            });

            // Deshabilitar botón si no hay fichas disponibles
            if (fichasDisponibles.get(raza) == 0) {
                botonRaza.setDisable(true);
            }

            // Agregar el botón al HBox
            botonRazas.getChildren().add(botonRaza);
        }
    }
}


