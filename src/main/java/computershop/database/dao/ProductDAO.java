package computershop.database.dao;

import computershop.database.basicCrud.EntityCRUD;
import computershop.database.entity.Product;

import java.util.List;

public interface ProductDAO extends EntityCRUD<Product> {
    public List<Product> searchEntities(String category, String name, String producer, String model, Float priceFrom, Float priceTo, Boolean amount);
}
