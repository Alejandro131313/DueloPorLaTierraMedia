package Clases;

public class Carta {
    private int id;
    private String nombre;
    private String color;
    private int coste;
    private String recursosQueRequiere;
    private String efecto;
    private String imagenRuta;

    public Carta(int id, String nombre, String color, int coste, String recursosQueRequiere, String efecto, String imagenRuta) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.coste = coste;
        this.recursosQueRequiere = recursosQueRequiere;
        this.efecto = efecto;
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

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
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

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }
}