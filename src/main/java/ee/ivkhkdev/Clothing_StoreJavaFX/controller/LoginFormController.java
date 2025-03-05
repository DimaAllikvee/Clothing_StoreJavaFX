package ee.ivkhkdev.Clothing_StoreJavaFX.controller;

import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerService;
import ee.ivkhkdev.Clothing_StoreJavaFX.service.FormService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {
    private FormService formService;
    private AppCustomerService appCustomerService;

    @FXML
    private Label lbInfo;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;

    public LoginFormController(FormService formService, AppCustomerService appUserService) {
        this.formService = formService;
        this.appCustomerService = appUserService;

    }

    @FXML private void login(){
        if(appCustomerService.authentication(tfUsername.getText(),pfPassword.getText())){
            formService.loadMainForm();
        }else{
            lbInfo.setText("Нет такого пользователя, или неправильный пароль");
        }
    }
    @FXML private void showRegistrationForm(){
        formService.loadRegistrationForm();
    }
}
