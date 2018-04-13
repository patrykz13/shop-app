package computershop.database.dao.impl;

import computershop.database.dao.ProductCategoryDAO;
import computershop.database.entity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductCategoryDAOImpl extends DbSessionFactory implements ProductCategoryDAO {
    @Override
    public List<ProductCategory> getEntities() {
        List<ProductCategory> productCategories = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<ProductCategory> theQuery = currentSession.createQuery("from ProductCategory order by name", ProductCategory.class);
            productCategories = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productCategories;
    }

    @Override
    public void saveEntity(ProductCategory entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public ProductCategory getEntity(int id) {
        ProductCategory productCategory = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            productCategory = currentSession.get(ProductCategory.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productCategory;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from ProductCategory where id=:productCategoryId")
                    .setParameter("productCategoryId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
