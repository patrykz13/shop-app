package computershop.database.service;

import computershop.database.dao.ProductDAO;
import computershop.database.dao.impl.ProductDAOImpl;
import computershop.database.entity.Product;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    public List<Product> getProducts() {
        return productDAO.getEntities();
    }

    public void saveProduct(Product product) {
        productDAO.saveEntity(product);
    }

    public Product getProduct(int id) {
        return productDAO.getEntity(id);
    }

    public void deleteProduct(int id) {
        productDAO.deleteEntity(id);
    }

    public List<Product> searchProducts(String category, String name, String producer, String model, Float priceFrom, Float priceTo, Boolean amount) {
        return productDAO.searchEntities(category, name, producer, model, priceFrom, priceTo, amount);
    }
}
