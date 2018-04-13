package computershop.database.dao.impl;

import computershop.database.dao.OrderDAO;
import computershop.database.dao.OrderPositionDAO;
import computershop.database.entity.Order;
import computershop.database.entity.OrderPosition;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAOImpl extends DbSessionFactory implements OrderDAO {
    @Override
    public List<Order> getEntities() {
        List<Order> orders = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Order> theQuery = currentSession.createQuery("from Order", Order.class);
            orders = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return orders;
    }

    @Override
    public void saveEntity(Order entity) throws GenericJDBCException{
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        }
        catch (GenericJDBCException e){
            throw e;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public Order getEntity(int id) {
        Order order = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            order = currentSession.get(Order.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return order;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Order where id=:orderId")
                    .setParameter("orderId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
