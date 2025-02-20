package com.interview.notes.code.year.y2025.feb25.codility.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Sample Person class with multiple attributes
class Person {
    private String name;
    private int age;
    private double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", salary=" + salary + "}";
    }
}

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 30, 50000));
        persons.add(new Person("Alice", 25, 60000));
        persons.add(new Person("Bob", 35, 45000));
        persons.add(new Person("Alice", 28, 55000));

        // 1. Basic Comparator using lambda
        Comparator<Person> byName = (p1, p2) -> p1.getName().compareTo(p2.getName());

        // 2. Comparing by multiple attributes using thenComparing
        Comparator<Person> byNameThenAge = Comparator
                .comparing(Person::getName)
                .thenComparing(Person::getAge);

        // 3. Complex comparison with multiple attributes
        Comparator<Person> byNameThenAgeReversedThenSalary = Comparator
                .comparing(Person::getName)
                .thenComparing(Person::getAge, Comparator.reverseOrder())
                .thenComparing(Person::getSalary);

        // 4. Reverse order example
        Comparator<Person> bySalaryReversed = Comparator
                .comparing(Person::getSalary)
                .reversed();

        // Example usage of different comparators
        System.out.println("Original list:");
        persons.forEach(System.out::println);

        // Sort by name
        System.out.println("\nSorted by name:");
        persons.sort(byName);
        persons.forEach(System.out::println);

        // Sort by name and then age
        System.out.println("\nSorted by name and then age:");
        persons.sort(byNameThenAge);
        persons.forEach(System.out::println);

        // Sort by name, then age (reversed), then salary
        System.out.println("\nSorted by name, then age (reversed), then salary:");
        persons.sort(byNameThenAgeReversedThenSalary);
        persons.forEach(System.out::println);

        // Sort by salary in descending order
        System.out.println("\nSorted by salary (descending):");
        persons.sort(bySalaryReversed);
        persons.forEach(System.out::println);

        // 5. Null-safe comparison
        Comparator<Person> nullSafeComparator = Comparator
                .nullsFirst(  // Handle null objects
                        Comparator.comparing(
                                Person::getName,
                                Comparator.nullsFirst(String::compareTo) // Handle null names
                        )
                );

        // 6. Custom comparison logic
        Comparator<Person> customComparator = (p1, p2) -> {
            int nameComparison = p1.getName().compareTo(p2.getName());
            if (nameComparison != 0) return nameComparison;

            if (p1.getSalary() > p2.getSalary()) return 1;
            if (p1.getSalary() < p2.getSalary()) return -1;

            return Integer.compare(p1.getAge(), p2.getAge());
        };
    }
}
