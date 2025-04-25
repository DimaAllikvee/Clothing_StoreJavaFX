package interfaces;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import javafx.collections.ObservableList;

public interface AppCustomerService extends AppService<Customer>  {
    void initSuperUser();
    boolean authentication(String username, String password);
    ObservableList<Customer> getListCustomers();
    void logout();
    Customer getCurrentCustomer();
    void addRole(Customer user, String role);
    void removeRole(Customer user, String role);

}
