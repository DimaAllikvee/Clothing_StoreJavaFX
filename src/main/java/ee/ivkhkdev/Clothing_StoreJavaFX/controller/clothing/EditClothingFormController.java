package ee.ivkhkdev.Clothing_StoreJavaFX.controller.clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.clothing.EditClothingFormLoader;
import interfaces.BrandService;
import interfaces.ClothingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditClothingFormController implements Initializable {


    private final ClothingService clothingService;
    private final BrandService brandService;
    private Clothing editClothing;
    private final EditClothingFormLoader editClothingFormLoader;

    @FXML private TextField tfName;
    @FXML private ComboBox<String> cbBrand;
    @FXML private TextField tfSize;
    @FXML private TextField tfQuantity;
    @FXML private TextField tfPrice;
    @FXML private TextField tcInStock;


    public EditClothingFormController(ClothingService clothingService, BrandService brandService, EditClothingFormLoader editClothingFormLoader) {

        this.clothingService = clothingService;
        this.brandService = brandService;
        this.editClothingFormLoader = editClothingFormLoader;
    }

    public void setEditClothing(Clothing editClothing) {
        this.editClothing = editClothing;
        tfName.setText(editClothing.getName());
        cbBrand.getItems().setAll(brandService.getAllBrands());
        cbBrand.getSelectionModel().select(editClothing.getBrand());
        tfSize.setText(editClothing.getSize());
        tfQuantity.setText(String.valueOf(editClothing.getQuantity()));
        tcInStock.setText(String.valueOf(editClothing.getInStock()));
        tfPrice.setText(String.valueOf(editClothing.getPrice()));
    }


    @FXML
    private void goEdit() {
        if (editClothing != null) {
            editClothing.setName(tfName.getText());
            String selectedBrand = cbBrand.getSelectionModel().getSelectedItem();
            editClothing.setBrand(selectedBrand);
            editClothing.setSize(tfSize.getText());
            editClothing.setQuantity(Integer.parseInt(tfQuantity.getText()));
            editClothing.setInStock(Integer.parseInt(tcInStock.getText()));
            editClothing.setPrice(Double.parseDouble(tfPrice.getText()));
            clothingService.add(editClothing);
        }
        editClothingFormLoader.loadMainForm();

    }


    @FXML
    private void goToMainForm() {
        editClothingFormLoader.loadMainForm();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbBrand.getItems().setAll(brandService.getAllBrands());
    }
}
