package computershop.database.dao.impl;

import computershop.database.dao.InfoAboutSetDAO;
import computershop.database.dao.InfoAboutStationaryShopDAO;
import computershop.database.view.InfoAboutSet;
import computershop.database.view.InfoAboutStationaryShop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InfoAboutSetDAOImpl extends DbSessionFactory implements InfoAboutSetDAO {

    @Override
    public List<InfoAboutSet> getEntities() {
        List<InfoAboutSet> infoAboutStationaryShops = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutSet> theQuery = currentSession.createQuery("from InfoAboutSet order by setName", InfoAboutSet.class);
            infoAboutStationaryShops = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutStationaryShops;
    }

    @Override
    public void saveEntity(InfoAboutSet entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public InfoAboutSet getEntity(int id) {
        InfoAboutSet infoAboutSet = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            infoAboutSet = currentSession.get(InfoAboutSet.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutSet;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from InfoAboutSet where id=:infoAboutSetId")
                    .setParameter("infoAboutSetId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
