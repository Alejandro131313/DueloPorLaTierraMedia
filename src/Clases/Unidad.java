package Clases;


public class Unidad {
    private int id;
    private String tipo;
    private String posicionTerritorio;

    public Unidad(int id, String tipo, String posicionTerritorio) {
        this.id = id;
        this.tipo = tipo;
        this.posicionTerritorio = posicionTerritorio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPosicionTerritorio() {
        return posicionTerritorio;
    }

    public void setPosicionTerritorio(String posicionTerritorio) {
        this.posicionTerritorio = posicionTerritorio;
    }
}
