package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.card;


import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.order.UserOrderFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CardClothingFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public CardClothingFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }


    public void loadUserOrderForm(Clothing clothing) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/order/userOrderForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму заказа", e);
        }
        // Получаем контроллер формы заказа
        UserOrderFormController controller = fxmlLoader.getController();
        // Передаем выбранный товар
        controller.setSelectedClothing(clothing);

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Заказы пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
