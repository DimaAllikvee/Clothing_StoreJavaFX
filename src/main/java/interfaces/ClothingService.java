package interfaces;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import javafx.collections.ObservableList;

public interface ClothingService {
    ObservableList<Clothing> getAllClothing();
    void addClothing(Clothing clothing);
    void updateClothing(Clothing clothing);
    void deleteClothing(Clothing clothing);
}