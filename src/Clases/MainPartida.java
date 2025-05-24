package Clases;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainPartida {
	
    private static final Logger logger = Logger.getLogger(MainPartida.class.getName());
    public static void main(String[] args) {
    	
        
    	System.setProperty("prism.order", "sw");
    	// Crear jugadores
        Jugador jugador1 = new Jugador(1, "Pedro", 30, 0);
        Jugador jugador2 = new Jugador(2, "Paco", 25, 0);

        // Crear un tablero
        Tablero tablero = new Tablero(1, "Disposición inicial", "fase1");

        // Crear jugador-partida
        JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, null, 0, "Sauron", new ArrayList<>(), null, null);
        JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, null, 0, "Comunidad", new ArrayList<>(), null, null);

        // Crear la partida
        Partida partida = new Partida(1, "10:00", "2025-01-10", tablero);

        // Asociar la partida a los JugadorPartida
        jugadorPartida1.setPartida(partida);
        jugadorPartida2.setPartida(partida);

        // Agregar los jugadores-partida a la partida
        partida.agregarJugador(jugador1);
        partida.agregarJugador(jugador2);

        // Confirmar la creación
        logger.info("Partida creada con los jugadores:");
        logger.info("-----------------------------------");
        logger.info("Nombre del Jugador | Facción | Oro");
        logger.info("-----------------------------------");

        for (Jugador jugador : partida.getJugadores()) {
            JugadorPartida jp = jugador.getId() == 1 ? jugadorPartida1 : jugadorPartida2;
            System.out.printf("%-18s | %-8s | %-3d%n", jugador.getNombre(), jp.getFaccion(), jp.getOro());
        }
        // Inicializar unidades (7 torres y 15 soldados por jugador)
        jugadorPartida1.inicializarUnidades();
        jugadorPartida2.inicializarUnidades();
       


        // Mostrar cartas iniciales en el tablero
        logger.info("\nCartas iniciales en el tablero:");
        int index = 1;
        for (Carta carta : tablero.getCartas()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas disponibles en el capítulo actual antes de robar
        logger.info("\nCartas disponibles en el capítulo actual (" + tablero.getCapitulo() + ") antes de robar:");
        index = 1;
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Robar cartas en el capítulo actual
        logger.info("\n--- Robar cartas en capítulo 1 ---");
        Carta cartaRobada1 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.añadirCarta(cartaRobada1);

        Carta cartaRobada2 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.añadirCarta(cartaRobada2);

        // Mostrar cartas restantes en el tablero
        logger.info("\nCartas restantes en el tablero (capítulo 1):");
        index = 1;
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas robadas
        logger.info("\nCartas robadas:");
        logger.info(jugador1.getNombre() + " tiene: ");
        index = 1;
        for (Carta carta : jugadorPartida1.getCartas()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        logger.info(jugador2.getNombre() + " tiene: ");
        index = 1;
        for (Carta carta : jugadorPartida2.getCartas()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Cambiar de capítulo y repetir el proceso
        tablero.setCapitulo("fase2");

        // Mostrar cartas disponibles en el capítulo 2 antes de robar
        logger.info("\nCartas disponibles en el capítulo actual (" + tablero.getCapitulo() + ") antes de robar:");
        index = 1;
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        logger.info("\n--- Robar cartas en capítulo 2 ---");
        Carta cartaRobada3 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.añadirCarta(cartaRobada3);

        Carta cartaRobada4 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.añadirCarta(cartaRobada4);

        // Mostrar cartas restantes en el tablero
        logger.info("\nCartas restantes en el tablero (capítulo 2):");
        index = 1;
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        // Mostrar cartas robadas en el capítulo 2
        logger.info("\nCartas robadas en capítulo 2:");
        logger.info(jugador1.getNombre() + " tiene: ");
        index = 1;
        for (Carta carta : jugadorPartida1.getCartas()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }

        logger.info(jugador2.getNombre() + " tiene: ");
        index = 1;
        for (Carta carta : jugadorPartida2.getCartas()) {
            logger.info(index + " - " + carta.getNombre() + " (" + carta.getFase() + ")");
            index++;
        }
    }
    	private static void mostrarUnidades(JugadorPartida jugadorPartida) {
        logger.info("Torres disponibles: " + jugadorPartida.contarUnidadesDisponibles("Torre"));
        logger.info("Soldados disponibles: " + jugadorPartida.contarUnidadesDisponibles("Soldado"));
        logger.info("Unidades colocadas:");
        for (Unidad unidad : jugadorPartida.getUnidades()) {
            if (!unidad.getPosicionTerritorio().equals("Sin colocar")) {
                logger.info("- " + unidad.getTipo() + " en " + unidad.getPosicionTerritorio());
            }
        }
    }
}
