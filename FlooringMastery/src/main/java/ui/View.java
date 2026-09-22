package ui;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class View {

    private UserIO io;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public View(UserIO io) {
        this.io = io;
    }

    public int displayMainMenuAndGetSelection() {
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        int choice = io.readInt("Please choose from the above options", 1, 6);
        return choice;
    }



    public void displayOrders(List<Order> orderList) {
        for (Order order : orderList) {
            displayOrderInfo(order);
            io.print("");
        }
    }

    public void displayOrderInfo(Order order) {
        io.print("Order #" + order.getOrderNumber());
        io.print("Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getState());
        io.print("Order date: " + order.getOrderDate().format(formatter));
        io.print("Tax rate: " + order.getTaxRate() + "%");
        io.print("Product type: " + order.getProductType());
        io.print("Cost Per Square Foot: $" + order.getCostPerSquareFoot());
        io.print("Labor Cost Per Square Foot: $" + order.getLaborCostPerSquareFoot());
        io.print("Material Cost: $" + order.getMaterialCost());
        io.print("Area: " + order.getArea() + " square feet");
        io.print("Labor Cost: $" + order.getLaborCost());
        io.print("Tax: $" + order.getTax());
        io.print("Total: $" + order.getTotal());

    }

    // ####### ADDING ORDER #######
    public void displayAddOrderBanner() {
        io.print("=== ADD AN ORDER ===");
    }

    public Order getAddOrderInput(List<Tax> taxes, List<Product> products) {
        String name = getNameInput("Enter customer name", false);
        String state = getStateInput("Enter state", taxes, false);
        String productType = getProductTypeInput("Enter product type", products, false);
        BigDecimal area = getAreaInput("Enter area", false);

        Order incompleteOrder = new Order(-1);
        incompleteOrder.setCustomerName(name);
        incompleteOrder.setState(state);
        incompleteOrder.setProductType(productType);
        incompleteOrder.setArea(area);

        return incompleteOrder;
    }

    public void displayAddOrderSuccess() {
        io.print("Order added successfully!");
    }



    // ####### EDITING ORDER #######
    public Order getEditOrderInput(Order order, List<Tax> taxes, List<Product> products) {

        Order editedOrder = new Order(order.getOrderNumber());

        String prompt = "Enter customer name (" + order.getCustomerName() + ")";
        String name = getNameInput(prompt, true);

        if (name.isEmpty()) {
            editedOrder.setCustomerName(order.getCustomerName());
        } else editedOrder.setCustomerName(name);


        prompt = "Enter state (" + order.getState() + ")";
        String state = getStateInput(prompt, taxes, true);

        if (state.isEmpty()) {
            editedOrder.setState(order.getState());

        } else editedOrder.setState(state);


        prompt = "Enter product type (" + order.getProductType() + ")";
        String productType = getProductTypeInput(prompt, products, true);

        if (productType.isEmpty()) {
            editedOrder.setProductType(order.getProductType());

        } else editedOrder.setState(productType);


        prompt = "Enter area (" + order.getArea() + ")";
        String stringArea = io.readString(prompt);

        if (stringArea.isEmpty()) {
            editedOrder.setArea(order.getArea());

        } else {
            BigDecimal area;

            do {
                area = validateAreaInput(stringArea);
            } while (area == null);

            editedOrder.setArea(area);
        }

        return editedOrder;
    }

    public void displayEditOrderSuccess() {
        io.print("Order edited successfully!");
    }

    // ####### REMOVING ORDER #######
    public void displayRemoveOrderBanner() {
        io.print("=== REMOVE AN ORDER ===");
    }

    public Boolean getConfirmation() {
        String confirmation;
        do{
            confirmation = io.readString("Complete this operation? (Y/N)");
        } while (!confirmation.equals("Y") && !confirmation.equals("N"));

        return confirmation.equals("Y");
    }

    public void displayRemoveOrderSuccess() {
        io.print("Order successfully removed!");
    }


    // ####### OTHER MESSAGES #######
    public void displayExportDataSuccess() {
        io.print("Data successfully exported!");
    }

    public void displayExitMessage() {
        io.print("Goodbye!");
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public void displayUnknownCommandMessage() {
        io.print("Unknown command!");
    }

    public void displayNoSuchOrderMessage() {
        io.print("No such order.");
    }

    public void displayInvalidOrderMessage() {
        io.print("Invalid order.");
    }

    public void displayBuffer() {
        io.print("");
    }


    // ####### INDIVIDUAL INPUT AND VALIDATION #######

    public LocalDate getDateInput() {
        LocalDate date;
        do {
            try {
                String stringDate = io.readString("Enter date (dd-MM-yyyy)");
                date = LocalDate.parse(stringDate, formatter);
            } catch (Exception e) {
                date = null;
            }
        } while (date == null);
        return date;
    }

    public int getOrderNumberInput() {
        int orderNumber;
        do{
            try{
                orderNumber = io.readInt("Enter order number");
            } catch (Exception e) {
                orderNumber = -1;
            }
        } while (orderNumber == -1);
        return orderNumber;
    }

    private String getNameInput(String prompt, boolean editing) {
        String name;
        do {
            name = io.readString(prompt);
            if (name.isEmpty() && editing) return "";
            name = validateNameInput(name);
        } while (name == null);

        return name;
    }

    private String validateNameInput(String name) {

        if (name.matches("[a-zA-Z., ]*")) {
            return name;
        }

        return null;
    }

    // ## State ##
    private String getStateInput(String prompt, List<Tax> taxes, boolean editing) {
        String state;
        Tax tax;
        do {
            state = io.readString(prompt);
            if (state.isEmpty() && editing) return "";
            tax = validateStateInput(state, taxes);

        } while (tax == null);

        return tax.getStateAbr();
    }

    private Tax validateStateInput(String state, List<Tax> taxes) {
        Tax tax = taxes.stream()
                .filter((t) -> t.getStateAbr().equals(state))
                .findFirst()
                .orElse(null);

        return tax;
    }

    // ## Product ##
    private String getProductTypeInput(String prompt, List<Product> products, boolean editing) {
        String productType;
        Product product;
        do {
            productType = io.readString(prompt);
            if (productType.isEmpty() && editing) return "";
            product = validateProductInput(productType, products);
        } while (product == null);

        return product.getProductType();
    }

    private Product validateProductInput(String productType, List<Product> products) {
        Product product = products.stream()
                .filter((p) -> p.getProductType().equals(productType))
                .findFirst()
                .orElse(null);

        return product;
    }

    // ## Area ##
    private BigDecimal getAreaInput(String prompt, boolean editing) {
        String areaInput;
        BigDecimal area;
        do {
            areaInput = io.readString(prompt);
            if (areaInput.isEmpty() && editing) return new BigDecimal("-1");
            area = validateAreaInput(areaInput);
        } while (area == null);

        return area;
    }

    private BigDecimal validateAreaInput(String areaInput) {
        BigDecimal area;
        try {
            area = new BigDecimal(areaInput).setScale(2, RoundingMode.FLOOR);

        } catch (NumberFormatException e) {
            return null;
        }

        return area;
    }



}
