package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import interfaces.BrandService;
import interfaces.ClothingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditClothingFormController implements Initializable {

    private final FormLoader formLoader;
    private final ClothingService clothingService;
    private final BrandService brandService;
    private Clothing editClothing;

    @FXML private TextField tfName;
    @FXML private ComboBox<String> cbBrand;
    @FXML private TextField tfSize;
    @FXML private TextField tfQuantity;
    @FXML private TextField tfPrice;

    public EditClothingFormController(FormLoader formLoader, ClothingService clothingService, BrandService brandService) {
        this.formLoader = formLoader;
        this.clothingService = clothingService;
        this.brandService = brandService;
    }

    /**
     * Устанавливает объект для редактирования и заполняет поля формы.
     */
    public void setEditClothing(Clothing editClothing) {
        this.editClothing = editClothing;
        tfName.setText(editClothing.getName());
        cbBrand.getItems().setAll(brandService.getAllBrands());
        cbBrand.getSelectionModel().select(editClothing.getBrand());
        tfSize.setText(editClothing.getSize());
        tfQuantity.setText(String.valueOf(editClothing.getQuantity()));
        tfPrice.setText(String.valueOf(editClothing.getPrice()));
    }

    /**
     * Сохраняет изменения в объекте товара и возвращает на главную форму.
     */
    @FXML
    private void goEdit() {
        if (editClothing != null) {
            editClothing.setName(tfName.getText());
            String selectedBrand = cbBrand.getSelectionModel().getSelectedItem();
            editClothing.setBrand(selectedBrand);
            editClothing.setSize(tfSize.getText());
            editClothing.setQuantity(Integer.parseInt(tfQuantity.getText()));
            editClothing.setPrice(Double.parseDouble(tfPrice.getText()));
            clothingService.add(editClothing);
        }
        formLoader.loadMainForm();

    }

    /**
     * Возвращает на главную форму без сохранения изменений.
     */
    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbBrand.getItems().setAll(brandService.getAllBrands());
    }
}
