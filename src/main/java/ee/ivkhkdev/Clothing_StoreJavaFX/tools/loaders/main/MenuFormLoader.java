package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.main;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.ProfileFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MenuFormLoader {

    private final SpringFXMLLoader springFXMLLoader;
    private final AppCustomerService appCustomerService;


    public MenuFormLoader(SpringFXMLLoader springFXMLLoader, AppCustomerService appCustomerService) {
        this.springFXMLLoader = springFXMLLoader;
        this.appCustomerService = appCustomerService;
    }


    public Parent loadMenuForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/menu/menuForm.fxml");
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить меню из /view/menu/menuForm.fxml", e);
        }
    }


    public void showAddClothingForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/clothes/AddClothingForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /clothes/newClothingForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление новой модели одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showAddCustomerForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/addCustomerForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление нового пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showAddBrandForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/clothes/AddBrandForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /clothes/AddBrandForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление нового бренда");
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

    public void showCustomersForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/showCustomerList.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Список пользователей");
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

    public void loadOrderForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/order/orderForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму заказа", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Оформление заказа");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadCatalogForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/catalog/CatalogForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить CatalogForm", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Каталог одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRevenueForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/order/RevenueForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить RevenueForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Доход магазина");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRatingChartForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/rating/ratingForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить RatingForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Рейтинг продаж");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadChatWindow() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/chat/chat.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить ChatForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Чат");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }
}
