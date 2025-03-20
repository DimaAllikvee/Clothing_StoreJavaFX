package ee.ivkhkdev.Clothing_StoreJavaFX.controller.profile;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ChangePasswordFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final FormLoader formLoader;
    private Customer currentCustomer;

    @FXML private PasswordField pfCurrentPassword;
    @FXML private PasswordField pfNewPassword;
    @FXML private PasswordField pfConfirmPassword;

    public ChangePasswordFormController(AppCustomerService appCustomerService, FormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    /**
     * Устанавливает текущего пользователя, для которого меняем пароль.
     */
    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    /**
     * Сохраняет новый пароль после проверки текущего и подтверждения.
     */
    @FXML
    private void savePassword() {
        String currentPass = pfCurrentPassword.getText().trim();
        String newPass = pfNewPassword.getText().trim();
        String confirmPass = pfConfirmPassword.getText().trim();

        if (currentCustomer == null) {
            System.out.println("Пользователь не найден");
            return;
        }

        // Проверяем, что текущий пароль введён правильно
        if (!currentCustomer.getPassword().equals(currentPass)) {
            System.out.println("Неверный текущий пароль");
            return;
        }

        // Проверяем, что новый пароль введён и совпадает с подтверждением
        if (newPass.isEmpty() || !newPass.equals(confirmPass)) {
            System.out.println("Новый пароль не введён или подтверждение не совпадает");
            return;
        }

        // Обновляем пароль
        currentCustomer.setPassword(newPass);
        appCustomerService.add(currentCustomer);
        formLoader.loadMainFormCatalog();

    }


    @FXML
    private void cancel() {
        formLoader.loadMainFormCatalog();

    }

    private void closeWindow() {
        Stage stage = (Stage) pfCurrentPassword.getScene().getWindow();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
