package computershop.database.service;

import computershop.database.dao.InfoAboutSetDAO;
import computershop.database.dao.impl.InfoAboutSetDAOImpl;
import computershop.database.view.InfoAboutSet;

import java.util.List;

public class InfoAboutSetService {
    private InfoAboutSetDAO infoAboutSetDAO = new InfoAboutSetDAOImpl();

    public List<InfoAboutSet> getInfoAboutSets() {
        return infoAboutSetDAO.getEntities();
    }

    public void saveInfoAboutSet(InfoAboutSet infoAboutSet) {
        infoAboutSetDAO.saveEntity(infoAboutSet);
    }

    public InfoAboutSet getInfoAboutSets(int id) {
        return infoAboutSetDAO.getEntity(id);
    }

    public void deleteInfoAboutSet(int id) {
        infoAboutSetDAO.deleteEntity(id);
    }
}
