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
        // 1. Проверяем, что выбран покупатель
        Customer selectedCustomer = cbCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            lblError.setText("Выберите покупателя для заказа.");
            return;
        }
        // 2. Проверяем, что выбран товар
        Clothing selectedClothing = cbClothing.getSelectionModel().getSelectedItem();
        if (selectedClothing == null) {
            lblError.setText("Выберите товар для заказа.");
            return;
        }
        // 3. Проверяем корректность введённого количества
        int orderQuantity;
        try {
            orderQuantity = Integer.parseInt(tfQuantity.getText().trim());
            if (orderQuantity <= 0) {
                lblError.setText("Введите корректное количество (больше нуля).");
                return;
            }
        } catch (NumberFormatException e) {
            lblError.setText("Введите корректное число в поле количества.");
            return;
        }

        // 4. Вычисляем итоговую стоимость
        double totalCost = selectedClothing.getPrice() * orderQuantity;

        // 5. Проверяем, достаточно ли средств у покупателя
        if (selectedCustomer.getBalance() < totalCost) {
            lblError.setText("Недостаточно средств у покупателя.");
            return;
        }

        // 6. Проверяем, что товар есть на складе
        if (selectedClothing.getInStock() <= 0) {
            lblError.setText("Товар отсутствует на складе.");
            return;
        }
        // 7. Проверяем, что пользователь не хочет купить больше, чем есть
        if (selectedClothing.getInStock() < orderQuantity) {
            lblError.setText("Недостаточно товара на складе (доступно: " + selectedClothing.getInStock() + ").");
            return;
        }

        // 8. Уменьшаем баланс покупателя
        selectedCustomer.setBalance(selectedCustomer.getBalance() - totalCost);
        // 9. Уменьшаем количество товара (и его inStock)
        selectedClothing.setQuantity(selectedClothing.getQuantity() - orderQuantity);
        selectedClothing.setInStock(selectedClothing.getInStock() - orderQuantity);

        // 10. Создаем заказ
        Order order = new Order(selectedCustomer, selectedClothing, orderQuantity, totalCost);
        orderService.add(order);

        // 11. Обновляем данные в базе
        clothingService.add(selectedClothing);
        appCustomerService.add(selectedCustomer);

        // 12. Сообщаем об успехе и возвращаемся на главную форму
        lblError.setText("Заказ оформлен. Итоговая сумма: " + totalCost);
        formLoader.loadMainForm();
    }




    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}
