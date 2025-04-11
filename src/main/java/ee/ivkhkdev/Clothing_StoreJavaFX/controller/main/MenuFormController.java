package ee.ivkhkdev.Clothing_StoreJavaFX.controller.main;

import ee.ivkhkdev.Clothing_StoreJavaFX.service.AppCustomerServiceImpl;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.loaders.main.MenuFormLoader;
import interfaces.AppCustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {


    private final MenuFormLoader menuFormLoader;
    private final AppCustomerService appCustomerService;

    // Меню
    @FXML private Menu mClothing;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;

    // Пункты меню
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;
    @FXML private MenuItem miAddCustomer;
    @FXML private MenuItem miShowCustomerList;
    @FXML private MenuItem miAddClothing;
    @FXML private MenuItem miAddBrand;
    @FXML private MenuItem miListClothing;
    @FXML private MenuItem miPlaceOrder;

    public MenuFormController(MenuFormLoader menuFormLoader, AppCustomerService appCustomerService) {
        this.menuFormLoader = menuFormLoader;

        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }

    private void initMenuVisible(){
        if (appCustomerService.getCurrentCustomer() == null) {
            // Если никто не вошел, показываем только Вход
            mClothing.setVisible(false);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);

            miEnter.setVisible(true);
            miProfile.setVisible(false);
            miLogout.setVisible(false);
            miAddCustomer.setVisible(false);
            miShowCustomerList.setVisible(false);
            miAddClothing.setVisible(false);
            miAddBrand.setVisible(false);
            miListClothing.setVisible(false);
            miPlaceOrder.setVisible(false);
            return;
        }

        // Если пользователь вошел, смотрим роли
        if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.ADMINISTRATOR.toString())) {
            // Администратор видит всё
            mClothing.setVisible(true);
            mAdmin.setVisible(true);
            mUsers.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            miAddCustomer.setVisible(true);
            miShowCustomerList.setVisible(true);
            miAddClothing.setVisible(true);
            miAddBrand.setVisible(true);
            miListClothing.setVisible(true);
            miPlaceOrder.setVisible(true);

        } else if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.MANAGER.toString())) {
            // Менеджер видит товары и пользователей, но не Админ меню
            mClothing.setVisible(true);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            miAddCustomer.setVisible(true);
            miShowCustomerList.setVisible(true);
            miAddClothing.setVisible(true);
            miAddBrand.setVisible(true);
            miListClothing.setVisible(true);
            miPlaceOrder.setVisible(true);

        } else if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.USER.toString())) {
            // Обычный пользователь видит только список товаров, оформить заказ, профиль и выход
            mClothing.setVisible(true); // Можно оставить видимым, но скрыть ненужные пункты
            mAdmin.setVisible(false);
            mUsers.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);

            // Скрываем админские пункты
            miAddCustomer.setVisible(false);
            miShowCustomerList.setVisible(false);
            miAddClothing.setVisible(false);
            miAddBrand.setVisible(false);

            // Показываем список товаров и оформление заказа
            miListClothing.setVisible(true);
            miPlaceOrder.setVisible(false);
        }
    }

    // Методы, вызываемые при выборе пунктов меню
    @FXML
    private void showAddClothingForm(){
        menuFormLoader.showAddClothingForm();
    }

    @FXML
    private void showAddCustomerForm(){
        menuFormLoader.showAddCustomerForm();
    }

    @FXML
    private void showAddBrandForm() {
        menuFormLoader.showAddBrandForm();
    }

    @FXML
    private void showLoginForm(){
        menuFormLoader.loadLoginForm();
    }

    @FXML
    private void logout(){

        appCustomerService.logout();
        menuFormLoader.loadLoginForm();
    }

    @FXML
    private void showCustomersForm(){
        menuFormLoader.showCustomersForm();
    }

    @FXML
    private void showProfileForm() {
        menuFormLoader.loadProfileForm();
    }

    @FXML
    private void showOrderForm() {
        menuFormLoader.loadOrderForm();
    }

    @FXML
    private void showEditCustomer(ActionEvent event) {
        System.out.println("showEditCustomer invoked.");
    }

    @FXML
    private void showCatalogForm() {
        menuFormLoader.loadCatalogForm();
    }

    @FXML
    private void showRevenueForm() {
        menuFormLoader.loadRevenueForm();
    }

    @FXML
    private void showRatingForm() {
        menuFormLoader.loadRatingChartForm();
    }

}
