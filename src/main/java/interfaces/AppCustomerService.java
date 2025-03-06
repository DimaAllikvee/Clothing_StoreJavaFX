package interfaces;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;

public interface AppCustomerService extends AppService<Customer>  {
    void initSuperUser();
    boolean authentication(String username, String password);
}
