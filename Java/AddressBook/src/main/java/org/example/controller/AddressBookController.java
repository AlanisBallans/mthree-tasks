package org.example.controller;

import org.example.dao.AddressBookDao;
import org.example.dao.AddressBookDaoException;
import org.example.dto.Address;
import org.example.ui.AddressBookView;

import java.util.List;

public class AddressBookController {

    private AddressBookDao dao;
    private AddressBookView view;

    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {


                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addAddress();
                        break;
                    case 2:
                        removeAddress();
                        break;
                    case 3:
                        findAddress();
                        break;
                    case 4:
                        countAddresses();
                        break;
                    case 5:
                        listAddresses();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (AddressBookDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }

        exitMessage();
    }



    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void addAddress() throws AddressBookDaoException {
        view.displayAddAddressBanner();
        Address addressToAdd =  view.getNewAddress();
        dao.addAddress(addressToAdd);
    }

    private void removeAddress() throws AddressBookDaoException {
        view.displayRemoveAddressBanner();
        String lastName = view.getLastNameChoice();
        dao.removeAddress(lastName);
    }

    private void findAddress() throws AddressBookDaoException {
        view.displayDisplayAddressBanner();
        String lastName = view.getLastNameChoice();
        Address address = dao.findAddress(lastName);
        view.displayAddress(address);
    }

    private void countAddresses() throws AddressBookDaoException {
        view.displayNumberOfAddressesBanner();
        int addressCount = dao.addressCount();
        view.displayNumberOfAddresses(addressCount);
    }

    private void listAddresses() throws AddressBookDaoException {
        view.displayDisplayAllBanner();
        List<Address> addressList = dao.addressList();
        view.displayAddressList(addressList);
    }

}
