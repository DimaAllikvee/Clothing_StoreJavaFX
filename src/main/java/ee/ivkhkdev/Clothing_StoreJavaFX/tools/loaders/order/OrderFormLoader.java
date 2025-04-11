package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.order;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class OrderFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public OrderFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }


}
