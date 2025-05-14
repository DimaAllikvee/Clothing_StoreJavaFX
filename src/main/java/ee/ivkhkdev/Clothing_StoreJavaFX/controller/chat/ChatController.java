package ee.ivkhkdev.Clothing_StoreJavaFX.controller.chat;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.ChatMessage;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.ChatMessageService;
import interfaces.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.chat.ChatFormLoader;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class ChatController implements Initializable {

    @FXML private VBox messagesContainer;
    @FXML private TextField tfInput;

    private final AppCustomerService   appCustomerService;
    private final ChatFormLoader       chatFormLoader;
    private final ChatMessageService   messageService;

    private ChatClient client;
    private String     username;
    private String     peerUsername;

    public ChatController(AppCustomerService appCustomerService,
                          ChatFormLoader chatFormLoader,
                          ChatMessageService messageService) {
        this.appCustomerService = appCustomerService;
        this.chatFormLoader     = chatFormLoader;
        this.messageService     = messageService;
    }

    @Override
    public void initialize(URL loc, ResourceBundle res) {
        username = appCustomerService.getCurrentCustomer() != null
                ? appCustomerService.getCurrentCustomer().getUsername()
                : "Guest";

        // 1) История
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (ChatMessage m : messageService.getHistory()) {
            addSystemLine(String.format("[%s] %s: %s",
                    m.getTimestamp().format(fmt),
                    m.getSender(),
                    m.getContent()));
        }
        addSystemLine("=== Конец истории ===");

        // 2) Подключение
        addSystemLine("Подключились как: " + username);
        try {
            client = new ChatClient("localhost", 12345, username, this::onMessageReceived);
        } catch (Exception e) {
            addSystemLine("Ошибка подключения: " + e.getMessage());
        }
    }

    @FXML
    private void onSend() {
        String text = tfInput.getText().trim();
        if (text.isEmpty() || client == null) return;

        String raw = username + ": " + text;
        messageService.save(username, text);
        client.send(raw);

        addChatLine(username, text);
        tfInput.clear();
    }

    @FXML
    private void onBack() {
        if (client != null) client.close(username);
        chatFormLoader.loadMainForm();
    }

    private void onMessageReceived(String raw) {
        // собственные уведомления не показываем
        if (raw.startsWith("*** " + username)) return;

        if (raw.startsWith("***")) {
            // join/leave
            addSystemLine(raw);
            showPopup(raw);
        } else {
            int idx = raw.indexOf(':');
            if (idx > 0) {
                String from    = raw.substring(0, idx).trim();
                String content = raw.substring(idx + 1).trim();
                addChatLine(from, content);
            } else {
                addSystemLine(raw);
            }
        }
    }

    private void addChatLine(String from, String text) {
        Platform.runLater(() -> {
            Label nameLbl = new Label(from + ":");
            nameLbl.getStyleClass().add("chat-username");
            nameLbl.setOnMouseClicked(evt -> {
                if (evt.getButton() == MouseButton.PRIMARY) {
                    ContextMenu menu = new ContextMenu();
                    MenuItem pm = new MenuItem("Написать личное сообщение");
                    pm.setOnAction(e -> {
                        peerUsername = from;
                        addSystemLine("--- Приватный чат с " + peerUsername + " ---");
                    });
                    menu.getItems().add(pm);
                    menu.show(nameLbl, evt.getScreenX(), evt.getScreenY());
                }
            });

            Label textLbl = new Label(text);
            textLbl.getStyleClass().add("chat-text");
            textLbl.setWrapText(true);

            HBox row = new HBox(8, nameLbl, textLbl);
            row.getStyleClass().add("chat-row");
            messagesContainer.getChildren().add(row);
        });
    }

    private void addSystemLine(String line) {
        Platform.runLater(() -> {
            Label sys = new Label(line);
            sys.getStyleClass().add("chat-system");
            messagesContainer.getChildren().add(sys);
        });
    }

    private void showPopup(String message) {
        Platform.runLater(() -> {
            Popup popup = new Popup();
            Label lbl = new Label(message);
            lbl.getStyleClass().add("chat-popup");
            popup.getContent().add(lbl);
            popup.setAutoHide(true);
            popup.show(messagesContainer.getScene().getWindow());

            // автоматическое скрытие через 3 секунды
            PauseTransition t = new PauseTransition(Duration.seconds(3));
            t.setOnFinished(e -> popup.hide());
            t.play();
        });
    }
}
