package ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.rating;

import ee.ivkhkdev.Clothing_StoreJavaFX.ClothingStoreApp;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RatingFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public RatingFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ClothingStoreApp.primaryStage;
    }

    public void loadRatingChartForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/rating/ratingForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить RatingForm.fxml", e);
        }
        Stage stage = getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Рейтинг продаж");
        stage.centerOnScreen();
        stage.show();
    }
}
