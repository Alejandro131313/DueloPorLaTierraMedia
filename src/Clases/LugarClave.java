package Clases;


public class LugarClave {
    private int id;
    private String nombre;
    private int coste;
    private String beneficio;

    public LugarClave(int id, String nombre, int coste, String beneficio) {
        this.id = id;
        this.nombre = nombre;
        this.coste = coste;
        this.beneficio = beneficio;
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

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }
}

