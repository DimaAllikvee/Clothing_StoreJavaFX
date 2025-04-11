package ee.ivkhkdev.Clothing_StoreJavaFX.controller.auth;

import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.auth.LoginFormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {

    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private Label lbInfo;

    private final AppCustomerServiceImpl customerService;
    private final LoginFormLoader loginFormLoader;

    public LoginFormController(AppCustomerServiceImpl customerService, LoginFormLoader loginFormLoader) {
        this.customerService = customerService;
        this.loginFormLoader = loginFormLoader;
    }

    @FXML
    private void login() {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        boolean success = customerService.authentication(username, password);
        if (!success) {
            lbInfo.setText("Неверный логин или пароль");
            return;
        }

        // Если аутентификация успешна, currentCustomer уже установлен
        // Проверяем роль
        if (AppCustomerServiceImpl.currentCustomer.getRoles().contains("ADMINISTRATOR")
                || AppCustomerServiceImpl.currentCustomer.getRoles().contains("MANAGER")) {
            // Открываем MainForm (где таблица)
            loginFormLoader.loadMainForm();
        } else {
            // Иначе считаем, что это USER
            loginFormLoader.loadCatalogForm();
        }


    }

    @FXML
    private void showRegistrationForm(){
        loginFormLoader.loadRegistrationForm();
    }


}


