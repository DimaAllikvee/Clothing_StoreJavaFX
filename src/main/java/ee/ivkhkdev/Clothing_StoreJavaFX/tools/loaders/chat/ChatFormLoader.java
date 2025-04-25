package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.chat;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;

import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.main.MainFormLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ChatFormLoader {
    private final SpringFXMLLoader springFXMLLoader;
    private final MainFormLoader mainFormLoader;

    public ChatFormLoader(SpringFXMLLoader springFXMLLoader,
                          MainFormLoader mainFormLoader) {
        this.springFXMLLoader = springFXMLLoader;
        this.mainFormLoader = mainFormLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    /** Загружает окно чата */
    public void loadChatForm() {
        FXMLLoader loader = springFXMLLoader.load("/view/chat/chat.fxml");
        try {
            Parent root = loader.load();
            Stage stage = getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Чат");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить ChatForm.fxml", e);
        }
    }

    /** Возврат к каталогу товаров (главной форме пользователя) */
    public void loadMainForm() {
        mainFormLoader.loadMainForm();
    }
}
