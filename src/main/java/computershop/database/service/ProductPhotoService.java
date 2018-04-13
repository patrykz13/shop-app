package computershop.database.service;

import computershop.database.dao.ProductPhotoDAO;
import computershop.database.dao.impl.ProductPhotoDAOImpl;
import computershop.database.entity.ProductPhoto;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class ProductPhotoService{
    private ProductPhotoDAO productPhotoDAO = new ProductPhotoDAOImpl();

    public List<ProductPhoto> getProductPhotos() {
        return productPhotoDAO.getEntities();
    }

    public void saveProductPhoto(ProductPhoto productPhoto) {
        productPhotoDAO.saveEntity(productPhoto);
    }

    public ProductPhoto getProductPhoto(int id) {
        return productPhotoDAO.getEntity(id);
    }

    public void deleteProductPhoto(int id) {
        productPhotoDAO.deleteEntity(id);
    }
}
