package Clases;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int id;
    private String disposicionCartas;
    private String capitulo;
    private List<Carta> cartas;

    public Tablero(int id, String disposicionCartas, String capitulo) {
        this.id = id;
        this.disposicionCartas = disposicionCartas;
        this.capitulo = capitulo;
        this.cartas = new ArrayList<>();
        inicializarCartas();
    }

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

    private void inicializarCartas() {
    	


    	cartas.add(new Carta(1, "RecursoFuerza1", "Gris", "0", "fuerza+1", "fase1", "/src/Images/Cartas/1.1.png"));
    	cartas.add(new Carta(2, "RecursoValor1", "Gris", "0", "valor+1", "fase1", "/src/Images/Cartas/1.2.png"));
    	cartas.add(new Carta(3, "RecursoAstucia1", "Gris", "0", "astucia+1", "fase1", "/src/Images/Cartas/1.3.png"));
    	cartas.add(new Carta(4, "RecursoValor2", "Gris", "0", "valor+1", "fase1", "/src/Images/Cartas/1.4.png"));
    	cartas.add(new Carta(5, "RecursoFuerza2", "Gris", "0", "fuerza+1", "fase1", "/src/Images/Cartas/1.5.png"));
    	cartas.add(new Carta(6, "RecursoAstucia2", "Gris", "0", "astucia+1", "fase1", "/src/Images/Cartas/1.6.png"));
    	cartas.add(new Carta(7, "RecursoCorona1", "Gris", "0", "corona+1", "fase1", "/src/Images/Cartas/1.7.png"));
    	cartas.add(new Carta(8, "RecursoSabiduria1", "Gris", "0", "sabiduria+1", "fase1", "/src/Images/Cartas/1.8.png"));
    	cartas.add(new Carta(9, "RazaElfos1", "Verde", "1 sabiduria", "elfos+1", "fase1", "/src/Images/Cartas/1.9.png"));
    	cartas.add(new Carta(10, "RazaEnanos1", "Verde", "1 corona", "enanos+1", "fase1", "/src/Images/Cartas/1.10.png"));
    	cartas.add(new Carta(11, "RazaHumanos1", "Verde", "0", "humanos+1", "fase1", "/src/Images/Cartas/1.11.png"));
    	cartas.add(new Carta(12, "RazaHobbits1", "Verde", "0", "hobbits+1", "fase1", "/src/Images/Cartas/1.12.png"));
    	cartas.add(new Carta(13, "Batalla1", "Roja", "1 astucia", "Rohan+1 || Gondor+1", "fase1", "/src/Images/Cartas/1.13.png"));
    	cartas.add(new Carta(14, "Batalla2", "Roja", "0", "Enedwatih+1 || Rhovanion+1", "fase1", "/src/Images/Cartas/1.14.png"));
    	cartas.add(new Carta(15, "Batalla3", "Roja", "0", "Lindon +1 || Arnor+1", "fase1", "/src/Images/Cartas/1.15.png"));
    	cartas.add(new Carta(16, "Mision1", "Azul", "1 fuerza", "avance+1", "fase1", "/src/Images/Cartas/1.16.png"));
    	cartas.add(new Carta(17, "Mision2", "Azul", "0", "avance+1", "fase1", "/src/Images/Cartas/1.17.png"));
    	cartas.add(new Carta(18, "Mision3", "Azul", "1 valor", "avance+1", "fase1", "/src/Images/Cartas/1.18.png"));
    	cartas.add(new Carta(19, "Mision4", "Azul", "1 oro", "avance+1", "fase1", "/src/Images/Cartas/1.19.png"));
    	cartas.add(new Carta(20, "Oro1", "Amarilla", "0", "oro+1", "fase1", "/src/Images/Cartas/1.20.png"));
    	cartas.add(new Carta(21, "Oro2", "Amarilla", "0", "oro+1", "fase1", "/src/Images/Cartas/1.21.png"));
    	cartas.add(new Carta(22, "Oro3", "Amarilla", "0", "oro+1", "fase1", "/src/Images/Cartas/1.22.png"));
    	cartas.add(new Carta(23, "Oro4", "Amarilla", "0", "oro+1", "fase1", "/src/Images/Cartas/1.23.png"));
    	cartas.add(new Carta(24, "RecursoSabiduria3", "Gris", "0", "sabiduria+1", "fase2", "/src/Images/Cartas/2.1.png"));
    	cartas.add(new Carta(25, "RecursoCorona2", "Gris", "0", "corona+1", "fase2", "/src/Images/Cartas/2.2.png"));
    	cartas.add(new Carta(26, "RecursoAstucia3", "Gris", "1 oro", "astucia+2", "fase2", "/src/Images/Cartas/2.3.png"));
    	cartas.add(new Carta(27, "RecursoCoronaSabiduria1", "Gris", "1 oro && 1 astucia", "corona+1 || sabiduria+1", "fase2", "/src/Images/Cartas/2.4.png"));
    	cartas.add(new Carta(28, "RecursoFuerza3", "Gris", "1 oro", "fuerza+2", "fase2", "/src/Images/Cartas/2.5.png"));
    	cartas.add(new Carta(29, "RecursoAstuciaFuerzaValor1", "Gris", "1 oro && 1 corona && 1 sabiduria", "astucia+1 || fuerza+1 || valor+1", "fase2", "/src/Images/Cartas/2.6.png"));
    	cartas.add(new Carta(30, "RecursoValor3", "Gris", "1 oro", "valor+2", "fase2", "/src/Images/Cartas/2.7.png"));
    	cartas.add(new Carta(31, "RazaElfos2", "Verde", "1 valor && 1 corona && 1 sabiduria", "elfos+1", "fase2", "/src/Images/Cartas/2.8.png"));
    	cartas.add(new Carta(32, "RazaEnanos2", "Verde", "2 fuerza && 1 valor", "enanos+1", "fase2", "/src/Images/Cartas/2.9.png"));
    	cartas.add(new Carta(33, "RazaHumanos2", "Verde", "2 valor && 1 sabiduria", "humanos+1", "fase2", "/src/Images/Cartas/2.10.png"));
    	cartas.add(new Carta(34, "RazaHobbits2", "Verde", "2 astucia && 1 corona", "hobbits+1", "fase2", "/src/Images/Cartas/2.11.png"));
    	cartas.add(new Carta(35, "Batalla4", "Roja", "2 valor && 1 corona", "Arnor+2 || Rhovanion+2", "fase2", "/src/Images/Cartas/2.12.png"));
    	cartas.add(new Carta(36, "Batalla5", "Roja", "1 astucia && 1 fuerza && 1 valor", "Lindon+2 || Rhovanion+2",  "fase2", "/src/Images/Cartas/2.13.png"));
    	cartas.add(new Carta(37, "Batalla6", "Roja", "2 fuerza && 1 valor", "Lindon+2 || Enedwaith+2", "fase2", "/src/Images/Cartas/2.14.png"));
    	cartas.add(new Carta(38, "Batalla7", "Roja", "2 astucia && 1 sabiduria", "Mordor+2 || Rohan+2", "fase2", "/src/Images/Cartas/2.15.png"));
    	cartas.add(new Carta(39, "Batalla8", "Roja", "3 astucia", "Gondor+2 || Mordor+2", "fase2", "/src/Images/Cartas/2.16.png"));
    	cartas.add(new Carta(40, "Mision5", "Azul", "1 oro", "avance+1", "fase2", "/src/Images/Cartas/2.17.png"));
    	cartas.add(new Carta(41, "Mision6", "Azul", "1 astucia && 1 fuerza", "avance+1", "fase2", "/src/Images/Cartas/2.18.png"));
    	cartas.add(new Carta(42, "Mision7", "Azul", "1 valor && 1 fuerza", "avance+1", "fase2", "/src/Images/Cartas/2.19.png"));
    	cartas.add(new Carta(43, "Mision8", "Azul", "2 fuerza && 1 sabiduria", "avance+2", "fase2", "/src/Images/Cartas/2.20.png"));
    	cartas.add(new Carta(44, "Mision9", "Azul", "1 fuerza && 2 valor", "avance+2", "fase2", "/src/Images/Cartas/2.21.png"));
    	cartas.add(new Carta(45, "Oro5", "Amarilla", "0", "oro+3", "fase2", "/src/Images/Cartas/2.22.png"));
    	cartas.add(new Carta(46, "Oro6", "Amarilla", "0", "oro+4", "fase2", "/src/Images/Cartas/2.23.png"));
    	cartas.add(new Carta(47, "RazaMagos1", "Verde", "2 astucia && 2 corona", "magos+1", "fase3", "/src/Images/Cartas/3.1.png"));
    	cartas.add(new Carta(48, "RazaMagos2", "Verde", "2 fuerza && 1 corona && 1 sabiduria", "magos+1", "fase3", "/src/Images/Cartas/3.2.png"));
    	cartas.add(new Carta(49, "RazaEnts1", "Verde", "2 fuerza && 2 sabiduria", "ents+1", "fase3", "/src/Images/Cartas/3.3.png"));
    	cartas.add(new Carta(50, "RazaEnts2", "Verde", "2 valor && 1 corona && 1 sabiduria", "ents+1",  "fase3", "/src/Images/Cartas/3.4.png"));
    	cartas.add(new Carta(51, "Batalla9", "Roja", "1 astucia && 1 fuerza && 2 corona && 1 sabiduria", "Gondor+3 || Arnor+3", "fase3", "/src/Images/Cartas/3.5.png"));
		cartas.add(new Carta(52, "Batalla10", "Roja", "2 fuerza && 2 valor", "Enedwaith+3 || Rohan+3", "fase3", "/src/Images/Cartas/3.6.png"));
		cartas.add(new Carta(53, "Batalla11", "Roja", "2 astucia && 1 valor && 2 sabiduria", "Lindon+3 || Mordor+3", "fase3", "/src/Images/Cartas/3.7.png"));
		cartas.add(new Carta(54, "Batalla12", "Roja", "3 astucia && 2 corona", "Rhovanion+3 || Gondor+3", "fase3", "/src/Images/Cartas/3.8.png"));
		cartas.add(new Carta(55, "Batalla13", "Roja", "2 astucia && 2 valor", "Enedwaith+3 || Arnor +3", "fase3", "/src/Images/Cartas/3.9.png"));
		cartas.add(new Carta(56, "Batalla14", "Roja", "1 astucia && 2 fuerza && 2 valor", "Rhovanion+3 || Mordor+3", "fase3", "/src/Images/Cartas/3.10.png"));
		cartas.add(new Carta(57, "Mision10", "Azul", "3 astucia && 1 valor", "avance+2", "fase3", "/src/Images/Cartas/3.11.png"));
		cartas.add(new Carta(58, "Mision11", "Azul", "2 fuerza && 1 corona", "avance+2", "fase3", "/src/Images/Cartas/3.12.png"));
		cartas.add(new Carta(59, "Mision12", "Azul", "1 astucia && 3 fuerza", "avance+2", "fase3", "/src/Images/Cartas/3.13.png"));
		cartas.add(new Carta(60, "Mision13", "Azul", "2 astucia && 2 valor", "avance+2", "fase3", "/src/Images/Cartas/3.14.png"));
		cartas.add(new Carta(61, "Mision14", "Azul", "3 oro", "avance+3", "fase3", "/src/Images/Cartas/3.15.png"));
		cartas.add(new Carta(62, "Oro7", "Amarilla", "1 astucia && 1 corona && 1 sabiduria", "oro+5", "fase3", "/src/Images/Cartas/3.16.png"));
		cartas.add(new Carta(63, "Oro8", "Amarilla", "2 astucia && 1 valor", "oro+5", "fase3", "/src/Images/Cartas/3.17.png"));
		cartas.add(new Carta(64, "Sabotaje1", "Morada", "3 valor && 1 corona", "soldado-1 && oro-2", "fase3", "/src/Images/Cartas/3.18.png"));
		cartas.add(new Carta(65, "Sabotaje2", "Morada", "2 valor && 1 corona && 1 sabiduria", "movimiento3", "fase3", "/src/Images/Cartas/3.19.png"));
		cartas.add(new Carta(66, "Sabotaje3", "Morada", "1 astucia && 1 fuerza && 1 valor && 1 sabiduria", "oro-1 && movimiento2", "fase3", "/src/Images/Cartas/3.20.png"));
		cartas.add(new Carta(67, "Sabotaje4", "Morada", "2 fuerza && 1 corona", "soldado-1 && oro-2 && movimiento1", "fase3", "/src/Images/Cartas/3.21.png"));
		cartas.add(new Carta(68, "Sabotaje5", "Morada", "3 fuerza && 1 sabiduria", "soldado-1 && movimiento2", "fase3", "/src/Images/Cartas/3.22.png"));
		cartas.add(new Carta(69, "Sabotaje6", "Morada", "1 fuerza && 1 valor && 1 sabiduria", "soldado-2 && oro-1", "fase3", "/src/Images/Cartas/3.23.png"));
		
		
		
		
        // Agregar una carta de cada tipo
        /*cartas.add(new Carta(1, "Batalla de los Campos del Pelennor", "Rojo", 3, "Espadas", "Aumenta fuerza en combate", "ruta/a/imagen1.png"));
        cartas.add(new Carta(2, "Ataque Sorpresa", "Azul", 2, "Magia", "Permite un movimiento extra", "ruta/a/imagen2.png"));
        cartas.add(new Carta(3, "Misión: Avanzar al Monte del Destino", "Verde", 4, "Misiones", "Avanza en la historia", "ruta/a/imagen3.png"));
        cartas.add(new Carta(4, "Recurso: Minas de Moria", "Amarillo", 1, "Recursos", "Genera oro adicional", "ruta/a/imagen4.png"));*/
    }
}