package Clases;

import java.io.*;
import java.net.*;

import Controladores.TableroNetworkController;
import javafx.scene.control.Alert;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Cliente de red para el juego Duelo por la Tierra Media.
 * Se encarga de conectarse al servidor, enviar y recibir objetos, y coordinar con el controlador de red.
 * 
 * Establece conexión mediante sockets y utiliza streams de objetos para la comunicación.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class DueloClient {

    private static final Logger LOGGER = Logger.getLogger(DueloClient.class.getName());
    private String host;
    private int puerto;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    
    private TableroNetworkController redController;

    public TableroNetworkController getRedController() {
        return redController;
    }
    public DueloClient(final String host, final int puerto) {
    	this.host = host;
    	this.puerto = puerto;
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Establece la conexión con el servidor y prepara los streams de entrada y salida.
     */
    
    public void conectar() {
        int reintentos = 5;
        while (reintentos > 0) {
            try {
                socket = new Socket(host, puerto);
                salida = new ObjectOutputStream(socket.getOutputStream());
                salida.flush();
                entrada = new ObjectInputStream(socket.getInputStream());

                redController = new TableroNetworkController();
                redController.inicializarConStreams(socket, entrada, salida);
                LOGGER.info("Conexión establecida con el servidor.");
                return;
            } catch (IOException e) {
                reintentos--;
                LOGGER.warning("Fallo al conectar. Reintentando... (" + reintentos + " restantes)");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }

        LOGGER.severe("No se pudo establecer conexión tras varios intentos.");
        mostrarAlerta("Error de conexión", "No se pudo conectar con el servidor. Inténtalo más tarde.");
    }
    
    /**
     * Envía un mensaje al servidor para robar una carta con el ID especificado.
     * 
     * @param idCarta ID de la carta a robar.
     */
    
    public void enviarRoboCarta(final int idCarta) {
        try {
            final Mensajes mensaje = new Mensajes(Mensajes.Tipo.ROBAR_CARTA, idCarta, "cliente");
            salida.writeObject(mensaje);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con el servidor", e);
        }
    }

  
}