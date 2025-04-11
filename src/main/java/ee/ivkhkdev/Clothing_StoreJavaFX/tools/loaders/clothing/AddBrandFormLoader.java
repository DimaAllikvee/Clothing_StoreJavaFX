package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.clothing;


import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddBrandFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public AddBrandFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadMainForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /main/mainForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clothing_StoreJavaFX магазин верхней одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
