package com.interview.notes.code.year.y2025.april.common.test2;

import java.util.*;
import java.util.stream.*;

public class AverageAge {
    public static void main(String[] args) {
        // Sample list of people with name and age
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 35),
            new Person("Charlie", 40),
            new Person("David", 28)
        );

        // Calculate the average age of people over 30
        double averageAge = people.stream()
                                  .filter(person -> person.getAge() > 30) // Filter people above 30
                                  .mapToInt(Person::getAge) // Map to age
                                  .average() // Calculate average
                                  .orElse(0.0); // Default if no one is above 30

        System.out.println("Average age of people above 30: " + averageAge);
    }
}

// Person class
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
