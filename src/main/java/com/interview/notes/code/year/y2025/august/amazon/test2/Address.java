package com.interview.notes.code.year.y2025.august.amazon.test2;

import java.util.*;

/**
 * @param street Private final fields to ensure immutability
 */ // Immutable class for Address - must be final to prevent inheritance
record Address(String street, String city) {
    // Constructor with validation
    Address(String street, String city) {
        // Validate inputs to prevent null values
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
    }

    // Override toString for better object representation
    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + "'}";
    }
}

/**
 * @param employeeId All fields are private and final to ensure immutability
 */ // Main immutable employee class - marked final to prevent inheritance
record ImmutableEmployee(String employeeId, String name, Address address, Date joiningDate,
                         Map<String, String> attributes) {
    // Constructor with validation
    ImmutableEmployee(String employeeId, String name, Address address,
                      Date joiningDate, Map<String, String> attributes) {
        // Validate all inputs
        this.employeeId = Objects.requireNonNull(employeeId, "Employee ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");

        // Create defensive copy of mutable Date object
        this.joiningDate = new Date(Objects.requireNonNull(joiningDate).getTime());

        // Create defensive copy of mutable Map
        this.attributes = Collections.unmodifiableMap(
                new HashMap<>(Objects.requireNonNull(attributes, "Attributes cannot be null")));
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 1: Basic object creation
        try {
            Map<String, String> attrs = new HashMap<>();
            attrs.put("department", "IT");
            attrs.put("position", "Developer");

            ImmutableEmployee emp = new ImmutableEmployee(
                    "E001",
                    "John Doe",
                    new Address("123 Main St", "New York"),
                    new Date(),
                    attrs
            );

            System.out.println("Test 1 - Basic Creation: PASS");
            System.out.println(emp);

            // Try to modify the returned map (should throw exception)
            try {
                emp.attributes().put("new", "value");
                System.out.println("Test 2 - Immutability: FAIL");
            } catch (UnsupportedOperationException e) {
                System.out.println("Test 2 - Immutability: PASS");
            }

            // Test null values
            try {
                new ImmutableEmployee(null, "Name",
                        new Address("Street", "City"), new Date(), new HashMap<>());
                System.out.println("Test 3 - Null Validation: FAIL");
            } catch (NullPointerException e) {
                System.out.println("Test 3 - Null Validation: PASS");
            }

            // Test large data input
            Map<String, String> largeMap = new HashMap<>();
            for (int i = 0; i < 10000; i++) {
                largeMap.put("key" + i, "value" + i);
            }
            ImmutableEmployee largeEmp = new ImmutableEmployee(
                    "E002", "Jane Doe",
                    new Address("456 Oak St", "Boston"),
                    new Date(),
                    largeMap
            );
            System.out.println("Test 4 - Large Data: PASS");

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public Date joiningDate() {
        return new Date(joiningDate.getTime());
    }

    // Override toString for better object representation
    @Override
    public String toString() {
        return "ImmutableEmployee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", joiningDate=" + joiningDate +
                ", attributes=" + attributes +
                '}';
    }
}
