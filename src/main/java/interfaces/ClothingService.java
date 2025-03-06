package interfaces;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import javafx.collections.ObservableList;



public interface ClothingService extends AppService<Clothing> {
    ObservableList<Clothing> getListClothing();
}
