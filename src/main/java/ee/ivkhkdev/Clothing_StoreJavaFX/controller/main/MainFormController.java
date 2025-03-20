package ee.ivkhkdev.Clothing_StoreJavaFX.controller.main;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.ClothingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {

    private final FormLoader formLoader;
    private final ClothingService clothingService;

    @FXML private VBox vbMainFormRoot;
    @FXML private TableView<Clothing> tvClothingList;
    @FXML private TableColumn<Clothing, Long> tcId;
    @FXML private TableColumn<Clothing, String> tcName;
    @FXML private TableColumn<Clothing, String> tcBrand;
    @FXML private TableColumn<Clothing, String> tcSize;
    @FXML private TableColumn<Clothing, Integer> tcQuantity;
    @FXML private TableColumn<Clothing, Integer> tcInStock;
    @FXML private TableColumn<Clothing, Double> tcPrice;
    @FXML private HBox hbEditClothing;  // Контейнер для кнопки редактирования

    public MainFormController(FormLoader formLoader, ClothingService clothingService) {
        this.formLoader = formLoader;
        this.clothingService = clothingService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainFormController initialized");

        // Загружаем меню и добавляем его в начало корневого контейнера
        vbMainFormRoot.getChildren().add(0, formLoader.loadMenuForm());

        // Настройка столбцов таблицы
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tcSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcInStock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Устанавливаем данные в таблицу
        tvClothingList.setItems(clothingService.getListClothing());

        // Добавляем слушатель для изменения выделения в таблице:
        tvClothingList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                hbEditClothing.setVisible(true);
            } else {
                hbEditClothing.setVisible(false);
            }
        });
    }



    @FXML
    private void showEditClothingForm(ActionEvent actionEvent) {
        Clothing selectedClothing = tvClothingList.getSelectionModel().getSelectedItem();
        if (selectedClothing != null) {
            formLoader.loadEditClothingForm(selectedClothing);
        } else {
            System.out.println("Выберите товар для редактирования.");
        }
    }
}
