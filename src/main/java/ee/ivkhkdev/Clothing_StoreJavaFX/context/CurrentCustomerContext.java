package ee.ivkhkdev.Clothing_StoreJavaFX.context;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CurrentCustomerContext {

    private Customer currentCustomer;

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}
