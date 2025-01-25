package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablero {
    private int id;
    private String disposicionCartas;
    private String capitulo;
    private List<Carta> cartas;
    private List<Carta> cartasCapitulo1;
    private List<Carta> cartasCapitulo2;
    private List<Carta> cartasCapitulo3;
    private List<LugarClave> lugaresclave;
    private List<LugarClave> lugaresClaveSeleccionados;
    private List<Razas> razas;
    //MisionAnillo Pensar como se va hacer y añadirlo al tablero

    public Tablero(int id, String disposicionCartas, String capitulo) {
        this.id = id;
        this.disposicionCartas = disposicionCartas;
        this.capitulo = capitulo;
        this.cartas = new ArrayList<>();
        this.lugaresclave = new ArrayList<>();
        this.cartasCapitulo1 = new ArrayList<>();
        this.cartasCapitulo2 = new ArrayList<>();
        this.cartasCapitulo3 = new ArrayList<>();
        inicializarCartas();
        inicializarLugaresClave();
        dividirCartasPorCapitulo();
        mezclarCartasCapitulos();
        initializeRazas();
        
        
    }
 // Lista y mapa para las razas
    public List<Razas> getRazas() {
        return razas;
    }
    private Map<Razas, Integer> fichasDisponibles;
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisposicionCartas() {
        return disposicionCartas;
    }

    public void setDisposicionCartas(String disposicionCartas) {
        this.disposicionCartas = disposicionCartas;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public void eliminarCarta(Carta carta) {
        this.cartas.remove(carta);
    }
  
    public List<Carta> getCartasCapitulo1() {
		return cartasCapitulo1;
	}
	public void setCartasCapitulo1(List<Carta> cartasCapitulo1) {
		this.cartasCapitulo1 = cartasCapitulo1;
	}
	public List<Carta> getCartasCapitulo2() {
		return cartasCapitulo2;
	}
	public void setCartasCapitulo2(List<Carta> cartasCapitulo2) {
		this.cartasCapitulo2 = cartasCapitulo2;
	}
	public List<Carta> getCartasCapitulo3() {
		return cartasCapitulo3;
	}
	public void setCartasCapitulo3(List<Carta> cartasCapitulo3) {
		this.cartasCapitulo3 = cartasCapitulo3;
	}
	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	private void inicializarCartas() {
    	

		cartas = new ArrayList<>();
    	cartas.add(new Carta(1, "RecursoFuerza1", "Gris", "0", "fuerza+1", "fase1", ("/Images/Cartas/1.1.png")));
    	cartas.add(new Carta(2, "RecursoValor1", "Gris", "0", "valor+1", "fase1", ("/Images/Cartas/1.2.png")));
    	cartas.add(new Carta(3, "RecursoAstucia1", "Gris", "0", "astucia+1", "fase1", ("/Images/Cartas/1.3.png")));
    	cartas.add(new Carta(4, "RecursoValor2", "Gris", "0", "valor+1", "fase1", ("/Images/Cartas/1.4.png")));
    	cartas.add(new Carta(5, "RecursoFuerza2", "Gris", "0", "fuerza+1", "fase1", ("/Images/Cartas/1.5.png")));
    	cartas.add(new Carta(6, "RecursoAstucia2", "Gris", "0", "astucia+1", "fase1", ("/Images/Cartas/1.6.png")));
    	cartas.add(new Carta(7, "RecursoCorona1", "Gris", "0", "corona+1", "fase1", ("/Images/Cartas/1.7.png")));
    	cartas.add(new Carta(8, "RecursoSabiduria1", "Gris", "0", "sabiduria+1", "fase1", ("/Images/Cartas/1.8.png")));
    	cartas.add(new Carta(9, "RazaElfos1", "Verde", "1 sabiduria", "elfos+1", "fase1", ("/Images/Cartas/1.9.png")));
    	cartas.add(new Carta(10, "RazaEnanos1", "Verde", "1 corona", "enanos+1", "fase1", ("/Images/Cartas/1.10.png")));
    	cartas.add(new Carta(11, "RazaHumanos1", "Verde", "0", "humanos+1", "fase1", ("/Images/Cartas/1.11.png")));
    	cartas.add(new Carta(12, "RazaHobbits1", "Verde", "0", "hobbits+1", "fase1", ("/Images/Cartas/1.12.png")));
    	cartas.add(new Carta(13, "Batalla1", "Roja", "1 astucia", "Rohan+1 || Gondor+1", "fase1", ("/Images/Cartas/1.13.png")));
    	cartas.add(new Carta(14, "Batalla2", "Roja", "0", "Enedwatih+1 || Rhovanion+1", "fase1", ("/Images/Cartas/1.14.png")));
    	cartas.add(new Carta(15, "Batalla3", "Roja", "0", "Lindon +1 || Arnor+1", "fase1", ("/Images/Cartas/1.15.png")));
    	cartas.add(new Carta(16, "Mision1", "Azul", "1 fuerza", "avance+1", "fase1", ("/Images/Cartas/1.16.png")));
    	cartas.add(new Carta(17, "Mision2", "Azul", "0", "avance+1", "fase1", ("/Images/Cartas/1.17.png")));
    	cartas.add(new Carta(18, "Mision3", "Azul", "1 valor", "avance+1", "fase1", ("/Images/Cartas/1.18.png")));
    	cartas.add(new Carta(19, "Mision4", "Azul", "1 oro", "avance+1", "fase1", ("/Images/Cartas/1.19.png")));
    	cartas.add(new Carta(20, "Oro1", "Amarilla", "0", "oro+1", "fase1", ("/Images/Cartas/1.20.png")));
    	cartas.add(new Carta(21, "Oro2", "Amarilla", "0", "oro+1", "fase1", ("/Images/Cartas/1.21.png")));
    	cartas.add(new Carta(22, "Oro3", "Amarilla", "0", "oro+1", "fase1", ("/Images/Cartas/1.22.png")));
    	cartas.add(new Carta(23, "Oro4", "Amarilla", "0", "oro+1", "fase1", ("/Images/Cartas/1.23.png")));
    	
    	/*cartas.add(new Carta(24, "RecursoSabiduria3", "Gris", "0", "sabiduria+1", "fase2", "Images/Cartas/2.1.png"));
    	cartas.add(new Carta(25, "RecursoCorona2", "Gris", "0", "corona+1", "fase2", "Images/Cartas/2.2.png"));
    	cartas.add(new Carta(26, "RecursoAstucia3", "Gris", "1 oro", "astucia+2", "fase2", "Images/Cartas/2.3.png"));
    	cartas.add(new Carta(27, "RecursoCoronaSabiduria1", "Gris", "1 oro && 1 astucia", "corona+1 || sabiduria+1", "fase2", "Images/Cartas/2.4.png"));
    	cartas.add(new Carta(28, "RecursoFuerza3", "Gris", "1 oro", "fuerza+2", "fase2", "/src/Images/Cartas/2.5.png"));
    	cartas.add(new Carta(29, "RecursoAstuciaFuerzaValor1", "Gris", "1 oro && 1 corona && 1 sabiduria", "astucia+1 || fuerza+1 || valor+1", "fase2", "Images/Cartas/2.6.png"));
    	cartas.add(new Carta(30, "RecursoValor3", "Gris", "1 oro", "valor+2", "fase2", "Images/Cartas/2.7.png"));
    	cartas.add(new Carta(31, "RazaElfos2", "Verde", "1 valor && 1 corona && 1 sabiduria", "elfos+1", "fase2", "Images/Cartas/2.8.png"));
    	cartas.add(new Carta(32, "RazaEnanos2", "Verde", "2 fuerza && 1 valor", "enanos+1", "fase2", "Images/Cartas/2.9.png"));
    	cartas.add(new Carta(33, "RazaHumanos2", "Verde", "2 valor && 1 sabiduria", "humanos+1", "fase2", "Images/Cartas/2.10.png"));
    	cartas.add(new Carta(34, "RazaHobbits2", "Verde", "2 astucia && 1 corona", "hobbits+1", "fase2", "Images/Cartas/2.11.png"));
    	cartas.add(new Carta(35, "Batalla4", "Roja", "2 valor && 1 corona", "Arnor+2 || Rhovanion+2", "fase2", "Images/Cartas/2.12.png"));
    	cartas.add(new Carta(36, "Batalla5", "Roja", "1 astucia && 1 fuerza && 1 valor", "Lindon+2 || Rhovanion+2",  "fase2", "Images/Cartas/2.13.png"));
    	cartas.add(new Carta(37, "Batalla6", "Roja", "2 fuerza && 1 valor", "Lindon+2 || Enedwaith+2", "fase2", "Images/Cartas/2.14.png"));
    	cartas.add(new Carta(38, "Batalla7", "Roja", "2 astucia && 1 sabiduria", "Mordor+2 || Rohan+2", "fase2", "Images/Cartas/2.15.png"));
    	cartas.add(new Carta(39, "Batalla8", "Roja", "3 astucia", "Gondor+2 || Mordor+2", "fase2", "Images/Cartas/2.16.png"));
    	cartas.add(new Carta(40, "Mision5", "Azul", "1 oro", "avance+1", "fase2", "Images/Cartas/2.17.png"));
    	cartas.add(new Carta(41, "Mision6", "Azul", "1 astucia && 1 fuerza", "avance+1", "fase2", "Images/Cartas/2.18.png"));
    	cartas.add(new Carta(42, "Mision7", "Azul", "1 valor && 1 fuerza", "avance+1", "fase2", "Images/Cartas/2.19.png"));
    	cartas.add(new Carta(43, "Mision8", "Azul", "2 fuerza && 1 sabiduria", "avance+2", "fase2", "Images/Cartas/2.20.png"));
    	cartas.add(new Carta(44, "Mision9", "Azul", "1 fuerza && 2 valor", "avance+2", "fase2", "Images/Cartas/2.21.png"));
    	cartas.add(new Carta(45, "Oro5", "Amarilla", "0", "oro+3", "fase2", "Images/Cartas/2.22.png"));
    	cartas.add(new Carta(46, "Oro6", "Amarilla", "0", "oro+4", "fase2", "Images/Cartas/2.23.png"));
    	
    	cartas.add(new Carta(47, "RazaMagos1", "Verde", "2 astucia && 2 corona", "magos+1", "fase3", "Images/Cartas/3.1.png"));
    	cartas.add(new Carta(48, "RazaMagos2", "Verde", "2 fuerza && 1 corona && 1 sabiduria", "magos+1", "fase3", "Images/Cartas/3.2.png"));
    	cartas.add(new Carta(49, "RazaEnts1", "Verde", "2 fuerza && 2 sabiduria", "ents+1", "fase3", "Images/Cartas/3.3.png"));
    	cartas.add(new Carta(50, "RazaEnts2", "Verde", "2 valor && 1 corona && 1 sabiduria", "ents+1",  "fase3", "Images/Cartas/3.4.png"));
    	cartas.add(new Carta(51, "Batalla9", "Roja", "1 astucia && 1 fuerza && 2 corona && 1 sabiduria", "Gondor+3 || Arnor+3", "fase3", "Images/Cartas/3.5.png"));
		cartas.add(new Carta(52, "Batalla10", "Roja", "2 fuerza && 2 valor", "Enedwaith+3 || Rohan+3", "fase3", "Images/Cartas/3.6.png"));
		cartas.add(new Carta(53, "Batalla11", "Roja", "2 astucia && 1 valor && 2 sabiduria", "Lindon+3 || Mordor+3", "fase3", "Images/Cartas/3.7.png"));
		cartas.add(new Carta(54, "Batalla12", "Roja", "3 astucia && 2 corona", "Rhovanion+3 || Gondor+3", "fase3", "Images/Cartas/3.8.png"));
		cartas.add(new Carta(55, "Batalla13", "Roja", "2 astucia && 2 valor", "Enedwaith+3 || Arnor +3", "fase3", "Images/Cartas/3.9.png"));
		cartas.add(new Carta(56, "Batalla14", "Roja", "1 astucia && 2 fuerza && 2 valor", "Rhovanion+3 || Mordor+3", "fase3", "Images/Cartas/3.10.png"));
		cartas.add(new Carta(57, "Mision10", "Azul", "3 astucia && 1 valor", "avance+2", "fase3", "Images/Cartas/3.11.png"));
		cartas.add(new Carta(58, "Mision11", "Azul", "2 fuerza && 1 corona", "avance+2", "fase3", "Images/Cartas/3.12.png"));
		cartas.add(new Carta(59, "Mision12", "Azul", "1 astucia && 3 fuerza", "avance+2", "fase3", "Images/Cartas/3.13.png"));
		cartas.add(new Carta(60, "Mision13", "Azul", "2 astucia && 2 valor", "avance+2", "fase3", "Images/Cartas/3.14.png"));
		cartas.add(new Carta(61, "Mision14", "Azul", "3 oro", "avance+3", "fase3", "Images/Cartas/3.15.png"));
		cartas.add(new Carta(62, "Oro7", "Amarilla", "1 astucia && 1 corona && 1 sabiduria", "oro+5", "fase3", "Images/Cartas/3.16.png"));
		cartas.add(new Carta(63, "Oro8", "Amarilla", "2 astucia && 1 valor", "oro+5", "fase3", "Images/Cartas/3.17.png"));
		cartas.add(new Carta(64, "Sabotaje1", "Morada", "3 valor && 1 corona", "soldado-1 && oro-2", "fase3", "Images/Cartas/3.18.png"));
		cartas.add(new Carta(65, "Sabotaje2", "Morada", "2 valor && 1 corona && 1 sabiduria", "movimiento3", "fase3", "Images/Cartas/3.19.png"));
		cartas.add(new Carta(66, "Sabotaje3", "Morada", "1 astucia && 1 fuerza && 1 valor && 1 sabiduria", "oro-1 && movimiento2", "fase3", "Images/Cartas/3.20.png"));
		cartas.add(new Carta(67, "Sabotaje4", "Morada", "2 fuerza && 1 corona", "soldado-1 && oro-2 && movimiento1", "fase3", "Images/Cartas/3.21.png"));
		cartas.add(new Carta(68, "Sabotaje5", "Morada", "3 fuerza && 1 sabiduria", "soldado-1 && movimiento2", "fase3", "Images/Cartas/3.22.png"));
		cartas.add(new Carta(69, "Sabotaje6", "Morada", "1 fuerza && 1 valor && 1 sabiduria", "soldado-2 && oro-1", "fase3", "Images/Cartas/3.23.png"));*/
		
    }
	


 // Dividir las cartas en listas por capítulo
    private void dividirCartasPorCapitulo() {
        for (Carta carta : cartas) {
            switch (carta.getFase()) {
                case "fase1":
                    cartasCapitulo1.add(carta);
                    break;
                case "fase2":
                    cartasCapitulo2.add(carta);
                    break;
                case "fase3":
                    cartasCapitulo3.add(carta);
                    break;
            }
        }
    }

    // Mezclar las cartas de cada capítulo
    private void mezclarCartasCapitulos() {
        Collections.shuffle(cartasCapitulo1);
        Collections.shuffle(cartasCapitulo2);
        Collections.shuffle(cartasCapitulo3);
        
     // Eliminar las 3 últimas cartas de cada lista si tienen más de 3 cartas para evitar errores
        if (cartasCapitulo1.size() > 3) {
            cartasCapitulo1 = cartasCapitulo1.subList(0, cartasCapitulo1.size() - 3);
        }
        if (cartasCapitulo2.size() > 3) {
            cartasCapitulo2 = cartasCapitulo2.subList(0, cartasCapitulo2.size() - 3);
        }
        if (cartasCapitulo3.size() > 3) {
            cartasCapitulo3 = cartasCapitulo3.subList(0, cartasCapitulo3.size() - 3);
        }
    }
    
    public List<Carta> obtenerCartasDelCapituloActual() {
        switch (capitulo) {
            case "fase1":
                return cartasCapitulo1;
            case "fase2":
                return cartasCapitulo2;
            case "fase3":
                return cartasCapitulo3;
            default:
                return new ArrayList<>();
        }
    }
    public Carta robarCarta(String capitulo) {
        List<Carta> cartasDelCapitulo;

        switch (capitulo) {
            case "fase1":
                cartasDelCapitulo = cartasCapitulo1;
                break;
            case "fase2":
                cartasDelCapitulo = cartasCapitulo2;
                break;
            case "fase3":
                cartasDelCapitulo = cartasCapitulo3;
                break;
            default:
                throw new IllegalArgumentException("Capítulo inválido: " + capitulo);
        }

        if (!cartasDelCapitulo.isEmpty()) {
            return cartasDelCapitulo.remove(0); // Robar la primera carta de la lista
            
            
            
            
        } else {
            System.out.println("No hay más cartas en " + capitulo);
            return null;
        }
        
       
    }
    
	
    public void inicializarLugaresClave() {
        lugaresclave = new ArrayList<>();
        lugaresclave.add(new LugarClave(1, "Territorio Arnor", "3fuerza + 2valor +1corona", "TorreArnor+soldado2+2movimiento", "/Images/LugarClave/TerritorioArnor.png"));
        lugaresclave.add(new LugarClave(2, "Territorio Enedwaith", "2astucia+3valor+1sabiduria", "TorreEnedwaith+avance+1", "/Images/LugarClave/TerritorioEnedwaith.png"));
        lugaresclave.add(new LugarClave(3, "Territorio Gondor", "3astucia+1fuerza+2sabiduria", "TorreGondor+avance+2", "/Images/LugarClave/TerritorioGondor.png"));
        lugaresclave.add(new LugarClave(4, "Territorio Lindon", "3fuerza+2corona+1sabiduria", "TorreLindon", "/Images/LugarClave/TerritorioLindon.png"));
        lugaresclave.add(new LugarClave(5, "Territorio Mordor", "3valor+2corona+1sabiduria", "TorreMordor", "/Images/LugarClave/TerritorioMordor.png"));
        lugaresclave.add(new LugarClave(6, "Territorio Rhovanion", "1astucia+2fuerza+1valor+1corona+1sabiduria", "TorreRhovanion+5oro+1movimiento", "/Images/LugarClave/TerritorioRhovanion.png"));
        lugaresclave.add(new LugarClave(7, "Territorio Rohan", "3astucia+2corona+1sabiduria", "TorreRohan+soldado3", "/Images/LugarClave/TerritorioRohan.png"));
    }
    
    
    //mezclar LugaresClave
    public void mezclarLugaresClave() {
        Collections.shuffle(lugaresclave);
    }
    
    //seleccionar 3 lugares clave
    public List<LugarClave> seleccionarTresLugaresClave() {
        if (lugaresclave.size() >= 3) {
            lugaresClaveSeleccionados = new ArrayList<>(lugaresclave.subList(0, 3)); // Seleccionar los primeros 3
        } else {
            lugaresClaveSeleccionados = new ArrayList<>(); // Si no hay suficientes lugares clave
        }
        return lugaresClaveSeleccionados;
    }
    public List<LugarClave> getLugaresClaveSeleccionados() {
        return lugaresClaveSeleccionados;
    }

    
    //Anadir razas
    public boolean obtenerFichaDeRaza(String nombreRaza) {
        for (Razas raza : razas) {
            if (raza.getNombre().equalsIgnoreCase(nombreRaza)) {
                int fichasRestantes = fichasDisponibles.getOrDefault(raza, 0);
                if (fichasRestantes > 0) {
                    fichasDisponibles.put(raza, fichasRestantes - 1); // Resta una ficha
                    return true; // Ficha obtenida
                }
                return false; // Sin fichas disponibles
            }
        }
        return false; // Raza no encontrada
    }
    // Inicializar y mostrar razas
    private void initializeRazas() {
        // Inicializar lista de razas
        razas = new ArrayList<>();
        razas.add(new Razas(1, "Hobbits", getClass().getResource("/Images/Fichas/FichaHobbits.png").toExternalForm()));
        razas.add(new Razas(2, "Enanos", getClass().getResource("/Images/Fichas/FichaEnanos.png").toExternalForm()));
        razas.add(new Razas(3, "Humanos", getClass().getResource("/Images/Fichas/FichaHumanos.png").toExternalForm()));
        razas.add(new Razas(4, "Elfos", getClass().getResource("/Images/Fichas/FichaElfos.png").toExternalForm()));
        razas.add(new Razas(5, "Magos", getClass().getResource("/Images/Fichas/FichaMagos.png").toExternalForm()));
        razas.add(new Razas(6, "Ents", getClass().getResource("/Images/Fichas/FichaEnts.png").toExternalForm()));

        // Inicializar mapa de fichas disponibles
        fichasDisponibles = new HashMap<>();
        for (Razas raza : razas) {
            fichasDisponibles.put(raza, 3); // Cada raza tiene 3 fichas inicialmente
        }
    }
    public int consultarFichasDeRaza(String nombreRaza) {
        for (Razas raza : razas) {
            if (raza.getNombre().equalsIgnoreCase(nombreRaza)) {
                return fichasDisponibles.getOrDefault(raza, 0);
            }
        }
        return -1; // Raza no encontrada
    }
    
    
}