package com.interview.notes.code.year.y2025.august.amazon.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Immutable representation of an employee.
 */

public final class ImmutableEmployee {
    private final String employeeId;                 // unique ID, immutable once set
    private final String name;                       // employee name, immutable
    private final Address address;                   // immutable Address instance
    private final Date joiningDate;                  // defensive-copied Date
    private final Map<String, String> attributes;    // unmodifiable Map

    /**
     * Constructs an ImmutableEmployee with all required fields.
     *
     * @param employeeId  non-null, non-blank
     * @param name        non-null, non-blank
     * @param address     non-null Address
     * @param joiningDate non-null Date; will be defensively copied
     * @param attributes  non-null Map; will be defensively copied
     */
    public ImmutableEmployee(
            String employeeId,
            String name,
            Address address,
            Date joiningDate,
            Map<String, String> attributes
    ) {
        this.employeeId = Objects.requireNonNull(employeeId, "employeeId");       // null check
        if (employeeId.trim().isEmpty()) throw new IllegalArgumentException("employeeId blank"); // blank check

        this.name = Objects.requireNonNull(name, "name");                         // null check
        if (name.trim().isEmpty()) throw new IllegalArgumentException("name blank");           // blank check

        this.address = Objects.requireNonNull(address, "address");                // null check

        Objects.requireNonNull(joiningDate, "joiningDate");                       // null check
        this.joiningDate = new Date(joiningDate.getTime());                       // defensive copy

        Objects.requireNonNull(attributes, "attributes");                         // null check
        // defensive copy of map to prevent external mutation
        Map<String, String> tempMap = new HashMap<>(attributes);
        // ensure no null keys/values
        tempMap.forEach((k, v) -> {
            Objects.requireNonNull(k, "attribute key");
            Objects.requireNonNull(v, "attribute value");
        });
        this.attributes = Collections.unmodifiableMap(tempMap);                    // unmodifiable view
    }

    // -------- getters with defensive copying --------

    /**
     * Simple main method to test ImmutableEmployee.
     * Uses PASS/FAIL prints and handles a large-data scenario.
     */
    public static void main(String[] args) {
        // Test 1: Basic creation & getters
        try {
            Date d = new Date();                                      // original date
            Map<String, String> m = new HashMap<>();                  // original map
            m.put("role", "dev");
            Address addr = new Address("123 Main St", "Metropolis");
            ImmutableEmployee e = new ImmutableEmployee("E001", "Alice", addr, d, m);
            // mutate originals
            d.setTime(0);
            m.put("role", "ops");
            // check immutability
            boolean pass1 = e.getJoiningDate().getTime() != 0
                    && "dev".equals(e.getAttributes().get("role"));
            System.out.println("Test 1 (immutability): " + (pass1 ? "PASS" : "FAIL"));
        } catch (Exception ex) {
            System.out.println("Test 1 (immutability): FAIL with exception " + ex);
        }

        // Test 2: equals() and hashCode()
        try {
            Address a1 = new Address("1 St", "CityX");
            Address a2 = new Address("1 St", "CityX");
            Date d1 = new Date(123456);
            Date d2 = new Date(123456);
            Map<String, String> map1 = Collections.singletonMap("k", "v");
            Map<String, String> map2 = Collections.singletonMap("k", "v");
            ImmutableEmployee e1 = new ImmutableEmployee("ID", "Bob", a1, d1, map1);
            ImmutableEmployee e2 = new ImmutableEmployee("ID", "Bob", a2, d2, map2);
            boolean pass2 = e1.equals(e2) && e1.hashCode() == e2.hashCode();
            System.out.println("Test 2 (equals/hashCode): " + (pass2 ? "PASS" : "FAIL"));
        } catch (Exception ex) {
            System.out.println("Test 2 (equals/hashCode): FAIL with exception " + ex);
        }

        // Test 3: Large attributes map
        try {
            // generate 100_000 attributes quickly
            Map<String, String> largeMap = IntStream.range(0, 100_000)
                    .boxed()
                    .collect(Collectors.toMap(i -> "key" + i, i -> "val" + i));
            ImmutableEmployee eLarge = new ImmutableEmployee(
                    "LID", "Carol",
                    new Address("Addr", "BigCity"),
                    new Date(),
                    largeMap
            );
            boolean pass3 = eLarge.getAttributes().size() == 100_000;
            System.out.println("Test 3 (large data): " + (pass3 ? "PASS" : "FAIL"));
        } catch (Exception ex) {
            System.out.println("Test 3 (large data): FAIL with exception " + ex);
        }
    }

    /**
     * @return employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Address (immutable)
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return defensive copy of joiningDate
     */
    public Date getJoiningDate() {
        return new Date(joiningDate.getTime());  // copy to protect internal state
    }

    // -------- equals, hashCode, toString --------

    /**
     * @return unmodifiable Map of attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;                      // already unmodifiable
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                           // same object
        if (!(o instanceof ImmutableEmployee)) return false;   // correct type
        ImmutableEmployee that = (ImmutableEmployee) o;
        return employeeId.equals(that.employeeId)
                && name.equals(that.name)
                && address.equals(that.address)
                && joiningDate.equals(that.joiningDate)
                && attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, address, joiningDate, attributes);
    }

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

    /**
     * Immutable Address sub-class.
     */
    public static final class Address {
        private final String street;                // street part
        private final String city;                  // city part

        /**
         * @param street non-null, non-blank
         * @param city   non-null, non-blank
         */
        public Address(String street, String city) {
            this.street = Objects.requireNonNull(street, "street");
            if (street.trim().isEmpty()) throw new IllegalArgumentException("street blank");
            this.city = Objects.requireNonNull(city, "city");
            if (city.trim().isEmpty()) throw new IllegalArgumentException("city blank");
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Address)) return false;
            Address that = (Address) o;
            return street.equals(that.street) && city.equals(that.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, city);
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}