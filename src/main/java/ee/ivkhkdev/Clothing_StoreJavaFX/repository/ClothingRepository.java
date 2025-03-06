package ee.ivkhkdev.Clothing_StoreJavaFX.repository;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {
}
