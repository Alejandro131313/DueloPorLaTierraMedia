package Controladores;

import Clases.Carta;
import Clases.Mensajes;
import Clases.Tablero;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TableroNetworkController {

    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    private TableroController tableroController;
    private Tablero tablero;

    public void setTableroController(TableroController controller) {
        this.tableroController = controller;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Object leerSiguienteMensaje() throws Exception {
        return entrada.readObject();
    }

    public void conectarConServidor(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializarConStreams(Socket socket, ObjectInputStream entrada, ObjectOutputStream salida) {
        this.socket = socket;
        this.entrada = entrada;
        this.salida = salida;
    }

    public void robarCartaDesdeCliente(int idCarta) {
        try {
            Mensajes mensaje = new Mensajes(Mensajes.Tipo.ROBAR_CARTA, idCarta, "cliente");
            salida.writeObject(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}