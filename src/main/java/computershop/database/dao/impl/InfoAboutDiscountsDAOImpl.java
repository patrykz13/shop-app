package computershop.database.dao.impl;

import computershop.database.dao.InfoAboutDiscountsDAO;
import computershop.database.dao.InfoAboutSetDAO;
import computershop.database.view.InfoAboutDiscounts;
import computershop.database.view.InfoAboutSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InfoAboutDiscountsDAOImpl extends DbSessionFactory implements InfoAboutDiscountsDAO {

    @Override
    public List<InfoAboutDiscounts> getEntities() {
        List<InfoAboutDiscounts> infoAboutDiscounts = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutDiscounts> theQuery = currentSession.createQuery("from InfoAboutDiscounts", InfoAboutDiscounts.class);
            infoAboutDiscounts = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutDiscounts;
    }

    @Override
    public void saveEntity(InfoAboutDiscounts entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public InfoAboutDiscounts getEntity(int id) {
        InfoAboutDiscounts infoAboutDiscount = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            infoAboutDiscount = currentSession.get(InfoAboutDiscounts.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutDiscount;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from InfoAboutDiscounts where id=:infoAboutDiscountId")
                    .setParameter("infoAboutDiscountId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public List<InfoAboutDiscounts> getDiscountsForAccount(int id) {
        List<InfoAboutDiscounts> infoAboutDiscounts = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<InfoAboutDiscounts> theQuery = currentSession.createQuery("from InfoAboutDiscounts where accountId =:accountId", InfoAboutDiscounts.class)
                    .setParameter("accountId", id);
            infoAboutDiscounts = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return infoAboutDiscounts;
    }
}
