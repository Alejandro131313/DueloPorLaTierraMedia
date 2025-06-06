package Clases;


/**
 * Representa la carrera entre la comunidad y Sauron.
 * Incluye el identificador de la carrera, la posición de la Comunidad, 
 * la posición de Sauron y una posición textual asociada.
 * 
 * Esta clase puede utilizarse para controlar el avance de la carrera
 * entre ambos.
 * 
 * @author Alejandro
 * @version 1.0
 */



public class MisionAnillo {
    private int idMision;
    private int comunidad;
    private int sauron;
    private String posicion;

    public MisionAnillo(final int idMision, final int comunidad, final int sauron, final String posicion) {
        this.idMision = idMision;
        this.comunidad = comunidad;
        this.sauron = sauron;
        this.posicion = posicion;
    }

    // Getters y Setters
    public int getId() {
        return idMision;
    }

    public void setId(final int idMision) {
        this.idMision = idMision;
    }

    public int getComunidad() {
        return comunidad;
    }

    public void setComunidad(final int comunidad) {
        this.comunidad = comunidad;
    }

    public int getSauron() {
        return sauron;
    }

    public void setSauron(final int sauron) {
        this.sauron = sauron;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(final String posicion) {
        this.posicion = posicion;
    }
}
