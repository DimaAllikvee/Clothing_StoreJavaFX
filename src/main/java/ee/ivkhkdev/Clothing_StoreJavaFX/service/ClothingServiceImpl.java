package ee.ivkhkdev.Clothing_StoreJavaFX.service;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.ClothingRepository;
import interfaces.ClothingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    @Override
    public Optional<Clothing> add(Clothing clothing) {
        return Optional.of(clothingRepository.save(clothing));
    }

    @Override
    public ObservableList<Clothing> getListClothing() {
        List<Clothing> list = clothingRepository.findAll();
        return FXCollections.observableArrayList(list);
    }
}
