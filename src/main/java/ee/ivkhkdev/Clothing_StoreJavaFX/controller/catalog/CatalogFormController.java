package ee.ivkhkdev.Clothing_StoreJavaFX.controller.catalog;

import ee.ivkhkdev.Clothing_StoreJavaFX.controller.card.CardClothingController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.catalog.CatalogFormLoader;
import interfaces.ClothingService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CatalogFormController implements Initializable {

    @FXML private MenuBar menuBar;
    @FXML private FlowPane flowPaneClothing;

    private final ClothingService clothingService;
    private final CatalogFormLoader catalogFormLoader;

    public CatalogFormController(ClothingService clothingService,CatalogFormLoader catalogFormLoader) {
        this.clothingService = clothingService;

        this.catalogFormLoader = catalogFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadClothingCards();
    }

    /**
     * Загружает карточки товаров и добавляет их в FlowPane.
     */
    private void loadClothingCards() {
        flowPaneClothing.getChildren().clear();
        for (Clothing clothing : clothingService.getListClothing()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cards/CardClothing.fxml"));
                Node cardNode = loader.load();

                CardClothingController controller = loader.getController();
                controller.setClothing(clothing);
                controller.setFormLoader(catalogFormLoader);

                flowPaneClothing.getChildren().add(cardNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void goHome() {
        System.out.println("Возврат на главную");
        catalogFormLoader.loadMainFormCatalog();
    }

    @FXML
    private void reloadCatalog() {
        System.out.println("Обновление каталога");
        loadClothingCards();
    }

    @FXML
    private void showProfile() {
        System.out.println("Открыть профиль");
        catalogFormLoader.loadProfileForm();
    }

    @FXML
    private void logout() {
        System.out.println("Выход");
        catalogFormLoader.loadLoginForm();
    }
}
