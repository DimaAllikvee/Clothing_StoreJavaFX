//package ee.ivkhkdev.Clothing_StoreJavaFX.service;
//
//import com.github.javafaker.Faker;
//import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
//import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Service
//public class FakeDataGeneratorService {
//
//    private final Faker faker;
//    private final Random random;
//
//    public FakeDataGeneratorService() {
//        this.faker = new Faker();
//        this.random = new Random();
//    }
//
//
//    public List<Brand> generateBrands(int count) {
//        List<Brand> brands = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//
//            brands.add(new Brand(faker.company().name()));
//        }
//        return brands;
//    }
//
//
//    public List<Clothing> generateClothings(int count, List<Brand> brands) {
//        List<Clothing> clothings = new ArrayList<>();
//        // Пример возможных размеров
//        String[] sizes = {"XS", "S", "M", "L", "XL", "XXL"};
//        for (int i = 0; i < count; i++) {
//            // Выбираем случайный бренд из списка
//            Brand randomBrand = brands.get(random.nextInt(brands.size()));
//            String name = faker.commerce().productName();
//            String size = sizes[random.nextInt(sizes.length)];
//            String color = faker.color().name();
//            double price = Double.parseDouble(faker.commerce().price(10.0, 100.0));
//            int quantity = random.nextInt(50) + 10; // от 10 до 59
//            int inStock = quantity; // изначально весь товар в наличии
//
//            Clothing clothing = new Clothing(name, randomBrand.getName(), size, color, price, quantity, inStock);
//            clothings.add(clothing);
//        }
//        return clothings;
//    }
//}
