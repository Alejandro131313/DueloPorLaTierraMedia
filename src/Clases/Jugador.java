

   
    
package Clases;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
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

    /**
     * Método para escribir un archivo HTML con la información de múltiples jugadores.
     * 
     * @param nombreFichero Nombre del archivo HTML.
     * @param jugadores     Lista de jugadores a incluir.
     */
    public static void escribirEnFicheroHTML(String nombreFichero, Jugador... jugadores) {
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
            bw.write("<title>Datos de los Jugadores</title>");
            bw.newLine();
            bw.write("<style>");
            bw.newLine();

            // Estilos CSS
            bw.write("body { font-family: 'Arial', sans-serif; background-color: #f5f5f5; color: #333; }");
            bw.newLine();
            bw.write("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
            bw.newLine();
            bw.write("th, td { padding: 10px; text-align: center; border: 1px solid #ccc; }");
            bw.newLine();
            bw.write("th { background-color: #4CAF50; color: white; }");
            bw.newLine();
            bw.write("tr:nth-child(even) { background-color: #f9f9f9; }");
            bw.newLine();

            bw.write("</style>");
            bw.newLine();
            bw.write("</head>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<h1 style=\"text-align: center;\">Datos de los Jugadores</h1>");
            bw.newLine();

            // Inicio de la tabla
            bw.write("<table>");
            bw.newLine();
            bw.write("<tr><th>ID</th><th>Nombre</th><th>Edad</th><th>Victorias</th></tr>");
            bw.newLine();

            // Agregar una fila por cada jugador
            for (Jugador jugador : jugadores) {
                bw.write("<tr>");
                bw.write("<td>" + jugador.getId() + "</td>");
                bw.write("<td>" + jugador.getNombre() + "</td>");
                bw.write("<td>" + jugador.getEdad() + "</td>");
                bw.write("<td>" + jugador.getNumeroVictorias() + "</td>");
                bw.write("</tr>");
                bw.newLine();
            }

            // Fin de la tabla
            bw.write("</table>");
            bw.newLine();
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el fichero: " + e.getMessage());
        }

        // Intentar abrir el archivo automáticamente
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
