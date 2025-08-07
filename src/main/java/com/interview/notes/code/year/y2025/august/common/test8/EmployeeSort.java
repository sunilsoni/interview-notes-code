package com.interview.notes.code.year.y2025.august.common.test8;

import java.util.*;
import java.util.stream.*;

public class EmployeeSort {
    
    // 1. Define Employee as a record (auto-generates ctor, accessors, equals, hashCode, toString)
    public record Employee(String name, int age) {}

    // 2. Sort by age descending in one stream pipeline
    public static List<Employee> sortByAgeDesc(List<Employee> emps) {
        return emps.stream()
                   .sorted(Comparator.comparingInt(Employee::age).reversed())
                   .toList(); // since Java 16; for Java 8 use .collect(Collectors.toList())
    }

    // 3. Simple main method for a PASS/FAIL check
    public static void main(String[] args) {
        var input = List.of(
            new Employee("Alice", 30),
            new Employee("Bob",   25),
            new Employee("Charlie",35)
        );
        var expected = List.of(
            new Employee("Charlie",35),
            new Employee("Alice",  30),
            new Employee("Bob",    25)
        );
        var actual = sortByAgeDesc(input);
        System.out.println(actual.equals(expected) ? "PASS" : "FAIL");
    }
}