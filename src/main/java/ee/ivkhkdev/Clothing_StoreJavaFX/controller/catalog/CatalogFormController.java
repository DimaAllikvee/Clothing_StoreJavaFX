package ee.ivkhkdev.Clothing_StoreJavaFX.controller.catalog;

import ee.ivkhkdev.Clothing_StoreJavaFX.controller.card.CardClothingController;
import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
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
    private final FormLoader formLoader;

    public CatalogFormController(ClothingService clothingService, FormLoader formLoader) {
        this.clothingService = clothingService;
        this.formLoader = formLoader;
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
                // Передаём FormLoader в контроллер карточки
                controller.setFormLoader(formLoader);

                flowPaneClothing.getChildren().add(cardNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // =========== Обработчики пунктов меню ===========

    @FXML
    private void goHome() {
        System.out.println("Возврат на главную");
        formLoader.loadMainFormCatalog();
    }

    @FXML
    private void reloadCatalog() {
        System.out.println("Обновление каталога");
        loadClothingCards();
    }

    @FXML
    private void showProfile() {
        System.out.println("Открыть профиль");
        formLoader.loadProfileForm();
    }

    @FXML
    private void logout() {
        System.out.println("Выход");
        formLoader.loadLoginForm();
    }
}
