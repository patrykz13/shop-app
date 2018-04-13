package computershop.database.service;

import computershop.database.dao.DiscountDAO;
import computershop.database.dao.impl.DiscountDAOImpl;
import computershop.database.entity.Discount;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class DiscountService {
    private DiscountDAO discountDAO = new DiscountDAOImpl();

    public List<Discount> getOrderPositions() {
        return discountDAO.getEntities();
    }

    public void saveDiscount(Discount discount) {
        discountDAO.saveEntity(discount);
    }

    public Discount getDiscount(int id) {
        return discountDAO.getEntity(id);
    }

    public void deleteDiscount(int id) {
        discountDAO.deleteEntity(id);
    }
}
