package Clases;

import org.mindrot.jbcrypt.BCrypt;


/**
 * Clase para la gestión segura de contraseñas usando el algoritmo BCrypt.
 * Permite cifrar contraseñas y verificar su validez de forma segura.
 * 
 * Utiliza el coste 12 por defecto, lo que proporciona una buena relación entre seguridad y rendimiento.
 * 
 * Esta clase es utilizada en el proceso de registro e inicio de sesión de usuarios.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class Seguridad {

    private static final int COSTE_HASH = 12;

    public static String encriptar(final String contrasena) {
        if (contrasena == null) {
            throw new IllegalArgumentException("La contraseña no puede ser null.");
        }
        return BCrypt.hashpw(contrasena, BCrypt.gensalt(COSTE_HASH));
    }

    public static boolean verificar(final String contrasena, final String hash) {
        if (contrasena == null || hash == null) {
            throw new IllegalArgumentException("La contraseña y el hash no pueden ser null.");
        }
        return BCrypt.checkpw(contrasena, hash);
    }
}