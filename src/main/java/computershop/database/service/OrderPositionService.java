package computershop.database.service;

import computershop.database.dao.OrderPositionDAO;
import computershop.database.dao.impl.OrderPositionDAOImpl;
import computershop.database.entity.OrderPosition;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class OrderPositionService{
    private OrderPositionDAO orderPositionDAO = new OrderPositionDAOImpl();

    public List<OrderPosition> getOrderPositions() {
        return orderPositionDAO.getEntities();
    }

    public void saveOrderPosition(OrderPosition orderPosition) {
        orderPositionDAO.saveEntity(orderPosition);
    }

    public OrderPosition getOrderPosition(int id) {
        return orderPositionDAO.getEntity(id);
    }

    public void deleteOrderPosition(int id) {
        orderPositionDAO.deleteEntity(id);
    }
}
