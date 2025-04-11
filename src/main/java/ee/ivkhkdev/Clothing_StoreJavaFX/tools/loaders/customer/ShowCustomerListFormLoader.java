package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.customer;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;



@Component
public class ShowCustomerListFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public ShowCustomerListFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }





}
