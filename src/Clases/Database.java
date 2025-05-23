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

    // Inserta un nuevo jugador (nombre sin contraseña)
    public static void insertarJugador(String nombre) throws SQLException {
        DBLogger.logExecution("insertarJugador", () -> {
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
            String query = "SELECT idJugador, NombreJugador FROM Jugadores";
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("idJugador");
                    String nombreJugador = rs.getString("NombreJugador");
                    System.out.println("ID: " + id + " | Nombre: " + nombreJugador);
                }
            }
            return null;
        });
    }

    // Registro: Guarda el hash de la contraseña
    public static boolean insertarUsuario(String usuario, String contrasena, String email) {
        return DBLogger.logExecution("insertarUsuario", () -> {
            String sql = "INSERT INTO Jugadores (NombreJugador, Password, EmailJugador) VALUES (?, ?, ?)";
            String hash = Seguridad.encriptar(contrasena);

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, hash);
                stmt.setString(3, email);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Login: Verifica que el hash de la contraseña coincida
    public static boolean verificarUsuario(String usuario, String contrasena) {
        return DBLogger.logExecution("verificarUsuario", () -> {
            String sql = "SELECT Password FROM Jugadores WHERE NombreJugador = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashGuardado = rs.getString("Password");
                    return Seguridad.verificar(contrasena, hashGuardado);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        });
    }
}

