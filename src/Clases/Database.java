	
package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
/*
    
    public static void insertarJugador(String nombre) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try (Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO Jugadores (NombreJugador) VALUES (?)";
            try (PreparedStatement stmt = conn1.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Error al insertar jugador en la BBDD:");
            e.printStackTrace();
        }
        System.out.println("Lista de jugadores registrados:");
        String query = "SELECT idJugadores, NombreJugador FROM Jugadores";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("idJugadores");
                String nombreJugador = rs.getString("NombreJugador");
                System.out.println("ID: " + id + " | Nombre: " + nombreJugador);
            }
        }

    }
*/

}
