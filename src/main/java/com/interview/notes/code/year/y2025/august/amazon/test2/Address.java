package com.interview.notes.code.year.y2025.august.amazon.test2;

import java.util.*;

// Immutable class for Address - must be final to prevent inheritance
final class Address {
    // Private final fields to ensure immutability
    private final String street;
    private final String city;

    // Constructor with validation
    public Address(String street, String city) {
        // Validate inputs to prevent null values
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
    }

    // Getters only - no setters to maintain immutability
    public String getStreet() { return street; }
    public String getCity() { return city; }

    // Override toString for better object representation
    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + "'}";
    }
}

// Main immutable employee class - marked final to prevent inheritance
final class ImmutableEmployee {
    // All fields are private and final to ensure immutability
    private final String employeeId;
    private final String name;
    private final Address address;
    private final Date joiningDate;
    private final Map<String, String> attributes;

    // Constructor with validation
    public ImmutableEmployee(String employeeId, String name, Address address, 
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

    // Getters with defensive copies where needed
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public Date getJoiningDate() { return new Date(joiningDate.getTime()); }
    public Map<String, String> getAttributes() { return attributes; }

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
                emp.getAttributes().put("new", "value");
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
