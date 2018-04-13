package computershop.database.dao.impl;

import computershop.database.dao.CustomerDAO;
import computershop.database.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public class CustomerDAOImpl extends DbSessionFactory implements CustomerDAO {

    @Override
    public List<Customer> getEntities() {
        List<Customer> customers = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
            customers = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return customers;
    }

    @Override
    public void saveEntity(Customer entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Customer getEntity(int id) {
        Customer customer = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            customer = currentSession.get(Customer.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return customer;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Customer where id=:customerId")
                    .setParameter("customerId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
