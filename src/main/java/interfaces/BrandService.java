package interfaces;



import java.util.List;

public interface BrandService {
    void CreateBrand(String brandName);
    List<String> getAllBrands();
}
