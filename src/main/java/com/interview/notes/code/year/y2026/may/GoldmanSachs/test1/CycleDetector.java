package com.interview.notes.code.year.y2026.may.GoldmanSachs.test1;

import java.util.List; // Imports the List interface to group our test cases

public class CycleDetector { // Main class containing our logic and tests

    // Core method to find the length of the cycle starting from a specific index
    public static int getCycleLength(int[] arr, int startIndex) { // Takes the array and starting point as inputs
        var visited = new int[arr.length]; // Creates an array to log the exact step number when an index is visited
        var step = 1; // Initializes our step counter, starting at 1 for the first jump
        var curr = startIndex; // Sets our current position to the provided starting index

        while (curr >= 0 && curr < arr.length) { // Loops as long as our current index is safely inside the array limits
            if (visited[curr] > 0) { // Checks if the current index already has a step logged (meaning we've been here before)
                return step - visited[curr]; // Calculates cycle length: current step minus the step we first arrived here
            } // Closes the if condition
            
            visited[curr] = step; // Logs the current step number into the visited array for our current position
            step++; // Increments the step counter by 1 to prepare for the next jump
            curr = arr[curr]; // Updates our current position to the new index dictated by the array's value
        } // Closes the while loop
        
        return -1; // If the loop finishes by going out of bounds, no cycle exists, so we return -1
    } // Closes the method

    // Main method acting as our custom testing framework without using JUnit
    public static void main(String[] args) { // Entry point of the program
        var tests = List.of( // Uses List.of to create an immutable list of our test scenarios
            new TestCase(new int[]{1, 2, 0}, 0, 3, "Simple 3-Cycle"), // Test 1: A perfect circular loop of 3 elements
            new TestCase(new int[]{1, 2, 3, 4, 1}, 0, 4, "Tail leading to Cycle"), // Test 2: Starts with a straight line, ends in a loop
            new TestCase(new int[]{1, 2, 3, 4, 5}, 0, -1, "No Cycle (Breaks Out)"), // Test 3: Straight line that jumps out of bounds
            new TestCase(new int[]{0}, 0, 1, "Immediate Self Loop"), // Test 4: An index pointing directly back to itself
            new TestCase(new int[]{1, 2, 3, 1, 4, 5}, 4, -1, "Starts outside cycle"), // Test 5: The array has a cycle, but we start somewhere else that goes out of bounds
            new TestCase(new int[100000], 0, 1, "Large Data Self Loop") // Test 6: Simulates large data input to ensure memory/time efficiency holds up
        ); // Closes the list definition

        // Using Java 8 Stream API to process and evaluate each test case cleanly
        tests.stream().forEach(test -> { // Opens a stream on our list and iterates over each test case object
            var result = getCycleLength(test.arr(), test.start()); // Calls our method with the test case inputs
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Uses a ternary operator to check if actual result matches expected result
            System.out.printf("[%s] %s (Expected: %d, Got: %d)%n", status, test.name(), test.expected(), result); // Prints the formatted PASS/FAIL output
        }); // Closes the stream operation
    } // Closes the main method

    // Java 14+ Record feature to create a clean, immutable object for our test scenarios
    record TestCase(int[] arr, int start, int expected, String name) {} // Defines the fields needed for each test case
} // Closes the class