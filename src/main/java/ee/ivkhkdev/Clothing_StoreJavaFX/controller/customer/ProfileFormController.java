package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProfileFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final FormLoader formLoader;
    private Customer currentCustomer;

    @FXML private Label lblUsername;
    @FXML private Label lblBalance;

    public ProfileFormController(AppCustomerService appCustomerService, FormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Здесь можно выполнить дополнительную инициализацию, если потребуется.
    }

    /**
     * Устанавливает текущего пользователя и заполняет метки.
     */
    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
        if (customer != null) {
            lblUsername.setText(customer.getUsername());
            lblBalance.setText(String.valueOf(customer.getBalance()));
        }
    }

    /**
     * Открывает модальное окно для изменения пароля.
     */
    @FXML
    private void openChangePasswordWindow() {
        formLoader.loadChangePasswordForm(currentCustomer);
    }

    /**
     * Возвращает на главное окно.
     */
    @FXML
    private void cancel() {
        formLoader.loadMainForm();

    }


}
