package ee.ivkhkdev.Clothing_StoreJavaFX.controller;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditCustomerFormController implements Initializable {

    private final FormLoader formLoader;
    private final AppCustomerService appCustomerService;
    private Customer editCustomer;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfBalance;
    @FXML private Label lbInfo;

    public EditCustomerFormController(FormLoader formLoader, AppCustomerService appCustomerService) {
        this.formLoader = formLoader;
        this.appCustomerService = appCustomerService;
    }

    public void setEditCustomer(Customer customer) {
        this.editCustomer = customer;
        tfFirstName.setText(customer.getFirstname());
        tfLastName.setText(customer.getLastname());
        tfUsername.setText(customer.getUsername());
        pfPassword.setText(customer.getPassword());
        tfBalance.setText(String.valueOf(customer.getBalance()));
    }

    @FXML
    private void goEdit() {
        if (editCustomer != null) {
            editCustomer.setFirstname(tfFirstName.getText());
            editCustomer.setLastname(tfLastName.getText());
            editCustomer.setUsername(tfUsername.getText());
            editCustomer.setPassword(pfPassword.getText());
            try {
                double balance = Double.parseDouble(tfBalance.getText());
                editCustomer.setBalance(balance);
            } catch (NumberFormatException e) {
                lbInfo.setText("Введите корректный баланс!");
                return;
            }
            appCustomerService.add(editCustomer);
        }
        formLoader.loadMainForm();

    }

    @FXML
    private void goToCustomerListForm() {
        formLoader.loadEditForm();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Дополнительная инициализация
    }
}
