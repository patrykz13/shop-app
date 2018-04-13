package computershop.database.dao.impl;

import computershop.database.dao.DiscountDAO;
import computershop.database.entity.Discount;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DiscountDAOImpl extends DbSessionFactory implements DiscountDAO {
    @Override
    public List<Discount> getEntities() {
        List<Discount> discounts = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Discount> theQuery = currentSession.createQuery("from Discount", Discount.class);
            discounts = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return discounts;
    }

    @Override
    public void saveEntity(Discount entity){
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public Discount getEntity(int id) {
        Discount discount = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            discount = currentSession.get(Discount.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return discount;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Discount where id=:discountId")
                    .setParameter("discountId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
