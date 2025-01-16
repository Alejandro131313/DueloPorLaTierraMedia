package Clases;

public class LugarClave {
	private int id;
	private String nombre;
    private String recursosQueRequiere;
    private String efecto;
    private String imagenRuta;

    public LugarClave(int id, String nombre, String recursosQueRequiere, String efecto, String imagenRuta) {
        this.id = id;
        this.nombre = nombre;
        this.recursosQueRequiere = recursosQueRequiere;
        this.efecto = efecto;
        this.imagenRuta = imagenRuta;
    }

    // Getter y Setter
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