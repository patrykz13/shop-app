package computershop.database.service;

import computershop.database.dao.CustomerDAO;
import computershop.database.dao.impl.CustomerDAOImpl;
import computershop.database.entity.Customer;

import java.util.List;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public List<Customer> getCustomers() {
        return customerDAO.getEntities();
    }

    public void saveCustomer(Customer customer) {
        customerDAO.saveEntity(customer);
    }

    public Customer getCustomer(int id) {
        return customerDAO.getEntity(id);
    }

    public void deleteCustomer(int id) {
        customerDAO.deleteEntity(id);
    }
}
