package computershop.database.dao;

import computershop.database.basicCrud.EntityCRUD;
import computershop.database.view.InfoAboutDiscounts;
import computershop.database.view.InfoAboutStationaryShop;

import java.util.List;

public interface InfoAboutDiscountsDAO extends EntityCRUD<InfoAboutDiscounts> {
    public List<InfoAboutDiscounts> getDiscountsForAccount(int id);
}
