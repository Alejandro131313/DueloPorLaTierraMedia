package Clases;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int id;
    private String disposicionCartas;
    private String capitulo;
    private List<Carta> cartas;

    public Tablero(int id, String disposicionCartas, String capitulo) {
        this.id = id;
        this.disposicionCartas = disposicionCartas;
        this.capitulo = capitulo;
        this.cartas = new ArrayList<>();
        inicializarCartas();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisposicionCartas() {
        return disposicionCartas;
    }

    public void setDisposicionCartas(String disposicionCartas) {
        this.disposicionCartas = disposicionCartas;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public void eliminarCarta(Carta carta) {
        this.cartas.remove(carta);
    }

    private void inicializarCartas() {
        // Agregar una carta de cada tipo
        cartas.add(new Carta(1, "Batalla de los Campos del Pelennor", "Rojo", 3, "Espadas", "Aumenta fuerza en combate", "ruta/a/imagen1.png"));
        cartas.add(new Carta(2, "Ataque Sorpresa", "Azul", 2, "Magia", "Permite un movimiento extra", "ruta/a/imagen2.png"));
        cartas.add(new Carta(3, "Misión: Avanzar al Monte del Destino", "Verde", 4, "Misiones", "Avanza en la historia", "ruta/a/imagen3.png"));
        cartas.add(new Carta(4, "Recurso: Minas de Moria", "Amarillo", 1, "Recursos", "Genera oro adicional", "ruta/a/imagen4.png"));
    }
}