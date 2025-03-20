package ee.ivkhkdev.Clothing_StoreJavaFX.controller.order;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.AppCustomerService;
import interfaces.ClothingService;
import interfaces.OrderService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OrderFormController implements Initializable {

    private final FormLoader formLoader;
    private final ClothingService clothingService;
    private final OrderService orderService;
    private final AppCustomerService appCustomerService;

    @FXML private ComboBox<Customer> cbCustomer;
    @FXML private ComboBox<Clothing> cbClothing;
    @FXML private TextField tfQuantity;
    @FXML private Label lblTotal;
    @FXML private Label lblError;

    public OrderFormController(FormLoader formLoader, ClothingService clothingService,
                               OrderService orderService, AppCustomerService appCustomerService) {
        this.formLoader = formLoader;
        this.clothingService = clothingService;
        this.orderService = orderService;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbCustomer.setItems(appCustomerService.getListCustomers());
        ObservableList<Clothing> clothingList = clothingService.getListClothing();
        cbClothing.setItems(clothingList);
        cbClothing.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> recalcTotal());
        tfQuantity.textProperty().addListener((obs, oldVal, newVal) -> recalcTotal());
    }

    private void recalcTotal() {
        Clothing selected = cbClothing.getSelectionModel().getSelectedItem();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(tfQuantity.getText().trim());
        } catch (NumberFormatException e) {

        }
        if (selected != null) {
            double total = selected.getPrice() * quantity;
            lblTotal.setText(String.format("%.2f", total));
        } else {
            lblTotal.setText("0.00");
        }
    }


    @FXML
    private void placeOrder() {
        Customer selectedCustomer = cbCustomer.getSelectionModel().getSelectedItem();
        Clothing selectedClothing = cbClothing.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            lblError.setText("Выберите покупателя для заказа.");
            return;
        }
        if (selectedClothing == null) {
            lblError.setText("Выберите товар для заказа.");
            return;
        }
        int orderQuantity;
        try {
            orderQuantity = Integer.parseInt(tfQuantity.getText().trim());
        } catch (NumberFormatException e) {
            lblError.setText("Введите корректное количество товара.");
            return;
        }
        double totalCost = selectedClothing.getPrice() * orderQuantity;
        if (selectedCustomer.getBalance() < totalCost) {
            lblError.setText("Недостаточно средств у покупателя.");
            return;
        }
        if (selectedClothing.getQuantity() < orderQuantity) {
            lblError.setText("Недостаточно товара на складе.");
            return;
        }

        // Уменьшаем баланс покупателя
        selectedCustomer.setBalance(selectedCustomer.getBalance() - totalCost);
        // Уменьшаем количество товара (и количество в наличии)
        selectedClothing.setQuantity(selectedClothing.getQuantity() - orderQuantity);
        selectedClothing.setInStock(selectedClothing.getInStock() - orderQuantity);

        // Создаем заказ
        Order order = new Order(selectedCustomer, selectedClothing, orderQuantity, totalCost);
        orderService.add(order);

        // Обновляем данные в базе
        clothingService.add(selectedClothing);
        appCustomerService.add(selectedCustomer);

        lblError.setText("Заказ оформлен. Итоговая сумма: " + totalCost);
        formLoader.loadMainForm();

    }



    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}
