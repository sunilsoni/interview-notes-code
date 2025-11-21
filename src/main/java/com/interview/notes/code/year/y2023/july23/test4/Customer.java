package com.interview.notes.code.year.y2023.july23.test4;

public record Customer(int id, String name, String phoneNumber, Address address) {
    public Customer(int id, String name, String phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = new Address(address.street(), address.city(), address.state(), address.postalCode());
    }

    @Override
    public Address address() {
        return new Address(address.street(), address.city(), address.state(), address.postalCode());
    }
}
