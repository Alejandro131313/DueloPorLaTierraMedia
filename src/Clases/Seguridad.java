package Clases;

import org.mindrot.jbcrypt.BCrypt;

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