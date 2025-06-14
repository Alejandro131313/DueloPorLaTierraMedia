package Clases;

import java.io.Serializable;

/**
 * Representa un mensaje intercambiado entre cliente y servidor durante la
 * partida. Cada mensaje puede tener un tipo, un identificador, un jugador
 * asociado y un contenido dinámico.
 * 
 * Esta clase es serializable para poder ser enviada por red mediante streams de
 * objetos.
 * 
 * @author Alejandro
 * @version 1.0
 */

public class Mensajes implements Serializable {
	private static final long serialVersionUID = 1L;

/**
* Tipos posibles de mensajes que se pueden intercambiar.
*/

	public enum Tipo {
		INICIAR_PARTIDA, TURNO_JUGADOR, ROBAR_CARTA, RESPUESTA_CARTA, CARTA_INVALIDA, FIN_PARTIDA
	}

	private Tipo tipo;
	private int idMensaje;
	private String jugador;
	private Object contenido; 

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
