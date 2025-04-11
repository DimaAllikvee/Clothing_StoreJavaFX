package ee.ivkhkdev.Clothing_StoreJavaFX.controller.order;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.catalog.CatalogFormLoader;
import interfaces.AppCustomerService;
import interfaces.ClothingService;
import interfaces.OrderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserOrderFormController implements Initializable {

    @FXML private Label lblProductInfo;
    @FXML private TextField tfQuantity;
    @FXML private Label lblTotal;
    @FXML private Label lblError;

    private Clothing selectedClothing;

    private final CatalogFormLoader catalogFormLoader;
    private final ClothingService clothingService;
    private final OrderService orderService;
    private final AppCustomerService appCustomerService;

    public UserOrderFormController(
            CatalogFormLoader catalogFormLoader, ClothingService clothingService,
            OrderService orderService,
            AppCustomerService appCustomerService) {
        this.catalogFormLoader = catalogFormLoader;

        this.clothingService = clothingService;
        this.orderService = orderService;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfQuantity.textProperty().addListener((obs, oldVal, newVal) -> recalcTotal());
    }


    public void setSelectedClothing(Clothing clothing) {
        this.selectedClothing = clothing;
        if (clothing != null) {
            lblProductInfo.setText(
                    clothing.getName() + ", " +
                            "Брэнд: " + clothing.getBrand() + ", " +
                            "Размер: " + clothing.getSize() + ", " +
                            "Цена: " + clothing.getPrice() + ", " +
                            "В наличии: " + clothing.getInStock()
            );
        }
    }


    private void recalcTotal() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(tfQuantity.getText().trim());
        } catch (NumberFormatException e) {
            // Если ввод некорректный, оставляем количество равным 0
        }
        if (selectedClothing != null) {
            double total = selectedClothing.getPrice() * quantity;
            lblTotal.setText(String.format("%.2f", total));
        } else {
            lblTotal.setText("0.00");
        }
    }


    @FXML
    private void placeOrder() {
        if (selectedClothing == null) {
            lblError.setText("Товар не выбран.");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(tfQuantity.getText().trim());
            if (quantity <= 0) {
                lblError.setText("Введите корректное количество товара.");
                return;
            }
        } catch (NumberFormatException e) {
            lblError.setText("Введите корректное количество товара.");
            return;
        }

        double totalCost = selectedClothing.getPrice() * quantity;
        // Получаем текущего покупателя из сервиса (чтобы быть уверенными, что он не null)
        Customer currentCustomer = appCustomerService.getCurrentCustomer();
        if (currentCustomer == null) {
            lblError.setText("Пользователь не авторизован.");
            return;
        }
        if (currentCustomer.getBalance() < totalCost) {
            lblError.setText("Недостаточно средств для оформления заказа.");
            return;
        }
        if (selectedClothing.getQuantity() < quantity) {
            lblError.setText("Недостаточно товара на складе.");
            return;
        }

        // Уменьшаем баланс покупателя и количество товара
        currentCustomer.setBalance(currentCustomer.getBalance() - totalCost);
        selectedClothing.setQuantity(selectedClothing.getQuantity() - quantity);
        selectedClothing.setInStock(selectedClothing.getInStock() - quantity);

        // Создаем заказ
        Order order = new Order(currentCustomer, selectedClothing, quantity, totalCost);
        orderService.add(order);


        clothingService.add(selectedClothing);
        appCustomerService.add(currentCustomer);

        lblError.setText("Заказ оформлен. Итоговая сумма: " + totalCost);
        catalogFormLoader.loadMainFormCatalog();

    }

    @FXML
    private void cancel() {
        catalogFormLoader.loadMainFormCatalog();
   
    }


}
