package ee.ivkhkdev.Clothing_StoreJavaFX.controller.clothing;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Brand;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.BrandServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.clothing.AddBrandFormLoader;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.catalog.CatalogFormLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AddBrandFormController implements Initializable {

    private final BrandServiceImpl brandServiceImpl;
    private final AddBrandFormLoader addBrandFormLoader;

    @FXML
    private ListView<String> lvBrands;

    @FXML
    private TextField tfNewBrand;

    @FXML
    private Button btnAddBrand;

    public AddBrandFormController(BrandServiceImpl brandServiceImpl, CatalogFormLoader catalogFormLoader, AddBrandFormLoader addBrandFormLoader) {
        this.brandServiceImpl = brandServiceImpl;


        this.addBrandFormLoader = addBrandFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshBrandList();
    }

    @FXML
    private void addBrand() {
        String newBrand = tfNewBrand.getText();
        if (newBrand == null || newBrand.trim().isEmpty()) {
            tfNewBrand.setPromptText("Введите название бренда!");
            return;
        }

        Optional<Brand> result = brandServiceImpl.add(new Brand(newBrand));
        tfNewBrand.clear();
        refreshBrandList();
    }

    @FXML
    private void goToMainForm() {
        addBrandFormLoader.loadMainForm();
    }

    private void refreshBrandList() {
        List<String> brands = brandServiceImpl.getAllBrands();
        lvBrands.setItems(FXCollections.observableArrayList(brands));
    }
}