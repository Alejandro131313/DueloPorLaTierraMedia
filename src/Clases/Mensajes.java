package Clases;

import java.io.Serializable;

public class Mensajes implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Tipo {
        INICIAR_PARTIDA,
        TURNO_JUGADOR,
        ROBAR_CARTA,
        RESPUESTA_CARTA,
        CARTA_INVALIDA,
        FIN_PARTIDA
    }

    private Tipo tipo;
    private int idCarta;
    private String jugador;
    private Object contenido; // Puede ser una Carta o estado del juego

    public Mensajes(Tipo tipo) {
        this.tipo = tipo;
    }

    public Mensajes(Tipo tipo, int idCarta, String jugador) {
        this.tipo = tipo;
        this.idCarta = idCarta;
        this.jugador = jugador;
    }

    public Mensajes(Tipo tipo, Object contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }
}  
