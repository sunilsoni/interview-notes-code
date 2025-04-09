package com.interview.notes.code.year.y2025.april.common.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// First, assuming you have this Person class
class Person {
    final String name;
    final double salary;

    Person(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

// Now, here's how to find the person with third-highest salary using Stream API
public class Main {
    public static void main(String[] args) {
        // Sample list of persons
        List<Person> persons = Arrays.asList(
            new Person("A", 1000),
            new Person("B", 2000),
            new Person("C", 3000),
            new Person("D", 4000),
            new Person("E", 5000)
        );

        // Method 1: Using skip and findFirst
        Person thirdHighestSalary = persons.stream()
            .sorted((p1, p2) -> Double.compare(p2.salary, p1.salary)) // Sort in descending order
            .skip(2) // Skip first two highest salaries
            .findFirst() // Get the third one
            .orElse(null); // Handle case if list has less than 3 elements

        // Method 2: Using limit and skip
        Person thirdHighestSalary2 = persons.stream()
            .sorted(Comparator.comparingDouble(p -> -p.salary)) // Sort in descending order
            .limit(3) // Take first 3
            .skip(2) // Skip 2 to get the third one
            .findFirst()
            .orElse(null);
    }
}
