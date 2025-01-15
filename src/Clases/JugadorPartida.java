package Clases;

import java.util.List;

public class JugadorPartida {
    private Jugador jugador;
    private Partida partida;
    private int oro;
    private String faccion;

    // Listas de entidades relacionadas con esta partida
    private List<Carta> cartas;
    private List<LugarClave> territorios;
    private List<Unidad> unidades;

    public JugadorPartida(Jugador jugador, Partida partida, int oro, String faccion, List<Carta> cartas, List<LugarClave> territorios, List<Unidad> unidades) {
        this.jugador = jugador;
        this.partida = partida;
        this.oro = oro;
        this.faccion = faccion;
        this.cartas = cartas;
        this.territorios = territorios;
        this.unidades = unidades;
    }

    // Getters y Setters
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public String getFaccion() {
        return faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<LugarClave> getTerritorios() {
        return territorios;
    }

    public void setTerritorios(List<LugarClave> territorios) {
        this.territorios = territorios;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }
    
    
    
    
    public void añadirCarta(Carta carta) {
        if (carta != null) {
            cartas.add(carta);
            System.out.println(jugador.getNombre() + " ha robado la carta: " + carta.getNombre());
        }
    }

    
    
    
    
    
}
