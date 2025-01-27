package Clases;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    	 if (!nombreFichero.endsWith(".html")) {
    	        nombreFichero += ".html";
    	    }
    	 try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
    	        bw.write("<!DOCTYPE html>");
    	        bw.newLine();
    	        bw.write("<html lang=\"es\">");
    	        bw.newLine();
    	        bw.write("<head>");
    	        bw.newLine();
    	        bw.write("<meta charset=\"UTF-8\">");
    	        bw.newLine();
    	        bw.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
    	        bw.newLine();
    	        bw.write("<title>Datos del Usuario</title>");
    	        bw.newLine();
    	        bw.write("<style>");
    	        bw.newLine();
    	        
    	        // Estilo de la página
    	        bw.write("body {");
    	        bw.write("    font-family: 'Garamond', serif;");
    	        bw.write("    background: url('https://www.example.com/fondo-papel.jpg') no-repeat center center fixed;");
    	        bw.write("    background-size: cover;");
    	        bw.write("    color: #f4f4f9;");
    	        bw.write("    margin: 0;");
    	        bw.write("    padding: 0;");
    	        bw.write("}");
    	        bw.newLine();

    	        // Estilo del título
    	        bw.write("h1 {");
    	        bw.write("    color: #d4af37;");
    	        bw.write("    text-align: center;");
    	        bw.write("    font-size: 3em;");
    	        bw.write("    margin-top: 50px;");
    	        bw.write("    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);");
    	        bw.write("}");
    	        bw.newLine();

    	        // Estilo de la tabla
    	        bw.write("table {");
    	        bw.write("    width: 60%;");
    	        bw.write("    margin: 50px auto;");
    	        bw.write("    border-collapse: collapse;");
    	        bw.write("    background-color: rgba(0, 0, 0, 0.7);");
    	        bw.write("    border-radius: 10px;");
    	        bw.write("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.6);");
    	        bw.write("}");
    	        bw.newLine();

    	        // Estilo de celdas de tabla
    	        bw.write("td, th {");
    	        bw.write("    padding: 15px;");
    	        bw.write("    text-align: left;");
    	        bw.write("    border: 1px solid #d4af37;");
    	        bw.write("    font-size: 1.2em;");
    	        bw.write("}");
    	        bw.newLine();

    	        // Estilo de los encabezados de la tabla
    	        bw.write("th {");
    	        bw.write("    background-color: #4b2a11;");
    	        bw.write("    color: #e0c77c;");
    	        bw.write("    font-size: 1.4em;");
    	        bw.write("    text-transform: uppercase;");
    	        bw.write("    border-bottom: 2px solid #d4af37;");
    	        bw.write("}");
    	        bw.newLine();

    	        // Estilo de las filas de la tabla
    	        bw.write("tr:nth-child(even) {");
    	        bw.write("    background-color: rgba(0, 0, 0, 0.8);");
    	        bw.write("}");
    	        bw.newLine();

    	        bw.write("</style>");
    	        bw.newLine();
    	        bw.write("</head>");
    	        bw.newLine();
    	        bw.write("<body>");
    	        bw.newLine();

    	        // Título de la página
    	        bw.write("<h1>Datos del Usuario</h1>");
    	        bw.newLine();

    	        // Tabla con los datos del jugador
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
    	        return;
    	    }
        try {
            File archivo = new File(nombreFichero);
            if (archivo.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(archivo.toURI());
            } else {
                System.out.println("No se pudo abrir el fichero automáticamente.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar abrir el fichero: " + e.getMessage());
        }
    }


   
    
    
}
