package computershop.database.service;

import computershop.database.dao.ProductSetDAO;
import computershop.database.dao.impl.ProductSetDAOImpl;
import computershop.database.entity.ProductSet;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class ProductSetService {
    private ProductSetDAO productSetDAO = new ProductSetDAOImpl();

    public List<ProductSet> getProductSet() {
        return productSetDAO.getEntities();
    }

    public void saveProductSet(ProductSet productSet) {
        productSetDAO.saveEntity(productSet);
    }

    public ProductSet getProductSet(int id) {
        return productSetDAO.getEntity(id);
    }

    public void deleteProductSet(int id) {
        productSetDAO.deleteEntity(id);
    }
}
