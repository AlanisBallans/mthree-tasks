package service.stubImpls;

import dao.interfaces.OrderDao;
import dto.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoStubImpl implements OrderDao {
    private Order onlyOrder;


    public OrderDaoStubImpl(Order onlyOrder) {

        this.onlyOrder = onlyOrder;
    }

    public OrderDaoStubImpl() {
        Order onlyOrder = new Order(1);
        onlyOrder.setOrderDate(LocalDate.of(2026, 9, 21));
        onlyOrder.setCustomerName("Hercules");
        onlyOrder.setState("TX");
        onlyOrder.setProductType("Tile");
        onlyOrder.setCostPerSquareFoot(new BigDecimal("24.45"));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("2.25"));
        onlyOrder.setArea(new BigDecimal("250"));
        onlyOrder.setTaxRate(new BigDecimal("25.00"));
        onlyOrder.setMaterialCost(new BigDecimal("415.43"));
        onlyOrder.setLaborCost(new BigDecimal(500));
        onlyOrder.setTax(new BigDecimal("50"));
        onlyOrder.setTotal(new BigDecimal("5435.5"));

        this.onlyOrder = onlyOrder;

    }


    public int getNextOrderNumber() {
        return 1;

    }

    public Order addOrder(Order order) {
        if (order.getOrderNumber() == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        return null;
    }

    public Order getOrder(LocalDate date, int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        return null;
    }

    public Order editOrder(Order order) {
        if (order.getOrderNumber() == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        return null;
    }

    public List<Order> getOrdersForDate(LocalDate date) {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public Map<LocalDate, Map<Integer, Order>> getAllOrders() {
        Map<LocalDate, Map<Integer, Order>> map = new HashMap<>();
        Map<Integer, Order> orderMap = new HashMap<>();
        orderMap.put(onlyOrder.getOrderNumber(), onlyOrder);
        map.put(LocalDate.of(2026, 9, 21), orderMap);
        return map;
    }

    public Order removeOrder(LocalDate date, int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        return null;
    }


}
