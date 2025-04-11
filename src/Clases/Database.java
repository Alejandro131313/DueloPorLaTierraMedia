	
package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASS = "";
    
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
    public static boolean insertarUsuario(String usuario, String contrasena, String email) {
        String sql = "INSERT INTO Jugadores (NombreJugador, Password, EmailJugador) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, email);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verificarUsuario(String usuario, String contrasena) {
        String sql = "SELECT * FROM Jugadores WHERE NombreJugador = ? AND Password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
