package com.interview.notes.code.year.y2024.dec24.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectDuplicateRemover {

    // Generic method to remove duplicates from any list
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        return list.stream()
                .filter(Objects::nonNull)     // Remove null values
                .distinct()                   // Remove duplicates
                .collect(Collectors.toList());
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test 1: List of Strings
        testStrings();

        // Test 2: List of Integers
        testIntegers();

        // Test 3: List of Custom Objects (Person)
        testCustomObjects();

        // Test 4: List with null values
        testWithNulls();

        // Test 5: Large list performance test
        testLargeList();
    }

    private static void testStrings() {
        System.out.println("\nTesting String List:");
        List<String> strings = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        List<String> unique = removeDuplicates(strings);
        System.out.println("Original: " + strings);
        System.out.println("Unique: " + unique);
        System.out.println("PASS: " + (unique.size() == 3));
    }

    private static void testIntegers() {
        System.out.println("\nTesting Integer List:");
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4, 1);
        List<Integer> unique = removeDuplicates(numbers);
        System.out.println("Original: " + numbers);
        System.out.println("Unique: " + unique);
        System.out.println("PASS: " + (unique.size() == 4));
    }

    private static void testCustomObjects() {
        System.out.println("\nTesting Custom Objects List:");
        List<Person> people = Arrays.asList(
                new Person("John", 25),
                new Person("Jane", 30),
                new Person("John", 25),  // Duplicate
                new Person("Bob", 35)
        );
        List<Person> unique = removeDuplicates(people);
        System.out.println("Original size: " + people.size());
        System.out.println("Unique size: " + unique.size());
        System.out.println("Unique objects: " + unique);
        System.out.println("PASS: " + (unique.size() == 3));
    }

    private static void testWithNulls() {
        System.out.println("\nTesting List with Nulls:");
        List<String> withNulls = Arrays.asList("a", null, "b", null, "a");
        List<String> unique = removeDuplicates(withNulls);
        System.out.println("Original: " + withNulls);
        System.out.println("Unique: " + unique);
        System.out.println("PASS: " + (!unique.contains(null) && unique.size() == 2));
    }

    private static void testLargeList() {
        System.out.println("\nTesting Large List Performance:");
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeList.add(i % 1000); // Will create many duplicates
        }

        long startTime = System.currentTimeMillis();
        List<Integer> unique = removeDuplicates(largeList);
        long endTime = System.currentTimeMillis();

        System.out.println("Original size: " + largeList.size());
        System.out.println("Unique size: " + unique.size());
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("PASS: " + (unique.size() == 1000));
    }

    // Sample Person class for testing
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}
