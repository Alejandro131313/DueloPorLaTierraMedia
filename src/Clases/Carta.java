
package Clases;
import java.io.Serializable;

public class Carta implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String nombre;
    private String color;
    private String recursosQueRequiere;
    private String efecto;
    private String fase;
    private String imagenRuta;

    public Carta(int id, String nombre, String color, String recursosQueRequiere, String efecto, String fase, String imagenRuta) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.recursosQueRequiere = recursosQueRequiere;
        this.efecto = efecto;
        this.fase = fase;
        this.imagenRuta = imagenRuta;
    }

	// Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRecursosQueRequiere() {
        return recursosQueRequiere;
    }

    public void setRecursosQueRequiere(String recursosQueRequiere) {
        this.recursosQueRequiere = recursosQueRequiere;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }
    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }
}