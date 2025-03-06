package ee.ivkhkdev.Clothing_StoreJavaFX.controller;



import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class MenuFormController implements Initializable {

    private final FormLoader formLoader;

    @FXML private Menu mClothing;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;
    @FXML private MenuItem miAddCustomer;
    @FXML private MenuItem miShowCustomerList;

    public MenuFormController(FormLoader formLoader) {
        this.formLoader = formLoader;
    }

    @FXML
    private void showAddClothingForm(){
         formLoader.showAddClothingForm();
    }

    @FXML
    private void showAddCustomerForm(){
        formLoader.showAddCustomerForm();
    }

    @FXML
    private void showAddBrandForm() {
       formLoader.showAddBrandForm();
    }

    @FXML
    private void showLoginForm(){
        formLoader.loadLoginForm();
    }

    @FXML
    private void logout(){
     //   AppUserService.currentUser = null;
        formLoader.loadLoginForm();
    }

    @FXML
    private void showCustomersForm(){
        formLoader.showCustomersForm();
    }

    private void initMenuVisible(){
        if (AppCustomerServiceImpl.currentCustomer.getRoles().contains(AppCustomerServiceImpl.ROLES.ADMINISTRATOR.toString())) {
            mClothing.setVisible(true);
            mAdmin.setVisible(true);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        } else if (AppCustomerServiceImpl.currentCustomer.getRoles().contains(AppCustomerServiceImpl.ROLES.MANAGER.toString())) {
            mClothing.setVisible(true);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        } else if (AppCustomerServiceImpl.currentCustomer.getRoles().contains(AppCustomerServiceImpl.ROLES.USER.toString())) {
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

