package ee.ivkhkdev.Clothing_StoreJavaFX.controller.chat;

import interfaces.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.chat.ChatFormLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ChatController implements Initializable {

    @FXML private TextArea taMessages;
    @FXML private TextField tfInput;

    private final AppCustomerService appCustomerService;
    private final ChatFormLoader chatFormLoader;
    private ChatClient client;
    private String username;

    public ChatController(AppCustomerService appCustomerService,
                          ChatFormLoader chatFormLoader) {
        this.appCustomerService = appCustomerService;
        this.chatFormLoader = chatFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username = appCustomerService.getCurrentCustomer() != null
                ? appCustomerService.getCurrentCustomer().getUsername()
                : "Guest";

        appendToChat("Подключились как: " + username);
        try {
            client = new ChatClient("localhost", 12345, username, this::onMessageReceived);
        } catch (Exception e) {
            appendToChat("Ошибка подключения: " + e.getMessage());
        }
    }

    @FXML
    private void onSend() {
        String text = tfInput.getText().trim();
        if (text.isEmpty() || client == null) return;

        // 1) отправляем на сервер
        client.send(username + ": " + text);

        // 2) сразу отображаем в своём чате
        appendToChat(username + ": " + text);

        tfInput.clear();
    }


    @FXML
    private void onBack() {
        if (client != null) {
            client.close(username);
        }
        chatFormLoader.loadMainForm();
    }

    private void onMessageReceived(String message) {
        // Фильтруем свои уведомления о входе/выходе
        if (message.startsWith("*** " + username)) {
            return;
        }
        appendToChat(message);
    }

    private void appendToChat(String message) {
        Platform.runLater(() -> taMessages.appendText(message + "\n"));
    }
}
