package Clases;
import java.util.logging.Logger;


/**
 * Clase para registrar operaciones de base de datos y medir su tiempo de ejecución.
 * 
 * Permite envolver cualquier operación en una función, mostrando en consola 
 * cuánto tarda y si fue exitosa o no.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class DBLogger {
    private static final Logger LOGGER = Logger.getLogger(DBLogger.class.getName());

    public static <T> T logExecution(final String operacion, final DBOperation<T> operacionDB) {
        final long start = System.currentTimeMillis();
        try {
            T resultado = operacionDB.ejecutar();
            final long end = System.currentTimeMillis();
            LOGGER.info("[BBDD] Operación '" + operacion + "' completada en " + (end - start) + " ms.");
            return resultado;
        } catch (Exception e) {
            final long end = System.currentTimeMillis();
            LOGGER.info("[BBDD] Error en operación '" + operacion + "' tras " + (end - start) + " ms.");
            return null;
        }
    }

    @FunctionalInterface
    public interface DBOperation<T> {
        T ejecutar() throws Exception;
    }
}
