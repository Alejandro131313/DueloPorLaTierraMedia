package Clases;


	import java.util.List;

	public class Mapa {
	    private List<Territorio> territorios;

	    public Mapa(List<Territorio> territorios) {
	        this.territorios = territorios;
	    }

	    // Getters y Setters
	    public List<Territorio> getTerritorios() {
	        return territorios;
	    }

	    public void setTerritorios(List<Territorio> territorios) {
	        this.territorios = territorios;
	    }
	}
