package service;

import dao.implementations.AuditDaoFileImpl;
import dao.implementations.OrderDaoFileImpl;
import dao.implementations.TaxDaoFileImpl;
import dto.Order;
import exceptions.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.stubImpls.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerImplTest {

    private ServiceLayer service;

    public ServiceLayerImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", ServiceLayerImpl.class);
    }


    @Test
    void testAddGetOrder() {
        Order order = new Order(2);
        order.setCustomerName("A");
        order.setState("CA");
        order.setProductType("Tile");
        order.setOrderDate(LocalDate.of(2026, 9, 21));
        order.setArea(new BigDecimal("111"));

        try {
            service.addOrder(order);
            service.getOrder(order.getOrderDate(), order.getOrderNumber());
        } catch (Exception e) {
            fail("Order was valid. No exception should have been thrown");
        }

    }

    @Test
    void testValidateValidOrder() throws PersistenceException {
        Order order = new Order(2);
        order.setCustomerName("Abby, Queen of Abbeys");
        order.setState("CA");
        order.setProductType("Tile");
        order.setOrderDate(LocalDate.of(2026, 9, 21));
        order.setArea(new BigDecimal("111"));

        Order completeOrder = service.validateOrder(order);
        assertNotNull(completeOrder, "Order is valid, should not be null.");

    }

    @Test
    void testValidateInvalidNameOrder() throws PersistenceException {
        Order order = new Order(2);
        order.setCustomerName("  ");
        order.setState("CA");
        order.setProductType("Tile");
        order.setOrderDate(LocalDate.of(2026, 9, 21));
        order.setArea(new BigDecimal("111"));

        Order completeOrder = service.validateOrder(order);
        assertNull(completeOrder, "Order is invalid, should be null.");

    }

    @Test
    void testValidateInvalidEverythingOrder() throws PersistenceException {
        Order order = new Order(2);
        order.setCustomerName("  ");
        order.setState("nowhere");
        order.setProductType("void");
        order.setOrderDate(LocalDate.of(2021, 9, 21));
        order.setArea(new BigDecimal("10"));

        Order completeOrder = service.validateOrder(order);
        assertNull(completeOrder, "Order is invalid, should be null.");

    }

    @Test
    void editOrder() {
        Order order = new Order(2);
        order.setCustomerName("A");
        order.setState("CA");
        order.setProductType("Tile");
        order.setOrderDate(LocalDate.of(2026, 9, 21));
        order.setArea(new BigDecimal("111"));
        try {
            service.editOrder(order);
        } catch (Exception e) {
            fail("Order should have passed through");
        }
    }

    @Test
    void getOrdersForDate() throws PersistenceException {
        Order orderClone = new Order(1);
        orderClone.setOrderDate(LocalDate.of(2026, 9, 21));
        orderClone.setCustomerName("Hercules");
        orderClone.setState("TX");
        orderClone.setProductType("Tile");
        orderClone.setCostPerSquareFoot(new BigDecimal("24.45"));
        orderClone.setLaborCostPerSquareFoot(new BigDecimal("2.25"));
        orderClone.setArea(new BigDecimal("250"));
        orderClone.setTaxRate(new BigDecimal("25.00"));
        orderClone.setMaterialCost(new BigDecimal("415.43"));
        orderClone.setLaborCost(new BigDecimal(500));
        orderClone.setTax(new BigDecimal("50"));
        orderClone.setTotal(new BigDecimal("5435.5"));

        List<Order> ordersForDate = service.getOrdersForDate(orderClone.getOrderDate());
        assertTrue(ordersForDate.contains(orderClone));
        assertEquals(1, ordersForDate.size());

    }

    @Test
    void removeOrder() throws PersistenceException {
        Order orderClone = new Order(1);
        orderClone.setOrderDate(LocalDate.of(2026, 9, 21));
        orderClone.setCustomerName("Hercules");
        orderClone.setState("TX");
        orderClone.setProductType("Tile");
        orderClone.setCostPerSquareFoot(new BigDecimal("24.45"));
        orderClone.setLaborCostPerSquareFoot(new BigDecimal("2.25"));
        orderClone.setArea(new BigDecimal("250"));
        orderClone.setTaxRate(new BigDecimal("25.00"));
        orderClone.setMaterialCost(new BigDecimal("415.43"));
        orderClone.setLaborCost(new BigDecimal(500));
        orderClone.setTax(new BigDecimal("50"));
        orderClone.setTotal(new BigDecimal("5435.5"));

        Order removedOrder = service.removeOrder(orderClone.getOrderDate(), orderClone.getOrderNumber());
        assertEquals(orderClone, removedOrder, "Removed order should be Hercules");

        removedOrder = service.removeOrder(orderClone.getOrderDate(), 2);
        assertNull(removedOrder, "Order 2 does not exist, should not be removed");

    }


}