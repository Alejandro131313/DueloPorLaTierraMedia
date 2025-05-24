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
    private int idMensaje;
    private String jugador;
    private Object contenido; // Puede ser una Carta o estado del juego

    public Mensajes(final Tipo tipo) {
        this.tipo = tipo;
    }

    public Mensajes(final Tipo tipo, final int idMensaje, final String jugador) {
        this.tipo = tipo;
        this.idMensaje = idMensaje;
        this.jugador = jugador;
    }

    public Mensajes(final Tipo tipo, final Object contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(final Tipo tipo) {
        this.tipo = tipo;
    }

    public int getIdCarta() {
        return idMensaje;
    }

    public void setIdCarta(final int idCarta) {
        this.idMensaje = idCarta;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(final String jugador) {
        this.jugador = jugador;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(final Object contenido) {
        this.contenido = contenido;
    }
}  
