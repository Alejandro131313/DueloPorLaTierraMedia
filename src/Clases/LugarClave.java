package Clases;


/**
 * Representa un lugar clave.
 * Cada lugar tiene un identificador, nombre,recursos que encesita,
 * efecto especial y una imagen asociada.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class LugarClave {
	private int idLugar;
	private String nombre;
    private String recursos;
    private String efecto;
    private String imagenRuta;

    /**
     * Constructor que inicializa todos los atributos del lugar clave.
     * 
     * @param idLugar     Identificador único del lugar.
     * @param nombre      Nombre del lugar.
     * @param recursos    Recursos necesarios para ser robada.
     * @param efecto      Efecto que produce en la partida.
     * @param imagenRuta  Ruta de la imagen asociada.
     */
    
    public LugarClave(final int idLugar, final String nombre, final String recursos, final String efecto, final String imagenRuta) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.recursos = recursos;
        this.efecto = efecto;
        this.imagenRuta = imagenRuta;
    }
//test
    // Getter y Setter
    public int getId() {
        return idLugar;
    }

    public void setId(final int idLugar) {
        this.idLugar = idLugar;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    public String getRecursosQueRequiere() {	
        return recursos;
    }

    public void setRecursosQueRequiere(final String recursosQueRequiere) {
        this.recursos = recursosQueRequiere;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(final String efecto) {
        this.efecto = efecto;
    }
    

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(final String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }
}