package ee.ivkhkdev.Clothing_StoreJavaFX.controller.chat;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class ChatClient {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Thread readerThread;

    public ChatClient(String host, int port, String user, Consumer<String> onMessage) throws IOException {
        this.socket = new Socket(host, port);
        this.in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Запускаем приёмник **до** отправки уведомления о входе,
        // чтобы не получать его же назад
        readerThread = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    onMessage.accept(line);
                }
            } catch (IOException ignored) {}
        }, "ChatClient-Reader");
        readerThread.setDaemon(true);
        readerThread.start();

        // Уведомляем о входе
        send("*** " + user + " присоединился к чату ***");
    }

    public void send(String msg) {
        try {
            out.write(msg);
            out.newLine();
            out.flush();
        } catch (IOException ignored) {}
    }

    public void close(String user) {
        // Уведомляем о выходе
        send("*** " + user + " вышел из чата ***");
        try { socket.close(); } catch (IOException ignored) {}
        readerThread.interrupt();
    }
}
