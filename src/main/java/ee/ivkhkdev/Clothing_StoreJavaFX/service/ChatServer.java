
package ee.ivkhkdev.Clothing_StoreJavaFX.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatServer {
    private final ChatMessageService messageService;
    private ServerSocket serverSocket;
    private final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
    private volatile boolean running;

    public ChatServer(ChatMessageService messageService) {
        this.messageService = messageService;
    }

    /** Попытаться запустить сервер при старте приложения */
    @EventListener(ApplicationReadyEvent.class)
    public void startServer() {
        try {
            start(12345);
        } catch (BindException e) {
            // порт уже занят — считаем, что сервер уже запущен где-то ещё
            System.out.println("ChatServer: порт 12345 уже занят, не запускаю второй сервер");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("ChatServer started on port " + port);
        System.out.println("Нажмите Enter для остановки...");

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

    public void broadcast(String raw, ClientHandler sender) {
        System.out.println("Received: " + raw);
        // сохраняем в БД
        String senderName;
        String content;
        if (raw.startsWith("***")) {
            senderName = "SYSTEM";
            content = raw;
        } else {
            int idx = raw.indexOf(':');
            senderName = raw.substring(0, idx).trim();
            content = raw.substring(idx + 1).trim();
        }
        messageService.save(senderName, content);

        // рассылка всем остальным
        for (ClientHandler c : clients) {
            if (c != sender) {
                c.send(raw);
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

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        clients.forEach(ClientHandler::close);
        clients.clear();
        System.out.println("ChatServer stopped");
    }
}
