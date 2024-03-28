package com.interview.notes.code.months.march24.test18;

public class CountryArea implements GeographicalArea {
    private final String country;

    public CountryArea(String country) {
        this.country = country;
    }

    @Override
    public boolean containsAddress(Address address) {
        return this.country.equals(address.getCountry());
    }
}