package computershop.database.service;

import computershop.database.dao.OrderDAO;
import computershop.database.dao.impl.OrderDAOImpl;
import computershop.database.entity.Order;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class OrderService{
    private OrderDAO orderDAO = new OrderDAOImpl();

    public List<Order> getOrder() {
        return orderDAO.getEntities();
    }

    public void saveOrder(Order order) {
        orderDAO.saveEntity(order);
    }

    public Order getOrder(int id) {
        return orderDAO.getEntity(id);
    }

    public void deleteOrder(int id) {
        orderDAO.deleteEntity(id);
    }
}
