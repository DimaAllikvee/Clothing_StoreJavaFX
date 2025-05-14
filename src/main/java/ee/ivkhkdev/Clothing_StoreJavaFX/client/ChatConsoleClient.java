package ee.ivkhkdev.Clothing_StoreJavaFX.client;

import ee.ivkhkdev.Clothing_StoreJavaFX.controller.chat.ChatClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatConsoleClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 12345;
        String user = args.length > 0 ? args[0] : "User" + new java.util.Random().nextInt(1000);

        // Подключаемся к вашему ChatServer через уже готовый ChatClient
        ChatClient client = new ChatClient(host, port, user, msg -> {
            System.out.println(msg);
        });

        System.out.println("Connected as " + user + ". Type lines and press Enter to send...");

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = console.readLine()) != null) {
            if ("exit".equalsIgnoreCase(line.trim())) {
                client.close(user);
                System.out.println("Goodbye!");
                break;
            }
            client.send(user + ": " + line);
        }
    }
}