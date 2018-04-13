package computershop.database.dao.impl;

import computershop.database.dao.InfoAboutOrderDAO;
import computershop.database.dao.InfoAboutOrderPositionsDAO;
import computershop.database.view.InfoAboutOrder;
import computershop.database.view.InfoAboutOrderPositions;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InfoAboutOrderPositionsDAOImpl extends DbSessionFactory implements InfoAboutOrderPositionsDAO {

    @Override
    public List<InfoAboutOrderPositions> getEntities() {
        List<InfoAboutOrderPositions> infoAboutOrderPositions = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutOrderPositions> theQuery = currentSession.createQuery("from InfoAboutOrderPositions", InfoAboutOrderPositions.class);
            infoAboutOrderPositions = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrderPositions;
    }

    @Override
    public void saveEntity(InfoAboutOrderPositions entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public InfoAboutOrderPositions getEntity(int id) {
        InfoAboutOrderPositions infoAboutOrderPositions = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            infoAboutOrderPositions = currentSession.get(InfoAboutOrderPositions.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrderPositions;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from InfoAboutOrderPositions where id=:infoAboutOrderPositionsId")
                    .setParameter("infoAboutOrderPositionsId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public List<InfoAboutOrderPositions> getOrderPositionsForOrder(int id) {
        List<InfoAboutOrderPositions> infoAboutOrderPositions = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutOrderPositions> theQuery = currentSession.createQuery("from InfoAboutOrderPositions where orderId =:orderId", InfoAboutOrderPositions.class)
                    .setParameter("orderId", id);
            infoAboutOrderPositions = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutOrderPositions;
    }
}
