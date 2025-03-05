package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.FormService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFormController {

    private final AppCustomerService appCustomerService;
    private final FormService formService;

    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;

    public RegistrationFormController(AppCustomerService appCustomerService, FormService formService) {
        this.appCustomerService = appCustomerService;
        this.formService = formService;
    }

    @FXML
    private void registration() {
        try {
            Customer newCustomer = new Customer();
            newCustomer.setFirstname(tfFirstname.getText());
            newCustomer.setLastname(tfLastname.getText());
            newCustomer.setUsername(tfUsername.getText());
            newCustomer.setPassword(pfPassword.getText());
            newCustomer.getRoles().add(AppCustomerService.ROLES.USER.toString());
            appCustomerService.add(newCustomer);
            formService.loadLoginForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
