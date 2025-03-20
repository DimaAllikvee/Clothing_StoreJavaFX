package ee.ivkhkdev.Clothing_StoreJavaFX.controller.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ShowCustomerListController implements Initializable {

    private final FormLoader formLoader;
    private final AppCustomerService appCustomerService;

    @FXML private TableView<Customer> tvCustomerList;
    @FXML private TableColumn<Customer, Long> tcId;
    @FXML private TableColumn<Customer, String> tcUsername;
    @FXML private TableColumn<Customer, String> tcFirstName;
    @FXML private TableColumn<Customer, String> tcLastName;
    @FXML private TableColumn<Customer, Double> tcBalance;
    @FXML private HBox hbEditCustomer;

    public ShowCustomerListController(FormLoader formLoader, AppCustomerService appCustomerService) {
        this.formLoader = formLoader;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка столбцов таблицы
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tcBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        // Устанавливаем список покупателей
        ObservableList<Customer> customers = appCustomerService.getListCustomers();
        tvCustomerList.setItems(customers);

        // Слушатель изменения выделения в таблице
        tvCustomerList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            hbEditCustomer.setVisible(newVal != null);
        });
    }

    @FXML
    private void editCustomer() {
        Customer selected = tvCustomerList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            formLoader.loadEditCustomerForm(selected);
        } else {
            System.out.println("Выберите покупателя для редактирования.");
        }
    }

    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}
