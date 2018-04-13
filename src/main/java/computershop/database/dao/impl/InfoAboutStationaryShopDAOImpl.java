package computershop.database.dao.impl;

import computershop.database.dao.InfoAboutStationaryShopDAO;
import computershop.database.entity.Customer;
import computershop.database.view.InfoAboutStationaryShop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InfoAboutStationaryShopDAOImpl extends DbSessionFactory implements InfoAboutStationaryShopDAO {

    @Override
    public List<InfoAboutStationaryShop> getEntities() {
        List<InfoAboutStationaryShop> infoAboutStationaryShops = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutStationaryShop> theQuery = currentSession.createQuery("from InfoAboutStationaryShop order by name", InfoAboutStationaryShop.class);
            infoAboutStationaryShops = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutStationaryShops;
    }

    @Override
    public void saveEntity(InfoAboutStationaryShop entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public InfoAboutStationaryShop getEntity(int id) {
        InfoAboutStationaryShop infoAboutStationaryShop = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            infoAboutStationaryShop = currentSession.get(InfoAboutStationaryShop.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutStationaryShop;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from InfoAboutStationaryShop where id=:infoAboutStationaryShopId")
                    .setParameter("infoAboutStationaryShopId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
