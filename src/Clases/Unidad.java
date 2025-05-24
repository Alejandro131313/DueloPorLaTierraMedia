package Clases;


public class Unidad {
    private int idUnidad;
    private String tipo;
    private String posicionTerritorio;

    public Unidad(final int idUnidad, final String tipo, final String posicionTerritorio) {
        this.idUnidad = idUnidad;
        this.tipo = tipo;
        this.posicionTerritorio = posicionTerritorio;
    }

    // Getters y Setters
    public int getId() {
        return idUnidad;
    }

    public void setId(final int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getPosicionTerritorio() {
        return posicionTerritorio;
    }

    public void setPosicionTerritorio(final String posicionTerritorio) {
        this.posicionTerritorio = posicionTerritorio;
    }
}
