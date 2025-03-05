package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("MainFormController initialized");
    }


    @FXML
    private void showEditClothingForm(ActionEvent event) {

        System.out.println("showEditClothingForm invoked.");
    }
}
