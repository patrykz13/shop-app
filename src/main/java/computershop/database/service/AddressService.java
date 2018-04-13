package computershop.database.service;

import computershop.database.dao.AddressDAO;
import computershop.database.dao.impl.AddressDAOImpl;
import computershop.database.entity.Address;

import java.util.List;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public class AddressService {
    private AddressDAO addressDAO = new AddressDAOImpl();

    public List<Address> getAddresses() {
        return addressDAO.getEntities();
    }

    public void saveAddress(Address address) {
            addressDAO.saveEntity(address);
    }

    public Address getAddress(int id) {
        return addressDAO.getEntity(id);
    }

    public void deleteAddress(int id) {
        addressDAO.deleteEntity(id);
    }
}
