package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AlternatingPrinter { // Main class declaration

    public static void main(String[] args) { // Standard entry point without needing JUnit
        runTests(); // Calls our custom test suite immediately
    } // Ends the main execution block

    // Core method taking 'pairs' (how many 0s and 1s) and 'delayMs' (pause time)
    static List<Integer> printSequence(int pairs, int delayMs) { // Returns a List so we can verify output
        var result = new ArrayList<Integer>(); // Uses Java 10+ 'var' for less typing; stores generated numbers
        
        // Java 8 Stream: loops from 0 up to (pairs * 2) - 1. E.g., 10 pairs = 20 loops.
        IntStream.range(0, pairs * 2).forEach(i -> { // Functional loop approach
            try { // Required try-catch block because Thread.sleep can be interrupted
                int val = i % 2; // Simple math: Even numbers % 2 = 0. Odd numbers % 2 = 1. No bit-masking needed.
                System.out.println(val); // Fulfills requirement to print the value to the console
                result.add(val); // Adds the printed value to our list so the test method can check it later
                Thread.sleep(delayMs); // Pauses the current Java thread for the requested time
            } catch (InterruptedException e) { // Catches exception if the thread is forcibly stopped
                Thread.currentThread().interrupt(); // Best practice: restores the interrupt flag so the app knows it was stopped
            } // Closes catch block
        }); // Closes the forEach stream loop
        
        return result; // Returns the full list of generated 0s and 1s back to the caller
    } // Closes the generation method

    static void runTests() { // Custom method to handle PASS/FAIL validation internally
        System.out.println("--- Test 1: Standard Check (2 pairs, minimal delay) ---"); // Logs test start
        var test1 = printSequence(2, 10); // Generates sequence quickly with only a 10ms delay
        var expected1 = List.of(0, 1, 0, 1); // Defines exactly what the output should look like
        System.out.println("Test 1 " + (test1.equals(expected1) ? "PASS" : "FAIL")); // Evaluates and prints PASS or FAIL
        
        System.out.println("\n--- Test 2: Large Data Edge Case (10,000 prints, 0ms delay) ---"); // Logs large data test
        var test2 = printSequence(5000, 0); // Tests scale by generating 5000 pairs (10k items) with no delay
        boolean isCorrectSize = test2.size() == 10000; // Verifies the loop ran the exact right amount of times
        boolean isCorrectEdges = test2.getFirst() == 0 && test2.getLast() == 1; // Java 21 feature: getFirst/getLast to verify start and end points
        System.out.println("Test 2 " + (isCorrectSize && isCorrectEdges ? "PASS" : "FAIL")); // Validates logic held up under large data
        
        System.out.println("\n--- Test 3: The Actual Interview Request (10 pairs, 1000ms delay) ---"); // Logs final actual requirement
        System.out.println("Running... this will take 20 seconds."); // Warns the user about the real-time delay
        var actualTest = printSequence(10, 1000); // Runs the exact scenario requested in the prompt
        boolean actualPass = actualTest.size() == 20 && actualTest.getFirst() == 0; // Verifies 20 total prints starting with 0
        System.out.println("Test 3 " + (actualPass ? "PASS" : "FAIL")); // Prints the final result
    } // Closes the test runner method
} // Closes the class