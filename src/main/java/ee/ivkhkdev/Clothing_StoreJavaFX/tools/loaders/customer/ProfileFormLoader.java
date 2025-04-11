package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.profile.ChangePasswordFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProfileFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public ProfileFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadChangePasswordForm(Customer customer) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/ChangePasswordForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/ChangePasswordForm.fxml", e);
        }
        // Получаем контроллер формы изменения пароля
        ChangePasswordFormController controller = fxmlLoader.getController();
        // Передаём текущего пользователя, если он установлен
        if (AppCustomerServiceImpl.currentCustomer != null) {
            controller.setCustomer(AppCustomerServiceImpl.currentCustomer);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Изменение пароля");
        primaryStage.centerOnScreen();
        primaryStage.show();
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
