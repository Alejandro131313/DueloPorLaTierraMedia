package Clases;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DueloServer {
    private int puerto;
    private final List<ObjectOutputStream> clientesConectados = new CopyOnWriteArrayList<ObjectOutputStream>();
    private Tablero tablero;

    public DueloServer(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            int numConexiones = 0;

            while (numConexiones < 2) {
                Socket socket = serverSocket.accept();
                new Thread(new ClienteHandler(socket, numConexiones)).start();
                numConexiones++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClienteHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream salida;
        private ObjectInputStream entrada;
        private int indiceJugador;

        public ClienteHandler(Socket socket, int indiceJugador) {
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
                            e.printStackTrace();
                        }
                    }
                }

                while (true) {
                    Object recibido = entrada.readObject();
                    if (recibido instanceof Mensajes) {
                        Mensajes mensaje = (Mensajes) recibido;

                        if (mensaje.getTipo() == Mensajes.Tipo.ROBAR_CARTA) {
                            int id = mensaje.getIdCarta();
                            Carta carta = null;
                            List<Carta> cartas = tablero.obtenerCartasDelCapituloActual();

                            for (Carta c : cartas) {
                                if (c.getId() == id) {
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
                                for (ObjectOutputStream out : clientesConectados) {
                                    out.writeObject(new Mensajes(Mensajes.Tipo.FIN_PARTIDA));
                                }
                                break;
                            }
                        }
                    }
                }

                System.out.println("p artida finalizada");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
