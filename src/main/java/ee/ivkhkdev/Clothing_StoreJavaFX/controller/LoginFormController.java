package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {
    private final FormLoader formLoader;
    private final AppCustomerServiceImpl appCustomerServiceImpl;

    @FXML
    private Label lbInfo;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;

    public LoginFormController(FormLoader formLoader, AppCustomerServiceImpl appCustomerServiceImpl) {
        this.formLoader = formLoader;
        this.appCustomerServiceImpl = appCustomerServiceImpl;
    }

    @FXML
    private void login(){
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        // Проверяем, заполнены ли логин и пароль
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            lbInfo.setText("Необходимо ввести логин и пароль");
            return;
        }

        if (appCustomerServiceImpl.authentication(username, password)) {
            formLoader.loadMainForm();
        } else {
            lbInfo.setText("Нет такого пользователя, или неправильный пароль");
        }
    }

    @FXML
    private void showRegistrationForm(){
        formLoader.loadRegistrationForm();
    }
}
