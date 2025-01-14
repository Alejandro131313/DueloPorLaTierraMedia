package Clases;

public class Jugador {
    private int id;
    private String nombre;
    private int edad;
    private int numeroVictorias;

    public Jugador(int id, String nombre, int edad, int numeroVictorias) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.numeroVictorias = numeroVictorias;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumeroVictorias() {
        return numeroVictorias;
    }

    public void setNumeroVictorias(int numeroVictorias) {
        this.numeroVictorias = numeroVictorias;
    }
}
