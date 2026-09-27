package dao.implementations;

import dao.interfaces.OrderDao;
import dto.Order;
import exceptions.PersistenceException;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class OrderDaoFileImpl implements OrderDao {


    private final String ORDER_FOLDER;
    private final String ORDER_FILE_IDENTIFIER = "Orders_";
    private final String ORDER_FILE_EXTENSION = ".txt";
    private final String DELIMITER = "::";
    private final String FILE_DATE_PATTERN = "yyyyMMdd";
    private final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern(FILE_DATE_PATTERN);

    private Map<LocalDate, Map<Integer, Order>> orders = new HashMap<>();
    private int largestOrderNumber = 0;

    public OrderDaoFileImpl() {
        ORDER_FOLDER = "Orders";
    }

    public OrderDaoFileImpl(String folderName) {
        ORDER_FOLDER = folderName;
    }

    /**
     * Writes the current data in memory to every appropriate file
     *
     * @throws PersistenceException
     */
    private void writeToFolder() throws PersistenceException {
        for (LocalDate date : orders.keySet()) {
            String fileName = getFileName(date);
            writeToFile(fileName, date);
        }
    }

    /**
     * Writes an order map for a specific date to a corresponding file.
     * This overwrites the file.
     *
     * @param fileName name of the file to write to
     * @param date date of the orders to write to the file
     * @throws PersistenceException
     */
    private void writeToFile(String fileName, LocalDate date) throws PersistenceException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(fileName));
        } catch (Exception e) {
            throw new PersistenceException("Unable to write to file", e);
        }

        for (Order order : orders.get(date).values()) {
            String orderString = order.getOrderNumber() + DELIMITER
                    + order.getCustomerName() + DELIMITER
                    + order.getState() + DELIMITER
                    + order.getTaxRate() + DELIMITER
                    + order.getProductType() + DELIMITER
                    + order.getCostPerSquareFoot() + DELIMITER
                    + order.getLaborCostPerSquareFoot() + DELIMITER
                    + order.getMaterialCost() + DELIMITER
                    + order.getArea() + DELIMITER
                    + order.getLaborCost() + DELIMITER
                    + order.getTax() + DELIMITER
                    + order.getTotal();
            writer.println(orderString);
        }

        writer.close();
    }

    private void loadFromFolder() throws PersistenceException {
        File folder = new File(ORDER_FOLDER);
        File[] orderFiles = folder.listFiles();

        for (File file : orderFiles) {
            String fileName = file.getPath();
            loadFromFile(fileName);
        }
    }

    private void loadFromFile(String fileName) throws PersistenceException {
        String stringDate = fileName.substring(fileName.length() - (FILE_DATE_PATTERN.length() + ORDER_FILE_EXTENSION.length()),
                fileName.length() - (ORDER_FILE_EXTENSION.length()));
        LocalDate date = LocalDate.parse(stringDate, FILE_DATE_FORMAT);

        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Order file not found", e);
        }

        // Create a hashmap for this date if the file isn't empty and there isn't one already
        if (scanner.hasNext() && !orders.containsKey(date)) {
                orders.put(date, new HashMap<>());
        }

        while (scanner.hasNext()) {
            String orderLine = scanner.nextLine();
            String[] orderArr = orderLine.split(DELIMITER);

            int orderNumber = Integer.parseInt(orderArr[0]);
            largestOrderNumber = Math.max(largestOrderNumber, orderNumber);
            Order order = new Order(orderNumber);

            order.setCustomerName(orderArr[1]);
            order.setState(orderArr[2]);
            order.setOrderDate(date);
            order.setTaxRate(new BigDecimal(orderArr[3]));
            order.setProductType(orderArr[4]);
            order.setCostPerSquareFoot(new BigDecimal(orderArr[5]));
            order.setLaborCostPerSquareFoot(new BigDecimal(orderArr[6]));
            order.setMaterialCost(new BigDecimal(orderArr[7]));
            order.setArea(new BigDecimal(orderArr[8]));
            order.setLaborCost(new BigDecimal(orderArr[9]));
            order.setTax(new BigDecimal(orderArr[10]));
            order.setTotal(new BigDecimal(orderArr[11]));

            orders.get(date).put(orderNumber, order);
        }

        scanner.close();
    }

    private String getFileName(LocalDate date) {
        String fileName = ORDER_FOLDER
                + File.separator
                + ORDER_FILE_IDENTIFIER
                + date.format(FILE_DATE_FORMAT)
                + ORDER_FILE_EXTENSION;
        return fileName;
    }

    @Override
    public int getNextOrderNumber() throws PersistenceException {
        loadFromFolder();
        return ++largestOrderNumber;
    }

    @Override
    public Order addOrder(Order order) throws PersistenceException {
        LocalDate orderDate = order.getOrderDate();
        String fileName = getFileName(orderDate);
        try {
            loadFromFile(fileName);
        } catch (PersistenceException ignored){};

        // Add a new hashmap entry for the date if there isn't one already
        if (!orders.containsKey(orderDate)) {
            orders.put(orderDate, new HashMap<>());
        }

        Order previousOrder = orders.get(orderDate).put(order.getOrderNumber(), order);

        writeToFile(fileName, orderDate);

        return previousOrder;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        try {
            loadFromFile(getFileName(date));

        } catch (PersistenceException e) {
            return null;
        }

        Map<Integer, Order> ordersForDate = orders.get(date);

        if (ordersForDate == null) return null;

        Order order = ordersForDate.get(orderNumber);
        return order;
    }

    @Override
    public Order editOrder(Order order) throws PersistenceException {
        LocalDate date = order.getOrderDate();
        try {
            loadFromFile(getFileName(date));
        } catch (PersistenceException e) {
            writeToFile(getFileName(date), date);
        }

        Order editedOrder = orders.get(date).put(order.getOrderNumber(), order);

        writeToFile(getFileName(date), date);
        return editedOrder;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        try {
            loadFromFile(getFileName(date));

        } catch (PersistenceException e) {
            return null;
        }

        Map<Integer, Order> dateOrders = orders.get(date);

        if (dateOrders == null) return null;

        return new ArrayList<>(dateOrders.values());

    }

    @Override
    public Map<LocalDate, Map<Integer, Order>> getAllOrders() throws PersistenceException {
        loadFromFolder();
        return orders;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws PersistenceException {
        loadFromFile(getFileName(date));
        Map<Integer, Order> ordersForDate = orders.get(date);

        if (ordersForDate == null) return null;

        Order removedOrder = ordersForDate.remove(orderNumber);
        writeToFile(getFileName(date), date);

        return removedOrder;
    }
}
