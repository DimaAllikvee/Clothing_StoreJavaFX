package ee.ivkhkdev.Clothing_StoreJavaFX.service;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;




@Service
public class FormService {

    private final SpringFXMLLoader springFXMLLoader;

    @Autowired
    public FormService(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    public void loadLoginForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/customer/loginForm.fxml");
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
        FXMLLoader fxmlLoader = springFXMLLoader.load("/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /main/mainForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nptv23JavaFX Библиотека");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/customer/registrationForm.fxml");
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
        FXMLLoader fxmlLoader = springFXMLLoader.load("/menu/menuForm.fxml");
        try {
            return fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

