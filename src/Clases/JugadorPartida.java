package Clases;

import java.util.ArrayList;
import java.util.List;

public class JugadorPartida {
    private Jugador jugador;
    private Partida partida;
    //Contadores Atributos
    private int oro;
    private int fuerza;
    private int valor;
    private int astucia;
    private int sabiduria;
    private int corona;
    //Contadores Razas
    private int hobbits;
    private int enanos;
    private int humanos;
    private int elfos;
    private int magos;
    private int ents;
    
    
    private String faccion;

    // Listas de entidades relacionadas con esta partida
    private List<Carta> cartas;
    private List<LugarClave> territorios;
    private List<Unidad> unidades;        //Array Torres 7 y 15 Soladados /Atributo posicionTerritorio SinColocar De Priemras
   

    public JugadorPartida(Jugador jugador, Partida partida, int oro, String faccion, List<Carta> cartas, List<LugarClave> territorios, List<Unidad> unidades) {
        this.jugador = jugador;
        this.partida = partida;
        this.oro = oro;
        this.faccion = faccion;
        this.cartas = cartas != null ? cartas : new ArrayList<>();
        this.territorios = territorios != null ? territorios : new ArrayList<>();
        this.unidades = unidades != null ? unidades : new ArrayList<>();

        // Inicialización de atributos numéricos
        this.fuerza = 0;
        this.valor = 0;
        this.astucia = 0;
        this.sabiduria = 0;
        this.corona = 0;

        // Inicialización de contadores de razas
        this.hobbits = 0;
        this.enanos = 0;
        this.humanos = 0;
        this.elfos = 0;
        this.magos = 0;
        this.ents = 0;
    }


    public JugadorPartida(String comunidad, String string) {
	}


	// Métodos para inicializar unidades
    public void inicializarUnidades() {
        // Agregar 7 torres
        for (int i = 0; i < 7; i++) {
            unidades.add(new Unidad(i + 1, "Torre", "Sin colocar"));
        }
        // Agregar 15 soldados
        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(i + 8, "Soldado", "Sin colocar"));
        }
    }
    public List<Unidad> obtenerUnidadesDisponibles(String tipo) {
        List<Unidad> disponibles = new ArrayList<>();
        for (Unidad unidad : unidades) {
            if (unidad.getTipo().equals(tipo) && unidad.getPosicionTerritorio().equals("Sin colocar")) {
                disponibles.add(unidad);
            }
        }
        return disponibles;
    }

    // Métodos para contar unidades disponibles
    public int contarUnidadesDisponibles(String tipo) {
        return (int) unidades.stream()
                .filter(u -> u.getTipo().equals(tipo) && u.getPosicionTerritorio().equals("Sin colocar"))
                .count();
    }
	

    // Getters y Setters
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

	public int getFuerza() {
	    return fuerza;
	}
	
	public void setFuerza(int fuerza) {
	    this.fuerza = fuerza;
	}
	
	public int getValor() {
	    return valor;
	}
	
	public void setValor(int valor) {
	    this.valor = valor;
	}
	
	public int getAstucia() {
	    return astucia;
	}
	
	public void setAstucia(int astucia) {
	    this.astucia = astucia;
	}
	
	public int getSabiduria() {
	    return sabiduria;
	}
	
	public void setSabiduria(int sabiduria) {
	    this.sabiduria = sabiduria;
	}
	
	public int getCorona() {
	    return corona;
	}
	
	public void setCorona(int corona) {
	    this.corona = corona;
	}


    public String getFaccion() {
        return faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<LugarClave> getTerritorios() {
        return territorios;
    }

    public void setTerritorios(List<LugarClave> territorios) {
        this.territorios = territorios;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }
    
    
    
    
    public int getHobbits() {
		return hobbits;
	}

	public void setHobbits(int hobbits) {
		this.hobbits = hobbits;
	}

	public int getEnanos() {
		return enanos;
	}

	public void setEnanos(int enanos) {
		this.enanos = enanos;
	}

	public int getHumanos() {
		return humanos;
	}

	public void setHumanos(int humanos) {
		this.humanos = humanos;
	}

	public int getElfos() {
		return elfos;
	}

	public void setElfos(int elfos) {
		this.elfos = elfos;
	}

	public int getMagos() {
		return magos;
	}

	public void setMagos(int magos) {
		this.magos = magos;
	}

	public int getEnts() {
		return ents;
	}

	public void setEnts(int ents) {
		this.ents = ents;
	}

	public void añadirCarta(Carta carta) {
        if (carta != null) {
            cartas.add(carta);
            System.out.println(jugador.getNombre() + " ha robado la carta: " + carta.getNombre());
        }
    }

    
    
    
    
    
}
