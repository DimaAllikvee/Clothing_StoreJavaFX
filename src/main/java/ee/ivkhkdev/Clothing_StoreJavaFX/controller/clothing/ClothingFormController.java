package ee.ivkhkdev.Clothing_StoreJavaFX.controller.clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
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
public class ClothingFormController implements Initializable {

    private final FormLoader formLoader;
    private final ClothingService clothingService;
    private final BrandService brandService;

    @FXML
    private TextField tfName;
    @FXML
    private ComboBox<String> cbBrand;
    @FXML
    private TextField tfSize;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfPrice;

    public ClothingFormController(FormLoader formLoader, ClothingService clothingService, BrandService brandService) {
        this.formLoader = formLoader;
        this.clothingService = clothingService;
        this.brandService = brandService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbBrand.getItems().setAll(brandService.getAllBrands());
    }

    @FXML
    private void add() {
        Clothing clothing = new Clothing();
        clothing.setName(tfName.getText());

        String selectedBrand = cbBrand.getSelectionModel().getSelectedItem();
        clothing.setBrand(selectedBrand);
        clothing.setSize(tfSize.getText());
        int quantity = Integer.parseInt(tfQuantity.getText());
        clothing.setQuantity(quantity);
        clothing.setInStock(quantity);
        double price = Double.parseDouble(tfPrice.getText());
        clothing.setPrice(price);

        clothingService.add(clothing);
        formLoader.loadMainForm();
    }

    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}