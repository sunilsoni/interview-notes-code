package com.interview.notes.code.year.y2026.june.common.test5;

import java.util.*;
import java.util.stream.Collectors;

record Employee(String name, double salary) {

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 5000),
            new Employee("Bob", 6000),
            new Employee("Charlie", 4000),
            new Employee("David", 7000),
            new Employee("Eve", 7000) // Tied for highest salary
        );

        // ==========================================
        // Approach 1: Simple Skip (Fails on duplicates)
        // ==========================================
        System.out.println("--- Approach 1: Simple Skip ---");
        Optional<Employee> secondHighestSimple = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .skip(1)
            .findFirst();
        
        // This will print 'Eve' because she is technically 2nd in the sorted list, 
        // even though she shares the 1st place salary.
        secondHighestSimple.ifPresent(emp -> 
            System.out.println("Employee: " + emp));

        // ==========================================
        // Approach 2: Grouping (Handles duplicates)
        // ==========================================
        System.out.println("\n--- Approach 2: Grouping by Salary ---");
        Optional<Employee> secondHighestRobust = employees.stream()
            // 1. Group employees by their salary
            .collect(Collectors.groupingBy(Employee::salary))
            // 2. Stream the entries of the resulting Map
            .entrySet().stream()
            // 3. Sort the map entries by salary (the key) in descending order
            .sorted(Map.Entry.<Double, List<Employee>>comparingByKey().reversed())
            // 4. Skip the highest salary group
            .skip(1)
            // 5. Get the list of employees with the second highest salary
            .map(Map.Entry::getValue)
            // 6. Flatten the list and grab the first one (or you could return the whole list)
            .flatMap(List::stream)
            .findFirst();

        // This correctly prints 'Bob' as he holds the second highest unique salary amount.
        secondHighestRobust.ifPresent(emp -> 
            System.out.println("Employee: " + emp));
    }
}