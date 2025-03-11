package interfaces;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import javafx.collections.ObservableList;

public interface OrderService extends AppService<Order> {
    ObservableList<Order> getAllOrders();

}
