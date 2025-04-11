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

        // Получаем текущего пользователя через метод getCurrentCustomer() вместо обращения к статическому полю
        if (customerService.getCurrentCustomer().getRoles().contains("ADMINISTRATOR")
                || customerService.getCurrentCustomer().getRoles().contains("MANAGER")) {
            // Если пользователь имеет роль администратора или менеджера,
            // открываем основное окно с таблицей (MainForm)
            loginFormLoader.loadMainForm();
        } else {
            // Иначе открываем каталог (CatalogForm) для обычного пользователя
            loginFormLoader.loadCatalogForm();
        }
    }

    @FXML
    private void showRegistrationForm() {
        loginFormLoader.loadRegistrationForm();
    }
}
