package ee.ivkhkdev.Clothing_StoreJavaFX.controller.card;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Clothing;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardClothingController {

    @FXML private ImageView imgClothing;
    @FXML private Label lblName;
    @FXML private Label lblBrand;
    @FXML private Label lblSize;
    @FXML private Label lblPrice;
    @FXML private Label lblInStock;
    @FXML private Button btnBuy;

    private Clothing clothing;
    private FormLoader formLoader;

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
        lblName.setText(clothing.getName());
        lblBrand.setText("Бренд: " + clothing.getBrand());
        lblSize.setText("Размер: " + clothing.getSize());
        lblPrice.setText("Цена: " + clothing.getPrice());
        lblInStock.setText("В наличии: " + clothing.getInStock());
    }

    public void setFormLoader(FormLoader formLoader) {
        this.formLoader = formLoader;
    }


    @FXML
    private void buyClothing() {
        if (formLoader != null) {
            // Открываем форму оформления заказа для данного товара
            formLoader.loadUserOrderForm(clothing);
        } else {
            System.out.println("FormLoader не установлен. Проверьте инъекцию зависимостей.");
        }
    }
}
