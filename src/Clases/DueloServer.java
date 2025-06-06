package Clases;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servidor principal del juego Duelo por la Tierra Media.
 * Acepta conexiones de dos clientes, gestiona la sincronización de turnos
 * y distribuye cartas desde el tablero según las acciones de los jugadores.
 * 
 * Cada cliente se maneja en un hilo independiente a través de {@code ClienteHandler}.
 * 
 * @author Alejandro
 * @version 1.0
 */


public class DueloServer {
    private static final Logger LOGGER = Logger.getLogger(DueloServer.class.getName());
    private final int puerto;
    private final List<ObjectOutputStream> clientes = new CopyOnWriteArrayList<>();
    private Tablero tablero;

    /**
     * Crea un servidor en el puerto especificado.
     * 
     * @param puerto Puerto en el que escuchará el servidor.
     */
    
    public DueloServer(final int puerto) {
        this.puerto = puerto;
    }

    /**
     * Inicia el servidor y espera la conexión de dos clientes.
     * Cada cliente es manejado en un hilo separado.
     */
    
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            for (int i = 0; i < 2; i++) {
                final Socket socket = serverSocket.accept();
                final ClienteHandler handler = new ClienteHandler(socket, i);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar el servidor", e);
        }
    }

    
    /**
     * Manejador interno que gestiona cada cliente conectado.
     * Procesa los mensajes entrantes y envía las respuestas correspondientes.
     */
    
    private class ClienteHandler implements Runnable {
        private final Socket socket;
        private ObjectOutputStream salida;
        private ObjectInputStream entrada;
        private final int indiceJugador;

        public ClienteHandler(final Socket socket, final int indiceJugador) {
            this.socket = socket;
            this.indiceJugador = indiceJugador;
        }

        
        /**
         * Lógica principal del cliente:
         * - Recibe objetos del cliente.
         * - Procesa peticiones de robo de carta.
         * - Envía la carta o mensaje de error.
         * - Finaliza la partida si no quedan cartas.
         */
        
        public void run() {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.salida = out;
                this.entrada = in;

                synchronized (DueloServer.this) {
                    clientes.add(salida);

                    if (clientes.size() == 2) {
                        tablero = new Tablero(1, "modoPiramidal", "fase1");
                        try {
                            clientes.get(0).writeObject(new Mensajes(Mensajes.Tipo.TURNO_JUGADOR, "JUGADOR1"));
                            clientes.get(1).writeObject(new Mensajes(Mensajes.Tipo.TURNO_JUGADOR, "JUGADOR2"));
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error al enviar mensajes de turno", e);
                        }
                    }
                }

                while (true) {
                    final Object recibido = entrada.readObject();
                    if (recibido instanceof Mensajes) {
                        final Mensajes mensaje = (Mensajes) recibido;

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
                                for (final ObjectOutputStream cliente : clientes) {
                                    cliente.writeObject(new Mensajes(Mensajes.Tipo.FIN_PARTIDA));
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
                    socket.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Error al cerrar socket del cliente", e);
                }
            }
        }
    }
} 
