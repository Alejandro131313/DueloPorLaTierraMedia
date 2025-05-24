package Clases;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DueloServer {
    private static final Logger LOGGER = Logger.getLogger(DueloServer.class.getName());
    private final int puerto;
    private final List<ObjectOutputStream> clientesConectados = new CopyOnWriteArrayList<ObjectOutputStream>();
    private Tablero tablero;

    public DueloServer(final int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            int numConexiones = 0;
            while (numConexiones < 2) {
            	final Socket socket = serverSocket.accept();
                new Thread(new ClienteHandler(socket, numConexiones)).start();
                numConexiones++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar el servidor", e);
        }
    }

    private class ClienteHandler implements Runnable {
        private final Socket socket;
        private ObjectOutputStream salida;
        private ObjectInputStream entrada;
        private final int indiceJugador;

        public ClienteHandler(final Socket socket, final int indiceJugador) {
            this.socket = socket;
            this.indiceJugador = indiceJugador;
        }

        public void run() {
            try {
            	salida = new ObjectOutputStream(socket.getOutputStream());
            	salida.flush();
            	entrada = new ObjectInputStream(socket.getInputStream());

                synchronized (DueloServer.this) {
                    clientesConectados.add(salida);

                    if (clientesConectados.size() == 2) {
                        tablero = new Tablero(1, "modoPiramidal", "fase1");

                        try {
                            clientesConectados.get(0).writeObject(new Mensajes(Mensajes.Tipo.TURNO_JUGADOR, "JUGADOR1"));
                            clientesConectados.get(1).writeObject(new Mensajes(Mensajes.Tipo.TURNO_JUGADOR, "JUGADOR2"));
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error al enviar mensajes de turno", e);
                        }
                    }
                }

                while (true) {
                	final Object recibido = entrada.readObject();
                    if (recibido instanceof Mensajes) {
                        Mensajes mensaje = (Mensajes) recibido;

                        if (mensaje.getTipo() == Mensajes.Tipo.ROBAR_CARTA) {
                        	final int idMensaje = mensaje.getIdCarta();
                            Carta carta = null;
                            final List<Carta> cartas = tablero.obtenerCartasDelCapituloActual();

                            for (final Carta c : cartas) {
                                if (c.getId() == idMensaje) {
                                    carta = c;
                                    break;
                                }
                            }

                            if (carta != null) {
                                cartas.remove(carta);
                                salida.writeObject(new Mensajes(Mensajes.Tipo.RESPUESTA_CARTA, carta));
                            } else {
                                salida.writeObject(new Mensajes(Mensajes.Tipo.CARTA_INVALIDA, "Carta no válida"));
                            }
                            if (cartas.isEmpty()) {
                                for (final ObjectOutputStream out : clientesConectados) {
                                    out.writeObject(new Mensajes(Mensajes.Tipo.FIN_PARTIDA));
                                }
                                break;
                            }
                        }
                    }
                }

                LOGGER.info("Partida finalizada");

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error durante la ejecución del cliente", e);
            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Error al cerrar socket del cliente", e);
                }
            }
        }
    }
}
