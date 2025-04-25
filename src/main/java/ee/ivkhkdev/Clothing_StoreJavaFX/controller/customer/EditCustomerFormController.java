package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer.EditCustomerFormLoader;
import interfaces.AppCustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

@Component
public class EditCustomerFormController implements Initializable {

    private final EditCustomerFormLoader editCustomerFormLoader;
    private final AppCustomerService    appCustomerService;
    private Customer                    editCustomer;

    @FXML private TextField    tfFirstName, tfLastName, tfUsername, tfBalance;
    @FXML private PasswordField pfPassword;
    @FXML private CheckBox      chkUser, chkManager, chkAdmin;
    @FXML private Button        btnSave;
    @FXML private Label         lbInfo;

    public EditCustomerFormController(EditCustomerFormLoader editCustomerFormLoader,
                                      AppCustomerService appCustomerService) {
        this.editCustomerFormLoader = editCustomerFormLoader;
        this.appCustomerService     = appCustomerService;
    }

    public void setEditCustomer(Customer customer) {
        this.editCustomer = customer;

        // Заполняем поля
        tfFirstName.setText(customer.getFirstname());
        tfLastName .setText(customer.getLastname());
        tfUsername .setText(customer.getUsername());
        pfPassword .setText(customer.getPassword());
        tfBalance  .setText(String.valueOf(customer.getBalance()));

        // Заполняем чекбоксы ролей
        Set<String> roles = customer.getRoles();
        chkUser   .setSelected(roles.contains("USER"));
        chkManager.setSelected(roles.contains("MANAGER"));
        chkAdmin  .setSelected(roles.contains("ADMINISTRATOR"));

        // Получаем текущего залогиненного пользователя
        Customer current = appCustomerService.getCurrentCustomer();
        boolean editingSuper = "admin".equalsIgnoreCase(customer.getUsername());
        boolean isSelf       = current != null
                && customer.getUsername().equalsIgnoreCase(current.getUsername());

        // Если редактируем SuperAdmin, но не себя — блокируем всё
        if (editingSuper && !isSelf) {
            lbInfo.setText("Нельзя редактировать SuperAdmin");
            tfFirstName.setDisable(true);
            tfLastName .setDisable(true);
            tfUsername .setDisable(true);
            pfPassword .setDisable(true);
            tfBalance  .setDisable(true);
            chkUser    .setDisable(true);
            chkManager .setDisable(true);
            chkAdmin   .setDisable(true);
            btnSave    .setDisable(true);
        }
        // Если это сам SuperAdmin, оставляем всё включённым
    }

    /**
     * Сохраняет изменения покупателя и ролей.
     */
    @FXML
    private void goEdit() {
        if (editCustomer == null) return;

        // Защита: если пытаются сохранить SuperAdmin не по-самому, ложим
        boolean editingSuper = "admin".equalsIgnoreCase(editCustomer.getUsername());
        Customer current = appCustomerService.getCurrentCustomer();
        boolean isSelf = current != null
                && editingSuper
                && current.getUsername().equalsIgnoreCase(editCustomer.getUsername());
        if (editingSuper && !isSelf) {
            return;
        }

        // Сохраняем основные поля
        editCustomer.setFirstname(tfFirstName.getText());
        editCustomer.setLastname (tfLastName .getText());
        editCustomer.setUsername (tfUsername .getText());
        editCustomer.setPassword (pfPassword .getText());
        try {
            editCustomer.setBalance(Double.parseDouble(tfBalance.getText()));
        } catch (NumberFormatException e) {
            lbInfo.setText("Введите корректный баланс!");
            return;
        }
        appCustomerService.add(editCustomer);

        // Синхронизируем роли
        syncRole(editCustomer, "USER",          chkUser   .isSelected());
        syncRole(editCustomer, "MANAGER",       chkManager.isSelected());
        syncRole(editCustomer, "ADMINISTRATOR", chkAdmin  .isSelected());

        editCustomerFormLoader.loadMainForm();
    }

    private void syncRole(Customer user, String role, boolean shouldHave) {
        if (shouldHave) {
            appCustomerService.addRole(user, role);
        } else {
            appCustomerService.removeRole(user, role);
        }
    }

    @FXML
    private void goToCustomerListForm() {
        editCustomerFormLoader.loadEditForm();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Всё настраивается в setEditCustomer()
    }
}
