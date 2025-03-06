package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AddCustomerFormController implements Initializable {

    private final FormLoader formLoader;
    private final AppCustomerService appCustomerService;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfBalance;
    @FXML private Label lbInfo;

    public AddCustomerFormController(FormLoader formLoader, AppCustomerService appCustomerService) {
        this.formLoader = formLoader;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Дополнительная инициализация, если требуется
    }

    @FXML
    private void add() {
        String firstName = tfFirstName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String username = tfUsername.getText().trim();
        String password = pfPassword.getText().trim();
        String balanceText = tfBalance.getText().trim();

        // Проверка заполненности полей
        if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                password.isEmpty() || balanceText.isEmpty()){
            lbInfo.setText("Все поля должны быть заполнены!");
            return;
        }

        double balance;
        try {
            balance = Double.parseDouble(balanceText);
        } catch (NumberFormatException ex) {
            lbInfo.setText("Введите корректный баланс");
            return;
        }

        Customer newCustomer = new Customer();
        newCustomer.setFirstname(firstName);
        newCustomer.setLastname(lastName);
        newCustomer.setUsername(username);
        newCustomer.setPassword(password);
        newCustomer.setBalance(balance);



        try {
            Optional<Customer> result = appCustomerService.add(newCustomer);
            if (result.isPresent()) {
                formLoader.loadLoginForm();
            } else {
                lbInfo.setText("Пользователь с таким логином уже существует!");
            }
        } catch (Exception e) {
            lbInfo.setText("Ошибка при регистрации: " + e.getMessage());
        }
    }

    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}
