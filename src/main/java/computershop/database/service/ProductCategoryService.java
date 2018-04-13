package computershop.database.service;

import computershop.database.dao.ProductCategoryDAO;
import computershop.database.dao.impl.ProductCategoryDAOImpl;
import computershop.database.entity.ProductCategory;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class ProductCategoryService {
    private ProductCategoryDAO productCategoryDAO = new ProductCategoryDAOImpl();

    public List<ProductCategory> getProductCategories() {
        return productCategoryDAO.getEntities();
    }

    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryDAO.saveEntity(productCategory);
    }

    public ProductCategory getProductCategory(int id) {
        return productCategoryDAO.getEntity(id);
    }

    public void deleteProductCategory(int id) {
        productCategoryDAO.deleteEntity(id);
    }
}
