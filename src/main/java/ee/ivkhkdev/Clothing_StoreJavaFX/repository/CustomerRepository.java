package ee.ivkhkdev.Clothing_StoreJavaFX.repository;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    boolean existsByUsername(String username);
}
