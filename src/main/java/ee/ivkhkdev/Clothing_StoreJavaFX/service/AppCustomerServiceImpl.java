package ee.ivkhkdev.Clothing_StoreJavaFX.service;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.Customer;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.CustomerRepository;
import interfaces.AppCustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppCustomerServiceImpl implements AppCustomerService {
    public static Customer currentCustomer;

    public enum ROLES {
        USER, MANAGER, ADMINISTRATOR
    }

    //Избавится от статического поля на динамический и также сделать так чтобы администратор мог менять роль зарегестрованы пользывателем
    //Сделать менеджера
    private final CustomerRepository repository;
    public AppCustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
        initSuperUser();
    }

    @Override
    public void initSuperUser() {
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

    @Override
    public Optional<Customer> add(Customer user) {

        if (user.getId() != null) {
            return Optional.of(repository.save(user));
        }
        // Если ID отсутствует, значит, это создание нового пользователя.
        if (repository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с логином '" + user.getUsername() + "' уже существует");
        }
        return Optional.of(repository.save(user));
    }



    @Override
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

    @Override
    public ObservableList<Customer> getListCustomers() {
        List<Customer> customers = repository.findAll();
        return FXCollections.observableArrayList(customers);
    }

    @Override
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }




}
