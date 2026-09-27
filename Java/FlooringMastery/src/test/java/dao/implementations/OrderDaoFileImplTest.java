package dao.implementations;

import dao.interfaces.OrderDao;
import dto.Order;
import exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoFileImplTest {

    private OrderDao testDao;

    @BeforeEach
    void setUp() throws Exception {
        String folder = "testOrders";

        // Clean out the test folder
        File folderFile = new File(folder);
        File[] prevFiles = folderFile.listFiles();
        for (File file : prevFiles) file.delete();

        // Add basic test files
        String file1Name = "Orders_20260918.txt";
        String file2Name = "Orders_20220611.txt";

        PrintWriter writer = new PrintWriter(new FileWriter(folder + File.separator + file1Name));
        writer.println("1::Ada Lovelace::CA::25.00::Tile::249.00::3.50::4.15::871.50::1033.35::476.21::2381.06");
        writer.println("2::Doctor Who::WA::9.25::Wood::243.00::5.15::4.75::1251.45::1154.25::216.51::2622.21");
        writer.close();

        writer = new PrintWriter(new FileWriter(folder + File.separator + file2Name));
        writer.println("3::Albert Einstein::CA::6.00::Carpet::217.00::2.25::2.10::488.25::455.70::56.64::1000.59");
        writer.close();

        testDao = new OrderDaoFileImpl("testOrders");
    }

    @Test
    void testGetNextOrderNumber() throws PersistenceException {
        int nextOrderNumber = testDao.getNextOrderNumber();
        assertEquals(4, nextOrderNumber, "Test orders have a max order number of 3," +
                " so the next one should be 4");
    }

    @Test
    void testAddGetOrder() throws PersistenceException {
        int nextOrderNumber = testDao.getNextOrderNumber();
        String customerName = "Charles Babbage";
        String state = "TX";
        LocalDate orderDate = LocalDate.of(2024, 7, 19);
        BigDecimal taxRate = new BigDecimal("2.25");
        String productType = "Tile";
        BigDecimal costPerSquareFoot = new BigDecimal(1.00);
        BigDecimal laborCostPerSquareFoot = new BigDecimal("2.20");
        BigDecimal area = new BigDecimal("100");
        BigDecimal materialCost = area.multiply(costPerSquareFoot);
        BigDecimal laborCost = laborCostPerSquareFoot.multiply(area);
        BigDecimal tax = (materialCost.add(laborCost)).multiply(taxRate);
        BigDecimal total = materialCost.add(laborCost).add(tax);

        Order order = new Order(nextOrderNumber);
        order.setCustomerName(customerName);
        order.setState(state);
        order.setOrderDate(orderDate);
        order.setTaxRate(taxRate);
        order.setProductType(productType);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setArea(area);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);

        testDao.addOrder(order);

        Order retrievedOrder = testDao.getOrder(orderDate, nextOrderNumber);
        assertEquals(order, retrievedOrder, "Checking the order that was retrieved is " +
                "the same as the order input");
    }

    @Test
    void testEditOrder() {

    }

    @Test
    void testGetOrdersForDate() {
        LocalDate date = LocalDate.of(2026, 9, 18);

        // Setting up expected data
        Order order1Clone = new Order(1);
        order1Clone.setCustomerName("Ada Lovelace");
        order1Clone.setState("CA");
        order1Clone.setOrderDate(date);
        order1Clone.setTaxRate(new BigDecimal("25.00"));
        order1Clone.setProductType("Tile");
        order1Clone.setCostPerSquareFoot(new BigDecimal("249.00"));
        order1Clone.setLaborCostPerSquareFoot(new BigDecimal("3.50"));
        order1Clone.setMaterialCost(new BigDecimal("4.15"));
        order1Clone.setArea(new BigDecimal("871.50"));
        order1Clone.setLaborCost(new BigDecimal("1033.35"));
        order1Clone.setTax(new BigDecimal("476.21"));
        order1Clone.setTotal(new BigDecimal("2381.06"));

        Order order2Clone = new Order(2);
        order2Clone.setCustomerName("Doctor Who");
        order2Clone.setState("WA");
        order2Clone.setOrderDate(date);
        order2Clone.setTaxRate(new BigDecimal("9.25"));
        order2Clone.setProductType("Wood");
        order2Clone.setCostPerSquareFoot(new BigDecimal("243.00"));
        order2Clone.setLaborCostPerSquareFoot(new BigDecimal("5.15"));
        order2Clone.setMaterialCost(new BigDecimal("4.75"));
        order2Clone.setArea(new BigDecimal("1251.45"));
        order2Clone.setLaborCost(new BigDecimal("1154.25"));
        order2Clone.setTax(new BigDecimal("216.51"));
        order2Clone.setTotal(new BigDecimal("2622.21"));

        List<Order> retrievedOrders = testDao.getOrdersForDate(date);
        assertTrue(retrievedOrders.contains(order1Clone));
        assertTrue(retrievedOrders.contains(order2Clone));
    }

    @Test
    void testGetAllOrders() {

    }

    @Test
    void testRemoveOrder() throws PersistenceException {
        LocalDate date = LocalDate.of(2026, 9, 18);
        Order order2Clone = new Order(2);
        order2Clone.setCustomerName("Doctor Who");
        order2Clone.setState("WA");
        order2Clone.setOrderDate(date);
        order2Clone.setTaxRate(new BigDecimal("9.25"));
        order2Clone.setProductType("Wood");
        order2Clone.setCostPerSquareFoot(new BigDecimal("243.00"));
        order2Clone.setLaborCostPerSquareFoot(new BigDecimal("5.15"));
        order2Clone.setMaterialCost(new BigDecimal("4.75"));
        order2Clone.setArea(new BigDecimal("1251.45"));
        order2Clone.setLaborCost(new BigDecimal("1154.25"));
        order2Clone.setTax(new BigDecimal("216.51"));
        order2Clone.setTotal(new BigDecimal("2622.21"));

        Order removedOrder = testDao.removeOrder(date, 2);

        assertEquals(order2Clone, removedOrder);
        assertFalse(testDao.getAllOrders().get(date).containsKey(2));
    }
}