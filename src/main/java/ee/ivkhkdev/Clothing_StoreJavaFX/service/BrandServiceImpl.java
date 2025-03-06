package ee.ivkhkdev.Clothing_StoreJavaFX.service;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.BrandRepository;
import interfaces.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void CreateBrand(String brandName) {
        // Проверяем, что бренд с таким именем еще не существует
        if (!brandRepository.existsByName(brandName)) {
            Brand brand = new Brand(brandName);
            brandRepository.save(brand);
        }
    }

    @Override
    public List<String> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        // Возвращаем список имен брендов
        return brands.stream()
                .map(Brand::getName)
                .collect(Collectors.toList());
    }
}


