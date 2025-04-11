package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.auth;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class RegitrationFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public RegitrationFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadLoginForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/loginForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Clothing_Store вход пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
}
