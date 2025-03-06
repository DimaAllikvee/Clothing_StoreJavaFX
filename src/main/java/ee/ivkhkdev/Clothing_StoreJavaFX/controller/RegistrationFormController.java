package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFormController {

    private final AppCustomerServiceImpl appCustomerServiceImpl;
    private final FormLoader formLoader;

    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lbInfo;

    public RegistrationFormController(AppCustomerServiceImpl appCustomerServiceImpl, FormLoader formLoader) {
        this.appCustomerServiceImpl = appCustomerServiceImpl;
        this.formLoader = formLoader;
    }

    @FXML
    private void registration() {
        try {
            Customer newCustomer = new Customer();
            newCustomer.setFirstname(tfFirstname.getText());
            newCustomer.setLastname(tfLastname.getText());
            newCustomer.setUsername(tfUsername.getText());
            newCustomer.setPassword(pfPassword.getText());
            newCustomer.getRoles().add(AppCustomerServiceImpl.ROLES.USER.toString());

            // Попытка сохранить пользователя; если логин занят — выбросится исключение
            appCustomerServiceImpl.add(newCustomer);

            // При успешной регистрации переходим на форму логина
            formLoader.loadLoginForm();
        } catch (IllegalArgumentException e) {
            // Выводим сообщение о том, что пользователь уже существует
            lbInfo.setText(e.getMessage());
        } catch (Exception e) {
            lbInfo.setText("Ошибка регистрации: " + e.getMessage());
        }
    }
}
