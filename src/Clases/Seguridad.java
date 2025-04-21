package Clases;

import org.mindrot.jbcrypt.BCrypt;

public class Seguridad {

    public static String encriptar(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt(12)); // cost = 12
    }

    public static boolean verificar(String contrasena, String hash) {
        return BCrypt.checkpw(contrasena, hash);
    }
}
