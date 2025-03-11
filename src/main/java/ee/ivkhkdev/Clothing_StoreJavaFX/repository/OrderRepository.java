package ee.ivkhkdev.Clothing_StoreJavaFX.repository;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
