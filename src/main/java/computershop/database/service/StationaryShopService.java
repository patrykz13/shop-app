package computershop.database.service;

import computershop.database.dao.StationaryShopDAO;
import computershop.database.dao.impl.StationaryShopDAOImpl;
import computershop.database.entity.StationaryShop;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class StationaryShopService {
    private StationaryShopDAO stationaryShopDAO = new StationaryShopDAOImpl();

    public List<StationaryShop> getStationaryShops() {
        return stationaryShopDAO.getEntities();
    }

    public void saveStationaryShop(StationaryShop stationaryShop) {
        stationaryShopDAO.saveEntity(stationaryShop);
    }

    public StationaryShop getStationaryShop(int id) {
        return stationaryShopDAO.getEntity(id);
    }

    public void deleteStationaryShop(int id) {
        stationaryShopDAO.deleteEntity(id);
    }
}
