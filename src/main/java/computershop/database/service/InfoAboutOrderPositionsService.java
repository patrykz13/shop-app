package computershop.database.service;

import computershop.database.dao.InfoAboutOrderPositionsDAO;
import computershop.database.dao.impl.InfoAboutOrderPositionsDAOImpl;
import computershop.database.view.InfoAboutOrderPositions;

import java.util.List;

public class InfoAboutOrderPositionsService {
    private InfoAboutOrderPositionsDAO infoAboutOrderPositionsDAO = new InfoAboutOrderPositionsDAOImpl();

    public List<InfoAboutOrderPositions> getInfoAboutOrderPositions() {
        return infoAboutOrderPositionsDAO.getEntities();
    }

    public void saveInfoAboutOrderPositions(InfoAboutOrderPositions infoAboutOrderPositions) {
        infoAboutOrderPositionsDAO.saveEntity(infoAboutOrderPositions);
    }

    public InfoAboutOrderPositions getInfoAboutOrderPositions(int id) {
        return infoAboutOrderPositionsDAO.getEntity(id);
    }

    public void deleteInfoAboutOrderPositions(int id) {
        infoAboutOrderPositionsDAO.deleteEntity(id);
    }

    public List<InfoAboutOrderPositions> getOrderPositionsForOrder(int id) {
        return infoAboutOrderPositionsDAO.getOrderPositionsForOrder(id);
    }
}
