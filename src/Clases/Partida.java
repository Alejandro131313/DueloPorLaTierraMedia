package Clases;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa una partida.
 * Almacena información sobre los jugadores, la fecha y hora de la partida,
 * el tablero y el control de turnos.
 * 
 * Una partida solo puede tener un máximo de 2 jugadores.
 * 
 * @author Alejandro
 * @version 1.0
 */

public class Partida {
    private int idPartida;
    private String horaDeLaPartida;
    private String diaDeLaPartida;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private int turnoActual;

    
    /**
     * Crea una nueva instancia de partida con la información básica.
     * 
     * @param idPartida       Identificador único de la partida.
     * @param horaDeLaPartida Hora en la que se inició la partida.
     * @param diaDeLaPartida  Día en que se juega la partida.
     * @param tablero         Tablero utilizado en esta partida.
     */
    
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

