package service;

import dao.interfaces.*;
import dto.Order;
import dto.Product;
import dto.Tax;
import exceptions.PersistenceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class ServiceLayerImpl implements ServiceLayer {

    private AuditDao auditDao;
    private ExportDao exportDao;
    private OrderDao orderDao;
    private TaxDao taxDao;
    private ProductDao productDao;

    public ServiceLayerImpl(AuditDao auditDao, ExportDao exportDao, OrderDao orderDao, TaxDao taxDao, ProductDao productDao) {
        this.auditDao = auditDao;
        this.exportDao = exportDao;
        this.orderDao = orderDao;
        this.taxDao = taxDao;
        this.productDao = productDao;

    }

    @Override
    public int getNextOrderNumber() throws PersistenceException {
        return orderDao.getNextOrderNumber();
    }

    @Override
    public Order addOrder(Order order) throws PersistenceException {
        Order prevOrder = orderDao.addOrder(order);
        auditDao.writeAuditEntry("ORDER " + order.getOrderNumber() + " ADDED");
        return prevOrder;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        Order retrievedOrder = orderDao.getOrder(date,orderNumber);
        return retrievedOrder;
    }

    @Override
    public Order editOrder(Order order) throws PersistenceException {
        Order editedOrder = orderDao.editOrder(order);
        auditDao.writeAuditEntry("ORDER " + editedOrder.getOrderNumber() + " EDITED");
        return editedOrder;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return orderDao.getOrdersForDate(date);
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws PersistenceException {
        Order removedOrder =  orderDao.removeOrder(date, orderNumber);
        auditDao.writeAuditEntry("ORDER " + removedOrder.getOrderNumber() + " REMOVED");
        return removedOrder;
    }

    @Override
    public void exportData() throws PersistenceException {
        exportDao.exportData();
        auditDao.writeAuditEntry("DATA EXPORTED");
    }

    @Override
    public List<Tax> getTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Order validateOrder(Order order) throws PersistenceException {
        Order completeOrder = new Order(getNextOrderNumber());

        String name = order.getCustomerName();
        if (validateName(name) == null) return null;

        String state = order.getState();
        if (validateState(state, taxDao.getAllTaxes()) == null) return null;

        String productType = order.getProductType();
        if (validateProduct(productType, productDao.getAllProducts()) == null) return null;

        BigDecimal area = order.getArea();
        if (validateArea(area) == null) return null;

        completeOrder.setCustomerName(name);
        completeOrder.setState(state);
        completeOrder.setProductType(productType);
        completeOrder.setArea(area);
        completeOrder.setOrderDate(order.getOrderDate());

        completeOrder = calculate(completeOrder);
        return completeOrder;
    }

//    public Order validateOrderStub(Order order) {
//        if (validateName(order.getCustomerName()) != null
//            && validateState(order.getState(), getTaxes()) != null)
//            && validateProduct(order.getProductType(), getProducts() != null)
//    }

    @Override
    public LocalDate validateDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) return date;
        return null;
    }

    private void writeToAudit(String message) {

    }

    // ####### VALIDATION #######
    @Override
    public String validateName(String name) {
        if (name.matches("[a-zA-Z0-9.,]+[a-zA-Z0-9., ]*[a-zA-Z0-9.,]+")) {
            return name;
        }

        return null;
    }

    @Override
    public Tax validateState(String state, List<Tax> taxes) {
        Tax tax = taxes.stream()
                .filter((t) -> t.getStateAbr().equals(state))
                .findFirst()
                .orElse(null);

        return tax;
    }

    @Override
    public Product validateProduct(String productType, List<Product> products) {
        Product product = products.stream()
                .filter((p) -> p.getProductType().equals(productType))
                .findFirst()
                .orElse(null);

        return product;
    }

    @Override
    public BigDecimal validateArea(BigDecimal area) {
        if (area.compareTo(new BigDecimal(100)) < 0) return null;

        return area;
    }

    @Override
    public Order calculate(Order order) {
        Product product = validateProduct(order.getProductType(), productDao.getAllProducts());
        Tax tax = validateState(order.getState(), taxDao.getAllTaxes());
        BigDecimal area = order.getArea();

        BigDecimal taxRate = tax.getTaxRate();
        order.setTaxRate(taxRate);

        BigDecimal costPerSquareFoot = product.getCostPerSquareFoot();
        order.setCostPerSquareFoot(costPerSquareFoot);

        BigDecimal laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        BigDecimal materialCost = area.multiply(costPerSquareFoot).setScale(2, RoundingMode.FLOOR);
        order.setMaterialCost(materialCost);

        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.FLOOR);
        order.setLaborCost(laborCost);

        BigDecimal taxValue = (materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal(100))).setScale(2, RoundingMode.FLOOR);
        order.setTax(taxValue);

        BigDecimal total = materialCost.add(laborCost).add(taxValue);
        order.setTotal(total);

        return order;
    }
}
