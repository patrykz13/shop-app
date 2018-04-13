package computershop.database.dao.impl;

import computershop.database.dao.AddressDAO;
import computershop.database.entity.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public class AddressDAOImpl extends DbSessionFactory implements AddressDAO {

    @Override
    public List<Address> getEntities() {
        List<Address> addresses = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Address> theQuery = currentSession.createQuery("from Address", Address.class);
            addresses = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return addresses;
    }

    @Override
    public void saveEntity(Address entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Address getEntity(int id) {
        Address address = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            address = currentSession.get(Address.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return address;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Address where id=:addressId")
                    .setParameter("addressId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
