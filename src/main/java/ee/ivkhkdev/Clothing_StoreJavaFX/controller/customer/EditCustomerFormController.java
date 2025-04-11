package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer.EditCustomerFormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditCustomerFormController implements Initializable {

    private final EditCustomerFormLoader editCustomerFormLoader;
    private final AppCustomerService appCustomerService;
    private Customer editCustomer;

    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfBalance;
    @FXML private Label lbInfo;

    public EditCustomerFormController(EditCustomerFormLoader editCustomerFormLoader, AppCustomerService appCustomerService) {
        this.editCustomerFormLoader = editCustomerFormLoader;

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
        editCustomerFormLoader.loadMainForm();

    }

    @FXML
    private void goToCustomerListForm() {
        editCustomerFormLoader.loadEditForm();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
