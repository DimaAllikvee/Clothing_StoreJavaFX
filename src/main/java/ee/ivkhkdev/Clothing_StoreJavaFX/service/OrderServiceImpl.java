package ee.ivkhkdev.Clothing_StoreJavaFX.service;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.OrderRepository;
import interfaces.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> add(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return FXCollections.observableArrayList(orders);
    }
}
