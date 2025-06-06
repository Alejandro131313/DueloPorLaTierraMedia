
package Clases;
import java.io.Serializable;

/**
 * Representa una carta del juego Duelo por la Tierra Media.
 * Cada carta contiene información como su nombre, color, recursos requeridos,
 * efecto y la ruta de su imagen.
 * 
 * Esta clase implementa {@link Serializable} para poder ser guardada o enviada
 * por red, por ejemplo en partidas multijugador.
 * 
 * @author Alejandro
 * @version 1.0
 */

public class Carta implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCarta;
    private String nombre;
    private String color;
    private String recursos;
    private String efecto;
    private String fase;
    private String imagenRuta;

    /**
     * Crea una nueva carta con todos sus atributos.
     * 
     * @param idCarta     Identificador único de la carta.
     * @param nombre      Nombre de la carta.
     * @param color       Color o facción de la carta (por ejemplo: gris, verde...).
     * @param recursos    Recursos que requiere.
     * @param efecto      Efecto que produce.
     * @param fase        Fase del juego en la que puede estar.
     * @param imagenRuta  Ruta de la imagen que representa visualmente la carta.
     */
    
    public Carta(final int idCarta, final String nombre, final String color,
            final String recursos, final String efecto, final String fase,
            final String imagenRuta) {
   this.idCarta = idCarta;
   this.nombre = nombre;
   this.color = color;
   this.recursos = recursos;
   this.efecto = efecto;
   this.fase = fase;
   this.imagenRuta = imagenRuta;
}

	// Getters y Setters
    public int getId() {
        return idCarta;
    }

    public void setId(final int idCarta) {
        this.idCarta = idCarta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getRecursosQueRequiere() {
        return recursos;
    }

    public void setRecursosQueRequiere(final String recursos) {
        this.recursos = recursos;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(final String efecto) {
        this.efecto = efecto;
    }
    public String getFase() {
        return fase;
    }

    public void setFase(final String fase) {
        this.fase = fase;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(final String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }
}