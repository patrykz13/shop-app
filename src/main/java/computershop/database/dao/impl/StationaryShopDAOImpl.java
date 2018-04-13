package computershop.database.dao.impl;

import computershop.database.dao.StationaryShopDAO;
import computershop.database.entity.StationaryShop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class StationaryShopDAOImpl extends DbSessionFactory implements StationaryShopDAO {

    @Override
    public List<StationaryShop> getEntities() {
        List<StationaryShop> stationaryShops = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<StationaryShop> theQuery = currentSession.createQuery("from StationaryShop order by name", StationaryShop.class);
            stationaryShops = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return stationaryShops;
    }

    @Override
    public void saveEntity(StationaryShop entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public StationaryShop getEntity(int id) {
        StationaryShop stationaryShop = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            stationaryShop = currentSession.get(StationaryShop.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return stationaryShop;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from StationaryShop where id=:stationaryShopId")
                    .setParameter("stationaryShopId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
