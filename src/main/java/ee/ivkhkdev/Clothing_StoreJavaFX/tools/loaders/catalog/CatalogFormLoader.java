package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.catalog;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.ProfileFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatalogFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public CatalogFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadMainFormCatalog() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/catalog/CatalogForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /main/mainFormCatalog.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clothing_StoreJavaFX магазин верхней одежды");
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
        // Передаем текущего пользователя
        if (AppCustomerServiceImpl.currentCustomer != null) {
            controller.setCustomer(AppCustomerServiceImpl.currentCustomer);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Профиль пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
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
