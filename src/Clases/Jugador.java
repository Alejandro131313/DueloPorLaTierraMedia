package Clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    
    
    //Metodo que escriba en un fichero    BufferWritter
    
    
    public void escribirEnFicheroHTML(String nombreFichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
            bw.write("<!DOCTYPE html>");
            bw.newLine();
            bw.write("<html>");
            bw.newLine();
            bw.write("<head>");
            bw.newLine();
            bw.write("<title>Datos del Usuario</title>");
            bw.newLine();
            bw.write("<style>");
            bw.newLine();
            bw.write("body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; }");
            bw.newLine();
            bw.write("h1 { color: #4CAF50; }");
            bw.newLine();
            bw.write("table { width: 50%; margin: auto; border-collapse: collapse; }");
            bw.newLine();
            bw.write("td { padding: 10px; border: 1px solid #ddd; text-align: left; }");
            bw.newLine();
            bw.write("th { background-color: #4CAF50; color: white; padding: 10px; }");
            bw.newLine();
            bw.write("</style>");
            bw.newLine();
            bw.write("</head>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<h1>Datos del Usuario</h1>");
            bw.newLine();
            bw.write("<table>");
            bw.newLine();
            bw.write("<tr><th>Atributo</th><th>Valor</th></tr>");
            bw.newLine();
            bw.write("<tr><td>Nombre</td><td>" + nombre + "</td></tr>");
            bw.newLine();
            bw.write("<tr><td>Edad</td><td>" + edad + "</td></tr>");
            bw.newLine();
            bw.write("<tr><td>Número de victorias</td><td>" + numeroVictorias + "</td></tr>");
            bw.newLine();
            bw.write("</table>");
            bw.newLine();
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el fichero: " + e.getMessage());
        }
    }


   
    
    
}
