package computershop.database.dao.impl;

import computershop.database.dao.ProductSetDAO;
import computershop.database.entity.Product;
import computershop.database.entity.ProductSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductSetDAOImpl extends DbSessionFactory implements ProductSetDAO {
    @Override
    public List<ProductSet> getEntities() {
        List<ProductSet> productSets = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<ProductSet> theQuery = currentSession.createQuery("from ProductSet", ProductSet.class);
            productSets = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productSets;
    }

    @Override
    public void saveEntity(ProductSet entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public ProductSet getEntity(int id) {
        ProductSet productSet = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            productSet = currentSession.get(ProductSet.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productSet;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from ProductSet where id=:productSetId")
                    .setParameter("productSetId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
