package Clases;


/**
 * Representa una raza.
 * Cada raza tiene un identificador, un nombre y una ruta de imagen asociada.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class Razas {
    private int idRaza;
    private String nombre;
    private String imagenRuta;
	

    public Razas(final int idRaza, final String nombre, final String imagenRuta) {
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.imagenRuta = imagenRuta;
    }
    
	// Getters y Setters
    public int getId() {
        return idRaza;
    }

    public void setId(final int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(final String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

}
