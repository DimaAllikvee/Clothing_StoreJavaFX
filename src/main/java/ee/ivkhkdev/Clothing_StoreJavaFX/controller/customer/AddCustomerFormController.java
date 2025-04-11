package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer.AddCustomerFormLoader;
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


    private final AppCustomerService appCustomerService;
    private final AddCustomerFormLoader addCustomerFormLoader;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfBalance;
    @FXML private Label lbInfo;

    public AddCustomerFormController(AppCustomerService appCustomerService, AddCustomerFormLoader addCustomerFormLoader) {

        this.appCustomerService = appCustomerService;
        this.addCustomerFormLoader = addCustomerFormLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                addCustomerFormLoader.loadLoginForm();
            } else {
                lbInfo.setText("Пользователь с таким логином уже существует!");
            }
        } catch (Exception e) {
            lbInfo.setText("Ошибка при регистрации: " + e.getMessage());
        }
    }

    @FXML
    private void goToMainForm() {
        addCustomerFormLoader.loadMainForm();
    }
}
