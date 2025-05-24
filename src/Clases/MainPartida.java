package Clases;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainPartida {
	
    private static final Logger LOGGER = Logger.getLogger(MainPartida.class.getName());
    public static void main(final String[] args) {
    	
        
    	System.setProperty("prism.order", "sw");
    	// Crear jugadores
        final Jugador jugador1 = new Jugador(1, "Pedro", 30, 0);
        final Jugador jugador2 = new Jugador(2, "Paco", 25, 0);

        // Crear un tablero
        final Tablero tablero = new Tablero(1, "Disposición inicial", "fase1");

        // Crear jugador-partida
        final JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, null, 0, "Sauron", new ArrayList<>(), null, null);
        final JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, null, 0, "Comunidad", new ArrayList<>(), null, null);

        // Crear la partida
        final Partida partida = new Partida(1, "10:00", "2025-01-10", tablero);

        // Asociar la partida a los JugadorPartida
        jugadorPartida1.setPartida(partida);
        jugadorPartida2.setPartida(partida);

        // Agregar los jugadores-partida a la partida
        partida.agregarJugador(jugador1);
        partida.agregarJugador(jugador2);

        // Confirmar la creación
        LOGGER.info("Partida creada con los jugadores:");
        LOGGER.info("-----------------------------------");
        LOGGER.info("Nombre del Jugador | Facción | Oro");
        LOGGER.info("-----------------------------------");

        for (final Jugador jugador : partida.getJugadores()) {
            final JugadorPartida jugPar = jugador.getId() == 1 ? jugadorPartida1 : jugadorPartida2;
            final String linea = String.format("%-18s | %-8s | %-3d", jugador.getNombre(), jugPar.getFaccion(), jugPar.getOro());
            LOGGER.info(linea);
        }
        // Inicializar unidades (7 torres y 15 soldados por jugador)
        jugadorPartida1.inicializarUnidades();
        jugadorPartida2.inicializarUnidades();
       


        // Mostrar cartas iniciales en el tablero
        LOGGER.info("\nCartas iniciales en el tablero:");
        int index = 1;
        for (final Carta carta : tablero.getCartas()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas disponibles en el capítulo actual antes de robar
        LOGGER.info("\nCartas disponibles en el capítulo actual (" + tablero.getCapitulo() + ") antes de robar:");
        index = 1;
        for (final Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Robar cartas en el capítulo actual
        LOGGER.info("\n--- Robar cartas en capítulo 1 ---");
        final Carta cartaRobada1 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.anadirCarta(cartaRobada1);

        final Carta cartaRobada2 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.anadirCarta(cartaRobada2);
        // Mostrar cartas restantes en el tablero
        LOGGER.info("\nCartas restantes en el tablero (capítulo 1):");
        index = 1;
        for (final Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas robadas
        LOGGER.info("\nCartas robadas:");
        LOGGER.info(jugador1.getNombre() + " tiene: ");
        index = 1;
        for (final Carta carta : jugadorPartida1.getCartas()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        LOGGER.info(jugador2.getNombre() + " tiene: ");
        index = 1;
        for (final Carta carta : jugadorPartida2.getCartas()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Cambiar de capítulo y repetir el proceso
        tablero.setCapitulo("fase2");

        // Mostrar cartas disponibles en el capítulo 2 antes de robar
        LOGGER.info("\nCartas disponibles en el capítulo actual (" + tablero.getCapitulo() + ") antes de robar:");
        index = 1;
        for (final Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        LOGGER.info("\n--- Robar cartas en capítulo 2 ---");
        final Carta cartaRobada3 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.anadirCarta(cartaRobada3);

        final  Carta cartaRobada4 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.anadirCarta(cartaRobada4);

        // Mostrar cartas restantes en el tablero
        LOGGER.info("\nCartas restantes en el tablero (capítulo 2):");
        index = 1;
        for (final Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas robadas en el capítulo 2
        LOGGER.info("\nCartas robadas en capítulo 2:");
        LOGGER.info(jugador1.getNombre() + " tiene: ");
        index = 1;
        for (final Carta carta : jugadorPartida1.getCartas()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        LOGGER.info(jugador2.getNombre() + " tiene: ");
        index = 1;
        for (final Carta carta : jugadorPartida2.getCartas()) {
            LOGGER.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }
    }
    	
    /*Comentado método que no utilizamos
     * private static void mostrarUnidades(JugadorPartida jugadorPartida) {
        LOGGER.info("Torres disponibles: " + jugadorPartida.contarUnidadesDisponibles("Torre"));
        LOGGER.info("Soldados disponibles: " + jugadorPartida.contarUnidadesDisponibles("Soldado"));
        LOGGER.info("Unidades colocadas:");
        for (Unidad unidad : jugadorPartida.getUnidades()) {
            if (!unidad.getPosicionTerritorio().equals("Sin colocar")) {
                LOGGER.info("- " + unidad.getTipo() + " en " + unidad.getPosicionTerritorio());
            }
        }
    }*/
}
