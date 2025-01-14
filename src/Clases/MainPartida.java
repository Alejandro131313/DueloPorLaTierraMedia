package Clases;

public class MainPartida {
    public static void main(String[] args) {
        // Crear jugadores
        Jugador jugador1 = new Jugador(1, "Pedro", 30, 0);
        Jugador jugador2 = new Jugador(2, "Paco", 25, 0);

        // Crear un tablero
        Tablero tablero = new Tablero(1, "Disposición inicial", "Capítulo 1");

        // Crear jugador-partida
        JugadorPartida jugadorPartida1 = new JugadorPartida(jugador1, null, 0, "Comunidad", null, null, null);
        JugadorPartida jugadorPartida2 = new JugadorPartida(jugador2, null, 0, "Sauron", null, null, null);

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
        for (Jugador jugador : partida.getJugadores()) {
            System.out.println("- " + jugador.getNombre());
        }

        System.out.println("Tablero inicial: " + partida.getTablero().getDisposicionCartas());

        // Mostrar las cartas inicializadas en el tablero
        System.out.println("\nCartas iniciales en el tablero:");
        for (Carta carta : partida.getTablero().getCartas()) {
            System.out.println("- ID: " + carta.getId() +
                               ", Nombre: " + carta.getNombre() +
                               ", Color: " + carta.getColor() +
                               ", Costo: " + carta.getCoste() +
                               ", Efecto: " + carta.getEfecto());
        }
        System.out.println("Prueba111111");
    }
    
}
