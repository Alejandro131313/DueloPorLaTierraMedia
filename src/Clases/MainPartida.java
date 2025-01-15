package Clases;

import java.util.ArrayList;

public class MainPartida {
    public static void main(String[] args) {
        // Crear jugadores
        Jugador jugador1 = new Jugador(1, "Pedro", 30, 0);
        Jugador jugador2 = new Jugador(2, "Paco", 25, 0);

        // Crear un tablero
        Tablero tablero = new Tablero(1, "Disposición inicial", "fase1");

        // Crear jugador-partida
        JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, null, 0, "Comunidad", new ArrayList<>(), null, null);
        JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, null, 0, "Sauron", new ArrayList<>(), null, null);

        // Crear la partida
        Partida partida = new Partida(1, "10:00", "2025-01-10", tablero);

        // Asociar la partida a los JugadorPartida
        jugadorPartida1.setPartida(partida);
        jugadorPartida2.setPartida(partida);

        // Agregar los jugadores-partida a la partida
        partida.agregarJugador(jugador1);
        partida.agregarJugador(jugador2);

        // Confirmar la creación
        System.out.println("Partida creada con los jugadores:");
        System.out.println("-----------------------------------");
        System.out.println("Nombre del Jugador | Facción | Oro");
        System.out.println("-----------------------------------");

        for (Jugador jugador : partida.getJugadores()) {
            JugadorPartida jp = jugador.getId() == 1 ? jugadorPartida1 : jugadorPartida2;
            System.out.printf("%-18s | %-8s | %-3d%n", jugador.getNombre(), jp.getFaccion(), jp.getOro());
        }

        // Mostrar cartas iniciales en el tablero
        System.out.println("\nCartas iniciales en el tablero:");
        for (Carta carta : tablero.getCartas()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        // Robar cartas en el capítulo actual
        System.out.println("\n--- Robar cartas en capítulo 1 ---");
        Carta cartaRobada1 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.añadirCarta(cartaRobada1);

        Carta cartaRobada2 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.añadirCarta(cartaRobada2);

        // Mostrar cartas restantes en el tablero
        System.out.println("\nCartas restantes en el tablero (capítulo 1):");
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        // Mostrar cartas robadas
        System.out.println("\nCartas robadas:");
        System.out.println(jugador1.getNombre() + " tiene: ");
        for (Carta carta : jugadorPartida1.getCartas()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        System.out.println(jugador2.getNombre() + " tiene: ");
        for (Carta carta : jugadorPartida2.getCartas()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        // Cambiar de capítulo y repetir el proceso
        tablero.setCapitulo("fase2");
        System.out.println("\n--- Robar cartas en capítulo 2 ---");
        Carta cartaRobada3 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida1.añadirCarta(cartaRobada3);

        Carta cartaRobada4 = tablero.robarCarta(tablero.getCapitulo());
        jugadorPartida2.añadirCarta(cartaRobada4);

        // Mostrar cartas restantes en el tablero
        System.out.println("\nCartas restantes en el tablero (capítulo 2):");
        for (Carta carta : tablero.obtenerCartasDelCapituloActual()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        // Mostrar cartas robadas en el capítulo 2
        System.out.println("\nCartas robadas en capítulo 2:");
        System.out.println(jugador1.getNombre() + " tiene: ");
        for (Carta carta : jugadorPartida1.getCartas()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }

        System.out.println(jugador2.getNombre() + " tiene: ");
        for (Carta carta : jugadorPartida2.getCartas()) {
            System.out.println("- " + carta.getNombre() + " (" + carta.getFase() + ")");
        }
    }
}
