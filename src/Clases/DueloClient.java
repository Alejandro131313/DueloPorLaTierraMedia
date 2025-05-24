package Clases;

import java.io.*;
import java.net.*;

import Controladores.TableroNetworkController;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void conectar() {
        try {
        	socket = new Socket(host, puerto);

        	salida = new ObjectOutputStream(socket.getOutputStream());
        	salida.flush();
        	entrada = new ObjectInputStream(socket.getInputStream());

        	redController = new TableroNetworkController();
        	redController.inicializarConStreams(socket, entrada, salida);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con el servidor", e);
        }
    }
    public void enviarRoboCarta(final int idCarta) {
        try {
            final Mensajes mensaje = new Mensajes(Mensajes.Tipo.ROBAR_CARTA, idCarta, "cliente");
            salida.writeObject(mensaje);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con el servidor", e);
        }
    }

  
}