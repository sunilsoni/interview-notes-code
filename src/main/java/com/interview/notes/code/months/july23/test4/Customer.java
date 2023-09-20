package com.interview.notes.code.months.july23.test4;

public final class Customer {
    private final int id;
    private final String name;
    private final String phoneNumber;
    private final Address address;

    public Customer(int id, String name, String phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = new Address(address.getStreet(), address.getCity(), address.getState(), address.getPostalCode());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return new Address(address.getStreet(), address.getCity(), address.getState(), address.getPostalCode());
    }
}
