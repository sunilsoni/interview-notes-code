package com.interview.notes.code.year.y2025.march.city.test1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CustomPredicateExample {
    public static void main(String[] args) {
        // Method 1: Creating a custom Predicate using a separate class
        StartsWithAPredicate startsWithA = new StartsWithAPredicate();

        // Method 2: Creating a Predicate using lambda expression
        Predicate<String> startsWithALambda = name -> name.startsWith("A");

        // Test data
        List<String> names = Arrays.asList("Alex", "Bob", "Anna", "Charlie", "Adam");

        // Using the custom Predicate (Method 1)
        System.out.println("Names starting with 'A' (using custom Predicate class):");
        for (String name : names) {
            if (startsWithA.test(name)) {
                System.out.println(name);
            }
        }

        // Using the lambda Predicate (Method 2)
        System.out.println("\nNames starting with 'A' (using lambda):");
        names.stream()
            .filter(startsWithALambda)
            .forEach(System.out::println);

        // Using stream with filter directly
        System.out.println("\nNames starting with 'A' (using direct stream filter):");
        names.stream()
            .filter(name -> name.startsWith("A"))
            .forEach(System.out::println);
    }
}

// Custom Predicate class
class StartsWithAPredicate implements Predicate<String> {
    @Override
    public boolean test(String name) {
        return name.startsWith("A");
    }
}
