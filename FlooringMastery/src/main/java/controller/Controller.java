package controller;

import dto.Order;
import exceptions.PersistenceException;
import service.ServiceLayer;
import ui.View;

import java.time.LocalDate;
import java.util.List;

public class Controller {

    private View view;
    private ServiceLayer service;

    public Controller(View view, ServiceLayer service) {
        this.view = view;
        this.service = service;
    }


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;

                    case 2:
                        addOrder();
                        break;

                    case 3:
                        editOrder();
                        break;

                    case 4:
                        removeOrder();
                        break;

                    case 5:
                        exportData();
                        break;

                    case 6:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }
                view.displayBuffer();
            }
        } catch (PersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }


    private int getMenuSelection() {
        return view.displayMainMenuAndGetSelection();
    }

    private void displayOrders() throws PersistenceException {
        LocalDate date = view.getDateInput();
        List<Order> orders = service.getOrdersForDate(date);
        if (orders == null) {
            view.displayNoSuchOrderMessage();
            return;
        }

        view.displayOrders(orders);
    }

    private void addOrder() throws PersistenceException {
        view.displayAddOrderBanner();
        LocalDate date;
        do {
            date = view.getDateInput();
            date = service.validateDate(date);

        } while (date == null);

        Order order;
        do {
            order = view.getAddOrderInput(service.getTaxes(), service.getProducts());
            order = service.validateOrder(order);

            view.displayBuffer();
            if (order == null) view.displayInvalidOrderMessage();
            view.displayBuffer();

        } while (order == null);

        view.displayBuffer();

        order.setOrderDate(date);

        view.displayOrderInfo(order);
        boolean confirmation = view.getConfirmation();
        if (!confirmation) return;

        view.displayBuffer();
        service.addOrder(order);
        view.displayAddOrderSuccess();
    }

    private void editOrder() throws PersistenceException {
        LocalDate date = view.getDateInput();
        int orderNumber = view.getOrderNumberInput();

        Order orderToEdit = service.getOrder(date, orderNumber);

        if (orderToEdit == null) {
            view.displayNoSuchOrderMessage();
            return;
        }

        Order replacementOrder = new Order(orderNumber);
        replacementOrder.setCustomerName(orderToEdit.getCustomerName());
        replacementOrder.setState(orderToEdit.getState());
        replacementOrder.setOrderDate(orderToEdit.getOrderDate());
        replacementOrder.setProductType(orderToEdit.getProductType());
        replacementOrder.setTaxRate(orderToEdit.getTaxRate());
        replacementOrder.setCostPerSquareFoot(orderToEdit.getCostPerSquareFoot());
        replacementOrder.setLaborCostPerSquareFoot(orderToEdit.getLaborCostPerSquareFoot());
        replacementOrder.setMaterialCost(orderToEdit.getMaterialCost());
        replacementOrder.setArea(orderToEdit.getArea());
        replacementOrder.setLaborCost(orderToEdit.getLaborCost());
        replacementOrder.setTax(orderToEdit.getTax());
        replacementOrder.setTotal(orderToEdit.getTotal());

        Order editedOrder;
        do {
            editedOrder = view.getEditOrderInput(orderToEdit, service.getTaxes(), service.getProducts());

            if (service.validateName(editedOrder.getCustomerName()) == null
                || service.validateState(editedOrder.getState(), service.getTaxes()) == null
                || service.validateProduct(editedOrder.getProductType(), service.getProducts()) == null
                || service.validateArea(editedOrder.getArea()) == null) {
                view.displayInvalidOrderMessage();
                editedOrder = null;
            }
        } while (editedOrder == null);

        if (!editedOrder.getCustomerName().equals(orderToEdit.getCustomerName())) {
            replacementOrder.setCustomerName(editedOrder.getCustomerName());
        }

        /*
            Editing values used in calculations; using calculate flag to indicate if
            this is required
         */
        boolean calculate = false;

        if (!editedOrder.getProductType().equals(orderToEdit.getProductType())) {
            replacementOrder.setProductType(editedOrder.getProductType());
            calculate = true;
        }
        if (!editedOrder.getState().equals(orderToEdit.getState())) {
            replacementOrder.setState(editedOrder.getState());
            calculate = true;
        }
        if (!editedOrder.getArea().equals(orderToEdit.getArea())) {
            replacementOrder.setArea(editedOrder.getArea());
            calculate = true;
        }

        if (calculate) {
            replacementOrder = service.calculate(replacementOrder);
        }

        view.displayOrderInfo(replacementOrder);

        Boolean confirmation = view.getConfirmation();

        if (!confirmation) return;

        service.editOrder(replacementOrder);
        view.displayEditOrderSuccess();

    }

    private void removeOrder() throws PersistenceException {
        view.displayRemoveOrderBanner();

        LocalDate date = view.getDateInput();

        int orderNumber = view.getOrderNumberInput();

        view.displayOrderInfo(service.getOrder(date, orderNumber));
        boolean confirmation = view.getConfirmation();
        if (!confirmation) return;

        Order removedOrder = service.removeOrder(date, orderNumber);

        if (removedOrder == null) {
            view.displayNoSuchOrderMessage();
            return;
        }
        view.displayRemoveOrderSuccess();

    }

    private void exportData() throws PersistenceException {
        service.exportData();
        view.displayExportDataSuccess();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void unknownCommand() {
        view.displayUnknownCommandMessage();
    }
}
