package ee.ivkhkdev.Clothing_StoreJavaFX.repository;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}

