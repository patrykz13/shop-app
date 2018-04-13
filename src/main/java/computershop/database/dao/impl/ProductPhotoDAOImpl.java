package computershop.database.dao.impl;

import computershop.database.dao.ProductPhotoDAO;
import computershop.database.entity.ProductPhoto;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductPhotoDAOImpl extends DbSessionFactory implements ProductPhotoDAO {
    @Override
    public List<ProductPhoto> getEntities() {
        List<ProductPhoto> productPhotos = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<ProductPhoto> theQuery = currentSession.createQuery("from ProductPhoto", ProductPhoto.class);
            productPhotos = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productPhotos;
    }

    @Override
    public void saveEntity(ProductPhoto entity) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.save(entity);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public ProductPhoto getEntity(int id) {
        ProductPhoto productPhoto = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            productPhoto = currentSession.get(ProductPhoto.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productPhoto;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from ProductPhoto where id=:productPhotoId")
                    .setParameter("productPhotoId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
