package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.EditCustomerFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.ProfileFormController;
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
public class CustomerFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public CustomerFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/registrationForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Создание нового пользователя");
        stage.centerOnScreen();
        stage.show();
    }

    public void loadCustomerListForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/showCustomerList.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Список пользователей");
        stage.centerOnScreen();
        stage.show();
    }

    public void loadEditCustomerForm(Customer customer) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/editCustomerForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/EditCustomerForm.fxml", e);
        }
        EditCustomerFormController controller = fxmlLoader.getController();
        controller.setEditCustomer(customer);

        Stage stage = getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Редактирование покупателя");
        stage.centerOnScreen();
        stage.show();
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
        if (AppCustomerServiceImpl.currentCustomer != null) {
            controller.setCustomer(AppCustomerServiceImpl.currentCustomer);
        }
        Stage stage = getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Профиль пользователя");
        stage.centerOnScreen();
        stage.show();
    }
}
