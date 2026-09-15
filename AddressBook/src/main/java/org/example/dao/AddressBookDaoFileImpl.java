package org.example.dao;

import org.example.dto.Address;

import java.io.*;
import java.util.*;

public class AddressBookDaoFileImpl implements AddressBookDao {

    private static final String ADDRESS_FILE = "addresses.txt";
    private static final String DELIMITER = "::";

    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(Address address) throws AddressBookDaoException {
        loadAddresses();
        Address addedAddress = addresses.put(address.getLastName(), address);
        writeAddresses();
        return addedAddress;
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookDaoException {
        loadAddresses();
        Address removedAddress = addresses.remove(lastName);
        writeAddresses();
        return removedAddress;
    }

    @Override
    public Address findAddress(String lastName) throws AddressBookDaoException {
        loadAddresses();
        Address address = addresses.get(lastName);
        writeAddresses();
        return address;

    }

    @Override
    public int addressCount() throws AddressBookDaoException {
        loadAddresses();
        int count = addresses.size();
        writeAddresses();
        return count;
    }

    @Override
    public List<Address> addressList() throws AddressBookDaoException {
        loadAddresses();
        List<Address> addressList = new ArrayList<>(addresses.values());
        writeAddresses();
        return addressList;
    }


    private Address unmarshallAddress(String addressAsText) {
        String[] addressArr = addressAsText.split(DELIMITER);

        Address address = new Address(addressArr[1]);
        address.setFirstName(addressArr[0]);
        address.setStreetAddress(addressArr[2]);
        address.setTown(addressArr[3]);
        address.setCounty(addressArr[4]);
        address.setPostcode(addressArr[5]);

        return address;
    }

    private String marshallAddress(Address address) {
        String addressAsText = address.getFirstName() + DELIMITER;
        addressAsText += address.getLastName() + DELIMITER;
        addressAsText += address.getStreetAddress() + DELIMITER;
        addressAsText += address.getTown() + DELIMITER;
        addressAsText += address.getCounty() + DELIMITER;
        addressAsText += address.getPostcode();

        return addressAsText;
    }

    private void loadAddresses() throws AddressBookDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ADDRESS_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException("Could not load addresses into memory.", e);
        }
        String currentLine;
        Address currentAddress;

        while (scanner.hasNext()) {
            currentLine = scanner.nextLine();
            currentAddress = unmarshallAddress(currentLine);
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
        scanner.close();
    }

    private void writeAddresses() throws AddressBookDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ADDRESS_FILE));
        } catch (IOException e) {
            throw new AddressBookDaoException("Could not save address data.", e);
        }

        for (Address address : addresses.values()) {
            String addressAsText = marshallAddress(address);
            out.println(addressAsText);
            out.flush();
        }

        out.close();
    }
}
