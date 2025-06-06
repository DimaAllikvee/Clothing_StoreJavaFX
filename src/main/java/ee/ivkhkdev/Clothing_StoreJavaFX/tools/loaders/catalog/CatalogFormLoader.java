package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.catalog;

import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.ProfileFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatalogFormLoader {

    private final SpringFXMLLoader springFXMLLoader;
    private final AppCustomerService appCustomerService; // Внедрение сервиса для текущего покупателя

    public CatalogFormLoader(SpringFXMLLoader springFXMLLoader, AppCustomerService appCustomerService) {
        this.springFXMLLoader = springFXMLLoader;
        this.appCustomerService = appCustomerService;
    }

    private Stage getPrimaryStage() {
        return ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp.primaryStage;
    }

    public void loadMainFormCatalog() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/catalog/CatalogForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/catalog/CatalogForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Каталог одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadProfileForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/profileForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/profileForm.fxml", e);
        }
        ProfileFormController controller = fxmlLoader.getController();
        Customer currentCustomer = appCustomerService.getCurrentCustomer();
        if (currentCustomer != null) {
            controller.setCustomer(currentCustomer);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Профиль пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadLoginForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/loginForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/loginForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clothing_Store вход пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
