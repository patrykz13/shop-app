package computershop.database.dao;

import computershop.database.basicCrud.EntityCRUD;
import computershop.database.view.InfoAboutOrder;

import java.util.List;

public interface InfoAboutOrderDAO extends EntityCRUD<InfoAboutOrder> {
    public List<InfoAboutOrder> getOrdersForCustomer(int id);
}
