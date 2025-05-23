package Clases;

public class DBLogger {

    public static <T> T logExecution(String operacion, DBOperation<T> operacionDB) {
        long start = System.currentTimeMillis();
        try {
            T resultado = operacionDB.ejecutar();
            long end = System.currentTimeMillis();
            System.out.println("[BBDD] Operación '" + operacion + "' completada en " + (end - start) + " ms.");
            return resultado;
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            System.err.println("[BBDD] Error en operación '" + operacion + "' tras " + (end - start) + " ms.");
            e.printStackTrace();
            return null;
        }
    }

    @FunctionalInterface
    public interface DBOperation<T> {
        T ejecutar() throws Exception;
    }
}
