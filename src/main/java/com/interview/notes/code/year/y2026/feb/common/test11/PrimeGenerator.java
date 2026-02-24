package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.List;
import java.util.stream.IntStream;

public class PrimeGenerator { // Defines the main class that encapsulates our prime generation logic

    public static List<Integer> getPrimes(int limit) { // Method taking an integer limit and returning a list of primes
        return IntStream.rangeClosed(2, limit) // Creates a stream of consecutive integers starting from 2 up to the given limit
                .filter(PrimeGenerator::isPrime) // Filters the stream, keeping only the numbers that return true from the isPrime method
                .boxed() // Converts primitive 'int' values into 'Integer' objects so they can be stored in a Java Collection
                .toList(); // Java 16+ feature: Collects the filtered integers directly into an unmodifiable List, reducing boilerplate code
    } // Closes the getPrimes method

    private static boolean isPrime(int n) { // Helper method to check if a single given number 'n' is prime
        if (n < 2) return false; // 0 and 1 are not prime numbers by definition, so we immediately return false to exclude them
        return IntStream.rangeClosed(2, (int) Math.sqrt(n)) // Generates a stream of numbers from 2 up to the square root of 'n' to check for factors
                .noneMatch(i -> n % i == 0); // Returns true ONLY if absolutely no number in this stream divides 'n' evenly (remainder 0)
    } // Closes the isPrime helper method

    public static void main(String[] args) { // Standard main method used here as our custom test execution starting point
        
        // Test Case 1: Standard small input
        runTest("Test 1 (Limit 10)", 10, List.of(2, 3, 5, 7)); // Calls our test runner to check if primes up to 10 match the expected list

        // Test Case 2: User's specific request
        runTest("Test 2 (Limit 50)", 50, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)); // Verifies primes up to 50 match expected values

        // Test Case 3: Edge case (No primes)
        runTest("Test 3 (Limit 1)", 1, List.of()); // Tests the edge case of 1, which should correctly return an empty list

        // Test Case 4: Large data input
        long startTime = System.currentTimeMillis(); // Captures the current system time to measure how long the large data test takes
        List<Integer> largeResult = getPrimes(1_000_000); // Generates primes up to 1 million to test performance and memory handling
        long endTime = System.currentTimeMillis(); // Captures the time after generation completes
        boolean passedLarge = largeResult.size() == 78498; // Validates correctness by checking if the exact known count of primes up to 1M is found
        System.out.println("Test 4 (Large Data 1M) - " + (passedLarge ? "PASS" : "FAIL") + " - Took " + (endTime - startTime) + "ms"); // Prints the large data test result and execution time
    } // Closes the main method

    private static void runTest(String testName, int limit, List<Integer> expected) { // Helper method to execute a test and print PASS/FAIL status
        List<Integer> actual = getPrimes(limit); // Calls our main generation method to get the actual calculated primes
        boolean passed = actual.equals(expected); // Compares the calculated list with the expected list to see if they perfectly match
        System.out.println(testName + " - " + (passed ? "PASS" : "FAIL")); // Uses a ternary operator to print the test name followed by PASS or FAIL
        if (!passed) System.out.println("   Expected: " + expected + " | Got: " + actual); // If the test failed, this prints the exact difference for easy debugging
    } // Closes the runTest method

} // Closes the PrimeGenerator class