package computershop.database.service;

import computershop.database.dao.InfoAboutStationaryShopDAO;
import computershop.database.dao.impl.InfoAboutStationaryShopDAOImpl;
import computershop.database.view.InfoAboutStationaryShop;

import java.util.List;

public class InfoAboutStationaryShopService{
    private InfoAboutStationaryShopDAO infoAboutStationaryShopDAO = new InfoAboutStationaryShopDAOImpl();

    public List<InfoAboutStationaryShop> getInfoAboutStationaryShops() {
        return infoAboutStationaryShopDAO.getEntities();
    }

    public void saveInfoAboutStationaryShop(InfoAboutStationaryShop infoAboutStationaryShop) {
        infoAboutStationaryShopDAO.saveEntity(infoAboutStationaryShop);
    }

    public InfoAboutStationaryShop getInfoAboutStationaryShop(int id) {
        return infoAboutStationaryShopDAO.getEntity(id);
    }

    public void deleteInfoAboutStationaryShop(int id) {
        infoAboutStationaryShopDAO.deleteEntity(id);
    }
}
