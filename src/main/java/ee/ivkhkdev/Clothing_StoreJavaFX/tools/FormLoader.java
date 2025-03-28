package ee.ivkhkdev.Clothing_StoreJavaFX.tools;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.order.UserOrderFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.profile.ChangePasswordFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.clothing.EditClothingFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.EditCustomerFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer.ProfileFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;




@Service
public class FormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    @Autowired
    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
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


    public void loadEditForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/showCustomerList.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Редактирование пользователя");
    }


    public void loadCustomerForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/showCustomerList.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Список пользователей");
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/registrationForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание нового пользователя");
    }


    public Parent loadMenuForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/menu/menuForm.fxml");
        try {
            return fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
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
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Редактирование покупателя");
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


}












