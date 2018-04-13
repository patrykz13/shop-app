package computershop.database.dao;

import computershop.database.basicCrud.EntityCRUD;
import computershop.database.view.InfoAboutOrder;
import computershop.database.view.InfoAboutOrderPositions;

import java.util.List;

public interface InfoAboutOrderPositionsDAO extends EntityCRUD<InfoAboutOrderPositions> {
    public List<InfoAboutOrderPositions> getOrderPositionsForOrder(int id);
}
