package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer.ProfileFormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProfileFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final ProfileFormLoader profileFormLoader;
    private Customer currentCustomer;

    @FXML private Label lblUsername;
    @FXML private Label lblBalance;

    public ProfileFormController(AppCustomerService appCustomerService, ProfileFormLoader profileFormLoader) {
        this.appCustomerService = appCustomerService;

        this.profileFormLoader = profileFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
        if (customer != null) {
            lblUsername.setText(customer.getUsername());
            lblBalance.setText(String.valueOf(customer.getBalance()));
        }
    }


    @FXML
    private void openChangePasswordWindow() {
        profileFormLoader.loadChangePasswordForm(currentCustomer);
    }


    @FXML
    private void cancel() {
        profileFormLoader.loadMainForm();

    }


}
