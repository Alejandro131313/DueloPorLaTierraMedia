package Controladores;

import Clases.Carta;
import Clases.Mensajes;
import Clases.Tablero;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableroNetworkController {
	
    private static final Logger LOGGER = Logger.getLogger(TableroNetworkController.class.getName());

    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    private TableroController tableroController;
    private Tablero tablero;

    public void setTableroController(final TableroController controller) {
        this.tableroController = controller;
    }

    public void setTablero(final Tablero tablero) {
        this.tablero = tablero;
    }

    public Object leerSiguienteMensaje() throws Exception {
        return entrada.readObject();
    }

    public void conectarConServidor(final String host, final int puerto) {
        try {
            socket = new Socket(host, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con el servidor", e);
        }
    }

    public void inicializarConStreams(final Socket socket, final ObjectInputStream entrada, final ObjectOutputStream salida) {
        this.socket = socket;
        this.entrada = entrada;
        this.salida = salida;
    }

    public void robarCartaDesdeCliente(final int idCarta) {
        try {
        	final Mensajes mensaje = new Mensajes(Mensajes.Tipo.ROBAR_CARTA, idCarta, "cliente");
            salida.writeObject(mensaje);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al enviar mensaje de robo de carta", e);
        }
    }
}