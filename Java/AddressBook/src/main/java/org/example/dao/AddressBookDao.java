package org.example.dao;

import org.example.dto.Address;

import java.util.List;

public interface AddressBookDao {

    Address addAddress(Address address) throws AddressBookDaoException;

    Address removeAddress(String lastName) throws AddressBookDaoException;

    Address findAddress(String lastName) throws AddressBookDaoException;

    int addressCount() throws AddressBookDaoException;

    List<Address> addressList() throws AddressBookDaoException;
}
