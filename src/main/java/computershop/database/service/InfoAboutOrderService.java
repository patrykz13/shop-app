package computershop.database.service;

import computershop.database.dao.InfoAboutOrderDAO;
import computershop.database.dao.impl.InfoAboutOrderDAOImpl;
import computershop.database.view.InfoAboutOrder;

import java.util.List;

public class InfoAboutOrderService {
    private InfoAboutOrderDAO infoAboutOrderDAO = new InfoAboutOrderDAOImpl();

    public List<InfoAboutOrder> getInfoAboutOrder() {
        return infoAboutOrderDAO.getEntities();
    }

    public void saveInfoAboutDiscount(InfoAboutOrder infoAboutOrder) {
        infoAboutOrderDAO.saveEntity(infoAboutOrder);
    }

    public InfoAboutOrder getInfoAboutOrder(int id) {
        return infoAboutOrderDAO.getEntity(id);
    }

    public void deleteInfoAboutOrder(int id) {
        infoAboutOrderDAO.deleteEntity(id);
    }

    public List<InfoAboutOrder> getOrdersForCustomer(int id) {
        return infoAboutOrderDAO.getOrdersForCustomer(id);
    }
}
