package ee.ivkhkdev.Clothing_StoreJavaFX.controller;



import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.FormService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class MenuFormController implements Initializable {

    private final FormService formService;

    @FXML private Menu mClothing;       // Меню для одежды (ранее mBooks)
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;

    public MenuFormController(FormService formService) {
        this.formService = formService;
    }

    @FXML
    private void showAddClothingForm(){
        //    formService.loadNewClothingForm();
    }

    @FXML
    private void showAddBrandForm() {
       // formService.loadBrandForm();
    }

    @FXML
    private void showLoginForm(){
        formService.loadLoginForm();
    }

    @FXML
    private void logout(){
     //   AppUserService.currentUser = null;
        formService.loadLoginForm();
    }

    private void initMenuVisible(){
        if (AppCustomerService.currentCustomer.getRoles().contains(AppCustomerService.ROLES.ADMINISTRATOR.toString())) {
            mClothing.setVisible(true);
            mAdmin.setVisible(true);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        } else if (AppCustomerService.currentCustomer.getRoles().contains(AppCustomerService.ROLES.MANAGER.toString())) {
            mClothing.setVisible(true);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        } else if (AppCustomerService.currentCustomer.getRoles().contains(AppCustomerService.ROLES.USER.toString())) {
            mClothing.setVisible(false);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}

