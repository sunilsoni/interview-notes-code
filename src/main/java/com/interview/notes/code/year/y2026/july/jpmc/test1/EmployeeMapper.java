package com.interview.notes.code.year.y2026.july.jpmc.test1;

import java.util.List; // Needed to use the List interface for our input collection
import java.util.Map; // Needed to use the Map interface for our output collection
import java.util.function.Function; // Needed for Function.identity() to return the object itself
import java.util.stream.Collectors; // Needed to use Collectors.toMap to build the map
import java.util.stream.IntStream; // Needed to quickly generate large datasets for testing

// Using Java 14+ 'record' creates a completely immutable class with getters, constructors, equals, and hashcode automatically
record Employee(long id, String name) {} 

public class EmployeeMapper { // The main class that houses our logic and tests

    // The core method that takes a list of employees and returns a map
    public static Map<Long, Employee> listToMap(List<Employee> employees) { // Accepts the employee list
        return employees.stream() // Converts the list into a Java Stream to process items sequentially
                .collect(Collectors.toMap( // Terminal operation that gathers stream elements into a Map
                        Employee::id, // Key mapper: calls the id() method on each Employee to use as the map key
                        Function.identity(), // Value mapper: returns the Employee object exactly as it is for the map value
                        (existing, replacement) -> existing // Merge function: if two IDs are identical, keep the existing one to avoid crashes
                )); // Closes the collect method
    }

    // The main method serves as our custom testing framework
    public static void main(String[] args) { // Standard entry point for a Java application
        
        System.out.println("Running test cases...\n"); // Prints a starting message to the console
        
        // TEST CASE 1: Standard small list
        var emp1 = new Employee(1L, "Alice"); // Creates first test employee using Java 10+ 'var' for type inference
        var emp2 = new Employee(2L, "Bob"); // Creates second test employee
        var list1 = List.of(emp1, emp2); // Creates an immutable list containing these two employees
        var map1 = listToMap(list1); // Calls our conversion method
        // Checks if the map size is 2, and if key '1' maps to Alice
        boolean test1Pass = map1.size() == 2 && map1.get(1L).name().equals("Alice"); 
        System.out.println("Test 1 (Standard List): " + (test1Pass ? "PASS" : "FAIL")); // Prints the result of the first test
        
        // TEST CASE 2: Empty list
        var list2 = List.<Employee>of(); // Creates a completely empty employee list
        var map2 = listToMap(list2); // Converts the empty list
        // An empty list should result in an empty map
        boolean test2Pass = map2.isEmpty(); 
        System.out.println("Test 2 (Empty List): " + (test2Pass ? "PASS" : "FAIL")); // Prints the result of the second test
        
        // TEST CASE 3: Duplicate IDs
        var emp3 = new Employee(3L, "Charlie"); // Creates an employee with ID 3
        var emp4 = new Employee(3L, "Duplicate Charlie"); // Creates a different employee but also with ID 3
        var list3 = List.of(emp3, emp4); // Puts them both in a list
        var map3 = listToMap(list3); // Converts the list. Our merge function should prevent an exception here.
        // The map should only have 1 item, and it should be the first Charlie (due to our merge function)
        boolean test3Pass = map3.size() == 1 && map3.get(3L).name().equals("Charlie"); 
        System.out.println("Test 3 (Duplicate IDs): " + (test3Pass ? "PASS" : "FAIL")); // Prints the result of the third test

        // TEST CASE 4: Large Data Input (1 Million Records)
        var largeList = IntStream.range(0, 1_000_000) // Generates a stream of integers from 0 to 999,999
                .mapToObj(i -> new Employee(i, "Emp" + i)) // Maps each integer into a new Employee object
                .toList(); // Collects the 1 million objects into a list (Java 16+ feature)
        
        long startTime = System.currentTimeMillis(); // Records the current time before conversion starts
        var largeMap = listToMap(largeList); // Converts the 1 million item list to a map
        long endTime = System.currentTimeMillis(); // Records the time after conversion finishes
        
        // Checks if the map successfully holds exactly 1,000,000 items
        boolean test4Pass = largeMap.size() == 1_000_000; 
        System.out.println("Test 4 (1,000,000 items): " + (test4Pass ? "PASS" : "FAIL")); // Prints the result of the stress test
        System.out.println("Large data processing took: " + (endTime - startTime) + " milliseconds."); // Prints how fast the stream was
    }
}