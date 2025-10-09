package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param id   Unique identifier for each person
 * @param name Person's name
 * @param age  Person's age
 */ // Person class representing each person record
record Person(int id, String name, int age) {
    // Constructor to initialize person details

    // Overriding toString() for easy printing
    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}

public class PersonFilterExample {

    public static void main(String[] args) {

        // Step 1: Create and populate list of persons
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(101, "Alice", 28));
        persons.add(new Person(110, "Bob", 32));
        persons.add(new Person(103, "Charlie", 25));

        // Step 2: Filter person whose ID = 101
        List<Person> filteredList = persons.stream()              // Create stream
                .filter(p -> p.id() == 101)                    // Keep only ID = 101
                .collect(Collectors.toList());                    // Collect back to list

        // Step 3: Print filtered person info
        System.out.println("Filtered Person (ID = 101):");
        filteredList.forEach(System.out::println);

        // Step 4: Sort list by name using Stream API
        List<Person> sortedList = persons.stream()
                .sorted(Comparator.comparing(Person::name))    // Sort by name alphabetically
                .collect(Collectors.toList());

        // Step 5: Print sorted list
        System.out.println("\nSorted List by Name:");
        sortedList.forEach(System.out::println);

        // Step 6: Test verification output
        System.out.println("\nTEST RESULTS:");
        if (!filteredList.isEmpty() && filteredList.get(0).name().equals("Alice")) {
            System.out.println("PASS ✅ Filter Test");
        } else {
            System.out.println("FAIL ❌ Filter Test");
        }

        if (sortedList.get(0).name().equals("Alice")) {
            System.out.println("PASS ✅ Sort Test");
        } else {
            System.out.println("FAIL ❌ Sort Test");
        }

        // Step 7: Large data performance check (optional)
        List<Person> largeList = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeList.add(new Person(i, "Person" + i, 20 + (i % 50)));
        }
        long start = System.currentTimeMillis();
        long count = largeList.stream().filter(p -> p.id() == 99999).count();
        long end = System.currentTimeMillis();
        System.out.println("\nLarge Data Test: Filtered Count = " + count + ", Time Taken = " + (end - start) + "ms");
    }
}