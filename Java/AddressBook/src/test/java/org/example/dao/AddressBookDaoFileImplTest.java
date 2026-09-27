package org.example.dao;

import org.example.dto.Address;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookDaoFileImplTest {

    AddressBookDao testDao;

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testAddresses.txt";
        new FileWriter(testFile);
        testDao = new AddressBookDaoFileImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void testAddFindAddress() throws AddressBookDaoException{
        String lastName = "Lovelace";
        Address address = new Address(lastName);
        address.setFirstName("Ada");
        address.setStreetAddress("1 Computer Road");
        address.setTown("Bristol");
        address.setCounty("Avon");
        address.setPostcode("BR10 1AA");

        testDao.addAddress(address);

        Address retrievedAddress = testDao.findAddress(lastName);

        assertEquals(address, retrievedAddress, "Checking address is correctly input to the system");

    }

    @org.junit.jupiter.api.Test
    void removeAddress() throws AddressBookDaoException {
        String lastName = "Lovelace";
        Address address = new Address(lastName);
        address.setFirstName("Ada");
        address.setStreetAddress("1 Computer Road");
        address.setTown("Bristol");
        address.setCounty("Avon");
        address.setPostcode("BR10 1AA");

        testDao.addAddress(address);
        assertTrue(testDao.addressList().contains(address), "Checking the address has been added");

        Address removedAddress = testDao.removeAddress(lastName);
        assertEquals(address, removedAddress);
        assertFalse(testDao.addressList().contains(address));
    }

    @org.junit.jupiter.api.Test
    void addressCount() throws AddressBookDaoException {
        String lastName = "Lovelace";
        Address address = new Address(lastName);
        address.setFirstName("Ada");
        address.setStreetAddress("1 Computer Road");
        address.setTown("Bristol");
        address.setCounty("Avon");
        address.setPostcode("BR10 1AA");

        testDao.addAddress(address);
        int addressCount = testDao.addressCount();
        assertEquals(1, addressCount);

        lastName = "Babbage";
        Address address2 = new Address(lastName);
        address.setFirstName("Charles");
        address.setStreetAddress("2 Computer Road");
        address.setTown("Southampton");
        address.setCounty("Hampshire");
        address.setPostcode("SO16 5TE");

        testDao.addAddress(address2);
        addressCount = testDao.addressCount();
        assertEquals(2, addressCount);

    }

}