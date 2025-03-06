package ee.ivkhkdev.Clothing_StoreJavaFX.tools;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.controller.EditClothingFormController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import javafx.fxml.FXMLLoader;
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

}

