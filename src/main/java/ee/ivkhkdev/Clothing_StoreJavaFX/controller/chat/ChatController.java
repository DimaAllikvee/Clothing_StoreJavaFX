
package ee.ivkhkdev.Clothing_StoreJavaFX.controller.chat;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.ChatMessage;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.ChatMessageService;
import interfaces.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.chat.ChatFormLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class ChatController implements Initializable {

    @FXML private TextArea taMessages;
    @FXML private TextField tfInput;

    private final AppCustomerService appCustomerService;
    private final ChatFormLoader chatFormLoader;
    private final ChatMessageService messageService;

    private ChatClient client;
    private String username;

    public ChatController(AppCustomerService appCustomerService,
                          ChatFormLoader chatFormLoader,
                          ChatMessageService messageService) {
        this.appCustomerService = appCustomerService;
        this.chatFormLoader = chatFormLoader;
        this.messageService = messageService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Получаем имя текущего пользователя
        username = appCustomerService.getCurrentCustomer() != null
                ? appCustomerService.getCurrentCustomer().getUsername()
                : "Guest";

        // 1) Выводим историю из БД:
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (ChatMessage m : messageService.getHistory()) {
            appendToChat(String.format("[%s] %s: %s",
                    m.getTimestamp().format(fmt),
                    m.getSender(),
                    m.getContent()));
        }
        appendToChat("=== Конец истории ===");

        // 2) Подключаемся к серверу
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

        String full = username + ": " + text;
        // 1) сохраняем в БД и отправляем на сервер
        messageService.save(username, text);
        client.send(full);

        // 2) сразу выводим в своём окне
        appendToChat(full);
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
        // фильтруем собственные “joined” уведомления
        if (message.startsWith("*** " + username)) {
            return;
        }
        appendToChat(message);
    }

    private void appendToChat(String line) {
        Platform.runLater(() -> taMessages.appendText(line + "\n"));
    }
}
