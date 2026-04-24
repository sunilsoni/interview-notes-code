package com.interview.notes.code.year.y2026.april.common.test9;

import java.util.ArrayList; // Imports ArrayList for dynamic array implementation
import java.util.Comparator; // Imports Comparator to define sorting rules
import java.util.List; // Imports List interface for the collection type
import java.util.stream.Collectors; // Imports Collectors to accumulate stream results
import java.util.stream.IntStream; // Imports IntStream to quickly generate large datasets

// Java 21 feature: 'record' automatically creates constructor, getters, equals, hashCode, and toString in one line
record Device(String name, int code) {} 

public class Solution { // Defines the main application class
    
    public static void main(String[] args) { // Standard main method entry point
        runTests(); // Calls the custom testing method to verify logic
    } // Closes main method

    static void runTests() { // Custom method to handle all testing without JUnit
        
        // --- TEST CASE 1: Standard Data --- // Comment marking standard test block
        List<Device> devices = new ArrayList<>(List.of( // Creates a mutable list from standard inputs
            new Device("Mobile", 102), // Instantiates Device 1
            new Device("Laptop", 101), // Instantiates Device 2
            new Device("Tablet", 103), // Instantiates Device 3
            new Device("Watch", 100)   // Instantiates Device 4
        )); // Closes list initialization
        
        devices.sort(Comparator.comparing(Device::name)); // Sorts the list alphabetically by referencing the name accessor
        // Java 21 feature: getFirst() cleanly retrieves the 0th index
        check(devices.getFirst().name().equals("Laptop"), "Sort By Name"); // Validates first item is 'Laptop'
        
        devices.sort(Comparator.comparingInt(Device::code)); // Sorts the list numerically by referencing the code accessor
        check(devices.getFirst().code() == 100, "Sort By Code"); // Validates first item is '100' (Watch)

        // --- TEST CASE 2: Large Data (100,000 records) --- // Comment marking large data test block
        List<Device> largeList = IntStream.range(0, 100000) // Generates a stream of 100,000 integers
            .mapToObj(i -> new Device("Dev" + (100000 - i), 100000 - i)) // Maps each integer to a Device in reverse order
            .collect(Collectors.toCollection(ArrayList::new)); // Collects the stream into a mutable ArrayList
            
        largeList.sort(Comparator.comparingInt(Device::code)); // Sorts the massive list by code
        check(largeList.getFirst().code() == 1, "Large Data Sort (100k records)"); // Validates the smallest code (1) is now first
    } // Closes runTests method

    static void check(boolean condition, String testName) { // Helper method to evaluate conditions and print PASS/FAIL
        if (condition) { // Checks if the passed boolean condition is true
            System.out.println("PASS: " + testName); // Prints success message
        } else { // Fallback if condition is false
            System.out.println("FAIL: " + testName); // Prints failure message
        } // Closes if-else
    } // Closes check method
} // Closes Solution class