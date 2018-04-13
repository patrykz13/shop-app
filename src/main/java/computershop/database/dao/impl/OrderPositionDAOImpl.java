package computershop.database.dao.impl;

import computershop.database.dao.OrderPositionDAO;
import computershop.database.entity.OrderPosition;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;

import java.util.List;

public class OrderPositionDAOImpl extends DbSessionFactory implements OrderPositionDAO {
    @Override
    public List<OrderPosition> getEntities() {
        List<OrderPosition> orderPositions = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<OrderPosition> theQuery = currentSession.createQuery("from OrderPosition order by name", OrderPosition.class);
            orderPositions = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return orderPositions;
    }

    @Override
    public void saveEntity(OrderPosition entity) throws GenericJDBCException{
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        }
        catch (GenericJDBCException e) {
          throw e;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public OrderPosition getEntity(int id) {
        OrderPosition orderPosition = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            orderPosition = currentSession.get(OrderPosition.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return orderPosition;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from OrderPosition where id=:orderPositionId")
                    .setParameter("orderPositionId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
