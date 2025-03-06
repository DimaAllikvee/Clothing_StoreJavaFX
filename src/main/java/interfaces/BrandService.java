package interfaces;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
import java.util.List;


public interface BrandService extends AppService<Brand> {
    List<String> getAllBrands();
    List<Brand> getAllBrandObjects();

}

