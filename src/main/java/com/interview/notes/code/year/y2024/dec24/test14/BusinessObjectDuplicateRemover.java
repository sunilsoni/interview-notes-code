package com.interview.notes.code.year.y2024.dec24.test14;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessObjectDuplicateRemover {

    // Modified generic method without using distinct()
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                item -> item,    // key mapper
                                item -> item,    // value mapper
                                (existing, replacement) -> existing,  // merge function (keep first occurrence)
                                LinkedHashMap::new  // maintains insertion order
                        ),
                        map -> new ArrayList<>(map.values())
                ));
    }

    // Alternative implementation using traditional approach
    public static <T> List<T> removeDuplicatesTraditional(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        LinkedHashMap<T, T> map = new LinkedHashMap<>();
        for (T item : list) {
            if (item != null) {
                map.putIfAbsent(item, item);
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        // Test both implementations
        testPersons();
        testProducts();
    }

    private static void testPersons() {
        System.out.println("\n=== Testing Person List ===");
        List<Person> persons = Arrays.asList(
                new Person("John", "Doe", 25, "john@email.com"),
                new Person("Jane", "Smith", 30, "jane@email.com"),
                new Person("John", "Doe", 35, "john.different@email.com"), // Duplicate
                new Person("Bob", "Smith", 40, "bob@email.com")
        );

        System.out.println("Original Persons:");
        persons.forEach(System.out::println);

        System.out.println("\nUnique Persons (Stream approach):");
        List<Person> uniquePersons1 = removeDuplicates(persons);
        uniquePersons1.forEach(System.out::println);

        System.out.println("\nUnique Persons (Traditional approach):");
        List<Person> uniquePersons2 = removeDuplicatesTraditional(persons);
        uniquePersons2.forEach(System.out::println);
    }

    private static void testProducts() {
        System.out.println("\n=== Testing Product List ===");
        List<Product> products = Arrays.asList(
                new Product("iPhone", "14", 999.99, "Base model"),
                new Product("iPhone", "14", 1099.99, "Different price"), // Duplicate
                new Product("iPhone", "14 Pro", 1299.99, "Pro model"),
                new Product("Samsung", "S23", 899.99, "Android flagship")
        );

        System.out.println("Original Products:");
        products.forEach(System.out::println);

        System.out.println("\nUnique Products (Stream approach):");
        List<Product> uniqueProducts1 = removeDuplicates(products);
        uniqueProducts1.forEach(System.out::println);

        System.out.println("\nUnique Products (Traditional approach):");
        List<Product> uniqueProducts2 = removeDuplicatesTraditional(products);
        uniqueProducts2.forEach(System.out::println);
    }

    static class Person {
        private String firstName;
        private String lastName;
        private int age;
        private String email;

        public Person(String firstName, String lastName, int age, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(firstName, person.firstName) &&
                    Objects.equals(lastName, person.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }

        @Override
        public String toString() {
            return String.format("Person{firstName='%s', lastName='%s', age=%d, email='%s'}",
                    firstName, lastName, age, email);
        }
    }

    static class Product {
        private String model;
        private String version;
        private double price;
        private String description;

        public Product(String model, String version, double price, String description) {
            this.model = model;
            this.version = version;
            this.price = price;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(model, product.model) &&
                    Objects.equals(version, product.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(model, version);
        }

        @Override
        public String toString() {
            return String.format("Product{model='%s', version='%s', price=%.2f, description='%s'}",
                    model, version, price, description);
        }
    }
}
