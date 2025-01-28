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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    private ImageView iconoOroComunidad, iconoFuerzaComunidad, iconoValorComunidad, iconoAstuciaComunidad, iconoSabiduriaComunidad, iconoCoronaComunidad;
    @FXML
    private Label hobbitsComunidad, enanosComunidad, humanosComunidad, elfosComunidad, magosComunidad, entsComunidad;
    @FXML
    private ImageView iconoHobbitsComunidad, iconoEnanosComunidad, iconoHumanosComunidad, iconoElfosComunidad, iconoMagosComunidad, iconoEntsComunidad;
    
    
    @FXML
    private Label oroMordor, fuerzaMordor, valorMordor, astuciaMordor, sabiduriaMordor, coronaMordor;
    @FXML
    private ImageView iconoOroMordor, iconoFuerzaMordor, iconoValorMordor, iconoAstuciaMordor, iconoSabiduriaMordor, iconoCoronaMordor;
    @FXML
    private Label hobbitsMordor, enanosMordor, humanosMordor, elfosMordor, magosMordor, entsMordor;
    @FXML
    private ImageView iconoHobbitsMordor, iconoEnanosMordor, iconoHumanosMordor, iconoElfosMordor, iconoMagosMordor, iconoEntsMordor;
    
    

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



    public void inicializarTablero(JugadorPartida jugadorPartida1, JugadorPartida jugadorPartida2, Tablero tablero) {
        this.jugadorActual = jugadorPartida1; //Config inicial
        this.jugadorMordor = jugadorPartida1;
        this.jugadorComunidad = jugadorPartida2;
        this.tablero = tablero;
        inicializarIconos();
        inicializarIconosRazas();
        //Actualizr nombrs de jguadores
        nombreJugadorComunidad.setText(jugadorComunidad.getJugador().getNombre());
        nombreJugadorMordor.setText(jugadorMordor.getJugador().getNombre());
        configurarTablero();
        actualizarFondoTurno();
    }
    private void inicializarIconos() {
        //JugadorComunidad
        iconoOroComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoOro.png").toExternalForm()));
        iconoFuerzaComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoFuerza.png").toExternalForm()));
        iconoValorComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoValor.png").toExternalForm()));
        iconoAstuciaComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoAstucia.png").toExternalForm()));
        iconoSabiduriaComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoSabiduria.png").toExternalForm()));
        iconoCoronaComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/IconoCorona.png").toExternalForm()));

        //JugadorMordor
        iconoOroMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoOro.png").toExternalForm()));
        iconoFuerzaMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoFuerza.png").toExternalForm()));
        iconoValorMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoValor.png").toExternalForm()));
        iconoAstuciaMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoAstucia.png").toExternalForm()));
        iconoSabiduriaMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoSabiduria.png").toExternalForm()));
        iconoCoronaMordor.setImage(new Image(getClass().getResource("/Images/Fichas/IconoCorona.png").toExternalForm()));
    }
    private void inicializarIconosRazas() {
        //JugadorComunidad
        iconoHobbitsComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
        iconoEnanosComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
        iconoHumanosComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
        iconoElfosComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
        iconoMagosComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
        iconoEntsComunidad.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));

        //JugadorMordor
        iconoHobbitsMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
        iconoEnanosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
        iconoHumanosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
        iconoElfosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
        iconoMagosMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
        iconoEntsMordor.setImage(new Image(getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));
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

        //Colocar todas las cartas enpirámide
        for (int i = 0; i < posicionesActuales.length; i++) {
            if (i >= cartasFaseActual.size()) break;
            Carta carta = cartasFaseActual.get(i);
            Button cartaBtn = crearBotonCarta(carta, i < 14); // Todas excepto la última fila están bloqueadas
            int row = posicionesActuales[i][0];
            int col = posicionesActuales[i][1];
            tableroCentral.add(cartaBtn, col, row);
            mapaCartasBotones.put(carta, cartaBtn);
        }

        //Config lugares clave y razas
        tablero.mezclarLugaresClave();
        List<LugarClave> seleccionados = tablero.seleccionarTresLugaresClave();
        mostrarLugaresClaveComoBotones(seleccionados);
        mostrarRazasComoBotones();
    }

    private Button crearBotonCarta(Carta carta, boolean bloqueada) {
        Button cartaBtn = new Button();
        cartaBtn.setStyle("-fx-padding: 4; -fx-border-color: black; -fx-border-radius: 4;");
        javafx.scene.image.ImageView imagen = new javafx.scene.image.ImageView(new javafx.scene.image.Image(getClass().getResource(carta.getImagenRuta()).toExternalForm()));
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

        //opcion para descartar carta
        cartaBtn.setOnContextMenuRequested(e -> {
            descartarCarta(carta, cartaBtn);
        });

        return cartaBtn;
    }


    private void verificarCambioDeFase() {
        boolean todasCartasJugadas = mapaCartasBotones.values().stream().noneMatch(Button::isVisible);

        if (todasCartasJugadas) {
            //limpia
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
        actualizarFondoTurno();
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

                // tiene los recurss?
             botonLugar.setOnAction(event -> manejarRoboLugarClave(lugar, botonLugar));
            botonLugaresClave.getChildren().add(botonLugar);
            }
      
    }
      private void manejarRoboLugarClave(LugarClave lugar, Button botonLugar) {
            if (tablero.comprobarRecursosLugarClave(lugar, jugadorActual)) {
                //aplicar el efecto del lugar clave
                tablero.aplicarEfectoLugarClave(lugar, jugadorActual);

                //ocultar el botón del lugar clave robado
                botonLugar.setVisible(false);

                //añadir el nombre del lugar clave robado a la `ListView` correspondiente
                if (jugadorActual.equals(jugadorComunidad)) {
                    lugaresClaveRobadosComunidad.getItems().add(lugar.getNombre());
                } else if (jugadorActual.equals(jugadorMordor)) {
                    lugaresClaveRobadosMordor.getItems().add(lugar.getNombre());
                }

                //actualizar los contadores del jugador actual
                actualizarContadores(jugadorActual, jugadorActual.equals(jugadorComunidad));
                cambiarTurno();
            } else {
                //mostrar alerta si no tiene los recursos necesarios
                mostrarAlerta("No puedes robar este lugar clave", "No tienes suficientes recursos para robar este lugar clave.");
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
