package computershop.database.dao.impl;

import computershop.database.dao.InfoAboutOrderDAO;
import computershop.database.view.InfoAboutOrder;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InfoAboutOrderDAOImpl extends DbSessionFactory implements InfoAboutOrderDAO {

    @Override
    public List<InfoAboutOrder> getEntities() {
        List<InfoAboutOrder> infoAboutOrders = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutOrder> theQuery = currentSession.createQuery("from InfoAboutOrder", InfoAboutOrder.class);
            infoAboutOrders = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrders;
    }

    @Override
    public void saveEntity(InfoAboutOrder entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public InfoAboutOrder getEntity(int id) {
        InfoAboutOrder infoAboutOrder = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            infoAboutOrder = currentSession.get(InfoAboutOrder.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrder;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from InfoAboutOrder where id=:infoAboutOrderId")
                    .setParameter("infoAboutOrderId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public List<InfoAboutOrder> getOrdersForCustomer(int id) {
        List<InfoAboutOrder> infoAboutOrders = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutOrder> theQuery = currentSession.createQuery("from InfoAboutOrder where customer_id =:customerId", InfoAboutOrder.class)
                    .setParameter("customerId", id);
            infoAboutOrders = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrders;
    }
}
