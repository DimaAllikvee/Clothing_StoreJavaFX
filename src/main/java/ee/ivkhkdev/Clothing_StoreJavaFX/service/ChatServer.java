package ee.ivkhkdev.Clothing_StoreJavaFX.service;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Простой сокет-сервер для чата.
 */
public class ChatServer {
    private ServerSocket serverSocket;
    private final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
    private volatile boolean running = false;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("ChatServer started on port " + port);
        System.out.println("Нажмите Enter для остановки...");

        // Принимаем новых клиентов
        new Thread(() -> {
            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(socket);
                    clients.add(handler);
                    new Thread(handler).start();
                } catch (IOException e) {
                    if (running) e.printStackTrace();
                }
            }
        }, "ChatServer-Accept").start();
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        for (ClientHandler c : clients) c.close();
        clients.clear();
        System.out.println("ChatServer stopped");
    }

    public void broadcast(String message, ClientHandler sender) {
        // Каждое полученное сообщение логируем отдельно
        System.out.println("Received: " + message);
        for (ClientHandler c : clients) {
            if (c != sender) {
                c.send(message);
            }
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private final BufferedReader in;
        private final BufferedWriter out;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    broadcast(line, this);
                }
            } catch (IOException ignored) {
            } finally {
                close();
            }
        }

        void send(String msg) {
            try {
                out.write(msg);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                close();
            }
        }

        void close() {
            clients.remove(this);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    public static void main(String[] args) throws Exception {
        ChatServer server = new ChatServer();
        server.start(12345);
        System.in.read();
        server.stop();
    }
}
