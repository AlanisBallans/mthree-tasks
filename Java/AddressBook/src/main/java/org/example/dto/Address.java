package org.example.dto;

import java.util.Objects;

public class Address {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String town;
    private String county;
    private String postcode;

    public Address(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(firstName, address.firstName) && Objects.equals(lastName, address.lastName)
                && Objects.equals(streetAddress, address.streetAddress) && Objects.equals(town, address.town)
                && Objects.equals(county, address.county) && Objects.equals(postcode, address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, streetAddress, town, county, postcode);
    }

    @Override
    public String toString() {
        return "Address{" + "firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", town=" + town + ", county=" + county + ", postcode=" + postcode + "}";
    }
}
