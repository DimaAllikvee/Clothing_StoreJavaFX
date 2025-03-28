//package ee.ivkhkdev.Clothing_StoreJavaFX.tools;
//
//import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
//import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
//import ee.ivkhkdev.Clothing_StoreJavaFX.repository.BrandRepository;
//import ee.ivkhkdev.Clothing_StoreJavaFX.repository.ClothingRepository;
//import ee.ivkhkdev.Clothing_StoreJavaFX.service.FakeDataGeneratorService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.util.List;
//
//@Component
//public class DataInitializer {
//
//    private final FakeDataGeneratorService fakeDataGeneratorService;
//    private final BrandRepository brandRepository;
//    private final ClothingRepository clothingRepository;
//
//    @Autowired
//    public DataInitializer(FakeDataGeneratorService fakeDataGeneratorService,
//                           BrandRepository brandRepository,
//                           ClothingRepository clothingRepository) {
//        this.fakeDataGeneratorService = fakeDataGeneratorService;
//        this.brandRepository = brandRepository;
//        this.clothingRepository = clothingRepository;
//    }
//
//    @PostConstruct
//    public void init() {
//        // Если хотите всегда генерировать данные, просто не делайте проверку
//        List<Brand> brands = fakeDataGeneratorService.generateBrands(1);
//        brandRepository.saveAll(brands);
//
//        List<Clothing> clothings = fakeDataGeneratorService.generateClothings(1, brands);
//        clothingRepository.saveAll(clothings);
//
//        System.out.println("Сгенерированы тестовые данные для брендов и одежды.");
//    }
//}
//
//
