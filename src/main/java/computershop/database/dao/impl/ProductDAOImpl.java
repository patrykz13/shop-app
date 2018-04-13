package computershop.database.dao.impl;

import computershop.database.dao.ProductDAO;
import computershop.database.entity.Product;
import computershop.database.entity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl extends DbSessionFactory implements ProductDAO {
    @Override
    public List<Product> getEntities() {
        List<Product> products = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Product> theQuery = currentSession.createQuery("from Product order by name", Product.class);
            products = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return products;
    }

    @Override
    public void saveEntity(Product entity) throws GenericJDBCException {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (GenericJDBCException e) {
            throw e;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Product getEntity(int id) {
        Product product = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            product = currentSession.get(Product.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return product;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Product where id=:productId")
                    .setParameter("productId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public List<Product> searchEntities(String category, String name, String producer, String model,
                                        Float priceFrom, Float priceTo, Boolean amount) {
        List<Product> products = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Product> theQuery = currentSession.createQuery("select p from Product p " +
                    "where p.name like:searchedName and (:searchedCategory like 'Wszystkie kategorie' or " +
                    "p.productCategory.name=:searchedCategory) and p.producer like:searchedProducer " +
                            "and p.model like:searchedModel and (:searchedAmount!=false or p.amount> 0) " +
                            "and p.sellingPriceBrutto>=:searchedPriceFrom and p.sellingPriceBrutto<=:searchedPriceTo"
                    , Product.class).setParameter("searchedName", "%" + name + "%")
                    .setParameter("searchedAmount", amount)
                    .setParameter("searchedCategory", category)
                    .setParameter("searchedProducer", "%" + producer + "%")
                    .setParameter("searchedModel", "%" + model + "%")
                    .setParameter("searchedPriceFrom", priceFrom)
                    .setParameter("searchedPriceTo", priceTo);
            products = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return products;
    }
}
