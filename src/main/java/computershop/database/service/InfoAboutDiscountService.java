package computershop.database.service;

import computershop.database.dao.InfoAboutDiscountsDAO;
import computershop.database.dao.impl.InfoAboutDiscountsDAOImpl;
import computershop.database.view.InfoAboutDiscounts;

import java.util.List;

public class InfoAboutDiscountService {
    private InfoAboutDiscountsDAO infoAboutDiscountDAO = new InfoAboutDiscountsDAOImpl();

    public List<InfoAboutDiscounts> getInfoAboutDiscounts() {
        return infoAboutDiscountDAO.getEntities();
    }

    public void saveInfoAboutDiscount(InfoAboutDiscounts infoAboutDiscount) {
        infoAboutDiscountDAO.saveEntity(infoAboutDiscount);
    }

    public InfoAboutDiscounts getInfoAboutDiscount(int id) {
        return infoAboutDiscountDAO.getEntity(id);
    }

    public void deleteInfoAboutDiscount(int id) {
        infoAboutDiscountDAO.deleteEntity(id);
    }

    public List<InfoAboutDiscounts> getDiscountsForAccount(int id) {
        return infoAboutDiscountDAO.getDiscountsForAccount(id);
    }
}
