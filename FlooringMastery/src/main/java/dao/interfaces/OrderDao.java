package dao.interfaces;

import dto.Order;
import exceptions.PersistenceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    int getNextOrderNumber() throws PersistenceException;

    Order addOrder(Order order) throws PersistenceException;

    Order getOrder(LocalDate date, int orderNumber);

    Order editOrder(Order order) throws PersistenceException;

    List<Order> getOrdersForDate(LocalDate date);

    Map<LocalDate, Map<Integer, Order>> getAllOrders() throws PersistenceException;

    Order removeOrder(LocalDate date, int orderNumber) throws PersistenceException;

}
