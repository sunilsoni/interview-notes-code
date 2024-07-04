package com.interview.notes.code.months.july24.Test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class Java8FeaturesExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
            new Person("John", 28),
            new Person("Sarah", 22),
            new Person("Tom", 31),
            new Person("Anna", 24),
            new Person("Mike", 27)
        ));

        // Define a Predicate using a lambda expression
        Predicate<Person> isOlderThan25 = person -> person.getAge() > 25;

        // Process the list using Streams and lambda expressions
        List<String> names = people.stream()
            .filter(isOlderThan25) // Use the Predicate to filter the list
            .sorted(Comparator.comparing(Person::getName)) // Sort by name
            .map(Person::getName) // Map to the names
            .collect(Collectors.toList()); // Collect the result into a list

        // Print the result
        names.forEach(System.out::println);
    }
}
