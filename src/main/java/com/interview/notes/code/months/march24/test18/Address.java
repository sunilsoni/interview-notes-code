package com.interview.notes.code.months.march24.test18;

import lombok.Getter;
import lombok.Setter;

// Class to represent a shipping address.
@Getter
@Setter
public class Address {
    private final String city;
    private final String state;
    private final String country;
    private final int zipcode;

    public Address(String city, String state, String country, int zipcode) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public int getZipcode() {
        return zipcode;
    }

    // Additional getters and setters could be implemented here.
}