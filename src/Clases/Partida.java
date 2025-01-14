package Clases;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private int id;
    private String horaDeLaPartida;
    private String diaDeLaPartida;
    private List<Jugador> jugadores;
    private Tablero tablero;

    public Partida(int id, String horaDeLaPartida, String diaDeLaPartida, Tablero tablero) {
        this.id = id;
        this.horaDeLaPartida = horaDeLaPartida;
        this.diaDeLaPartida = diaDeLaPartida;
        this.tablero = tablero;
        this.jugadores = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraDeLaPartida() {
        return horaDeLaPartida;
    }

    public void setHoraDeLaPartida(String horaDeLaPartida) {
        this.horaDeLaPartida = horaDeLaPartida;
    }

    public String getDiaDeLaPartida() {
        return diaDeLaPartida;
    }

    public void setDiaDeLaPartida(String diaDeLaPartida) {
        this.diaDeLaPartida = diaDeLaPartida;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() < 2) {
            jugadores.add(jugador);
        } else {
            throw new IllegalStateException("Ya hay dos jugadores en la partida.");
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}

