package Clases;

import java.io.*;
import java.net.*;

import Controladores.TableroNetworkController;

public class DueloClient {
    private String host;
    private int puerto;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    
    private TableroNetworkController redController;

    public TableroNetworkController getRedController() {
        return redController;
    }
    public DueloClient(String host, int puerto) {
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
            System.err.println("error al conectar con el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void enviarRoboCarta(int idCarta) {
        try {
            Mensajes mensaje = new Mensajes(Mensajes.Tipo.ROBAR_CARTA, idCarta, "cliente");
            salida.writeObject(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
}