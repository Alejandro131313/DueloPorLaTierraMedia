package Clases;


public class MisionAnillo {
    private int id;
    private int comunidad;
    private int sauron;
    private String posicion;

    public MisionAnillo(int id, int comunidad, int sauron, String posicion) {
        this.id = id;
        this.comunidad = comunidad;
        this.sauron = sauron;
        this.posicion = posicion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComunidad() {
        return comunidad;
    }

    public void setComunidad(int comunidad) {
        this.comunidad = comunidad;
    }

    public int getSauron() {
        return sauron;
    }

    public void setSauron(int sauron) {
        this.sauron = sauron;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
