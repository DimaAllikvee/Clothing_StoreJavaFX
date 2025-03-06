package ee.ivkhkdev.Clothing_StoreJavaFX.controller;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.ClothingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {

    private final FormLoader formLoader;
    private final ClothingService clothingService;

    @FXML
    private VBox vbMainFormRoot;

    @FXML
    private TableView<Clothing> tvClothingList;

    @FXML
    private TableColumn<Clothing, Long> tcId;

    @FXML
    private TableColumn<Clothing, String> tcName;

    @FXML
    private TableColumn<Clothing, String> tcBrand;

    @FXML
    private TableColumn<Clothing, String> tcSize;

    @FXML
    private TableColumn<Clothing, Integer> tcQuantity;

    @FXML
    private TableColumn<Clothing, Integer> tcInStock;

    @FXML
    private TableColumn<Clothing, Double> tcPrice;

    public MainFormController(FormLoader formLoader, ClothingService clothingService) {
        this.formLoader = formLoader;
        this.clothingService = clothingService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainFormController initialized");

        vbMainFormRoot.getChildren().add(0, formLoader.loadMenuForm());


        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tcSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcInStock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Устанавливаем список одежды, полученный из сервиса, в таблицу
        tvClothingList.setItems(clothingService.getListClothing());
    }

    @FXML
    private void showEditClothingForm(ActionEvent event) {
        System.out.println("showEditClothingForm invoked.");
        // Логика редактирования выбранной модели одежды
    }
}
