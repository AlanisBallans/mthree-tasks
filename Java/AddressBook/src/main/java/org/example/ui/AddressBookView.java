package org.example.ui;

import org.example.dto.Address;

import java.util.List;

public class AddressBookView {

    private UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add New Address");
        io.print("2. Remove an Address");
        io.print("3. Find Address by Last Name");
        io.print("4. View number of Addresses");
        io.print("5. List Addresses");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Address getNewAddress() {
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String streetAddress = io.readString("Please enter Street Address");
        String town = io.readString("Please enter Town");
        String county = io.readString("Please enter County");
        String postcode = io.readString("Please enter Postcode");
        Address currentAddress = new Address(lastName);
        currentAddress.setFirstName(firstName);
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setTown(town);
        currentAddress.setCounty(county);
        currentAddress.setPostcode(postcode);
        return currentAddress;
    }

    public String getLastNameChoice() {
        return io.readString("Please enter the Last Name.");
    }

    public void displayAddAddressBanner() {
        io.print("=== Add Address ===");
    }

    public void displayAddSuccessBanner() {
        io.readString("Address successfully added. Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Address List ===");
    }

    public void displayDisplayAddressBanner() {
        io.print("=== Display Address ===");
    }

    public void displayRemoveAddressBanner() {
        io.print("=== Remove Address ===");
    }

    public void displayNumberOfAddressesBanner() {
        io.print("=== Number of Addresses ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayAddress(Address address) {
        if (address != null) {

            io.print(address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreetAddress());
            io.print(address.getTown());
            io.print(address.getCounty());
            io.print(address.getPostcode());
            io.print("");
        } else {
            io.print("No such address.");
        }
    }

    public void displayAddressList(List<Address> addressList) {
        for (Address currentAddress : addressList) {
            displayAddress(currentAddress);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOneAddress(Address address) {
        displayAddress(address);
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveResult(Address addressRecord) {
        if (addressRecord != null) {
            io.print("Address successfully removed");
        } else {
            io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayNumberOfAddresses(int addressBookSize) {
        io.print("This address book contains " + addressBookSize + " addresses.");
        io.readString("Please hit enter to continue.");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
