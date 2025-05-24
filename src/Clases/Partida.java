package Clases;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private int idPartida;
    private String horaDeLaPartida;
    private String diaDeLaPartida;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private int turnoActual;

    public Partida(final int idPartida, final String horaDeLaPartida, final String diaDeLaPartida, final Tablero tablero) {
        this.idPartida = idPartida;
        this.horaDeLaPartida = horaDeLaPartida;
        this.diaDeLaPartida = diaDeLaPartida;
        this.tablero = tablero;
        this.jugadores = new ArrayList<>();
        this.turnoActual = 0; // Empieza con el primer jugador
    }

    // Getters y Setters
    public int getId() {
        return idPartida;
    }

    public void setId(final int idPartida) {
        this.idPartida = idPartida;
    }

    public String getHoraDeLaPartida() {
        return horaDeLaPartida;
    }

    public void setHoraDeLaPartida(final String horaDeLaPartida) {
        this.horaDeLaPartida = horaDeLaPartida;
    }

    public String getDiaDeLaPartida() {
        return diaDeLaPartida;
    }

    public void setDiaDeLaPartida(final String diaDeLaPartida) {
        this.diaDeLaPartida = diaDeLaPartida;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(final Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser null.");
        }
        if (jugadores.size() < 2) {
            jugadores.add(jugador);
        } else {
            throw new IllegalStateException("Ya hay dos jugadores en la partida.");
        }
    }
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(final Tablero tablero) {
        this.tablero = tablero;
    }
    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public void avanzarTurno() {
        if (!jugadores.isEmpty()) {
            turnoActual = (turnoActual + 1) % jugadores.size();
        }
    }
}

