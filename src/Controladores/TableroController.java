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
    
    
    
    @FXML
    private HBox pistaCarrera;

    private int posicionSauron = 0; 
    private int posicionComunidad = 14; 
    
    private void moverFicha(int posicionActual, int cantidad, String colorFicha) {
        int nuevaPosicion = posicionActual + cantidad;


        if (nuevaPosicion >= 0 && nuevaPosicion < pistaCarrera.getChildren().size()) {
           
            Label labelActual = (Label) pistaCarrera.getChildren().get(posicionActual);
            labelActual.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-border-color: black;");

           
            posicionActual = nuevaPosicion;

        
            Label nuevoLabel = (Label) pistaCarrera.getChildren().get(posicionActual);
            nuevoLabel.setStyle("-fx-border-color: " + colorFicha + "; -fx-background-color: " + obtenerColorFondoFicha(colorFicha) + "; -fx-padding: 10;");

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
           
            moverFicha(posicionSauron, avance, "red");
            moverFicha(posicionComunidad, avance, "blue");
        } else if (efecto.equals("oro+1")) {
            
            moverFicha(posicionSauron, 1, "red");
        } else {
            System.out.println("La carta no afecta el movimiento de las fichas.");
        }
    }

    private final int[][] posiciones = {
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
        
        Label labelSauron = (Label) pistaCarrera.getChildren().get(posicionSauron);
        labelSauron.setStyle("-fx-border-color: red; -fx-background-color:pink; -fx-padding: 10;");
        

        Label labelComunidad = (Label) pistaCarrera.getChildren().get(posicionComunidad);
        labelComunidad.setStyle("-fx-border-color: blue; -fx-background-color: green; -fx-padding: 10;");
        

        // Actualizar los nombres de los jugadores
        nombreJugadorComunidad.setText(jugadorComunidad.getJugador().getNombre());
        nombreJugadorMordor.setText(jugadorMordor.getJugador().getNombre());

        // Configurar el tablero de juego (por ejemplo, las cartas y lugares clave)
        configurarTablero();
    }

    private void configurarTablero() {
        tableroCentral.getChildren().clear();
        mapaCartasBotones.clear();

        // Obtener las cartas de la fase actual
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

        // Colocar todas las cartas en la pirámide
        for (int i = 0; i < posicionesActuales.length; i++) {
            if (i >= cartasFaseActual.size()) break;
            Carta carta = cartasFaseActual.get(i);
            Button cartaBtn = crearBotonCarta(carta, i < 14); // Todas excepto la última fila están bloqueadas
            int row = posicionesActuales[i][0];
            int col = posicionesActuales[i][1];
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
            // Verificar si el jugador cumple los requisitos de la carta antes de robar
            boolean recursosSuficientes = false;
            switch (tablero.getCapitulo()) {
                case "fase1":
                    recursosSuficientes = tablero.comprobarRecursosFase1(carta, jugadorActual);
                    procesarMovimientoPorCarta(carta);
                    break;
                case "fase2":
                    recursosSuficientes = tablero.comprobarRecursosFase2(carta, jugadorActual);
                    procesarMovimientoPorCarta(carta);
                    break;
                case "fase3":
                    recursosSuficientes = tablero.comprobarRecursosFase3(carta, jugadorActual);
                    procesarMovimientoPorCarta(carta);
                    break;
            }

            if (!recursosSuficientes) {
                mostrarAlerta("No puedes robar esta carta", "No tienes suficientes recursos para robar esta carta.");
                return;
            }

            // Si se puede robar según las cartas inferiores
            if (puedeRobarse(carta)) {
                if (jugadorActual.equals(jugadorComunidad)) {
                    cartasJugador1.getItems().add(carta.getNombre());
                } else {
                    cartasJugador2.getItems().add(carta.getNombre());
                }
                cartaBtn.setVisible(false); // Ocultar el botón después de seleccionarlo

                // Aplicar el efecto correspondiente a la fase actual
                switch (tablero.getCapitulo()) {
                    case "fase1":
                        tablero.aplicarEfectoCartaFase1(carta, jugadorActual);
                        break;
                    case "fase2":
                        tablero.aplicarEfectoCartaFase2(carta, jugadorActual);
                        break;
                    case "fase3":
                        tablero.aplicarEfectoCartaFase3(carta, jugadorActual);
                        break;
                }

                actualizarContadores(jugadorActual, jugadorActual.equals(jugadorComunidad));
                verificarCambioDeFase(); // Verificar si todas las cartas han sido jugadas
                cambiarTurno();
                actualizarCartasHabilitadas();
            } else {
                mostrarAlerta("No puedes robar esta carta", "Debes cumplir los requisitos o desbloquear las cartas inferiores.");
            }
        });

        // Agregar opción para descartar la carta
        cartaBtn.setOnContextMenuRequested(e -> {
            descartarCarta(carta, cartaBtn);
        });

        return cartaBtn;
    }


    private void verificarCambioDeFase() {
        boolean todasCartasJugadas = mapaCartasBotones.values().stream().noneMatch(Button::isVisible);

        if (todasCartasJugadas) {
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
        // Confirmar si se desea descartar la carta
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Descartar carta");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Estás seguro de que deseas descartar esta carta?");
        confirmacion.showAndWait().ifPresent(response -> {
            if (response.getText().equals("Aceptar")) {
                // Ocultar la carta descartada
                cartaBtn.setVisible(false);
                // Eliminar la carta del tablero
                tablero.eliminarCarta(carta);
                // Cambiar de turno
                cambiarTurno();
                // Actualizar las cartas habilitadas
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
