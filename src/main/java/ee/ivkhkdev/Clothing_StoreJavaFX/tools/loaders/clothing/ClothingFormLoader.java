package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.clothing.EditClothingFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClothingFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public ClothingFormLoader(SpringFXMLLoader springFXMLLoader) {
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

    public void loadEditClothingForm(Clothing clothing) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/clothes/EditClothingForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/clothes/EditClothingForm.fxml", e);
        }
        // Получаем контроллер и передаем объект для редактирования
        EditClothingFormController controller = fxmlLoader.getController();
        controller.setEditClothing(clothing);

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Редактирование модели одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
