package service;

import dto.Order;
import dto.Product;
import dto.Tax;
import exceptions.PersistenceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ServiceLayer {

    int getNextOrderNumber() throws PersistenceException;

    Order addOrder(Order order) throws PersistenceException;

    Order getOrder(LocalDate date, int orderNumber);

    Order editOrder(Order order) throws PersistenceException;

    List<Order> getOrdersForDate(LocalDate date);

    Order removeOrder(LocalDate date, int orderNumber) throws PersistenceException;

    void exportData() throws PersistenceException;

    List<Tax> getTaxes();

    List<Product> getProducts();

    Order validateOrder(Order editedOrder) throws PersistenceException;

    LocalDate validateDate(LocalDate date);

    // ####### VALIDATION #######
    String validateName(String name);

    Tax validateState(String state, List<Tax> taxes);

    Product validateProduct(String productType, List<Product> products);

    BigDecimal validateArea(BigDecimal area);

    Order calculate(Order replacementOrder);
}
