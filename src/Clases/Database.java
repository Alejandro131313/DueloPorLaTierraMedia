package Clases;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para realizar operaciones sobre la base de datos del juego.
 * Permite insertar jugadores, registrar usuarios con contraseña cifrada y verificar credenciales.
 * 
 * 
 * @author Alejandro
 * @version 1.0
 */

public class Database {
	
    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
	
    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASS = "";

    
    /**
     * Inserta un nuevo jugador en la base de datos.
     * Solo se almacena el nombre del jugdor, sin contraseña ni email.
     * 
     * Tambien imprime en consola la lista de jugadores existentes tras la insercion.
     * 
     * @param nombre Nombre del jugador a insertar.
     * @throws SQLException si ocurre un error al conectarse o ejecutar la consulta.
     */
    
    
    // Inserta un nuevo jugador (nombre sin contraseña)
    public static void insertarJugador(final String nombre) throws SQLException {
        DBLogger.logExecution("insertarJugador", () -> {
            final String insertSql = "INSERT INTO Jugadores (NombreJugador) VALUES (?)";
            final String selectSql = "SELECT idJugador, NombreJugador FROM Jugadores";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

                // Inserción
                try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                    stmt.setString(1, nombre);
                    stmt.executeUpdate();
                }

                // Mostrar jugadores
                LOGGER.info("Lista de jugadores registrados:");
                try (Statement statment = conn.createStatement();
                     ResultSet resultSet = statment.executeQuery(selectSql)) {

                    while (resultSet.next()) {
                        final int idJugdor = resultSet.getInt("idJugador");
                        final String nombreJugador = resultSet.getString("NombreJugador");
                        LOGGER.info("ID: " + idJugdor + " | Nombre: " + nombreJugador);
                    }
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al insertar jugador en la BBDD", e);
            }

            return null;
        });
    }

    
    /**
     * Inserta un nuevo usuario en la base de datos.
     * Almacena el nombre de usuario, el email y el hash de la contraseña usando {@link Seguridad.encriptar}.
     * 
     * @param usuario    Nombre del usuario.
     * @param contrasena Contraseña en texto plano que será cifrada.
     * @param email      Correo electrónico del usuario.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso de error.
     */
    
    
    // Registro: Guarda el hash de la contraseña
    public static boolean insertarUsuario(final String usuario, final String contrasena, final String email) {
        return DBLogger.logExecution("insertarUsuario", () -> {
            final String sql = "INSERT INTO Jugadores (NombreJugador, Password, EmailJugador) VALUES (?, ?, ?)";
            final String hash = Seguridad.encriptar(contrasena);

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario);
                stmt.setString(2, hash);
                stmt.setString(3, email);
                stmt.executeUpdate();
                return true;

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al insertar usuario", e);
                return false;
            }
        });
    }

    
    
    /**
     * Verifica si un usuario existe y si su contraseña coincide con la almacenada.
     * Se compara la contraseña ingresada con el hash guardado mediante {@link Seguridad.verificar}.
     * 
     * @param usuario    Nombre del usuario.
     * @param contrasena Contraseña en texto plano a verificar.
     * @return {@code true} si las credenciales son correctas, {@code false} en caso contrario.
     */
    // Login: Verifica que el hash de la contraseña coincida
    public static boolean verificarUsuario(final String usuario, final String contrasena) {
        return DBLogger.logExecution("verificarUsuario", () -> {
            final String sql = "SELECT Password FROM Jugadores WHERE NombreJugador = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario);
                try (ResultSet resultSt = stmt.executeQuery()) {
                    if (resultSt.next()) {
                        final String hashGuardado = resultSt.getString("Password");
                        return Seguridad.verificar(contrasena, hashGuardado);
                    }
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al verificar usuario", e);
            }

            return false;
        });
    }
}