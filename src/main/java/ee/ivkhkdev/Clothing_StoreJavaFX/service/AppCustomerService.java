package ee.ivkhkdev.Clothing_StoreJavaFX.service;

import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AppCustomerService {
    public static Customer currentCustomer;
    public enum ROLES{
        USER, MANAGER, ADMINISTRATOR
    }
    private CustomerRepository repository;

    public AppCustomerService(CustomerRepository repository) {
        this.repository = repository;
        initSuperUser();
    }

    private void initSuperUser() {
        if (repository.count() > 0) {
            return;
        }
        Customer admin = new Customer();
        admin.setUsername("admin");
        admin.setPassword("12345");
        admin.setFirstname("Admin");
        admin.setLastname("SuperAdmin");
        admin.getRoles().add(ROLES.ADMINISTRATOR.toString());
        admin.getRoles().add(ROLES.MANAGER.toString());
        admin.getRoles().add(ROLES.USER.toString());
        repository.save(admin);
    }
    public void add(Customer user) {
        repository.save(user);
    }
    public boolean authentication(String username, String password) {
        Optional<Customer> optionalAppUser = repository.findByUsername(username);
        if(optionalAppUser.isEmpty()) {
            return false;
        }
        Customer loginUser = optionalAppUser.get();
        if(!loginUser.getPassword().equals(password)) {
            return false;
        }
        currentCustomer = loginUser;
        return true;
    }

}