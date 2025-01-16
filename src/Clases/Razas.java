package Clases;

public class Razas {
    private int id;
    private String nombre;
    private String imagenRuta;
	

    public Razas(int id, String nombre, String imagenRuta) {
        this.id = id;
        this.nombre = nombre;
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
    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

}
